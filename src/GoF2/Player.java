package GoF2;

import AE.GlobalStatus;
import AE.Math.AEVector3D;
import AE.Math.Matrix;


public final class Player {

   public Gun[][] guns;
   private Player[] var_1d0;
   float var_21b;
   private int empPoints;
   private int maxEmpPoints;
   private int hp;
   private int maxHp;
   private float shield;
   private int maxShield;
   private int armorPoints;
   private int maxArmorPoints;
   private int percentHP;
   private int percentShield;
   private int percentArmor;
   private int percentEMP;
   private int summaryPercent;
   private int var_766;
   private boolean active;
   private boolean vulnerable;
   public boolean enemy;
   public boolean friend;
   public boolean var_91d;
   public Matrix transform;
   private AEVector3D hitVector = new AEVector3D();
   private KIPlayer kiPlayer;
   private boolean playShootSound;
   public boolean killedByKI;
   private float var_a43;
   private float empForce;
   private int egoInflictDamage;
   private int var_b0b;
   private boolean var_b5c;
   private int empImpactForce;
   private int var_bbb;
   private boolean var_bfe;
   private boolean tempEnemy_;
   private boolean var_cfe;
   public boolean var_d5a;
   public int posX;
   public int posY;
   public int posZ;


   public Player(float var1, int var2, int var3, int var4, int var5) {
      this.var_21b = var1;
      this.hp = var2;
      this.maxHp = var2;
      this.updateDamageRate();
      this.guns = new Gun[3][];
      if(var3 > 0) {
         this.guns[0] = new Gun[var3];
      }

      if(var4 > 0) {
         this.guns[1] = new Gun[var4];
      }

      if(var5 > 0) {
         this.guns[2] = new Gun[var5];
      }

      this.transform = new Matrix();
      this.active = true;
      this.vulnerable = true;
      this.kiPlayer = null;
      this.playShootSound = false;
      this.killedByKI = false;
      this.var_d5a = false;
   }

   public final boolean isAsteroid() {
      return this.kiPlayer != null?this.kiPlayer.var_5c1:false;
   }

   public final void setAlwaysEnemy(boolean var1) {
      this.tempEnemy_ = var1;
      this.enemy = true;
      this.friend = false;
      this.var_b5c = true;
   }

   public final void setAlwaysFriend(boolean var1) {
      this.var_cfe = var1;
      this.enemy = false;
      this.friend = true;
      this.var_b5c = false;
   }

   public final boolean isAlwaysFriend() {
      return this.var_cfe;
   }

   public final void setEmpData(int var1, int var2) {
      this.empPoints = var1;
      if(this.empPoints > this.maxEmpPoints) {
         this.maxEmpPoints = this.empPoints;
      }

      this.updateDamageRate();
      this.empImpactForce = var2;
   }

   public final void setPlayShootSound(boolean var1) {
      this.playShootSound = var1;
   }

   public final void sub_28a(int var1) {
      this.var_21b = (float)var1;
   }

   public final void setKIPlayer(KIPlayer var1) {
      this.kiPlayer = var1;
   }

   public final KIPlayer getKIPlayer() {
      return this.kiPlayer;
   }

   public final void reset() {
      this.hp = this.maxHp;
      this.shield = (float)this.maxShield;
      this.updateDamageRate();
      this.active = true;
      this.vulnerable = true;
      this.transform.identity();
      this.killedByKI = false;
      this.var_b5c = false;
      this.egoInflictDamage = 0;
      this.var_b0b = 0;
      this.var_91d = false;
   }

   public final void setEnemies(Player[] var1) {
      this.var_1d0 = var1;
      if(this.guns != null) {
         for(int var3 = 0; var3 < this.guns.length; ++var3) {
            if(this.guns[var3] != null) {
               for(int var2 = 0; var2 < this.guns[var3].length; ++var2) {
                  if(this.guns[var3][var2] != null) {
                     this.guns[var3][var2].setTargets(this.var_1d0);
                  }
               }
            }
         }
      }

   }

   public final void addEnemies(Player[] var1) {
      if(this.var_1d0 == null) {
         this.setEnemies(var1);
      } else {
         Player[] var2 = new Player[this.var_1d0.length + var1.length];

         int var3;
         for(var3 = 0; var3 < this.var_1d0.length; ++var3) {
            var2[var3] = this.var_1d0[var3];
         }

         for(var3 = 0; var3 < var1.length; ++var3) {
            var2[this.var_1d0.length + var3] = var1[var3];
         }

         this.setEnemies(var2);
      }
   }

   public final Player[] getEnemies() {
      return this.var_1d0;
   }

   public final Player getEnemy(int var1) {
      return this.var_1d0[var1];
   }

   public final void setHitPoints(int var1) {
      this.hp = var1;
      if(this.hp > this.maxHp) {
         this.maxHp = this.hp;
      }

      this.updateDamageRate();
   }

   public final void setShieldHP(int var1) {
      this.shield = (float)var1;
      if(this.shield > (float)this.maxShield) {
         this.shield = (float)this.maxShield;
      }

      this.updateDamageRate();
   }

   public final void setArmorHP(int var1) {
      this.armorPoints = var1;
      if(this.armorPoints > this.maxArmorPoints) {
         this.armorPoints = this.maxArmorPoints;
      }

      this.updateDamageRate();
   }

   public final void setMaxHP(int var1) {
      this.maxHp = var1;
      this.hp = var1;
      this.updateDamageRate();
   }

   public final void setMaxShieldHP(int var1) {
      this.maxShield = var1;
      this.shield = (float)var1;
      this.updateDamageRate();
   }

   public final void setMaxArmorHP(int var1) {
      this.maxArmorPoints = var1;
      this.armorPoints = var1;
      this.updateDamageRate();
   }

   public final int getShieldHP() {
      return (int)this.shield;
   }

   public final int getCombinedHP() {
      return (int)this.shield + this.armorPoints + this.hp;
   }
   
   public final int getShieldArmorHullMAXSummary() {
	   return (int)this.maxShield + this.maxArmorPoints + this.maxHp;
   }

   public final int getArmorHP() {
      return this.armorPoints;
   }

   public final int getMaxArmorHP() {
      return this.maxArmorPoints;
   }

   public final void regenerateShield(float var1) {
      if(this.shield + var1 < (float)this.maxShield) {
         this.shield += var1;
      } else {
         this.shield = (float)this.maxShield;
      }

      this.updateDamageRate();
   }

   public final void regenerateArmor() {
      if(this.armorPoints + 1 < this.maxArmorPoints) {
         ++this.armorPoints;
      } else {
         this.armorPoints = this.maxArmorPoints;
      }

      this.updateDamageRate();
   }

   public final void regenerateHull() {
      if(this.hp + 1 < this.maxHp) {
         ++this.hp;
      } else {
         this.hp = this.maxHp;
      }

      this.updateDamageRate();
   }

   public final int getHitpoints() {
      return this.hp;
   }

   public final int getMaxHitpoints() {
      return this.maxHp;
   }
   
   public final int getEMPHealthPoints() {
	   return this.empPoints;
   }
   
   private void updateDamageRate() { // health
		this.percentHP = (int)((float)this.hp / (float)this.maxHp * 100.0F); // обшивка
		this.percentShield = (int)(this.shield / (float)this.maxShield * 100.0F); // щит
		this.percentArmor = (int)((float)this.armorPoints / (float)this.maxArmorPoints * 100.0F); // броня
		this.percentEMP = (int)((float)this.empPoints / (float)this.maxEmpPoints * 100.0F); // электро-hp
		this.summaryPercent = (int)((float)this.getCombinedHP() / (float)this.getShieldArmorHullMAXSummary() * 100.0F); // суммарное здоровье
	}
	
	public final int getSummaryPercent() {
		return this.summaryPercent;
	}

   public final int getDamageRate() {
      return this.percentHP;
   }

   public final int getEmpDamageRate() {
      return this.percentEMP;
   }

   public final int getShieldDamageRate() {
      return this.percentShield;
   }

   public final int getArmorDamageRate() {
      return this.percentArmor;
   }

   public final void setVulnerable_(boolean var1) {
      this.vulnerable = var1;
   }

   public final void setHitVector_(int var1, int var2, int var3) {
      this.hitVector.x = var1;
      this.hitVector.y = var2;
      this.hitVector.z = var3;
   }

   public final void setBombForce(float var1) {
      this.var_a43 = var1;
   }

   public final void setEmpForce_(float var1) {
      this.empForce = var1;
   }

   public final float getBombForce() {
      return this.var_a43;
   }

   public final float setEmpForce_() {
      return this.empForce;
   }

   public final AEVector3D getHitVector() {
      return this.hitVector;
   }

   public final void damageEmp(int var1, boolean var2) {
      if(this.vulnerable && this.active && this.empPoints > 0 && this.hp > 0) {
         if(!var2 && this.kiPlayer != null && !this.tempEnemy_ && this.kiPlayer.race != 9 && Status.getSystem() != null && this.kiPlayer.race == Status.getSystem().getRace()) {
            this.var_b0b += var1;
            if(this.var_b0b > this.maxEmpPoints / 3) {
               this.var_b5c = true;
               this.kiPlayer.var_36b.friendTurnedEnemy(this.kiPlayer.race);
            }
         }

         this.empPoints -= var1;
         if(this.empPoints <= 0) {
            if(!var2 && this.kiPlayer != null) {
               if(!this.tempEnemy_ && this.kiPlayer.race != 9 && Status.getSystem() != null && this.kiPlayer.race == Status.getSystem().getRace()) {
                  this.kiPlayer.var_36b.alarmAllFriends(this.kiPlayer.race, false);
               }

               if(!this.kiPlayer.var_5c1 && !this.kiPlayer.junk) {
                  Status.getStanding().applyDisable(this.kiPlayer.race);
               }
            }

            float var3 = (float)this.empImpactForce;
            this.empForce = var3;
            this.var_bfe = true;
            this.empPoints = 0;
            this.var_bbb = 0;
         }

         this.updateDamageRate();
      }
   }

   public final void damageHP(int var1, boolean var2) {
      if(this.vulnerable && this.active && this.hp > 0) {
         if(!var2 && this.kiPlayer != null && !this.tempEnemy_ && this.kiPlayer.race != 9 && Status.getSystem() != null && this.kiPlayer.race == Status.getSystem().getRace()) {
            this.egoInflictDamage += var1;
            if(this.egoInflictDamage > this.maxHp / 3) {
               this.var_b5c = true;
               this.kiPlayer.var_36b.friendTurnedEnemy(this.kiPlayer.race);
            }

            if(this.kiPlayer != null && this.egoInflictDamage > this.maxHp - this.maxHp / 3) {
               this.kiPlayer.var_36b.alarmAllFriends(this.kiPlayer.race, true);
            }
         }

         if((var1 = (int)this.shield - var1) < 0) {
            this.shield = 0.0F;
            if((var1 = this.armorPoints - -var1) < 0) {
               this.armorPoints = 0;
               this.hp -= -var1;
            } else {
               this.armorPoints = var1;
            }
         } else {
            this.shield = (float)var1;
         }

         if(this.hp <= 0) {
            this.hp = 0;
            if(var2) {
               this.killedByKI = true;
            } else if(this.kiPlayer != null && !this.kiPlayer.var_5c1 && !this.kiPlayer.junk) {
               Status.getStanding().applyKill(this.kiPlayer.race);
            }
         }

         this.updateDamageRate();
      }
   }

   public final boolean isAlwaysEnemy() {
      return this.var_b5c;
   }

   public final void setAlwaysEnemy() {
      this.var_b5c = true;
   }

   public final boolean hasGunOfType(int var1) {
      return var1 < 3 && var1 >= 0?this.guns[var1] != null:false;
   }

   public final AEVector3D getPosition(AEVector3D var1) {
      return this.transform.getPosition(var1);
   }

   public final void setActive(boolean var1) {
      this.active = var1;
   }

   public final boolean isActive() {
      return this.active;
   }

   public final boolean isDead() {
      return this.hp <= 0;
   }

   public final void removeAllGuns() {
      this.guns = null;
   }

   public final void resetGunDelay(int var1) {
      if(this.guns != null && 0 < this.guns.length && this.guns[0] != null) {
         for(var1 = 0; var1 < this.guns[0].length; ++var1) {
            this.guns[0][var1].timeSinceLastShot = 0;
         }
      }

   }

   public final void playShootSound__(int var1, long var2, boolean var4, Matrix var5) {
      if(this.guns != null && var1 < this.guns.length && var1 >= 0 && this.guns[var1] != null) {
         for(int var6 = 0; var6 < this.guns[var1].length; ++var6) {
            if(this.guns[var1][var6].timeSinceLastShot > this.guns[var1][var6].reloadTimeMilis && this.guns[var1][var6].shoot(var5, var2, var4)) {
               this.guns[var1][var6].timeSinceLastShot = 0;
               if(this.playShootSound) {
                  switch(this.guns[var1][var6].subType) {
                  case 4: // звук выстрелов
                     GlobalStatus.soundManager.playSfx(8);
                     break;
                  case 5:
                     GlobalStatus.soundManager.playSfx(9);
                     break;
                  case 6:
                     GlobalStatus.soundManager.playSfx(10);
                     break;
                  case 7:
                     GlobalStatus.soundManager.playSfx(10);
                  }
               }
            }
         }
      }

   }

   public final void shoot_(int var1, long var2, boolean var4) {
      this.playShootSound__(var1, var2, false, this.transform);
   }

   public final boolean shoot(int var1, int var2, long var3, boolean var5) {
      Matrix var13 = this.transform;
      boolean var4 = false;
      long var9 = var3;
      int var12 = var2;
      var2 = var1;
      Player var11 = this;
      boolean var6 = true;
      if(this.guns != null && var1 < this.guns.length && var1 >= 0 && this.guns[var1] != null) {
         for(int var7 = 0; var7 < var11.guns[var2].length; ++var7) {
            Gun var8;
            if(((var8 = var11.guns[var2][var7]).subType == 7 || var8.subType == 6) && var8.projectilesTimeLeft[0] >= 0) {
               var8.ignite();
            } else if(var8.index == var12 && var8.timeSinceLastShot > var8.reloadTimeMilis) {
               if(var8.shoot(var13, var9, var4)) {
                  if(var11.playShootSound) { // если выпущена ракета
                     switch(var11.guns[var2][var7].subType) {
                     case 4: // звук пуска ракет
                        GlobalStatus.soundManager.playSfx(8);
                        break;
                     case 5:
                        GlobalStatus.soundManager.playSfx(9);
                        break;
                     case 6:
                        GlobalStatus.soundManager.playSfx(10);
                        break;
                     case 7:
                        GlobalStatus.soundManager.playSfx(10);
                     }
                  }

                  var8.timeSinceLastShot = 0;
                  break;
               }

               if(var8.ammo <= 0) {
                  var6 = false;
               }
            }
         }
      }

      return var6;
   }

   public final void update(long var1) {
      this.var_766 = (int)((long)this.var_766 + var1);
      if(this.var_766 > 3000) {
         this.var_766 = 0;
      }

      if(this.var_bfe) {
         this.var_bbb = (int)((long)this.var_bbb + var1);
         this.empPoints = (int)((float)this.var_bbb / (float)this.empImpactForce * (float)this.maxEmpPoints);
         if(this.empPoints > this.maxEmpPoints) {
            this.empPoints = this.maxEmpPoints;
            this.var_bfe = false;
            this.var_bbb = 0;
         }

         this.updateDamageRate();
      }

   }
}
