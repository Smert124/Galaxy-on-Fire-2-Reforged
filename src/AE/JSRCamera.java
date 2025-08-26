package AE;

import javax.microedition.m3g.Camera;
import javax.microedition.m3g.Transform;

import AE.PaintCanvas.AEGraphics3D;

public final class JSRCamera extends AECamera {

   private Camera camera;
   private static Transform transform = new Transform();


   public JSRCamera(int var1, int var2, int var3, int var4, int var5) {
      super(var1, var2, var3, var4, var5);
   }

   public final void setPerspective(int var1, int var2, int var3) {
      float var4 = 1.0F;
      super.setPerspective(var1, var2, var3);
      if(this.camera == null) {
         this.camera = new Camera();
      }

      if(this.var_680 != 0 && this.var_702 != 0) {
         var4 = (float)this.var_680 / (float)this.var_702;
      }

      this.camera.setPerspective((float)var1 * GlobalStatus.current_fov, var4, (float)var2, (float)var3); // Влияет на поле зрения..
   }

   public final void setActive() {
      if(AEGraphics3D.graphics3D != null) {
         AEGraphics3D.graphics3D.setCamera(this.camera, transform);
      }

   }

}
