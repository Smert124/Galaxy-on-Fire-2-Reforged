package Main;

import javax.microedition.lcdui.game.GameCanvas;
import AE.GlobalStatus;
import GoF2.RecordHandler;

import GoF2.AutoPilotList;
import GoF2.Hud;
import GoF2.Layout;
import GoF2.Popup;

public abstract class GOF2Canvas extends GameCanvas {

   boolean var_13;
   int[] var_69;
   int[] var_af;
   int[] var_110;
   int[] var_172;
   int var_190;
   int var_1bf;
   boolean var_1da;
   boolean var_219;
   boolean var_235;
   boolean right,left,up,down;
   boolean follow;
   public int w = getWidth();
   public int h = getHeight();
   public static RecordHandler SAVE_RMS;


   protected GOF2Canvas() {
      super(false);
      this.sub_52();
   }

   protected GOF2Canvas(boolean var1) {
      super(var1);
      this.sub_52();
   }

   private void sub_52() {
	  GlobalStatus.width = getWidth();
	  GlobalStatus.height = getHeight();
      this.var_13 = false;
      this.var_219 = true;
      this.var_235 = false;
      this.var_190 = 0;
      this.var_69 = new int[13];
      this.var_af = new int[]{-202, -202, -202, -202, -202, -202, -202, -202, -202, -202, -202, -202, -202, -202, -202, -202, -202, -202, -202, -202, -202};
      this.var_110 = new int[]{-202, -202, -202, -202, -202, -202, -202, -202, -202, -202, -202, -202, -202, -202, -202, -202, -202, -202, -202, -202, -202};
      this.var_172 = new int[]{49, 50, 51, 52, 53, 54, 55, 56, 57, -6, 48, -7, 42, 35};
      this.var_1da = false;
      this.setFullScreenMode(this.var_13);
	  this.Adaptation();

      for(int var2 = 0; var2 < 13; ++var2) {
         if(var2 != 0 && var2 != 3 && var2 != 4 && var2 != 7) {
            this.var_69[var2] = this.sub_81(super.getKeyCode(var2));
         } else {
            this.var_69[var2] = 0;
         }
      }

   }

   private int sub_81(int var1) {
      for(int var2 = 0; var2 < this.var_190; ++var2) {
         if(this.var_af[var2] == var1) {
            return this.var_110[var2];
         }
      }

      return var1;
   }

   protected void keyPressed(int var1) {
	   GlobalStatus.screen_keyboard = false;
      this.sub_c3(this.sub_81(var1));
	  switch(var1) {
	   case 50: right=true; break; 
       case 56: left=true; break;
       case 52: down=true; break; 
       case 54: up=true; break;
	  }
   }

   protected void keyReleased(int var1) {
      this.sub_e1(this.sub_81(var1));
	  switch(var1) {
	   case 50: right=false; break; 
       case 56: left=false; break;
       case 52: down=false; break; 
       case 54: up=false; break;
	  }
   }

   protected void keyRepeated(int var1) {
	   GlobalStatus.screen_keyboard = false;
      this.sub_13a(this.sub_81(var1));
   }

   protected void sub_c3(int var1) {}

   protected void sub_e1(int var1) {}

   protected void sub_13a(int var1) {}

   protected void sub_15e(int var1) {
      if(this.var_1bf == var1 && this.var_235) {
         this.sub_13a(var1);
      } else {
         if(this.var_1da && this.var_219) {
            this.sub_e1(this.var_1bf);
            this.var_1da = false;
         }

         this.sub_c3(var1);
         this.var_1bf = var1;
         this.var_1da = true;
      }

   }
   
   public void pointerDragged(int x, int y) {
	   GlobalStatus.screen_keyboard = true;
	   Hud.pointerDragged(x, y);
    /** if(follow == true) {
		SharedVariables.cur_x = x;
		SharedVariables.cur_y = y;
		SharedVariables.joy_pressed = true;
	}
	if(SharedVariables.cur_x > 120) {
		SharedVariables.cur_x = 120;
	}
	if(SharedVariables.cur_x < 5) {
		SharedVariables.cur_x = 5;
	}
	if(SharedVariables.cur_y > SharedVariables.height - 20) {
		SharedVariables.cur_y = SharedVariables.height - 20;
	}
	if(SharedVariables.cur_y < SharedVariables.height - 120) {
		SharedVariables.cur_y = SharedVariables.height - 120;
	}
	if(SharedVariables.cur_x >= 45 && SharedVariables.cur_x <= 117 && SharedVariables.cur_y >= SharedVariables.height - 133 && SharedVariables.cur_y < SharedVariables.height - 65) {
		Control();
		up = false;
		down = false;
		left = false;
		right = false;
	}
	if(SharedVariables.cur_x >= 47 && SharedVariables.cur_x <= 88 && SharedVariables.cur_y >= SharedVariables.height - 125 && SharedVariables.cur_y <= SharedVariables.height - 87) {
		this.sub_15e(this.var_172[1]);
	}
	if(SharedVariables.cur_x >= 47 && SharedVariables.cur_x <= 88 && SharedVariables.cur_y >= SharedVariables.height - 40 && SharedVariables.cur_y <= SharedVariables.height) {
		this.sub_15e(this.var_172[7]);
	}
	if(SharedVariables.cur_x >= 0 && SharedVariables.cur_x <= 46 && SharedVariables.cur_y >= SharedVariables.height - 81 && SharedVariables.cur_y <= SharedVariables.height - 40) {
		this.sub_15e(this.var_172[3]);
	}
	if(SharedVariables.cur_x >= 85 && SharedVariables.cur_x <= 300 && SharedVariables.cur_y >= SharedVariables.height - 81 && SharedVariables.cur_y <= SharedVariables.height - 40) {
		this.sub_15e(this.var_172[5]);
	} **/
   }
   
   public void Control() {
    if(up) {
	 this.sub_15e(this.var_172[1]);
	}
	if(down) {
	 this.sub_15e(this.var_172[7]);
	}
	if(left) {
	 this.sub_15e(this.var_172[3]);
	}
	if(right) {
	 this.sub_15e(this.var_172[5]);
	}
   }

   protected void pointerPressed(int x, int y) {
	   Intro.pointerPressed(x, y);
	   OptionsWindow.pointerPressed(x, y);
	   Layout.UIPointerPressed(x, y);
	   Popup.dialogueMenuPointerPressed(x, y);
	   ModStation.pointerPressed(x, y);
	   Hud.pointerPressed(x, y);
	   HangarList.hangarListPointerPressed(x, y);
	   if(AutoPilotList.autopilotListButton != null) {
		   AutoPilotList.pointerPressed(x, y);
	   }
	  //follow = true;
	  GlobalStatus.screen_keyboard = true;
	/**  if(w >= 47 && w <= 88 && h >= SharedVariables.height - 125 && h <= SharedVariables.height - 87) { // key_up
	   Control();
	   up = true;
	   SharedVariables.up_pressed = true;
	   this.sub_15e(this.var_172[1]);
	  }
	  if(w >= 47 && w <= 88 && h >= SharedVariables.height - 40 && h <= SharedVariables.height) { // key_down
	   Control();
	   down = true;
	   SharedVariables.down_pressed = true;
	   this.sub_15e(this.var_172[7]);
	  }
	  if(w >= 0 && w <= 46 && h >= SharedVariables.height - 81 && h <= SharedVariables.height - 40) { // key_left
	   Control();
	   left = true;
	   SharedVariables.left_pressed = true;
	   this.sub_15e(this.var_172[3]);
	  }
	  if(w >= 85 && w <= 120 && h >= SharedVariables.height - 81 && h <= SharedVariables.height - 40) { // key_right
	   Control();
	   right = true;
	   SharedVariables.right_pressed = true;
	   this.sub_15e(this.var_172[5]);
	  }
	  if(w >= 150 && w <= 190 && h >= SharedVariables.height - 50 && h <= SharedVariables.height - 0) { // key_boost
	   SharedVariables.boost_pressed = true;
	   this.sub_15e(this.var_172[2]);
	  }
	  if(w >= 0 && w <= 60 && h >= SharedVariables.height - 176 && h <= SharedVariables.height - 137) { // key_autopilot
	   SharedVariables.autopilot_pressed = true;
	   this.sub_15e(this.var_172[8]);
	  }
	  if(w >= SharedVariables.width - 142 && w <= SharedVariables.width - 96 && h >= SharedVariables.height - 79 && h <= SharedVariables.height - 32) { // key_autofire
	   SharedVariables.autofire_pressed = true;
	   this.sub_15e(this.var_172[6]);
	  }
	  if(w >= SharedVariables.width - 49 && w <= SharedVariables.width && h >= SharedVariables.height - 129 && h <= SharedVariables.height - 81) { // key_rocket
	   SharedVariables.rocket_pressed = true;
	   this.sub_15e(this.var_172[0]);
	  }
	  if(w >= SharedVariables.width - 76 && w <= SharedVariables.width - 6 && h >= SharedVariables.height - 80 && h <= SharedVariables.height - 9) { // key_ok
	   SharedVariables.ok_pressed = true;
	   this.sub_15e(this.var_172[4]);
	  }
	  if(w >= SharedVariables.width - 92 && w <= SharedVariables.width - 50 && h >= SharedVariables.height - 129 && h <= SharedVariables.height - 81) { //key_turret
	   SharedVariables.camera_pressed = true;
	   this.sub_15e(this.var_172[10]);
	  }
	  if(w >= SharedVariables.width - 76 && w <= SharedVariables.width - 6 && h >= SharedVariables.height - 180 && h <= SharedVariables.height - 132) { // key_quickmenu
	   SharedVariables.quickmenu_pressed = true;
	   SharedVariables.left_key_active = true;
	   this.sub_15e(this.var_172[11]);
	  }
	  if(w >= SharedVariables.width - 100 && w <= SharedVariables.width && h >= 0 && h <= 45) { // key_pause
	   SharedVariables.pause_pressed = true;
	   SharedVariables.right_key_active = true;
	   this.sub_15e(this.var_172[9]);
	  } **/
	/**  if(w >= 198 && w <= SharedVariables.width - 216 && h >= SharedVariables.height - 230 && h <= SharedVariables.height - 120) { // fire
	   this.sub_15e(this.var_172[4]);
	  } **/
	/**  if(w >= 0 && w <= 90 && h >= 0 && h <= 42)
	  {
		SharedVariables.left_key_active = true;
		this.sub_15e(this.var_172[11]);
	  }
	  if(w >= 0 && w <= 60 && h >= SharedVariables.height - 223 && h <= SharedVariables.height - 177) // ship stop
	  {
		SharedVariables.ship_stop_pressed = true;
		this.sub_15e(this.var_172[13]);
	  } **/
    // System.out.println("Coordinates" + "(" + x + "," + y + ")");
	 /* System.out.println("" + SharedVariables.start_ship);
	 System.out.println("" + SharedVariables.texture_type[SharedVariables.textures]); */
   }

   protected void pointerReleased(int var1, int var2) {
	   Intro.pointerReleased(var1, var2);
	   OptionsWindow.pointerReleased(var1, var2);
	   Layout.UIPointerReleased(var1, var2);
	   Popup.dialogueMenuPointerReleased(var1, var2);
	   ModStation.pointerReleased(var1, var2);
	   Hud.pointerReleased(var1, var2);
	   HangarList.hangarListPointerReleased(var1, var2);
	   if(AutoPilotList.autopilotListButton != null) {
		   AutoPilotList.pointerReleased(var1, var2);
	   }
    up = false;
	down = false;
	left = false;
	right = false;
	follow = false;
	GlobalStatus.up_pressed = false;
	GlobalStatus.down_pressed = false;
	GlobalStatus.left_pressed = false;
	GlobalStatus.right_pressed = false;
	GlobalStatus.joy_pressed = false;
	GlobalStatus.ok_pressed = false;
	GlobalStatus.rocket_pressed = false;
	GlobalStatus.autofire_pressed = false;
	GlobalStatus.boost_pressed = false;
	GlobalStatus.quickmenu_pressed = false;
	GlobalStatus.camera_pressed = false;
	GlobalStatus.autopilot_pressed = false;
	GlobalStatus.ship_stop_pressed = false;
	GlobalStatus.pause_pressed = false;
	GlobalStatus.left_key_active = false;
	GlobalStatus.right_key_active = false;
	GlobalStatus.rect_button_pressed = false;
	GlobalStatus.cur_x = 67;
	GlobalStatus.cur_y = GlobalStatus.height - 64;

   }

   public int getGameAction(int var1) {
      boolean var2 = false;
      for(int var3 = 0; var3 < this.var_190; ++var3) {
         if(this.var_110[var3] == var1) {
            var2 = true;
            int var4 = super.getGameAction(this.var_af[var3]);
            if(var4 != 0) {
               return var4;
            }
         }
      }

      return var2?0:super.getGameAction(var1);
   }
   
   public void Adaptation() {
	   GlobalStatus.screen_keyboard = false;
	   if(GlobalStatus.texture_type[GlobalStatus.textures] == 3) {
		   (new RecordHandler()).writeOptions();
		}
		if(w >= 360 && h >= 640 || w >= 640 || h >= 360) {
			GlobalStatus.galaxymap_texture = true;
		}
		if(w <= 240 && h <= 320 || w <= 320 || h <= 240) {
			GlobalStatus.galaxymap_texture = false;
		}
	}

   public int getKeyCode(int var1) {
      return this.var_69[var1] != 0?this.var_69[var1]:super.getKeyCode(var1);
   }

   public String getKeyName(int var1) {
      for(int var2 = 0; var2 < this.var_190; ++var2) {
         if(this.var_110[var2] == var1) {
            return super.getKeyName(this.var_af[var2]);
         }
      }

      return super.getKeyName(var1);
   }
}
