package Main;

import javax.microedition.lcdui.Image;
import HardEngine.*;

import AE.AEResourceManager;
import AE.GlobalStatus;
import AE.IApplicationModule;
import AE.Math.AEMath;
import AE.PaintCanvas.Font;
import GoF2.Achievements;
import GoF2.Dialogue;
import GoF2.Generator;
import GoF2.Globals;
import GoF2.Item;
import GoF2.Layout;
import GoF2.Level;
import GoF2.Mission;
import GoF2.Popup;
import GoF2.ProducedGood;
import GoF2.RecordHandler;
import GoF2.Standing;
import GoF2.Status;

public final class ModStation extends IApplicationModule {

   private Class_1991 var_61;
   public static boolean var_80;
   public static boolean var_b7;
   private long currentTime;
   private long lastTime;
   private long frameTime;
   private long barTick;
   private long loadingTick;
   private int selectedMenuItem;
   private int[] menuItemHighlights;
   private Image factionLogo;
   private CutScene cutScene;
   private OptionsWindow optionsWindow;
   private boolean menuOpen;
   private boolean optionsOpen;
   private boolean popupOpen;
   private boolean missionsOpen;
   private boolean barOpen;
   private boolean hangarOpen;
   private boolean mapOpen;
   private boolean statusOpen;
   private boolean missionMsgOpen;
   private boolean medalMsgOpen;
   private boolean helpMsgOpen;
   private boolean bribeMsgOpen;
   private Popup popup;
   private SpaceLounge spaceLounge;
   private HangarWindow hangarWindow;
   private StatusPanel statusPanel;
   private MissionsWindow missionsWindow;
   private Dialogue dialogue;
   private Dialogue help;
   private NewMedalPopup medalPopup;
   public StarMap starMap;
   private Mission activeMission__;
   private int menuPosX;
   private int menuPosY;
   private int menuWidth;
   private boolean loadedFromSave = false;
   private boolean autoSaved = false;
   private boolean leaveStationWarnOpen;
   private int[][] newMedals;
   private int newMedalCollected;
   private int unused_b98;
   private int boozeOwnedAtArrival;
   private int bribe;
   private boolean paidBribe;
   private boolean medalsChecked;
   private boolean goodsCollected;
   private boolean loaded;
   private int var_2c0;
   private static AEButtonManager[] AEButtonHangarMenu;
   private static boolean isDialogueWindowDisplayed = false;


   public final CutScene getCutScene() {
      return this.cutScene;
   }

   public final void OnRelease() {
      if(this.hangarWindow != null) {
         this.hangarWindow.OnRelease();
      }

      this.hangarWindow = null;
      this.menuItemHighlights = null;
      if(this.optionsWindow != null) {
         this.optionsWindow.OnRelease();
      }

      this.optionsWindow = null;
      if(this.spaceLounge != null) {
         this.spaceLounge.OnRelease();
      }

      this.spaceLounge = null;
      this.popup = null;
      if(this.cutScene != null) {
         this.cutScene.OnRelease();
      }

      this.cutScene = null;
      if(this.starMap != null) {
         this.starMap.OnRelease();
      }

      this.starMap = null;
      if(this.statusPanel != null) {
         this.statusPanel.OnRelease();
      }

      this.statusPanel = null;
      this.loaded = false;
      this.factionLogo = null;
	  AEButtonHangarMenu = null;
      System.gc();
   }

   public final void fromGameSave() {
      this.loadedFromSave = true;
   }

   public final void OnInitialize() {
      if(this.cutScene == null) {
         this.cutScene = new CutScene(23);
      }

      if(!this.cutScene.isLoaded()) {
         this.cutScene.OnInitialize();
      } else {
         this.unused_b98 = 50;
         long var1 = Status.var_e01;
         Status.departStation(Status.getStation());
         Item[] var2;
         int var3;
         String[] var5;
         int var6;
         if(Status.getPlayingTime() - var1 > 30000L) {
            new Generator();
            var5 = null;
            if((var2 = Status.getStation().sub_360()) != null) {
               for(var3 = 0; var3 < var2.length; ++var3) {
                  var6 = var2[var3].getAmount();
                  int var4;
                  if((var4 = GlobalStatus.random.nextInt(3)) < var6) {
                     var2[var3].changeAmount(-var4);
                  }
               }
            }
         }

         Status.getStation().visit();
         Achievements.applyNewMedals();
         this.popupOpen = false;
         this.optionsOpen = false;
         this.missionsOpen = false;
         this.barOpen = false;
         this.hangarOpen = false;
         this.mapOpen = false;
         this.menuOpen = true;
         this.statusOpen = false;
         this.bribeMsgOpen = false;
         this.helpMsgOpen = false;
         this.menuItemHighlights = new int[6];

         for(var6 = 0; var6 < this.menuItemHighlights.length; ++var6) {
            this.menuItemHighlights[var6] = 0;
            if(var6 == this.selectedMenuItem) {
               this.menuItemHighlights[var6] = 1;
            }
         }

         this.menuPosX = 1;
         this.menuPosY = GlobalStatus.var_eb6 - 37 - this.menuItemHighlights.length * 10;
         (var5 = new String[6])[0] = GlobalStatus.gameText.getText(62);
         var5[1] = GlobalStatus.gameText.getText(218);
         var5[2] = GlobalStatus.gameText.getText(72);
         var5[3] = GlobalStatus.gameText.getText(33);
         var5[4] = GlobalStatus.gameText.getText(64);
         var5[5] = GlobalStatus.gameText.getText(66);

         for(int var7 = 0; var7 < var5.length; ++var7) {
            if((var3 = Font.getTextWidth(var5[var7], 1)) > this.menuWidth) {
               this.menuWidth = var3 + 20;
            }
         }

         this.boozeOwnedAtArrival = 0;
         this.bribe = 0;
         this.paidBribe = false;
         this.medalsChecked = false;
         this.goodsCollected = false;
         this.autoSaved = false;
         this.leaveStationWarnOpen = false;
         this.loadingTick = 0L;
         this.popup = new Popup();
         this.factionLogo = AEResourceManager.getImage(14 + Status.getSystem().getRace());
		 AEButtonHangarMenu = new AEButtonManager[6];
		 for(int buttons = 0; buttons < AEButtonHangarMenu.length; ++buttons) {
			 AEButtonHangarMenu[buttons] = new AEButtonManager();
		 }
         if(!this.loadedFromSave) {
            if(!this.medalMsgOpen && this.help == null && !this.paidBribe && Status.getStanding().isEnemy(Status.getSystem().getRace())) {
               Standing var8 = Status.getStanding();
               var6 = (var3 = Status.getSystem().getRace()) != 1 && var3 != 0?1:0;
               this.bribe = (int)((float)AEMath.abs(var8.getStanding(var6)) / 100.0F * 2800.0F);
               this.bribe += -100 + GlobalStatus.random.nextInt(200);
               String var9 = Status.replaceTokens(GlobalStatus.gameText.getText(85), "" + this.bribe, "#C");
               this.popup.set(var9, true);
               this.bribeMsgOpen = true;
               this.popupOpen = true;
            }

            if(Status.getCurrentCampaignMission() == 20 && Status.getCampaignMission().getTargetStation() == Status.getStation().getId()) {
               var2 = Status.getStation().sub_360();

               for(var3 = 0; var3 < var2.length; ++var3) {
                  if(var2[var3].getIndex() == 41) {
                     var2[var3].setPrice(0);
                  }
               }

               Status.getStation().addItem(Globals.getItems()[41].getCopyInAmmount(10, 0)); // �������� � ����� Kappa EM GL1
            }

            if(Status.getCurrentCampaignMission() == 27 && Status.getCampaignMission().getTargetStation() == Status.getStation().getId()) {
               Status.getShip().removeCargo(131);
            }
         }

         if(!this.autoSaved && !this.loadedFromSave) {
            (new RecordHandler()).saveGame(3);
            this.autoSaved = true;
         }

         Level.programmedStation = null;
         if(Status.getCurrentCampaignMission() == 1) {
            Status.setShip(Globals.getShips()[GlobalStatus.newgame_ship]); // �������, ������� ����� ������� ����� ��������
            Status.getShip().setRace(8);
            Item var10;
            (var10 = Globals.getItems()[90].makeItem()).setUnsaleable(true); // ���������� ���. Boolean - ������ �� �������� �� ����� �������?
            Status.getShip().setEquipment(var10, 0);
            Item var13 = Globals.getItems()[81].makeItem(); // ������
            Status.getShip().setEquipment(var13, 1);
            var13.setUnsaleable(true);
            this.cutScene.replacePlayerShip(Status.getShip().getIndex(), 8);
         }

         GlobalStatus.soundManager.playMusic(1);
         this.barTick = 0L;
         if(!GlobalStatus.var_10e5 && Class_12ea.sub_9f() >= 15) {
            RecordHandler var11 = new RecordHandler();
            this.var_61 = new Class_1991();
            if(var11.sub_45d(1) && var11.sub_45d(0)) {
               this.var_61.sub_160(7);
            } else if(var11.sub_45d(2)) {
               if(var11.sub_45d(6)) {
                  this.var_61.sub_160(6);
                  this.var_61.sub_3be(true);
                  this.var_61.sub_415();
               } else {
                  this.var_61.sub_160(3);
               }
            } else {
               this.var_61.sub_160(0);
            }
         }

         var_80 = false;
         var_b7 = false;
         int[] var12;
         if((var12 = (new RecordHandler()).sub_3dc(5)) != null && var12.length > 0) {
            GlobalStatus.var_1083 = var12[0] == 1;
         } else {
            GlobalStatus.var_1083 = false;
         }

         System.gc();
         this.currentTime = System.currentTimeMillis();
         this.lastTime = System.currentTimeMillis();
         this.loaded = true;
      }
   }

   private void checkMedals_() {
      if(!this.medalMsgOpen && !this.helpMsgOpen) {
         this.newMedals = null;
         int[] var1 = Achievements.getNewMedals();
         int var2 = 0;

         int var3;
         for(var3 = 0; var3 < var1.length; ++var3) {
            if(var1[var3] > 0) {
               ++var2;
            }
         }

         if(var2 > 0) {
            this.newMedals = new int[var2][2];
            var2 = 0;

            for(var3 = 0; var3 < var1.length; ++var3) {
               if(var1[var3] > 0) {
                  this.newMedals[var2++] = new int[]{var3, var1[var3]};
               }
            }

            this.medalMsgOpen = true;
            this.newMedalCollected = 0;
            this.medalPopup = new NewMedalPopup();
            this.medalPopup.set(this.newMedals[0][0], this.newMedals[0][1]);
         }

      } else {
         ++this.newMedalCollected;
         if(this.newMedalCollected >= this.newMedals.length) {
            this.medalMsgOpen = false;
         } else {
            this.medalPopup.set(this.newMedals[this.newMedalCollected][0], this.newMedals[this.newMedalCollected][1]);
         }
      }
   }

   public final boolean isLoaded() {
      return this.loaded;
   }

   public final void renderScene(int var1) {
      if(this.loaded) {
         Layout.setTickHighlight(false);
         this.currentTime = System.currentTimeMillis();
         this.frameTime = this.currentTime - this.lastTime;
         this.lastTime = this.currentTime;
         this.barTick += this.frameTime;
         this.loadingTick += this.frameTime;
         if(this.var_61 != null) {
            this.var_61.sub_3a(var1, (int)this.frameTime);
         } else {
            if(var_80) {
               this.var_61 = new Class_1991();
               this.var_61.sub_160(9);
               var_80 = false;
            } else if(var_b7) {
               this.var_61 = new Class_1991();
               this.var_61.sub_160(19);
               var_b7 = false;
            }

            if(this.optionsWindow != null && this.optionsWindow.sub_10a() && this.optionsOpen) {
               this.optionsWindow.scrollAndTick_(var1, (int)this.frameTime);
               this.optionsWindow.draw();
            } else {
               Status.incPlayingTime(this.frameTime);
               Achievements.updateMaxCredits(Status.getCredits());
               if(!this.missionsOpen && !this.barOpen && !this.hangarOpen && !this.mapOpen && !this.statusOpen && this.cutScene != null) {
                  this.cutScene.renderScene(!this.optionsOpen && !this.bribeMsgOpen && !this.helpMsgOpen && !this.popupOpen?var1:0);
               }

               if(this.loadingTick < 6000L) {
                  Font.drawString(GlobalStatus.gameText.getText(this.loadedFromSave?32:28), 5, GlobalStatus.var_eb6 - 16 - Font.getFontSpacingY(), 1);
               } else {
                  this.loadedFromSave = false;
               }

               if(this.loadingTick >= 6000L) {
                  if(!this.popupOpen && !this.missionMsgOpen) {
                     if(!this.goodsCollected) {
                        ModStation var2 = this;
                        String var3 = GlobalStatus.gameText.getText(92) + "\n";
                        ProducedGood[] var4;
                        if((var4 = Status.getWaitingGoods()) != null) {
                           for(int var5 = 0; var5 < var4.length; ++var5) {
                              ProducedGood var6;
                              if((var6 = var4[var5]) != null && var6.stationId == Status.getStation().getId()) {
                                 Item var9 = Globals.getItems()[var6.index].makeItem(var6.producedQuantity);
                                 Status.getShip().addCargo(var9);
                                 var3 = var3 + "\n" + var9.getAmount() + "t " + GlobalStatus.gameText.getNamedParameterItems(var9.getIndex());
                                 var4[var5] = null;
                                 var2.popupOpen = true;
                              }
                           }
                        }

                        if(var2.popupOpen) {
                           Popup var10001 = var2.popup;
                           var2.popup.set(var3, false);
                        }

                        this.goodsCollected = true;
                     }

                     if(!this.medalsChecked) {
                        this.checkMedals_();
                        this.medalsChecked = true;
                     }

                     if(!this.medalMsgOpen && this.help == null && !GlobalStatus.fanWingmenNoticeShown && Achievements.gotAllMedals()) {
                        this.help = new Dialogue(GlobalStatus.gameText.getText(325));
                        GlobalStatus.fanWingmenNoticeShown = true;
                        this.helpMsgOpen = true;
                     }

                     if(!this.medalMsgOpen && this.help == null && !GlobalStatus.voidxNoticeShown && Achievements.gotAllGoldMedals()) {
                        this.help = new Dialogue(GlobalStatus.gameText.getText(326));
                        GlobalStatus.voidxNoticeShown = true;
                        this.helpMsgOpen = true;
                     }
                  }

                  if(!this.popupOpen && !this.missionMsgOpen) {
                     Mission var7;
                     if((var7 = Status.missionCompleted_(true, 0L)) != null) {
                        this.activeMission__ = var7;
                     }

                     if(var7 != null) {
                        this.dialogue = new Dialogue(var7, (Level)null, 1);
                        this.missionMsgOpen = true;
                     }
                  }

                  int var8 = var1;
                  if(this.helpMsgOpen) {
                     var8 = 0;
                  }

                  if(this.missionMsgOpen) {
                     if(this.hangarOpen || this.barOpen) {
                        Layout.drawBG();
                     }

                     this.dialogue.handleScrollPress_(var8, (int)this.frameTime);
                     this.dialogue.draw();
                  } else if(this.optionsOpen) {
                     this.optionsWindow.scrollAndTick_(var8, (int)this.frameTime);
                     this.optionsWindow.draw();
                  } else if(this.missionsOpen) {
                     Layout.addTicks((int)this.frameTime);
                     this.missionsWindow.draw(var8, (int)this.frameTime);
                  } else if(this.barOpen) {
                     Layout.addTicks((int)this.frameTime);
                     this.spaceLounge.draw(var8, (int)this.frameTime);
                  } else if(this.hangarOpen) {
                     Layout.addTicks((int)this.frameTime);
                     this.hangarWindow.draw(var8, (int)this.frameTime);
                  } else if(this.mapOpen) {
                     this.starMap.update(var8, (int)this.frameTime);
                     if(this.starMap != null && this.starMap.scopedOnSystem() && this.help == null && !GlobalStatus.systemMapHelpShown) {
                        this.help = new Dialogue(GlobalStatus.gameText.getText(316));
                        GlobalStatus.systemMapHelpShown = true;
                        this.helpMsgOpen = true;
                     }
                  } else if(this.statusOpen) {
                     Layout.addTicks((int)this.frameTime);
                     this.statusPanel.draw(var8, (int)this.frameTime);
                  } else {
                     Layout.addTicks((int)this.frameTime);
					 boolean bool;
					 this.var_2c0 = Status.getShip().getCurrentLoad();
					 // Class_ba6.sub_14d(Status.ship_class().sub_534() + "/" + Status.ship_class().sub_506() + " t", 500, 20, 6);
                     if(this.menuOpen) { // ���� �������� � ������
                        Layout.drawMenuWindow("", 0, 0, AEButtonHangarMenu[0].standartButtonWidth + AEButtonHangarMenu[0].standartButtonWidth / 4, GlobalStatus.var_eb6);
						AEButtonHangarMenu[0].drawStandartButton(Globals.rectRoundedButtonNormal, Globals.rectRoundedButtonPressed, (AEButtonHangarMenu[0].standartButtonWidth / 2) + ((this.menuWidth + 50) / 8), GlobalStatus.var_eb6 - (AEButtonHangarMenu[0].standartButtonHeight * (AEButtonHangarMenu.length + 1)));
                        Layout.sub_24e_CENTER(GlobalStatus.gameText.getText(62), AEButtonHangarMenu[0].standartButtonX, AEButtonHangarMenu[0].standartButtonY, this.menuWidth, this.menuItemHighlights[0] == 1, false, true);
						
						AEButtonHangarMenu[1].drawStandartButton(Globals.rectRoundedButtonNormal, Globals.rectRoundedButtonPressed, AEButtonHangarMenu[0].standartButtonX, AEButtonHangarMenu[0].standartButtonY + AEButtonHangarMenu[0].standartButtonHeight);
                        Layout.sub_24e_CENTER(GlobalStatus.gameText.getText(218), AEButtonHangarMenu[1].standartButtonX, AEButtonHangarMenu[1].standartButtonY, this.menuWidth, this.menuItemHighlights[1] == 1, false, true);
						
						AEButtonHangarMenu[2].drawStandartButton(Globals.rectRoundedButtonNormal, Globals.rectRoundedButtonPressed, AEButtonHangarMenu[1].standartButtonX, AEButtonHangarMenu[1].standartButtonY + AEButtonHangarMenu[1].standartButtonHeight);
                        Layout.sub_24e_CENTER(GlobalStatus.gameText.getText(72), AEButtonHangarMenu[2].standartButtonX, AEButtonHangarMenu[2].standartButtonY, this.menuWidth, this.menuItemHighlights[2] == 1, false, true);
						
						AEButtonHangarMenu[3].drawStandartButton(Globals.rectRoundedButtonNormal, Globals.rectRoundedButtonPressed, AEButtonHangarMenu[2].standartButtonX, AEButtonHangarMenu[2].standartButtonY + AEButtonHangarMenu[2].standartButtonHeight);
                        Layout.sub_24e_CENTER(GlobalStatus.gameText.getText(33), AEButtonHangarMenu[3].standartButtonX, AEButtonHangarMenu[3].standartButtonY, this.menuWidth, this.menuItemHighlights[3] == 1, false, true);
						
						AEButtonHangarMenu[4].drawStandartButton(Globals.rectRoundedButtonNormal, Globals.rectRoundedButtonPressed, AEButtonHangarMenu[3].standartButtonX, AEButtonHangarMenu[3].standartButtonY + AEButtonHangarMenu[3].standartButtonHeight);
                        Layout.sub_24e_CENTER(GlobalStatus.gameText.getText(64), AEButtonHangarMenu[4].standartButtonX, AEButtonHangarMenu[4].standartButtonY, this.menuWidth, this.menuItemHighlights[4] == 1, false, true);
						
						AEButtonHangarMenu[5].drawStandartButton(Globals.rectRoundedButtonNormal, Globals.rectRoundedButtonPressed, AEButtonHangarMenu[4].standartButtonX, AEButtonHangarMenu[4].standartButtonY + AEButtonHangarMenu[4].standartButtonHeight);
                        Layout.sub_24e_CENTER(GlobalStatus.gameText.getText(66), AEButtonHangarMenu[5].standartButtonX, AEButtonHangarMenu[5].standartButtonY, this.menuWidth, this.menuItemHighlights[5] == 1, false, true);
						touchControlHangarMenu();
						
                     } else if(!this.missionMsgOpen && !this.hangarOpen && !this.mapOpen && !this.optionsOpen && !this.missionsOpen && !this.popupOpen && !this.medalMsgOpen && !this.statusOpen) {
                        Layout.addTicks((int)this.frameTime);
                        Layout.setTickHighlight(Layout.slowClockHigh_());
                     }
					 
					 /**
					 SharedVariables.graphics.setColor(240, 0, 255);
					 SharedVariables.graphics.drawLine(SharedVariables.var_e75 / 2, 0, SharedVariables.var_e75 / 2, SharedVariables.var_eb6);
					 SharedVariables.graphics.drawLine(0, SharedVariables.var_eb6 / 2, SharedVariables.var_e75, SharedVariables.var_eb6 / 2);
					 **/
					 
					 GlobalStatus.graphics.drawImage(this.factionLogo, 3, 35, 0);
					 Font.drawString(Status.getStation().getName() + " " + GlobalStatus.gameText.getText(40), 10, 10, 0, 17);
					 Font.drawString(Status.getSystem().getName(), this.factionLogo.getWidth() + 10, 35, 1, 17);
                     Font.drawString(GlobalStatus.gameText.getText(37) + ": " + Status.getStation().getTecLevel(), this.factionLogo.getWidth() + 10, 55, 1, 17);
					 Font.drawString(GlobalStatus.gameText.getText(229 + Status.getSystem().getRace()), this.factionLogo.getWidth() + 10, 75, 1, 17);

                     if(this.medalMsgOpen) {
                        Layout.drawFooter("", "");
                        this.medalPopup.draw();
                     } else if(this.popupOpen) {
                        Layout.drawFooter("", "");
                        this.popup.draw();
						touchControlDialogueWindow();
                     } else {
                        Layout.drawPanelWithoutCornerDepart(" ");
						if((bool = this.var_2c0 > Status.getShip().getCargoPlus()) && Layout.quickClockHigh_() || !bool) {
							Font.drawString(this.var_2c0 + "/" + Status.getShip().getCargoPlus() + "t", (GlobalStatus.var_e75 / 2) + (Layout.AENavigationButton[1].standartButtonWidth / 2), GlobalStatus.var_eb6 - (Layout.AENavigationButton[1].standartButtonHeight / 4), bool?4:0, 34);
						}
						Font.drawString(Layout.formatCredits(Status.getCredits()), Layout.AENavigationButton[1].standartButtonX - ((Layout.AENavigationButton[1].standartButtonWidth / 2) + 4), GlobalStatus.var_eb6 - (Layout.AENavigationButton[1].standartButtonHeight / 4), 0, 34);
                     }
                  }

                  if(this.helpMsgOpen) { // ����������� ���� � ����������
                     if(this.help == null) {
                        this.helpMsgOpen = false;
                        return;
                     }

                     this.help.handleScrollPress_(var1, (int)this.frameTime);
                     this.help.drawInterupring_();
					 Layout.dialogueButtonsTouchFlag = false;
                  } else {
					  Layout.dialogueButtonsTouchFlag = true;
				  }

               }
            }
         }
      }
   }
   
   public void touchControlDialogueWindow() {
	   if(this.popupOpen) {
		   if(this.popup.getChoice() == true && this.popup.AEButton[0].getStandartButtonPressed() == true) {
			   handleKeystate(256);
			   this.popup.AEButton[0].standartButtonActive = false;
		   } else if (this.popup.getChoice() == false && this.popup.AEButton[1].getStandartButtonPressed() == true) {
			   handleKeystate(256);
			   this.popup.AEButton[1].standartButtonActive = false;
		   }
	   }
   }
   
   public void touchControlHangarMenu() {
	   this.isDialogueWindowDisplayed = this.popupOpen;
	   if(!this.popupOpen) {
		   if(AEButtonHangarMenu[0].getStandartButtonPressed() == true) {
			   this.selectedMenuItem = 0;
			   handleKeystate(256);
			   AEButtonHangarMenu[0].standartButtonActive = false;
			}
			if(AEButtonHangarMenu[1].getStandartButtonPressed() == true) {
				this.selectedMenuItem = 1;
				handleKeystate(256);
				AEButtonHangarMenu[1].standartButtonActive = false;
			}
			if(AEButtonHangarMenu[2].getStandartButtonPressed() == true) {
				this.selectedMenuItem = 2;
				handleKeystate(256);
				AEButtonHangarMenu[2].standartButtonActive = false;
			}
			if(AEButtonHangarMenu[3].getStandartButtonPressed() == true) {
				this.selectedMenuItem = 3;
				handleKeystate(256);
				AEButtonHangarMenu[3].standartButtonActive = false;
			}
			if(AEButtonHangarMenu[4].getStandartButtonPressed() == true) {
				this.selectedMenuItem = 4;
				handleKeystate(256);
				AEButtonHangarMenu[4].standartButtonActive = false;
			}
			if(AEButtonHangarMenu[5].getStandartButtonPressed() == true) {
				this.selectedMenuItem = 5;
				handleKeystate(256);
				AEButtonHangarMenu[5].standartButtonActive = false;
			}
	   }
   }

   public final void handleKeystate(int var1) {
      if(this.loaded) {
         if(this.var_61 != null) {
            if(!this.var_61.sub_124(var1)) {
               this.var_61 = null;
               int[] var7;
               if((var7 = (new RecordHandler()).sub_3dc(5)) != null && var7.length > 0) {
                  GlobalStatus.var_1083 = var7[0] == 1;
               }
            }

         } else {
            Status.checkForLevelUp();
            if(this.loadingTick < 6000L) { // ����� ������� �������� ���� ������
				this.loadingTick = 6001L;
               if(var1 == 256) { // ������� ������ ��� ����������� ���� ������
                  this.loadingTick = 6001L;
               }

            } else if(this.helpMsgOpen) {
               if((var1 == 256) || Layout.hintsOKButton.getStandartButtonPressed()) { // ������� �� ������ �� �� ����������� ����������
                  this.helpMsgOpen = false;
                  this.help = null;
				  Layout.hintsOKButton.standartButtonActive = false;
               }

            } else {
               int var2;
               Item[] var4;
               if(this.missionMsgOpen) {
                  if(!this.dialogue.OnKeyPress_(var1)) {
                     if(this.activeMission__.getType() == 8) {
                        if(!this.activeMission__.isCampaignMission()) {
                           Status.getShip().removeCargo(this.activeMission__.getCommodityIndex(), this.activeMission__.getCommodityAmount_());
                           if(this.hangarWindow != null) {
                              this.hangarWindow.initialize();
                           }
                        }
                     } else if(!this.activeMission__.isCampaignMission() && this.activeMission__.getType() == 11) {
                        Status.setPassengers(0);
                        Status.passengersCarried += this.activeMission__.getCommodityAmount_();
                        if((var4 = Status.getShip().getCargo()) != null) {
                           for(var2 = 0; var2 < var4.length; ++var2) {
                              if(var4[var2].setUnsaleable() && var4[var2].getIndex() == 116 || var4[var2].getIndex() == 117) {
                                 Status.getShip().removeCargo(var4[var2]);
                                 if(this.hangarWindow != null) {
                                    this.hangarWindow.initialize();
                                 }
                                 break;
                              }
                           }
                        }
                     } else if(this.activeMission__.getType() != 3 && this.activeMission__.getType() != 5 && this.activeMission__.getType() != 11 && this.activeMission__.getType() == 0) {
                        if((var4 = Status.getShip().getCargo()) != null) {
                           for(var2 = 0; var2 < var4.length; ++var2) {
                              if(var4[var2].setUnsaleable() && var4[var2].getIndex() == 116) {
                                 Status.getShip().removeCargo(var4[var2]);
                                 if(this.hangarWindow != null) {
                                    this.hangarWindow.initialize();
                                 }
                                 break;
                              }
                           }
                        }

                        Status.missionGoodsCarried += this.activeMission__.getCommodityAmount_();
                     }

                     if(this.activeMission__.isCampaignMission()) {
                        Status.nextCampaignMission();
                        if(Status.getCurrentCampaignMission() == 9 || Status.getCurrentCampaignMission() == 44) {
                           Status.removeMission_(this.activeMission__);
                           Status.setMission(Mission.emptyMission_);
                           this.missionMsgOpen = false;
                           GlobalStatus.applicationManager.SetCurrentApplicationModule(this);
                           return;
                        }

                        if(Status.getCurrentCampaignMission() == 18) {
                           this.cutScene.replacePlayerShip(Status.getShip().getIndex(), 0);
                           Status.removeMission_(this.activeMission__);
                           this.missionMsgOpen = false;
                           return;
                        }
                     } else {
                        Status.incMissionCount();
                     }

                     Status.changeCredits(this.activeMission__.getReward());
                     Status.removeMission_(this.activeMission__);
                     this.missionMsgOpen = false;
                  }
               } else if(this.barOpen) {
                  if(!this.spaceLounge.handleKeystate(var1)) {
                     this.cutScene.resetCamera();
                     this.barOpen = false;
                     GlobalStatus.soundManager.playMusic(1);
                  }

               } else if(this.hangarOpen) {
                  if(!GlobalStatus.shipHelpShown && var1 == 4 && this.hangarWindow.inRoot() && this.hangarWindow.getCurrentTab() == 1) {
                     this.help = new Dialogue(GlobalStatus.gameText.getText(310));
                     GlobalStatus.shipHelpShown = true;
                     this.helpMsgOpen = true;
                  } else if(!GlobalStatus.bluePrintsHelpShown && var1 == 32 && this.hangarWindow.inRoot() && this.hangarWindow.getCurrentTab() == 1) {
                     this.help = new Dialogue(GlobalStatus.gameText.getText(312));
                     GlobalStatus.bluePrintsHelpShown = true;
                     this.helpMsgOpen = true;
                  } else if(!GlobalStatus.bluePrintInfoHelpShown && var1 == 256 && this.hangarWindow.inRoot() && this.hangarWindow.getCurrentTab() == 2) {
                     this.help = new Dialogue(GlobalStatus.gameText.getText(313));
                     GlobalStatus.bluePrintInfoHelpShown = true;
                     this.helpMsgOpen = true;
                  } else if(!GlobalStatus.actionsHelpShown && var1 == 256 && this.hangarWindow.getSelectedItem() != null && this.hangarWindow.getSelectedItem().isItem() && this.hangarWindow.inRoot() && this.hangarWindow.getCurrentTab() == 0) {
                     this.help = new Dialogue(GlobalStatus.gameText.getText(311));
                     GlobalStatus.actionsHelpShown = true;
                     this.helpMsgOpen = true;
                  }

                  if(!this.hangarWindow.handleKeyPressed(var1)) {
                     var1 = 0;
                     Item[] var6;
                     if((var6 = Status.getShip().getCargo()) != null) {
                        for(int var3 = 0; var3 < var6.length; ++var3) {
                           if(var6[var3].getIndex() >= 132 && var6[var3].getIndex() < 154) {
                              var1 += var6[var3].getAmount();
                           }
                        }
                     }

                     if(var1 > this.boozeOwnedAtArrival) {
                        Status.boughtBooze += var1 - this.boozeOwnedAtArrival;
                     }

                     this.cutScene.resetCamera();
                     this.hangarOpen = false;
                  }

               } else if(this.mapOpen) {
                  if(!this.starMap.handleKeystate(var1)) {
                     this.mapOpen = false;
                  }

               } else if(this.missionsOpen) {
                  if(!this.missionsWindow.handleKeystate(var1)) {
                     this.missionsOpen = false;
                  }

               } else if(this.statusOpen) {
                  if(!GlobalStatus.medalsHelpShown && var1 == 32) {
                     this.help = new Dialogue(GlobalStatus.gameText.getText(323));
                     GlobalStatus.medalsHelpShown = true;
                     this.helpMsgOpen = true;
                  }

                  if(!this.statusPanel.handleKeyPress(var1)) {
                     this.statusOpen = false;
                  }

               } else if(this.medalMsgOpen) {
                  if(var1 == 256) {
                     this.checkMedals_();
                  }

               } else if(this.popupOpen) {
                  if(var1 == 4) {
                     this.popup.left();
                  }

                  if(var1 == 32) {
                     this.popup.right();
                  }

                  if(var1 == 256) {
                     if(this.popup.getChoice() && this.leaveStationWarnOpen || !this.popup.getChoice() && this.leaveStationWarnOpen && this.bribeMsgOpen) {
                        this.leaveStationWarnOpen = false;
                        Status.departStation(Status.getStation());
                        Status.baseArmour = -1;
                        Status.shield = -1;
                        Status.additionalArmour = -1;
                        Achievements.resetNewMedals();
                        GlobalStatus.applicationManager.SetCurrentApplicationModule(GlobalStatus.scenes[2]);
                        return;
                     }

                     if(this.bribeMsgOpen) {
                        if(this.popup.getChoice()) {
                           if(Status.getCredits() < this.bribe) {
                              String var5 = Status.replaceTokens(GlobalStatus.gameText.getText(83), Layout.formatCredits(this.bribe - Status.getCredits()), "#C");
                              this.popup.set(var5, false);
                              this.leaveStationWarnOpen = true;
                              this.bribeMsgOpen = true;
                              this.popupOpen = true;
                              return;
                           }

                           Status.changeCredits(-this.bribe);
                           this.paidBribe = true;
                           this.bribeMsgOpen = false;
                           this.popupOpen = false;
                        } else {
                           this.leaveStationWarnOpen = false;
                           Status.departStation(Status.getStation());
                           Status.baseArmour = -1;
                           Status.shield = -1;
                           Status.additionalArmour = -1;
                           Achievements.resetNewMedals();
                           GlobalStatus.applicationManager.SetCurrentApplicationModule(GlobalStatus.scenes[2]);
                        }
                     }

                     this.popupOpen = false;
                  }

               } else {
                  if(this.menuOpen && !this.optionsOpen) {
                     if(var1 == 256 && !this.missionMsgOpen && !this.medalMsgOpen) {
                        switch(this.selectedMenuItem) {
                        case 0: // �����
                           if(Status.getCurrentCampaignMission() < 5) {
                              this.help = new Dialogue(GlobalStatus.gameText.getText(257));
                              this.helpMsgOpen = true;
                              return;
                           }

                           if(this.hangarWindow == null) {
                              this.hangarWindow = new HangarWindow();
                              this.hangarWindow.initialize();
                           }

                           if(this.spaceLounge != null && this.spaceLounge.hangarNeedsUpdate()) {
                              this.hangarWindow.initialize();
                              this.spaceLounge.setHangarUpdate(false);
                           } else if(this.missionsWindow != null && this.missionsWindow.hangarNeedsUpdate()) {
                              this.hangarWindow.initialize();
                              this.missionsWindow.setHangarUpdate(false);
                           }

                           this.boozeOwnedAtArrival = 0;
                           if((var4 = Status.getShip().getCargo()) != null) {
                              for(var2 = 0; var2 < var4.length; ++var2) {
                                 if(var4[var2].getIndex() >= 132 && var4[var2].getIndex() < 154) {
                                    this.boozeOwnedAtArrival += var4[var2].getAmount();
                                 }
                              }
                           }

                           this.hangarOpen = true;
                           if(this.help == null && !GlobalStatus.shopHelpShown) {
                              this.help = new Dialogue(GlobalStatus.gameText.getText(309));
                              GlobalStatus.shopHelpShown = true;
                              this.helpMsgOpen = true;
                           }

                           return;
                        case 1: // ���
                           if(Status.getCurrentCampaignMission() < 13 || (Status.getStation().getId() == 100 || Status.getStation().getId() == 101)) {
                              this.help = new Dialogue(GlobalStatus.gameText.getText(257));
                              this.helpMsgOpen = true;
                              return;
                           }

                           if(this.spaceLounge == null) {
                              this.spaceLounge = new SpaceLounge();
                           } else {
                              this.spaceLounge.init();
                           }

                           GlobalStatus.soundManager.playMusic(2);
                           this.barOpen = true;
                           this.barTick = -150L;
                           if(this.help == null && !GlobalStatus.barHelpShown) {
                              this.help = new Dialogue(GlobalStatus.gameText.getText(314));
                              GlobalStatus.barHelpShown = true;
                              this.helpMsgOpen = true;
                           }

                           return;
                        case 2: // �����?
                           if(Status.getCurrentCampaignMission() < 9) {
                              this.help = new Dialogue(GlobalStatus.gameText.getText(257));
                              this.helpMsgOpen = true;
                              return;
                           }

                           if(Status.getShip().getCurrentLoad() > Status.getShip().getCargoPlus()) {
                              this.popup.setAsWarning(GlobalStatus.gameText.getText(84));
                              this.popupOpen = true;
                              return;
                           }

                           if(this.starMap == null) {
                              this.starMap = new StarMap(false, (Mission)null, false, -1);
                           } else {
                              this.starMap.init(false, (Mission)null, false, -1);
                           }

                           if(Status.getShip().hasEquipment(85) || Status.getShip().getKhadorIntegrated() == true) { // ���� ���������� ��������� ������ ��� � ������ ������� Deep Science
                              this.starMap.setJumpMapMode(false, true);
                           }

                           this.mapOpen = true;
                           if(this.help == null && !GlobalStatus.galaxyMapHelpShown && Status.getCurrentCampaignMission() >= 16) {
                              this.help = new Dialogue(GlobalStatus.gameText.getText(315));
                              GlobalStatus.galaxyMapHelpShown = true;
                              this.helpMsgOpen = true;
                              return;
                           }

                           if(this.help == null && !GlobalStatus.systemMapHelpShown && Status.getCurrentCampaignMission() < 16) {
                              this.help = new Dialogue(GlobalStatus.gameText.getText(316));
                              GlobalStatus.systemMapHelpShown = true;
                              this.helpMsgOpen = true;
                           }

                           return;
                        case 3: // �������?
                           if(Status.getCurrentCampaignMission() < 13) {
                              this.help = new Dialogue(GlobalStatus.gameText.getText(257));
                              this.helpMsgOpen = true;
                              return;
                           }

                           if(this.missionsWindow == null) {
                              this.missionsWindow = new MissionsWindow();
                           } else {
                              this.missionsWindow.init();
                           }

                           this.missionsOpen = true;
                           if(this.help == null && !GlobalStatus.missionsHelpShown) {
                              this.help = new Dialogue(GlobalStatus.gameText.getText(318));
                              GlobalStatus.missionsHelpShown = true;
                              this.helpMsgOpen = true;
                           }

                           return;
                        case 4: // ������?
                           if(this.statusPanel == null) {
                              this.statusPanel = new StatusPanel();
                           }

                           this.statusOpen = true;
                           this.statusPanel.init();
                           if(this.help == null && !GlobalStatus.statusHelpShown) {
                              this.help = new Dialogue(GlobalStatus.gameText.getText(322));
                              GlobalStatus.statusHelpShown = true;
                              this.helpMsgOpen = true;
                           }

                           return;
                        case 5: // ���������?
                           if(this.optionsWindow == null) {
                              this.optionsWindow = new OptionsWindow();
                           }

                           this.optionsOpen = true;
                           this.optionsWindow.reset_(2);
                           return;
                        }
                     }

                     if(var1 == 64) {
                        this.menuItemHighlights[this.selectedMenuItem] = 0;
                        if(this.selectedMenuItem < this.menuItemHighlights.length - 1) {
                           ++this.selectedMenuItem;
                        } else {
                           this.selectedMenuItem = 0;
                        }

                        this.menuItemHighlights[this.selectedMenuItem] = 1;
                     }

                     if(var1 == 2) {
                        this.menuItemHighlights[this.selectedMenuItem] = 0;
                        if(this.selectedMenuItem > 0) {
                           --this.selectedMenuItem;
                        } else {
                           this.selectedMenuItem = this.menuItemHighlights.length - 1;
                        }

                        this.menuItemHighlights[this.selectedMenuItem] = 1;
                     }

                     if(var1 == 16384) {
                        this.menuOpen = !this.menuOpen; // �������� ���� ������
                        return;
                     }
                  }

                  if(!this.optionsOpen && (var1 == 8192) || Layout.AENavigationButton[1].getStandartButtonPressed()) { // DEPART
                     this.leaveStation();
					 Layout.AENavigationButton[1].standartButtonActive = false;
                  } else if(this.optionsOpen) {
                     this.optionsWindow.handleKeystate(var1);
                     if(this.optionsWindow != null) {
                        if(var1 == 4) {
                           this.optionsWindow.optionsLeft();
                        }

                        if(var1 == 32) {
                           this.optionsWindow.optionsRight();
                        }

                        if(var1 == 2) {
                           this.optionsWindow.scrollUp((int)this.frameTime);
                        }

                        if(var1 == 64) {
                           this.optionsWindow.scrollDown((int)this.frameTime);
                        }

                        if(var1 == 16384) { // �� � ���� �������� � ������
                           this.optionsWindow.update1_();
                        }

                        if(Layout.AENavigationButton[0].getStandartButtonPressed() || (var1 == 8192) && this.optionsWindow.goBack()) { // ������� ���� �������� � ������
                           this.optionsOpen = !this.optionsOpen;
                           this.optionsWindow.reset_(2);
						   Layout.AENavigationButton[0].standartButtonActive = false;
                        }

                        if(var1 == 256 && !this.missionMsgOpen && !this.medalMsgOpen && this.optionsWindow.update()) {
                           this.optionsOpen = false;
                        }

                     }
                  } else {
                     if(var1 == 16384) { // �������� ���� ������
                        this.menuOpen = !this.menuOpen;
                     }

                  }
               }
            }
         }
      }
   }

   private void leaveStation() {
      if(Status.getShip().getCurrentLoad() > Status.getShip().getCargoPlus()) {
         Popup var10000 = this.popup;
         String var2 = GlobalStatus.gameText.getText(84);
         var10000.set(var2, false);
         this.popupOpen = true;
      } else if(Status.getCurrentCampaignMission() != 6 && (Status.getCurrentCampaignMission() != 7 || Status.getShip().sub_4ab() != 0 || Status.getShip().getCombinedHP() != Status.getShip().getBaseHP())) {
         if(Status.getCurrentCampaignMission() == 20 && Status.getStation().getId() == Status.getCampaignMission().getTargetStation()) {
            this.help = new Dialogue(GlobalStatus.gameText.getText(260));
            this.helpMsgOpen = true;
         } else {
            if(Status.getCurrentCampaignMission() == 21 && Status.getStation().getId() == Status.getCampaignMission().getTargetStation()) {
               Status.getShip().getEquipment(1);
               if(Status.getShip().hasEquipment(41)) {
                  if(!this.popupOpen && !this.missionMsgOpen && !this.medalMsgOpen) {
                     this.popup.set(GlobalStatus.gameText.getText(217), true);
                     this.popup.left();
                     this.popupOpen = true;
                     this.leaveStationWarnOpen = true;
                  }

                  return;
               }

               this.help = new Dialogue(GlobalStatus.gameText.getText(260));
               this.helpMsgOpen = true;
            } else if(!this.popupOpen && !this.missionMsgOpen && !this.medalMsgOpen) {
               this.popup.set(GlobalStatus.gameText.getText(217), true);
               this.popup.left();
               this.popupOpen = true;
               this.leaveStationWarnOpen = true;
            }

         }
      } else {
         if(!Status.getShip().hasCargoType_FIX_ME_() && !Status.getShip().hasCargoType_FIX_ME_()) {
            this.help = new Dialogue(GlobalStatus.gameText.getText(258));
         } else {
            this.help = new Dialogue(GlobalStatus.gameText.getText(259));
         }

         this.helpMsgOpen = true;
      }
   }
   
   public static void pointerPressed(int x, int y) {
	   if(AEButtonHangarMenu != null && isDialogueWindowDisplayed == false) {
		   AEButtonHangarMenu[0].standartButtonTouch(x, y);
		   AEButtonHangarMenu[1].standartButtonTouch(x, y);
		   AEButtonHangarMenu[2].standartButtonTouch(x, y);
		   AEButtonHangarMenu[3].standartButtonTouch(x, y);
		   AEButtonHangarMenu[4].standartButtonTouch(x, y);
		   AEButtonHangarMenu[5].standartButtonTouch(x, y);
	   }
   }
   
   public static void pointerReleased(int x, int y) {
	   if(AEButtonHangarMenu != null && isDialogueWindowDisplayed == false) {
		   AEButtonHangarMenu[0].buttonsTouchReleased(x, y);
		   AEButtonHangarMenu[1].buttonsTouchReleased(x, y);
		   AEButtonHangarMenu[2].buttonsTouchReleased(x, y);
		   AEButtonHangarMenu[3].buttonsTouchReleased(x, y);
		   AEButtonHangarMenu[4].buttonsTouchReleased(x, y);
		   AEButtonHangarMenu[5].buttonsTouchReleased(x, y);
	   }
   }
}
