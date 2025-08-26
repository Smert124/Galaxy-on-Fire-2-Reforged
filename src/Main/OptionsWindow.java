package Main;

import javax.microedition.lcdui.Image;
import HardEngine.*;
import javax.microedition.io.ConnectionNotFoundException;

import AE.GlobalStatus;
import AE.PaintCanvas.Font;
import AE.SoundManager;
import AE.TextInput;
import AE.Time;
import GoF2.GameRecord;
import GoF2.GameText;
import GoF2.Globals;
import GoF2.Layout;
import GoF2.LoadingScreen;
import GoF2.Popup;
import GoF2.RecordHandler;
import GoF2.Status;
import GoF2.TextBox;

public final class OptionsWindow {

   private static int posX;
   private static int headerHeight;
   private static int windowWidth;
   private static int[] menuItemCounts = new int[]{6, 10, 10, GlobalStatus.max_settings, 3, 3/*����� � �������*/, 11, 0, 0, 2, GlobalStatus.max_settings, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2};
   private Image gameLogo;
   private int fontSpacingY;
   private int saveGameItemHeight;
   private int selectedRow;
   private int subMenu;
   private String saveInfo;
   private Popup confirmPopup;
   private boolean confirmPopupOpen;
   private boolean forcePauseMenu_;
   private GameRecord[] saves;
   private String stationName;
   private ListWindow_ manualWindow;
   private TextBox credits;
   private TextBox instructions;
   private TextBox controls;
   private TextBox hidden__;
   private TextInput nameInput;
   private int frameTime;
   private int musicLevel;
   private int soundLevel;
   private int recordWindowWidth;
   private boolean webAccessAvailable = false;
   private String operatorURL = null;
   private String operatorsPrompt = null;
   private String operatorMenuEntry = null;
   private int wapMode = -1;
   private boolean unused_b06 = false;
   private Image unused_b34 = null;
   private int optionsListPosY;
   private Class_1991 var_8a1;
   private int language_flag_image_x = GlobalStatus.var_e75 / 2 + 15;
   
   private static AEButtonManager[] AEButtonMainMenu;
   private static boolean button_main_menu_touchFlag = false;
   private static AEButtonManager[] AEButtonLoadNSaveGame;
   private static boolean button_load_n_save_game_menu_touchFlag = false;
   private static AEButtonManager AEButtonLoadNSaveSelector;
   private static boolean button_dialogue_window_touch_flag = false;
   private static AEButtonManager[] AEButtonCheckBoxSettings;
   private static AEButtonManager[] AEButtonSettings;
   private static boolean button_settings_touch_flag = false;
   private static AEButtonManager[] AEButtonHelpMenu;
   private static AEButtonManager skipSceneButton;
   private static boolean button_help_menu_touch_flag = false;
   private static boolean asteroids = false;
   private static boolean music_state = false;
   private static boolean effects_state = false;
   
   private static AEButtonManager AETelegramButton;


   public OptionsWindow() {
      this.initWapItem();
      if(this.webAccessAvailable) {
         menuItemCounts[0] = 7;
      }
	  
      this.gameLogo = LoadingScreen.getGameLogo();
      headerHeight = this.gameLogo.getHeight() + 25;
      this.optionsListPosY = headerHeight + 16;
      windowWidth = 117;
      posX = GlobalStatus.var_e75 - windowWidth >> 1;
      this.fontSpacingY = Font.getFontSpacingY();
      this.selectedRow = 0;
      this.subMenu = 0;
      this.confirmPopup = new Popup(20, GlobalStatus.var_eb6 / 2, GlobalStatus.var_e75 - 40);
      this.confirmPopupOpen = false;
      this.forcePauseMenu_ = false;
      this.credits = new TextBox(posX / 2 + 8, this.optionsListPosY, windowWidth + posX - 16, GlobalStatus.var_eb6 - headerHeight - 48, GlobalStatus.gameText.getText(23) + "\n\n" + GlobalStatus.gameText.getText(25));
      this.instructions = new TextBox(posX / 2 + 8, this.optionsListPosY, windowWidth + posX - 16, GlobalStatus.var_eb6 - headerHeight - 48, "");
      this.controls = new TextBox(posX / 2 + 8, this.optionsListPosY, windowWidth + posX - 16, GlobalStatus.var_eb6 - headerHeight - 48, GlobalStatus.gameText.getText(22));
      this.hidden__ = new TextBox(posX + 8, this.optionsListPosY, windowWidth - 16, GlobalStatus.var_eb6 - headerHeight - 48, "");
      String[] var1 = new String[GameText.helpFull.length];
	  
	  
	  skipSceneButton = new AEButtonManager();
	  AEButtonMainMenu = new AEButtonManager[6];
	  for(int buttonCount = 0; buttonCount < AEButtonMainMenu.length; ++buttonCount) {
		  AEButtonMainMenu[buttonCount] = new AEButtonManager();
	  }
	  
	  AEButtonLoadNSaveGame = new AEButtonManager[10];
	  for(int buttonCount = 0; buttonCount < AEButtonLoadNSaveGame.length; ++buttonCount) {
		  AEButtonLoadNSaveGame[buttonCount] = new AEButtonManager();
	  }
	  
	  AEButtonCheckBoxSettings = new AEButtonManager[10];
	  for(int buttonCount = 0; buttonCount < AEButtonCheckBoxSettings.length; ++buttonCount) {
		  AEButtonCheckBoxSettings[buttonCount] = new AEButtonManager();
	  }
	  
	  AEButtonSettings = new AEButtonManager[6];
	  for(int buttonCount = 0; buttonCount < AEButtonSettings.length; ++buttonCount) {
		  AEButtonSettings[buttonCount] = new AEButtonManager();
	  }
	  
	  AEButtonHelpMenu = new AEButtonManager[3];
	  for(int buttonCount = 0; buttonCount < AEButtonHelpMenu.length; ++buttonCount) {
		  AEButtonHelpMenu[buttonCount] = new AEButtonManager();
	  }
	  
	  AETelegramButton = new AEButtonManager();
	  AEButtonLoadNSaveSelector = new AEButtonManager();

      for(int var2 = 0; var2 < var1.length; ++var2) {
         var1[var2] = GlobalStatus.gameText.getText(GameText.helpTitles[var2]);
      }

      this.manualWindow = new ListWindow_(posX, headerHeight + 2, windowWidth, GlobalStatus.var_eb6 - headerHeight - 16 - 10, (String[])null);
      this.manualWindow.setRowHeight(12);
      this.manualWindow.setEntries(0, var1);
      this.manualWindow.setSelectionHighlight(true);
      this.manualWindow.setBackGroundDraw(false);
      this.manualWindow.decFontIndex_();
      this.nameInput = new TextInput(10);
      this.musicLevel = GlobalStatus.musicVolume;
      this.soundLevel = GlobalStatus.sfxVolume;
      menuItemCounts[12] = 2;
      this.var_8a1 = null;
   }

   private static String unescape(String var0) {
      int var1 = 0;
      int var2 = var0.length();
      StringBuffer var3 = new StringBuffer();

      while(var1 < var2) {
         char var4;
         if((var4 = var0.charAt(var1)) == 92 && var1 + 6 <= var2 && var0.charAt(var1 + 1) == 117) {
            var4 = (char)Integer.parseInt(var0.substring(var1 + 2, var1 + 6), 16);
            var3.append(var4);
            var1 += 6;
         } else {
            var3.append(var4);
            ++var1;
         }
      }

      return var3.toString();
   }

   private void initWapItem() {
      try {
         try {
            this.operatorURL = GlobalStatus.midlet.getAppProperty("Operator-URL-ru");
            this.operatorMenuEntry = GlobalStatus.midlet.getAppProperty("Operator-Menu-ru");
         } catch (Exception var10) {
            this.webAccessAvailable = false;
            this.operatorURL = null;
            this.operatorsPrompt = null;
            this.operatorMenuEntry = null;

            try {
               this.operatorURL = GlobalStatus.midlet.getAppProperty("Operator-URL-gn");
               this.operatorMenuEntry = GlobalStatus.midlet.getAppProperty("Operator-Menu-gn");
            } catch (Exception var9) {
               this.webAccessAvailable = false;
               return;
            }

            if(this.operatorURL == null || this.operatorMenuEntry == null) {
               this.webAccessAvailable = false;
               return;
            }
         }

         if(this.operatorURL == null || this.operatorMenuEntry == null) {
            try {
               this.operatorURL = GlobalStatus.midlet.getAppProperty("Operator-URL-gn");
               this.operatorMenuEntry = GlobalStatus.midlet.getAppProperty("Operator-Menu-gn");
            } catch (Exception var8) {
               this.webAccessAvailable = false;
               return;
            }
         }

         try {
            this.operatorsPrompt = GlobalStatus.midlet.getAppProperty("Operator-Prompt-ru");
         } catch (Exception var7) {
            this.operatorsPrompt = null;

            try {
               this.operatorsPrompt = GlobalStatus.midlet.getAppProperty("Operator-Prompt-gn");
            } catch (Exception var6) {
               ;
            }
         }

         if(this.operatorsPrompt == null) {
            try {
               this.operatorsPrompt = GlobalStatus.midlet.getAppProperty("Operator-Prompt-gn");
            } catch (Exception var5) {
               ;
            }
         }

         if(this.operatorsPrompt != null) {
            this.operatorsPrompt = unescape(this.operatorsPrompt);
         }

         if(this.operatorMenuEntry != null) {
            this.operatorMenuEntry = unescape(this.operatorMenuEntry);
         }
         String var1 = GlobalStatus.midlet.getAppProperty("Operator-Mode");
         if(this.operatorURL != null && var1 != null) {
            char[] var2 = new char[]{'1', '2'};

            for(int var3 = 0; var3 < var2.length; ++var3) {
               int var4;
               if((var4 = var1.indexOf(String.valueOf(var2[var3]))) >= 0) {
                  this.wapMode = Integer.valueOf(String.valueOf(var1.charAt(var4))).intValue();
               }
            }

            if(this.wapMode < 1 || this.wapMode > 2) {
               this.wapMode = 2;
            }

            this.webAccessAvailable = true;
         } else {
            throw new Exception();
         }
      } catch (Exception var11) {
         this.webAccessAvailable = false;
      }
   }

   private void loadSavePreviews(GameRecord[] var1) {
      this.recordWindowWidth = 0;
      if(var1 != null) {
         for(int var2 = 0; var2 < var1.length; ++var2) {
            this.saveInfo = var2 + 1 + ".  " + (var1[var2] == null?GlobalStatus.gameText.getText(26):Time.timeToHM(var1[var2].playTime) + (var2 == 3?" Autosave":" " + var1[var2].stationName));
            int var3;
            if((var3 = Font.getTextWidth(this.saveInfo, 1)) > this.recordWindowWidth) {
               this.recordWindowWidth = var3;
            }
         }

         this.recordWindowWidth += 24;
      } else {
         this.recordWindowWidth = windowWidth + (windowWidth >> 1);
      }
   }

   public final void OnRelease() {
      this.saves = null;
      this.confirmPopup = null;
      this.gameLogo = null;
   }

   public final boolean sub_10a() {
      return this.var_8a1 != null;
   }

   public final void scrollAndTick_(int var1, int var2) {
      this.frameTime = var2;
      if(this.var_8a1 != null) {
         this.var_8a1.sub_3a(var1, var2);
      } else {
         Layout.addTicks(var2);
         if((var1 & 64) != 0) {
            if(this.subMenu >= 15) {
               this.instructions.scrollUp(var2);
               return;
            }

            if(this.subMenu == 8) {
               this.credits.scrollUp(var2);
               return;
            }

            if(this.subMenu == 7) {
               this.controls.scrollUp(var2);
               return;
            }
         } else if((var1 & 2) != 0) {
            if(this.subMenu >= 15) {
               this.instructions.scrollDown(var2);
               return;
            }

            if(this.subMenu == 8) {
               this.credits.scrollDown(var2);
               return;
            }

            if(this.subMenu == 7) {
               this.controls.scrollDown(var2);
            }
         }

      }
   }
   
   public void touchButtonsControl() {
	   
	   if((this.subMenu == 0 || this.subMenu == 5) && button_dialogue_window_touch_flag == false) {
		   button_main_menu_touchFlag = true;
	   } else {
		   button_main_menu_touchFlag = false;
	   }
	   
	   if((this.subMenu == 1 || this.subMenu == 2) && button_dialogue_window_touch_flag == false) {
		   button_load_n_save_game_menu_touchFlag = true;
	   } else {
		   button_load_n_save_game_menu_touchFlag = false;
	   }
	   
	   if(this.subMenu == 3 && button_dialogue_window_touch_flag == false) {
		   button_settings_touch_flag = true;
	   } else {
		   button_settings_touch_flag = false;
	   }
	   
	   if(this.subMenu == 4 && button_dialogue_window_touch_flag == false) {
		   button_help_menu_touch_flag = true;
	   } else {
		   button_help_menu_touch_flag = false;
	   }
	   
	   if(AETelegramButton.getStandartButtonPressed() == true && this.subMenu == 0) {
		   try {
			   GF2.GF2.platformRequest("http://t.me/HardCondition");
			  // GF2.GF2.destroyApp(true);
			} catch(ConnectionNotFoundException ex) {
				System.out.println("ERROR LINK: " + ex);
			}
	   }
	   
	   if((button_main_menu_touchFlag == true && this.subMenu == 0) && button_dialogue_window_touch_flag == false) { // ������� ����
		   
		   if(AEButtonMainMenu[0].getStandartButtonPressed() == true) {
			   AEButtonMainMenu[0].standartButtonActive = false;
			   this.selectedRow = 0;
			   this.update();
		    }
			
			if(AEButtonMainMenu[1].getStandartButtonPressed() == true) {
				AEButtonMainMenu[1].standartButtonActive = false;
				this.selectedRow = 1;
				this.update();
			}
			
			if(AEButtonMainMenu[2].getStandartButtonPressed() == true) {
				AEButtonMainMenu[2].standartButtonActive = false;
				this.selectedRow = 2;
				this.update();
			}
			
			if(AEButtonMainMenu[3].getStandartButtonPressed() == true) {
				AEButtonMainMenu[3].standartButtonActive = false;
				this.selectedRow = 3;
				this.update();
			}
			
			if(AEButtonMainMenu[4].getStandartButtonPressed() == true) {
				AEButtonMainMenu[4].standartButtonActive = false;
				this.selectedRow = 4;
				this.update();
			}
			
			if(AEButtonMainMenu[5].getStandartButtonPressed() == true) {
				AEButtonMainMenu[5].standartButtonActive = false;
				this.selectedRow = 5;
				this.update();
			}
			
		}
		
		if((button_load_n_save_game_menu_touchFlag == true && this.subMenu == 1 || this.subMenu == 2) && button_dialogue_window_touch_flag == false) { // ��������� \ ���������
			
			if(AEButtonLoadNSaveGame[0].getStandartButtonPressed() == true) {
				this.selectedRow = 0;
			}
			if(AEButtonLoadNSaveGame[1].getStandartButtonPressed() == true) {
				this.selectedRow = 1;
			}
			if(AEButtonLoadNSaveGame[2].getStandartButtonPressed() == true) {
				this.selectedRow = 2;
			}
			if(AEButtonLoadNSaveGame[3].getStandartButtonPressed() == true) {
				this.selectedRow = 3;
			}
			if(AEButtonLoadNSaveGame[4].getStandartButtonPressed() == true) {
				this.selectedRow = 4;
			}
			if(AEButtonLoadNSaveGame[5].getStandartButtonPressed() == true) {
				this.selectedRow = 5;
			}
			if(AEButtonLoadNSaveGame[6].getStandartButtonPressed() == true) {
				this.selectedRow = 6;
			}if(AEButtonLoadNSaveGame[7].getStandartButtonPressed() == true) {
				this.selectedRow = 7;
			}
			if(AEButtonLoadNSaveGame[8].getStandartButtonPressed() == true) {
				this.selectedRow = 8;
			}
			if(AEButtonLoadNSaveGame[9].getStandartButtonPressed() == true) {
				this.selectedRow = 9;
			}
			
			if(AEButtonLoadNSaveSelector.getStandartButtonPressed() == true) {
				AEButtonLoadNSaveSelector.standartButtonActive = false;
				this.update();
			}
			
		}
		
		if(this.subMenu == 3) { // ���������
			
			if(GlobalStatus.asteroid == 0) {
				asteroids = false;
			} else {
				asteroids = true;
			}
			
			if(this.soundLevel == 3) {
				effects_state = false;
			} else {
				effects_state = true;
			}
			
			if(this.musicLevel == 3) {
				music_state = false;
			} else {
				music_state = true;
			}
			
			if(AEButtonCheckBoxSettings[0].getSwitchableButtonPressed() == true) {
				AEButtonCheckBoxSettings[0].switchableButtonActive = false;
				this.selectedRow = 0;
				this.optionsRight();
			}
			if(AEButtonSettings[0].getStandartButtonPressed() == true) {
				AEButtonSettings[0].standartButtonActive = false;
				this.selectedRow = 1;
				this.optionsRight();
			}
			if(AEButtonSettings[1].getStandartButtonPressed() == true) {
				AEButtonSettings[1].standartButtonActive = false;
				this.selectedRow = 2;
				this.optionsRight();
			}
			if(AEButtonCheckBoxSettings[1].getSwitchableButtonPressed() == true) {
				AEButtonCheckBoxSettings[1].switchableButtonActive = false;
				this.selectedRow = 3;
				this.optionsRight();
			}
			if(AEButtonSettings[2].getStandartButtonPressed() == true) {
				AEButtonSettings[2].standartButtonActive = false;
				this.selectedRow = 4;
				this.optionsRight();
			}
			if(AEButtonSettings[3].getStandartButtonPressed() == true) {
				AEButtonSettings[3].standartButtonActive = false;
				this.selectedRow = 5;
				this.optionsRight();
			}
			if(AEButtonSettings[4].getStandartButtonPressed() == true) {
				AEButtonSettings[4].standartButtonActive = false;
				this.selectedRow = 6;
				this.optionsRight();
			}
			if(AEButtonCheckBoxSettings[2].getSwitchableButtonPressed() == true) {
				AEButtonCheckBoxSettings[2].switchableButtonActive = false;
				this.selectedRow = 7;
				this.optionsRight();
			}
			if(AEButtonCheckBoxSettings[3].getSwitchableButtonPressed() == true) {
				AEButtonCheckBoxSettings[3].switchableButtonActive = false;
				this.selectedRow = 8;
				this.optionsRight();
			}
			if(AEButtonCheckBoxSettings[4].getSwitchableButtonPressed() == true) {
				AEButtonCheckBoxSettings[4].switchableButtonActive = false;
				this.selectedRow = 9;
				this.optionsRight();
			}
			if(AEButtonCheckBoxSettings[5].getSwitchableButtonPressed() == true) {
				AEButtonCheckBoxSettings[5].switchableButtonActive = false;
				this.selectedRow = 10;
				this.optionsRight();
			}
			if(AEButtonCheckBoxSettings[6].getSwitchableButtonPressed() == true) {
				AEButtonCheckBoxSettings[6].switchableButtonActive = false;
				this.selectedRow = 11;
				this.optionsRight();
			}
			if(AEButtonSettings[5].getStandartButtonPressed() == true) {
				AEButtonSettings[5].standartButtonActive = false;
				this.selectedRow = 12;
				if(GlobalStatus.default_language == 6) {
					this.optionsLeft();
				} else {
					this.optionsRight();
				}
			}
			if(AEButtonCheckBoxSettings[7].getSwitchableButtonPressed() == true) {
				AEButtonCheckBoxSettings[7].switchableButtonActive = false;
				this.selectedRow = 13;
				this.optionsRight();
			}
			if(AEButtonCheckBoxSettings[8].getSwitchableButtonPressed() == true) {
				AEButtonCheckBoxSettings[8].switchableButtonActive = false;
				this.selectedRow = 14;
				this.optionsRight();
			}
			if(AEButtonCheckBoxSettings[9].getSwitchableButtonPressed() == true) {
				AEButtonCheckBoxSettings[9].switchableButtonActive = false;
				this.selectedRow = 15;
				this.optionsRight();
			}
		}
		
		if(this.subMenu == 4) { // ������
			if(AEButtonHelpMenu[0].getStandartButtonPressed() == true) {
				AEButtonHelpMenu[0].standartButtonActive = false;
				this.selectedRow = 0;
				this.update();
			}
			if(AEButtonHelpMenu[1].getStandartButtonPressed() == true) {
				AEButtonHelpMenu[1].standartButtonActive = false;
				this.selectedRow = 1;
				this.update();
			}
			if(AEButtonHelpMenu[2].getStandartButtonPressed() == true) {
				AEButtonHelpMenu[2].standartButtonActive = false;
				this.selectedRow = 2;
				this.update();
			}
		}
		
		if(this.subMenu == 5) { // ����� � �������
			if(AEButtonMainMenu[3].getStandartButtonPressed() == true) {
				AEButtonMainMenu[3].standartButtonActive = false;
				this.selectedRow = 0;
				this.update();
			}
			
			if(AEButtonMainMenu[4].getStandartButtonPressed() == true) {
				AEButtonMainMenu[4].standartButtonActive = false;
				this.selectedRow = 1;
				this.update();
			}
			
			if(AEButtonMainMenu[5].getStandartButtonPressed() == true) {
				AEButtonMainMenu[5].standartButtonActive = false;
				this.selectedRow = 2;
				this.update();
			}
			
			if(skipSceneButton.getStandartButtonPressed()) {
				skipMission();
				skipSceneButton.standartButtonActive = false;
			}
		}
		
		if(this.confirmPopupOpen && button_dialogue_window_touch_flag == true) {
			
			if(this.confirmPopup.AEButton[0].getStandartButtonPressed() == true) {
				this.confirmPopup.AEButton[0].standartButtonActive = false;
				this.confirmPopup.currentChoice = true;
				this.confirmPopup.left();
				this.update();
			}
			
			if(this.confirmPopup.AEButton[1].getStandartButtonPressed() == true) {
				this.confirmPopup.AEButton[1].standartButtonActive = false;
				this.confirmPopup.currentChoice = false;
				this.confirmPopup.right();
				this.update();
			}
			
		}
		
		if(this.confirmPopup.isChoice == false && button_dialogue_window_touch_flag == true) {
			
			if(this.confirmPopup.AEButton[2].getStandartButtonPressed() == true) {
				this.confirmPopup.AEButton[2].standartButtonActive = false;
				this.update();
			}
			
		}
	   
   }
   
   public void skipMission() {
	   Status.nextCampaignMission();
	   GlobalStatus.applicationManager.SetCurrentApplicationModule(GlobalStatus.scenes[1]);
   }

   public final boolean update() {
      if(this.var_8a1 != null) {
         return false;
      } else {
         RecordHandler var1;
         switch(this.subMenu) {
         case 0:
            if(this.confirmPopupOpen && this.selectedRow == 5) {
               if(this.confirmPopup.getChoice()) {
                  GlobalStatus.applicationManager.Quit();
                  return true;
               }

               this.confirmPopupOpen = false;
               return false;
            }

            switch(this.selectedRow) {
            case 0:
               if(Status.getPlayingTime() <= 0L) {
                  Status.startNewGame();
                  GlobalStatus.applicationManager.SetCurrentApplicationModule(GlobalStatus.scenes[2]);
                  SoundManager.stopMusic__();
                  return true;
               }

               if(!this.confirmPopupOpen) {
                  this.confirmPopup.set(GlobalStatus.gameText.getText(30), true);
                  this.confirmPopupOpen = true;
                  return false;
               }

               if(this.confirmPopup.getChoice()) {
                  Status.startNewGame();
                  GlobalStatus.applicationManager.SetCurrentApplicationModule(GlobalStatus.scenes[2]);
                  SoundManager.stopMusic__();
                  return true;
               }

               this.confirmPopupOpen = false;
               return false;
            case 1:
               this.subMenu = 1;
               if(this.saves == null) {
                  var1 = new RecordHandler();
                  this.saves = var1.readAllPreviews();
               }

               this.loadSavePreviews(this.saves);
               this.selectedRow = 0;
               return false;
            case 2:
               this.subMenu = 2;
               if(this.saves == null) {
                  var1 = new RecordHandler();
                  this.saves = var1.readAllPreviews();
               }

               this.loadSavePreviews(this.saves);
               this.selectedRow = 0;
               return false;
            case 3:
               this.subMenu = 3;
               this.selectedRow = 0;
               return false;
            case 4:
               this.subMenu = 4;
               this.selectedRow = 0;
               return false;
            case 5:
               if(!this.confirmPopupOpen) {
                  this.confirmPopup.set(GlobalStatus.gameText.getText(31), true);
                  this.confirmPopupOpen = true;
               }

               return false;
            case 6:
               if(this.webAccessAvailable) {
                  switch(this.wapMode) {
                  case 1:
                     try {
                        if(!this.confirmPopupOpen && this.operatorsPrompt != null) {
                           this.confirmPopup.set(this.operatorsPrompt, true);
                        } else if(this.operatorsPrompt == null || this.confirmPopup.getChoice()) {
                           GlobalStatus.midlet.platformRequest(this.operatorURL);
                           GlobalStatus.applicationManager.Quit();
                           return true;
                        }

                        if(this.operatorsPrompt != null) {
                           this.confirmPopupOpen = !this.confirmPopupOpen;
                        }
                     } catch (Exception var3) {
                        ;
                     }

                     return false;
                  default:
                     try {
                        if(!this.confirmPopupOpen && this.operatorsPrompt != null) {
                           this.confirmPopup.set(this.operatorsPrompt, true);
                        } else if(this.operatorsPrompt == null || this.confirmPopup.getChoice()) {
                           GlobalStatus.midlet.platformRequest(this.operatorURL);
                        }

                        if(this.operatorsPrompt != null) {
                           this.confirmPopupOpen = !this.confirmPopupOpen;
                        }
                     } catch (Exception var2) {
                        ;
                     }

                     return false;
                  }
               }

               return false;
            default:
               return false;
            }
         case 1:
            if(this.selectedRow == this.saves.length || this.saves[this.selectedRow] != null) {
               if(Class_12ea.sub_1c0() <= 0L) {
                  this.confirmPopupOpen = false;
                  this.loadGameRecord();
                  return true;
               }

               if(!this.confirmPopupOpen) {
                  this.confirmPopup.set(GlobalStatus.gameText.getText(29), true);
                  this.confirmPopupOpen = true;
               } else {
                  if(this.confirmPopup.getChoice()) {
                     this.loadGameRecord();
                  }

                  this.confirmPopupOpen = false;
               }
            }
            break;
         case 2:
            if(this.confirmPopupOpen) {
               if(!this.confirmPopup.getChoice()) {
                  this.confirmPopupOpen = false;
                  return true;
               }

               if(this.selectedRow == this.saves.length) {
                  this.var_8a1 = new Class_1991();
                  this.var_8a1.sub_160(13);
                  this.loadSavePreviews(this.saves);
                  this.confirmPopupOpen = false;
               } else {
                  GlobalStatus.var_bfc.startLoading_(false);
                  (var1 = new RecordHandler()).saveGame(this.selectedRow);
                  this.saves[this.selectedRow] = var1.readPreview(this.selectedRow);
                  this.loadSavePreviews(this.saves);
                  GlobalStatus.var_bfc.close();
                  this.confirmPopup.set(GlobalStatus.gameText.getText(28), false);
               }
            } else {
               if(Class_1991.var_833) {
                  Class_1991.var_833 = false;
                  return true;
               }

               if(this.selectedRow < this.saves.length && this.saves[this.selectedRow] == null) {
                  GlobalStatus.var_bfc.startLoading_(false);
                  (var1 = new RecordHandler()).saveGame(this.selectedRow);
                  this.saves = var1.readAllPreviews();
                  GlobalStatus.var_bfc.close();
                  this.confirmPopup.set(GlobalStatus.gameText.getText(28), false);
                  this.confirmPopupOpen = true;
               } else {
                  this.confirmPopup.set(GlobalStatus.gameText.getText(27), true);
                  this.confirmPopupOpen = true;
               }
            }
            break;
         case 3:
            this.optionsRight();
            break;
         case 4:
            switch(this.selectedRow) {
            case 0:
               this.subMenu = 6;
               this.selectedRow = 0;
               this.manualWindow.selectedEntry = 0;
               return false;
            case 1:
               this.subMenu = 7;
               return false;
            case 2:
               this.subMenu = 8;
               return false;
            default:
               return false;
            }
         case 5:
            if(this.confirmPopupOpen) {
               if(this.confirmPopup.getChoice()) {
                  GlobalStatus.applicationManager.SetCurrentApplicationModule(GlobalStatus.scenes[0]);
                  GlobalStatus.soundManager.playMusic(0);
                  return true;
               }

               this.confirmPopupOpen = false;
            } else {
               switch(this.selectedRow) {
               case 0:
                  this.subMenu = 3;
                  this.selectedRow = 0;
                  return false;
               case 1:
                  this.subMenu = 4;
                  this.selectedRow = 0;
                  return false;
               case 2:
                  if(!this.confirmPopupOpen) {
                     this.confirmPopup.set(GlobalStatus.gameText.getText(31), true);
                     this.confirmPopupOpen = true;
                  }
               }
            }
            break;
         case 6:
            this.instructions.zeroTopPadding();
            this.instructions.setText(GlobalStatus.gameText.getText(GameText.helpFull[this.selectedRow]));
            this.subMenu = 15 + this.selectedRow;
            System.out.println(this.selectedRow + "  " + this.subMenu);
         case 7:
         case 8:
         case 10:
         default:
            break;
         case 9:
            switch(this.selectedRow) {
            case 0:
               if(Status.getPlayingTime() > 0L) {
                  if(!this.confirmPopupOpen) {
                     this.confirmPopup.set(GlobalStatus.gameText.getText(30), true);
                     this.confirmPopupOpen = true;
                     return false;
                  }

                  if(this.confirmPopup.getChoice()) {
                     this.subMenu = 12;
                     this.selectedRow = 0;
                  }

                  this.confirmPopupOpen = false;
               } else {
                  this.subMenu = 11;
                  this.selectedRow = 0;
               }
               break;
            case 1:
               this.subMenu = 10;
               this.selectedRow = 0;
            }
         }

         return false;
      }
   }

   private void loadGameRecord() {
      if(!GlobalStatus.var_10e5 && (this.selectedRow == this.saves.length || this.selectedRow < this.saves.length && this.saves[this.selectedRow].goodsProduced >= 15)) {
         if(this.selectedRow == this.saves.length) {
            if(this.var_8a1 == null) {
               this.var_8a1 = new Class_1991();
            }

            this.var_8a1.sub_379(true);
         }

         RecordHandler var2 = new RecordHandler();
         this.var_8a1 = new Class_1991();
         if(var2.sub_45d(1) && var2.sub_45d(0)) {
            this.var_8a1.sub_160(7);
            return;
         }

         if(var2.sub_45d(2)) {
            if(var2.sub_45d(6)) {
               this.var_8a1.sub_160(6);
               this.var_8a1.sub_3be(true);
               this.var_8a1.sub_415();
            } else {
               this.var_8a1.sub_160(3);
            }
         } else {
            this.var_8a1.sub_160(0);
         }
      } else {
         if(this.selectedRow == this.saves.length) {
            this.var_8a1 = new Class_1991();
            this.var_8a1.sub_160(14);
            return;
         }

         (new RecordHandler()).sub_70(this.selectedRow).sub_b6();
         ((ModStation)((ModStation)GlobalStatus.scenes[1])).fromGameSave();
         GlobalStatus.applicationManager.SetCurrentApplicationModule(GlobalStatus.scenes[1]);
      }

   }

   public final void scrollUp(int var1) {
      if(this.var_8a1 == null) {
         if(Status.getCurrentCampaignMission() >= 15 && !this.confirmPopupOpen && (this.subMenu == 1 || this.subMenu == 2)) {
            if(this.selectedRow > 0) {
               --this.selectedRow;
            } else {
               this.selectedRow = this.saves.length;
            }

            if(this.subMenu == 2 && this.selectedRow == menuItemCounts[this.subMenu]) {
               this.selectedRow = menuItemCounts[this.subMenu] - 1;
            }

         } else if(!this.confirmPopupOpen) {
            if(this.subMenu != 7 && this.subMenu != 8 && this.subMenu != 13 && this.subMenu != 14 && this.subMenu < 15) {
               if(this.selectedRow > 0) {
                  --this.selectedRow;
               } else {
                  this.selectedRow = menuItemCounts[this.subMenu] - 1;
               }

               if(this.selectedRow == 2 && this.subMenu == 0 && Status.getPlayingTime() == 0L) {
                  --this.selectedRow;
               }

               if(this.subMenu == 6) {
                  this.manualWindow.scrollUp();
                  return;
               }
            } else if(this.subMenu == 13) {
               this.hidden__.scrollDown(var1);
            }

         }
      }
   }

   public final void scrollDown(int var1) {
      if(this.var_8a1 == null) {
         if(Status.getCurrentCampaignMission() >= 15 && !this.confirmPopupOpen && (this.subMenu == 1 || this.subMenu == 2)) {
            if(this.selectedRow < this.saves.length) {
               ++this.selectedRow;
            } else {
               this.selectedRow = 0;
            }

            if(this.subMenu == 2 && this.selectedRow == menuItemCounts[this.subMenu]) {
               this.selectedRow = menuItemCounts[this.subMenu] + 1;
            }

         } else if(!this.confirmPopupOpen) {
            if(this.subMenu != 7 && this.subMenu != 8 && this.subMenu != 13 && this.subMenu != 14 && this.subMenu < 15) {
               if(this.selectedRow < menuItemCounts[this.subMenu] - 1) {
                  ++this.selectedRow;
               } else {
                  this.selectedRow = 0;
               }

               if(this.selectedRow == 2 && this.subMenu == 0 && Status.getPlayingTime() == 0L) {
                  ++this.selectedRow;
               }

               if(this.subMenu == 6) {
                  this.manualWindow.scrollDown();
                  return;
               }
            } else if(this.subMenu == 13) {
               this.hidden__.scrollUp(var1);
            }

         }
      }
   }

   public final void optionsLeft() {
      if(this.var_8a1 == null) {
         if(this.confirmPopupOpen) {
            this.confirmPopup.left();
         } else {
            if(this.subMenu == 3) {
               switch(this.selectedRow) {
               case 0:
                  GlobalStatus.cheat_mode = !GlobalStatus.cheat_mode;
				  if(GlobalStatus.cheat_mode == true)
				  {
					  GlobalStatus.MONEY_LEBOVSKI = 999999999;
				  } else {
					  GlobalStatus.MONEY_LEBOVSKI = 0;
				  }
                  this.updateMusicLevel();
                  return;
               case 1:
					--GlobalStatus.start_ship;
					if(GlobalStatus.start_ship < 0) {
						GlobalStatus.start_ship = GlobalStatus.max_ships-1;
					}
                   this.updateSoundLevel();
                  return;
               case 2:
					--GlobalStatus.newgame_ship;
					if(GlobalStatus.newgame_ship < 0) {
						GlobalStatus.newgame_ship = GlobalStatus.max_ships-1;
					}
                  return;
               case 3:
                  this.optionsRight();
				return;
				case 4:
					--GlobalStatus.textures;
					this.updateMusicLevel();
				return;
				case 5:
					--GlobalStatus.planets;
					this.updateMusicLevel();
				return;
				case 6:
					--GlobalStatus.nebulas;
					this.updateMusicLevel();
				return;
				case 7:
					--GlobalStatus.asteroid;
					this.updateMusicLevel();
				break;
				case 8:
					GlobalStatus.bigInterface = !GlobalStatus.bigInterface;
					interface_scale();
				return;
				case 9:
					GlobalStatus.FXAA = !GlobalStatus.FXAA;
				break;
				case 10:
					GlobalStatus.low_details = !GlobalStatus.low_details;
				break;
				case 11:
					GlobalStatus.screen_keyboard = !GlobalStatus.screen_keyboard;
				break;
				case 12:
					GlobalStatus.default_language = 1;
				break;
				case 13:
                  --this.soundLevel;
                  this.updateSoundLevel();
                  return;
               case 14:
                  this.optionsRight();
                  return;
			   case 15:
					GlobalStatus.developer_mode = !GlobalStatus.developer_mode;
			   break;
               }
            }

         }
      }
   }
   
   public void interface_scale() {
	   if(GlobalStatus.bigInterface == true) {
		   GlobalStatus.INTERFACE_SCALE_MULTIPLIER = 2;
	   }
	   if(GlobalStatus.bigInterface == false) {
		   GlobalStatus.INTERFACE_SCALE_MULTIPLIER = 1;
	   }
   }

   private void updateSoundLevel() {
      if(this.soundLevel < 0) {
         this.soundLevel = 3;
      }

      if(this.soundLevel > 3) {
         this.soundLevel = 0;
      }

      switch(this.soundLevel) {
      case 0:
         GlobalStatus.sfxOn = true;
         GlobalStatus.soundManager.setSfxVolume(60);
         break;
      case 1:
         GlobalStatus.sfxOn = true;
         GlobalStatus.soundManager.setSfxVolume(80);
         break;
      case 2:
         GlobalStatus.sfxOn = true;
         GlobalStatus.soundManager.setSfxVolume(100);
         break;
      case 3:
         GlobalStatus.sfxOn = false;
      }

      GlobalStatus.sfxVolume = this.soundLevel;
   }

   private void updateMusicLevel() {
      if(this.musicLevel < 0) {
         this.musicLevel = 3;
      }

      if(this.musicLevel > 3) {
         this.musicLevel = 0;
      }
	  
	  if(GlobalStatus.textures < 0)
	  {
		GlobalStatus.textures = 3;
	  }
	  
	  if(GlobalStatus.textures > 3)
	  {
		GlobalStatus.textures = 0;
	  }
	  
	  if(GlobalStatus.textures == 0)
	  {
		  GlobalStatus.texture_type[GlobalStatus.textures] = 256;
	  }
	  
	  if(GlobalStatus.planets == 0)
	  {
		  GlobalStatus.planet_size[GlobalStatus.planets] = 64;
	  }
	  
	  if(GlobalStatus.nebulas == 0)
	  {
		  GlobalStatus.nebula_size[GlobalStatus.nebulas] = 64;
	  }
	  
	  if(GlobalStatus.planets < 0)
	  {
		GlobalStatus.planets = 3;
	  }
	  
	  if(GlobalStatus.planets > 3)
	  {
		GlobalStatus.planets = 0;
	  }
	  
	  if(GlobalStatus.nebulas < 0)
	  {
		GlobalStatus.nebulas = 3;
      }
	  
	  if(GlobalStatus.nebulas > 3)
	  {
		GlobalStatus.nebulas = 0;
	  }
	  
	  if(GlobalStatus.asteroid < 0)
	  {
		GlobalStatus.asteroid = 5;
	  }
	  
	  if(GlobalStatus.asteroid > 5)
	  {
		GlobalStatus.asteroid = 0;
	  }

      switch(this.musicLevel) {
      case 0:
         GlobalStatus.musicOn = true;
         GlobalStatus.soundManager.setMusicVolume(60);
         if(GlobalStatus.applicationManager.GetCurrentApplicationModule() != GlobalStatus.scenes[2] && !SoundManager.isMusicPlaying()) {
            GlobalStatus.soundManager.playMusic(0);
         }
         break;
      case 1:
         GlobalStatus.musicOn = true;
         GlobalStatus.soundManager.setMusicVolume(80);
         break;
      case 2:
         GlobalStatus.musicOn = true;
         GlobalStatus.soundManager.setMusicVolume(100);
         if(GlobalStatus.applicationManager.GetCurrentApplicationModule() != GlobalStatus.scenes[2] && !SoundManager.isMusicPlaying()) {
            GlobalStatus.soundManager.playMusic(0);
         }
         break;
      case 3:
         SoundManager.stopMusic__();
         GlobalStatus.musicOn = false;
      }

      GlobalStatus.musicVolume = this.musicLevel;
   }

   public final void optionsRight() {
      if(this.var_8a1 == null) {
         if(this.confirmPopupOpen) {
            this.confirmPopup.right();
         } else {
            if(this.subMenu == 3) {
               switch(this.selectedRow) {
               case 0:
                  GlobalStatus.cheat_mode = !GlobalStatus.cheat_mode;
				  if(GlobalStatus.cheat_mode == true)
				  {
					  GlobalStatus.MONEY_LEBOVSKI = 999999999;
				  } else {
					  GlobalStatus.MONEY_LEBOVSKI = 0;
				  }
                  this.updateMusicLevel();
                  return;
               case 1:
					++GlobalStatus.start_ship;
					if(GlobalStatus.start_ship > GlobalStatus.max_ships-1) {
						GlobalStatus.start_ship = 0;
					}
					this.updateSoundLevel();
                  return;
               case 2:
					++GlobalStatus.newgame_ship;
					if(GlobalStatus.newgame_ship > GlobalStatus.max_ships-1) {
						GlobalStatus.newgame_ship = 0;
					}
                  break;
               case 3:
                  GlobalStatus.invertedControlsOn = !GlobalStatus.invertedControlsOn;
				break;
				case 4:
					++GlobalStatus.textures;
					this.updateMusicLevel();
				return;
				case 5:
					++GlobalStatus.planets;
					this.updateMusicLevel();
				return;
				case 6:
					++GlobalStatus.nebulas;
					this.updateMusicLevel();
				return;
				case 7:
					++GlobalStatus.asteroid;
					this.updateMusicLevel();
				break;
				case 8:
					GlobalStatus.bigInterface = !GlobalStatus.bigInterface;
					interface_scale();
				return;
				case 9:
					GlobalStatus.FXAA = !GlobalStatus.FXAA;
				break;
				case 10:
					GlobalStatus.low_details = !GlobalStatus.low_details;
				break;
				case 11:
					GlobalStatus.screen_keyboard = !GlobalStatus.screen_keyboard;
				break;
				case 12:
					GlobalStatus.default_language = 6;
				break;
				case 13:
				  ++this.soundLevel;
                  this.updateSoundLevel();
                  return;
               case 14:
                  ++this.musicLevel;
                  this.updateMusicLevel();
                  return;
			   case 15:
					GlobalStatus.developer_mode = !GlobalStatus.developer_mode;
			   break;
               }
            }

         }
      }
   }

   public final boolean goBack() {
      if(this.var_8a1 != null) {
         return false;
      } else if(this.confirmPopupOpen) {
         return false;
      } else if(this.subMenu != 0 && this.subMenu != 5) {
         if(this.subMenu == 3) {
            (new RecordHandler()).writeOptions();
            (new RecordHandler()).BOOTLOADER_RMS_WRITE();
         }

         if(this.subMenu == 7) {
            this.selectedRow = 1;
            this.subMenu = 4;
            return false;
         } else if(this.subMenu == 6) {
            this.selectedRow = 0;
            this.subMenu = 4;
            return false;
         } else if(this.subMenu >= 15) {
            this.subMenu = 6;
            return false;
         } else if(this.subMenu == 10) {
            this.selectedRow = 1;
            this.subMenu = 9;
            return false;
         } else if(this.subMenu == 8) {
            this.selectedRow = 2;
            this.subMenu = 4;
            return false;
         } else if(this.subMenu == 12) {
            this.subMenu = 12;
            return false;
         } else if(this.subMenu == 11) {
            return false;
         } else {
            if(this.subMenu > 0 || this.subMenu == 9) {
               if(this.subMenu == 9) {
                  this.selectedRow = 0;
               } else {
                  this.selectedRow = this.subMenu;
               }

               this.subMenu = 0;
               if(this.forcePauseMenu_) {
                  this.resetPauseMenu();
               }
            }

            return false;
         }
      } else {
         return true;
      }
   }

   public final boolean update1_() {
      if(this.var_8a1 != null) {
         return false;
      } else {
         this.update();
         return false;
      }
   }

   public final void reset_(int var1) {
      this.selectedRow = var1;
      this.subMenu = 0;
   }

   public final void resetPauseMenu() {
      this.subMenu = 5;
      this.selectedRow = 0;
      this.confirmPopupOpen = false;
      this.forcePauseMenu_ = true;
   }

   public final void draw() {
	   GlobalStatus.LANGUAGE_PACK();
	   this.touchButtonsControl();
      if(this.var_8a1 == null) {
		  if(this.subMenu == 0 && Status.getPlayingTime() <= 0L) {
			  GlobalStatus.graphics.drawImage(this.gameLogo, GlobalStatus.var_e75 / 2, 10, 17); // ��������� �������� ����
			  Font.drawString(GlobalStatus.gameText.getText(41) + ": " + Status.getSystem().getName(), 10, 10, 5);
			  Font.drawString(GlobalStatus.gameText.getText(40) + ": " + Status.getStation().getName(), 10, 25, 5);
			  Font.drawString(GlobalStatus.gameText.getText(219) + ": " + GlobalStatus.gameText.getText(229 + Status.getSystem().getRace()), 10, 40, 5);
		  }
         switch(this.subMenu) {
         case 0:
			// ���������� ������������� ����
			if(Status.getPlayingTime() > 0L) {
				
				Layout.drawMenuWindow(GlobalStatus.gameText.getText(67), 0, 0, GlobalStatus.var_e75, GlobalStatus.var_eb6);
				
			}
            this.saveGameItemHeight = Status.getPlayingTime() > 0L?this.fontSpacingY:0;
            //STATION_INTERFACE.drawMenuWindow(SharedVariables.lang_loader.getNamedParameter(67), var_2a, var_4d, var_5e, ((Status.sub_1086() > 0L?var_8b[this.menuWindowType]:var_8b[this.menuWindowType] - 1) + 2) * this.var_13b);
			
			// start new game
			AEButtonMainMenu[0].drawStandartButton(Globals.rectRoundedButtonNormal, Globals.rectRoundedButtonPressed, GlobalStatus.var_e75 / 2, (GlobalStatus.var_eb6 / 2) - 100); 
            Layout.drawTextItem(GlobalStatus.gameText.getText(0), AEButtonMainMenu[0].standartButtonX, AEButtonMainMenu[0].standartButtonY, windowWidth, this.selectedRow == 0);
			
			// load game
			AEButtonMainMenu[1].drawStandartButton(Globals.rectRoundedButtonNormal, Globals.rectRoundedButtonPressed, GlobalStatus.var_e75 / 2, AEButtonMainMenu[0].standartButtonY + AEButtonMainMenu[0].standartButtonHeight + 2); 
            Layout.drawTextItem(GlobalStatus.gameText.getText(1), AEButtonMainMenu[1].standartButtonX, AEButtonMainMenu[1].standartButtonY, windowWidth, this.selectedRow == 1);
			
			
            if (Status.getPlayingTime() > 0L) {
				AEButtonMainMenu[2].drawStandartButton(Globals.rectRoundedButtonNormal, Globals.rectRoundedButtonPressed, GlobalStatus.var_e75 / 2, AEButtonMainMenu[1].standartButtonY + AEButtonMainMenu[1].standartButtonHeight + 2);
				Layout.drawTextItem(GlobalStatus.gameText.getText(2), AEButtonMainMenu[2].standartButtonX, AEButtonMainMenu[2].standartButtonY, windowWidth, this.selectedRow == 2);
				
				// save game
				AEButtonMainMenu[3].drawStandartButton(Globals.rectRoundedButtonNormal, Globals.rectRoundedButtonPressed, GlobalStatus.var_e75 / 2, AEButtonMainMenu[2].standartButtonY + AEButtonMainMenu[2].standartButtonHeight + 2);
				Layout.drawTextItem(GlobalStatus.gameText.getText(3), AEButtonMainMenu[3].standartButtonX, AEButtonMainMenu[3].standartButtonY, windowWidth, this.selectedRow == 3);
			} else {
				
				// settings if save disabled
				AEButtonMainMenu[3].drawStandartButton(Globals.rectRoundedButtonNormal, Globals.rectRoundedButtonPressed, GlobalStatus.var_e75 / 2, AEButtonMainMenu[1].standartButtonY + AEButtonMainMenu[1].standartButtonHeight + 2);
				Layout.drawTextItem(GlobalStatus.gameText.getText(3), AEButtonMainMenu[3].standartButtonX, AEButtonMainMenu[3].standartButtonY, windowWidth, this.selectedRow == 3);
			}
			
			// help
			AEButtonMainMenu[4].drawStandartButton(Globals.rectRoundedButtonNormal, Globals.rectRoundedButtonPressed, GlobalStatus.var_e75 / 2, AEButtonMainMenu[3].standartButtonY + AEButtonMainMenu[3].standartButtonHeight + 2);
            Layout.drawTextItem(GlobalStatus.gameText.getText(4), AEButtonMainMenu[4].standartButtonX, AEButtonMainMenu[4].standartButtonY, windowWidth, this.selectedRow == 4);
			
			//exit
			AEButtonMainMenu[5].drawStandartButton(Globals.rectRoundedButtonNormal, Globals.rectRoundedButtonPressed, GlobalStatus.var_e75 / 2, AEButtonMainMenu[4].standartButtonY + AEButtonMainMenu[4].standartButtonHeight + 2);
            Layout.drawTextItem(GlobalStatus.gameText.getText(5), AEButtonMainMenu[5].standartButtonX, AEButtonMainMenu[5].standartButtonY, windowWidth, this.selectedRow == 5);
			
			AETelegramButton.drawStandartButton(Globals.rectRoundedButtonNormal, Globals.rectRoundedButtonPressed, GlobalStatus.var_e75 / 2, AEButtonMainMenu[5].standartButtonY + AEButtonMainMenu[5].standartButtonHeight * 2);
			Layout.drawTextItem(GlobalStatus.gameText.getText(533), AETelegramButton.standartButtonX, AETelegramButton.standartButtonY, windowWidth, this.selectedRow == 7);
			
            if(this.webAccessAvailable) {
               Layout.sub_239(this.operatorMenuEntry, posX, this.optionsListPosY + 5 * this.fontSpacingY + this.saveGameItemHeight, windowWidth, this.selectedRow == 6);
            }
            break;
         case 1:
         case 2: // ��������� ����
            int var1 = 12 * this.fontSpacingY;
            boolean var2;
            if(var2 = Status.getCurrentCampaignMission() >= 15) {
               var1 = (this.saves.length + 10) * this.fontSpacingY;
            }
			
            if(this.subMenu == 2) {
               Layout.drawMenuWindow(GlobalStatus.gameText.getText(2), 0, 0, GlobalStatus.var_e75, GlobalStatus.var_eb6);
            } else {
               Layout.drawMenuWindow(GlobalStatus.gameText.getText(1), 0, 0, GlobalStatus.var_e75, GlobalStatus.var_eb6);
            }
			AEButtonLoadNSaveGame[0].drawStandartButton(Globals.rectWideButtonNormal, Globals.rectWideButtonPressed, GlobalStatus.var_e75 / 2, 70);
			AEButtonLoadNSaveGame[1].drawStandartButton(Globals.rectWideButtonNormal, Globals.rectWideButtonPressed, GlobalStatus.var_e75 / 2, AEButtonLoadNSaveGame[0].standartButtonY + (AEButtonLoadNSaveGame[0].standartButtonHeight + 10));
			AEButtonLoadNSaveGame[2].drawStandartButton(Globals.rectWideButtonNormal, Globals.rectWideButtonPressed, GlobalStatus.var_e75 / 2, AEButtonLoadNSaveGame[1].standartButtonY + (AEButtonLoadNSaveGame[1].standartButtonHeight + 10));
			AEButtonLoadNSaveGame[3].drawStandartButton(Globals.rectWideButtonNormal, Globals.rectWideButtonPressed, GlobalStatus.var_e75 / 2, AEButtonLoadNSaveGame[2].standartButtonY + (AEButtonLoadNSaveGame[2].standartButtonHeight + 10));
			AEButtonLoadNSaveGame[4].drawStandartButton(Globals.rectWideButtonNormal, Globals.rectWideButtonPressed, GlobalStatus.var_e75 / 2, AEButtonLoadNSaveGame[3].standartButtonY + (AEButtonLoadNSaveGame[3].standartButtonHeight + 10));
			AEButtonLoadNSaveGame[5].drawStandartButton(Globals.rectWideButtonNormal, Globals.rectWideButtonPressed, GlobalStatus.var_e75 / 2, AEButtonLoadNSaveGame[4].standartButtonY + (AEButtonLoadNSaveGame[4].standartButtonHeight + 10));
			AEButtonLoadNSaveGame[6].drawStandartButton(Globals.rectWideButtonNormal, Globals.rectWideButtonPressed, GlobalStatus.var_e75 / 2, AEButtonLoadNSaveGame[5].standartButtonY + (AEButtonLoadNSaveGame[5].standartButtonHeight + 10));
			AEButtonLoadNSaveGame[7].drawStandartButton(Globals.rectWideButtonNormal, Globals.rectWideButtonPressed, GlobalStatus.var_e75 / 2, AEButtonLoadNSaveGame[6].standartButtonY + (AEButtonLoadNSaveGame[6].standartButtonHeight + 10));
			AEButtonLoadNSaveGame[8].drawStandartButton(Globals.rectWideButtonNormal, Globals.rectWideButtonPressed, GlobalStatus.var_e75 / 2, AEButtonLoadNSaveGame[7].standartButtonY + (AEButtonLoadNSaveGame[7].standartButtonHeight + 10));
			AEButtonLoadNSaveGame[9].drawStandartButton(Globals.rectWideButtonNormal, Globals.rectWideButtonPressed, GlobalStatus.var_e75 / 2, AEButtonLoadNSaveGame[8].standartButtonY + (AEButtonLoadNSaveGame[8].standartButtonHeight + 10));
			AEButtonLoadNSaveSelector.drawStandartButton(Globals.rectRoundedSmallButtonNormal, Globals.rectRoundedSmallButtonPressed, GlobalStatus.var_e75 - (AEButtonLoadNSaveSelector.standartButtonWidth / 2), AEButtonLoadNSaveGame[this.selectedRow].standartButtonY);
            for(var1 = 0; var1 < this.saves.length; ++var1) {
               this.saveInfo = var1 + 1 + ".  ";
               if(this.saves[var1] == null) {
                  this.saveInfo = this.saveInfo + GlobalStatus.gameText.getText(26);
               } else {
                  this.saveInfo = this.saveInfo + Time.timeToHM(this.saves[var1].playTime);
                  this.stationName = this.saves[var1].stationName;
                  if(var1 == 3) {
                     this.saveInfo = this.saveInfo + " " + this.stationName + " [Auto]";
                  } else {
                     this.saveInfo = this.saveInfo + " " + this.stationName;
                  }
               }
			   
			   Font.drawString(GlobalStatus.gameText.getText(35), AEButtonLoadNSaveSelector.standartButtonX - 10, AEButtonLoadNSaveSelector.standartButtonY - 10, 0);
               Layout.sub_239(this.saveInfo, AEButtonLoadNSaveGame[var1].standartButtonX - (AEButtonLoadNSaveGame[var1].standartButtonWidth / 2), AEButtonLoadNSaveGame[var1].standartButtonY - (AEButtonLoadNSaveGame[0].standartButtonHeight / 4), this.recordWindowWidth, this.selectedRow == var1);
			   
            }

            if(var2) {
				Layout.sub_239(Class_1017.sub_2b(this.subMenu == 2?59:60), GlobalStatus.var_e75 - this.recordWindowWidth >> 1, this.optionsListPosY + (this.saves.length + 1) * this.fontSpacingY, this.recordWindowWidth, this.selectedRow == this.saves.length);
            }
            break;
         case 3: // ������� "���������"
            Layout.drawMenuWindow(GlobalStatus.gameText.getText(3), 0, 0, GlobalStatus.var_e75, GlobalStatus.var_eb6); // window
			
			AEButtonCheckBoxSettings[0].drawswitchableButton(Globals.rectRoundedButtonNormal, Globals.rectRoundedButtonPressed, Globals.rectRoundedButtonInactive, GlobalStatus.var_e75 / 2, (GlobalStatus.var_eb6 / 2) - 150, GlobalStatus.cheat_mode);
            Layout.drawTextItem(GlobalStatus.gameText.getText(534) + " " + GlobalStatus.gameText.getText(GlobalStatus.cheat_mode?15:16), AEButtonCheckBoxSettings[0].switchableButtonX, AEButtonCheckBoxSettings[0].switchableButtonY, windowWidth, this.selectedRow == 0); // cheat mode
			
			AEButtonSettings[0].drawStandartButton(Globals.rectRoundedButtonNormal, Globals.rectRoundedButtonPressed, AEButtonCheckBoxSettings[0].switchableButtonX, AEButtonCheckBoxSettings[0].switchableButtonY + AEButtonCheckBoxSettings[0].switchableButtonHeight + 2);
            Layout.drawTextItem(GlobalStatus.gameText.getText(535) + " " + Status.getShip().getShipName(GlobalStatus.start_ship), AEButtonSettings[0].standartButtonX, AEButtonSettings[0].standartButtonY, windowWidth, this.selectedRow == 1); // start_ship
			
			AEButtonSettings[1].drawStandartButton(Globals.rectRoundedButtonNormal, Globals.rectRoundedButtonPressed, AEButtonSettings[0].standartButtonX, AEButtonSettings[0].standartButtonY + AEButtonSettings[0].standartButtonHeight + 2);
            Layout.drawTextItem(GlobalStatus.gameText.getText(536) + " " + Status.getShip().getShipName(GlobalStatus.newgame_ship), AEButtonSettings[1].standartButtonX, AEButtonSettings[1].standartButtonY, windowWidth, this.selectedRow == 2); // newgame_ship
			
			AEButtonCheckBoxSettings[1].drawswitchableButton(Globals.rectRoundedButtonNormal, Globals.rectRoundedButtonPressed, Globals.rectRoundedButtonInactive, AEButtonSettings[1].standartButtonX, AEButtonSettings[1].standartButtonY + AEButtonSettings[1].standartButtonHeight + 2, GlobalStatus.invertedControlsOn);
            Layout.drawTextItem(GlobalStatus.gameText.getText(14) + ": " + GlobalStatus.gameText.getText(GlobalStatus.invertedControlsOn?15:16), AEButtonCheckBoxSettings[1].switchableButtonX, AEButtonCheckBoxSettings[1].switchableButtonY, windowWidth, this.selectedRow == 3); // inversion
			
			AEButtonSettings[2].drawStandartButton(Globals.rectRoundedButtonNormal, Globals.rectRoundedButtonPressed, AEButtonCheckBoxSettings[1].switchableButtonX, AEButtonCheckBoxSettings[1].switchableButtonY + AEButtonCheckBoxSettings[1].switchableButtonHeight + 2);
			Layout.drawTextItem(GlobalStatus.gameText.getText(537) + " " + GlobalStatus.texture_size[GlobalStatus.textures], AEButtonSettings[2].standartButtonX, AEButtonSettings[2].standartButtonY, windowWidth, this.selectedRow == 4); // textures
			
			AEButtonSettings[3].drawStandartButton(Globals.rectRoundedButtonNormal, Globals.rectRoundedButtonPressed, AEButtonSettings[2].standartButtonX - AEButtonSettings[2].standartButtonWidth - 2, AEButtonSettings[2].standartButtonY);
			Layout.drawTextItem(GlobalStatus.gameText.getText(538) + " " + GlobalStatus.texture_size[GlobalStatus.planets], AEButtonSettings[3].standartButtonX, AEButtonSettings[3].standartButtonY, windowWidth, this.selectedRow == 5); // planets
			
			AEButtonSettings[4].drawStandartButton(Globals.rectRoundedButtonNormal, Globals.rectRoundedButtonPressed, AEButtonSettings[2].standartButtonX + AEButtonSettings[2].standartButtonWidth + 2, AEButtonSettings[2].standartButtonY);
			Layout.drawTextItem(GlobalStatus.gameText.getText(539) + " " + GlobalStatus.texture_size[GlobalStatus.nebulas], AEButtonSettings[4].standartButtonX, AEButtonSettings[4].standartButtonY, windowWidth, this.selectedRow == 6); // fogs
			
			
			AEButtonCheckBoxSettings[2].drawswitchableButton(Globals.rectRoundedButtonNormal, Globals.rectRoundedButtonPressed, Globals.rectRoundedButtonInactive, AEButtonSettings[2].standartButtonX, AEButtonSettings[2].standartButtonY + AEButtonSettings[2].standartButtonHeight + 2, asteroids);
			Layout.drawTextItem(GlobalStatus.gameText.getText(540) + " " + GlobalStatus.asteroid_setting[GlobalStatus.asteroid], AEButtonCheckBoxSettings[2].switchableButtonX, AEButtonCheckBoxSettings[2].switchableButtonY, windowWidth, this.selectedRow == 7); // asteroids
			
			AEButtonCheckBoxSettings[3].drawswitchableButton(Globals.rectRoundedButtonNormal, Globals.rectRoundedButtonPressed, Globals.rectRoundedButtonInactive, AEButtonCheckBoxSettings[2].switchableButtonX - AEButtonCheckBoxSettings[2].switchableButtonWidth - 2, AEButtonCheckBoxSettings[2].switchableButtonY, GlobalStatus.bigInterface);
			Layout.drawTextItem(GlobalStatus.gameText.getText(541) + " " + GlobalStatus.gameText.getText(GlobalStatus.bigInterface?15:16), AEButtonCheckBoxSettings[3].switchableButtonX, AEButtonCheckBoxSettings[3].switchableButtonY, windowWidth, this.selectedRow == 8); // BIG GUI
			
			AEButtonCheckBoxSettings[4].drawswitchableButton(Globals.rectRoundedButtonNormal, Globals.rectRoundedButtonPressed, Globals.rectRoundedButtonInactive, AEButtonCheckBoxSettings[2].switchableButtonX + AEButtonCheckBoxSettings[2].switchableButtonWidth + 2, AEButtonCheckBoxSettings[2].switchableButtonY, GlobalStatus.FXAA);
			Layout.drawTextItem(GlobalStatus.gameText.getText(542) + " " + GlobalStatus.gameText.getText(GlobalStatus.FXAA?15:16), AEButtonCheckBoxSettings[4].switchableButtonX, AEButtonCheckBoxSettings[4].switchableButtonY, windowWidth, this.selectedRow == 9); // aliasing
			
			AEButtonCheckBoxSettings[5].drawswitchableButton(Globals.rectRoundedButtonNormal, Globals.rectRoundedButtonPressed, Globals.rectRoundedButtonInactive, AEButtonCheckBoxSettings[2].switchableButtonX, AEButtonCheckBoxSettings[2].switchableButtonY + AEButtonCheckBoxSettings[2].switchableButtonHeight + 2, GlobalStatus.low_details);
			Layout.drawTextItem(GlobalStatus.gameText.getText(543) + " " + GlobalStatus.gameText.getText(GlobalStatus.low_details?15:16), AEButtonCheckBoxSettings[5].switchableButtonX, AEButtonCheckBoxSettings[5].switchableButtonY, windowWidth, this.selectedRow == 10); // effects
			
			AEButtonCheckBoxSettings[6].drawswitchableButton(Globals.rectRoundedButtonNormal, Globals.rectRoundedButtonPressed, Globals.rectRoundedButtonInactive, AEButtonCheckBoxSettings[5].switchableButtonX - AEButtonCheckBoxSettings[5].switchableButtonWidth - 2, AEButtonCheckBoxSettings[5].switchableButtonY, GlobalStatus.screen_keyboard);
			Layout.drawTextItem(GlobalStatus.gameText.getText(544) + " " + GlobalStatus.gameText.getText(GlobalStatus.screen_keyboard?15:16), AEButtonCheckBoxSettings[6].switchableButtonX, AEButtonCheckBoxSettings[6].switchableButtonY, windowWidth, this.selectedRow == 11); // touch
			
			AEButtonSettings[5].drawStandartButton(Globals.rectRoundedButtonNormal, Globals.rectRoundedButtonPressed, AEButtonCheckBoxSettings[5].switchableButtonX + AEButtonCheckBoxSettings[5].switchableButtonWidth + 2, AEButtonCheckBoxSettings[5].switchableButtonY);
			Layout.drawTextItem(GlobalStatus.gameText.getText(12) + ": ", AEButtonSettings[5].standartButtonX, AEButtonSettings[5].standartButtonY, windowWidth, this.selectedRow == 12); // language
			GlobalStatus.graphics.drawRegion(Globals.flaggen, GlobalStatus.language_flag_x, GlobalStatus.language_flag_y, 16, 11, 0, AEButtonSettings[5].standartButtonX + 50, AEButtonSettings[5].standartButtonY - 2, 3);
			
			AEButtonCheckBoxSettings[7].drawswitchableButton(Globals.rectRoundedButtonNormal, Globals.rectRoundedButtonPressed, Globals.rectRoundedButtonInactive, AEButtonCheckBoxSettings[5].switchableButtonX, AEButtonCheckBoxSettings[5].switchableButtonY + AEButtonCheckBoxSettings[5].switchableButtonHeight + 2, effects_state);
            Layout.drawTextItem(GlobalStatus.gameText.getText(7) + " " + GlobalStatus.gameText.getText(GameText.soundLevels[this.soundLevel]), AEButtonCheckBoxSettings[7].switchableButtonX, AEButtonCheckBoxSettings[7].switchableButtonY, windowWidth, this.selectedRow == 13); // sound
			
			AEButtonCheckBoxSettings[8].drawswitchableButton(Globals.rectRoundedButtonNormal, Globals.rectRoundedButtonPressed, Globals.rectRoundedButtonInactive, AEButtonCheckBoxSettings[7].switchableButtonX - AEButtonCheckBoxSettings[7].switchableButtonWidth - 2, AEButtonCheckBoxSettings[7].switchableButtonY, music_state);
			Layout.drawTextItem(GlobalStatus.gameText.getText(6) + " " + GlobalStatus.gameText.getText(GameText.soundLevels[this.musicLevel]), AEButtonCheckBoxSettings[8].switchableButtonX, AEButtonCheckBoxSettings[8].switchableButtonY, windowWidth, this.selectedRow == 14); // music
			
			AEButtonCheckBoxSettings[9].drawswitchableButton(Globals.rectRoundedButtonNormal, Globals.rectRoundedButtonPressed, Globals.rectRoundedButtonInactive, AEButtonCheckBoxSettings[7].switchableButtonX + AEButtonCheckBoxSettings[7].switchableButtonWidth + 2, AEButtonCheckBoxSettings[7].switchableButtonY, GlobalStatus.developer_mode);
			Layout.drawTextItem(GlobalStatus.gameText.getText(545) + " " + GlobalStatus.gameText.getText(GlobalStatus.developer_mode?15:16), AEButtonCheckBoxSettings[9].switchableButtonX, AEButtonCheckBoxSettings[9].switchableButtonY, windowWidth, this.selectedRow == 15); // devmode
			
			if(GlobalStatus.default_language == 1)
			{
				GlobalStatus.language_flag_x = 0;
				GlobalStatus.language_flag_y = 0;
				language_flag_image_x = 75;
			}
			if(GlobalStatus.default_language == 6)
			{
				GlobalStatus.language_flag_x = 480;
				GlobalStatus.language_flag_y = 0;
				language_flag_image_x = 50;
			}
			if(this.selectedRow == 4 || this.selectedRow == 8 || this.selectedRow == 9 || this.selectedRow == 12) // save & restart
			{
				Font.sub_14d_CENTER(GlobalStatus.gameText.getText(546), AEButtonCheckBoxSettings[7].switchableButtonX, AEButtonCheckBoxSettings[7].switchableButtonY + AEButtonCheckBoxSettings[7].switchableButtonHeight, 4);
			}
			if(this.selectedRow == 0)
			{
				if(GlobalStatus.cheat_mode == true) {
					Font.sub_14d_CENTER(GlobalStatus.gameText.getText(547), AEButtonCheckBoxSettings[7].switchableButtonX, AEButtonCheckBoxSettings[7].switchableButtonY + AEButtonCheckBoxSettings[7].switchableButtonHeight, 5);
				}
			}
			if(this.selectedRow == 15) {
				Font.sub_14d_CENTER(GlobalStatus.gameText.getText(548), AEButtonCheckBoxSettings[7].switchableButtonX, AEButtonCheckBoxSettings[7].switchableButtonY + AEButtonCheckBoxSettings[7].switchableButtonHeight, 5);
			}
            break;
         case 4: // ������
            Layout.drawMenuWindow(GlobalStatus.gameText.getText(4), 0, 0, GlobalStatus.var_e75, GlobalStatus.var_eb6);
			AEButtonHelpMenu[0].drawStandartButton(Globals.rectRoundedButtonNormal, Globals.rectRoundedButtonPressed, GlobalStatus.var_e75 / 2, (GlobalStatus.var_eb6 / 2) - AEButtonHelpMenu[0].standartButtonHeight);
            Layout.drawTextItem(GlobalStatus.gameText.getText(19), AEButtonHelpMenu[0].standartButtonX, AEButtonHelpMenu[0].standartButtonY, windowWidth, this.selectedRow == 0);
			
			AEButtonHelpMenu[1].drawStandartButton(Globals.rectRoundedButtonNormal, Globals.rectRoundedButtonPressed, AEButtonHelpMenu[0].standartButtonX, AEButtonHelpMenu[0].standartButtonY + AEButtonHelpMenu[0].standartButtonHeight + 2);
            Layout.drawTextItem(GlobalStatus.gameText.getText(20), AEButtonHelpMenu[1].standartButtonX, AEButtonHelpMenu[1].standartButtonY, windowWidth, this.selectedRow == 1);
			
			AEButtonHelpMenu[2].drawStandartButton(Globals.rectRoundedButtonNormal, Globals.rectRoundedButtonPressed, AEButtonHelpMenu[1].standartButtonX, AEButtonHelpMenu[1].standartButtonY + AEButtonHelpMenu[1].standartButtonHeight + 2);
            Layout.drawTextItem(GlobalStatus.gameText.getText(21), AEButtonHelpMenu[2].standartButtonX, AEButtonHelpMenu[2].standartButtonY, windowWidth, this.selectedRow == 2);
			
            break;
         case 5: // ����� (� �������)
            Layout.drawMenuWindow(GlobalStatus.gameText.getText(17), 0, 0, GlobalStatus.var_e75, GlobalStatus.var_eb6);
			
            AEButtonMainMenu[3].drawStandartButton(Globals.rectRoundedButtonNormal, Globals.rectRoundedButtonPressed, GlobalStatus.var_e75 / 2, (GlobalStatus.var_eb6 / 2) - AEButtonMainMenu[3].standartButtonHeight);
			Layout.drawTextItem(GlobalStatus.gameText.getText(3), AEButtonMainMenu[3].standartButtonX, AEButtonMainMenu[3].standartButtonY, windowWidth, this.selectedRow == 0);
			
            // help
			AEButtonMainMenu[4].drawStandartButton(Globals.rectRoundedButtonNormal, Globals.rectRoundedButtonPressed, GlobalStatus.var_e75 / 2, AEButtonMainMenu[3].standartButtonY + AEButtonMainMenu[3].standartButtonHeight + 2);
            Layout.drawTextItem(GlobalStatus.gameText.getText(4), AEButtonMainMenu[4].standartButtonX, AEButtonMainMenu[4].standartButtonY, windowWidth, this.selectedRow == 1);
			
			//exit
			AEButtonMainMenu[5].drawStandartButton(Globals.rectRoundedButtonNormal, Globals.rectRoundedButtonPressed, GlobalStatus.var_e75 / 2, AEButtonMainMenu[4].standartButtonY + AEButtonMainMenu[4].standartButtonHeight + 2);
            Layout.drawTextItem(GlobalStatus.gameText.getText(5), AEButtonMainMenu[5].standartButtonX, AEButtonMainMenu[5].standartButtonY, windowWidth, this.selectedRow == 2);
			
			if(Status.getCurrentCampaignMission() < 2) {
				skipSceneButton.drawStandartButton(Globals.rectRoundedButtonNormal, Globals.rectRoundedButtonPressed, GlobalStatus.var_e75 / 2, AEButtonMainMenu[5].standartButtonY + AEButtonMainMenu[5].standartButtonHeight + 2);
				Layout.drawTextItem(GlobalStatus.gameText.getText(239), skipSceneButton.standartButtonX, skipSceneButton.standartButtonY, windowWidth, this.selectedRow == 3);
			}
			
            break;
         case 6:
            Layout.drawMenuWindow(GlobalStatus.gameText.getText(19), 0, 0, GlobalStatus.var_e75, GlobalStatus.var_eb6);
            this.manualWindow.drawItems();
            this.manualWindow.drawScroll();
            break;
         case 7:
            Layout.drawMenuWindow(GlobalStatus.gameText.getText(20), 0, 0, GlobalStatus.var_e75, GlobalStatus.var_eb6);
            this.controls.draw();
            break;
         case 8:
            Layout.drawMenuWindow(GlobalStatus.gameText.getText(21), 0, 0, GlobalStatus.var_e75, GlobalStatus.var_eb6);
            Font.drawString(GlobalStatus.gameVersion, (GlobalStatus.var_e75 >> 1) - (Font.getStringWidth(GlobalStatus.gameVersion) >> 1), 10 + this.gameLogo.getHeight() + 2, 1);
            this.credits.draw();
         case 9:
         case 10:
         case 12:
         case 13:
         case 14:
         default:
            break;
         case 11:
            Layout.drawTextBox(GlobalStatus.gameText.getText(34), posX, headerHeight, windowWidth, 4 * this.fontSpacingY, true);
            this.nameInput.update(this.frameTime);
            Font.drawString(this.nameInput.getText() + this.nameInput.getNextChar(), posX + 10, this.optionsListPosY + this.fontSpacingY, 0);
            if(Layout.quickClockHigh_()) {
               Font.drawString("_", posX + 10 + Font.getStringWidth(this.nameInput.getText()), this.optionsListPosY + 2 + this.fontSpacingY, 0);
            }
            break;
         case 15:
         case 16:
         case 17:
         case 18:
         case 19:
         case 20:
         case 21:
         case 22:
         case 23:
         case 24:
         case 25:
            Layout.drawMenuWindow(GlobalStatus.gameText.getText(GameText.helpTitles[this.selectedRow]), 0, 0, GlobalStatus.var_e75, GlobalStatus.var_eb6); // ���� � ��������� ������� �������� � ���� ������
            this.instructions.draw();
         }

         if(this.confirmPopupOpen) {
            this.confirmPopup.draw();
			button_load_n_save_game_menu_touchFlag = false;
			button_main_menu_touchFlag = false;
			button_dialogue_window_touch_flag = true;
            Layout.drawFooter1("", "", false);
         } else if(this.subMenu == 0) {
           // STATION_INTERFACE.sub_604(SharedVariables.lang_loader.getNamedParameter(253), Status.sub_1086() == 0L?"":SharedVariables.lang_loader.getNamedParameter(65), false);
		   button_load_n_save_game_menu_touchFlag = true;
		   button_dialogue_window_touch_flag = false;
			Layout.drawPanelWithoutCorner("");
			if(Status.getPlayingTime() > 0L) {
				Layout.drawPanelWithoutCorner(GlobalStatus.gameText.getText(65));
			}
         } else if(this.subMenu == 5) {
            Layout.drawPanelWithoutCorner(GlobalStatus.gameText.getText(65));
         } else {
            if(this.subMenu != 11) {
               if(this.subMenu != 7 && this.subMenu != 8 && this.subMenu != 14 && this.subMenu != 13 && (this.subMenu < 15 || this.subMenu > 24)) {
                  //STATION_INTERFACE.sub_604(SharedVariables.lang_loader.getNamedParameter(35), SharedVariables.lang_loader.getNamedParameter(65), false);
				  Layout.drawPanelWithoutCorner(GlobalStatus.gameText.getText(65));
                  return;
               }
			   Layout.drawPanelWithoutCorner(GlobalStatus.gameText.getText(65));
            }

         }
      }
   }

   public final boolean handleKeystate(int var1) {
      if(this.var_8a1 != null) {
         if(!this.var_8a1.sub_124(var1)) {
            int[] var3;
            if(GlobalStatus.var_10e5 && this.subMenu == 1) {
               if(this.selectedRow == this.saves.length) {
                  GameRecord var2;
                  if((var2 = this.var_8a1.sub_33a()) == null) {
                     this.var_8a1 = null;
                     if((var3 = (new RecordHandler()).sub_3dc(5)) != null && var3.length > 0) {
                        GlobalStatus.var_1083 = var3[0] == 1;
                     }

                     return false;
                  }

                  var2.sub_b6();
               } else {
                  (new RecordHandler()).sub_70(this.selectedRow).sub_b6();
               }

               if(this.var_8a1 != null) {
                  this.var_8a1 = null;
                  if((var3 = (new RecordHandler()).sub_3dc(5)) != null && var3.length > 0) {
                     GlobalStatus.var_1083 = var3[0] == 1;
                  }
               }

               ((ModStation)((ModStation)GlobalStatus.scenes[1])).fromGameSave();
               GlobalStatus.applicationManager.SetCurrentApplicationModule(GlobalStatus.scenes[1]);
            }

            this.confirmPopupOpen = false;
            if(this.var_8a1 != null) {
               this.var_8a1 = null;
               if((var3 = (new RecordHandler()).sub_3dc(5)) != null && var3.length > 0) {
                  GlobalStatus.var_1083 = var3[0] == 1;
               }
            }
         }

         return true;
      } else {
         if(this.subMenu == 11) {
            if(var1 == 8192 && this.nameInput.getText().length() == 0) {
               this.selectedRow = 0;
               this.subMenu = 9;
            }

            if(var1 != 0 && var1 != -1) {
               this.nameInput.handleKeystate(var1);
            }
         }

         return true;
      }
   }
   
   public static void pointerPressed(int x, int y) {
	   
	   if(button_main_menu_touchFlag == true && AEButtonMainMenu != null) {
		   AEButtonMainMenu[0].standartButtonTouch(x, y);
		   AEButtonMainMenu[1].standartButtonTouch(x, y);
		   AEButtonMainMenu[2].standartButtonTouch(x, y);
		   AEButtonMainMenu[3].standartButtonTouch(x, y);
		   AEButtonMainMenu[4].standartButtonTouch(x, y);
		   AEButtonMainMenu[5].standartButtonTouch(x, y);
		   AETelegramButton.standartButtonTouch(x, y);
		   skipSceneButton.standartButtonTouch(x, y);
		   
		//   touchButtonsControl();
		//   System.out.println("MAIN_MENU: " + x + ", " + y);
	   }
	   
	   if(button_load_n_save_game_menu_touchFlag == true && AEButtonLoadNSaveGame != null && AEButtonLoadNSaveSelector != null) {
		   AEButtonLoadNSaveGame[0].standartButtonTouch(x, y);
		   AEButtonLoadNSaveGame[1].standartButtonTouch(x, y);
		   AEButtonLoadNSaveGame[2].standartButtonTouch(x, y);
		   AEButtonLoadNSaveGame[3].standartButtonTouch(x, y);
		   AEButtonLoadNSaveGame[4].standartButtonTouch(x, y);
		   AEButtonLoadNSaveGame[5].standartButtonTouch(x, y);
		   AEButtonLoadNSaveGame[6].standartButtonTouch(x, y);
		   AEButtonLoadNSaveGame[7].standartButtonTouch(x, y);
		   AEButtonLoadNSaveGame[8].standartButtonTouch(x, y);
		   AEButtonLoadNSaveGame[9].standartButtonTouch(x, y);
		   AEButtonLoadNSaveSelector.standartButtonTouch(x, y);
		//   System.out.println("Load and save: " + x + ", " + y);
	   }
	   
	   if(button_settings_touch_flag == true && AEButtonSettings != null && AEButtonCheckBoxSettings != null) {
		   AEButtonCheckBoxSettings[0].switchableButtonTouch(x, y);
		   AEButtonCheckBoxSettings[1].switchableButtonTouch(x, y);
		   AEButtonCheckBoxSettings[2].switchableButtonTouch(x, y);
		   AEButtonCheckBoxSettings[3].switchableButtonTouch(x, y);
		   AEButtonCheckBoxSettings[4].switchableButtonTouch(x, y);
		   AEButtonCheckBoxSettings[5].switchableButtonTouch(x, y);
		   AEButtonCheckBoxSettings[6].switchableButtonTouch(x, y);
		   AEButtonCheckBoxSettings[7].switchableButtonTouch(x, y);
		   AEButtonCheckBoxSettings[8].switchableButtonTouch(x, y);
		   AEButtonCheckBoxSettings[9].switchableButtonTouch(x, y);
		   AEButtonSettings[0].standartButtonTouch(x, y);
		   AEButtonSettings[1].standartButtonTouch(x, y);
		   AEButtonSettings[2].standartButtonTouch(x, y);
		   AEButtonSettings[3].standartButtonTouch(x, y);
		   AEButtonSettings[4].standartButtonTouch(x, y);
		   AEButtonSettings[5].standartButtonTouch(x, y);
	   }
	   
	   if(button_help_menu_touch_flag == true && AEButtonHelpMenu != null) {
		   AEButtonHelpMenu[0].standartButtonTouch(x, y);
		   AEButtonHelpMenu[1].standartButtonTouch(x, y);
		   AEButtonHelpMenu[2].standartButtonTouch(x, y);
	   }
	   
    }

    // ��������� ������� ���������� �������
    public static void pointerReleased(int x, int y) {
		
		if(button_main_menu_touchFlag == true && AEButtonMainMenu != null) {
			AEButtonMainMenu[0].buttonsTouchReleased(x, y);
			AEButtonMainMenu[1].buttonsTouchReleased(x, y);
			AEButtonMainMenu[2].buttonsTouchReleased(x, y);
			AEButtonMainMenu[3].buttonsTouchReleased(x, y);
			AEButtonMainMenu[4].buttonsTouchReleased(x, y);
			AEButtonMainMenu[5].buttonsTouchReleased(x, y);
			AETelegramButton.buttonsTouchReleased(x, y);
			skipSceneButton.buttonsTouchReleased(x, y);
		}
		
		if(button_load_n_save_game_menu_touchFlag == true && AEButtonLoadNSaveGame != null && AEButtonLoadNSaveSelector != null) {
			AEButtonLoadNSaveGame[0].buttonsTouchReleased(x, y);
			AEButtonLoadNSaveGame[1].buttonsTouchReleased(x, y);
			AEButtonLoadNSaveGame[2].buttonsTouchReleased(x, y);
			AEButtonLoadNSaveGame[3].buttonsTouchReleased(x, y);
			AEButtonLoadNSaveGame[4].buttonsTouchReleased(x, y);
			AEButtonLoadNSaveGame[5].buttonsTouchReleased(x, y);
			AEButtonLoadNSaveGame[6].buttonsTouchReleased(x, y);
			AEButtonLoadNSaveGame[7].buttonsTouchReleased(x, y);
			AEButtonLoadNSaveGame[8].buttonsTouchReleased(x, y);
			AEButtonLoadNSaveGame[9].buttonsTouchReleased(x, y);
			AEButtonLoadNSaveSelector.buttonsTouchReleased(x, y);
		}
		
		if(button_settings_touch_flag == true && AEButtonSettings != null && AEButtonCheckBoxSettings != null) {
			AEButtonCheckBoxSettings[0].buttonsTouchReleased(x, y);
			AEButtonCheckBoxSettings[1].buttonsTouchReleased(x, y);
			AEButtonCheckBoxSettings[2].buttonsTouchReleased(x, y);
			AEButtonCheckBoxSettings[3].buttonsTouchReleased(x, y);
			AEButtonCheckBoxSettings[4].buttonsTouchReleased(x, y);
			AEButtonCheckBoxSettings[5].buttonsTouchReleased(x, y);
			AEButtonCheckBoxSettings[6].buttonsTouchReleased(x, y);
			AEButtonCheckBoxSettings[7].buttonsTouchReleased(x, y);
			AEButtonCheckBoxSettings[8].buttonsTouchReleased(x, y);
			AEButtonCheckBoxSettings[9].buttonsTouchReleased(x, y);
			AEButtonSettings[0].buttonsTouchReleased(x, y);
			AEButtonSettings[1].buttonsTouchReleased(x, y);
			AEButtonSettings[2].buttonsTouchReleased(x, y);
			AEButtonSettings[3].buttonsTouchReleased(x, y);
			AEButtonSettings[4].buttonsTouchReleased(x, y);
			AEButtonSettings[5].buttonsTouchReleased(x, y);
		}
		
		if(button_help_menu_touch_flag == true && AEButtonHelpMenu != null) {
			AEButtonHelpMenu[0].buttonsTouchReleased(x, y);
			AEButtonHelpMenu[1].buttonsTouchReleased(x, y);
			AEButtonHelpMenu[2].buttonsTouchReleased(x, y);
		}
		
    }

}
