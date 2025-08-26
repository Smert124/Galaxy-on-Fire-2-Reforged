package GoF2;

import AE.GlobalStatus;
import AE.PaintCanvas.Font;
import HardEngine.AEButtonManager;


public final class Popup {

   private int posX;
   private int posY;
   private int width;
   private int height;
   private String[] rows;
   private int choicePosY;
   public static boolean currentChoice;
   public boolean isChoice;
   public static AEButtonManager[] AEButton;


   public Popup(int var1, int var2, int var3) {
      this.posX = var1;
      this.posY = var2;
      this.width = var3;
      this.height = GlobalStatus.var_eb6 / 2;
	  AEButton = new AEButtonManager[3];
	  for(int countButton = 0; countButton < 3; ++countButton) {
		  if(AEButton[countButton] == null) {
			  AEButton[countButton] = new AEButtonManager();
		  }
	  }
   }

   public Popup() {
      this(GlobalStatus.var_e75 < 176?10:20, GlobalStatus.var_eb6 / 2, GlobalStatus.var_e75 - (GlobalStatus.var_e75 < 176?20:40));
   }

   public final void setAsWarning(String var1) {
      this.set(var1, false);
   }

   public final void set(String var1, boolean var2) {
      this.currentChoice = true;
      this.isChoice = var2;
      this.rows = Font.splitToLines(var1, this.width - 14);
      this.posY = (GlobalStatus.var_eb6 >> 1) - (Font.getTotalSpacingY(this.rows) + 2 * Font.getFontSpacingY() + 4 >> 1);
      this.choicePosY = this.posY + this.rows.length * Font.getFontSpacingY() + 4 + 14 + 2;
      this.height = this.choicePosY + Font.getFontSpacingY() + 7 - this.posY;
   }

   public final boolean getChoice() {
      return this.isChoice?this.currentChoice:false;
   }

   public final void left() { // кнопка влево
      if(!this.currentChoice && this.isChoice) {
         this.currentChoice = true;
      }

   }

   public final void right() { // кнопка вправо
      if(this.currentChoice && this.isChoice) {
         this.currentChoice = false;
      }

   }
   
   public void touchControl() {
	   if(AEButton[0].getStandartButtonPressed() == true) {
		   left();
	   }
	   if(AEButton[1].getStandartButtonPressed() == true) {
		   right();
	   }
	   if(AEButton[2].getStandartButtonPressed() == true) {
		   getChoice();
	   }
   }

   public final void draw() {
      Layout.sub_1e6();
      Layout.drawTextBox("", this.posX, this.posY, this.width, this.height + 30, true);
      Font.drawLines(this.rows, this.posX + 7, this.posY + 14 + 4, 0);
      if(this.isChoice) {
		  AEButton[0].drawStandartButton(Globals.rectRoundedSmallButtonNormal, Globals.rectRoundedSmallButtonPressed, this.posX + this.width / 3, this.choicePosY + 20);
		  Font.drawString(GlobalStatus.gameText.getText(38), AEButton[0].standartButtonX, AEButton[0].standartButtonY - 10, this.currentChoice?2:1, 24);
		  
		  AEButton[1].drawStandartButton(Globals.rectRoundedSmallButtonNormal, Globals.rectRoundedSmallButtonPressed, this.posX + this.width - this.width / 3, this.choicePosY + 20);
		  Font.drawString(GlobalStatus.gameText.getText(39), AEButton[1].standartButtonX, AEButton[1].standartButtonY - 10, this.currentChoice?1:2, 24);
      } else {
         int var10001 = this.posX + this.width / 2;
		 AEButton[2].drawStandartButton(Globals.rectRoundedSmallButtonNormal, Globals.rectRoundedSmallButtonPressed, var10001, this.choicePosY + 20);
         Font.drawString("OK", AEButton[2].standartButtonX - 7, AEButton[2].standartButtonY - 10, 2); // всплывающее окно об ошибке или информации, по центру экрана
      }
	  touchControl();
   }
   
   public static void dialogueMenuPointerPressed(int x, int y) {
	   
	   if(AEButton != null) {
		   AEButton[0].standartButtonTouch(x, y);
		   AEButton[1].standartButtonTouch(x, y);
		   AEButton[2].standartButtonTouch(x, y);
		}
	   
   }
   
   public static void dialogueMenuPointerReleased(int x, int y) {
	   
	   if(AEButton != null) {
		   AEButton[0].buttonsTouchReleased(x, y);
		   AEButton[1].buttonsTouchReleased(x, y);
		   AEButton[2].buttonsTouchReleased(x, y);
		}
	   
   }
   
}
