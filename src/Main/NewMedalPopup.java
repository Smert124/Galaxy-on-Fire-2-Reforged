package Main;

import javax.microedition.lcdui.game.Sprite;
import javax.microedition.lcdui.Image;

import AE.AEResourceManager;
import AE.GlobalStatus;
import AE.PaintCanvas.Font;
import GoF2.Achievements;
import GoF2.Globals;
import GoF2.Layout;
import GoF2.Status;

public final class NewMedalPopup {

   private int posX;
   private int posY;
   private int width;
   private int height;
   private String[] name;
   private String[] description;
   private int okPosY;
   private int tier;
   private Sprite medalsSprite;
   private Image medals;


   private NewMedalPopup(int var1, int var2, int var3) {
      this.posX = 10;
      this.posY = var2;
      this.width = var3;
      this.height = GlobalStatus.var_eb6 / 2;
   }

   public NewMedalPopup() {
      this(10, GlobalStatus.var_eb6 / 2, GlobalStatus.var_e75 - 20);
   }

   public final void set(int var1, int var2) {
      this.tier = var2;
	  this.medals = AEResourceManager.getImage(105);
      this.medalsSprite = new Sprite(this.medals, 31, 15);
      this.name = Font.splitToLines(GlobalStatus.gameText.getText(var1 + 745), this.width - 14 - 31 - 4);
      this.description = Font.splitToLines(Status.replaceToken(GlobalStatus.gameText.getText(var1 + 782), Achievements.VALUES[var1][var2 - 1] + ""), this.width - 14);
      this.posY = (GlobalStatus.var_eb6 >> 1) - (Font.getTotalSpacingY(this.description) + 15 + 4 + 2 * Font.getFontSpacingY() >> 1);
      this.okPosY = this.posY + this.description.length * Font.getFontSpacingY() + 15 + 4 + 14 + 2;
      this.height = this.okPosY + Font.getFontSpacingY() + 7 - this.posY;
   }

   public final void draw() {
      Layout.sub_1e6();
      Layout.drawTextBox(GlobalStatus.gameText.getText(178), this.posX, this.posY, this.width, this.height, true);
      this.medalsSprite.setFrame(this.tier);
      this.medalsSprite.setPosition(this.posX + 7, this.posY + 14 + 2);
      this.medalsSprite.paint(GlobalStatus.graphics);
      Font.drawLines(this.name, this.posX + 7 + 31 + 4, this.posY + 14 + 2, 1);
      Font.drawLines(this.description, this.posX + 7, this.posY + 15 + 2 + 14, 0);
      GlobalStatus.graphics.setColor(Layout.uiInnerOutlineColor);
      int var10001 = this.posX + this.width / 2;
      String var1 = "OK";
      Font.drawString("OK", var10001 - Font.getTextWidth("OK", 0) / 2, this.okPosY, 2);
   }
}
