package HardEngine;

import java.io.IOException;
import javax.microedition.io.ConnectionNotFoundException;

public final class Class_b0 {

   private int var_ac = 1;
   private int var_11f = 1;
   private int var_134 = 1;
   private int var_151 = 1;
   private int var_17c = 1;
   private Class_3d4 var_18f;
   private int var_1c1 = 0;
   private int var_210 = 0;
   private byte[] var_224 = "0".getBytes();
   private boolean var_23a = false;
   private boolean var_270 = false;
   private boolean var_2af = true;


   public Class_b0(String var1, int var2, int var3, Class_f09 var4) {
      this.var_210 = var2;
      this.var_1c1 = var3;
      this.var_18f = new Class_3d4(var1, var4);
   }

   public final void sub_5f(int var1) {
      this.var_1c1 = var1;
   }

   public final void sub_70(byte[] var1) {
      this.var_224 = var1;
   }

   public final byte[][] sub_b0(int var1, byte[] var2, byte[] var3) throws ConnectionNotFoundException, Class_674, Class_808, IOException, Class_e89, Class_1476 {
      this.var_2af = false;
      return this.sub_10d(var1, var2, var3);
   }

   public final byte[][] sub_10d(int var1, byte[] var2, byte[] var3) throws ConnectionNotFoundException, Class_674, Class_808, IOException, Class_e89, Class_1476 {
      Object var4 = null;
      Object var5 = null;
      Object var6 = null;
      Object var7 = null;
      Object var8 = null;
      var4 = null;
      byte[][] var9 = null;
      byte[] var10 = null;
      byte[] var11 = null;

      byte[][] var31;
      try {
         this.var_270 = true;
         byte[] var25 = String.valueOf(var2.length).getBytes();
         byte[] var26 = String.valueOf(var1).getBytes();
         byte[] var29 = String.valueOf(this.var_210).getBytes();
         byte[] var30 = String.valueOf(this.var_1c1).getBytes();
         var1 = 0;
         byte[] var33;
         if(this.var_2af && var2.length > 0) {
                try {
                    var2 = Class_ae6.sub_37(var3, var2);
                } catch (Class_1476 ex) {
                    ex.printStackTrace();
                }
            (var33 = new byte[var25.length + var26.length + var29.length + var30.length + var2.length + this.var_224.length + 6])[0] = 48;
            ++var1;
         } else {
            var33 = new byte[var25.length + var26.length + var29.length + var30.length + var2.length + this.var_224.length + 5];
         }

         System.arraycopy(var25, 0, var33, var1, var25.length);
         var1 += var25.length;
         var33[var1++] = 38;
         System.arraycopy(var26, 0, var33, var1, var26.length);
         var1 += var26.length;
         var33[var1++] = 38;
         System.arraycopy(var29, 0, var33, var1, var29.length);
         var1 += var29.length;
         var33[var1++] = 38;
         System.arraycopy(var30, 0, var33, var1, var30.length);
         var1 += var30.length;
         var33[var1++] = 38;
         System.arraycopy(this.var_224, 0, var33, var1, this.var_224.length);
         var1 += this.var_224.length;
         var33[var1++] = 38;
         System.arraycopy(var2, 0, var33, var1, var2.length);
         this.var_ac = 1;
         this.var_11f = 1;
         this.var_134 = 1;
         this.var_151 = 1;
         var25 = new byte[0];

         while(true) {
            int var22;
            int var27;
            if(!this.var_23a) {
               try {
                  var25 = this.var_18f.sub_79(var33, this.var_ac == 1 && this.var_11f == 1 && this.var_134 == 1 && this.var_151 == 1);
               } catch (ConnectionNotFoundException var17) {
                  var17.printStackTrace();
                  this.var_18f.sub_bd();
                  if(this.var_ac >= 3) {
                     throw new ConnectionNotFoundException(var17.toString());
                  }

                  ++this.var_ac;
                  Class_767.sub_19a(1500);
                  continue;
               } catch (IOException var18) {
                  var18.printStackTrace();
                  this.var_18f.sub_bd();
                  if(this.var_11f >= 3) {
                     throw new Class_674(var18.toString());
                  }

                  ++this.var_11f;
                  Class_767.sub_19a(1500);
                  continue;
               } catch (Class_808 var19) {
                  var19.printStackTrace();
                  this.var_18f.sub_bd();
                  if(this.var_134 >= 3) {
                     throw var19;
                  }

                  ++this.var_134;
                  Class_767.sub_19a(1500);
                  continue;
               }

               var9 = new byte[3][];
               if(this.var_23a) {
                  var9[0] = "900".getBytes();
                  var9[1] = new byte[0];
                  byte[][] var24 = var9;
                  return var24;
               }

               var22 = 0;
               var1 = 0;

               for(var27 = 0; var22 < 2 && var27 < var25.length; ++var27) {
                  if(var25[var27] == 38) {
                     var9[var22] = new byte[var27 - var1];
                     System.arraycopy(var25, var1, var9[var22], 0, var27 - var1);
                     ++var22;
                     ++var27;
                     var1 = var27;
                  }
               }

               if(var22 != 2) {
                  throw new IOException("null");
               }

               var10 = var9[1];
               if(Integer.parseInt(new String(var10)) == 903 || Integer.parseInt(new String(var10)) == 906) {
                  if(Integer.parseInt(new String(var10)) == 906) {
                     this.var_18f.sub_12e();
                     this.var_18f.sub_bd();
                     if(this.var_17c < 3) {
                        ++this.var_17c;
                        Class_767.sub_19a(1500);
                        continue;
                     }
                  } else {
                     this.var_18f.sub_bd();
                     if(this.var_151 < 3) {
                        ++this.var_151;
                        Class_767.sub_19a(1500);
                        continue;
                     }
                  }
               }
            }

            boolean var23 = false;
            if(var25[0] == 38) {
               break;
            }

            var22 = Integer.valueOf(new String(var9[0])).intValue();
            var11 = new byte[var27 = var25.length - var1];
            System.arraycopy(var25, var1, var11, 0, var27);
            Object var28 = null;
            if(var25[0] == 48) {
               byte[] var32;
               if((var32 = Class_ae6.sub_79(var3, var11)).length != var22) {
                  var11 = new byte[var22];
                  System.arraycopy(var32, 0, var11, 0, var22);
               } else {
                  var11 = var32;
               }
            }

            if(var11.length != var22) {
               throw new Class_674("������ ��� ��������!\n��������� ������ �������� ���������.");
            }
            break;
         }

         var31 = new byte[][]{var10, var11};
      } catch (NumberFormatException var20) {
         throw new IOException("null");
      } finally {
         this.var_2af = true;
         this.var_23a = false;
         this.var_270 = false;
      }

      return var31;
   }

   public final void sub_121() {
      this.var_18f.sub_117();
      if(this.var_270) {
         this.var_23a = true;
      }

   }

   public final void sub_150() {
      if(this.var_18f != null) {
         this.var_18f.sub_bd();
      }

   }
}
