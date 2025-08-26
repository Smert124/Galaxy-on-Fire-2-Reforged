package Main;

import HardEngine.*;

import AE.GlobalStatus;
import AE.IApplicationModule;
import GoF2.Globals;
import GoF2.Layout;
import GoF2.LoadingScreen;

public final class Intro extends IApplicationModule {
	
	private boolean loaded;
	private long timeResourceLoaded;
	private int introState;
	private int soundSwitchCurrent;
	public static AEButtonManager[] AEButton;
	public static boolean touchFlag = false;
  // public static AEButtonManager joystick_test;
   
   public final void OnInitialize() {
	  AEButton = new AEButtonManager[2];
	  for(int countButton = 0; countButton < 2; ++countButton) {
		  if(AEButton[countButton] == null) {
			  AEButton[countButton] = new AEButtonManager();
		  }
	  }
	//  joystick_test = new AEButtonManager();
      this.timeResourceLoaded = System.currentTimeMillis();
      this.introState = 0;
      this.loaded = true;
      this.soundSwitchCurrent = 0;
      System.gc();
   }

   public final void OnRelease() {
      this.loaded = false;
   }

   public final boolean isLoaded() {
      return this.loaded;
   }

   public final void handleKeystate(int var1) {
	  if(AEButton[0].getStandartButtonPressed() == true) {
		  
		  this.soundSwitchCurrent = 0;
		  var1 = 256;
		  
	  }
	  
	  if(AEButton[1].getStandartButtonPressed() == true) {
		  
		  this.soundSwitchCurrent = 1;
		  var1 = 256;
		  
	  }
      if(this.introState == 0) {
         if(var1 == 2 && this.soundSwitchCurrent == 1) {
            --this.soundSwitchCurrent;
         }
         if(var1 == 64 && this.soundSwitchCurrent == 0) {
            ++this.soundSwitchCurrent;
         }
         if(var1 == 256 || var1 == 16384) {
            GlobalStatus.musicOn = this.soundSwitchCurrent == 0;
            GlobalStatus.sfxOn = this.soundSwitchCurrent == 0;
            if(this.soundSwitchCurrent == 1) {
               GlobalStatus.musicVolume = 3;
               GlobalStatus.sfxVolume = 3;
            } else {
               GlobalStatus.musicVolume = 1;
               GlobalStatus.sfxVolume = 1;
            }
            GlobalStatus.soundManager.setMusicVolume(100);
            GlobalStatus.soundManager.setSfxVolume(100);
            GlobalStatus.soundManager.playMusic(0);
            this.timeResourceLoaded = System.currentTimeMillis();
            this.introState = 1;
         }
      }
   }

   public final void renderScene(int var1) {
      Layout.drawBG();
      if(this.introState == 0) {
         GlobalStatus.graphics.drawImage(LoadingScreen.getGameLogo(), GlobalStatus.var_e75 >> 1, 10, 17);
		 
		 AEButton[0].drawStandartButton(Globals.rectRoundedButtonNormal, Globals.rectRoundedButtonPressed, GlobalStatus.var_e75 >> 1, GlobalStatus.var_eb6 + 15 >> 1);
		 AEButton[1].drawStandartButton(Globals.rectRoundedButtonNormal, Globals.rectRoundedButtonPressed, GlobalStatus.var_e75 >> 1, AEButton[0].standartButtonY + (AEButton[0].standartButtonHeight + 2));
		 
         Layout.drawTextItem(GlobalStatus.gameText.getText(6) + " " + GlobalStatus.gameText.getText(15), AEButton[0].standartButtonX, AEButton[0].standartButtonY, GlobalStatus.var_e75, this.soundSwitchCurrent == 0);
         Layout.drawTextItem(GlobalStatus.gameText.getText(6) + " " + GlobalStatus.gameText.getText(16), AEButton[1].standartButtonX, AEButton[1].standartButtonY, GlobalStatus.var_e75, this.soundSwitchCurrent == 1);
		 touchFlag = true;
      } else {
         if(this.introState == 1 && GlobalStatus.developer_mode == false) {
            GlobalStatus.graphics.drawImage(Globals.fishlabsImage, GlobalStatus.var_e75 / 2, GlobalStatus.var_eb6 / 2, 3);
		    touchFlag = false;
			//IL.draw_HC();
            if(System.currentTimeMillis() - this.timeResourceLoaded > 2000L) {
               ++this.introState;
               return;
            }
         } else if(this.introState == 2 && GlobalStatus.developer_mode == false) {
            GlobalStatus.graphics.drawImage(Globals.abyssImage, GlobalStatus.var_e75 / 2, GlobalStatus.var_eb6 / 2, 3);
			//IL.draw_Abyss();
            if(System.currentTimeMillis() - this.timeResourceLoaded > 4000L) {
               ++this.introState;
               return;
            }
         } else {
            GlobalStatus.applicationManager.SetCurrentApplicationModule(GlobalStatus.scenes[0]);
         }
      }
   }
   
   public static void pointerPressed(int x, int y) {
	   
	   if(touchFlag == true && AEButton != null) {
		   AEButton[0].standartButtonTouch(x, y);
		   AEButton[1].standartButtonTouch(x, y);
	   }
	   
    }
	
    public static void pointerReleased(int x, int y) {
		
		if(touchFlag == true && AEButton != null) {
			AEButton[0].buttonsTouchReleased(x, y);
			AEButton[1].buttonsTouchReleased(x, y);
		}
		
    }
   
}