package GoF2;

import AE.AbstractMesh;
import AE.GlobalStatus;


public final class PlayerJunk extends KIPlayer {

   private long frameTime;


   public PlayerJunk(int var1, Player var2, AbstractMesh var3, int var4, int var5, int var6) {
      super(9996, -1, var2, var3, var4, var5, var6);
      this.player.transform = var3.getToParentTransform();
      var1 = 4096 + GlobalStatus.random.nextInt(8096);
      var3.setScale(var1, var1, var1);
      this.junk = true;
   }

   public final void update(long var1) {
      this.frameTime = var1;
      if(this.player.getHitpoints() <= 0 && this.state != 3 && this.state != 4) {
         this.var_36b.junkDied();
         this.state = 3;
         if(GlobalStatus.random.nextInt(100) < 10) {
            this.hasCargo = true;
            this.cargo = new int[2];
            this.cargo[1] = 1 + GlobalStatus.random.nextInt(10);
            this.cargo[0] = 99;
            this.createCrate(2);
            this.hasCargo = true;
         } else {
            this.player.setActive(false);
            if(this.var_36b.getPlayer().radar.tractorBeamTarget == this) {
               this.var_36b.getPlayer().radar.tractorBeamTarget = null;
            }
         }

         if(this.explosion != null) {
            this.position = this.mainMesh_.getLocalPos(this.position);
            this.explosion.start(this.position.x, this.position.y, this.position.z);
         }
      }

      switch(this.state) {
      case 3:
         if(this.explosion == null || this.explosion.isOver()) {
            this.state = 4;
         }
      default:
      }
   }

   public final void appendToRender() {
      if(this.var_74b != null) {
         GlobalStatus.renderer.drawNodeInVF(this.var_74b);
      }

      if(this.state != 4 && this.state != 3) {
         super.appendToRender();
      }

      if(this.state == 3 && this.explosion != null) {
         this.explosion.update(this.frameTime);
      }

   }

   public final boolean outerCollide(int var1, int var2, int var3) {
      return false;
   }
}
