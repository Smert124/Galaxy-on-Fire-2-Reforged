package HardEngine;

import java.io.InputStream;
import java.util.Vector;

public final class Class_2b0 extends Class_353 {

   private Class_86b var_b1;
   private Class_b0 var_107;
   private Class_166d var_171;
   private Vector var_1b7;
   private boolean var_211;
   private int var_27c;
   public byte[] var_2a5;


   public Class_2b0(int var1, String var2, int var3, byte[] var4, Class_9a7 var5, Class_1df0 var6) {
      super(var5);
      this.var_211 = false;
      this.var_27c = 0;
      this.var_2a5 = "spaces.ru/soo/Hard_Condition/".getBytes();
      "spaces.ru/soo/Hard_Condition/".getBytes();
      this.var_1b7 = new Vector();
      this.var_27c = this.sub_242();
      this.sub_229();
      if(var6 != null) {
         var6.sub_3e(this);
         this.var_b1 = new Class_86b(this, var5, var6);
      } else {
         this.var_b1 = new Class_86b(this, var5);
      }

      this.var_107 = new Class_b0(var2, this.var_27c, var3, new Class_f09(this.var_b1));
      if(var4 != null) {
         this.var_2a5 = var4;
      }

      super.sub_52(this);
   }

   public Class_2b0(int var1, String var2, Class_9a7 var3, Class_1df0 var4) {
      this(2, var2, 0, "spaces.ru/soo/Hard_Condition/".getBytes(), var3, var4);
   }

   public final void sub_25(int var1) {
      this.var_107.sub_5f(var1);
   }

   public final void sub_47(byte[] var1) {
      this.var_107.sub_70(var1);
   }

   public final void sub_9a(Class_1df0 var1) {
      if(var1 != null) {
         var1.sub_3e(this);
      }

      this.var_b1.sub_4c(var1);
   }

   public final void sub_d8(int var1, byte[] var2, int var3) {
      Class_166d var4 = new Class_166d(this, this.var_b1, this.var_107, var1, var2, var3);
      if(this.var_171 == null) {
         this.var_171 = var4;
         this.var_171.start();
      } else {
         int var5 = 0;
         if(!this.var_1b7.isEmpty()) {
            while(((Class_166d)this.var_1b7.elementAt(var5)).sub_2a4() >= 800) {
               ++var5;
            }
         }

         this.var_1b7.insertElementAt(var4, var5);
      }
   }

   public final void sub_ed() {
      if(this.var_171 != null) {
         this.var_171.sub_25b();
         this.var_171 = null;
      }

      if(!this.var_1b7.isEmpty()) {
         this.var_1b7.removeAllElements();
      }

   }

   public final void sub_11e(int var1, int var2) {
      this.sub_13a(var1, new byte[0], var2);
   }

   public final void sub_13a(int var1, byte[] var2, int var3) {
      if(this.var_171 == null) {
         this.var_171 = new Class_166d(this, this.var_b1, this.var_107, var1, var2, false, this.var_2a5, var3);
         this.var_171.sub_69();
         this.var_171.start();
      } else {
         this.var_1b7.addElement(new Class_166d(this, this.var_b1, this.var_107, var1, var2, false, this.var_2a5, var3));
      }
   }

   public final void sub_186(int var1, byte[] var2, byte[] var3, int var4) {
      if(this.var_171 == null) {
         this.var_171 = new Class_166d(this, this.var_b1, this.var_107, var1, var2, true, var3, var4);
         this.var_171.sub_69();
         this.var_171.start();
      } else {
         this.var_1b7.addElement(new Class_166d(this, this.var_b1, this.var_107, var1, var2, true, var3, var4));
      }
   }

   private byte[] sub_229() {
      InputStream var1 = this.getClass().getResourceAsStream("/net/HardCondition/key");
      byte[] var2 = null;

      try {
         var2 = new byte[var1.available()];

         int var3;
         for(int var4 = 0; (var3 = var1.read()) != -1; var2[var4++] = (byte)var3) {
            ;
         }
      } catch (Exception var5) {
         ;
      }

      return var2;
   }

   private int sub_242() {
      InputStream var1 = this.getClass().getResourceAsStream("/net/HardCondition/id");
      int var2 = -1;

      try {
         byte[] var3 = new byte[var1.available()];
         int var4 = 0;
         char var5 = 77;

         int var8;
         for(boolean var6 = false; (var8 = var1.read()) != -1; var5 = (char)var8) {
            var5 ^= (char)var8;
            var3[var4++] = (byte)var5;
         }

         var2 = Integer.parseInt(new String(var3));
      } catch (Exception var7) {
         var7.printStackTrace();
      }

      return var2;
   }

   public final void sub_24f() {
      Class_166d.sub_2c3();
      if(!this.var_1b7.isEmpty()) {
         this.var_171 = (Class_166d)this.var_1b7.firstElement();
         this.var_171.sub_69();
         this.var_1b7.removeElement(this.var_171);
         this.var_171.start();
      } else {
         this.var_171 = null;
      }
   }
}
