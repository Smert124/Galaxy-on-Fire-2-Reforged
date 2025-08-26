package GoF2;

import AE.AbstractMesh;
import AE.BoundingVolume;
import AE.GlobalStatus;
import AE.Math.AEVector3D;


public class PlayerStaticFar extends PlayerStatic {

   protected BoundingVolume[] boundingBoxes;


   public PlayerStaticFar(int var1, AbstractMesh var2, int var3, int var4, int var5) {
      super(var1, var2, var3, var4, var5);
      this.var_4c6 = var3;
      this.var_4e6 = var4;
      this.var_521 = var5;
      if(var2 != null) {
         short var7 = 7500;
         Player var6 = this.player;
         this.player.var_21b = (float)var7;
         this.mainMesh_.setRenderLayer(2);
      }
   }

   public void appendToRender() {
      super.appendToRender();
   }

   public void update(long var1) {
      if(this.mainMesh_ != null) {
         this.tempVector_ = GlobalStatus.renderer.sub_85().getLocalPos(this.tempVector_);
         this.position.set(this.var_249, this.var_352, this.var_3c2);
         this.position.subtract(this.tempVector_, virtDistToCam_);
         int var3;
         if((var3 = virtDistToCam_.getLength()) > 28000) {
            virtDistToCam_.normalize();
            virtDistToCam_.scale(28000);
            virtDistToCam_.add(this.tempVector_);
            this.mainMesh_.moveTo(virtDistToCam_);
            var3 = (int)(28000.0F / (float)var3 * 4096.0F);
            this.mainMesh_.setScale(var3, var3, var3);
         } else {
            this.mainMesh_.setScale(4096, 4096, 4096);
            this.mainMesh_.moveTo(this.var_249, this.var_352, this.var_3c2);
         }
      }
   }

   public boolean outerCollide(int var1, int var2, int var3) {
      return (float)(var1 - this.var_249) < this.player.var_21b && (float)(var1 - this.var_249) > -this.player.var_21b && (float)(var2 - this.var_352) < this.player.var_21b && (float)(var2 - this.var_352) > -this.player.var_21b && (float)(var3 - this.var_3c2) < this.player.var_21b && (float)(var3 - this.var_3c2) > -this.player.var_21b;
   }

   public final AEVector3D getTargetPos_(AEVector3D var1) {
      var1.x = this.var_4c6;
      var1.y = this.var_4e6;
      var1.z = this.var_521;
      return var1;
   }

   public boolean outerCollide(AEVector3D var1) {
      return this.outerCollide(var1.x, var1.y, var1.z);
   }

   public AEVector3D getProjectionVector_(AEVector3D var1) {
      return this.boundingBoxes != null?this.boundingBoxes[0].getProjectionVector(var1):null;
   }
}
