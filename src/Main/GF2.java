package Main;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import AE.GlobalStatus;

import GoF2.FileRead;
import GoF2.Globals;
import GoF2.RecordHandler;

public class GF2 extends MIDlet implements Runnable {

   public int var_4f4;
   public static final int var_52f = 1;
   public static final int var_5c8 = 2;
   public static final int var_5f9 = 3;
   public static final int var_670 = 4;
   private FONT_LOADER font_loader;
   public static Display display;
   private boolean started = false;
   private boolean selfDestructing = false;
   private Thread thread;
   public static GF2 GF2;
   public static RecordHandler RMS_LOADER;
   
   public GF2() {
    GF2 = this;
      try {
         this.display = Display.getDisplay(this);
         GlobalStatus.display = this.display;
         GlobalStatus.midlet = this;
         this.font_loader = new FONT_LOADER(this);
         this.thread = new Thread(this);
         this.var_4f4 = 1;
      } catch (Exception var1) {
         GlobalStatus.CATCHED_ERROR = "ERROR: " + var1;
		 System.out.println(GlobalStatus.CATCHED_ERROR);
      }
   }
   
   public void BOOTLOADER() {
	   Globals.loadScripts();
	   FileRead.MASTER_CONFIG();
	   (new RecordHandler()).BOOTLOADER_RMS_READ();
	   
   }

   public Display sub_38() {
      return this.display;
   }

   public void startApp() {
      try { 
		 BOOTLOADER();
         this.display.setCurrent(this.font_loader);
         this.font_loader.setFullScreenMode(true);
         display = Display.getDisplay(this);
         if(!this.started) {
            this.thread.start();
            this.started = true;
         }

      } catch (Exception var1) {
         GlobalStatus.CATCHED_ERROR = "ERROR: " + var1;
		 System.out.println(GlobalStatus.CATCHED_ERROR);
      }
   }

   public void pauseApp() {
      this.sub_114();
   }

   public void sub_68() {
      this.var_4f4 = 2;
   }

   public void sub_114() {
      this.var_4f4 = 4;
   }

   public void destroyApp(boolean var1) {
      try {
         this.selfDestructing = true;
         this.font_loader.sub_57();
         this.notifyDestroyed();
      } catch (Exception var2) {
         GlobalStatus.CATCHED_ERROR = "ERROR: " + var2;
		 System.out.println(GlobalStatus.CATCHED_ERROR);
      }
   }

   public void run() {
      try {
         while(true) {
            switch(this.var_4f4) {
            case 1:
               if(GlobalStatus.soundDeviceUnavailable && !this.selfDestructing && GlobalStatus.currentMusic != -1) {
                  GlobalStatus.soundManager.playMusic(GlobalStatus.currentMusic);
               }

               this.font_loader.sub_4d();
               this.font_loader.sub_d1();
               break;
            case 2:
               if(GlobalStatus.soundManager != null && GlobalStatus.musicOn) {
                  GlobalStatus.soundManager.resume();
               }

               GlobalStatus.paused = false;
               this.var_4f4 = 1;
               break;
            case 3:
               this.font_loader.sub_8d();
               this.font_loader.sub_d1();
               break;
            case 4:
               this.font_loader.sub_2f();
               this.var_4f4 = 3;
            }

            try {
               Thread.sleep(2L); // 2L
            } catch (InterruptedException var2) {
               GlobalStatus.CATCHED_ERROR = "ERROR: " + var2;
			   System.out.println(GlobalStatus.CATCHED_ERROR);
            }
         }
      } catch (Exception var3) {
		 GlobalStatus.CATCHED_ERROR = "ERROR: " + var3;
		 System.out.println(GlobalStatus.CATCHED_ERROR);
         var3.printStackTrace();
      }
   }

   public void sub_199() {
      this.destroyApp(false);
   }
}
