/**
** @class Fonts
** �������� �������, ������ � ������ ���������.
**/

package Main;

import java.util.Random;

import AE.AEResourceManager;
import AE.GlobalStatus;
import AE.IApplicationModule;
import AE.PaintCanvas.AEGraphics3D;
import AE.PaintCanvas.Font;
import AE.Renderer;
import AE.SoundManager;
import GoF2.ApplicationManager;
import GoF2.GameText;
import GoF2.Globals;
import GoF2.Layout;
import GoF2.LoadingScreen;
import GoF2.RecordHandler;
import GoF2.Status;
import HardEngine.Class_1017;
import HardEngine.Class_3e;

public final class FONT_LOADER extends Class_3e {

   private GF2 gf2;
   private int var_98; // button
   private int var_d2; // button
   private int var_133;
   private int var_165;
   private int var_17d;
   private int var_1b8;
   private ApplicationManager var_1df = null;
   private LoadingScreen var_1fc;
   private boolean var_25b;
   private boolean var_274;
   private boolean var_2c1;
   private long var_2e9;
   private long var_32a;


   FONT_LOADER(GF2 var1) {
      super(false);
       GlobalStatus.checkDeviceControlSupport();
      this.var_274 = true;
      this.var_2c1 = false;
      this.gf2 = var1;
      this.var_25b = false;
      this.var_2e9 = 0L;
      this.var_32a = 0L;
      GlobalStatus.display = var1.sub_38();
      GlobalStatus.random = new Random();

      try {
         GlobalStatus.gameVersion = "v " + var1.getAppProperty("MIDlet-Version");
      } catch (Exception var2) {
         var2.printStackTrace();
      }
   }

   public final void sub_2f() {
      if(this.var_1df.GetCurrentApplicationModule() == GlobalStatus.scenes[2]) {
         ((Main.MGame)((Main.MGame)GlobalStatus.scenes[2])).pause();
      }

      GlobalStatus.soundManager.stop();
      GlobalStatus.paused = true;
   }

   protected final void hideNotify() {
      if(!GlobalStatus.var_102a) {
         this.gf2.sub_114();
      }

   }

   final synchronized void sub_4d() {
      if(this.var_274) {
         this.var_274 = false;
         GlobalStatus.var_e75 = this.getWidth();
         GlobalStatus.var_eb6 = this.getHeight();
         this.setFullScreenMode(true);
         this.flushGraphics();
         GlobalStatus.var_e75 = this.getWidth();
         GlobalStatus.var_eb6 = this.getHeight();
         this.setFullScreenMode(true);
      } else if(!this.var_2c1) {
		 Globals.loadImages();
         this.var_2c1 = true;
         GlobalStatus.graphics = this.getGraphics();
         GlobalStatus.var_e75 = this.getWidth();
         GlobalStatus.var_eb6 = this.getHeight();
         (GlobalStatus.gameText = new GameText()).setLanguage(GlobalStatus.language);
         (GlobalStatus.var_1119 = new Class_1017()).sub_f(GlobalStatus.language);
         GlobalStatus.var_e75 = this.getWidth();
         GlobalStatus.var_eb6 = this.getHeight();
         this.setFullScreenMode(true);
		 /** ����������� ������ **/
         Font.setGraphics(GlobalStatus.graphics);
         Font.addCharMap("/Resource/Fonts/font_w.png", 0, 15, 16);
         Font.setPrimarySymMapSpacing(0);
         Font.setMainFontSpacingY(15);
         Font.setSymMapSetOffsetY(-2, 0);
         Font.addCharMap("/Resource/Fonts/font_g.png", 1, 15, 16);
         Font.setSymMapSpacing(0, 1);
         Font.setSpacingY(15, 1);
         Font.setSymMapSetOffsetY(-2, 1);
         Font.addCharMap("/Resource/Fonts/font_r.png", 2, 15, 16);
         Font.setSymMapSpacing(0, 2);
         Font.setSpacingY(15, 2);
         Font.setSymMapSetOffsetY(-2, 2);
         Font.addCharMap("/Resource/Fonts/font_void.png", 2, 15, 16);
         Font.setSymMapSpacing(0, 3);
         Font.setSpacingY(15, 3);
         Font.setSymMapSetOffsetY(-2, 3);
		 /** ����������� ������. �����. **/
		 Font.addCharMap("/Resource/Fonts/font_red.png", 2, 15, 16);
         Font.setSymMapSpacing(0, 4);
         Font.setSpacingY(15, 4);
         Font.setSymMapSetOffsetY(-2, 4);
		 Font.addCharMap("/Resource/Fonts/font_cyanShadow.png", 2, 15, 16);
         Font.setSymMapSpacing(0, 5);
         Font.setSpacingY(15, 5);
         Font.setSymMapSetOffsetY(-2, 5);
		 Font.addCharMap("/Resource/Fonts/font_green.png", 2, 15, 16);
         Font.setSymMapSpacing(0, 6);
         Font.setSpacingY(15, 6);
         Font.setSymMapSetOffsetY(-2, 6);
		 Font.addCharMap("/Resource/Fonts/font_violet.png", 2, 15, 16);
         Font.setSymMapSpacing(0, 7);
         Font.setSpacingY(15, 7);
         Font.setSymMapSetOffsetY(-2, 7);
		 Font.addCharMap("/Resource/Fonts/font_void_long.png", 2, 15, 16);
         Font.setSymMapSpacing(0, 8);
         Font.setSpacingY(15, 8);
         Font.setSymMapSetOffsetY(-2, 8);
		 /** ������ ��� �� �������� **/
		 Font.addCharMap("/Resource/Fonts/branded/font_w.png", 0, 15, 16);
         Font.setPrimarySymMapSpacing(0);
		 Font.setSpacingY(15, 9);
         Font.setSymMapSetOffsetY(-2, 9);
         Font.addCharMap("/Resource/Fonts/branded/font_g.png", 0, 15, 16);
         Font.setSymMapSpacing(0, 10);
         Font.setSpacingY(15, 10);
         Font.setSymMapSetOffsetY(-2, 10);
         Font.addCharMap("/Resource/Fonts/branded/font_r.png", 0, 15, 16);
         Font.setSymMapSpacing(0, 11);
         Font.setSpacingY(15, 11);
         Font.setSymMapSetOffsetY(-2, 11);
		 Font.addCharMap("/Resource/Fonts/branded/font_red.png", 0, 15, 16);
         Font.setSymMapSpacing(0, 12);
         Font.setSpacingY(15, 12);
         Font.setSymMapSetOffsetY(-2, 12);
		 Font.addCharMap("/Resource/Fonts/branded/font_cyanShadow.png", 0, 15, 16);
         Font.setSymMapSpacing(0, 13);
         Font.setSpacingY(15, 13);
         Font.setSymMapSetOffsetY(-2, 13);
		 Font.addCharMap("/Resource/Fonts/branded/font_green.png", 0, 15, 16);
         Font.setSymMapSpacing(0, 14);
         Font.setSpacingY(15, 14);
         Font.setSymMapSetOffsetY(-2, 14);
		 Font.addCharMap("/Resource/Fonts/branded/font_violet.png", 0, 15, 16);
         Font.setSymMapSpacing(0, 15);
         Font.setSpacingY(15, 15);
         Font.setSymMapSetOffsetY(-2, 15);
         GlobalStatus.var_dda = this;
         Layout.OnInitialize();
         this.var_1fc = new LoadingScreen();
         this.var_1fc.sub_9d(this);
         this.var_1fc.startLoading_(true);
         GlobalStatus.var_bfc = this.var_1fc;
         GlobalStatus.resetHints();
         (new RecordHandler()).readOptions();
         GlobalStatus.musicOn = false;
         GlobalStatus.graphics3D = new AEGraphics3D();
         (GlobalStatus.renderer = new Renderer(GlobalStatus.graphics3D)).addLayer();
         GlobalStatus.renderer.addLayer();
         GlobalStatus.renderer.addLayer();
         GlobalStatus.soundManager = new SoundManager();
         Globals.buildResourceList();
         Globals.OnInitialize();
         Status.setCredits(0);
         this.var_1df = new ApplicationManager(this.gf2, this.var_1fc);
         GlobalStatus.applicationManager = this.var_1df;
         (GlobalStatus.scenes = new IApplicationModule[4])[3] = new Intro();
         GlobalStatus.scenes[0] = new ModMainMenu();
         GlobalStatus.scenes[1] = new ModStation();
         GlobalStatus.scenes[2] = new Main.MGame();
         Status.startNewGame();
         GlobalStatus.var_1083 = false;
         this.var_1df.SetCurrentApplicationModule(GlobalStatus.scenes[3]);
      } else {
         Layout.navigationDelayDownTick();
         this.var_1df.renderScene(this.var_98);
         if(this.var_17d == 16384) {
            Layout.selectNavigationButton(true);
            this.var_1b8 = this.var_17d;
         } else if(this.var_17d == 8192) {
            Layout.selectNavigationButton(false);
            this.var_1b8 = this.var_17d;
         }

         if(Layout.navigationDelayPassed()) {
            this.var_1df.handleKeystate(this.var_1b8);
            this.var_1b8 = -1;
         }

         if(this.var_17d != -1 && this.var_1b8 == -1) {
            this.var_1df.handleKeystate(this.var_17d);
         }

         this.var_17d = -1;
         this.var_165 = -1;
         this.flushGraphics();
      }
   }

   final void sub_57() {
      this.var_1df.GetCurrentApplicationModule().OnRelease();
      Globals.OnRelease();
      Layout.OnRelease();
      Font.OnRelease();
      GlobalStatus.soundManager.OnRelease();
      AEResourceManager.OnRelease();
      this.var_1fc.close();
      this.var_1fc = null;
   }

   public final void sub_8d() {
      Layout.drawBG();
      Font.drawLinesAligned(Font.splitToLines(GlobalStatus.gameText.getText(81), GlobalStatus.var_e75 - 20), GlobalStatus.var_e75 >> 1, GlobalStatus.var_eb6 >> 1, 1, 24);
      if(this.var_32a == 0L) {
         this.var_32a = System.currentTimeMillis();
      }

      long var1 = System.currentTimeMillis();
      this.var_2e9 += var1 - this.var_32a;
      this.var_32a = var1;
      if(this.var_17d == 256) {
         if(Status.getPlayingTime() > this.var_2e9) {
            Status.incPlayingTime(-this.var_2e9);
         }

         this.var_2e9 = 0L;
         this.var_32a = 0L;
         this.gf2.sub_68();
         this.var_17d = 0;
         this.var_165 = 0;
         this.var_d2 = 0;
         this.var_133 = 0;
         this.var_98 = 0;
      }

      this.flushGraphics();
   }

   final synchronized void sub_d1() {
      this.var_98 = this.var_d2;
   }

   public final void sub_c3(int var1) {
      this.var_133 = this.var_d2;
      if(var1 == -7) { // quickmenu
         this.var_d2 |= 8192;
		 GlobalStatus.quickmenu_pressed = true;
		 GlobalStatus.left_key_active = true;
      }

      if(var1 == -6) { // pause
         this.var_d2 |= 16384;
		 GlobalStatus.pause_pressed = true;
		 GlobalStatus.right_key_active = true;
      }

      if(var1 == 49 || var1 == 101 || var1 == 114) { // rocket
         this.var_d2 |= '\u8000';
		 GlobalStatus.rocket_pressed = true;
      }

      if(var1 == 51 || var1 == 105 || var1 == 117) { // boost
         this.var_d2 |= 65536;
		 GlobalStatus.boost_pressed = true;
      }

      if(var1 == 48 || var1 == 32) { // turret
         this.var_d2 |= 131072;
		 GlobalStatus.camera_pressed = true;
      }

      if(var1 == -11) {
         this.var_d2 |= 262144;
      }

      if(var1 == -8) {
         this.var_d2 |= 524288;
      }

      if(var1 == -1 || var1 == 50 || var1 == 121 || var1 == 116) { // up
         this.var_d2 |= 2;
		 GlobalStatus.up_pressed = true;
      }

      if(var1 == -2 || var1 == 56 || var1 == 98 || var1 == 118) { // down
         this.var_d2 |= 64;
		 GlobalStatus.down_pressed = true;
      }

      if(var1 == -3 || var1 == 52 || var1 == 100 || var1 == 102) { // left
         this.var_d2 |= 4;
		 GlobalStatus.left_pressed = true;
      }

      if(var1 == -4 || var1 == 54 || var1 == 106 || var1 == 107) { // right
         this.var_d2 |= 32;
		 GlobalStatus.right_pressed = true;
      }

      if(var1 == -5 || var1 == 53 || var1 == 103 || var1 == 104) { // ok
         this.var_d2 |= 256;
		 GlobalStatus.ok_pressed = true;
      }

      if(var1 == 55 || var1 == 99 || var1 == 120) { // autofire
         this.var_d2 |= 512;
		 GlobalStatus.autofire_pressed = true;
      }

      if(var1 == 57 || var1 == 109 || var1 == 110) { // autopilot
         this.var_d2 |= 1024;
		 GlobalStatus.autopilot_pressed = true;
      }

      if(var1 == 42) {
         this.var_d2 |= 2048;
		// System.out.println("�������: *");
      }

      if(var1 == 35) { // stop ship
         this.var_d2 |= 4096;
		 GlobalStatus.ship_stop_pressed = true;
      }

      this.var_17d = this.var_d2 & ~this.var_133;
   }

   public final void sub_13a(int var1) {
      this.sub_c3(var1);
   }

   public final void sub_e1(int var1) {
      this.var_133 = this.var_d2;
      if(var1 == -7) {
         this.var_d2 &= -8193;
		 GlobalStatus.quickmenu_pressed = false;
		 GlobalStatus.left_key_active = false;
      }

      if(var1 == -6) {
         this.var_d2 &= -16385;
		 GlobalStatus.pause_pressed = false;
		 GlobalStatus.right_key_active = false;
      }

      if(var1 == 49 || var1 == 101 || var1 == 114) {
         this.var_d2 &= -32769;
		 GlobalStatus.rocket_pressed = false;
      }

      if(var1 == 51 || var1 == 105 || var1 == 117) {
         this.var_d2 &= -65537;
		 GlobalStatus.boost_pressed = false;
      }

      if(var1 == 48 || var1 == 32) {
         this.var_d2 &= -131073;
		 GlobalStatus.camera_pressed = false;
      }

      if(var1 == -11) {
         this.var_d2 &= -262145;
      }

      if(var1 == -8) {
         this.var_d2 &= -524289;
      }

      if(var1 == -1 || var1 == 50 || var1 == 121 || var1 == 116) {
         this.var_d2 &= -3;
		 GlobalStatus.up_pressed = false;
      }

      if(var1 == -2 || var1 == 56 || var1 == 98 || var1 == 118) {
         this.var_d2 &= -65;
		 GlobalStatus.down_pressed = false;
      }

      if(var1 == -3 || var1 == 52 || var1 == 100 || var1 == 102) {
         this.var_d2 &= -5;
		 GlobalStatus.left_pressed = false;
      }

      if(var1 == -4 || var1 == 54 || var1 == 106 || var1 == 107) {
         this.var_d2 &= -33;
		 GlobalStatus.right_pressed = false;
      }

      if(var1 == -5 || var1 == 53 || var1 == 103 || var1 == 104) {
         this.var_d2 &= -257;
		 GlobalStatus.ok_pressed = false;
      }

      if(var1 == 55 || var1 == 99 || var1 == 120) {
         this.var_d2 &= -513;
		 GlobalStatus.autofire_pressed = false;
      }

      if(var1 == 57 || var1 == 109 || var1 == 110) {
         this.var_d2 &= -1025;
		 GlobalStatus.autopilot_pressed = false;
      }

      if(var1 == 42) {
         this.var_d2 &= -2049;
      }

      if(var1 == 35) {
         this.var_d2 &= -4097;
		 GlobalStatus.ship_stop_pressed = false;
      }

      this.var_165 = ~this.var_d2 & this.var_133;
   }
}
