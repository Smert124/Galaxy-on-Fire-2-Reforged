/**
** ���������� ������� GF2.
** + ����� ������������ ��� ����������� �������� �� ����� ��������. ����: img in load
**/

package GoF2;

import HardEngine.Class_3e;
import Main.GF2;

//import HardEngine.interface_loader;
import javax.microedition.lcdui.Image;

import AE.GlobalStatus;
import AE.PaintCanvas.Font;

public final class LoadingScreen implements Runnable {

   private boolean stillLoading = false;
   private Class_3e var_13f;
   private Thread loadingThread;
   private String[] tipsRows;
   private String stateIndicator;
   private static String textOverStateIndicator = "";
   private long endTime;
   private long startTime;
//   private static interface_loader IL;

   public LoadingScreen() {
	//  IL = new interface_loader();
   }

   public static Image getGameLogo()
   {
	return Globals.gof2LogoImage;
   }

   public final void sub_9d(Class_3e var1) {
      this.var_13f = var1;
   }

   public final void run() {
      while(this.stillLoading && !GlobalStatus.paused) {
         Layout.drawBG();
         int var10001 = GlobalStatus.var_e75 / 2;
         String var2 = this.stateIndicator;
         Font.drawString(this.stateIndicator, var10001 - Font.getTextWidth(this.stateIndicator, 0) / 2, GlobalStatus.var_eb6 / 2 - Font.getFontSpacingY() / 2 - 10, 0);
         Font.drawString(textOverStateIndicator, GlobalStatus.var_e75 / 2 - Font.getTextWidth(this.stateIndicator, 0) / 2, GlobalStatus.var_eb6 / 2 - (Font.getFontSpacingY() << 1) - 10, 0);
         GlobalStatus.graphics.drawImage(Globals.gof2LogoImage, GlobalStatus.var_e75 >> 1, 10, 17);
		 
		 if(GlobalStatus.screen_keyboard == true)
		 {
		//	IL.draw_interface();
		//	IL.draw_station_interface();
		 }
		 
		 if(GlobalStatus.developer_mode == true) {
			 Font.drawString(GF2.GF2.getAppProperty("MIDlet-Name"), 5, 20, 6);
			 Font.drawString("BUILD " + GF2.GF2.getAppProperty("MIDlet-Version"), 5, 40, 4);
			 Font.drawString("SYSTEMS: " + GlobalStatus.max_systems, 5, 60, 6);
			 Font.drawString("STATIONS: " + GlobalStatus.max_stations, 5, 80, 6);
			 Font.drawString("SHIPS: " + GlobalStatus.max_ships, 5, 100, 6);
			 Font.drawString("ITEMS: " + GlobalStatus.max_items, 5, 120, 6);
			 Font.drawString("COLLISION TEST: " + GlobalStatus.STATION_COLLISION_BOX_VISIBLE, 5, 140, 6);
		 }
		 Font.drawString("DEVELOPER: " + GlobalStatus.developer_mode, 5, 0, 6);
		 Font.sub_14d_CENTER(GlobalStatus.CATCHED_ERROR, GlobalStatus.var_e75 / 2, GlobalStatus.var_eb6 / 2, 4); // BLYAT
		 
		// Class_ba6.sub_14d("(", 86, 5, 1);
		// Class_ba6.sub_14d(")", 128, 5, 1);
         if(GlobalStatus.applicationManager != null && GlobalStatus.applicationManager.GetCurrentApplicationModule() != null && GlobalStatus.applicationManager.GetCurrentApplicationModule() != GlobalStatus.scenes[3]) {
            this.draw();
         }

         this.endTime = System.currentTimeMillis();
         Status.loadingTime += this.endTime - this.startTime;
         this.startTime = this.endTime;
         if(this.var_13f != null) {
            this.var_13f.flushGraphics();
         }

         try {
            Thread.sleep(10L); // 10L deafault
         } catch (Exception var3) {
			 GlobalStatus.CATCHED_ERROR = "RUN ERROR: " + var3;
			 this.stillLoading = false;
         }
      }

   }

   public final void draw() {
      Layout.drawMask(10, GlobalStatus.var_eb6 - 10 * this.tipsRows.length - 32, GlobalStatus.var_e75 - 20, 10 * this.tipsRows.length + 20);
      Layout.drawTextBox(GlobalStatus.gameText.getText(277), 10, GlobalStatus.var_eb6 - 10 * this.tipsRows.length - 34, GlobalStatus.var_e75 - 20, 10 * this.tipsRows.length + 24, false);
      Font.drawLines(this.tipsRows, 17, GlobalStatus.var_eb6 - 10 * this.tipsRows.length - 18, 1);
   }

   public final void close() {
      this.stillLoading = false;
   }

   public final void startLoading_(boolean var1) {
      this.startTime = System.currentTimeMillis();
      ++Status.var_1016;
      this.stateIndicator = GlobalStatus.gameText.getText(var1?214:215);
      int var2 = GlobalStatus.random.nextInt(GameText.tips.length);
      this.tipsRows = Font.splitToLines(GlobalStatus.gameText.getText(GameText.tips[var2]), GlobalStatus.var_e75 - 34);
      this.stillLoading = true;
      this.loadingThread = new Thread(this);
      this.loadingThread.start();
   }

}
