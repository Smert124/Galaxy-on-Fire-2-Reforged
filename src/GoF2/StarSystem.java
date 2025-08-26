package GoF2;

import javax.microedition.lcdui.game.Sprite;

import AE.AEResourceManager;
import AE.AbstractMesh;
import AE.CameraControllerGroup;
import AE.GlobalStatus;
import AE.LensFlareFX;
import AE.Math.AEMath;
import AE.Math.AEVector3D;
import AE.Math.Matrix;

public final class StarSystem {

   private AbstractMesh[] starAndPlanets;
   private CameraControllerGroup cameraControler;
   private AEVector3D tempVec;
   private LensFlareFX lensFlare;
   private Sprite sun;
   private KIPlayer[] localPlanets;
   private boolean[] occupiedNebulaPos;
   private boolean[] occupiedAstroObjPos;
   public static int currentPlanetEnumIndex;
   private boolean inAlienSpace;

   public StarSystem() {
      new Matrix();
      new Matrix();
      this.cameraControler = new CameraControllerGroup();
      this.inAlienSpace = Status.getSystem() == null;
      int var4;
      int i;
      if(!this.inAlienSpace) {
         int[] stationsID = Status.getSystem().getStations();
         this.starAndPlanets = new AbstractMesh[stationsID.length + 1];
         this.localPlanets = new KIPlayer[stationsID.length];
         GlobalStatus.random.setSeed((long)(Status.getStation().getId() * 300));
         boolean[] var10 = new boolean[24];
         int var3 = 0;
         var4 = 0;
         boolean var5 = false;
		 
		 int[] planetImages = Status.getPlanetTextures();
		 int[] planetImagesID = new int[planetImages.length + 1];
		 planetImagesID[0] = Status.getStation().getPlanetTextureId();
		 for(i = 0; i < planetImages.length; i++) {
			 planetImagesID[i + 1] = planetImages[i];
		 }
         this.starAndPlanets[0] = AEResourceManager.getGeometryResource(3100 + Status.getSystem().getStarTextureIndex());
		 this.starAndPlanets[0].setRenderLayer(1);
		 this.starAndPlanets[0].setScale(768, 768, 768);
         this.starAndPlanets[0].setRotation(-600 + GlobalStatus.random.nextInt(256), GlobalStatus.random.nextInt(24) * 800, 0);
         this.starAndPlanets[0].moveForward(-20000); // расстояние планеты от центра мира
         this.starAndPlanets[0].rotateEuler(0, 1024, 0);
         this.starAndPlanets[0].updateTransform(true);
         this.cameraControler.uniqueAppend_(this.starAndPlanets[0]);
         if (this.starAndPlanets.length > 1){
            for(i = 1; i < this.starAndPlanets.length; ++i) {
            
               this.starAndPlanets[i] = AEResourceManager.getGeometryResource(3200 + planetImagesID[i]); // получаем модель
               this.starAndPlanets[i].setRenderLayer(1);
               if(i == 0) {
                  var4 = var3 = GlobalStatus.random.nextInt(24);
               } else {
                  boolean var6;
                  if(stationsID[i - 1] == Status.getStation().getId()) {
                     this.starAndPlanets[i].setDraw(true);
                     var6 = false;
                     int var7;
                     if((var7 = Status.getStation().getPlanetTextureId()) != 17 && var7 != 18) {
                        while(!var6) {
                           var6 = AEMath.abs((var3 = GlobalStatus.random.nextInt(24)) - var4) > 3 && !var10[var3];
                        }
                     } else {
                        var3 = var4;
                     }

                     currentPlanetEnumIndex = i;
                  } else {
                     this.localPlanets[i - 1] = new PlayerStatic(0, this.starAndPlanets[i], 0, 0, 0);
                     this.starAndPlanets[i].setDraw(true);

                     for(var6 = false; !var6; var6 = AEMath.abs((var3 = 7 + GlobalStatus.random.nextInt(11)) - var4) > 2 && !var10[var3]) {
                        ;
                     }
                  }
               }
               
               var10[var3] = true;

            
            if(var3 == var4 && i != 0) {
                  this.starAndPlanets[i].setRotation(0, var3 * 170 + 128, 0);
               } else if(i > 0 && stationsID[i - 1] == Status.getStation().getId()) {
                  this.starAndPlanets[i].setRotation(0, var3 * 170, 0);
               } else {
                  this.starAndPlanets[i].setRotation(-128 + GlobalStatus.random.nextInt(256), var3 * 170, 0);
               }

               this.starAndPlanets[i].moveForward(-20000); // расстояние планеты от центра мира
			   this.starAndPlanets[i].rotateEuler(0, 0, 0);
               this.starAndPlanets[i].updateTransform(true);
               this.starAndPlanets[i].update(1L);
               this.cameraControler.uniqueAppend_(this.starAndPlanets[i]);
            }
         }
      }
      GlobalStatus.random.setSeed(System.currentTimeMillis());
      this.tempVec = new AEVector3D();
      this.lensFlare = new LensFlareFX();
   }

   public final AbstractMesh[] getPlanets() {
      return this.starAndPlanets;
   }

   public final KIPlayer[] getPlanetTargets() {
      return this.localPlanets;
   }

   public final void render_() { // вот тут можно добавить освещение
      this.cameraControler.moveTo(GlobalStatus.renderer.sub_85().getLocalPos(this.tempVec));
      this.cameraControler.updateTransform(true);
      int var1;
	  
	  if(this.starAndPlanets != null && this.starAndPlanets[0] != null) {
		  GlobalStatus.renderer.forceRenderModel(this.starAndPlanets[0]);
	  }
	  
      for(var1 = 0; var1 < (this.inAlienSpace?1:this.starAndPlanets.length); ++var1) {
         if(this.inAlienSpace) {
            this.tempVec.x = 0;
            this.tempVec.y = 0;
            this.tempVec.z = 0;
            GlobalStatus.renderer.sub_85().getScreenPosition(this.tempVec);
         } else {
            GlobalStatus.renderer.sub_85().getScreenPosition(this.starAndPlanets[var1].getLocalPos(this.tempVec)); // большие и маленькие планеты
         }

         if(this.tempVec.z < 0) {
            if(var1 == 0) {
				/**
               this.sunSprite.setTransform(0);
               this.sunSprite.setRefPixelPosition(this.Vector3D.x, this.Vector3D.y);
               this.sunSprite.paint(SharedVariables.graphics);
               this.sunSprite.setTransform(5);
               this.sunSprite.setRefPixelPosition(this.Vector3D.x - 1, this.Vector3D.y);
               this.sunSprite.paint(SharedVariables.graphics);
               this.sunSprite.setTransform(3);
               this.sunSprite.setRefPixelPosition(this.Vector3D.x - 1, this.Vector3D.y - 1);
               this.sunSprite.paint(SharedVariables.graphics);
               this.sunSprite.setTransform(6);
               this.sunSprite.setRefPixelPosition(this.Vector3D.x, this.Vector3D.y - 1);
               this.sunSprite.paint(SharedVariables.graphics);
			   **/
            } else if(var1 == currentPlanetEnumIndex) {
				this.starAndPlanets[var1].setScale(1024, 1024, 0);
				GlobalStatus.renderer.forceRenderModel(this.starAndPlanets[var1]);
            } else {
				this.starAndPlanets[var1].setScale(128, 128, 0);
				GlobalStatus.renderer.forceRenderModel(this.starAndPlanets[var1]);
            }
         }
      }

   }

   public final void render2D() {
      if(this.inAlienSpace) {
         this.tempVec.x = 0;
         this.tempVec.y = 0;
         this.tempVec.z = 0;
         GlobalStatus.renderer.sub_85().getScreenPosition(this.tempVec);
		 /**
         this.sunSprite.setTransform(0);
         this.sunSprite.setRefPixelPosition(this.Vector3D.x, this.Vector3D.y);
         this.sunSprite.paint(SharedVariables.graphics);
         this.sunSprite.setTransform(5);
         this.sunSprite.setRefPixelPosition(this.Vector3D.x - 1, this.Vector3D.y);
         this.sunSprite.paint(SharedVariables.graphics);
         this.sunSprite.setTransform(3);
         this.sunSprite.setRefPixelPosition(this.Vector3D.x - 1, this.Vector3D.y - 1);
         this.sunSprite.paint(SharedVariables.graphics);
         this.sunSprite.setTransform(6);
         this.sunSprite.setRefPixelPosition(this.Vector3D.x, this.Vector3D.y - 1);
         this.sunSprite.paint(SharedVariables.graphics);
		 **/
      } else {
         GlobalStatus.renderer.sub_85().getScreenPosition(this.starAndPlanets[0].getLocalPos(this.tempVec));
      }

      this.lensFlare.render2D(this.tempVec);
   }
   
   public final void renderSunStreak__() { // окрашивает космос в цвет системы из файла
      if(this.inAlienSpace) {
         this.tempVec.x = 0;
         this.tempVec.y = 0;
         this.tempVec.z = -4096;
      } else {
         this.tempVec = this.starAndPlanets[0].getLocalDirection(this.tempVec);
      }

      this.tempVec.x = -this.tempVec.x;
      this.tempVec.y = -this.tempVec.y;
      this.tempVec.z = -this.tempVec.z;
      this.tempVec.normalize();
      this.tempVec = GlobalStatus.renderer.sub_85().getLocalTransform().transformVectorNoScale(this.tempVec);
      this.tempVec.x = -this.tempVec.x;
      this.tempVec = GlobalStatus.renderer.sub_85().getLocalPos(this.tempVec);
      this.cameraControler.moveTo(this.tempVec);
      this.cameraControler.updateTransform(true);
      if(this.inAlienSpace) {
         this.tempVec.x = 0;
         this.tempVec.y = 0;
         this.tempVec.z = 20000;
      } else {
         this.tempVec = this.starAndPlanets[0].getLocalPos(this.tempVec);
      }

      this.tempVec.subtract(GlobalStatus.renderer.sub_85().getLocalPos());
      this.tempVec.normalize();
      AEVector3D var1;
      (var1 = new AEVector3D(GlobalStatus.renderer.sub_85().getLocalDirection())).normalize();
      this.tempVec.subtract(var1);
      float var2 = (float)this.tempVec.getLength() / 8192.0F;
      Level.bgR = 0;
      Level.bgG = 0;
      Level.bgB = 0;
      Level.bgR = AEMath.max(Level.spaceR, (int)(Level.starR * var2));
      Level.bgG = AEMath.max(Level.spaceG, (int)(Level.starG * var2));
      Level.bgB = AEMath.max(Level.spaceB, (int)(Level.starB * var2));
   }
}