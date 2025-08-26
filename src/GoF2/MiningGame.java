/**
** @class Miner
** Мини-игра "Шахтер".
** Отображает сенсорные клавиши при активации мини-игры "Шахтер".
**/

package GoF2;

import AE.GlobalStatus;
import AE.Math.AEMath;
import AE.PaintCanvas.Font;
import AE.PaintCanvas.ImageFactory;

public final class MiningGame {

   private int nextLevelTreshold;
   private int levels;
   private int curLevel;
   private int levelProgress;
   private int nextMinedProgress;
   private int nextMinedThreshold;
   private float cursorAcceleration;
   private float driftSpeed;
   private float driftSuppress;
   private float pressCumulativeAcceleration;
   private float cursorPos;
   private float failProgress;
   private float miningProgress;
   private float minedTons;
   private int minedIndicatorHighlightCounter;
   private int frameTime;
   private float minigSpeed;
   private float controlability;
   private Hud hud;
   private int minedItemId;
   private int boardPosX;
   private int boardPosY;
   private int failedBarPosX;
   private int barsPadding;
   private boolean succeed;
   private int[] _LAYER_WITDTHS = new int[]{44, 39, 34, 29, 23, 18, 13};


   public MiningGame(int var1, int var2, Hud var3) {

      this.hud = var3;
      this.boardPosX = GlobalStatus.var_e75 / 2 - Globals.miningBackground.getWidth() / 2;
      this.boardPosY = GlobalStatus.var_eb6 / 2 + Globals.miningBackground.getHeight() / 2;
      this.failedBarPosX = Globals.miningBackground.getWidth();
      this.barsPadding = 2;
      this.nextMinedThreshold = -1;
      this.driftSpeed = 1.0F;
      this.succeed = false;
      this.minedItemId = var2;
      this.levels = var1;
      Item var5 = Status.getShip().getFirstEquipmentOfSort(19);
      this.nextLevelTreshold = 6000; // Руда добывается мгновенно, если -1.
      if(var5 != null) {
         float var6 = (float)var5.getAttribute(30) / 100.0F;
         this.driftSuppress = 25.0F + var6 * 55.0F;
         this.controlability = 50.0F + var6 * 200.0F;
         this.minigSpeed = (float)var5.getAttribute(31) / 100.0F;
      }

      this.minedIndicatorHighlightCounter = 400;
   }

   public final boolean gameLost() {
      return this.failProgress >= 2500.0F;
   }

   public final int getMiningProgressRounded() {
      return (int)this.miningProgress;
   }

   public final boolean gotCore() {
      return this.succeed && this.levels == 7;
   }

   public final boolean update(int var1, int var2) {
      if(this.curLevel >= this.levels) {
         return false;
      } else {
         this.frameTime = var1;
         int var3;
         int var4 = -(var3 = (2 * this.barsPadding + 3 * this._LAYER_WITDTHS[this.curLevel]) / 2);
         this.nextMinedProgress += var1;
         if(AEMath.abs((int)this.cursorPos) > this._LAYER_WITDTHS[this.curLevel] / 2) {
            this.failProgress += (float)var1;
            if(this.failProgress > 2500.0F) {
               this.failProgress = 2500.0F;
               this.miningProgress = 0.0F;
               return false;
            }
         } else {
            this.levelProgress += var1;
            float var5 = (var5 = 0.15F + (float)(this.curLevel + 1) / 7.0F * 2.35F) * this.minigSpeed;
            this.miningProgress += var5 / 1000.0F * (float)var1;
         }

         if(this.levelProgress > this.nextLevelTreshold) {
            this.levelProgress = 0;
            ++this.curLevel;
            this.nextLevelTreshold = (int)((float)this.nextLevelTreshold * 0.83F);
            if(this.curLevel >= this.levels) {
               this.succeed = true;
               return false;
            }

            this.nextMinedProgress = this.nextMinedThreshold + 1;
            this.cursorPos *= 0.88F;
         }

         if(this.nextMinedProgress > this.nextMinedThreshold) {
            this.nextMinedProgress = 0;
            this.nextMinedThreshold = 500 + (int)((float)GlobalStatus.random.nextInt(100) / 100.0F * 2000.0F);
            this.cursorAcceleration = GlobalStatus.random.nextInt(2) == 0?-1.0F:1.0F;
            this.driftSpeed = 0.0F;
            this.pressCumulativeAcceleration /= 2.0F;
         }

         this.driftSpeed = (float)var1 / this.driftSuppress * this.cursorAcceleration;
         if((var2 & 32) != 0) { // Оси смещения сверла.
            this.pressCumulativeAcceleration -= (float)var1 / this.controlability;
         } else if((var2 & 4) != 0) {
            this.pressCumulativeAcceleration += (float)var1 / this.controlability;
         } else {
            this.pressCumulativeAcceleration = 0.0F;
         }

         this.cursorPos += this.driftSpeed - this.pressCumulativeAcceleration;
         if(this.cursorPos > (float)var3) {
            this.cursorPos = (float)var3;
         } else if(this.cursorPos < (float)var4) {
            this.cursorPos = (float)var4;
         }

         return true;
      }
   }

   public final void render2D() {
      GlobalStatus.graphics.drawImage(Globals.miningBackground, this.boardPosX, this.boardPosY, 36);
      int var1 = this.levels * 7;
      int var2 = (this.curLevel + 1) * 7;
      GlobalStatus.graphics.setClip(0, this.boardPosY - var1, GlobalStatus.var_e75, var1);
      GlobalStatus.graphics.drawImage(Globals.miningGreenEmpty, this.boardPosX + 50, this.boardPosY, 36);
      if(this.curLevel > 0) {
         var1 = this.curLevel * 7;
         GlobalStatus.graphics.setClip(0, this.boardPosY - var1, GlobalStatus.var_e75, var1);
         GlobalStatus.graphics.drawImage(Globals.miningGreenComplete, this.boardPosX + 50, this.boardPosY, 36);
      }
      var1 = this._LAYER_WITDTHS[this.curLevel];
      int var3 = (int)((float)this.levelProgress / (float)this.nextLevelTreshold * (float)var1);
      GlobalStatus.graphics.setClip((GlobalStatus.var_e75 >> 1) - (var3 >> 1), this.boardPosY - var2, var3, 7);
      GlobalStatus.graphics.drawImage(Globals.miningGreenComplete, this.boardPosX + 50, this.boardPosY, 36);
      var2 = this.boardPosY - this.curLevel * 7;
      var3 = this.boardPosX + (this.failedBarPosX - 2 * this.barsPadding - var1 * 3) / 2;
      int var4 = (int)(this.failProgress / 2500.0F * (float)(var1 + 5));
      GlobalStatus.graphics.setClip(var3 + var1 - var4, var2 - 5, var4, 5);
      GlobalStatus.graphics.drawImage(Globals.miningRedArea, this.boardPosX, this.boardPosY, 36);
      GlobalStatus.graphics.setClip(var3 + var1 * 2 + 2 * this.barsPadding, var2 - 5, var4, 5);
      GlobalStatus.graphics.drawImage(Globals.miningRedArea, this.boardPosX, this.boardPosY, 36);
      GlobalStatus.graphics.setClip(0, 0, GlobalStatus.var_e75, GlobalStatus.var_eb6);
      var1 = (GlobalStatus.var_e75 >> 1) + (int)this.cursorPos;
      GlobalStatus.graphics.drawImage(Globals.miningCursor, var1, var2 + 2, 33);
      var1 = this.boardPosY - Globals.miningBackground.getHeight();
      if(this.levels == 7) {
         ImageFactory.drawItemFrameless(this.minedItemId - 154 + 165, Globals.itemsImage, (GlobalStatus.var_e75 >> 1) - 1, var1 + 10, 3);
      }

      if((int)this.miningProgress > (int)this.minedTons) {
         this.minedTons = this.miningProgress;
         this.minedIndicatorHighlightCounter = 0;
      }

      boolean var5 = this.minedIndicatorHighlightCounter < 400;
      String var6 = "t " + GlobalStatus.gameText.getNamedParameterItems(this.minedItemId);
      Font.drawString("" + (int)this.miningProgress + var6, GlobalStatus.var_e75 >> 1, this.boardPosY + Font.getFontSpacingY(), 1, 24);
   }
}
