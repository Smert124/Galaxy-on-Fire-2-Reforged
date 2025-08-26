package Main;

import javax.microedition.lcdui.Image;

import AE.AEResourceManager;
import AE.AECamera;
import AE.GlobalStatus;
import AE.Group;
import AE.PaintCanvas.Font;
import AE.PaintCanvas.ImageFactory;
import GoF2.FileRead;
import GoF2.GameText;
import GoF2.Globals;
import GoF2.Item;
import GoF2.Layout;
import GoF2.ListItem;
import GoF2.Ship;
import GoF2.SolarSystem;
import GoF2.Status;
import GoF2.TextBox;

public final class ListItemWindow {
	
   private final short[] LISTITEMWINDOW_HIDDEN_ATTRIBUTES = new short[]{(short)0, (short)1, (short)4, (short)5, (short)6, (short)7, (short)8, (short)35, (short)36, (short)532};
   private final String[] _LISTITEMWINDOW_UNITS = new String[]{null, null, null, null, null, null, null, null, null, null, null, "ms", "m", "km/h", "m", null, null, "ms", null, "%", "%", null, "ms", "%", "ms", "ms", "%", "ms", null, null, "%", "%", null, "ms", "ms", null, null};
   private ListItem contextItem;
   private Image items;
   private Image itemTypes;
   private Image var_1bb;
   private Image var_1ef;
   private TextBox textBox;
   private TextBox highlightedText;
   private boolean show3DShip;
   private AECamera shipPreviewCam;
   private AECamera lastCam;
   private Group ship;
   private float yaw;
   private float pitch;
   private float speedPitchDown;
   private float speedPitchUp;
   private float speedYawCCW;
   private float speedYawCW;
   private String lowPriceSysRow;
   private String highPriceSysRow;
   private Image[] ships2 = new Image[GlobalStatus.max_ships];
   private int damage;
   private int reloadTimeMS;
   private double reloadTimeSec;
   private double dpsRAW;
   private double dps;
   private double roundedDPS;


   public ListItemWindow() {
      this.textBox = new TextBox(10, 40, GlobalStatus.var_e75 - 20, GlobalStatus.var_eb6 - 40 - 16 - 20, "");
      this.textBox.setFont(1);
      this.highlightedText = new TextBox(10, 40, GlobalStatus.var_e75 - 20, GlobalStatus.var_eb6 - 40 - 16 - 20, "");
      this.highlightedText.setHide(true);
      this.highlightedText.setFont(0);
   }

   public final void setup(ListItem var1, Image var2, Image var3, Image var4, Image var5, boolean var6) { // просмотр характеристик кораблей и оборудования
      this.contextItem = var1;
      this.items = var2;
      this.itemTypes = var3;
      this.var_1bb = var4;
      this.var_1ef = var5;
      String var9 = "";
      String var10 = "";
      if(!var1.isItem() && !var1.isBluePrint() && !var1.isPendingProduct()) {
         if(var1.isShip()) {
            var9 = var9 + "\n" + "\n" + GlobalStatus.gameText.getText(60);
            var10 = var10 + "\n" + "\n" + var1.ship.getBaseHP();
            var9 = var9 + "\n" + GlobalStatus.gameText.getText(61);
            var10 = var10 + "\n" + var1.ship.getBaseLoad();
            var9 = var9 + "\n" + GlobalStatus.gameText.getText(123);
            var10 = var10 + "\n" + var1.ship.getSlots(0);
            var9 = var9 + "\n" + GlobalStatus.gameText.getText(124);
            var10 = var10 + "\n" + var1.ship.getSlots(1);
            var9 = var9 + "\n" + GlobalStatus.gameText.getText(125);
            var10 = var10 + "\n" + var1.ship.getSlots(2);
            var9 = var9 + "\n" + GlobalStatus.gameText.getText(127);
            var10 = var10 + "\n" + var1.ship.getSlots(3);
            var9 = var9 + "\n" + GlobalStatus.gameText.getText(59);
            var10 = var10 + "\n" + (int)(var1.ship.getHandling() * 100.0F);
			var9 = var9 + "\n" + GlobalStatus.gameText.getText(654);
            var10 = var10 + "\n" + GlobalStatus.gameText.getText(var1.ship.getKhadorIntegrated()?38:39);
            var9 = var9 + "\n" + GlobalStatus.gameText.getText(36);
            var10 = var10 + "\n" + Layout.formatCredits(var1.getPrice());
            this.textBox.setText(var9);
            this.highlightedText.setText(var10);
            this.yaw = 1900.0F;
            this.pitch = 0.0F;
            this.speedPitchDown = 0.0F;
            this.speedPitchUp = 0.0F;
            this.speedYawCCW = 0.0F;
            this.speedYawCW = 0.0F;
         }

      } else {
         Item var11 = var1.isItem()?var1.item:(var1.isBluePrint()?Globals.getItems()[var1.bluePrint.getIndex()]:Globals.getItems()[var1.producedGood.index]);
         int var12 = 0;

         int var14;
         while(var12 < 38) {
            boolean var7 = false;
            int var8 = 0;
			int damage_2 = 0;

            while(true) {
               if(var8 < this.LISTITEMWINDOW_HIDDEN_ATTRIBUTES.length) {
                  if(this.LISTITEMWINDOW_HIDDEN_ATTRIBUTES[var8] != var12) {
                     ++var8;
                     continue;
                  }

                  var7 = true;
               }

               if(!var7 && (var8 = var11.getAttribute(var12)) != -979797979) {
                  if(!var9.equals("")) {
                     var9 = var9 + "\n";
                     var10 = "\n" + var10 + "\n";
                  }

                  var9 = "\n" + var9 + GlobalStatus.gameText.getText(GameText.LISTITEMWINDOW_KEY_TEXT_IDS[var12]);
                  if(var12 != 29 && var12 != 28) {
                     if(var12 == 21) {
                        var10 = var10 + GlobalStatus.gameText.getText(var8 == 0?39:38);
                     } else if(var12 == 2) {
                        var10 = var10 + GlobalStatus.gameText.getText(var8 + 98); // тип оружия
                     } else {
                        label131: {
						   if(var12 == 9) {
							   this.damage = var8;
							   var9 = var9 + "\n" + GlobalStatus.gameText.getText(GameText.LISTITEMWINDOW_KEY_TEXT_IDS[36]); // отобразить "урон в секунду" в левой части
						   }
						   
						   if(var12 == 11) {
							   this.reloadTimeMS = var8;
							   this.reloadTimeSec = this.reloadTimeMS / 1000.0;
							   this.dpsRAW = this.damage / this.reloadTimeSec;
							   this.roundedDPS = roundToTwoDecimalPlaces(this.dpsRAW);
							   var10 = var10 + getDPS() + "\n"; // отобразить сам параметр урона в секунду справа
							//   System.out.println("DPS: " + getDPS());
						   }
						   
						   int var10000;
                           if(var12 == 13) {
                              var10000 = var8 * 250;
                           } else {
                              if(var12 != 12) {
                                 break label131;
                              }

                            //  var14 = (var8 = (int)((float)var8)) % 100;
							  var14 = (var8 = (int)((float)var8 / 3600.0F * (float)(var11.getAttribute(13) * 250))) % 100;
							// Старая формула расчета скорости и дальности. Дальность зависела от скорости.
                              var10000 = (var8 + var14) % 100 == 0?var8 + var14:var8 - var14;
							  
                           }
                           var8 = var10000;
                        }

                        var10 = var10 + var8;
                        if(this._LISTITEMWINDOW_UNITS[var12] != null) {
                           var10 = var10 + this._LISTITEMWINDOW_UNITS[var12];
                        }
                     }
                  } else {
                     var10 = var10 + GlobalStatus.gameText.getText(var8 == 0?39:38);
                  }
               }

               ++var12;
               break;
            }
         }

         if(!var1.isBluePrint() && !var1.isPendingProduct() && var6) {
            var9 = var9 + "\n" + GlobalStatus.gameText.getText(36);
            var10 = var10 + "\n" + Layout.formatCredits(var11.getSinglePrice());
         }

         if(var1.isItem()) {
            new FileRead();
            var5 = null;
            SolarSystem[] var15 = FileRead.loadSystemsBinary();
            if(Status.var_cca[var1.getIndex()] > 0) {
               if(Status.var_d11[var1.getIndex()] == Status.getSystem().getId()) {
                  this.lowPriceSysRow = GlobalStatus.gameText.getText(95);
               } else {
                  this.lowPriceSysRow = GlobalStatus.gameText.getText(93);
               }

               this.lowPriceSysRow = Status.replaceTokens(this.lowPriceSysRow, Status.var_cca[var1.getIndex()] + "", "#C");
               this.lowPriceSysRow = Status.replaceTokens(this.lowPriceSysRow, var15[Status.var_d11[var1.getIndex()]].getName(), "#S");
            } else {
               this.lowPriceSysRow = null;
            }

            if(Status.var_cff[var1.getIndex()] > 0) {
               if(Status.var_d2a[var1.getIndex()] == Status.getSystem().getId()) {
                  this.highPriceSysRow = GlobalStatus.gameText.getText(96);
               } else {
                  this.highPriceSysRow = GlobalStatus.gameText.getText(94);
               }

               this.highPriceSysRow = Status.replaceTokens(this.highPriceSysRow, Status.var_cff[var1.getIndex()] + "", "#C");
               this.highPriceSysRow = Status.replaceTokens(this.highPriceSysRow, var15[Status.var_d2a[var1.getIndex()]].getName(), "#S");
            } else {
               this.highPriceSysRow = null;
            }

            String var16 = GlobalStatus.gameText.getNamedParameterItemsDescription(var1.getIndex());
            var9 = var9 + "\n\n" + var16;
            if(this.lowPriceSysRow != null) {
               var9 = var9 + "\n\n" + this.lowPriceSysRow;
            }

            if(this.highPriceSysRow != null) {
               var9 = var9 + "\n\n" + this.highPriceSysRow;
            }
         } else if(var1.isBluePrint() || var1.isPendingProduct()) {
            String var13 = GlobalStatus.gameText.getNamedParameterItemsDescription(var1.getIndex());
            var9 = var9 + "\n\n" + var13;
         }

         this.textBox.setText(var9);
         this.highlightedText.setText(var10);
         var12 = this.textBox.getTextHeight_() - this.highlightedText.getTextHeight_();

         for(var14 = 0; var14 < var12; ++var14) {
            var10 = var10 + "\n";
         }

         this.highlightedText.setText(var10);
      }
   }
   
   public static double roundToTwoDecimalPlaces(double value) {
	   return (int) (value * 100 + 0.5) / 100.0;
   }

   public final void scrollDown(int var1) {
      this.textBox.scrollDown(var1);
      this.highlightedText.scrollDown(var1);
   }

   public final void scrollUp(int var1) {
      this.textBox.scrollUp(var1);
      this.highlightedText.scrollUp(var1);
   }

   public final boolean shows3DShip() {
      return this.show3DShip;
   }

   public final void draw() {
      if(!this.show3DShip) {
         Layout.drawBG();
         Layout.drawWindowFrame(GlobalStatus.gameText.getText(212));
      }

      if(!this.contextItem.isItem() && !this.contextItem.isBluePrint() && !this.contextItem.isPendingProduct()) {
         if(this.contextItem.isShip()) {
           // FACE_GEN.sub_167(this.var_ea.var_234.getShipID(), this.var_ea.var_234.sub_3e(), this.var_1bb, this.var_1ef, 5, 27, 6);
		   if(ships2[this.contextItem.ship.getIndex()] == null) {
			   ships2[this.contextItem.ship.getIndex()] = AEResourceManager.getImage(9000 + this.contextItem.ship.getIndex());
			}
			GlobalStatus.graphics.drawImage(ships2[this.contextItem.ship.getIndex()], 5, 50, 6);
            Font.drawString(this.contextItem.ship.getShipName(this.contextItem.ship.getIndex()), 5 + ImageFactory.faceWidth + 20, 44, 1);
         }
      } else {
         Item var1;
         ImageFactory.drawItem((var1 = this.contextItem.isItem()?this.contextItem.item:(this.contextItem.isBluePrint()?Globals.getItems()[this.contextItem.bluePrint.getIndex()]:Globals.getItems()[this.contextItem.producedGood.index])).getIndex(), var1.getType(), this.items, this.itemTypes, 5, 50, 6);
         Font.drawString(GlobalStatus.gameText.getNamedParameterItems(var1.getIndex()), 5 + ImageFactory.itemFrameWidth + 6, 41, 1); // имя предмета в описании
      }

      this.textBox.draw();
      this.highlightedText.draw();
   }

   public final void updateRotation(int var1, int var2) {
      if(this.show3DShip) {
         if((var1 & 4) != 0) {
            this.speedYawCCW = (float)var2;
         } else {
            this.speedYawCCW *= 0.9F;
         }

         if((var1 & 32) != 0) {
            this.speedYawCW = (float)var2;
         } else {
            this.speedYawCW *= 0.9F;
         }

         if((var1 & 64) != 0) {
            this.speedPitchDown = (float)var2;
         } else {
            this.speedPitchDown *= 0.9F;
         }

         if((var1 & 2) != 0) {
            this.speedPitchUp = (float)var2;
         } else {
            this.speedPitchUp *= 0.9F;
         }

         this.pitch += this.speedPitchDown - this.speedPitchUp;
         if(this.pitch > 768.0F) {
            this.pitch = 768.0F;
         } else if(this.pitch < -256.0F) {
            this.pitch = -256.0F;
         }

         this.yaw += this.speedYawCCW - this.speedYawCW;
      }

   }

   public final void updateCamera_(boolean var1) {
      this.show3DShip = var1;
      if(var1) {
         this.lastCam = GlobalStatus.renderer.sub_85();
         this.shipPreviewCam = AECamera.create(GlobalStatus.var_e75, GlobalStatus.var_eb6, 1000, 10, 31768);
         this.shipPreviewCam.translate(0, 400, -Ship.SHIP_PREVIEW_SCALING[this.contextItem.getIndex()]);
         this.shipPreviewCam.rotateEuler(256, 2048, 0); // смещение корабля в просмотре его характеристик
         new Group();
         Group var2 = null;
         (var2 = Globals.getShipGroup(this.contextItem.getIndex(), this.contextItem.ship.sub_3e())).translate(0, 0, Ship.SHIP_HANGAR_OFFSETS[this.contextItem.getIndex()]);
         this.ship = new Group();
         this.ship.uniqueAppend_(var2);
      } else {
         if(this.lastCam != null) {
            GlobalStatus.renderer.setActiveCamera(this.lastCam);
         }

      }
   }

   public final void render() {
      this.ship.setRotation((int)this.pitch, (int)this.yaw, 0);
      Layout.drawBG();

      try {
         GlobalStatus.renderer.setActiveCamera(this.shipPreviewCam);
         GlobalStatus.graphics3D.bindTarget(GlobalStatus.graphics);
         GlobalStatus.renderer.drawNodeInVF(this.ship);
         GlobalStatus.renderer.renderFrame(System.currentTimeMillis());
         GlobalStatus.graphics3D.clear();
         GlobalStatus.graphics3D.releaseTarget();
      } catch (Exception var2) {
         GlobalStatus.graphics3D.releaseTarget();
         var2.printStackTrace();
      }

      Layout.drawNonFullScreenWindow(GlobalStatus.gameText.getText(212), false);
   }
   
   private double getDPS() {
	   
	   return this.roundedDPS;
	   
   }
}
