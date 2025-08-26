package AE;

import javax.microedition.lcdui.Image;

import AE.Math.AEMath;
import AE.Math.AEVector3D;

public final class LensFlareFX {

   private static Image[] flares = new Image[4];
   private static Image mainFlare;


   public LensFlareFX() {
      for(int var1 = 0; var1 < this.flares.length; ++var1) {
		 if(this.flares[var1] == null) {
			 this.flares[var1] = AEFile.loadImage("/Resource/interface/lens" + var1 + ".png", false);
		 }
      }
	  
      int var3 = flares[1].getHeight() / 4;
      int var2 = flares[1].getWidth() / 4;
	  if(this.mainFlare == null) {
		  this.mainFlare = AEFile.resizeImage(AEFile.loadImage("/Resource/interface/lens1.png", false), var2, var3);
	  }
   }

   public final void render2D(AEVector3D var1) {
      int var2 = var1.x;
      int var3 = var1.y;
      int var7;
      if((var7 = var1.z) < 0 && var2 > -GlobalStatus.var_e75 && var2 < GlobalStatus.var_e75 << 1 && var3 > -GlobalStatus.var_eb6 && var3 < GlobalStatus.var_eb6 << 1) {
         var7 = GlobalStatus.var_e75 >> 1;
         int var4 = GlobalStatus.var_eb6 >> 1;
         var2 = var7 - var2;
         var3 = var4 - var3;
         int var5 = AEMath.sqrt((long)(var2 * var2 + var3 * var3));
         var2 = -(var2 << 12);
         var3 = -(var3 << 12);
         if(var5 > 0) {
            var2 /= var5;
            var3 /= var5;
         }
		 GlobalStatus.lighting = var5;
         if(this.flares != null) {
            for(int var6 = 0; var6 < this.flares.length; ++var6) {
               switch(var6) {
               case 0:
				  GlobalStatus.HDR = true;
                  if(var5 > 2000) {
                     GlobalStatus.graphics.drawImage(this.flares[var6], var7 + (var2 * var5 / 2 >> 12), var4 + (var3 * var5 / 2 >> 12), 3);
                     if(this.mainFlare != null) {
                        GlobalStatus.graphics.drawImage(this.mainFlare, var7 + (var2 * var5 / 3 >> 12), var4 + (var3 * var5 / 3 >> 12), 3);
                     }
                  }
                  break;
               case 1:
                  if(var5 > 3000) {
                     GlobalStatus.graphics.drawImage(this.flares[var6], var7 + (var2 * var5 / 8 >> 12), var4 + (var3 * var5 / 8 >> 12), 3);
                     if(this.mainFlare != null) {
                        GlobalStatus.graphics.drawImage(this.mainFlare, var7 - (var2 * var5 / 2 >> 12), var4 - (var3 * var5 / 2 >> 12), 3);
                     }
                  }
                  break;
               case 2:
                  if(var5 > 4000) {
                     GlobalStatus.graphics.drawImage(this.flares[var6], var7 - (var2 * var5 / 4 >> 12), var4 - (var3 * var5 / 4 >> 12), 3);
                     if(this.mainFlare != null) {
                        GlobalStatus.graphics.drawImage(this.mainFlare, var7 - (var2 * var5 / 6 >> 12), var4 - (var3 * var5 / 6 >> 12), 3);
                     }
                  }
                  break;
               case 3:
                  if(var5 > 5000) {
                     GlobalStatus.graphics.drawImage(this.flares[var6], var7 - (var2 * var5 / 7 >> 12), var4 - (var3 * var5 / 7 >> 12), 3);
                     if(this.mainFlare != null) {
                        GlobalStatus.graphics.drawImage(this.mainFlare, var7 - (var2 * var5 / 10 >> 12), var4 - (var3 * var5 / 10 >> 12), 3);
                     }
                  }
               }
            }
         }
      }

   }
}
