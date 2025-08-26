package HardEngine;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Vector;

public final class Class_767 {

   public static byte[][] sub_22(byte[] var0, char var1) {
      if(var0 == null) {
         return null;
      } else {
         int var2 = 0;
         Vector var3 = new Vector();

         byte[] var5;
         for(int var4 = 0; var4 < var0.length; ++var4) {
            if(var0[var4] == var1) {
               var5 = new byte[var4 - var2];
               System.arraycopy(var0, var2, var5, 0, var4 - var2);
               var2 = var4 + 1;
               var3.addElement(var5);
            }
         }

         var5 = new byte[var0.length - var2];
         System.arraycopy(var0, var2, var5, 0, var0.length - var2);
         var3.addElement(var5);
         Enumeration var6 = var3.elements();
         byte[][] var7 = new byte[var3.size()][];

         for(var2 = 0; var6.hasMoreElements(); var7[var2++] = (byte[])((byte[])var6.nextElement())) {
            ;
         }

         return var7;
      }
   }

   public static byte[][] sub_72(byte[] var0, char var1, int var2) {
      if(var0 == null) {
         return null;
      } else {
         byte[][] var7 = new byte[var2--][];
         Object var3 = null;
         int var4 = 0;
         int var5 = 0;

         int var6;
         byte[] var8;
         for(var6 = 0; var6 < var2 && var5 < var0.length; ++var5) {
            if(var0[var5] == 38) {
               var8 = new byte[var5 - var4];
               System.arraycopy(var0, var4, var8, 0, var5 - var4);
               var4 = var5 + 1;
               var7[var6++] = var8;
            }
         }

         var8 = new byte[var0.length - var4];
         System.arraycopy(var0, var4, var8, 0, var0.length - var4);
         var7[var6] = var8;
         return var7;
      }
   }

   public static byte[][] sub_7f(int[] var0, byte[] var1) {
      if(var1 == null) {
         return null;
      } else {
         int var2;
         byte[][] var6 = new byte[var2 = var0.length][];
         Object var3 = null;
         int var4 = 0;

         for(int var5 = 0; var5 < var0.length; ++var5) {
            byte[] var7 = new byte[var0[var5]];
            System.arraycopy(var1, var4, var7, 0, var0[var5]);
            var4 += var0[var5];
            var6[var5] = var7;
         }

         return var6;
      }
   }

   public static String[] sub_b9(int var0, String var1) {
      if(var1 == null) {
         return null;
      } else {
         var0 = var1.length() / 1600;
         ++var0;
         String[] var2 = null;
         var2 = new String[var0 + 1];
         String var6 = null;
         int var3 = 0;
         int var4 = 1600;
         int var5 = 0;

         while(var4 < var1.length()) {
            if((var6 = var1.substring(var3, var4)).lastIndexOf(37) == 1599) {
               var4 += 2;
               var6 = var1.substring(var3, var4);
            } else if(var6.lastIndexOf(37) == 1598) {
               ++var4;
               var6 = var1.substring(var3, var4);
            }

            var4 = (var3 += var6.length()) + 1600;
            var2[var5++] = var6;
            if(var4 >= var1.length()) {
               var4 = var1.length();
               var6 = var1.substring(var3, var4);
               var2[var5] = var6;
            }
         }

         boolean var7 = false;
         String[] var8 = var2;
         var2 = new String[0];
         if(var5 < var8.length && 0 < var5) {
            var2 = new String[var5 + 1];
            System.arraycopy(var8, 0, var2, 0, var5 + 1);
         }

         return var2;
      }
   }

   public static String[] sub_e7(byte[][] var0, String var1) {
      int var10002 = var0.length - 1;
      String var3 = var1;
      int var2 = var10002;
      int var6 = 0;
      var0 = var0;
      String[] var4 = new String[var2 + 1];

      for(int var5 = 0; var6 <= var2; ++var5) {
            try {
                var4[var5] = new String(var0[var6], var3);
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
         ++var6;
      }

      return var4;
   }

   public static int[] sub_f9(byte[][] var0) {
      int var2 = var0.length - 1;
      int var1 = 0;
      var0 = var0;
      int[] var3 = new int[var2 + 1];

      for(int var4 = 0; var1 <= var2; ++var4) {
         var3[var4] = Integer.parseInt(new String(var0[var1]));
         ++var1;
      }

      return var3;
   }

   public static boolean[] sub_128(byte[] var0, int var1) {
      boolean[] var2 = null;

      boolean[] var9;
      for(int var3 = 0; var1 > 0; var2 = var9) {
         int var4 = var1 > 8?8:var1;
         byte var10000 = var0[var3++];
         int var5 = var4;
         byte var10 = var10000;
         boolean[] var6 = new boolean[var4];
         if(var4 != 0) {
            int var7 = 0;
            if(var10 < 0 && var4 == 8) {
               var6[7] = true;
            }

            for(char var8 = 1; var7 < var5; var8 += var8) {
               var6[var7++] = (var10 & var8) == var8;
            }
         }

         var1 -= 8;
         boolean[] var11 = var6;
         if(var2 == null) {
            var9 = var6;
         } else if(var6 == null) {
            var9 = var2;
         } else {
            var6 = new boolean[var2.length + var6.length];
            System.arraycopy(var2, 0, var6, 0, var2.length);
            System.arraycopy(var11, 0, var6, var2.length, var11.length);
            var9 = var6;
         }
      }

      return var2;
   }

   public static boolean sub_178(String var0, char[] var1) {
      for(int var2 = 0; var2 < var1.length; ++var2) {
         if(var0.lastIndexOf(var1[var2]) != -1) {
            return true;
         }
      }

      return false;
   }

   public static String sub_18c(int[] var0) {
      if(var0 != null && var0.length != 0) {
         String var1 = "";

         for(int var2 = 0; var2 < var0.length - 1; ++var2) {
            var1 = var1 + "" + var0[var2] + '%';
         }

         return var1 + var0[var0.length - 1];
      } else {
         return "";
      }
   }

   public static void sub_19a(int var0) {
      try {
         Thread.sleep(var0);
      } catch (Exception var1) {
         ;
      }
   }

   public static int sub_1c0(int var0, int var1) {
      long var2 = 0L;
      if(var0 != 0 && var1 != 0) {
         var2 = (var2 = (long)(100000000 / var1)) * (long)var0 / 1000000L;
      }

      return (int)var2;
   }

   public static String sub_1e3(String var0) {
      StringBuffer var1 = new StringBuffer();
      int var2 = 0;

      try {
         while(true) {
            char var3;
            for(; ((var3 = var0.charAt(var2++)) < 48 || var3 > 57) && (var3 < 65 || var3 > 90) && (var3 < 97 || var3 > 122); var1.append(Integer.toHexString(var3))) {
               var1.append("%");
               if(var3 <= 15) {
                  var1.append("0");
               }
            }

            var1.append((char)var3);
         }
      } catch (Exception var4) {
         return var1.toString();
      }
   }
}
