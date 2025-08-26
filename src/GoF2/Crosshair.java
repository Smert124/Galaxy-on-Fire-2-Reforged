package GoF2;

import javax.microedition.lcdui.Image;

import AE.AECamera;
import AE.GlobalStatus;
import AE.Math.AEVector3D;
import AE.Math.Matrix;

public final class Crosshair {

   private int[] localPosition;
   private Image image;
   public static AEVector3D relativePos;
   public static AEVector3D screenPos;


   public Crosshair() {
      relativePos = new AEVector3D();
      screenPos = new AEVector3D();

      try {
	   if(this.image == null) {
         this.image = Globals.hudCrosshair;
	   }
      } catch (Exception var2) {
         var2.printStackTrace();
      }

      this.localPosition = new int[3];
   }

   public final void update(Matrix var1, AECamera var2) {
      this.localPosition[0] = var1.getPositionX() + 5 * var1.getDirectionX();
      this.localPosition[1] = var1.getPositionY() + 5 * var1.getDirectionY();
      this.localPosition[2] = var1.getPositionZ() + 5 * var1.getDirectionZ();
      relativePos.set(this.localPosition[0], this.localPosition[1], this.localPosition[2]);
      screenPos.set(relativePos);
      var2.getScreenPosition(screenPos);
   }

   public final void OnRelease() {
      this.localPosition = null;
      screenPos = null;
      this.image = null;
   }

   public final void draw() {
      GlobalStatus.graphics.drawImage(this.image, screenPos.x, screenPos.y, 3);
   }
}
