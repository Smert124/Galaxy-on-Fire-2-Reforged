package AE;

import AE.Math.AEMath;
import AE.Math.AEVector3D;


public abstract class AECamera extends AEGeometry {

   private int nearPlane;
   private int farPlane;
   private AEVector3D[] vfPlaneNormals = new AEVector3D[6];
   private AEVector3D[] vfPlaneNormalsLocal = new AEVector3D[6];
   private int[] lengths;
   private static AEVector3D cameraPos = new AEVector3D();
   private static AEVector3D nearPlaneCenter = new AEVector3D();
   private static AEVector3D farPlaneCenter = new AEVector3D();
   protected int var_680;
   protected int var_702;
   private int verticalProjectionFactor;
   private int horizontalProjectionFactor;
   private int screenWidth = GlobalStatus.var_e75;
   private int screenHeight = GlobalStatus.var_eb6;


   protected AECamera(int var1, int var2, int var3, int var4, int var5) {
      for(int var6 = this.vfPlaneNormalsLocal.length - 1; var6 >= 0; --var6) {
         this.vfPlaneNormalsLocal[var6] = new AEVector3D();
      }

      this.lengths = new int[6];
      this.vfPlaneNormals[0] = new AEVector3D(0, 0, -4096);
      this.vfPlaneNormals[1] = new AEVector3D(0, 0, 4096);
      this.vfPlaneNormals[2] = new AEVector3D();
      this.vfPlaneNormals[3] = new AEVector3D();
      this.vfPlaneNormals[4] = new AEVector3D();
      this.vfPlaneNormals[5] = new AEVector3D();
      this.var_680 = var1;
      this.var_702 = var2;
      this.setPerspective(var3, var4, var5);
   }

   public final void updateTransform(boolean var1) {
      if(this.transformDirty_ || var1) {
         if(this.group != null) {
            this.tempTransform = this.group.tempTransform.multiplyTo(this.globalTransform, this.tempTransform);
         } else {
            this.tempTransform.set(this.globalTransform);
         }

         this.vfPlaneNormalsLocal = this.tempTransform.transformVectorsNoScale(this.vfPlaneNormals, this.vfPlaneNormalsLocal);
         cameraPos = this.tempTransform.getPosition(cameraPos);
         nearPlaneCenter = this.tempTransform.getDirection(nearPlaneCenter);
         farPlaneCenter.set(nearPlaneCenter);
         nearPlaneCenter.scale(-this.nearPlane);
         farPlaneCenter.scale(-this.farPlane);
         nearPlaneCenter.add(cameraPos);
         farPlaneCenter.add(cameraPos);
         this.lengths[0] = nearPlaneCenter.dot(this.vfPlaneNormalsLocal[0]);
         this.lengths[1] = farPlaneCenter.dot(this.vfPlaneNormalsLocal[1]);
         this.lengths[2] = cameraPos.dot(this.vfPlaneNormalsLocal[2]);
         this.lengths[3] = cameraPos.dot(this.vfPlaneNormalsLocal[3]);
         this.lengths[4] = cameraPos.dot(this.vfPlaneNormalsLocal[4]);
         this.lengths[5] = cameraPos.dot(this.vfPlaneNormalsLocal[5]);
         this.transformDirty_ = false;
         this.boundsDirty_ = false;
      }

   }

       public void setPerspective(int fov, int near, final int far) {
        this.nearPlane = near;
        this.farPlane = far;
        int sin = AEMath.sin(fov >> 1);
        int cos = AEMath.cos(fov >> 1);
        int wh = ((this.screenWidth << AEMath.Q) / this.screenHeight);
        int coshw = cos * AEMath.sqrt((int)(this.screenHeight << AEMath.Q) / this.screenWidth) >> AEMath.Q;
        int coswh = cos * AEMath.sqrt(wh) >> AEMath.Q;
        this.vfPlaneNormals[2].set(coshw, 0, -sin);
        this.vfPlaneNormals[3].set(-coshw, 0, -sin);
        this.vfPlaneNormals[4].set(0, -coswh, -sin);
        this.vfPlaneNormals[5].set(0, coswh, -sin);
//        this.vfPlaneNormals[2].set(cos, 0, -sin);
//        this.vfPlaneNormals[3].set(-cos, 0, -sin);
//        this.vfPlaneNormals[4].set(0, -cos, -sin);
//        this.vfPlaneNormals[5].set(0, cos, -sin);
        this.verticalProjectionFactor = (sin << AEMath.Q) / cos;
        this.horizontalProjectionFactor = this.verticalProjectionFactor * wh >> AEMath.Q;
        
    }


   public final void setFoV(int var1) {
      this.setPerspective(var1, this.nearPlane, this.farPlane);
   }

   public final boolean getScreenPosition(AEVector3D var1) {
      if((var1 = this.tempTransform.inverseTransformVector(var1)).z > this.nearPlane) {
         return false;
      } else {
         int var2 = this.horizontalProjectionFactor * var1.z >> 12;
         int var3 = this.verticalProjectionFactor * var1.z >> 12;
         if(var2 != 0 && var3 != 0) {
            var1.x = -((var1.x << 11) / var2 * this.var_680 >> 12) + (this.var_680 >> 1);
            var1.y = ((var1.y << 11) / var3 * this.var_702 >> 12) + (this.var_702 >> 1);
            return var1.x >= 0 && var1.y >= 0 && var1.x < this.var_680 && var1.y < this.var_702;
         } else {
            return false;
         }
      }
   }

   public final byte isInViewFrustum(AEBoundingSphere var1) {
      for(int var3 = 5; var3 >= 0; --var3) {
         int var2;
         if((var2 = var1.center.dot(this.vfPlaneNormalsLocal[var3]) - this.lengths[var3]) < -var1.radius) {
            return (byte)0;
         }

         if(AEMath.abs(var2) < var1.radius) {
            return (byte)1;
         }
      }

      return (byte)2;
   }

   public final void appendToRender(AECamera var1, Renderer var2) {}

   public final void forceAppendToRender(AECamera var1, Renderer var2) {}

   public abstract void setActive();

   public static AECamera create(int var0, int var1, int var2, int var3, int var4) {
      return new JSRCamera(var0, var1, var2, var3, var4);
   }

}
