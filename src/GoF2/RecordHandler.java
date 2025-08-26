/**
**	@class Save old
**	Работа с сохранениями
**/

package GoF2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;

import AE.GlobalStatus;

public final class RecordHandler {

   public static RecordStore recordStore = null;
   private Mission mission;
   private Agent agent;


   public final GameRecord[] readAllPreviews() {
      GameRecord[] var1 = new GameRecord[10];

      for(int var2 = 0; var2 < var1.length; ++var2) {
         var1[var2] = this.readPreview(var2);
      }

      return var1;
   }

   public final GameRecord sub_70(int var1) {
      new GameRecord();
      Object var2 = null;
      return this.recordStoreRead(var1);
   }
   
   public final void BOOTLOADER_RMS_READ() {
	   try {
		   this.recordStore = RecordStore.openRecordStore("GoF2_REFORGED_BOOT", false);
		   this.recordStore.closeRecordStore();
		   } catch (RecordStoreException var5) {
			   ;
		   }
		   
		   try {
			   this.recordStore = RecordStore.openRecordStore("GoF2_REFORGED_BOOT", false);
			   byte[] var1 = this.recordStore.getRecord(this.recordStore.getNextRecordID() - 1);
			   ByteArrayInputStream var6 = new ByteArrayInputStream(var1);
			   DataInputStream var2;
			   GlobalStatus.default_language = (var2 = new DataInputStream(var6)).readInt();
			   GlobalStatus.language = GlobalStatus.default_language;
			   GlobalStatus.developer_mode = var2.readBoolean();
			   GlobalStatus.INTERFACE_SCALE_MULTIPLIER = var2.readInt();
			   var2.close();
			   var6.close();
			   this.recordStore.closeRecordStore();
			   this.recordStore = null;
			   } catch (RecordStoreException var3) {
				   ;
			   } catch (IOException var4) {
				   ;
			   }
   }
   
   public final void BOOTLOADER_RMS_WRITE() {
	   try {
		   RecordStore.deleteRecordStore("GoF2_REFORGED_BOOT");
		   } catch (RecordStoreException var6) {
			   ;
		   }
		   
		   try {
			   this.recordStore = RecordStore.openRecordStore("GoF2_REFORGED_BOOT", true);
		   } catch (RecordStoreException var5) {
			   ;
		   }
		   
		   ByteArrayOutputStream var1 = null;
		   DataOutputStream var2 = null;
		   
		   try {
			   var1 = new ByteArrayOutputStream();
			   (var2 = new DataOutputStream(var1)).writeInt(GlobalStatus.default_language);
			   var2.writeBoolean(GlobalStatus.developer_mode);
			   var2.writeInt(GlobalStatus.INTERFACE_SCALE_MULTIPLIER);
			   var2.close();
			   var1.close();
			   this.recordStore.addRecord(var1.toByteArray(), 0, var1.toByteArray().length);
			   this.recordStore.closeRecordStore();
			   this.recordStore = null;
			} catch (IOException var3) {
				;
			} catch (RecordStoreException var4) {
				var4.printStackTrace();
			}
   }

   public final void readOptions() {
      try {
         this.recordStore = RecordStore.openRecordStore("GoF2_REFORGED_Options", false);
         this.recordStore.closeRecordStore();
      } catch (RecordStoreException var5) {
         ;
      }

      try {
         this.recordStore = RecordStore.openRecordStore("GoF2_REFORGED_Options", false);
         byte[] var1 = this.recordStore.getRecord(this.recordStore.getNextRecordID() - 1);
         ByteArrayInputStream var6 = new ByteArrayInputStream(var1);
         DataInputStream var2;
         GlobalStatus.musicOn = (var2 = new DataInputStream(var6)).readBoolean();
         GlobalStatus.vibrationOn = var2.readBoolean();
         GlobalStatus.invertedControlsOn = var2.readBoolean();
		 GlobalStatus.texture_type[GlobalStatus.textures] = var2.readInt();
		 GlobalStatus.textures = var2.readInt();
		 GlobalStatus.planet_size[GlobalStatus.planets] = var2.readInt();
		 GlobalStatus.planets = var2.readInt();
		 GlobalStatus.nebula_size[GlobalStatus.nebulas] = var2.readInt();
		 GlobalStatus.nebulas = var2.readInt();
		 GlobalStatus.asteroids[GlobalStatus.asteroid] = var2.readInt();
		 GlobalStatus.asteroid = var2.readInt();
		 GlobalStatus.bigInterface = var2.readBoolean();
		 GlobalStatus.FXAA = var2.readBoolean();
		 GlobalStatus.low_details = var2.readBoolean();
         GlobalStatus.musicVolume = var2.readInt();
         GlobalStatus.sfxVolume = var2.readInt();
         GlobalStatus.sfxOn = var2.readBoolean();
		// SharedVariables.cheat_mode = var2.readBoolean();
         var2.close();
         var6.close();
         this.recordStore.closeRecordStore();
         this.recordStore = null;
      } catch (RecordStoreException var3) {
         ;
      } catch (IOException var4) {
         ;
      }
   }

   public final void writeOptions() {
      try {
         RecordStore.deleteRecordStore("GoF2_REFORGED_Options");
      } catch (RecordStoreException var6) {
         ;
      }

      try {
         this.recordStore = RecordStore.openRecordStore("GoF2_REFORGED_Options", true);
      } catch (RecordStoreException var5) {
         ;
      }

      ByteArrayOutputStream var1 = null;
      DataOutputStream var2 = null;

      try {
         var1 = new ByteArrayOutputStream();
         (var2 = new DataOutputStream(var1)).writeBoolean(GlobalStatus.musicOn);
         var2.writeBoolean(GlobalStatus.vibrationOn);
         var2.writeBoolean(GlobalStatus.invertedControlsOn);
		 var2.writeInt(GlobalStatus.texture_type[GlobalStatus.textures]);
		 var2.writeInt(GlobalStatus.textures);
		 var2.writeInt(GlobalStatus.planet_size[GlobalStatus.planets]);
		 var2.writeInt(GlobalStatus.planets);
		 var2.writeInt(GlobalStatus.nebula_size[GlobalStatus.nebulas]);
		 var2.writeInt(GlobalStatus.nebulas);
		 var2.writeInt(GlobalStatus.asteroids[GlobalStatus.asteroid]);
		 var2.writeInt(GlobalStatus.asteroid);
		 var2.writeBoolean(GlobalStatus.bigInterface);
		 var2.writeBoolean(GlobalStatus.FXAA);
		 var2.writeBoolean(GlobalStatus.low_details);
         var2.writeInt(GlobalStatus.musicVolume);
         var2.writeInt(GlobalStatus.sfxVolume);
         var2.writeBoolean(GlobalStatus.sfxOn);
		// var2.writeBoolean(SharedVariables.cheat_mode);
         var2.close();
         var1.close();
         this.recordStore.addRecord(var1.toByteArray(), 0, var1.toByteArray().length);
         this.recordStore.closeRecordStore();
         this.recordStore = null;
      } catch (IOException var3) {
         ;
      } catch (RecordStoreException var4) {
         var4.printStackTrace();
      }
   }

   public final GameRecord readRecordFromByteArray(byte[] var1) {
      ByteArrayInputStream var16 = new ByteArrayInputStream(var1);
      DataInputStream var2 = new DataInputStream(var16);
      GameRecord var3 = new GameRecord();

      try {
         int var4;
         for(var4 = 0; var4 < var3.visitedStations.length; ++var4) {
            var3.visitedStations[var4] = var2.readBoolean();
         }

         var3.credits = var2.readInt();
         var3.rating = var2.readInt();
         var3.playTime = var2.readLong();
         var3.kills = var2.readInt();
         var3.missionCount = var2.readInt();
         var3.level = var2.readInt();
         var3.lastWorth = var2.readInt();
         var3.currentCampaignMissionIndex = var2.readInt();
         var3.stationsVisited = var2.readInt();
         var3.goodsProduced = var2.readInt();
         var3.freelanceMission = this.readMission(var2);
         var3.storyMission = this.readMission(var2);
         var3.jumpGateUses = var2.readInt();
         var3.cargoSalvaged = var2.readInt();
         var3.unused__ = var2.readInt();
         var3.pirateKills = var2.readInt();
         var3.wormholeStation = var2.readInt();
         var3.wormholeSystem = var2.readInt();
         var3.lastVisitedNonVoidOrbit = var2.readInt();
         var3.wormHoleTick = var2.readInt();

         for(var4 = 0; var4 < Status.var_1053.length; ++var4) {
            var3.minedOreTypes[var4] = var2.readBoolean();
         }

         for(var4 = 0; var4 < Status.var_1071.length; ++var4) {
            var3.minedCoreTypes[var4] = var2.readBoolean();
         }

         var3.missionGoodsCarried = var2.readInt();
         var3.minedOre = var2.readInt();
         var3.minedCores = var2.readInt();
         var3.boughtBooze = var2.readInt();

         for(var4 = 0; var4 < Status.drinkTypesPossesed.length; ++var4) {
            var3.drinkTypesPossesed[var4] = var2.readBoolean();
         }

         var3.destroyedJunk = var2.readInt();

         for(var4 = 0; var4 < Status.systemsVisited.length; ++var4) {
            var3.systemsVisited[var4] = var2.readBoolean();
         }

         var3.passengersCarried = var2.readInt();
         var3.invisibleTime = var2.readLong();
         var3.bombsUsed = var2.readInt();
         var3.alienJunkSalvaged = var2.readInt();
         var3.barInteractions = var2.readInt();
         var3.commandedWingmen = var2.readInt();
         var2.readInt();
         var3.asteroidsDestroyed = var2.readInt();
         var3.maxFreeCargo = var2.readInt();
         var3.missionsRejected = var2.readInt();
         var3.askedToRepeate = var2.readInt();
         var3.acceptedNotAskingDifficulty = var2.readInt();
         var3.acceptedNotAskingLocation = var2.readInt();
         var4 = var2.readInt();
         var3.achievements = new int[var4];

         for(var4 = 0; var4 < var3.achievements.length; ++var4) {
            var3.achievements[var4] = var2.readInt();
         }

         var3.egoShip = Globals.getShips()[var2.readInt()].cloneBase();
         var3.egoShip.setRace(var2.readInt());
         var4 = var2.readInt();
         Item[] var5 = null;
         int var6;
         int var7;
         int var8;
         if(var4 > 0) {
            var5 = new Item[var4];

            for(var6 = 0; var6 < var4; ++var6) {
               if((var7 = var2.readInt()) == -1) {
                  var5[var6] = null;
               } else {
                  var8 = var2.readInt();
                  var5[var6] = Globals.getItems()[var7].getCopyInAmmount(var8, Globals.getItems()[var7].getMaxPrice());
                  var5[var6].setUnsaleable(var2.readBoolean());
               }
            }

            var3.egoShip.replaceEquipment(var5);
         }

         var6 = var2.readInt();
         Item[] var19 = null;
         int var9;
         int var17;
         if(var6 > 0) {
            var19 = new Item[var6];

            for(var8 = 0; var8 < var6; ++var8) {
               var4 = var2.readInt();
               var17 = var2.readInt();
               var9 = var2.readInt();
               boolean var10 = var2.readBoolean();
               var19[var8] = Globals.getItems()[var4].getCopyInAmmount(var17, Globals.getItems()[var4].getMaxPrice());
               var19[var8].setPrice(var9);
               var19[var8].setUnsaleable(var10);
            }

            var3.egoShip.refreshCargoSpaceUnsafe(var19);
         }

         Station[] var22 = new Station[3];

         int var11;
         int var12;
         for(var4 = 0; var4 < var22.length + 1; ++var4) {
            var5 = null;
            Station var18;
            short var21;
            if((var21 = var2.readShort()) == -1) {
               var18 = null;
            } else {
               var18 = Galaxy.getStation(var21);
               var6 = var2.readInt();
               Item[] var29 = null;
               if(var6 > 0) {
                  var29 = new Item[var6];

                  for(var7 = 0; var7 < var6; ++var7) {
                     var9 = var2.readInt();
                     var11 = var2.readInt();
                     var12 = var2.readInt();
                     boolean var13 = var2.readBoolean();
                     var29[var7] = Globals.getItems()[var9].getCopyInAmmount(var11, Globals.getItems()[var9].getMaxPrice());
                     var29[var7].setPrice(var12);
                     var29[var7].setUnsaleable(var13);
                  }

                  var18.sub_37b(var29);
               }

               var7 = var2.readInt();
               Ship[] var23 = null;
               if(var7 > 0) {
                  var23 = new Ship[var7];

                  for(var11 = 0; var11 < var7; ++var11) {
                     var12 = var2.readInt();
                     var23[var11] = Globals.getShips()[var12].cloneBase();
                     var23[var11].setRace(var2.readInt());
                  }

                  var18.sub_395(var23);
               }

               var11 = var2.readInt();
               Agent[] var30 = null;
               if(var11 > 0) {
                  var30 = new Agent[var11];

                  for(int var33 = 0; var33 < var11; ++var33) {
                     var30[var33] = this.readAgent(var2);
                  }

                  var18.sub_3f6(var30);
               }
            }

            if(var4 == var22.length) {
               var3.currentStation = var18;
            } else {
               var22[var4] = var18;
            }
         }

         var3.lastVisitedStations = var22;
         int[] var24 = Status.getStanding().getStanding();

         for(var17 = 0; var17 < var24.length; ++var17) {
            var24[var17] = var2.readInt();
         }

         var2.readInt();
         var2.readInt();
         var3.standing = new Standing();
         var3.standing.setStanding(var24);
         BluePrint[] var20 = new BluePrint[Status.getBluePrints().length];

         for(var9 = 0; var9 < var20.length; ++var9) {
            var20[var9] = new BluePrint(Status.getBluePrints()[var9].getIndex());
            BluePrint var26 = var20[var9];

            for(var7 = 0; var7 < var26.missingComponentsTons.length; ++var7) {
               var26.missingComponentsTons[var7] = var2.readInt();
            }

            var26.investedCredits = var2.readInt();
            var26.unlocked = var2.readBoolean();
            var26.timesProduced = var2.readInt();
            var26.productionStationId = var2.readInt();
            String var25;
            if((var25 = var2.readUTF()).equals("")) {
               var25 = null;
            }

            var26.productionStationName = var25;
         }

         var3.blueprints = var20;
         if((var9 = var2.readInt()) > 0) {
            ProducedGood[] var27 = new ProducedGood[var9];

            for(var7 = 0; var7 < var27.length; ++var7) {
               var9 = var2.readInt();
               var11 = var2.readInt();
               var12 = var2.readInt();
               String var32;
               if((var32 = var2.readUTF()).equals("")) {
                  var32 = null;
               }

               var27[var7] = new ProducedGood(var9, var32, var12, var11);
            }

            var3.waitingGoods = var27;
         } else {
            var3.waitingGoods = null;
         }

         int var31;
         if((var31 = var2.readInt()) > 0) {
            String[] var28 = new String[var31];

            for(var9 = 0; var9 < var31; ++var9) {
               var28[var9] = var2.readUTF();
            }

            var3.wingmenNames = var28;
            var3.wingmanRace = var2.readInt();
            var3.wingmenTimeRemaining = var2.readInt();
            var9 = var2.readInt();
            var3.wingmanFace = new byte[var9];

            for(var11 = 0; var11 < var3.wingmanFace.length; ++var11) {
               var3.wingmanFace[var11] = var2.readByte();
            }
         } else {
            var3.wingmenNames = null;
         }

         var3.passengerCount = var2.readInt();
         var3.visibleSystems = new boolean[Status.visibleSystems.length];

         for(var7 = 0; var7 < Status.visibleSystems.length; ++var7) {
            var3.visibleSystems[var7] = var2.readBoolean();
         }

         var3.highestItemPrices = new int[Status.var_cff.length];

         for(var7 = 0; var7 < Status.var_cff.length; ++var7) {
            var3.highestItemPrices[var7] = var2.readInt();
         }

         var3.lowestItemPrices = new int[Status.var_cca.length];

         for(var7 = 0; var7 < Status.var_cca.length; ++var7) {
            var3.lowestItemPrices[var7] = var2.readInt();
         }

         var3.highestItemPriceSystems = new byte[Status.var_d2a.length];

         for(var7 = 0; var7 < Status.var_d2a.length; ++var7) {
            var3.highestItemPriceSystems[var7] = var2.readByte();
         }

         var3.lowestItemPriceSystems = new byte[Status.var_d11.length];

         for(var7 = 0; var7 < Status.var_d11.length; ++var7) {
            var3.lowestItemPriceSystems[var7] = var2.readByte();
         }

         var3.specialAgents = new Agent[Status.getSpecialAgents().length];

         for(var7 = 0; var7 < var3.specialAgents.length; ++var7) {
            var3.specialAgents[var7] = this.readAgent(var2);
         }

         var3.shopHelpShown = var2.readBoolean();
         var3.shipHelpShown = var2.readBoolean();
         var3.actionsHelpShown = var2.readBoolean();
         var3.bluePrintsHelpShown = var2.readBoolean();
         var3.bluePrintInfoHelpShown = var2.readBoolean();
         var3.barHelpShown = var2.readBoolean();
         var3.galaxyMapHelpShown = var2.readBoolean();
         var3.systemMapHelpShown = var2.readBoolean();
         var3.unused12121_ = var2.readBoolean();
         var3.miningHelpShown = var2.readBoolean();
         var3.asteroidHelpShown = var2.readBoolean();
         var3.missionsHelpShown = var2.readBoolean();
         var3.cargoFullHelpShown = var2.readBoolean();
         var3.wingmenHelpShown = var2.readBoolean();
         var3.actionMenuHelpShown = var2.readBoolean();
         var3.boosterHelpShown = var2.readBoolean();
         var3.statusHelpShown = var2.readBoolean();
         var3.medalsHelpShown = var2.readBoolean();
         var3.fanWingmenNoticeShown = var2.readBoolean();
         var3.voidxNoticeShown = var2.readBoolean();
         var3.reputationHelpShown = var2.readBoolean();
         var3.buyingHelpShown = var2.readBoolean();
         var3.itemMountingHelpShown = var2.readBoolean();
         var3.itemMounting2HelpShown = var2.readBoolean();
         var3.interplanetHelpShown = var2.readBoolean();
         var3.loadingsCount = var2.readLong();
         var3.timeSpentLoading = var2.readLong();

         try {
            var3.jumpDriveHelpShown = var2.readBoolean();
            var3.cloakHelpShown = var2.readBoolean();
         } catch (Exception var14) {
            ;
         }

         var2.close();
         var16.close();
      } catch (Exception var15) {
         System.err.println("Error in readRecordFromByteArray!");
      }

      return var3;
   }

   private GameRecord recordStoreRead(int var1) {
      this.agent = null;
      this.mission = null;
      GameRecord var2 = null;

      try {
         this.recordStore = RecordStore.openRecordStore("GoF2_REFORGED_" + var1, false);
         this.recordStore.closeRecordStore();
      } catch (RecordStoreException var4) {
         ;
      }

      try {
         this.recordStore = RecordStore.openRecordStore("GoF2_REFORGED_" + var1, false);
         byte[] var5 = this.recordStore.getRecord(this.recordStore.getNextRecordID() - 1);
         var2 = this.readRecordFromByteArray(var5);
         this.recordStore.closeRecordStore();
         this.recordStore = null;
      } catch (Exception var3) {
         var2 = null;
      }

      return var2;
   }

   public final void saveGame(int var1) {
      this.agent = null;
      this.mission = null;

      try {
         RecordStore.deleteRecordStore("GoF2_REFORGED_" + var1);
      } catch (RecordStoreException var10) {
         ;
      }

      try {
         this.recordStore = RecordStore.openRecordStore("GoF2_REFORGED_" + var1, true);
      } catch (RecordStoreException var9) {
         ;
      }

      try {
         byte[] var2 = this.recordStoreToByteArray();
         this.recordStore.addRecord(var2, 0, var2.length);
         this.recordStore.closeRecordStore();
         this.recordStore = null;
         int var12 = var1;
         RecordHandler var11 = this;

         try {
            RecordStore.deleteRecordStore("GoF2_REFORGED_Preview_" + var12);
         } catch (RecordStoreException var7) {
            ;
         }

         try {
            var11.recordStore = RecordStore.openRecordStore("GoF2_REFORGED_Preview_" + var12, true);
         } catch (RecordStoreException var6) {
            ;
         }

         ByteArrayOutputStream var3 = null;
         DataOutputStream var13 = null;

         try {
            var3 = new ByteArrayOutputStream();
            (var13 = new DataOutputStream(var3)).writeLong(Status.getPlayingTime());
            var13.writeInt(Status.getCredits());
            var13.writeUTF(Status.getStation().getName());
            var13.writeUTF(Status.getSystem().getName());
            var13.writeInt(Status.getCurrentCampaignMission());
            var13.close();
            var3.close();
            var11.recordStore.addRecord(var3.toByteArray(), 0, var3.toByteArray().length);
            var11.recordStore.closeRecordStore();
            var11.recordStore = null;
         } catch (IOException var4) {
            ;
         } catch (RecordStoreException var5) {
            return;
         }
      } catch (Exception var8) {
         ;
      }

   }

   public final byte[] recordStoreToByteArray() {
      ByteArrayOutputStream var1 = null;
      DataOutputStream var2 = null;

      try {
         var1 = new ByteArrayOutputStream();
         var2 = new DataOutputStream(var1);
         boolean[] var3 = Galaxy.getVisitedStations();

         int var4;
         for(var4 = 0; var4 < var3.length; ++var4) {
            var2.writeBoolean(var3[var4]);
         }

         var2.writeInt(Status.getCredits());
         var2.writeInt(Status.getRating());
         var2.writeLong(Status.getPlayingTime());
         var2.writeInt(Status.getKills());
         var2.writeInt(Status.getMissionCount());
         var2.writeInt(Status.getLevel());
         var2.writeInt(Status.getLastXP());
         var2.writeInt(Status.getGoodsProduced());
         var2.writeInt(Status.getStationsVisited());
         var2.writeInt(Status.getCurrentCampaignMission());
         this.writeMission(var2, Status.getFreelanceMission());
         this.writeMission(var2, Status.getCampaignMission());
         var2.writeInt(Status.getJumpgateUsed());
         var2.writeInt(Status.getCargoSalvaged());
         var2.writeInt(Status.setUnusedVar());
         var2.writeInt(Status.getPirateKills());
         var2.writeInt(Status.wormholeStation);
         var2.writeInt(Status.wormholeSystem);
         var2.writeInt(Status.lastVisitedNonVoidOrbit);
         var2.writeInt(Status.wormHoleTick);

         for(var4 = 0; var4 < Status.var_1053.length; ++var4) {
            var2.writeBoolean(Status.var_1053[var4]);
         }

         for(var4 = 0; var4 < Status.var_1071.length; ++var4) {
            var2.writeBoolean(Status.var_1071[var4]);
         }

         var2.writeInt(Status.missionGoodsCarried);
         var2.writeInt(Status.var_10ba);
         var2.writeInt(Status.var_1106);
         var2.writeInt(Status.boughtBooze);

         for(var4 = 0; var4 < Status.drinkTypesPossesed.length; ++var4) {
            var2.writeBoolean(Status.drinkTypesPossesed[var4]);
         }

         var2.writeInt(Status.destroyedJunk);

         for(var4 = 0; var4 < Status.systemsVisited.length; ++var4) {
            var2.writeBoolean(Status.systemsVisited[var4]);
         }

         var2.writeInt(Status.passengersCarried);
         var2.writeLong(Status.invisibleTime);
         var2.writeInt(Status.bombsUsed);
         var2.writeInt(Status.alienJunkSalvaged);
         var2.writeInt(Status.var_134b);
         var2.writeInt(Status.var_1381);
         var2.writeInt(1);
         var2.writeInt(Status.asteroidsDestroyed);
         var2.writeInt(Status.maxFreeCargo);
         var2.writeInt(Status.missionsRejected);
         var2.writeInt(Status.askedToRepeate);
         var2.writeInt(Status.acceptedNotAskingDifficulty);
         var2.writeInt(Status.var_149c);
         int[] var15 = Achievements.getMedals();
         var2.writeInt(var15.length);

         int var9;
         for(var9 = 0; var9 < var15.length; ++var9) {
            var2.writeInt(var15[var9]);
         }

         var2.writeInt(Status.getShip().getIndex());
         var2.writeInt(Status.getShip().sub_3e());
         Item[] var10;
         if((var10 = Status.getShip().getEquipment()) == null) {
            var2.writeInt(0);
         } else {
            var2.writeInt(var10.length);

            for(var4 = 0; var4 < var10.length; ++var4) {
               if(var10[var4] != null) {
                  var2.writeInt(var10[var4].getIndex());
                  var2.writeInt(var10[var4].getAmount());
                  var2.writeBoolean(var10[var4].setUnsaleable());
               } else {
                  var2.writeInt(-1);
               }
            }
         }

         Item[] var18;
         if((var18 = Status.getShip().getCargo()) == null) {
            var2.writeInt(0);
         } else {
            var2.writeInt(var18.length);

            for(var9 = 0; var9 < var18.length; ++var9) {
               var2.writeInt(var18[var9].getIndex());
               var2.writeInt(var18[var9].getAmount());
               var2.writeInt(var18[var9].getSinglePrice());
               var2.writeBoolean(var18[var9].setUnsaleable());
            }
         }

         int var6;
         int var7;
         Agent[] var20;
         for(var9 = 0; var9 < Status.getLastVisitedStations().length + 1; ++var9) {
            Station var5 = null;
            if(var9 == Status.getLastVisitedStations().length) {
               var5 = Status.getStation();
            } else {
               var5 = Status.getLastVisitedStations()[var9];
            }

            if(var5 == null) {
               var2.writeShort(-1);
            } else {
               var2.writeShort(var5.getId());
               if((var18 = var5.sub_360()) == null) {
                  var2.writeInt(0);
               } else {
                  var2.writeInt(var18.length);

                  for(var6 = 0; var6 < var18.length; ++var6) {
                     var2.writeInt(var18[var6].getIndex());
                     var2.writeInt(var18[var6].getAmount());
                     var2.writeInt(var18[var6].getSinglePrice());
                     var2.writeBoolean(var18[var6].setUnsaleable());
                  }
               }

               Ship[] var12;
               if((var12 = var5.sub_2e3()) == null) {
                  var2.writeInt(0);
               } else {
                  var2.writeInt(var12.length);

                  for(var4 = 0; var4 < var12.length; ++var4) {
                     var2.writeInt(var12[var4].getIndex());
                     var2.writeInt(var12[var4].sub_3e());
                  }
               }

               if((var20 = var5.sub_41e()) == null) {
                  var2.writeInt(0);
               } else {
                  var2.writeInt(var20.length);

                  for(var7 = 0; var7 < var20.length; ++var7) {
                     Agent var14 = var20[var7];
                     this.writeAgent(var2, var14);
                  }
               }
            }
         }

         int[] var11 = Status.getStanding().getStanding();

         for(int var13 = 0; var13 < var11.length; ++var13) {
            var2.writeInt(var11[var13]);
         }

         var2.writeInt(1);
         var2.writeInt(1);
         BluePrint[] var16 = Status.getBluePrints();

         for(var6 = 0; var6 < var16.length; ++var6) {
            BluePrint var19 = var16[var6];

            for(var7 = 0; var7 < var19.missingComponentsTons.length; ++var7) {
               var2.writeInt(var19.missingComponentsTons[var7]);
            }

            var2.writeInt(var19.investedCredits);
            var2.writeBoolean(var19.unlocked);
            var2.writeInt(var19.timesProduced);
            var2.writeInt(var19.productionStationId);
            if(var19.productionStationName == null) {
               var2.writeUTF("");
            } else {
               var2.writeUTF(var19.productionStationName);
            }
         }

         ProducedGood[] var17;
         if((var17 = Status.getWaitingGoods()) == null) {
            var2.writeInt(-1);
         } else {
            var4 = 0;

            for(var7 = 0; var7 < var17.length; ++var7) {
               if(var17[var7] != null) {
                  ++var4;
               }
            }

            if(var4 == 0) {
               var2.writeInt(-1);
            } else {
               var2.writeInt(var4);

               for(var7 = 0; var7 < var17.length; ++var7) {
                  if(var17[var7] != null) {
                     var2.writeInt(var17[var7].index);
                     var2.writeInt(var17[var7].producedQuantity);
                     var2.writeInt(var17[var7].stationId);
                     if(var17[var7].stationName == null) {
                        var2.writeUTF("");
                     } else {
                        var2.writeUTF(var17[var7].stationName);
                     }
                  }
               }
            }
         }

         if(Status.var_b3a == null) {
            var2.writeInt(-1);
         } else {
            var2.writeInt(Status.var_b3a.length);

            for(var4 = 0; var4 < Status.var_b3a.length; ++var4) {
               var2.writeUTF(Status.var_b3a[var4]);
            }

            var2.writeInt(Status.wingmanRace);
            var2.writeInt(Status.var_bea);
            var2.writeInt(Status.wingmanFace.length);

            for(var4 = 0; var4 < Status.wingmanFace.length; ++var4) {
               var2.writeByte(Status.wingmanFace[var4]);
            }
         }

         var2.writeInt(Status.passengerCount);

         for(var4 = 0; var4 < Status.visibleSystems.length; ++var4) {
            var2.writeBoolean(Status.visibleSystems[var4]);
         }

         for(var4 = 0; var4 < Status.var_cff.length; ++var4) {
            var2.writeInt(Status.var_cff[var4]);
         }

         for(var4 = 0; var4 < Status.var_cca.length; ++var4) {
            var2.writeInt(Status.var_cca[var4]);
         }

         for(var4 = 0; var4 < Status.var_d2a.length; ++var4) {
            var2.writeByte(Status.var_d2a[var4]);
         }

         for(var4 = 0; var4 < Status.var_d11.length; ++var4) {
            var2.writeByte(Status.var_d11[var4]);
         }

         var20 = Status.getSpecialAgents();

         for(var7 = 0; var7 < var20.length; ++var7) {
            this.writeAgent(var2, var20[var7]);
         }

         var2.writeBoolean(GlobalStatus.shopHelpShown);
         var2.writeBoolean(GlobalStatus.shipHelpShown);
         var2.writeBoolean(GlobalStatus.actionsHelpShown);
         var2.writeBoolean(GlobalStatus.bluePrintsHelpShown);
         var2.writeBoolean(GlobalStatus.bluePrintInfoHelpShown);
         var2.writeBoolean(GlobalStatus.barHelpShown);
         var2.writeBoolean(GlobalStatus.galaxyMapHelpShown);
         var2.writeBoolean(GlobalStatus.systemMapHelpShown);
         var2.writeBoolean(GlobalStatus.somehelpShown_unused_);
         var2.writeBoolean(GlobalStatus.miningHelpShown);
         var2.writeBoolean(GlobalStatus.asteroidHelpShown);
         var2.writeBoolean(GlobalStatus.missionsHelpShown);
         var2.writeBoolean(GlobalStatus.cargoFullHelpShown);
         var2.writeBoolean(GlobalStatus.wingmenHelpShown);
         var2.writeBoolean(GlobalStatus.actionMenuHelpShown);
         var2.writeBoolean(GlobalStatus.boosterHelpShown);
         var2.writeBoolean(GlobalStatus.statusHelpShown);
         var2.writeBoolean(GlobalStatus.medalsHelpShown);
         var2.writeBoolean(GlobalStatus.fanWingmenNoticeShown);
         var2.writeBoolean(GlobalStatus.voidxNoticeShown);
         var2.writeBoolean(GlobalStatus.reputationHelpShown);
         var2.writeBoolean(GlobalStatus.buyingHelpShown);
         var2.writeBoolean(GlobalStatus.itemMountingHelpShown);
         var2.writeBoolean(GlobalStatus.itemMounting2HelpShown);
         var2.writeBoolean(GlobalStatus.interplanetHelpShown);
         var2.writeLong(Status.var_1016);
         var2.writeLong(Status.loadingTime);
         var2.writeBoolean(GlobalStatus.jumpDriveHelpShown);
         var2.writeBoolean(GlobalStatus.cloakHelpShown);
         var2.close();
         var1.close();
      } catch (Exception var8) {
         var8.printStackTrace();
      }

      return var1.toByteArray();
   }

   private void writeAgent(DataOutputStream var1, Agent var2) throws IOException {
      var1.writeInt(var2.getCosts());
      var1.writeInt(var2.getSellSystemIndex());
      var1.writeInt(var2.getSellBlueprintIndex());
      var1.writeInt(var2.getEvent());
      var1.writeInt(var2.getMessageId());
      var1.writeInt(var2.sub_8f());
      var1.writeInt(var2.sub_166());
      var1.writeInt(var2.getSellItemIndex());
      var1.writeInt(var2.getSellItemPrice());
      var1.writeInt(var2.getSellItemQuantity());
      var1.writeInt(var2.getStationId());
      var1.writeInt(var2.getSystemId());
      var1.writeInt(var2.wingmanFriendsCount);
      var1.writeBoolean(var2.isMale());
      var1.writeBoolean(var2.getUnused0_());
      var1.writeBoolean(var2.isAccepted());
      var1.writeBoolean(var2.wasAskedForDifficulty);
      var1.writeBoolean(var2.wasAskedForLocation);
      if(var2.getFace() != null) {
         var1.writeInt(var2.getFace().length);

         for(int var3 = 0; var3 < var2.getFace().length; ++var3) {
            var1.writeByte(var2.getFace()[var3]);
         }
      } else {
         var1.writeInt(-1);
      }

      if(var2.getMessage() == null) {
         var1.writeUTF("");
      } else {
         var1.writeUTF(var2.getMessage());
      }

      if(var2.var_4f == null) {
         var1.writeUTF("");
      } else {
         var1.writeUTF(var2.var_4f);
      }

      if(var2.getStationName() == null) {
         var1.writeUTF("");
      } else {
         var1.writeUTF(var2.getStationName());
      }

      if(var2.var_875 == null) {
         var1.writeUTF("");
      } else {
         var1.writeUTF(var2.var_875);
      }

      if(var2.wingman1Name == null) {
         var1.writeUTF("");
      } else {
         var1.writeUTF(var2.wingman1Name);
      }

      if(var2.wingman2Name == null) {
         var1.writeUTF("");
      } else {
         var1.writeUTF(var2.wingman2Name);
      }

      this.agent = var2;
      if(var2.sub_3d9() != null && this.mission != var2.sub_3d9()) {
         var1.writeInt(1);
         this.writeMission(var1, var2.sub_3d9());
      } else {
         var1.writeInt(-1);
      }
   }

   private Agent readAgent(DataInputStream var1) throws IOException {
      int var2 = var1.readInt();
      int var3 = var1.readInt();
      int var4 = var1.readInt();
      int var5 = var1.readInt();
      int var6 = var1.readInt();
      int var7 = var1.readInt();
      int var8 = var1.readInt();
      int var9 = var1.readInt();
      int var10 = var1.readInt();
      int var11 = var1.readInt();
      int var12 = var1.readInt();
      int var13 = var1.readInt();
      int var14 = var1.readInt();
      boolean var15 = var1.readBoolean();
      boolean var16 = var1.readBoolean();
      boolean var17 = var1.readBoolean();
      boolean var18 = var1.readBoolean();
      boolean var19 = var1.readBoolean();
      int var20 = var1.readInt();
      byte[] var21 = null;
      if(var20 > 0) {
         var21 = new byte[var20];

         for(var20 = 0; var20 < var21.length; ++var20) {
            var21[var20] = var1.readByte();
         }
      }

      String var28 = var1.readUTF();
      String var22 = var1.readUTF();
      String var23 = var1.readUTF();
      String var24 = var1.readUTF();
      String var25 = var1.readUTF();
      String var26 = var1.readUTF();
      if(var25.equals("")) {
         var25 = null;
      }

      if(var26.equals("")) {
         var26 = null;
      }

      Mission var27 = null;
      if(var1.readInt() > 0) {
         var27 = this.readMission(var1);
      }

      Agent var29;
      (var29 = new Agent(var6, var22, var12, var13, var8, var15, var3, var4, var10)).setCosts(var2);
      var29.setEvent(var5);
      var29.setType(var7);
      var29.setSellItem(var9, var11, var10);
      var29.wingman1Name = var25;
      var29.wingman2Name = var26;
      var29.wingmanFriendsCount = var14;
      var29.setUnused0_(var16);
      var29.sub_789(var17);
      var29.setImageParts(var21);
      var29.setMessage(var28);
      var29.setAgentsStationName(var23);
      var29.setAgentsSystemName(var24);
      var29.sub_3c9(var27);
      var29.wasAskedForDifficulty = var18;
      var29.wasAskedForLocation = var19;
      return var29;
   }

   private void writeMission(DataOutputStream var1, Mission var2) throws IOException {
      var1.writeInt(var2.getType());
      if(!var2.isEmpty()) {
         if(var2.getClientName() == null) {
            var1.writeUTF("");
         } else {
            var1.writeUTF(var2.getClientName());
         }

         if(var2.getTargetName() == null) {
            var1.writeUTF("");
         } else {
            var1.writeUTF(var2.getTargetName());
         }

         if(var2.getTargetStationName() == null) {
            var1.writeUTF("");
         } else {
            var1.writeUTF(var2.getTargetStationName());
         }

         var1.writeBoolean(var2.isCampaignMission());
         if(var2.getClientImage() != null) {
            var1.writeInt(var2.getClientImage().length);

            for(int var3 = 0; var3 < var2.getClientImage().length; ++var3) {
               var1.writeByte(var2.getClientImage()[var3]);
            }
         } else {
            var1.writeInt(-1);
         }

         var1.writeInt(var2.getClientRace());
         var1.writeInt(var2.getBonus());
         var1.writeInt(var2.getReward());
         var1.writeInt(var2.getTargetStation());
         var1.writeInt(var2.getDifficulty());
         var1.writeInt(var2.getCommodityIndex());
         var1.writeInt(var2.getCommodityAmount_());
         var1.writeInt(var2.getStatusValue_());
         var1.writeBoolean(var2.isVisible());
         this.mission = var2;
         if(var2.getAgent() != null && this.agent != var2.getAgent()) {
            var1.writeInt(1);
            this.writeAgent(var1, var2.getAgent());
         } else {
            var1.writeInt(-1);
         }
      }
   }

   private Mission readMission(DataInputStream var1) throws IOException {
      int var2;
      if((var2 = var1.readInt()) == -1) {
         return Mission.emptyMission_;
      } else {
         String var3;
         if((var3 = var1.readUTF()).equals("")) {
            var3 = null;
         }

         String var4;
         if((var4 = var1.readUTF()).equals("")) {
            var4 = null;
         }

         var1.readUTF().equals("");
         boolean var5 = var1.readBoolean();
         byte[] var6 = null;
         int var7;
         int var8;
         if((var7 = var1.readInt()) > 0) {
            var6 = new byte[var7];

            for(var8 = 0; var8 < var7; ++var8) {
               var6[var8] = var1.readByte();
            }
         }

         var8 = var1.readInt();
         var7 = var1.readInt();
         int var9 = var1.readInt();
         int var10 = var1.readInt();
         int var11 = var1.readInt();
         int var12 = var1.readInt();
         int var13 = var1.readInt();
         int var14 = var1.readInt();
         boolean var15 = var1.readBoolean();
         int var16 = var1.readInt();
         Agent var17 = null;
         if(var16 > 0) {
            var17 = this.readAgent(var1);
         }

         var1 = null;
         Mission var18;
         if(var5) {
            var18 = new Mission(var2, var9, var10);
         } else {
            var18 = new Mission(var2, var3, var6, var8, var9, var10, var11);
         }

         var18.setBonus(var7);
         var18.setCommodity(var12, var13);
         var18.setTasksTreshold_(var14);
         var18.setVisible(var15);
         var18.setAgent(var17);
         var18.setTargetName(var4);
         return var18;
      }
   }

   public final GameRecord readPreview(int var1) {
      GameRecord var2 = null;

      try {
         this.recordStore = RecordStore.openRecordStore("GoF2_REFORGED_Preview_" + var1, false);
         this.recordStore.closeRecordStore();
      } catch (RecordStoreException var5) {
         ;
      }

      try {
         this.recordStore = RecordStore.openRecordStore("GoF2_REFORGED_Preview_" + var1, false);
         byte[] var7 = this.recordStore.getRecord(this.recordStore.getNextRecordID() - 1);
         ByteArrayInputStream var6 = new ByteArrayInputStream(var7);
         DataInputStream var3 = new DataInputStream(var6);
         (var2 = new GameRecord()).playTime = var3.readLong();
         var2.credits = var3.readInt();
         var2.stationName = var3.readUTF();
         var3.readUTF();
         var2.goodsProduced = var3.readInt();
         var3.close();
         var6.close();
         this.recordStore.closeRecordStore();
         this.recordStore = null;
      } catch (Exception var4) {
         var2 = null;
      }

      return var2;
   }

   public final byte[] sub_31d() {
      byte[] var1 = null;
      int[] var2;
      if((var2 = this.sub_3dc(0)) != null) {
         var1 = new byte[var2.length];

         for(int var3 = 0; var3 < var1.length; ++var3) {
            var1[var3] = (byte)var2[var3];
         }
      }

      return var1;
   }

   public final String sub_37c(int var1) {
      String var2 = null;

      try {
         this.recordStore = RecordStore.openRecordStore("GoF2_REFORGED_C" + var1, false);
         this.recordStore.closeRecordStore();
      } catch (RecordStoreException var5) {
         ;
      }

      try {
         this.recordStore = RecordStore.openRecordStore("GoF2_REFORGED_C" + var1, false);
         byte[] var7 = this.recordStore.getRecord(this.recordStore.getNextRecordID() - 1);
         ByteArrayInputStream var6 = new ByteArrayInputStream(var7);
         DataInputStream var3;
         var2 = (var3 = new DataInputStream(var6)).readUTF();
         var3.close();
         var6.close();
         this.recordStore.closeRecordStore();
         this.recordStore = null;
      } catch (Exception var4) {
         var2 = null;
      }

      return var2;
   }

   public final int[] sub_3dc(int var1) {
      Object var2 = null;

      try {
         this.recordStore = RecordStore.openRecordStore("GoF2_REFORGED_C" + var1, false);
         this.recordStore.closeRecordStore();
      } catch (RecordStoreException var5) {
         ;
      }

      int[] var7;
      try {
         this.recordStore = RecordStore.openRecordStore("GoF2_REFORGED_C" + var1, false);
         byte[] var8 = this.recordStore.getRecord(this.recordStore.getNextRecordID() - 1);
         ByteArrayInputStream var3 = new ByteArrayInputStream(var8);
         DataInputStream var4 = new DataInputStream(var3);
         if(var1 == 0) {
            var7 = new int[16];

            for(var1 = 0; var1 < var7.length; ++var1) {
               var7[var1] = var4.readInt();
            }
         } else {
            (var7 = new int[1])[0] = var4.readInt();
         }

         var4.close();
         var3.close();
         this.recordStore.closeRecordStore();
         this.recordStore = null;
      } catch (Exception var6) {
         var7 = null;
      }

      return var7;
   }

   public final void sub_406(int var1, byte[] var2, String var3, int var4) {
      try {
         RecordStore.deleteRecordStore("GoF2_REFORGED_C" + var4);
      } catch (RecordStoreException var8) {
         ;
      }

      try {
         this.recordStore = RecordStore.openRecordStore("GoF2_REFORGED_C" + var4, true);
      } catch (RecordStoreException var7) {
         ;
      }

      ByteArrayOutputStream var5 = null;
      DataOutputStream var6 = null;

      try {
         var5 = new ByteArrayOutputStream();
         var6 = new DataOutputStream(var5);
         if((var4 == 4 || var4 == 3 || var4 == 7) && var3 != null) {
            var6.writeUTF(var3);
         } else if(var4 == 0 && var2 != null) {
            for(var1 = 0; var1 < var2.length; ++var1) {
               var6.writeInt(var2[var1]);
            }
         } else {
            var6.writeInt(var1);
         }

         var6.close();
         var5.close();
         this.recordStore.addRecord(var5.toByteArray(), 0, var5.toByteArray().length);
         this.recordStore.closeRecordStore();
         this.recordStore = null;
      } catch (IOException var9) {
         ;
      } catch (RecordStoreException var10) {
         ;
      }
   }

   public final boolean sub_45d(int var1) {
      return this.sub_3dc(var1) != null;
   }
}
