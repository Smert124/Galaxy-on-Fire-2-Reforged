package Main;

import AE.AEResourceManager;
import AE.AbstractMesh;
import AE.AECamera;
import AE.CameraControllerGroup;
import AE.GlobalStatus;
import AE.GraphNode;
import AE.IApplicationModule;
import AE.LookAtCamera;
import AE.Math.AEVector3D;
import AE.PaintCanvas.Font;
import AE.TargetFollowCamera;
import GoF2.Achievements;
import GoF2.AutoPilotList;
import GoF2.Dialogue;
import GoF2.FileRead;
import GoF2.Galaxy;
import GoF2.Hud;
import GoF2.KIPlayer;
import GoF2.Layout;
import GoF2.Level;
import GoF2.LevelScript;
import GoF2.Mission;
import GoF2.Objective;
import GoF2.PlayerEgo;
import GoF2.PlayerJumpgate;
import GoF2.Popup;
import GoF2.Radar;
import GoF2.Radio;
import GoF2.RecordHandler;
import GoF2.Route;
import GoF2.Status;

public final class MGame extends IApplicationModule {

   private int lookingBack;
   private long currentTimeMs;
   private long lastFrameTimeMs;
   private long frameTime;
   private long menuListScrollTick;
   private long unusedTick_;
   private long fiveSecTick;
   private boolean loaded;
   private PlayerEgo playerEgo;
   private AECamera camera;
   private CameraControllerGroup cameras_;
   private TargetFollowCamera targetFollowCamera;
   private LookAtCamera lookAtCamera;
   private boolean isIntro;
   private boolean paused;
   private boolean dialogueOpen_;
   private boolean cinematicBreak_;
   private boolean egoDead;
   private int fov;
   private Hud hud;
   private Level level;
   private LevelScript levelScript;
   private Radar radar;
   private Radio radio;
   private OptionsWindow pauseMenu;
   private Dialogue sequentialDialogue_;
   private Dialogue interuptDialogue;
   private StarMap starMap;
   private AutoPilotList autoPilotList;
   private Popup popup;
   private boolean jumpgateReached;
   private boolean unused947_;
   private boolean mapOpen_;
   private boolean autopilotMenuOpen;
   private boolean touchesStream;
   private boolean touchesStation;
   private boolean jumpGateSoundStarted;
   private boolean interruptedByDialogue;
   private boolean inTurretMode;
   private boolean actionMenuOpen;
   private int keysPressed;
   private boolean shootingDisabled;
   private boolean usingJumpDrive;
   private boolean jumpDriveAnimStarted;
   private AbstractMesh jumpFlash;
   private boolean wingmenLeftNoticeShown;
   private AEVector3D egoJumpPos = new AEVector3D();


   public final void OnRelease() {
      if(this.playerEgo != null) {
         this.playerEgo.OnRelease();
      }

      this.playerEgo = null;
      if(this.starMap != null) {
         this.starMap.OnRelease();
      }

      this.starMap = null;
      if(this.sequentialDialogue_ != null) {
         this.sequentialDialogue_.OnRelease();
      }

      this.sequentialDialogue_ = null;
      this.camera = null;
      if(this.level != null) {
         this.level.onRelease();
      }

      this.level = null;
      if(this.radar != null) {
         this.radar.OnRelease();
      }

      this.radar = null;
      if(this.radio != null) {
         this.radio.OnRelease();
      }

      this.radio = null;
      this.hud.OnRelease();
      if(this.pauseMenu != null) {
         this.pauseMenu.OnRelease();
      }

      if(this.jumpFlash != null) {
         this.jumpFlash.OnRelease();
         this.jumpFlash = null;
      }

      this.targetFollowCamera.setTarget((GraphNode)null);
      this.targetFollowCamera.OnRelease((GraphNode)null);
      this.cameras_ = null;
      this.camera = null;
      this.pauseMenu = null;
      this.levelScript = null;
      this.loaded = false;
      this.autoPilotList = null;
      System.gc();
   }

   public final void OnInitialize() {
      if(this.level == null) {
         this.level = new Level();
      }

      if(this.level.createSpace()) {
         this.playerEgo = this.level.getPlayer();
         this.hud = new Hud();
         this.radio = new Radio();
         this.radio.setMessages(this.level.getMessages());
         this.camera = AECamera.create(GlobalStatus.var_e75, GlobalStatus.var_eb6, 700, 100, 32000);
         this.cameras_ = new CameraControllerGroup();
         this.cameras_.uniqueAppend_(this.camera);
         GlobalStatus.renderer.setActiveCamera(this.camera);
         this.targetFollowCamera = new TargetFollowCamera(this.playerEgo.shipGrandGroup_, this.camera);
         this.lookAtCamera = new LookAtCamera(this.playerEgo.shipGrandGroup_, this.camera);
         this.lookAtCamera.setLookAt(false);
         this.cameras_.addController(this.lookAtCamera);
         this.cameras_.addController(this.targetFollowCamera);
         this.radar = new Radar(this.level);
         this.levelScript = new LevelScript(this.targetFollowCamera, this.lookAtCamera, this.level, this.hud, this.radar);
         LevelScript.resetCamera(this.targetFollowCamera, this.level);
         this.playerEgo.setCamControllers(this.targetFollowCamera, this.lookAtCamera);
         this.isIntro = Status.getCurrentCampaignMission() == 0;
         this.egoDead = false;
         this.lookingBack = 0;
         this.paused = false;
         this.cinematicBreak_ = false;
         this.fiveSecTick = 0L;
         this.autopilotMenuOpen = false;
         this.jumpgateReached = false;
         this.unused947_ = false;
         this.currentTimeMs = System.currentTimeMillis();
         this.lastFrameTimeMs = System.currentTimeMillis();
         if(Status.baseArmour >= 0) {
            this.playerEgo.player.setHitPoints(Status.baseArmour);
         }

         if(Status.shield >= 0) {
            this.playerEgo.player.setShieldHP(Status.shield);
         }

         if(Status.additionalArmour >= 0) {
            this.playerEgo.player.setArmorHP(Status.additionalArmour);
         }

         this.playerEgo.resetLastHP();
         Status.baseArmour = Status.getShip().getBaseArmour();
         Status.shield = Status.getShip().getShield();
         Status.additionalArmour = Status.getShip().getAdditionalArmour();
         if(!Status.inAlienOrbit()) {
            Status.lastVisitedNonVoidOrbit = Status.getStation().getId();
         }

         this.interruptedByDialogue = false;
         this.mapOpen_ = false;
         this.touchesStream = false;
         this.touchesStation = false;
         Layout.setTickHighlight(false);
         System.gc();
         this.currentTimeMs = System.currentTimeMillis();
         this.lastFrameTimeMs = System.currentTimeMillis();
         this.fiveSecTick = 0L;
         this.loaded = true;
         this.inTurretMode = false;
         this.actionMenuOpen = false;
         this.shootingDisabled = false;
         this.usingJumpDrive = false;
         this.jumpDriveAnimStarted = false;
         this.wingmenLeftNoticeShown = false;
         this.jumpGateSoundStarted = false;
         if(Status.getShip().hasEquipment(GlobalStatus.displayedSecondary)) {
            this.level.getPlayer().setCurrentSecondaryWeaponIndex(GlobalStatus.displayedSecondary);
         }

         if(Status.getCurrentCampaignMission() != 0 && Status.getCurrentCampaignMission() != 1) {
            if(GlobalStatus.random.nextInt(2) == 0) {
               GlobalStatus.soundManager.playMusic(4);
            } else {
               GlobalStatus.soundManager.playMusic(0);
            }
         } else {
            GlobalStatus.soundManager.playMusic(4);
         }
      }
   }

   public final boolean isLoaded() {
      return this.loaded;
   }

   public final void renderScene(int var1) {
      this.keysPressed = var1;

      try {
         if(this.loaded) {
            this.currentTimeMs = System.currentTimeMillis();
            this.frameTime = this.currentTimeMs - this.lastFrameTimeMs;
            this.lastFrameTimeMs = this.currentTimeMs;
            this.unusedTick_ += this.frameTime;
            this.menuListScrollTick += this.frameTime;
            if(!this.paused) {
               this.levelScript.timePassed += this.frameTime;
               if(!this.cinematicBreak_) {
                  this.fiveSecTick += this.frameTime;
               }

               Status.incPlayingTime(this.frameTime);
               Layout.addTicks((int)this.frameTime);
               Status.var_bea = (int)((long)Status.var_bea - this.frameTime);
			   this.hud.HUDTouchFlag = true;
            } else {
				this.hud.pointerReleased(0, 0);
				this.hud.HUDTouchFlag = false; // HUD blocked
			}

            if(!this.egoDead) {
               if(this.paused) {
                  if(this.pauseMenu != null && !this.actionMenuOpen && !this.dialogueOpen_ && !this.autopilotMenuOpen && !this.jumpgateReached && !this.interruptedByDialogue) {
                     this.pauseMenu.scrollAndTick_(var1, (int)this.frameTime);
                     if(this.menuListScrollTick > 150L) {
                        this.menuListScrollTick = 0L;
                        if((var1 & 2) != 0) {
                           this.pauseMenu.scrollUp((int)this.frameTime);
                        }

                        if((var1 & 64) != 0) {
                           this.pauseMenu.scrollDown((int)this.frameTime);
                        }
                     }
                  }

                  if(!this.loaded) {
                     return;
                  }

                  this.level.render2(this.frameTime);
                  this.playerEgo.render(!this.cinematicBreak_);
                  GlobalStatus.graphics3D.bindTarget(GlobalStatus.graphics);
                  GlobalStatus.renderer.renderStillFrame();
                  GlobalStatus.graphics3D.releaseTarget();
                  if(this.autopilotMenuOpen) {
                     this.autoPilotList.draw();
                     return;
                  }

                  if(this.jumpgateReached) {
                     this.popup.draw();
                     return;
                  }

                  if(this.interruptedByDialogue) {
                     this.interuptDialogue.handleScrollPress_(var1, (int)this.frameTime);
                     if(this.mapOpen_) {
                        this.starMap.update(0, (int)this.frameTime);
                     }

                     this.interuptDialogue.drawInterupring_();
                     return;
                  }

                  if(this.actionMenuOpen) { // display quickmenu
                     this.hud.draw(this.paused?0L:this.frameTime, (long)this.levelScript.timeLimit - this.levelScript.timePassed, this.playerEgo, this.isIntro);
                     this.hud.drawMenu_((int)this.frameTime);
                     return;
                  }

                  if(this.dialogueOpen_) {
                     Layout.addTicks((int)this.frameTime);
                     this.sequentialDialogue_.handleScrollPress_(var1, (int)this.frameTime);
                     this.sequentialDialogue_.draw();
                     return;
                  }

                  this.pauseMenu.draw();
                  return;
               }

               if(this.playerEgo.isDockedToPlanet()) {
                  Status.departStation(Galaxy.getStation(this.radar.getPlanetDockIndex()));
                  Level.setInitStreamOut();
                  Status.baseArmour = this.playerEgo.player.getHitpoints();
                  Status.shield = this.playerEgo.player.getShieldHP();
                  Status.additionalArmour = this.playerEgo.player.getArmorHP();
                  GlobalStatus.applicationManager.SetCurrentApplicationModule(GlobalStatus.scenes[2]);
                  return;
               }

               if(this.shootingDisabled && this.updateJumpScene()) {
                  return;
               }

               if(this.mapOpen_) {
                  int var4 = (int)this.frameTime;
                  this.starMap.update(var1, var4);
               }

               if((this.levelScript.startSequenceOver() || !this.levelScript.isActive()) && !this.jumpgateReached && (this.dockEvent() || this.mapOpen_)) {
                  return;
               }

               if(this.interuptDialogue == null && this.levelScript.timePassed > 1000L) {
                  if(!GlobalStatus.boosterHelpShown && Status.getShip().sub_3f2()) {
                     this.interuptDialogue = new Dialogue(GlobalStatus.gameText.getText(306));
                     this.interruptedByDialogue = true;
                     this.paused = true;
                     GlobalStatus.boosterHelpShown = true;
                     return;
                  }

                  if(!GlobalStatus.jumpDriveHelpShown && Status.getShip().sub_405()) {
                     this.interuptDialogue = new Dialogue(GlobalStatus.gameText.getText(304));
                     this.interruptedByDialogue = true;
                     this.paused = true;
                     GlobalStatus.jumpDriveHelpShown = true;
                     return;
                  }

                  if(!GlobalStatus.cloakHelpShown && Status.getShip().sub_413()) {
                     this.interuptDialogue = new Dialogue(GlobalStatus.gameText.getText(305));
                     this.interruptedByDialogue = true;
                     this.paused = true;
                     GlobalStatus.cloakHelpShown = true;
                     return;
                  }

                  if(!GlobalStatus.interplanetHelpShown && !this.playerEgo.isAutoPilot() && Status.getCurrentCampaignMission() > 9) {
                     this.interuptDialogue = new Dialogue(GlobalStatus.gameText.getText(300));
                     this.interruptedByDialogue = true;
                     this.paused = true;
                     GlobalStatus.interplanetHelpShown = true;
                     return;
                  }

                  if(Status.var_b3a != null) {
                     if(!GlobalStatus.wingmenHelpShown) {
                        this.paused = true;
                        this.interuptDialogue = new Dialogue(GlobalStatus.gameText.getText(320));
                        this.interruptedByDialogue = true;
                        GlobalStatus.wingmenHelpShown = true;
                        return;
                     }

                     if(!this.wingmenLeftNoticeShown && Status.var_bea <= 0) {
                        this.paused = true;
                        this.interuptDialogue = new Dialogue(GlobalStatus.gameText.getText(153), Status.var_b3a[0], Status.wingmanFace);
                        this.interruptedByDialogue = true;
                        this.wingmenLeftNoticeShown = true;
                        return;
                     }
                  }

                  if(!GlobalStatus.reputationHelpShown && Status.getStanding().atWar()) {
                     this.paused = true;
                     this.interuptDialogue = new Dialogue(GlobalStatus.gameText.getText(324));
                     this.interruptedByDialogue = true;
                     GlobalStatus.reputationHelpShown = true;
                     return;
                  }

                  if(!GlobalStatus.miningHelpShown && this.playerEgo.isMining() && !GlobalStatus.miningHelpShown) {
                     this.paused = true;
                     this.interuptDialogue = new Dialogue(GlobalStatus.gameText.getText(307));
                     this.interruptedByDialogue = true;
                     GlobalStatus.miningHelpShown = true;
                     return;
                  }

                  if(!GlobalStatus.asteroidHelpShown && this.playerEgo.isMining() && Status.getCurrentCampaignMission() > 3) {
                     this.paused = true;
                     this.interuptDialogue = new Dialogue(GlobalStatus.gameText.getText(308));
                     this.interruptedByDialogue = true;
                     GlobalStatus.asteroidHelpShown = true;
                     return;
                  }

                  if(!GlobalStatus.cargoFullHelpShown && this.hud.cargoFull() && Status.getCurrentCampaignMission() > 6) {
                     this.paused = true;
                     this.interuptDialogue = new Dialogue(GlobalStatus.gameText.getText(319));
                     this.interruptedByDialogue = true;
                     GlobalStatus.cargoFullHelpShown = true;
                     return;
                  }
               }

               if(this.fiveSecTick > 5000L && !this.dialogueOpen_ && !Status.getMission().hasFailed() && !Status.getMission().hasWon()) {
                  this.dialogueEvent();
               }
            }

            if(this.playerEgo.boosting() && !this.playerEgo.isDockingToPlanet()) {
               this.fov = 750 + (int)(this.playerEgo.getBoostPercentage() * 150.0F);
               this.camera.setFoV(this.fov);
            }

            if(Status.getMission().hasFailed() || this.egoDead || !this.successCheck_()) {
               if(!this.egoDead) {
                  this.gameOverCheck();
               }

               this.cinematicBreak_ = this.levelScript.process((int)this.frameTime, this.targetFollowCamera);
               if(this.levelScript != null) {
                  this.OnRender3D();
                  this.OnRender2D();
                  if(!this.egoDead && !this.cinematicBreak_) {
                     if(Level.driveJumping && this.levelScript.timePassed > 7500L) {
                        Level.driveJumping = false;
                        this.usingJumpDrive = true;
                        this.startJumpScene();
                     }

                     if(this.isIntro) {
                        this.playerEgo.shoot((int)this.frameTime, 0);
                     }

                     boolean var2 = this.playerEgo.isAutoPilot() && !this.playerEgo.isLookingBack() || this.playerEgo.isDockingToAsteroid() || this.playerEgo.isDockingToStream_();
                     if((var1 & 4) != 0) {
                        if(var2) {
                           this.hud.hudEvent(7, this.playerEgo);
                        } else {
                           this.playerEgo.turretRotateLeft((int)this.frameTime);
                        }
                     }

                     if((var1 & 32) != 0) {
                        if(var2) {
                           this.hud.hudEvent(7, this.playerEgo);
                        } else {
                           this.playerEgo.turretRotateRight((int)this.frameTime);
                        }
                     }

                     if((var1 & 2) != 0) {
                        if(var2) {
                           this.hud.hudEvent(7, this.playerEgo);
                        } else if(GlobalStatus.invertedControlsOn) {
                           this.playerEgo.turretPitchDown((int)this.frameTime);
                        } else {
                           this.playerEgo.turretPitchUp((int)this.frameTime);
                        }
                     }

                     if((var1 & 64) != 0) {
                        if(var2) {
                           this.hud.hudEvent(7, this.playerEgo);
                        } else if(GlobalStatus.invertedControlsOn) {
                           this.playerEgo.turretPitchUp((int)this.frameTime);
                        } else {
                           this.playerEgo.turretPitchDown((int)this.frameTime);
                        }
                     }

                     if(((var1 & 256) != 0 && !this.shootingDisabled && (this.playerEgo.isLookingBack() || !this.playerEgo.isAutoPilot() && !this.playerEgo.isDockingToAsteroid())) || this.hud.fireButton.getStandartButtonPressed() && this.radar.getLockedAsteroid() == null && !this.playerEgo.isDockingToAsteroid() && this.radar.targetedPlanet == null) { // ОГОНЬ
                        this.playerEgo.shoot((int)this.frameTime, 0);
                     }
                  }

                  if(this.playerEgo.isInWormhole()) {
                     if(Status.getMission().isCampaignMission()) {
                        int var6;
                        if((var6 = Status.getCurrentCampaignMission()) == 29 || var6 == 41 || var6 == 40 && this.level.getEnemies()[0].player.isActive()) {
                           this.playerEgo.player.setHitPoints(0);
                           return;
                        }

                        if(var6 != 42) {
                           Status.nextCampaignMission();
                        }

                        if(var6 == 40) {
                           Level.lastMissionFreighterHitpoints = this.level.getEnemies()[0].player.getHitpoints();
                        }

                        this.level.removeObjectives();
                        Status.setMission(Mission.emptyMission_);
                     }

                     Status.setMission(Mission.emptyMission_);
                     if(Status.inAlienOrbit()) {
                        Status.setCurrentStation_andInitSystem_(Galaxy.getStation(Status.lastVisitedNonVoidOrbit));
                     } else {
                        Status.setCurrentStation_andInitSystem_(Status.station_class);
                     }

                     Status.baseArmour = this.playerEgo.player.getHitpoints();
                     Status.shield = this.playerEgo.player.getShieldHP();
                     Status.additionalArmour = this.playerEgo.player.getArmorHP();
                     Status.departStation(Status.getStation());
                     Level.programmedStation = null;
                     Level.initStreamOutPosition = true;
                     Level.comingFromAlienWorld = true;
                     GlobalStatus.applicationManager.SetCurrentApplicationModule(GlobalStatus.scenes[2]);
                  }

               }
            }
         }
      } catch (Exception var5) {
         var5.printStackTrace();
      }
   }

   private void OnRender3D() {
      try {
         this.level.updateOrbit_(this.frameTime);
         this.level.render(this.frameTime);
         this.playerEgo.update((int)this.frameTime, this.radar, this.hud, this.radio, this.keysPressed);
         this.playerEgo.render(!this.cinematicBreak_);
         if(this.jumpFlash != null) {
            GlobalStatus.renderer.drawNodeInVF(this.jumpFlash);
         }

         this.levelScript.renderProbe__();
         GlobalStatus.graphics3D.bindTarget(GlobalStatus.graphics);
         this.cameras_.updateTransform(true);
         GlobalStatus.renderer.renderFrame(System.currentTimeMillis());
         GlobalStatus.graphics3D.releaseTarget();
      } catch (Exception var2) {
         GlobalStatus.graphics3D.releaseTarget();
         var2.printStackTrace();
      }
   }

   private void OnRender2D() {
      this.level.renderRockets_();
      if(this.cinematicBreak_) {
         if(Status.getCurrentCampaignMission() > 1) {
            GlobalStatus.var_bfc.draw();
            this.hud.drawOrbitInformation();
         }

         if(this.levelScript.startSequenceOver()) {
            this.radio.draw(this.levelScript.timePassed, this.frameTime, this.playerEgo);
            return;
         }
      } else if(this.egoDead) {
         Font.drawString(GlobalStatus.gameText.getText(156), GlobalStatus.var_e75 / 2, 20, 0, 8);
         if(this.fiveSecTick > 3000L) {
            this.popup.draw();
            return;
         }
      } else {
         this.playerEgo.draw(!this.cinematicBreak_, GlobalStatus.renderer.sub_85());
         if(!this.playerEgo.isMining()) {
            this.radar.draw(this.playerEgo.player, GlobalStatus.renderer.sub_85(), this.targetFollowCamera, this.hud, (int)this.frameTime);
         }

         this.hud.draw(this.paused?0L:this.frameTime, (long)this.levelScript.timeLimit - this.levelScript.timePassed, this.playerEgo, this.isIntro); // показать HUD 
         this.radio.draw(this.levelScript.timePassed, this.frameTime, this.playerEgo);
         this.radar.drawCurrentLock(this.hud);
      }

   }

   private void gameOverCheck() {
      if(this.playerEgo.getHitpoints() <= 0) {
         this.egoDead = true;
         GlobalStatus.gameText.getText(156);
      }

      if(this.level.checkGameOver((int)this.frameTime)) {
         if(this.sequentialDialogue_ == null) {
            this.sequentialDialogue_ = new Dialogue();
         }

         this.sequentialDialogue_.set(Status.getMission(), 2);
         this.dialogueOpen_ = true;
         this.paused = true;
      }

      if(this.fiveSecTick > 5000L) {
         this.fiveSecTick = 0L;
         if(this.levelScript.timeLimit > 0 && this.levelScript.timePassed > (long)this.levelScript.timeLimit && (Status.getCurrentCampaignMission() == 42 || this.level.successObjective != null && !this.level.successObjective.isSurvivalObjective())) {
            if(this.sequentialDialogue_ == null) {
               this.sequentialDialogue_ = new Dialogue();
            }

            this.sequentialDialogue_.set(Status.getMission(), 2);
            this.dialogueOpen_ = true;
            this.paused = true;
         }
      }

      if(this.egoDead) {
         this.fiveSecTick = 0L;
         this.targetFollowCamera.lockPosition();
         GlobalStatus.vibrate(0);
         if(this.popup == null) {
            this.popup = new Popup();
         }

         this.popup.set(GlobalStatus.gameText.getText(255), true);
      }

   }

   private boolean successCheck_() {
      if(this.fiveSecTick > 5000L) {
         Mission var1 = Status.missionCompleted_(false, this.levelScript.timePassed);
         if(this.level.checkObjective((int)this.levelScript.timePassed) || var1 != null) {
            if(Status.getMission().getType() != 5 && Status.getMission().getType() != 3) {
               if(Status.getMission().isCampaignMission() && Status.getCurrentCampaignMission() == 127) {
                  Status.nextCampaignMission();
                  this.level.removeObjectives();
                  Status.setMission(Mission.emptyMission_);
               } else {
                  if(!Status.getMission().isCampaignMission()) {
                     Status.incMissionCount();
                  }

                  if(Status.getMission().isCampaignMission() && (!Status.getMission().isCampaignMission() || !Dialogue.hasSuccessDialogue(Status.getCurrentCampaignMission()))) {
                     return false;
                  }

                  if(this.sequentialDialogue_ == null) {
                     this.sequentialDialogue_ = new Dialogue();
                  }

                  this.sequentialDialogue_.set(var1 != null?var1:Status.getMission(), 1);
                  this.dialogueOpen_ = true;
                  this.paused = true;
               }

               return true;
            }

            Status.getMission().setType(11);
            Status.getMission().setTargetStation(Status.getMission().getAgent().getStationId());
            if(this.sequentialDialogue_ == null) {
               this.sequentialDialogue_ = new Dialogue();
            }

            this.sequentialDialogue_.set(Status.getMission(), 1);
            Status.getMission().setWon(false);
            String var2 = Status.replaceTokens(GlobalStatus.gameText.getText(439), Status.getMission().getTargetStationName(), "#S");
            Status.getMission().getAgent().setMessage(var2);
            Status.setMission(Mission.emptyMission_);
            this.level.successObjective = null;
            this.level.failObjective_ = null;
            this.dialogueOpen_ = true;
            this.paused = true;
            return true;
         }
      }

      return false;
   }

   private void dialogueEvent() {
      if(this.levelScript.startSequenceOver() && (Dialogue.hasBriefingDialogue(Status.getCurrentCampaignMission()) || !Status.getMission().isCampaignMission()) && !Status.getMission().isEmpty() && Status.getMission().getType() != 8 && Status.getMission().getType() != 0 && Status.getMission().isVisible()) {
         if(!Status.getMission().isCampaignMission() && Status.getMission().getType() == 11) {
            return;
         }

         if(this.sequentialDialogue_ == null) {
            this.sequentialDialogue_ = new Dialogue(Status.getMission(), this.level, 0);
         }

         this.paused = true;
         this.dialogueOpen_ = true;
      }

   }

   private boolean dockEvent() {
      this.touchesStream = this.level.collideStream(this.playerEgo.getPosition());
      this.touchesStation = this.level.collideStation(this.playerEgo.getPosition());
      if(!Status.getMission().isEmpty() && Status.getMission().getType() != 11 && Status.getMission().getType() != 0 && Status.getMission().getType() != 23) {
         if((this.touchesStation || this.touchesStream) && this.playerEgo.isAutoPilot()) {
            this.hud.hudEvent(21, this.playerEgo);
         }

         return false;
      } else if(this.touchesStream && this.playerEgo.goingToStream() && !this.playerEgo.isDockingToStream_() && !this.playerEgo.isDockedToStream()) {
         this.playerEgo.dockToStream(this.targetFollowCamera, true);
         return false;
      } else {
         if(this.touchesStream) {
            Status.baseArmour = this.playerEgo.player.getHitpoints();
            Status.shield = this.playerEgo.player.getShieldHP();
            Status.additionalArmour = this.playerEgo.player.getArmorHP();
            if(this.playerEgo.isAutoPilot() && Level.programmedStation != null) {
               if(!this.jumpgateReached) {
                  if(this.popup == null) {
                     this.popup = new Popup();
                  }

                  this.popup.set(GlobalStatus.gameText.getText(295) + ": " + Level.programmedStation.getName() + "\n" + GlobalStatus.gameText.getText(242), true);
                  this.popup.left();
                  this.jumpgateReached = true;
                  this.paused = true;
                  this.playerEgo.setAutoPilot((KIPlayer)null);
               }

               return false;
            }

            if(this.mapOpen_) {
               this.menuListScrollTick = 0L;
               return true;
            }

            if(this.playerEgo.goingToStream()) {
               if(Level.programmedStation != null) {
                  if(!this.jumpgateReached) {
                     if(this.popup == null) {
                        this.popup = new Popup();
                     }

                     this.popup.set(GlobalStatus.gameText.getText(295) + ": " + Level.programmedStation.getName() + "\n" + GlobalStatus.gameText.getText(242), true);
                     this.popup.left();
                     this.jumpgateReached = true;
                     this.paused = true;
                     this.playerEgo.setAutoPilot((KIPlayer)null);
                  }

                  return false;
               }

               if(this.starMap == null) {
                  this.starMap = new StarMap(false, (Mission)null, false, -1);
               }

               this.starMap.setJumpMapMode(true, false);
               this.playerEgo.setAutoPilot((KIPlayer)null);
               this.mapOpen_ = true;
               return true;
            }
         } else if((this.touchesStation || this.playerEgo.getAutoPilotTarget() == this.level.getLandmarks()[0] && this.playerEgo.collidesWithStation()) && this.playerEgo.goingToStation() && !Status.inAlienOrbit()) {
            Achievements.checkForNewMedal(this.playerEgo);
            GlobalStatus.applicationManager.SetCurrentApplicationModule(GlobalStatus.scenes[1]);
            return true;
         }

         return false;
      }
   }

   public final void pause() {
      if(!this.paused) {
         if(this.pauseMenu == null) {
            this.pauseMenu = new OptionsWindow();
         }

         this.pauseMenu.resetPauseMenu();
         this.paused = true;
      }

   }

   private boolean updateJumpScene() {
      int var1;
      if(this.usingJumpDrive) {
         this.egoJumpPos = this.jumpFlash.getPosition(this.egoJumpPos);
         this.camera.translate((int)this.frameTime * 3, 0, 0);
         this.camera.translate(0, 0, -((int)this.frameTime) << 1);
         var1 = (int)this.frameTime << 2;
         this.jumpFlash.rotateEuler(var1, var1, var1);
      } else {
         this.egoJumpPos = ((PlayerJumpgate)this.level.getLandmarks()[1]).getTargetPos_(this.egoJumpPos);
         this.camera.translate((int)this.frameTime * 5, 0, 0);
         this.camera.translate(0, 0, -((int)this.frameTime) * 3);
      }

      if((var1 = this.camera.getPosZ()) < this.egoJumpPos.z - 2000) {
         if(!this.usingJumpDrive) {
            ((PlayerJumpgate)this.level.getLandmarks()[1]).activate();
         }

         if(!this.jumpGateSoundStarted) {
            GlobalStatus.soundManager.playSfx(7);
            this.jumpGateSoundStarted = true;
         }
      }

      if(this.usingJumpDrive?this.playerEgo.shipGrandGroup_.getPosZ() > this.egoJumpPos.z - 1000:this.level.getLandmarks()[1].mainMesh_.getCurrentAnimFrame() > 60 && this.level.getLandmarks()[1].mainMesh_.getCurrentAnimFrame() < 79) {
         if(!this.jumpDriveAnimStarted && this.usingJumpDrive) {
            this.jumpFlash.setAnimationMode((byte)3);
            this.jumpFlash.setAnimationSpeed(50);
            this.jumpDriveAnimStarted = true;
         }

         if(this.playerEgo.speed < 200) {
            this.playerEgo.speed += 20; // набор скорости при прыжке
         }

         this.lookAtCamera.setLookAt(false);
      }

      if(this.usingJumpDrive && this.jumpDriveAnimStarted && this.jumpFlash.getCurrentAnimFrame() >= 20) {
         this.jumpFlash.setScale(0, 0, 0);
      }

      if(var1 < this.egoJumpPos.z - 15000) {
         Status.departStation(Level.programmedStation);
         Level.setInitStreamOut();
         if(!this.usingJumpDrive) {
            Status.jumpgateUsed();
         }

         if(Level.programmedStation.sub_475(Status.station_class)) {
            Level.initStreamOutPosition = true;
            Level.comingFromAlienWorld = true;
            Status.setCurrentStation_andInitSystem_(Status.station_class);
         }

         Level.programmedStation = null;
         Status.baseArmour = this.playerEgo.player.getHitpoints();
         Status.shield = this.playerEgo.player.getShieldHP();
         Status.additionalArmour = this.playerEgo.player.getArmorHP();
         GlobalStatus.applicationManager.SetCurrentApplicationModule(GlobalStatus.scenes[2]);
         return true;
      } else {
         return false;
      }
   }

   private void startJumpScene() { // Двигатель кадора\Khador's Engine. Похоже, это касается и Jumpgate...
      this.paused = false; // Если поставить "true" - игра крашится.
      this.shootingDisabled = true; // Корабль после входа в прыжок обретает эффекты, и больше движений, но вы не телепортируетесь.
      this.cinematicBreak_ = false; // Unknown
      this.playerEgo.setCollide(false); // Телепортация не работает, если "true".
      this.targetFollowCamera.setLookAtCam(false); // Следовать ли за кораблем игрока после входа в прыжок? | Плохо работает телепортация.
      this.lookAtCamera.setLookAt(true); // Переводить ли камеру на модель прыжка\Jupmgate?
      if(this.usingJumpDrive) { // Сам двигатель Кадора.
	     this.targetFollowCamera.setLookAtCam(false); // Камера следует за кораблем после прыжка.
		 this.lookAtCamera.setLookAt(true); // Камера не поворачивается на модель прыжка.
         this.jumpFlash = AEResourceManager.getGeometryResource(6783); // Модель прыжка (цифры);
         this.jumpFlash.setRenderLayer(2); // Unknown
         this.egoJumpPos = this.playerEgo.shipGrandGroup_.getPosition(this.egoJumpPos);
         this.egoJumpPos.add(this.playerEgo.shipGrandGroup_.getDirection());
         this.jumpFlash.moveTo(this.egoJumpPos);
         this.jumpFlash.setAnimationMode((byte)1);
         this.jumpFlash.setAnimationSpeed(30); // Unknown. Default: 30.
      } else {
         this.egoJumpPos = ((PlayerJumpgate)this.level.getLandmarks()[1]).getTargetPos_(this.egoJumpPos);
      }

      this.egoJumpPos.z -= 5000;
      this.playerEgo.setPosition_(this.egoJumpPos);
      this.playerEgo.shipGrandGroup_.setRotation(0, 0, 0);
      this.lookAtCamera.setTarget(this.playerEgo.shipGrandGroup_);
      this.lookAtCamera.setCamera(this.camera);
      this.lookAtCamera.setOrientationLock(new AEVector3D(0, 4096, 0), 1);
      this.egoJumpPos.x -= 2000;
      this.egoJumpPos.y += 300;
      this.egoJumpPos.z += 4000;
      GlobalStatus.renderer.sub_85().moveTo(this.egoJumpPos);
   }
   
   public final void handleKeystate(int var1) {
      if(this.loaded) {
         if(this.paused) {
            if(this.actionMenuOpen) {
               this.actionMenuOpen = this.hud.handleActionMenuKeypress(var1, this.level, this.radar);
               this.paused = this.actionMenuOpen;
               if(!this.paused) {
                  this.playerEgo.resetGunDelay();
                  if(this.hud.getJumpDriveSelected()) {
                     if(Status.inAlienOrbit()) {
                        Level.programmedStation = Galaxy.getStation(Status.lastVisitedNonVoidOrbit);
                        this.usingJumpDrive = true;
                        this.startJumpScene();
                        this.paused = false;
                        this.hud.setJumpDriveSelected(false);
                        return;
                     }

                     if(this.starMap == null) {
                        this.starMap = new StarMap(false, (Mission)null, false, -1);
                     }

                     this.usingJumpDrive = true;
                     this.starMap.setJumpMapMode(true, true);
                     if(!Status.inAlienOrbit()) {
                        this.starMap.askForJumpIntoAlienWorld();
                     }

                     this.mapOpen_ = true;
                     this.paused = false;
                  }

                  this.hud.setJumpDriveSelected(false);
                  return;
               }
            }

            if((this.interruptedByDialogue && var1 == 256) || (this.interruptedByDialogue && Layout.hintsOKButton.getStandartButtonPressed())) {
               this.interruptedByDialogue = false;
               this.paused = false;
               this.interuptDialogue = null;
               this.playerEgo.resetGunDelay();
			   Layout.hintsOKButton.standartButtonActive = false;
               return;
            }

            if(this.dialogueOpen_) {
               if(!this.sequentialDialogue_.OnKeyPress_(var1)) {
                  this.paused = false;
                  this.dialogueOpen_ = false;
                  if(Status.getMission().hasFailed()) {
                     if(Status.getMission().isCampaignMission() || Status.getCurrentCampaignMission() == 42) {
                        GlobalStatus.applicationManager.SetCurrentApplicationModule(GlobalStatus.scenes[0]);
                        GlobalStatus.soundManager.playMusic(0);
                        Status.startNewGame();
                        return;
                     }

                     if(Status.getMission().getType() == 12) {
                        Status.changeCredits(-Status.getMission().getReward());
                     }

                     Status.setFreelanceMission(Mission.emptyMission_);
                     this.level.removeObjectives();
                     this.levelScript.timeLimit = 0;
                     Status.setMission(Mission.emptyMission_);
                     this.playerEgo.setRoute((Route)null);
                     if(this.playerEgo.gointToAsteroidField()) {
                        this.playerEgo.setAutoPilot((KIPlayer)null);
                     }

                     this.playerEgo.removeRoute();
                     this.level.setPlayerRoute((Route)null);
                     if(!Status.inAlienOrbit()) {
                        this.autoPilotList = new AutoPilotList(this.level);
                     }
                  } else if(!Status.getMission().hasWon() && !Status.getCampaignMission().hasWon()) {
                     this.levelScript.startSequence();
                  } else {
                     boolean var3 = Status.getCampaignMission().hasWon() && !Status.getMission().isCampaignMission();
                     Mission var2;
                     if((var2 = Status.getMission().hasWon()?Status.getMission():Status.getCampaignMission()).isInstantActionMission()) {
                        GlobalStatus.applicationManager.SetCurrentApplicationModule(GlobalStatus.scenes[0]);
                        GlobalStatus.soundManager.playMusic(0);
                        return;
                     }

                     if(var2.isCampaignMission()) {
                        Status.nextCampaignMission();
                     } else {
                        Status.setFreelanceMission(Mission.emptyMission_);
                     }

                     Status.changeCredits(var2.getReward());
                     this.levelScript.timeLimit = 0;
                     if(var2.isCampaignMission() && Status.getCurrentCampaignMission() == 15) {
                        Status.setCurrentStation_andInitSystem_((new FileRead()).loadStation(98)); // To Alioth
                        GlobalStatus.applicationManager.SetCurrentApplicationModule(GlobalStatus.scenes[1]);
                        return;
                     }

                     if(var2.isCampaignMission() && Status.getCurrentCampaignMission() == 22) {
                        GlobalStatus.applicationManager.SetCurrentApplicationModule(GlobalStatus.scenes[1]);
                        return;
                     }

                     if(var2.isCampaignMission() && Status.getCurrentCampaignMission() == 42) {
                        this.levelScript.timeLimit = '\uea60';
                        this.levelScript.timePassed = 0L;
                        this.level.failObjective_ = new Objective(3, this.levelScript.timeLimit, this.level);
                        this.level.successObjective = new Objective(3, this.levelScript.timeLimit, this.level);
                     }

                     if(!var3) {
                        this.level.removeObjectives();
                        Status.setMission(Mission.emptyMission_);
                        this.playerEgo.setRoute((Route)null);
                        if(this.playerEgo.gointToAsteroidField()) {
                           this.playerEgo.setAutoPilot((KIPlayer)null);
                        }

                        this.playerEgo.removeRoute();
                        this.level.setPlayerRoute((Route)null);
                        if(!Status.inAlienOrbit()) {
                           this.autoPilotList = new AutoPilotList(this.level);
                        }
                     }
                  }

                  this.menuListScrollTick = 0L;
                  return;
               }
            } else {
               if(this.autopilotMenuOpen) {
                  if(var1 == 1024) { // закрыть меню автопилота без цели
                     this.hud.autopilotButton.switchableButtonActive = false;
					 this.autopilotMenuOpen = false;
                     this.paused = false;
                  }
				  
				  if(this.autoPilotList.autopilotListButton[0].getStandartButtonPressed()) {
					  this.levelScript.setAutoPilotToProgrammedStation();
					   this.autopilotMenuOpen = false;
                     this.paused = false;
					  return;
				  } else if(this.autoPilotList.autopilotListButton[1].getStandartButtonPressed()) {
					  this.playerEgo.setAutoPilot(this.level.getLandmarks()[1]);
					  this.hud.hudEvent(12, this.playerEgo);
					   this.autopilotMenuOpen = false;
                     this.paused = false;
					  return;
				  } else if(this.autoPilotList.autopilotListButton[2].getStandartButtonPressed()) {
					  this.playerEgo.setAutoPilot(this.level.getLandmarks()[0]);
					  this.hud.hudEvent(10, this.playerEgo);
					   this.autopilotMenuOpen = false;
                     this.paused = false;
					  return;
				  } else if(this.autoPilotList.autopilotListButton[3].getStandartButtonPressed()) {
					  this.playerEgo.setAutoPilot(this.level.getAsteroidWaypoint());
					  this.hud.hudEvent(14, this.playerEgo);
					   this.autopilotMenuOpen = false;
                     this.paused = false;
					  return;
				  } else if(this.autoPilotList.autopilotListButton[4].getStandartButtonPressed()) {
					  if(this.level.getPlayerRoute() != null) {
						  this.playerEgo.setAutoPilot(this.level.getPlayerRoute().getDockingTarget_());
						  this.hud.hudEvent(13, this.playerEgo);
						   this.autopilotMenuOpen = false;
						   this.paused = false;
                        }
				  }

                  if(var1 == 2 && this.autopilotMenuOpen) {
                     this.autoPilotList.down();
                  }

                  if(var1 == 64 && this.autopilotMenuOpen) {
                     this.autoPilotList.up();
                  }

                  if(var1 == 256) {
                     GlobalStatus.soundManager.playSfx(13);
                     this.autopilotMenuOpen = false;
                     if(this.playerEgo != null) {
                        this.playerEgo.resetGunDelay();
                     }

                     this.paused = false;
                     switch(this.autoPilotList.getSelection()) { // пункты меню автопилота
                     case 0: // цель полета
                        this.levelScript.setAutoPilotToProgrammedStation();
                        return;
                     case 1: // ретранслятор
                        this.playerEgo.setAutoPilot(this.level.getLandmarks()[1]);
                        this.hud.hudEvent(12, this.playerEgo);
                        return;
                     case 2: // станция
                        this.playerEgo.setAutoPilot(this.level.getLandmarks()[0]);
                        this.hud.hudEvent(10, this.playerEgo);
                        return;
                     case 3: // поле астероидов
                        this.playerEgo.setAutoPilot(this.level.getAsteroidWaypoint());
                        this.hud.hudEvent(14, this.playerEgo);
                        return;
                     case 4: // точка назначения
                        if(this.level.getPlayerRoute() != null) {
                           this.playerEgo.setAutoPilot(this.level.getPlayerRoute().getDockingTarget_());
                           this.hud.hudEvent(13, this.playerEgo);
                        }
                     }
                  }

                  return;
               }

               if(this.jumpgateReached) {
                  if(var1 == 256) {
                     this.jumpgateReached = false;
                     if(this.popup.getChoice()) {
                        if(this.playerEgo.isLookingBack()) {
                           this.playerEgo.setTurretMode(false);
                        }

                        this.startJumpScene();
                        this.playerEgo.resetGunDelay();
                        return;
                     }

                     if(this.starMap == null) {
                        this.starMap = new StarMap(false, (Mission)null, false, -1);
                     }

                     this.starMap.setJumpMapMode(true, false);
                     this.mapOpen_ = true;
                     this.paused = false;
                  }

                  if(var1 == 4) {
                     this.popup.left();
                  }

                  if(var1 == 32) {
                     this.popup.right();
                     return;
                  }
               } else if(this.pauseMenu != null && !this.actionMenuOpen) {
                  this.pauseMenu.handleKeystate(var1);
                  if(var1 == 256 && this.pauseMenu.update()) {
                     this.paused = false;
                     return;
                  }

                  if(var1 == 16384) { // ок в меню паузы
                     this.pauseMenu.update1_();
                  }

                  if((var1 == 8192) || Layout.AENavigationButton[0].getStandartButtonPressed() && this.pauseMenu.goBack()) { // возврат из главного меню к полету
                     this.paused = false;
					 Layout.AENavigationButton[0].standartButtonActive = false;
                  }

                  if(var1 == 4) {
                     this.pauseMenu.optionsLeft();
                  }

                  if(var1 == 32) {
                     this.pauseMenu.optionsRight();
                     return;
                  }
               }
            }
         } else if(this.mapOpen_) {
            this.mapOpen_ = this.starMap.handleKeystate(var1);
            if(!this.mapOpen_ && this.touchesStream) {
               this.playerEgo.dockToStream(this.targetFollowCamera, false);
               this.playerEgo.setAutoPilot((KIPlayer)null);
               this.playerEgo.shipGrandGroup_.getToParentTransform().setOrientation(this.level.getLandmarks()[1].mainMesh_.getDirection());
               this.playerEgo.setPosition_(this.level.getLandmarks()[1].mainMesh_.getPostition());
               this.playerEgo.shipGrandGroup_.moveForward(4096);
            } else if(this.starMap.destSelected) {
               this.mapOpen_ = false;
               this.startJumpScene();
            }

            if(!this.mapOpen_) {
               this.starMap.OnRelease();
               this.starMap = null;
               return;
            }
         } else if(!this.egoDead) {
            if((var1 == 16384) || this.hud.pauseButton.getStandartButtonPressed()) { // PAUSE
               if(this.pauseMenu == null) {
                  this.pauseMenu = new OptionsWindow();
               }

               this.pauseMenu.resetPauseMenu();
               this.paused = !this.paused;
               if(this.paused) {
                  return;
               }
            }

            if(!this.cinematicBreak_ && Status.getCurrentCampaignMission() > 1) {
               if(!this.playerEgo.isMining()) {
                  this.actionMenuOpen = this.hud.handleActionMenuKeypress(var1, this.level, this.radar);
                  this.paused = this.actionMenuOpen;
                  if(this.actionMenuOpen && !GlobalStatus.actionMenuHelpShown) {
                     this.interuptDialogue = new Dialogue(GlobalStatus.gameText.getText(321));
                     this.interruptedByDialogue = true;
                     GlobalStatus.actionMenuHelpShown = true;
                  }
               }

               if(this.paused) {
                  return;
               }

               if((var1 == 131072) || this.hud.cameraButton.getStandartButtonPressed()) { // camera
                  this.inTurretMode = !this.inTurretMode;
				  this.hud.cameraButton.standartButtonActive = false;
                  if(!this.playerEgo.setTurretMode(this.inTurretMode)) {
                     if(this.lookingBack != 1) {
                        LevelScript.lookBehind(this.targetFollowCamera);
                        this.lookingBack = 1;
                     } else {
                        LevelScript.resetCamera(this.targetFollowCamera, this.level);
                        this.lookingBack = 0;
                     }
                  }
               }

               if((var1 == 256) || hud.fireButton.getStandartButtonPressed()) {
                  if(this.radar.targetedLandmark != null) {
                     if(!this.playerEgo.isAutoPilot()) {
                        this.playerEgo.setAutoPilot(this.radar.targetedLandmark); // захват ретранслятора сканером
                        if(this.radar.targetedLandmark.equals(this.level.getLandmarks()[0])) { // захват сканером станции в автопилот
                           this.hud.hudEvent(10, this.playerEgo);
                        } else if(this.radar.targetedLandmark.equals(this.level.getLandmarks()[3])) {
                           this.hud.hudEvent(15, this.playerEgo);
                        } else {
                           this.hud.hudEvent(12, this.playerEgo);
                        }

                        GlobalStatus.soundManager.playSfx(13);
                     } else if(!this.playerEgo.isLookingBack()) {
                        this.hud.hudEvent(6, this.playerEgo);
                        this.playerEgo.setAutoPilot((KIPlayer)null);
                        this.radar.targetedLandmark = null;
                        this.radar.contextLandmark = null;
                        this.playerEgo.resetGunDelay();
                     }
                  } else {
                     if(this.radar.targetedPlanet != null && !this.playerEgo.isDockingToPlanet()) { // захват планеты в сканер автопилота
                        this.playerEgo.stopPlanetDock_(this.targetFollowCamera);
                        this.playerEgo.silentBoost_();
                        this.playerEgo.resetGunDelay();
                        GlobalStatus.soundManager.playSfx(13);
                        return;
                     }

                     if(!this.playerEgo.isMining()) {
                        if(this.radar.getLockedAsteroid() != null && !this.playerEgo.isDockingToAsteroid()) { // захват астероида
						   this.hud.fireButton.standartButtonActive = false;
                           this.hud.hudEvent(11, this.playerEgo);
                           GlobalStatus.soundManager.playSfx(13);
                        } else if(this.playerEgo.isDockingToAsteroid()) { // отключение автопилота нажатием 5
                           this.hud.fireButton.standartButtonActive = false;
						   this.hud.hudEvent(6, this.playerEgo);
                        }

                        this.playerEgo.dockToAsteroid(this.radar.getLockedAsteroid(), this.targetFollowCamera, this.radar);
                     } else if(this.playerEgo.isMining()) {
                        this.playerEgo.endMining();
                     }
                  }
               }

               if((var1 == 65536 && !this.playerEgo.boosting()) || (hud.boosterButton.getSwitchableButtonPressed() && !this.playerEgo.boosting())) { // BOOSTER
                  this.playerEgo.boost();
                  this.hud.hudEvent(3, this.playerEgo);
               }
			   
			   if(var1 == 4096)
			   {
				this.playerEgo.sub_234();
				++GlobalStatus.stop_ship;
				//System.out.println("Stop_ship: " + SharedVariables.stop_ship);
			   }

                if((var1 == 32768 || this.hud.rocketButton.getStandartButtonPressed()) && this.playerEgo.player.hasGunOfType(1)) { // ROCKET
					this.playerEgo.shoot((int)this.frameTime, 1);
					hud.rocketButton.standartButtonActive = false;
				}

               if(var1 == 512 && Status.getCurrentCampaignMission() > 0) { // 7
                  GlobalStatus.soundManager.playSfx(4);
                  this.isIntro = !this.isIntro;
                  this.hud.hudEvent(this.isIntro?1:2, this.playerEgo);
               }

               if((var1 == 1024) || this.hud.autopilotButton.getSwitchableButtonPressed()) { // открыть меню автопилота
                  this.hud.autopilotButton.switchableButtonActive = false;
				  GlobalStatus.soundManager.playSfx(4);
                  if(this.playerEgo.isAutoPilot()) {
                     this.playerEgo.setAutoPilot((KIPlayer)null);
                     this.hud.hudEvent(6, this.playerEgo);
                     return;
                  }

                  if(!this.autopilotMenuOpen) {
                     if(this.playerEgo.isDockingToAsteroid()) {
                        this.playerEgo.dockToAsteroid(this.radar.getLockedAsteroid(), this.targetFollowCamera, this.radar);
                        this.hud.hudEvent(6, this.playerEgo);
                        return;
                     }

                     if(this.playerEgo.isDockingToStream_()) {
                        this.playerEgo.dockToAsteroid(this.radar.targetedLandmark, this.targetFollowCamera, this.radar);
                        this.hud.hudEvent(6, this.playerEgo);
                        return;
                     }

                     if(!Status.inAlienOrbit()) {
                        if(this.autoPilotList == null || this.level.getPlayerRoute() != null && this.level.getPlayerRoute().getLastWaypoint().reached_) {
                           this.autoPilotList = new AutoPilotList(this.level);
                        }
						
                        this.autopilotMenuOpen = true;
                        this.paused = true;
                        return;
                     }
                  }
               }
            } else {
               if((var1 == 4096 || var1 == 2048) && Status.getCurrentCampaignMission() < 2) {
                  Status.nextCampaignMission();
                  GlobalStatus.applicationManager.SetCurrentApplicationModule(GlobalStatus.scenes[1]);
                  return;
               }

               if(var1 == 256 && this.levelScript.timePassed > 50L) {
                  this.levelScript.skipSequence();
                  this.playerEgo.resetGunDelay();
               }

               if(var1 == 65536 && !this.playerEgo.boosting()) {
                  this.playerEgo.boost();
                  this.hud.hudEvent(3, this.playerEgo);
                  return;
               }
            }
         } else if(this.fiveSecTick > 3000L) {
            if(var1 == 32) {
               this.popup.right();
               return;
            }

            if(var1 == 4) {
               this.popup.left();
               return;
            }

            if(var1 == 256) {
               if(this.popup.getChoice()) {
                  (new RecordHandler()).sub_70(3).sub_b6();
                  ((ModStation)((ModStation)GlobalStatus.scenes[1])).fromGameSave();
                  GlobalStatus.applicationManager.SetCurrentApplicationModule(GlobalStatus.scenes[1]);
                  return;
               }

               GlobalStatus.applicationManager.SetCurrentApplicationModule(GlobalStatus.scenes[0]);
               GlobalStatus.soundManager.playMusic(0);
               return;
            }
         }

      }
   }
}
