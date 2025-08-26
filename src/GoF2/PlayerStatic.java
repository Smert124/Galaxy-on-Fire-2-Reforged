package GoF2;

import AE.AbstractMesh;
import AE.GlobalStatus;
import AE.Math.AEVector3D;


public class PlayerStatic extends KIPlayer {

   protected static AEVector3D virtDistToCam_ = new AEVector3D();
   public int var_249;
   public int var_352;
   public int var_3c2;


   public PlayerStatic(int var1, AbstractMesh var2, int var3, int var4, int var5) {
      super(var1, -1, new Player(2000.0F, 0, 0, 0, 0), var2, var3, var4, var5);
      this.var_249 = var3;
      this.var_352 = var4;
      this.var_3c2 = var5;
   }

   public void update(long var1) {}

   public AEVector3D getPosition(AEVector3D var1) {
      if(this.mainMesh_ == null) {
         var1.set(this.var_249, this.var_352, this.var_3c2);
      } else {
         var1.set(this.mainMesh_.getLocalPos(this.tempVector_));
      }

      return var1;
   }

   public void appendToRender() {
      GlobalStatus.renderer.drawNodeInVF(this.mainMesh_);
   }

   public boolean outerCollide(int var1, int var2, int var3) {
      return false;
   }

}
