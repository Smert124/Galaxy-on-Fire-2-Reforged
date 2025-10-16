package HardEngine;


final class Class_ca6 extends Thread {

   private Thread var_203;
   private Class_2b0 var_244;


   public Class_ca6(Class_166d var1, Thread var2, Class_2b0 var3) {
      this.var_203 = var2;
      this.var_244 = var3;
   }

   public final void run() {
      if(this.var_203 != null && this.var_203.isAlive()) {
         try {
            this.var_203.join();
         } catch (Exception var1) {
            ;
         }
      }

      this.var_244.sub_24f();
      this.var_244 = null;
      this.var_203 = null;
   }
}
