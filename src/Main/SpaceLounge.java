package Main;

// бар

import javax.microedition.lcdui.Image;
import HardEngine.*;

import AE.AbstractMesh;
import AE.AECamera;
import AE.EaseInOut;
import AE.GlobalStatus;
import AE.Math.AEMath;
import AE.Math.AEVector3D;
import AE.PaintCanvas.Font;
import AE.PaintCanvas.ImageFactory;
import GoF2.Achievements;
import GoF2.Agent;
import GoF2.FileRead;
import GoF2.Generator;
import GoF2.Globals;
import GoF2.Item;
import GoF2.Layout;
import GoF2.ListItem;
import GoF2.Mission;
import GoF2.Popup;
import GoF2.SolarSystem;
import GoF2.Status;

public final class SpaceLounge {

   private StarMap starMap;
   private Popup popup;
   private ListItemWindow intemInfoWindow;
   private AECamera camera;
   private int chatType_;
   private boolean newSystemWaitingToBeSeen;
   private boolean missionConfirmPopup;
   private boolean transactionConfirmPopup_;
   private boolean itemDescriptionOpen;
   private int selectedAgent;
   private Agent[] agents;
   private int answearsPosY_;
   private int answearsPosX;
   private int chatTextPosY;
   private int chatTextPosX;
   private String[] chatRows;
   private int chatScroll;
   private int chatAnswear;
   private boolean starMapOpen;
   private boolean hangarUpdate;
   private Image[][] clientsFaces;
   private CutScene scene;
   private EaseInOut cameraIntegratorX_;
   private EaseInOut cameraIntegratorZ_;
   private AEVector3D cameraPos = new AEVector3D();
   private boolean[] mentionedOres;
   private boolean var_67e = false;


   public SpaceLounge() {
      this.init();
      int var1 = -1;

      for(int var2 = 0; var2 < this.agents.length; ++var2) {
         Agent var3;
         if(((var3 = this.agents[var2]).sub_8f() == 6 || var3.sub_8f() == 0 && var3.sub_3d9() != null && var3.sub_3d9().getType() == 12) && var3.isAccepted()) {
            var1 = var2;
            break;
         }
      }

      if(var1 >= 0) {
         Agent[] var4 = new Agent[this.agents.length - 1];
         if(var1 == 0) {
            System.arraycopy(this.agents, 1, var4, 0, var4.length);
         } else {
            System.arraycopy(this.agents, 0, var4, 0, var1);
            System.arraycopy(this.agents, var1 + 1, var4, var1, var4.length - var1);
         }

         Status.getStation().sub_3f6(var4);
         this.init();
      }

      if(this.scene == null) {
         this.scene = new CutScene(4);
      }

      while(!this.scene.isLoaded()) {
         this.scene.OnInitialize();
      }

      this.cameraIntegratorX_ = new EaseInOut(GlobalStatus.renderer.sub_85().getPosX(), this.scene.level.getEnemies()[0].mainMesh_.getPosX());
      this.cameraIntegratorZ_ = new EaseInOut(GlobalStatus.renderer.sub_85().getPosZ(), this.scene.level.getEnemies()[0].mainMesh_.getPosZ() - 1000);
   }

   public final void init() {
      boolean var1 = false;
      this.agents = Status.getStation().sub_41e();
      if(this.agents != null) {
         this.clientsFaces = new Image[this.agents.length][];
      }

      int var2;
      for(var2 = 0; var2 < this.agents.length; ++var2) {
         this.clientsFaces[var2] = ImageFactory.faceFromByteArray(this.agents[var2].getFace());
      }

      this.answearsPosX = 9;
      this.chatTextPosY = 21;
      this.chatTextPosX = ImageFactory.faceWidth + 10;
      this.popup = new Popup();
      var1 = false;
      this.hangarUpdate = false;
      if(this.scene != null) {
         this.scene.resetCamera();
      }

      this.newSystemWaitingToBeSeen = false;
      this.mentionedOres = new boolean[20];

      for(var2 = 0; var2 < this.mentionedOres.length; ++var2) {
         this.mentionedOres[var2] = false;
      }

      this.camera = GlobalStatus.renderer.sub_85();
   }

   public final void OnRelease() {
      this.clientsFaces = null;
      if(this.scene != null) {
         this.scene.OnRelease();
      }

      this.scene = null;
   }

   public final boolean handleKeystate(int var1) {
      if(this.starMapOpen) {
         if(!this.starMap.handleKeystate(var1)) {
            this.starMapOpen = false;
            this.scene.resetCamera();
         }

         return true;
      } else if(this.transactionConfirmPopup_) {
         if(var1 == 256) {
            if(this.var_67e) {
               if(this.popup.getChoice()) {
                  ModStation.var_80 = true;
                  this.var_67e = false;
               }

               this.transactionConfirmPopup_ = false;
               return true;
            }

            this.transactionConfirmPopup_ = false;
         }

         if(var1 == 4) {
            this.popup.left();
         } else if(var1 == 32) {
            this.popup.right();
         }

         return true;
      } else {
         boolean var8;
         switch(this.chatType_) {
         case 0:
            var8 = false;
            if(var1 == 2 || var1 == 32) {
               --this.selectedAgent;
               if(this.selectedAgent < 0) {
                  this.selectedAgent = this.agents.length - 1;
               }

               var8 = true;
            }

            if(var1 == 64 || var1 == 4) {
               ++this.selectedAgent;
               if(this.selectedAgent >= this.agents.length) {
                  this.selectedAgent = 0;
               }

               var8 = true;
            }

            if(var8) {
               AbstractMesh var12 = this.scene.level.getEnemies()[this.selectedAgent].mainMesh_;
               this.cameraIntegratorX_.SetRange(GlobalStatus.renderer.sub_85().getPosX(), var12.getPosX());
               this.cameraIntegratorZ_.SetRange(GlobalStatus.renderer.sub_85().getPosZ(), var12.getPosZ() - 1000);
            }

            if((var1 == 8192) || Layout.dialogueButtonBack.getStandartButtonPressed()) {
               GlobalStatus.renderer.setActiveCamera(this.camera);
			   Layout.dialogueButtonBack.standartButtonActive = false;
               return false;
            }

            if(var1 == 16384 || var1 == 256 || Layout.dialogueButtonNext.getStandartButtonPressed()) {
			   Layout.dialogueButtonNext.standartButtonActive = false;
               this.startChat();
            }
            break;
         case 1:
         case 3:
            if(var1 == 8192) {
               if(this.chatScroll >= 3) {
                  this.chatScroll -= 4;
               } else if(this.chatType_ == 1) {
                  this.chatType_ = 0;
                  this.chatScroll = 0;
               }
            }

            if(var1 == 16384 || var1 == 256) {
               var8 = false;
               if(this.chatScroll < this.chatRows.length - 4) {
                  this.chatScroll += 4;
                  if(this.agents[this.selectedAgent].sub_8f() != 1 && this.chatType_ == 1 && this.chatScroll >= this.chatRows.length - 4 || this.chatType_ == 3 && this.chatScroll >= this.chatRows.length) {
                     var8 = true;
                  }
               } else {
                  var8 = true;
               }

               if(var8) {
                  this.chatAnswear = 0;
                  Agent var10;
                  if((var10 = this.agents[this.selectedAgent]).sub_8f() == 1) {
                     this.chatType_ = 0;
                  } else {
                     this.chatType_ = this.chatType_ == 1?2:0;
                     if(this.chatType_ == 0 && this.newSystemWaitingToBeSeen) {
                        this.starMap = ((ModStation)GlobalStatus.scenes[1]).starMap;
                        if(this.starMap == null) {
                           ((ModStation)GlobalStatus.scenes[1]).starMap = new StarMap(false, (Mission)null, true, var10.getSellSystemIndex());
                           this.starMap = ((ModStation)GlobalStatus.scenes[1]).starMap;
                        } else {
                           this.starMap.init(false, (Mission)null, true, var10.getSellSystemIndex());
                        }

                        this.starMapOpen = true;
                        this.newSystemWaitingToBeSeen = false;
                     }
                  }
               }
            }
            break;
         case 2:
            int var2 = this.agents[this.selectedAgent].sub_3d9() != null && this.agents[this.selectedAgent].sub_3d9().isOutsideMission()?5:(this.agents[this.selectedAgent].sub_8f() != 2 && this.agents[this.selectedAgent].sub_8f() != 3?3:4);
            if(this.itemDescriptionOpen) {
               if(var1 == 8192) {
                  this.itemDescriptionOpen = false;
               }

               return true;
            }

            int var3;
            Agent var6;
            if(this.missionConfirmPopup) {
               if(var1 == 4) {
                  this.popup.left();
               }

               if(var1 == 32) {
                  this.popup.right();
               }

               if(var1 != 256) {
                  return true;
               }

               if(this.popup.getChoice()) {
                  String var7 = GlobalStatus.gameText.getText(484 + GlobalStatus.random.nextInt(3));
                  switch((var6 = this.agents[this.selectedAgent]).sub_8f()) {
                  case 0:
                  case 5:
                     if(Status.getFreelanceMission() != null && !Status.getFreelanceMission().isEmpty()) {
                        if(Status.getFreelanceMission().getType() != 0 && Status.getFreelanceMission().getType() != 3 && Status.getFreelanceMission().getType() != 5) {
                           if(Status.getFreelanceMission().getType() == 11) {
                              Status.setPassengers(0);
                           }
                        } else {
                           Item[] var14;
                           if((var14 = Status.getShip().getCargo()) != null) {
                              for(int var15 = 0; var15 < var14.length; ++var15) {
                                 if(var14[var15].setUnsaleable() && var14[var15].getIndex() == 116) {
                                    Status.getShip().removeCargo(var14[var15]);
                                    this.hangarUpdate = true;
                                    break;
                                 }
                              }
                           }
                        }

                        if(!Status.getFreelanceMission().getAgent().isGenericAgent_()) {
                           Status.getFreelanceMission().getAgent().sub_789(false);
                        }

                        Status.setFreelanceMission(Mission.emptyMission_);
                     }

                     if(var6.sub_3d9().getType() == 0) {
                        Item var13;
                        (var13 = Globals.getItems()[116].makeItem(var6.sub_3d9().getCommodityAmount_())).setUnsaleable(true);
                        Status.getShip().addCargo(var13);
                     } else if(var6.sub_3d9().getType() == 11) {
                        Status.setPassengers(var6.sub_3d9().getCommodityAmount_());
                     }

                     if(var6.sub_3d9().getType() == 12) {
                        var7 = GlobalStatus.gameText.getText(490);
                     } else {
                        var7 = var7 + " " + GlobalStatus.gameText.getText(487 + GlobalStatus.random.nextInt(3));
                     }

                     if(var6.sub_3d9().isOutsideMission()) {
                        if(!var6.wasAskedForDifficulty) {
                           ++Status.acceptedNotAskingDifficulty;
                        }

                        if(!var6.wasAskedForLocation) {
                           ++Status.var_149c;
                        }
                     }

                     Status.setFreelanceMission(var6.sub_3d9());
                     Status.getFreelanceMission().setAgent(var6);
                     this.init();
                     this.hangarUpdate = true;
                  case 1:
                  default:
                     break;
                  case 2:
                     Status.changeCredits(-var6.getSellItemPrice());
                     Status.getShip().addCargo(Globals.getItems()[var6.getSellItemIndex()].makeItem(var6.getSellItemQuantity()));
                     if(var6.getSellItemIndex() >= 132 && var6.getSellItemIndex() < 154) {
                        Status.drinkTypesPossesed[var6.getSellItemIndex() - 132] = true;
                     }

                     this.hangarUpdate = true;
                     break;
                  case 3:
                     Status.changeCredits(-var6.getSellItemPrice());

                     for(var3 = 0; var3 < Status.getBluePrints().length; ++var3) {
                        if(Status.getBluePrints()[var3].getIndex() == var6.getSellBlueprintIndex()) {
                           Status.getBluePrints()[var3].unlock();
                           break;
                        }
                     }

                     this.hangarUpdate = true;
                     break;
                  case 4:
                     Status.changeCredits(-var6.getSellItemPrice());
                     Status.setSystemVisibility(var6.getSellSystemIndex(), true);
                     this.hangarUpdate = true;
                     this.newSystemWaitingToBeSeen = true;
                     break;
                  case 6:
                     Status.var_1381 += 1 + var6.getWingmanFriendsCount_();
                     Status.setWingmenNames(var6.getWingmenNames());
                     Status.wingmanRace = var6.sub_166();
                     Status.var_bea = 600000;
                     Status.wingmanFace = var6.getFace();
                     if(Achievements.gotAllMedals()) {
                        Status.changeCredits(var6.getCosts());
                     } else {
                        Status.changeCredits(-var6.getCosts());
                     }
                     break;
                  case 7:
                     Status.getStanding().rehabilitate(var6.sub_166());
                     Status.changeCredits(-var6.getCosts());
                  }

                  var6.sub_789(true);
                  this.chatRows = Font.splitToLines(var7, GlobalStatus.var_e75 - this.chatTextPosX - this.answearsPosX);
                  this.chatType_ = 3;
                  this.chatScroll = 0;
               }

               this.missionConfirmPopup = false;
               return true;
            }

            if(var1 == 2) {
               --this.chatAnswear;
               if(this.chatAnswear < 0) {
                  this.chatAnswear = var2 - 1;
               }
            }

            if(var1 == 64) {
               ++this.chatAnswear;
               if(this.chatAnswear >= var2) {
                  this.chatAnswear = 0;
               }
            }

            if(var1 == 16384 || var1 == 256) {
               boolean var5 = false;
               switch(this.chatAnswear) {
               case 0:
                  var3 = (var6 = this.agents[this.selectedAgent]).sub_8f();
                  String var4 = "";
                  String var9;
                  switch(var3) {
                  case 0:
                  case 5:
                     var3 = var6.sub_3d9().getType();
                     if(!GlobalStatus.var_1083 && (var6.sub_8f() == 6 || var3 != 0 && var3 != 11 && var3 != 4 && var3 != 7 && var3 != 8 && var3 != 12)) {
                        var9 = Class_1017.sub_2b(37) + "\n\n" + Class_1017.sub_2b(38);
                        this.popup.set(var9, true);
                        this.transactionConfirmPopup_ = true;
                        this.var_67e = true;
                        return true;
                     }

                     if(var6.sub_3d9().getType() == 0) {
                        var3 = var6.sub_3d9().getCommodityAmount_();
                        if(!Status.getShip().spaceAvailable(var3)) {
                           var4 = Status.replaceTokens(GlobalStatus.gameText.getText(162), "" + var3, "#Q");
                           var5 = true;
                        }
                     } else if(var6.sub_3d9().getType() == 11 && (var3 = var6.sub_3d9().getCommodityAmount_()) > Status.getShip().getMaxPassengers()) {
                        var4 = Status.replaceTokens(GlobalStatus.gameText.getText(163), "" + var3, "#Q");
                        var5 = true;
                     }

                     if(!var5) {
                        if(var6.isGenericAgent_()) {
                           var4 = Status.replaceTokens(Status.replaceTokens(GlobalStatus.gameText.getText(499), Layout.formatCredits(var6.sub_3d9().getReward()), "#C"), GlobalStatus.gameText.getText(179 + var6.sub_3d9().getType()), "#M");
                        } else {
                           var4 = GlobalStatus.gameText.getText(498);
                        }
                     }
                  case 1:
                  default:
                     break;
                  case 2:
                  case 3:
                  case 4:
                     if(!GlobalStatus.var_1083 && var6.sub_8f() == 2 && Globals.getItems()[var6.getSellItemIndex()].getTecLevel() >= 4) {
                        var9 = Class_1017.sub_2b(35) + "\n\n" + Class_1017.sub_2b(38);
                        this.popup.set(var9, true);
                        this.transactionConfirmPopup_ = true;
                        this.var_67e = true;
                        return true;
                     }

                     if(Status.getCredits() < var6.getSellItemPrice()) {
                        this.popup.set(Status.replaceTokens(GlobalStatus.gameText.getText(83), Layout.formatCredits(var6.getSellItemPrice() - Status.getCredits()), "#C"), false);
                        this.transactionConfirmPopup_ = true;
                        return true;
                     }

                     if(var3 == 3) {
                        var4 = Status.replaceTokens(Status.replaceTokens(GlobalStatus.gameText.getText(503), GlobalStatus.gameText.getNamedParameterItems(Globals.getItems()[var6.getSellBlueprintIndex()].getIndex()), "#P"), Layout.formatCredits(var6.getSellItemPrice()), "#C");
                     } else if(var3 == 4) {
                        var4 = GlobalStatus.gameText.getText(504);
                        new FileRead();
                        var9 = null;
                        SolarSystem[] var11 = FileRead.loadSystemsBinary();
                        var4 = Status.replaceTokens(Status.replaceTokens(var4, var11[var6.getSellSystemIndex()].getName(), "#S"), Layout.formatCredits(var6.getSellItemPrice()), "#C");
                     } else if(var3 == 2) {
                        var4 = Status.replaceTokens(Status.replaceTokens(Status.replaceTokens(GlobalStatus.gameText.getText(502), "" + var6.getSellItemQuantity(), "#Q"), GlobalStatus.gameText.getNamedParameterItems(var6.getSellItemIndex()), "#P"), Layout.formatCredits(var6.getSellItemPrice()), "#C");
                     }
                     break;
                  case 6:
                     if(!GlobalStatus.var_1083 && var6.sub_8f() == 6) {
                        var9 = Class_1017.sub_2b(37) + "\n\n" + Class_1017.sub_2b(38);
                        this.popup.set(var9, true);
                        this.transactionConfirmPopup_ = true;
                        this.var_67e = true;
                        return true;
                     }

                     if(Status.getCredits() < var6.getCosts()) {
                        this.popup.set(Status.replaceTokens(GlobalStatus.gameText.getText(83), Layout.formatCredits(var6.getCosts() - Status.getCredits()), "#C"), false);
                        this.transactionConfirmPopup_ = true;
                        return true;
                     }

                     if(Status.getWingmenNames() != null) {
                        this.popup.set(GlobalStatus.gameText.getText(424), false);
                        this.transactionConfirmPopup_ = true;
                        return true;
                     }

                     var4 = Status.replaceTokens(Status.replaceTokens(GlobalStatus.gameText.getText(Achievements.gotAllMedals()?501:500), "" + (1 + var6.getWingmanFriendsCount_()), "#Q"), Layout.formatCredits(var6.getCosts()), "#C");
                     break;
                  case 7:
                     if(Status.getCredits() < var6.getCosts()) {
                        this.popup.set(Status.replaceTokens(GlobalStatus.gameText.getText(83), Layout.formatCredits(var6.getCosts() - Status.getCredits()), "#C"), false);
                        this.transactionConfirmPopup_ = true;
                        return true;
                     }

                     var4 = Status.replaceTokens(GlobalStatus.gameText.getText(515), Layout.formatCredits(var6.getCosts()), "#C");
                  }

                  if(var5) {
                     this.popup.set(var4, false);
                     this.transactionConfirmPopup_ = true;
                  } else {
                     this.popup.set(var4, true);
                     this.missionConfirmPopup = true;
                  }
                  break;
               case 1:
                  this.chatRows = Font.splitToLines(GlobalStatus.gameText.getText(479 + GlobalStatus.random.nextInt(4)), GlobalStatus.var_e75 - this.chatTextPosX - this.answearsPosX);
                  this.chatType_ = 3;
                  this.chatScroll = 0;
                  ++Status.missionsRejected;
                  break;
               case 2:
                  this.chatType_ = 1;
                  this.chatRows = Font.splitToLines(this.agents[this.selectedAgent].getMessage(), GlobalStatus.var_e75 - this.chatTextPosX - this.answearsPosX);
                  this.chatScroll = 0;
                  ++Status.askedToRepeate;
                  break;
               case 3:
                  if(this.agents[this.selectedAgent].sub_8f() != 2 && this.agents[this.selectedAgent].sub_8f() != 3) {
                     this.agents[this.selectedAgent].wasAskedForLocation = true;
                     if(this.agents[this.selectedAgent].sub_3d9().getTargetStation() == Status.getStation().getId()) {
                        this.chatRows = Font.splitToLines(GlobalStatus.gameText.getText(441), GlobalStatus.var_e75 - this.chatTextPosX - this.answearsPosX);
                        this.chatType_ = 1;
                        this.chatScroll = 0;
                     } else {
                        this.starMap = ((ModStation)GlobalStatus.scenes[1]).starMap;
                        if(this.starMap == null) {
                           ((ModStation)GlobalStatus.scenes[1]).starMap = new StarMap(true, this.agents[this.selectedAgent].sub_3d9(), false, -1);
                           this.starMap = ((ModStation)GlobalStatus.scenes[1]).starMap;
                        } else {
                           this.starMap.init(true, this.agents[this.selectedAgent].sub_3d9(), false, -1);
                        }

                        this.starMapOpen = true;
                     }
                  } else {
                     if(this.intemInfoWindow == null) {
                        this.intemInfoWindow = new ListItemWindow();
                     }

                     if(this.agents[this.selectedAgent].sub_8f() == 2) {
                        this.intemInfoWindow.setup(new ListItem(Globals.getItems()[this.agents[this.selectedAgent].getSellItemIndex()]), Globals.itemsImage, Globals.itemTypesImage, (Image)null, (Image)null, false);
                     } else {
                        for(var3 = 0; var3 < Status.getBluePrints().length; ++var3) {
                           if(Status.getBluePrints()[var3].getIndex() == this.agents[this.selectedAgent].getSellBlueprintIndex()) {
                              this.intemInfoWindow.setup(new ListItem(Globals.getItems()[Status.getBluePrints()[var3].getIndex()]), Globals.itemsImage, Globals.itemTypesImage, (Image)null, (Image)null, false);
                              break;
                           }
                        }
                     }

                     this.itemDescriptionOpen = true;
                  }
                  break;
               case 4:
                  this.agents[this.selectedAgent].wasAskedForDifficulty = true;
                  var3 = (int)((float)this.agents[this.selectedAgent].sub_3d9().getDifficulty() / 10.0F * 5.0F);
                  this.chatRows = Font.splitToLines(GlobalStatus.gameText.getText(var3 + 443), GlobalStatus.var_e75 - this.chatTextPosX - this.answearsPosX);
                  this.chatType_ = 1;
                  this.chatScroll = 0;
               }

               if(this.agents[this.selectedAgent].isGenericAgent_()) {
                  this.agents[this.selectedAgent].setEvent(1);
               }
            }
         }

         return true;
      }
   }

   public final void draw(int var1, int var2) {
      if(this.itemDescriptionOpen) {
         this.intemInfoWindow.draw();
         Layout.drawFooter("", GlobalStatus.gameText.getText(65));
      } else if(this.starMapOpen) {
         this.starMap.update(var1, var2);
      } else {
         Layout.drawBG();
         GlobalStatus.graphics.setClip(1, 15, GlobalStatus.var_e75 - 1, GlobalStatus.var_eb6 - 16);
         this.cameraIntegratorX_.Increase(var2);
         this.cameraIntegratorZ_.Increase(var2);
         var2 = this.cameraIntegratorX_.GetValue();
         int var3 = this.cameraIntegratorZ_.GetValue();
         GlobalStatus.renderer.sub_85().moveTo(var2, 500, var3);
         if(this.scene != null) {
            CutScene var10001 = this.scene;
            this.scene.renderScene(var1);
         }

         GlobalStatus.graphics.setClip(0, 0, GlobalStatus.var_e75, GlobalStatus.var_eb6);
         Layout.drawNonFullScreenWindow(GlobalStatus.gameText.getText(218), false);
         SpaceLounge var5 = this;
         if(this.chatType_ == 0) {
            this.answearsPosY_ = GlobalStatus.var_eb6 - 16 - (this.agents.length + 1) * Font.getFontSpacingY() + 2;
         } else if(this.chatType_ == 2) {
            if(this.agents[this.selectedAgent].sub_8f() != 2 && this.agents[this.selectedAgent].sub_8f() != 3) {
               if(this.agents[this.selectedAgent].sub_3d9() != null && this.agents[this.selectedAgent].sub_3d9().isOutsideMission()) {
                  this.answearsPosY_ = GlobalStatus.var_eb6 - 16 - 6 * Font.getFontSpacingY() + 2;
               } else {
                  this.answearsPosY_ = GlobalStatus.var_eb6 - 16 - 4 * Font.getFontSpacingY() + 2;
               }
            } else {
               this.answearsPosY_ = GlobalStatus.var_eb6 - 16 - 5 * Font.getFontSpacingY() + 2;
            }
         } else {
            this.answearsPosY_ = GlobalStatus.var_eb6;
         }

         if(this.chatType_ == 2) {
            Layout.drawMenuWindow((String)null, 3, this.answearsPosY_, GlobalStatus.var_e75 - 7, GlobalStatus.var_eb6 - this.answearsPosY_ - 16 - 3);
         }

         if(this.chatType_ != 0) {
            Layout.drawMenuWindow((String)null, 3, 15, GlobalStatus.var_e75 - 7, ImageFactory.faceHeight + 7);
            ImageFactory.drawChar(this.clientsFaces[this.selectedAgent], 7, 19, 0);
            if(this.agents[this.selectedAgent].sub_8f() == 0 || this.agents[this.selectedAgent].sub_8f() == 6 || this.agents[this.selectedAgent].sub_8f() == 2 && Globals.getItems()[this.agents[this.selectedAgent].getSellItemIndex()].getTecLevel() >= 4) {
               var2 = -1;
               if(this.agents[this.selectedAgent].sub_3d9() != null) {
                  var2 = this.agents[this.selectedAgent].sub_3d9().getType();
               }

               if(!GlobalStatus.var_1083 && (this.agents[this.selectedAgent].sub_8f() == 2 && Globals.getItems()[this.agents[this.selectedAgent].getSellItemIndex()].getTecLevel() >= 4 || this.agents[this.selectedAgent].sub_8f() == 6 || var2 != 0 && var2 != 11 && var2 != 4 && var2 != 7 && var2 != 8 && var2 != 12)) {
                  GlobalStatus.graphics.drawImage(Layout.sub_8b(), 7 + ImageFactory.faceWidth - 3, 19 + ImageFactory.faceHeight - 3, 40);
                  if(this.chatType_ != 1 && this.chatType_ != 3) {
                    GlobalStatus.graphics.drawImage(Layout.sub_8b(), this.answearsPosX + Font.getTextWidth(GlobalStatus.gameText.getText(495), 0) + 1, this.answearsPosY_ + 4 + 0 * Font.getFontSpacingY(), 20);
                  }
               }
            }
         }

         if(this.chatType_ == 0) {
            Layout.drawFooter(GlobalStatus.gameText.getText(494), GlobalStatus.gameText.getText(65));
			int shipLoad = Status.getShip().getCurrentLoad();
			boolean bool;
			if((bool = shipLoad > Status.getShip().getCargoPlus()) && Layout.quickClockHigh_() || !bool) {
				Font.drawString(shipLoad + "/" + Status.getShip().getCargoPlus() + "t", (GlobalStatus.var_e75 / 2) + (Layout.AENavigationButton[1].standartButtonWidth / 2), GlobalStatus.var_eb6 - (Layout.AENavigationButton[1].standartButtonHeight / 4), bool?4:0, 34);
			}
			Font.drawString(Layout.formatCredits(Status.getCredits()), Layout.AENavigationButton[1].standartButtonX - (Layout.AENavigationButton[1].standartButtonWidth + 4), GlobalStatus.var_eb6 - (Layout.AENavigationButton[1].standartButtonHeight / 4), 0, 34);
            this.cameraPos = this.scene.level.getEnemies()[this.selectedAgent].mainMesh_.getLocalPos(this.cameraPos);
            this.cameraPos.x -= 100;
            this.cameraPos.y += 500;
            GlobalStatus.renderer.sub_85().getScreenPosition(this.cameraPos);
            Agent var6;
            Font.drawString(!this.agents[this.selectedAgent].isKnown() && !this.agents[this.selectedAgent].isSpecialAgent()?GlobalStatus.gameText.getText(229 + this.agents[this.selectedAgent].sub_166()):(var6 = this.agents[this.selectedAgent]).var_4f, this.cameraPos.x, this.cameraPos.y, 2);
            if(this.agents[this.selectedAgent].isKnown()) {
               Font.drawString(this.agents[this.selectedAgent].sub_3d9() != null?GlobalStatus.gameText.getText(179 + this.agents[this.selectedAgent].sub_3d9().getType()):(this.agents[this.selectedAgent].sub_8f() == 6?GlobalStatus.gameText.getText(146):(this.agents[this.selectedAgent].sub_8f() == 2?GlobalStatus.gameText.getText(145):(this.agents[this.selectedAgent].sub_8f() == 7?GlobalStatus.gameText.getText(514):""))), this.cameraPos.x, this.cameraPos.y + Font.getFontSpacingY(), 1);
            }
         } else {
            var2 = 0;

            for(var3 = this.chatScroll; var3 < var5.chatScroll + 4 && var3 < var5.chatRows.length; ++var3) {
               String var4 = var3 != var5.chatRows.length - 1 && var3 == var5.chatScroll + 4 - 1?"..":"";
               Font.drawString(var5.chatRows[var3] + (Layout.quickClockHigh_()?var4:""), var5.chatTextPosX, var5.chatTextPosY + var2++ * Font.getFontSpacingY(), 1);
            }

            if(var5.chatType_ != 1 && var5.chatType_ != 3) {
               Font.drawString(GlobalStatus.gameText.getText(495), var5.answearsPosX, var5.answearsPosY_ + 3 + 0 * Font.getFontSpacingY(), var5.chatAnswear == 0?2:1);
               Font.drawString(GlobalStatus.gameText.getText(496), var5.answearsPosX, var5.answearsPosY_ + 3 + 1 * Font.getFontSpacingY(), var5.chatAnswear == 1?2:1);
               Font.drawString(GlobalStatus.gameText.getText(497), var5.answearsPosX, var5.answearsPosY_ + 3 + 2 * Font.getFontSpacingY(), var5.chatAnswear == 2?2:1);
               if((var3 = var5.agents[var5.selectedAgent].sub_8f()) != 2 && var3 != 3) {
                  if(var3 == 0 && var5.agents[var5.selectedAgent].sub_3d9().isOutsideMission()) {
                     Font.drawString(GlobalStatus.gameText.getText(440), var5.answearsPosX, var5.answearsPosY_ + 3 + 3 * Font.getFontSpacingY(), var5.chatAnswear == 3?2:1);
                     Font.drawString(GlobalStatus.gameText.getText(442), var5.answearsPosX, var5.answearsPosY_ + 3 + 4 * Font.getFontSpacingY(), var5.chatAnswear == 4?2:1);
                  }
               } else {
                  Font.drawString(GlobalStatus.gameText.getText(415), var5.answearsPosX, var5.answearsPosY_ + 3 + 3 * Font.getFontSpacingY(), var5.chatAnswear == 3?2:1);
               }

               Layout.drawFooter(GlobalStatus.gameText.getText(253), "");
            } else {
               Layout.drawFooter(GlobalStatus.gameText.getText(73), var5.chatScroll > 0?GlobalStatus.gameText.getText(74):(var5.chatType_ == 1?GlobalStatus.gameText.getText(246):""));
            }
         }

         if(this.missionConfirmPopup || this.transactionConfirmPopup_) {
            this.popup.draw();
         }

      }
   }

   private void startChat() {
      Agent var1;
      String var8;
      if(!(var1 = this.agents[this.selectedAgent]).isGenericAgent_()) {
         var8 = null;
         if(var1.getEvent() > 0) {
            var8 = GlobalStatus.gameText.getText(492);
         } else {
            if(!var1.isAccepted()) {
               ++Status.var_134b;
               if(var1.sub_8f() == 4) {
                  var8 = GlobalStatus.gameText.getText(505 + GlobalStatus.random.nextInt(2));
                  var8 = var8 + " " + GlobalStatus.gameText.getNamedParameterAgents(var1.getMessageId());
                  var8 = var8 + " " + GlobalStatus.gameText.getText(508);
                  new FileRead();
                  SolarSystem[] var11 = FileRead.loadSystemsBinary();
                  var8 = Status.replaceTokens(Status.replaceTokens(var8, var11[var1.getSellSystemIndex()].getName(), "#S"), Layout.formatCredits(var1.getSellItemPrice()), "#C");
               } else {
                  var8 = GlobalStatus.gameText.getText(505 + GlobalStatus.random.nextInt(2));
                  var8 = var8 + " " + GlobalStatus.gameText.getNamedParameterAgents(var1.getMessageId());
                  var8 = Status.replaceTokens(Status.replaceTokens(var8 + " " + GlobalStatus.gameText.getText(507), GlobalStatus.gameText.getNamedParameterItems(Globals.getItems()[var1.getSellBlueprintIndex()].getIndex()), "#N"), Layout.formatCredits(var1.getSellItemPrice()), "#C");
               }

               var8 = var8 + "\n" + GlobalStatus.gameText.getText(475 + GlobalStatus.random.nextInt(3));
               this.chatRows = Font.splitToLines(var8, GlobalStatus.var_e75 - this.chatTextPosX - this.answearsPosX);
               this.agents[this.selectedAgent].setMessage(var8);
               this.chatScroll = 0;
               this.chatType_ = 1;
               return;
            }

            var8 = GlobalStatus.gameText.getText(492);
         }

         this.chatRows = Font.splitToLines(var8, GlobalStatus.var_e75 - this.chatTextPosX - this.answearsPosX);
         this.chatScroll = 0;
         this.chatType_ = 3;
      } else {
         if(var1.isKnown() && var1.sub_8f() != 7) {
            if(var1.isAccepted()) {
               var8 = GlobalStatus.gameText.getText(var1.sub_8f() == 5?491:(var1.sub_8f() != 6 && (var1.sub_3d9() == null || var1.sub_3d9().getType() != 12)?492:493));
               this.chatRows = Font.splitToLines(var8, GlobalStatus.var_e75 - this.chatTextPosX - this.answearsPosX);
               this.chatScroll = 0;
               this.chatType_ = 3;
               return;
            }

            this.chatRows = Font.splitToLines(var1.getMessage(), GlobalStatus.var_e75 - this.chatTextPosX - this.answearsPosX);
         } else {
            ++Status.var_134b;
            int var2;
            boolean var3 = (var2 = var1.sub_8f()) != 1 && var2 != 7;
            boolean var4 = var2 == 0 || var2 == 5;
            var8 = var3?GlobalStatus.gameText.getText(390 + GlobalStatus.random.nextInt(6)):"";
            String var9 = var3?GlobalStatus.gameText.getText(396 + GlobalStatus.random.nextInt(2)):"";
            String var10 = var4?GlobalStatus.gameText.getText(398 + GlobalStatus.random.nextInt(6)):"";
            String var5 = "";
            var9 = Status.replaceTokens(var9, var1.sub_81(), "#N");
            Generator var6 = new Generator();
            int var7;
            switch(var1.sub_8f()) {
            case 0:
               var5 = var5 + GlobalStatus.gameText.getText(425 + var1.sub_3d9().getType());
               if(var1.sub_3d9().getType() == 5 || var1.sub_3d9().getType() == 3) {
                  var5 = var5 + " " + GlobalStatus.gameText.getText(438);
               }

               var5 = Status.replaceTokens(Status.replaceTokens(Status.replaceTokens(Status.replaceTokens(var5, GlobalStatus.gameText.getText(448 + var1.sub_3d9().getCommodityIndex()), "#P"), "" + var1.sub_3d9().getCommodityAmount_(), "#Q"), var1.sub_3d9().getTargetStationName(), "#S"), var1.sub_3d9().getTargetName(), "#N");
               var5 = Status.replaceTokens(var5 + "\n" + GlobalStatus.gameText.getText(404 + GlobalStatus.random.nextInt(3)), Layout.formatCredits(var1.sub_3d9().getReward()), "#C");
               break;
            case 1:
               boolean var15 = false;
               boolean var16 = false;

               do {
                  var7 = GlobalStatus.random.nextInt(20);
                  if(!this.mentionedOres[var7]) {
                     this.mentionedOres[var7] = true;
                     var16 = true;
                  }
               } while(!var16);

               if(var1.sub_166() != 0 && var7 == 16) {
                  var7 = 4;
               }

               var5 = Status.replaceTokens(Status.replaceTokens(Status.replaceTokens(GlobalStatus.gameText.getText(var7 + 455), Globals.getRandomPlanetName(), "#S"), var1.sub_81(), "#N"), GlobalStatus.gameText.getNamedParameterItems(154 + GlobalStatus.random.nextInt(10)), "#ORE");
               break;
            case 2:
               var5 = var5 + GlobalStatus.gameText.getText(407 + GlobalStatus.random.nextInt(4));
               var5 = Status.replaceTokens(Status.replaceTokens(Status.replaceTokens(var5 + "\n" + GlobalStatus.gameText.getText(412 + GlobalStatus.random.nextInt(2)), "" + var1.getSellItemQuantity(), "#Q"), GlobalStatus.gameText.getNamedParameterItems(var1.getSellItemIndex()), "#P"), Layout.formatCredits(var1.getSellItemPrice()), "#C");
               if(var1.getSellItemQuantity() > 1) {
                  var5 = Status.replaceTokens(var5 + " " + GlobalStatus.gameText.getText(414), Layout.formatCredits(var1.getSellItemPrice() / var1.getSellItemQuantity()), "#C");
               }
            case 3:
            case 4:
            default:
               break;
            case 5:
               var1.sub_3c9(var6.createFreelanceMission(var1));
               var5 = Status.replaceTokens(Status.replaceTokens(Status.replaceTokens(var5 + GlobalStatus.gameText.getText(416 + GlobalStatus.random.nextInt(2)), "" + var1.sub_3d9().getCommodityAmount_(), "#Q"), GlobalStatus.gameText.getNamedParameterItems(var1.sub_3d9().getCommodityIndex()), "#P"), Layout.formatCredits(var1.sub_3d9().getReward()), "#C");
               break;
            case 6:
               var5 = Status.replaceTokens(var5 + GlobalStatus.gameText.getText((Achievements.gotAllMedals()?421:418) + var1.getWingmanFriendsCount_()), Layout.formatCredits(var1.getCosts()), "#C");
               if(var1.getWingmanFriendsCount_() > 0) {
                  var5 = Status.replaceTokens(var5, "" + var1.getWingmanName(1), "#W");
               }
               break;
            case 7:
               int var14 = var1.sub_166();
               int var12 = Status.getStanding().getStanding(var14 != 2 && var14 != 3?0:1);
               if(!Status.getStanding().isEnemy(var14)) {
                  var5 = GlobalStatus.gameText.getText(513);
                  this.chatRows = Font.splitToLines(var5, GlobalStatus.var_e75 - this.chatTextPosX - this.answearsPosX);
                  this.chatScroll = 0;
                  this.chatType_ = 3;
                  return;
               }

               var7 = (int)((float)AEMath.abs(var12) / 100.0F * 16000.0F);
               var12 = var14 == 2?509:(var14 == 3?510:(var14 == 0?511:512));
               var5 = Status.replaceTokens(GlobalStatus.gameText.getText(var12), Layout.formatCredits(var7), "#C");
               var1.setCosts(var7);
            }

            var1.setMessage(var5);
            if(var1.sub_8f() == 1) {
               this.chatRows = Font.splitToLines(var5, GlobalStatus.var_e75 - this.chatTextPosX - this.answearsPosX);
               var1.setEvent(1);
            } else if(var1.sub_8f() == 7) {
               this.chatRows = Font.splitToLines(var5, GlobalStatus.var_e75 - this.chatTextPosX - this.answearsPosX);
            } else {
               String var13;
               if(var1.sub_3d9() != null && var1.sub_3d9().getType() == 12) {
                  var5 = Status.replaceTokens(GlobalStatus.gameText.getText(437), Layout.formatCredits(var1.sub_3d9().getReward()), "#C");
                  var1.setMessage(var5);
                  var13 = "\n" + GlobalStatus.gameText.getText(475 + GlobalStatus.random.nextInt(3));
                  this.chatRows = Font.splitToLines(var5 + var13, GlobalStatus.var_e75 - this.chatTextPosX - this.answearsPosX);
               } else {
                  var5 = "\n" + var5;
                  var13 = "\n" + GlobalStatus.gameText.getText(475 + GlobalStatus.random.nextInt(3));
                  this.chatRows = Font.splitToLines(var8 + " " + var9 + " " + var10 + var5 + var13, GlobalStatus.var_e75 - this.chatTextPosX - this.answearsPosX);
               }
            }
         }

         var1.setAgentsStationName(Status.getStation().getName());
         var1.setAgentsSystemName(Status.getSystem().getName());
         this.chatType_ = 1;
         this.chatScroll = 0;
      }
   }

   public final boolean hangarNeedsUpdate() {
      return this.hangarUpdate;
   }

   public final void setHangarUpdate(boolean var1) {
      this.hangarUpdate = false;
   }
}
