package GoF2;

import javax.microedition.lcdui.Image;

import AE.AEFile;
import AE.AEResourceManager;
import AE.AbstractMesh;
import AE.GlobalStatus;
import AE.GraphNode;
import AE.Group;

public final class Globals {

   private static String PATH_MESHES = "/Resource/Mesh/";
   private static String PATH_TEXTURES = "/Resource/textures/";
   private static String dlc_valkyrie = "/Resource/Mesh/Valkyrie/";
   private static String dlc_supernova = "/Resource/Mesh/Supernova/";
   public static final short[] BAR_MESHES = new short[]{14025, 14029, 14027, 14329}; // bar
   public static final short[] space_lounge_model_alpha = new short[]{15025, 15029, 15027, 14429}; // bar ALPHA
   public static final short[] space_lounge_model_add = new short[]{15125, 15129, 15127, 14529}; // bar ALPHA_ADD
   public static final short[] BAR_FIGURES = new short[]{14223, 14225, 14226, 14223, 14227, 14223, 14228, 14229, 14223}; // figure
   public static final short[][] HANGAR_MESHES = new short[][]{{14007, 14008, 14009, 14010, 14011, 14012, 14013}, {14014, 14015, 14016, 14017, 14018, 14019}, {14020, 14021, 14022, 14023, 14024}}; // hangar
   public static short[] TYPE_WEAPONS; // weapons
   
   public static Image interfaceImage;
   public static Image shipInterfaceImage;
   public static Image panelInfoUpper;
   public static Image hudLockonNeutral;
   public static Image hudRadarIconNeutral;
   public static Image hudLockonNeutralFar;
   public static Image hudLockonEnemy;
   public static Image hudRadarIconEnemy;
   public static Image hudLockonEnemyFar;
   public static Image hudLockonFriend;
   public static Image hudRadarIconFirend;
   public static Image hudLockonFriendFar;
   public static Image hudWaypoint;
   public static Image hudLockonWaypoint;
   public static Image hudBars;
   public static Image logosSmall;
   public static Image bracketBox;
   public static Image hudCrate;
   public static Image hudSpacejunk;
   public static Image hudAsteroid;
   public static Image hudCrateVoid;
   public static Image hudVortex;
   public static Image menuMapJumpgate;
   public static Image hudScanprocessAnim;
   public static Image hudMeteorClass;
   public static Image menuMapSidemission;
   public static Image menuMapMainmission;
   public static Image menuMapBlueprint;
   public static Image menuMapVisited;
   public static Image menuMapDirection;
   public static Image mapSunGlow;
   public static Image medalsOn;
   public static Image medals;
   public static Image flaggen;
   public static Image itemsImage;
   public static Image hudHullBarFull;
   public static Image hudArmorFull;
   public static Image hudHullBarEmpty;
   public static Image hudStatusPanel;
   public static Image hudShieldNormalIcon;
   public static Image hudShieldBarEmpty;
   public static Image hudShieldBarFull;
   public static Image hudShipNormalIcon;
   public static Image panelInfoLower;
   public static Image quickmenuCrosshair;
   public static Image quickmenuIcons;
   public static Image hudCrosshair;
   public static Image lockImage;
   public static Image miningBackground;
   public static Image miningCursor;
   public static Image miningGreenComplete;
   public static Image miningGreenEmpty;
   public static Image miningRedArea;
   public static Image menuSkipImage;
   public static Image menuBackgroundImage;
   public static Image menuMainCornerImage;
   public static Image menuMainPanelUpperImage;
   public static Image menuMainPanelLowerImage;
   public static Image menuPanelCornerLeftImage;
   public static Image menuLowerPanelSolidImage;
   public static Image fishlabsImage;
   public static Image abyssImage;
   public static Image gof2LogoImage;
   public static Image itemTypesImage;
   public static Image shipsColorImage;
   public static Image itemTypesSelImage;
   public static Image cargoPanelImage;
   public static Image hudBarYellowEmpty;
   public static Image hudBarYellowFull;
   public static Image hudBarRedEmpty;
   public static Image hudBarRedFull;
   public static Image hudBarBlueEmpty;
   public static Image hudBarWhiteFull;
   public static Image hudBarGreenEmpty;
   public static Image hudBarGreenFull;
   public static Image hudBarBlueFull;
   public static Image hudBarWhiteEmpty;
   public static Image hudBarOrangeFull;
   public static Image menuBackgroundTextBox;
   public static Image menuMapPlayerHome;
   public static Image radarRingImage;
   
   /** BUTTONS **/
   public static Image rectRoundedButtonNormal;
   public static Image rectRoundedButtonPressed;
   public static Image rectRoundedButtonInactive;
   
   public static Image rectRoundedSmallButtonNormal;
   public static Image rectRoundedSmallButtonPressed;
   
   public static Image rectWideButtonNormal;
   public static Image rectWideButtonPressed;
   
   public static Image departButtonNormal;
   public static Image departButtonPressed;
   
   public static Image joystickBackground;
   public static Image joystickNormal;
   public static Image joystickPressed;
   
   public static Image autopilotNormal;
   public static Image autopilotPressed;
   
   public static Image boosterNormal;
   public static Image boosterPressed;
   public static Image boosterInactive;
   
   public static Image rightPanelBackgroundImage;
   
   public static Image fireButtonNormal;
   public static Image fireButtonPressed;
   
   public static Image rocketButtonNormal;
   public static Image rocketButtonPressed;
   
   public static Image cameraButtonNormal;
   public static Image cameraButtonPressed;
   
   public static Image quickmenuButtonNormal;
   public static Image quickmenuButtonPressed;
   
   public static Image pauseButtonNormal;
   public static Image pauseButtonPressed;
   
   public static Image triangleButtonNormal;
   public static Image trianlgeButtonPressed;
   /** BUTTONS **/
   
   public static final byte[] CHAR_KEITH;
   private static byte[] CHAR_BRENT;
   private static byte[] CHAR_GUNANT;
   private static byte[] CHAR_NORRIS;
   private static byte[] CHAR_MKKT_BKKT;
   private static byte[] CHAR_TOMMY;
   private static byte[] CHAR_CARLA;
   private static byte[] CHAR_ERRKT;
   private static byte[] CHAR_JEAN;
   private static byte[] CHAR_PIRATE_CHIEF;
   private static byte[] CHAR_PIRATE_1;
   private static byte[] CHAR_PIRATE_2;
   private static byte[] CHAR_SECURITY_GUY;
   private static byte[] CHAR_SECURITY_GIRL;
   private static byte[] CHAR_KIDNAPPER;
   private static byte[] CHAR_STORY;
   public static final byte[] CHAR_COMPUTER;
   private static byte[] CHAR_INFO_PIC;
   private static byte[] CHAR_TERRAN_OFFICER;
   private static byte[] CHAR_VOID;
   private static byte[] CHAR_KHADOR;
   private static byte[] CHAR_NIVELIAN_SECURITY;
   public static final byte[][] CHAR_IMAGES;
   private static Ship[] ships;
   private static Item[] items;
   private static byte h[][];
   private static int tex_deepscience = 1001;
   private static int tex_void = 1002;
   private static int tex_vossk = 1003;
   private static int tex_midorian = 1004;
   private static int tex_nivelian = 1005;
   private static int tex_pirates = 1006;
   private static int tex_terran = 1007;
   private static int tex_bloodstar = 1008;
   private static int tex_turrets = 1009;
   private static int tex_ships_large = 1010;
   private static int tex_hangar_nivelian = 1011;
   private static int tex_hangar_terran = 1012;
   private static int tex_valkyrie_station = 1013;
   private static int tex_void_station = 1014;
   private static int tex_asteroid = 1015;
   private static int tex_fx = 1016;
   private static int skybox00 = 1017;
   private static int tex_grey = 1018;
   private static int tex_wraith = 1019;
   private static int tex_elite = 1020;
   private static int tex_bluefyre = 1021;
   private static int tex_gatorcustom = 1022;
   private static int tex_amboss = 1023;
   private static int tex_scimitar = 1024;
   private static int tex_station_midorian = 1025;
   private static int tex_station_player_add = 1026;
   private static int tex_station_deescience = 1027;
   private static int tex_station_deescience_add = 1028;
   private static int tex_v_projectiles = 1029;
   private static int tex_main_projectiles = 1030;
   private static int tex_asteroid_ice = 1031;
   private static int tex_asteroid_void = 1032;
   private static int tex_rhino = 1033;
   private static int tex_gryphon = 1034;
   private static int tex_nasrrk = 1035;
   private static int tex_grozamkii = 1036;
   private static int tex_berger_special = 1037;
   private static int tex_kinzer_rs = 1038;
   private static int tex_map_planets = 1039;
   private static int tex_suns = 1040;
   
   private static int tex_planet_0 = 1041;
   private static int tex_planet_1 = 1042;
   private static int tex_planet_2 = 1043;
   private static int tex_planet_3 = 1044;
   private static int tex_planet_4 = 1045;
   private static int tex_planet_5 = 1046;
   private static int tex_planet_6 = 1047;
   private static int tex_planet_7 = 1048;
   private static int tex_planet_8 = 1049;
   private static int tex_planet_9 = 1050;
   private static int tex_planet_10 = 1051;
   private static int tex_planet_11 = 1052;
   private static int tex_planet_12 = 1053;
   private static int tex_planet_13 = 1054;
   private static int tex_planet_14 = 1055;
   private static int tex_planet_15 = 1056;
   private static int tex_planet_16 = 1057;
   private static int tex_planet_17 = 1058;
   private static int tex_planet_18 = 1059;
   private static int tex_planet_19 = 1060;
   private static int tex_planet_20 = 1061;
   private static int tex_planet_21 = 1062;
   private static int tex_planet_22 = 1063;
   private static int tex_planet_23 = 1064;
   private static int tex_planet_24 = 1065;
   private static int tex_planet_25 = 1066;
   private static int tex_planet_26 = 1067;
   private static int tex_planet_27 = 1068;
   private static int tex_planet_28 = 1069;
   private static int tex_planet_29 = 1070;
   private static int tex_bar_terran = 1071;
   private static int tex_bar_nivelian = 1072;
   private static int tex_bar_vossk = 1073;
   private static int tex_bar_midorian = 1074;
   private static int tex_v_galaxymap_planets = 1075;
   private static int tex_sn_galaxymap_planets = 1076;
   private static int tex_sn_sun_011 = 1077;
   private static int tex_gof1 = 1078;
   private static int tex_phantom_xt = 1079;
   private static int tex_tenetared = 1080;
   private static int tex_darkzov = 1081;
   private static int tex_ghost = 1082;
   private static int tex_darkangel = 1083;
   private static int tex_ntirrk = 1084;
   
   private static int skybox01 = 1085;
   private static int skybox02 = 1086;
   private static int skybox03 = 1087;
   private static int skybox04 = 1088;
   private static int skybox05 = 1089;
   private static int skybox06 = 1090;
   private static int skybox07 = 1091;
   private static int skybox08 = 1092;
   private static int skybox09 = 1093;
   private static int skybox10 = 1094;
   
   public static void sub_39(byte[] var0) {
      new FileRead();
   }

   public static void OnInitialize() {
      new FileRead();
      ships = FileRead.loadShipsBinary();
      items = FileRead.loadItemsBinary();
   }

   public static Ship[] getShips() {
      return ships;
   }

   public static Item[] getItems() {
      return items;
   }

   public static String getItemName(int var0) {
      return GlobalStatus.gameText.getNamedParameterItems(var0);
   }

   public static int[] getRaceUVkeyframeId_(int var0) { // окраска (полоска фракции) на модели
      switch(var0) {
      case 2:
         return new int[]{2, 2};
      case 3:
         return new int[]{3, 3};
      case 8:
         return new int[]{0, 0};
      default:
         return new int[]{1, 1};
      }
   }

   public static String getRandomName(int var0, boolean var1) {
      new FileRead();
      String[] var2 = FileRead.loadNamesBinary(var0, var1, true);
      String[] var3 = FileRead.loadNamesBinary(var0, var1, false);
      String var5 = var2 == null?"":var2[GlobalStatus.random.nextInt(var2.length)];
      String var4 = var3 == null?"":var3[GlobalStatus.random.nextInt(var3.length)];
      return var5 + " " + var4;
   }

   public static String getRandomPlanetName() {
      return (new FileRead()).loadStation(GlobalStatus.random.nextInt(100)).getName();
   }

   public static int getRandomEnemyFighter(int race) { // спавн кораблей в зависимости от расы
      int shipID;
	  // 0 - terran
	  // 1 - vossk
	  // 2 - nivelian
	  // 3 - midorian
	  // 4 - multipod
	  // 5 - Cyborg
	  // 6 - bobolan
	  // 7 - grey
	  // 8 - pirate
	  // 9 - void
      if(race == 9) { // 9 = Void
         shipID = 8;
      } else if(race == 1) { // 1 = Vossk
         shipID = getRandomVosskShipID();
      } else if(race == 7) { // 1 = Grey
		  shipID = 42;
	  } else if(race == 0) {
		  shipID = getRandomTerranShipID();
	  } else if(race == 2) {
		  shipID = getRandomNivelianShip();
	  } else if(race == 3) {
		  shipID = getRandomMidorianShip();
	  } else if(race == 8) {
		  shipID = getRandomPirateShip();
	  } else { // продажа остальных кораблей
         race = ships.length;
		 do {
			 shipID = GlobalStatus.random.nextInt(race);
		 } while (!isShipIDValid(shipID));
      }

      return shipID;
   }
   
   
   public static int getShipIDByRaceForSale(int race) { // продажа кораблей в зависимости от расы
      int shipID;
	  // 0 - terran
	  // 1 - vossk
	  // 2 - nivelian
	  // 3 - midorian
	  // 4 - multipod
	  // 5 - Cyborg
	  // 6 - bobolan
	  // 7 - grey
	  // 8 - pirate
	  // 9 - void
      if(race == 1) { // 1 = Vossk
         shipID = getRandomVosskShipID();
      } else { // продажа остальных кораблей
         race = ships.length;
		 do {
			 shipID = GlobalStatus.random.nextInt(race);
		 } while (!isShipIDValid(shipID));
      }

      return shipID;
   }
   
   private static int getRandomTerranShipID() {
	   
	   int[] terranShipID = new int[]{1, 5, 7, 10, 17, 22, 26, 27, 28, 33, 34, 36, 50, 51, 62, 63};
	   int randomIndex = GlobalStatus.random.nextInt(terranShipID.length);
	   return terranShipID[randomIndex];
	   
   }
   
   private static int getRandomVosskShipID() {
	   
	   int[] vosskShipIDs = new int[]{9, 39, 41, 52, 61};
	   int randomIndex = GlobalStatus.random.nextInt(vosskShipIDs.length);
	   return vosskShipIDs[randomIndex];
	   
   }
   
   private static int getRandomNivelianShip() {
	   
	   int[] nivelianShipID = new int[]{4, 12, 16, 18, 21, 31, 35, 43, 59};
	   int randomIndex = GlobalStatus.random.nextInt(nivelianShipID.length);
	   return nivelianShipID[randomIndex];
	   
   }
   
   private static int getRandomMidorianShip() {
	   
	   int[] midorianShipID = new int[]{0, 3, 6, 19, 20, 30};
	   int randomIndex = GlobalStatus.random.nextInt(midorianShipID.length);
	   return midorianShipID[randomIndex];
	   
   }
   
   private static int getRandomPirateShip() {
	   
	   int[] pirateShipID = new int[]{2, 11, 23, 24, 25, 29, 32};
	   int randomIndex = GlobalStatus.random.nextInt(pirateShipID.length);
	   return pirateShipID[randomIndex];
	   
   }
   
   public static int getRandomDeepScienceShip() {
	   
	   int[] DeepScienceShipID = new int[]{37, 38, 40};
	   int randomIndex = GlobalStatus.random.nextInt(DeepScienceShipID.length);
	   return DeepScienceShipID[randomIndex];
	   
   }
   
   private static boolean isShipIDValid(int shipID) { // запрет продажи определенных кораблей, если они не приписаны к расе
	   return shipID != 8 && shipID != 9 && shipID != 13 && shipID != 14 && shipID != 15 && shipID != 37 && shipID != 38 && shipID != 40 && shipID != 44 && shipID != 45 && shipID != 46 && shipID != 47 && shipID != 48 && shipID != 49 && shipID != 53 && shipID != 54 && shipID != 55 && shipID != 56 && shipID != 57 && shipID != 58;
   }

   public static Group getShipGroup(int var0, int var1) {
      Group var2 = new Group();
      new FileRead();
	  TYPE_WEAPONS = FileRead.readWeaponProjectile(1);
      int[] var3 = FileRead.loadShipParts(var0);
      int[] var6 = getRaceUVkeyframeId_(var1);

      for(int var4 = 0; var4 < var3.length; var4 += 10) {
         AbstractMesh var5;
         if((var5 = AEResourceManager.getGeometryResource(var3[var4])).getID() >= 13064 && var5.getID() <= 13071) {
            var5.setAnimationMode((byte)2);
         } else {
            var5.setAnimationRangeInTime(var6[0], var6[1]);
            var5.disableAnimation();
         }

         if(var5.getID() == 2509 || var5.getID() == 13261 || var5.getID() == 6755 || var5.getID() == 6756 || var5.getID() == 6760 || var5.getID() == 6761 || var5.getID() == 6762 || var5.getID() == 6763 || var5.getID() == 6764 || var5.getID() == 6765 || var5.getID() == 3334 || var5.getID() == 3335 || var5.getID() == 6788 || var5.getID() == 6789 || var5.getID() == 6790 || var5.getID() == 6791 || var5.getID() == 6792 || var5.getID() == 6793 || var5.getID() == 6794 || var5.getID() == 6795 || var5.getID() == 6796 || var5.getID() == 6797 || var5.getID() == 13067 || var5.getID() == 13068 || var5.getID() == 13070 || var5.getID() == 13064 || var5.getID() == 14072 || var5.getID() == 13065 || var5.getID() == 13071 || var5.getID() == 13061 || var5.getID() == 13063 || var0 == 14 || var0 == 13 || var0 == 15 || var5.getID() >= 13001 && var5.getID() <= 14000 || var5.getID() >= 20000 && var5.getID() <= 20100) {
            var5.moveTo(var3[var4 + 1], var3[var4 + 2], var3[var4 + 3]);
            var5.setRotation(var3[var4 + 4], var3[var4 + 5], var3[var4 + 6]);
            var5.setScale(var3[var4 + 7], var3[var4 + 8], var3[var4 + 9]);
         }

         var5.setRenderLayer(2);
         var5.setDraw(true); // Показывать модели кораблей?
         var2.uniqueAppend_(var5);
      }

      return var2;
   }

   public static void buildShip(Group var0, int var1) {
      new FileRead();
      int[] var4;
      int var2 = (var4 = FileRead.loadShipParts(var1)).length - 10;

      for(GraphNode var3 = var0.getEndNode(); var3 != null; var3 = var3.getParent()) {
         var3.moveTo(var4[var2 + 1], var4[var2 + 2], var4[var2 + 3]);
         var3.setRotation(var4[var2 + 4], var4[var2 + 5], var4[var2 + 6]);
         var3.setScale(var4[var2 + 7], var4[var2 + 8], var4[var2 + 9]);
         var2 -= 10;
      }

   }
   
   public static void loadScripts() {
	   
	   AEResourceManager.setText(1, "/Configs/master_config.txt");
	   AEResourceManager.setText(2, "/Configs/agents.txt");
	   AEResourceManager.setText(3, "/Configs/items.txt");
	   AEResourceManager.setText(4, "/Configs/projectiles.txt");
	   AEResourceManager.setText(5, "/Configs/shipparts.txt");
	   AEResourceManager.setText(6, "/Configs/ships.txt");
	   AEResourceManager.setText(7, "/Configs/ships_turrets.txt");
	   AEResourceManager.setText(8, "/Configs/ships_viewport.txt");
	   AEResourceManager.setText(9, "/Configs/stationparts.txt");
	   AEResourceManager.setText(10, "/Configs/stations.txt");
	   AEResourceManager.setText(11, "/Configs/systems.txt");
	   AEResourceManager.setText(12, "/Configs/interface.txt");
	   AEResourceManager.setText(100, "/Configs/names_terran_0_m.txt");
	   AEResourceManager.setText(101, "/Configs/names_terran_0_w.txt");
	   AEResourceManager.setText(102, "/Configs/names_terran_1.txt");
	   AEResourceManager.setText(103, "/Configs/names_vossk_0.txt");
	   AEResourceManager.setText(104, "/Configs/names_vossk_1.txt");
	   AEResourceManager.setText(105, "/Configs/names_nivelian_0.txt");
	   AEResourceManager.setText(106, "/Configs/names_nivelian_1.txt");
	   AEResourceManager.setText(107, "/Configs/names_multipod_0.txt");
	   AEResourceManager.setText(108, "/Configs/names_multipod_1.txt");
	   AEResourceManager.setText(109, "/Configs/names_cyborg_0.txt");
	   AEResourceManager.setText(110, "/Configs/names_bobolan_0.txt");
	   AEResourceManager.setText(111, "/Configs/names_bobolan_1.txt");
	   AEResourceManager.setText(112, "/Configs/names_grey_0.txt");

   }
   
   public static void loadImages() {
	   
	   AEResourceManager.setImage(0, "/Resource/interface/empty.png");
	   AEResourceManager.setImage(1, "/Resource/interface/ship_interface.png");
	   
	   AEResourceManager.setImage(3, "/Resource/interface/items.png");
	   AEResourceManager.setImage(14, "/Resource/interface/logo_0.png");
	   AEResourceManager.setImage(15, "/Resource/interface/logo_1.png");
	   AEResourceManager.setImage(16, "/Resource/interface/logo_2.png");
	   AEResourceManager.setImage(17, "/Resource/interface/logo_3.png");
	   AEResourceManager.setImage(18, "/Resource/interface/logo_4.png");
	   AEResourceManager.setImage(19, "/Resource/interface/logo_5.png");
	   AEResourceManager.setImage(20, "/Resource/interface/logo_6.png");
	   AEResourceManager.setImage(21, "/Resource/interface/logo_7.png");
	   AEResourceManager.setImage(22, "/Resource/interface/logo_8.png");
	   AEResourceManager.setImage(23, "/Resource/interface/logo_9.png");
	   
	   AEResourceManager.setImage(54, "/Resource/interface/interface.png");
	   
	   AEResourceManager.setImage(60, "/Resource/textures/fog_galaxy_low.png");
	   AEResourceManager.setImage(61, "/Resource/textures/fog_galaxy_high.png");
	   
	   AEResourceManager.setImage(68, "/Resource/interface/standing_small_0.png");
	   AEResourceManager.setImage(69, "/Resource/interface/standing_0.png");
	   AEResourceManager.setImage(70, "/Resource/interface/standing_small_1.png");
	   AEResourceManager.setImage(71, "/Resource/interface/standing_1.png");
	   AEResourceManager.setImage(72, "/Resource/interface/standing_small_2.png");
	   AEResourceManager.setImage(73, "/Resource/interface/standing_2.png");
	   AEResourceManager.setImage(74, "/Resource/interface/faces/Brille_golden.png");
	   AEResourceManager.setImage(75, "/Resource/interface/faces/Brille_schwarz.png");
	   
	   
	   for(int ships = 0; ships < GlobalStatus.max_ships; ++ships) {
		   AEResourceManager.setImage(9000 + ships, "/Resource/interface/ships/ship_" + ships + ".png");
	   }
	   
	   for(int lens = 0; lens < 4; ++lens) {
		   AEResourceManager.setImage(9200 + lens, "/Resource/interface/lens" + lens + ".png");
	   }
	   
	   for(int suns = 0; suns < 12; ++suns) {
		   AEResourceManager.setImage(9400 + suns, "/Resource/textures/sun/sun_" + suns + ".png");
	   }
	   
	   int RECT_ROUNDED_BUTTON_NORMAL_START_X = FileRead.loadInterface(0, 1);
	   int RECT_ROUNDED_BUTTON_NORMAL_START_Y = FileRead.loadInterface(0, 2);
	   int RECT_ROUNDED_BUTTON_NORMAL_WIDTH = FileRead.loadInterface(0, 3);
	   int RECT_ROUNDED_BUTTON_NORMAL_HEIGHT = FileRead.loadInterface(0, 4);
	   
	   int RECT_ROUNDED_BUTTON_PRESSED_START_X = FileRead.loadInterface(1, 1);
	   int RECT_ROUNDED_BUTTON_PRESSED_START_Y = FileRead.loadInterface(1, 2);
	   int RECT_ROUNDED_BUTTON_PRESSED_WIDTH = FileRead.loadInterface(1, 3);
	   int RECT_ROUNDED_BUTTON_PRESSED_HEIGHT = FileRead.loadInterface(1, 4);
	   
	   int RECT_ROUNDED_SMALL_BUTTON_NORMAL_START_X = FileRead.loadInterface(2, 1); 
	   int RECT_ROUNDED_SMALL_BUTTON_NORMAL_START_y = FileRead.loadInterface(2, 2);
	   int RECT_ROUNDED_SMALL_BUTTON_NORMAL_WIDTH = FileRead.loadInterface(2, 3);
	   int RECT_ROUNDED_SMALL_BUTTON_NORMAL_HEIGHT = FileRead.loadInterface(2, 4);
	   
	   int RECT_ROUNDED_SMALL_BUTTON_PRESSED_START_X = FileRead.loadInterface(3, 1);
	   int RECT_ROUNDED_SMALL_BUTTON_PRESSED_START_Y = FileRead.loadInterface(3, 2);
	   int RECT_ROUNDED_SMALL_BUTTON_PRESSED_WIDTH = FileRead.loadInterface(3, 3);
	   int RECT_ROUNDED_SMALL_BUTTON_PRESSED_HEIGHT = FileRead.loadInterface(3, 4);
	   
	   int RECT_WIDE_BUTTON_NORMAL_START_X = FileRead.loadInterface(4, 1);
	   int RECT_WIDE_BUTTON_NORMAL_START_Y = FileRead.loadInterface(4, 2);
	   int RECT_WIDE_BUTTON_NORMAL_WIDTH = FileRead.loadInterface(4, 3);
	   int RECT_WIDE_BUTTON_NORMAL_HEIGHT = FileRead.loadInterface(4, 4);
	   
	   int RECT_WIDE_BUTTON_PRESSED_START_X = FileRead.loadInterface(5, 1);
	   int RECT_WIDE_BUTTON_PRESSED_START_Y = FileRead.loadInterface(5, 2);
	   int RECT_WIDE_BUTTON_PRESSED_WIDTH = FileRead.loadInterface(5, 3);
	   int RECT_WIDE_BUTTON_PRESSED_HEIGHT = FileRead.loadInterface(5, 4);
	   
	   int RECT_ROUNDED_BUTTON_INACTIVE_START_X = FileRead.loadInterface(6, 1);
	   int RECT_ROUNDED_BUTTON_INACTIVE_START_Y = FileRead.loadInterface(6, 2);
	   int RECT_ROUNDED_BUTTON_INACTIVE_WIDTH = FileRead.loadInterface(6, 3);
	   int RECT_ROUNDED_BUTTON_INACTIVE_HEIGHT = FileRead.loadInterface(6, 4);
	   
	   int DEPART_BUTTON_NORMAL_START_X = FileRead.loadInterface(7, 1);
	   int DEPART_BUTTON_NORMAL_START_Y = FileRead.loadInterface(7, 2);
	   int DEPART_BUTTON_NORMAL_WIDTH = FileRead.loadInterface(7, 3);
	   int DEPART_BUTTON_NORMAL_HEIGHT = FileRead.loadInterface(7, 4);
	   
	   int DEPART_BUTTON_PRESSED_START_X = FileRead.loadInterface(8, 1);
	   int DEPART_BUTTON_PRESSED_START_Y = FileRead.loadInterface(8, 2);
	   int DEPART_BUTTON_PRESSED_WIDTH = FileRead.loadInterface(8, 3);
	   int DEPART_BUTTON_PRESSED_HEIGHT = FileRead.loadInterface(8, 4);
	   
	   int JOYSTICK_BACKGROUND_START_X = FileRead.loadInterface(9, 1);
	   int JOYSTICK_BACKGROUND_START_Y = FileRead.loadInterface(9, 2);
	   int JOYSTICK_BACKGROUND_WIDTH = FileRead.loadInterface(9, 3);
	   int JOYSTICK_BACKGROUND_HEIGHT = FileRead.loadInterface(9, 4);
	   
	   int JOYSTICK_NORMAL_START_X = FileRead.loadInterface(10, 1);
	   int JOYSTICK_NORMAL_START_Y = FileRead.loadInterface(10, 2);
	   int JOYSTICK_NORMAL_WIDTH = FileRead.loadInterface(10, 3);
	   int JOYSTICK_NORMAL_HEIGHT = FileRead.loadInterface(10, 4);
	   
	   int JOYSTICK_PRESSED_START_X = FileRead.loadInterface(11, 1);
	   int JOYSTICK_PRESSED_START_Y = FileRead.loadInterface(11, 2);
	   int JOYSTICK_PRESSED_WIDTH = FileRead.loadInterface(11, 3);
	   int JOYSTICK_PRESSED_HEIGHT = FileRead.loadInterface(11, 4);
	   
	   int PANEL_INFO_UPPER_START_X = FileRead.loadInterface(12, 1);
	   int PANEL_INFO_UPPER_START_Y = FileRead.loadInterface(12, 2);
	   int PANEL_INFO_UPPER_WIDTH = FileRead.loadInterface(12, 3);
	   int PANEL_INFO_UPPER_HEIGHT = FileRead.loadInterface(12, 4);
	   
	   int HUD_LOCKON_NEUTRAL_START_X = FileRead.loadInterface(13, 1);
	   int HUD_LOCKON_NEUTRAL_START_Y = FileRead.loadInterface(13, 2);
	   int HUD_LOCKON_NEUTRAL_WIDTH = FileRead.loadInterface(13, 3);
	   int HUD_LOCKON_NEUTRAL_HEIGHT = FileRead.loadInterface(13, 4);
	   
	   int HUD_RADARICON_NEUTRAL_START_X = FileRead.loadInterface(14, 1);
	   int HUD_RADARICON_NEUTRAL_START_Y = FileRead.loadInterface(14, 2);
	   int HUD_RADARICON_NEUTRAL_WIDTH = FileRead.loadInterface(14, 3);
	   int HUD_RADARICON_NEUTRAL_HEIGHT = FileRead.loadInterface(14, 4);
	   
	   int HUD_LOCKON_NEUTRAL_FAR_START_X = FileRead.loadInterface(15, 1);
	   int HUD_LOCKON_NEUTRAL_FAR_START_Y = FileRead.loadInterface(15, 2);
	   int HUD_LOCKON_NEUTRAL_FAR_WIDTH = FileRead.loadInterface(15, 3);
	   int HUD_LOCKON_NEUTRAL_FAR_HEIGHT = FileRead.loadInterface(15, 4);
	   
	   int HUD_LOCKON_ENEMY_START_X = FileRead.loadInterface(16, 1);
	   int HUD_LOCKON_ENEMY_START_Y = FileRead.loadInterface(16, 2);
	   int HUD_LOCKON_ENEMY_WIDTH = FileRead.loadInterface(16, 3);
	   int HUD_LOCKON_ENEMY_HEIGHT = FileRead.loadInterface(16, 4);
	   
	   int HUD_RADARICON_ENEMY_START_X = FileRead.loadInterface(17, 1);
	   int HUD_RADARICON_ENEMY_START_Y = FileRead.loadInterface(17, 2);
	   int HUD_RADARICON_ENEMY_WIDTH = FileRead.loadInterface(17, 3);
	   int HUD_RADARICON_ENEMY_HEIGHT = FileRead.loadInterface(17, 4);
	   
	   int HUD_LOCKON_ENEMY_FAR_START_X = FileRead.loadInterface(18, 1);
	   int HUD_LOCKON_ENEMY_FAR_START_Y = FileRead.loadInterface(18, 2);
	   int HUD_LOCKON_ENEMY_FAR_WIDTH = FileRead.loadInterface(18, 3);
	   int HUD_LOCKON_ENEMY_FAR_HEIGHT = FileRead.loadInterface(18, 4);
	   
	   int HUD_LOCKON_FRIEND_START_X = FileRead.loadInterface(19, 1);
	   int HUD_LOCKON_FRIEND_START_Y = FileRead.loadInterface(19, 2);
	   int HUD_LOCKON_FRIEND_WIDTH = FileRead.loadInterface(19, 3);
	   int HUD_LOCKON_FRIEND_HEIGHT = FileRead.loadInterface(19, 4);
	   
	   int HUD_RADARICON_FRIEND_START_X = FileRead.loadInterface(20, 1);
	   int HUD_RADARICON_FRIEND_START_Y = FileRead.loadInterface(20, 2);
	   int HUD_RADARICON_FRIEND_WIDTH = FileRead.loadInterface(20, 3);
	   int HUD_RADARICON_FRIEND_HEIGHT = FileRead.loadInterface(20, 4);
	   
	   int HUD_LOCKON_FRIEND_FAR_START_X = FileRead.loadInterface(21, 1);
	   int HUD_LOCKON_FRIEND_FAR_START_Y = FileRead.loadInterface(21, 2);
	   int HUD_LOCKON_FRIEND_FAR_WIDTH = FileRead.loadInterface(21, 3);
	   int HUD_LOCKON_FRIEND_FAR_HEIGHT = FileRead.loadInterface(21, 4);
	   
	   int HUD_RADARICON_WAYPOINT_START_X = FileRead.loadInterface(22, 1);
	   int HUD_RADARICON_WAYPOINT_START_Y = FileRead.loadInterface(22, 2);
	   int HUD_RADARICON_WAYPOINT_WIDTH = FileRead.loadInterface(22, 3);
	   int HUD_RADARICON_WAYPOINT_HEIGHT = FileRead.loadInterface(22, 4);
	   
	   int HUD_LOCKON_WAYPOINT_START_X = FileRead.loadInterface(23, 1);
	   int HUD_LOCKON_WAYPOINT_START_Y = FileRead.loadInterface(23, 2);
	   int HUD_LOCKON_WAYPOINT_WIDTH = FileRead.loadInterface(23, 3);
	   int HUD_LOCKON_WAYPOINT_HEIGHT = FileRead.loadInterface(23, 4);
	   
	   int HUD_BARS_START_X = FileRead.loadInterface(24, 1);
	   int HUD_BARS_START_Y = FileRead.loadInterface(24, 2);
	   int HUD_BARS_WIDTH = FileRead.loadInterface(24, 3);
	   int HUD_BARS_HEIGHT = FileRead.loadInterface(24, 4);
	   
	   int LOGOS_SMALL_START_X = FileRead.loadInterface(25, 1);
	   int LOGOS_SMALL_START_Y = FileRead.loadInterface(25, 2);
	   int LOGOS_SMALL_WIDTH = FileRead.loadInterface(25, 3);
	   int LOGOS_SMALL_HEIGHT = FileRead.loadInterface(25, 4);
	   
	   int BRACKET_BOX_START_X = FileRead.loadInterface(26, 1);
	   int BRACKET_BOX_START_Y = FileRead.loadInterface(26, 2);
	   int BRACKET_BOX_WIDTH = FileRead.loadInterface(26, 3);
	   int BRACKET_BOX_HEIGHT = FileRead.loadInterface(26, 4);
	   
	   int HUD_CRATE_START_X = FileRead.loadInterface(27, 1);
	   int HUD_CRATE_START_Y = FileRead.loadInterface(27, 2);
	   int HUD_CRATE_WIDTH = FileRead.loadInterface(27, 3);
	   int HUD_CRATE_HEIGHT = FileRead.loadInterface(27, 4);
	   
	   int HUD_SPACEJUNK_START_X = FileRead.loadInterface(28, 1);
	   int HUD_SPACEJUNK_START_Y = FileRead.loadInterface(28, 2);
	   int HUD_SPACEJUNK_WIDTH = FileRead.loadInterface(28, 3);
	   int HUD_SPACEJUNK_HEIGHT = FileRead.loadInterface(28, 4);
	   
	   int HUD_ASTEROID_START_X = FileRead.loadInterface(29, 1);
	   int HUD_ASTEROID_START_Y = FileRead.loadInterface(29, 2);
	   int HUD_ASTEROID_WIDTH = FileRead.loadInterface(29, 3);
	   int HUD_ASTEROID_HEIGHT = FileRead.loadInterface(29, 4);
	   
	   int HUD_CRATE_VOID_START_X = FileRead.loadInterface(30, 1);
	   int HUD_CRATE_VOID_START_Y = FileRead.loadInterface(30, 2);
	   int HUD_CRATE_VOID_WIDTH = FileRead.loadInterface(30, 3);
	   int HUD_CRATE_VOID_HEIGHT = FileRead.loadInterface(30, 4);
	   
	   int HUD_VORTEX_START_X = FileRead.loadInterface(31, 1);
	   int HUD_VORTEX_START_Y = FileRead.loadInterface(31, 2);
	   int HUD_VORTEX_WIDTH = FileRead.loadInterface(31, 3);
	   int HUD_VORTEX_HEIGHT = FileRead.loadInterface(31, 4);
	   
	   int MENU_MAP_JUMPGATE_START_X = FileRead.loadInterface(32, 1);
	   int MENU_MAP_JUMPGATE_START_Y = FileRead.loadInterface(32, 2);
	   int MENU_MAP_JUMPGATE_WIDTH = FileRead.loadInterface(32, 3);
	   int MENU_MAP_JUMPGATE_HEIGHT = FileRead.loadInterface(32, 4);
	   
	   int HUD_SCANPROCESS_ANIM_START_X = FileRead.loadInterface(33, 1);
	   int HUD_SCANPROCESS_ANIM_START_Y = FileRead.loadInterface(33, 2);
	   int HUD_SCANPROCESS_ANIM_WIDTH = FileRead.loadInterface(33, 3);
	   int HUD_SCANPROCESS_ANIM_HEIGHT = FileRead.loadInterface(33, 4);
	   
	   int HUD_METEOR_CLASS_START_X = FileRead.loadInterface(34, 1);
	   int HUD_METEOR_CLASS_START_Y = FileRead.loadInterface(34, 2);
	   int HUD_METEOR_CLASS_WIDTH = FileRead.loadInterface(34, 3);
	   int HUD_METEOR_CLASS_HEIGHT = FileRead.loadInterface(34, 4);
	   
	   int MENU_MAP_SIDEMISSION_START_X = FileRead.loadInterface(35, 1);
	   int MENU_MAP_SIDEMISSION_START_Y = FileRead.loadInterface(35, 2);
	   int MENU_MAP_SIDEMISSION_WIDTH = FileRead.loadInterface(35, 3);
	   int MENU_MAP_SIDEMISSION_HEIGHT = FileRead.loadInterface(35, 4);
	   
	   int MENU_MAP_MAINMISSION_START_X = FileRead.loadInterface(36, 1);
	   int MENU_MAP_MAINMISSION_START_Y = FileRead.loadInterface(36, 2);
	   int MENU_MAP_MAINMISSION_WIDTH = FileRead.loadInterface(36, 3);
	   int MENU_MAP_MAINMISSION_HEIGHT = FileRead.loadInterface(36, 4);
	   
	   int MENU_MAP_BLUEPRINT_START_X = FileRead.loadInterface(37, 1);
	   int MENU_MAP_BLUEPRINT_START_Y = FileRead.loadInterface(37, 2);
	   int MENU_MAP_BLUEPRINT_WIDTH = FileRead.loadInterface(37, 3);
	   int MENU_MAP_BLUEPRINT_HEIGHT = FileRead.loadInterface(37, 4);
	   
	   int MENU_MAP_VISITED_START_X = FileRead.loadInterface(38, 1);
	   int MENU_MAP_VISITED_START_Y = FileRead.loadInterface(38, 2);
	   int MENU_MAP_VISITED_WIDTH = FileRead.loadInterface(38, 3);
	   int MENU_MAP_VISITED_HEIGHT = FileRead.loadInterface(38, 4);
	   
	   int MENU_MAP_DIRECTION_START_X = FileRead.loadInterface(39, 1);
	   int MENU_MAP_DIRECTION_START_Y = FileRead.loadInterface(39, 2);
	   int MENU_MAP_DIRECTION_WIDTH = FileRead.loadInterface(39, 3);
	   int MENU_MAP_DIRECTION_HEIGHT = FileRead.loadInterface(39, 4);
	   
	   int MAP_SUN_GLOW_START_X = FileRead.loadInterface(40, 1);
	   int MAP_SUN_GLOW_START_Y = FileRead.loadInterface(40, 2);
	   int MAP_SUN_GLOW_WIDTH = FileRead.loadInterface(40, 3);
	   int MAP_SUN_GLOW_HEIGHT = FileRead.loadInterface(40, 4);
	   
	   int MEDALS_ON_START_X = FileRead.loadInterface(41, 1);
	   int MEDALS_ON_START_Y = FileRead.loadInterface(41, 2);
	   int MEDALS_ON_WIDTH = FileRead.loadInterface(41, 3);
	   int MEDALS_ON_HEIGHT = FileRead.loadInterface(41, 4);
	   
	   int MEDALS_START_X = FileRead.loadInterface(42, 1);
	   int MEDALS_START_Y = FileRead.loadInterface(42, 2);
	   int MEDALS_WIDTH = FileRead.loadInterface(42, 3);
	   int MEDALS_HEIGHT = FileRead.loadInterface(42, 4);
	   
	   int FLAGGEN_START_X = FileRead.loadInterface(43, 1);
	   int FLAGGEN_START_Y = FileRead.loadInterface(43, 2);
	   int FLAGGEN_WIDTH = FileRead.loadInterface(43, 3);
	   int FLAGGEN_HEIGHT = FileRead.loadInterface(43, 4);
	   
	   interfaceImage = AEResourceManager.getImage(54);
	   shipInterfaceImage = AEResourceManager.getImage(1);
	   itemsImage = AEResourceManager.getImage(3);
	   
	   rectRoundedButtonNormal = AEFile.resize_image(AEFile.cutImageRegion(interfaceImage, RECT_ROUNDED_BUTTON_NORMAL_START_X, RECT_ROUNDED_BUTTON_NORMAL_START_Y, RECT_ROUNDED_BUTTON_NORMAL_WIDTH, RECT_ROUNDED_BUTTON_NORMAL_HEIGHT), RECT_ROUNDED_BUTTON_NORMAL_WIDTH * GlobalStatus.INTERFACE_SCALE_MULTIPLIER, RECT_ROUNDED_BUTTON_NORMAL_HEIGHT * GlobalStatus.INTERFACE_SCALE_MULTIPLIER);
	   rectRoundedButtonPressed = AEFile.resize_image(AEFile.cutImageRegion(interfaceImage, RECT_ROUNDED_BUTTON_PRESSED_START_X, RECT_ROUNDED_BUTTON_PRESSED_START_Y, RECT_ROUNDED_BUTTON_PRESSED_WIDTH, RECT_ROUNDED_BUTTON_PRESSED_HEIGHT), RECT_ROUNDED_BUTTON_PRESSED_WIDTH * GlobalStatus.INTERFACE_SCALE_MULTIPLIER, RECT_ROUNDED_BUTTON_PRESSED_HEIGHT * GlobalStatus.INTERFACE_SCALE_MULTIPLIER);
	   
	   rectRoundedSmallButtonNormal = AEFile.resize_image(AEFile.cutImageRegion(interfaceImage, RECT_ROUNDED_SMALL_BUTTON_NORMAL_START_X, RECT_ROUNDED_SMALL_BUTTON_NORMAL_START_y, RECT_ROUNDED_SMALL_BUTTON_NORMAL_WIDTH, RECT_ROUNDED_SMALL_BUTTON_NORMAL_HEIGHT), RECT_ROUNDED_SMALL_BUTTON_NORMAL_WIDTH * GlobalStatus.INTERFACE_SCALE_MULTIPLIER, RECT_ROUNDED_SMALL_BUTTON_NORMAL_HEIGHT * GlobalStatus.INTERFACE_SCALE_MULTIPLIER);
	   rectRoundedSmallButtonPressed = AEFile.resize_image(AEFile.cutImageRegion(interfaceImage, RECT_ROUNDED_SMALL_BUTTON_PRESSED_START_X, RECT_ROUNDED_SMALL_BUTTON_PRESSED_START_Y, RECT_ROUNDED_SMALL_BUTTON_PRESSED_WIDTH, RECT_ROUNDED_SMALL_BUTTON_PRESSED_HEIGHT), RECT_ROUNDED_SMALL_BUTTON_PRESSED_WIDTH * GlobalStatus.INTERFACE_SCALE_MULTIPLIER, RECT_ROUNDED_SMALL_BUTTON_PRESSED_HEIGHT * GlobalStatus.INTERFACE_SCALE_MULTIPLIER);
	   
	   rectWideButtonNormal = AEFile.resize_image(AEFile.cutImageRegion(interfaceImage, RECT_WIDE_BUTTON_NORMAL_START_X, RECT_WIDE_BUTTON_NORMAL_START_Y, RECT_WIDE_BUTTON_NORMAL_WIDTH, RECT_WIDE_BUTTON_NORMAL_HEIGHT), GlobalStatus.var_e75, RECT_WIDE_BUTTON_NORMAL_HEIGHT * GlobalStatus.INTERFACE_SCALE_MULTIPLIER);
	   rectWideButtonPressed = AEFile.resize_image(AEFile.cutImageRegion(interfaceImage, RECT_WIDE_BUTTON_PRESSED_START_X, RECT_WIDE_BUTTON_PRESSED_START_Y, RECT_WIDE_BUTTON_PRESSED_WIDTH, RECT_WIDE_BUTTON_PRESSED_HEIGHT), GlobalStatus.var_e75, RECT_WIDE_BUTTON_PRESSED_HEIGHT * GlobalStatus.INTERFACE_SCALE_MULTIPLIER);
	   
	   rectRoundedButtonInactive = AEFile.resize_image(AEFile.cutImageRegion(interfaceImage, RECT_ROUNDED_BUTTON_INACTIVE_START_X, RECT_ROUNDED_BUTTON_INACTIVE_START_Y, RECT_ROUNDED_BUTTON_INACTIVE_WIDTH, RECT_ROUNDED_BUTTON_INACTIVE_HEIGHT), RECT_ROUNDED_BUTTON_INACTIVE_WIDTH * GlobalStatus.INTERFACE_SCALE_MULTIPLIER, RECT_ROUNDED_BUTTON_INACTIVE_HEIGHT * GlobalStatus.INTERFACE_SCALE_MULTIPLIER);
	   
	   departButtonNormal = AEFile.resize_image(AEFile.cutImageRegion(interfaceImage, DEPART_BUTTON_NORMAL_START_X, DEPART_BUTTON_NORMAL_START_Y, DEPART_BUTTON_NORMAL_WIDTH, DEPART_BUTTON_NORMAL_HEIGHT), DEPART_BUTTON_NORMAL_WIDTH * GlobalStatus.INTERFACE_SCALE_MULTIPLIER, DEPART_BUTTON_NORMAL_HEIGHT * GlobalStatus.INTERFACE_SCALE_MULTIPLIER);
	   departButtonPressed = AEFile.resize_image(AEFile.cutImageRegion(interfaceImage, DEPART_BUTTON_PRESSED_START_X, DEPART_BUTTON_PRESSED_START_Y, DEPART_BUTTON_PRESSED_WIDTH, DEPART_BUTTON_PRESSED_HEIGHT), DEPART_BUTTON_PRESSED_WIDTH * GlobalStatus.INTERFACE_SCALE_MULTIPLIER, DEPART_BUTTON_PRESSED_HEIGHT * GlobalStatus.INTERFACE_SCALE_MULTIPLIER);
	   
	   joystickBackground = AEFile.resize_image(AEFile.cutImageRegion(shipInterfaceImage, JOYSTICK_BACKGROUND_START_X, JOYSTICK_BACKGROUND_START_Y, JOYSTICK_BACKGROUND_WIDTH, JOYSTICK_BACKGROUND_HEIGHT), JOYSTICK_BACKGROUND_WIDTH * GlobalStatus.INTERFACE_SCALE_MULTIPLIER, JOYSTICK_BACKGROUND_HEIGHT * GlobalStatus.INTERFACE_SCALE_MULTIPLIER);
	   joystickNormal = AEFile.resize_image(AEFile.cutImageRegion(shipInterfaceImage, JOYSTICK_NORMAL_START_X, JOYSTICK_NORMAL_START_Y, JOYSTICK_NORMAL_WIDTH, JOYSTICK_NORMAL_HEIGHT), JOYSTICK_NORMAL_WIDTH * GlobalStatus.INTERFACE_SCALE_MULTIPLIER, JOYSTICK_NORMAL_HEIGHT * GlobalStatus.INTERFACE_SCALE_MULTIPLIER);
	   joystickPressed = AEFile.resize_image(AEFile.cutImageRegion(shipInterfaceImage, JOYSTICK_PRESSED_START_X, JOYSTICK_PRESSED_START_Y, JOYSTICK_PRESSED_WIDTH, JOYSTICK_PRESSED_HEIGHT), JOYSTICK_PRESSED_WIDTH * GlobalStatus.INTERFACE_SCALE_MULTIPLIER, JOYSTICK_PRESSED_HEIGHT * GlobalStatus.INTERFACE_SCALE_MULTIPLIER);
	   
	   panelInfoUpper = AEFile.cutImageRegion(shipInterfaceImage, PANEL_INFO_UPPER_START_X, PANEL_INFO_UPPER_START_Y, PANEL_INFO_UPPER_WIDTH, PANEL_INFO_UPPER_HEIGHT);
	   hudLockonNeutral = AEFile.cutImageRegion(shipInterfaceImage, HUD_LOCKON_NEUTRAL_START_X, HUD_LOCKON_NEUTRAL_START_Y, HUD_LOCKON_NEUTRAL_WIDTH, HUD_LOCKON_NEUTRAL_HEIGHT);
	   hudRadarIconNeutral = AEFile.cutImageRegion(shipInterfaceImage, HUD_RADARICON_NEUTRAL_START_X, HUD_RADARICON_NEUTRAL_START_Y, HUD_RADARICON_NEUTRAL_WIDTH, HUD_RADARICON_NEUTRAL_HEIGHT);
	   hudLockonNeutralFar = AEFile.cutImageRegion(shipInterfaceImage, HUD_LOCKON_NEUTRAL_FAR_START_X, HUD_LOCKON_NEUTRAL_FAR_START_Y, HUD_LOCKON_NEUTRAL_FAR_WIDTH, HUD_LOCKON_NEUTRAL_FAR_HEIGHT);
	   hudLockonEnemy = AEFile.cutImageRegion(shipInterfaceImage, HUD_LOCKON_ENEMY_START_X, HUD_LOCKON_ENEMY_START_Y, HUD_LOCKON_ENEMY_WIDTH, HUD_LOCKON_ENEMY_HEIGHT);
	   hudRadarIconEnemy = AEFile.cutImageRegion(shipInterfaceImage, HUD_RADARICON_ENEMY_START_X, HUD_RADARICON_ENEMY_START_Y, HUD_RADARICON_ENEMY_WIDTH, HUD_RADARICON_ENEMY_HEIGHT);
	   hudLockonEnemyFar = AEFile.cutImageRegion(shipInterfaceImage, HUD_LOCKON_ENEMY_FAR_START_X, HUD_LOCKON_ENEMY_FAR_START_Y, HUD_LOCKON_ENEMY_FAR_WIDTH, HUD_LOCKON_ENEMY_FAR_HEIGHT);
	   hudLockonFriend = AEFile.cutImageRegion(shipInterfaceImage, HUD_LOCKON_FRIEND_START_X, HUD_LOCKON_FRIEND_START_Y, HUD_LOCKON_FRIEND_WIDTH, HUD_LOCKON_FRIEND_HEIGHT);
	   hudRadarIconFirend = AEFile.cutImageRegion(shipInterfaceImage, HUD_RADARICON_FRIEND_START_X, HUD_RADARICON_FRIEND_START_Y, HUD_RADARICON_FRIEND_WIDTH, HUD_RADARICON_FRIEND_HEIGHT);
	   hudLockonFriendFar = AEFile.cutImageRegion(shipInterfaceImage, HUD_LOCKON_FRIEND_FAR_START_X, HUD_LOCKON_FRIEND_FAR_START_Y, HUD_LOCKON_FRIEND_FAR_WIDTH, HUD_LOCKON_FRIEND_FAR_HEIGHT);
	   hudWaypoint = AEFile.cutImageRegion(shipInterfaceImage, HUD_RADARICON_WAYPOINT_START_X, HUD_RADARICON_WAYPOINT_START_Y, HUD_RADARICON_WAYPOINT_WIDTH, HUD_RADARICON_WAYPOINT_HEIGHT);
	   hudLockonWaypoint = AEFile.cutImageRegion(shipInterfaceImage, HUD_LOCKON_WAYPOINT_START_X, HUD_LOCKON_WAYPOINT_START_Y, HUD_LOCKON_WAYPOINT_WIDTH, HUD_LOCKON_WAYPOINT_HEIGHT);
	   hudBars = AEFile.cutImageRegion(shipInterfaceImage, HUD_BARS_START_X, HUD_BARS_START_Y, HUD_BARS_WIDTH, HUD_BARS_HEIGHT);
	   logosSmall = AEFile.cutImageRegion(shipInterfaceImage, LOGOS_SMALL_START_X, LOGOS_SMALL_START_Y, LOGOS_SMALL_WIDTH, LOGOS_SMALL_HEIGHT);
	   bracketBox = AEFile.cutImageRegion(shipInterfaceImage, BRACKET_BOX_START_X, BRACKET_BOX_START_Y, BRACKET_BOX_WIDTH, BRACKET_BOX_HEIGHT);
	   hudCrate = AEFile.cutImageRegion(shipInterfaceImage, HUD_CRATE_START_X, HUD_CRATE_START_Y, HUD_CRATE_WIDTH, HUD_CRATE_HEIGHT);
	   hudSpacejunk = AEFile.cutImageRegion(shipInterfaceImage, HUD_SPACEJUNK_START_X, HUD_SPACEJUNK_START_Y, HUD_SPACEJUNK_WIDTH, HUD_SPACEJUNK_HEIGHT);
	   hudAsteroid = AEFile.cutImageRegion(shipInterfaceImage, HUD_ASTEROID_START_X, HUD_ASTEROID_START_Y, HUD_ASTEROID_WIDTH, HUD_ASTEROID_HEIGHT);
	   hudCrateVoid = AEFile.cutImageRegion(shipInterfaceImage, HUD_CRATE_VOID_START_X, HUD_CRATE_VOID_START_Y, HUD_CRATE_VOID_WIDTH, HUD_CRATE_VOID_HEIGHT);
	   hudVortex = AEFile.cutImageRegion(shipInterfaceImage, HUD_VORTEX_START_X, HUD_VORTEX_START_Y, HUD_VORTEX_WIDTH, HUD_VORTEX_HEIGHT);
	   menuMapJumpgate = AEFile.cutImageRegion(shipInterfaceImage, MENU_MAP_JUMPGATE_START_X, MENU_MAP_JUMPGATE_START_Y, MENU_MAP_JUMPGATE_WIDTH, MENU_MAP_JUMPGATE_HEIGHT);
	   hudScanprocessAnim = AEFile.cutImageRegion(shipInterfaceImage, HUD_SCANPROCESS_ANIM_START_X, HUD_SCANPROCESS_ANIM_START_Y, HUD_SCANPROCESS_ANIM_WIDTH, HUD_SCANPROCESS_ANIM_HEIGHT);
	   hudMeteorClass = AEFile.cutImageRegion(shipInterfaceImage, HUD_METEOR_CLASS_START_X, HUD_METEOR_CLASS_START_Y, HUD_METEOR_CLASS_WIDTH, HUD_METEOR_CLASS_HEIGHT);
	   menuMapSidemission = AEFile.cutImageRegion(shipInterfaceImage, MENU_MAP_SIDEMISSION_START_X, MENU_MAP_SIDEMISSION_START_Y, MENU_MAP_SIDEMISSION_WIDTH, MENU_MAP_SIDEMISSION_HEIGHT);
	   menuMapMainmission = AEFile.cutImageRegion(shipInterfaceImage, MENU_MAP_MAINMISSION_START_X, MENU_MAP_MAINMISSION_START_Y, MENU_MAP_MAINMISSION_WIDTH, MENU_MAP_MAINMISSION_HEIGHT);
	   menuMapBlueprint = AEFile.cutImageRegion(shipInterfaceImage, MENU_MAP_BLUEPRINT_START_X, MENU_MAP_BLUEPRINT_START_Y, MENU_MAP_BLUEPRINT_WIDTH, MENU_MAP_BLUEPRINT_HEIGHT);
	   menuMapVisited = AEFile.cutImageRegion(shipInterfaceImage, MENU_MAP_VISITED_START_X, MENU_MAP_VISITED_START_Y, MENU_MAP_VISITED_WIDTH, MENU_MAP_VISITED_HEIGHT);
	   menuMapDirection = AEFile.cutImageRegion(interfaceImage, MENU_MAP_DIRECTION_START_X, MENU_MAP_DIRECTION_START_Y, MENU_MAP_DIRECTION_WIDTH, MENU_MAP_DIRECTION_HEIGHT);
	   mapSunGlow = AEFile.cutImageRegion(interfaceImage, MAP_SUN_GLOW_START_X, MAP_SUN_GLOW_START_Y, MAP_SUN_GLOW_WIDTH, MAP_SUN_GLOW_HEIGHT);
	   medalsOn = AEFile.cutImageRegion(interfaceImage, MEDALS_ON_START_X, MEDALS_ON_START_Y, MEDALS_ON_WIDTH, MEDALS_ON_HEIGHT);
	   medals = AEFile.cutImageRegion(interfaceImage, MEDALS_START_X, MEDALS_START_Y, MEDALS_WIDTH, MEDALS_HEIGHT);
	   flaggen = AEFile.cutImageRegion(interfaceImage, FLAGGEN_START_X, FLAGGEN_START_Y, FLAGGEN_WIDTH, FLAGGEN_HEIGHT);
	   
	   int HUD_HULL_BAR_FULL_START_X = FileRead.loadInterface(44, 1);
	   int HUD_HULL_BAR_FULL_START_Y = FileRead.loadInterface(44, 2);
	   int HUD_HULL_BAR_FULL_WIDTH = FileRead.loadInterface(44, 3);
	   int HUD_HULL_BAR_FULL_HEIGHT = FileRead.loadInterface(44, 4);
	   hudHullBarFull = AEFile.resize_image(AEFile.cutImageRegion(shipInterfaceImage, HUD_HULL_BAR_FULL_START_X, HUD_HULL_BAR_FULL_START_Y, HUD_HULL_BAR_FULL_WIDTH, HUD_HULL_BAR_FULL_HEIGHT), HUD_HULL_BAR_FULL_WIDTH * GlobalStatus.INTERFACE_SCALE_MULTIPLIER, HUD_HULL_BAR_FULL_HEIGHT * GlobalStatus.INTERFACE_SCALE_MULTIPLIER);
	   
	   int HUD_ARMOR_FULL_START_X = FileRead.loadInterface(45, 1);
	   int HUD_ARMOR_FULL_START_Y = FileRead.loadInterface(45, 2);
	   int HUD_ARMOR_FULL_WIDTH = FileRead.loadInterface(45, 3);
	   int HUD_ARMOR_FULL_HEIGHT = FileRead.loadInterface(45, 4);
	   hudArmorFull = AEFile.resize_image(AEFile.cutImageRegion(shipInterfaceImage, HUD_ARMOR_FULL_START_X, HUD_ARMOR_FULL_START_Y, HUD_ARMOR_FULL_WIDTH, HUD_ARMOR_FULL_HEIGHT), HUD_ARMOR_FULL_WIDTH * GlobalStatus.INTERFACE_SCALE_MULTIPLIER, HUD_ARMOR_FULL_HEIGHT * GlobalStatus.INTERFACE_SCALE_MULTIPLIER);
	   
	   int HUD_HULL_BAR_EMPTY_START_X = FileRead.loadInterface(46, 1);
	   int HUD_HULL_BAR_EMPTY_START_Y = FileRead.loadInterface(46, 2);
	   int HUD_HULL_BAR_EMPTY_WIDTH = FileRead.loadInterface(46, 3);
	   int HUD_HULL_BAR_EMPTY_HEIGHT = FileRead.loadInterface(46, 4);
	   hudHullBarEmpty = AEFile.resize_image(AEFile.cutImageRegion(shipInterfaceImage, HUD_HULL_BAR_EMPTY_START_X, HUD_HULL_BAR_EMPTY_START_Y, HUD_HULL_BAR_EMPTY_WIDTH, HUD_HULL_BAR_EMPTY_HEIGHT), HUD_HULL_BAR_EMPTY_WIDTH * GlobalStatus.INTERFACE_SCALE_MULTIPLIER, HUD_HULL_BAR_EMPTY_HEIGHT * GlobalStatus.INTERFACE_SCALE_MULTIPLIER);
	   
	   int HUD_STATUS_PANEL_START_X = FileRead.loadInterface(47, 1);
	   int HUD_STATUS_PANEL_START_Y = FileRead.loadInterface(47, 2);
	   int HUD_STATUS_PANEL_WIDTH = FileRead.loadInterface(47, 3);
	   int HUD_STATUS_PANEL_HEIGHT = FileRead.loadInterface(47, 4);
	   hudStatusPanel = AEFile.resize_image(AEFile.cutImageRegion(shipInterfaceImage, HUD_STATUS_PANEL_START_X, HUD_STATUS_PANEL_START_Y, HUD_STATUS_PANEL_WIDTH, HUD_STATUS_PANEL_HEIGHT), HUD_STATUS_PANEL_WIDTH * GlobalStatus.INTERFACE_SCALE_MULTIPLIER, HUD_STATUS_PANEL_HEIGHT * GlobalStatus.INTERFACE_SCALE_MULTIPLIER);
	   
	   int HUD_SHIELD_NORMAL_ICON_START_X = FileRead.loadInterface(48, 1);
	   int HUD_SHIELD_NORMAL_ICON_START_Y = FileRead.loadInterface(48, 2);
	   int HUD_SHIELD_NORMAL_ICON_WIDTH = FileRead.loadInterface(48, 3);
	   int HUD_SHIELD_NORMAL_ICON_HEIGHT = FileRead.loadInterface(48, 4);
	   hudShieldNormalIcon = AEFile.resize_image(AEFile.cutImageRegion(shipInterfaceImage, HUD_SHIELD_NORMAL_ICON_START_X, HUD_SHIELD_NORMAL_ICON_START_Y, HUD_SHIELD_NORMAL_ICON_WIDTH, HUD_SHIELD_NORMAL_ICON_HEIGHT), HUD_SHIELD_NORMAL_ICON_WIDTH * GlobalStatus.INTERFACE_SCALE_MULTIPLIER, HUD_SHIELD_NORMAL_ICON_HEIGHT * GlobalStatus.INTERFACE_SCALE_MULTIPLIER);
	   
	   int HUD_SHIELD_BAR_EMPTY_START_X = FileRead.loadInterface(49, 1);
	   int HUD_SHIELD_BAR_EMPTY_START_Y = FileRead.loadInterface(49, 2);
	   int HUD_SHIELD_BAR_EMPTY_WIDTH = FileRead.loadInterface(49, 3);
	   int HUD_SHIELD_BAR_EMPTY_HEIGHT = FileRead.loadInterface(49, 4);
	   hudShieldBarEmpty = AEFile.resize_image(AEFile.cutImageRegion(shipInterfaceImage, HUD_SHIELD_BAR_EMPTY_START_X, HUD_SHIELD_BAR_EMPTY_START_Y, HUD_SHIELD_BAR_EMPTY_WIDTH, HUD_SHIELD_BAR_EMPTY_HEIGHT), HUD_SHIELD_BAR_EMPTY_WIDTH * GlobalStatus.INTERFACE_SCALE_MULTIPLIER, HUD_SHIELD_BAR_EMPTY_HEIGHT * GlobalStatus.INTERFACE_SCALE_MULTIPLIER);
	   
	   int HUD_SHIELD_BAR_FULL_START_X = FileRead.loadInterface(50, 1);
	   int HUD_SHIELD_BAR_FULL_START_Y = FileRead.loadInterface(50, 2);
	   int HUD_SHIELD_BAR_FULL_WIDTH = FileRead.loadInterface(50, 3);
	   int HUD_SHIELD_BAR_FULL_HEIGHT = FileRead.loadInterface(50, 4);
	   hudShieldBarFull = AEFile.resize_image(AEFile.cutImageRegion(shipInterfaceImage, HUD_SHIELD_BAR_FULL_START_X, HUD_SHIELD_BAR_FULL_START_Y, HUD_SHIELD_BAR_FULL_WIDTH, HUD_SHIELD_BAR_FULL_HEIGHT), HUD_SHIELD_BAR_FULL_WIDTH * GlobalStatus.INTERFACE_SCALE_MULTIPLIER, HUD_SHIELD_BAR_FULL_HEIGHT * GlobalStatus.INTERFACE_SCALE_MULTIPLIER);
	   
	   int HUD_SHIP_NORMAL_ICON_START_X = FileRead.loadInterface(51, 1);
	   int HUD_SHIP_NORMAL_ICON_START_Y = FileRead.loadInterface(51, 2);
	   int HUD_SHIP_NORMAL_ICON_WIDTH = FileRead.loadInterface(51, 3);
	   int HUD_SHIP_NORMAL_ICON_HEIGHT = FileRead.loadInterface(51, 4);
	   hudShipNormalIcon = AEFile.resize_image(AEFile.cutImageRegion(shipInterfaceImage, HUD_SHIP_NORMAL_ICON_START_X, HUD_SHIP_NORMAL_ICON_START_Y, HUD_SHIP_NORMAL_ICON_WIDTH, HUD_SHIP_NORMAL_ICON_HEIGHT), HUD_SHIP_NORMAL_ICON_WIDTH * GlobalStatus.INTERFACE_SCALE_MULTIPLIER, HUD_SHIP_NORMAL_ICON_HEIGHT * GlobalStatus.INTERFACE_SCALE_MULTIPLIER);
	   
	   int PANEL_INFO_LOWER_START_X = FileRead.loadInterface(52, 1);
	   int PANEL_INFO_LOWER_START_Y = FileRead.loadInterface(52, 2);
	   int PANEL_INFO_LOWER_WIDTH = FileRead.loadInterface(52, 3);
	   int PANEL_INFO_LOWER_HEIGHT = FileRead.loadInterface(52, 4);
	   panelInfoLower = AEFile.cutImageRegion(shipInterfaceImage, PANEL_INFO_LOWER_START_X, PANEL_INFO_LOWER_START_Y, PANEL_INFO_LOWER_WIDTH, PANEL_INFO_LOWER_HEIGHT);
	   
	   int AUTOPILOT_NORMAL_START_X = FileRead.loadInterface(53, 1);
	   int AUTOPILOT_NORMAL_START_Y = FileRead.loadInterface(53, 2);
	   int AUTOPILOT_NORMAL_WIDTH = FileRead.loadInterface(53, 3);
	   int AUTOPILOT_NORMAL_HEIGHT = FileRead.loadInterface(53, 4);
	   autopilotNormal = AEFile.resize_image(AEFile.cutImageRegion(shipInterfaceImage, AUTOPILOT_NORMAL_START_X, AUTOPILOT_NORMAL_START_Y, AUTOPILOT_NORMAL_WIDTH, AUTOPILOT_NORMAL_HEIGHT), AUTOPILOT_NORMAL_WIDTH * GlobalStatus.INTERFACE_SCALE_MULTIPLIER, AUTOPILOT_NORMAL_HEIGHT * GlobalStatus.INTERFACE_SCALE_MULTIPLIER);
	   
	   int AUTOPILOT_PRESSED_START_X = FileRead.loadInterface(54, 1);
	   int AUTOPILOT_PRESSED_START_Y = FileRead.loadInterface(54, 2);
	   int AUTOPILOT_PRESSED_WIDTH = FileRead.loadInterface(54, 3);
	   int AUTOPILOT_PRESSED_HEIGHT = FileRead.loadInterface(54, 4);
	   autopilotPressed = AEFile.resize_image(AEFile.cutImageRegion(shipInterfaceImage, AUTOPILOT_PRESSED_START_X, AUTOPILOT_PRESSED_START_Y, AUTOPILOT_PRESSED_WIDTH, AUTOPILOT_PRESSED_HEIGHT), AUTOPILOT_PRESSED_WIDTH * GlobalStatus.INTERFACE_SCALE_MULTIPLIER, AUTOPILOT_PRESSED_HEIGHT * GlobalStatus.INTERFACE_SCALE_MULTIPLIER);
	   
	   int BOOSTER_NORMAL_START_X = FileRead.loadInterface(55, 1);
	   int BOOSTER_NORMAL_START_Y = FileRead.loadInterface(55, 2);
	   int BOOSTER_NORMAL_WIDTH = FileRead.loadInterface(55, 3);
	   int BOOSTER_NORMAL_HEIGHT = FileRead.loadInterface(55, 4);
	   boosterNormal = AEFile.resize_image(AEFile.cutImageRegion(shipInterfaceImage, BOOSTER_NORMAL_START_X, BOOSTER_NORMAL_START_Y, BOOSTER_NORMAL_WIDTH, BOOSTER_NORMAL_HEIGHT), BOOSTER_NORMAL_WIDTH * GlobalStatus.INTERFACE_SCALE_MULTIPLIER, BOOSTER_NORMAL_HEIGHT * GlobalStatus.INTERFACE_SCALE_MULTIPLIER);
	   
	   int BOOSTER_PRESSED_START_X = FileRead.loadInterface(56, 1);
	   int BOOSTER_PRESSED_START_Y = FileRead.loadInterface(56, 2);
	   int BOOSTER_PRESSED_WIDTH = FileRead.loadInterface(56, 3);
	   int BOOSTER_PRESSED_HEIGHT = FileRead.loadInterface(56, 4);
	   boosterPressed = AEFile.resize_image(AEFile.cutImageRegion(shipInterfaceImage, BOOSTER_PRESSED_START_X, BOOSTER_PRESSED_START_Y, BOOSTER_PRESSED_WIDTH, BOOSTER_PRESSED_HEIGHT), BOOSTER_PRESSED_WIDTH * GlobalStatus.INTERFACE_SCALE_MULTIPLIER, BOOSTER_PRESSED_HEIGHT * GlobalStatus.INTERFACE_SCALE_MULTIPLIER);
	   
	   int BOOSTER_INACTIVE_START_X = FileRead.loadInterface(57, 1);
	   int BOOSTER_INACTIVE_START_Y = FileRead.loadInterface(57, 2);
	   int BOOSTER_INACTIVE_WIDTH = FileRead.loadInterface(57, 3);
	   int BOOSTER_INACTIVE_HEIGHT = FileRead.loadInterface(57, 4);
	   boosterInactive = AEFile.resize_image(AEFile.cutImageRegion(shipInterfaceImage, BOOSTER_INACTIVE_START_X, BOOSTER_INACTIVE_START_Y, BOOSTER_INACTIVE_WIDTH, BOOSTER_INACTIVE_HEIGHT), BOOSTER_INACTIVE_WIDTH * GlobalStatus.INTERFACE_SCALE_MULTIPLIER, BOOSTER_INACTIVE_HEIGHT * GlobalStatus.INTERFACE_SCALE_MULTIPLIER);
	   
	   int QUICKMENU_CROSSHAIR_START_X = FileRead.loadInterface(58, 1);
	   int QUICKMENU_CROSSHAIR_START_Y = FileRead.loadInterface(58, 2);
	   int QUICKMENU_CROSSHAIR_WIDTH = FileRead.loadInterface(58, 3);
	   int QUICKMENU_CROSSHAIR_HEIGHT = FileRead.loadInterface(58, 4);
	   quickmenuCrosshair = AEFile.cutImageRegion(shipInterfaceImage, QUICKMENU_CROSSHAIR_START_X, QUICKMENU_CROSSHAIR_START_Y, QUICKMENU_CROSSHAIR_WIDTH, QUICKMENU_CROSSHAIR_HEIGHT);
	   
	   int QUICKMENU_ICONS_START_X = FileRead.loadInterface(59, 1);
	   int QUICKMENU_ICONS_START_Y = FileRead.loadInterface(59, 2);
	   int QUICKMENU_ICONS_WIDTH = FileRead.loadInterface(59, 3);
	   int QUICKMENU_ICONS_HEIGHT = FileRead.loadInterface(59, 4);
	   quickmenuIcons = AEFile.cutImageRegion(shipInterfaceImage, QUICKMENU_ICONS_START_X, QUICKMENU_ICONS_START_Y, QUICKMENU_ICONS_WIDTH, QUICKMENU_ICONS_HEIGHT);
	   
	   int HUD_CROSSHAIR_START_X = FileRead.loadInterface(60, 1);
	   int HUD_CROSSHAIR_START_Y = FileRead.loadInterface(60, 2);
	   int HUD_CROSSHAIR_WIDTH = FileRead.loadInterface(60, 3);
	   int HUD_CROSSHAIR_HEIGHT = FileRead.loadInterface(60, 4);
	   hudCrosshair = AEFile.cutImageRegion(shipInterfaceImage, HUD_CROSSHAIR_START_X, HUD_CROSSHAIR_START_Y, HUD_CROSSHAIR_WIDTH, HUD_CROSSHAIR_HEIGHT);
	   
	   int LOCK_START_X = FileRead.loadInterface(61, 1);
	   int LOCK_START_Y = FileRead.loadInterface(61, 2);
	   int LOCK_WIDTH = FileRead.loadInterface(61, 3);
	   int LOCK_HEIGHT = FileRead.loadInterface(61, 4);
	   lockImage = AEFile.cutImageRegion(shipInterfaceImage, LOCK_START_X, LOCK_START_Y, LOCK_WIDTH, LOCK_HEIGHT);
	   
	   int MINING_BACKGROUND_START_X = FileRead.loadInterface(62, 1);
	   int MINING_BACKGROUND_START_Y = FileRead.loadInterface(62, 2);
	   int MINING_BACKGROUND_WIDTH = FileRead.loadInterface(62, 3);
	   int MINING_BACKGROUND_HEIGHT = FileRead.loadInterface(62, 4);
	   miningBackground = AEFile.cutImageRegion(shipInterfaceImage, MINING_BACKGROUND_START_X, MINING_BACKGROUND_START_Y, MINING_BACKGROUND_WIDTH, MINING_BACKGROUND_HEIGHT);
	   
	   int MINING_CURSOR_START_X = FileRead.loadInterface(63, 1);
	   int MINING_CURSOR_START_Y = FileRead.loadInterface(63, 2);
	   int MINING_CURSOR_WIDTH = FileRead.loadInterface(63, 3);
	   int MINING_CURSOR_HEIGHT = FileRead.loadInterface(63, 4);
	   miningCursor = AEFile.cutImageRegion(shipInterfaceImage, MINING_CURSOR_START_X, MINING_CURSOR_START_Y, MINING_CURSOR_WIDTH, MINING_CURSOR_HEIGHT);
	   
	   int MINING_GREEN_COMPLETE_START_X = FileRead.loadInterface(64, 1);
	   int MINING_GREEN_COMPLETE_START_Y = FileRead.loadInterface(64, 2);
	   int MINING_GREEN_COMPLETE_WIDTH = FileRead.loadInterface(64, 3);
	   int MINING_GREEN_COMPLETE_HEIGHT = FileRead.loadInterface(64, 4);
	   miningGreenComplete = AEFile.cutImageRegion(shipInterfaceImage, MINING_GREEN_COMPLETE_START_X, MINING_GREEN_COMPLETE_START_Y, MINING_GREEN_COMPLETE_WIDTH, MINING_GREEN_COMPLETE_HEIGHT);
	   
	   int MINING_GREEN_EMPTY_START_X = FileRead.loadInterface(65, 1);
	   int MINING_GREEN_EMPTY_START_Y = FileRead.loadInterface(65, 2);
	   int MINING_GREEN_EMPTY_WIDTH = FileRead.loadInterface(65, 3);
	   int MINING_GREEN_EMPTY_HEIGHT = FileRead.loadInterface(65, 4);
	   miningGreenEmpty = AEFile.cutImageRegion(shipInterfaceImage, MINING_GREEN_EMPTY_START_X, MINING_GREEN_EMPTY_START_Y, MINING_GREEN_EMPTY_WIDTH, MINING_GREEN_EMPTY_HEIGHT);
	   
	   int MINING_REDAREA_START_X = FileRead.loadInterface(66, 1);
	   int MINING_REDAREA_START_Y = FileRead.loadInterface(66, 2);
	   int MINING_REDAREA_WIDTH = FileRead.loadInterface(66, 3);
	   int MINING_REDAREA_HEIGHT = FileRead.loadInterface(66, 4);
	   miningRedArea = AEFile.cutImageRegion(shipInterfaceImage, MINING_REDAREA_START_X, MINING_REDAREA_START_Y, MINING_REDAREA_WIDTH, MINING_REDAREA_HEIGHT);
	   
	   int SKIP_START_X = FileRead.loadInterface(67, 1);
	   int SKIP_START_Y = FileRead.loadInterface(67, 2);
	   int SKIP_WIDTH = FileRead.loadInterface(67, 3);
	   int SKIP_HEIGHT = FileRead.loadInterface(67, 4);
	   menuSkipImage = AEFile.cutImageRegion(interfaceImage, SKIP_START_X, SKIP_START_Y, SKIP_WIDTH, SKIP_HEIGHT);
	   
	   int MENU_BACKGROUND_START_X = FileRead.loadInterface(68, 1);
	   int MENU_BACKGROUND_START_Y = FileRead.loadInterface(68, 2);
	   int MENU_BACKGROUND_WIDTH = FileRead.loadInterface(68, 3);
	   int MENU_BACKGROUND_HEIGHT = FileRead.loadInterface(68, 4);
	   menuBackgroundImage = AEFile.cutImageRegion(interfaceImage, MENU_BACKGROUND_START_X, MENU_BACKGROUND_START_Y, MENU_BACKGROUND_WIDTH, MENU_BACKGROUND_HEIGHT);
	   
	   int MENU_MAIN_CORNER_START_X = FileRead.loadInterface(69, 1);
	   int MENU_MAIN_CORNER_START_Y = FileRead.loadInterface(69, 2);
	   int MENU_MAIN_CORNER_WIDTH = FileRead.loadInterface(69, 3);
	   int MENU_MAIN_CORNER_HEIGHT = FileRead.loadInterface(69, 4);
	   menuMainCornerImage = AEFile.cutImageRegion(interfaceImage, MENU_MAIN_CORNER_START_X, MENU_MAIN_CORNER_START_Y, MENU_MAIN_CORNER_WIDTH, MENU_MAIN_CORNER_HEIGHT);
	   
	   int MENU_MAIN_PANEL_UPPER_START_X = FileRead.loadInterface(70, 1);
	   int MENU_MAIN_PANEL_UPPER_START_Y = FileRead.loadInterface(70, 2);
	   int MENU_MAIN_PANEL_UPPER_WIDTH = FileRead.loadInterface(70, 3);
	   int MENU_MAIN_PANEL_UPPER_HEIGHT = FileRead.loadInterface(70, 4);
	   menuMainPanelUpperImage = AEFile.cutImageRegion(interfaceImage, MENU_MAIN_PANEL_UPPER_START_X, MENU_MAIN_PANEL_UPPER_START_Y, MENU_MAIN_PANEL_UPPER_WIDTH, MENU_MAIN_PANEL_UPPER_HEIGHT);
	   
	   int MENU_PANEL_LOWER_START_X = FileRead.loadInterface(71, 1);
	   int MENU_PANEL_LOWER_START_Y = FileRead.loadInterface(71, 2);
	   int MENU_PANEL_LOWER_WIDTH = FileRead.loadInterface(71, 3);
	   int MENU_PANEL_LOWER_HEIGHT = FileRead.loadInterface(71, 4);
	   menuMainPanelLowerImage = AEFile.resize_image(AEFile.cutImageRegion(interfaceImage, MENU_PANEL_LOWER_START_X, MENU_PANEL_LOWER_START_Y, MENU_PANEL_LOWER_WIDTH, MENU_PANEL_LOWER_HEIGHT), MENU_PANEL_LOWER_WIDTH * GlobalStatus.INTERFACE_SCALE_MULTIPLIER, MENU_PANEL_LOWER_HEIGHT * GlobalStatus.INTERFACE_SCALE_MULTIPLIER);
	   
	   int MENU_PANEL_CORNER_LEFT_START_X = FileRead.loadInterface(72, 1);
	   int MENU_PANEL_CORNER_LEFT_START_Y = FileRead.loadInterface(72, 2);
	   int MENU_PANEL_CORNER_LEFT_WIDTH = FileRead.loadInterface(72, 3);
	   int MENU_PANEL_CORNER_LEFT_HEIGHT = FileRead.loadInterface(72, 4);
	   menuPanelCornerLeftImage = AEFile.resize_image(AEFile.cutImageRegion(interfaceImage, MENU_PANEL_CORNER_LEFT_START_X, MENU_PANEL_CORNER_LEFT_START_Y, MENU_PANEL_CORNER_LEFT_WIDTH, MENU_PANEL_CORNER_LEFT_HEIGHT), MENU_PANEL_CORNER_LEFT_WIDTH * GlobalStatus.INTERFACE_SCALE_MULTIPLIER, MENU_PANEL_CORNER_LEFT_HEIGHT * GlobalStatus.INTERFACE_SCALE_MULTIPLIER);
	   
	   int MENU_LOWER_PANEL_SOLID_START_X = FileRead.loadInterface(73, 1);
	   int MENU_LOWER_PANEL_SOLID_START_Y = FileRead.loadInterface(73, 2);
	   int MENU_LOWER_PANEL_SOLID_WIDTH = FileRead.loadInterface(73, 3);
	   int MENU_LOWER_PANEL_SOLID_HEIGHT = FileRead.loadInterface(73, 4);
	   menuLowerPanelSolidImage = AEFile.resize_image(AEFile.cutImageRegion(interfaceImage, MENU_LOWER_PANEL_SOLID_START_X, MENU_LOWER_PANEL_SOLID_START_Y, MENU_LOWER_PANEL_SOLID_WIDTH, MENU_LOWER_PANEL_SOLID_HEIGHT), MENU_LOWER_PANEL_SOLID_WIDTH * GlobalStatus.INTERFACE_SCALE_MULTIPLIER, MENU_LOWER_PANEL_SOLID_HEIGHT * GlobalStatus.INTERFACE_SCALE_MULTIPLIER);
	   
	   int FISHLABS_START_X = FileRead.loadInterface(74, 1);
	   int FISHLABS_START_Y = FileRead.loadInterface(74, 2);
	   int FISHLABS_WIDTH = FileRead.loadInterface(74, 3);
	   int FISHLABS_HEIGHT = FileRead.loadInterface(74, 4);
	   fishlabsImage = AEFile.cutImageRegion(interfaceImage, FISHLABS_START_X, FISHLABS_START_Y, FISHLABS_WIDTH, FISHLABS_HEIGHT);
	   
	   int ABYSS_START_X = FileRead.loadInterface(75, 1);
	   int ABYSS_START_Y = FileRead.loadInterface(75, 2);
	   int ABYSS_WIDTH = FileRead.loadInterface(75, 3);
	   int ABYSS_HEIGHT = FileRead.loadInterface(75, 4);
	   abyssImage = AEFile.cutImageRegion(interfaceImage, ABYSS_START_X, ABYSS_START_Y, ABYSS_WIDTH, ABYSS_HEIGHT);
	   
	   int GOF2_LOGO_START_X = FileRead.loadInterface(76, 1);
	   int GOF2_LOGO_START_Y = FileRead.loadInterface(76, 2);
	   int GOF2_LOGO_WIDTH = FileRead.loadInterface(76, 3);
	   int GOF2_LOGO_HEIGHT = FileRead.loadInterface(76, 4);
	   gof2LogoImage = AEFile.cutImageRegion(interfaceImage, GOF2_LOGO_START_X, GOF2_LOGO_START_Y, GOF2_LOGO_WIDTH, GOF2_LOGO_HEIGHT);
	   
	   int ITEM_TYPES_START_X = FileRead.loadInterface(77, 1);
	   int ITEM_TYPES_START_Y = FileRead.loadInterface(77, 2);
	   int ITEM_TYPES_WIDTH = FileRead.loadInterface(77, 3);
	   int ITEM_TYPES_HEIGHT = FileRead.loadInterface(77, 4);
	   itemTypesImage = AEFile.cutImageRegion(interfaceImage, ITEM_TYPES_START_X, ITEM_TYPES_START_Y, ITEM_TYPES_WIDTH, ITEM_TYPES_HEIGHT);
	   
	   int ITEM_TYPES_SEL_START_X = FileRead.loadInterface(78, 1);
	   int ITEM_TYPES_SEL_START_Y = FileRead.loadInterface(78, 2);
	   int ITEM_TYPES_SEL_WIDTH = FileRead.loadInterface(78, 3);
	   int ITEM_TYPES_SEL_HEIGHT = FileRead.loadInterface(78, 4);
	   itemTypesSelImage = AEFile.cutImageRegion(interfaceImage, ITEM_TYPES_SEL_START_X, ITEM_TYPES_SEL_START_Y, ITEM_TYPES_SEL_WIDTH, ITEM_TYPES_SEL_HEIGHT);
	   
	   int SHIPS_COLOR_START_X = FileRead.loadInterface(79, 1);
	   int SHIPS_COLOR_START_Y = FileRead.loadInterface(79, 2);
	   int SHIPS_COLOR_WIDTH = FileRead.loadInterface(79, 3);
	   int SHIPS_COLOR_HEIGHT = FileRead.loadInterface(79, 4);
	   shipsColorImage = AEFile.cutImageRegion(interfaceImage, SHIPS_COLOR_START_X, SHIPS_COLOR_START_Y, SHIPS_COLOR_WIDTH, SHIPS_COLOR_HEIGHT);
	   
	   int RIGHT_HUD_BACKGROUND_START_X = FileRead.loadInterface(80, 1);
	   int RIGHT_HUD_BACKGROUND_START_Y = FileRead.loadInterface(80, 2);
	   int RIGHT_HUD_BACKGROUND_WIDTH = FileRead.loadInterface(80, 3);
	   int RIGHT_HUD_BACKGROUND_HEIGHT = FileRead.loadInterface(80, 4);
	   rightPanelBackgroundImage = AEFile.resize_image(AEFile.cutImageRegion(shipInterfaceImage, RIGHT_HUD_BACKGROUND_START_X, RIGHT_HUD_BACKGROUND_START_Y, RIGHT_HUD_BACKGROUND_WIDTH, RIGHT_HUD_BACKGROUND_HEIGHT), RIGHT_HUD_BACKGROUND_WIDTH * GlobalStatus.INTERFACE_SCALE_MULTIPLIER, RIGHT_HUD_BACKGROUND_HEIGHT * GlobalStatus.INTERFACE_SCALE_MULTIPLIER);
	   
	   int FIRE_BUTTON_NORMAL_START_X = FileRead.loadInterface(81, 1);
	   int FIRE_BUTTON_NORMAL_START_Y = FileRead.loadInterface(81, 2);
	   int FIRE_BUTTON_NORMAL_WIDTH = FileRead.loadInterface(81, 3);
	   int FIRE_BUTTON_NORMAL_HEIGHT = FileRead.loadInterface(81, 4);
	   fireButtonNormal = AEFile.resize_image(AEFile.cutImageRegion(shipInterfaceImage, FIRE_BUTTON_NORMAL_START_X, FIRE_BUTTON_NORMAL_START_Y, FIRE_BUTTON_NORMAL_WIDTH, FIRE_BUTTON_NORMAL_HEIGHT), FIRE_BUTTON_NORMAL_WIDTH * GlobalStatus.INTERFACE_SCALE_MULTIPLIER, FIRE_BUTTON_NORMAL_HEIGHT * GlobalStatus.INTERFACE_SCALE_MULTIPLIER);
	   
	   int FIRE_BUTTON_PRESSED_START_X = FileRead.loadInterface(82, 1);
	   int FIRE_BUTTON_PRESSED_START_Y = FileRead.loadInterface(82, 2);
	   int FIRE_BUTTON_PRESSED_WIDTH = FileRead.loadInterface(82, 3);
	   int FIRE_BUTTON_PRESSED_HEIGHT = FileRead.loadInterface(82, 4);
	   fireButtonPressed = AEFile.resize_image(AEFile.cutImageRegion(shipInterfaceImage, FIRE_BUTTON_PRESSED_START_X, FIRE_BUTTON_PRESSED_START_Y, FIRE_BUTTON_PRESSED_WIDTH, FIRE_BUTTON_PRESSED_HEIGHT), FIRE_BUTTON_PRESSED_WIDTH * GlobalStatus.INTERFACE_SCALE_MULTIPLIER, FIRE_BUTTON_PRESSED_HEIGHT * GlobalStatus.INTERFACE_SCALE_MULTIPLIER);
	   
	   int ROCKET_BUTTON_NORMAL_START_X = FileRead.loadInterface(83, 1);
	   int ROCKET_BUTTON_NORMAL_START_Y = FileRead.loadInterface(83, 2);
	   int ROCKET_BUTTON_NORMAL_WIDTH = FileRead.loadInterface(83, 3);
	   int ROCKET_BUTTON_NORMAL_HEIGHT = FileRead.loadInterface(83, 4);
	   rocketButtonNormal = AEFile.resize_image(AEFile.cutImageRegion(shipInterfaceImage, ROCKET_BUTTON_NORMAL_START_X, ROCKET_BUTTON_NORMAL_START_Y, ROCKET_BUTTON_NORMAL_WIDTH, ROCKET_BUTTON_NORMAL_HEIGHT), ROCKET_BUTTON_NORMAL_WIDTH * GlobalStatus.INTERFACE_SCALE_MULTIPLIER, ROCKET_BUTTON_NORMAL_HEIGHT * GlobalStatus.INTERFACE_SCALE_MULTIPLIER);
	   
	   int ROCKET_BUTTON_PRESSED_START_X = FileRead.loadInterface(84, 1);
	   int ROCKET_BUTTON_PRESSED_START_Y = FileRead.loadInterface(84, 2);
	   int ROCKET_BUTTON_PRESSED_WIDTH = FileRead.loadInterface(84, 3);
	   int ROCKET_BUTTON_PRESSED_HEIGHT = FileRead.loadInterface(84, 4);
	   rocketButtonPressed = AEFile.resize_image(AEFile.cutImageRegion(shipInterfaceImage, ROCKET_BUTTON_PRESSED_START_X, ROCKET_BUTTON_PRESSED_START_Y, ROCKET_BUTTON_PRESSED_WIDTH, ROCKET_BUTTON_PRESSED_HEIGHT), ROCKET_BUTTON_PRESSED_WIDTH * GlobalStatus.INTERFACE_SCALE_MULTIPLIER, ROCKET_BUTTON_PRESSED_HEIGHT * GlobalStatus.INTERFACE_SCALE_MULTIPLIER);
	   
	   int CAMERA_BUTTON_NORMAL_START_X = FileRead.loadInterface(85, 1);
	   int CAMERA_BUTTON_NORMAL_START_Y = FileRead.loadInterface(85, 2);
	   int CAMERA_BUTTON_NORMAL_WIDTH = FileRead.loadInterface(85, 3);
	   int CAMERA_BUTTON_NORMAL_HEIGHT = FileRead.loadInterface(85, 4);
	   cameraButtonNormal = AEFile.resize_image(AEFile.cutImageRegion(shipInterfaceImage, CAMERA_BUTTON_NORMAL_START_X, CAMERA_BUTTON_NORMAL_START_Y, CAMERA_BUTTON_NORMAL_WIDTH, CAMERA_BUTTON_NORMAL_HEIGHT), CAMERA_BUTTON_NORMAL_WIDTH * GlobalStatus.INTERFACE_SCALE_MULTIPLIER, CAMERA_BUTTON_NORMAL_HEIGHT * GlobalStatus.INTERFACE_SCALE_MULTIPLIER);
	   
	   int CAMERA_BUTTON_PRESSED_START_X = FileRead.loadInterface(86, 1);
	   int CAMERA_BUTTON_PRESSED_START_Y = FileRead.loadInterface(86, 2);
	   int CAMERA_BUTTON_PRESSED_WIDTH = FileRead.loadInterface(86, 3);
	   int CAMERA_BUTTON_PRESSED_HEIGHT = FileRead.loadInterface(86, 4);
	   cameraButtonPressed = AEFile.resize_image(AEFile.cutImageRegion(shipInterfaceImage, CAMERA_BUTTON_PRESSED_START_X, CAMERA_BUTTON_PRESSED_START_Y, CAMERA_BUTTON_PRESSED_WIDTH, CAMERA_BUTTON_PRESSED_HEIGHT), CAMERA_BUTTON_PRESSED_WIDTH * GlobalStatus.INTERFACE_SCALE_MULTIPLIER, CAMERA_BUTTON_PRESSED_HEIGHT * GlobalStatus.INTERFACE_SCALE_MULTIPLIER);
	   
	   int QUICKMENU_BUTTON_NORMAL_START_X = FileRead.loadInterface(87, 1);
	   int QUICKMENU_BUTTON_NORMAL_START_Y = FileRead.loadInterface(87, 2);
	   int QUICKMENU_BUTTON_NORMAL_WIDTH = FileRead.loadInterface(87, 3);
	   int QUICKMENU_BUTTON_NORMAL_HEIGHT = FileRead.loadInterface(87, 4);
	   quickmenuButtonNormal = AEFile.resize_image(AEFile.cutImageRegion(shipInterfaceImage, QUICKMENU_BUTTON_NORMAL_START_X, QUICKMENU_BUTTON_NORMAL_START_Y, QUICKMENU_BUTTON_NORMAL_WIDTH, QUICKMENU_BUTTON_NORMAL_HEIGHT), QUICKMENU_BUTTON_NORMAL_WIDTH * GlobalStatus.INTERFACE_SCALE_MULTIPLIER, QUICKMENU_BUTTON_NORMAL_HEIGHT * GlobalStatus.INTERFACE_SCALE_MULTIPLIER);
	   
	   int QUICKMENU_BUTTON_PRESSED_START_X = FileRead.loadInterface(88, 1);
	   int QUICKMENU_BUTTON_PRESSED_START_Y = FileRead.loadInterface(88, 2);
	   int QUICKMENU_BUTTON_PRESSED_WIDTH = FileRead.loadInterface(88, 3);
	   int QUICKMENU_BUTTON_PRESSED_HEIGHT = FileRead.loadInterface(88, 4);
	   quickmenuButtonPressed = AEFile.resize_image(AEFile.cutImageRegion(shipInterfaceImage, QUICKMENU_BUTTON_PRESSED_START_X, QUICKMENU_BUTTON_PRESSED_START_Y, QUICKMENU_BUTTON_PRESSED_WIDTH, QUICKMENU_BUTTON_PRESSED_HEIGHT), QUICKMENU_BUTTON_PRESSED_WIDTH * GlobalStatus.INTERFACE_SCALE_MULTIPLIER, QUICKMENU_BUTTON_PRESSED_HEIGHT * GlobalStatus.INTERFACE_SCALE_MULTIPLIER);
	   
	   int CARGO_PANEL_START_X = FileRead.loadInterface(89, 1);
	   int CARGO_PANEL_START_Y = FileRead.loadInterface(89, 2);
	   int CARGO_PANEL_WIDTH = FileRead.loadInterface(89, 3);
	   int CARGO_PANEL_HEIGHT = FileRead.loadInterface(89, 4);
	   cargoPanelImage = AEFile.resize_image(AEFile.cutImageRegion(shipInterfaceImage, CARGO_PANEL_START_X, CARGO_PANEL_START_Y, CARGO_PANEL_WIDTH, CARGO_PANEL_HEIGHT), CARGO_PANEL_WIDTH * GlobalStatus.INTERFACE_SCALE_MULTIPLIER, CARGO_PANEL_HEIGHT * GlobalStatus.INTERFACE_SCALE_MULTIPLIER);
	   
	   int PAUSE_BUTTON_NORMAL_START_X = FileRead.loadInterface(90, 1);
	   int PAUSE_BUTTON_NORMAL_START_Y = FileRead.loadInterface(90, 2);
	   int PAUSE_BUTTON_NORMAL_WIDTH = FileRead.loadInterface(90, 3);
	   int PAUSE_BUTTON_NORMAL_HEIGHT = FileRead.loadInterface(90, 4);
	   pauseButtonNormal = AEFile.resize_image(AEFile.cutImageRegion(shipInterfaceImage, PAUSE_BUTTON_NORMAL_START_X, PAUSE_BUTTON_NORMAL_START_Y, PAUSE_BUTTON_NORMAL_WIDTH, PAUSE_BUTTON_NORMAL_HEIGHT), PAUSE_BUTTON_NORMAL_WIDTH * GlobalStatus.INTERFACE_SCALE_MULTIPLIER, PAUSE_BUTTON_NORMAL_HEIGHT * GlobalStatus.INTERFACE_SCALE_MULTIPLIER);
	   
	   int PAUSE_BUTTON_PRESSED_START_X = FileRead.loadInterface(91, 1);
	   int PAUSE_BUTTON_PRESSED_START_Y = FileRead.loadInterface(91, 2);
	   int PAUSE_BUTTON_PRESSED_WIDTH = FileRead.loadInterface(91, 3);
	   int PAUSE_BUTTON_PRESSED_HEIGHT = FileRead.loadInterface(91, 4);
	   pauseButtonPressed = AEFile.resize_image(AEFile.cutImageRegion(shipInterfaceImage, PAUSE_BUTTON_PRESSED_START_X, PAUSE_BUTTON_PRESSED_START_Y, PAUSE_BUTTON_PRESSED_WIDTH, PAUSE_BUTTON_PRESSED_HEIGHT), PAUSE_BUTTON_PRESSED_WIDTH * GlobalStatus.INTERFACE_SCALE_MULTIPLIER, PAUSE_BUTTON_PRESSED_HEIGHT * GlobalStatus.INTERFACE_SCALE_MULTIPLIER);
	   
	   int HUD_BAR_YELLOW_EMPTY_START_X = FileRead.loadInterface(92, 1);
	   int HUD_BAR_YELLOW_EMPTY_START_Y = FileRead.loadInterface(92, 2);
	   int HUD_BAR_YELLOW_EMPTY_WIDTH = FileRead.loadInterface(92, 3);
	   int HUD_BAR_YELLOW_EMPTY_HEIGHT = FileRead.loadInterface(92, 4);
	   hudBarYellowEmpty = AEFile.cutImageRegion(shipInterfaceImage, HUD_BAR_YELLOW_EMPTY_START_X, HUD_BAR_YELLOW_EMPTY_START_Y, HUD_BAR_YELLOW_EMPTY_WIDTH, HUD_BAR_YELLOW_EMPTY_HEIGHT);
	   
	   int HUD_BAR_YELLOW_FULL_START_X = FileRead.loadInterface(93, 1);
	   int HUD_BAR_YELLOW_FULL_START_Y = FileRead.loadInterface(93, 2);
	   int HUD_BAR_YELLOW_FULL_WIDTH = FileRead.loadInterface(93, 3);
	   int HUD_BAR_YELLOW_FULL_HEIGHT = FileRead.loadInterface(93, 4);
	   hudBarYellowFull = AEFile.cutImageRegion(shipInterfaceImage, HUD_BAR_YELLOW_FULL_START_X, HUD_BAR_YELLOW_FULL_START_Y, HUD_BAR_YELLOW_FULL_WIDTH, HUD_BAR_YELLOW_FULL_HEIGHT);
	   
	   int HUD_BAR_RED_EMPTY_START_X = FileRead.loadInterface(94, 1);
	   int HUD_BAR_RED_EMPTY_START_Y = FileRead.loadInterface(94, 2);
	   int HUD_BAR_RED_EMPTY_WIDTH = FileRead.loadInterface(94, 3);
	   int HUD_BAR_RED_EMPTY_HEIGHT = FileRead.loadInterface(94, 4);
	   hudBarRedEmpty = AEFile.cutImageRegion(shipInterfaceImage, HUD_BAR_RED_EMPTY_START_X, HUD_BAR_RED_EMPTY_START_Y, HUD_BAR_RED_EMPTY_WIDTH, HUD_BAR_RED_EMPTY_HEIGHT);
	   
	   int HUD_BAR_RED_FULL_START_X = FileRead.loadInterface(95, 1);
	   int HUD_BAR_RED_FULL_START_Y = FileRead.loadInterface(95, 2);
	   int HUD_BAR_RED_FULL_WIDTH = FileRead.loadInterface(95, 3);
	   int HUD_BAR_RED_FULL_HEIGHT = FileRead.loadInterface(95, 4);
	   hudBarRedFull = AEFile.cutImageRegion(shipInterfaceImage, HUD_BAR_RED_FULL_START_X, HUD_BAR_RED_FULL_START_Y, HUD_BAR_RED_FULL_WIDTH, HUD_BAR_RED_FULL_HEIGHT);
	   
	   int HUD_BAR_BLUE_EMPTY_START_X = FileRead.loadInterface(96, 1);
	   int HUD_BAR_BLUE_EMPTY_START_Y = FileRead.loadInterface(96, 2);
	   int HUD_BAR_BLUE_EMPTY_WIDTH = FileRead.loadInterface(96, 3);
	   int HUD_BAR_BLUE_EMPTY_HEIGHT = FileRead.loadInterface(96, 4);
	   hudBarBlueEmpty = AEFile.cutImageRegion(shipInterfaceImage, HUD_BAR_BLUE_EMPTY_START_X, HUD_BAR_BLUE_EMPTY_START_Y, HUD_BAR_BLUE_EMPTY_WIDTH, HUD_BAR_BLUE_EMPTY_HEIGHT);
	   
	   int HUD_BAR_WHITE_FULL_START_X = FileRead.loadInterface(97, 1);
	   int HUD_BAR_WHITE_FULL_START_Y = FileRead.loadInterface(97, 2);
	   int HUD_BAR_WHITE_FULL_WIDTH = FileRead.loadInterface(97, 3);
	   int HUD_BAR_WHITE_FULL_HEIGHT = FileRead.loadInterface(97, 4);
	   hudBarWhiteFull = AEFile.cutImageRegion(shipInterfaceImage, HUD_BAR_WHITE_FULL_START_X, HUD_BAR_WHITE_FULL_START_Y, HUD_BAR_WHITE_FULL_WIDTH, HUD_BAR_WHITE_FULL_HEIGHT);
	   
	   int HUD_BAR_GREEN_EMPTY_START_X = FileRead.loadInterface(98, 1);
	   int HUD_BAR_GREEN_EMPTY_START_Y = FileRead.loadInterface(98, 2);
	   int HUD_BAR_GREEN_EMPTY_WIDTH = FileRead.loadInterface(98, 3);
	   int HUD_BAR_GREEN_EMPTY_HEIGHT = FileRead.loadInterface(98, 4);
	   hudBarGreenEmpty = AEFile.cutImageRegion(shipInterfaceImage, HUD_BAR_GREEN_EMPTY_START_X, HUD_BAR_GREEN_EMPTY_START_Y, HUD_BAR_GREEN_EMPTY_WIDTH, HUD_BAR_GREEN_EMPTY_HEIGHT);
	   
	   int HUD_BAR_GREEN_FULL_START_X = FileRead.loadInterface(99, 1);
	   int HUD_BAR_GREEN_FULL_START_Y = FileRead.loadInterface(99, 2);
	   int HUD_BAR_GREEN_FULL_WIDTH = FileRead.loadInterface(99, 3);
	   int HUD_BAR_GREEN_FULL_HEIGHT = FileRead.loadInterface(99, 4);
	   hudBarGreenFull = AEFile.cutImageRegion(shipInterfaceImage, HUD_BAR_GREEN_FULL_START_X, HUD_BAR_GREEN_FULL_START_Y, HUD_BAR_GREEN_FULL_WIDTH, HUD_BAR_GREEN_FULL_HEIGHT);
	   
	   int HUD_BAR_BLUE_FULL_START_X = FileRead.loadInterface(100, 1);
	   int HUD_BAR_BLUE_FULL_START_Y = FileRead.loadInterface(100, 2);
	   int HUD_BAR_BLUE_FULL_WIDTH = FileRead.loadInterface(100, 3);
	   int HUD_BAR_BLUE_FULL_HEIGHT = FileRead.loadInterface(100, 4);
	   hudBarBlueFull = AEFile.cutImageRegion(shipInterfaceImage, HUD_BAR_BLUE_FULL_START_X, HUD_BAR_BLUE_FULL_START_Y, HUD_BAR_BLUE_FULL_WIDTH, HUD_BAR_BLUE_FULL_HEIGHT);
	   
	   int HUD_BAR_WHITE_EMPTY_START_X = FileRead.loadInterface(101, 1);
	   int HUD_BAR_WHITE_EMPTY_START_Y = FileRead.loadInterface(101, 2);
	   int HUD_BAR_WHITE_EMPTY_WIDTH = FileRead.loadInterface(101, 3);
	   int HUD_BAR_WHITE_EMPTY_HEIGHT = FileRead.loadInterface(101, 4);
	   hudBarWhiteEmpty = AEFile.cutImageRegion(shipInterfaceImage, HUD_BAR_WHITE_EMPTY_START_X, HUD_BAR_WHITE_EMPTY_START_Y, HUD_BAR_WHITE_EMPTY_WIDTH, HUD_BAR_WHITE_EMPTY_HEIGHT);
	   
	   int HUD_BAR_ORANGE_FULL_START_X = FileRead.loadInterface(102, 1);
	   int HUD_BAR_ORANGE_FULL_START_Y = FileRead.loadInterface(102, 2);
	   int HUD_BAR_ORANGE_FULL_WIDTH = FileRead.loadInterface(102, 3);
	   int HUD_BAR_ORANGE_FULL_HEIGHT = FileRead.loadInterface(102, 4);
	   hudBarOrangeFull = AEFile.cutImageRegion(shipInterfaceImage, HUD_BAR_ORANGE_FULL_START_X, HUD_BAR_ORANGE_FULL_START_Y, HUD_BAR_ORANGE_FULL_WIDTH, HUD_BAR_ORANGE_FULL_HEIGHT);
	   
	   int MENU_BACKGROUND_TEXTBOX_START_X = FileRead.loadInterface(103, 1);
	   int MENU_BACKGROUND_TEXTBOX_START_Y = FileRead.loadInterface(103, 2);
	   int MENU_BACKGROUND_TEXTBOX_WIDTH = FileRead.loadInterface(103, 3);
	   int MENU_BACKGROUND_TEXTBOX_HEIGHT = FileRead.loadInterface(103, 4);
	   menuBackgroundTextBox = AEFile.cutImageRegion(interfaceImage, MENU_BACKGROUND_TEXTBOX_START_X, MENU_BACKGROUND_TEXTBOX_START_Y, MENU_BACKGROUND_TEXTBOX_WIDTH, MENU_BACKGROUND_TEXTBOX_HEIGHT);
	   
	   int TRIANGLE_BUTTON_NORMAL_START_X = FileRead.loadInterface(104, 1);
	   int TRIANGLE_BUTTON_NORMAL_START_Y = FileRead.loadInterface(104, 2);
	   int TRIANGLE_BUTTON_NORMAL_WIDTH = FileRead.loadInterface(104, 3);
	   int TRIANGLE_BUTTON_NORMAL_HEIGHT = FileRead.loadInterface(104, 4);
	   triangleButtonNormal = AEFile.resize_image(AEFile.cutImageRegion(interfaceImage, TRIANGLE_BUTTON_NORMAL_START_X, TRIANGLE_BUTTON_NORMAL_START_Y, TRIANGLE_BUTTON_NORMAL_WIDTH, TRIANGLE_BUTTON_NORMAL_HEIGHT), TRIANGLE_BUTTON_NORMAL_WIDTH * GlobalStatus.INTERFACE_SCALE_MULTIPLIER, TRIANGLE_BUTTON_NORMAL_HEIGHT * GlobalStatus.INTERFACE_SCALE_MULTIPLIER);
	   
	   int TRIANGLE_BUTTON_PRESSED_START_X = FileRead.loadInterface(105, 1);
	   int TRIANGLE_BUTTON_PRESSED_START_Y = FileRead.loadInterface(105, 2);
	   int TRIANGLE_BUTTON_PRESSED_WIDTH = FileRead.loadInterface(105, 3);
	   int TRIANGLE_BUTTON_PRESSED_HEIGHT = FileRead.loadInterface(105, 4);
	   trianlgeButtonPressed = AEFile.resize_image(AEFile.cutImageRegion(interfaceImage, TRIANGLE_BUTTON_PRESSED_START_X, TRIANGLE_BUTTON_PRESSED_START_Y, TRIANGLE_BUTTON_PRESSED_WIDTH, TRIANGLE_BUTTON_PRESSED_HEIGHT), TRIANGLE_BUTTON_PRESSED_WIDTH * GlobalStatus.INTERFACE_SCALE_MULTIPLIER, TRIANGLE_BUTTON_PRESSED_HEIGHT * GlobalStatus.INTERFACE_SCALE_MULTIPLIER);
	   
	   int PLAYER_HOME_START_X = FileRead.loadInterface(106, 1);
	   int PLAYER_HOME_START_Y = FileRead.loadInterface(106, 2);
	   int PLAYER_HOME_WIDTH = FileRead.loadInterface(106, 3);
	   int PLAYER_HOME_HEIGHT = FileRead.loadInterface(106, 4);
	   menuMapPlayerHome = AEFile.cutImageRegion(interfaceImage, PLAYER_HOME_START_X, PLAYER_HOME_START_Y, PLAYER_HOME_WIDTH, PLAYER_HOME_HEIGHT);
	   
	   int RADAR_RING_START_X = FileRead.loadInterface(107, 1);
	   int RADAR_RING_START_Y = FileRead.loadInterface(107, 2);
	   int RADAR_RING_WIDTH = FileRead.loadInterface(107, 3);
	   int RADAR_RING_HEIGHT = FileRead.loadInterface(107, 4);
	   radarRingImage = AEFile.resize_image(AEFile.cutImageRegion(shipInterfaceImage, RADAR_RING_START_X, RADAR_RING_START_Y, RADAR_RING_WIDTH, RADAR_RING_HEIGHT), (RADAR_RING_WIDTH * 2) * GlobalStatus.INTERFACE_SCALE_MULTIPLIER, (RADAR_RING_HEIGHT * 2) * GlobalStatus.INTERFACE_SCALE_MULTIPLIER);
	   
   }


   public static void buildResourceList() {
	   
	   AEResourceManager.addTextureResource(0, PATH_TEXTURES + "main_" + GlobalStatus.texture_type[GlobalStatus.textures] + "x");
	   AEResourceManager.addTextureResource(1, PATH_TEXTURES + "spec");
	   AEResourceManager.addTextureResource(tex_void, PATH_TEXTURES + "tex_void");
	   AEResourceManager.addTextureResource(tex_turrets, PATH_TEXTURES + "tex_turrets");
	   AEResourceManager.addTextureResource(tex_asteroid, PATH_TEXTURES + "asteroid");
	   AEResourceManager.addTextureResource(tex_asteroid_ice, PATH_TEXTURES + "asteroid_ice");
	   AEResourceManager.addTextureResource(tex_asteroid_void, PATH_TEXTURES + "asteroid_void");
	   
	   AEResourceManager.addTextureResource(skybox00, PATH_TEXTURES + "skybox/skybox00");
	   AEResourceManager.addTextureResource(skybox01, PATH_TEXTURES + "skybox/skybox01");
	   AEResourceManager.addTextureResource(skybox02, PATH_TEXTURES + "skybox/skybox02");
	   AEResourceManager.addTextureResource(skybox03, PATH_TEXTURES + "skybox/skybox03");
	   AEResourceManager.addTextureResource(skybox04, PATH_TEXTURES + "skybox/skybox04");
	   AEResourceManager.addTextureResource(skybox05, PATH_TEXTURES + "skybox/skybox05");
	   AEResourceManager.addTextureResource(skybox06, PATH_TEXTURES + "skybox/skybox06");
	   AEResourceManager.addTextureResource(skybox07, PATH_TEXTURES + "skybox/skybox07");
	   AEResourceManager.addTextureResource(skybox08, PATH_TEXTURES + "skybox/skybox08");
	   AEResourceManager.addTextureResource(skybox09, PATH_TEXTURES + "skybox/skybox09");
	   AEResourceManager.addTextureResource(skybox10, PATH_TEXTURES + "skybox/skybox10");
	   
	   AEResourceManager.addTextureResource(tex_station_midorian, PATH_TEXTURES + "tex_station_midorian_high");
	   AEResourceManager.addTextureResource(tex_station_midorian, PATH_TEXTURES + "tex_player_station_add");
	   AEResourceManager.addTextureResource(tex_station_deescience, PATH_TEXTURES + "tex_station_deescience_high");
	   AEResourceManager.addTextureResource(tex_station_deescience_add, PATH_TEXTURES + "tex_station_deescience_add");
	   AEResourceManager.addTextureResource(tex_map_planets, PATH_TEXTURES + "map_planets");
	   AEResourceManager.addTextureResource(tex_suns, PATH_TEXTURES + "sun/suns");
	   AEResourceManager.addTextureResource(tex_bar_terran, PATH_TEXTURES + "tex_bar_terran");
	   AEResourceManager.addTextureResource(tex_bar_nivelian, PATH_TEXTURES + "tex_bar_nivelian");
	   AEResourceManager.addTextureResource(tex_bar_vossk, PATH_TEXTURES + "tex_bar_vossk");
	   AEResourceManager.addTextureResource(tex_bar_midorian, PATH_TEXTURES + "tex_bar_midorian");
	   AEResourceManager.addTextureResource(tex_v_galaxymap_planets, PATH_TEXTURES + "v_galaxymap_planets");
	   AEResourceManager.addTextureResource(tex_sn_galaxymap_planets, PATH_TEXTURES + "sn_galaxymap_planets");
	   AEResourceManager.addTextureResource(tex_sn_sun_011, PATH_TEXTURES + "sn_sun_011");
	   AEResourceManager.addTextureResource(tex_gof1, PATH_TEXTURES + "tex_gof1");
	   
	   AEResourceManager.addTextureResource(tex_planet_0, "/Resource/textures/planets/planet_0");
	   AEResourceManager.addTextureResource(tex_planet_1, "/Resource/textures/planets/planet_1");
	   AEResourceManager.addTextureResource(tex_planet_2, "/Resource/textures/planets/planet_2");
	   AEResourceManager.addTextureResource(tex_planet_3, "/Resource/textures/planets/planet_3");
	   AEResourceManager.addTextureResource(tex_planet_4, "/Resource/textures/planets/planet_4");
	   AEResourceManager.addTextureResource(tex_planet_5, "/Resource/textures/planets/planet_5");
	   AEResourceManager.addTextureResource(tex_planet_6, "/Resource/textures/planets/planet_6");
	   AEResourceManager.addTextureResource(tex_planet_7, "/Resource/textures/planets/planet_7");
	   AEResourceManager.addTextureResource(tex_planet_8, "/Resource/textures/planets/planet_8");
	   AEResourceManager.addTextureResource(tex_planet_9, "/Resource/textures/planets/planet_9");
	   AEResourceManager.addTextureResource(tex_planet_10, "/Resource/textures/planets/planet_10");
	   AEResourceManager.addTextureResource(tex_planet_11, "/Resource/textures/planets/planet_11");
	   AEResourceManager.addTextureResource(tex_planet_12, "/Resource/textures/planets/planet_12");
	   AEResourceManager.addTextureResource(tex_planet_13, "/Resource/textures/planets/planet_13");
	   AEResourceManager.addTextureResource(tex_planet_14, "/Resource/textures/planets/planet_14");
	   AEResourceManager.addTextureResource(tex_planet_15, "/Resource/textures/planets/planet_15");
	   AEResourceManager.addTextureResource(tex_planet_16, "/Resource/textures/planets/planet_16");
	   AEResourceManager.addTextureResource(tex_planet_17, "/Resource/textures/planets/planet_17");
	   AEResourceManager.addTextureResource(tex_planet_18, "/Resource/textures/planets/planet_18");
	   AEResourceManager.addTextureResource(tex_planet_19, "/Resource/textures/planets/planet_19");
	   AEResourceManager.addTextureResource(tex_planet_20, "/Resource/textures/planets/planet_20");
	   AEResourceManager.addTextureResource(tex_planet_21, "/Resource/textures/planets/planet_21");
	   AEResourceManager.addTextureResource(tex_planet_22, "/Resource/textures/planets/planet_22");
	   AEResourceManager.addTextureResource(tex_planet_23, "/Resource/textures/planets/planet_void");
	   AEResourceManager.addTextureResource(tex_planet_24, "/Resource/textures/planets/planet_24");
	   AEResourceManager.addTextureResource(tex_planet_25, "/Resource/textures/planets/planet_25");
	   AEResourceManager.addTextureResource(tex_planet_26, "/Resource/textures/planets/planet_26");
	   
	   if(GlobalStatus.texture_type[GlobalStatus.textures] == 256) {
		   AEResourceManager.addTextureResource(tex_deepscience, PATH_TEXTURES + "low/v_tex_deepscience_low");
		   AEResourceManager.addTextureResource(tex_vossk, PATH_TEXTURES + "low/tex_vossk_low");
		   AEResourceManager.addTextureResource(tex_midorian, PATH_TEXTURES + "low/tex_mido_low");
		   AEResourceManager.addTextureResource(tex_nivelian, PATH_TEXTURES + "low/tex_nivelian_low");
		   AEResourceManager.addTextureResource(tex_pirates, PATH_TEXTURES + "low/tex_pirate_low");
		   AEResourceManager.addTextureResource(tex_terran, PATH_TEXTURES + "low/tex_terran_low");
		   AEResourceManager.addTextureResource(tex_bloodstar, PATH_TEXTURES + "low/sn_tex_bloodstar_low");
		   AEResourceManager.addTextureResource(tex_ships_large, PATH_TEXTURES + "low/tex_terran_ships_large_low");
		   AEResourceManager.addTextureResource(tex_hangar_nivelian, PATH_TEXTURES + "low/tex_hangar_nivelian_low");
		   AEResourceManager.addTextureResource(tex_hangar_terran, PATH_TEXTURES + "low/tex_hangar_terran_low");
		   AEResourceManager.addTextureResource(tex_valkyrie_station, PATH_TEXTURES + "low/v_tex_battlestation_low");
		   AEResourceManager.addTextureResource(tex_void_station, PATH_TEXTURES + "low/tex_void_station_low");
		   AEResourceManager.addTextureResource(tex_bluefyre, PATH_TEXTURES + "low/tex_bluefyre_low");
		   AEResourceManager.addTextureResource(tex_grey, PATH_TEXTURES + "low/tex_grey_low");
		   AEResourceManager.addTextureResource(tex_wraith, PATH_TEXTURES + "low/tex_wraith_low");
		   AEResourceManager.addTextureResource(tex_elite, PATH_TEXTURES + "low/tex_elite_low");
		   AEResourceManager.addTextureResource(tex_gatorcustom, PATH_TEXTURES + "low/tex_gatorcustom_low");
		   AEResourceManager.addTextureResource(tex_amboss, PATH_TEXTURES + "low/tex_amboss_low");
		   AEResourceManager.addTextureResource(tex_scimitar, PATH_TEXTURES + "low/tex_scimitar_low");
		   AEResourceManager.addTextureResource(tex_v_projectiles, PATH_TEXTURES + "low/v_projectiles");
		   AEResourceManager.addTextureResource(tex_main_projectiles, PATH_TEXTURES + "low/projectiles");
		   AEResourceManager.addTextureResource(tex_fx, PATH_TEXTURES + "low/fx");
		   AEResourceManager.addTextureResource(tex_rhino, PATH_TEXTURES + "low/tex_rhino_low");
		   AEResourceManager.addTextureResource(tex_gryphon, PATH_TEXTURES + "low/tex_gryphon_low");
		   AEResourceManager.addTextureResource(tex_nasrrk, PATH_TEXTURES + "low/tex_nasrrk_low");
		   AEResourceManager.addTextureResource(tex_grozamkii, PATH_TEXTURES + "low/tex_grozamkii_low");
		   AEResourceManager.addTextureResource(tex_berger_special, PATH_TEXTURES + "low/tex_berger_special_low");
		   AEResourceManager.addTextureResource(tex_kinzer_rs, PATH_TEXTURES + "low/tex_kinzerrs_low");
		   AEResourceManager.addTextureResource(tex_phantom_xt, PATH_TEXTURES + "low/tex_phantomxt_low");
		   AEResourceManager.addTextureResource(tex_tenetared, PATH_TEXTURES + "low/tex_tenetared_low");
		   AEResourceManager.addTextureResource(tex_darkzov, PATH_TEXTURES + "low/tex_darkzov_low");
		   AEResourceManager.addTextureResource(tex_ghost, PATH_TEXTURES + "low/tex_ghost_low");
		   AEResourceManager.addTextureResource(tex_darkangel, PATH_TEXTURES + "low/tex_darkangel_low");
		   AEResourceManager.addTextureResource(tex_ntirrk, PATH_TEXTURES + "low/tex_ntirrk_low");
		}
		
		if(GlobalStatus.texture_type[GlobalStatus.textures] >= 512) {
			AEResourceManager.addTextureResource(tex_deepscience, PATH_TEXTURES + "high/v_tex_deepscience_high");
			AEResourceManager.addTextureResource(tex_vossk, PATH_TEXTURES + "high/tex_vossk_high");
			AEResourceManager.addTextureResource(tex_midorian, PATH_TEXTURES + "high/tex_mido_high");
			AEResourceManager.addTextureResource(tex_nivelian, PATH_TEXTURES + "high/tex_nivelian_high");
			AEResourceManager.addTextureResource(tex_pirates, PATH_TEXTURES + "high/tex_pirate_high");
			AEResourceManager.addTextureResource(tex_terran, PATH_TEXTURES + "high/tex_terran_high");
			AEResourceManager.addTextureResource(tex_bloodstar, PATH_TEXTURES + "high/sn_tex_bloodstar_high");
			AEResourceManager.addTextureResource(tex_ships_large, PATH_TEXTURES + "high/tex_terran_ships_large_high");
			AEResourceManager.addTextureResource(tex_hangar_nivelian, PATH_TEXTURES + "high/tex_hangar_nivelian_high");
			AEResourceManager.addTextureResource(tex_hangar_terran, PATH_TEXTURES + "high/tex_hangar_terran_high");
			AEResourceManager.addTextureResource(tex_valkyrie_station, PATH_TEXTURES + "high/v_tex_battlestation_high");
			AEResourceManager.addTextureResource(tex_void_station, PATH_TEXTURES + "high/tex_void_station_high");
			AEResourceManager.addTextureResource(tex_bluefyre, PATH_TEXTURES + "high/tex_bluefyre_high");
			AEResourceManager.addTextureResource(tex_grey, PATH_TEXTURES + "high/tex_grey_high");
			AEResourceManager.addTextureResource(tex_wraith, PATH_TEXTURES + "high/tex_wraith_high");
			AEResourceManager.addTextureResource(tex_elite, PATH_TEXTURES + "high/tex_elite_high");
			AEResourceManager.addTextureResource(tex_gatorcustom, PATH_TEXTURES + "high/tex_gatorcustom_high");
			AEResourceManager.addTextureResource(tex_amboss, PATH_TEXTURES + "high/tex_amboss_high");
			AEResourceManager.addTextureResource(tex_scimitar, PATH_TEXTURES + "high/tex_scimitar_high");
			AEResourceManager.addTextureResource(tex_v_projectiles, PATH_TEXTURES + "high/v_projectiles");
			AEResourceManager.addTextureResource(tex_main_projectiles, PATH_TEXTURES + "high/projectiles");
			AEResourceManager.addTextureResource(tex_fx, PATH_TEXTURES + "high/fx");
			AEResourceManager.addTextureResource(tex_rhino, PATH_TEXTURES + "high/tex_rhino_high");
			AEResourceManager.addTextureResource(tex_gryphon, PATH_TEXTURES + "high/tex_gryphon_high");
			AEResourceManager.addTextureResource(tex_nasrrk, PATH_TEXTURES + "high/tex_nasrrk_high");
			AEResourceManager.addTextureResource(tex_grozamkii, PATH_TEXTURES + "high/tex_grozamkii_high");
			AEResourceManager.addTextureResource(tex_berger_special, PATH_TEXTURES + "high/tex_berger_special_high");
			AEResourceManager.addTextureResource(tex_kinzer_rs, PATH_TEXTURES + "high/tex_kinzerrs_high");
			AEResourceManager.addTextureResource(tex_phantom_xt, PATH_TEXTURES + "high/tex_phantomxt_high");
			AEResourceManager.addTextureResource(tex_tenetared, PATH_TEXTURES + "high/tex_tenetared_high");
			AEResourceManager.addTextureResource(tex_darkzov, PATH_TEXTURES + "high/tex_darkzov_high");
			AEResourceManager.addTextureResource(tex_ghost, PATH_TEXTURES + "high/tex_ghost_high");
			AEResourceManager.addTextureResource(tex_darkangel, PATH_TEXTURES + "high/tex_darkangel_high");
			AEResourceManager.addTextureResource(tex_ntirrk, PATH_TEXTURES + "high/tex_ntirrk_high");
		}
		
		AEResourceManager.addGeometryResource(0, PATH_MESHES + "error.aem", 2000, 0);
		AEResourceManager.addGeometryResource(2509, PATH_MESHES + "cross_xyz.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(2510, PATH_MESHES + "col_box.m3g", 15000, 0);
		AEResourceManager.addSkyboxResource(9991, PATH_MESHES + "skybox.m3g", 1);
		AEResourceManager.addGeometryResource(10000, PATH_MESHES + "skybox/skybox00_add.aem", -1, skybox00);
		AEResourceManager.addGeometryResource(10001, PATH_MESHES + "skybox/skybox01_add.aem", -1, skybox01);
		AEResourceManager.addGeometryResource(10002, PATH_MESHES + "skybox/skybox02_add.aem", -1, skybox02);
		AEResourceManager.addGeometryResource(10003, PATH_MESHES + "skybox/skybox03_add.aem", -1, skybox03);
		AEResourceManager.addGeometryResource(10004, PATH_MESHES + "skybox/skybox04_add.aem", -1, skybox04);
		AEResourceManager.addGeometryResource(10005, PATH_MESHES + "skybox/skybox05_add.aem", -1, skybox05);
		AEResourceManager.addGeometryResource(10006, PATH_MESHES + "skybox/skybox06_add.aem", -1, skybox06);
		AEResourceManager.addGeometryResource(10007, PATH_MESHES + "skybox/skybox07_add.aem", -1, skybox07);
		AEResourceManager.addGeometryResource(10008, PATH_MESHES + "skybox/skybox08_add.aem", -1, skybox08);
		AEResourceManager.addGeometryResource(10009, PATH_MESHES + "skybox/skybox09_add.aem", -1, skybox09);
		AEResourceManager.addGeometryResource(10010, PATH_MESHES + "skybox/skybox10_add.aem", -1, skybox10);
		AEResourceManager.addGeometryResource(18, PATH_MESHES + "nuke.m3g", 2000, 0);
		AEResourceManager.addGeometryResource(16, PATH_MESHES + "emp.m3g", 2000, 0);
		AEResourceManager.addGeometryResource(17, PATH_MESHES + "box.m3g", 2000, 0);
		AEResourceManager.addGeometryResource(15, PATH_MESHES + "jumpgate_terran.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3301, PATH_MESHES + "stat_arm0.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3302, PATH_MESHES + "stat_arm1.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3303, PATH_MESHES + "stat_bottom0.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3304, PATH_MESHES + "stat_bottom1.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3305, PATH_MESHES + "stat_bottom2.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3306, PATH_MESHES + "stat_bottom3.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3307, PATH_MESHES + "stat_bottom4.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3308, PATH_MESHES + "stat_bottom5.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3309, PATH_MESHES + "stat_bridge0.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3310, PATH_MESHES + "stat_bridge1.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3311, PATH_MESHES + "stat_bridge2.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3312, PATH_MESHES + "stat_connector0.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3313, PATH_MESHES + "stat_hangar0.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3314, PATH_MESHES + "stat_hangar1.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3315, PATH_MESHES + "stat_hangar2.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3316, PATH_MESHES + "stat_hangar3.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3317, PATH_MESHES + "stat_hangar4.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3318, PATH_MESHES + "stat_middle0.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3319, PATH_MESHES + "stat_middle1.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3320, PATH_MESHES + "stat_middle2.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3321, PATH_MESHES + "stat_middle3.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3322, PATH_MESHES + "stat_top0.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3323, PATH_MESHES + "stat_top1.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3324, PATH_MESHES + "stat_top10.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3325, PATH_MESHES + "stat_top2.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3326, PATH_MESHES + "stat_top3.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3327, PATH_MESHES + "stat_top4.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3328, PATH_MESHES + "stat_top5.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3329, PATH_MESHES + "stat_top6.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3330, PATH_MESHES + "stat_top7.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3331, PATH_MESHES + "stat_top8.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3332, PATH_MESHES + "stat_top9.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3333, PATH_MESHES + "stat_bridge3.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3334, PATH_MESHES + "stat_light0.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3335, PATH_MESHES + "stat_light1.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3338, PATH_MESHES + "stat_vossk_arm1.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3340, PATH_MESHES + "stat_vossk_bottom1.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3339, PATH_MESHES + "stat_vossk_bottom2.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3341, PATH_MESHES + "stat_vossk_middle1.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3342, PATH_MESHES + "stat_vossk_middle2.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3343, PATH_MESHES + "stat_vossk_top1.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3337, PATH_MESHES + "void_station.m3g", 32000, 0);
		
		AEResourceManager.addGeometryResource(3348, PATH_MESHES + "stat_player.aem", 15000, tex_station_midorian);
		AEResourceManager.addGeometryResource(3349, PATH_MESHES + "stat_player_nl.aem", 15000, tex_station_midorian);
		AEResourceManager.addGeometryResource(3350, PATH_MESHES + "stat_player_nl_add.aem", 15000, tex_station_midorian);
		AEResourceManager.addGeometryResource(3351, PATH_MESHES + "stat_player_add.aem", 15000, tex_station_player_add);
		
		AEResourceManager.addGeometryResource(4544, dlc_valkyrie + "station_deep_science.aem", 15000, tex_station_deescience);
		AEResourceManager.addGeometryResource(4545, dlc_valkyrie + "station_deep_science_add.aem", 15000, tex_station_deescience_add);
		AEResourceManager.addGeometryResource(4546, dlc_valkyrie + "station_deep_science_nl.aem", 15000, tex_station_deescience);
		AEResourceManager.addGeometryResource(4547, dlc_valkyrie + "station_deep_science_nl_add.aem", 15000, tex_station_deescience);
		
		AEResourceManager.addGeometryResource(13001, PATH_MESHES + "ship_betty_body.aem", 2000, tex_midorian);
		AEResourceManager.addGeometryResource(13101, PATH_MESHES + "ship_betty_light_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(13002, PATH_MESHES + "ship_teneta_body.aem", 2000, tex_terran);
		AEResourceManager.addGeometryResource(13102, PATH_MESHES + "ship_teneta_light_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(13003, PATH_MESHES + "ship_hiro_body.aem", 2000, tex_pirates);
		AEResourceManager.addGeometryResource(13103, PATH_MESHES + "ship_hiro_light_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(13004, PATH_MESHES + "ship_badger_body.aem", 2000, tex_midorian);
		AEResourceManager.addGeometryResource(13104, PATH_MESHES + "ship_badger_light_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(13005, PATH_MESHES + "ship_dace_body.aem", 2000, tex_nivelian);
		AEResourceManager.addGeometryResource(13105, PATH_MESHES + "ship_dace_nl_add.aem", 2000, tex_nivelian);
		AEResourceManager.addGeometryResource(13006, PATH_MESHES + "ship_inflict_body.aem", 2000, tex_terran);
		AEResourceManager.addGeometryResource(13106, PATH_MESHES + "ship_inflict_light_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(13007, PATH_MESHES + "ship_hector_body.aem", 2000, tex_midorian);
		AEResourceManager.addGeometryResource(13107, PATH_MESHES + "ship_hector_light_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(13008, PATH_MESHES + "ship_anaan_body.aem", 2000, tex_terran);
		AEResourceManager.addGeometryResource(13108, PATH_MESHES + "ship_anaan_light_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(13061, PATH_MESHES + "void_fighter.aem", 2000, tex_void);
		AEResourceManager.addGeometryResource(13161, PATH_MESHES + "void_fighter_nl_add.aem", 2000, tex_void);
		AEResourceManager.addGeometryResource(13162, PATH_MESHES + "ship_hsoc_body.aem", 2000, tex_vossk);
		AEResourceManager.addGeometryResource(13262, PATH_MESHES + "ship_hsoc_nl_add.aem", 2000, tex_vossk);
		AEResourceManager.addGeometryResource(13063, PATH_MESHES + "ship_phantom_body.aem", 2000, tex_terran);
		AEResourceManager.addGeometryResource(13163, PATH_MESHES + "ship_phantom_light_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(13009, PATH_MESHES + "ship_hernstein_body.aem", 2000, tex_pirates);
		AEResourceManager.addGeometryResource(13109, PATH_MESHES + "ship_hernstein_light_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(13010, PATH_MESHES + "ship_type43_body.aem", 2000, tex_nivelian);
		AEResourceManager.addGeometryResource(13110, PATH_MESHES + "ship_type43_nl.aem", 2000, tex_nivelian);
		AEResourceManager.addGeometryResource(13210, PATH_MESHES + "ship_type43_nl_add.aem", 2000, tex_nivelian);
		AEResourceManager.addGeometryResource(13011, PATH_MESHES + "ship_kinzer_body.aem", 2000, tex_nivelian);
		AEResourceManager.addGeometryResource(13111, PATH_MESHES + "ship_kinzer_nl.aem", 2000, tex_nivelian);
		AEResourceManager.addGeometryResource(13211, PATH_MESHES + "ship_kinzer_nl_add.aem", 2000, tex_nivelian);
		AEResourceManager.addGeometryResource(13012, PATH_MESHES + "ship_ward_body.aem", 2000, tex_terran);
		AEResourceManager.addGeometryResource(13112, PATH_MESHES + "ship_ward_light_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(13013, PATH_MESHES + "ship_hatsuyuki_body.aem", 2000, tex_nivelian);
		AEResourceManager.addGeometryResource(13113, PATH_MESHES + "ship_hatsuyuki_nl.aem", 2000, tex_nivelian);
		AEResourceManager.addGeometryResource(13213, PATH_MESHES + "ship_hatsuyuki_nl_add.aem", 2000, tex_nivelian);
		AEResourceManager.addGeometryResource(13014, PATH_MESHES + "ship_nuyangII_body.aem", 2000, tex_midorian);
		AEResourceManager.addGeometryResource(13114, PATH_MESHES + "ship_nuyangII_body.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(13015, PATH_MESHES + "ship_cicero_body.aem", 2000, tex_midorian);
		AEResourceManager.addGeometryResource(13115, PATH_MESHES + "ship_cicero_light_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(13016, PATH_MESHES + "ship_aegir_body.aem", 2000, tex_nivelian);
		AEResourceManager.addGeometryResource(13116, PATH_MESHES + "ship_aegir_nl_add.aem", 2000, tex_nivelian);
		AEResourceManager.addGeometryResource(13017, PATH_MESHES + "ship_groza_body.aem", 2000, tex_terran);
		AEResourceManager.addGeometryResource(13117, PATH_MESHES + "ship_groza_light_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(13018, PATH_MESHES + "ship_azov_body.aem", 2000, tex_pirates);
		AEResourceManager.addGeometryResource(13118, PATH_MESHES + "ship_azov_light_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(13019, PATH_MESHES + "ship_velasco_body.aem", 2000, tex_pirates);
		AEResourceManager.addGeometryResource(13119, PATH_MESHES + "ship_velasco_light_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(13020, PATH_MESHES + "ship_tyrion_body.aem", 2000, tex_pirates);
		AEResourceManager.addGeometryResource(13120, PATH_MESHES + "ship_tyrion_light_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(13021, PATH_MESHES + "ship_hera_body.aem", 2000, tex_terran);
		AEResourceManager.addGeometryResource(13121, PATH_MESHES + "ship_hera_light_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(13022, PATH_MESHES + "ship_taipan_body.aem", 2000, tex_terran);
		AEResourceManager.addGeometryResource(13122, PATH_MESHES + "ship_taipan_light_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(13023, PATH_MESHES + "ship_veteran_body.aem", 2000, tex_terran);
		AEResourceManager.addGeometryResource(13123, PATH_MESHES + "ship_veteran_light_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(13024, PATH_MESHES + "ship_mantis_body.aem", 2000, tex_pirates);
		AEResourceManager.addGeometryResource(13124, PATH_MESHES + "ship_mantis_light_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(13025, PATH_MESHES + "ship_bergrercrossxt_body.aem", 2000, tex_midorian);
		AEResourceManager.addGeometryResource(13125, PATH_MESHES + "ship_bergrercrossxt_light_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(13026, PATH_MESHES + "ship_salvehn_body.aem", 2000, tex_nivelian);
		AEResourceManager.addGeometryResource(13126, PATH_MESHES + "ship_salvehn_nl_add.aem", 2000, tex_nivelian);
		AEResourceManager.addGeometryResource(13027, PATH_MESHES + "ship_wasp_body.aem", 2000, tex_pirates);
		AEResourceManager.addGeometryResource(13127, PATH_MESHES + "ship_wasp_light_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(13028, PATH_MESHES + "ship_furious_body.aem", 2000, tex_terran);
		AEResourceManager.addGeometryResource(13128, PATH_MESHES + "ship_furious_light_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(13029, PATH_MESHES + "ship_razor6_body.aem", 2000, tex_terran);
		AEResourceManager.addGeometryResource(13129, PATH_MESHES + "ship_razor6_light_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(13030, PATH_MESHES + "ship_nightowl_body.aem", 2000, tex_nivelian);
		AEResourceManager.addGeometryResource(13130, PATH_MESHES + "ship_nightowl_nl_add.aem", 2000, tex_nivelian);
		AEResourceManager.addGeometryResource(13031, PATH_MESHES + "ship_cormorant_body.aem", 2000, tex_terran);
		AEResourceManager.addGeometryResource(13131, PATH_MESHES + "ship_cormorant_light_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(13037, dlc_valkyrie + "ship_cronus_body.aem", 2000, tex_deepscience);
		AEResourceManager.addGeometryResource(13137, dlc_valkyrie + "ship_cronus_add.aem", 2000, tex_deepscience);
		AEResourceManager.addGeometryResource(13038, dlc_valkyrie + "ship_typhon_body.aem", 2000, tex_deepscience);
		AEResourceManager.addGeometryResource(13138, dlc_valkyrie + "ship_typhon_light_add.aem", 2000, tex_deepscience);
		AEResourceManager.addGeometryResource(13039, dlc_valkyrie + "ship_skanarr_body.aem", 2000, tex_vossk);
		AEResourceManager.addGeometryResource(13139, dlc_valkyrie + "ship_skanarr_nl.aem", 2000, tex_vossk);
		AEResourceManager.addGeometryResource(13239, dlc_valkyrie + "ship_skanarr_nl_add.aem", 2000, tex_vossk);
		AEResourceManager.addGeometryResource(13040, dlc_valkyrie + "ship_nemesis_body.aem", 2000, tex_deepscience);
		AEResourceManager.addGeometryResource(13140, dlc_valkyrie + "ship_nemesis_add.aem", 2000, tex_deepscience);
		AEResourceManager.addGeometryResource(13041, dlc_valkyrie + "ship_ksuukk_body.aem", 2000, tex_vossk);
		AEResourceManager.addGeometryResource(13141, dlc_valkyrie + "ship_ksuukk_nl_add.aem", 2000, tex_vossk);
		AEResourceManager.addGeometryResource(13042, PATH_MESHES + "ship_volnoor_body.aem", 2000, tex_grey);
		AEResourceManager.addGeometryResource(13142, PATH_MESHES + "ship_volnoor_nl_add.aem", 2000, tex_grey);
		AEResourceManager.addGeometryResource(13242, PATH_MESHES + "ship_volnoor_light_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(13043, PATH_MESHES + "ship_wraith_body.aem", 2000, tex_wraith);
		AEResourceManager.addGeometryResource(13143, PATH_MESHES + "ship_wraith_nl_add.aem", 2000, tex_wraith);
		AEResourceManager.addGeometryResource(13044, dlc_supernova + "ship_specter_body.aem", 2000, tex_elite);
		AEResourceManager.addGeometryResource(13144, dlc_supernova + "ship_specter_light_add.aem", 2000, tex_elite);
		AEResourceManager.addGeometryResource(13045, dlc_supernova + "ship_bloodstar_body.aem", 2000, tex_bloodstar);
		AEResourceManager.addGeometryResource(13145, dlc_supernova + "ship_bloodstar_light_add.aem", 2000, tex_bloodstar);
		AEResourceManager.addGeometryResource(13245, dlc_supernova + "ship_bloodstar_engine_glow_add.aem", 2000, tex_bloodstar);
		AEResourceManager.addGeometryResource(13046, dlc_supernova + "ship_bluefyre_body.aem", 2000, tex_bluefyre);
		AEResourceManager.addGeometryResource(13146, dlc_supernova + "ship_bluefyre_light_add.aem", 2000, tex_bluefyre);
		AEResourceManager.addGeometryResource(13047, dlc_supernova + "ship_gatorcustom_body.aem", 2000, tex_gatorcustom);
		AEResourceManager.addGeometryResource(13147, dlc_supernova + "ship_gatorcustom_light_add.aem", 2000, tex_gatorcustom);
		AEResourceManager.addGeometryResource(13048, dlc_supernova + "ship_amboss_body.aem", 2000, tex_amboss);
		AEResourceManager.addGeometryResource(13148, dlc_supernova + "ship_amboss_light_add.aem", 2000, tex_amboss);
		AEResourceManager.addGeometryResource(13049, dlc_supernova + "ship_scimitar_body.aem", 2000, tex_scimitar);
		AEResourceManager.addGeometryResource(13149, dlc_supernova + "ship_scimitar_light_add.aem", 2000, tex_scimitar);
		AEResourceManager.addGeometryResource(13249, dlc_supernova + "ship_scimitar_emissive.aem", 2000, tex_scimitar);
		AEResourceManager.addGeometryResource(13349, dlc_supernova + "ship_scimitar_emissive_add.aem", 2000, tex_scimitar);
		AEResourceManager.addGeometryResource(13050, dlc_supernova + "ship_rhino_body.aem", 2000, tex_rhino);
		AEResourceManager.addGeometryResource(13150, dlc_supernova + "ship_rhino_light_add.aem", 2000, tex_rhino);
		AEResourceManager.addGeometryResource(13051, dlc_supernova + "ship_gryphon_body.aem", 2000, tex_gryphon);
		AEResourceManager.addGeometryResource(13151, dlc_supernova + "ship_gryphon_light_add.aem", 2000, tex_gryphon);
		AEResourceManager.addGeometryResource(13052, dlc_supernova + "ship_nasrrk_body.aem", 2000, tex_nasrrk);
		AEResourceManager.addGeometryResource(13152, dlc_supernova + "ship_nasrrk_emissive.aem", 2000, tex_nasrrk);
		AEResourceManager.addGeometryResource(13252, dlc_supernova + "ship_nasrrk_emissive_add.aem", 2000, tex_nasrrk);
		AEResourceManager.addGeometryResource(13352, dlc_supernova + "ship_nasrrk_light_add.aem", 2000, tex_nasrrk);
		AEResourceManager.addGeometryResource(13053, dlc_supernova + "ship_grozamkii_body.aem", 2000, tex_grozamkii);
		AEResourceManager.addGeometryResource(13153, dlc_supernova + "ship_grozamkii_light_add.aem", 2000, tex_grozamkii);
		AEResourceManager.addGeometryResource(13054, dlc_supernova + "ship_berger_special_body.aem", 2000, tex_berger_special);
		AEResourceManager.addGeometryResource(13154, dlc_supernova + "ship_berger_special_light_add.aem", 2000, tex_berger_special);
		AEResourceManager.addGeometryResource(13055, dlc_supernova + "ship_kinzer_rs_body.aem", 2000, tex_kinzer_rs);
		AEResourceManager.addGeometryResource(13155, dlc_supernova + "ship_kinzer_rs_light_add.aem", 2000, tex_kinzer_rs);
		AEResourceManager.addGeometryResource(13056, dlc_supernova + "ship_phantomxt_body.aem", 2000, tex_phantom_xt);
		AEResourceManager.addGeometryResource(13156, dlc_supernova + "ship_phantomxt_light_add.aem", 2000, tex_phantom_xt);
		AEResourceManager.addGeometryResource(13057, dlc_supernova + "ship_tenetared_body.aem", 2000, tex_tenetared);
		AEResourceManager.addGeometryResource(13157, dlc_supernova + "ship_tenetared_light_add.aem", 2000, tex_tenetared);
		AEResourceManager.addGeometryResource(13058, dlc_supernova + "ship_darkzov_body.aem", 2000, tex_darkzov);
		AEResourceManager.addGeometryResource(13158, dlc_supernova + "ship_darkzov_light_add.aem", 2000, tex_darkzov);
		AEResourceManager.addGeometryResource(13059, dlc_supernova + "ship_ghost_body.aem", 2000, tex_ghost);
		AEResourceManager.addGeometryResource(13159, dlc_supernova + "ship_ghost_light_add.aem", 2000, tex_ghost);
		AEResourceManager.addGeometryResource(13060, dlc_supernova + "ship_darkangel_body.aem", 2000, tex_darkangel);
		AEResourceManager.addGeometryResource(13160, dlc_supernova + "ship_darkangel_light_add.aem", 2000, tex_darkangel);
		AEResourceManager.addGeometryResource(19061, dlc_supernova + "ship_ntirrk_body.aem", 2000, tex_ntirrk);
		AEResourceManager.addGeometryResource(19161, dlc_supernova + "ship_ntirrk_light_add.aem", 2000, tex_ntirrk);
		AEResourceManager.addGeometryResource(19261, dlc_supernova + "ship_ntirrk_nl.aem", 2000, tex_ntirrk);
		AEResourceManager.addGeometryResource(19361, dlc_supernova + "ship_ntirrk_nl_add.aem", 2000, tex_ntirrk);
		
		AEResourceManager.addGeometryResource(19062, PATH_MESHES + "ship_hawk_body.aem", 2000, tex_gof1);
		AEResourceManager.addGeometryResource(19063, PATH_MESHES + "ship_icarus_body.aem", 2000, tex_gof1);
		AEResourceManager.addGeometryResource(19064, PATH_MESHES + "ship_draaken_body.aem", 2000, tex_gof1);
		
		AEResourceManager.addGeometryResource(20000, PATH_MESHES + "ship_betty_engine_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(20001, PATH_MESHES + "ship_teneta_engine_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(20002, PATH_MESHES + "ship_hiro_engine_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(20003, PATH_MESHES + "ship_badger_engine_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(20004, PATH_MESHES + "ship_dace_engine_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(20005, PATH_MESHES + "ship_inflict_engine_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(20006, PATH_MESHES + "ship_hector_engine_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(20007, PATH_MESHES + "ship_anaan_engine_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(20008, PATH_MESHES + "void_fighter_engine_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(20009, PATH_MESHES + "ship_hsoc_engine_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(20010, PATH_MESHES + "ship_phantom_engine_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(20011, PATH_MESHES + "ship_hernstein_engine_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(20012, PATH_MESHES + "ship_type43_engine_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(20013, PATH_MESHES + "ship_ksarr_engine_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(20014, PATH_MESHES + "ship_tcruiser_engine_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(20015, PATH_MESHES + "ship_ttanker_engine_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(20016, PATH_MESHES + "ship_kinzer_engine_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(20017, PATH_MESHES + "ship_ward_engine_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(20018, PATH_MESHES + "ship_hatsuyuki_engine_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(20019, PATH_MESHES + "ship_nuyangII_engine_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(20020, PATH_MESHES + "ship_cicero_engine_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(20021, PATH_MESHES + "ship_aegir_engine_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(20022, PATH_MESHES + "ship_groza_engine_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(20023, PATH_MESHES + "ship_azov_engine_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(20024, PATH_MESHES + "ship_velasco_engine_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(20025, PATH_MESHES + "ship_tyrion_engine_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(20026, PATH_MESHES + "ship_hera_engine_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(20027, PATH_MESHES + "ship_taipan_engine_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(20028, PATH_MESHES + "ship_veteran_engine_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(20029, PATH_MESHES + "ship_mantis_engine_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(20030, PATH_MESHES + "ship_bergrercrossxt_engine_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(20031, PATH_MESHES + "ship_salvehn_engine_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(20032, PATH_MESHES + "ship_wasp_engine_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(20033, PATH_MESHES + "ship_furious_engine_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(20034, PATH_MESHES + "ship_razor6_engine_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(20035, PATH_MESHES + "ship_nightowl_engine_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(20036, PATH_MESHES + "ship_cormorant_engine_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(20037, dlc_valkyrie + "ship_cronus_engine_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(20038, dlc_valkyrie + "ship_typhon_engine_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(20039, dlc_valkyrie + "ship_skanarr_engine_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(20040, dlc_valkyrie + "ship_nemesis_engine_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(20041, dlc_valkyrie + "ship_ksukk_engine_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(21042, dlc_valkyrie + "ship_volnoor_engine_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(21043, dlc_valkyrie + "ship_wraith_engine_add.aem", 2000, tex_fx);
		AEResourceManager.addGeometryResource(20044, dlc_supernova + "ship_specter_engine_add.aem", 2000, tex_elite);
		AEResourceManager.addGeometryResource(21045, dlc_supernova + "ship_bloodstar_engine_add.aem", 2000, tex_bloodstar);
		AEResourceManager.addGeometryResource(20046, dlc_supernova + "ship_bluefyre_engine_add.aem", 2000, tex_bluefyre);
		AEResourceManager.addGeometryResource(21047, dlc_supernova + "ship_gatorcustom_engine_add.aem", 2000, tex_gatorcustom);
		AEResourceManager.addGeometryResource(20048, dlc_supernova + "ship_amboss_engine_add.aem", 2000, tex_amboss);
		AEResourceManager.addGeometryResource(20049, dlc_supernova + "ship_scimitar_engine_add.aem", 2000, tex_scimitar);
		AEResourceManager.addGeometryResource(20050, dlc_supernova + "ship_rhino_engine_add.aem", 2000, tex_rhino);
		AEResourceManager.addGeometryResource(21050, dlc_supernova + "ship_rhino_engine_glow_add.aem", 2000, tex_rhino);
		AEResourceManager.addGeometryResource(20051, dlc_supernova + "ship_gryphon_engine_add.aem", 2000, tex_gryphon);
		AEResourceManager.addGeometryResource(20052, dlc_supernova + "ship_nasrrk_engine_add.aem", 2000, tex_nasrrk);
		AEResourceManager.addGeometryResource(21052, dlc_supernova + "ship_nasrrk_engine_glow_add.aem", 2000, tex_nasrrk);
		AEResourceManager.addGeometryResource(21053, dlc_supernova + "ship_ghost_engine_glow_add.aem", 2000, tex_ghost);
		AEResourceManager.addGeometryResource(21054, dlc_supernova + "ship_darkangel_engine_glow_add.aem", 2000, tex_darkangel);
		AEResourceManager.addGeometryResource(21055, dlc_supernova + "ship_ntirrk_engine_glow_add.aem", 2000, tex_ntirrk);
		
		AEResourceManager.addGeometryResource(3344, dlc_valkyrie + "battlestation_idle.m3g", 15000, tex_valkyrie_station);
		AEResourceManager.addGeometryResource(3345, dlc_valkyrie + "battlestation_add.aem", 15000, tex_fx);
		
		AEResourceManager.addGeometryResource(13064, PATH_MESHES + "boost_red.m3g", 5000, 1);
		AEResourceManager.addGeometryResource(13065, PATH_MESHES + "boost_violet.m3g", 5000, 1);
		AEResourceManager.addGeometryResource(13067, PATH_MESHES + "boost_cyan_add.aem", 5000, tex_gof1);
		AEResourceManager.addGeometryResource(13068, PATH_MESHES + "boost_green.m3g", 5000, 1);
		AEResourceManager.addGeometryResource(13070, PATH_MESHES + "boost_orange.m3g", 5000, 1);
		AEResourceManager.addGeometryResource(13071, PATH_MESHES + "boost_yellow_add.aem", 5000, tex_gof1);
		AEResourceManager.addGeometryResource(14072, PATH_MESHES + "boost_blue.m3g", 5000, 1);
		
		AEResourceManager.addGeometryResource(13072, PATH_MESHES + "ship_vt_00.aem", 500000, 0);
		AEResourceManager.addGeometryResource(13073, PATH_MESHES + "ship_vt_01.aem", 500000, 0);
		AEResourceManager.addGeometryResource(13074, PATH_MESHES + "ship_vt_02.aem", 500000, 0);
		AEResourceManager.addGeometryResource(13075, PATH_MESHES + "ship_vt_03.aem", 500000, 0);
		AEResourceManager.addGeometryResource(13076, PATH_MESHES + "ship_vt_04.aem", 500000, 0);
		AEResourceManager.addGeometryResource(13077, PATH_MESHES + "ship_vt_05.aem", 500000, 0);
		
		AEResourceManager.addGeometryResource(13078, PATH_MESHES + "ship_tb_00.aem", 50000, 0);
		AEResourceManager.addGeometryResource(13079, PATH_MESHES + "ship_tb_01.aem", 50000, 0);
		AEResourceManager.addGeometryResource(13080, PATH_MESHES + "ship_tb_02.aem", 50000, 0);
		AEResourceManager.addGeometryResource(13081, PATH_MESHES + "ship_tb_03.aem", 50000, 0);
		AEResourceManager.addGeometryResource(13082, PATH_MESHES + "ship_tb_04.aem", 50000, 0);
		AEResourceManager.addGeometryResource(13083, PATH_MESHES + "ship_tb_05.aem", 50000, 0);
		AEResourceManager.addGeometryResource(13084, PATH_MESHES + "ship_tb_06.aem", 50000, 0);
		AEResourceManager.addGeometryResource(13085, PATH_MESHES + "ship_tb_07.aem", 50000, 0);
		AEResourceManager.addGeometryResource(13086, PATH_MESHES + "ship_tb_08.aem", 50000, 0);
		AEResourceManager.addGeometryResource(13087, PATH_MESHES + "ship_tb_09.aem", 50000, 0);
		AEResourceManager.addGeometryResource(13088, PATH_MESHES + "ship_tb_10.aem", 50000, 0);
		AEResourceManager.addGeometryResource(13089, PATH_MESHES + "ship_tb_11.aem", 50000, 0);
		AEResourceManager.addGeometryResource(13090, PATH_MESHES + "ship_tb_12.aem", 50000, 0);
		AEResourceManager.addGeometryResource(13091, PATH_MESHES + "ship_tb_13.aem", 50000, 0);
		AEResourceManager.addGeometryResource(13092, PATH_MESHES + "ship_tb_14.aem", 50000, 0);
		AEResourceManager.addGeometryResource(13093, PATH_MESHES + "ship_tb_15.aem", 50000, 0);
		AEResourceManager.addGeometryResource(13094, PATH_MESHES + "ship_tb_16.aem", 50000, 0);
		
		AEResourceManager.addGeometryResource(13095, PATH_MESHES + "ship_tt_00.aem", 500000, 0);
		AEResourceManager.addGeometryResource(13096, PATH_MESHES + "ship_tt_01.aem", 500000, 0);
		AEResourceManager.addGeometryResource(13097, PATH_MESHES + "ship_tt_02.aem", 500000, 0);
		AEResourceManager.addGeometryResource(13098, PATH_MESHES + "ship_tt_03.aem", 500000, 0);
		
		AEResourceManager.addGeometryResource(13999, PATH_MESHES + "arrow.m3g", 2000, 0);
		AEResourceManager.addGeometryResource(14007, PATH_MESHES + "null.m3g", 15000, 0); // h1s1_
		AEResourceManager.addGeometryResource(14008, PATH_MESHES + "null.m3g", 15000, 0); // h1s2_
		AEResourceManager.addGeometryResource(14009, PATH_MESHES + "null.m3g", 15000, 0); // h1s3_
		AEResourceManager.addGeometryResource(14010, PATH_MESHES + "null.m3g", 15000, 0); // h1s4_
		AEResourceManager.addGeometryResource(14011, PATH_MESHES + "null.m3g", 15000, 0); // h1s5_
		AEResourceManager.addGeometryResource(14012, PATH_MESHES + "hangar_terran.m3g", 15000, tex_hangar_terran); // h1s6_
		AEResourceManager.addGeometryResource(14013, PATH_MESHES + "null.m3g", 15000, 0); // h1s7_
		AEResourceManager.addGeometryResource(14014, PATH_MESHES + "null.m3g", 15000, 0); // h2s1_
		AEResourceManager.addGeometryResource(14015, PATH_MESHES + "null.m3g", 15000, 0); // h2s2_
		AEResourceManager.addGeometryResource(14016, PATH_MESHES + "null.m3g", 15000, 0); // h2s3_
		AEResourceManager.addGeometryResource(14017, PATH_MESHES + "null.m3g", 15000, 0); // h2s4_
		AEResourceManager.addGeometryResource(14018, PATH_MESHES + "hangar_nivelian.m3g", 15000, tex_hangar_nivelian); // h2s5
		AEResourceManager.addGeometryResource(14019, PATH_MESHES + "null.m3g", 15000, 0); // h2s6_
		AEResourceManager.addGeometryResource(14020, PATH_MESHES + "h3s1_.m3g", 15000, 0); // h3s1_
		AEResourceManager.addGeometryResource(14021, PATH_MESHES + "h3s2_.m3g", 15000, 0); // h3s2_
		AEResourceManager.addGeometryResource(14022, PATH_MESHES + "h3s3_.m3g", 15000, 0); // h3s3_
		AEResourceManager.addGeometryResource(14023, PATH_MESHES + "h3s4_.m3g", 15000, 0); // h3s4_
		AEResourceManager.addGeometryResource(14024, PATH_MESHES + "h3s5_.m3g", 15000, 0); // h3s5_
		
		AEResourceManager.addGeometryResource(14025, PATH_MESHES + "bar/bar_terran_nl.aem", 15000, tex_bar_terran); // b1s1_
		AEResourceManager.addGeometryResource(15025, PATH_MESHES + "bar/bar_terran_alpha.aem", 15000, tex_bar_terran); // b1s1_
		AEResourceManager.addGeometryResource(15125, PATH_MESHES + "bar/bar_terran_add.aem", 15000, tex_bar_terran); // b1s1_
		
		AEResourceManager.addGeometryResource(14027, PATH_MESHES + "bar/bar_nivelian_nl.aem", 15000, tex_bar_nivelian); // b2s1_
		AEResourceManager.addGeometryResource(15027, PATH_MESHES + "bar/bar_nivelian_alpha.aem", 15000, tex_bar_nivelian); // b2s1_
		AEResourceManager.addGeometryResource(15127, PATH_MESHES + "bar/bar_nivelian_add.aem", 15000, tex_bar_nivelian); // b2s1_
		
		AEResourceManager.addGeometryResource(14029, PATH_MESHES + "bar/bar_vossk_nl.aem", 15000, tex_bar_vossk); // b3s1_
		AEResourceManager.addGeometryResource(15029, PATH_MESHES + "bar/bar_vossk_alpha.aem", 15000, tex_bar_vossk); // b3s1_
		AEResourceManager.addGeometryResource(15129, PATH_MESHES + "bar/bar_vossk_add.aem", 15000, tex_bar_vossk); // b3s1_
		
		AEResourceManager.addGeometryResource(14329, PATH_MESHES + "bar/bar_midorian_nl.aem", 15000, tex_bar_midorian);
		AEResourceManager.addGeometryResource(14429, PATH_MESHES + "bar/bar_midorian_alpha.aem", 15000, tex_bar_midorian);
		AEResourceManager.addGeometryResource(14529, PATH_MESHES + "bar/bar_midorian_add.aem", 15000, tex_bar_midorian);
		
		AEResourceManager.addGeometryResource(14223, PATH_MESHES + "fig_terran_m.m3g", 2000, 0);
		AEResourceManager.addGeometryResource(14224, PATH_MESHES + "fig_terran_f.m3g", 2000, 0);
		AEResourceManager.addGeometryResource(14225, PATH_MESHES + "fig_vossk.m3g", 2000, 0);
		AEResourceManager.addGeometryResource(14226, PATH_MESHES + "fig_nivelian.m3g", 2000, 0);
		AEResourceManager.addGeometryResource(14227, PATH_MESHES + "fig_multipod.m3g", 2000, 0);
		AEResourceManager.addGeometryResource(14228, PATH_MESHES + "fig_bobolan.m3g", 2000, 0);
		AEResourceManager.addGeometryResource(14229, PATH_MESHES + "fig_grey.m3g", 2000, 0);
		AEResourceManager.addGeometryResource(9992, PATH_MESHES + "explo_small.m3g", 2000, 1);
		AEResourceManager.addGeometryResource(9993, PATH_MESHES + "explo_big.m3g", 2000, 1);
		AEResourceManager.addGeometryResource(9996, PATH_MESHES + "spacejunk.m3g", 2000, 0);
		AEResourceManager.addGeometryResource(6767, PATH_MESHES + "void_waste.m3g", 2000, 0);
		AEResourceManager.addGeometryResource(6769, PATH_MESHES + "asteroid/asteroid.aem", 15000, tex_asteroid);
		AEResourceManager.addGeometryResource(6804, PATH_MESHES + "asteroid/asteroid_void.aem", 15000, tex_asteroid_void);
		AEResourceManager.addGeometryResource(6807, PATH_MESHES + "asteroid/asteroid_ice.aem", 15000, tex_asteroid_ice);
		AEResourceManager.addGeometryResource(6808, PATH_MESHES + "asteroid/asteroid_magma.m3g", 15000, tex_asteroid);
		AEResourceManager.addGeometryResource(6770, PATH_MESHES + "turret_0_stand.m3g", 2000, tex_turrets);
		AEResourceManager.addGeometryResource(6771, PATH_MESHES + "turret_0_gun.m3g", 2000, tex_turrets);
		AEResourceManager.addGeometryResource(6772, PATH_MESHES + "turret_1_stand.m3g", 2000, tex_turrets);
		AEResourceManager.addGeometryResource(6773, PATH_MESHES + "turret_1_gun.m3g", 2000, tex_turrets);
		AEResourceManager.addGeometryResource(6774, PATH_MESHES + "turret_2_stand.m3g", 2000, tex_turrets);
		AEResourceManager.addGeometryResource(6775, PATH_MESHES + "turret_2_gun.m3g", 2000, tex_turrets);
		AEResourceManager.addGeometryResource(6779, PATH_MESHES + "orbit.m3g", 5000, 1);
		AEResourceManager.addGeometryResource(6781, PATH_MESHES + "map_sun.m3g", 15000, 1);
		AEResourceManager.addGeometryResource(6782, PATH_MESHES + "asteroid_explo.m3g", 5000, 0);
		AEResourceManager.addGeometryResource(6783, PATH_MESHES + "Khador_jump.m3g", 2000, 1);
		AEResourceManager.addGeometryResource(6784, PATH_MESHES + "projectiles/gunshot_0.m3g", 5000, 1);
		AEResourceManager.addGeometryResource(6785, PATH_MESHES + "projectiles/gunshot_1.m3g", 5000, 1);
		AEResourceManager.addGeometryResource(6786, PATH_MESHES + "projectiles/gunshot_2.m3g", 5000, 1);
		AEResourceManager.addGeometryResource(6805, PATH_MESHES + "vortex.m3g", 15000, 1);
		AEResourceManager.addGeometryResource(6806, PATH_MESHES + "vortex_dust.m3g", 5000, 1);
		
		AEResourceManager.addGeometryResource(3000, PATH_MESHES + "galaxymap/map_planet_000.aem", 2000, tex_map_planets);
		AEResourceManager.addGeometryResource(3001, PATH_MESHES + "galaxymap/map_planet_001.aem", 2000, tex_map_planets);
		AEResourceManager.addGeometryResource(3002, PATH_MESHES + "galaxymap/map_planet_002.aem", 2000, tex_map_planets);
		AEResourceManager.addGeometryResource(3003, PATH_MESHES + "galaxymap/map_planet_003.aem", 2000, tex_map_planets);
		AEResourceManager.addGeometryResource(3004, PATH_MESHES + "galaxymap/map_planet_004.aem", 2000, tex_map_planets);
		AEResourceManager.addGeometryResource(3005, PATH_MESHES + "galaxymap/map_planet_005.aem", 2000, tex_map_planets);
		AEResourceManager.addGeometryResource(3006, PATH_MESHES + "galaxymap/map_planet_006.aem", 2000, tex_map_planets);
		AEResourceManager.addGeometryResource(3007, PATH_MESHES + "galaxymap/map_planet_007.aem", 2000, tex_map_planets);
		AEResourceManager.addGeometryResource(3008, PATH_MESHES + "galaxymap/map_planet_008.aem", 2000, tex_map_planets);
		AEResourceManager.addGeometryResource(3009, PATH_MESHES + "galaxymap/map_planet_009.aem", 2000, tex_map_planets);
		AEResourceManager.addGeometryResource(3010, PATH_MESHES + "galaxymap/map_planet_010.aem", 2000, tex_map_planets);
		AEResourceManager.addGeometryResource(3011, PATH_MESHES + "galaxymap/map_planet_011.aem", 2000, tex_map_planets);
		AEResourceManager.addGeometryResource(3012, PATH_MESHES + "galaxymap/map_planet_012.aem", 2000, tex_map_planets);
		AEResourceManager.addGeometryResource(3013, PATH_MESHES + "galaxymap/map_planet_013.aem", 2000, tex_map_planets);
		AEResourceManager.addGeometryResource(3014, PATH_MESHES + "galaxymap/map_planet_014.aem", 2000, tex_map_planets);
		AEResourceManager.addGeometryResource(3015, PATH_MESHES + "galaxymap/map_planet_015.aem", 2000, tex_map_planets);
		AEResourceManager.addGeometryResource(3016, PATH_MESHES + "galaxymap/map_planet_016.aem", 2000, tex_map_planets);
		AEResourceManager.addGeometryResource(3017, PATH_MESHES + "galaxymap/map_planet_017.aem", 2000, tex_map_planets);
		AEResourceManager.addGeometryResource(3018, PATH_MESHES + "galaxymap/map_planet_018.aem", 2000, tex_map_planets);
		AEResourceManager.addGeometryResource(3019, PATH_MESHES + "galaxymap/map_planet_019.aem", 2000, tex_map_planets);
		AEResourceManager.addGeometryResource(3020, PATH_MESHES + "galaxymap/v_map_planet_020.aem", 2000, tex_v_galaxymap_planets);
		AEResourceManager.addGeometryResource(3021, PATH_MESHES + "galaxymap/v_map_planet_021.aem", 2000, tex_v_galaxymap_planets);
		AEResourceManager.addGeometryResource(3022, PATH_MESHES + "galaxymap/v_map_planet_022.aem", 2000, tex_v_galaxymap_planets);
		AEResourceManager.addGeometryResource(3023, PATH_MESHES + "galaxymap/map_planet_019.aem", 2000, tex_map_planets);
		AEResourceManager.addGeometryResource(3024, PATH_MESHES + "galaxymap/sd_sn_map_planet_024.aem", 2000, tex_sn_galaxymap_planets);
		AEResourceManager.addGeometryResource(3025, PATH_MESHES + "galaxymap/sd_sn_map_planet_025.aem", 2000, tex_sn_galaxymap_planets);
		AEResourceManager.addGeometryResource(3026, PATH_MESHES + "galaxymap/sd_sn_map_planet_026.aem", 2000, tex_sn_galaxymap_planets);
		
		AEResourceManager.addGeometryResource(3100, PATH_MESHES + "galaxymap/map_sun_000_add.aem", 2000, tex_suns);
		AEResourceManager.addGeometryResource(3101, PATH_MESHES + "galaxymap/map_sun_001_add.aem", 2000, tex_suns);
		AEResourceManager.addGeometryResource(3102, PATH_MESHES + "galaxymap/map_sun_002_add.aem", 2000, tex_suns);
		AEResourceManager.addGeometryResource(3103, PATH_MESHES + "galaxymap/map_sun_003_add.aem", 2000, tex_suns);
		AEResourceManager.addGeometryResource(3104, PATH_MESHES + "galaxymap/map_sun_004_add.aem", 2000, tex_suns);
		AEResourceManager.addGeometryResource(3105, PATH_MESHES + "galaxymap/map_sun_005_add.aem", 2000, tex_suns);
		AEResourceManager.addGeometryResource(3106, PATH_MESHES + "galaxymap/map_sun_006_add.aem", 2000, tex_suns);
		AEResourceManager.addGeometryResource(3107, PATH_MESHES + "galaxymap/map_sun_007_add.aem", 2000, tex_suns);
		AEResourceManager.addGeometryResource(3108, PATH_MESHES + "galaxymap/map_sun_008_add.aem", 2000, tex_suns);
		AEResourceManager.addGeometryResource(3109, PATH_MESHES + "galaxymap/map_sun_009_add.aem", 2000, tex_suns);
		AEResourceManager.addGeometryResource(3110, PATH_MESHES + "galaxymap/map_sun_010_add.aem", 2000, tex_suns);
		AEResourceManager.addGeometryResource(3111, PATH_MESHES + "galaxymap/sd_sn_map_sun_011_add.aem", 2000, tex_sn_galaxymap_planets);
		
		AEResourceManager.addGeometryResource(3200, PATH_MESHES + "plane_alpha.aem", 500000, tex_planet_0);
		AEResourceManager.addGeometryResource(3201, PATH_MESHES + "plane_alpha.aem", 500000, tex_planet_1);
		AEResourceManager.addGeometryResource(3202, PATH_MESHES + "plane_alpha.aem", 500000, tex_planet_2);
		AEResourceManager.addGeometryResource(3203, PATH_MESHES + "plane_alpha.aem", 500000, tex_planet_3);
		AEResourceManager.addGeometryResource(3204, PATH_MESHES + "plane_alpha.aem", 500000, tex_planet_4);
		AEResourceManager.addGeometryResource(3205, PATH_MESHES + "plane_alpha.aem", 500000, tex_planet_5);
		AEResourceManager.addGeometryResource(3206, PATH_MESHES + "plane_alpha.aem", 500000, tex_planet_6);
		AEResourceManager.addGeometryResource(3207, PATH_MESHES + "plane_alpha.aem", 500000, tex_planet_7);
		AEResourceManager.addGeometryResource(3208, PATH_MESHES + "plane_alpha.aem", 500000, tex_planet_8);
		AEResourceManager.addGeometryResource(3209, PATH_MESHES + "plane_alpha.aem", 500000, tex_planet_9);
		AEResourceManager.addGeometryResource(3210, PATH_MESHES + "plane_alpha.aem", 500000, tex_planet_10);
		AEResourceManager.addGeometryResource(3211, PATH_MESHES + "plane_alpha.aem", 500000, tex_planet_11);
		AEResourceManager.addGeometryResource(3212, PATH_MESHES + "plane_alpha.aem", 500000, tex_planet_12);
		AEResourceManager.addGeometryResource(3213, PATH_MESHES + "plane_alpha.aem", 500000, tex_planet_13);
		AEResourceManager.addGeometryResource(3214, PATH_MESHES + "plane_alpha.aem", 500000, tex_planet_14);
		AEResourceManager.addGeometryResource(3215, PATH_MESHES + "plane_alpha.aem", 500000, tex_planet_15);
		AEResourceManager.addGeometryResource(3216, PATH_MESHES + "plane_alpha.aem", 500000, tex_planet_16);
		AEResourceManager.addGeometryResource(3217, PATH_MESHES + "plane_alpha.aem", 500000, tex_planet_17);
		AEResourceManager.addGeometryResource(3218, PATH_MESHES + "plane_alpha.aem", 500000, tex_planet_18);
		AEResourceManager.addGeometryResource(3219, PATH_MESHES + "plane_alpha.aem", 500000, tex_planet_19);
		AEResourceManager.addGeometryResource(3220, PATH_MESHES + "plane_alpha.aem", 500000, tex_planet_20);
		AEResourceManager.addGeometryResource(3221, PATH_MESHES + "plane_alpha.aem", 500000, tex_planet_21);
		AEResourceManager.addGeometryResource(3222, PATH_MESHES + "plane_alpha.aem", 500000, tex_planet_22);
		AEResourceManager.addGeometryResource(3223, PATH_MESHES + "plane_alpha.aem", 500000, tex_planet_23);
		AEResourceManager.addGeometryResource(3224, PATH_MESHES + "plane_alpha.aem", 500000, tex_planet_24);
		AEResourceManager.addGeometryResource(3225, PATH_MESHES + "plane_alpha.aem", 500000, tex_planet_25);
		AEResourceManager.addGeometryResource(3226, PATH_MESHES + "plane_alpha.aem", 500000, tex_planet_26);
		AEResourceManager.addGeometryResource(3227, PATH_MESHES + "plane_alpha.aem", 500000, tex_planet_27);
		AEResourceManager.addGeometryResource(3228, PATH_MESHES + "plane_alpha.aem", 500000, tex_planet_28);
		AEResourceManager.addGeometryResource(3229, PATH_MESHES + "plane_alpha.aem", 500000, tex_planet_29);
		
		AEResourceManager.addGeometryResource(6754, PATH_MESHES + "projectiles/laser_orange_add.aem", 5000, tex_fx);
		AEResourceManager.addGeometryResource(6755, PATH_MESHES + "projectiles/laser_yellow_add.aem", 5000, tex_fx);
		AEResourceManager.addGeometryResource(6756, PATH_MESHES + "projectiles/laser_white_add.aem", 5000, tex_fx);
		AEResourceManager.addGeometryResource(6760, PATH_MESHES + "projectiles/laser_green_add.aem", 5000, tex_fx);
		AEResourceManager.addGeometryResource(6761, PATH_MESHES + "projectiles/laser_red.m3g", 5000, 1);
		AEResourceManager.addGeometryResource(6762, PATH_MESHES + "projectiles/laser_cyan_add.aem", 5000, tex_fx);
		AEResourceManager.addGeometryResource(6763, PATH_MESHES + "projectiles/laser_blue_add.aem", 5000, tex_fx);
		AEResourceManager.addGeometryResource(6764, PATH_MESHES + "projectiles/laser_voilet_add.aem", 5000, tex_fx);
		AEResourceManager.addGeometryResource(6765, PATH_MESHES + "projectiles/laser_red.m3g", 5000, 1);
		AEResourceManager.addGeometryResource(6788, PATH_MESHES + "projectiles/blaster_red_add.aem", 5000, tex_fx);
		AEResourceManager.addGeometryResource(6789, PATH_MESHES + "projectiles/blaster_0_add.aem", 5000, tex_fx);
		AEResourceManager.addGeometryResource(6790, PATH_MESHES + "projectiles/blaster_yellow_add.aem", 5000, tex_fx);
		AEResourceManager.addGeometryResource(6791, PATH_MESHES + "projectiles/blaster_green_add.aem", 5000, tex_fx);
		AEResourceManager.addGeometryResource(6792, PATH_MESHES + "projectiles/blaster_white_add.aem", 5000, tex_fx);
		AEResourceManager.addGeometryResource(6793, PATH_MESHES + "projectiles/blaster_cyan_add.aem", 5000, tex_fx);
		AEResourceManager.addGeometryResource(6794, PATH_MESHES + "projectiles/blaster_blue_add.aem", 5000, tex_fx);
		AEResourceManager.addGeometryResource(6795, PATH_MESHES + "projectiles/blaster_red_add.aem", 5000, tex_fx);
		AEResourceManager.addGeometryResource(6796, PATH_MESHES + "projectiles/blaster_yellow_add.aem", 5000, tex_fx);
		AEResourceManager.addGeometryResource(6797, PATH_MESHES + "projectiles/blaster_violet_add.aem", 5000, tex_fx);
		
		AEResourceManager.addGeometryResource(6812, PATH_MESHES + "projectiles/projectile_071_add.aem", 5000, tex_fx); // очень яркий, синий почти белый большой лазер
		AEResourceManager.addGeometryResource(6813, PATH_MESHES + "projectiles/projectile_072_add.aem", 5000, tex_fx); // очень яркий, зеленый большой лазер
		AEResourceManager.addGeometryResource(6814, PATH_MESHES + "projectiles/projectile_073_add.aem", 5000, tex_fx); // большой фиолетовый шар
		AEResourceManager.addGeometryResource(6815, PATH_MESHES + "projectiles/projectile_074_add.aem", 5000, tex_fx); // тонкий темно-синий лазер
		AEResourceManager.addGeometryResource(6816, PATH_MESHES + "projectiles/projectile_075_add.aem", 5000, tex_fx); // фиеолетовый с темно-синим лазер
		AEResourceManager.addGeometryResource(6817, PATH_MESHES + "projectiles/projectile_076_add.aem", 5000, tex_fx); // синий минималистичный лазер
		AEResourceManager.addGeometryResource(6818, PATH_MESHES + "projectiles/projectile_077_add.aem", 5000, tex_fx); // красный лазер
		AEResourceManager.addGeometryResource(6819, PATH_MESHES + "projectiles/projectile_078_add.aem", 5000, tex_fx); // гладкий фиолетовый лазер со светящимся шаром
		AEResourceManager.addGeometryResource(6820, PATH_MESHES + "projectiles/projectile_079_add.aem", 5000, tex_fx); // гладкий желтый лазер со светящимся шаром
		AEResourceManager.addGeometryResource(6821, PATH_MESHES + "projectiles/projectile_080_add.aem", 5000, tex_fx); // синеватая четырехлучевая звезда
		AEResourceManager.addGeometryResource(6822, PATH_MESHES + "projectiles/projectile_193_add.aem", 5000, tex_fx); // маленький желтый шар
		AEResourceManager.addGeometryResource(6823, PATH_MESHES + "projectiles/projectile_194_add.aem", 5000, tex_fx); // хз что такое
		AEResourceManager.addGeometryResource(6845, PATH_MESHES + "projectiles/missile.aem", 5000, tex_fx);
		
		AEResourceManager.addGeometryResource(6900, PATH_MESHES + "projectiles/projectile_000_anim_add.aem", 5000, tex_main_projectiles);
		AEResourceManager.addGeometryResource(6902, PATH_MESHES + "projectiles/projectile_002_anim_add.aem", 5000, tex_main_projectiles);
		AEResourceManager.addGeometryResource(6903, PATH_MESHES + "projectiles/projectile_003_anim_add.aem", 5000, tex_main_projectiles);
		AEResourceManager.addGeometryResource(6912, PATH_MESHES + "projectiles/projectile_012_anim_add.aem", 5000, tex_main_projectiles);
		AEResourceManager.addGeometryResource(6913, PATH_MESHES + "projectiles/projectile_013_anim_add.aem", 5000, tex_main_projectiles);
		AEResourceManager.addGeometryResource(6914, PATH_MESHES + "projectiles/projectile_014_anim_add.aem", 5000, tex_main_projectiles);
		AEResourceManager.addGeometryResource(6915, PATH_MESHES + "projectiles/projectile_015_anim_add.aem", 5000, tex_main_projectiles);
		AEResourceManager.addGeometryResource(6916, PATH_MESHES + "projectiles/projectile_016_anim_add.aem", 5000, tex_main_projectiles);
		AEResourceManager.addGeometryResource(6917, PATH_MESHES + "projectiles/projectile_017_anim_add.aem", 5000, tex_main_projectiles);
		AEResourceManager.addGeometryResource(6918, PATH_MESHES + "projectiles/projectile_018_anim_add.aem", 5000, tex_main_projectiles);
		AEResourceManager.addGeometryResource(6921, PATH_MESHES + "projectiles/projectile_021_anim_add.aem", 5000, tex_main_projectiles);
		AEResourceManager.addGeometryResource(6922, PATH_MESHES + "projectiles/projectile_022_anim_add.aem", 5000, tex_main_projectiles);
		AEResourceManager.addGeometryResource(6923, PATH_MESHES + "projectiles/projectile_023_anim_add.aem", 5000, tex_main_projectiles);
		AEResourceManager.addGeometryResource(6924, PATH_MESHES + "projectiles/projectile_024_anim_add.aem", 5000, tex_main_projectiles);
		AEResourceManager.addGeometryResource(6925, PATH_MESHES + "projectiles/projectile_025_anim_add.aem", 5000, tex_main_projectiles);
		AEResourceManager.addGeometryResource(6926, PATH_MESHES + "projectiles/projectile_026_anim_add.aem", 5000, tex_main_projectiles);
		AEResourceManager.addGeometryResource(6927, PATH_MESHES + "projectiles/projectile_027_anim_add.aem", 5000, tex_main_projectiles);
		
		AEResourceManager.addGeometryResource(6824, PATH_MESHES + "projectiles/v_projectile_176_anim_add.aem", 5000, tex_v_projectiles);
		AEResourceManager.addGeometryResource(6825, PATH_MESHES + "projectiles/v_projectile_177_anim_add.aem", 5000, tex_v_projectiles);
		AEResourceManager.addGeometryResource(6826, PATH_MESHES + "projectiles/v_projectile_178_anim_add.aem", 5000, tex_v_projectiles);
		AEResourceManager.addGeometryResource(6834, PATH_MESHES + "projectiles/v_projectile_183_anim_add.aem", 5000, tex_v_projectiles);
		AEResourceManager.addGeometryResource(6844, PATH_MESHES + "projectiles/v_projectile_193_anim_add.aem", 5000, tex_v_projectiles);
		
		AEResourceManager.addGeometryResource(5555, PATH_MESHES + "test_1.m3g", 5000, 1);
		AEResourceManager.addGeometryResource(5655, PATH_MESHES + "test_2.m3g", 5000, 1);
		AEResourceManager.addGeometryResource(5755, PATH_MESHES + "test_3.m3g", 5000, 1);
		
		AEResourceManager.addGeometryResource(6555, PATH_MESHES + "test_1.aem", 5000, 1);
		AEResourceManager.addGeometryResource(6655, PATH_MESHES + "test_2.aem", 5000, 1);
		AEResourceManager.addGeometryResource(6755, PATH_MESHES + "test_3.aem", 5000, 1);
		AEResourceManager.addGeometryResource(6855, PATH_MESHES + "test_1_add.aem", 5000, tex_fx);
		AEResourceManager.addGeometryResource(6955, PATH_MESHES + "test_2_add.aem", 5000, tex_fx);
		AEResourceManager.addGeometryResource(6965, PATH_MESHES + "test_3_add.aem", 5000, tex_fx);
		
	}

   public static void OnRelease() {
      ships = null;
      items = null;
      AEResourceManager.OnRelease();
   }

   static { // лица для сюжета?
      short[][] var10000 = new short[][]{{11, 22, 28, 5, 7}, {4, 30}, {3, 14}, {9, 16}};
      var10000 = new short[][]{{11, 22, 28, 5, 7}, {4, 30}, {3, 14}, {9, 16}};
      CHAR_KEITH = new byte[]{0, 7, 7, 7, 7};
      CHAR_BRENT = new byte[]{0, 2, 2, 2, 2};
      CHAR_GUNANT = new byte[]{2, 1, 1, 1, 1};
      CHAR_NORRIS = new byte[]{0, 3, 3, 3, 3};
      CHAR_MKKT_BKKT = new byte[]{4, 0, 0, 0, 0};
      CHAR_TOMMY = new byte[]{0, 4, 4, 4, 4};
      CHAR_CARLA = new byte[]{10, 0, 0, 0, 0};
      CHAR_ERRKT = new byte[]{1, 0, 0, 0, 0};
      CHAR_JEAN = new byte[]{0, 3, 2, 3, 1};
      CHAR_PIRATE_CHIEF = new byte[]{0, 1, 2, 3, 4};
      CHAR_PIRATE_1 = new byte[]{0, 2, 3, 3, 5};
      CHAR_PIRATE_2 = new byte[]{0, 3, 4, 3, 6};
      CHAR_SECURITY_GUY = new byte[]{0, 6, 0, 5, 1};
      CHAR_SECURITY_GIRL = new byte[]{10, 1, 2, 3, 4};
      CHAR_KIDNAPPER = new byte[]{2, 1, 2, 1, 2};
      CHAR_STORY = new byte[]{11, 0, 0, 0, 0};
      CHAR_COMPUTER = new byte[]{11, 0, 0, 0, 0};
      CHAR_INFO_PIC = new byte[]{11, 0, 0, 0, 0};
      CHAR_TERRAN_OFFICER = new byte[]{0, 2, 4, 4, 4};
      CHAR_VOID = new byte[]{9, 0, 0, 0, 0};
      CHAR_KHADOR = new byte[]{7, 0, 0, 0, 0};
      CHAR_NIVELIAN_SECURITY = new byte[]{2, 0, 0, 0, 0};
      CHAR_IMAGES = new byte[][]{CHAR_KEITH, CHAR_BRENT, CHAR_GUNANT, CHAR_NORRIS, CHAR_MKKT_BKKT, CHAR_TOMMY, CHAR_CARLA, CHAR_ERRKT, CHAR_JEAN, CHAR_PIRATE_CHIEF, CHAR_PIRATE_1, CHAR_PIRATE_2, CHAR_SECURITY_GUY, CHAR_SECURITY_GIRL, CHAR_KIDNAPPER, CHAR_STORY, CHAR_COMPUTER, CHAR_INFO_PIC, CHAR_TERRAN_OFFICER, CHAR_VOID, CHAR_KHADOR, CHAR_NIVELIAN_SECURITY};
   }
}
