package HardEngine;


public final class Class_86b {

   private Class_9a7 var_10;
   private static int var_13a = -1;
   private Class_2b0 var_153;
   private int var_191 = 0;
   private byte[] var_253 = null;
   private byte[] var_276 = null;
   private String var_28e = "";
   private Class_1df0 var_2e0;


   public Class_86b(Class_2b0 var1, Class_9a7 var2) {
      this.var_10 = var2;
      this.var_153 = var1;
   }

   public Class_86b(Class_2b0 var1, Class_9a7 var2, Class_1df0 var3) {
      this.var_10 = var2;
      this.var_153 = var1;
      this.var_2e0 = var3;
   }

   public final void sub_4c(Class_1df0 var1) {
      this.var_2e0 = var1;
   }

   public final void sub_61(int var1, int var2, byte[] var3, int var4) {
      byte[][] var32;
      int var5;
      byte[] var6;
      byte[][] var7;
      byte[][] var8;
      int var9;
      int var10;
      Class_2b0 var10001;
      byte[][] var25;
      Class_86b var24;
      byte[] var27;
      byte[][] var26;
      int var30;
      switch(var1) {
      case 100:
      case 106:
         var5 = var4;
         var27 = var3;
         var30 = var2;
         var2 = var1;
         var24 = this;
         if(var30 == 0) {
            try {
               var32 = Class_767.sub_72(var27, '&', 4);
               var24.var_276 = var32[1];
               var24.var_191 = Integer.parseInt(new String(var32[2]));
               var24.var_253 = var32[3];
               if(var2 != 106) {
                  var24.var_153.sub_d8(800, var32[0], var5);
                  break;
               }

               var24.var_153.sub_d8(802, var32[0], var5);
            } catch (Exception var22) {
               if(var2 != 106) {
                  this.var_10.sub_b2(900, -1, (byte[])null);
                  break;
               }

               this.var_10.sub_7d(900, var5, -1, (byte[])null);
            }
         } else {
            if(var2 == 106) {
               this.var_10.sub_7d(var30, var5, -1, (byte[])null);
               break;
            }

            this.var_10.sub_b2(var30, -1, (byte[])null);
         }

         return;
      case 101:
         var4 = var4;
         var3 = var3;
         var24 = this;
         if(var2 == 0) {
            try {
               byte[][] var35 = Class_767.sub_72(var3, '&', 5);
               var24.var_28e = new String(var35[1]);
               var24.var_191 = Integer.parseInt(new String(var35[2]));
               var24.var_276 = var35[3];
               var24.var_253 = var35[4];
               var24.var_153.sub_d8(801, var35[0], var4);
               break;
            } catch (Exception var23) {
               ;
            }
         }

         return;
      case 102:
         if(var2 == 0) {
            this.var_153.sub_47(var3);
         }

         this.var_10.sub_e8(var2);
         return;
      case 103:
         this.var_153.sub_47("0".getBytes());
         return;
      case 104:
         return;
      case 105:
         return;
      case 107:
         var3 = var3;
         var2 = var2;
         var5 = -1;
         if(var2 == 0) {
            try {
               var5 = Integer.parseInt(new String(var3));
            } catch (Exception var17) {
               var2 = 900;
            }
         }

         this.var_10.sub_41(var2, var5);
         return;
      case 120:
         return;
      case 121:
      case 123:
         var27 = var3;
         var30 = var2;
         int var34 = -1;
         byte[] var33 = null;

         try {
            if(var30 == 0) {
               var25 = Class_767.sub_72(var27, '&', 2);
               var34 = Integer.parseInt(new String(var25[0]));
               var33 = var25[1];
            }
         } catch (Exception var16) {
            var30 = 900;
         }

         if(var1 == 123) {
            this.var_10.sub_2c2(var30, var34, var33);
         }

         return;
      case 122:
         this.var_10.sub_25e(var2);
         return;
      case 124:
         var3 = var3;
         var2 = var2;
         var5 = -1;
         String var31 = null;
         if(var2 == 0) {
            try {
               var7 = Class_767.sub_72(var3, '&', 2);
               var5 = Integer.parseInt(new String(var7[0]));
               var31 = new String(var7[1], "UTF-8");
            } catch (Exception var15) {
               var2 = 900;
            }
         }

         this.var_10.sub_223(var2, var5, var31);
         return;
      case 125:
         this.sub_c3(var2, var3, var4);
         return;
      case 140:
         var3 = var3;
         var2 = var2;
         var5 = -1;
         if(var2 == 0) {
            try {
               var32 = Class_767.sub_22(var3, '&');
               var5 = Integer.parseInt(new String(var32[0]));
            } catch (Exception var14) {
               var2 = 900;
            }
         }

         this.var_10.sub_113(var2, var5);
         return;
      case 141:
      case 142:
         this.sub_91(var1, var2, var3, var4);
         return;
      case 143:
         var3 = var3;
         var2 = var2;
         var5 = -1;
         if(var2 == 0) {
            try {
               var7 = Class_767.sub_22(var3, '&');
               var5 = Integer.parseInt(new String(var7[0]));
               Class_767.sub_f9(Class_767.sub_22(var7[1], '%'));
            } catch (Exception var13) {
               var2 = 900;
            }
         }

         this.var_10.sub_1d7(var2, var5);
         return;
      case 144:
         var3 = var3;
         if(var2 == 0) {
            try {
               var25 = Class_767.sub_22(var3, '&');
               Integer.parseInt(new String(var25[0]));
               Class_767.sub_f9(var26 = Class_767.sub_22(var25[1], '%'));
               Class_767.sub_128(var25[2], var26.length);
               break;
            } catch (Exception var21) {
               ;
            }
         }

         return;
      case 145:
         var3 = var3;
         var2 = var2;
         var5 = -1;
         int[] var28 = null;
         var25 = null;
         if(var2 == 0) {
            try {
               var26 = Class_767.sub_72(var3, '&', 5);
               var5 = Integer.parseInt(new String(var26[0]));
               var28 = Class_767.sub_f9(Class_767.sub_22(var26[1], '%'));
               Class_767.sub_f9(Class_767.sub_22(var26[2], '%'));
               var25 = Class_767.sub_7f(Class_767.sub_f9(Class_767.sub_22(var26[3], '%')), var26[4]);
            } catch (Exception var12) {
               var2 = 900;
            }
         }

         this.var_10.sub_1a5(var2, var5, var28, var25);
         return;
      case 146:
         var3 = var3;
         if(var2 == 0) {
            try {
               var25 = Class_767.sub_72(var3, '&', 3);
               Integer.parseInt(new String(var25[0]));
               Integer.parseInt(new String(var25[1]));
               break;
            } catch (Exception var20) {
               ;
            }
         }

         return;
      case 147:
         var3 = var3;
         var2 = var2;
         int var29 = -1;
         if(var2 == 0) {
            try {
               var8 = Class_767.sub_72(var3, '&', 6);
               Integer.parseInt(new String(var8[0]));
               Integer.parseInt(new String(var8[1]));
               var29 = Integer.parseInt(new String(var8[2]));
               var9 = Integer.parseInt(new String(var8[3]));
               var10 = Integer.parseInt(new String(var8[4]));
               var8 = Class_767.sub_7f(new int[]{var9, var10}, var8[5]);
               new String(var8[0], "UTF-8");
               new String(var8[1], "UTF-8");
            } catch (Exception var11) {
               var2 = 900;
            }
         }

         this.var_10.sub_153(var2, var29);
         return;
      case 148:
         var3 = var3;
         if(var2 == 0) {
            try {
               var8 = Class_767.sub_72(var3, '&', 4);
               Integer.parseInt(new String(var8[0]));
               Integer.parseInt(new String(var8[1]));
               var9 = Integer.parseInt(new String(var8[2]));
               var10 = Integer.parseInt(new String(var8[3]));
               var8 = Class_767.sub_7f(new int[]{var9, var10}, var8[4]);
               new String(var8[0], "UTF-8");
               new String(var8[1], "UTF-8");
               break;
            } catch (Exception var19) {
               ;
            }
         }

         return;
      case 180:
         var3 = var3;
         var2 = var2;

         try {
            if(var2 == 0) {
               var7 = Class_767.sub_72(var3, '&', 2);
               Integer.parseInt(new String(var7[0]));
               new String(var7[1]);
            }
            break;
         } catch (Exception var18) {
            return;
         }
      case 181:
         this.var_10.sub_73(var2);
         return;
      case 800:
      case 802:
         if(var2 == 0) {
            this.var_153.sub_25(this.var_191);
            var6 = this.var_253;
            var10001 = this.var_153;
            this.var_153.var_2a5 = var6;
            this.var_153.sub_47(this.var_276);
         }

         if(var1 != 802) {
            this.var_10.sub_b2(var2, this.var_191, this.var_253);
            return;
         }

         this.var_10.sub_7d(var2, var4, this.var_191, this.var_253);
         break;
      case 801:
         if(var2 == 0) {
            this.var_153.sub_25(this.var_191);
            var6 = this.var_253;
            var10001 = this.var_153;
            this.var_153.var_2a5 = var6;
            this.var_153.sub_47(this.var_276);
         }

         return;
      default:
         if(this.var_2e0 != null) {
            this.var_2e0.sub_18(var1, var2, var3, var4);
         }
      }

   }

   private void sub_91(int var1, int var2, byte[] var3, int var4) {
      Object var7 = null;
      if(var2 == 0) {
         try {
            byte[][] var10;
            Class_767.sub_f9(Class_767.sub_22((var10 = Class_767.sub_72(var3, '&', 8))[0], '%'));
            Class_767.sub_f9(Class_767.sub_22(var10[1], '%'));
            byte[][] var12;
            int[][] var8 = new int[(var12 = Class_767.sub_22(var10[2], '%')).length][];

            for(var4 = 0; var4 < var12.length; ++var4) {
               byte[][] var5 = Class_767.sub_22(var12[var4], ',');
               var8[var4] = Class_767.sub_f9(var5);
            }

            Class_767.sub_f9(Class_767.sub_22(var10[3], '%'));
            int[] var9 = Class_767.sub_f9(Class_767.sub_22(var10[4], '%'));
            int[] var13 = Class_767.sub_f9(Class_767.sub_22(var10[5], '%'));
            int[] var14 = Class_767.sub_f9(Class_767.sub_22(var10[6], '%'));
            byte[][] var11 = Class_767.sub_7f(var9, var10[7]);
            var10 = Class_767.sub_7f(var13, var11[0]);
            var11 = Class_767.sub_7f(var14, var11[1]);
            Class_767.sub_e7(var10, "UTF-8");
            Class_767.sub_e7(var11, "UTF-8");
            return;
         } catch (Exception var6) {
            ;
         }
      }

   }

   private void sub_c3(int var1, byte[] var2, int var3) {
      String[] var7 = null;
      if(var1 == 0) {
         try {
            byte[][] var5;
            Class_767.sub_f9(Class_767.sub_22((var5 = Class_767.sub_72(var2, '&', 3))[0], '%'));
            var7 = new String[(var5 = Class_767.sub_7f(Class_767.sub_f9(Class_767.sub_22(var5[1], '%')), var5[2])).length];

            for(int var6 = 0; var6 < var5.length; ++var6) {
               var7[var6] = new String(var5[var6], "UTF-8");
            }

            return;
         } catch (Exception var4) {
            ;
         }
      }

   }

   public final synchronized void sub_121(boolean var1, int var2, int var3) {
      boolean var4 = false;
      var4 = false;
      int var5 = 0;
      int var6;
      if(var1) {
         if((var6 = Class_767.sub_1c0(var2, var3)) != 0) {
            var5 = var6 / 2;
         }
      } else {
         if((var6 = Class_767.sub_1c0(var2, var3)) != 0) {
            var5 = var6 / 2;
         }

         var5 += 50;
      }

      this.var_10.sub_62((byte)var5);
   }

   public final void sub_167(int var1) {
      var_13a = var1;
      this.var_10.sub_62((byte)0);
   }

   public final void sub_192() {
      this.var_10.sub_62((byte)100);
      var_13a = -1;
   }

}
