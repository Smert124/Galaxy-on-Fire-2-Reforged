package AE;


public final class BoundingAAB extends BoundingVolume {

   private int var_50;
   private int var_a0;
   private int var_ba;


   public BoundingAAB(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9) {
      super(var1, var2, var3, var4, var5, var6);
      this.var_50 = var7 >> 1;
      this.var_a0 = var8 >> 1;
      this.var_ba = var9 >> 1;
   }

   public final boolean outerCollide_(int var1, int var2, int var3) {
      return this.isPointInBounding(var1, var2, var3)?super.outerCollide_(var1, var2, var3):false;
   }

   public final boolean isPointInBounding(int var1, int var2, int var3) {
      return var1 > this.var_230 + this.var_4a5 - (this.var_50 < 0?-this.var_50:this.var_50) && var1 < this.var_230 + this.var_4a5 + (this.var_50 < 0?-this.var_50:this.var_50) && var2 > this.var_34f + this.var_53e - (this.var_a0 < 0?-this.var_a0:this.var_a0) && var2 < this.var_34f + this.var_53e + (this.var_a0 < 0?-this.var_a0:this.var_a0) && var3 > this.var_3cc + this.var_5c6 - (this.var_ba < 0?-this.var_ba:this.var_ba) && var3 < this.var_3cc + this.var_5c6 + (this.var_ba < 0?-this.var_ba:this.var_ba);
   }

   public final void setPosition(int var1, int var2, int var3) {
      super.setPosition(var1, var2, var3);
   }
}
