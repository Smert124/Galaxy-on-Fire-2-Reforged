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
   private static String PATH_INTERFACE = "/Resource/interface/";
   private static String PATH_CONFIG = "/Configs/";
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
   public static Image itemsImage;
   
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
   
   public static Image beveledButtonNormal;
   public static Image beveledButtonPressed;
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
   private static int tex_beams = 1095;
   private static int tex_station_terran = 1096;
   private static int tex_col_test = 1097;
   
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

         if(var5.getID() == 2509 || var5.getID() == 13261 || var5.getID() == 6755 || var5.getID() == 6756 || var5.getID() == 6760 || var5.getID() == 6761 || var5.getID() == 6762 || var5.getID() == 6763 || var5.getID() == 6764 || var5.getID() == 6765 || var5.getID() == 3334 || var5.getID() == 3335 || var5.getID() == 6788 || var5.getID() == 6789 || var5.getID() == 6790 || var5.getID() == 6791 || var5.getID() == 6792 || var5.getID() == 6793 || var5.getID() == 6794 || var5.getID() == 6795 || var5.getID() == 6796 || var5.getID() == 6797 || var5.getID() == 13067 || var5.getID() == 13068 || var5.getID() == 13070 || var5.getID() == 13064 || var5.getID() == 14072 || var5.getID() == 13065 || var5.getID() == 13071 || var5.getID() == 13061 || var5.getID() == 13063 || var0 == 14 || var0 == 13 || var0 == 15 || var5.getID() >= 13001 && var5.getID() <= 14000 || var5.getID() >= 20000 && var5.getID() <= 20100 || var5.getID() == 2510 || var5.getID() == 2511) {
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
	   
	   AEResourceManager.setText(1, PATH_CONFIG + "master_config.txt");
	   AEResourceManager.setText(2, PATH_CONFIG + "agents.txt");
	   AEResourceManager.setText(3, PATH_CONFIG + "items.txt");
	   AEResourceManager.setText(4, PATH_CONFIG + "projectiles.txt");
	   AEResourceManager.setText(5, PATH_CONFIG + "shipparts.txt");
	   AEResourceManager.setText(6, PATH_CONFIG + "ships.txt");
	   AEResourceManager.setText(7, PATH_CONFIG + "ships_turrets.txt");
	   AEResourceManager.setText(8, PATH_CONFIG + "ships_viewport.txt");
	   AEResourceManager.setText(9, PATH_CONFIG + "stationparts.txt");
	   AEResourceManager.setText(10, PATH_CONFIG + "stations.txt");
	   AEResourceManager.setText(11, PATH_CONFIG + "systems.txt");
	   AEResourceManager.setText(13, PATH_CONFIG + "collision.txt");
	   AEResourceManager.setText(100, PATH_CONFIG + "names_terran_0_m.txt");
	   AEResourceManager.setText(101, PATH_CONFIG + "names_terran_0_w.txt");
	   AEResourceManager.setText(102, PATH_CONFIG + "names_terran_1.txt");
	   AEResourceManager.setText(103, PATH_CONFIG + "names_vossk_0.txt");
	   AEResourceManager.setText(104, PATH_CONFIG + "names_vossk_1.txt");
	   AEResourceManager.setText(105, PATH_CONFIG + "names_nivelian_0.txt");
	   AEResourceManager.setText(106, PATH_CONFIG + "names_nivelian_1.txt");
	   AEResourceManager.setText(107, PATH_CONFIG + "names_multipod_0.txt");
	   AEResourceManager.setText(108, PATH_CONFIG + "names_multipod_1.txt");
	   AEResourceManager.setText(109, PATH_CONFIG + "names_cyborg_0.txt");
	   AEResourceManager.setText(110, PATH_CONFIG + "names_bobolan_0.txt");
	   AEResourceManager.setText(111, PATH_CONFIG + "names_bobolan_1.txt");
	   AEResourceManager.setText(112, PATH_CONFIG + "names_grey_0.txt");

   }
   
   public static void loadImages() {
	   
	   AEResourceManager.setImage(0, PATH_INTERFACE + "empty.png");
	   
	   AEResourceManager.setImage(3, PATH_INTERFACE + "items.png");
	   AEResourceManager.setImage(14, PATH_INTERFACE + "logo_0.png");
	   AEResourceManager.setImage(15, PATH_INTERFACE + "logo_1.png");
	   AEResourceManager.setImage(16, PATH_INTERFACE + "logo_2.png");
	   AEResourceManager.setImage(17, PATH_INTERFACE + "logo_3.png");
	   AEResourceManager.setImage(18, PATH_INTERFACE + "logo_4.png");
	   AEResourceManager.setImage(19, PATH_INTERFACE + "logo_5.png");
	   AEResourceManager.setImage(20, PATH_INTERFACE + "logo_6.png");
	   AEResourceManager.setImage(21, PATH_INTERFACE + "logo_7.png");
	   AEResourceManager.setImage(22, PATH_INTERFACE + "logo_8.png");
	   AEResourceManager.setImage(23, PATH_INTERFACE + "logo_9.png");
	   
	   AEResourceManager.setImage(60, PATH_TEXTURES + "fog_galaxy_low.png");
	   AEResourceManager.setImage(61, PATH_TEXTURES + "fog_galaxy_high.png");
	   
	   AEResourceManager.setImage(68, PATH_INTERFACE + "standing_small_0.png");
	   AEResourceManager.setImage(69, PATH_INTERFACE + "standing_0.png");
	   AEResourceManager.setImage(70, PATH_INTERFACE + "standing_small_1.png");
	   AEResourceManager.setImage(71, PATH_INTERFACE + "standing_1.png");
	   AEResourceManager.setImage(72, PATH_INTERFACE + "standing_small_2.png");
	   AEResourceManager.setImage(73, PATH_INTERFACE + "standing_2.png");
	   AEResourceManager.setImage(74, PATH_INTERFACE + "faces/Brille_golden.png");
	   AEResourceManager.setImage(75, PATH_INTERFACE + "faces/Brille_schwarz.png");
	   
	   AEResourceManager.setImage(76, PATH_INTERFACE + "panel_info_upper.png");
	   AEResourceManager.setImage(77, PATH_INTERFACE + "hud_lockon_neutral.png");
	   AEResourceManager.setImage(78, PATH_INTERFACE + "hud_lockon_neutral_far.png");
	   AEResourceManager.setImage(79, PATH_INTERFACE + "hud_radaricon_neutral.png");
	   AEResourceManager.setImage(80, PATH_INTERFACE + "hud_lockon_enemy.png");
	   AEResourceManager.setImage(81, PATH_INTERFACE + "hud_lockon_enemy_far.png");
	   AEResourceManager.setImage(82, PATH_INTERFACE + "hud_radaricon_enemy.png");
	   AEResourceManager.setImage(83, PATH_INTERFACE + "hud_lockon_friend.png");
	   AEResourceManager.setImage(84, PATH_INTERFACE + "hud_lockon_friend_far.png");
	   AEResourceManager.setImage(85, PATH_INTERFACE + "hud_radaricon_friend.png");
	   AEResourceManager.setImage(86, PATH_INTERFACE + "hud_radaricon_waypoint.png");
	   AEResourceManager.setImage(87, PATH_INTERFACE + "hud_lockon_waypoint.png");
	   AEResourceManager.setImage(88, PATH_INTERFACE + "logos_small.png");
	   AEResourceManager.setImage(89, PATH_INTERFACE + "bracket_box.png");
	   AEResourceManager.setImage(90, PATH_INTERFACE + "hud_crate.png");
	   AEResourceManager.setImage(91, PATH_INTERFACE + "hud_spacejunk.png");
	   AEResourceManager.setImage(92, PATH_INTERFACE + "hud_asteroid.png");
	   AEResourceManager.setImage(93, PATH_INTERFACE + "hud_crate_void.png");
	   AEResourceManager.setImage(94, PATH_INTERFACE + "hud_vortex.png");
	   AEResourceManager.setImage(95, PATH_INTERFACE + "menu_map_jumpgate.png");
	   AEResourceManager.setImage(96, PATH_INTERFACE + "hud_scanprocess_anim.png");
	   AEResourceManager.setImage(97, PATH_INTERFACE + "hud_meteor_class.png");
	   AEResourceManager.setImage(98, PATH_INTERFACE + "menu_map_sidemission.png");
	   AEResourceManager.setImage(99, PATH_INTERFACE + "menu_map_mainmission.png");
	   AEResourceManager.setImage(100, PATH_INTERFACE + "menu_map_blueprint.png");
	   AEResourceManager.setImage(101, PATH_INTERFACE + "menu_map_visited.png");
	   AEResourceManager.setImage(102, PATH_INTERFACE + "menu_map_direction.png");
	   AEResourceManager.setImage(103, PATH_INTERFACE + "map_sun_glow.png");
	   AEResourceManager.setImage(104, PATH_INTERFACE + "medals_on.png");
	   AEResourceManager.setImage(105, PATH_INTERFACE + "medals.png");
	   AEResourceManager.setImage(106, PATH_INTERFACE + "flaggen.png");
	   AEResourceManager.setImage(107, PATH_INTERFACE + "hud_hull_bar_full.png");
	   AEResourceManager.setImage(108, PATH_INTERFACE + "hud_armor_full.png");
	   AEResourceManager.setImage(109, PATH_INTERFACE + "hud_hull_bar_empty.png");
	   AEResourceManager.setImage(110, PATH_INTERFACE + "hud_status_panel.png");
	   AEResourceManager.setImage(111, PATH_INTERFACE + "hud_shield_normal_icon.png");
	   AEResourceManager.setImage(112, PATH_INTERFACE + "hud_shield_bar_empty.png");
	   AEResourceManager.setImage(113, PATH_INTERFACE + "hud_shield_bar_full.png");
	   AEResourceManager.setImage(114, PATH_INTERFACE + "hud_ship_normal_icon.png");
	   AEResourceManager.setImage(115, PATH_INTERFACE + "panel_info_lower.png");
	   AEResourceManager.setImage(116, PATH_INTERFACE + "quickmenu_crosshair_anim.png");
	   AEResourceManager.setImage(117, PATH_INTERFACE + "quickmenu_icons.png");
	   AEResourceManager.setImage(118, PATH_INTERFACE + "hud_crosshair.png");
	   AEResourceManager.setImage(119, PATH_INTERFACE + "lock.png");
	   AEResourceManager.setImage(120, PATH_INTERFACE + "mining_background.png");
	   AEResourceManager.setImage(121, PATH_INTERFACE + "mining_cursor.png");
	   AEResourceManager.setImage(122, PATH_INTERFACE + "mining_green_complete.png");
	   AEResourceManager.setImage(123, PATH_INTERFACE + "mining_green_empty.png");
	   AEResourceManager.setImage(124, PATH_INTERFACE + "mining_red_area.png");
	   AEResourceManager.setImage(125, PATH_INTERFACE + "skip.png");
	   AEResourceManager.setImage(126, PATH_INTERFACE + "menu_background.png");
	   AEResourceManager.setImage(127, PATH_INTERFACE + "menu_main_corner.png");
	   AEResourceManager.setImage(128, PATH_INTERFACE + "menu_main_panel_upper.png");
	   AEResourceManager.setImage(129, PATH_INTERFACE + "menu_main_panel_lower.png");
	   AEResourceManager.setImage(130, PATH_INTERFACE + "menu_panel_corner_left.png");
	   AEResourceManager.setImage(131, PATH_INTERFACE + "menu_lower_panel_solid.png");
	   AEResourceManager.setImage(132, PATH_INTERFACE + "fishlabs_logo.png");
	   AEResourceManager.setImage(133, PATH_INTERFACE + "abyss_logo.png");
	   AEResourceManager.setImage(134, PATH_INTERFACE + "gof2_logo.png");
	   AEResourceManager.setImage(135, PATH_INTERFACE + "item_types.png");
	   AEResourceManager.setImage(136, PATH_INTERFACE + "ships_color.png");
	   AEResourceManager.setImage(137, PATH_INTERFACE + "item_types_sel.png");
	   AEResourceManager.setImage(138, PATH_INTERFACE + "cargo_panel.png");
	   AEResourceManager.setImage(139, PATH_INTERFACE + "hud_bar_yellow_empty.png");
	   AEResourceManager.setImage(140, PATH_INTERFACE + "hud_bar_yellow_full.png");
	   AEResourceManager.setImage(141, PATH_INTERFACE + "hud_bar_red_empty.png");
	   AEResourceManager.setImage(142, PATH_INTERFACE + "hud_bar_red_full.png");
	   AEResourceManager.setImage(143, PATH_INTERFACE + "hud_bar_blue_empty.png");
	   AEResourceManager.setImage(144, PATH_INTERFACE + "hud_bar_white_full.png");
	   AEResourceManager.setImage(145, PATH_INTERFACE + "hud_bar_green_empty.png");
	   AEResourceManager.setImage(146, PATH_INTERFACE + "hud_bar_green_full.png");
	   AEResourceManager.setImage(147, PATH_INTERFACE + "hud_bar_blue_full.png");
	   AEResourceManager.setImage(148, PATH_INTERFACE + "hud_bar_white_empty.png");
	   AEResourceManager.setImage(149, PATH_INTERFACE + "hud_bar_orange_full.png");
	   AEResourceManager.setImage(150, PATH_INTERFACE + "menu_background_textbox.png");
	   AEResourceManager.setImage(151, PATH_INTERFACE + "menu_map_player_home.png");
	   
	   AEResourceManager.setImage(152, PATH_INTERFACE + "buttons/rect_rounded_button_normal.png");
	   AEResourceManager.setImage(153, PATH_INTERFACE + "buttons/rect_rounded_button_pressed.png");
	   AEResourceManager.setImage(154, PATH_INTERFACE + "buttons/rect_rounded_button_inactive.png");
	   
	   AEResourceManager.setImage(155, PATH_INTERFACE + "buttons/rect_rounded_small_button_normal.png");
	   AEResourceManager.setImage(156, PATH_INTERFACE + "buttons/rect_rounded_small_button_pressed.png");
	   
	   AEResourceManager.setImage(157, PATH_INTERFACE + "buttons/rect_wide_button_normal.png");
	   AEResourceManager.setImage(158, PATH_INTERFACE + "buttons/rect_wide_button_pressed.png");
	   
	   AEResourceManager.setImage(159, PATH_INTERFACE + "buttons/depart_button_normal.png");
	   AEResourceManager.setImage(160, PATH_INTERFACE + "buttons/depart_button_pressed.png");
	   
	   AEResourceManager.setImage(161, PATH_INTERFACE + "buttons/joystick_background.png");
	   AEResourceManager.setImage(162, PATH_INTERFACE + "buttons/joystick_normal.png");
	   AEResourceManager.setImage(163, PATH_INTERFACE + "buttons/joystick_pressed.png");
	   
	   AEResourceManager.setImage(164, PATH_INTERFACE + "buttons/autopilot_normal.png");
	   AEResourceManager.setImage(165, PATH_INTERFACE + "buttons/autopilot_pressed.png");
	   
	   AEResourceManager.setImage(166, PATH_INTERFACE + "buttons/booster_normal.png");
	   AEResourceManager.setImage(167, PATH_INTERFACE + "buttons/booster_pressed.png");
	   AEResourceManager.setImage(168, PATH_INTERFACE + "buttons/booster_inactive.png");
	   
	   AEResourceManager.setImage(169, PATH_INTERFACE + "buttons/right_panel_background.png");
	   
	   AEResourceManager.setImage(170, PATH_INTERFACE + "buttons/fire_button_normal.png");
	   AEResourceManager.setImage(171, PATH_INTERFACE + "buttons/fire_button_pressed.png");
	   
	   AEResourceManager.setImage(172, PATH_INTERFACE + "buttons/rocket_button_normal.png");
	   AEResourceManager.setImage(173, PATH_INTERFACE + "buttons/rocket_button_pressed.png");
	   
	   AEResourceManager.setImage(174, PATH_INTERFACE + "buttons/camera_button_normal.png");
	   AEResourceManager.setImage(175, PATH_INTERFACE + "buttons/camera_button_pressed.png");
	   
	   AEResourceManager.setImage(176, PATH_INTERFACE + "buttons/quickmenu_button_normal.png");
	   AEResourceManager.setImage(177, PATH_INTERFACE + "buttons/quickmenu_button_pressed.png");
	   
	   AEResourceManager.setImage(178, PATH_INTERFACE + "buttons/pause_button_normal.png");
	   AEResourceManager.setImage(179, PATH_INTERFACE + "buttons/pause_button_pressed.png");
	   
	   AEResourceManager.setImage(180, PATH_INTERFACE + "buttons/triangle_button_normal.png");
	   AEResourceManager.setImage(181, PATH_INTERFACE + "buttons/triangle_button_pressed.png");
	   
	   AEResourceManager.setImage(182, PATH_INTERFACE + "buttons/beveled_button_normal.png");
	   AEResourceManager.setImage(183, PATH_INTERFACE + "buttons/beveled_button_pressed.png");
	   
	   AEResourceManager.setImage(184, PATH_INTERFACE + "buttons/starmap_button_normal.png");
	   AEResourceManager.setImage(185, PATH_INTERFACE + "buttons/starmap_button_pressed.png");
	   
	   itemsImage = AEResourceManager.getImage(3);
	   
	   rectRoundedButtonNormal = AEResourceManager.getImage(152);
	   rectRoundedButtonPressed = AEResourceManager.getImage(153);
	   rectRoundedButtonInactive = AEResourceManager.getImage(154);
	   
	   rectRoundedSmallButtonNormal = AEResourceManager.getImage(155);
	   rectRoundedSmallButtonPressed = AEResourceManager.getImage(156);
	   
	   Image rectWideButton = AEResourceManager.getImage(157);
	   int rectWideButtonHeight = rectWideButton.getHeight();
	   
	   rectWideButtonNormal = AEFile.resize_image(AEResourceManager.getImage(157), GlobalStatus.var_e75, rectWideButtonHeight);
	   rectWideButtonPressed = AEFile.resize_image(AEResourceManager.getImage(158), GlobalStatus.var_e75, rectWideButtonHeight);
	   
	   departButtonNormal = AEResourceManager.getImage(159);
	   departButtonPressed = AEResourceManager.getImage(160);
	   
	   joystickBackground = AEResourceManager.getImage(161);
	   joystickNormal = AEResourceManager.getImage(162);
	   joystickPressed = AEResourceManager.getImage(163);
	   
	   autopilotNormal = AEResourceManager.getImage(164);
	   autopilotPressed = AEResourceManager.getImage(165);
	   
	   boosterNormal = AEResourceManager.getImage(166);
	   boosterPressed = AEResourceManager.getImage(167);
	   boosterInactive = AEResourceManager.getImage(168);
	   
	   rightPanelBackgroundImage = AEResourceManager.getImage(169);
	   
	   fireButtonNormal = AEResourceManager.getImage(170);
	   fireButtonPressed = AEResourceManager.getImage(171);
	   
	   rocketButtonNormal = AEResourceManager.getImage(172);
	   rocketButtonPressed = AEResourceManager.getImage(173);
	   
	   cameraButtonNormal = AEResourceManager.getImage(174);
	   cameraButtonPressed = AEResourceManager.getImage(175);
	   
	   quickmenuButtonNormal = AEResourceManager.getImage(176);
	   quickmenuButtonPressed = AEResourceManager.getImage(177);
	   
	   pauseButtonNormal = AEResourceManager.getImage(178);
	   pauseButtonPressed = AEResourceManager.getImage(179);
	   
	   triangleButtonNormal = AEResourceManager.getImage(180);
	   trianlgeButtonPressed = AEResourceManager.getImage(181);
	   
	   beveledButtonNormal = AEResourceManager.getImage(182);
	   beveledButtonPressed = AEResourceManager.getImage(183);
	   
	   for(int ships = 0; ships < GlobalStatus.max_ships; ++ships) {
		   AEResourceManager.setImage(9000 + ships, PATH_INTERFACE + "ships/ship_" + ships + ".png");
	   }
	   
	   for(int lens = 0; lens < 4; ++lens) {
		   AEResourceManager.setImage(9200 + lens, PATH_INTERFACE + "lens" + lens + ".png");
	   }
	   
	   for(int suns = 0; suns < 12; ++suns) {
		   AEResourceManager.setImage(9400 + suns, PATH_TEXTURES + "sun/sun_" + suns + ".png");
	   }
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
	   AEResourceManager.addTextureResource(tex_station_player_add, PATH_TEXTURES + "tex_player_station_add");
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
	   AEResourceManager.addTextureResource(tex_col_test, "/Resource/col_test");
	   
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
	   AEResourceManager.addTextureResource(tex_beams, "/Resource/textures/beams");
	   AEResourceManager.addTextureResource(tex_station_terran, "/Resource/textures/tex_station_terran");
	   
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
		AEResourceManager.addGeometryResource(2510, PATH_MESHES + "col_box_add.aem", 500000, tex_col_test);
		AEResourceManager.addGeometryResource(2511, PATH_MESHES + "col_sphere_add.aem", 15000, tex_col_test);
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
		AEResourceManager.addGeometryResource(15, PATH_MESHES + "jumpgate_terran.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(16, PATH_MESHES + "emp.m3g", 2000, 0);
		AEResourceManager.addGeometryResource(17, PATH_MESHES + "box.m3g", 2000, 0);
		AEResourceManager.addGeometryResource(18, PATH_MESHES + "nuke.m3g", 2000, 0);
		
		
		
		AEResourceManager.addGeometryResource(3500, PATH_MESHES + "station/midorian/stat_arm0.aem", 15000, tex_station_midorian);
		AEResourceManager.addGeometryResource(3510, PATH_MESHES + "station/midorian/stat_arm0_nl.aem", 15000, tex_station_midorian);
		AEResourceManager.addGeometryResource(3520, PATH_MESHES + "station/midorian/stat_arm0_nl_add.aem", 15000, tex_station_midorian);
		
		AEResourceManager.addGeometryResource(3501, PATH_MESHES + "station/midorian/stat_hangar2.aem", 15000, tex_station_midorian);
		AEResourceManager.addGeometryResource(3511, PATH_MESHES + "station/midorian/stat_hangar2_nl.aem", 15000, tex_station_midorian);
		AEResourceManager.addGeometryResource(3521, PATH_MESHES + "station/midorian/stat_hangar2_nl_add.aem", 15000, tex_station_midorian);
		
		AEResourceManager.addGeometryResource(3502, PATH_MESHES + "station/midorian/stat_bottom0.aem", 15000, tex_station_midorian);
		AEResourceManager.addGeometryResource(3512, PATH_MESHES + "station/midorian/stat_bottom0_nl.aem", 15000, tex_station_midorian);
		AEResourceManager.addGeometryResource(3522, PATH_MESHES + "station/midorian/stat_bottom0_nl_add.aem", 15000, tex_station_midorian);
		
		AEResourceManager.addGeometryResource(3503, PATH_MESHES + "station/midorian/stat_top10.aem", 15000, tex_station_midorian);
		AEResourceManager.addGeometryResource(3513, PATH_MESHES + "station/midorian/stat_top10_nl.aem", 15000, tex_station_midorian);
		AEResourceManager.addGeometryResource(3523, PATH_MESHES + "station/midorian/stat_top10_nl_add.aem", 15000, tex_station_midorian);
		
		AEResourceManager.addGeometryResource(3504, PATH_MESHES + "station/midorian/stat_hangar4.aem", 15000, tex_station_midorian);
		
		AEResourceManager.addGeometryResource(3505, PATH_MESHES + "station/midorian/stat_top7.aem", 15000, tex_station_midorian);
		AEResourceManager.addGeometryResource(3515, PATH_MESHES + "station/midorian/stat_top7_nl.aem", 15000, tex_station_midorian);
		AEResourceManager.addGeometryResource(3525, PATH_MESHES + "station/midorian/stat_top7_nl_add.aem", 15000, tex_station_midorian);
		
		AEResourceManager.addGeometryResource(3506, PATH_MESHES + "station/midorian/stat_bottom7.aem", 15000, tex_station_midorian);
		AEResourceManager.addGeometryResource(3516, PATH_MESHES + "station/midorian/stat_bottom7_nl.aem", 15000, tex_station_midorian);
		AEResourceManager.addGeometryResource(3526, PATH_MESHES + "station/midorian/stat_bottom7_nl_add.aem", 15000, tex_station_midorian);
		
		AEResourceManager.addGeometryResource(3507, PATH_MESHES + "station/midorian/stat_hangar0.aem", 15000, tex_station_midorian);
		AEResourceManager.addGeometryResource(3517, PATH_MESHES + "station/midorian/stat_hangar0_nl.aem", 15000, tex_station_midorian);
		AEResourceManager.addGeometryResource(3527, PATH_MESHES + "station/midorian/stat_hangar0_nl_add.aem", 15000, tex_station_midorian);
		
		AEResourceManager.addGeometryResource(3508, PATH_MESHES + "station/midorian/stat_bottom2.aem", 15000, tex_station_midorian);
		AEResourceManager.addGeometryResource(3518, PATH_MESHES + "station/midorian/stat_bottom2_nl.aem", 15000, tex_station_midorian);
		AEResourceManager.addGeometryResource(3528, PATH_MESHES + "station/midorian/stat_bottom2_nl_add.aem", 15000, tex_station_midorian);
		
		
		
		
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
		AEResourceManager.addGeometryResource(3336, PATH_MESHES + "null.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3337, PATH_MESHES + "void_station.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3338, PATH_MESHES + "stat_vossk_arm1.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3339, PATH_MESHES + "stat_vossk_bottom2.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3340, PATH_MESHES + "stat_vossk_bottom1.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3341, PATH_MESHES + "stat_vossk_middle1.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3342, PATH_MESHES + "stat_vossk_middle2.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3343, PATH_MESHES + "stat_vossk_top1.m3g", 15000, 0);
		AEResourceManager.addGeometryResource(3344, dlc_valkyrie + "battlestation.aem", 15000, tex_valkyrie_station);
		AEResourceManager.addGeometryResource(3345, dlc_valkyrie + "battlestation_add.aem", 15000, tex_fx);
		AEResourceManager.addGeometryResource(3346, dlc_valkyrie + "battlestation_nl.aem", 15000, tex_valkyrie_station);
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
		
		AEResourceManager.addGeometryResource(13064, PATH_MESHES + "boost_red.m3g", 5000, 1);
		AEResourceManager.addGeometryResource(13065, PATH_MESHES + "boost_violet.m3g", 5000, 1);
		AEResourceManager.addGeometryResource(13067, PATH_MESHES + "boost_cyan_add.aem", 5000, tex_gof1);
		AEResourceManager.addGeometryResource(13068, PATH_MESHES + "boost_green.m3g", 5000, 1);
		AEResourceManager.addGeometryResource(13070, PATH_MESHES + "boost_orange.m3g", 5000, 1);
		AEResourceManager.addGeometryResource(13071, PATH_MESHES + "boost_yellow_add.aem", 5000, tex_gof1);
		AEResourceManager.addGeometryResource(14072, PATH_MESHES + "boost_blue.m3g", 5000, 1);
		
		AEResourceManager.addGeometryResource(13072, PATH_MESHES + "ship_vt_00.aem", 15000, 0);
		AEResourceManager.addGeometryResource(13073, PATH_MESHES + "ship_vt_01.aem", 15000, 0);
		AEResourceManager.addGeometryResource(13074, PATH_MESHES + "ship_vt_02.aem", 15000, 0);
		AEResourceManager.addGeometryResource(13075, PATH_MESHES + "ship_vt_03.aem", 15000, 0);
		AEResourceManager.addGeometryResource(13076, PATH_MESHES + "ship_vt_04.aem", 15000, 0);
		AEResourceManager.addGeometryResource(13077, PATH_MESHES + "ship_vt_05.aem", 15000, 0);
		
		AEResourceManager.addGeometryResource(13078, PATH_MESHES + "ship_tb_00.aem", 15000, 0);
		AEResourceManager.addGeometryResource(13079, PATH_MESHES + "ship_tb_01.aem", 15000, 0);
		AEResourceManager.addGeometryResource(13080, PATH_MESHES + "ship_tb_02.aem", 15000, 0);
		AEResourceManager.addGeometryResource(13081, PATH_MESHES + "ship_tb_03.aem", 15000, 0);
		AEResourceManager.addGeometryResource(13082, PATH_MESHES + "ship_tb_04.aem", 15000, 0);
		AEResourceManager.addGeometryResource(13083, PATH_MESHES + "ship_tb_05.aem", 15000, 0);
		AEResourceManager.addGeometryResource(13084, PATH_MESHES + "ship_tb_06.aem", 15000, 0);
		AEResourceManager.addGeometryResource(13085, PATH_MESHES + "ship_tb_07.aem", 15000, 0);
		AEResourceManager.addGeometryResource(13086, PATH_MESHES + "ship_tb_08.aem", 15000, 0);
		AEResourceManager.addGeometryResource(13087, PATH_MESHES + "ship_tb_09.aem", 15000, 0);
		AEResourceManager.addGeometryResource(13088, PATH_MESHES + "ship_tb_10.aem", 15000, 0);
		AEResourceManager.addGeometryResource(13089, PATH_MESHES + "ship_tb_11.aem", 15000, 0);
		AEResourceManager.addGeometryResource(13090, PATH_MESHES + "ship_tb_12.aem", 15000, 0);
		AEResourceManager.addGeometryResource(13091, PATH_MESHES + "ship_tb_13.aem", 15000, 0);
		AEResourceManager.addGeometryResource(13092, PATH_MESHES + "ship_tb_14.aem", 15000, 0);
		AEResourceManager.addGeometryResource(13093, PATH_MESHES + "ship_tb_15.aem", 15000, 0);
		AEResourceManager.addGeometryResource(13094, PATH_MESHES + "ship_tb_16.aem", 15000, 0);
		
		AEResourceManager.addGeometryResource(13095, PATH_MESHES + "ship_tt_00.aem", 15000, 0);
		AEResourceManager.addGeometryResource(13096, PATH_MESHES + "ship_tt_01.aem", 15000, 0);
		AEResourceManager.addGeometryResource(13097, PATH_MESHES + "ship_tt_02.aem", 15000, 0);
		AEResourceManager.addGeometryResource(13098, PATH_MESHES + "ship_tt_03.aem", 15000, 0);
		
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
		
		AEResourceManager.addGeometryResource(6770, PATH_MESHES + "turrets/turret_47.aem", 2000, tex_turrets);
		AEResourceManager.addGeometryResource(6771, PATH_MESHES + "turrets/turret_47_gun.aem", 2000, tex_turrets);
		AEResourceManager.addGeometryResource(6772, PATH_MESHES + "turrets/turret_48.aem", 2000, tex_turrets);
		AEResourceManager.addGeometryResource(6773, PATH_MESHES + "turrets/turret_48_gun.aem", 2000, tex_turrets);
		AEResourceManager.addGeometryResource(6774, PATH_MESHES + "turrets/turret_49.aem", 2000, tex_turrets);
		AEResourceManager.addGeometryResource(6775, PATH_MESHES + "turrets/turret_49_gun.aem", 2000, tex_turrets);
		
		AEResourceManager.addGeometryResource(5001, PATH_MESHES + "turrets/autoturret_01.aem", 2000, tex_turrets);
		AEResourceManager.addGeometryResource(5002, PATH_MESHES + "turrets/autoturret_01_gun.aem", 2000, tex_turrets);
		AEResourceManager.addGeometryResource(5003, PATH_MESHES + "turrets/autoturret_02.aem", 2000, tex_turrets);
		AEResourceManager.addGeometryResource(5004, PATH_MESHES + "turrets/autoturret_02_gun.aem", 2000, tex_turrets);
		AEResourceManager.addGeometryResource(5005, PATH_MESHES + "turrets/autoturret_03.aem", 2000, tex_turrets);
		AEResourceManager.addGeometryResource(5006, PATH_MESHES + "turrets/autoturret_03_gun.aem", 2000, tex_turrets);
		
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
		
		int planetBoundingSphere = 15000;
		AEResourceManager.addGeometryResource(3200, PATH_MESHES + "plane_alpha.aem", planetBoundingSphere, tex_planet_0);
		AEResourceManager.addGeometryResource(3201, PATH_MESHES + "plane_alpha.aem", planetBoundingSphere, tex_planet_1);
		AEResourceManager.addGeometryResource(3202, PATH_MESHES + "plane_alpha.aem", planetBoundingSphere, tex_planet_2);
		AEResourceManager.addGeometryResource(3203, PATH_MESHES + "plane_alpha.aem", planetBoundingSphere, tex_planet_3);
		AEResourceManager.addGeometryResource(3204, PATH_MESHES + "plane_alpha.aem", planetBoundingSphere, tex_planet_4);
		AEResourceManager.addGeometryResource(3205, PATH_MESHES + "plane_alpha.aem", planetBoundingSphere, tex_planet_5);
		AEResourceManager.addGeometryResource(3206, PATH_MESHES + "plane_alpha.aem", planetBoundingSphere, tex_planet_6);
		AEResourceManager.addGeometryResource(3207, PATH_MESHES + "plane_alpha.aem", planetBoundingSphere, tex_planet_7);
		AEResourceManager.addGeometryResource(3208, PATH_MESHES + "plane_alpha.aem", planetBoundingSphere, tex_planet_8);
		AEResourceManager.addGeometryResource(3209, PATH_MESHES + "plane_alpha.aem", planetBoundingSphere, tex_planet_9);
		AEResourceManager.addGeometryResource(3210, PATH_MESHES + "plane_alpha.aem", planetBoundingSphere, tex_planet_10);
		AEResourceManager.addGeometryResource(3211, PATH_MESHES + "plane_alpha.aem", planetBoundingSphere, tex_planet_11);
		AEResourceManager.addGeometryResource(3212, PATH_MESHES + "plane_alpha.aem", planetBoundingSphere, tex_planet_12);
		AEResourceManager.addGeometryResource(3213, PATH_MESHES + "plane_alpha.aem", planetBoundingSphere, tex_planet_13);
		AEResourceManager.addGeometryResource(3214, PATH_MESHES + "plane_alpha.aem", planetBoundingSphere, tex_planet_14);
		AEResourceManager.addGeometryResource(3215, PATH_MESHES + "plane_alpha.aem", planetBoundingSphere, tex_planet_15);
		AEResourceManager.addGeometryResource(3216, PATH_MESHES + "plane_alpha.aem", planetBoundingSphere, tex_planet_16);
		AEResourceManager.addGeometryResource(3217, PATH_MESHES + "plane_alpha.aem", planetBoundingSphere, tex_planet_17);
		AEResourceManager.addGeometryResource(3218, PATH_MESHES + "plane_alpha.aem", planetBoundingSphere, tex_planet_18);
		AEResourceManager.addGeometryResource(3219, PATH_MESHES + "plane_alpha.aem", planetBoundingSphere, tex_planet_19);
		AEResourceManager.addGeometryResource(3220, PATH_MESHES + "plane_alpha.aem", planetBoundingSphere, tex_planet_20);
		AEResourceManager.addGeometryResource(3221, PATH_MESHES + "plane_alpha.aem", planetBoundingSphere, tex_planet_21);
		AEResourceManager.addGeometryResource(3222, PATH_MESHES + "plane_alpha.aem", planetBoundingSphere, tex_planet_22);
		AEResourceManager.addGeometryResource(3223, PATH_MESHES + "plane_alpha.aem", planetBoundingSphere, tex_planet_23);
		AEResourceManager.addGeometryResource(3224, PATH_MESHES + "plane_alpha.aem", planetBoundingSphere, tex_planet_24);
		AEResourceManager.addGeometryResource(3225, PATH_MESHES + "plane_alpha.aem", planetBoundingSphere, tex_planet_25);
		AEResourceManager.addGeometryResource(3226, PATH_MESHES + "plane_alpha.aem", planetBoundingSphere, tex_planet_26);
		AEResourceManager.addGeometryResource(3227, PATH_MESHES + "plane_alpha.aem", planetBoundingSphere, tex_planet_27);
		AEResourceManager.addGeometryResource(3228, PATH_MESHES + "plane_alpha.aem", planetBoundingSphere, tex_planet_28);
		AEResourceManager.addGeometryResource(3229, PATH_MESHES + "plane_alpha.aem", planetBoundingSphere, tex_planet_29);
		
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
