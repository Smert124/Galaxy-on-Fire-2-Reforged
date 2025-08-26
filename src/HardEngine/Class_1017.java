package HardEngine;

import java.io.DataInputStream;
import java.io.InputStream;

import AE.GlobalStatus;

public final class Class_1017 {

   private static String[] var_29 = new String[63];
   private static int var_74 = GlobalStatus.language;


   public final void sub_f(int var1) {
      var_74 = var1;
      InputStream var5 = null;

      int var2;
      try {
         switch(var_74) {
         case 0:
            var5 = null;
            break;
         case 1:
            var5 = this.getClass().getResourceAsStream("/Resource/lang/en/ENInfo.lang");
            break;
         case 2:
            var5 = null;
            break;
         case 3:
            var5 = null;
            break;
         case 4:
            var5 = null;
            break;
         case 5:
            var5 = null;
            break;
         case 6: /** ���������� ����� �� ������� "����������" **/
            var5 = this.getClass().getResourceAsStream("/Resource/lang/ru/RUInfo.lang");
            break;
         case 7:
            var5 = null;
            break;
         case 8:
            var5 = null;
         }

         DataInputStream var4 = new DataInputStream(var5);

         for(var2 = 0; var2 < var_29.length; ++var2) {
            var_29[var2] = var4.readUTF();
         }

         var4.close();
      } catch (Exception var3) {
         for(var2 = 0; var2 < var_29.length; ++var2) {
            var_29[var2] = "<ERROR|LANG>";
         }

      }
   }

   public static String sub_2b(int var0) {
      return var_29[var0];
   }

}
