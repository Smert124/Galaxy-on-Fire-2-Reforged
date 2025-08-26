package GoF2;

import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

import AE.AEResourceManager;
import AE.GlobalStatus;
import AE.Math.AEMath;
import AE.PaintCanvas.Font;
import AE.PaintCanvas.ImageFactory;
import AE.Time;
import HardEngine.AEButtonManager;
import HardEngine.interface_loader;

public final class Hud {

   private String[][] actionmenuLabels = new String[][]{{"", "", "", ""}, {"", "", "", ""}, {"", "", "", ""}, {"", "", "", ""}, {"", "", "", ""}};
   private byte[][] actionmenuButtonsState;
   private static int lastWingmenAction;
   private static int drawSecondaryIcon = -1;
   private int screenMidX;
   private int hullAlertMSDx;
   private int hullAlertLSDx;
   private int var_6ec;
   private Sprite quickMenuCrosshairSprite;
   private Sprite quickMenuIconsSprite;
   private Sprite var_9b2;
   private Image logoRace;
   private int var_b13;
   private String tempLogMsg;
   private String logMsg = "";
   private boolean drawBoostIcon;
   private boolean hasShield;
   private boolean hasArmor;
   private boolean hasWeapon;
   private int var_d17;
   public boolean drawUI;
   private boolean cargoFull;
   private boolean actionmenuOpen;
   private int actionSubMenuOpen;
   private int actionmenuSelectDir;
   private boolean playerHit;
   private boolean actionmenuConverging;
   private boolean menuReady;
   private boolean settingSecondaryWeapon;
   private int menuItemSelectTick;
   private int menuItemsSpread;
   private int menuAnimStep;
   private Item[] secondaries;
   private Item[] cloakAndDrive;
   private ListItem[] logMessages;
   private int firstLogEntryLifeTime;
   private boolean displayLog_;
   private int queueScroll;
   private boolean jumpDriveSelected;
   private long L;
   public static AEButtonManager joystick;
   public static AEButtonManager autopilotButton;
   public static AEButtonManager boosterButton;
   public static AEButtonManager fireButton;
   public static AEButtonManager rocketButton;
   public static AEButtonManager cameraButton;
   public static AEButtonManager quickmenuButton;
   public static AEButtonManager pauseButton;
   public static boolean HUDTouchFlag = false;
   
   public static interface_loader[] AEGraphics;

   public Hud() {
      this.init();
   }

   private void init() {
      try {
        this.logMessages = new ListItem[27];
		
		joystick = new AEButtonManager();
		autopilotButton = new AEButtonManager();
		boosterButton = new AEButtonManager();
		fireButton = new AEButtonManager();
		rocketButton = new AEButtonManager();
		cameraButton = new AEButtonManager();
		quickmenuButton = new AEButtonManager();
		pauseButton = new AEButtonManager();
		
		AEGraphics = new interface_loader[10];
		
		for(int count = 0; count < AEGraphics.length; ++count) {
			AEGraphics[count] = new interface_loader();
		}
		
         this.quickMenuCrosshairSprite = new Sprite(Globals.quickmenuCrosshair, Globals.quickmenuCrosshair.getHeight(), Globals.quickmenuCrosshair.getHeight());
         this.quickMenuCrosshairSprite.defineReferencePixel(this.quickMenuCrosshairSprite.getWidth() >> 1, this.quickMenuCrosshairSprite.getHeight() >> 1);
         this.quickMenuIconsSprite = new Sprite(Globals.quickmenuIcons, Globals.quickmenuIcons.getHeight(), Globals.quickmenuIcons.getHeight());
         this.quickMenuIconsSprite.defineReferencePixel(this.quickMenuIconsSprite.getWidth() >> 1, this.quickMenuIconsSprite.getHeight() >> 1);
		 
         this.hullAlertMSDx = GlobalStatus.var_e75 / 2 - 21;
         this.hullAlertLSDx = this.hullAlertMSDx + 14;
         this.var_6ec = this.hullAlertLSDx + 14;
         this.screenMidX = GlobalStatus.var_e75 >> 1;
         this.drawBoostIcon = Status.getShip().getBoostDelay() > 0;
         this.hasShield = Status.getShip().getShield() > 0;
         this.hasArmor = Status.getShip().getAdditionalArmour() > 0;
         this.hasWeapon = Status.getShip().sub_4ab() > 0;
         this.actionmenuButtonsState = new byte[5][4];
         Item[] var8 = Status.getShip().getEquipment(1);
         boolean var2 = true;
         if(var8 != null) {
            for(int var3 = 0; var3 < var8.length; ++var3) {
               if(var8[var3] != null) {
                  var2 = false;
               }
            }
         }

         if(!var2) {
            this.actionmenuButtonsState[0][0] = 1;
            this.initSecondariesSubMenu();
            this.actionmenuLabels[0][0] = GlobalStatus.gameText.getText(124);
         } else {
            this.actionmenuLabels[0][0] = "";
            this.actionmenuButtonsState[0][0] = 0;
         }

         Item var6 = Status.getShip().getFirstEquipmentOfSort(21); // маскировка
         Item var9 = Status.getShip().getFirstEquipmentOfSort(18); // гипердвигатель
         if(var6 == null && var9 == null) {
            this.cloakAndDrive = null;
         } else {
            this.cloakAndDrive = new Item[var6 != null && var9 != null?2:1];
         }

         int var5 = 0;
         if(var6 != null) {
            this.actionmenuButtonsState[0][1] = 1;
            this.actionmenuLabels[0][1] = GlobalStatus.gameText.getNamedParameterItems(var6.getIndex()); // название генератора маскировки
            ++var5;
            this.cloakAndDrive[0] = var6;
         } else {
            this.actionmenuButtonsState[0][1] = 0;
            this.actionmenuLabels[0][1] = "";
         }

         if(var9 != null || Status.getShip().getKhadorIntegrated() == true) {
            this.actionmenuButtonsState[0][3] = 1;
            this.actionmenuLabels[0][3] = GlobalStatus.gameText.getNamedParameterItems(85); // двигатель Кадора в Quickmenu
            //this.var_1151[var5] = var9; // хз зачем, видимо считывает установленный двигатель? Нет, он вроде и так выше проверяется на отсутствие.
         } else {
            this.actionmenuButtonsState[0][3] = 0;
            this.actionmenuLabels[0][3] = "";
         }

         if(Status.getWingmenNames() != null) {
            this.actionmenuButtonsState[0][2] = 1;
            this.actionmenuButtonsState[3][0] = 1;
            this.actionmenuButtonsState[3][1] = 1;
            this.actionmenuButtonsState[3][2] = 1;
            this.actionmenuButtonsState[3][3] = 1;
            this.actionmenuLabels[0][2] = GlobalStatus.gameText.getText(146);
            this.actionmenuLabels[3][0] = GlobalStatus.gameText.getText(147);
            this.actionmenuLabels[3][1] = GlobalStatus.gameText.getText(148);
            this.actionmenuLabels[3][2] = GlobalStatus.gameText.getText(149);
            this.actionmenuLabels[3][3] = GlobalStatus.gameText.getText(151);
         } else {
            this.actionmenuLabels[0][2] = "";
         }

         this.logMsg = "";
         this.cargoFull = false;
         this.actionmenuOpen = false;
         this.drawUI = true;
         this.jumpDriveSelected = false;
      } catch (Exception var4) {
         var4.printStackTrace();
      }
   }

   private void initSecondariesSubMenu() {
      this.secondaries = Status.getShip().getEquipment(1);
      Item[] var1 = this.secondaries;

      for(int var2 = 0; var2 < this.actionmenuLabels[1].length; ++var2) {
         if(var2 < var1.length && var1[var2] != null && var1[var2].getAmount() > 0) {
            this.actionmenuButtonsState[1][var2] = 1;
            this.actionmenuLabels[1][var2] = var1[var2].toString() + "(" + var1[var2].getAmount() + ")";
         } else {
            if(var2 < var1.length && var1[var2] == null) {
               this.actionmenuButtonsState[1][var2] = 0;
            }

            this.actionmenuLabels[1][var2] = "";
         }
      }

   }

   public final void OnRelease() {
   }

   private void drawEventQueue() {
      int var1 = 25 - this.queueScroll;
      int var2 = ImageFactory.itemHeight - 2;
      if(this.queueScroll > 0 && this.queueScroll < var2) {
         ++this.queueScroll;
      }

      GlobalStatus.graphics.setClip(0, var2 + 25, GlobalStatus.var_e75, var2 * 2);

      for(int var3 = 0; var3 < 4; ++var3) {
         ListItem var4;
         if((var4 = this.logMessages[var3]) != null) {
            boolean var5;
            if(var5 = var4.items != null) {
               int var10002 = (GlobalStatus.var_e75 >> 1) + ImageFactory.faceWidth / 2;
               String var6 = var4.label;
               ImageFactory.drawItemFrameless(var4.itemId, var4.items, var10002 - (Font.getTextWidth(var4.label, 0) >> 1) - 5, var1 + var3 * var2 - 2, 24);
            }

            Font.drawString(var4.label, (GlobalStatus.var_e75 >> 1) + (var5?ImageFactory.faceWidth / 2 - 5:0), var1 + var3 * var2, var3 != 1 && (var3 != 2 || this.queueScroll != var2)?1:(var4.showCountItemType == 1?2:0), 24);
         }
      }

      GlobalStatus.graphics.setClip(0, 0, GlobalStatus.var_e75, GlobalStatus.var_eb6);
   }

   private void appendHudEvent(ListItem var1) {
      for(int var2 = 1; var2 < this.logMessages.length; ++var2) {
         if(this.logMessages[var2] == null) {
            this.logMessages[var2] = var1;
            this.displayLog_ = true;
            return;
         }
      }

   }

   private void updateQueue(int var1) {
      this.firstLogEntryLifeTime += var1;
      if(this.firstLogEntryLifeTime <= 4000) {
         if(this.queueScroll == 0 && this.firstLogEntryLifeTime > 2000) {
            this.queueScroll = 1;
         }

      } else {
         this.firstLogEntryLifeTime = 0;

         for(var1 = 1; var1 < this.logMessages.length; ++var1) {
            this.logMessages[var1 - 1] = this.logMessages[var1];
         }

         if(this.logMessages[1] == null) {
            this.displayLog_ = false;
         }

         this.queueScroll = 0;
      }
   }

   public final void hudEvent(int var1, PlayerEgo var2) {
      switch(var1) {
      case 1:
         if(!this.hasWeapon) {
            return;
         }

         this.tempLogMsg = GlobalStatus.gameText.getText(13) + " " + GlobalStatus.gameText.getText(15);
         break;
      case 2:
         if(!this.hasWeapon) {
            return;
         }

         this.tempLogMsg = GlobalStatus.gameText.getText(13) + " " + GlobalStatus.gameText.getText(16);
         break;
      case 3:
         if(!this.drawBoostIcon || !var2.readyToBoost()) {
            return;
         }

         this.tempLogMsg = GlobalStatus.gameText.getText(154);
         break;
      case 4:
         if(!this.drawBoostIcon) {
            return;
         }

         this.tempLogMsg = GlobalStatus.gameText.getText(155);
         break;
      case 5:
         this.tempLogMsg = GlobalStatus.gameText.getText(292) + " " + GlobalStatus.gameText.getText(15);
         break;
      case 6:
         this.tempLogMsg = GlobalStatus.gameText.getText(292) + " " + GlobalStatus.gameText.getText(16);
         break;
      case 7:
         this.tempLogMsg = GlobalStatus.gameText.getText(276);
         break;
      case 8:
         this.tempLogMsg = GlobalStatus.gameText.getText(263); // бурение прервано
         break;
      case 9:
         this.tempLogMsg = GlobalStatus.gameText.getText(264); // магнитный луч не установлен
         break;
      case 10:
         this.tempLogMsg = GlobalStatus.gameText.getText(270) + ": " + Status.getStation().getName() + " " + GlobalStatus.gameText.getText(40);
         break;
      case 11:
         this.tempLogMsg = GlobalStatus.gameText.getText(270) + ": " + GlobalStatus.gameText.getText(274);
         break;
      case 12:
         this.tempLogMsg = GlobalStatus.gameText.getText(270) + ": " + GlobalStatus.gameText.getText(271);
         break;
      case 13:
         this.tempLogMsg = GlobalStatus.gameText.getText(270) + ": " + GlobalStatus.gameText.getText(272);
         break;
      case 14:
         this.tempLogMsg = GlobalStatus.gameText.getText(270) + ": " + GlobalStatus.gameText.getText(273);
         break;
      case 15:
         this.tempLogMsg = GlobalStatus.gameText.getText(270) + ": " + GlobalStatus.gameText.getText(269);
         break;
      case 16:
         this.tempLogMsg = GlobalStatus.gameText.getText(147); // открыть огонь (сопровождение)
         break;
      case 17:
         this.tempLogMsg = GlobalStatus.gameText.getText(148);
         break;
      case 18:
         this.tempLogMsg = GlobalStatus.gameText.getText(149);
         break;
      case 19:
         this.tempLogMsg = this.actionmenuLabels[4][3];
         break;
      case 20:
         this.tempLogMsg = GlobalStatus.gameText.getText(265); // бур не установлен
         break;
      case 21:
         this.tempLogMsg = GlobalStatus.gameText.getText(254);
         break;
      case 22:
         this.tempLogMsg = GlobalStatus.gameText.getText(266); // пустой
         break;
      case 23:
         this.tempLogMsg = GlobalStatus.gameText.getText(267); // точка назначения достигнута
         break;
      case 24:
         this.tempLogMsg = GlobalStatus.gameText.getText(268); // последняя точка достигнута
      }

      if(!this.sameHudEventAsBeforeAggregate(this.tempLogMsg)) {
         this.appendHudEvent(new ListItem(this.tempLogMsg));
         String var3 = this.tempLogMsg;
         Font.getTextWidth(this.tempLogMsg, 0);
      }
   }

   private boolean sameHudEventAsBeforeAggregate(String var1) {
      for(int var2 = this.logMessages.length - 1; var2 > 0; --var2) {
         if(this.logMessages[var2] != null && this.logMessages[var2].label.equals(var1)) {
            return true;
         }
      }

      return false;
   }

   public final void playerHit() {
      this.playerHit = true;
   }
   
   public final void catchCargo(int var1, int var2, boolean var3, boolean var4, boolean var5, boolean var6) {
      this.cargoFull = var3;
      ListItem var7;
      if(var4) {
         this.logMsg = GlobalStatus.gameText.getText(261); // броне-кабина
         this.logMsg = Status.replaceTokens(this.logMsg, GlobalStatus.gameText.getNamedParameterItems((Status.getMission().getType() == 3?116:117)), "#N");
         this.logMsg = Status.replaceTokens(this.logMsg, "1", "#Q"); // Q - руда? N - количество предметов?
         (var7 = new ListItem(this.logMsg)).items = Globals.itemsImage;
         var7.itemId = var1;
      } else {
         if(var3) { // если грузовой отсек заполнился
            this.logMsg = GlobalStatus.gameText.getText(159); // грузовой отсек заполнен
            this.appendHudEvent(new ListItem(this.logMsg, 1));
            return;
         }

         if(var2 <= 0) {
            return;
         }

         if(var5) { // если подбираешь груз
            this.logMsg = var2 + "t " + GlobalStatus.gameText.getNamedParameterItems(var1);
         } else if(var6) { // сканирование груза
            this.logMsg = var2 + "t " + GlobalStatus.gameText.getNamedParameterItems(var1);
         } else {
            this.logMsg = var2 + "t " + GlobalStatus.gameText.getNamedParameterItems(var1); // сколько поднял магнитным лучем при грабеже\дрейфующее в космосе
         }

         (var7 = new ListItem(this.logMsg)).items = Globals.itemsImage;
         var7.itemId = var1;
         if(var6) {
            var7.isSelectable = true;
         }
      }

      this.appendHudEvent(var7);
   }

   public final boolean cargoFull() {
      return this.cargoFull;
   }

   public final void draw(long var1, long var3, PlayerEgo var5, boolean var6) {
	  pauseButton.drawStandartButton(Globals.pauseButtonNormal, Globals.pauseButtonPressed, GlobalStatus.var_e75 - (pauseButton.standartButtonWidth / 2) - (pauseButton.standartButtonWidth / 4), (pauseButton.standartButtonHeight / 2) + pauseButton.standartButtonHeight / 4);
	  AEGraphics[9].drawImage(Globals.cargoPanelImage, pauseButton.standartButtonX - (43 * GlobalStatus.INTERFACE_SCALE_MULTIPLIER), pauseButton.standartButtonY - (8 * GlobalStatus.INTERFACE_SCALE_MULTIPLIER), 3);
      if(this.drawUI) {
         if(this.playerHit) {
            this.playerHit = false;
         }
         int var7 = Globals.hudShieldBarFull.getWidth();
         boolean var8 = false;
         int var12;
		 
		 joystick.drawJoystick((joystick.joystickBackgroundWidth / 2) + (joystick.joystickWidth / 2), GlobalStatus.var_eb6 - ((joystick.joystickBackgroundHeight / 2) + (joystick.joystickHeight / 2)));
		 
		 if(Status.getCurrentCampaignMission() != 0) {
			 
			 AEGraphics[8].drawImage(Globals.rightPanelBackgroundImage, GlobalStatus.var_e75, GlobalStatus.var_eb6, GlobalStatus.graphics.BOTTOM | GlobalStatus.graphics.RIGHT);
			 
			 fireButton.drawStandartButton(Globals.fireButtonNormal, Globals.fireButtonPressed, GlobalStatus.var_e75 - (AEGraphics[8].getImageWidth() / 2) + (10 * GlobalStatus.INTERFACE_SCALE_MULTIPLIER), GlobalStatus.var_eb6 - (AEGraphics[8].getImageHeight() / 2) + (10 * GlobalStatus.INTERFACE_SCALE_MULTIPLIER));
			 
			 cameraButton.drawStandartButton(Globals.cameraButtonNormal, Globals.cameraButtonPressed, AEGraphics[8].getImageX() - (88 * GlobalStatus.INTERFACE_SCALE_MULTIPLIER), AEGraphics[8].getImageY() - (85 * GlobalStatus.INTERFACE_SCALE_MULTIPLIER));
			 
			 quickmenuButton.drawStandartButton(Globals.quickmenuButtonNormal, Globals.quickmenuButtonPressed, AEGraphics[8].getImageX() - (44 * GlobalStatus.INTERFACE_SCALE_MULTIPLIER), AEGraphics[8].getImageY() - (99 * GlobalStatus.INTERFACE_SCALE_MULTIPLIER));
		 }
		 
		 int hullWidth = (int)((this.var_d17 / 100.0f) * Globals.hudHullBarFull.getWidth());
		 
         if(this.hasArmor) { // armor hud
				var12 = (int)((float)var5.player.getArmorDamageRate() / 100.0F * (float)var7);
				if(!this.hasShield) {
					AEGraphics[4].drawImage(Globals.hudShipNormalIcon, Globals.hudShipNormalIcon.getWidth(), Globals.hudShipNormalIcon.getHeight(), 3);
					AEGraphics[1].drawImage(Globals.hudStatusPanel, AEGraphics[4].getImageX() + (Globals.hudShieldNormalIcon.getWidth() / 2) + (Globals.hudStatusPanel.getWidth() / 2), AEGraphics[4].getImageY(), 3);
					AEGraphics[2].drawImage(Globals.hudHullBarEmpty, AEGraphics[4].getImageX() + (Globals.hudShieldNormalIcon.getWidth() / 2) + (Globals.hudHullBarEmpty.getWidth() / 2), AEGraphics[4].getImageY(), 3);
					AEGraphics[5].drawRegion(Globals.hudHullBarFull, 0, 0, hullWidth, Globals.hudHullBarFull.getHeight(), 0, AEGraphics[4].getImageX() + (AEGraphics[4].getImageWidth() / 2), AEGraphics[4].getImageY() - (Globals.hudHullBarFull.getHeight() / 2), 0);
					AEGraphics[6].drawRegion(Globals.hudArmorFull, 0, 0, var12, Globals.hudArmorFull.getHeight(), 0, AEGraphics[4].getImageX() + (AEGraphics[4].getImageWidth() / 2), AEGraphics[4].getImageY() - (Globals.hudArmorFull.getHeight() / 2), 0);
				} else {
					AEGraphics[4].drawImage(Globals.hudShipNormalIcon, Globals.hudShipNormalIcon.getWidth(), AEGraphics[0].getImageHeight() + Globals.hudShipNormalIcon.getHeight(), 3);
					AEGraphics[1].drawImage(Globals.hudStatusPanel, AEGraphics[0].getImageX() + (Globals.hudShieldNormalIcon.getWidth() / 2) + (Globals.hudStatusPanel.getWidth() / 2), AEGraphics[4].getImageY(), 3);
					AEGraphics[2].drawImage(Globals.hudHullBarEmpty, AEGraphics[0].getImageX() + (Globals.hudShieldNormalIcon.getWidth() / 2) + (Globals.hudHullBarEmpty.getWidth() / 2), AEGraphics[4].getImageY(), 3);
					AEGraphics[5].drawRegion(Globals.hudHullBarFull, 0, 0, hullWidth, Globals.hudHullBarFull.getHeight(), 0, AEGraphics[4].getImageX() + (AEGraphics[4].getImageWidth() / 2), AEGraphics[4].getImageY() - (Globals.hudHullBarFull.getHeight() / 2), 0);
					AEGraphics[6].drawRegion(Globals.hudArmorFull, 0, 0, var12, Globals.hudArmorFull.getHeight(), 0, AEGraphics[4].getImageX() + (AEGraphics[4].getImageWidth() / 2), AEGraphics[4].getImageY() - (Globals.hudArmorFull.getHeight() / 2), 0);
				}
         }

         if(this.hasShield) { // shield hud
				var12 = (int)((float)var5.player.getShieldDamageRate() / 100.0F * (float)var7);
				AEGraphics[0].drawImage(Globals.hudShieldNormalIcon, Globals.hudShieldNormalIcon.getWidth(), Globals.hudShieldNormalIcon.getHeight(), 3);
				AEGraphics[1].drawImage(Globals.hudStatusPanel, AEGraphics[0].getImageX() + (Globals.hudShieldNormalIcon.getWidth() / 2) + (Globals.hudStatusPanel.getWidth() / 2), AEGraphics[0].getImageY(), 3);
				AEGraphics[2].drawImage(Globals.hudShieldBarEmpty, AEGraphics[0].getImageX() + (Globals.hudShieldNormalIcon.getWidth() / 2) + (Globals.hudShieldBarEmpty.getWidth() / 2), AEGraphics[0].getImageY(), 3);
				AEGraphics[3].drawRegion(Globals.hudShieldBarFull, 0, 0, var12, Globals.hudShieldBarFull.getHeight(), 0, AEGraphics[0].getImageX() + (AEGraphics[0].getImageWidth() / 2), AEGraphics[0].getImageY() - (Globals.hudShieldBarFull.getHeight() / 2), 0);
				if(!this.hasArmor) {
					AEGraphics[4].drawImage(Globals.hudShipNormalIcon, Globals.hudShipNormalIcon.getWidth(), AEGraphics[0].getImageHeight() + Globals.hudShipNormalIcon.getHeight(), 3);
					AEGraphics[1].drawImage(Globals.hudStatusPanel, AEGraphics[0].getImageX() + (Globals.hudShieldNormalIcon.getWidth() / 2) + (Globals.hudStatusPanel.getWidth() / 2), AEGraphics[4].getImageY(), 3);
					AEGraphics[2].drawImage(Globals.hudHullBarEmpty, AEGraphics[0].getImageX() + (Globals.hudShieldNormalIcon.getWidth() / 2) + (Globals.hudHullBarEmpty.getWidth() / 2), AEGraphics[4].getImageY(), 3);
					AEGraphics[5].drawRegion(Globals.hudHullBarFull, 0, 0, hullWidth, Globals.hudHullBarFull.getHeight(), 0, AEGraphics[4].getImageX() + (AEGraphics[4].getImageWidth() / 2), AEGraphics[4].getImageY() - (Globals.hudHullBarFull.getHeight() / 2), 0);
				}
         }
		 
		 if(!this.hasShield && !this.hasArmor) {
			AEGraphics[4].drawImage(Globals.hudShipNormalIcon, Globals.hudShipNormalIcon.getWidth(), Globals.hudShipNormalIcon.getHeight(), 3);
			AEGraphics[1].drawImage(Globals.hudStatusPanel, AEGraphics[4].getImageX() + (Globals.hudShieldNormalIcon.getWidth() / 2) + (Globals.hudStatusPanel.getWidth() / 2), AEGraphics[4].getImageY(), 3);
			AEGraphics[2].drawImage(Globals.hudHullBarEmpty, AEGraphics[4].getImageX() + (Globals.hudShieldNormalIcon.getWidth() / 2) + (Globals.hudHullBarEmpty.getWidth() / 2), AEGraphics[4].getImageY(), 3);
			AEGraphics[5].drawRegion(Globals.hudHullBarFull, 0, 0, hullWidth, Globals.hudHullBarFull.getHeight(), 0, AEGraphics[4].getImageX() + (AEGraphics[4].getImageWidth() / 2), AEGraphics[4].getImageY() - (Globals.hudHullBarFull.getHeight() / 2), 0);
		 }
		 
		 if(this.hasArmor || this.hasShield)
		 {
				//SharedVariables.graphics.drawImage(armor, 0, 17, 0);
				//SharedVariables.graphics.drawImage(panel_shield, 0, this.armor.getHeight() + 17, 0);
		 }

         if(!this.settingSecondaryWeapon && drawSecondaryIcon > 0 && (var7 = var5.getCurrentSecondaryWeaponIndex()) >= 0 && this.secondaries[drawSecondaryIcon - 1] != null) {
			AEGraphics[7].drawImage(Globals.panelInfoLower, GlobalStatus.var_e75 / 2, GlobalStatus.var_eb6 - AEGraphics[7].getImageHeight() + 9, 3);
			rocketButton.drawStandartButton(Globals.rocketButtonNormal, Globals.rocketButtonPressed, AEGraphics[8].getImageX() - (99 * GlobalStatus.INTERFACE_SCALE_MULTIPLIER), AEGraphics[8].getImageY() - (33 * GlobalStatus.INTERFACE_SCALE_MULTIPLIER));
            Font.sub_14d_CENTER(GlobalStatus.gameText.getNamedParameterItems(var7) + " (" + this.secondaries[drawSecondaryIcon - 1].getAmount() + ")", AEGraphics[7].getImageX(), AEGraphics[7].getImageY() - 7, 1); // количество ракет
         }
		 
         if(this.drawBoostIcon) {
			boosterButton.drawswitchableButton(Globals.boosterNormal, Globals.boosterPressed, Globals.boosterInactive, joystick.AEGraphics[0].getImageX() + (joystick.AEGraphics[0].getImageWidth()) + (boosterButton.switchableButtonWidth), GlobalStatus.var_eb6 - (boosterButton.switchableButtonHeight), var5.readyToBoost());
         }
		 if(Status.getCurrentCampaignMission() != 0) {
			autopilotButton.drawswitchableButton(var5.isAutoPilot() ? Globals.autopilotPressed : Globals.autopilotNormal, Globals.autopilotPressed, Globals.autopilotNormal, autopilotButton.switchableButtonWidth, joystick.AEGraphics[0].getImageY() - ((joystick.AEGraphics[0].getImageHeight()) + autopilotButton.switchableButtonHeight), var5.isAutoPilot()); // autopilot button
		 }
		 if(GlobalStatus.screen_keyboard == false)
		 {
			//this.var_7ba.setPosition(4 + this.var_ee4, 2);
			if(this.logMessages[1] == null || !this.logMessages[1].label.equals(GlobalStatus.gameText.getText(276)) || Layout.quickClockHigh_()) {
				//this.var_7ba.paint(SharedVariables.graphics);
			}
		 }

         if(this.hasWeapon) {
            if(var6) { // autofire
			   if(GlobalStatus.screen_keyboard == false)
			   {
				//this.var_7ba.setFrame(14);
			   }
			   if(GlobalStatus.screen_keyboard == true)
			   {
				//IL.draw_autofire_ON();
			   }
            } else {
			   if(GlobalStatus.screen_keyboard == false)
			   {
				//this.var_7ba.setFrame(15);
			   }
			    if(GlobalStatus.screen_keyboard == true)
			   {
				//IL.draw_autofire();
			   }
            }
			if(GlobalStatus.screen_keyboard == false)
			{
				//this.var_7ba.setPosition(2, 2);
				//this.var_7ba.paint(SharedVariables.graphics);
			}
         }

         if(var5.isCloakPresent()) {
            /** this.var_7ba.setFrame(12);
            this.var_7ba.setPosition(SharedVariables.var_e75 - 2 - this.var_ee4, 2);
            this.var_7ba.paint(SharedVariables.graphics); **/
            if(var5.isCloaked()) {
               //SharedVariables.graphics.setClip(SharedVariables.var_e75 - 2 - this.var_ee4, var7 + 2 - (int)((1.0F - var5.sub_40f()) * (float)var7), var7, var7);
            } else {
               //SharedVariables.graphics.setClip(SharedVariables.var_e75 - 2 - this.var_ee4, var7 + 2 - (int)(var5.sub_40f() * (float)var7), var7, var7);
            }

            /** this.var_7ba.setFrame(11);
            this.var_7ba.setPosition(SharedVariables.var_e75 - 2 - this.var_ee4, 2);
            this.var_7ba.paint(SharedVariables.graphics);
            SharedVariables.graphics.setClip(0, 0, SharedVariables.var_e75, SharedVariables.var_eb6);
			**/
         }

         /* this.var_7ba.setFrame(17);
         this.var_7ba.setPosition(SharedVariables.var_e75 - 4 - 2 * this.var_ee4, 2);
         this.var_7ba.paint(SharedVariables.graphics); */
         if(Status.getShip().getFreeCargo() > 0) {
           // this.var_7ba.setFrame(7);
         } else {
            //this.var_7ba.setFrame(9);
         }
		 if(GlobalStatus.screen_keyboard == false) // cargo
		 {
			//this.var_7ba.setPosition(SharedVariables.var_e75 - 2 - this.var_ee4, 4 + this.var_ee4);
			//this.var_7ba.paint(SharedVariables.graphics);
		 }
		 if(GlobalStatus.screen_keyboard == true) // cargo
		 {
			//this.var_7ba.setPosition(2, 2);
			//this.var_7ba.paint(SharedVariables.graphics);
		 }
         int var10;
         if(this.settingSecondaryWeapon) {
            var10 = this.actionmenuSelectDir;
            this.menuAnimStep = (int)((long)this.menuAnimStep - var1 / 10L);
            boolean var11 = false;
            if(this.menuAnimStep <= 0) {
               this.menuAnimStep = 0;
               var11 = true;
            }

            var12 = (GlobalStatus.var_e75 >> 1) + (var10 == 2?this.menuAnimStep:(var10 == 4?-this.menuAnimStep:0));
            var10 = (GlobalStatus.var_eb6 >> 1) + (var10 == 1?-this.menuAnimStep:(var10 == 3?this.menuAnimStep:0));
            this.quickMenuIconsSprite.setRefPixelPosition(var12, var10);
            this.quickMenuIconsSprite.setFrame(2);
            if(!var11 || var11 && Layout.quickClockHigh_()) {
               this.quickMenuIconsSprite.paint(GlobalStatus.graphics);
               if(this.actionSubMenuOpen == 1) {
                  if((var7 = var5.getCurrentSecondaryWeaponIndex()) >= 0) {
                     ImageFactory.drawItemFrameless(var7, Globals.itemsImage, var12, var10, 3); // что выбрал в быстром меню
                  }
               } else if(this.actionSubMenuOpen == 3) {
                  this.quickMenuIconsSprite.setRefPixelPosition(var12, var10);
                  this.quickMenuIconsSprite.setFrame(6 + this.actionmenuSelectDir);
                  this.quickMenuIconsSprite.paint(GlobalStatus.graphics);
               }
            }

            this.menuItemSelectTick = (int)((long)this.menuItemSelectTick + var1);
            if(this.menuItemSelectTick > 1500) {
               this.settingSecondaryWeapon = false;
            }
         }

         if(var3 > 0L) {
            Font.drawString(Time.timeToHMS(var3), GlobalStatus.var_e75 >> 1, 2, var3 < 10000L?2:1, 24);
         } else if(Status.getMission().getType() == 12) {
            Font.drawString(var5.level.var_633 + " : " + var5.level.var_614, GlobalStatus.var_e75 >> 1, 2, 1, 24);
         }
		 boolean cargoFull;
		 if((cargoFull = Status.getShip().getCurrentLoad() >= Status.getShip().getCargoPlus()) && Layout.quickClockHigh_() || !cargoFull) {
			 Font.drawString(Status.getShip().getCurrentLoad() + "/" + Status.getShip().getCargoPlus() + "t", AEGraphics[9].getImageX() - 3, AEGraphics[9].getImageY() - 7, cargoFull?4:1, 24);
		 }

         if(this.displayLog_) {
            this.updateQueue((int)var1);
            this.drawEventQueue();
         }

         var10 = var5.getHullDamageRate();
         if(this.var_d17 > var10) {
            this.var_b13 = 0;
         }

         /* if(var10 < 100 && this.var_b13 < 3000) {
            if(STATION_INTERFACE.sub_167()) { // Отображение hud_hull_alarm + _numbers.
               this.var_b13 = (int)((long)this.var_b13 + var1);
               SharedVariables.graphics.drawImage(this.hud_hull_alarmImage, this.var_65c, 41, 17);
               this.var_9b2.setFrame(var10 / 10);
               this.var_9b2.setPosition(this.var_6b8, 55);
               this.var_9b2.paint(SharedVariables.graphics);
               this.var_9b2.setFrame(var10 % 10);
               this.var_9b2.setPosition(this.var_6cc, 55);
               this.var_9b2.paint(SharedVariables.graphics);
               this.var_9b2.setFrame(10);
               this.var_9b2.setPosition(this.var_6ec, 55);
               this.var_9b2.paint(SharedVariables.graphics);
            }
         } else if(var10 < 100 && this.var_b13 < 3000 && Status.GetQuestStage() > 1) { // Если оболочка меньше 100%, и броня меньше 3000%, то..
            SharedVariables.graphics.drawImage(this.hud_hull_alarm_shipiconImage, (SharedVariables.var_e75 >> 1) - 4, SharedVariables.var_eb6 - this.var_990.getHeight() + 15, 40);
            Class_ba6.sub_18e(var10 + "%", SharedVariables.var_e75 >> 1, SharedVariables.var_eb6 - this.var_990.getHeight() + 15, 0, 33);
         } **/
         this.var_d17 = var10;
      }
   }

   public final void drawBigDigits(int var1, int var2, int var3, boolean var4) { // hud hull alarm numbers
      if(var1 > 9) {
         /**this.var_9b2.setFrame(var1 / 10);
         this.var_9b2.setPosition(var2, var3);
         this.var_9b2.paint(SharedVariables.graphics); **/
      }
	  /**
      this.var_9b2.setFrame(var1 % 10);
      this.var_9b2.setPosition(var2 + 14, var3);
      this.var_9b2.paint(SharedVariables.graphics);
	  **/
   }

   public final void drawOrbitInformation() {
      if(!Status.inAlienOrbit()) {
         if(this.logoRace == null) {
            this.logoRace = AEResourceManager.getImage(14 + Status.getSystem().getRace());
         }

         int var1 = this.logoRace.getWidth() + 6;
		 int font_color = 1;
		switch(Status.getSystem().getSafety()) {
			case 0:
				font_color = 4;
			break;
			case 1:
				font_color = 2;
			break;
			case 2:
				font_color = 2;
			break;
			case 3:
				font_color = 6;
			break;
		}
         GlobalStatus.graphics.drawImage(this.logoRace, 3, 3, 20);
         Font.drawString(Status.getStation().getName(), var1, 3, 0);
         Font.drawString(Status.getSystem().getName() + " " + GlobalStatus.gameText.getText(41), var1, 3 + Font.getFontSpacingY(), 1);
         Font.drawString(GlobalStatus.gameText.getText(220) + ": " + GlobalStatus.gameText.getText(225 + Status.getSystem().getSafety()), var1, 3 + 2 * Font.getFontSpacingY(), font_color);
      }

   }

   public final void drawMenu_(int var1) {
      int var2 = GlobalStatus.var_eb6 >> 1;
      int var3 = GlobalStatus.var_e75 >> 1;
      if(this.actionmenuConverging) {
         if(this.menuItemSelectTick > 25) {
            this.menuItemSelectTick -= 25;
            this.quickMenuCrosshairSprite.nextFrame();
         }

         this.menuItemSelectTick += var1;
         this.quickMenuCrosshairSprite.setRefPixelPosition(var3, var2);
         this.quickMenuCrosshairSprite.paint(GlobalStatus.graphics);
      } else {
         if(this.menuReady) {
            int var4 = (this.quickMenuCrosshairSprite.getHeight() >> 1) + 5 + (this.quickMenuIconsSprite.getHeight() >> 1);
            if(this.actionSubMenuOpen == 0) {
               this.quickMenuCrosshairSprite.setRefPixelPosition(var3, var2);
               this.quickMenuCrosshairSprite.paint(GlobalStatus.graphics);
               var4 = (this.quickMenuCrosshairSprite.getHeight() >> 1) + 5 + (this.quickMenuIconsSprite.getHeight() >> 1);
            } else {
               if(this.menuAnimStep > 0) {
                  this.menuAnimStep -= var1 / 3;
                  if(this.menuAnimStep < 0) {
                     this.menuAnimStep = 0;
                  }
               }

               this.quickMenuCrosshairSprite.setRefPixelPosition(var3, var2);
               this.quickMenuCrosshairSprite.paint(GlobalStatus.graphics);
            }

            if(this.menuItemsSpread > var4) {
               this.menuItemsSpread -= var1;
               if(this.menuItemsSpread < var4) {
                  this.menuItemsSpread = var4;
               }
            }

            Item[] var7 = null;
            if(this.actionSubMenuOpen == 1) {
               var7 = Status.getShip().getEquipment(1);
            } else {
               var7 = this.cloakAndDrive;
            }

            for(var4 = 0; var4 < 4; ++var4) {
               this.quickMenuIconsSprite.setFrame(this.actionmenuButtonsState[this.actionSubMenuOpen][var4]);
               if(var4 == this.actionmenuSelectDir - 1) {
                  this.quickMenuIconsSprite.setFrame(2);
               }

               int var5 = var3 + (var4 == 1?this.menuItemsSpread:(var4 == 3?-this.menuItemsSpread:0));
               int var6 = var2 + (var4 == 0?-this.menuItemsSpread:(var4 == 2?this.menuItemsSpread:0));
               this.quickMenuIconsSprite.setRefPixelPosition(var5, var6);
               this.quickMenuIconsSprite.paint(GlobalStatus.graphics);
               if(this.actionSubMenuOpen == 0) {
                  if(this.actionmenuButtonsState[this.actionSubMenuOpen][var4] > 0) {
                     this.quickMenuIconsSprite.setFrame(var4 + 3);
                     this.quickMenuIconsSprite.paint(GlobalStatus.graphics);
                  }
               } else if(this.actionSubMenuOpen == 1) {
                  if(var4 < var7.length && var7[var4] != null && var7[var4].getAmount() > 0) {
                     ImageFactory.drawItemFrameless(var7[var4].getIndex(), Globals.itemsImage, var5, var6, 3);
                     Font.drawString("x" + var7[var4].getAmount(), var5, var6 + (ImageFactory.itemHeight >> 1), 1, 24); // ракет в быстром меню
                  }
               } else if(this.actionSubMenuOpen == 3) {
                  this.quickMenuIconsSprite.setFrame(var4 + 7);
                  this.quickMenuIconsSprite.paint(GlobalStatus.graphics);
               }
            }

            if(this.actionmenuSelectDir > 0) {
               Font.drawString(this.actionmenuLabels[this.actionSubMenuOpen][this.actionmenuSelectDir - 1], var3, 10, 0, 24);
            }

            if(Status.var_bea > 0) {
               Font.drawString(GlobalStatus.gameText.getText(152) + ": " + Time.timeToHMS((long)Status.var_bea), var3, GlobalStatus.var_eb6 - 30, 0, 24);
            }
         }

      }
   }

   public final boolean handleActionMenuKeypress(int var1, Level var2, Radar var3) {
      if((var1 == 8192) || quickmenuButton.getStandartButtonPressed()) {
         if(this.actionmenuOpen && this.menuReady) {
            this.actionmenuOpen = false;
            this.actionmenuConverging = false;
            return this.actionmenuOpen;
         }

         if(!this.actionmenuConverging) {
            this.settingSecondaryWeapon = false;
            this.menuItemSelectTick = 0;
            this.actionmenuSelectDir = 0;
            this.menuItemsSpread = AEMath.max(GlobalStatus.var_e75, GlobalStatus.var_eb6) + this.quickMenuIconsSprite.getHeight();
            this.quickMenuCrosshairSprite.setFrame(0);
            this.menuReady = false;
            this.actionmenuConverging = true;
            this.menuItemSelectTick = 0;
            this.actionmenuOpen = true;
            GlobalStatus.soundManager.playSfx(4);
            return this.actionmenuOpen;
         }
      }

      if(this.actionmenuConverging && this.quickMenuCrosshairSprite.getFrame() >= this.quickMenuCrosshairSprite.getRawFrameCount() - 1) {
         this.actionmenuConverging = false;
         this.menuReady = true;
         this.actionSubMenuOpen = 0;
         return this.actionmenuOpen;
      } else {
         int var4 = this.actionSubMenuOpen;
         if(this.actionmenuOpen && this.menuReady) {
            int var5 = this.actionmenuSelectDir;
            if(this.actionmenuSelectDir > 0 && var1 == 256) {
               var1 = this.actionmenuSelectDir == 1?2:(this.actionmenuSelectDir == 2?32:(this.actionmenuSelectDir == 3?64:4));
            }

            if(this.actionmenuSelectDir == 0 || this.actionmenuSelectDir == 1 && var1 != 2 || this.actionmenuSelectDir == 2 && var1 != 32 || this.actionmenuSelectDir == 3 && var1 != 64 || this.actionmenuSelectDir == 4 && var1 != 4) {
               switch(var1) {
               case 2:
                  this.actionmenuSelectDir = 1;
                  GlobalStatus.soundManager.playSfx(4);
                  break;
               case 4:
                  this.actionmenuSelectDir = 4;
                  GlobalStatus.soundManager.playSfx(4);
                  break;
               case 32:
                  this.actionmenuSelectDir = 2;
                  GlobalStatus.soundManager.playSfx(4);
                  break;
               case 64:
                  this.actionmenuSelectDir = 3;
                  GlobalStatus.soundManager.playSfx(4);
                  break;
               case 256:
                  if(this.actionmenuSelectDir == 0) {
                     this.handleActionMenuKeypress(8192, var2, var3);
                  }
               }

               if(this.actionmenuSelectDir == 0 && this.actionmenuSelectDir == 3 && !var2.getPlayer().isCloaked() && !var2.getPlayer().isCloakReady() || this.actionmenuSelectDir > 0 && this.actionmenuLabels[this.actionSubMenuOpen][this.actionmenuSelectDir - 1].equals("")) {
                  this.actionmenuSelectDir = var5;
               }

               return this.actionmenuOpen;
            }

            switch(this.actionSubMenuOpen) {
            case 1:
               if(var1 != 2 && var1 != 32 && var1 != 64 && var1 != 4 && var1 != 256) {
                  this.actionmenuOpen = true;
               } else {
                  var5 = var1 == 2?0:(var1 == 32?1:(var1 == 64?2:(var1 == 4?3:this.actionmenuSelectDir)));
                  if(!this.actionmenuLabels[1][var5].equals("")) {
                     var1 = Status.getShip().getEquipment(1)[var5].getIndex();
                     var2.getPlayer().setCurrentSecondaryWeaponIndex(Status.getShip().getEquipment(1)[var5].getIndex());
                     GlobalStatus.displayedSecondary = var1;
                     drawSecondaryIcon = this.actionmenuSelectDir;
                  }

                  this.actionmenuOpen = false;
               }
               break;
            case 3:
               KIPlayer[] var6;
               if((var6 = var2.getEnemies()) != null) {
                  lastWingmenAction = var1 == 2?2:(var1 == 32?4:(var1 == 64?3:(var1 == 4?1:0)));
                  this.hudEvent(lastWingmenAction == 2?16:(lastWingmenAction == 4?17:(lastWingmenAction == 1?19:18)), var2.getPlayer());
                  this.actionmenuLabels[3][3] = this.actionmenuLabels[3][3].equals(GlobalStatus.gameText.getText(151))?GlobalStatus.gameText.getText(150):GlobalStatus.gameText.getText(151);

                  for(var1 = 0; var1 < var6.length; ++var1) {
                     if(var6[var1].isWingman() && !var6[var1].isDead()) {
                        if(lastWingmenAction == 0) {
                           this.actionmenuOpen = true;
                        } else {
                           var6[var1].setWingmanCommand(lastWingmenAction, lastWingmenAction == 4?var3.getLockedEnemy():null);
                        }
                     }
                  }

                  this.actionmenuOpen = false;
               }
               break;
            default:
               switch(var1) {
               case 2:
                  if(!this.actionmenuLabels[0][0].equals("")) {
                     this.initSecondariesSubMenu();
                     this.actionSubMenuOpen = 1;
                     this.actionmenuOpen = true;
                     GlobalStatus.soundManager.playSfx(4);
                  }
                  break;
               case 4:
                  if(!Status.getMission().isEmpty() && Status.getMission().getType() != 11 && Status.getMission().getType() != 0 && Status.getMission().getType() != 23) {
                     this.hudEvent(21, var2.getPlayer());
                     GlobalStatus.soundManager.playSfx(4);
                  } else if(!this.actionmenuLabels[0][3].equals("")) {
                     this.jumpDriveSelected = true;
                     GlobalStatus.soundManager.playSfx(4);
                  }

                  this.actionmenuOpen = false;
                  break;
               case 32:
                  if(!this.actionmenuLabels[0][1].equals("")) {
                     var2.getPlayer().toggleCloaking_();
                     GlobalStatus.soundManager.playSfx(4);
                  }

                  this.actionmenuOpen = false;
                  break;
               case 64:
                  if(!this.actionmenuLabels[0][2].equals("")) {
                     this.actionSubMenuOpen = 3;
                     this.actionmenuOpen = true;
                     GlobalStatus.soundManager.playSfx(4);
                  }
                  break;
               case 256:
                  this.actionmenuOpen = false;
               }
            }

            if(!this.actionmenuOpen) {
               this.settingSecondaryWeapon = this.actionSubMenuOpen != 0 || this.actionmenuSelectDir != 2 && this.actionmenuSelectDir != 4;
               this.menuAnimStep = (this.quickMenuCrosshairSprite.getHeight() >> 1) + 5 + (this.quickMenuIconsSprite.getHeight() >> 1);
               this.menuItemSelectTick = 0;
            }
         }

         if(this.actionSubMenuOpen != var4) {
            this.actionmenuSelectDir = 0;
            this.menuItemsSpread = AEMath.max(GlobalStatus.var_e75, GlobalStatus.var_eb6) + this.quickMenuIconsSprite.getHeight();
            this.menuAnimStep = (this.quickMenuCrosshairSprite.getHeight() >> 1) + 5 + (this.quickMenuIconsSprite.getHeight() >> 1);
         }

         return this.actionmenuOpen;
      }
   }
   
   public static void pointerPressed(int x, int y) {
	   if(joystick != null && HUDTouchFlag) {
		   joystick.joystickTouch(x, y);
	   }
	   if(autopilotButton != null && HUDTouchFlag) {
		   autopilotButton.switchableButtonTouch(x, y);
	   }
	   if(boosterButton != null && HUDTouchFlag) {
		   boosterButton.switchableButtonTouch(x, y);
	   }
	   if(fireButton != null && HUDTouchFlag) {
		   fireButton.standartButtonTouch(x, y);
	   }
	   if(rocketButton != null && HUDTouchFlag) {
		   rocketButton.standartButtonTouch(x, y);
	   }
	   
	   if(cameraButton != null && HUDTouchFlag) {
		   cameraButton.standartButtonTouch(x, y);
	   }
	   if(quickmenuButton != null && HUDTouchFlag) {
		   quickmenuButton.standartButtonTouch(x, y);
	   }
	   if(pauseButton != null && HUDTouchFlag) {
		   pauseButton.standartButtonTouch(x, y);
	   }
   }
   
   public static void pointerDragged(int x, int y) {
	   if(joystick != null && HUDTouchFlag) {
		   joystick.joystickDrag(x, y);
	   }
   }
   
   public static void pointerReleased(int x, int y) {
	   if(joystick != null && HUDTouchFlag) {
		   joystick.buttonsTouchReleased(x, y);
	   }
	   if(autopilotButton != null && HUDTouchFlag) {
		   autopilotButton.buttonsTouchReleased(x, y);
	   }
	   if(boosterButton != null && HUDTouchFlag) {
		   boosterButton.buttonsTouchReleased(x, y);
	   }
	   if(fireButton != null && HUDTouchFlag) {
		   fireButton.buttonsTouchReleased(x, y);
	   }
	   if(rocketButton != null && HUDTouchFlag) {
		   rocketButton.buttonsTouchReleased(x, y);
	   }
	   
	   if(rocketButton != null && HUDTouchFlag) {
		   cameraButton.buttonsTouchReleased(x, y);
	   }
	   if(quickmenuButton != null && HUDTouchFlag) {
		   quickmenuButton.buttonsTouchReleased(x, y);
	   }
	   if(pauseButton != null && HUDTouchFlag) {
		   pauseButton.buttonsTouchReleased(x, y);
	   }
   }

   public final boolean getJumpDriveSelected() {
      return this.jumpDriveSelected;
   }

   public final void setJumpDriveSelected(boolean var1) {
      this.jumpDriveSelected = false;
   }

}
