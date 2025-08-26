package HardEngine;


public final class Class_11db extends Class_1df0 {

   private Class_a99 var_18c;


   public Class_11db(Class_b13 var1, Class_a99 var2) {
      super(var1);
      this.var_18c = var2;
   }

   public final void sub_18(int var1, int var2, byte[] var3, int var4) {
      int[] var5;
      byte[][] var7;
      byte[][] var13;
      String[] var14;
      switch(var1) {
      case 310:
         this.var_18c.sub_28(var2);
         return;
      case 311:
         var3 = var3;
         var2 = var2;
         int[] var15 = null;
         if(var2 == 0) {
            try {
               var15 = Class_767.sub_f9(Class_767.sub_22(var3, '&'));
            } catch (Exception var11) {
               var2 = 900;
            }
         }

         this.var_18c.sub_45(var2, var15);
         return;
      case 312:
         var3 = var3;
         var2 = var2;
         var4 = -1;
         if(var2 == 0) {
            try {
               var4 = Integer.parseInt(new String(var3));
            } catch (Exception var10) {
               var2 = 900;
            }
         }

         this.var_18c.sub_7e(var2, var4);
         return;
      case 313:
         var3 = var3;
         var2 = var2;
         var14 = null;
         var5 = null;
         if(var2 == 0) {
            try {
               byte[][] var16;
               var13 = Class_767.sub_22((var16 = Class_767.sub_22(var3, '&'))[0], '%');
               var7 = Class_767.sub_22(var16[1], '%');
               var14 = Class_767.sub_e7(var13, "UTF-8");
               var5 = Class_767.sub_f9(var7);
            } catch (Exception var9) {
               var2 = 900;
            }
         }

         this.var_18c.sub_a2(var2, var14, var5);
         return;
      case 314:
         var3 = var3;
         var2 = var2;
         var14 = null;
         var5 = null;
         int[] var6 = null;
         if(var2 == 0) {
            try {
               var7 = Class_767.sub_22((var13 = Class_767.sub_22(var3, '&'))[0], '%');
               byte[][] var8 = Class_767.sub_22(var13[1], '%');
               var13 = Class_767.sub_22(var13[2], '%');
               var14 = Class_767.sub_e7(var7, "UTF-8");
               var5 = Class_767.sub_f9(var8);
               var6 = Class_767.sub_f9(var13);
            } catch (Exception var12) {
               var12.printStackTrace();
               var2 = 900;
            }
         }

         this.var_18c.sub_d1(var2, var14, var6, var5);
      default:
      }
   }
}
