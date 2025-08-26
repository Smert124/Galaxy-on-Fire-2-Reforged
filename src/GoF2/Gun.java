package GoF2;

import AE.GlobalStatus;
import AE.Math.AEVector3D;
import AE.Math.Matrix;


public final class Gun {

   private static final short[][][] MUZZLE_POSITIONS = new short[][][]{{{(short)0, (short)0, (short)0}}, {{(short)270, (short)0, (short)100}, {(short)-270, (short)0, (short)100}}, {{(short)320, (short)0, (short)100}, {(short)0, (short)0, (short)100}, {(short)-320, (short)0, (short)100}}, {{(short)370, (short)0, (short)100}, {(short)170, (short)100, (short)150}, {(short)-170, (short)100, (short)150}, {(short)-370, (short)0, (short)100}}, {{(short)350, (short)100, (short)200}, {(short)270, (short)-100, (short)150}, {(short)0, (short)0, (short)100}, {(short)-270, (short)-100, (short)150}, {(short)-350, (short)100, (short)200}}, {{(short)500, (short)100, (short)150}, {(short)500, (short)-100, (short)150}, {(short)200, (short)0, (short)100}, {(short)-200, (short)0, (short)100}, {(short)-500, (short)-100, (short)150}, {(short)-500, (short)100, (short)150}}, {{(short)500, (short)-100, (short)150}, {(short)400, (short)100, (short)150}, {(short)200, (short)-50, (short)100}, {(short)0, (short)0, (short)150}, {(short)-200, (short)-50, (short)100}, {(short)-400, (short)100, (short)150}, {(short)-500, (short)-100, (short)150}}, {{(short)550, (short)-100, (short)100}, {(short)550, (short)100, (short)100}, {(short)300, (short)0, (short)100}, {(short)100, (short)0, (short)150}, {(short)-100, (short)0, (short)150}, {(short)-300, (short)0, (short)100}, {(short)-550, (short)100, (short)100}, {(short)-550, (short)-100, (short)100}}, {{(short)550, (short)-100, (short)100}, {(short)550, (short)100, (short)100}, {(short)300, (short)0, (short)100}, {(short)100, (short)100, (short)150}, {(short)0, (short)-100, (short)100}, {(short)-100, (short)100, (short)150}, {(short)-300, (short)0, (short)100}, {(short)-550, (short)100, (short)100}, {(short)-550, (short)-100, (short)100}}, {{(short)600, (short)-120, (short)100}, {(short)600, (short)120, (short)100}, {(short)400, (short)0, (short)100}, {(short)200, (short)100, (short)150}, {(short)200, (short)-100, (short)100}, {(short)-200, (short)-100, (short)100}, {(short)-200, (short)100, (short)150}, {(short)-400, (short)0, (short)100}, {(short)-600, (short)120, (short)100}, {(short)-600, (short)-120, (short)100}}};
   private Level level;
   private Player[] targets;
   public AEVector3D[] projectilesPos;
   AEVector3D[] projectilesDir;
   private AEVector3D tempPos;
   private AEVector3D tempDir;
   private Player tempTarget;
   public int[] projectilesTimeLeft;
   private int equipmentId;
   private int damage;
   public int ammo;
   public float projectileSpeed;
   public int range;
   public AEVector3D muzzleOffset;
   public boolean fired;
   private AEVector3D tempVector;
   public int timeSinceLastShot;
   public int reloadTimeMilis;
   boolean inAir;
   private boolean unused547_;
   public int index;
   public int subType;
   private int magnitude;
   private Sparks sparks;
   private boolean unused5de_;
   private boolean friendGun;
   private int spread;


   public Gun(int var1, int var2, int var3, int var4, int var5, int var6, float var7, AEVector3D var8, AEVector3D var9) {
      this.damage = var2;
      this.projectileSpeed = var7;
      this.equipmentId = var1;
      this.muzzleOffset = var8;
      this.range = var5;
      this.reloadTimeMilis = var6;
      this.timeSinceLastShot = 0;
      if(var4 < 0) {
         var4 = Integer.MAX_VALUE;
      }

      this.ammo = var4;
      this.projectilesTimeLeft = new int[var3];
      this.projectilesPos = new AEVector3D[var3];
      this.projectilesDir = new AEVector3D[var3];
      this.tempPos = new AEVector3D();
      this.tempDir = new AEVector3D();

      for(var1 = 0; var1 < var3; ++var1) {
         this.projectilesPos[var1] = new AEVector3D('\uc350', 0, 0);
         this.projectilesDir[var1] = new AEVector3D();
         this.projectilesTimeLeft[var1] = -1;
      }

      this.unused547_ = true;
      this.fired = false;
      this.tempVector = new AEVector3D();
      this.inAir = false;
      this.sparks = null;
      this.targets = null;
      this.unused5de_ = false;
      this.friendGun = false;
      this.spread = 0;
   }

   public final void setSpread(int var1) {
      this.spread = 1000;
   }

   public final void setFriendGun(boolean var1) {
      this.friendGun = true;
   }

   public final void setMagnitude(int var1) {
      this.magnitude = var1;
   }

   public final int getMagnitude() {
      return this.magnitude;
   }

   public final void setOffset(int var1, int var2) {
      short[] var3 = MUZZLE_POSITIONS[var2 - 1][var1];
      this.tempVector.set(this.muzzleOffset);
      this.muzzleOffset = new AEVector3D(var3[0] + this.tempVector.x, var3[1] + this.tempVector.y, var3[2] + this.tempVector.z); // позиция оружия на корабле
   }

   public final void OnRelease() {
      this.level = null;
      this.targets = null;
      this.projectilesPos = null;
      this.projectilesDir = null;
      this.tempPos = null;
      this.tempDir = null;
      this.tempTarget = null;
      this.projectilesTimeLeft = null;
      this.muzzleOffset = null;
      this.sparks = null;
   }

   public final void setSparks(Sparks var1) {
      this.sparks = var1;
   }

   public final void setLevel(Level var1) {
      this.level = var1;
   }

   public final void setTargets(Player[] var1) {
      this.targets = var1;
   }

   public final Player[] getTargets() {
      return this.targets;
   }

   public final boolean shoot(Matrix var1, long var2, boolean var4) {
      Matrix var10001 = var1;
      var1 = null;
      Matrix var10 = var10001;
      Gun var9 = this;
      this.timeSinceLastShot = 0;
      this.fired = true;
      this.inAir = true;
      Item var3 = null;
      if(!this.friendGun && ((var3 = Status.getShip().getEquipment()[this.equipmentId]) == null || var3.getAmount() == 0)) {
         return false;
      } else {
         for(int var11 = 0; var11 < var9.projectilesPos.length; ++var11) {
            if(var9.projectilesTimeLeft[var11] <= 0) {
               var9.projectilesPos[var11].set(var10.getPosition(var9.tempVector));
               if(var9.muzzleOffset != null) {
                  var9.tempVector.set(var9.muzzleOffset);
                  var9.tempPos = var10.transformVectorNoScale(var9.tempVector, var9.tempPos);
                  var9.projectilesPos[var11].add(var9.tempPos);
               }

               var9.tempVector.set(var10.getDirection(var9.tempVector));
               var9.projectilesDir[var11].set(var9.tempVector);
               if(var9.spread > 0) {
                  var9.projectilesDir[var11].x += -(var9.spread >> 1) + GlobalStatus.random.nextInt(var9.spread);
                  var9.projectilesDir[var11].y += -(var9.spread >> 1) + GlobalStatus.random.nextInt(var9.spread);
                  var9.projectilesDir[var11].z += -(var9.spread >> 1) + GlobalStatus.random.nextInt(var9.spread);
               }

               var9.projectilesDir[var11].normalize();
               var9.projectilesDir[var11].scale((int)(var9.projectileSpeed * (float)var2) << 12);
               var9.projectilesDir[var11].x >>= 12;
               var9.projectilesDir[var11].y >>= 12;
               var9.projectilesDir[var11].z >>= 12;
               var9.projectilesTimeLeft[var11] = var9.range;
               if(!var9.friendGun && var9.ammo > 0 && var3 != null && var3.getType() == 1) {
                  --var9.ammo;
                  var3.changeAmount(-1);
                  if(var9.ammo == 0) {
                     Status.getShip().freeSlot(var3);
                  }
               }

               return true;
            }
         }

         return false;
      }
   }

   public final void ignite() {
      if(this.subType == 7) {
         ++Status.bombsUsed;
      }

      if(this.targets != null) {
         for(int var1 = 0; var1 < this.targets.length; ++var1) {
            this.tempTarget = this.targets[var1];
            if((this.subType != 6 || !this.tempTarget.isAsteroid()) && this.tempTarget.isActive()) {
               for(int var2 = 0; var2 < this.projectilesPos.length; ++var2) {
                  this.tempPos.set(this.projectilesPos[var2]);
                  this.tempVector = this.tempTarget.transform.getPosition(this.tempVector);
                  this.tempVector.subtract(this.tempPos);
                  int var3;
                  if((var3 = this.tempVector.getLength()) < this.magnitude) {
                     float var6;
                     if((var6 = (float)(this.magnitude - var3) / (float)this.magnitude) > 1.0F) {
                        var6 = 1.0F;
                     }

                     int var4 = Globals.getItems()[this.index].getAttribute(10);
                     if(this.subType == 7) {
                        if(var4 != -979797979) {
                           this.tempTarget.setEmpForce_((float)var4 * var6);
                        }

                        this.tempTarget.setBombForce(var6);
                        if(this.tempTarget.isAsteroid()) {
                           var6 *= 0.6F;
                        }

                        this.tempTarget.damageHP((int)((float)this.damage * var6), this.friendGun);
                     } else {
                        this.tempTarget.damageEmp((int)((float)var4 * var6), this.friendGun);
                     }

                     this.tempVector.normalize();
                     this.tempTarget.setHitVector_(this.tempVector.x, this.tempVector.y, this.tempVector.z);
                     this.projectilesTimeLeft[var2] = -1;
                     this.tempVector.set(this.projectilesDir[var2]);
                     this.tempVector.normalize();
                  }
               }
            }
         }
      }

      boolean var5 = this.subType == 7;
      this.level.flashScreen(var5?3:4);
      GlobalStatus.soundManager.playSfx(var5?11:12);
      if(this.sparks != null) {
         this.sparks.explode(this.projectilesPos[0]);
      }

   }

   public final void calcCharacterCollision(long var1) {
      this.timeSinceLastShot = (int)((long)this.timeSinceLastShot + var1);
      if(this.sparks != null) {
         this.sparks.update(var1);
      }

      if(this.inAir) {
         Gun var3 = this;
         boolean var6 = false;
         if(this.targets != null) {
            boolean var7;
            boolean var8 = (var7 = this.subType == 7 || this.subType == 6) || this.subType == 4 || this.subType == 5;

            label92:
            for(int var9 = 0; var9 < var3.targets.length; ++var9) {
               var3.tempTarget = var3.targets[var9];
               if(var3.tempTarget.isActive()) {
                  for(int var10 = 0; var10 < var3.projectilesPos.length; ++var10) {
                     var3.tempPos.set(var3.projectilesPos[var10]);
                     var3.tempDir.set(var3.projectilesDir[var10]);
                     int var4;
                     int var5;
                     int var13;
                     if(var3.tempTarget.var_d5a) {
                        var4 = var3.tempTarget.posX - var3.tempPos.x + var3.tempDir.x;
                        var5 = var3.tempTarget.posY - var3.tempPos.y + var3.tempDir.y;
                        var13 = var3.tempTarget.posZ - var3.tempPos.z + var3.tempDir.z;
                     } else {
                        var4 = var3.tempTarget.transform.getPositionX() - var3.tempPos.x + var3.tempDir.x;
                        var5 = var3.tempTarget.transform.getPositionY() - var3.tempPos.y + var3.tempDir.y;
                        var13 = var3.tempTarget.transform.getPositionZ() - var3.tempPos.z + var3.tempDir.z;
                     }

                     int var11 = (int)var3.tempTarget.var_21b;
                     if(var4 < var11 && var4 > -var11 && var5 < var11 && var5 > -var11 && var13 < var11 && var13 > -var11) {
                        if(var8 && var3.tempTarget.isAsteroid()) {
                           var3.tempTarget.damageHP(9999, false);
                           break label92;
                        }

                        if(var7) {
                           var3.ignite();
                           break label92;
                        }

                        if((var4 = Globals.getItems()[var3.index].getAttribute(10)) != -979797979) {
                           var3.tempTarget.damageEmp(var4, var3.friendGun);
                        }

                        var3.tempTarget.damageHP(var3.damage, var3.friendGun);
                        var3.tempTarget.setHitVector_(-var3.tempDir.x, -var3.tempDir.y, -var3.tempDir.z);
                        var3.projectilesTimeLeft[var10] = -1;
                        var3.tempVector.set(var3.projectilesDir[var10]);
                        var3.tempVector.normalize();
                        if(var3.sparks != null) {
                           var3.sparks.explode(var3.projectilesPos[var10]);
                        }
                     }
                  }
               }
            }
         }

         for(int var12 = 0; var12 < this.projectilesPos.length; ++var12) {
            if(this.projectilesTimeLeft[var12] > 0) {
               this.projectilesTimeLeft[var12] -= (int)var1;
               this.projectilesPos[var12].add(this.projectilesDir[var12]);
               if(this.projectilesTimeLeft[var12] <= 0 && (this.subType == 7 || this.subType == 6)) {
                  this.ignite();
               }
            } else {
               this.projectilesPos[var12].set('\uc350', '\uc350', '\uc350');
               this.projectilesDir[var12].set(0, 0, 0);
            }
         }
      }

   }

   public final void renderSparks() {
      if(this.sparks != null) {
         this.sparks.render();
      }

   }

}
