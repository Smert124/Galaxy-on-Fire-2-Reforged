package AE;

import javax.microedition.lcdui.Image;
import javax.microedition.m3g.Image2D;
import javax.microedition.m3g.Texture2D;

public final class JSRTexture extends ITexture {

   public Texture2D[] textures;
   public Image texture_image;


   public JSRTexture(JSRTexture var1) {
      if(var1 != null) {
         this.textures = new Texture2D[1];

         try {
            Image2D var3 = var1.textures[0].getImage();
            this.textures[0] = new Texture2D(var3);
            this.textures[0].setBlending(Texture2D.FUNC_REPLACE);
            this.textures[0].setWrapping(Texture2D.WRAP_REPEAT, Texture2D.WRAP_REPEAT);
			this.textures[0].setFiltering(Texture2D.FILTER_BASE_LEVEL, Texture2D.FILTER_LINEAR);
            return;
         } catch (Exception var2) {
            this.textures = null;
         }
      }

   }

   public JSRTexture(String[] var1) {
      if(var1 != null) {
         this.textures = new Texture2D[var1.length];

         for(int var2 = 0; var2 < var1.length; ++var2) {
            try {
			   if(GlobalStatus.texture_type[GlobalStatus.textures] == 256) {
				texture_image = AEFile.loadImage(var1[var2] + ".png", false); // Crypt textures?
                Image2D var5 = new Image2D(100, texture_image);
				this.textures[var2] = new Texture2D(var5);
			   }
			   if(GlobalStatus.texture_type[GlobalStatus.textures] == 512 || GlobalStatus.texture_type[GlobalStatus.textures] == 1024) {
				texture_image = AEFile.loadImage(var1[var2] + ".png", false); // Crypt textures?
			    Image tex = AEFile.resize_image(texture_image, GlobalStatus.texture_type[GlobalStatus.textures], GlobalStatus.texture_type[GlobalStatus.textures]);
                Image2D var5 = new Image2D(100, tex);
				this.textures[var2] = new Texture2D(var5);
			   }
			   if(GlobalStatus.texture_type[GlobalStatus.textures] == 2048) {
				texture_image = AEFile.loadImage(var1[var2] + ".png", false); // Crypt textures?
			    Image tex = AEFile.resize_image(texture_image, 1024, 1024);
                Image2D var5 = new Image2D(100, tex);
				this.textures[var2] = new Texture2D(var5);
			   }
			   this.textures[var2].setFiltering(Texture2D.FILTER_BASE_LEVEL, Texture2D.FILTER_LINEAR);
               this.textures[var2].setBlending(Texture2D.FUNC_MODULATE);
               this.textures[var2].setWrapping(Texture2D.WRAP_REPEAT, Texture2D.WRAP_REPEAT);
            } catch (Exception var4) {
               this.textures = null;
            }
         }
      }

   }

   public final Texture2D[] getTexturesArray() {
      return this.textures;
   }

   public final void OnRelease() {
      if(this.textures != null) {
         for(int var1 = 0; var1 < this.textures.length; ++var1) {
            this.textures[var1] = null;
         }

         this.textures = null;
      }

   }
}
