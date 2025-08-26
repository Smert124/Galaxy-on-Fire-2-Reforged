package GoF2;

import AE.GlobalStatus;
import AE.Math.AEMath;
import AE.PaintCanvas.ImageFactory;


public final class Generator {

   private final int[] GENERATOR_CAMPAIGN_STATIONS = new int[]{10, 22, 27, 29, 30, 48, 55, 56, 76, 79, 91, 98};
   private final int[] GENERATOR_TYPE_PROBS = new int[]{5, 20, 2, 5, 100};


   public static Ship[] getShipBuyList(Station station) {
      if(station.getSystemIndex() == 15 && Status.getCurrentCampaignMission() < 16) { // запрет продажи кораблей в Mido (начало игры)
         return null;
      } else {
         Ship[] ship = null;
         int systemRace = Status.getSystem().getRace();
         boolean var7 = station.getId() == 10 && Achievements.gotAllGoldMedals(); // Thynome, после получения медалей спавнится VoidX
         int var10000 = systemRace != 1 && !var7?GlobalStatus.random.nextInt(6):1;
         int var8 = var10000;
         if(var10000 == 0) {
            return null;
         } else {
            ship = new Ship[var8];

            for(int var3 = 0; var3 < ship.length; ++var3) {
               int var4 = 0;
               boolean var5 = false;

               while(!var5) {
                  var4 = var7?8:Globals.getShipIDByRaceForSale(systemRace); // продажа кораблей на станциях
                  if(!GlobalStatus.var_1083 && GlobalStatus.var_10e5 && GlobalStatus.random.nextInt(2) == 0) {
                     switch(GlobalStatus.random.nextInt(5)) {
                     case 0:
                        var4 = 0;
                        break;
                     case 1:
                        var4 = 32;
                        break;
                     case 2:
                        var4 = 2;
                        break;
                     case 3:
                        var4 = 5;
                        break;
                     case 4:
                        var4 = 35;
                     }
                  }

                  var5 = true;

                  for(int var6 = 0; var6 < ship.length; ++var6) {
                     if(ship[var6] != null && ship[var6].getIndex() == var4) {
                        var5 = false;
                        break;
                     }
                  }
               }

               ship[var3] = Globals.getShips()[var4].cloneBase();
               ship[var3].setRace(Status.getSystem().getRace());
               ship[var3].adjustPrice();
            }

            return ship;
         }
      }
   }

   public final int[] getLootList() {
      Item[] var1 = Globals.getItems();
      int var2;
      if((var2 = GlobalStatus.random.nextInt(3)) == 0) {
         return null;
      } else {
         byte var3 = 7;
         if(!GlobalStatus.var_1083) {
            var3 = 3;
         }

         int[] var10 = new int[var2 << 1];
         Item[] var4 = Globals.getItems();
         int var5 = 0;

         for(int var6 = 0; var6 < var10.length; var6 += 2) {
            boolean var7 = false;
            int var8 = 0;

            for(int var9 = 0; var9 < 100 && !var7; ++var9) {
               var8 = GlobalStatus.random.nextInt(var1.length);
               var5 = var4[var8].getType();
               if(var4[var8].getBluePrintComponentsIds() == null && GlobalStatus.random.nextInt(100) < this.GENERATOR_TYPE_PROBS[var5] && GlobalStatus.random.nextInt(100) < var1[var8].getOccurance() && var4[var8].getSinglePrice() > 0 && var8 != 175 && var8 != 164 && (var5 == 4 || var4[var8].getTecLevel() <= var3)) {
                  var7 = true;
               }
            }

            if(!var7) {
               var8 = 154 + GlobalStatus.random.nextInt(10);
               var5 = 4;
            }

            var10[var6] = var8;
            if(var5 == 4) {
               var10[var6 + 1] = 1 + GlobalStatus.random.nextInt(9);
            } else {
               var10[var6 + 1] = 1;
            }
         }

         return var10;
      }
   }

   private int generateStationIndex(SolarSystem[] var1, int var2) {
      int var3 = 0;
      boolean var4 = false;

      while(!var4) {
         if(GlobalStatus.random.nextInt(100) < 20) {
            var3 = var2;
         } else if(GlobalStatus.random.nextInt(100) < 40) {
            int[] var5;
            var3 = (var5 = var1[Status.getSystem().getId()].getStations())[GlobalStatus.random.nextInt(var5.length)];
         } else {
            var3 = GlobalStatus.random.nextInt(100);
         }

         if(Status.getSystem().getId() == 15) {
            var3 = Status.getSystem().getStations()[GlobalStatus.random.nextInt(Status.getSystem().getStations().length)];
         }

         var4 = true;
         int var7 = 0;

         while(true) {
            if(var7 < this.GENERATOR_CAMPAIGN_STATIONS.length) {
               if(var3 != this.GENERATOR_CAMPAIGN_STATIONS[var7]) {
                  ++var7;
                  continue;
               }

               var4 = false;
            }

            var7 = 0;

            for(int var6 = 0; var6 < var1.length; ++var6) {
               if(var1[var6].sub_36a(var3)) {
                  var7 = var6;
                  break;
               }
            }

            if(!Status.getVisibleSystems()[var7]) {
               var4 = false;
            }

            if(var1[var7].getNeighbourSystems() == null && var7 != Status.getSystem().getId()) {
               var4 = false;
            }
            break;
         }
      }

      return var3;
   }

   public final Agent[] createAgents(Station var1) {
      Agent[] var2 = Status.getSpecialAgents();
      int var3 = 0;
      boolean var4 = Status.getCurrentCampaignMission() > 16;

      int var5;
      for(var5 = 0; var5 < var2.length; ++var5) {
         if(var2[var5].getStationId() == var1.getId() && var4) {
            ++var3;
         }
      }

      Agent[] var12 = new Agent[AEMath.min(5, var3 + 3 + GlobalStatus.random.nextInt(2))];
      var5 = 0;

      int var6;
      for(var6 = 0; var6 < var2.length; ++var6) {
         if(var2[var6].getStationId() == var1.getId() && var4) {
            var12[var5++] = var2[var6];
         }
      }

      boolean var17 = false;

      int var21;
      for(int var10 = var5; var10 < var12.length; ++var10) {
         Station var13 = var1;
         boolean var18 = false;
         var5 = Status.getSystem().getRace();
         if(GlobalStatus.random.nextInt(100) < 20) {
            var5 = GlobalStatus.random.nextInt(8);
         }

         boolean var7 = false;
         int var8 = -1;

         while(!var7) {
            var8 = GlobalStatus.random.nextInt(7);
            if((var5 != 1 || var8 != 6) && var8 != 4 && var8 != 3) {
               var7 = true;
            }
         }

         if(GlobalStatus.random.nextInt(100) < 33 || (var8 == 5 || var8 == 6) && Status.getCurrentCampaignMission() < 16) {
            var8 = 0;
         }

         var7 = var8 != 6 && var5 == 0?GlobalStatus.random.nextInt(100) < 60:true;
         Agent var9;
         (var9 = new Agent(-1, Globals.getRandomName(var5, var7), var1.getId(), Status.getSystem().getId(), var5, var7, -1, -1, -1)).setType(var8);
         var9.setImageParts(ImageFactory.createChar(var7, var5));
         int var14;
         if(var9.sub_8f() == 6) {
            String[] var19 = new String[var5 = GlobalStatus.random.nextInt(3)];

            for(var14 = 0; var14 < var5; ++var14) {
               var19[var14] = Globals.getRandomName(var9.sub_166(), true);
            }

            var9.wingman1Name = null;
            var9.wingman2Name = null;
            var9.wingmanFriendsCount = 0;
            if(var19.length > 0 && var19[0] != null) {
               var9.wingman1Name = var19[0];
               ++var9.wingmanFriendsCount;
            }

            if(var19.length > 1 && var19[1] != null) {
               var9.wingman2Name = var19[1];
               ++var9.wingmanFriendsCount;
            }

            var9.setCosts((var5 + 1) * (700 + GlobalStatus.random.nextInt(1300)));
         } else if(var9.sub_8f() == 2) {
            Item[] var20 = Globals.getItems();
            var7 = false;

            do {
               do {
                  do {
                     do {
                        do {
                           do {
                              var21 = GlobalStatus.random.nextInt(var20.length);
                           } while(!GlobalStatus.var_1083 && var13.getSystemIndex() == 15 && var20[var21].getTecLevel() >= 4);
                        } while(var21 == 175); // исключить предметы из продажи
                     } while(var21 == 164);
                  } while(var21 == 131);
               } while(var20[var21].getBluePrintComponentsIds() != null);
            } while(var20[var21].getSinglePrice() == 0);

            Item var15 = var20[var21];
            var5 = 5 + GlobalStatus.random.nextInt(15);
            if(var15.getType() == 3 || var15.getType() == 0 || var15.getType() == 2) {
               var5 = 1;
            }

            var14 = var5 * (int)((float)(40 + GlobalStatus.random.nextInt(120)) / 100.0F * (float)Globals.getItems()[var21].getSinglePrice());
            var9.setSellItem(var21, var5, var14);
         }

         var12[var10] = var9;
         if(var12[var10].sub_8f() == 6) {
            if(var17) {
               var12[var10].setType(1);
            }

            var17 = true;
         } else if(var12[var10].sub_8f() == 0) {
            var12[var10].sub_3c9(this.createFreelanceMission(var12[var10]));
         }
      }

      Standing var11 = Status.getStanding();
      int[] var16 = new int[]{2, 3, 0, 1};

      for(var5 = 0; var5 < var16.length; ++var5) {
         var6 = var16[var5];
         if(var11.isEnemy(var6)) {
            for(var21 = 0; var21 < var12.length; ++var21) {
               if(var12[var21].isGenericAgent_() && var12[var21].sub_8f() != 7) {
                  var12[var21] = null;
                  var12[var21] = new Agent(-1, Globals.getRandomName(var6, true), var1.getId(), Status.getSystem().getId(), var6, true, -1, -1, -1);
                  var12[var21].setType(7);
                  var12[var21].setImageParts(ImageFactory.createChar(true, var6));
                  break;
               }
            }
         }
      }

      return var12;
   }

   public final Mission createFreelanceMission(Agent var1) {
      new FileRead();
      SolarSystem[] var2 = FileRead.loadSystemsBinary();
      int var3 = this.generateStationIndex(var2, var1.getStationId());
      if(Status.getSystem().getId() == 15) {
         var3 = Status.getSystem().getStations()[0] + GlobalStatus.random.nextInt(4);
      }

      boolean var4 = false;
      int var5 = 0;

      while(!var4) {
         if((var5 = GlobalStatus.random.nextInt(13)) != 8) {
            var4 = true;
         }
      }

      if(Status.getCurrentCampaignMission() < 16) {
         switch(GlobalStatus.random.nextInt(5)) {
         case 0:
            var5 = 11;
            break;
         case 1:
            var5 = 0;
            break;
         case 2:
            var5 = 7;
            break;
         case 3:
            var5 = 4;
            break;
         case 4:
            var5 = 12;
         }
      }

      if(var5 == 12) {
         var3 = var1.getStationId();
      }

      if(var1.sub_8f() == 5) {
         var5 = 8;
         var3 = var1.getStationId();
      }

      if(var5 == 11 || var5 == 0) {
         while(var3 == Status.getStation().getId()) {
            var3 = this.generateStationIndex(var2, var1.getStationId());
         }
      }

      int var14 = var1.sub_166();
      String var6 = var1.var_4f;
      boolean var7 = false;
      int var8 = 0;
      int var9 = 0;
      int var15;
      if(Status.getCurrentCampaignMission() < 16) {
         var15 = 1 + GlobalStatus.random.nextInt(2);
      } else {
         var15 = 1 + GlobalStatus.random.nextInt(9);
      }

      if(var5 == 8) {
         Item[] var10 = Globals.getItems();
         boolean var11 = false;

         int var16;
         do {
            do {
               do {
                  do {
                     var16 = 97 + GlobalStatus.random.nextInt(var10.length - 97);
                  } while(var10[var16].getSinglePrice() == 0);
               } while(var16 == 175);
            } while(var16 == 164);
         } while(var16 == 131);

         var9 = var16;
         var8 = 5 + GlobalStatus.random.nextInt(15);
         var15 = var10[var16].getTecLevel();
      } else if(var5 != 3 && var5 != 5) {
         if(var5 == 0) {
            var8 = 5 + (int)(95.0F * ((float)var15 / 10.0F));
            var9 = GlobalStatus.random.nextInt(7);
         } else if(var5 == 11) {
            var8 = 2 + (int)(18.0F * ((float)var15 / 10.0F));
         } else if(var5 == 2) {
            var8 = 2 + (int)((float)GlobalStatus.random.nextInt(4));
         }
      } else {
         var8 = 2 + (int)(8.0F * ((float)var15 / 10.0F));
         var9 = var5 == 3?116:117;
      }

      var15 = AEMath.min(10, var15);
      float var17 = 1.0F + Galaxy.distance(var2[Status.getStation().getSystemIndex()], var2[Galaxy.getStation(var3).getSystemIndex()]) / 1200.0F;
      float var18 = (var18 = (float)(1500 + (int)((float)var15 / 10.0F * 5500.0F))) * var17 + (float)(Status.getLevel() * 200);
      if(var5 == 7) {
         var18 *= 0.7F;
      } else if(var5 == 9) {
         var18 *= 1.2F;
      } else if(var5 == 8) {
         var18 = var18 / 2.0F + (float)(var8 * Globals.getItems()[var9].getMaxPrice() * 3);
      } else if(var5 == 11) {
         var18 = (var18 *= 0.6F) + (float)var8 * (var18 / 5.0F);
      } else if(var5 == 5 || var5 == 3) {
         var18 *= 2.0F;
      }

      int var13 = (int)var18 % 50;
      var18 = (var18 + (float)var13) % 50.0F == 0.0F?var18 + (float)var13:var18 - (float)var13;
      Mission var12 = new Mission(var5, var6, var1.getFace(), var14, (int)var18, var3, var15);
      var3 = (int)(var18 / 10.0F + (float)GlobalStatus.random.nextInt((int)var18 / 10));
      if(var5 == 8) {
         var3 = (int)((float)var3 * 0.5F);
      } else if(var5 == 6) {
         var12.setTargetName(Globals.getRandomName(0, true));
      }

      var13 = var3 % 50;
      var3 = (var3 + var13) % 50 == 0?var3 + var13:var3 - var13;
      var12.setBonus(var3);
      var12.setCommodity(var9, var8);
      return var12;
   }
}
