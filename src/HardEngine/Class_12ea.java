package HardEngine;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;

import AE.GlobalStatus;
import GoF2.Status;

public final class Class_12ea {

   public static int sub_9f() {
      if(GlobalStatus.var_10e5) {
         return 0;
      } else {
         GlobalStatus.var_10e5 = true;
         sub_252();
         sub_214();
         return 0;
      }
   }

   public static long sub_1c0() {
      if(GlobalStatus.var_10e5) {
         return Status.getPlayingTime();
      } else {
         GlobalStatus.var_10e5 = true;
         sub_252();
         sub_214();
         return Status.getPlayingTime();
      }
   }

   public static void sub_214() {
      Status.var_14c0 = new int[]{56, 55, 55, 55, 55, 10, 48, -1, 48, 10, 91, -1, 91, 98, 10, 10, 30, 29, 27, 27, 22, 30, -1, -1, -1, 98, 98};
   }

   public static void sub_252() {
      byte[] var1 = new byte[64];
      int[] var2 = new int[]{-1, 666, 93434, -1, -1, 1, 1, -1, 23254};
      int var3 = var2.length;

      try {
         for(int var4 = 0; var4 < var3; ++var4) {
            try {
               RecordStore.deleteRecordStore("GoF2_REFORGED_C" + var4);
            } catch (RecordStoreException var7) {
               ;
            }

            RecordStore var0 = RecordStore.openRecordStore("GoF2_REFORGED_C" + var4, true);
            ByteArrayOutputStream var5 = new ByteArrayOutputStream();
            DataOutputStream var6 = new DataOutputStream(var5);
            if(var4 == 0) {
               var6.write(var1);
            }

            if(var4 == 3) {
               var6.writeUTF("HardEngine");
            }

            if(var4 == 4) {
               var6.writeUTF("PAROLE");
            }

            if(var4 == 7) {
               var6.writeUTF("7777777777777777");
            }

            if(var2[var4] != -1) {
               var6.writeInt(var2[var4]);
            }

            var6.close();
            var5.close();
            var0.addRecord(var5.toByteArray(), 0, var5.toByteArray().length);
            var0.closeRecordStore();
            var0 = null;
         }
      } catch (Exception var8) {
         return;
      }
   }
}
