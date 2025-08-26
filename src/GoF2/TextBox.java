package GoF2;

import javax.microedition.lcdui.Image;

import AE.GlobalStatus;
import AE.Math.AEMath;
import AE.PaintCanvas.Font;
import AE.PaintCanvas.ImageFactory;

public final class TextBox {

   private int posX;
   private int posY;
   private int width;
   private int height;
   int topPadding;
   private String[] rows;
   private int fontSpacingY__;
   private boolean verticalBar;
   private int vBarPercent;
   private String rawText;
   private byte[] faceIds;
   private int topPadding2;
   private boolean hide;
   int font;
   private Image[] faceImgs;
   private int heightLimit;
   
   public int padding;
   public int rectWidth;
   public int rectHeight;
   public int startX;
   public int startY;


   public TextBox(int var1, int var2, int var3, int var4, String var5) {
      this.posX = var1;
      this.posY = var2;
      this.width = var3;
      this.height = var4;
      this.heightLimit = var4;
      this.topPadding = 0;
      if(var5 != null) {
         this.setText(var5);
      }

      this.hide = false;
      this.font = 0;
	  
	  this.padding = AEMath.getPercentInt(GlobalStatus.var_e75, 15);
	  this.rectWidth = GlobalStatus.var_e75 - 2 * padding; // Ширина прямоугольника
	  this.rectHeight = AEMath.getPercentInt(GlobalStatus.var_eb6, 12); // Задайте желаемую высоту прямоугольника
	  this.startX = padding; // Начало по X
	  this.startY = AEMath.getPercentInt(GlobalStatus.var_eb6, 5); // Центрируем по Y
   }

   public final void zeroTopPadding() {
      this.topPadding = 0;
   }

   public final void setHide(boolean var1) {
      this.hide = true;
   }

   public final void setFont(int var1) {
      this.font = var1;
   }

   public final String sub_1de() {
      return this.rawText;
   }

   public final void setText(String var1) {
      this.rawText = var1;
      if(this.faceIds == null) {
         this.topPadding2 = 0;
      }

      if(var1 != null) {
         if(this.faceIds == null) {
            this.rows = Font.splitToLines(var1, this.width + 3);
         } else {
            this.rows = Font.splitToLines(var1, this.rectWidth, 1, ImageFactory.faceWidth + 3, ImageFactory.faceHeight);
         }

         this.fontSpacingY__ = Font.getTotalSpacingY(this.rows) + this.topPadding2;
         this.height = AEMath.min(this.heightLimit, Font.getTotalSpacingY(this.rows) + 13);
         if(this.faceIds != null) {
            this.height = AEMath.max(this.height, ImageFactory.faceHeight + 10);
         }

         this.verticalBar = this.fontSpacingY__ > this.height;
         if(this.verticalBar) {
            if(this.faceIds == null) {
               this.rows = Font.splitToLines(var1, this.width + 3);
            } else {
               this.rows = Font.splitToLines(var1, this.rectWidth, 1, ImageFactory.faceWidth + 3, ImageFactory.faceHeight);
            }

            this.fontSpacingY__ = Font.getTotalSpacingY(this.rows) + this.topPadding2;
            this.vBarPercent = (int)((float)this.height / (float)this.fontSpacingY__ * (float)this.height) - 1;
         }

      }
   }

   public final int getHeight_() {
      return this.height + Font.getFontSpacingY() + (this.verticalBar?6:0);
   }

   public final int getTextHeight_() {
      return this.rows == null?0:(this.faceIds == null?this.rows.length:AEMath.max(this.rows.length, ImageFactory.faceHeight / this.rows.length + 1));
   }

   public final void set(byte[] var1, String var2, boolean var3) {
      this.faceIds = var1;
      Font.splitToLines(var2, this.width - ImageFactory.faceWidth - 2);
      this.faceImgs = ImageFactory.faceFromByteArray(var1);
      this.topPadding2 = 3;
      this.setText(this.rawText);
   }

   public final void scrollUp(int var1) {
      if(this.fontSpacingY__ > this.height) {
         this.topPadding -= var1 / 10;
         if(this.topPadding < -(this.fontSpacingY__ - this.height)) {
            this.topPadding = -(this.fontSpacingY__ - this.height);
         }
      }

   }

   public final void scrollDown(int var1) {
      this.topPadding += var1 / 10;
      if(this.topPadding > 0) {
         this.topPadding = 0;
      }

   }

   public final void draw() {
      GlobalStatus.graphics.setColor(Layout.uiInnerOutlineColor);
	  GlobalStatus.graphics.setClip(0, 0, GlobalStatus.var_e75, GlobalStatus.var_eb6);
     // SharedVariables.graphics.setClip(this.startX, this.startY + this.var_281 + this.var_316 + this.var_523 + 7, this.rectWidth, this.sub_222());
      if(this.faceIds != null) {
         ImageFactory.drawChar(this.faceImgs, this.startX, this.startY + this.posY + this.topPadding + this.topPadding2 + 7, 0);
      }

      if(this.rows != null) {
         if(this.hide) {
            Font.drawLinesAligned(this.rows, this.posX + this.width - 3, this.startY + this.posY + this.topPadding + this.topPadding2, this.font, 2); // правый текст в характеристиках
         } else if(this.faceIds == null) {
            Font.drawLines(this.rows, this.posX + 2, this.startY + this.posY + this.topPadding + this.topPadding2, this.font); // левый текст в характеристиках
         } else {
            Font.drawLinesWithIndent(this.rows, this.startX + 7, this.startY + this.posY + this.topPadding + this.topPadding2 + 7, this.font, ImageFactory.faceWidth + 3, ImageFactory.faceHeight); // текст в диалоговом окне
			// Class_ba6.sub_21c(this.var_3ab, this.var_251 + 2, this.var_281 + this.var_316 + this.var_523, this.var_56c, FACE_GEN.var_14f + 3, FACE_GEN.var_2ea); // текст в диалоговом окне
         }
      }

      GlobalStatus.graphics.setClip(0, 0, GlobalStatus.var_e75, GlobalStatus.var_eb6);
      if(this.verticalBar) {
         int var1 = this.posY + 2 - (int)((float)this.topPadding / (float)this.fontSpacingY__ * (float)(this.height - 7));
         GlobalStatus.graphics.setColor(Layout.uiInnerOutlineColor);
         GlobalStatus.graphics.drawLine(this.posX + this.width, this.posY + 2, this.posX + this.width, this.posY + this.height - 4);
         GlobalStatus.graphics.setColor(0, 80, 135); // самая правая часть
         GlobalStatus.graphics.fillRect(this.posX + this.width - 1, var1, 3, this.vBarPercent);
         GlobalStatus.graphics.setColor(0, 90, 155); // самая левая часть
         GlobalStatus.graphics.drawLine(this.posX + this.width - 1, var1 + 1, this.posX + this.width - 1, var1 + this.vBarPercent - 2);
         GlobalStatus.graphics.drawLine(this.posX + this.width - 1, var1 + this.vBarPercent - 1, this.posX + this.width - 1, var1 + this.vBarPercent - 1);
         GlobalStatus.graphics.setColor(0, 128, 255); // центральная часть
         GlobalStatus.graphics.drawLine(this.posX + this.width, var1 + 1, this.posX + this.width, var1 + this.vBarPercent - 2);
         GlobalStatus.graphics.setColor(Layout.uiInnerOutlineColor);
      }

   }
}
