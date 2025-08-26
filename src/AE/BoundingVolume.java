package AE;

import AE.Math.AEVector3D;


public abstract class BoundingVolume {

   private static AEVector3D temp = new AEVector3D();
   protected int var_230;
   protected int var_34f;
   protected int var_3cc;
   protected int var_4a5;
   protected int var_53e;
   protected int var_5c6;


   public BoundingVolume(int var1, int var2, int var3, int var4, int var5, int var6) {
      this.var_230 = var1;
      this.var_34f = var2;
      this.var_3cc = var3;
      this.var_4a5 = var4;
      this.var_53e = var5;
      this.var_5c6 = var6;
   }

   public final AEVector3D getProjectionVector(AEVector3D var1) {
      temp.set(var1.x - this.var_230, var1.y - this.var_34f, var1.z - this.var_3cc);
      temp.normalize();
      return temp;
   }

   public void setPosition(int var1, int var2, int var3) {
      this.var_230 = var1;
      this.var_34f = var2;
      this.var_3cc = var3;
   }

   public boolean outerCollide_(int var1, int var2, int var3) {
      return false;
   }

   public boolean isPointInBounding(int var1, int var2, int var3) {
      return false;
   }

}
