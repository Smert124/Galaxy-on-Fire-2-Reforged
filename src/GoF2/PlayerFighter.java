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
    
    // Neural AI System
    private MicroNeuron combatNeuron;
    private float[] neuralInputs = new float[17];
    private int previousHealth;
    private int previousTargetHealth = -1;
    private long lastNeuralDecisionTime;
    
    // Advanced AI Personality
    private int combatStyle;
    private int personalitySeed;
    private float personalAggression;
    private float personalAccuracy; 
    private float personalManeuverability;
    private int currentMood = 0;
    private int successfulShots = 0;
    private int failedShots = 0;
    
    // Predictive Shooting
    private AEVector3D lastTargetPos = new AEVector3D();
    private long lastTargetUpdateTime;
    private AEVector3D leadPosition = new AEVector3D();
    
    // Extreme Behavior
    private boolean inRageMode = false;
    private int rageModeTime = 0;
    private float extremeHandling = 4.0F;
    
    // Temporary vectors
    private AEVector3D tempCalc1 = new AEVector3D();
    private AEVector3D tempCalc2 = new AEVector3D();
    
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
    
    public PlayerFighter(int var1, int var2, Player var3, AbstractMesh var4, int var5, int var6, int var7) {
        super(var1, var2, var3, (AbstractMesh)null, var5, var6, var7);
        this.race = var2;
        this.initPos.set(var5, var6, var7);
        this.hasCargo = true;
        this.trail = new Trail(var2 != 9 && var2 != 8?0:1);
        this.trail.setWidth(80);
        this.trail.reset(this.initPos);
        this.canBoost = true;
        this.speed = this.defaultSpeed;
        this.handling = this.defaultHandling;
        defaultRoute_.setLoop(true);
        stationRouteAliens.setLoop(true);
        this.activeRoute_ = var2 == 9?stationRouteAliens.clone():defaultRoute_.clone();
        this.strayFromTarget_ = false;
        
        // Initialize Neural AI
        if (GlobalStatus.NEURAL_NPC) {
            initializeAdvancedAI();
        }
        
        if(var2 == 9) {
            this.cargo = null;
        } else {
            Generator var8 = new Generator();
            this.cargo = var8.getLootList();
        }
        
        this.sightRange = Status.inAlienOrbit() ? 100000 : 50000;
        this.collisionEnabled = true;
        this.hitpoints = this.player.getHitpoints();
        this.damageAccumulator = 0;
        this.damageEscape = false;
        this.wingmanCommand = 0;
        this.armed = true;
    }
    
    private void initializeAdvancedAI() {
        personalitySeed = GlobalStatus.random.nextInt(1000);
        combatStyle = GlobalStatus.random.nextInt(4);
        
        if (race == 8 || race == 9) {
            personalAggression = 0.7f + GlobalStatus.random.nextFloat() * 0.3f;
            personalAccuracy = 0.5f + GlobalStatus.random.nextFloat() * 0.3f;
            personalManeuverability = 0.6f + GlobalStatus.random.nextFloat() * 0.4f;
        } else {
            personalAggression = 0.3f + GlobalStatus.random.nextFloat() * 0.4f;
            personalAccuracy = 0.6f + GlobalStatus.random.nextFloat() * 0.3f;
            personalManeuverability = 0.4f + GlobalStatus.random.nextFloat() * 0.3f;
        }
        
        float[] initialWeights = {
            0.6f, 0.4f, 0.8f, 0.3f, 
            0.5f, 0.7f, personalAggression, personalAccuracy
        };
        combatNeuron = new MicroNeuron(initialWeights);
        previousHealth = this.player.getHitpoints();
        extremeHandling = 3.0f + personalManeuverability * 3.0f;
    }
	
	private void applyNeuralShipControl(float[] neuralOutputs) {
		if(!GlobalStatus.NEURAL_NPC || neuralOutputs.length < 20) return;
		
		int pitch = (int)((neuralOutputs[9] * 120f - 60f) * personalManeuverability);  // -60° до +60°
		int yaw = (int)((neuralOutputs[10] * 120f - 60f) * personalManeuverability);   // -60° до +60°  
		int roll = (int)((neuralOutputs[11] * 80f - 40f) * personalManeuverability);   // -40° до +40°
		
		this.geometry.rotateEuler(pitch, yaw, roll);
		
		AEVector3D thrustVector = new AEVector3D();
		
		thrustVector.x = (int)((neuralOutputs[12] * 2 - 1) * 1000);  // -1000 до +1000
		thrustVector.y = (int)((neuralOutputs[13] * 2 - 1) * 1000);  // -1000 до +1000  
		thrustVector.z = (int)((neuralOutputs[14] * 2 - 1) * 1000);  // -1000 до +1000
		
		thrustVector.normalize();
		
		int thrustPower = (int)(this.speed * frameTime);
		this.geometry.translate(
			thrustVector.x * thrustPower / 1000,
			thrustVector.y * thrustPower / 1000,
			thrustVector.z * thrustPower / 1000
		);
		
		float agility = neuralOutputs[15];
		this.handling = defaultHandling * (0.7f + agility * 1.3f);
		
		if(neuralOutputs[16] > 0.8f && target__ != null) {
			executePreciseNeuralAiming();
		}
		
		executeNeuralManeuvers(neuralOutputs);
	}
	
	private void executePreciseNeuralAiming() {
		if(target__ == null) return;
		calculateLeadPosition();
		
		AEVector3D toLead = tempCalc1;
		toLead.x = leadPosition.x - pos.x;
		toLead.y = leadPosition.y - pos.y;
		toLead.z = leadPosition.z - pos.z;
		toLead.normalize();
		
		AEVector3D currentDirection = geometry.getDirection(tempCalc2);
		currentDirection.normalize();
		
		float aimSmoothness = 0.3f;
		distanceToTarget.x = (int)(currentDirection.x * (1-aimSmoothness) + toLead.x * aimSmoothness);
		distanceToTarget.y = (int)(currentDirection.y * (1-aimSmoothness) + toLead.y * aimSmoothness);
		distanceToTarget.z = (int)(currentDirection.z * (1-aimSmoothness) + toLead.z * aimSmoothness);
		distanceToTarget.normalize();
		
		this.geometry.getToParentTransform().setOrientation(distanceToTarget);
	}
	
	private void executeNeuralManeuvers(float[] neuralOutputs) {
		if(neuralOutputs[17] > 0.7f && neuralOutputs[18] > 0.5f) {
			geometry.rotateEuler(60, 25, 120);
			speed *= 1.2f;
		} else if (neuralOutputs[17] < 0.3f && neuralOutputs[19] > 0.6f) {
			geometry.rotateEuler(15, -10, 45);
			speed *= 0.8f;
		} else if (neuralOutputs[18] > 0.8f) {
			geometry.rotateEuler(0, 80, 0);
		}
		
		if(GlobalStatus.random.nextInt(100) < 20) {
			geometry.rotateEuler(
				GlobalStatus.random.nextInt(10) - 5,
				GlobalStatus.random.nextInt(10) - 5, 
				GlobalStatus.random.nextInt(10) - 5
			);
		}
	}
    
    private float[] gatherNeuralInputs() {
		if(!GlobalStatus.NEURAL_NPC || combatNeuron == null) return null;
		
		neuralInputs[0] = (float)player.getHitpoints() / player.getMaxHitpoints();
		float distance = (float)distanceToTarget.getLength();
		neuralInputs[1] = 1.0f - Math.min(distance / 25000.0f, 1.0f);
		neuralInputs[2] = calculateThreatLevel();
		neuralInputs[3] = canBoost ? 0.8f : 0.2f;
		neuralInputs[4] = calculatePositionAdvantage();
		neuralInputs[5] = calculateCombatPerformance();
		neuralInputs[6] = personalAggression;
		neuralInputs[7] = personalAccuracy;
		neuralInputs[8] = (speed - defaultSpeed) / (3.0f - defaultSpeed);
		neuralInputs[9] = (target__ != null) ? (float)target__.getHitpoints() / target__.getMaxHitpoints() : 0.5f;
		neuralInputs[10] = isTargetMovingAway() ? 1.0f : 0.0f;
		
		return neuralInputs;
	}
	
	private boolean isTargetMovingAway() {
		if(target__ == null || lastTargetPos == null) return false;
		
		AEVector3D currentTargetPos = target__.getPosition(tempCalc1);
		AEVector3D toTargetPrev = tempCalc2;
		
		toTargetPrev.x = lastTargetPos.x - pos.x;
		toTargetPrev.y = lastTargetPos.y - pos.y; 
		toTargetPrev.z = lastTargetPos.z - pos.z;
		
		AEVector3D toTargetCurrent = new AEVector3D();
		
		toTargetCurrent.x = currentTargetPos.x - pos.x;
		toTargetCurrent.y = currentTargetPos.y - pos.y;
		toTargetCurrent.z = currentTargetPos.z - pos.z;
		
		return toTargetCurrent.getLength() > toTargetPrev.getLength();
	}
    
    private float calculateThreatLevel() {
        float threat = 0.0f;
        threat += Math.min((float)damageAccumulator / player.getMaxHitpoints(), 1.0f) * 0.4f;
        threat += (1.0f - (float)player.getShieldDamageRate() / 100.0f) * 0.3f;
        threat += (2 - currentMood) * 0.1f;
        return Math.min(threat, 1.0f);
    }
    
    private float calculatePositionAdvantage() {
        float advantage = 0.5f;
        if (pos.y > targetPos_.y + 2000) advantage += 0.2f;
        if (speed > defaultSpeed * 1.2f) advantage += 0.15f;
        if (isInFlankingPosition()) advantage += 0.25f;
        return Math.min(advantage, 1.0f);
    }
    
    private float calculateCombatPerformance() {
        if (successfulShots + failedShots == 0) return 0.5f;
        float ratio = (float)successfulShots / (successfulShots + failedShots);
        return Math.max(0.1f, Math.min(ratio, 0.9f));
    }
    
    private boolean isInFlankingPosition() {
        if (target__ == null) return false;
        AEVector3D targetDir = target__.transform.getDirection(tempCalc1);
        AEVector3D toTarget = tempCalc2;
        toTarget.x = pos.x - targetPos_.x;
        toTarget.y = pos.y - targetPos_.y;
        toTarget.z = pos.z - targetPos_.z;
        toTarget.normalize();
        float dot = targetDir.x * toTarget.x + targetDir.y * toTarget.y + targetDir.z * toTarget.z;
        return dot < -0.5f;
    }
    
    private void calculateLeadPosition() {
        if (target__ == null || !target__.isActive()) return;
        AEVector3D currentTargetPos = target__.getPosition(tempCalc1);
        int leadDistance = 1500 + (int)(personalAccuracy * 1000);
        AEVector3D targetDirection = target__.transform.getDirection(tempCalc2);
        leadPosition.x = currentTargetPos.x + targetDirection.x * leadDistance;
        leadPosition.y = currentTargetPos.y + targetDirection.y * leadDistance;
        leadPosition.z = currentTargetPos.z + targetDirection.z * leadDistance;
    }
    
    private boolean isAimingAtLeadPosition() {
        if (target__ == null) return false;
        AEVector3D toLead = tempCalc1;
        toLead.x = leadPosition.x - pos.x;
        toLead.y = leadPosition.y - pos.y;
        toLead.z = leadPosition.z - pos.z;
        toLead.normalize();
        AEVector3D ourDirection = geometry.getDirection(tempCalc2);
        ourDirection.normalize();
        long dot = (long)toLead.x * ourDirection.x + (long)toLead.y * ourDirection.y + (long)toLead.z * ourDirection.z;
        return dot > 95000;
    }
    
    private boolean isTargetDangerousAtCloseRange() {
        if (target__ == null) return true;
        if (isTargetShootingAtUs()) return true;
        if (isTargetFacingUs()) return true;
        if (target__.getHitpoints() < target__.getMaxHitpoints() * 0.3f) return false;
        return false;
    }
    
    private boolean isTargetShootingAtUs() {
        if (target__ == null) return false;
        AEVector3D targetDirection = target__.transform.getDirection(tempCalc1);
        AEVector3D toUs = tempCalc2;
        toUs.x = pos.x - targetPos_.x;
        toUs.y = pos.y - targetPos_.y;
        toUs.z = pos.z - targetPos_.z;
        toUs.normalize();
        float aimAccuracy = targetDirection.x * toUs.x + targetDirection.y * toUs.y + targetDirection.z * toUs.z;
        return aimAccuracy > 0.9f;
    }
    
    private boolean isTargetFacingUs() {
        if (target__ == null) return false;
        AEVector3D targetDirection = target__.transform.getDirection(tempCalc1);
        AEVector3D toUs = tempCalc2;
        toUs.x = pos.x - targetPos_.x;
        toUs.y = pos.y - targetPos_.y;
        toUs.z = pos.z - targetPos_.z;
        toUs.normalize();
        float facing = targetDirection.x * toUs.x + targetDirection.y * toUs.y + targetDirection.z * toUs.z;
        return facing > 0.7f;
    }
    
    private void executePersonalityBasedCombat() {
		float[] inputs = gatherNeuralInputs();
		if(inputs == null) return;
		
		float decision = combatNeuron.predict(inputs);
		
		float speedMultiplier = (decision * 2.0f);
		int tacticalMode = decodeTacticalMode(decision);
		
		applyNeuralSpeedControl(speedMultiplier, tacticalMode);
		applyNeuralShipControl(inputs);
		advancedLearning();
	}
	
	private void applyNeuralSpeedControl(float speedMultiplier, int tacticalMode) {
		float baseSpeed = defaultSpeed;
		
		switch(tacticalMode) {
			
			case 4: // Режим ярости
				baseSpeed = 3.0f;
				inRageMode = true;
				rageModeTime = 0;
            break;
			
			case 3: // Агрессивный
				baseSpeed = defaultSpeed * 1.4f;
            break;
			
			case 2: // Сбалансированный
				baseSpeed = defaultSpeed;
            break;
			
			case 1: // Оборонительный
				baseSpeed = defaultSpeed * 0.7f;
            break;
			
			case 0: // Уклонение
				baseSpeed = defaultSpeed * 1.6f;
            break;
		}
		
		speed = baseSpeed * speedMultiplier;
		
		/* if(speed < defaultSpeed * 0.3f) speed = defaultSpeed * 0.3f;
		if(speed > 3.5f) speed = 3.5f; */
		
		handling = defaultHandling * (2.0f - speedMultiplier * 0.5f);
	}
	
	private void applyNeuralBoostControl(float speedMultiplier) {
		if(!canBoost) return;
		
		boolean shouldBoost = (speedMultiplier > 1.5f && player.getHitpoints() > player.getMaxHitpoints() * 0.4f);
		
		if(shouldBoost && boostTick > 3000) {
			speed = 5.0f;
			boostTick = 0;
			boostTimeLimit = 4000 + GlobalStatus.random.nextInt(2000);
		}
	}
	
	private int decodeTacticalMode(float decision) {
		if(decision > 0.8f && personalAggression > 0.7f) return 4; // Режим ярости
		if(decision > 0.7f) return 3; // Агрессивный
		if(decision > 0.5f) return 2; // Сбалансированный  
		if(decision > 0.3f) return 1; // Оборонительный
		return 0; // Уклонение
	}
    
    private void executeCloseRangeCombat() {
        if (target__ == null) return;
        float distance = (float)distanceToTarget.getLength();
        
        if (distance < 1500) {
            executeEmergencyAvoidance();
            weaponActive = 0;
        } else if (distance < 4000) {
            if (isTargetDangerousAtCloseRange()) {
                executeHitAndRun();
            } else {
                executePointBlankAttack();
            }
        }
    }
    
    private void executeRageMode() {
        inRageMode = true;
        rageModeTime = 0;
        weaponActive = 1;
        speed = defaultSpeed * 2.0f;
        handling = extremeHandling;
        
        if (target__ != null) {
            calculateLeadPosition();
            if (isAimingAtLeadPosition()) {
                player.shoot_(weaponActive, 50, false);
            }
        }
        performExtremeManeuvers();
    }
    
    private void executeAggressiveTactics() {
        weaponActive = 1;
        
        if (target__ != null) {
            float distance = (float)distanceToTarget.getLength();
            
            if (distance < 5000 && !isTargetDangerousAtCloseRange()) {
                speed = defaultSpeed * 1.1f;
                calculateLeadPosition();
                if (isAimingAtLeadPosition()) {
                    player.shoot_(weaponActive, frameTime, false);
                }
            } else if (distance > 12000) {
                speed = defaultSpeed * 1.6f;
            } else {
                maintainCombatDistance();
            }
        }
    }
    
    private void executeBalancedTactics() {
        weaponActive = (GlobalStatus.random.nextInt(100) < 70) ? 1 : 0;
        executeDefensivePositioning();
        
        if (target__ != null && isInGoodFiringPosition()) {
            calculateLeadPosition();
            if (isAimingAtLeadPosition() && GlobalStatus.random.nextInt(100) < 60) {
                player.shoot_(weaponActive, frameTime, false);
            }
        }
    }
    
    private void executeDefensiveTactics() {
        weaponActive = (GlobalStatus.random.nextInt(100) < 40) ? 1 : 0;
        speed = defaultSpeed * 0.8f;
        
        if (GlobalStatus.random.nextInt(100) < 60) {
            executeDefensiveManeuver();
        }
    }
    
    private void executeEvasiveTactics() {
        weaponActive = 0;
        speed = defaultSpeed * 1.3f;
        
        if (player.getHitpoints() < player.getMaxHitpoints() * 0.3f) {
            executeEmergencyEscape();
        } else {
            executeEvasivePattern();
        }
    }
    
    private void executePointBlankAttack() {
		speed = defaultSpeed * 0.5f;
		weaponActive = 1;
		player.shoot_(weaponActive, 50, false);
		
		AEVector3D toTarget = tempCalc1;
		
		toTarget.x = targetPos_.x - pos.x;
		toTarget.y = targetPos_.y - pos.y;
		toTarget.z = targetPos_.z - pos.z;
		
		toTarget.normalize();
		
		AEVector3D ourDirection = geometry.getDirection(tempCalc2);
		float verticalDiff = Math.abs(toTarget.y - ourDirection.y);
		
		if(verticalDiff > 0.6f) {
			distanceToTarget.y = (distanceToTarget.y + (int)(toTarget.y * 0.3f * 1000)) / 2;
		} else {
			AEVector3D rightVec = geometry.getRightVector(tempCalc1);
			distanceToTarget.x = (distanceToTarget.x + (int)(rightVec.x * 0.2f * 1000)) / 2;
			distanceToTarget.z = (distanceToTarget.z + (int)(rightVec.z * 0.2f * 1000)) / 2;
		}
		
		distanceToTarget.normalize();
	}
    
    private void executeHitAndRun() {
        weaponActive = 1;
        speed = defaultSpeed * 1.8f;
        
        if (isAimingAtLeadPosition()) {
            player.shoot_(weaponActive, frameTime, false);
        }
        
        AEVector3D escapeVector = geometry.getRightVector(tempCalc1);
        distanceToTarget.x = escapeVector.x * 2000;
        distanceToTarget.y = escapeVector.y * 2000;
        distanceToTarget.z = escapeVector.z * 2000;
        distanceToTarget.normalize();
    }
    
    private void executeEmergencyAvoidance() {
        distanceToTarget.x = -distanceToTarget.x * 3;
        distanceToTarget.y = -distanceToTarget.y * 3;
        distanceToTarget.z = -distanceToTarget.z * 3;
        distanceToTarget.normalize();
        speed = defaultSpeed * 1.8f;
        weaponActive = 0;
        
        if (GlobalStatus.random.nextInt(2) == 0) {
            geometry.rotateEuler(0, 0, 90);
        } else {
            geometry.rotateEuler(0, 0, -90);
        }
    }
    
    private void executeDefensivePositioning() {
        if (target__ == null) return;
        float distance = (float)distanceToTarget.getLength();
        
        if (distance < 3000) {
            distanceToTarget.x = -distanceToTarget.x * 2;
            distanceToTarget.y = -distanceToTarget.y * 2;
            distanceToTarget.z = -distanceToTarget.z * 2;
            distanceToTarget.normalize();
            speed = defaultSpeed * 1.5f;
            weaponActive = 0;
            return;
        }
        
        if (distance < 8000) {
            maintainCombatDistance();
            return;
        }
        
        speed = defaultSpeed * 1.2f;
    }
    
    private void maintainCombatDistance() {
        if (target__ == null) return;
        
        AEVector3D toTarget = tempCalc1;
        toTarget.x = targetPos_.x - pos.x;
        toTarget.y = targetPos_.y - pos.y;
        toTarget.z = targetPos_.z - pos.z;
        toTarget.normalize();
        
        AEVector3D ourDirection = geometry.getDirection(tempCalc2);
        float verticalAngle = Math.abs(toTarget.y - ourDirection.y);
        
        if (verticalAngle > 0.8f) {
            executeHorizontalManeuver();
        } else {
            executeCircularManeuver();
        }
    }
    
    private void executeHorizontalManeuver() {
		AEVector3D rightVec = geometry.getRightVector(tempCalc1);
		
		distanceToTarget.x = (rightVec.x * 6000 + distanceToTarget.x * 1000) / 7000;
		distanceToTarget.y = distanceToTarget.y * 1000 / 3000;
		distanceToTarget.z = (rightVec.z * 6000 + distanceToTarget.z * 1000) / 7000;
		
		distanceToTarget.normalize();
		speed = defaultSpeed * 0.9f;
	}
    
    private void executeCircularManeuver() {
        AEVector3D rightVec = geometry.getRightVector(tempCalc1);
        distanceToTarget.x = (rightVec.x * 5000 + distanceToTarget.x * 2000) / 7000;
        distanceToTarget.y = (rightVec.y * 5000 + distanceToTarget.y * 2000) / 7000;
        distanceToTarget.z = (rightVec.z * 5000 + distanceToTarget.z * 2000) / 7000;
        distanceToTarget.normalize();
        speed = defaultSpeed * 0.8f;
    }
    
    private void executeDefensiveManeuver() {
        geometry.rotateEuler(0, 80, 40);
        speed = defaultSpeed * 0.9f;
    }
    
    private void executeEmergencyEscape() {
        if (canBoost) {
            speed = 3.5f;
        }
        distanceToTarget.x = -distanceToTarget.x * 2;
        distanceToTarget.y = -distanceToTarget.y * 2;
        distanceToTarget.z = -distanceToTarget.z * 2;
    }
    
    private void executeEvasivePattern() {
        int pattern = GlobalStatus.random.nextInt(3);
        switch (pattern) {
            case 0:
                geometry.rotateEuler(
                    GlobalStatus.random.nextInt(100) - 50,
                    GlobalStatus.random.nextInt(100) - 50,
                    GlobalStatus.random.nextInt(100) - 50
                );
                break;
            case 1:
                distanceToTarget = geometry.getRightVector(distanceToTarget);
                break;
            case 2:
                geometry.rotateEuler(30, -60, 45);
                speed = defaultSpeed * 1.2f;
                break;
        }
    }
    
    private boolean isInGoodFiringPosition() {
        if (target__ == null) return false;
        float distance = (float)distanceToTarget.getLength();
        if (distance < 2000) return false;
        if (distance > 15000) return false;
        
        AEVector3D ourDirection = geometry.getDirection(tempCalc1);
        AEVector3D toTarget = tempCalc2;
        toTarget.x = targetPos_.x - pos.x;
        toTarget.y = targetPos_.y - pos.y;
        toTarget.z = targetPos_.z - pos.z;
        toTarget.normalize();
        
        float approachAngle = ourDirection.x * toTarget.x + ourDirection.y * toTarget.y + ourDirection.z * toTarget.z;
        return approachAngle < 0.8f;
    }
    
    private void performExtremeManeuvers() {
        int maneuver = GlobalStatus.random.nextInt(6);
        switch (maneuver) {
            case 0:
                geometry.rotateEuler(0, 0, 80);
                break;
            case 1:
                geometry.rotateEuler(0, 60, 0);
                break;
            case 2:
                geometry.rotateEuler(50, 0, 0);
                break;
            case 3:
                geometry.rotateEuler(0, -40, 30);
                break;
            case 4:
                geometry.rotateEuler(30, 50, 0);
                break;
            case 5:
                geometry.rotateEuler(20, -30, 25);
                break;
        }
    }
    
    private void advancedLearning() {
		float[] inputs = gatherNeuralInputs();
		if(inputs == null) return;
		
		float performance = evaluateCombatPerformance();
		
		float controlEfficiency = evaluateControlEfficiency();
		float aimingEfficiency = evaluateAimingEfficiency();
		
		performance = (performance * 0.5f) + (controlEfficiency * 0.3f) + (aimingEfficiency * 0.2f);
		combatNeuron.learn(inputs, performance, 0.02f);
		
		previousHealth = player.getHitpoints();
		
		if(target__ != null && target__.isActive()) {
			previousTargetHealth = target__.getHitpoints();
		}
		
	}
	
	private float evaluateControlEfficiency() {
		float efficiency = 0.5f;
		
		if(player.getHitpoints() > previousHealth - 10) {
			efficiency += 0.2f;
		}
		
		if(isInGoodFiringPosition()) {
			efficiency += 0.3f;
		}
		
		return Math.max(0.1f, Math.min(efficiency, 0.9f));
	}
	
	private float evaluateAimingEfficiency() {
		
		if(successfulShots + failedShots == 0) return 0.5f;
		
		return (float)successfulShots / (successfulShots + failedShots);
	}
	
	private float evaluateSpeedEfficiency() {
		if(target__ == null) return 0.5f;
		
		float efficiency = 0.5f;
		float distance = (float)distanceToTarget.getLength();
		
		if(distance < 5000 && speed > defaultSpeed * 1.2f) {
			efficiency -= 0.3f;
		} else if (distance > 15000 && speed < defaultSpeed * 0.8f) {
			efficiency -= 0.2f;
		} else if (distance > 8000 && distance < 12000 && speed > defaultSpeed * 0.7f && speed < defaultSpeed * 1.3f) {
			efficiency += 0.2f;
		}
		
		if(speed > 2.5f && distance > 10000) {
			efficiency += 0.1f;
		}
		
		return Math.max(0.1f, Math.min(efficiency, 0.9f));
	}
	
	private Player selectOptimalTarget(Player[] enemies) {
		
		if(enemies == null || enemies.length == 0) return null;
		
		Player[] validTargets = filterTargetsByRace(enemies);
		if(validTargets.length == 0) return null;
		
		Player bestTarget = null;
		float bestScore = -1.0f;
		
		for(int i = 0; i < validTargets.length; i++) {
			
			if(validTargets[i] != null && validTargets[i].isActive() && !validTargets[i].isDead()) {
				float targetScore = evaluateTarget(validTargets[i]);
				
				if(targetScore > bestScore) {
					bestScore = targetScore;
					bestTarget = validTargets[i];
				}
			}
		}
		
		return bestTarget;
	}
	
	private Player[] filterTargetsByRace(Player[] enemies) {
		
		java.util.Vector validTargets = new java.util.Vector();
		
		for(int i = 0; i < enemies.length; i++) {
			
			if(enemies[i] != null && enemies[i].isActive() && !enemies[i].isDead()) {
				
				if(isValidTargetByRace(enemies[i])) {
					validTargets.addElement(enemies[i]);
				}
				
			}
			
		}
		
		Player[] result = new Player[validTargets.size()];
		validTargets.copyInto(result);
		return result;
	}
	
	private boolean isValidTargetByRace(Player enemy) {
		
		if(enemy.getKIPlayer() == null) return false;
		int targetRace = enemy.getKIPlayer().race;
		
		if(this.isWingman()) {
			return enemy.enemy;
		} else {
			return (this.race != 8 && targetRace == 8) ||
               (this.race == 8 && targetRace != 8) ||
               (this.race != 9 && targetRace == 9) ||
               (this.race == 9 && targetRace != 9) ||
               (this.race == 1 && targetRace == 0) ||
               (this.race == 0 && targetRace == 1) ||
               (this.race == 2 && targetRace == 3) ||
               (this.race == 3 && targetRace == 2);
		}
		
	}
	
	private float evaluateTarget(Player enemy) {
		float score = 0.0f;
		
		AEVector3D enemyPos = enemy.getPosition(tempCalc1);
		float distance = (float)pos.distanceTo(enemyPos);
		score += (1.0f - Math.min(distance / sightRange, 1.0f)) * 0.25f;
		
		float targetHealth = (float)enemy.getHitpoints() / enemy.getMaxHitpoints();
		score += (1.0f - targetHealth) * 0.20f;
		
		float threatFromTarget = calculateThreatFromTarget(enemy);
		score += (1.0f - threatFromTarget) * 0.15f;
		
		float positionalAdvantage = calculatePositionalAdvantageOverTarget(enemy);
		score += positionalAdvantage * 0.20f;
		
		score += personalAggression * 0.20f;
		return score;
	}
	
	private float calculateThreatFromTarget(Player enemy) {
		float threat = 0.0f;
		threat += (float)enemy.getHitpoints() / enemy.getMaxHitpoints() * 0.4f;
		threat += (1.0f - (float)enemy.getShieldDamageRate() / 100.0f) * 0.3f;
		
		if(isEnemyFacingUs(enemy)) {
			threat += 0.3f;
		}
		
		return Math.min(threat, 1.0f);
	}
	
	private boolean isEnemyFacingUs(Player enemy) {
		if(enemy == null) return false;
		
		AEVector3D enemyPos = enemy.getPosition(tempCalc1);
		AEVector3D enemyDirection = enemy.transform.getDirection(tempCalc2);
		
		AEVector3D toUs = new AEVector3D();
		
		toUs.x = pos.x - enemyPos.x;
		toUs.y = pos.y - enemyPos.y;
		toUs.z = pos.z - enemyPos.z;
		
		toUs.normalize();
		
		float facing = enemyDirection.x * toUs.x + enemyDirection.y * toUs.y + enemyDirection.z * toUs.z;
		return facing > 0.7f;
	}
	
	private float calculatePositionalAdvantageOverTarget(Player enemy) {
		AEVector3D enemyPos = enemy.getPosition(tempCalc1);
		AEVector3D enemyDir = enemy.transform.getDirection(tempCalc2);
		
		AEVector3D toUs = new AEVector3D();
		toUs.x = pos.x - enemyPos.x;
		toUs.y = pos.y - enemyPos.y;
		toUs.z = pos.z - enemyPos.z;
		toUs.normalize();
		
		float dot = enemyDir.x * toUs.x + enemyDir.y * toUs.y + enemyDir.z * toUs.z;
		
		if(dot < -0.5f) {
			return 0.8f;
		} else if (dot > 0.5f) {
			return 0.2f;
		}
		
		return 0.5f;
	}
    
    private float evaluateCombatPerformance() {
        float performance = 0.5f;
        
        if (target__ != null && previousTargetHealth > 0) {
            int currentTargetHealth = target__.getHitpoints();
            if (currentTargetHealth < previousTargetHealth) {
                performance += 0.3f;
            }
        }
        
        if (player.getHitpoints() < previousHealth) {
            performance -= 0.4f;
        }
        
        if (isInFlankingPosition()) {
            performance += 0.2f;
        }
        
        performance += (calculateCombatPerformance() - 0.5f) * 0.3f;
        
        return Math.max(0.1f, Math.min(performance, 0.9f));
    }

    private void updateExhaustBasedOnSpeed() {
        if (speed <= 0.1f || this.stunned) {
            setExhaustVisible(false);
        } else if (speed < defaultSpeed * 0.3f) {
            setExhaustVisible(true);
        } else if (speed > defaultSpeed * 1.5f) {
            setExhaustVisible(true);
        } else if (inRageMode) {
            setExhaustVisible(true);
        } else {
            setExhaustVisible(true);
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
            this.player.enemy = this.race != 8 && this.race != 9 ? (this.isWingman() ? false : Status.getStanding().isEnemy(this.race)) : true;
            this.player.friend = this.race != 8 && this.race != 9 ? (this.isWingman() ? true : Status.getStanding().isFriend(this.race)) : false;
            
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
            
            if(this.trailTick > var1) {
                if(this.trail != null) {
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
						
						if(GlobalStatus.NEURAL_NPC && (this.behavioutChangeTick > 5000 || !this.targetIsActive)) {
							Player optimalTarget = selectOptimalTarget(var3);
							
							if(optimalTarget != null) {
								
								for(int i = 0; i < var3.length; i++) {
									
									if(var3[i] == optimalTarget) {
										this.targedId_ = i;
										this.targetIsActive = true;
										this.target__ = optimalTarget;
										break;
									}
									
								}
								
							}
							
							this.behavioutChangeTick = 0;
						} else {
							this.targedId_ = -1;
						}
                    } else if(this.targedId_ >= 0 && !var3[this.targedId_].isActive()) {
                        this.targetIsActive = false;
                    }
                    
                    this.target__ = null;
                    
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
					updateExhaustBasedOnSpeed();
                    
					if(GlobalStatus.NEURAL_NPC && frameTime - lastNeuralDecisionTime > 1000) {
                        executePersonalityBasedCombat();
                        lastNeuralDecisionTime = frameTime;
						
						if(inRageMode) {
							rageModeTime += var1;
							
							if(rageModeTime > 6000) {
								inRageMode = false;
								speed = defaultSpeed;
								handling = defaultHandling;
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
								
								if(this.distanceToTarget.x < this.soundRange && this.distanceToTarget.x > -this.soundRange && this.distanceToTarget.y < this.soundRange && this.distanceToTarget.y > -this.soundRange && this.targetPos_.x - this.pos.x < 35000 && this.targetPos_.x - this.pos.x > -35000 && this.targetPos_.y - this.pos.y < 35000 && this.targetPos_.y - this.pos.y > -35000 && this.targetPos_.z - this.pos.z < 35000 && this.targetPos_.z - this.pos.z > -35000) {
									
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
									geometry.moveForward((int)((float)var1 * this.speed * 0.3f));
									this.geometry.rotateEuler(var7, var7, var7);
									
									if((var10 -= (float)var1) < 0.05F) {
										var10 = 0.0F;
										this.stunned = false;
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
	
    public final void OnRelease() {
        super.OnRelease();
        if(this.trail != null) {
            this.trail.OnRelease();
        }
        this.trail = null;
        this.target__ = null;
        this.boundings = null;
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
        if(this.trail != null) {
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
            if(var2.getID() == 13067 || var2.getID() == 13068 || var2.getID() == 13070 || 
               var2.getID() == 13064 || var2.getID() == 13065 || var2.getID() == 13071 || 
               var2.getID() == 14072 || 
               var2.getID() >= 20000 && var2.getID() <= 20100 || 
               var2.getID() >= 21000 && var2.getID() <= 21100) {
                var2.setDraw(var1);
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
                    if(this.trail != null) {
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
            successfulShots = 0;
            failedShots = 0;
            inRageMode = false;
            currentMood = 0;
        }
        
        if(this.race == 9) {
            this.cargo = null;
        } else {
            Generator var2 = new Generator();
            this.cargo = var2.getLootList();
        }
    }
}