package GoF2;

import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

import AE.AbstractMesh;
import AE.AECamera;
import AE.GlobalStatus;
import AE.Math.AEMath;
import AE.Math.AEVector3D;
import AE.Math.Matrix;
import AE.PaintCanvas.Font;
import AE.TargetFollowCamera;
import AE.Vec3f;
import HardEngine.interface_loader;

public final class Radar {

   private int[] asteroidsInFront;
   private interface_loader[] AEGraphics;
   private Sprite logos_small_sprite;
   private Sprite scanprocessSprite;
   private Sprite hud_meteor_class_sprite;
   private Sprite hudBarsSprite;
   private int screenProjectionX;
   private int screenProjectionY;
   private int elipsoidCenterX;
   private int elipsoidCenterY;
   private int elipsoidWidth;
   private int elipsoidHeight;
   public boolean inViewFrustum;
   private KIPlayer[] enemies;
   private KIPlayer[] landmarks;
   private KIPlayer[] asteroids;
   private AbstractMesh[] planets;
   private Route playerRoute;
   private Level level;
   private Matrix camInvMatrix = new Matrix();
   private AEVector3D tempCameraSpacePos = new AEVector3D();
   private AEVector3D tempPos = new AEVector3D();
   private AEVector3D playerPos = new AEVector3D();
   private AEVector3D tempContextPosition = new AEVector3D();
   private Vec3f floatVector = new Vec3f();
   private int enemyArmorBarHeight;
   private String[] stationaryPlayersNames = new String[4];
   private String closestStatPlayerDistanceVisible;
   private int enemyScanPassedTime;
   private int asteroidScanPassedTime;
   private int planetScanPassedTime;
   private int landmarkScanPassedTime;
   public KIPlayer contextShip;
   private KIPlayer targetedPlayer;
   private KIPlayer contextAsteroid;
   private KIPlayer targetedAsteroid;
   private AbstractMesh contextPlanet;
   public AbstractMesh targetedPlanet;
   public KIPlayer tractorBeamTarget;
   public KIPlayer contextLandmark;
   public KIPlayer targetedLandmark;
   public boolean draw;
   private boolean onPlanetCourse_;
   private int targetedPlanetLocalIndex;
   private boolean showCargo;
   private boolean showAasteroids;
   private boolean scanerPresent;
   private boolean drillPresent;
   private boolean tractorBeamPresent;
   private boolean tractorBeamAutomatic;
   private boolean tractorBeam360;
   private boolean pastIntro;
   private int tractorBeamScanTime;
   private int scanTime;
   private int activeEnemyEnemiesCount;
   private String hud_shipname_scan = "";
   
    public Radar(Level var1) {
		this.level = var1;
		this.elipsoidCenterX = GlobalStatus.var_e75 / 2;
		this.elipsoidCenterY = GlobalStatus.var_eb6 / 2;
		this.elipsoidWidth = (this.elipsoidCenterX - 5) * (this.elipsoidCenterX - 5);
		this.elipsoidHeight = (this.elipsoidCenterY - 20) * (this.elipsoidCenterY - 20);
		this.stationaryPlayersNames[0] = (Status.inAlienOrbit()?GlobalStatus.gameText.getText(238):Status.getStation().getName()) + " " + GlobalStatus.gameText.getText(40); // (����) " " (�������)
		this.stationaryPlayersNames[1] = GlobalStatus.gameText.getText(271); // �������� �����
		this.stationaryPlayersNames[2] = "";
		this.stationaryPlayersNames[3] = GlobalStatus.gameText.getText(269); // �����������
		AbstractMesh[] var5;
		
		AEGraphics = new interface_loader[2];
		for(int count = 0; count < AEGraphics.length; ++count) {
			AEGraphics[count] = new interface_loader();
		}
		
		if((var5 = var1.getPlayerGuns_()) != null) {
			for(int var2 = 0; var2 < var5.length; ++var2) {
				if(var5[var2] instanceof RocketGun) {
					((RocketGun)var5[var2]).setRadar(this);
				}
			}
		}
		
		try {
			
			this.hudBarsSprite = new Sprite(Globals.hudBars, Globals.hudBars.getWidth() / 8, Globals.hudBars.getHeight());
			this.logos_small_sprite = new Sprite(Globals.logosSmall, Globals.logosSmall.getHeight(), Globals.logosSmall.getHeight());
			this.logos_small_sprite.defineReferencePixel(Globals.logosSmall.getHeight(), Globals.logosSmall.getHeight());
			this.scanprocessSprite = new Sprite(Globals.hudScanprocessAnim, Globals.hudScanprocessAnim.getHeight(), Globals.hudScanprocessAnim.getHeight());
			this.scanprocessSprite.defineReferencePixel(Globals.hudScanprocessAnim.getHeight() / 2, Globals.hudScanprocessAnim.getHeight() / 2);
			this.hud_meteor_class_sprite = new Sprite(Globals.hudMeteorClass, 11, 11);
			this.hud_meteor_class_sprite.defineReferencePixel(11, 11);
			
			Item drillItem = Status.getShip().getFirstEquipmentOfSort(19);
			Item scannerItem = Status.getShip().getFirstEquipmentOfSort(17);
			Item tractorBeamItem = Status.getShip().getFirstEquipmentOfSort(13);
			this.drillPresent = drillItem != null;
			if(scannerItem != null) {
				this.scanerPresent = true;
				this.showCargo = scannerItem.getAttribute(29) == 1;
				this.showAasteroids = scannerItem.getAttribute(28) == 1;
				this.scanTime = scannerItem.getAttribute(27);
			} else {
				this.scanerPresent = false;
				this.showCargo = false;
				this.showAasteroids = false;
				this.scanTime = 8000;
			}
			
			if(tractorBeamItem != null) {
				this.tractorBeamPresent = true;
				int tractorBeamMode = tractorBeamItem.getAttribute(21);
				this.tractorBeamAutomatic = tractorBeamMode == 1;
				this.tractorBeam360 = tractorBeamMode == 2;
				this.tractorBeamScanTime = tractorBeamItem.getAttribute(22);
			} else {
				this.tractorBeamPresent = false;
				this.tractorBeamScanTime = 0;
				this.tractorBeamAutomatic = false;
			}
			
			this.pastIntro = Status.getCurrentCampaignMission() > 0;
			this.activeEnemyEnemiesCount = 0;
			this.draw = true;
			this.asteroidsInFront = new int[5];
			
			for(int var11 = 0; var11 < this.asteroidsInFront.length; ++var11) {
				this.asteroidsInFront[var11] = -1;
			}
		} catch (Exception var4) {
			var4.printStackTrace();
		}
	}

   public final void OnRelease() {
      this.enemies = null;
      this.landmarks = null;
      this.playerRoute = null;
      this.level = null;
      this.playerPos = null;
      this.tempContextPosition = null;
      this.tempCameraSpacePos = null;
   }

   private void elipsoidIntersect(AECamera var1, AEVector3D var2) {
      this.tempPos.set(var2);
      this.tempCameraSpacePos = var1.getInverseTransform(this.camInvMatrix).transformVector2(this.tempPos, this.tempCameraSpacePos);
      this.tempCameraSpacePos.y = -this.tempCameraSpacePos.y;
      this.tempCameraSpacePos.z = -this.tempCameraSpacePos.z;
      var1.getScreenPosition(this.tempPos);
      this.screenProjectionX = this.tempPos.x;
      this.screenProjectionY = this.tempPos.y;
      this.inViewFrustum = this.screenProjectionX <= GlobalStatus.var_e75 && this.screenProjectionX >= 0 && this.screenProjectionY <= GlobalStatus.var_eb6 && this.screenProjectionY >= 0;
      if(this.tempCameraSpacePos.z < 0 || !this.inViewFrustum) {
         AEVector3D var4 = this.tempCameraSpacePos;
         int var3 = this.screenProjectionY;
         int var9 = this.screenProjectionX;
         float var5 = (float)(this.elipsoidCenterX - var9);
         float var6 = (float)(this.elipsoidCenterY + -10 - var3);
         float var8;
         if((var8 = var5 * var5 / (float)this.elipsoidWidth + var6 * var6 / (float)this.elipsoidHeight) >= 0.0F) {
            float var7 = (float)AEMath.sqrt((long)(var8 * 4096.0F)) / 6144.0F; // 4096 default
            var8 = (var8 - var7) / var8;
            if(0.0F <= var8 && var8 <= 1.0F) {
               var4.x = (int)((float)var9 + var5 * var8);
               var4.y = (int)((float)var3 + var6 * var8);
            }
         }

         this.tempCameraSpacePos = var4;
         this.screenProjectionX = this.tempCameraSpacePos.x;
         this.screenProjectionY = this.tempCameraSpacePos.y;
      }

   }

   private void elipsoidIntersect(AECamera var1, KIPlayer var2) {
      this.elipsoidIntersect(var1, var2.getPosition(this.tempPos));
   }

   public final void draw(Player var1, AECamera var2, TargetFollowCamera var3, Hud var4, int var5) {
      if(!this.draw) {
         KIPlayer[] var22;
         if((var22 = this.level.getEnemies()) != null) {
            for(int var24 = 0; var24 < var22.length; ++var24) {
               var22[var24].withinRenderDistance = true;
            }
         }

      } else {
         Mission var6;
         boolean var7 = !(var6 = Status.getMission()).isEmpty() && var6.getType() != 11 && var6.getType() != 0;
         boolean var23 = this.level.getPlayer().isLookingBack();
         boolean var8 = Status.inAlienOrbit();
         this.enemies = this.level.getEnemies();
         this.landmarks = this.level.getLandmarks();
         this.playerRoute = this.level.getPlayerRoute();
         this.asteroids = this.level.getAsteroids();
         this.planets = this.level.getStarSystem().getPlanets();
         if(!var8) {
            this.onPlanetCourse_ = this.level.getPlayer().goingToPlanet() && !this.level.getPlayer().isDockingToPlanet() && this.level.getPlayer().getAutoPilotTarget() == this.level.getStarSystem().getPlanetTargets()[Status.getSystem().getStationEnumIndex(Status.getSystem().getStations()[this.targetedPlanetLocalIndex])];
         } else {
            this.onPlanetCourse_ = this.level.getPlayer().goingToPlanet() && !this.level.getPlayer().isDockingToPlanet();
         }

         if(this.playerRoute != null && this.playerRoute.getDockingTarget_() != null) {
            this.elipsoidIntersect(var2, this.playerRoute.getDockingTarget_());
            this.closestStatPlayerDistanceVisible = this.calcDistance(this.playerRoute.getDockingTarget_().x, this.playerRoute.getDockingTarget_().y, this.playerRoute.getDockingTarget_().z, var1);
            if(this.inViewFrustum) {
               GlobalStatus.graphics.drawImage(Globals.hudLockonWaypoint, this.screenProjectionX, this.screenProjectionY, 3);
               Font.sub_14d_CENTER(this.closestStatPlayerDistanceVisible, this.screenProjectionX, this.screenProjectionY + 12, 1);
            } else if(this.scanerPresent) {
               GlobalStatus.graphics.drawImage(Globals.hudWaypoint, this.screenProjectionX, this.screenProjectionY, 3);
            }
         }

         boolean var9 = this.targetedAsteroid != null || this.tractorBeamTarget != null || this.targetedPlanet != null;
         boolean var10 = false;
         int var12;
         int var13;
         if(this.landmarks != null) {
            for(int var11 = 0; var11 < this.landmarks.length; ++var11) {
               if(var11 != 2 && this.landmarks[var11] != null && this.landmarks[var11].isVisible()) {
                  this.elipsoidIntersect(var2, this.landmarks[var11]);
                  if(this.inViewFrustum) {
                     if(!var9 && !var10 && !var23 && var11 != 3) {
                        var12 = Crosshair.screenPos.x - 30;
                        var13 = Crosshair.screenPos.y - 30;
                        if(this.screenProjectionX > var12 && this.screenProjectionX < var12 + 60 && this.screenProjectionY > var13 && this.screenProjectionY < var13 + 60) {
                           if(this.contextLandmark != this.landmarks[var11]) {
                              this.landmarkScanPassedTime = 0;
                           }

                           this.contextLandmark = this.landmarks[var11];
                           var10 = true;
                        }
                     }

                     this.playerPos = var1.getPosition(this.playerPos);
                     this.tempContextPosition = this.landmarks[var11].player.getPosition(this.tempContextPosition);
                     if(var11 != 3) {
                        GlobalStatus.graphics.drawImage(Globals.bracketBox, this.screenProjectionX, this.screenProjectionY, 3);
                     }

                     Font.drawString(this.stationaryPlayersNames[var11], var11 == 0?this.screenProjectionX + 25:this.screenProjectionX + 15, this.screenProjectionY - 15, 0);
                     if(var11 < 2) {
                        if(var11 == 0 && !var8) {
                           Font.drawString(GlobalStatus.gameText.getText(37) + ": " + Status.getStation().getTecLevel(), var11 == 0?this.screenProjectionX + 25:this.screenProjectionX + 15, this.screenProjectionY, 1);
                           this.closestStatPlayerDistanceVisible = this.calcDistance(this.landmarks[var11].var_4c6, this.landmarks[var11].var_4e6, this.landmarks[var11].var_521, var1);
                           Font.drawString(this.closestStatPlayerDistanceVisible, var11 == 0?this.screenProjectionX + 35:this.screenProjectionX + 35, this.screenProjectionY + 15, 1);
                        } else {
                           this.closestStatPlayerDistanceVisible = this.calcDistance(this.landmarks[var11].var_4c6, this.landmarks[var11].var_4e6, this.landmarks[var11].var_521, var1);
                           Font.drawString(this.closestStatPlayerDistanceVisible, var11 == 0?this.screenProjectionX + 35:this.screenProjectionX + 35, this.screenProjectionY + 5, 1);
                        }
                     }
                  } else if(var11 == 3) {
                     GlobalStatus.graphics.drawImage(Globals.hudVortex, this.screenProjectionX, this.screenProjectionY, 3);
                  }
               }
            }

            if(!var23) {
               if(this.targetedLandmark != null) {
                  if(this.targetedLandmark.isDead()) {
                     this.targetedLandmark = null;
                  }

                  this.targetedLandmark = null;
               }

               if(var10 && !this.level.getPlayer().isDockingToAsteroid() && !this.level.getPlayer().isAutoPilot()) {
                  this.landmarkScanPassedTime += var5;
                  if(this.landmarkScanPassedTime > this.scanTime) {
                     this.targetedLandmark = this.contextLandmark;
                  }

                  if(this.landmarkScanPassedTime > 0) {
                     if(this.contextLandmark != null && !this.contextLandmark.equals(this.targetedLandmark)) {
                        this.scanprocessSprite.setFrame((int)((float)(this.scanprocessSprite.getRawFrameCount() - 2) * ((float)this.landmarkScanPassedTime / (float)this.scanTime)));
                        this.scanprocessSprite.setRefPixelPosition(Crosshair.screenPos.x, Crosshair.screenPos.y);
                        this.scanprocessSprite.paint(GlobalStatus.graphics);
                     } else if(Layout.quickClockHigh_()) {
                        this.scanprocessSprite.setFrame(this.scanprocessSprite.getRawFrameCount() - 2);
                        this.scanprocessSprite.setRefPixelPosition(Crosshair.screenPos.x, Crosshair.screenPos.y);
                        this.scanprocessSprite.paint(GlobalStatus.graphics);
                     }
                  }
               } else {
                  if(this.targetedLandmark == null) {
                     this.contextLandmark = null;
                  }

                  this.landmarkScanPassedTime = 0;
               }
            }
         }

         var9 = this.targetedAsteroid != null || this.tractorBeamTarget != null || this.targetedLandmark != null;
         boolean var28 = false;
         boolean var14;
         boolean var15;
         int var19;
         int var18;
         if(this.planets != null) {
            for(var12 = 1; var12 < this.planets.length; ++var12) {
               this.elipsoidIntersect(var2, this.planets[var12].getLocalPos(this.tempPos));
               if(this.inViewFrustum) {
                  int[] var30;
                  var8 = (var30 = Status.getSystem().getStations())[var12 - 1] == Status.getStation().getId();
                  var14 = false;
                  if(Status.getSystem().getJumpGateEnumIndex() == var12 - 1 && !var8) {
                     GlobalStatus.graphics.drawImage(Globals.menuMapJumpgate, this.screenProjectionX + 10, this.screenProjectionY - 10, 0);
                     var14 = true;
                  }

                  var15 = false;
                  Mission var16 = Status.getCampaignMission();
                  Mission var17 = Status.getFreelanceMission();
                  if(var16 != null && !var16.isEmpty() && var30[var12 - 1] == var16.getTargetStation() && !var8) {
					  GlobalStatus.graphics.drawImage(Globals.menuMapMainmission, this.screenProjectionX + 10 + (var14?14:0), this.screenProjectionY - 10, 0);
					  var15 = true;
                  }

                  if(var17 != null && !var17.isEmpty() && var30[var12 - 1] == var17.getTargetStation() && !var8) {
					  GlobalStatus.graphics.drawImage(Globals.menuMapSidemission, this.screenProjectionX + 10 + (var14?14:0), this.screenProjectionY - 10, 0);
					  var15 = true;
				  }

                  if(!var9 && !var28 && !var23) {
                     var18 = StarSystem.currentPlanetEnumIndex == var12?100:30;
                     var19 = Crosshair.screenPos.x - (var18 >> 1);
                     int var20 = Crosshair.screenPos.y - (var18 >> 1);
                     if(this.screenProjectionX > var19 && this.screenProjectionX < var19 + var18 && this.screenProjectionY > var20 && this.screenProjectionY < var20 + var18) {
                        if(var12 != StarSystem.currentPlanetEnumIndex) {
                           if(this.contextPlanet != this.planets[var12]) {
                              this.planetScanPassedTime = 0;
                           }

                           this.targetedPlanetLocalIndex = var12 - 1;
                           this.contextPlanet = this.planets[var12];
                           var28 = true;
                        }

                        Font.drawString(Status.getPlanetNames()[var12 - 1], this.screenProjectionX + 10 + (var14?14:0) + (var15?14:0), this.screenProjectionY - 10, 0);
                     }
                  }
               }
            }

            if(!var10 && !var23) {
               if(this.targetedPlanet != null) {
                  this.targetedPlanet = null;
               }

               if(var28 && !this.level.getPlayer().isDockingToAsteroid()) {
                  this.planetScanPassedTime += var5;
                  if(this.planetScanPassedTime > this.scanTime) {
                     if(var7) {
                        var4.hudEvent(21, this.level.getPlayer());
                     } else {
                        this.targetedPlanet = this.contextPlanet;
                        if(this.onPlanetCourse_) {
                           this.level.getPlayer().stopPlanetDock_(var3);
                        }
                     }
                  }

                  if(this.planetScanPassedTime > 0 && !var7) {
                     if(this.contextPlanet != null && !this.contextPlanet.equals(this.targetedPlanet)) {
                        this.scanprocessSprite.setFrame((int)((float)(this.scanprocessSprite.getRawFrameCount() - 2) * ((float)this.planetScanPassedTime / (float)this.scanTime)));
                        this.scanprocessSprite.setRefPixelPosition(Crosshair.screenPos.x, Crosshair.screenPos.y);
                        this.scanprocessSprite.paint(GlobalStatus.graphics);
                     } else if(!this.level.getPlayer().isDockingToPlanet() && Layout.quickClockHigh_()) {
                        this.scanprocessSprite.setFrame(this.scanprocessSprite.getRawFrameCount() - 2);
                        this.scanprocessSprite.setRefPixelPosition(Crosshair.screenPos.x, Crosshair.screenPos.y);
                        this.scanprocessSprite.paint(GlobalStatus.graphics);
                     }
                  }
               } else {
                  if(this.targetedPlanet == null) {
                     this.contextPlanet = null;
                  }

                  this.planetScanPassedTime = 0;
               }
            }
         }

         if(this.targetedPlayer != null && (this.targetedPlayer.isDying() || this.targetedPlayer.isDead())) {
            this.targetedPlayer = null;
            var9 = false;
            this.contextShip = null;
         }

         boolean var29 = false;
         int var38;
         int var37;
         if(this.scanerPresent && this.enemies != null) {
            for(var13 = 0; var13 < this.enemies.length; ++var13) {
               KIPlayer var25;
               if((var25 = this.enemies[var13]).player.isActive() && (!var25.isDying() || var25.hasCargo)) {
                  this.elipsoidIntersect(var2, var25);
                  var14 = var25.hasCargo && (var25.isDead() || var25.isDying());
                  if(!var25.isDead() && !var25.isDying() || var25.hasCargo) {
                     var15 = var25.equals(this.targetedPlayer);
                     Gun[] var34;
                     if(!var25.isDead() && this.level.getPlayer().player.hasGunOfType(1) && (var34 = this.level.getPlayer().player.guns[1]) != null) {
                        GlobalStatus.graphics.setStrokeStyle(1);

                        for(var37 = 0; var37 < var34.length; ++var37) {
                           Gun var39;
                           if((var39 = var34[var37]) != null && (var39.subType == 7 || var39.subType == 6) && var39.inAir) {
                              this.playerPos.set(var39.projectilesPos[0]);
                              this.tempContextPosition = var25.getPosition(this.tempContextPosition);
                              this.tempContextPosition.subtract(this.playerPos);
                              if((var19 = this.tempContextPosition.getLength()) < var39.getMagnitude()) {
                                 float var41;
                                 if((var41 = (float)var19 / (float)var39.getMagnitude()) > 1.0F) {
                                    var41 = 1.0F;
                                 }

                                 int var21 = -16777216 | (int)(var41 * 255.0F) << 16 | (int)((1.0F - var41) * 255.0F) << 8;
                                 GlobalStatus.graphics.setColor(var21);
                                 GlobalStatus.renderer.sub_85().getScreenPosition(this.playerPos);
                                 this.tempContextPosition.x = this.screenProjectionX;
                                 this.tempContextPosition.y = this.screenProjectionY;
                                 this.tempContextPosition.z = 0;
                                 this.tempContextPosition.subtract(this.playerPos);
                                 this.tempContextPosition.z = 0;
                                 this.tempContextPosition.x <<= 12;
                                 this.tempContextPosition.y <<= 12;
                                 this.tempContextPosition.normalize();
                                 this.tempContextPosition.scale(16);
                                 this.playerPos.add(this.tempContextPosition);
                                 GlobalStatus.graphics.drawLine(this.screenProjectionX, this.screenProjectionY, this.playerPos.x, this.playerPos.y);
                              }
                           }
                        }

                        GlobalStatus.graphics.setStrokeStyle(0);
                     }

                     if(!var25.var_5c1 && !var25.junk && !var14 && var25.player.enemy) {
                        ++this.activeEnemyEnemiesCount;
                     }

                     Image var32;
                     if(this.inViewFrustum) {
                        this.playerPos = var1.getPosition(this.playerPos);
                        this.tempContextPosition = var25.player.getPosition(this.tempContextPosition);
                        if(this.playerPos.x - this.tempContextPosition.x <= 24000 && this.playerPos.x - this.tempContextPosition.x >= -24000 && this.playerPos.y - this.tempContextPosition.y <= 24000 && this.playerPos.y - this.tempContextPosition.y >= -24000 && this.playerPos.z - this.tempContextPosition.z <= 24000 && this.playerPos.z - this.tempContextPosition.z >= -24000 && var25.getMeshId() != 9996) {
                           label845: {
                              var25.withinRenderDistance = true;
                              Image var10001;
                              if(var14) {
                                 var10001 = Globals.bracketBox;
                              } else {
								 
								 int hullBarPercent = (int)((var25.player.getDamageRate() / 100.0f) * Globals.hudBarYellowFull.getWidth());
								 int empBarPercent = (int)((float)var25.player.getEmpDamageRate() / 100.0F * Globals.hudBarWhiteFull.getWidth());
								 int armorBarPercent = (int)((float)var25.player.getArmorDamageRate() / 100.0F * Globals.hudBarWhiteFull.getWidth());
								 int sheldBarPercent = (int)((float)var25.player.getShieldDamageRate() / 100.0F * Globals.hudBarWhiteFull.getWidth());
								 
								 Image barBackground = var25.player.enemy ? Globals.hudBarRedEmpty : (var25.player.friend ? Globals.hudBarGreenEmpty : Globals.hudBarYellowEmpty);
								 Image barFull = var25.player.enemy ? Globals.hudBarRedFull : (var25.player.friend ? Globals.hudBarGreenFull : Globals.hudBarYellowFull);
								 
								 if(GlobalStatus.shields == 1) {
									GlobalStatus.graphics.drawImage(Globals.hudBarBlueEmpty, this.screenProjectionX - (Globals.hudBarYellowFull.getWidth() / 2), this.screenProjectionY + (Globals.hudLockonNeutral.getHeight() / 2), 0);
									GlobalStatus.graphics.drawRegion(Globals.hudBarBlueFull, 0, 0, sheldBarPercent, Globals.hudBarBlueFull.getHeight(), 0, this.screenProjectionX - (Globals.hudBarYellowFull.getWidth() / 2), this.screenProjectionY + (Globals.hudLockonNeutral.getHeight() / 2), 0);
									
									GlobalStatus.graphics.drawImage(Globals.hudBarRedEmpty, this.screenProjectionX - (Globals.hudBarYellowFull.getWidth() / 2), this.screenProjectionY + (Globals.hudLockonNeutral.getHeight() / 2) + Globals.hudBarBlueEmpty.getHeight(), 0);
									GlobalStatus.graphics.drawRegion(Globals.hudBarRedFull, 0, 0, hullBarPercent, Globals.hudBarYellowFull.getHeight(), 0, this.screenProjectionX - (Globals.hudBarYellowFull.getWidth() / 2), this.screenProjectionY + (Globals.hudLockonNeutral.getHeight() / 2) + Globals.hudBarBlueEmpty.getHeight(), 0);
									
									GlobalStatus.graphics.drawRegion(Globals.hudBarOrangeFull, 0, 0, armorBarPercent, Globals.hudBarOrangeFull.getHeight(), 0, this.screenProjectionX - (Globals.hudBarOrangeFull.getWidth() / 2), this.screenProjectionY + (Globals.hudLockonNeutral.getHeight() / 2) + Globals.hudBarBlueEmpty.getHeight(), 0);
									
									GlobalStatus.graphics.drawImage(Globals.hudBarWhiteEmpty, this.screenProjectionX - (Globals.hudBarYellowFull.getWidth() / 2), this.screenProjectionY + (Globals.hudLockonNeutral.getHeight() / 2) + Globals.hudBarWhiteEmpty.getHeight() * 2, 0);
									GlobalStatus.graphics.drawRegion(Globals.hudBarWhiteFull, 0, 0, empBarPercent, Globals.hudBarWhiteFull.getHeight(), 0, this.screenProjectionX - (Globals.hudBarYellowFull.getWidth() / 2), this.screenProjectionY + (Globals.hudLockonNeutral.getHeight() / 2) + Globals.hudBarWhiteEmpty.getHeight() * 2, 0);
								 } else {
									GlobalStatus.graphics.drawImage(barBackground, this.screenProjectionX - (Globals.hudBarYellowFull.getWidth() / 2), this.screenProjectionY + (Globals.hudLockonNeutral.getHeight() / 2), 0);
									GlobalStatus.graphics.drawRegion(barFull, 0, 0, hullBarPercent, Globals.hudBarYellowFull.getHeight(), 0, this.screenProjectionX - (Globals.hudBarYellowFull.getWidth() / 2), this.screenProjectionY + (Globals.hudLockonNeutral.getHeight() / 2), 0);
									
									GlobalStatus.graphics.drawImage(Globals.hudBarWhiteEmpty, this.screenProjectionX - (Globals.hudBarYellowFull.getWidth() / 2), this.screenProjectionY + (Globals.hudLockonNeutral.getHeight() / 2) + Globals.hudBarWhiteEmpty.getHeight(), 0);
									GlobalStatus.graphics.drawRegion(Globals.hudBarWhiteFull, 0, 0, empBarPercent, Globals.hudBarWhiteFull.getHeight(), 0, this.screenProjectionX - (Globals.hudBarYellowFull.getWidth() / 2), this.screenProjectionY + (Globals.hudLockonNeutral.getHeight() / 2) + Globals.hudBarWhiteEmpty.getHeight(), 0);
								 }
								 
								 /**
                                 var38 = var25.var_1a9.var_8bc?0:(var25.var_1a9.var_8d3?6:4);
                                 this.var_ddc = (int)((float)var25.var_1a9.getHullPointsPercent() / 100.0F * 16.0F);
                                 this.hudBarsSprite.setFrame(var38 + 1);
                                 this.hudBarsSprite.setPosition(this.var_a9a + 8 + 2, this.var_ab8 - 8);
                                 this.hudBarsSprite.paint(SharedVariables.graphics);
                                 this.hudBarsSprite.setFrame(var38);
                                 SharedVariables.graphics.setClip(0, this.var_ab8 - 8 + 16 - this.var_ddc, SharedVariables.var_e75, this.var_ddc);
                                 this.hudBarsSprite.paint(SharedVariables.graphics);
								 SharedVariables.graphics.setClip(0, 0, SharedVariables.var_e75, SharedVariables.var_eb6);
                                 
								 this.var_ddc = (int)((float)var25.var_1a9.getEMPHealthPointPercent() / 100.0F * 16.0F);
                                 this.hudBarsSprite.setFrame(3);
                                 this.hudBarsSprite.setPosition(this.var_a9a + 8 + 7, this.var_ab8 - 8);
                                 this.hudBarsSprite.paint(SharedVariables.graphics);
                                 this.hudBarsSprite.setFrame(2);
                                 SharedVariables.graphics.setClip(0, this.var_ab8 - 8 + 16 - this.var_ddc, SharedVariables.var_e75, this.var_ddc);
                                 this.hudBarsSprite.paint(SharedVariables.graphics);
                                 SharedVariables.graphics.setClip(0, 0, SharedVariables.var_e75, SharedVariables.var_eb6);
								 **/
								 
                                 if(!var15) {
                                    break label845;
                                 }

                                 var10001 = var25.player.enemy ? Globals.hudLockonEnemy : (var25.player.friend ? Globals.hudLockonFriend : Globals.hudLockonNeutral);
                              }
                              AEGraphics[1].drawImage(var10001, this.screenProjectionX, this.screenProjectionY, 3);
                           }
                        } else {
                           var32 = var14?Globals.bracketBox:(var15?(var25.player.enemy?Globals.hudLockonEnemyFar:(var25.player.friend?Globals.hudLockonFriendFar:Globals.hudLockonNeutral)):(var25.player.enemy?Globals.hudRadarIconEnemy:(var25.player.friend?Globals.hudRadarIconFirend:Globals.hudRadarIconNeutral)));
                           GlobalStatus.graphics.drawImage(var32, this.screenProjectionX, this.screenProjectionY, 3);
                           var25.withinRenderDistance = false;
                        }

                        if(!this.level.getPlayer().isAutoPilot() && (!var25.junk || var14) && (!var29 || var25.stunned) && (var14 && var25.isDead() || !var14)) {
                           if(var14 && this.tractorBeamTarget == null && (this.tractorBeamAutomatic && !this.tractorBeam360)) {
                              this.tractorBeamTarget = var25;
                              var29 = true;
                              this.contextShip = var25;
                           } else {
                              var38 = Crosshair.screenPos.x - 30;
                              var37 = Crosshair.screenPos.y - 30;
                              if(this.screenProjectionX > var38 && this.screenProjectionX < var38 + 60 && this.screenProjectionY > var37 && this.screenProjectionY < var37 + 60 && (var14 && this.tractorBeamPresent || this.pastIntro && !this.onPlanetCourse_)) {
                                 if(this.contextShip != var25) {
                                    this.enemyScanPassedTime = 0;
                                 }

                                 this.contextShip = var25;
                                 var29 = true;
                              }
                           }
                        }
                     } else if(var14) {
                        GlobalStatus.graphics.drawImage(Globals.hudRadarIconNeutral, this.screenProjectionX, this.screenProjectionY, 3);
                        GlobalStatus.graphics.drawImage(this.enemies[var13].junk?Globals.hudSpacejunk:(this.enemies[var13].race == 9?Globals.hudCrateVoid:Globals.hudCrate), this.screenProjectionX, this.screenProjectionY, 3);
                     } else {
                        var32 = var25.player.enemy?(var15?Globals.hudLockonEnemyFar:Globals.hudRadarIconEnemy):(var25.player.friend?(var15?Globals.hudLockonFriendFar:Globals.hudRadarIconFirend):(var15?Globals.hudLockonNeutralFar:Globals.hudRadarIconNeutral));
                        GlobalStatus.graphics.drawImage(var32, this.screenProjectionX, this.screenProjectionY, 3);
                     }
					 
					 if(!this.level.getPlayer().isAutoPilot() && (!var25.junk || var14) && (var14 && var25.isDead() || !var14)) {
                           if(var14 && this.tractorBeamTarget == null && (this.tractorBeam360 && !tractorBeamAutomatic)) {
                              this.tractorBeamTarget = var25;
                              var29 = true;
                              this.contextShip = var25;
                           }
					 }
					 
                  }
               }
            }

            if(!this.level.getPlayer().isDockingToAsteroid() && !var23 && !var9) {
               boolean var31 = this.contextShip != null && this.contextShip.hasCargo && (this.contextShip.isDead() || this.contextShip.isDying());
               if(this.targetedPlayer != null && (this.targetedPlayer.isDying() || this.targetedPlayer.isDead())) {
                  this.targetedPlayer = null;
               }

               if(var29) {
                  this.enemyScanPassedTime += var5;
                  if(this.enemyScanPassedTime > (!var31 && !this.contextShip.stunned?this.scanTime:this.tractorBeamScanTime)) {
                     if(!var31 && !this.contextShip.stunned) {
                        if(this.targetedPlayer != this.contextShip) {
                           this.targetedPlayer = this.contextShip;
                           if(this.showCargo) {
                              this.playerPos = var1.getPosition(this.playerPos);
                              this.tempContextPosition = this.targetedPlayer.player.getPosition(this.tempContextPosition);
                              if(this.playerPos.x - this.tempContextPosition.x < 24000 || this.playerPos.x - this.tempContextPosition.x > -24000 || this.playerPos.y - this.tempContextPosition.y < 24000 || this.playerPos.y - this.tempContextPosition.y > -24000 || this.playerPos.z - this.tempContextPosition.z < 24000 || this.playerPos.z - this.tempContextPosition.z > -24000) {
                                 int[] var27 = this.targetedPlayer.cargo;
                                 if(this.targetedPlayer.cargo != null) {
                                    for(int var35 = 0; var35 < var27.length; var35 += 2) {
                                       if(var27[var35 + 1] > 0) {
                                          var4.catchCargo(var27[0], var27[var35 + 1], false, false, false, true);
                                          break;
                                       }
                                    }
                                 } else {
                                    var4.hudEvent(22, this.level.getPlayer()); // ���� �������� ����� ������� ���� - ���������� �����
                                 }
                              }
                           }
                        }
                     } else {
                        this.tractorBeamTarget = this.contextShip;
                        if(!this.tractorBeamPresent) {
                           var4.hudEvent(9, this.level.getPlayer());
                           this.tractorBeamTarget = null;
                        }
                     }

                     this.enemyScanPassedTime = 0;
                  }

                  if(this.enemyScanPassedTime > (!var31 && !this.contextShip.stunned?0:500) && this.contextShip != null && !this.contextShip.equals(!var31 && !this.contextShip.stunned?this.targetedPlayer:this.tractorBeamTarget)) {
                     this.scanprocessSprite.setFrame((int)((float)(this.scanprocessSprite.getRawFrameCount() - 2) * ((float)(this.enemyScanPassedTime - (!var31 && !this.contextShip.stunned?0:500)) / (float)(!var31 && !this.contextShip.stunned?this.scanTime:this.tractorBeamScanTime - 500))));
                     this.scanprocessSprite.setRefPixelPosition(Crosshair.screenPos.x, Crosshair.screenPos.y);
                     this.scanprocessSprite.paint(GlobalStatus.graphics);
                  }
               } else {
                  if(var31 && this.tractorBeamTarget == null || !var31 && this.targetedPlayer == null) {
                     this.contextShip = null;
                  }

                  this.enemyScanPassedTime = 0;
               }
            }
         }

         var13 = 0;

         for(int var26 = 0; var26 < this.asteroidsInFront.length; ++var26) {
            this.asteroidsInFront[var26] = -1;
         }

         var9 = this.contextShip != null && this.targetedPlayer == null || this.tractorBeamTarget != null || this.targetedLandmark != null || this.targetedPlanet != null;
         var8 = false;
         if(!this.level.getPlayer().isLockedOnAsteroid_() && this.asteroids != null) {
            var14 = false;
            if(this.showAasteroids) {
               var14 = this.level.isInAsteroidCenterRange(this.level.getPlayer().getPosition());
            }

            int var33;
            boolean var40;
            for(var33 = 0; var33 < this.asteroids.length; ++var33) {
               KIPlayer var36;
               if((var36 = this.asteroids[var33]).hasCargo && (var36.isDead() || var36.isDying()) || !var36.isDead() && !var36.isDying()) {
                  this.elipsoidIntersect(var2, var36);
                  var40 = var36.hasCargo && (var36.isDead() || var36.isDying());
                  if(this.inViewFrustum) {
                     if(!var9 && !var29 && !var8 && !var23 && ((PlayerAsteroid)var36).getMass_SizeCoef__() > 15 && !this.onPlanetCourse_) {
                        if(var40 && this.tractorBeamTarget == null && (this.tractorBeamAutomatic && !this.tractorBeam360)) {
                           this.tractorBeamTarget = var36;
                           var8 = true;
                           this.contextShip = var36;
                        } else {
                           var18 = Crosshair.screenPos.x - 20;
                           var19 = Crosshair.screenPos.y - 20;
                           if(this.screenProjectionX > var18 && this.screenProjectionX < var18 + 40 && this.screenProjectionY > var19 && this.screenProjectionY < var19 + 40 && !((PlayerAsteroid)var36).clampedByDistance && this.pastIntro && var13 < 4) {
                              this.asteroidsInFront[var13] = var33;
                              ++var13;
                           }
                        }
                     }

                     if(var14 && (var18 = ((PlayerAsteroid)var36).getQualityFrameIndex()) == 0) {
                        this.hud_meteor_class_sprite.setFrame(var18);
                        this.hud_meteor_class_sprite.setRefPixelPosition(this.screenProjectionX, this.screenProjectionY);
                        this.hud_meteor_class_sprite.paint(GlobalStatus.graphics);
                     }

                     if(var40) {
                        GlobalStatus.graphics.drawImage(Globals.bracketBox, this.screenProjectionX, this.screenProjectionY, 3);
                     }
                  } else if(var40) {
                     GlobalStatus.graphics.drawImage(Globals.hudRadarIconNeutral, this.screenProjectionX, this.screenProjectionY, 3);
                     GlobalStatus.graphics.drawImage(Globals.hudAsteroid, this.screenProjectionX, this.screenProjectionY, 3);
                  }
				  if(!this.level.getPlayer().isAutoPilot() && (!var36.junk || var40) && (var40 && var36.isDead() || !var40)) {
                           if(var40 && this.tractorBeamTarget == null && (this.tractorBeam360 && this.tractorBeamAutomatic)) {
                              this.tractorBeamTarget = var36;
                              var29 = true;
                              this.contextShip = var36;
                           }
					 }
               }
            }

            var33 = 999999;
            var38 = -1;

            for(var37 = 0; var37 < 5; ++var37) {
               if((var18 = this.asteroidsInFront[var37]) >= 0) {
                  this.tempCameraSpacePos = this.asteroids[var18].getPosition(this.tempCameraSpacePos);
                  this.tempPos = var1.getPosition(this.tempPos);
                  this.tempCameraSpacePos.subtract(this.tempPos);
                  if((var19 = this.tempCameraSpacePos.getLength()) < var33) {
                     var33 = var19;
                     var38 = var18;
                  }
               }
            }

            if(var38 >= 0) {
               if(this.contextAsteroid != this.asteroids[var38]) {
                  this.asteroidScanPassedTime = 0;
               }

               this.contextAsteroid = this.asteroids[var38];
               var8 = true;
            }

            if(!var29 && !var23) {
               if(this.targetedAsteroid != null) {
                  if(this.targetedAsteroid.isDead()) {
                     this.targetedAsteroid = null;
                  }

                  this.targetedAsteroid = null;
               }

               var40 = this.contextAsteroid != null && this.contextAsteroid.hasCargo && (this.contextAsteroid.isDead() || this.contextAsteroid.isDying());
               if(var8 && !this.level.getPlayer().isDockingToAsteroid()) {
                  this.asteroidScanPassedTime += var5;
                  if(this.asteroidScanPassedTime > this.scanTime - 200) {
                     if(var40) {
                        if(!this.tractorBeamPresent) {
                           var4.hudEvent(9, this.level.getPlayer());
                        } else {
                           this.tractorBeamTarget = this.contextAsteroid;
                        }
                     } else if(!this.drillPresent) {
                        var4.hudEvent(20, this.level.getPlayer());
                     } else {
                        this.targetedAsteroid = this.contextAsteroid;
                     }
                  }

                  if(this.asteroidScanPassedTime > 500) {
                     if(this.contextAsteroid != null && !this.contextAsteroid.equals(var40?this.tractorBeamTarget:this.targetedAsteroid)) {
                        if((var18 = (int)((float)(this.scanprocessSprite.getRawFrameCount() - 1) * ((float)(this.asteroidScanPassedTime - 500) / (float)(this.scanTime - 500)))) < this.scanprocessSprite.getRawFrameCount() - 1) {
                           this.scanprocessSprite.setFrame(var18);
                           this.scanprocessSprite.setRefPixelPosition(Crosshair.screenPos.x, Crosshair.screenPos.y);
                           this.scanprocessSprite.paint(GlobalStatus.graphics);
                        }
                     } else if(Layout.quickClockHigh_()) {
                        this.scanprocessSprite.setFrame(this.scanprocessSprite.getRawFrameCount() - 2);
                        this.scanprocessSprite.setRefPixelPosition(Crosshair.screenPos.x, Crosshair.screenPos.y);
                        this.scanprocessSprite.paint(GlobalStatus.graphics);
                     }
                  }
               } else {
                  if(var40 && this.tractorBeamTarget == null || !var40 && this.targetedAsteroid == null) {
                     this.contextAsteroid = null;
                  }

                  this.asteroidScanPassedTime = 0;
               }
            }
         }

         if(this.activeEnemyEnemiesCount > 0) {
            if(GlobalStatus.currentMusic != 3) {
               GlobalStatus.soundManager.playMusic(3);
            }
         } else if(GlobalStatus.currentMusic != 4 && GlobalStatus.currentMusic != 0) {
            GlobalStatus.soundManager.playMusic(4);
         }

         this.activeEnemyEnemiesCount = 0;
      }
   }

   public final int getPlanetDockIndex() {
      return Status.getSystem().getStations()[this.targetedPlanetLocalIndex];
   }

   public final void drawCurrentLock(Hud var1) {
      if(this.draw) {
         boolean var2;
         if(var2 = this.targetedPlanet != null) {
			AEGraphics[0].drawImage(Globals.panelInfoUpper, GlobalStatus.var_e75 / 2, AEGraphics[0].getImageHeight(), 3);
            Font.sub_14d_CENTER(Status.getPlanetNames()[this.targetedPlanetLocalIndex], AEGraphics[0].getImageX(), AEGraphics[0].getImageY() - (AEGraphics[0].getImageHeight() / 4), 1); // ������� � �������
         } else {
            KIPlayer npc;
            if((npc = this.targetedAsteroid != null?this.targetedAsteroid:(this.targetedPlayer != null?this.targetedPlayer:(this.targetedLandmark != null?this.targetedLandmark:null))) != null) {
               boolean var3 = npc.equals(this.targetedAsteroid);
               boolean var4 = npc.equals(this.targetedLandmark);
               String var5 = this.targetedLandmark == this.landmarks[0]?this.stationaryPlayersNames[0]:(this.targetedLandmark == this.landmarks[3]?this.stationaryPlayersNames[3]:this.stationaryPlayersNames[1]);
			   
			   AEGraphics[0].drawImage(Globals.panelInfoUpper, GlobalStatus.var_e75 / 2, AEGraphics[0].getImageHeight(), 3);
			   
               if(var3) {
                  this.hud_meteor_class_sprite.setFrame(((PlayerAsteroid)npc).getQualityFrameIndex());
                  this.hud_meteor_class_sprite.setRefPixelPosition((GlobalStatus.var_e75 / 2) - ((Font.getStringWidth(GlobalStatus.gameText.getNamedParameterItems(((PlayerAsteroid)npc).oreItemId)) / 2) + 4), AEGraphics[0].getImageY() + (this.hud_meteor_class_sprite.getHeight() / 2));
                  this.hud_meteor_class_sprite.paint(GlobalStatus.graphics);
                  Font.sub_14d_CENTER(GlobalStatus.gameText.getNamedParameterItems(((PlayerAsteroid)npc).oreItemId), AEGraphics[0].getImageX(), AEGraphics[0].getImageY() - (AEGraphics[0].getImageHeight() / 4), 1); // ���� ��� ������������ ���������
                  return;
               }
               if(!var4) {
                  this.logos_small_sprite.setFrame(npc.race);
                  this.logos_small_sprite.setRefPixelPosition((GlobalStatus.var_e75 / 2) - ((Font.getStringWidth(this.hud_shipname_scan) / 2) + 4), AEGraphics[0].getImageY() + (this.logos_small_sprite.getHeight() / 2));
                  this.logos_small_sprite.paint(GlobalStatus.graphics);
               }

               if(npc.name != null && npc.name.equals(GlobalStatus.gameText.getText(833))) { // ����������
                  if(Layout.quickClockHigh_()) {
                     Font.sub_14d_CENTER(npc.name, AEGraphics[0].getImageX(), AEGraphics[0].getImageY() - (AEGraphics[0].getImageHeight() / 4), 2);
                     return;
                  }
               } else {
                  String var6 = var4?var5:(npc.name != null?npc.name:GlobalStatus.gameText.getText(229 + npc.race));
                  if(!var4) {
                     var6 = var6 + " " + npc.player.getSummaryPercent() + "%"; // ���������� ������� �������� ������� �� ������
					 this.hud_shipname_scan = var6;
                  } else {
                     if(Status.inAlienOrbit()) {
                        this.logos_small_sprite.setFrame(9);
                     } else {
                        this.logos_small_sprite.setFrame(Status.getSystem().getRace());
                     }

                     this.logos_small_sprite.setRefPixelPosition((GlobalStatus.var_e75 / 2) - ((Font.getStringWidth(var6) / 2) - (Font.getStringWidth(GlobalStatus.gameText.getText(833))) + 4), AEGraphics[0].getImageY() + (this.logos_small_sprite.getHeight() / 2));
                     this.logos_small_sprite.paint(GlobalStatus.graphics);
                  }
				  if(GlobalStatus.developer_mode == true) {
					  Font.drawString("HULL: " + npc.player.getHitpoints(), 20, 110, 6);
					  Font.drawString("EMP: " + npc.player.getEMPHealthPoints(), 20, 130, 6);
					  Font.drawString("SHIELD: " + npc.player.getShieldHP(), 20, 150, 6);
					  Font.drawString("ARMOR: " + npc.player.getArmorHP(), 20, 170, 6);
					//  Class_ba6.sub_14d("NAME_LENGTH: " + Class_ba6.sub_2bf(var6), 20, 190, 6);
				  }
				  //Class_ba6.sub_14d_CENTER(" " + npc.var_1a9.getEMPHealthPointPercent(), AEGraphics[0].getImageX() + (AEGraphics[0].getImageWidth() / 2) - (Class_ba6.sub_2bf("" + npc.var_1a9.getEMPHealthPointPercent()) / 2), AEGraphics[0].getImageY() - (AEGraphics[0].getImageHeight() / 4), 5); // �����������
                  Font.sub_14d_CENTER(var6, AEGraphics[0].getImageX(), AEGraphics[0].getImageY() - (AEGraphics[0].getImageHeight() / 4), 1);
               }
            }

         }
      }
   }

   private String calcDistance(int var1, int var2, int var3, Player var4) {
      this.playerPos = var4.getPosition(this.playerPos);
      this.floatVector.x = (float)(this.playerPos.x >> 1);
      this.floatVector.y = (float)(this.playerPos.y >> 1);
      this.floatVector.z = (float)(this.playerPos.z >> 1);
      var1 >>= 1;
      var2 >>= 1;
      var3 >>= 1;
      float var6 = ((this.floatVector.x - (float)var1) * (this.floatVector.x - (float)var1) + (this.floatVector.y - (float)var2) * (this.floatVector.y - (float)var2) + (this.floatVector.z - (float)var3) * (this.floatVector.z - (float)var3)) / 4096.0F;
      float var7 = 65536.0F;
      float var9 = 65536.0F;

      for(int var5 = 0; var5 < 20; ++var5) {
         var7 *= 0.5F;
         float var10;
         if((var10 = var6 - var9 * var9) > 0.0F) {
            var9 += var7;
         } else if(var10 < 0.0F) {
            var9 -= var7;
         }
      }

      var1 = (var1 = (int)var9) * 12;
      String var8 = var1 + "m";
      if(var1 >= 1000) {
         if((var2 = var1 % 1000) >= 100) {
            var8 = "" + var2;
         } else {
            var8 = "0";
         }

         var8 = var8.substring(0, 1);
         var8 = var1 + " m";
      }

      return var8;
   }

   public final KIPlayer getLockedAsteroid() {
      return this.targetedAsteroid;
   }

   public final void unlockAsteroid() {
      this.targetedAsteroid = null;
   }

   public final KIPlayer getLockedEnemy() {
      return this.targetedPlayer;
   }
}
