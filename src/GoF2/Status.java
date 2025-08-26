package GoF2;

import java.util.Vector;

import AE.GlobalStatus;
import AE.Math.AEMath;

public final class Status {

   private static int credits;
   private static int rating;
   private static long playTime;
   private static int kills;
   private static int missionsCount;
   private static int level = 1;
   private static int lastXP;
   private static int stationsVisited;
   private static int bluePrintsProduced;
   private static int pirateKills;
   private static int jumpgatesUsed;
   private static int cargoSalvaged;
   private static int unused_;
   private static int currentCampaignMissionIndex;
   public static Standing standing;
   private static Ship playersShip;
   private static Mission mission;
   private static Mission[] currentMissions = new Mission[2];
   private static Station currentStation;
   private static Station[] station_class_array = new Station[3];
   public static BluePrint[] var_8eb;
   public static SolarSystem currentSolarSystem;
   private static String[] var_993;
   private static int[] var_a58;
   public static ProducedGood[] waitingGoods;
   public static Agent[] specialAgents;
   public static String[] var_b3a;
   public static byte[] wingmanFace;
   public static int wingmanRace;
   public static int var_bea;
   public static int passengerCount;
   public static boolean[] visibleSystems = new boolean[GlobalStatus.max_systems];
   public static int[] var_cca;
   public static int[] var_cff;
   public static byte[] var_d11;
   public static byte[] var_d2a;
   public static int shield;
   public static int additionalArmour;
   public static int baseArmour;
   public static long var_e01;
   public static Station station_class = new Station();
   public static int wormholeSystem;
   public static int wormholeStation;
   public static int lastVisitedNonVoidOrbit;
   public static int wormHoleTick;
   public static long loadingTime;
   public static long var_1016;
   public static boolean[] var_1053 = new boolean[11];
   public static boolean[] var_1071 = new boolean[11];
   public static int missionGoodsCarried;
   public static int var_10ba;
   public static int var_1106;
   public static int boughtBooze;
   public static boolean[] drinkTypesPossesed = new boolean[22];
   public static int destroyedJunk;
   public static boolean[] systemsVisited = new boolean[GlobalStatus.max_systems];
   public static int passengersCarried;
   public static long invisibleTime;
   public static int bombsUsed;
   public static int alienJunkSalvaged;
   public static int var_134b;
   public static int var_1381;
   public static int asteroidsDestroyed;
   public static int maxFreeCargo;
   public static int missionsRejected;
   public static int askedToRepeate;
   public static int acceptedNotAskingDifficulty;
   public static int var_149c;
   public static int[] var_14c0 = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};


   public static void appendProduced(BluePrint var0) {
      int var1 = 0;
      if(waitingGoods == null) {
         waitingGoods = new ProducedGood[1];
      } else {
         int var2;
         for(var2 = 0; var2 < waitingGoods.length; ++var2) {
            if(waitingGoods[var2] != null && waitingGoods[var2].index == var0.getIndex() && waitingGoods[var2].stationId == var0.productionStationId) {
               waitingGoods[var2].producedQuantity += var0.getTonsPerProduction2();
               return;
            }
         }

         for(var2 = 0; var2 < waitingGoods.length; ++var2) {
            if(waitingGoods[var2] == null) {
               waitingGoods[var2] = new ProducedGood(var0);
               return;
            }
         }

         ProducedGood[] var4 = new ProducedGood[waitingGoods.length + 1];

         for(int var3 = 0; var3 < waitingGoods.length; ++var3) {
            var4[var1++] = waitingGoods[var3];
         }

         waitingGoods = null;
         waitingGoods = var4;
      }

      waitingGoods[var1] = new ProducedGood(var0);
   }

   public static ProducedGood[] getWaitingGoods() {
      return waitingGoods;
   }

   public static boolean[] getVisibleSystems() {
      return visibleSystems;
   }

   public static void setSystemVisibility(int var0, boolean var1) {
      visibleSystems[var0] = true;
   }

   public static Station[] getLastVisitedStations() {
      return station_class_array;
   }

   public static void setLastVisitedStations(Station[] var0) {
      station_class_array = var0;
   }

   private static Station inLastVisitedStations(Station station) {
      for(int var1 = 0; var1 < 3; ++var1) {
         if(station_class_array[var1] != null && station_class_array[var1].sub_475(station)) {
            return station_class_array[var1];
         }
      }

      return null;
   }

   public static void departStation(Station station) {
      if(station != station_class) {
         Station var1;
         label228: {
            var1 = inLastVisitedStations(station);
            Station var4;
            boolean var10000;
            if((var4 = inLastVisitedStations(station)) != null) {
               setCurrentStation_andInitSystem_(var4);
            } else {
               int var5;
               if(station_class_array[0] != null) {
                  for(var5 = 2; var5 > 0; --var5) {
                     station_class_array[var5] = station_class_array[var5 - 1];
                  }

                  station_class_array[0] = station;
                  setCurrentStation_andInitSystem_(station);
                  var10000 = true;
                  break label228;
               }

               for(var5 = 2; var5 >= 0; --var5) {
                  if(station_class_array[var5] == null) {
                     station_class_array[var5] = station;
                     setCurrentStation_andInitSystem_(station);
                     var10000 = true;
                     break label228;
                  }
               }
            }

            var10000 = false;
         }

         if(var1 == null) {
            Generator var2 = new Generator();
            Station var3 = station;
            Item[] var10001;
            if(station.getId() == 78 && currentCampaignMissionIndex < 7) {
               Item[] items;
			   int max_items = 6; // ������������ ����� ���������� ���������. �������� � ����������� �� ���-������, �.�. ���� ��������� 176 - ���������� ������. �� ������, � � �� �����������.
			   if(GlobalStatus.cheat_mode == true) {
				   max_items = GlobalStatus.max_items;
			   }
			   if(GlobalStatus.cheat_mode == false) {
				   max_items = 6;
			   }
               (items = new Item[max_items])[0] = Globals.getItems()[0].getCopyInAmmount(1, 0); // ������������, ������� ����� �������� �� Var Hastra
               items[1] = Globals.getItems()[22].getCopyInAmmount(1, 0);
               items[2] = Globals.getItems()[55].getCopyInAmmount(1, 0);
               items[3] = Globals.getItems()[50].getCopyInAmmount(1, 0);
               items[4] = Globals.getItems()[31].getCopyInAmmount(5, 0);
               items[5] = Globals.getItems()[32].getCopyInAmmount(5, 0);
			  if(GlobalStatus.cheat_mode == true) { // ������������, ������� �������� ��� ���-������
				   for(int all_items = 0; all_items < max_items; all_items++) {
					   items[all_items] = Globals.getItems()[all_items].getCopyInAmmount(100, 0);
				   }
				   System.out.println("Items_added: " + max_items);
				   System.out.println("Money_added: " + GlobalStatus.MONEY_LEBOVSKI);
				   System.out.println("Have fun!");
			   }
			   if(GlobalStatus.developer_mode == true) {
				   currentCampaignMissionIndex = 44;
				   level = 20;
				   if(GlobalStatus.cheat_mode == false) {
					   items[0] = Globals.getItems()[85].getCopyInAmmount(1, 0); // ������ �������� - ����
				   }
			   }
               var10001 = items;
            } else {
               Vector var19 = new Vector();
               Item[] var21 = Globals.getItems();
               new FileRead();
               SolarSystem[] var14 = FileRead.loadSystemsBinary();
               int var6;
               int var7 = (var6 = station.getTecLevel()) < 4?1:var6 / 2;
               if(currentCampaignMissionIndex > 16 && currentCampaignMissionIndex < 25 && GlobalStatus.random.nextInt(100) < 40) {
                  Item var8 = var21[68].makeItem();
                  var19.addElement(var8);
               }

               GlobalStatus.random.setSeed(System.currentTimeMillis());

               int var22;
               int var24;
               for(var22 = 0; var22 < var21.length; ++var22) {
                  Item var9 = var21[var22];
                  boolean var10 = false;
                  if(var3.getId() == var9.getAttribute(36)) {
                     var10 = true;
                  }

                  int var11 = var9.getTecLevel();
                  boolean var12 = var9.getIndex() >= 132 && var9.getIndex() < 154;
                  if(var10 || var9.getBluePrintComponentsIds() == null && var22 != 175 && var22 != 164 && var11 <= var3.getTecLevel() && var9.getOccurance() != 0 && var9.getSinglePrice() != 0 && (var9.getAttribute(35) != 1 || var14[var3.getSystemIndex()].getRace() == 1) && (!var12 || var9.getIndex() == 132 + var3.getSystemIndex())) {
                     int var13 = var9.getOccurance();
                     if(var10 || (var12 || var11 <= var6 && var11 >= var7) && GlobalStatus.random.nextInt(100) < var13) {
                        var24 = var14[var9.getLowestPriceSystemId()].getPosX();
                        var11 = var14[var9.getLowestPriceSystemId()].getPosY();
                        var24 = Galaxy.invDistancePercent(var14[var3.getSystemIndex()].getPosX(), var14[var3.getSystemIndex()].getPosY(), var24, var11);
                        var11 = 5 + GlobalStatus.random.nextInt(15);
                        if(var9.getType() != 4 && var9.getType() != 1) {
                           var11 = AEMath.max(1, var11 / 5);
                        } else if(var9.getType() == 4 && var24 > 50) {
                           var11 *= AEMath.max(1, (int)((float)(var24 - 50) / 50.0F * 20.0F));
                        }

                        var19.addElement(var9.makeItem(var11));
                     }
                  }
               }

               Item[] var23 = new Item[var22 = var19.size()];

               for(var24 = 0; var24 < var22; ++var24) {
                  var23[var24] = (Item)var19.elementAt(var24);
               }

               var10001 = var23;
            }

            station.sub_37b(var10001);
            station.sub_395(Generator.getShipBuyList(station));
            station.sub_3f6(var2.createAgents(station));
         }

         var_e01 = playTime;
      }

      Mission var15 = Mission.emptyMission_;
      mission = Mission.emptyMission_;

      for(int var16 = 0; var16 < currentMissions.length; ++var16) {
         if(currentMissions[var16] != null) {
            if(currentMissions[var16].isCampaignMission() && currentMissions[var16].getType() == 25 && station.getId() == wormholeStation) {
               mission = currentMissions[var16];
               break;
            }

            if(currentMissions[var16].getTargetStation() == station.getId() && currentMissions[var16].getType() != 8 && currentMissions[var16].getType() != 19 && currentMissions[var16].getType() != 16 && currentMissions[var16].getType() != 14 && currentMissions[var16].getType() != 13) {
               if(currentMissions[var16].isCampaignMission() || currentMissions[var16].getType() != 11) {
                  mission = currentMissions[var16];
               }
               break;
            }
         }
      }

      if(!gameWon() && currentCampaignMissionIndex >= 32 && station.getId() != currentMissions[0].getTargetStation()) {
         if(++wormHoleTick > 10) {
            wormHoleTick = 0;
            boolean var18 = false;

            while(!var18) {
               wormholeSystem = GlobalStatus.random.nextInt(22);
               if(visibleSystems[wormholeSystem] && wormholeSystem != 10 && wormholeSystem != 15 && (!mission.isCampaignMission() || mission.getTargetStation() != wormholeStation)) {
                  var18 = true;
                  SolarSystem var17;
                  wormholeStation = (var17 = Galaxy.loadSystem_(wormholeSystem)).getStations()[GlobalStatus.random.nextInt(var17.getStations().length)];
               }
            }

            return;
         }
      } else if(gameWon()) {
         wormholeStation = -10;
         wormholeSystem = -10;
      }

   }

   public static void setPassengers(int var0) {
      passengerCount = var0;
   }

   public static Mission getMission() {
      return mission;
   }

   public static void setMission(Mission var0) {
      mission = var0;
   }

   public static Mission getFreelanceMission() {
      return currentMissions[1];
   }

   public static void setFreelanceMission(Mission var0) {
      currentMissions[1] = var0;
   }

   public static Mission getCampaignMission() {
      return currentMissions[0];
   }

   public static void startStoryMission(Mission var0) {
      var0.setCampaignMission(true);
      currentMissions[0] = var0;
   }

   public static void incMissionCount() {
      ++missionsCount;
   }

   public static void setCurrentCampaignMission(int var0) {
      currentCampaignMissionIndex = var0;
   }

   public static int getCurrentCampaignMission() {
      return currentCampaignMissionIndex;
   }

   public static void nextCampaignMission() { // ��� ������� ������ ��������� ������.
      boolean var0;
      int var1;
      Item[] var2;
      switch(++currentCampaignMissionIndex) {
      case 1:
         startStoryMission(new Mission(11, GlobalStatus.MONEY_LEBOVSKI, 78)); // startStoryMission(new Class_3e4(18, ������� ����� �����, 78)); �������� ������ ������� ��������� ��� �� ����������� ������. ������ � ����� ��� �� � ������ ������ ���� �����.
         return;
      case 2:
         startStoryMission(new Mission(18, 0, 78)); 
         currentMissions[0].setTasksTreshold_(5); // ������� ���� ������ � ������ �����
         return;
      case 3:
         startStoryMission(new Mission(11, 0, 78));
         return;
      case 4:
         playersShip.setCargo((Item[])null);
         startStoryMission(new Mission(18, 0, 78));
         currentMissions[0].setTasksTreshold_(10); // ������� ���� ������ �� ������ ���
         return;
      case 5:
         startStoryMission(new Mission(11, 0, 78));
         return;
      case 6:
         playersShip.removeAllCargo();
         startStoryMission(new Mission(22, 0, 78));
         return;
      case 7:
         startStoryMission(new Mission(4, 0, 78));
         return;
      case 8:
         if((var2 = playersShip.getEquipment()) != null) {
            for(var1 = 0; var1 < var2.length; ++var1) {
               if(var2[var1] != null) {
                  var2[var1].setUnsaleable(false);
                  var2[var1].setPrice(Globals.getItems()[var1].getSinglePrice());
               }
            }
         }

         Item[] var6;
         if((var6 = playersShip.getCargo()) != null) {
            for(int var5 = 0; var5 < var6.length; ++var5) {
               if(var6[var5] != null) {
                  var6[var5].setUnsaleable(false);
                  var6[var5].setPrice(Globals.getItems()[var5].getSinglePrice());
               }
            }
         }

         startStoryMission(new Mission(11, 0, 78));
         return;
      case 9:
         startStoryMission(new Mission(11, 0, 78));
         return;
      case 10:
         Item var4 = playersShip.getFirstEquipmentOfSort(19);
         playersShip.removeEquipment(var4);
         startStoryMission(new Mission(11, 0, 79));
         return;
      case 11:
         startStoryMission(new Mission(11, 0, 76));
         return;
      case 12:
         startStoryMission(new Mission(11, 0, 79));
         return;
      case 13:
         startStoryMission(new Mission(13, 0, 0));
         currentMissions[0].setTasksTreshold_(missionsCount + 2);
         currentMissions[0].setVisible(false);
         return;
      case 14:
         startStoryMission(new Mission(4, 0, 79));
         return;
      case 15:
         startStoryMission(new Mission(11, 0, 98));
         return;
      case 16:
         startStoryMission(new Mission(4, 0, 98));
         return;
      case 17:
         startStoryMission(new Mission(11, 0, 98));
         return;
      case 18:
         playersShip.setRace(0);
         startStoryMission(new Mission(20, 0, 56));
         return;
      case 19:
         startStoryMission(new Mission(20, 0, var_14c0[1]));
         return;
      case 20:
         startStoryMission(new Mission(23, 0, var_14c0[2]));
         currentMissions[0].setTasksTreshold_(6);
         return;
      case 21:
         startStoryMission(new Mission(4, 0, var_14c0[3]));
         return;
      case 22:
         startStoryMission(new Mission(11, 0, var_14c0[4]));
         return;
      case 23:
         var0 = true;
         visibleSystems[6] = true;
         startStoryMission(new Mission(11, GlobalStatus.thynome_cash, var_14c0[5]));
         return;
      case 24:
         wormholeStation = 48;
         wormholeSystem = 9;
         startStoryMission(new Mission(4, 0, var_14c0[6]));
         return;
      case 25:
         if((var2 = playersShip.getCargo()) != null) {
            for(var1 = 0; var1 < var2.length; ++var1) {
               if(var2[var1].getIndex() == 131) {
                  var2[var1].setUnsaleable(true);
                  break;
               }
            }
         }

         startStoryMission(new Mission(20, 0, var_14c0[7]));
         return;
      case 26:
         wormholeStation = -1;
         wormholeSystem = -1;
         startStoryMission(new Mission(4, 0, var_14c0[8]));
         return;
      case 27:
         startStoryMission(new Mission(11, 0, var_14c0[9]));
         return;
      case 28:
         wormholeStation = 91;
         wormholeSystem = 18;
         startStoryMission(new Mission(4, 0, var_14c0[10]));
         return;
      case 29:
         startStoryMission(new Mission(4, 0, var_14c0[11]));
         return;
      case 30:
         startStoryMission(new Mission(20, 0, var_14c0[12]));
         return;
      case 31:
         startStoryMission(new Mission(11, 250000, var_14c0[13])); // �������� ������ ����� Pescal Inartu
         return;
      case 32:
         startStoryMission(new Mission(11, 0, var_14c0[14]));
         return;
      case 33:
         startStoryMission(new Mission(8, 0, var_14c0[15]));
         currentMissions[0].setCommodity(164, 50); // ��������� ����, 50
         return;
      case 34:
         playersShip.removeCargo(164, 50); // ������� 50 ���������� ����?

         for(var1 = 0; var1 < var_8eb.length; ++var1) { // ���� ������ ������, � ������� ��������� 50 ����������
            if(var_8eb[var1].getIndex() == 85) {
               var_8eb[var1].unlocked = true;
               var_8eb[var1].startProduction(Globals.getItems()[164].makeItem(), 50, -1);
               BluePrint var10000 = var_8eb[var1];
               var0 = false;
               var10000.investedCredits = 0;
            }
         }

         startStoryMission(new Mission(11, 0, var_14c0[16]));
         return;
      case 35:
         startStoryMission(new Mission(11, 0, var_14c0[17]));
         return;
      case 36:
         startStoryMission(new Mission(12, 0, var_14c0[18]));
         return;
      case 37:
         startStoryMission(new Mission(24, 0, var_14c0[19]));
         currentMissions[0].setVisible(false);
         return;
      case 38:
         startStoryMission(new Mission(4, 0, var_14c0[20]));
         return;
      case 39:
         startStoryMission(new Mission(11, 0, var_14c0[21]));
         return;
      case 40:
         startStoryMission(new Mission(25, 0, var_14c0[22]));
         return;
      case 41:
         startStoryMission(new Mission(4, 0, var_14c0[23]));
         return;
      case 42:
         wormholeStation = -10;
         wormholeSystem = -10;
         startStoryMission(new Mission(24, 0, var_14c0[24]));
         return;
      case 43:
         startStoryMission(new Mission(11, 0, var_14c0[25]));
         return;
      case 44:
         startStoryMission(new Mission(11, 0, var_14c0[26]));
         return;
      case 45:
         wormholeStation = -10;
         wormholeSystem = -10;
         startStoryMission(Mission.emptyMission_);
         currentMissions[0].setVisible(false);
         changeCredits(40000);
      default:
      }
   }

   public static boolean gameWon() {
      return currentCampaignMissionIndex > 44;
   }

   public static boolean inEmptyOrbit() {
      return currentCampaignMissionIndex < 2 && currentStation.getId() == 78 || inAlienOrbit() && currentCampaignMissionIndex > 42;
   }

   public static boolean inAlienOrbit() {
      return currentStation.sub_475(station_class);
   }

   public static Mission missionCompleted_(boolean var0, long var1) {
      label140:
      for(int var3 = 0; var3 < currentMissions.length; ++var3) {
         Mission var4;
         if((var4 = currentMissions[var3]).hasWon()) {
            return null;
         }

         if(var4 != null) {
            Item[] var5;
            int var6;
            switch(var4.getType()) {
            case 0:
            case 11:
               if(var0 && currentStation.getId() == var4.getTargetStation()) {
                  return var4;
               }
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 9:
            case 10:
            case 12:
            case 17:
            default:
               break;
            case 8:
               if(var0 && currentStation.getId() == var4.getTargetStation() && Item.isInList(var4.getCommodityIndex(), var4.getCommodityAmount_(), playersShip.getCargo())) {
                  return var4;
               }
               break;
            case 13:
               if(missionsCount >= var4.getStatusValue_()) {
                  var4.setWon(true);
                  return var4;
               }
               break;
            case 14:
               if(kills >= var4.getStatusValue_()) {
                  var4.setWon(true);
                  return var4;
               }
               break;
            case 15:
               var5 = playersShip.getEquipment();
               var6 = 0;

               while(true) {
                  if(var6 >= var5.length) {
                     continue label140;
                  }

                  if(var5[var6] != null && var5[var6].getIndex() == var4.getStatusValue_()) {
                     return var4;
                  }

                  ++var6;
               }
            case 16:
               if(stationsVisited >= var4.getStatusValue_()) {
                  var4.setWon(true);
                  return var4;
               }
               break;
            case 18:
               if(playersShip.getCurrentLoad() >= var4.getStatusValue_()) {
                  var4.setWon(true);
                  return var4;
               }
               break;
            case 19:
               if(bluePrintsProduced >= var4.getStatusValue_()) {
                  var4.setWon(true);
                  return var4;
               }
               break;
            case 20:
               if(!var0 && currentStation.getId() == var4.getTargetStation() && var1 > 10000L) {
                  return var4;
               }
               break;
            case 21:
               var5 = playersShip.getEquipment();
               var6 = 0;

               while(true) {
                  if(var6 >= var5.length) {
                     continue label140;
                  }

                  if(var5[var6] != null && var5[var6].getType() == var4.getStatusValue_()) {
                     return var4;
                  }

                  ++var6;
               }
            case 22:
               var5 = playersShip.getEquipment();
               boolean var9 = false;
               boolean var7 = false;

               for(int var8 = 0; var8 < var5.length; ++var8) {
                  if(var5[var8] != null && var5[var8].getType() == 0) {
                     var9 = true;
                  } else if(var5[var8] != null && var5[var8].getSort() == 10) {
                     var7 = true;
                  }
               }

               if(var9 && var7) {
                  return var4;
               }
               break;
            case 23:
               if(var0) {
                  var5 = playersShip.getEquipment();

                  for(var6 = 0; var6 < var5.length; ++var6) {
                     if(var5[var6] != null && var5[var6].getSort() == var4.getStatusValue_()) {
                        return var4;
                     }
                  }
               }
               break;
            case 24:
               if(var0 || !var0 && currentStation.getId() != var4.getTargetStation() && var1 > 10000L) {
                  return var4;
               }
            }
         }
      }

      return null;
   }

   public static void setJumpgateUsed(int var0) {
      jumpgatesUsed = var0;
   }

   public static void jumpgateUsed() {
      ++jumpgatesUsed;
   }

   public static int getJumpgateUsed() {
      return jumpgatesUsed;
   }

   public static void incCargoSalvaged(int var0) {
      cargoSalvaged += var0;
   }

   public static void setCargoSalvaged(int var0) {
      cargoSalvaged = var0;
   }

   public static int getCargoSalvaged() {
      return cargoSalvaged;
   }

   public static void getUnusedVar(int var0) {
      unused_ = var0;
   }

   public static int setUnusedVar() {
      return unused_;
   }

   public static void removeMission_(Mission var0) {
      for(int var1 = 0; var1 < currentMissions.length; ++var1) {
         if(currentMissions[var1] == var0) {
            currentMissions[var1] = Mission.emptyMission_;
         }
      }

      if(mission == var0) {
         mission = null;
      }

   }

   public static void incStationsVisited() {
      ++stationsVisited;
   }

   public static int getStationsVisited() {
      return stationsVisited;
   }

   public static Ship getShip() {
      return playersShip;
   }

   public static void setShip(Ship var0) {
      playersShip = var0;
   }

   public static Station getStation() {
      return currentStation;
   }

   public static void setCurrentStation_andInitSystem_(Station station) { // ����������� �������� ������� ��� ��������� �� ��
      currentStation = station;
      if((currentSolarSystem = Galaxy.loadSystem_(station.getSystemIndex())) != null) {
         systemsVisited[currentStation.getSystemIndex()] = true;
         new FileRead();
         Station[] var3 = FileRead.loadStationsBinary(currentSolarSystem);
         var_993 = null;
         var_993 = new String[var3.length];
         var_a58 = new int[var3.length];
         int var1 = 0;

         while(var1 < currentSolarSystem.getStations().length) {
            int var2 = 0;

            while(true) {
               if(var2 < var3.length) {
                  if(currentSolarSystem.getStations()[var1] != var3[var2].getId()) {
                     ++var2;
                     continue;
                  }

                  if(currentCampaignMissionIndex == 0) {
                     var_993[var1] = "";
                  } else {
                     var_993[var1] = var3[var2].getName();
                  }

                  var_a58[var1] = var3[var2].getPlanetTextureId();
               }

               ++var1;
               break;
            }
         }

      }
   }

   public static String[] getPlanetNames() {
      return var_993;
   }

   public static int[] getPlanetTextures() {
      return var_a58;
   }

   public static SolarSystem getSystem() {
      return currentSolarSystem;
   }

   public static int getCredits() {
      return credits;
   }

   public static void setRating(int var0) {
      rating = var0;
   }

   public static void setPlayingTime(long var0) {
      playTime = var0;
   }

   public static void setKills(int var0) {
      kills = var0;
   }

   public static void setMissionCount(int var0) {
      missionsCount = var0;
   }

   public static void setLevel(int var0) {
      level = var0;
   }

   public static void setLastXP(int var0) {
      lastXP = var0;
   }

   public static void setGoodsProduced(int var0) {
      bluePrintsProduced = var0;
   }

   public static int getGoodsProduced() {
      return bluePrintsProduced;
   }

   public static void incGoodsProduced(int var0) {
      ++bluePrintsProduced;
   }

   public static void setStationsVisited(int var0) {
      stationsVisited = var0;
   }

   public static void changeCredits(int var0) {
      credits += var0;
   }

   public static void setCredits(int var0) {
      credits = var0;
   }

   public static void checkForLevelUp() {
      int var0 = kills + var_1381 / 2 + var_10ba / 50 + var_1106 + 2 * missionsCount;
      if((float)lastXP * 1.3F < (float)var0) {
         lastXP = var0;
         ++level;
      }

   }

   public static int getRating() {
      return rating;
   }

   public static int getLastXP() {
      return lastXP;
   }

   public static int getKills() {
      return kills;
   }

   public static void incKills() {
      ++kills;
      Achievements.incKills();
   }

   public static void setPirateKills(int var0) {
      pirateKills = var0;
   }

   public static int getPirateKills() {
      return pirateKills;
   }

   public static void incPirateKills() {
      ++pirateKills;
      Achievements.incPirateKills();
   }

   public static int getMissionCount() {
      return missionsCount;
   }

   public static int getLevel() {
      return level;
   }

   public static Standing getStanding() {
      return standing;
   }

   public static BluePrint[] getBluePrints() {
      return var_8eb;
   }

   public static Agent[] getSpecialAgents() {
      return specialAgents;
   }

   public static void incPlayingTime(long var0) {
      playTime += var0;
   }

   public static long getPlayingTime() {
      return playTime;
   }

   public static String[] getWingmenNames() {
      return var_b3a;
   }

   public static void setWingmenNames(String[] var0) {
      var_b3a = var0;
   }

   public static String replaceToken(String var0, String var1) {
      int var2;
      return (var2 = var0.indexOf(35)) >= 0?var0.substring(0, var2) + var1 + var0.substring(var2 + 1):var0;
   }

   public static String replaceTokens(String var0, String var1, String var2) {
      int var3;
      return (var3 = var0.indexOf(var2)) >= 0?var0.substring(0, var3) + var1 + var0.substring(var3 + var2.length()):var0;
   }

   public static void calcCargoPrices() {
      new FileRead();
      SolarSystem[] var0 = FileRead.loadSystemsBinary();

      for(int var1 = 0; var1 < 3; ++var1) {
         Item[] var2;
         if((var2 = var1 == 0?playersShip.getCargo():(var1 == 1?playersShip.getEquipment():currentStation.sub_360())) != null) {
            for(int var5 = 0; var5 < var2.length; ++var5) {
               Item var6;
               if((var6 = var2[var5]) != null) {
                  int var4 = Galaxy.distancePercent(var0[var6.getLowestPriceSystemId()].getPosX(), var0[var6.getLowestPriceSystemId()].getPosY(), var0[var6.getHighestPriceSystemId()].getPosX(), var0[var6.getHighestPriceSystemId()].getPosY());
                  int var3 = Galaxy.distancePercent(var0[var2[var5].getLowestPriceSystemId()].getPosX(), var0[var2[var5].getLowestPriceSystemId()].getPosY(), var0[currentStation.getSystemIndex()].getPosX(), var0[currentStation.getSystemIndex()].getPosY());
                  var3 = var6.getMinPrice() + (int)(AEMath.min(1.0F, 100.0F / (float)var4 * (float)var3 / 100.0F) * (float)(var6.getMaxPrice() - var6.getMinPrice()));
                  if(var6.getSinglePrice() > 0) {
                     GlobalStatus.random.setSeed((long)currentStation.getId());
                     var4 = AEMath.max(1, (int)((float)var3 * 0.05F));
                     var3 += -var4 + GlobalStatus.random.nextInt((var4 << 1) + 1);
                     var6.setPrice(var3);
                  }
               }
            }

            GlobalStatus.random.setSeed(System.currentTimeMillis());
         }
      }

   }

   public static void startNewGame() {
      credits = 0;
      rating = 0;
      kills = 0;
      missionsCount = 0;
      bluePrintsProduced = 0;
      pirateKills = 0;
      jumpgatesUsed = 0;
      cargoSalvaged = 0;
      unused_ = 0;
      level = 1;
      lastXP = 15;
      stationsVisited = 0;
      playTime = 0L;

      int var0;
      for(var0 = 0; var0 < var_1053.length; ++var0) {
         var_1053[var0] = false;
      }

      for(var0 = 0; var0 < var_1071.length; ++var0) {
         var_1071[var0] = false;
      }

      missionGoodsCarried = 0;
      var_10ba = 0;
      var_1106 = 0;
      boughtBooze = 0;

      for(var0 = 0; var0 < drinkTypesPossesed.length; ++var0) {
         drinkTypesPossesed[var0] = false;
      }

      destroyedJunk = 0;

      for(var0 = 0; var0 < systemsVisited.length; ++var0) {
         systemsVisited[var0] = false;
      }

      passengersCarried = 0;
      invisibleTime = 0L;
      bombsUsed = 0;
      alienJunkSalvaged = 0;
      var_134b = 0;
      var_1381 = 0;
      asteroidsDestroyed = 0;
      maxFreeCargo = 0;
      missionsRejected = 0;
      askedToRepeate = 0;
      acceptedNotAskingDifficulty = 0;
      var_149c = 0;
      Achievements.init();
      var_cff = new int[GlobalStatus.max_items];
      var_cca = new int[GlobalStatus.max_items];
      var_d2a = new byte[GlobalStatus.max_items];
      var_d11 = new byte[GlobalStatus.max_items];
      boolean[] var3 = Galaxy.getVisitedStations();

      int var1;
      for(var1 = 0; var1 < var3.length; ++var1) {
         var3[var1] = false;
      }

      Galaxy.setVisitedStations(var3);
      var1 = 0;
      Item[] var5 = Globals.getItems();

      int var2;
      for(var2 = 0; var2 < var5.length; ++var2) {
         if(var5[var2].getBluePrintComponentsIds() != null) {
            ++var1;
         }
      }

      if(var1 > 0) {
         var_8eb = new BluePrint[var1];
         var2 = 0;

         for(var1 = 0; var1 < var5.length; ++var1) {
            if(var5[var1].getBluePrintComponentsIds() != null) {
               var_8eb[var2++] = new BluePrint(var1);
            }
         }
      }

      waitingGoods = null;
      new FileRead();
      specialAgents = FileRead.loadAgents();
      standing = new Standing();
      wormholeStation = -1;
      wormholeSystem = -1;
      lastVisitedNonVoidOrbit = 0;
      GlobalStatus.resetHints();
      Mission var7 = Mission.emptyMission_;
      currentMissions[1] = var7;
      boolean var8 = true;
	  currentCampaignMissionIndex = 0;
      startStoryMission(new Mission(4, 0, 78)); // ������������, ������� ��������������� �� Phantom � ������ ����
      mission = var7 = currentMissions[0];
      (playersShip = Globals.getShips()[GlobalStatus.start_ship].cloneBase()).setRace(0); // ������� � ��������� �������� (�� ��������� Phantom, id 10)
      playersShip.setSellingPrice();
      setCurrentStation_andInitSystem_(Galaxy.getStation(78)); // �� ����� ������� ������������
      playersShip.setCargo((Item[])null);
	  
      Item var6 = Globals.getItems()[1].makeItem(); // ������ 1
      playersShip.setEquipment(var6, 0);
	  
      Item var4 = Globals.getItems()[1].makeItem(); // ������ 2
      playersShip.setEquipment(var4, 1);
	  
      Item var9 = Globals.getItems()[51].makeItem(); // ���
      playersShip.setEquipment(var9, 0);
	  
      var9 = Globals.getItems()[56].makeItem(); // �����
      playersShip.setEquipment(var9, 1);
	  
      var9 = Globals.getItems()[81].makeItem(); // ������
      playersShip.setEquipment(var9, 2);
	  
      baseArmour = playersShip.getBaseArmour();
      shield = playersShip.getShield();
      additionalArmour = playersShip.getAdditionalArmour();
   }

}
