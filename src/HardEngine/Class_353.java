package HardEngine;


public abstract class Class_353 {

   private Class_9a7 var_36;
   private Class_2b0 var_6b;


   protected Class_353(Class_9a7 var1) {
      this.var_36 = var1;
   }

   protected final void sub_52(Class_2b0 var1) {
      this.var_6b = var1;
   }

   public final void sub_9d(int var1, String var2, String var3, int var4) {
      Object var7 = null;

      try {
         this.var_6b.sub_25(var1);
         Class_2b0 var10000 = this.var_6b;
         byte[] var6 = var2.getBytes();
         var10000.var_2a5 = var6;
         byte[] var8 = ("" + var3).getBytes("UTF-8");
         this.var_6b.sub_186(181, var8, this.var_6b.var_2a5, 0);
      } catch (Exception var5) {
         var5.printStackTrace();
         this.var_36.sub_73(900);
         this.var_6b.sub_24f();
      }
   }

   public final void sub_b5(String var1, String var2, int var3) {
      Object var5 = null;

      try {
         char[] var6 = new char[]{'&', '%'};
         if(Class_767.sub_178(var1, var6) || Class_767.sub_178(var2, var6)) {
            this.var_36.sub_7d(24, 0, -1, (byte[])null);
            this.var_6b.sub_24f();
            return;
         }

         byte[] var7 = ("" + var1 + '&' + var2).getBytes("UTF-8");
         this.var_6b.sub_186(106, var7, this.var_6b.var_2a5, 0);
      } catch (Exception var4) {
         this.var_36.sub_7d(900, 0, -1, (byte[])null);
         this.var_6b.sub_24f();
      }

   }

   public final void sub_106(String var1, String var2, String var3, int var4) {
      Object var6 = null;

      try {
         if(var3 == null || var3.length() == 0) {
            var3 = "-";
         }

         char[] var7 = new char[]{'&', '%'};
         if(Class_767.sub_178(var1, var7) || Class_767.sub_178(var2, var7)) {
            this.var_36.sub_b2(24, -1, (byte[])null);
            this.var_6b.sub_24f();
            return;
         }

         byte[] var8 = ("" + var1 + '&' + var2 + '&' + var3).getBytes("UTF-8");
         this.var_6b.sub_186(100, var8, this.var_6b.var_2a5, 0);
      } catch (Exception var5) {
         this.var_36.sub_b2(900, -1, (byte[])null);
         this.var_6b.sub_24f();
      }

   }

   public final void sub_156(String var1, String var2, int var3) {
      Object var5 = null;

      try {
         char[] var6 = new char[]{'&', '%'};
         if(Class_767.sub_178(var1, var6) || Class_767.sub_178(var2, var6)) {
            this.var_36.sub_e8(24);
            this.var_6b.sub_24f();
            return;
         }

         byte[] var7 = ("" + var1 + '&' + var2).getBytes("UTF-8");
         this.var_6b.sub_186(102, var7, this.var_6b.var_2a5, 0);
      } catch (Exception var4) {
         this.var_36.sub_e8(900);
         this.var_6b.sub_24f();
      }

   }

   public final void sub_170(int var1) {
      this.var_6b.sub_11e(107, 0);
   }

   public final void sub_17b(int var1, byte[] var2, int var3) {
      byte[] var4;
      byte[] var10000;
      if((var4 = ("" + 0 + '&').getBytes()) == null) {
         var10000 = var2;
      } else if(var2 == null) {
         var10000 = var4;
      } else {
         byte[] var5 = new byte[var4.length + var2.length];
         System.arraycopy(var4, 0, var5, 0, var4.length);
         System.arraycopy(var2, 0, var5, var4.length, var2.length);
         var10000 = var5;
      }

      var4 = var10000;
      this.var_6b.sub_186(122, var4, this.var_6b.var_2a5, 0);
   }

   public final void sub_18f(int var1, int var2) {
      this.var_6b.sub_186(123, ("" + 0).getBytes(), this.var_6b.var_2a5, 0);
   }

   public final void sub_1dc(int var1) {
      this.var_6b.sub_11e(140, 0);
   }

   public final void sub_20f(int var1, int var2) {
      this.var_6b.sub_186(143, ("" + 14).getBytes(), this.var_6b.var_2a5, 0);
   }

   public final void sub_252(int var1, int var2) {
      this.var_6b.sub_186(145, ("" + var1).getBytes(), this.var_6b.var_2a5, 0);
   }

   public final void sub_26a(int var1, byte var2, int var3) {
      this.var_6b.sub_186(147, ("" + 14 + '&' + var2).getBytes(), this.var_6b.var_2a5, 0);
   }

   public final void sub_2c1(int var1, byte var2, int var3) {
      this.var_6b.sub_13a(124, ("" + var1 + '&' + var2).getBytes(), 0);
   }
}
