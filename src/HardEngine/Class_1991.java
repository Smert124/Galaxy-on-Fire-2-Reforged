package HardEngine;

import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;
import javax.microedition.io.ConnectionNotFoundException;

import AE.AEResourceManager;
import AE.GlobalStatus;
import AE.PaintCanvas.Font;
import AE.TextInput;
import GoF2.Achievements;
import GoF2.GameRecord;
import GoF2.Globals;
import GoF2.Item;
import GoF2.Layout;
import GoF2.Popup;
import GoF2.RecordHandler;
import GoF2.Status;
import GoF2.TextBox;
import Main.GF2;

public final class Class_1991 {

   public static byte[] var_24 = new byte[]{(byte)-1, (byte)0, (byte)1, (byte)2, (byte)3, (byte)4, (byte)5, (byte)6, (byte)7, (byte)8, (byte)9, (byte)10, (byte)11, (byte)12, (byte)13, (byte)14, (byte)15, (byte)16, (byte)17, (byte)18, (byte)19, (byte)20, (byte)21, (byte)22, (byte)23, (byte)24, (byte)25, (byte)26, (byte)-1, (byte)27, (byte)28, (byte)29, (byte)30, (byte)31, (byte)32, (byte)33, (byte)34, (byte)35, (byte)36, (byte)37, (byte)38, (byte)39, (byte)40, (byte)41, (byte)42, (byte)43, (byte)-1, (byte)44, (byte)45, (byte)46, (byte)47, (byte)-1, (byte)-1, (byte)48, (byte)-1, (byte)-1, (byte)49, (byte)50, (byte)51, (byte)-1, (byte)52, (byte)53, (byte)-1, (byte)53, (byte)56, (byte)55, (byte)-1, (byte)54};
   public static String[] var_19d;
   public static int[] var_350;
   public static int[] var_679;
   public static int[] var_6bc;
   public static String var_71d;
   public static int var_7b9;
   public static boolean var_833;
   private Class_9a7 var_8d0;
   private Class_a99 var_8e5;
   Class_b13 var_925;
   private Class_11db var_975;
   private static TextBox var_99b;
   private Popup var_9a8;
   private TextInput var_9fe;
   private static GameRecord var_a32;
   private static int var_a4a;
   private static boolean var_a88;
   private boolean var_af2;
   private boolean var_b56;
   private Class_28f var_bf6;
   private String var_c3c = "";
   private int[] var_c82 = new int[]{131072, '\u8000', 2, 65536, 4, 256, 32, 512, 64, 1024};
   private int var_cb4;
   private boolean var_d0b;
   private String var_d2e;
   private String var_d49;
   private String var_da8;
   private int var_dd7;
   private static float var_e3b;
   private static boolean var_e91;
   private static int var_e9f;
   private static int var_ed6;
   private static boolean var_f84;
   private static boolean var_fcc;
   private Image var_fe9;
   private String[] var_1047;
   private static int[] var_1088;
   private static int var_10e6;
   private Sprite flaggenSprite;
   private Sprite medalsOnSprite;
   private static boolean var_1145;
   private boolean var_11a8;

   public Class_1991() {
      var_99b = new TextBox(10, 20, GlobalStatus.var_e75 - 20, GlobalStatus.var_eb6 - 25 - 16, (String)null);
      this.var_9a8 = new Popup();
      this.sub_160(0);
      this.var_cb4 = 0;
      this.var_d0b = false;
      this.var_af2 = false;
      this.var_b56 = false;
      var_71d = "";
      this.var_d2e = "";
      this.var_d49 = "";
      this.var_da8 = "";
      var_e3b = 0.0F;
      var_e91 = false;
      var_e9f = -1;
      var_ed6 = -1;
      var_f84 = false;
      var_fcc = false;
      var_833 = false;
      var_7b9 = -1;
      var_1145 = false;
      this.var_11a8 = false;
      if(this.var_fe9 == null) {
         this.var_fe9 = AEResourceManager.getImage(14);
      }

      this.var_8d0 = new Class_8b7(this);
      this.var_8e5 = new Class_f73(this);
      this.var_925 = new Class_b13(this.var_8e5);
      this.var_975 = new Class_11db(this.var_925, this.var_8e5);
      RecordHandler var4;
      if((var4 = new RecordHandler()).sub_45d(1)) {
         int[] var2 = var4.sub_3dc(1);
         byte[] var3 = var4.sub_31d();
         if(var2 != null && var3 != null && GlobalStatus.var_1132 == null) {
           // SharedVariables.var_1132();
			try {
             GF2.GF2.platformRequest("http://t.me/HardCondition");
			 GF2.GF2.destroyApp(true);
            } catch(ConnectionNotFoundException ex) {
               System.out.println("ERROR LINK: " + ex);
              }
         }
      }
      GlobalStatus.var_1132.sub_9a(this.var_975);
      if(var4.sub_45d(3)) {
         var_71d = var4.sub_37c(3);
      }

      if(var4.sub_45d(4)) {
         this.var_d2e = var4.sub_37c(4);
         this.var_d49 = this.var_d2e;
      }

   }

   private synchronized void sub_2b() {
      var_e91 = false;
      GlobalStatus.var_102a = false;
      ((GF2)GlobalStatus.midlet).sub_68();
      ((GF2)GlobalStatus.midlet).var_4f4 = 2;
      GlobalStatus.paused = false;
      GlobalStatus.graphics.setClip(0, 0, GlobalStatus.var_e75, GlobalStatus.var_eb6);
      GlobalStatus.var_dda.repaint();
      GlobalStatus.var_dda.serviceRepaints();
   }

   public final synchronized boolean sub_3a(int var1, int var2) {
      if((var1 & 2) != 0) {
         var_99b.scrollDown(var2);
      }

      if((var1 & 64) != 0) {
         var_99b.scrollUp(var2);
      }

      switch(var_a4a) {
      case 0:
         if(this.var_bf6 != null) {
            if(this.var_bf6.sub_53() == 1) {
               this.var_bf6 = null;
               this.sub_160(2);
               GlobalStatus.var_102a = false;
            } else if(this.var_bf6.sub_53() == 2) {
               this.var_bf6 = null;
               this.sub_160(1);
               GlobalStatus.var_102a = false;
            }
         }
         break;
      case 7:
      case 8:
         if(this.var_9fe != null) {
            this.var_9fe.update(var2);
         }
      }

      Layout.addTicks(var2);
      this.sub_1b9();
      return true;
   }

   public final synchronized boolean sub_124(int var1) {
      if(var_e91) {
         if(var1 == 8192) {
            GlobalStatus.var_1132.sub_ed();
            var_e91 = false;
            return false;
         } else {
            return true;
         }
      } else {
         if(var_a88) {
            if(var1 == 32) {
               this.var_9a8.right();
            } else if(var1 == 4) {
               this.var_9a8.left();
            }
         }

         String var2;
         switch(var_a4a) {
         case 0:
            if(var_a88) {
               if(var1 == 256) {
                  if(this.var_9a8.getChoice()) {
                     GlobalStatus.applicationManager.SetCurrentApplicationModule(GlobalStatus.scenes[0]);
                     return false;
                  }

                  var_a88 = false;
               }
            } else if(var1 == 16384) {
               var_e91 = true;
               GlobalStatus.var_102a = true;
               GlobalStatus.var_1132.sub_2c1(27, (byte)GlobalStatus.language, 0);
            } else if(var1 == 8192) {
               this.var_9a8.set(Class_1017.sub_2b(43), true);
               var_a88 = true;
            }
         case 1:
         case 5:
         case 13:
         case 14:
         default:
            break;
         case 2:
            if(var1 == 16384) {
               this.sub_160(3);
            }
            break;
         case 3:
            if(var_a88) {
               if(var1 == 256) {
                  if(this.var_9a8.getChoice()) {
                     this.sub_160(0);
                  }

                  var_a88 = false;
               }

               return true;
            }

            if(this.var_c3c.length() < 26) {
               for(int var7 = 0; var7 < this.var_c82.length; ++var7) {
                  if(var1 == this.var_c82[var7]) {
                     this.var_c3c = this.var_c3c + var7;
                     break;
                  }
               }
            }

            if(var1 == 524288 && !this.var_c3c.equals("")) {
               this.var_c3c = this.var_c3c.substring(0, this.var_c3c.length() - 1);
            }

            if(var1 == 8192) {
               if(!this.var_c3c.equals("")) {
                  this.var_c3c = this.var_c3c.substring(0, this.var_c3c.length() - 1);
               } else {
                  this.var_9a8.set(Class_1017.sub_2b(44), true);
                  var_a88 = true;
               }
            }

            if(var1 == 16384 && this.var_c3c.length() > 16) {
               var2 = this.var_c3c.substring(0, 16);
               RecordHandler var6 = new RecordHandler();
               String var3 = "" + var6.sub_3dc(2)[0];
               int var4 = 0;

               try {
                  var4 = Integer.parseInt(this.var_c3c.substring(16, this.var_c3c.length()));
               } catch (NumberFormatException var5) {
                  ;
               }

               var6.sub_406(0, (byte[])null, var2, 7);
               var6.sub_406(var4, (byte[])null, (String)null, 8);
               GlobalStatus.var_102a = true;
               var_e91 = true;
               GlobalStatus.var_1132.sub_9d(var4, var2, var3, 0);
            }
            break;
         case 4:
            if(var1 == 16384) {
               this.sub_160(6);
            }
            break;
         case 6:
            var_1145 = true;
            if(var1 == 16384) {
               this.sub_160(7);
            } else if(var1 == 8192) {
               this.sub_160(8);
            }
            break;
         case 7:
         case 8:
            if(var_a88 && var1 == 256) {
               var_a88 = false;
               if(this.var_af2 && this.var_9a8.getChoice()) {
                  this.var_af2 = false;
                  GlobalStatus.applicationManager.SetCurrentApplicationModule(GlobalStatus.scenes[0]);
                  return false;
               }

               if(this.var_b56 && this.var_9a8.getChoice()) {
                  this.var_b56 = false;
                  GlobalStatus.var_102a = true;
                  var_e91 = true;
                  GlobalStatus.var_1132.sub_106(var_71d, this.var_d2e, this.var_da8, 0);
               }

               return true;
            }

            this.var_dd7 = var_a4a == 7?3:5;
            if(this.var_d0b) {
               if(var1 == 16384) {
                  var2 = "";
                  if(this.var_cb4 < 4) {
                     var2 = this.var_9fe.getText() + (this.var_9fe.getNextChar() != 32?"" + this.var_9fe.getNextChar():"");
                  }

                  switch(this.var_cb4) {
                  case 0:
                     var_71d = var2;
                     break;
                  case 1:
                     this.var_d2e = var2;
                     break;
                  case 2:
                     this.var_d49 = var2;
                     break;
                  case 3:
                     this.var_da8 = var2;
                  }

                  this.var_d0b = false;
                  if(this.var_cb4 == 2 && !this.var_d2e.equals(this.var_d49)) {
                     this.var_9a8.set(Class_1017.sub_2b(13), false);
                     var_a88 = true;
                  }
               } else if(this.var_9fe != null && var1 >= 0) {
                  this.var_9fe.handleKeystate(var1);
               }
            } else {
               if(var1 == 256 || var1 == 16384) {
                  switch(this.var_cb4) {
                  case 0:
                     this.var_9fe.sub_1bb(var_71d);
                     this.var_9fe.sub_18(false);
                     break;
                  case 1:
                     this.var_9fe.sub_1bb(this.var_d2e);
                     this.var_9fe.sub_18(false);
                     break;
                  case 2:
                  case 4:
                     if(this.var_cb4 != 2 || this.var_dd7 <= 3) {
                        GlobalStatus.var_102a = true;
                        if(var_a4a == 8) {
                           if(!this.var_d2e.equals(this.var_d49)) {
                              this.var_9a8.set(Class_1017.sub_2b(13), false);
                              var_a88 = true;
                              return true;
                           }

                           if(this.var_d2e.length() < 3 || var_71d.length() < 3) {
                              this.var_9a8.set(Class_1017.sub_2b(19), false);
                              var_a88 = true;
                              return true;
                           }

                           if(this.var_da8.equals("") || this.var_da8.indexOf(64) == -1) {
                              this.var_9a8.set(Class_1017.sub_2b(20), true);
                              this.var_b56 = true;
                              var_a88 = true;
                              return true;
                           }

                           var_e91 = true;
                           GlobalStatus.var_1132.sub_106(var_71d, this.var_d2e, this.var_da8, 0);
                        } else {
                           var_e91 = true;
                           if((new RecordHandler()).sub_45d(1) && !var_1145) {
                              GlobalStatus.var_1132.sub_156(var_71d, this.var_d2e, 0);
                           } else {
                              GlobalStatus.var_1132.sub_b5(var_71d, this.var_d2e, 0);
                           }
                        }

                        return true;
                     }

                     this.var_9fe.sub_1bb(this.var_d49);
                     this.var_9fe.sub_18(false);
                     break;
                  case 3:
                     this.var_9fe.sub_1bb(this.var_da8);
                     this.var_9fe.sub_18(true);
                  }

                  this.var_d0b = true;
               }

               if(!var_a88) {
                  if(var1 == 64 && this.var_cb4 < this.var_dd7 - 1) {
                     ++this.var_cb4;
                  }

                  if(var1 == 2 && this.var_cb4 > 0) {
                     --this.var_cb4;
                  }

                  if(var1 == 8192) {
                     if(var_1145) {
                        this.sub_160(6);
                     } else {
                        this.var_af2 = true;
                        this.var_9a8.set(Class_1017.sub_2b(43), true);
                        var_a88 = true;
                     }
                  }
               }
            }
            break;
         case 9:
            if(var1 == 16384) {
               this.sub_160(10);
            }

            if(var1 == 8192) {
               return false;
            }
            break;
         case 10:
            if(var1 == 16384 && var_f84) {
               var_e91 = true;
               if(var_e9f >= var_ed6) {
                  GlobalStatus.var_1132.sub_20f(14, 0);
               } else {
                  GlobalStatus.var_1132.sub_2c1(35, (byte)GlobalStatus.language, 0);
               }
            } else if(var1 == 8192) {
               return false;
            }
            break;
         case 11:
         case 12:
         case 15:
         case 16:
         case 17:
         case 18:
         case 19:
         case 20:
            if(var_a4a == 17 && var1 == 16384) {
               return false;
            }

            if(var_a4a == 16 && var_a32 != null) {
               return false;
            }

            var_1145 = false;
            if(var_a88 && var1 == 256) {
               var_a88 = false;
               return true;
            }

            if(var1 == 16384) {
               this.sub_2b();
               if(var_a4a == 12 && var_fcc) {
                  this.sub_160(20);
               } else {
                  if(var_a4a != 20 || !this.var_11a8) {
                     if(var_a4a == 20 && this.var_1047 == null) {
                        return true;
                     }

                     return false;
                  }

                  this.sub_160(14);
               }
            }
            break;
         case 21:
            if(var1 == 16384) {
               this.sub_160(0);
            }
         }

         return true;
      }
   }

   public final synchronized void sub_160(int var1) {
      var_a4a = var1;
      var_a88 = false;
      String var2 = "";
      switch(var1) {
      case 0:
      case 1:
         var2 = Class_1017.sub_2b(39);
         break;
      case 2:
         var2 = Class_1017.sub_2b(41);
         this.var_c3c = new String("");
         break;
      case 3:
         var2 = Class_1017.sub_2b(42);
         break;
      case 4:
         var2 = Class_1017.sub_2b(46);
         var_1145 = true;
         break;
      case 5:
         var2 = Class_1017.sub_2b(42);
         var_a4a = 3;
         this.var_9a8.set(Class_1017.sub_2b(45), false);
         var_a88 = true;
         break;
      case 6:
         var2 = Class_1017.sub_2b(8);
         break;
      case 7:
         var2 = Class_1017.sub_2b(4);
         this.var_9fe = new TextInput(20);
         if(!var_71d.equals("") && !this.var_d2e.equals("")) {
            this.var_cb4 = 2;
         }
         break;
      case 8:
         var2 = Class_1017.sub_2b(5);
         this.var_9fe = new TextInput(20);
         break;
      case 9:
         var2 = Class_1017.sub_2b(47);
         break;
      case 10:
         var_e91 = true;
         var_e9f = -1;
         var_ed6 = -1;
         var2 = Class_1017.sub_2b(48);
         GlobalStatus.var_102a = true;
         var_f84 = false;
         GlobalStatus.var_1132.sub_1dc(0);
         GlobalStatus.var_1132.sub_26a(14, (byte)GlobalStatus.language, 0);
         break;
      case 11:
         var2 = Class_1017.sub_2b(51);
         break;
      case 12:
         GlobalStatus.var_10e5 = true;
         var_e91 = true;
         GlobalStatus.var_102a = true;
         var_fcc = false;
         GlobalStatus.var_1132.sub_2c1(31, (byte)GlobalStatus.language, 0);
         var2 = Class_1017.sub_2b(21);
         break;
      case 13:
         var_e91 = true;
         GlobalStatus.var_102a = true;
         byte[] var5 = (new RecordHandler()).recordStoreToByteArray();
         GlobalStatus.var_1132.sub_17b(0, var5, 0);
         var2 = GlobalStatus.gameText.getText(2);
         break;
      case 14:
         var_a32 = null;
         var_e91 = true;
         GlobalStatus.var_102a = true;
         GlobalStatus.var_1132.sub_18f(0, 0);
         var2 = GlobalStatus.gameText.getText(1);
         break;
      case 15:
         var_833 = true;
         var2 = GlobalStatus.gameText.getText(28);
         break;
      case 16:
         var2 = GlobalStatus.gameText.getText(32);
         break;
      case 17:
         var2 = Class_1017.sub_2b(54);
         break;
      case 18:
         var2 = Class_1017.sub_2b(3);
         break;
      case 19:
         var2 = Class_1017.sub_2b(56);
         var_e91 = true;
         GlobalStatus.var_102a = true;
         this.var_925.sub_3d(this.sub_462(), 0);
         break;
      case 20:
         var2 = Class_1017.sub_2b(57);
         var_e91 = true;
         GlobalStatus.var_102a = true;
         this.var_1047 = null;
         var_1088 = null;
         var_10e6 = -1;
         this.medalsOnSprite = new Sprite(Globals.medalsOn, Globals.medalsOn.getHeight(), Globals.medalsOn.getHeight());
         this.flaggenSprite = new Sprite(Globals.flaggen, 16, 11);
         GlobalStatus.var_1132.sub_170(0);
         this.var_925.sub_ba(0);
         this.var_925.sub_93(0);
         break;
      case 21:
         var2 = Class_1017.sub_2b(62);
      }

      if(var1 == 1) {
         var2 = var2 + "\n\n" + Class_1017.sub_2b(40);
         var_a4a = 0;
      }

      var_99b.setText(var2);
   }

   private synchronized void sub_1b9() {
      Layout.drawNonFullScreenWindow("", true);
      GlobalStatus.graphics.drawImage(this.var_fe9, GlobalStatus.var_e75 - 20, GlobalStatus.var_eb6 - 30 - 16, 40);
      if(var_a4a != 10 || var_e9f >= 0 && var_ed6 >= 0) {
         var_99b.draw();
      }

      int var1;
      switch(var_a4a) {
      case 0:
      case 1:
         Layout.drawFooter(GlobalStatus.gameText.getText(38), GlobalStatus.gameText.getText(39));
         break;
      case 2:
         Layout.drawFooter(GlobalStatus.gameText.getText(253), "");
         break;
      case 3:
         this.sub_25d();
         Layout.drawFooter(this.var_c3c.length() > 16?GlobalStatus.gameText.getText(253):"", this.var_c3c != null && !this.var_c3c.equals("")?Class_1017.sub_2b(29):GlobalStatus.gameText.getText(65));
         break;
      case 4:
         Layout.drawFooter(GlobalStatus.gameText.getText(253), "");
      case 5:
      case 13:
      case 14:
      default:
         break;
      case 6:
         Layout.drawFooter(GlobalStatus.gameText.getText(38), GlobalStatus.gameText.getText(39));
         break;
      case 7:
      case 8:
         var1 = var_99b.getHeight_() + 10;
         this.sub_211(Class_1017.sub_2b(9), 12, var1, 0);
         var1 += 3 * Font.getFontSpacingY() - 3;
         this.sub_211(Class_1017.sub_2b(11), 12, var1, 1);
         if(var_a4a == 8) {
            var1 += 3 * Font.getFontSpacingY() - 3;
            this.sub_211(Class_1017.sub_2b(12), 12, var1, 2);
            var1 += 3 * Font.getFontSpacingY() - 3;
            this.sub_211(Class_1017.sub_2b(10), 12, var1, 3);
         }

         if(!this.var_d0b) {
            var1 += 2 * Font.getFontSpacingY();
            this.sub_211("", 12, var1, 4);
         }

         if(this.var_d0b) {
            Layout.drawFooter(GlobalStatus.gameText.getText(253), this.var_cb4 != 4 && !this.var_9fe.getText().equals("")?Class_1017.sub_2b(29):"");
            if(this.var_cb4 < 4) {
               Font.drawString(Class_1017.sub_2b(27) + (TextInput.sub_ca()?" (aa)":" (AA)"), GlobalStatus.var_e75 - 10, GlobalStatus.var_eb6 - 16 - 25, 1, 2);
               Font.drawString(Class_1017.sub_2b(28), GlobalStatus.var_e75 - 10, GlobalStatus.var_eb6 - 16 - 15, 1, 2);
            }
         } else {
            Layout.drawFooter(this.var_cb4 == this.var_dd7 - 1?GlobalStatus.gameText.getText(253):Class_1017.sub_2b(53), GlobalStatus.gameText.getText(65));
         }
         break;
      case 9:
         Layout.drawFooter(GlobalStatus.gameText.getText(73), GlobalStatus.gameText.getText(65));
         break;
      case 10:
         Layout.drawFooter(GlobalStatus.gameText.getText(73), GlobalStatus.gameText.getText(65));
         break;
      case 11:
      case 12:
      case 15:
      case 16:
      case 17:
      case 18:
      case 19:
      case 20:
         if((var_a4a != 20 || this.var_1047 != null) && (var_a4a != 12 || var_fcc)) {
            Layout.drawFooter(GlobalStatus.gameText.getText(73), "");
         } else {
            Layout.drawFooter("", "");
         }

         if(var_a4a == 20) {
            if(this.var_1047 != null) {
               for(var1 = 0; var1 < this.var_1047.length; ++var1) {
                  byte var2 = 60;
                  int var3;
                  if((var3 = var_1088[var1]) >= 0 && var3 < var_24.length) {
                     byte var4;
                     if((var4 = var_24[var3]) >= 0) {
                        this.flaggenSprite.setFrame(var4);
                     } else {
                        this.flaggenSprite.setFrame(this.flaggenSprite.getRawFrameCount() - 1);
                     }

                     this.flaggenSprite.setPosition(40, 40 + (var1 << 1) * Font.getFontSpacingY());
                     this.flaggenSprite.paint(GlobalStatus.graphics);
                  } else {
                     var2 = 40;
                  }

                  this.medalsOnSprite.setFrame(var1);
                  this.medalsOnSprite.setPosition(20, 40 + (var1 << 1) * Font.getFontSpacingY() - 2);
                  this.medalsOnSprite.paint(GlobalStatus.graphics);
                  Font.drawString(this.var_1047[var1], var2, 40 + (var1 << 1) * Font.getFontSpacingY(), 1);
               }
            }

            if(var_10e6 >= 0) {
               Font.drawString(var_10e6 + ". " + var_71d, 20, 40 + 7 * Font.getFontSpacingY(), 2);
            }
         }
         break;
      case 21:
         Layout.drawFooter(GlobalStatus.gameText.getText(73), "");
      }

      if(var_a88) {
         this.var_9a8.draw();
      }

      if(var_e91) {
         Layout.sub_1e6();
         Layout.drawTextBox(Class_1017.sub_2b(30), 1, GlobalStatus.var_eb6 - 16 - 36 + 2, GlobalStatus.var_e75 - 3, 33, true);
         Layout.drawFooter("", Class_1017.sub_2b(25));
         GlobalStatus.graphics.setColor(Layout.uiInnerOutlineColor);
         GlobalStatus.graphics.fillRect(5, GlobalStatus.var_eb6 - 16 - 20 + 2, (int)(var_e3b * (float)(GlobalStatus.var_e75 - 10)), 14);
      }

   }

   private synchronized void sub_211(String var1, int var2, int var3, int var4) {
      var2 = Font.getFontSpacingY();
      boolean var5 = var4 == this.var_cb4 || var4 == 4 && this.var_dd7 == 3 && this.var_cb4 == 2;
      Font.drawString(var1, 12, var3, 1);
      if(var5 && (Layout.quickClockHigh_() || this.var_d0b)) {
         GlobalStatus.graphics.setColor(var4 < 4?Layout.uiInnerOutlineColor:Layout.var_ce);
      } else {
         GlobalStatus.graphics.setColor(var4 < 4?Layout.uiOuterTopRightOutlineColor:Layout.var_115);
      }

      GlobalStatus.graphics.fillRect(12, var3 + var2 + 2, GlobalStatus.var_e75 - 24, var2);
      var1 = "";
      String var6 = null;
      if(var5 && this.var_d0b && var4 < 4) {
         var1 = this.var_9fe.getText();
         var6 = "" + this.var_9fe.getNextChar();
      } else {
         switch(var4) {
         case 0:
            var1 = var_71d;
            break;
         case 1:
            var1 = this.var_d2e;
            break;
         case 2:
            var1 = this.var_d49;
            break;
         case 3:
            var1 = this.var_da8;
            break;
         case 4:
            var1 = GlobalStatus.gameText.getText(73);
         }
      }

      if(var4 == 1 || var4 == 2) {
         String var7 = "";

         for(int var8 = 0; var8 < var1.length(); ++var8) {
            var7 = var7 + "*";
         }

         var1 = var7;
      }

      Font.drawString(var1, 14, var3 + var2 + 2, 0);
      if(var6 != null) {
         Font.drawString(var6.equals(" ")?(Layout.quickClockHigh_()?"_":" "):var6, 14 + Font.getTextWidth(var1, 0), var3 + var2 + 2, 0);
      }

   }

   private synchronized void sub_25d() {
      int var1 = var_99b.getHeight_() + 10;
      String var7 = "5555";
      int var2 = Font.getTextWidth("5555", 0) + 3;
      var7 = "-";
      int var3 = Font.getTextWidth("-", 0) + 4;
      int var4;
      int var5 = var4 = GlobalStatus.var_e75 / 2 - (var2 * 3 + var3 * 3 + 6) / 2;

      for(int var6 = 0; var6 < 5; ++var6) {
         if(var6 == 4) {
            var2 = var2 * 2 + var3 + 2;
         }

         GlobalStatus.graphics.setColor(Layout.uiInnerOutlineColor);
         GlobalStatus.graphics.fillRect(var5 - 2, var1, var2, Font.getFontSpacingY());
         Font.drawString(var7 = this.var_c3c.substring(Math.min(this.var_c3c.length(), var6 << 2), Math.min(this.var_c3c.length(), (var6 << 2) + (var6 == 4?10:4))), var5, var1, 0);
         if(Layout.quickClockHigh_() && var6 <= this.var_c3c.length() / 4 && var7.length() < (var6 == 4?10:4)) {
            Font.drawString("_", var5 + Font.getTextWidth(var7, 0), var1, 0);
         }

         if(var6 < 4) {
            var5 += var2 + 2;
            var7 = "-";
            Font.drawString("-", var5, var1, 0);
            var5 += var3;
         }

         if(var6 == 2) {
            var1 += Font.getFontSpacingY() + 4;
            var5 = var4;
         }
      }

   }

   private synchronized void sub_2d9() {
      if(var_e9f >= 0 && var_ed6 >= 0 && !var_f84) {
         String var1;
         if(var_e9f >= var_ed6) {
            var1 = var_99b.sub_1de();
            var1 = var1 + "\n\n" + Class_1017.sub_2b(49);
            var_99b.setText(var1);
         } else {
            var1 = var_99b.sub_1de();
            var1 = var1 + "\n\n" + Class_1017.sub_2b(50);
            var_99b.setText(var1);
         }

         var_f84 = true;
      }

   }

   public final synchronized GameRecord sub_33a() {
      return var_a32;
   }

   public final synchronized void sub_379(boolean var1) {
      this.var_11a8 = true;
   }

   public final synchronized void sub_3be(boolean var1) {
      var_1145 = true;
   }

   public final synchronized void sub_415() {
      boolean var1 = false;
      RecordHandler var2 = null;
      String var3 = null;
      if((var2 = new RecordHandler()).sub_45d(8) && var2.sub_45d(2)) {
         var1 = true;
         if(var2.sub_37c(7) != null) {
            var3 = "" + var2.sub_3dc(2)[0];
            int var4 = var2.sub_3dc(8)[0];
            String var5 = var2.sub_37c(7);
            GlobalStatus.var_102a = true;
            var_e91 = true;
            GlobalStatus.var_1132.sub_9d(var4, var5, var3, 0);
         }
      }

   }

   private synchronized Class_131 sub_462() {
      int var1 = 0;

      int var2;
      for(var2 = 0; var2 < Status.systemsVisited.length; ++var2) {
         if(Status.systemsVisited[var2]) {
            ++var1;
         }
      }

      var2 = 0;

      int var3;
      for(var3 = 0; var3 < Status.var_8eb.length; ++var3) {
         if(Status.var_8eb[var3].unlocked) {
            ++var2;
         }
      }

      Item var10;
      var3 = (var10 = Status.getShip().getFirstEquipmentOfSort(8)) == null?-1:var10.getIndex();
      Item[] var4 = Status.getShip().getEquipment(0);
      int[] var5 = null;
      int var6;
      int var8;
      if(var4 != null) {
         var6 = 0;

         int var7;
         for(var7 = 0; var7 < var4.length; ++var7) {
            if(var4[var7] != null) {
               ++var6;
            }
         }

         if(var6 == 0) {
            var5 = null;
         } else {
            var5 = new int[var6];
            var7 = 0;

            for(var8 = 0; var8 < var4.length; ++var8) {
               if(var4[var8] != null) {
                  var5[var7++] = var4[var8].getIndex();
               }
            }
         }
      }

      Item[] var13 = Status.getShip().getEquipment(1);
      int[] var14 = null;
      int var9;
      if(var13 != null) {
         var8 = 0;

         int var11;
         for(var11 = 0; var11 < var13.length; ++var11) {
            if(var13[var11] != null) {
               ++var8;
            }
         }

         if(var8 == 0) {
            var14 = null;
         } else {
            var14 = new int[var8];
            var11 = 0;

            for(var9 = 0; var9 < var13.length; ++var9) {
               if(var13[var9] != null) {
                  var14[var11++] = var13[var9].getIndex();
               }
            }
         }
      }

      Item[] var15 = Status.getShip().getEquipment(3);
      int[] var12 = null;
      if(var15 != null) {
         var9 = 0;

         for(var6 = 0; var6 < var15.length; ++var6) {
            if(var15[var6] != null) {
               ++var9;
            }
         }

         if(var9 == 0) {
            var12 = null;
         } else {
            var12 = new int[var9];
            var6 = 0;

            for(var9 = 0; var9 < var15.length; ++var9) {
               if(var15[var9] != null) {
                  var12[var6++] = var15[var9].getIndex();
               }
            }
         }
      }

      return new Class_131(Status.getPlayingTime(), Status.getCredits(), Status.getKills(), Status.getMissionCount(), Status.getLevel(), Status.getStationsVisited(), var1, var2, Status.getGoodsProduced(), Status.getJumpgateUsed(), Status.getCargoSalvaged(), Status.var_1381, -Status.standing.getStanding(0), Status.standing.getStanding(0), -Status.standing.getStanding(1), Status.standing.getStanding(1), Status.passengersCarried, Status.missionGoodsCarried, Status.var_10ba, Status.var_1106, Status.asteroidsDestroyed, Status.alienJunkSalvaged, Status.getShip().getIndex(), var3, var5, var14, var12, Achievements.getMedals());
   }

   static void sub_4a5(Class_1991 var0) {
      var0.sub_2b();
   }

   static boolean sub_519(boolean var0) {
      var_e91 = var0;
      return var0;
   }

   static Popup sub_572(Class_1991 var0) {
      return var0.var_9a8;
   }

   static boolean sub_587(boolean var0) {
      var_a88 = true;
      return true;
   }

   static int sub_5d1(int var0) {
      var_10e6 = var0;
      return var0;
   }

   static String[] sub_61b(Class_1991 var0, String[] var1) {
      return var0.var_1047 = var1;
   }

   static int[] sub_638(int[] var0) {
      var_1088 = var0;
      return var0;
   }

   static float sub_66b(float var0) {
      var_e3b = var0;
      return var0;
   }

   static String sub_6cd(Class_1991 var0) {
      return var0.var_d2e;
   }

   static int sub_6f7(int var0) {
      var_e9f = var0;
      return var0;
   }

   static TextBox sub_725() {
      return var_99b;
   }

   static void sub_75f(Class_1991 var0) {
      var0.sub_2d9();
   }

   static int sub_7bd(int var0) {
      var_ed6 = var0;
      return var0;
   }

   static Class_28f sub_7de(Class_1991 var0, Class_28f var1) {
      return var0.var_bf6 = var1;
   }

   static Class_28f sub_80e(Class_1991 var0) {
      return var0.var_bf6;
   }

   static boolean sub_862() {
      return var_fcc;
   }

   static int sub_892() {
      return var_a4a;
   }

   static boolean sub_8b3(boolean var0) {
      var_fcc = true;
      return true;
   }

   static GameRecord sub_8d3(GameRecord var0) {
      var_a32 = var0;
      return var0;
   }

}
