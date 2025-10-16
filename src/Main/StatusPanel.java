package Main;

import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;
import HardEngine.*;

import AE.AEResourceManager;
import AE.GlobalStatus;
import AE.PaintCanvas.Font;
import AE.PaintCanvas.ImageFactory;
import AE.Time;
import GoF2.Achievements;
import GoF2.Globals;
import GoF2.Layout;
import GoF2.ListItem;
import GoF2.LoadingScreen;
import GoF2.Popup;
import GoF2.Standing;
import GoF2.Status;
import GoF2.TextBox;

public final class StatusPanel {

   private boolean infoOpen;
   private Popup infoMedal;
   private StatusWindow statusWindows;
   private String[] statsLeftColumn;
   private String[] statsRightColumn;
   private Sprite standingBarOutter;
   private Sprite standingBarInner;
   private Sprite standingCursor;
   private Sprite standingLogos;
   private Image allMedalsAdornment;
   private Image allGoldAdornment;
   private int posY;
   private int scrollRows;
   private int posX;
   private int width;
   private int height;
   private int scrollThumbSize;
   private int scrollPos;
   private Image[] face;
   private int var_555;
   private int var_58c;
   private int var_5de;
   private int var_5ef;
   private int var_627;
   private Sprite medalsOnSprite;
   private Sprite flaggenSprite;
   private String[] var_6a7;
   private final short[] stats_description = new short[]{(short)70, (short)80, (short)71, (short)33, (short)158, (short)281, (short)287, (short)282, (short)31, (short)289, (short)288, (short)283, (short)284, (short)32, (short)33};
   private Image var_f3;
   private static int var_5e;
   private static int var_2a;
   private static int var_897;
   private static int var_4d;
   private TextBox var_476;


   public StatusPanel() {
      this.statusWindows = new StatusWindow(new String[]{GlobalStatus.gameText.getText(64), GlobalStatus.gameText.getText(63), Class_1017.sub_2b(58)}, GlobalStatus.gameText.getText(64));
      this.infoOpen = false;
      this.infoMedal = new Popup(20, GlobalStatus.var_eb6 / 3, GlobalStatus.var_e75 - 40);
      this.init();
      this.posY = 35;
      this.posX = 9;
      this.scrollPos = this.posY;
      this.scrollRows = GlobalStatus.var_eb6 - this.posY - 16 - 5;
      this.width = GlobalStatus.var_e75 - 2 * this.posX;
      this.height = 66 + ImageFactory.faceHeight + 4 + 6 * (Font.getFontSpacingY() + 2) + this.statsLeftColumn.length * (Font.getFontSpacingY() + 2);
      this.scrollThumbSize = 0;
	  this.var_f3 = LoadingScreen.getGameLogo();
	  var_5e = 117;
	  var_2a = GlobalStatus.var_e75 - var_5e >> 1;
	  var_4d = this.var_f3.getHeight() + 25;
	  this.var_897 = var_4d + 16;
      if(this.height > this.scrollRows) {
         this.scrollThumbSize = (int)((float)this.scrollRows / (float)this.height * (float)this.scrollRows);
         this.width -= 5;
      }

      this.var_555 = this.posY;
      this.var_627 = GlobalStatus.var_e75 - 2 * this.posX;
      this.var_58c = this.stats_description.length * (22 + 3 * Font.getFontSpacingY());
      this.var_5de = 0;
      if(this.var_58c > this.scrollRows) {
         this.var_5de = (int)((float)this.scrollRows / (float)this.var_58c * (float)this.scrollRows);
         this.var_627 -= 5;
      }

      this.var_5ef = this.var_555;
      this.medalsOnSprite = new Sprite(Globals.medalsOn, Globals.medalsOn.getHeight(), Globals.medalsOn.getHeight());
      Image var2;
      this.flaggenSprite = new Sprite(Globals.flaggen, 16, 11);
      this.standingLogos = new Sprite(Globals.logosSmall, Globals.logosSmall.getHeight(), Globals.logosSmall.getHeight());
      if(GlobalStatus.var_e75 < 240) {
         var2 = AEResourceManager.getImage(68);
      } else {
         var2 =AEResourceManager.getImage(69);
      }

      this.standingBarOutter = new Sprite(var2, var2.getWidth() / 3, var2.getHeight());
      this.standingBarOutter.defineReferencePixel(0, this.standingBarOutter.getHeight());
      if(GlobalStatus.var_e75 < 240) {
         var2 = AEResourceManager.getImage(70);
      } else {
         var2 = AEResourceManager.getImage(71);
      }

      this.standingBarInner = new Sprite(var2);
      this.standingBarInner.defineReferencePixel(0, this.standingBarInner.getHeight());
      if(GlobalStatus.var_e75 < 240) {
         var2 = AEResourceManager.getImage(72);
      } else {
         var2 = AEResourceManager.getImage(73);
      }

      this.standingCursor = new Sprite(var2);
      this.standingCursor.defineReferencePixel(this.standingCursor.getWidth() >> 1, 0);
   }

   public final void OnRelease() {
      if(this.statusWindows != null) {
         this.statusWindows.OnRelease();
      }

      this.statusWindows = null;
      this.infoMedal = null;
      this.statsLeftColumn = null;
      this.statsRightColumn = null;
      this.standingBarOutter = null;
      this.standingBarInner = null;
      this.standingCursor = null;
      this.standingLogos = null;
   }

   public final void init() {
      this.statsLeftColumn = new String[11];
      this.statsRightColumn = new String[11];
      this.statsLeftColumn[0] = GlobalStatus.gameText.getText(77);
      this.statsRightColumn[0] = Status.getShip().getShipName(Status.getShip().getIndex());
      this.statsLeftColumn[1] = GlobalStatus.gameText.getText(290);
      this.statsRightColumn[1] = "" + Status.getShip().sub_4ab();
      this.statsLeftColumn[2] = GlobalStatus.gameText.getText(291);
      this.statsRightColumn[2] = "" + Status.getShip().getCombinedHP();
      this.statsLeftColumn[3] = GlobalStatus.gameText.getText(33);
      this.statsRightColumn[3] = "" + Status.getMissionCount();
      this.statsLeftColumn[4] = GlobalStatus.gameText.getText(71);
      this.statsRightColumn[4] = "" + Status.getKills();
      this.statsLeftColumn[5] = GlobalStatus.gameText.getText(282);
      this.statsRightColumn[5] = "" + Status.getCargoSalvaged();
      this.statsLeftColumn[6] = GlobalStatus.gameText.getText(280);
      this.statsRightColumn[6] = "" + Status.getStationsVisited();
      this.statsLeftColumn[7] = GlobalStatus.gameText.getText(287);
      this.statsRightColumn[7] = "" + Status.getJumpgateUsed();
      this.statsLeftColumn[8] = GlobalStatus.gameText.getText(281);
      this.statsRightColumn[8] = "" + Status.getGoodsProduced();
      this.statsLeftColumn[9] = GlobalStatus.gameText.getText(283);
      this.statsRightColumn[9] = "" + Status.var_10ba;
      this.statsLeftColumn[10] = GlobalStatus.gameText.getText(284);
      this.statsRightColumn[10] = "" + Status.var_1106;
   }

   public final boolean handleKeyPress(int var1) {
      if((var1 == 256 || var1 == 16384) && this.statusWindows.getCurrentTab() == 1 && !this.infoOpen) {
         ListItem var4 = (ListItem)((ListItem)this.statusWindows.getSelectedItem());
         if(Achievements.getMedals()[var4.itemId] != 0) {
            String var2 = Status.replaceToken(GlobalStatus.gameText.getText(782 + var4.itemId), Achievements.VALUES[var4.itemId][var4.medalTier - 1] + "");
            if(var4.itemId == 2 && var4.medalTier == 2) {
               var2 = var2 + "\n\n" + GlobalStatus.gameText.getText(134);

               for(var1 = 0; var1 < Status.var_1053.length; ++var1) {
                  if(!Status.var_1053[var1]) {
                     var2 = var2 + "\n- " + GlobalStatus.gameText.getText(var1 + 723);
                  }
               }
            } else if(var4.itemId == 3 && var4.medalTier == 2) {
               var2 = var2 + "\n\n" + GlobalStatus.gameText.getText(134);

               for(var1 = 0; var1 < Status.var_1071.length; ++var1) {
                  if(!Status.var_1071[var1]) {
                     var2 = var2 + "\n- " + GlobalStatus.gameText.getText(var1 + 734);
                  }
               }
            } else if(var4.itemId == 9 && var4.medalTier == 2) {
               var2 = var2 + "\n\n" + GlobalStatus.gameText.getText(134);

               for(var1 = 0; var1 < Status.drinkTypesPossesed.length; ++var1) {
                  if(!Status.drinkTypesPossesed[var1]) {
                     var2 = var2 + "\n- " + GlobalStatus.gameText.getText(var1 + 701);
                  }
               }
            } else if(var4.itemId == 13 && var4.medalTier == 2) {
               var2 = var2 + "\n\n" + GlobalStatus.gameText.getText(134);

               for(var1 = 0; var1 < Status.var_8eb.length; ++var1) {
                  if(!Status.var_8eb[var1].unlocked) {
                     var2 = var2 + "\n- " + GlobalStatus.gameText.getNamedParameterItems(Status.var_8eb[var1].getIndex());
                  }
               }
            } else if(var4.itemId == 14 && var4.medalTier == 2) {
               var2 = var2 + "\n\n" + GlobalStatus.gameText.getText(134);

               for(var1 = 0; var1 < Status.var_8eb.length; ++var1) {
                  if(Status.var_8eb[var1].timesProduced == 0) {
                     var2 = var2 + "\n- " + GlobalStatus.gameText.getNamedParameterItems(Status.var_8eb[var1].getIndex());
                  }
               }
            }

            Popup var10001 = this.infoMedal;
            this.infoMedal.set(var2, false);
            this.infoOpen = true;
         }

         return true;
      } else if(!this.infoOpen) {
         if(this.statusWindows.selectedTab == 2 && var1 == 16384) {
            if(!GlobalStatus.var_10e5) {
               Popup var10000 = this.infoMedal;
               String var3 = GlobalStatus.gameText.getText(257);
               var10000.set(var3, false);
               this.infoOpen = true;
            } else {
               ModStation.var_b7 = true;
            }
         }

         if(var1 == 4) {
            this.statusWindows.leftAction();
         }

         if(var1 == 32) {
            this.statusWindows.rightAction();
         }

         if(this.statusWindows.selectedTab == 1) {
            if(var1 == 2) {
               this.statusWindows.scrollUp();
            }

            if(var1 == 64) {
               this.statusWindows.scrollDown();
            }
         }
		 
		 if(this.statusWindows.selectedTab == 2) {
			 
			 if(var1 == 2) {
			//	this.var_476.sub_3d8(0);
			 }

            if(var1 == 64) {
			//   this.var_476.sub_374(0);
            }
			 
		 }

         return var1 != 8192;
      } else {
         if((this.statusWindows.selectedTab == 1 || this.statusWindows.selectedTab == 2) && var1 == 256) {
            this.infoOpen = false;
         }

         return true;
      }
   }

   private void drawSubHeader(int var1, String var2) {
      int var3 = this.width;
      if(this.statusWindows.selectedTab == 2) {
         var3 = this.var_627;
      }

      GlobalStatus.graphics.setColor(Layout.uiOuterDownLeftOutlineInnerLabalBgColor);
      GlobalStatus.graphics.fillRect(this.posX, var1, var3, 17);
      GlobalStatus.graphics.setColor(0);
      GlobalStatus.graphics.drawRect(this.posX + 1, var1 + 1, var3 - 3, 16);
      GlobalStatus.graphics.setColor(Layout.uiInnerOutlineColor);
      GlobalStatus.graphics.drawRect(this.posX, var1, var3 - 1, 18);
      Layout.sub_449(this.posX, var1, false);
      Font.drawString(var2, this.posX + 6, var1 + 4, 0);
   }

   private static long sub_183(int var0) {
      switch(var0) {
      case 0:
         return Status.getPlayingTime();
      case 1:
         return (long)Status.getCredits();
      case 2:
         return (long)Status.getKills();
      case 3:
         return (long)Status.getMissionCount();
      case 4:
         return (long)Status.getLevel();
      case 5:
         return (long)Status.getGoodsProduced();
      case 6:
         return (long)Status.getJumpgateUsed();
      case 7:
         return (long)Status.getCargoSalvaged();
      case 8:
         return (long)Status.var_1381;
      case 9:
         return (long)Status.passengersCarried;
      case 10:
         return (long)Status.missionGoodsCarried;
      case 11:
         return (long)Status.var_10ba;
      case 12:
         return (long)Status.var_1106;
      case 13:
         return (long)Status.asteroidsDestroyed;
      case 14:
         return (long)Status.alienJunkSalvaged;
      default:
         return 0L;
      }
   }

   private String sub_18d(int var1) {
      return var1 != 8 && var1 != 13 && var1 != 14?GlobalStatus.gameText.getText(this.stats_description[var1]):Class_1017.sub_2b(this.stats_description[var1]);
   }

   private void sub_1a0() {
      if(Class_1991.var_6bc != null && Class_1991.var_19d != null && Class_1991.var_350 != null && Class_1991.var_679 != null) {
         int var1 = Font.getFontSpacingY();
         GlobalStatus.graphics.setColor(0);
         GlobalStatus.graphics.setClip(0, this.var_555, GlobalStatus.var_e75, this.scrollRows);
         int var2 = this.var_5ef;
         String var3 = "     ";

         for(int var4 = 0; var4 < this.stats_description.length; ++var4) {
            boolean var5 = Class_1991.var_6bc[var4] == 1;
            if(Class_1991.var_6bc[var4] <= 3) {
               this.drawSubHeader(var2, var3 + this.sub_18d(var4));
               this.medalsOnSprite.setFrame(Class_1991.var_6bc[var4] - 1);
               this.medalsOnSprite.setPosition(15, var2 + 2);
               this.medalsOnSprite.paint(GlobalStatus.graphics);
            } else {
               this.drawSubHeader(var2, this.sub_18d(var4));
            }

            var2 += 22;
            int var6;
            byte var7;
            String var8;
            if(!var5) {
               int var10001;
               if((var6 = Class_1991.var_679[var4]) >= 0 && var6 < Class_1991.var_24.length) {
                  if((var7 = Class_1991.var_24[var6]) >= 0) {
                     this.flaggenSprite.setFrame(var7);
                  } else {
                     this.flaggenSprite.setFrame(this.flaggenSprite.getRawFrameCount() - 1);
                  }

                  var10001 = this.posX + 2;
                  var8 = "1. ";
                  this.flaggenSprite.setPosition(var10001 + Font.getTextWidth("1. ", 0), var2);
                  this.flaggenSprite.paint(GlobalStatus.graphics);
               } else {
                  this.flaggenSprite.setFrame(this.flaggenSprite.getRawFrameCount() - 1);
                  var10001 = this.posX + 2;
                  var8 = "1. ";
                  this.flaggenSprite.setPosition(var10001 + Font.getTextWidth("1. ", 0), var2);
                  this.flaggenSprite.paint(GlobalStatus.graphics);
               }

               var8 = "" + Class_1991.var_350[var4];
               if(var4 == 1) {
                  var8 = Layout.formatCredits(Class_1991.var_350[var4]);
               } else if(var4 == 0) {
                  var8 = Time.timeToHM((long)(Class_1991.var_350[var4] * 1000));
               }

               Font.drawString("1. " + var3 + Class_1991.var_19d[var4] + " (" + var8 + ")", this.posX + 2, var2, 1, 17);
               var2 += var1;
            }

            var6 = Class_1991.var_7b9;
            if(Class_1991.var_7b9 >= 0 && var6 < Class_1991.var_24.length) {
               if((var7 = Class_1991.var_24[var6]) >= 0) {
                  this.flaggenSprite.setFrame(var7);
               } else {
                  this.flaggenSprite.setFrame(this.flaggenSprite.getRawFrameCount() - 1);
               }

               this.flaggenSprite.setPosition(this.posX + 2 + Font.getTextWidth(Class_1991.var_6bc[var4] + ". ", 0), var2);
               this.flaggenSprite.paint(GlobalStatus.graphics);
            } else {
               this.flaggenSprite.setFrame(this.flaggenSprite.getRawFrameCount() - 1);
               this.flaggenSprite.setPosition(this.posX + 2 + Font.getTextWidth(Class_1991.var_6bc[var4] + ". ", 0), var2);
               this.flaggenSprite.paint(GlobalStatus.graphics);
            }

            var8 = "" + sub_183(var4);
            if(var4 == 1) {
               var8 = Layout.formatCredits((int)sub_183(var4));
            } else if(var4 == 0) {
               var8 = Time.timeToHM(sub_183(var4));
            }

            Font.drawString(Class_1991.var_6bc[var4] + ". " + var3 + Class_1991.var_71d + " (" + var8 + ")", this.posX + 2, var2, 2, 17);
            var2 += var1 * 2;
         }

         GlobalStatus.graphics.setClip(0, 0, GlobalStatus.var_e75, GlobalStatus.var_eb6);
         this.sub_1ac();
      } else {
         if(this.var_6a7 == null) {
           // this.var_6a7 = Class_ba6.sub_550(Class_1017.sub_2b(55), SharedVariables.var_e75 - 20); // данные во вкладке "обновления".
			this.var_476 = new TextBox(var_2a / 2 + 8, this.var_897, var_5e + var_2a - 16, GlobalStatus.var_eb6 - var_4d - 48, Class_1017.sub_2b(55));
			this.var_476.draw();
         }

       //  Class_ba6.sub_1d6(this.var_6a7, 10, 40, 1);
      }
   }

   private void sub_1ac() {
      int var1 = this.width;
      int var2 = this.height;
      int var3 = this.scrollPos;
      int var4 = this.scrollThumbSize;
      if(this.statusWindows.selectedTab == 2) {
         var1 = this.var_627;
         var2 = this.var_58c;
         var3 = this.var_5ef;
         var4 = this.var_5de;
      }

      if(var4 > 0) {
         var2 = (int)((float)(this.posY - var3) / (float)(var2 - this.scrollRows) * (float)(this.scrollRows - var4));
         GlobalStatus.graphics.setColor(Layout.uiInnerOutlineColor);
         GlobalStatus.graphics.drawLine(this.posX + var1 + 3, this.posY, this.posX + var1 + 3, this.posY + this.scrollRows - 1);
         GlobalStatus.graphics.setColor(0, 80, 135);
         GlobalStatus.graphics.fillRect(this.posX + var1 + 2, this.posY + var2, 3, var4);
         GlobalStatus.graphics.setColor(0, 90, 155);
         GlobalStatus.graphics.drawLine(this.posX + var1 + 2, this.posY + 1 + var2, this.posX + var1 + 2, this.posY + var4 - 2 + var2);
         GlobalStatus.graphics.drawLine(this.posX + var1 + 2, this.posY + var4 - 1 + var2, this.posX + var1 + 3, this.posY + var4 - 1 + var2);
         GlobalStatus.graphics.setColor(0, 128, 255);
         GlobalStatus.graphics.drawLine(this.posX + var1 + 3, this.posY + 1 + var2, this.posX + var1 + 3, this.posY + var4 - 2 + var2);
         GlobalStatus.graphics.setColor(Layout.uiInnerOutlineColor);
      }

   }

   public final void draw(int var1, int var2) {
      Layout.drawBG();
      this.statusWindows.draw();
      if(this.statusWindows.selectedTab == 0) {
         if(this.scrollThumbSize > 0) {
            if((var1 & 2) != 0) {
               this.scrollPos += var2 / 8;
               if(this.scrollPos > this.posY) {
                  this.scrollPos = this.posY;
               }
            }

            if((var1 & 64) != 0) {
               this.scrollPos -= var2 / 8;
               if(this.scrollPos + this.height < this.posY + this.scrollRows) {
                  this.scrollPos = this.posY + this.scrollRows - this.height;
               }
            }
         }

         StatusPanel var7 = this;
         var2 = Font.getFontSpacingY();
         GlobalStatus.graphics.setColor(0);
         GlobalStatus.graphics.setClip(0, this.posY, GlobalStatus.var_e75, this.scrollRows);
         int var3 = this.scrollPos;
         this.drawSubHeader(var3, GlobalStatus.gameText.getText(819));
         var3 += 22;
         if(this.face == null) {
            this.face = ImageFactory.faceFromByteArray(Globals.CHAR_KEITH);
         }

         ImageFactory.drawChar(this.face, this.posX, var3, 20);
         if(Achievements.gotAllGoldMedals()) {
            if(this.allGoldAdornment == null) {
               this.allGoldAdornment = AEResourceManager.getImage(74);
            }

            GlobalStatus.graphics.drawImage(this.allGoldAdornment, this.posX + 1, var3 + 1, 20);
         } else if(Achievements.gotAllMedals()) {
            if(this.allMedalsAdornment == null) {
               this.allMedalsAdornment = AEResourceManager.getImage(75);
            }

            GlobalStatus.graphics.drawImage(this.allMedalsAdornment, this.posX + 1, var3 + 1, 20);
         }

         Font.drawString(GlobalStatus.gameText.getText(80), this.posX + ImageFactory.faceWidth + 4, var3, 1, 17);
         Font.drawString(Layout.formatCredits(Status.getCredits()), this.posX + this.width, var3, 6, 18);
         var3 += var2 + 2;
         Font.drawString(GlobalStatus.gameText.getText(158), this.posX + ImageFactory.faceWidth + 4, var3, 1, 17);
         Font.drawString(Status.getLevel() + "", this.posX + this.width, var3, 1, 18);
         var3 += var2 + 2;
         Font.drawString(GlobalStatus.gameText.getText(70), this.posX + ImageFactory.faceWidth + 4, var3, 1, 17);
         Font.drawString(Time.timeToHMS(Status.getPlayingTime()), this.posX + this.width, var3, 1, 18);
         var3 = this.scrollPos + 18 + 4 + ImageFactory.faceHeight + 4;
         this.drawSubHeader(var3, GlobalStatus.gameText.getText(298));
         var3 += 26;
         Standing var8 = Status.getStanding();

         int var5;
         for(var5 = 0; var5 < 2; ++var5) {
            int var6 = var7.posX + 6;
            var7.standingLogos.setFrame(var5 == 0?0:2);
            var7.standingLogos.setPosition(var6, var3);
            var7.standingLogos.paint(GlobalStatus.graphics);
            var6 += var7.standingLogos.getHeight() + 4;
            Font.drawString(GlobalStatus.gameText.getText(var5 == 0?229:231), var6, var3 + Font.getFontSpacingY() + var7.standingCursor.getHeight(), 1);
            var7.standingBarOutter.setTransform(0);
            var7.standingBarOutter.setFrame(var8.isEnemy(var5 == 0?0:2)?0:(var8.isFriend(var5 == 0?0:2)?1:2));
            var7.standingBarOutter.setRefPixelPosition(var6, var3 + var7.standingBarOutter.getHeight());
            var7.standingBarOutter.paint(GlobalStatus.graphics);
            var6 += var7.standingBarOutter.getWidth() + 1;
            var7.standingBarInner.setTransform(0);
            var7.standingBarInner.setRefPixelPosition(var6, var3 + var7.standingBarOutter.getHeight());
            var7.standingBarInner.paint(GlobalStatus.graphics);
            var6 = var7.posX + var7.width - 2;
            var7.standingLogos.setFrame(var5 == 0?1:3);
            var7.standingLogos.setPosition(var6 - var7.standingLogos.getHeight(), var3);
            var7.standingLogos.paint(GlobalStatus.graphics);
            var6 -= 4 + var7.standingLogos.getHeight();
            Font.drawString(GlobalStatus.gameText.getText(var5 == 0?230:232), var6, var3 + Font.getFontSpacingY() + var7.standingCursor.getHeight(), 1, 18);
            var7.standingBarOutter.setTransform(2);
            var7.standingBarOutter.setFrame(var8.isEnemy(var5 == 0?1:3)?0:(var8.isFriend(var5 == 0?1:3)?1:2));
            var7.standingBarOutter.setRefPixelPosition(var6, var3 + var7.standingBarOutter.getHeight());
            var7.standingBarOutter.paint(GlobalStatus.graphics);
            var6 -= var7.standingBarOutter.getWidth() + 1;
            var7.standingBarInner.setTransform(2);
            var7.standingBarInner.setRefPixelPosition(var6, var3 + var7.standingBarOutter.getHeight());
            var7.standingBarInner.paint(GlobalStatus.graphics);
            var6 = (GlobalStatus.var_e75 >> 1) - (int)((float)var8.getStanding(var5 == 0?0:1) / 100.0F * (float)(var7.standingBarOutter.getWidth() + 1 + var7.standingBarInner.getWidth()));
            var7.standingCursor.setRefPixelPosition(var6, var3 + var7.standingBarOutter.getHeight());
            var7.standingCursor.paint(GlobalStatus.graphics);
            var3 += 3 * Font.getFontSpacingY() + 4;
         }

         var7.drawSubHeader(var3, GlobalStatus.gameText.getText(299));
         var3 += 22;

         for(var5 = 0; var5 < var7.statsLeftColumn.length; ++var5) {
            Font.drawString(var7.statsLeftColumn[var5], var7.posX + 2, var3, 1, 17);
            Font.drawString(var7.statsRightColumn[var5], var7.posX + var7.width, var3, 0, 18);
            var3 += var2 + 2;
         }

         GlobalStatus.graphics.setClip(0, 0, GlobalStatus.var_e75, GlobalStatus.var_eb6);
         var7.sub_1ac();
      } else if(this.statusWindows.selectedTab == 2) {
         if(this.var_5de > 0) {
            if((var1 & 2) != 0) {
				this.var_476.scrollDown(var1);
				this.var_5ef += var2 / 8;
				if(this.var_5ef > this.var_555) {
					this.var_5ef = this.var_555;
				}
			}

            if((var1 & 64) != 0) {
				this.var_476.scrollUp(var2);
				this.var_5ef -= var2 / 8;
				if(this.var_5ef + this.var_58c < this.var_555 + this.scrollRows) {
					this.var_5ef = this.var_555 + this.scrollRows - this.var_58c;
				}
			}
         }

         this.sub_1a0();
      }

      if(this.statusWindows.getCurrentTab() == 1) {
         Layout.drawFooter(((ListItem)this.statusWindows.getSelectedItem()).medalTier > 0?GlobalStatus.gameText.getText(212):"", GlobalStatus.gameText.getText(65));
      } else if(this.statusWindows.getCurrentTab() == 2) {
         Layout.drawFooter(Class_1017.sub_2b(61), GlobalStatus.gameText.getText(65));
      } else {
         Layout.drawFooter("", GlobalStatus.gameText.getText(65));
      }

      if(this.infoOpen) {
         this.infoMedal.draw();
      }

   }
}
