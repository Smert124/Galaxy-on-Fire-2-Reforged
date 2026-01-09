package Main;

import javax.microedition.lcdui.Image;

import AE.AEResourceManager;
import AE.GlobalStatus;
import GoF2.Globals;
import GoF2.Item;
import GoF2.Layout;
import GoF2.ListItem;
import GoF2.Popup;
import GoF2.Status;

public final class HangarWindow {

   private static int initTab = 1;
   private long timeOpen;
   private int frameTime;
   private boolean loaded;
   private HangarList list;
   private ListItem selectedItem;
   private ListItemWindow itemInfo;
   private Popup unused__3a1_;
   private Popup freeSlotInfoPopup;
   private boolean freeSlotInfoPopupOpen;
   private int tabsWidth_unused_;
   private int[] tabWidths_unused_;
   private int state__;
   private int speedSellTick;
   private int speedBuyTick;
   private int openedBluePrint;
   private Image itemTypesImage;
   private Image shipsColorImage;


   public final void OnRelease() {
      this.tabWidths_unused_ = null;
      if(this.list != null) {
         this.list.OnRelease();
      }

      this.list = null;
      this.selectedItem = null;
      this.itemInfo = null;
      this.unused__3a1_ = null;
      this.freeSlotInfoPopup = null;
      this.loaded = false;
      System.gc();
   }

   public final void initialize() {
      this.list = new HangarList(new String[]{GlobalStatus.gameText.getText(77), GlobalStatus.gameText.getText(79), GlobalStatus.gameText.getText(130), "", ""}, GlobalStatus.gameText.getText(62));
      this.list.sub_50(Status.getShip());
      Status.calcCargoPrices();
      Item[] var1 = Item.combineItems(Status.getShip().getCargo(), Status.getStation().sub_360());
      this.list.initShopTab(var1);
      this.list.initBlueprintTab(Status.getBluePrints());
	  
	  this.itemTypesImage = AEResourceManager.getImage(135);
	  this.shipsColorImage = AEResourceManager.getImage(136);
	  
      if(var1 != null) {
         for(int var2 = 0; var2 < var1.length; ++var2) {
            Item var3;
            int var4 = (var3 = var1[var2]).getSinglePrice();
            int var5 = var3.getIndex();
            if(var4 > Status.var_cff[var5] || Status.var_cff[var5] == 0) {
               Status.var_cff[var5] = var4;
               Status.var_d2a[var5] = (byte)Status.getSystem().getId();
            }

            if(var4 < Status.var_cca[var5] || Status.var_cca[var5] == 0) {
               Status.var_cca[var5] = var4;
               Status.var_d11[var5] = (byte)Status.getSystem().getId();
            }
         }
      }

      Status.getShip().adjustPrice();
      this.itemInfo = new ListItemWindow();
      this.unused__3a1_ = new Popup(20, GlobalStatus.var_eb6 / 3, GlobalStatus.var_e75 - 40);
      this.state__ = 0;
      this.freeSlotInfoPopup = new Popup(10, GlobalStatus.var_eb6 / 3, GlobalStatus.var_e75 - 20);
      this.tabsWidth_unused_ = GlobalStatus.var_e75 - 10;
      this.tabWidths_unused_ = new int[3];
      this.tabWidths_unused_[0] = this.tabsWidth_unused_ / 3 - 2;
      this.tabWidths_unused_[1] = this.tabWidths_unused_[0];
      this.tabWidths_unused_[2] = this.tabsWidth_unused_ - this.tabWidths_unused_[0] - this.tabWidths_unused_[1] - 4;
      this.list.setCurrentTab(initTab);
      System.gc();
      this.loaded = true;
   }

   public final int getCurrentTab() {
      return this.list.getCurrentTab();
   }

   public final boolean inRoot() {
      return this.state__ == 0 && !this.list.browsingInterrupted_();
   }

   public final ListItem getSelectedItem() {
      return this.selectedItem;
   }

   public final boolean handleKeyPressed(int var1) {
      this.selectedItem = (ListItem)((ListItem)this.list.getSelectedItem());
      if(var1 == 256) {
         if(this.freeSlotInfoPopupOpen && this.timeOpen > 200L) {
            this.freeSlotInfoPopupOpen = false;
            return true;
         }

         if(this.state__ == 4) {
            this.state__ = 0;
         } else if(this.state__ == 0) {
            if(this.list.getCurrentTab() == 2) {
               this.openedBluePrint = this.list.getSelectedEntry();
            }

            this.list.selectItem();
         } else if(this.state__ != 1) {
            if(this.state__ == 2) {
               this.state__ = 0;
            } else if(this.state__ == 3) {
               this.state__ = 0;
               this.list.sub_50(Status.getShip());
            }
         }
      }

      if(this.freeSlotInfoPopupOpen) {
         return true;
      } else {
         if(var1 == 2 && this.state__ == 0) {
            this.list.scrollUp();
         }

         if(var1 == 64 && this.state__ == 0) {
            this.list.scrollDown();
         }

         if(var1 == 4 && (this.state__ == 4 || this.state__ == 0)) {
            this.list.leftAction();
         }

         if(var1 == 32 && (this.state__ == 4 || this.state__ == 0)) {
            this.list.rightAction();
         }

         if(var1 == 8192 || Layout.dialogueButtonBack.getStandartButtonPressed()) { // "назад" в ангаре
			Layout.dialogueButtonBack.standartButtonActive = false;
            if(this.state__ == 4) {
               initTab = this.list.getCurrentTab();
               return false;
            }

            if(this.state__ == 0 && !this.list.popupOpen) {
               if(this.list.isTrading()) {
                  this.list.cancelTransaction(false);
                  return true;
               }

               if(this.list.getCurrentTab() == 3) {
                  this.list.setCurrentTab(0);
                  return true;
               }

               if(this.list.getCurrentTab() != 4) {
                  if(this.list.browsingInterrupted_()) {
                     return true;
                  }

                  this.itemInfo.updateCamera_(false);
                  return false;
               }

               if(!this.list.browsingInterrupted_()) {
                  this.list.setCurrentTab(2);

                  for(var1 = 0; var1 < this.openedBluePrint - 1; ++var1) {
                     this.list.scrollDown();
                  }

                  this.list.draw();
               }

               return true;
            }

            if(this.state__ == 1) {
               this.state__ = 0;
            } else if(this.state__ == 5) {
               this.state__ = 4;
            }
         }

         if(var1 == 16384 || Layout.dialogueButtonNext.getStandartButtonPressed()) {
			Layout.dialogueButtonNext.standartButtonActive = false;
            if(this.state__ == 4) {
               this.state__ = 5;
            } else if(this.state__ == 0 && this.selectedItem != null) {
               if(this.selectedItem.isItem() || this.selectedItem.isShip() || this.selectedItem.isBluePrint() || this.selectedItem.isPendingProduct()) {
                  this.itemInfo.setup((ListItem)((ListItem)this.list.getSelectedItem()), Globals.itemsImage, this.itemTypesImage, this.list.var_67, this.shipsColorImage, true);
                  this.state__ = 1;
               }

               if(this.selectedItem != null) {
                  if(this.selectedItem.isMedal__()) {
                     Popup var10000 = this.freeSlotInfoPopup;
                     String var2 = GlobalStatus.gameText.getText(82);
                     var10000.set(var2, false);
                     this.freeSlotInfoPopupOpen = true;
                  } else {
                     this.itemInfo.updateCamera_(this.selectedItem.isShip());
                  }
               }
            }
         }

         return true;
      }
   }

   public final boolean draw(int var1, int var2) {
      if(!this.loaded) {
         return true;
      } else {
         this.frameTime = var2;
         this.timeOpen += (long)var2;
         if(this.state__ == 1 && this.itemInfo.shows3DShip()) {
            this.itemInfo.updateRotation(var1, var2);
         }

         if(this.state__ == 0) {
            this.list.draw();
         }

         if(this.state__ == 1) {
            if(this.itemInfo.shows3DShip()) {
               this.itemInfo.render();
            }

            this.itemInfo.draw();
         } else if(this.state__ == 2) {
            this.list.draw();
        //    this.list.drawFrame();
            this.unused__3a1_.draw();
         }

         label152: {
            String var10001;
            String var10000;
            if(!this.freeSlotInfoPopupOpen && this.state__ != 2) {
               label153: {
                  if(this.state__ == 1 && this.list.getCurrentTab() == 0 && !this.selectedItem.isShip()) {
                     var10000 = "";
                  } else {
                     if(this.state__ == 0 && (this.list.getSelectedItem() == null || !((ListItem)this.list.getSelectedItem()).isSelectable)) {
                        Layout.drawFooter("", this.state__ == 0 && this.list.isTrading() && !this.list.popupOpen?GlobalStatus.gameText.getText(246):GlobalStatus.gameText.getText(65));
                        break label152;
                     }

                     var10000 = this.state__ == 1?"":(this.list.getCurrentTab() == 3 && !((ListItem)this.list.getSelectedItem()).isItem() && !((ListItem)this.list.getSelectedItem()).isShip()?"":GlobalStatus.gameText.getText(212));
                     if(this.state__ == 0 && this.list.isTrading() && !this.list.popupOpen) {
                        var10001 = GlobalStatus.gameText.getText(246);
                        break label153;
                     }
                  }

                  var10001 = GlobalStatus.gameText.getText(65);
               }
            } else {
               var10000 = "";
               var10001 = "";
            }

            Layout.drawFooter(var10000, var10001);
         }
		 
		 this.list.drawFrame();

         if(this.freeSlotInfoPopupOpen) {
            this.freeSlotInfoPopup.draw();
         }

         this.selectedItem = (ListItem)((ListItem)this.list.getSelectedItem());
         if(this.freeSlotInfoPopupOpen) {
            return true;
         } else {
            if(this.state__ == 0 && this.list.browsingInterrupted_()) {
               if((var1 & 4) == 0) {
                  this.speedSellTick = 0;
               } else {
                  this.speedSellTick += var2;
               }

               if((var1 & 32) == 0) {
                  this.speedBuyTick = 0;
               } else {
                  this.speedBuyTick += var2;
               }

               if(this.speedSellTick > 500) {
                  this.list.skipSell();
                  this.speedSellTick = 0;
               }

               if(this.speedBuyTick > 500) {
                  this.list.skipBuy();
                  this.speedBuyTick = 0;
               }
            }

            if((var1 & 2) != 0 && this.state__ == 1) {
               this.itemInfo.scrollDown(var2);
            }

            if((var1 & 64) != 0 && this.state__ == 1) {
               this.itemInfo.scrollUp(var2);
            }

            return true;
         }
      }
   }

}
