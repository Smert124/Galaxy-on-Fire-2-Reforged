package HardEngine;

import java.util.Random;

import GoF2.RecordHandler;

public final class Class_28f {

   private String var_85;
   private int var_e9 = 0;


   public Class_28f() {
      String var1 = new String(this.sub_a8(5, (byte)48, (byte)57));
      boolean var2 = false;

      int var4;
      try {
         var4 = Integer.parseInt(var1);
      } catch (NumberFormatException var3) {
         var4 = (new Random()).nextInt(99999);
      }

      (new RecordHandler()).sub_406(var4, (byte[])null, (String)null, 2);
      this.var_85 = "GET-ACTIVATION-KEY: " + var4;
   }

   public final int sub_53() {
      return this.var_e9;
   }

   public final void sub_8d(String var1) {
      String var2 = this.var_85;
      //(new Class_18e7(this, var1, var2)).start();
   }

   private byte[] sub_a8(int var1, byte var2, byte var3) {
      byte[] var7 = new byte[5];

      for(int var8 = 0; var8 < var7.length; ++var8) {
         byte var4 = 57;
         byte var9 = 48;
         var7[var8] = (byte)(Math.abs((new Random()).nextInt() % (var4 - var9 + 1)) + var9);

        /** try {
            Thread.sleep(0);
         } catch (Exception var6) {
            ;
         } **/
      }

      return var7;
   }

   static int sub_dd(Class_28f var0, int var1) {
      return var0.var_e9 = var1;
   }
}
