package AE;

import Main.GOF2Canvas;
import java.util.Random;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Graphics;
import javax.microedition.midlet.MIDlet;
import javax.microedition.lcdui.Image;

import AE.PaintCanvas.IGraphics3D;
import GoF2.ApplicationManager;
import GoF2.GameText;
import GoF2.LoadingScreen;
import HardEngine.Class_1017;
import HardEngine.Class_2b0;

public final class GlobalStatus {
   
   public static Image[] shipsImage;
   public static boolean musicOn = true;
   public static boolean sfxOn = true;
   public static boolean vibrationOn = true;
   public static boolean invertedControlsOn = true;
   public static int musicVolume = 2;
   public static int sfxVolume = 2;
   public static int default_language = 1;
   public static int language = default_language;
   public static boolean shopHelpShown;
   public static boolean shipHelpShown;
   public static boolean actionsHelpShown;
   public static boolean bluePrintsHelpShown;
   public static boolean bluePrintInfoHelpShown;
   public static boolean barHelpShown;
   public static boolean galaxyMapHelpShown;
   public static boolean systemMapHelpShown;
   public static boolean somehelpShown_unused_;
   public static boolean miningHelpShown;
   public static boolean asteroidHelpShown;
   public static boolean missionsHelpShown;
   public static boolean cargoFullHelpShown;
   public static boolean wingmenHelpShown;
   public static boolean actionMenuHelpShown;
   public static boolean boosterHelpShown;
   public static boolean statusHelpShown;
   public static boolean medalsHelpShown;
   public static boolean fanWingmenNoticeShown;
   public static boolean voidxNoticeShown;
   public static boolean reputationHelpShown;
   public static boolean buyingHelpShown;
   public static boolean itemMountingHelpShown;
   public static boolean itemMounting2HelpShown;
   public static boolean interplanetHelpShown;
   public static boolean jumpDriveHelpShown;
   public static boolean cloakHelpShown;
   public static Renderer renderer;
   public static IGraphics3D graphics3D;
   public static Display display;
   public static LoadingScreen var_bfc;
   public static int displayedSecondary = -1;
   public static boolean soundDeviceUnavailable = false;
   public static int currentMusic = -1;
   public static GameText gameText;
   public static Graphics graphics;
   public static GOF2Canvas var_dda;
   public static MIDlet midlet;
   public static int var_e75;
   public static int var_eb6;
   public static SoundManager soundManager;
   public static Random random;
   public static String gameVersion = "";
   public static boolean paused = false;
   public static ApplicationManager applicationManager;
   public static IApplicationModule[] scenes;
   public static boolean var_102a = false;
   public static boolean var_1083 = false;
   public static boolean var_10e5 = false;
   public static Class_1017 var_1119;
   public static Class_2b0 var_1132;
   public static int default_planet_size = 350;
   public static int planet_size[] = {64, 128, 256, 350};
   public static int default_nebula_size = 300;
   public static int nebula_size[] = {64, 128, 256, 300};
   public static int default_texture_type = 256;
   public static int texture_type[] = {256, 512, 1024, 2048};
   public static int planet_small_size = 64;
   public static boolean joy_pressed = false;
   public static boolean up_pressed = false;
   public static boolean down_pressed = false;
   public static boolean left_pressed = false;
   public static boolean right_pressed = false;
   public static boolean ok_pressed = false;
   public static boolean rocket_pressed = false;
   public static boolean autofire_pressed = false;
   public static boolean boost_pressed = false;
   public static boolean quickmenu_pressed = false;
   public static boolean camera_pressed = false;
   public static boolean autopilot_pressed = false;
   public static boolean ship_stop_pressed = false;
   public static boolean pause_pressed = false;
   public static boolean left_key_active = false;
   public static boolean right_key_active = false;
   public static boolean rect_button_pressed = false;
   public static int lighting;
   public static boolean HDR = false;
   public static boolean screen_keyboard = true;
   public static int default_preview_size_x = 320;
   public static int default_preview_size_y = 191;
   public static int preview_size_x = default_preview_size_x;
   public static int preview_size_y = default_preview_size_y;
   public static int width;
   public static int height;
   public static int cur_x = 67;
   public static int cur_y = height - 64;
   public static int textures = 0;
   public static int nebulas = 0;
   public static int planets = 0;
   public static String[] texture_size = {"Low", "Medium", "High", "Ultra"};
   public static boolean bigInterface = false;
   public static int asteroid = 1;
   public static int asteroids[] = {0, 10, 15, 50, 70, 100};
   public static String[] asteroid_setting = {"0%", "5%", "25%", "50%", "75%", "100%"};
   public static int stop_ship = 0;
   public static boolean FXAA = false;
   public static boolean low_details = false;
   public static boolean galaxymap_texture = false; // low\high
   public static int start_ship = 10;
   public static int newgame_ship = 0;
   public static boolean cheat_mode = false;
   public static int thynome_cash = 100000;
   public static int MONEY_LEBOVSKI = 0;
   public static int max_items = 176;
   public static int all_items[] = new int[max_items];
   public static int max_stations = 100;
   public static int max_systems = 22;
   public static int max_agents = 16;
   public static int skybox_model = 9991;
   public static int max_settings = 16;
   public static int test_1 = 0;
   public static boolean developer_mode = false;
   public static float default_fov = 0.085F; // 0.085F def
   public static float max_fov = 0.110F;
   public static float min_fov = 0.050F;
   public static float current_fov = default_fov;
   public static int max_ships = 37;
   
   public static int language_flag_x = 0;
   public static int language_flag_y = 0;
   
   public static int difficult = 0;
   public static int shields = 0;
   public static int kaamo = 0;
   
   public static String CATCHED_ERROR = ""; // BLYAT
   
   public static int INTERFACE_SCALE_MULTIPLIER = 1;
   public static final int INTERFACE_IMAGE_1 = 54;
   public static final int INTERFACE_IMAGE_SHIP = 1;
   public static final int INTERFACE_IMAGE_ITEMS = 3;
   public static final boolean STATION_COLLISION_BOX_VISIBLE = true;
   
   public static void vibrate(int var0) {
      if(vibrationOn) {
       display.vibrate(var0);
      }
   }

   public static void resetHints() {
      shopHelpShown = false;
      shipHelpShown = false;
      actionsHelpShown = false;
      bluePrintsHelpShown = false;
      bluePrintInfoHelpShown = false;
      barHelpShown = false;
      galaxyMapHelpShown = false;
      systemMapHelpShown = false;
      somehelpShown_unused_ = false;
      miningHelpShown = false;
      asteroidHelpShown = false;
      missionsHelpShown = false;
      cargoFullHelpShown = false;
      wingmenHelpShown = false;
      actionMenuHelpShown = false;
      boosterHelpShown = false;
      statusHelpShown = false;
      medalsHelpShown = false;
      fanWingmenNoticeShown = false;
      voidxNoticeShown = false;
      reputationHelpShown = false;
      buyingHelpShown = false;
      itemMountingHelpShown = false;
      itemMounting2HelpShown = false;
      interplanetHelpShown = false;
      jumpDriveHelpShown = false;
      cloakHelpShown = false;
   }

    public static void checkDeviceControlSupport() {
      try {
         Class.forName("com.nokia.mid.ui.DeviceControl");
      } catch (Throwable var0) {
         ;
      }
   }

}
