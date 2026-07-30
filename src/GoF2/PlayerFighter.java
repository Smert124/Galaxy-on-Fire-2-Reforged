package GoF2;

import AE.AbstractMesh;
import AE.BoundingVolume;
import AE.GlobalStatus;
import AE.GraphNode;
import AE.Math.AEMath;
import AE.Math.AEVector3D;
import AE.Math.Matrix;
import HardEngine.MicroNeuron;

public final class PlayerFighter extends KIPlayer {

   private int deathTransitionTime = 5000;
   private int sightRange = 50000;
   private int soundRange = 500;
   private float defaultSpeed = 2.0F;
   private float defaultHandling = 2.0F;
   private int boostChance = 20;
   private AEVector3D pos = new AEVector3D();
   private AEVector3D initPos = new AEVector3D();
   private AEVector3D targetPos_ = new AEVector3D();
   private AEVector3D distanceToTarget = new AEVector3D();
   private static Matrix tempTransform = new Matrix();
   private int targedId_;
   private boolean canBoost;
   private int behavioutChangeTick;
   private int boostTick;
   private int boostTimeLimit;
   private int trailTick;
   private Player target__;
   private boolean folowingRoute;
   private long frameTime;
   private int hitpoints;
   private int damageAccumulator;
   private boolean damageEscape;
   private Route activeRoute;
   private int initialWaypoint;
   private int weaponActive;
   private Trail trail;
   private boolean strayFromTarget_;
   private float speed;
   private float handling;
   private BoundingVolume[] boundings;
   private boolean targetIsActive;
   private boolean collisionEnabled;
   private boolean unused821_;
   private int crateTime_;
   
   private MicroNeuron brain;
   private float[] brainInputs = new float[8];
   private float[] lastInputs = new float[8];
   private float lastDecision = 0.5f;
   private float[] rewardHistory = new float[20];
   private int rewardIndex = 0;
   private int neuralActionCounter = 0;
   private boolean neuralActive = false;
   private int neuralLearnTick = 0;
   private float accumulatedReward = 0.5f;
   private int deathCounter = 0;
   private int killCounter = 0;
   private int cooperationTarget = -1;
   private boolean isCoopLeader = false;
   private int coopCommandTick = 0;
   private float lastReward = 0.5f;
   private boolean hasVoidTarget = false;
   
   private static float[] sharedWeights = null;
   private static int totalDeaths = 0;
   private static int totalKills = 0;
   private static float globalRewardSum = 0.0f;
   private static int globalRewardCount = 0;
   
   private static Route defaultRoute_ = new Route(new int[] {
          20000, 0, 20000,
          20000, 0, -20000,
          -20000, 0, -20000,
          -20000, 0, 20000
          });
   private static Route stationRouteAliens = new Route(new int[] {
          40000, 0, 40000,
          40000, 0, -40000,
          -40000, 0, -40000,
          -40000, 0, 40000
          });

   public PlayerFighter(int var1, int var2, Player var3, AbstractMesh var4, int var5, int var6, int var7) {
      super(var1, var2, var3, (AbstractMesh)null, var5, var6, var7);
      this.race = var2;
      this.initPos.set(var5, var6, var7);
      this.hasCargo = true;
	  
      if(GlobalStatus.EFFECTS_QUALITY) {
          this.trail = new Trail(var2 != 9 && var2 != 8?7:5, 16);
          this.trail.setWidth(100);
          this.trail.reset(this.initPos);
      }
	  
      this.canBoost = true;
      this.speed = this.defaultSpeed;
      this.handling = this.defaultHandling;
      defaultRoute_.setLoop(true);
      stationRouteAliens.setLoop(true);
      this.activeRoute_ = var2 == 9?stationRouteAliens.clone():defaultRoute_.clone();
      this.strayFromTarget_ = false;
      
      if(var2 == 9) {
         this.cargo = null;
      } else {
         Generator var8 = new Generator();
         this.cargo = var8.getLootList();
      }

      this.sightRange = Status.inAlienOrbit()?100000:50000;
      this.collisionEnabled = true;
      this.hitpoints = this.player.getHitpoints();
      this.damageAccumulator = 0;
      this.damageEscape = false;
      this.wingmanCommand = 0;
      this.armed = true;
      this.lastReward = 0.5f;
      this.hasVoidTarget = false;
      
      if(GlobalStatus.NEURAL_NPC) {
          this.initNeuralNetwork();
      }
   }
   
   private void initNeuralNetwork() {
       float[] weights;
       
       if(sharedWeights != null && sharedWeights.length == 8) {
           weights = new float[8];
           System.arraycopy(sharedWeights, 0, weights, 0, 8);
       } else {
           weights = new float[8];
           for(int i = 0; i < weights.length; i++) {
               float variation = (GlobalStatus.random.nextInt(40) - 20) / 100.0f;
               weights[i] = (GlobalStatus.random.nextInt(200) - 100) / 100.0f + variation;
           }
       }
       
       this.brain = new MicroNeuron(weights);
       this.neuralActive = true;
       this.neuralActionCounter = 0;
       
       for(int i = 0; i < rewardHistory.length; i++) {
           rewardHistory[i] = 0.5f;
       }
       this.lastReward = 0.5f;
   }

   private float[] collectNeuralInputs() {
       int distToTarget = this.distanceToTarget.getLength();
       brainInputs[0] = (float)distToTarget / (float)this.sightRange;
       if(brainInputs[0] > 1.0f) brainInputs[0] = 1.0f;
       
       AEVector3D dir = this.geometry.getDirection(new AEVector3D());
       int dot = dir.dot(this.distanceToTarget);
       int dirLen = dir.getLength();
       int targetLen = this.distanceToTarget.getLength();
       if(dirLen > 0 && targetLen > 0) {
           int cosAngle = dot / (dirLen * targetLen / 4096);
           brainInputs[1] = (float)(4096 - cosAngle) / 8192.0f;
       } else {
           brainInputs[1] = 0.5f;
       }
       
       brainInputs[2] = (float)this.player.getHitpoints() / (float)this.player.getMaxHitpoints();
       
       Player[] enemies = this.player.getEnemies();
       int nearbyEnemies = 0;
       if(enemies != null) {
           for(int i = 0; i < enemies.length; i++) {
               if(enemies[i] != null && enemies[i].isActive() && !enemies[i].isDead()) {
                   AEVector3D enemyPos = enemies[i].getPosition(new AEVector3D());
                   if(enemyPos != null) {
                       int dist = this.pos.distanceTo(enemyPos);
                       if(dist < 50000) {
                           nearbyEnemies++;
                       }
                   }
               }
           }
       }
       brainInputs[3] = nearbyEnemies > 5 ? 1.0f : nearbyEnemies / 5.0f;
       
       boolean hasAmmo = false;
       if(this.player.guns != null) {
           for(int i = 0; i < this.player.guns.length && !hasAmmo; i++) {
               if(this.player.guns[i] != null && this.player.guns[i].length > 0) {
                   for(int j = 0; j < this.player.guns[i].length; j++) {
                       if(this.player.guns[i][j] != null && this.player.guns[i][j].ammo > 0) {
                           hasAmmo = true;
                           break;
                       }
                   }
               }
           }
       }
       brainInputs[4] = hasAmmo ? 1.0f : 0.0f;
       brainInputs[5] = this.speed / 3.0f;
       
       int alliesNearby = this.countAlliesNearby();
       brainInputs[6] = alliesNearby > 5 ? 1.0f : alliesNearby / 5.0f;
       
       float dangerLevel = (1.0f - brainInputs[2]) * 0.5f + brainInputs[3] * 0.5f;
       brainInputs[7] = dangerLevel;
       
       return brainInputs;
   }
   
   private int countAlliesNearby() {
       int count = 0;
       if(this.var_36b == null) return 0;
       
       KIPlayer[] allShips = this.var_36b.getEnemies();
       if(allShips == null) return 0;
       
       for(int i = 0; i < allShips.length; i++) {
           if(allShips[i] != null && allShips[i] != this) {
               if(allShips[i].race == this.race && allShips[i].isVisible()) {
                   AEVector3D otherPos = allShips[i].getPosition(new AEVector3D());
                   if(otherPos != null) {
                       int dist = this.pos.distanceTo(otherPos);
                       if(dist < 30000) {
                           count++;
                       }
                   }
               }
           }
       }
       return count;
   }
   
   private boolean hasVoidEnemies() {
       Player[] enemies = this.player.getEnemies();
       if(enemies == null) return false;
       
       for(int i = 0; i < enemies.length; i++) {
           if(enemies[i] != null && enemies[i].isActive() && !enemies[i].isDead()) {
               KIPlayer ki = enemies[i].getKIPlayer();
               if(ki != null && ki.race == 9) {
                   return true;
               }
           }
       }
       return false;
   }

   private float predictNeural() {
       if(!neuralActive || brain == null) {
           return GlobalStatus.random.nextInt(100) / 100.0f;
       }
       
       float[] inputs = collectNeuralInputs();
       System.arraycopy(inputs, 0, lastInputs, 0, inputs.length);
       lastDecision = brain.predict(inputs);
       return lastDecision;
   }
   
   private void learnNeural(float reward) {
       if(!neuralActive || brain == null) return;
       
       neuralLearnTick++;
       if(neuralLearnTick < 5) return;
       neuralLearnTick = 0;
       
       float learningRate = 0.03f + (1.0f - reward) * 0.07f;
       brain.learn(lastInputs, reward, learningRate);
       
       if(sharedWeights == null || sharedWeights.length != 8) {
           sharedWeights = new float[8];
       }
       float[] weights = brain.getWeights();
       if(weights != null && weights.length == 8) {
           System.arraycopy(weights, 0, sharedWeights, 0, 8);
       }
       
       globalRewardSum += reward;
       globalRewardCount++;
   }
   
   private float calculateNeuralReward() {
       float reward = 0.5f;
       
       if(this.target__ != null && this.target__.isDead()) {
           reward += 0.5f;
           killCounter++;
           totalKills++;
       }
       
       if(this.target__ != null && !this.target__.isDead()) {
           int hpLost = this.target__.getMaxHitpoints() - this.target__.getHitpoints();
           if(hpLost > 0) {
               reward += 0.1f;
           }
       }
       
       int currentHP = this.player.getHitpoints();
       if(currentHP < this.hitpoints) {
           float damagePercent = (float)(this.hitpoints - currentHP) / (float)this.player.getMaxHitpoints();
           reward -= damagePercent * 0.3f;
       }
       this.hitpoints = currentHP;
       
       if(this.isDead()) {
           reward -= 1.0f;
           deathCounter++;
           totalDeaths++;
           
           if(deathCounter > 0 && killCounter > 0) {
               float kdRatio = (float)killCounter / (float)(deathCounter + 1);
               if(kdRatio < 0.5f) {
                   reward = 0.0f;
               }
           }
       }
       
       int alliesNearby = countAlliesNearby();
       if(alliesNearby > 1) {
           if(this.target__ != null && this.cooperationTarget >= 0) {
               if(this.var_36b != null) {
                   KIPlayer[] allShips = this.var_36b.getEnemies();
                   int alliesAttackingSameTarget = 0;
                   for(int i = 0; i < allShips.length; i++) {
                       if(allShips[i] != null && allShips[i] != this && 
                          allShips[i].race == this.race && allShips[i] instanceof PlayerFighter) {
                           PlayerFighter ally = (PlayerFighter)allShips[i];
                           if(ally.target__ != null && ally.target__ == this.target__) {
                               alliesAttackingSameTarget++;
                           }
                       }
                   }
                   if(alliesAttackingSameTarget > 0) {
                       reward += 0.15f;
                   }
               }
           }
       }
       
       if(this.target__ != null && this.target__.isDead()) {
           KIPlayer ki = this.target__.getKIPlayer();
           if(ki != null && ki.race == 9) {
               reward += 0.3f;
           }
       }
       
       if(!this.isDead() && currentHP > this.player.getMaxHitpoints() * 0.7f) {
           reward += 0.05f;
       }
       
       if(reward < 0.0f) reward = 0.0f;
       if(reward > 1.0f) reward = 1.0f;
       
       return reward;
   }
   
   private void applyNeuralDecision(float decision) {
       if(!this.wingman) {
           handleCooperation();
       }
       
       // Проверяем, есть ли цель
       if(this.target__ == null || !this.target__.isActive() || this.target__.isDead()) {
           Player[] enemies = this.player.getEnemies();
           if(enemies != null) {
               int bestVoidId = -1;
               int bestEnemyId = -1;
               int nearestVoidDist = Integer.MAX_VALUE;
               int nearestEnemyDist = Integer.MAX_VALUE;
               
               // Проверяем всех врагов, включая игрока
               for(int i = 0; i < enemies.length; i++) {
                   if(enemies[i] != null && enemies[i].isActive() && !enemies[i].isDead()) {
                       boolean isEnemy = false;
                       boolean isVoid = false;
                       
                       KIPlayer ki = enemies[i].getKIPlayer();
                       
                       // Если это игрок (ki == null) - он враг для пиратов и войдов
                       if(ki == null) {
                           if(this.race == 8 || this.race == 9 || this.player.enemy) {
                               isEnemy = true;
                           }
                       } else {
                           if(ki.race == 9) {
                               isVoid = true;
                               isEnemy = true;
                           } else if(this.race == 8 || this.race == 9) {
                               isEnemy = true;
                           } else if(this.race != 8 && ki.race == 8 || 
                                     this.race == 8 && ki.race != 8 ||
                                     this.race != 9 && ki.race == 9 ||
                                     this.race == 9 && ki.race != 9 ||
                                     this.race == 1 && ki.race == 0 ||
                                     this.race == 0 && ki.race == 1 ||
                                     this.race == 2 && ki.race == 3 ||
                                     this.race == 3 && ki.race == 2) {
                               isEnemy = true;
                           }
                       }
                       
                       if(isEnemy) {
                           AEVector3D enemyPos = enemies[i].getPosition(new AEVector3D());
                           if(enemyPos != null) {
                               int dist = this.pos.distanceTo(enemyPos);
                               if(dist < this.sightRange) {
                                   if(isVoid && dist < nearestVoidDist) {
                                       nearestVoidDist = dist;
                                       bestVoidId = i;
                                   } else if(!isVoid && dist < nearestEnemyDist) {
                                       nearestEnemyDist = dist;
                                       bestEnemyId = i;
                                   }
                               }
                           }
                       }
                   }
               }
               
               if(bestVoidId != -1) {
                   this.target__ = enemies[bestVoidId];
                   this.targedId_ = bestVoidId;
                   this.targetIsActive = true;
                   this.hasVoidTarget = true;
               } else if(bestEnemyId != -1) {
                   this.target__ = enemies[bestEnemyId];
                   this.targedId_ = bestEnemyId;
                   this.targetIsActive = true;
                   this.hasVoidTarget = false;
               }
               
               if(this.target__ != null) {
                   AEVector3D enemyPos = this.target__.getPosition(new AEVector3D());
                   if(enemyPos != null) {
                       this.targetPos_.x = enemyPos.x;
                       this.targetPos_.y = enemyPos.y;
                       this.targetPos_.z = enemyPos.z;
                   }
               }
           }
       }
       
       // Если цели нет - следуем маршруту
       if(this.target__ == null || !this.target__.isActive() || this.target__.isDead()) {
           if(this.activeRoute_ != null) {
               this.folowingRoute = true;
               if(this.activeRoute_.getDockingTarget_() != null && this.player.isActive()) {
                   this.activeRoute_.update(this.player.transform.getPositionX(), 
                                           this.player.transform.getPositionY(), 
                                           this.player.transform.getPositionZ());
                   if(this.activeRoute_.getDockingTarget_() != null) {
                       this.targetPos_.x = this.activeRoute_.getDockingTarget_().x;
                       this.targetPos_.y = this.activeRoute_.getDockingTarget_().y;
                       this.targetPos_.z = this.activeRoute_.getDockingTarget_().z;
                   }
               }
           }
           return;
       }
       
       // Обновляем дистанцию до цели
       this.distanceToTarget.x = this.targetPos_.x - this.pos.x;
       this.distanceToTarget.y = this.targetPos_.y - this.pos.y;
       this.distanceToTarget.z = this.targetPos_.z - this.pos.z;
       
       if(decision < 0.30f) {
           // Агрессивная атака - используем оригинальную логику стрельбы
           int dist = this.distanceToTarget.getLength();
           if(dist < 35000 && dist > 0) {
               // Оригинальная проверка из стандартного кода
               if(this.target__ != null && !this.target__.var_91d && this.target__.isActive() && !this.target__.isDead()) {
                   this.player.shoot_(this.weaponActive, this.frameTime, false);
               }
               
               if(countAlliesNearby() > 1 && dist < 20000) {
                   if(this.canBoost && this.speed < 3.0f) {
                       this.speed = 3.0f;
                       this.handling = 1.3f;
                       this.boostTick = 0;
                   }
               }
           }
           this.strayFromTarget_ = false;
       } else if(decision < 0.60f) {
           // Тактическое маневрирование
           if(this.canBoost && this.speed < 3.0f) {
               if(this.player.getHitpoints() < this.player.getMaxHitpoints() * 0.6f) {
                   this.damageEscape = true;
                   this.speed = 3.0f;
                   this.handling = 1.3f;
                   this.boostTick = 0;
               }
           }
           this.strayFromTarget_ = true;
       } else {
           // Активная фаза - буст
           if(this.canBoost) {
               this.boostTick = 0;
               this.speed = 3.0f;
               this.handling = 1.3f;
           }
       }
   }
   
   private void handleCooperation() {
       coopCommandTick++;
       
       if(coopCommandTick > 100) {
           coopCommandTick = 0;
           
           int alliesNearby = countAlliesNearby();
           boolean hasVoids = hasVoidEnemies();
           
           if(alliesNearby > 2 && !this.isCoopLeader) {
               KIPlayer leader = findCoopLeader();
               if(leader != null && leader instanceof PlayerFighter) {
                   PlayerFighter leaderFighter = (PlayerFighter)leader;
                   if(leaderFighter.target__ != null) {
                       this.target__ = leaderFighter.target__;
                       this.targedId_ = -1;
                       this.targetIsActive = true;
                       this.hasVoidTarget = leaderFighter.hasVoidTarget;
                   }
               }
           }
           
           if(alliesNearby > 3 && this.isCoopLeader) {
               if(this.target__ != null && !this.target__.isDead()) {
                   if(this.hasVoidTarget && this.target__.getHitpoints() < this.target__.getMaxHitpoints() * 0.2f) {
                       findCooperativeTarget(true);
                   } else if(!this.hasVoidTarget && hasVoids) {
                       findCooperativeTarget(true);
                   } else if(this.target__.getHitpoints() < this.target__.getMaxHitpoints() * 0.2f) {
                       findCooperativeTarget(false);
                   }
               } else {
                   findCooperativeTarget(hasVoids);
               }
           }
       }
   }
   
   private KIPlayer findCoopLeader() {
       if(this.var_36b == null) return null;
       
       KIPlayer[] allShips = this.var_36b.getEnemies();
       if(allShips == null) return null;
       
       KIPlayer nearestLeader = null;
       int nearestDist = Integer.MAX_VALUE;
       
       for(int i = 0; i < allShips.length; i++) {
           if(allShips[i] != null && allShips[i] != this && 
              allShips[i].race == this.race && allShips[i] instanceof PlayerFighter) {
               PlayerFighter fighter = (PlayerFighter)allShips[i];
               if(fighter.isCoopLeader) {
                   AEVector3D leaderPos = fighter.getPosition(new AEVector3D());
                   if(leaderPos != null) {
                       int dist = this.pos.distanceTo(leaderPos);
                       if(dist < nearestDist) {
                           nearestDist = dist;
                           nearestLeader = allShips[i];
                       }
                   }
               }
           }
       }
       return nearestLeader;
   }
   
   private KIPlayer findNearestAlly() {
       if(this.var_36b == null) return null;
       
       KIPlayer[] allShips = this.var_36b.getEnemies();
       if(allShips == null) return null;
       
       KIPlayer nearest = null;
       int nearestDist = Integer.MAX_VALUE;
       
       for(int i = 0; i < allShips.length; i++) {
           if(allShips[i] != null && allShips[i] != this && 
              allShips[i].race == this.race && allShips[i].isVisible()) {
               AEVector3D otherPos = allShips[i].getPosition(new AEVector3D());
               if(otherPos != null) {
                   int dist = this.pos.distanceTo(otherPos);
                   if(dist < nearestDist) {
                       nearestDist = dist;
                       nearest = allShips[i];
                   }
               }
           }
       }
       return nearest;
   }
   
   private void findCooperativeTarget(boolean preferVoids) {
       Player[] enemies = this.player.getEnemies();
       if(enemies == null) return;
       
       Player bestVoidTarget = null;
       Player bestTarget = null;
       int highestVoidThreat = 0;
       int highestThreat = 0;
       
       for(int i = 0; i < enemies.length; i++) {
           if(enemies[i] != null && enemies[i].isActive() && !enemies[i].isDead()) {
               int threatLevel = enemies[i].getMaxHitpoints() - enemies[i].getHitpoints();
               AEVector3D enemyPos = enemies[i].getPosition(new AEVector3D());
               if(enemyPos != null) {
                   int dist = this.pos.distanceTo(enemyPos);
                   if(dist < 50000) {
                       threatLevel += 50000 - dist;
                   }
               }
               
               KIPlayer ki = enemies[i].getKIPlayer();
               if(ki != null && ki.race == 9) {
                   threatLevel += 10000;
                   if(threatLevel > highestVoidThreat) {
                       highestVoidThreat = threatLevel;
                       bestVoidTarget = enemies[i];
                   }
               } else if(threatLevel > highestThreat) {
                   highestThreat = threatLevel;
                   bestTarget = enemies[i];
               }
           }
       }
       
       if(preferVoids && bestVoidTarget != null) {
           this.target__ = bestVoidTarget;
           this.targetIsActive = true;
           this.targedId_ = -1;
           this.isCoopLeader = true;
           this.cooperationTarget = this.targedId_;
           this.hasVoidTarget = true;
       } else if(bestTarget != null) {
           this.target__ = bestTarget;
           this.targetIsActive = true;
           this.targedId_ = -1;
           this.isCoopLeader = true;
           this.cooperationTarget = this.targedId_;
           this.hasVoidTarget = false;
       } else if(bestVoidTarget != null) {
           this.target__ = bestVoidTarget;
           this.targetIsActive = true;
           this.targedId_ = -1;
           this.isCoopLeader = true;
           this.cooperationTarget = this.targedId_;
           this.hasVoidTarget = true;
       }
   }

   public final void OnRelease() {
      super.OnRelease();
      if(GlobalStatus.EFFECTS_QUALITY && this.trail != null) {
         this.trail.OnRelease();
      }
      this.trail = null;
      this.target__ = null;
      this.boundings = null;
      
      if(GlobalStatus.NEURAL_NPC && brain != null) {
          float[] weights = brain.getWeights();
          if(weights != null && weights.length == 8) {
              if(sharedWeights == null) {
                  sharedWeights = new float[8];
              }
              System.arraycopy(weights, 0, sharedWeights, 0, 8);
          }
      }
      this.brain = null;
   }

   public final void setMissionCrate(boolean var1) {
      this.carriesMissionCrate = var1;
      if(var1) {
         this.cargo = null;
         this.cargo = new int[2];
         this.cargo[0] = Status.getMission().getType() == 5?116:117;
         this.cargo[1] = 1;
      }
   }

   public final boolean lostMissionCrateToEgo() {
      return this.lostMissionCrateToEgo;
   }

   public final boolean diedWithMissionCrate() {
      return this.diedWithMissionCrate;
   }

   public final boolean lostCargo() {
      return !this.hasCargo;
   }

   public final boolean unk151_() {
      return false;
   }

   public final void setPosition(int var1, int var2, int var3) {
      this.var_4c6 = var1;
      this.var_4e6 = var2;
      this.var_521 = var3;
      this.geometry.moveTo(var1, var2, var3);
      this.initPos.set(var1, var2, var3);
      if(GlobalStatus.EFFECTS_QUALITY && this.trail != null) {
         this.trail.reset(this.initPos);
      }
   }

   public final void setWingmanCommand(int var1, KIPlayer var2) {
      super.setWingmanCommand(var1, var2);
      switch(var1) {
      case 0:
         this.damageEscape = false;
         this.canBoost = false;
         break;
      case 1:
         this.weaponActive = this.weaponActive == 0?1:0;
         break;
      case 2:
         this.damageEscape = false;
         this.canBoost = false;
         break;
      case 3:
      case 4:
         this.behavioutChangeTick = 5001;
         if(this.speed != 3.0F) {
            this.boostTick = 5001;
         }
         if(var1 == 3 && this.var_36b.getPlayerRoute() != null) {
            this.activeRoute = this.var_36b.getPlayerRoute().getExactClone();
            this.initialWaypoint = this.activeRoute.getCurrent();
         }
      }
      this.speed = this.defaultSpeed;
      this.handling = this.defaultHandling;
   }

   public final void setSpeed(int var1) {
      this.canBoost = false;
      this.defaultSpeed = (float)var1;
      this.speed = this.defaultSpeed;
   }

   public final void setRotate(int var1) {
      this.canBoost = false;
      this.defaultHandling = (float)3;
      this.handling = this.defaultHandling;
   }

   public final void setBoostProb(int var1) {
      this.boostChance = var1;
   }

   public final void removeTrail() {
      this.trail = null;
   }

   public final void setExhaustVisible(boolean var1) {
      for(GraphNode var2 = this.geometry.getEndNode(); var2 != null; var2 = var2.getParent()) {
         if(var2.getID() == 13067 || var2.getID() == 13068 || var2.getID() == 13070 || var2.getID() == 13064 || var2.getID() == 13065 || var2.getID() == 13071 || var2.getID() == 14072 || var2.getID() >= 20000 && var2.getID() <= 20100 || var2.getID() >= 21000 && var2.getID() <= 21100) {
            var2.setDraw(var1);
         }
      }
   }

   public final void update(long var1) {
      if(this.state == 4 && !this.hasCargo) {
         this.setActive(false);
      } else {
         this.boostTick = (int)((long)this.boostTick + var1);
         this.behavioutChangeTick = (int)((long)this.behavioutChangeTick + var1);
         if(this.race == 1) {
            this.race = 1;
         }

         this.frameTime = var1;
         this.pos = this.geometry.getPosition(this.pos);
         
         this.player.enemy = this.race != 8 && this.race != 9?(this.isWingman()?false:Status.getStanding().isEnemy(this.race)):true;
         this.player.friend = this.race != 8 && this.race != 9?(this.isWingman()?true:Status.getStanding().isFriend(this.race)):false;
         
         if(GlobalStatus.NEURAL_NPC && this.neuralActive && !this.wingman) {
             if(this.race == 8 || this.race == 9) {
                 this.player.enemy = true;
                 this.player.friend = false;
             }
         }
         
         if(this.player.isAlwaysEnemy()) {
            this.player.enemy = true;
            this.player.friend = false;
         }
         if(this.player.isAlwaysFriend()) {
            this.player.friend = true;
            this.player.enemy = false;
         }

         if(!this.wingman && this.activeRoute_ == null) {
            this.activeRoute_ = defaultRoute_.clone();
         }

         this.trailTick = (int)((long)this.trailTick + var1);
         if(this.trailTick > 200) {
            if(GlobalStatus.EFFECTS_QUALITY && this.trail != null) {
               this.trail.update(this.pos);
            }
            this.trailTick = 0;
         }

         this.player.transform = this.geometry.getToParentTransform();
         
         if(this.wingman && this.state != 4 && this.activeRoute_ != null) {
            this.tempVector_ = this.var_36b.getPlayer().shipGrandGroup_.getPosition(this.tempVector_);
            if(this.wingmanCommand == 2) {
               switch(this.wingmanId) {
               case 0:
                  this.position = this.var_36b.getPlayer().shipGrandGroup_.getRightVector(this.position);
                  this.position.x >>= 1;
                  this.position.y >>= 1;
                  this.position.z >>= 1;
                  this.tempVector_.subtract(this.position);
                  this.tempVector_.add(this.var_36b.getPlayer().shipGrandGroup_.getDirection(this.position));
                  break;
               case 1:
                  this.position = this.var_36b.getPlayer().shipGrandGroup_.getRightVector(this.position);
                  this.position.x >>= 1;
                  this.position.y >>= 1;
                  this.position.z >>= 1;
                  this.tempVector_.add(this.position);
                  this.tempVector_.add(this.var_36b.getPlayer().shipGrandGroup_.getDirection(this.position));
                  break;
               case 2:
                  this.position = this.var_36b.getPlayer().shipGrandGroup_.getDirection(this.position);
                  this.tempVector_.subtract(this.position);
               }
            }
            if(this.activeRoute_.length() > 1) {
               this.activeRoute_ = new Route(new int[]{this.tempVector_.x, this.tempVector_.y, this.tempVector_.z});
            } else {
               this.activeRoute_.setNewCoords(this.tempVector_);
            }
            this.activeRoute_.setLoop(true);
         }

         int var4;
         int var5;
         if(this.state != 4 && this.state != 3) {
            this.player.update(var1);
            Player[] var3;
            if((var3 = this.player.getEnemies()) != null) {
               if(!this.targetIsActive) {
                  this.targedId_ = -1;
               } else if(this.targedId_ >= 0 && !var3[this.targedId_].isActive()) {
                  this.targetIsActive = false;
               }

               this.target__ = null;
               
               if(GlobalStatus.NEURAL_NPC && this.neuralActive && !this.wingman) {
                   boolean hasVoids = hasVoidEnemies();
                   
                   if(this.targedId_ == -1 || !this.targetIsActive) {
                       if(this.target__ != null && this.target__.isActive() && !this.target__.isDead()) {
                           this.targetIsActive = true;
                       } else {
                           int bestVoidId = -1;
                           int bestEnemyId = -1;
                           int nearestVoidDist = Integer.MAX_VALUE;
                           int nearestEnemyDist = Integer.MAX_VALUE;
                                                      for(int i = 0; i < var3.length; i++) {
                               if(var3[i] != null && var3[i].isActive() && !var3[i].isDead()) {
                                   boolean isEnemy = false;
                                   boolean isVoid = false;
                                   
                                   KIPlayer ki = var3[i].getKIPlayer();
                                   
                                   // Если это игрок (ki == null)
                                   if(ki == null) {
                                       if(this.race == 8 || this.race == 9 || this.player.enemy) {
                                           isEnemy = true;
                                       }
                                   } else {
                                       if(ki.race == 9) {
                                           isVoid = true;
                                           isEnemy = true;
                                       } else if(this.race == 8 || this.race == 9) {
                                           isEnemy = true;
                                       } else if(this.player.isAlwaysEnemy()) {
                                           isEnemy = true;
                                       } else if(this.race != 8 && ki.race == 8 || 
                                                 this.race == 8 && ki.race != 8 ||
                                                 this.race != 9 && ki.race == 9 ||
                                                 this.race == 9 && ki.race != 9 ||
                                                 this.race == 1 && ki.race == 0 ||
                                                 this.race == 0 && ki.race == 1 ||
                                                 this.race == 2 && ki.race == 3 ||
                                                 this.race == 3 && ki.race == 2) {
                                           isEnemy = true;
                                       }
                                   }
                                   
                                   if(isEnemy) {
                                       AEVector3D enemyPos = var3[i].getPosition(new AEVector3D());
                                       if(enemyPos != null) {
                                           int dist = this.pos.distanceTo(enemyPos);
                                           if(dist < this.sightRange) {
                                               if(isVoid && dist < nearestVoidDist) {
                                                   nearestVoidDist = dist;
                                                   bestVoidId = i;
                                               } else if(!isVoid && dist < nearestEnemyDist) {
                                                   nearestEnemyDist = dist;
                                                   bestEnemyId = i;
                                               }
                                           }
                                       }
                                   }
                               }
                           }
                           
                           if(bestVoidId != -1) {
                               this.targedId_ = bestVoidId;
                               this.targetIsActive = true;
                               this.hasVoidTarget = true;
                           } else if(bestEnemyId != -1) {
                               this.targedId_ = bestEnemyId;
                               this.targetIsActive = true;
                               this.hasVoidTarget = false;
                           } else {
                               this.targedId_ = -1;
                               this.targetIsActive = false;
                               this.hasVoidTarget = false;
                           }
                       }
                   }
                   
                   if(this.targedId_ >= 0 && this.targedId_ < var3.length) {
                       this.target__ = var3[this.targedId_];
                       if(this.target__ != null && this.target__.isActive() && !this.target__.isDead()) {
                           this.tempVector_ = this.target__.getPosition(this.tempVector_);
                           this.targetPos_.x = this.tempVector_.x;
                           this.targetPos_.y = this.tempVector_.y;
                           this.targetPos_.z = this.tempVector_.z;
                       } else {
                           this.target__ = null;
                           this.targetIsActive = false;
                           this.targedId_ = -1;
                           this.hasVoidTarget = false;
                       }
                   } else {
                       this.target__ = null;
                       this.targetIsActive = false;
                       this.targedId_ = -1;
                       this.hasVoidTarget = false;
                   }
                   
                   if(this.target__ == null && this.activeRoute_ != null) {
                       this.folowingRoute = true;
                       if(this.activeRoute_.getDockingTarget_() != null && this.player.isActive()) {
                           this.activeRoute_.update(this.player.transform.getPositionX(), 
                                                   this.player.transform.getPositionY(), 
                                                   this.player.transform.getPositionZ());
                           if(this.activeRoute_.getDockingTarget_() != null) {
                               this.targetPos_.x = this.activeRoute_.getDockingTarget_().x;
                               this.targetPos_.y = this.activeRoute_.getDockingTarget_().y;
                               this.targetPos_.z = this.activeRoute_.getDockingTarget_().z;
                           }
                       }
                   } else {
                       this.folowingRoute = false;
                   }
               } else {
                   // Оригинальная логика выбора цели
                   if(this.behavioutChangeTick > 5000) {
                      boolean var10001;
                      PlayerFighter var10000;
                      label654: {
                         if(this.strayFromTarget_) {
                            var10000 = this;
                         } else {
                            var10000 = this;
                            if(GlobalStatus.random.nextInt(100) < 20) {
                               var10001 = true;
                               break label654;
                            }
                         }
                         var10001 = false;
                      }

                      label649: {
                         var10000.strayFromTarget_ = var10001;
                         this.behavioutChangeTick = 0;
                         if(GlobalStatus.random.nextInt(100) < 30 && var3.length > 1) {
                            this.targetIsActive = false;

                            for(var4 = 0; var4 < 5 && !this.targetIsActive; ++var4) {
                               this.targedId_ = GlobalStatus.random.nextInt(var3.length);
                               if(var3[this.targedId_].isActive()) {
                                  this.tempVector_ = var3[this.targedId_].getPosition(this.tempVector_);
                                  if(this.race != 8 && this.player.isAlwaysEnemy() || this.pos.x - this.tempVector_.x < this.sightRange && this.pos.x - this.tempVector_.x > -this.sightRange && this.pos.y - this.tempVector_.y < this.sightRange && this.pos.y - this.tempVector_.y > -this.sightRange && this.pos.z - this.tempVector_.z < this.sightRange && this.pos.z - this.tempVector_.z > -this.sightRange) {
                                     this.targetIsActive = true;
                                     break;
                                  }
                               }
                            }

                            if(this.targetIsActive) {
                               break label649;
                            }
                         }
                         this.targedId_ = 0;
                      }

                      if(this.wingman && this.wingmanCommand == 4 && this.wingmanTarget != null) {
                         for(var4 = 0; var4 < var3.length; ++var4) {
                            if(var3[var4].equals(this.wingmanTarget.player) && var3[var4].isActive() && !var3[var4].isAlwaysFriend()) {
                               this.targedId_ = var4;
                            }
                         }
                      }

                      if(var3[this.targedId_].isActive() && !var3[this.targedId_].isDead()) {
                         this.tempVector_ = var3[this.targedId_].getPosition(this.tempVector_);
                         if(this.pos.x - this.tempVector_.x >= this.sightRange || this.pos.x - this.tempVector_.x <= -this.sightRange || this.pos.y - this.tempVector_.y >= this.sightRange || this.pos.y - this.tempVector_.y <= -this.sightRange || this.pos.z - this.tempVector_.z >= this.sightRange || this.pos.z - this.tempVector_.z <= -this.sightRange) {
                            this.targedId_ = -1;
                         }
                      } else {
                         this.targedId_ = -1;
                      }
                   } else if(!this.targetIsActive) {
                      for(var4 = 0; var4 < var3.length; ++var4) {
                         if(var3[var4] != null && var3[var4].isActive() && !var3[var4].isDead()) {
                            this.tempVector_ = var3[var4].getPosition(this.tempVector_);
                            if(this.race != 8 && this.player.isAlwaysEnemy() || this.pos.x - this.tempVector_.x < this.sightRange && this.pos.x - this.tempVector_.x > -this.sightRange && this.pos.y - this.tempVector_.y < this.sightRange && this.pos.y - this.tempVector_.y > -this.sightRange && this.pos.z - this.tempVector_.z < this.sightRange && this.pos.z - this.tempVector_.z > -this.sightRange) {
                               this.targedId_ = var4;
                               this.targetIsActive = true;
                               break;
                            }
                         }
                      }
                   }

                   if(!this.player.enemy && this.targedId_ == 0) {
                      this.targedId_ = 1;
                      this.targetIsActive = false;
                   }

                   if(!this.wingman && this.targedId_ > 0) {
                      this.targedId_ = -1;

                      for(var4 = 1; var4 < var3.length; ++var4) {
                         if(var3[var4].isActive() && !var3[var4].isDead() && var3[var4].getKIPlayer() != null) {
                            var5 = var3[var4].getKIPlayer().race;
                            if(this.isWingman()?var3[var4].enemy:this.race != 8 && var5 == 8 || this.race == 8 && var5 != 8 || this.race != 9 && var5 == 9 || this.race == 9 && var5 != 9 || this.race == 1 && var5 == 0 || this.race == 0 && var5 == 1 || this.race == 2 && var5 == 3 || this.race == 3 && var5 == 2) {
                               this.targedId_ = var4;
                               this.targetIsActive = true;
                               break;
                            }
                         }
                      }
                   }

                   if(this.wingman && !this.targetIsActive && this.wingmanCommand == 2) {
                      this.targedId_ = -1;
                   }

                   this.folowingRoute = false;
                   if(this.targedId_ == -1 && this.activeRoute_ != null) {
                      if(this.activeRoute_.getDockingTarget_() != null && this.player.isActive()) {
                         this.activeRoute_.update(this.player.transform.getPositionX(), this.player.transform.getPositionY(), this.player.transform.getPositionZ());
                         if(this.activeRoute_.getDockingTarget_() != null) {
                            this.targetPos_.x = this.activeRoute_.getDockingTarget_().x;
                            this.targetPos_.y = this.activeRoute_.getDockingTarget_().y;
                            this.targetPos_.z = this.activeRoute_.getDockingTarget_().z;
                            this.folowingRoute = true;
                         }
                      } else {
                         this.targedId_ = 0;
                         this.target__ = this.player.getEnemy(this.targedId_);
                         if(this.target__ != null) {
                            this.tempVector_ = this.target__.getPosition(this.tempVector_);
                            this.targetPos_.x = this.tempVector_.x;
                            this.targetPos_.y = this.tempVector_.y;
                            this.targetPos_.z = this.tempVector_.z;
                         }
                      }
                   } else {
                      if(this.targedId_ < 0) {
                         this.targedId_ = 0;
                      }
                      this.target__ = this.player.getEnemy(this.targedId_);
                      this.tempVector_ = this.target__.getPosition(this.tempVector_);
                      this.targetPos_.x = this.tempVector_.x;
                      this.targetPos_.y = this.tempVector_.y;
                      this.targetPos_.z = this.tempVector_.z;
                   }
               }
            } else if(this.activeRoute_ != null) {
               this.activeRoute_.update(this.player.transform.getPositionX(), this.player.transform.getPositionY(), this.player.transform.getPositionZ());
               if(this.activeRoute_.getDockingTarget_() != null) {
                  this.targetPos_.x = this.activeRoute_.getDockingTarget_().x;
                  this.targetPos_.y = this.activeRoute_.getDockingTarget_().y;
                  this.targetPos_.z = this.activeRoute_.getDockingTarget_().z;
                  this.folowingRoute = true;
               }
            } else {
               this.state = 5;
            }

            if(this.jumper && this.folowingRoute) {
               this.jumpTick = (int)((long)this.jumpTick + var1);
               if(this.jumpTick >= 20000) {
                  this.jumpTick = 0;
                  this.state = 6;
                  this.folowingRoute = false;
               }
            } else {
               this.jumpTick = 0;
            }

            if(this.wingman && this.wingmanCommand == 3 && this.activeRoute != null) {
               this.activeRoute.update(this.player.transform.getPositionX(), this.player.transform.getPositionY(), this.player.transform.getPositionZ());
               if(this.activeRoute.getCurrent() > this.initialWaypoint) {
                  this.activeRoute = null;
                  this.setWingmanCommand(0, (KIPlayer)null);
               } else if(this.activeRoute.getDockingTarget_() != null) {
                  this.targetPos_.x = this.activeRoute.getDockingTarget_().x;
                  this.targetPos_.y = this.activeRoute.getDockingTarget_().y;
                  this.targetPos_.z = this.activeRoute.getDockingTarget_().z;
                  this.folowingRoute = true;
               }
            }
         }

         this.distanceToTarget.x = this.targetPos_.x - this.pos.x;
         this.distanceToTarget.y = this.targetPos_.y - this.pos.y;
         this.distanceToTarget.z = this.targetPos_.z - this.pos.z;
         
         if(this.player.enemy && (this.state == 5 || this.wingman && this.wingmanCommand == 0)) {
            this.tempVector_ = this.var_36b.getPlayer().player.getPosition(this.tempVector_);
            this.distanceToTarget.x = this.tempVector_.x - this.pos.x;
            this.distanceToTarget.y = this.tempVector_.y - this.pos.y;
            this.distanceToTarget.z = this.tempVector_.z - this.pos.z;
            if(this.distanceToTarget.x < 25000 && this.distanceToTarget.x > -25000 && this.distanceToTarget.y < 25000 && this.distanceToTarget.y > -25000 && this.distanceToTarget.z < 25000 && this.distanceToTarget.z > -25000) {
               this.state = 1;
               this.player.setActive(true);
               if(this.wingman && this.wingmanCommand == 0) {
                  this.wingmanCommand = 2;
               }
            }
         }

         int var6;
         int var7;
         float var9;
         if((var7 = this.player.getHitpoints()) <= 0 && this.state != 3 && this.state != 4) {
            if(this.player.enemy) {
               if(this.race == 8) {
                  if(!this.player.killedByKI) {
                     Status.incPirateKills();
                  }
               } else if(this.race == 9) {
                  this.cargo = new int[2];
                  this.cargo[0] = 131;
                  this.cargo[1] = 1 + GlobalStatus.random.nextInt(3);
               }
               this.var_36b.enemyDied(this.player.killedByKI);
            } else {
               this.var_36b.friendDied();
               if(this.wingman) {
                  Level.wingmanDied(this.wingmanId);
               }
            }

            this.state = 3;
            this.crateTime_ = 0;
            if(this.explosion != null) {
               this.explosion.start(this.pos.x, this.pos.y, this.pos.z);
            }

            var4 = AEMath.min(40000, this.distanceToTarget.getLength());
            var9 = 1.0F - (float)var4 / 40000.0F;
            var6 = GlobalStatus.soundManager.getMusicVolume();
            GlobalStatus.soundManager.playSfx(1, (int)((float)var6 * var9));
            this.hasCargo = this.cargoAvailable_();
            if(this.hasCargo) {
               this.createCrate(this.race == 9?3:0);
            }
            this.unused821_ = false;
         }

         switch(this.state) {
         case 0:
            this.state = 1;
            break;
         case 1:
            if(GlobalStatus.NEURAL_NPC && this.neuralActive && !this.wingman) {
                collectNeuralInputs();
                float decision = predictNeural();
                applyNeuralDecision(decision);
                
                neuralActionCounter++;
                if(neuralActionCounter > 30) {
                    float reward = calculateNeuralReward();
                    rewardHistory[rewardIndex] = reward;
                    rewardIndex = (rewardIndex + 1) % rewardHistory.length;
                    
                    float avgReward = 0.0f;
                    for(int i = 0; i < rewardHistory.length; i++) {
                        avgReward += rewardHistory[i];
                    }
                    avgReward /= rewardHistory.length;
                    
                    if(Math.abs(avgReward - lastReward) > 0.03f) {
                        learnNeural(avgReward);
                        lastReward = avgReward;
                        accumulatedReward = avgReward;
                    }
                    neuralActionCounter = 0;
                }
                
                if(this.canBoost && this.speed > this.defaultSpeed && this.boostTick > this.boostTimeLimit) {
                    this.boostTick = 0;
                    this.damageEscape = false;
                    this.speed = this.defaultSpeed;
                    this.handling = this.defaultHandling;
                }
                
                if(this.target__ != null && this.target__.isActive() && !this.target__.isDead()) {
                    this.distanceToTarget.normalize();
                    this.position.set(this.distanceToTarget);
                    this.distanceToTarget = this.geometry.getInverseTransform(tempTransform).transformVectorNoScale2(this.position, this.distanceToTarget);
                    this.distanceToTarget.y = -this.distanceToTarget.y;
                    
                    if(!this.strayFromTarget_) {
                        this.tempVector_.set(this.geometry.getDirection(this.tempVector_));
                        this.position.subtract(this.tempVector_);
                        this.position.normalize();
                        this.position.scale((int)((float)this.frameTime * this.handling));
                        this.distanceToTarget = this.tempVector_.add(this.position, this.distanceToTarget);
                        this.distanceToTarget.normalize();
                        this.geometry.getToParentTransform().setOrientation(this.distanceToTarget);
                    }
                    
                    this.geometry.moveForward((int)((float)this.frameTime * this.speed));
                } else if(this.activeRoute_ != null) {
                    this.activeRoute_.update(this.player.transform.getPositionX(), 
                                            this.player.transform.getPositionY(), 
                                            this.player.transform.getPositionZ());
                    if(this.activeRoute_.getDockingTarget_() != null) {
                        this.targetPos_.x = this.activeRoute_.getDockingTarget_().x;
                        this.targetPos_.y = this.activeRoute_.getDockingTarget_().y;
                        this.targetPos_.z = this.activeRoute_.getDockingTarget_().z;
                        
                        this.distanceToTarget.x = this.targetPos_.x - this.pos.x;
                        this.distanceToTarget.y = this.targetPos_.y - this.pos.y;
                        this.distanceToTarget.z = this.targetPos_.z - this.pos.z;
                        this.distanceToTarget.normalize();
                        this.position.set(this.distanceToTarget);
                        this.distanceToTarget = this.geometry.getInverseTransform(tempTransform).transformVectorNoScale2(this.position, this.distanceToTarget);
                        this.distanceToTarget.y = -this.distanceToTarget.y;
                        
                        this.tempVector_.set(this.geometry.getDirection(this.tempVector_));
                        this.position.subtract(this.tempVector_);
                        this.position.normalize();
                        this.position.scale((int)((float)this.frameTime * this.handling));
                        this.distanceToTarget = this.tempVector_.add(this.position, this.distanceToTarget);
                        this.distanceToTarget.normalize();
                        this.geometry.getToParentTransform().setOrientation(this.distanceToTarget);
                        
                        this.geometry.moveForward((int)((float)this.frameTime * this.speed));
                        this.folowingRoute = true;
                    }
                }
            } else {
                if(this.canBoost) {
                   if(this.hitpoints > var7) {
                      this.damageAccumulator += this.hitpoints - var7;
                      this.hitpoints = var7;
                      if((float)this.damageAccumulator / (float)this.player.getMaxHitpoints() * 100.0F > 40.0F) {
                         this.damageAccumulator = 0;
                         this.boostTick = 10000;
                         this.damageEscape = true;
                      }
                   }

                   if(this.boostTick > 5000 && this.speed != 3.0F) {
                      if(this.damageEscape || GlobalStatus.random.nextInt(100) < this.boostChance) {
                         this.boostTimeLimit = GlobalStatus.random.nextInt(3000) + 5000;
                      }
                      this.boostTick = 0;
                      this.speed = 3.0F;
                      this.handling = 1.3F;
                   }

                   if(this.speed > this.defaultSpeed && this.boostTick > this.boostTimeLimit) {
                      this.boostTick = 0;
                      this.damageEscape = false;
                      this.speed = this.defaultSpeed;
                      this.handling = this.defaultHandling;
                   }
                }

                if(this.target__ != null && !this.folowingRoute && this.distanceToTarget.x < 8000 && this.distanceToTarget.x > -8000 && this.distanceToTarget.y < 8000 && this.distanceToTarget.y > -8000 && this.distanceToTarget.z < 8000 && this.distanceToTarget.z > -8000) {
                   this.distanceToTarget = this.geometry.getRightVector(this.distanceToTarget);
                }

                this.distanceToTarget.normalize();
                this.position.set(this.distanceToTarget);
                this.distanceToTarget = this.geometry.getInverseTransform(tempTransform).transformVectorNoScale2(this.position, this.distanceToTarget);
                this.distanceToTarget.y = -this.distanceToTarget.y;
                var9 = this.player.getBombForce();
                float var10 = this.player.setEmpForce_();
                if(this.targetIsActive && !this.folowingRoute && this.target__ != null) {
                   if(!this.target__.var_91d) {
                      if(this.distanceToTarget.x < this.soundRange && this.distanceToTarget.x > -this.soundRange && this.distanceToTarget.y < this.soundRange && this.distanceToTarget.y > -this.soundRange && this.targetPos_.x - this.pos.x < '\u88b8' && this.targetPos_.x - this.pos.x > -35000 && this.targetPos_.y - this.pos.y < 35000 && this.targetPos_.y - this.pos.y > -35000 && this.targetPos_.z - this.pos.z < 35000 && this.targetPos_.z - this.pos.z > -35000) {
                         if(this.target__.isActive() && !this.target__.isDead() && var9 < 0.05F && var10 < 0.05F) {
                            this.player.shoot_(this.weaponActive, var1, false);
                         } else {
                            this.targetIsActive = false;
                         }
                      }
                   } else {
                      this.targetIsActive = false;
                   }
                }

                if(!this.strayFromTarget_) {
                   this.tempVector_.set(this.geometry.getDirection(this.tempVector_));
                   this.position.subtract(this.tempVector_);
                   this.position.normalize();
                   this.position.scale((int)((float)var1 * this.handling));
                   this.distanceToTarget = this.tempVector_.add(this.position, this.distanceToTarget);
                   this.distanceToTarget.normalize();
                   this.geometry.getToParentTransform().setOrientation(this.distanceToTarget);
                }

                if(!this.wingman || this.wingmanCommand != 0) {
                   if(var10 < 0.05F && var9 < 0.05F) {
                      this.geometry.moveForward((int)((float)var1 * this.speed));
                   } else {
                      if(var9 > 0.0F) {
                         this.tempVector_.set(this.player.getHitVector());
                         this.tempVector_.scale((int)(512.0F * var9));
                         this.geometry.translate(this.tempVector_);
                         var7 = (int)var1;
                         this.geometry.rotateEuler(var7, var7, var7);
                         if((var9 *= 0.98F) < 0.05F) {
                            var9 = 0.0F;
                         }
                         this.player.setBombForce(var9);
                      }

                      if(var10 > 0.0F) {
                         this.stunned = true;
                         var7 = (int)var1 >> 1;
                         this.geometry.rotateEuler(var7, var7, var7);
                         this.setExhaustVisible(false);
                         if((var10 -= (float)var1) < 0.05F) {
                            var10 = 0.0F;
                            this.stunned = false;
                            this.setExhaustVisible(true);
                         }
                         this.player.setEmpForce_(var10);
                      }
                   }
                }
            }
            break;
         case 2:
         default:
            break;
         case 3:
            this.collisionEnabled = false;
            var5 = (int)var1 >> 1;
            if(this.var_74b != null) {
               this.var_74b.rotateEuler(var5, var5, var5);
            }
            if(this.explosion != null) {
               if(this.explosion.isOver()) {
                  this.state = 4;
                  this.explosion.reset();
               } else {
                  this.crateTime_ = (int)((long)this.crateTime_ + var1);
                  if(this.crateTime_ > this.deathTransitionTime) {
                     this.crateTime_ = 0;
                     this.state = 4;
                  }
               }
            } else {
               this.state = 4;
            }
            break;
         case 4:
            this.crateLifeTime = (int)((long)this.crateLifeTime + var1);
            if(this.hasCargo && this.player.isActive() && this.var_74b != null) {
               var5 = (int)var1 >> 1;
               this.var_74b.rotateEuler(var5, var5, var5);
               if(this.crateLifeTime > 45000 && this.explosion != null) {
                  if(this.crateLifeTime < 90000) {
                     this.explosion.start(this.pos.x, this.pos.y, this.pos.z);
                     if(this.var_36b.getPlayer().radar.tractorBeamTarget == this) {
                        this.var_36b.getPlayer().radar.tractorBeamTarget = null;
                     }
                     this.crateLifeTime = 90000;
                  } else if(this.explosion.isOver()) {
                     if(this.carriesMissionCrate && !this.lostMissionCrateToEgo) {
                        this.diedWithMissionCrate = true;
                     }
                     this.var_74b = null;
                     this.crateLifeTime = 0;
                     this.setActive(false);
                  }
               }
            } else {
               if(this.carriesMissionCrate && !this.lostMissionCrateToEgo) {
                  this.diedWithMissionCrate = true;
               }
               if(this.crateLifeTime > 45000) {
                  this.setActive(false);
               }
            }
            break;
         case 5:
            if(this.target__ != null && !this.target__.var_91d && this.distanceToTarget.x < this.sightRange && this.distanceToTarget.x > -this.sightRange && this.distanceToTarget.y < this.sightRange && this.distanceToTarget.y > -this.sightRange && this.distanceToTarget.z < this.sightRange && this.distanceToTarget.z > -this.sightRange) {
               this.state = 1;
               this.player.setActive(true);
            }
            break;
         case 6:
            this.speed *= 1.1F;
            this.geometry.moveForward((int)((float)var1 * this.speed));
            if(this.speed > 100.0F) {
               this.setDead();
            }
         }

         KIPlayer[] var11;
         if(this.collisionEnabled && (var11 = this.var_36b.getLandmarks()) != null) {
            for(var6 = 0; var6 < var11.length; ++var6) {
               AEVector3D var8;
               if(var11[var6] != null && var11[var6].outerCollide(this.geometry.getPosition(this.position)) && (var8 = var11[var6].getProjectionVector_(this.geometry.getPosition(this.position))) != null) {
                  this.tempVector_.set(this.geometry.getDirection(this.tempVector_));
                  var8.subtract(this.tempVector_);
                  var8.scale((int)((float)var1 * 5.0F));
                  this.pos = this.tempVector_.add(var8, this.pos);
                  this.pos.normalize();
                  this.geometry.getToParentTransform().setOrientation(this.pos);
                  this.geometry.moveForward((int)((float)var1 * this.speed));
               }
            }
         }
      }
   }

   public final void appendToRender() {
      if(this.var_74b != null) {
         GlobalStatus.renderer.drawNodeInVF(this.var_74b);
      }

      if(this.player.isActive() || this.state == 5) {
         if(this.state != 4 && this.state != 3) {
            if(this.withinRenderDistance) {
               GlobalStatus.renderer.drawNodeInVF(this.geometry);
               if(GlobalStatus.EFFECTS_QUALITY && this.trail != null) {
                  this.trail.render();
               }
            } else {
               this.geometry.updateTransform(true);
            }
         } else if((this.state == 3 || this.state == 4) && this.explosion != null && !this.explosion.isOver()) {
            this.explosion.update(this.frameTime);
         }

         if(this.state == 6) {
            GlobalStatus.renderer.drawNodeInVF(this.jumpMesh);
         }
      }
   }

   public final boolean outerCollide(int var1, int var2, int var3) {
      return this.state != 4 && this.state != 3?false:false;
   }

   public final void revive() {
      Globals.buildShip(this.geometry, this.shipId_);
      boolean var1 = this.player.isAlwaysEnemy();
      this.player.reset();
      if(var1) {
         this.player.setAlwaysEnemy();
      }

      this.state = 1;
      this.var_74b = null;
      this.targetIsActive = false;
      this.targedId_ = -1;
      this.activeRoute_.reset();
      this.hitpoints = this.player.getHitpoints();
      this.damageAccumulator = 0;
      this.damageEscape = false;
      this.crateLifeTime = 0;
      this.setActive(true);
      this.jumpTick = 0;
      this.speed = this.defaultSpeed;
      this.setExhaustVisible(true);
      
      if(GlobalStatus.NEURAL_NPC) {
          this.initNeuralNetwork();
          
          if(deathCounter > 0 && killCounter > 0) {
              float kdRatio = (float)killCounter / (float)(deathCounter + 1);
              if(kdRatio < 0.5f && accumulatedReward < 0.3f) {
                  if(sharedWeights != null) {
                      for(int i = 0; i < sharedWeights.length; i++) {
                          sharedWeights[i] += (GlobalStatus.random.nextInt(40) - 20) / 200.0f;
                      }
                  }
              }
          }
          
          deathCounter = 0;
          killCounter = 0;
          accumulatedReward = 0.5f;
          this.lastReward = 0.5f;
          this.hasVoidTarget = false;
      }
      
      if(this.race == 9) {
         this.cargo = null;
      } else {
         Generator var2 = new Generator();
         this.cargo = var2.getLootList();
      }
   }

}