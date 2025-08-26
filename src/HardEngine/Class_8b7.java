package HardEngine;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import AE.GlobalStatus;
import GoF2.Globals;
import GoF2.RecordHandler;
import GoF2.Status;

final class Class_8b7 extends Class_1182 {

   private final Class_1991 var_4e;


   Class_8b7(Class_1991 var1) {
      this.var_4e = var1;
   }

   public final void sub_41(int var1, int var2) {
      Class_1991.sub_4a5(this.var_4e);
      switch(var1) {
      case 0:
         Class_1991.var_7b9 = var2;
         return;
      case 904:
         Class_1991.sub_572(this.var_4e).set(Class_1017.sub_2b(1), false);
         Class_1991.sub_587(true);
         return;
      default:
         Class_1991.sub_572(this.var_4e).set(Class_1017.sub_2b(3) + "(" + var1 + ")", false);
         Class_1991.sub_587(true);
      }
   }

   public final void sub_62(byte var1) {
      Class_1991.sub_66b((float)var1 / 100.0F);
   }

   public final void sub_73(int var1) {
      Class_1991.sub_4a5(this.var_4e);
      if(var1 != 0 && var1 != 30) {
         this.var_4e.sub_160(5);
      } else {
         (new RecordHandler()).sub_406(1, (byte[])null, (String)null, 6);
         this.var_4e.sub_160(4);
      }
   }

   public final void sub_7d(int var1, int var2, int var3, byte[] var4) {
      this.sub_b2(var1, var3, var4);
   }

   public final void sub_b2(int var1, int var2, byte[] var3) {
      Class_1991.sub_4a5(this.var_4e);
      switch(var1) {
      case 0:
         RecordHandler var4;
         (var4 = new RecordHandler()).sub_406(var2, (byte[])null, (String)null, 1);
         var4.sub_406(0, var3, (String)null, 0);
         var4.sub_406(0, (byte[])null, Class_1991.var_71d, 3);
         var4.sub_406(0, (byte[])null, Class_1991.sub_6cd(this.var_4e), 4);
         GlobalStatus.var_102a = true;
         Class_1991.sub_519(true);
         GlobalStatus.var_1132.sub_252(13, 0);
         this.var_4e.sub_160(12);
         return;
      case 20:
         Class_1991.sub_572(this.var_4e).set(Class_1017.sub_2b(14), false);
         Class_1991.sub_587(true);
         return;
      case 21:
         Class_1991.sub_572(this.var_4e).set(Class_1017.sub_2b(18), false);
         Class_1991.sub_587(true);
         return;
      case 22:
         Class_1991.sub_572(this.var_4e).set(Class_1017.sub_2b(18), false);
         Class_1991.sub_587(true);
         return;
      case 23:
         Class_1991.sub_572(this.var_4e).set(Class_1017.sub_2b(16), false);
         Class_1991.sub_587(true);
         return;
      case 24:
         Class_1991.sub_572(this.var_4e).set(Class_1017.sub_2b(15), false);
         Class_1991.sub_587(true);
         return;
      case 904:
         Class_1991.sub_572(this.var_4e).set(Class_1017.sub_2b(1), false);
         Class_1991.sub_587(true);
         return;
      default:
         Class_1991.sub_572(this.var_4e).set(Class_1017.sub_2b(3) + "(" + var1 + ")", false);
         Class_1991.sub_587(true);
      }
   }

   public final void sub_e8(int var1) {
      switch(var1) {
      case 0:
         RecordHandler var2;
         (var2 = new RecordHandler()).sub_406(0, (byte[])null, Class_1991.var_71d, 3);
         var2.sub_406(0, (byte[])null, Class_1991.sub_6cd(this.var_4e), 4);
         GlobalStatus.var_1132.sub_252(13, 0);
         GlobalStatus.var_1132.sub_252(14, 0);
         this.var_4e.sub_160(12);
         return;
      case 21:
         Class_1991.sub_572(this.var_4e).set(Class_1017.sub_2b(18), false);
         Class_1991.sub_587(true);
         break;
      case 22:
         Class_1991.sub_572(this.var_4e).set(Class_1017.sub_2b(18), false);
         Class_1991.sub_587(true);
         break;
      case 24:
         Class_1991.sub_572(this.var_4e).set(Class_1017.sub_2b(15), false);
         Class_1991.sub_587(true);
         break;
      case 28:
         Class_1991.sub_572(this.var_4e).set(Class_1017.sub_2b(17), false);
         Class_1991.sub_587(true);
         break;
      case 904:
         Class_1991.sub_572(this.var_4e).set(Class_1017.sub_2b(1), false);
         Class_1991.sub_587(true);
         break;
      case 907:
         this.var_4e.sub_160(21);
         break;
      default:
         Class_1991.sub_572(this.var_4e).set(Class_1017.sub_2b(3) + "(" + var1 + ")", false);
         Class_1991.sub_587(true);
      }

      Class_1991.sub_519(false);
   }

   public final synchronized void sub_113(int var1, int var2) {
      Class_1991.sub_4a5(this.var_4e);
      switch(var1) {
      case 0:
         Class_1991.sub_6f7(var2);
         String var3 = Status.replaceTokens(Class_1991.sub_725().sub_1de(), "" + var2, "#CB");
         Class_1991.sub_725().setText(var3);
         Class_1991.sub_75f(this.var_4e);
         return;
      case 904:
         Class_1991.sub_572(this.var_4e).set(Class_1017.sub_2b(1), false);
         Class_1991.sub_587(true);
         return;
      default:
         Class_1991.sub_572(this.var_4e).set(Class_1017.sub_2b(3) + "(" + var1 + ")", false);
         Class_1991.sub_587(true);
      }
   }

   public final void sub_153(int var1, int var2) {
      Class_1991.sub_4a5(this.var_4e);
      switch(var1) {
      case 0:
         Class_1991.sub_7bd(var2);
         String var3 = Status.replaceTokens(Class_1991.sub_725().sub_1de(), "" + var2, "#CT");
         Class_1991.sub_725().setText(var3);
         Class_1991.sub_75f(this.var_4e);
         return;
      case 904:
         Class_1991.sub_572(this.var_4e).set(Class_1017.sub_2b(1), false);
         Class_1991.sub_587(true);
         return;
      default:
         Class_1991.sub_572(this.var_4e).set(Class_1017.sub_2b(3) + "(" + var1 + ")", false);
         Class_1991.sub_587(true);
      }
   }

   public final void sub_1a5(int var1, int var2, int[] var3, byte[][] var4) {
      Class_1991.sub_4a5(this.var_4e);
      switch(var1) {
      case 0:
         switch(var2) {
         case 13:
            Globals.sub_39(var4[0]);
            return;
         case 14:
            for(var1 = 0; var1 < var3.length; ++var1) {
               if(var3[var1] == 57) {
                  try {
                     ByteArrayInputStream var7 = new ByteArrayInputStream(var4[var1]);
                     DataInputStream var8 = new DataInputStream(var7);

                     for(int var5 = 0; var5 < Status.var_14c0.length; ++var5) {
                        Status.var_14c0[var5] = var8.readInt();
                     }

                     var8.close();
                  } catch (Exception var6) {
                     var6.printStackTrace();
                  }
               }
            }

            (new RecordHandler()).sub_406(1, (byte[])null, (String)null, 5);
         default:
            return;
         }
      case 25:
         (new RecordHandler()).sub_406(0, (byte[])null, (String)null, 5);
         return;
      case 904:
         Class_1991.sub_572(this.var_4e).set(Class_1017.sub_2b(1), false);
         Class_1991.sub_587(true);
         return;
      default:
         Class_1991.sub_725().setText(Class_1017.sub_2b(3) + "(" + var1 + ")");
         Class_1991.sub_572(this.var_4e).set(Class_1017.sub_2b(3) + "(" + var1 + ")", false);
         Class_1991.sub_587(true);
      }
   }

   public final void sub_1d7(int var1, int var2) {
      Class_1991.sub_4a5(this.var_4e);
      switch(var1) {
      case 0:
         switch(var2) {
         case 13:
            Class_1991.sub_519(true);
            GlobalStatus.var_102a = true;
            GlobalStatus.var_1132.sub_252(13, 0);
            return;
         case 14:
            Class_1991.sub_519(true);
            GlobalStatus.var_102a = true;
            GlobalStatus.var_1132.sub_252(14, 0);
            this.var_4e.sub_160(11);
         default:
            return;
         }
      case 26:
         Class_1991.sub_572(this.var_4e).set(Class_1017.sub_2b(3), false);
         Class_1991.sub_587(true);
         return;
      case 904:
         Class_1991.sub_572(this.var_4e).set(Class_1017.sub_2b(1), false);
         Class_1991.sub_587(true);
         return;
      default:
         Class_1991.sub_725().setText(Class_1017.sub_2b(3) + "(" + var1 + ")");
         Class_1991.sub_572(this.var_4e).set(Class_1017.sub_2b(3) + "(" + var1 + ")", false);
         Class_1991.sub_587(true);
      }
   }

   public final void sub_223(int var1, int var2, String var3) {
      Class_1991.sub_4a5(this.var_4e);
      label23:
      switch(var1) {
      case 0:
         switch(var2) {
         case 27:
            GlobalStatus.var_102a = true;
            Class_1991.sub_7de(this.var_4e, new Class_28f());
            Class_1991.sub_80e(this.var_4e).sub_8d(var3);
            return;
         case 31:
            if(!Class_1991.sub_862() && Class_1991.sub_892() == 12) {
               String var5 = Class_1991.sub_725().sub_1de();
               var5 = var5 + "\n\n" + var3;
               Class_1991.sub_725().setText(var5);
               Class_1991.sub_8b3(true);
            }
         case 35:
            try {
               GlobalStatus.midlet.platformRequest(var3);
            } catch (Exception var4) {
               Class_1991.sub_572(this.var_4e).set(Class_1017.sub_2b(3), false);
               Class_1991.sub_587(true);
               this.var_4e.sub_160(9);
            }
            break label23;
         default:
            return;
         }
      case 904:
         Class_1991.sub_572(this.var_4e).set(Class_1017.sub_2b(1), false);
         Class_1991.sub_587(true);
         return;
      default:
         Class_1991.sub_572(this.var_4e).set(Class_1017.sub_2b(3) + "(" + var1 + ")", false);
         Class_1991.sub_587(true);
      }

   }

   public final void sub_25e(int var1) {
      Class_1991.sub_4a5(this.var_4e);
      switch(var1) {
      case 0:
         this.var_4e.sub_160(15);
         return;
      case 904:
         Class_1991.sub_572(this.var_4e).set(Class_1017.sub_2b(1), false);
         Class_1991.sub_587(true);
         this.var_4e.sub_160(18);
         return;
      default:
         Class_1991.sub_572(this.var_4e).set(Class_1017.sub_2b(3) + "(" + var1 + ")", false);
         Class_1991.sub_587(true);
         this.var_4e.sub_160(18);
      }
   }

   public final void sub_2c2(int var1, int var2, byte[] var3) {
      Class_1991.sub_4a5(this.var_4e);
      switch(var1) {
      case 0:
         if(var2 == 0) {
            Class_1991.sub_8d3((new RecordHandler()).readRecordFromByteArray(var3));
            this.var_4e.sub_160(16);
            return;
         }
         break;
      case 2:
         this.var_4e.sub_160(17);
         return;
      case 904:
         Class_1991.sub_572(this.var_4e).set(Class_1017.sub_2b(1), false);
         Class_1991.sub_587(true);
         this.var_4e.sub_160(18);
         return;
      default:
         Class_1991.sub_572(this.var_4e).set(Class_1017.sub_2b(3) + "(" + var1 + ")", false);
         Class_1991.sub_587(true);
         this.var_4e.sub_160(18);
      }

   }
}
