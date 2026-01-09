package GoF2;

import javax.microedition.lcdui.Image;

import AE.GlobalStatus;
import AE.Math.AEMath;
import AE.PaintCanvas.Font;
import AE.PaintCanvas.ImageFactory;

public final class Radio {

   private RadioMessage[] messages;
   private RadioMessage currentMessage;
   private String[] rows;
   private byte[] face;
   private long msgStartTime;
   private int msgTime;
   private String title;
   private long msgCheckTick;
   private Image[] faceImages;
   private int font;


   public final void OnRelease() {
      this.messages = null;
      this.currentMessage = null;
   }

   public final void setMessages(RadioMessage[] var1) {
      this.messages = var1;
      if(var1 != null) {
         for(int var2 = 0; var2 < var1.length; ++var2) {
            var1[var2].setRadio(this);
         }
      }

   }

   public final void setCurrentMessage(RadioMessage var1) {
      this.currentMessage = var1;
   }

   public final RadioMessage getMessageFromQueue(int var1) {
      return this.messages[var1];
   }

   public final void showMessage() {
      this.msgCheckTick = 501L;
   }

   public final void draw(long var1, long var3, PlayerEgo var5) {
      int var13;
	  
	  // Определяем отступы
	  int padding = AEMath.getPercentInt(GlobalStatus.var_e75, 30);
	  
	  // Вычисляем ширину и высоту прямоугольника
	  int rectWidth = GlobalStatus.var_e75 - 2 * padding; // Ширина прямоугольника
	  int rectHeight = 70; // Задайте желаемую высоту прямоугольника
	  
	  // Вычисляем начальные координаты
	  int startX = padding; // Начало по X
	  int startY = Radar.panelInfoUpper.getHeight() * 2; // Центрируем по Y
	  
      if(this.currentMessage == null) {
         this.msgCheckTick += var3;
         if(this.msgCheckTick > 500L) {
            this.msgCheckTick = 0L;
            PlayerEgo var2 = var5;
            long var8 = var1;
            Radio var11 = this;
            if(this.messages != null) {
               for(var13 = 0; var13 < var11.messages.length; ++var13) {
                  if(var11.messages[var13].triggered(var8, var2)) {
                     int var12;
                     if((var12 = var11.messages[var13].getImageID()) >= 21) {
                        int var4 = var12 == 24?2:(var12 == 23?0:(var12 == 21?3:1));
                        var11.face = ImageFactory.createChar(true, var4);
                     } else {
                        var11.face = Globals.CHAR_IMAGES[var12];
                     }

                     var11.faceImages = ImageFactory.faceFromByteArray(var11.face);
                     var11.font = var12 == 19?3:1;
                     var11.rows = Font.splitToLines(GlobalStatus.gameText.getText(var11.messages[var13].getTextID()), rectWidth, var11.font, ImageFactory.faceWidth + 3, ImageFactory.faceHeight + 3); // высчитывается перенос текста, равен длине текстбокса
                     var11.msgStartTime = var8;
                     var11.msgTime = var11.rows.length * (int)(2000.0F * (float)GlobalStatus.var_e75 / (float)GlobalStatus.var_e75) + 1500;
                     return;
                  }
               }
            }

            return;
         }
      } else if(var1 > this.msgStartTime + 2000L) {
         try {
            var13 = this.currentMessage.getImageID();
            this.title = GlobalStatus.gameText.getText(var13 + 819); // отображение имен персонажей. Вероятно, в диалогах и всплывашках
            Layout.drawTextBoxDialogue(this.title, startX, startY, rectWidth, rectHeight, true);
         } catch (Exception var10) {
            var10.printStackTrace();
         }

         ImageFactory.drawChar(this.faceImages, startX + 7, startY + Layout.menuMainCornerImage.getHeight() + 7, 0);
         Font.drawLinesWithIndent(this.rows, startX + 7, startY + Layout.menuMainCornerImage.getHeight() + 7, this.font, ImageFactory.faceWidth + 3, ImageFactory.faceHeight + 3);
		 
         if(var1 > this.msgStartTime + 2000L + (long)this.msgTime) {
            this.msgStartTime = 0L;
            this.currentMessage.finish();
            this.currentMessage = null;
         }
      }

   }
}
