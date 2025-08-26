package HardEngine;

import java.io.IOException;

public final class Class_3d4 {

   private boolean var_144 = true;
   private boolean var_19b = false;
   private String var_293;
   private Class_f09 var_3c8;


   public Class_3d4(String var1, Class_f09 var2) {
      this.var_293 = sub_44(var1);
      this.var_3c8 = var2;
   }

   private static String sub_44(String var0) {
      StringBuffer var1 = new StringBuffer();
      return var1.toString();
   }

   public final byte[] sub_79(byte[] var1, boolean var2) throws Class_808, IOException {
      try {
         if(this.var_144) {
         } else {
            this.sub_8d(var1, var2);
         }
      } catch (IOException var13) {
         throw new Class_808();
      }

      return !this.var_19b?this.sub_b2():new byte[0];
   }

   private void sub_8d(byte[] var1, boolean var2) throws IOException {

   }

   private byte[] sub_b2() throws IOException {
	   return null;
   }

   public final void sub_bd() {

   }

   public final void sub_117() {
   }

   public final void sub_12e() {
   }
}