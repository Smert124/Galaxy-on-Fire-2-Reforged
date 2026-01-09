/**
** @class Interface_key
** Отрисовывает сенсорные клавиши в интерфейсе.
**/

package GoF2;

import javax.microedition.lcdui.Image;
//import HardEngine.interface_loader;

import AE.AEResourceManager;
import AE.GlobalStatus;
import AE.PaintCanvas.Font;
import HardEngine.AEButtonManager;
import HardEngine.interface_loader;

public final class Layout {

   public static int var_ce = -9783452;
   public static int var_115 = -14071252;
   public static int uiInnerOutlineColor = -14328435;
   public static int uiOuterTopRightOutlineColor = -14398096;
   public static int uiOuterDownLeftOutlineInnerLabalBgColor = -15914687;
   public static int uiInactiveInnerLabelColor = -14935012;
   private static int footerHeight;
   private static Image lock;
   private static Image menuHeaderPattern;
   private static Image menu_panel_bar;
   
   private static int[] backgroundDimmColors;
   private static long quickTickCounter;
   private static long slowTickCounter;
   private static int naviDelay;
   private static boolean leftButtonSelected;
   private static boolean tickHighlight;
   
   public static AEButtonManager[] AENavigationButton;
   public static AEButtonManager dialogueButtonNext;
   public static AEButtonManager dialogueButtonBack;
   public static AEButtonManager dialogueButtonSkip;
   public static AEButtonManager hintsOKButton;
   public static boolean navigationButtonTouchFlag = false;
   public static boolean departButtonTouchFlag = false;
   public static boolean dialogueButtonsTouchFlag = false;
   private static interface_loader AEGraphics;
   
   private static Image lockImage;
   private static Image menuSkipImage;
   private static Image menuBackgroundImage;
   public static Image menuMainCornerImage;
   private static Image menuMainPanelUpperImage;
   private static Image menuMainPanelLowerImage;
   private static Image menuPanelCornerLeftImage;
   private static Image menuLowerPanelSolidImage;
   private static Image menuBackgroundTextBox;

   public static void OnRelease() {
      backgroundDimmColors = null;
   }

   public static void OnInitialize() {
      footerHeight = 10;
		  // menu_header_pattern_image = DECRYPTOR.DECRYPT_IMAGE("/Resource/interface/menu_header_pattern.png", false);
		  
		  AEGraphics = new interface_loader();
		  dialogueButtonNext = new AEButtonManager();
		  dialogueButtonBack = new AEButtonManager();
		  dialogueButtonSkip = new AEButtonManager();
		  hintsOKButton = new AEButtonManager();
		  
		  AENavigationButton = new AEButtonManager[2];
		  for(int countButton = 0; countButton < 2; ++countButton) {
			  if(AENavigationButton[countButton] == null) {
				  AENavigationButton[countButton] = new AEButtonManager();
			  }
		  }
		  
		  lockImage = AEResourceManager.getImage(119);
		  menuSkipImage = AEResourceManager.getImage(125);
		  menuBackgroundImage = AEResourceManager.getImage(126);
		  menuMainCornerImage = AEResourceManager.getImage(127);
		  menuMainPanelUpperImage = AEResourceManager.getImage(128);
		  menuMainPanelLowerImage = AEResourceManager.getImage(129);
		  menuPanelCornerLeftImage = AEResourceManager.getImage(130);
		  menuLowerPanelSolidImage = AEResourceManager.getImage(131);
		  menuBackgroundTextBox = AEResourceManager.getImage(150);
		  
		  menuHeaderPattern = menuMainPanelUpperImage;
		  menu_panel_bar = menuMainPanelUpperImage;

      if(backgroundDimmColors == null) {
         backgroundDimmColors = new int[1024];

         for(int var1 = 0; var1 < backgroundDimmColors.length; ++var1) {
            backgroundDimmColors[var1] = -2013265920;
         }
      }

   }

   public static Image sub_8b() {
      if(lock == null) {
         lock = lockImage;
      }

      return lock;
   }

   public static void addTicks(int var0) {
      slowTickCounter += (long)var0;
      quickTickCounter += (long)var0;
   }

   public static boolean slowClockHigh_() {
      if(slowTickCounter > 1000L) {
         if(slowTickCounter > 2000L) {
            slowTickCounter = 0L;
         }

         return true;
      } else {
         return false;
      }
   }

   public static boolean quickClockHigh_() {
      if(quickTickCounter > 300L) {
         if(quickTickCounter > 600L) {
            quickTickCounter = 0L;
         }

         return true;
      } else {
         return false;
      }
   }

   public static void drawMask(int var0, int var1, int var2, int var3) {
      GlobalStatus.graphics.setClip(10, var1, var2, var3);
      sub_1e6();
      GlobalStatus.graphics.setClip(0, 0, GlobalStatus.var_e75, GlobalStatus.var_eb6);
   }

   public static void sub_1e6() {
      for(int var0 = 0; var0 < GlobalStatus.var_e75 / 32 + 1; ++var0) {
         for(int var1 = 0; var1 < GlobalStatus.var_eb6 / 32 + 1; ++var1) {
            GlobalStatus.graphics.drawRGB(backgroundDimmColors, 0, 32, var0 << 5, var1 << 5, 32, 32, true);
         }
      }

   }

   public static void sub_239(String var0, int var1, int var2, int var3, boolean var4) {
      sub_276(var0, var1, var2, var3, var4, false);
   }
   
   public static void drawTextItem(String var0, int var1, int var2, int var3, boolean var4) {
      drawTextItem(var0, var1, var2, var3, var4, false);
   }

   public static void drawTextItem(String var0, int var1, int var2, int var3, boolean var4, boolean var5, boolean var6) {
      Font.drawString(var0, var5?var1 + (var3 >> 1):var1 + 12, var2 + 1, var4?2:1, var5?24:17);
      if(var6) {
         if(lock == null) {
            lock = lockImage;
         }

         if(Status.getCurrentCampaignMission() < 13 && (var0.equals(GlobalStatus.gameText.getText(218)) || var0.equals(GlobalStatus.gameText.getText(33))) || Status.getCurrentCampaignMission() < 9 && var0.equals(GlobalStatus.gameText.getText(72)) || Status.getCurrentCampaignMission() < 5 && var0.equals(GlobalStatus.gameText.getText(62)) || (Status.getStation().getId() == 100 && var0.equals(GlobalStatus.gameText.getText(218)) || Status.getStation().getId() == 101 && var0.equals(GlobalStatus.gameText.getText(218)))) {
            GlobalStatus.graphics.drawImage(lock, var1 + 3, var2 + 2, 0);
         }
      }

   }
   
public static void sub_24e_CENTER(String var0, int var1, int var2, int var3, boolean var4, boolean var5, boolean var6) {
   int textWidth = Font.symbolMaps[0].stringWidth(var0);
   int centeredX = var5 ? var1 + (var3 >> 1) - (textWidth >> 1) : var1 + 12;
   Font.sub_18e_CENTER(var0, centeredX, var2 + 1, var4 ? 2 : 1, var5 ? 24 : 17);

   if(var6) {
      if(lock == null) {
         lock = lockImage;
      }

      if(Status.getCurrentCampaignMission() < 13 && (var0.equals(GlobalStatus.gameText.getText(218)) || var0.equals(GlobalStatus.gameText.getText(33))) ||
         Status.getCurrentCampaignMission() < 9 && var0.equals(GlobalStatus.gameText.getText(72)) ||
         Status.getCurrentCampaignMission() < 5 && var0.equals(GlobalStatus.gameText.getText(62)) ||
         (Status.getStation().getId() == 100 && var0.equals(GlobalStatus.gameText.getText(218)) ||
         Status.getStation().getId() == 101 && var0.equals(GlobalStatus.gameText.getText(218)))) {
         GlobalStatus.graphics.drawImage(lock, var1 + 3, var2 + 2, 0);
      }
   }
}

   public static void sub_276(String var0, int var1, int var2, int var3, boolean var4, boolean var5) {
      drawTextItem(var0, var1, var2, var3, var4, var5, false);
   }
   
   public static void drawHintsWindow(String var0, int var1, int var2, int var3, boolean var4, boolean var5) {
	  hintsOKButton.drawStandartButton(Globals.rectRoundedSmallButtonNormal, Globals.rectRoundedSmallButtonPressed, var5?var1 + (var3 >> 1):var1 + 12, var2 + 1);
      drawTextItem(var0, var1, hintsOKButton.standartButtonY - 7, var3, var4, var5, false);
   }
   
   public static void drawTextItem(String var0, int var1, int var2, int var3, boolean var4, boolean var5) {
      sub_24e_CENTER(var0, var1, var2, var3, var4, var5, false);
   }

   public static void drawBG() {
      drawBGPattern(0, 0, GlobalStatus.var_e75, GlobalStatus.var_eb6, menuBackgroundImage);
   }

   public static void drawBGPattern(int var0, int var1, int var2, int var3, Image var4) {
      int var5 = var4.getWidth();
      int var6 = var4.getHeight();
      GlobalStatus.graphics.setClip(var0, var1, var2, var3);

      for(int var7 = 0; var7 < var2 / var5 + 1; ++var7) {
         for(int var8 = 0; var8 < var3 / var6 + 1; ++var8) {
            GlobalStatus.graphics.drawImage(var4, var0 + var7 * var5, var1 + var8 * var6, 20);
         }
      }

      GlobalStatus.graphics.setClip(0, 0, GlobalStatus.var_e75, GlobalStatus.var_eb6);
   }

   public static void drawWindowFrame(String var0) {
      drawNonFullScreenWindow(var0, false);
   }

   public static void drawNonFullScreenWindow(String var0, boolean var1) {
      GlobalStatus.graphics.setColor(0);
      GlobalStatus.graphics.drawRect(0, 0, GlobalStatus.var_e75 - 1, GlobalStatus.var_eb6 - 1);
      GlobalStatus.graphics.drawLine(0, GlobalStatus.var_eb6 - 2, GlobalStatus.var_e75, GlobalStatus.var_eb6 - 2);
      drawTextBox(var0, 1, 1, GlobalStatus.var_e75 - 3, GlobalStatus.var_eb6 - 8 - footerHeight, var1);
   }

   public static String formatCredits(int var0) {
      String var4 = String.valueOf(var0);
      int var1 = 0;
      String var2 = "";

      for(int var3 = var4.length() - 1; var3 >= 0; --var3) {
         ++var1;
         var2 = var4.charAt(var3) + var2;
         if(var1 == 3 && var3 > 0) {
            var1 = 0;
            var2 = "." + var2;
         }
      }

      if(var2.length() > 2 && var2.charAt(1) == 46 && var2.charAt(0) == 45) {
         var2 = var2.substring(2);
         var2 = "-" + var2;
      }

      return var2 + "$";
   }

   public static void drawMenuWindow(String var0, int var1, int var2, int var3, int var4) {
	  drawBGPattern(var1, var2, var3, menuMainPanelUpperImage.getHeight(), menuMainPanelUpperImage);
      drawTextBox(var0, var1, var2, var3, var4, true);
   }

   public static void drawTextBox(String var0, int var1, int var2, int var3, int var4, boolean var5) {
      if(var5) {
         drawBGPattern(var1, var2 + menuMainCornerImage.getHeight(), var3, var4, menuBackgroundImage);
      }
      if(var0 != null) {
         AEGraphics.drawImage(menuMainCornerImage, var1 + (menuMainCornerImage.getWidth() / 2), var2 + (menuMainCornerImage.getHeight() / 2), 3);
      }

      if(var0 != null && !var0.equals("")) {
         Font.drawString(var0, AEGraphics.getImageX() - (menuMainCornerImage.getWidth() / 2) + 7, AEGraphics.getImageY() - 7, 0);
      }

   }
   
   public static void drawTextBoxDialogue(String var0, int var1, int var2, int var3, int var4, boolean var5) {
      if(var5) {
         drawBGPattern(var1, var2 + menuMainCornerImage.getHeight(), var3, var4, menuBackgroundTextBox);
      }
      if(var0 != null) {
         AEGraphics.drawImage(menuMainCornerImage, var1 + (menuMainCornerImage.getWidth() / 2), var2 + (menuMainCornerImage.getHeight() / 2), 3);
      }

      if(var0 != null && !var0.equals("")) {
         Font.drawString(var0, AEGraphics.getImageX() - (menuMainCornerImage.getWidth() / 2) + 7, AEGraphics.getImageY() - 7, 0);
      }

   }

   public static void sub_449(int var0, int var1, boolean var2) {
	   
	//   SharedVariables.graphics.drawImage(var2?menu_panel_active_image:menu_panel_inactive_image, var0, var1, 20);
	   
   }

   public static void drawFooter(String var0, String var1) {
      String var10000 = var0;
      boolean var2 = true;
      drawButtonsPanel(var10000, var1, false);
   }

   public static void selectNavigationButton(boolean var0) {
      leftButtonSelected = var0;
      naviDelay = 5;
   }

   public static boolean navigationDelayPassed() {
      return naviDelay <= 0;
   }

   public static void navigationDelayDownTick() {
      --naviDelay;
   }

   public static void setTickHighlight(boolean var0) {
      tickHighlight = var0;
   }

   public static void drawStringWidthLimited(String var0, int var1, int var2, int var3, int var4) {
      String var10000 = var0;
      boolean var5 = true;
      String var6;
      Font.drawString((var6 = Font.truncateStringLine(var0 = var10000.trim(), var3 - 2, 0, true)).equals(var0)?var0:var6 + "...", var1, var2, var4, 17);
   }

   public static void drawFooter1(String var0, String var1, boolean var2) {
      drawButtonsPanel(var0, var1, false);
   }

   private static void drawMenuPanel(int var0, int var1, int var2, int var3) { // рисует верхнюю и нижнюю панель в интерфейсе
	  drawBGPattern(var0, var1, var2, var3, menu_panel_bar);
   }
   
   public static void drawPanelWithCorner() {
	   GlobalStatus.graphics.drawImage(menuMainPanelLowerImage, 65, GlobalStatus.var_eb6 - (menuMainPanelLowerImage.getHeight() / 2), 3);
	   GlobalStatus.graphics.drawImage(menuPanelCornerLeftImage, 151, GlobalStatus.var_eb6 - (menuPanelCornerLeftImage.getHeight() / 2), 3);
	   drawBGPattern(158, GlobalStatus.var_eb6 - (menuLowerPanelSolidImage.getHeight()), GlobalStatus.var_e75, GlobalStatus.var_eb6, menuLowerPanelSolidImage);
   }
   
   public static void drawPanelWithoutCorner(String navigationButton) {
	   
	   boolean var4 = !leftButtonSelected && naviDelay > 0 && !navigationButton.equals("");
	   if(navigationButton.equals("")) {
		   navigationButtonTouchFlag = false;
	   } else {
		   drawBGPattern(0, GlobalStatus.var_eb6 - (menuLowerPanelSolidImage.getHeight()), GlobalStatus.var_e75, GlobalStatus.var_eb6, menuLowerPanelSolidImage);
		   AENavigationButton[0].drawStandartButton(Globals.rectRoundedSmallButtonNormal, Globals.rectRoundedSmallButtonPressed, AENavigationButton[0].standartButtonWidth / 2, GlobalStatus.var_eb6 - (AENavigationButton[0].standartButtonHeight / 2));
		   Font.drawString(navigationButton, AENavigationButton[0].standartButtonX + 20, AENavigationButton[0].standartButtonY, var4?4:1, 34);
		   navigationButtonTouchFlag = true;
	   }
   }
   
   public static void drawPanelWithoutCornerDepart(String navigationButton) {
	   
	   boolean var4 = !leftButtonSelected && naviDelay > 0 && !navigationButton.equals("");
	   if(navigationButton.equals("")) {
		   departButtonTouchFlag = false;
	   } else {
		   drawBGPattern(0, GlobalStatus.var_eb6 - (menuLowerPanelSolidImage.getHeight()), GlobalStatus.var_e75, GlobalStatus.var_eb6, menuLowerPanelSolidImage);
		   AENavigationButton[1].drawStandartButton(Globals.departButtonNormal, Globals.departButtonPressed, GlobalStatus.var_e75 - (AENavigationButton[1].standartButtonWidth / 2), GlobalStatus.var_eb6 - (AENavigationButton[1].standartButtonHeight / 2));
		   Font.drawString(navigationButton, AENavigationButton[1].standartButtonX + 20, AENavigationButton[1].standartButtonY, var4?4:1, 34);
		   departButtonTouchFlag = true;
	   }
   }

    public static void drawButtonsPanel(String actionButton, String backButton, boolean skip) {
		navigationButtonTouchFlag = false;
		departButtonTouchFlag = false;
		boolean var3 = leftButtonSelected && naviDelay > 0 && !actionButton.equals("") || tickHighlight;
		boolean var4 = !leftButtonSelected && naviDelay > 0 && !backButton.equals("");
		drawBGPattern(0, GlobalStatus.var_eb6 - (menuLowerPanelSolidImage.getHeight()), GlobalStatus.var_e75, GlobalStatus.var_eb6, menuLowerPanelSolidImage);
		
		if(!actionButton.equals("")) {
			dialogueButtonNext.drawStandartButton(Globals.rectRoundedSmallButtonNormal, Globals.rectRoundedSmallButtonPressed, dialogueButtonNext.standartButtonWidth / 2, GlobalStatus.var_eb6 - (dialogueButtonNext.standartButtonHeight / 2));
			Font.sub_14d_CENTER(actionButton, dialogueButtonNext.standartButtonX, dialogueButtonNext.standartButtonY - 7, var3?6:1); // Левая софт-кнопка. зеленый:белый.
		}
		
		if(!backButton.equals("")) {
			dialogueButtonBack.drawStandartButton(Globals.rectRoundedSmallButtonNormal, Globals.rectRoundedSmallButtonPressed, GlobalStatus.var_e75 - (dialogueButtonBack.standartButtonWidth / 2), GlobalStatus.var_eb6 - (dialogueButtonBack.standartButtonHeight / 2));
			Font.sub_14d_CENTER(backButton, dialogueButtonBack.standartButtonX, dialogueButtonBack.standartButtonY - 7, var4?4:1); // Правая софт-кнопка. красный-белый.
		}
		
		if(skip) {
			dialogueButtonSkip.drawStandartButton(Globals.rectRoundedSmallButtonNormal, Globals.rectRoundedSmallButtonPressed, GlobalStatus.var_e75 / 2, GlobalStatus.var_eb6 - (dialogueButtonSkip.standartButtonHeight / 2));
			GlobalStatus.graphics.drawImage(menuSkipImage,dialogueButtonSkip.standartButtonX, dialogueButtonSkip.standartButtonY, 3);
		}
	}
   
   public static void UIPointerPressed(int x, int y) {
	   
	   if(AENavigationButton != null) {
		   if(navigationButtonTouchFlag == true) {
			   AENavigationButton[0].standartButtonTouch(x, y);
		   }
		   if(departButtonTouchFlag == true) {
			   AENavigationButton[1].standartButtonTouch(x, y);
		   }
	   }
	   
	   if(dialogueButtonBack != null) {
		   dialogueButtonBack.standartButtonTouch(x, y);
	   }
	   
	   if(dialogueButtonNext != null) {
		   dialogueButtonNext.standartButtonTouch(x, y);
	   }
	   
	   if(dialogueButtonSkip != null) {
		   dialogueButtonSkip.standartButtonTouch(x, y);
	   }
	   
	   if(hintsOKButton != null) {
		   hintsOKButton.standartButtonTouch(x, y);
	   }
	   
   }
   
   public static void UIPointerReleased(int x, int y) {
	   if(AENavigationButton != null) {
		   if(navigationButtonTouchFlag == true) {
			   AENavigationButton[0].buttonsTouchReleased(x, y);
		   }
		   if(departButtonTouchFlag == true) {
			   AENavigationButton[1].buttonsTouchReleased(x, y);
		   }
	   }
	   
	   if(dialogueButtonBack != null) {
		   dialogueButtonBack.buttonsTouchReleased(x, y);
	   }
	   
	   if(dialogueButtonNext != null) {
		   dialogueButtonNext.buttonsTouchReleased(x, y);
	   }
	   
	   if(dialogueButtonSkip != null) {
		   dialogueButtonSkip.buttonsTouchReleased(x, y);
	   }
	   
	   if(hintsOKButton != null) {
		   hintsOKButton.buttonsTouchReleased(x, y);
	   }
   }

}
