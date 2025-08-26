package HardEngine;

import AE.GlobalStatus;


final class Class_f73 implements Class_a99 {

   private final Class_1991 var_6a;


   Class_f73(Class_1991 var1) {
      this.var_6a = var1;
   }

   public final void sub_28(int var1) {
      Class_1991.sub_4a5(this.var_6a);
      switch(var1) {
      case 0:
         Class_1991.sub_519(true);
         GlobalStatus.var_102a = true;
         this.var_6a.var_925.sub_74(0);
         this.var_6a.var_925.sub_cf(0);
         this.var_6a.sub_160(20);
         return;
      case 904:
         Class_1991.sub_572(this.var_6a).set(Class_1017.sub_2b(1), false);
         Class_1991.sub_587(true);
         return;
      default:
         Class_1991.sub_572(this.var_6a).set(Class_1017.sub_2b(3) + "(" + var1 + ")", false);
         Class_1991.sub_587(true);
      }
   }

   public final void sub_45(int var1, int[] var2) {
      Class_1991.sub_4a5(this.var_6a);
      switch(var1) {
      case 0:
         Class_1991.var_6bc = var2;
         return;
      case 904:
         Class_1991.sub_572(this.var_6a).set(Class_1017.sub_2b(1), false);
         Class_1991.sub_587(true);
         return;
      default:
         Class_1991.sub_572(this.var_6a).set(Class_1017.sub_2b(3) + "(" + var1 + ")", false);
         Class_1991.sub_587(true);
      }
   }

   public final void sub_d1(int var1, String[] var2, int[] var3, int[] var4) {
      Class_1991.sub_4a5(this.var_6a);
      switch(var1) {
      case 0:
         Class_1991.var_19d = var2;
         Class_1991.var_350 = var3;
         Class_1991.var_679 = var4;
         return;
      case 904:
         Class_1991.sub_572(this.var_6a).set(Class_1017.sub_2b(1), false);
         Class_1991.sub_587(true);
         return;
      default:
         Class_1991.sub_572(this.var_6a).set(Class_1017.sub_2b(3) + "(" + var1 + ")", false);
         Class_1991.sub_587(true);
      }
   }

   public final void sub_7e(int var1, int var2) {
      Class_1991.sub_4a5(this.var_6a);
      switch(var1) {
      case 0:
         Class_1991.sub_5d1(var2);
         return;
      case 2:
         Class_1991.sub_5d1(-1);
         return;
      case 904:
         Class_1991.sub_572(this.var_6a).set(Class_1017.sub_2b(1), false);
         Class_1991.sub_587(true);
         return;
      default:
         Class_1991.sub_572(this.var_6a).set(Class_1017.sub_2b(3) + "(" + var1 + ")", false);
         Class_1991.sub_587(true);
      }
   }

   public final void sub_a2(int var1, String[] var2, int[] var3) {
      Class_1991.sub_4a5(this.var_6a);
      switch(var1) {
      case 0:
         Class_1991.sub_61b(this.var_6a, var2);
         Class_1991.sub_638(var3);
         return;
      case 904:
         Class_1991.sub_572(this.var_6a).set(Class_1017.sub_2b(1), false);
         Class_1991.sub_587(true);
         return;
      default:
         Class_1991.sub_572(this.var_6a).set(Class_1017.sub_2b(3) + "(" + var1 + ")", false);
         Class_1991.sub_587(true);
      }
   }
}
