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
         sub_237();
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
         sub_237();
         sub_214();
         return Status.getPlayingTime();
      }
   }

   public static void sub_214() {
      Status.var_14c0 = new int[]{56, 55, 55, 55, 55, 10, 48, -1, 48, 10, 91, -1, 91, 98, 10, 10, 30, 29, 27, 27, 22, 30, -1, -1, -1, 98, 98};
   }

   public static void sub_237() {
    //  byte[] var0 = new byte[]{(byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)95, (byte)0, (byte)0, (byte)0, (byte)25, (byte)0, (byte)0, (byte)67, (byte)48, (byte)0, (byte)0, (byte)0, (byte)1, (byte)0, (byte)0, (byte)0, (byte)1, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)3, (byte)0, (byte)0, (byte)0, (byte)-116, (byte)0, (byte)0, (byte)0, (byte)1, (byte)0, (byte)0, (byte)0, (byte)-64, (byte)0, (byte)0, (byte)0, (byte)65, (byte)0, (byte)2, (byte)54, (byte)104, (byte)0, (byte)0, (byte)0, (byte)2, (byte)0, (byte)0, (byte)0, (byte)4, (byte)0, (byte)0, (byte)0, (byte)1, (byte)0, (byte)0, (byte)0, (byte)7, (byte)0, (byte)0, (byte)0, (byte)106, (byte)0, (byte)0, (byte)0, (byte)2, (byte)0, (byte)0, (byte)0, (byte)-101, (byte)0, (byte)0, (byte)0, (byte)52, (byte)0, (byte)0, (byte)125, (byte)-56, (byte)0, (byte)0, (byte)0, (byte)1, (byte)0, (byte)0, (byte)0, (byte)2, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)4, (byte)0, (byte)0, (byte)0, (byte)-106, (byte)0, (byte)0, (byte)0, (byte)3, (byte)0, (byte)0, (byte)0, (byte)-121, (byte)0, (byte)0, (byte)0, (byte)55, (byte)0, (byte)0, (byte)-105, (byte)-12, (byte)0, (byte)0, (byte)0, (byte)2, (byte)0, (byte)0, (byte)0, (byte)2, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)5, (byte)0, (byte)0, (byte)0, (byte)112, (byte)0, (byte)0, (byte)0, (byte)4, (byte)0, (byte)0, (byte)0, (byte)-86, (byte)0, (byte)0, (byte)0, (byte)38, (byte)0, (byte)0, (byte)-9, (byte)-88, (byte)0, (byte)0, (byte)0, (byte)4, (byte)0, (byte)0, (byte)0, (byte)1, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)5, (byte)0, (byte)0, (byte)0, (byte)-94, (byte)0, (byte)0, (byte)0, (byte)5, (byte)0, (byte)0, (byte)0, (byte)-86, (byte)0, (byte)0, (byte)0, (byte)60, (byte)0, (byte)0, (byte)121, (byte)124, (byte)0, (byte)0, (byte)0, (byte)2, (byte)0, (byte)0, (byte)0, (byte)1, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)4, (byte)0, (byte)0, (byte)0, (byte)115, (byte)0, (byte)0, (byte)0, (byte)6, (byte)0, (byte)0, (byte)0, (byte)105, (byte)0, (byte)0, (byte)0, (byte)42, (byte)0, (byte)0, (byte)-107, (byte)56, (byte)0, (byte)0, (byte)0, (byte)2, (byte)0, (byte)0, (byte)0, (byte)1, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)5, (byte)0, (byte)0, (byte)0, (byte)-108, (byte)0, (byte)0, (byte)0, (byte)7, (byte)0, (byte)0, (byte)0, (byte)-36, (byte)0, (byte)0, (byte)0, (byte)-16, (byte)0, (byte)2, (byte)-80, (byte)-84, (byte)0, (byte)0, (byte)0, (byte)2, (byte)0, (byte)0, (byte)0, (byte)1, (byte)0, (byte)0, (byte)0, (byte)1, (byte)0, (byte)0, (byte)0, (byte)7, (byte)0, (byte)0, (byte)0, (byte)65, (byte)0, (byte)0, (byte)0, (byte)8, (byte)0, (byte)0, (byte)1, (byte)-62, (byte)0, (byte)0, (byte)0, (byte)30, (byte)0, (byte)123, (byte)-45, (byte)56, (byte)0, (byte)0, (byte)0, (byte)4, (byte)0, (byte)0, (byte)0, (byte)4, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)15, (byte)0, (byte)0, (byte)0, (byte)-101, (byte)0, (byte)0, (byte)0, (byte)9, (byte)0, (byte)0, (byte)0, (byte)-6, (byte)0, (byte)0, (byte)0, (byte)65, (byte)0, (byte)2, (byte)73, (byte)-16, (byte)0, (byte)0, (byte)0, (byte)2, (byte)0, (byte)0, (byte)0, (byte)2, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)7, (byte)0, (byte)0, (byte)0, (byte)-104, (byte)0, (byte)0, (byte)0, (byte)10, (byte)0, (byte)0, (byte)0, (byte)-56, (byte)0, (byte)0, (byte)0, (byte)100, (byte)0, (byte)5, (byte)17, (byte)-88, (byte)0, (byte)0, (byte)0, (byte)2, (byte)0, (byte)0, (byte)0, (byte)4, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)5, (byte)0, (byte)0, (byte)0, (byte)-106, (byte)0, (byte)0, (byte)0, (byte)11, (byte)0, (byte)0, (byte)0, (byte)-46, (byte)0, (byte)0, (byte)0, (byte)-76, (byte)0, (byte)4, (byte)-2, (byte)32, (byte)0, (byte)0, (byte)0, (byte)2, (byte)0, (byte)0, (byte)0, (byte)2, (byte)0, (byte)0, (byte)0, (byte)1, (byte)0, (byte)0, (byte)0, (byte)8, (byte)0, (byte)0, (byte)0, (byte)75, (byte)0, (byte)0, (byte)0, (byte)12, (byte)0, (byte)0, (byte)0, (byte)-81, (byte)0, (byte)0, (byte)0, (byte)30, (byte)0, (byte)1, (byte)26, (byte)108, (byte)0, (byte)0, (byte)0, (byte)2, (byte)0, (byte)0, (byte)0, (byte)3, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)6, (byte)0, (byte)0, (byte)0, (byte)-124, (byte)0, (byte)0, (byte)0, (byte)13, (byte)0, (byte)0, (byte)0, (byte)100, (byte)0, (byte)0, (byte)0, (byte)50, (byte)0, (byte)0, (byte)76, (byte)-112, (byte)0, (byte)0, (byte)0, (byte)1, (byte)0, (byte)0, (byte)0, (byte)2, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)5, (byte)0, (byte)0, (byte)0, (byte)50, (byte)0, (byte)0, (byte)0, (byte)14, (byte)0, (byte)0, (byte)0, (byte)100, (byte)0, (byte)0, (byte)0, (byte)50, (byte)0, (byte)0, (byte)76, (byte)-112, (byte)0, (byte)0, (byte)0, (byte)1, (byte)0, (byte)0, (byte)0, (byte)2, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)5, (byte)0, (byte)0, (byte)0, (byte)50, (byte)0, (byte)0, (byte)0, (byte)15, (byte)0, (byte)0, (byte)0, (byte)100, (byte)0, (byte)0, (byte)0, (byte)50, (byte)0, (byte)0, (byte)76, (byte)-112, (byte)0, (byte)0, (byte)0, (byte)1, (byte)0, (byte)0, (byte)0, (byte)2, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)5, (byte)0, (byte)0, (byte)0, (byte)50, (byte)0, (byte)0, (byte)0, (byte)16, (byte)0, (byte)0, (byte)0, (byte)-76, (byte)0, (byte)0, (byte)0, (byte)45, (byte)0, (byte)32, (byte)119, (byte)-72, (byte)0, (byte)0, (byte)0, (byte)2, (byte)0, (byte)0, (byte)0, (byte)4, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)10, (byte)0, (byte)0, (byte)0, (byte)120, (byte)0, (byte)0, (byte)0, (byte)17, (byte)0, (byte)0, (byte)0, (byte)-111, (byte)0, (byte)0, (byte)0, (byte)65, (byte)0, (byte)25, (byte)62, (byte)-28, (byte)0, (byte)0, (byte)0, (byte)4, (byte)0, (byte)0, (byte)0, (byte)2, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)10, (byte)0, (byte)0, (byte)0, (byte)95, (byte)0, (byte)0, (byte)0, (byte)18, (byte)0, (byte)0, (byte)0, (byte)115, (byte)0, (byte)0, (byte)0, (byte)28, (byte)0, (byte)2, (byte)-39, (byte)76, (byte)0, (byte)0, (byte)0, (byte)2, (byte)0, (byte)0, (byte)0, (byte)2, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)8, (byte)0, (byte)0, (byte)0, (byte)-111, (byte)0, (byte)0, (byte)0, (byte)19, (byte)0, (byte)0, (byte)0, (byte)-31, (byte)0, (byte)0, (byte)0, (byte)105, (byte)0, (byte)14, (byte)84, (byte)-64, (byte)0, (byte)0, (byte)0, (byte)4, (byte)0, (byte)0, (byte)0, (byte)2, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)9, (byte)0, (byte)0, (byte)0, (byte)90, (byte)0, (byte)0, (byte)0, (byte)20, (byte)0, (byte)0, (byte)0, (byte)125, (byte)0, (byte)0, (byte)0, (byte)25, (byte)0, (byte)0, (byte)-52, (byte)76, (byte)0, (byte)0, (byte)0, (byte)2, (byte)0, (byte)0, (byte)0, (byte)1, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)6, (byte)0, (byte)0, (byte)0, (byte)-101, (byte)0, (byte)0, (byte)0, (byte)21, (byte)0, (byte)0, (byte)0, (byte)-61, (byte)0, (byte)0, (byte)0, (byte)70, (byte)0, (byte)41, (byte)101, (byte)68, (byte)0, (byte)0, (byte)0, (byte)4, (byte)0, (byte)0, (byte)0, (byte)4, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)11, (byte)0, (byte)0, (byte)0, (byte)100, (byte)0, (byte)0, (byte)0, (byte)22, (byte)0, (byte)0, (byte)0, (byte)-96, (byte)0, (byte)0, (byte)0, (byte)-126, (byte)0, (byte)19, (byte)67, (byte)64, (byte)0, (byte)0, (byte)0, (byte)3, (byte)0, (byte)0, (byte)0, (byte)3, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)9, (byte)0, (byte)0, (byte)0, (byte)118, (byte)0, (byte)0, (byte)0, (byte)23, (byte)0, (byte)0, (byte)0, (byte)-51, (byte)0, (byte)0, (byte)0, (byte)55, (byte)0, (byte)0, (byte)-2, (byte)-80, (byte)0, (byte)0, (byte)0, (byte)2, (byte)0, (byte)0, (byte)0, (byte)2, (byte)0, (byte)0, (byte)0, (byte)1, (byte)0, (byte)0, (byte)0, (byte)5, (byte)0, (byte)0, (byte)0, (byte)-128, (byte)0, (byte)0, (byte)0, (byte)24, (byte)0, (byte)0, (byte)0, (byte)-86, (byte)0, (byte)0, (byte)0, (byte)95, (byte)0, (byte)10, (byte)111, (byte)124, (byte)0, (byte)0, (byte)0, (byte)3, (byte)0, (byte)0, (byte)0, (byte)2, (byte)0, (byte)0, (byte)0, (byte)1, (byte)0, (byte)0, (byte)0, (byte)8, (byte)0, (byte)0, (byte)0, (byte)125, (byte)0, (byte)0, (byte)0, (byte)25, (byte)0, (byte)0, (byte)0, (byte)124, (byte)0, (byte)0, (byte)0, (byte)52, (byte)0, (byte)6, (byte)-28, (byte)16, (byte)0, (byte)0, (byte)0, (byte)1, (byte)0, (byte)0, (byte)0, (byte)4, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)8, (byte)0, (byte)0, (byte)0, (byte)-126, (byte)0, (byte)0, (byte)0, (byte)26, (byte)0, (byte)0, (byte)0, (byte)-104, (byte)0, (byte)0, (byte)0, (byte)64, (byte)0, (byte)1, (byte)-95, (byte)48, (byte)0, (byte)0, (byte)0, (byte)2, (byte)0, (byte)0, (byte)0, (byte)2, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)7, (byte)0, (byte)0, (byte)0, (byte)108, (byte)0, (byte)0, (byte)0, (byte)27, (byte)0, (byte)0, (byte)0, (byte)-80, (byte)0, (byte)0, (byte)0, (byte)50, (byte)0, (byte)1, (byte)-114, (byte)-44, (byte)0, (byte)0, (byte)0, (byte)3, (byte)0, (byte)0, (byte)0, (byte)2, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)6, (byte)0, (byte)0, (byte)0, (byte)113, (byte)0, (byte)0, (byte)0, (byte)28, (byte)0, (byte)0, (byte)0, (byte)-61, (byte)0, (byte)0, (byte)0, (byte)110, (byte)0, (byte)51, (byte)-80, (byte)8, (byte)0, (byte)0, (byte)0, (byte)3, (byte)0, (byte)0, (byte)0, (byte)4, (byte)0, (byte)0, (byte)0, (byte)1, (byte)0, (byte)0, (byte)0, (byte)12, (byte)0, (byte)0, (byte)0, (byte)92, (byte)0, (byte)0, (byte)0, (byte)29, (byte)0, (byte)0, (byte)0, (byte)-16, (byte)0, (byte)0, (byte)0, (byte)75, (byte)0, (byte)63, (byte)29, (byte)108, (byte)0, (byte)0, (byte)0, (byte)4, (byte)0, (byte)0, (byte)0, (byte)4, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)12, (byte)0, (byte)0, (byte)0, (byte)118, (byte)0, (byte)0, (byte)0, (byte)30, (byte)0, (byte)0, (byte)0, (byte)-91, (byte)0, (byte)0, (byte)0, (byte)45, (byte)0, (byte)1, (byte)87, (byte)92, (byte)0, (byte)0, (byte)0, (byte)2, (byte)0, (byte)0, (byte)0, (byte)2, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)6, (byte)0, (byte)0, (byte)0, (byte)-128, (byte)0, (byte)0, (byte)0, (byte)31, (byte)0, (byte)0, (byte)0, (byte)-100, (byte)0, (byte)0, (byte)0, (byte)110, (byte)0, (byte)1, (byte)111, (byte)-8, (byte)0, (byte)0, (byte)0, (byte)2, (byte)0, (byte)0, (byte)0, (byte)1, (byte)0, (byte)0, (byte)0, (byte)1, (byte)0, (byte)0, (byte)0, (byte)6, (byte)0, (byte)0, (byte)0, (byte)102, (byte)0, (byte)0, (byte)0, (byte)32, (byte)0, (byte)0, (byte)0, (byte)100, (byte)0, (byte)0, (byte)0, (byte)30, (byte)0, (byte)0, (byte)75, (byte)-56, (byte)0, (byte)0, (byte)0, (byte)1, (byte)0, (byte)0, (byte)0, (byte)1, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)3, (byte)0, (byte)0, (byte)0, (byte)-96, (byte)0, (byte)0, (byte)0, (byte)33, (byte)0, (byte)0, (byte)0, (byte)-80, (byte)0, (byte)0, (byte)0, (byte)108, (byte)0, (byte)1, (byte)38, (byte)-120, (byte)0, (byte)0, (byte)0, (byte)1, (byte)0, (byte)0, (byte)0, (byte)2, (byte)0, (byte)0, (byte)0, (byte)1, (byte)0, (byte)0, (byte)0, (byte)6, (byte)0, (byte)0, (byte)0, (byte)112, (byte)0, (byte)0, (byte)0, (byte)34, (byte)0, (byte)0, (byte)0, (byte)-121, (byte)0, (byte)0, (byte)0, (byte)60, (byte)0, (byte)1, (byte)70, (byte)-12, (byte)0, (byte)0, (byte)0, (byte)4, (byte)0, (byte)0, (byte)0, (byte)1, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)6, (byte)0, (byte)0, (byte)0, (byte)-126, (byte)0, (byte)0, (byte)0, (byte)35, (byte)0, (byte)0, (byte)0, (byte)-101, (byte)0, (byte)0, (byte)0, (byte)40, (byte)0, (byte)0, (byte)108, (byte)-104, (byte)0, (byte)0, (byte)0, (byte)1, (byte)0, (byte)0, (byte)0, (byte)2, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)4, (byte)0, (byte)0, (byte)0, (byte)-106, (byte)0, (byte)0, (byte)0, (byte)36, (byte)0, (byte)0, (byte)0, (byte)-56, (byte)0, (byte)0, (byte)1, (byte)94, (byte)0, (byte)2, (byte)-124, (byte)-120, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)4, (byte)0, (byte)0, (byte)0, (byte)1, (byte)0, (byte)0, (byte)0, (byte)7, (byte)0, (byte)0, (byte)0, (byte)45};
    //  AEAssetsManager.sub_39(var0);
	//CONFIG_LOADER.loadShip();
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
