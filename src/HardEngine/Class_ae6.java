package HardEngine;


public final class Class_ae6 {

   private static final byte[] var_95 = new byte[]{(byte)49, (byte)50, (byte)51, (byte)52};
   private static int[] var_ca = new int[4];
   private static int var_112 = 0;
   private static int var_25e = 0;


   public static byte[] sub_37(byte[] var0, byte[] var1) throws Class_1476 {
      sub_a3(var0);
      sub_133(var0);
      int var15;
      byte[] var2 = new byte[var15 = var1.length + var_95.length];
      System.arraycopy(var1, 0, var2, 0, var1.length);
      System.arraycopy(var_95, 0, var2, var1.length, var_95.length);
      long var10;
      String var16 = Long.toString(var10 = System.currentTimeMillis() ^ Long.MAX_VALUE);
      long var13 = 1L;

      int var3;
      for(var3 = var16.length() - 1; var3 > 0 && String.valueOf(var13).length() < 8; --var3) {
         var13 *= (long)var16.charAt(var3);
      }

      var16 = Long.toString(var13 ^ var10);

      for(var3 = 0; var16.charAt(var16.length() - 8 - var3) == 48; ++var3) {
         ;
      }

      int var17;
      var_112 = var17 = Integer.parseInt(var16.substring(var16.length() - 8 - var3, var16.length() - var3));
      var_25e = var17;
      var3 = var15 % 8;
      byte[] var18 = new byte[var15 + 8 - var3 + 8];
      System.arraycopy(String.valueOf(var17).getBytes(), 0, var18, 0, 8);

      for(var17 = 0; var17 <= var15 / 8; ++var17) {
         byte[] var4 = new byte[8];
         int var5 = 8;
         if(var17 == var15 / 8) {
            var5 = var2.length % 8;
         }

         System.arraycopy(var2, var17 << 3, var4, 0, var5);
         System.arraycopy(sub_fe(var4, true), 0, var18, (var17 << 3) + 8, 8);
      }

      return var18;
   }

   public static byte[] sub_79(byte[] var0, byte[] var1) throws Class_e89, Class_1476 {
      sub_a3(var0);
      sub_133(var0);

      int var6;
      try {
         var6 = Integer.parseInt((new String(var1)).substring(0, 8));
      } catch (StringIndexOutOfBoundsException var4) {
         throw new Class_e89("Данные, которые будут расшифрованы недействительны!");
      } catch (NumberFormatException var5) {
         throw new Class_e89("Данные, которые будут расшифрованы недействительны!");
      }

      var_112 = var6;
      var_25e = var6;
      var0 = new byte[var1.length - 8];

      int var2;
      byte[] var3;
      for(var2 = 1; var2 < var1.length / 8; ++var2) {
         var3 = new byte[8];
         System.arraycopy(var1, var2 << 3, var3, 0, 8);
         System.arraycopy(sub_fe(var3, false), 0, var0, var2 - 1 << 3, 8);
      }

      var2 = -1;

      for(int var7 = var0.length - 1; var7 > 0; --var7) {
         if(var7 >= var_95.length && var0[var7] == var_95[3] && var0[var7 - 1] == var_95[2] && var0[var7 - 2] == var_95[1] && var0[var7 - 3] == var_95[0]) {
            var2 = var7 - var_95.length + 1;
            break;
         }
      }

      if(var2 == -1) {
         throw new Class_e89("Entschlüsselung war nicht erfolgreich!\nWahrscheinlich wurde ein falscher Schlüssel angegeben.");
      } else {
         var3 = new byte[var2];
         System.arraycopy(var0, 0, var3, 0, var2);
         return var3;
      }
   }

   private static void sub_a3(byte[] var0) throws Class_1476 {
      if(var0 == null) {
         throw new Class_1476("Es wurde kein Schlüssel angegeben!");
      } else if(var0.length != 16) {
         throw new Class_1476("Es wurde ein ungültiger Schlüssel angegeben.\nGültige Schlüssel haben eine Länge von 16 Bytes.");
      }
   }

   private static byte[] sub_fe(byte[] var0, boolean var1) {
      int var2 = var0[0] & 255 | (var0[1] & 255) << 8 | (var0[2] & 255) << 16 | var0[3] << 24;
      int var3 = var0[4] & 255 | (var0[5] & 255) << 8 | (var0[6] & 255) << 16 | var0[7] << 24;
      int[] var6;
      var2 = (var6 = new int[]{var2, var3})[0];
      int var7 = var6[1];
      var3 = 32;
      int var8;
      if(var1) {
         var2 ^= var_112;
         var7 ^= var_25e;

         for(var8 = 0; var3-- > 0; var7 += (var2 << 4 ^ var2 >>> 5) + var2 ^ var8 + var_ca[var8 >> 11 & 3]) {
            var2 += (var7 << 4 ^ var7 >>> 5) + var7 ^ var8 + var_ca[var8 & 3];
            var8 -= 1640531527;
         }

         var_112 = var2;
         var_25e = var7;
      } else {
         var8 = -957401312;
         int var4 = var2;

         int var5;
         for(var5 = var7; var3-- > 0; var2 -= (var7 << 4 ^ var7 >>> 5) + var7 ^ var8 + var_ca[var8 & 3]) {
            var7 -= (var2 << 4 ^ var2 >>> 5) + var2 ^ var8 + var_ca[var8 >> 11 & 3];
            var8 += 1640531527;
         }

         var2 ^= var_112;
         var7 ^= var_25e;
         var_112 = var4;
         var_25e = var5;
      }

      byte[] var9;
      (var9 = new byte[8])[0] = (byte)var2;
      var9[1] = (byte)(var2 >>> 8);
      var9[2] = (byte)(var2 >>> 16);
      var9[3] = (byte)(var2 >> 24);
      var9[4] = (byte)var7;
      var9[5] = (byte)(var7 >>> 8);
      var9[6] = (byte)(var7 >>> 16);
      var9[7] = (byte)(var7 >> 24);
      return var9;
   }

   private static void sub_133(byte[] var0) {
      int var1 = 0;

      for(int var2 = 0; var2 < 4; ++var2) {
         var_ca[var2] = var0[var1++] & 255 | (var0[var1++] & 255) << 8 | (var0[var1++] & 255) << 16 | (var0[var1++] & 255) << 24;
      }

   }

}
