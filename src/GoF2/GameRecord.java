/**
** Что-то про статистику в конце.
**/

package GoF2;

import AE.GlobalStatus;
import AE.Time;


public final class GameRecord {

   public boolean[] visitedStations = new boolean[GlobalStatus.max_stations];
   public int credits;
   public int rating;
   public long playTime;
   public int kills;
   public int missionCount;
   public int level;
   public int lastWorth;
   public int currentCampaignMissionIndex;
   public int pirateKills;
   public int jumpGateUses;
   public int cargoSalvaged;
   public int unused__;
   public int stationsVisited;
   public int goodsProduced;
   public int wormholeStation;
   public int wormholeSystem;
   public int lastVisitedNonVoidOrbit;
   public int wormHoleTick;
   public Mission freelanceMission;
   public Mission storyMission;
   public Station[] lastVisitedStations;
   public int[] achievements;
   public boolean[] minedOreTypes = new boolean[11];
   public boolean[] minedCoreTypes = new boolean[11];
   public int missionGoodsCarried;
   public int minedOre;
   public int minedCores;
   public int boughtBooze;
   public boolean[] drinkTypesPossesed = new boolean[22];
   public int destroyedJunk;
   public boolean[] systemsVisited = new boolean[GlobalStatus.max_systems];
   public int passengersCarried;
   public long invisibleTime;
   public int bombsUsed;
   public int alienJunkSalvaged;
   public int barInteractions;
   public int commandedWingmen;
   public int asteroidsDestroyed;
   public int maxFreeCargo;
   public int missionsRejected;
   public int askedToRepeate;
   public int acceptedNotAskingDifficulty;
   public int acceptedNotAskingLocation;
   public boolean shopHelpShown = false;
   public boolean shipHelpShown = false;
   public boolean actionsHelpShown = false;
   public boolean bluePrintsHelpShown = false;
   public boolean bluePrintInfoHelpShown = false;
   public boolean barHelpShown = false;
   public boolean galaxyMapHelpShown = false;
   public boolean systemMapHelpShown = false;
   public boolean unused12121_ = false;
   public boolean miningHelpShown = false;
   public boolean asteroidHelpShown = false;
   public boolean missionsHelpShown = false;
   public boolean cargoFullHelpShown = false;
   public boolean wingmenHelpShown = false;
   public boolean actionMenuHelpShown = false;
   public boolean boosterHelpShown = false;
   public boolean statusHelpShown = false;
   public boolean medalsHelpShown = false;
   public boolean fanWingmenNoticeShown = false;
   public boolean voidxNoticeShown = false;
   public boolean reputationHelpShown = false;
   public boolean buyingHelpShown = false;
   public boolean itemMountingHelpShown = false;
   public boolean itemMounting2HelpShown = false;
   public boolean interplanetHelpShown = false;
   public boolean jumpDriveHelpShown = false;
   public boolean cloakHelpShown = false;
   public long loadingsCount = 0L;
   public long timeSpentLoading = 0L;
   public Ship egoShip;
   public Station currentStation;
   public Standing standing;
   public BluePrint[] blueprints;
   public ProducedGood[] waitingGoods;
   public Agent[] specialAgents;
   public String[] wingmenNames;
   public int wingmanRace;
   public int wingmenTimeRemaining;
   public byte[] wingmanFace;
   public int passengerCount;
   public boolean[] visibleSystems;
   public int[] lowestItemPrices;
   public int[] highestItemPrices;
   public byte[] lowestItemPriceSystems;
   public byte[] highestItemPriceSystems;
   public String stationName;


   public final void sub_b6() {
      Status.startNewGame();
      Galaxy.setVisitedStations(this.visitedStations);
      Status.setLastXP(this.lastWorth);
      Status.setCredits(this.credits);
      Status.setRating(this.rating);
      Status.setPlayingTime(this.playTime);
      Status.setKills(this.kills);
      Status.setMissionCount(this.missionCount);
      Status.setLevel(this.level);
      Status.setLastXP(this.lastWorth);
      Status.setGoodsProduced(this.currentCampaignMissionIndex);
      Status.setPirateKills(this.pirateKills);
      Status.setJumpgateUsed(this.jumpGateUses);
      Status.setCargoSalvaged(this.cargoSalvaged);
      Status.getUnusedVar(this.unused__);
      Status.setStationsVisited(this.stationsVisited);
      Status.wormholeStation = this.wormholeStation;
      Status.wormholeSystem = this.wormholeSystem;
      Status.lastVisitedNonVoidOrbit = this.lastVisitedNonVoidOrbit;
      Status.wormHoleTick = this.wormHoleTick;
      Status.setCurrentCampaignMission(this.goodsProduced);
      Status.setFreelanceMission(this.freelanceMission);
      Status.startStoryMission(this.storyMission);
      Status.setLastVisitedStations(this.lastVisitedStations);
      Status.var_1053 = this.minedOreTypes;
      Status.var_1071 = this.minedCoreTypes;
      Status.missionGoodsCarried = this.missionGoodsCarried;
      Status.var_10ba = this.minedOre;
      Status.var_1106 = this.minedCores;
      Status.boughtBooze = this.boughtBooze;
      Status.drinkTypesPossesed = this.drinkTypesPossesed;
      Status.destroyedJunk = this.destroyedJunk;
      Status.systemsVisited = this.systemsVisited;
      Status.passengersCarried = this.passengersCarried;
      Status.invisibleTime = this.invisibleTime;
      Status.bombsUsed = this.bombsUsed;
      Status.alienJunkSalvaged = this.alienJunkSalvaged;
      Status.var_134b = this.barInteractions;
      Status.var_1381 = this.commandedWingmen;
      Status.asteroidsDestroyed = this.asteroidsDestroyed;
      Status.maxFreeCargo = this.maxFreeCargo;
      Status.missionsRejected = this.missionsRejected;
      Status.askedToRepeate = this.askedToRepeate;
      Status.acceptedNotAskingDifficulty = this.acceptedNotAskingDifficulty;
      Status.var_149c = this.acceptedNotAskingLocation;
      Achievements.init();
      if(this.achievements != null) {
         Achievements.setMedals(this.achievements);
      }

      Status.setShip(this.egoShip);
      Status.setCurrentStation_andInitSystem_(this.currentStation);
      Status.setMission(Mission.emptyMission_);
      Status.standing = this.standing;
      Status.var_8eb = this.blueprints;
      Status.waitingGoods = this.waitingGoods;
      Status.specialAgents = this.specialAgents;
      Status.var_b3a = this.wingmenNames;
      Status.wingmanRace = this.wingmanRace;
      Status.var_bea = this.wingmenTimeRemaining;
      Status.wingmanFace = this.wingmanFace;
      Status.passengerCount = this.passengerCount;
      Status.visibleSystems = this.visibleSystems;
      Status.var_cca = this.lowestItemPrices;
      Status.var_cff = this.highestItemPrices;
      Status.var_d11 = this.lowestItemPriceSystems;
      Status.var_d2a = this.highestItemPriceSystems;
      GlobalStatus.shopHelpShown = this.shopHelpShown;
      GlobalStatus.shipHelpShown = this.shipHelpShown;
      GlobalStatus.actionsHelpShown = this.actionsHelpShown;
      GlobalStatus.bluePrintsHelpShown = this.bluePrintsHelpShown;
      GlobalStatus.bluePrintInfoHelpShown = this.bluePrintInfoHelpShown;
      GlobalStatus.barHelpShown = this.barHelpShown;
      GlobalStatus.galaxyMapHelpShown = this.galaxyMapHelpShown;
      GlobalStatus.systemMapHelpShown = this.systemMapHelpShown;
      GlobalStatus.somehelpShown_unused_ = this.unused12121_;
      GlobalStatus.miningHelpShown = this.miningHelpShown;
      GlobalStatus.asteroidHelpShown = this.asteroidHelpShown;
      GlobalStatus.missionsHelpShown = this.missionsHelpShown;
      GlobalStatus.cargoFullHelpShown = this.cargoFullHelpShown;
      GlobalStatus.wingmenHelpShown = this.wingmenHelpShown;
      GlobalStatus.actionMenuHelpShown = this.actionMenuHelpShown;
      GlobalStatus.boosterHelpShown = this.boosterHelpShown;
      GlobalStatus.statusHelpShown = this.statusHelpShown;
      GlobalStatus.medalsHelpShown = this.medalsHelpShown;
      GlobalStatus.fanWingmenNoticeShown = this.fanWingmenNoticeShown;
      GlobalStatus.voidxNoticeShown = this.voidxNoticeShown;
      GlobalStatus.reputationHelpShown = this.reputationHelpShown;
      GlobalStatus.buyingHelpShown = this.buyingHelpShown;
      GlobalStatus.itemMountingHelpShown = this.itemMountingHelpShown;
      GlobalStatus.itemMounting2HelpShown = this.itemMounting2HelpShown;
      GlobalStatus.interplanetHelpShown = this.interplanetHelpShown;
      GlobalStatus.jumpDriveHelpShown = this.jumpDriveHelpShown;
      GlobalStatus.cloakHelpShown = this.cloakHelpShown;
      Status.var_1016 = this.loadingsCount;
      Status.loadingTime = this.timeSpentLoading;
      this.visitedStations = null;
   }

   public final String toString() {
      return "Playing time: \t\t" + Time.timeToHMS(this.playTime) + "\n" + "Level: \t\t" + this.level + "\n" + "Credits: \t\t" + this.credits + "\n\n" + "Rating: \t\t" + this.rating + "\n" + "Kills: \t\t" + this.kills + "\n" + "MissionCount: \t\t" + this.missionCount + "\n" + "LastWorth: \t\t" + this.lastWorth + "\n" + "StationsVisited: \t\t" + this.stationsVisited + "\n";
   }
}
