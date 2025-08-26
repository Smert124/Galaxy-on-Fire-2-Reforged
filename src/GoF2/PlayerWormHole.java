/**
* @class Vortex generator
*/

package GoF2;

import AE.AEResourceManager;
import AE.AbstractMesh;
import AE.GlobalStatus;
import AE.Group;
import AE.Math.AEVector3D;

public final class PlayerWormHole extends PlayerStaticFar {

   private int lifeTime;
   private int scale;

   public PlayerWormHole(int var1, AbstractMesh var2, int var3, int var4, int var5, boolean var6) {
      super(6805, var2, var3, var4, var5);
      this.name = GlobalStatus.gameText.getText(269);
      this.setVisible(var6);
      this.geometry = new Group();
      if(var6) {
         this.mainMesh_.setAnimationSpeed(25); // Default: 30
         this.mainMesh_.setRotation(-128, -256, 10);
         this.mainMesh_.setAnimationMode((byte)2);
      }

      this.geometry.uniqueAppend_(this.mainMesh_);
      this.mainMesh_.moveTo(-128, -128, 128);
      this.geometry.moveTo(var3, var4, var5);
      char var8 = '\u9c40';
      Player var7 = this.player;
      this.player.var_21b = (float)var8;

      for(var1 = 0; var1 < 10; ++var1) { // Default: 10
         (var2 = AEResourceManager.getGeometryResource(6806)).setTransform(this.mainMesh_.getToParentTransform());
         var2.roll((var1 + 1) * GlobalStatus.random.nextInt(400));
         var2.setAnimationMode((byte)2);
         var2.setAnimationSpeed(20 + GlobalStatus.random.nextInt(50));
         this.geometry.uniqueAppend_(var2);
      }

      this.reset(false);
   }

   public final void reset(boolean var1) {
      this.lifeTime = var1?'\u9858':0;
      this.scale = 4096;
   }

   public final boolean isShrinking() {
      return this.lifeTime > '\u9c40';
   }

   public final void setPosition(int var1, int var2, int var3) {
      this.var_249 = var1;
      this.var_352 = var2;
      this.var_3c2 = var3;
      this.geometry.moveTo(var1, var2, var3);
   }

   public final void appendToRender() {
      if(this.visible) {
         GlobalStatus.renderer.drawNodeInVF(this.geometry);
      }

   }

   public final void update(long var1) {
      if(this.visible) {
         this.lifeTime = (int)((long)this.lifeTime + var1);
         int var2;
         int var5;
         if(this.lifeTime < 0) {
            this.scale = 4096 - (int)((float)(-this.lifeTime) / 3000.0F * 4096.0F);
            if(this.lifeTime >= 0) {
               this.scale = 4096;
            }
         } else if(this.lifeTime > '\u9c40') {
            if((var5 = Status.getCurrentCampaignMission()) == 40 || var5 == 42) {
               this.lifeTime = '\u9c40';
            }

            this.scale = 4096 - (int)((float)(this.lifeTime - '\u9c40') / 3000.0F * 4096.0F);
            if(this.lifeTime > '\ua7f8') {
               if(!Status.inAlienOrbit() && !Status.getStation().isAttackedByAliens()) {
                  this.visible = false;
                  this.geometry.setDraw(false);
               } else {
                  this.lifeTime = -3000;
                  var2 = (20000 + GlobalStatus.random.nextInt('\u9c40')) * (GlobalStatus.random.nextInt(2) == 0?1:-1);
                  int var3 = (20000 + GlobalStatus.random.nextInt('\u9c40')) * (GlobalStatus.random.nextInt(2) == 0?1:-1);
                  int var4 = (20000 + GlobalStatus.random.nextInt('\u9c40')) * (GlobalStatus.random.nextInt(2) == 0?1:-1);
                  if(var5 == 29 || var5 == 41) {
                     this.tempVector_ = this.var_36b.getPlayer().getPosition();
                     var2 += this.tempVector_.x + var2 * 3;
                     var3 += this.tempVector_.y + var3 * 3;
                     var4 += this.tempVector_.z + var4 * 3;
                  }

                  this.setPosition(var2, var3, var4);
                  if(this.var_36b.getPlayer().goingToWormhole()) {
                     this.var_36b.getPlayer().getHud().hudEvent(6, this.var_36b.getPlayer());
                     this.var_36b.getPlayer().setAutoPilot((KIPlayer)null);
                  }
               }
            }
         }

         this.tempVector_ = GlobalStatus.renderer.sub_85().getLocalPos(this.tempVector_);
         this.position.set(this.var_249, this.var_352, this.var_3c2);
         this.position.subtract(this.tempVector_, virtDistToCam_);
         if((var5 = virtDistToCam_.getLength()) > 28000) {
            virtDistToCam_.normalize();
            virtDistToCam_.scale(28000);
            virtDistToCam_.add(this.tempVector_);
            this.geometry.moveTo(virtDistToCam_);
            var2 = (int)(28000.0F / (float)var5 * (float)this.scale);
            this.geometry.setScale(var2, var2, var2);
         } else {
            this.geometry.setScale(this.scale, this.scale, this.scale);
            this.geometry.moveTo(this.var_249, this.var_352, this.var_3c2);
         }

         virtDistToCam_ = this.geometry.getPostition();
         this.tempVector_.subtract(virtDistToCam_);
         this.tempVector_.normalize();
         virtDistToCam_.set(0, 4096, 0);
         this.position = virtDistToCam_.crossProduct(this.tempVector_, this.position);
         this.position.normalize();
         (virtDistToCam_ = this.position.crossProduct(this.tempVector_, virtDistToCam_)).normalize();
         this.geometry.setTransform(this.position, virtDistToCam_, this.tempVector_);
      }

   }

   public final AEVector3D getPosition(AEVector3D var1) {
      var1.x = this.var_249;
      var1.y = this.var_352;
      var1.z = this.var_3c2;
      return var1;
   }
}
