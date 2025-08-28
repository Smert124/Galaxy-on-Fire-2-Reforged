package GoF2;

import AE.AEResourceManager;
import AE.AbstractGun;
import AE.AbstractMesh;
import AE.GlobalStatus;
import AE.GraphNode;
import AE.ITexture;
import AE.Math.AEVector3D;
import AE.Math.Matrix;
import AE.ParticleSystemMesh;

// лучевое оружие?

public final class LaserGun extends AbstractMesh implements AbstractGun {

   private Gun gun;
   private int beamRadius = 80;
   private AbstractMesh beam;
   private AbstractMesh flash;
   private int[] vertexPositions;
   private int[] vertexWorldPositions;
   private int[] uvs;
   private static AEVector3D muzzlePos;
   private static AEVector3D aimPoint;
   private static AEVector3D direction;
   private AEVector3D muzzlePos2;
   private static int[][] BEAM_UVs = new int[][]{{0, 0, 64, 4}, {0, 8, 64, 16}, {0, 16, 64, 24}, {0, 24, 64, 32}};
   private static int[] FLASH_KEYFRAMES = new int[]{0, 1, 9};
   private Level level;
   private boolean directionLocked;


   public LaserGun(Gun var1, int var2, Level var3) {
      this.level = var3;
      this.gun = var1;
      muzzlePos = new AEVector3D();
      aimPoint = new AEVector3D();
      direction = new AEVector3D();
      this.muzzlePos2 = new AEVector3D();
      var2 -= 9;
      this.flash = AEResourceManager.getGeometryResource(6781);
      this.flash.setRenderLayer(2);
      this.flash.setAnimationRangeInTime(FLASH_KEYFRAMES[var2], FLASH_KEYFRAMES[var2]);
      this.flash.setAnimationMode((byte)1);
      this.flash.setScale(1024, 1024, 1024);
      this.beam = AbstractMesh.newPlaneStrip(0, 10, (byte)2);
      this.beam.setTexture(AEResourceManager.getTextureResource(1095));
      this.beam.setRenderLayer(2);
      this.vertexWorldPositions = new int[120];
      this.vertexPositions = new int[120];
      this.uvs = new int[80];

      for(int var4 = 0; var4 < this.uvs.length; var4 += 8) {
         this.uvs[var4] = BEAM_UVs[var2][0];
         this.uvs[var4 + 1] = BEAM_UVs[var2][1];
         this.uvs[var4 + 2] = BEAM_UVs[var2][2];
         this.uvs[var4 + 3] = BEAM_UVs[var2][1];
         this.uvs[var4 + 4] = BEAM_UVs[var2][2];
         this.uvs[var4 + 5] = BEAM_UVs[var2][3];
         this.uvs[var4 + 6] = BEAM_UVs[var2][0];
         this.uvs[var4 + 7] = BEAM_UVs[var2][3];
      }

      this.directionLocked = false;
   }

   public final void OnRelease() {
      this.gun.OnRelease();
      this.gun = null;
      if(this.beam != null) {
         this.beam.OnRelease();
      }

      this.beam = null;
   }

   public final void render() {
      if(!this.gun.inAir) {
         this.directionLocked = false;
      } else {
         if(!this.directionLocked && this.gun.inAir) {
            aimPoint.set(Crosshair.relativePos);
            this.directionLocked = true;
         }

         Matrix var1 = this.level.getPlayer().shipGrandGroup_.getToParentTransform();
         this.muzzlePos2.set(var1.getPosition(muzzlePos));
         if(this.gun.muzzleOffset != null) {
            muzzlePos.set(this.gun.muzzleOffset);
            direction = var1.transformVectorNoScale(muzzlePos, direction);
            this.muzzlePos2.add(direction);
         }

         this.flash.setRotation(var1.getEulerX(), var1.getEulerY(), var1.getEulerZ());
         this.flash.moveTo(this.muzzlePos2);
         muzzlePos.set(this.muzzlePos2);
         AEVector3D var10000 = direction = aimPoint.subtract(muzzlePos, direction);
         var10000.x /= 10;
         direction.y /= 10;
         direction.z /= 10;

         int var2;
         int var3;
         for(var3 = 0; var3 < this.vertexWorldPositions.length >> 1; var3 += 12) {
            var2 = var3 / 12;
            this.vertexWorldPositions[var3] = muzzlePos.x + var2 * direction.x + this.beamRadius;
            this.vertexWorldPositions[var3 + 1] = muzzlePos.y + var2 * direction.y;
            this.vertexWorldPositions[var3 + 2] = muzzlePos.z + var2 * direction.z;
            this.vertexWorldPositions[var3 + 3] = muzzlePos.x + var2 * direction.x - this.beamRadius;
            this.vertexWorldPositions[var3 + 4] = muzzlePos.y + var2 * direction.y;
            this.vertexWorldPositions[var3 + 5] = muzzlePos.z + var2 * direction.z;
            this.vertexWorldPositions[var3 + 6] = muzzlePos.x + (var2 + 1) * direction.x - this.beamRadius;
            this.vertexWorldPositions[var3 + 7] = muzzlePos.y + (var2 + 1) * direction.y;
            this.vertexWorldPositions[var3 + 8] = muzzlePos.z + (var2 + 1) * direction.z;
            this.vertexWorldPositions[var3 + 9] = muzzlePos.x + (var2 + 1) * direction.x + this.beamRadius;
            this.vertexWorldPositions[var3 + 10] = muzzlePos.y + (var2 + 1) * direction.y;
            this.vertexWorldPositions[var3 + 11] = muzzlePos.z + (var2 + 1) * direction.z;
         }

         for(var3 = this.vertexWorldPositions.length >> 1; var3 < this.vertexWorldPositions.length; var3 += 12) {
            var2 = (var3 - (this.vertexWorldPositions.length >> 1)) / 12;
            this.vertexWorldPositions[var3] = muzzlePos.x + var2 * direction.x;
            this.vertexWorldPositions[var3 + 1] = muzzlePos.y + var2 * direction.y + this.beamRadius;
            this.vertexWorldPositions[var3 + 2] = muzzlePos.z + var2 * direction.z + this.beamRadius;
            this.vertexWorldPositions[var3 + 3] = muzzlePos.x + var2 * direction.x;
            this.vertexWorldPositions[var3 + 4] = muzzlePos.y + var2 * direction.y - this.beamRadius;
            this.vertexWorldPositions[var3 + 5] = muzzlePos.z + var2 * direction.z - this.beamRadius;
            this.vertexWorldPositions[var3 + 6] = muzzlePos.x + (var2 + 1) * direction.x;
            this.vertexWorldPositions[var3 + 7] = muzzlePos.y + (var2 + 1) * direction.y - this.beamRadius;
            this.vertexWorldPositions[var3 + 8] = muzzlePos.z + (var2 + 1) * direction.z - this.beamRadius;
            this.vertexWorldPositions[var3 + 9] = muzzlePos.x + (var2 + 1) * direction.x;
            this.vertexWorldPositions[var3 + 10] = muzzlePos.y + (var2 + 1) * direction.y + this.beamRadius;
            this.vertexWorldPositions[var3 + 11] = muzzlePos.z + (var2 + 1) * direction.z + this.beamRadius;
         }

         for(var3 = 0; var3 < this.vertexWorldPositions.length; var3 += 3) {
            this.vertexPositions[var3] = this.vertexWorldPositions[var3] - muzzlePos.x;
            this.vertexPositions[var3 + 1] = this.vertexWorldPositions[var3 + 1] - muzzlePos.y;
            this.vertexPositions[var3 + 2] = this.vertexWorldPositions[var3 + 2] - muzzlePos.z;
         }

         this.beam.moveTo(muzzlePos.x, muzzlePos.y, muzzlePos.z);
         ((ParticleSystemMesh)this.beam).setMeshData_(this.vertexPositions, this.uvs);
         GlobalStatus.renderer.drawNodeInVF(this.flash);
         GlobalStatus.renderer.drawNodeInVF(this.beam);
         if(this.gun.projectilesTimeLeft[0] <= 0) {
            this.gun.inAir = false;
         }

      }
   }

   public final void update(long var1) {
      this.gun.calcCharacterCollision(var1);
      if(this.gun.inAir) {
         this.beamRadius -= (int)var1 >> 2;
         if(this.beamRadius < 0) {
            this.beamRadius = 0;
            this.gun.inAir = false;
            return;
         }
      } else {
         this.beamRadius = 150;
      }

   }

   public final GraphNode clone() {
      return null;
   }

   public final void setTexture(ITexture var1) {}

}
