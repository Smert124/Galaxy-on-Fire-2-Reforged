/** @class Uncoder
**  @author Smert124
** ���������� ������ ������� .HCi, .m3g, .HCt
*/

package AE;

import java.io.DataInputStream;
import java.io.InputStream;
import javax.microedition.lcdui.Image;
import java.io.IOException;

public final class AEFile {

   private static Class clazs;


   public static Image resizeImage(Image var0, int var1, int var2) {
      try {
         float var17 = (float)var1 / (float)var0.getWidth();
         float var18 = (float)var2 / (float)var0.getHeight();
         int var3 = var0.getWidth();
         int var4 = var0.getHeight();
         int[] var5 = new int[var3 * var4];
         int[] var6 = new int[(int)((float)var3 * var17) * (int)((float)var4 * var18)];
         var0.getRGB(var5, 0, var3, 0, 0, var3, var4);
         int[] var10000 = var5;
         int var8 = (int)((float)var3 * var17);
         int var7 = var3;
         var6 = var6;
         var5 = var5;
         int var9 = var10000.length / var3;
         int var10 = var6.length / var8;

         for(int var11 = 0; var11 < var8; ++var11) {
            for(int var12 = 0; var12 < var10; ++var12) {
               float var13 = (float)var11 * (float)(var7 - 1) / (float)(var8 - 1);
               boolean var14 = false;
               int var20;
               if((double)var13 - Math.floor((double)var13) > 0.5D) {
                  var20 = (int)Math.ceil((double)var13);
               } else {
                  var20 = (int)Math.floor((double)var13);
               }

               var13 = (float)var12 * (float)(var9 - 1) / (float)(var10 - 1);
               boolean var15 = false;
               int var19;
               if((double)var13 - Math.floor((double)var13) > 0.5D) {
                  var19 = (int)Math.ceil((double)var13);
               } else {
                  var19 = (int)Math.floor((double)var13);
               }

               var6[var11 + var12 * var8] = var5[var20 + var19 * var7];
            }
         }

         var0 = Image.createRGBImage(var6, (int)((float)var3 * var17), (int)((float)var4 * var18), true);
         System.gc();
      } catch (Exception var16) {
      }

      return var0;
   }
   
   public static Image cutImageRegion(Image sourceImage, int startCutX, int startCutY, int width, int height) {
	   // �������� ������� ��������� �����������
	   int imageWidth = sourceImage.getWidth();
	   int imageHeight = sourceImage.getHeight();
	   
	   // ���������, ��� ���������� � ������� ��������� � ���������� ��������
	   if(startCutX < 0 || startCutY < 0 || width <= 0 || height <= 0 ||
		startCutX + width > imageWidth || startCutY + height > imageHeight) {
		   throw new IllegalArgumentException("Invalid cut region parameters" + imageWidth + "," + imageHeight + "; CUT: " + startCutX + "," + startCutY + "; SIZE: " + width + "," + height);
	   }
	   
	   // ������� ������ ��� �������� RGB ��������
	   int[] rgbArray = new int[width * height];
	   
	   // ��������� RGB �������� �� ��������� �����������
	   sourceImage.getRGB(rgbArray, 0, width, startCutX, startCutY, width, height);
	   
	   // ������� ����� ����������� �� ������� RGB
	   Image cutImage = Image.createRGBImage(rgbArray, width, height, true);
	   
	   return cutImage;
	}

   
   public static Image sub_241(Image var0, boolean var1) {
      if(var1) {
         int var34 = var0.getWidth();
         int var2 = var0.getHeight();
         int[] var3 = new int[var34 * var2];
         var0.getRGB(var3, 0, var34, 0, 0, var34, var2);
         int[] var33 = new int[var34 * var2];

         for(int var4 = var34 + 1; var4 < var33.length - 1 - var34; ++var4) {
            int var5 = var3[var4] >>> 24;
            int var6 = var3[var4 - 1 - var34] >> 16 & 255;
            int var7 = var3[var4 - 1 - var34] >> 8 & 255;
            int var8 = var3[var4 - 1 - var34] & 255;
            int var9 = var3[var4 - var34] >> 16 & 255;
            int var10 = var3[var4 - var34] >> 8 & 255;
            int var11 = var3[var4 - var34] & 255;
            int var12 = var3[var4 + 1 - var34] >> 16 & 255;
            int var13 = var3[var4 + 1 - var34] >> 8 & 255;
            int var14 = var3[var4 + 1 - var34] & 255;
            int var15 = var3[var4 - 1] >> 16 & 255;
            int var16 = var3[var4 - 1] >> 8 & 255;
            int var17 = var3[var4 - 1] & 255;
            int var18 = var3[var4] >> 16 & 255;
            int var19 = var3[var4] >> 8 & 255;
            int var20 = var3[var4] & 255;
            int var21 = var3[var4 + 1] >> 16 & 255;
            int var22 = var3[var4 + 1] >> 8 & 255;
            int var23 = var3[var4 + 1] & 255;
            int var24 = var3[var4 - 1 + var34] >> 16 & 255;
            int var25 = var3[var4 - 1 + var34] >> 8 & 255;
            int var26 = var3[var4 - 1 + var34] & 255;
            int var27 = var3[var4 + var34] >> 16 & 255;
            int var28 = var3[var4 + var34] >> 8 & 255;
            int var29 = var3[var4 + var34] & 255;
            int var30 = var3[var4 + 1 + var34] >> 16 & 255;
            int var31 = var3[var4 + 1 + var34] >> 8 & 255;
            int var32 = var3[var4 + 1 + var34] & 255;
            var18 = (var6 + var9 + var12 + var15 + var18 + var21 + var24 + var27 + var30) / 9;
            var19 = (var7 + var10 + var13 + var16 + var19 + var22 + var25 + var28 + var31) / 9;
            var20 = (var8 + var11 + var14 + var17 + var20 + var23 + var26 + var29 + var32) / 9;
            var33[var4] = var5 << 24 | var18 << 16 | var19 << 8 | var20;
         }

         var0 = Image.createRGBImage(var33, var34, var2, true);
      }

      return var0;
   }

    public static Image resize_image(Image image, int newWidth, int newHeight) {
        // �������� ������ �������� ��������� �����������
        int[] originalPixels = new int[image.getWidth() * image.getHeight()];
        image.getRGB(originalPixels, 0, image.getWidth(), 0, 0, image.getWidth(), image.getHeight());

        // �������� ������ ��������� ������� ��������
        int[] resizedPixels = rescaleArray(originalPixels, image.getWidth(), image.getHeight(), newWidth, newHeight);

        // ������� ����� ����������� � ����������� ���������
        Image resizedImage = Image.createRGBImage(resizedPixels, newWidth, newHeight, true);

        return resizedImage;
    }

    private static int[] rescaleArray(int[] originalPixels, int originalWidth, int originalHeight, int newWidth, int newHeight) {
        int[] resizedPixels = new int[newWidth * newHeight];

        // ���������������� ��������� ��������
        for (int y = 0; y < newHeight; y++) {
            for (int x = 0; x < newWidth; x++) {
                // ��������� ���������� ��� ���������������
                int originalX = (x * originalWidth) / newWidth;
                int originalY = (y * originalHeight) / newHeight;

                // �������� ���� �������
                resizedPixels[y * newWidth + x] = originalPixels[originalY * originalWidth + originalX];
            }
        }

        return resizedPixels;
    }
   
   public static Image Aliasing(Image input) { // aliasing
	int wdh=input.getWidth();
	int hgt=input.getHeight();
	int[] pixel=new int[wdh*hgt];
	input.getRGB(pixel,0,wdh,0,0,wdh,hgt);
	int[] pix = new int[wdh * hgt];
	int imwi = wdh;
	for(int io = 1 + imwi; io < pix.length - 1 - imwi; io++) {
		int qa = (pixel[io] >> 24) & 0xff;
		int qr00 = (pixel[io - 1 - imwi] >> 16) & 0xff;
		int qg00 = (pixel[io - 1 - imwi] >> 8) & 0xff;
		int qb00 = pixel[io - 1 - imwi] & 0xff;

		int qr01 = (pixel[io - imwi] >> 16) & 0xff;
		int qg01 = (pixel[io - imwi] >> 8) & 0xff;
		int qb01 = pixel[io - imwi] & 0xff;
		
		int qr02 = (pixel[io + 1 - imwi] >> 16) & 0xff;
		int qg02 = (pixel[io + 1 - imwi] >> 8) & 0xff;
		int qb02 = pixel[io + 1 - imwi] & 0xff;

		int qr10 = (pixel[io - 1] >> 16) & 0xff;
		int qg10 = (pixel[io - 1] >> 8) & 0xff;
		int qb10 = pixel[io - 1] & 0xff;

		int qr11 = (pixel[io] >> 16) & 0xff;
		int qg11 = (pixel[io] >> 8) & 0xff;
		int qb11 = pixel[io] & 0xff;

		int qr12 = (pixel[io + 1] >> 16) & 0xff;
		int qg12 = (pixel[io + 1] >> 8) & 0xff;
		int qb12 = pixel[io + 1] & 0xff;

		int qr20 = (pixel[io - 1 + imwi] >> 16) & 0xff;
		int qg20 = (pixel[io - 1 + imwi] >> 8) & 0xff;
		int qb20 = pixel[io - 1 + imwi] & 0xff;

		int qr21 = (pixel[io + imwi] >> 16) & 0xff;
		int qg21 = (pixel[io + imwi] >> 8) & 0xff;
		int qb21 = pixel[io + imwi] & 0xff;

		int qr22 = (pixel[io + 1 + imwi] >> 16) & 0xff;
		int qg22 = (pixel[io + 1 + imwi] >> 8) & 0xff;
		int qb22 = pixel[io + 1 + imwi] & 0xff;
		qr11 = (qr00 + qr01 + qr02 + qr10 + qr11 + qr12 + qr20 + qr21 + qr22) / 9;
		qg11 = (qg00 + qg01 + qg02 + qg10 + qg11 + qg12 + qg20 + qg21 + qg22) / 9;
		qb11 = (qb00 + qb01 + qb02 + qb10 + qb11 + qb12 + qb20 + qb21 + qb22) / 9;
		pix[io] = (qa << 24) | (qr11 << 16) | (qg11 << 8) | qb11;
	}
   return
   Image.createRGBImage(pix,wdh,hgt,true);
   }
   
   private static int[] reescalaArray(int ai[], int i, int j, int k, int l) { // ������ �����������
	int ai1[] = new int[k * l];
	for(int i1 = 0; i1 < l; i1++) {
	 int j1 = (i1 * j) / l;
	 for(int k1 = 0; k1 < k; k1++) {
	  int l1 = (k1 * i) / k;
	  ai1[k * i1 + k1] = ai[i * j1 + l1];
	 }
	}
	return ai1;
   }

   public static Image loadImage(String var0, boolean var1) {
      Image var2 = null;
      if(var1) {
         try {
            InputStream var8 = (clazs == null?(clazs = getClass("java.lang.Class")):clazs).getResourceAsStream(var0);
            DataInputStream var9;
            int var11;
            byte[] var3 = new byte[var11 = (var9 = new DataInputStream(var8)).available()];
            var9.read(var3, 0, var11);
            var9.close();
            int var10;
            if(var11 < 100) {
               var10 = 10 + var11 % 10;
            } else if(var11 < 200) {
               var10 = 50 + var11 % 20;
            } else if(var11 < 300) {
               var10 = 80 + var11 % 20;
            } else {
               var10 = 100 + var11 % 50;
            }
            for(int var4 = 0; var4 < var10; ++var4) {
               byte var5 = var3[var4];
               var3[var4] = var3[var11 - var4 - var10];
               var3[var11 - var4 - var10] = var5;
            }
			/* for(int var4 = 0; var4 < var10; ++var4) {
               byte var5 = var3[var4];                // ������������ ����: Fishlabs
               var3[var4] = var3[var11 - var4 - 1];
               var3[var11 - var4 - 1] = var5;
            } */
            var2 = Image.createImage(var3, 0, var11);
            System.gc();
         } catch (Exception var7) {
			 GlobalStatus.CATCHED_ERROR = "BLYAT! CHECK IMAGE! <" + var0 + ">";
			 try {
				var2 = Image.createImage("/Resource/error.png");
			} catch (Exception ex) {
				;
			}
         }
      } else {
         try {
            var2 = Image.createImage(var0);
         } catch (IOException var6) {
			GlobalStatus.CATCHED_ERROR = "BLYAT! CHECK IMAGE! <" + var0 + ">";
            try {
				var2 = Image.createImage("/Resource/error.png");
			} catch (Exception ex) {
				;
			}
         }
      }
      return var2;
   }

   public static Image loadCryptedImage(String var0) {
      return loadImage(var0, true);
   }

   public static byte[] readFileBytes(String var0) {
    byte[] var1 = null;
    try {
     InputStream var2 = (clazs == null?(clazs = getClass("java.lang.Class")):clazs).getResourceAsStream(var0);
     DataInputStream var3;
     int var4;
     var1 = new byte[var4 = (var3 = new DataInputStream(var2)).available()];
     var3.read(var1, 0, var4);
     var3.close();
    } catch (Exception var8) {
    }

      return var1;
   }

   private static Class getClass(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var1) {
         throw new NoClassDefFoundError(var1.getMessage());
      }
   }
}
