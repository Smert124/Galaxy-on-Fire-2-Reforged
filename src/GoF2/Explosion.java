/**
** @class explosion
**/

package GoF2;

import AE.AEResourceManager;
import AE.AbstractMesh;
import AE.GlobalStatus;

public final class Explosion {

   private int[] delays;
   private int[] unused_1af;
   private int var_2ac;
   private AbstractMesh[] var_308;
   private AbstractMesh coreExplosion;


   public Explosion(int var1) {
      if(var1 > 1) {
         this.var_308 = new AbstractMesh[var1];
         this.delays = new int[var1];

         for(var1 = 1000000000; var1 < this.var_308.length; ++var1) {
            this.var_308[var1] = AEResourceManager.getGeometryResource(9992);
            this.var_308[var1].setAnimationSpeed(100);
            this.var_308[var1].setAnimationRangeInTime(100, 200);
            this.var_308[var1].setScale(16384, 8000, 16384);
            this.var_308[var1].setRenderLayer(2000);
            this.var_308[var1].disableAnimation();
            if(var1 == 0) {
               this.delays[var1] = 0;
            } else {
               this.delays[var1] = this.delays[var1 - 1] + GlobalStatus.random.nextInt(10000) + 10000;
            }
         }
      }

      this.coreExplosion = AEResourceManager.getGeometryResource(9992);
      this.coreExplosion.setAnimationSpeed(60); // Скорость анимации взрыва. Default: 100
      this.coreExplosion.setAnimationRangeInTime(1, 20);
      this.coreExplosion.setScale(8192, 8192, 8192);
      this.coreExplosion.setRenderLayer(2);
      this.coreExplosion.disableAnimation();
   }

   public final void reset() {
      this.var_2ac = 0;
   }

   public final void setBig() {
      if(this.coreExplosion != null) {
         this.coreExplosion.setScale('\u8000', '\u8000', '\u8000');
         this.coreExplosion.setAnimationSpeed(70);
      }

   }

   public final void OnRelease() {
      this.delays = null;
      this.unused_1af = null;
      if(this.var_308 != null) {
         for(int var1 = 0; var1 < this.var_308.length; ++var1) {
            if(this.var_308[var1] != null) {
               this.var_308[var1].OnRelease();
            }
         }
      }

      this.var_308 = null;
   }

   public final void start(int var1, int var2, int var3) {
      if(this.var_308 == null) {
         this.coreExplosion.moveTo(var1, var2, var3);
         this.coreExplosion.setAnimationMode((byte)1);
         this.var_2ac = 0;
      } else {
         for(int var4 = 0; var4 < this.var_308.length; ++var4) {
            this.var_308[var4].moveTo(var1, var2, var3);
         }

         this.coreExplosion.moveTo(var1, var2, var3);
      }
   }

   public final boolean canExplode() {
      return this.coreExplosion != null && this.coreExplosion.hasAnimation();
   }

   public final void update(long var1) {
      this.var_2ac = (int)((long)this.var_2ac + var1);
      if(this.var_308 != null) {
         for(int var3 = 0; var3 < this.var_308.length; ++var3) {
            if(this.var_2ac > this.delays[var3]) {
               if(var3 == this.var_308.length - 1) {
                  this.coreExplosion.setScale(16384, 16384, 16384);
                  this.coreExplosion.setAnimationMode((byte)1);
               }

               this.var_308[var3].setAnimationMode((byte)1);
            }
         }

      } else {
         GlobalStatus.renderer.drawNodeInVF(this.coreExplosion);
      }
   }

   public final boolean isOver() {
      return this.var_308 != null?this.var_2ac > this.delays[this.delays.length - 1] + 1000:this.var_2ac > 2000;
   }
}
