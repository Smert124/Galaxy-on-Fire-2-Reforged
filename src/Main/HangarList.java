package Main;

import javax.microedition.lcdui.Image;
import HardEngine.*;

import AE.AEResourceManager;
import AE.GlobalStatus;
import AE.PaintCanvas.Font;
import AE.PaintCanvas.ImageFactory;
import GoF2.BluePrint;
import GoF2.Globals;
import GoF2.Item;
import GoF2.Layout;
import GoF2.ListItem;
import GoF2.Popup;
import GoF2.ProducedGood;
import GoF2.Ship;
import GoF2.Status;

public class HangarList extends TabbedWindow_ {
	
	public Image var_67;
	protected String unused_2_;
	private Image var_10a;
	private boolean trading_;
	public boolean popupOpen;
	private Popup popup;
	private int[] stationItemTypeCounts;
	private int shipLoad;
	private Item[] stationItems;
	private BluePrint bluePrint;
	private int mountingSlot;
	private int preMountingScrollPos;
	private boolean isIngredientShippingPopup;
	private boolean isShipBuyPopup;
	private int amountToPutInBluePrint;
	private int lastStationAmount;
	private int lastOnShipAmount;
	private int lastBluePrintAmount;
	private int credits;
	private int lastShipLoad;
	private boolean var_631;
	private Image[] ships1 = new Image[GlobalStatus.max_ships];
	private Image[] ships2 = new Image[GlobalStatus.max_ships];
	public static AEButtonManager[] tabButton;
	private Image itemTypesImage;
	private Image shipsColorImage;
	private Image itemTypesSelImage;


   public HangarList(String[] var1, String var2) {
      this(0, 0, GlobalStatus.var_e75, GlobalStatus.var_eb6, var1, var2);
   }

   private HangarList(int var1, int var2, int var3, int var4, String[] var5, String var6) {
      super(0, 0, var3, var4, var5, var6);
      this.var_631 = false;
      this.trading_ = false;
      this.popup = new Popup();
	  
	  this.tabButton = new AEButtonManager[3];
	  for(int count = 0; count < 3; ++count) {
		  this.tabButton[count] = new AEButtonManager();
	  }
	  
	  this.itemTypesImage = AEResourceManager.getImage(135);
	  this.shipsColorImage = AEResourceManager.getImage(136);
	  this.itemTypesSelImage = AEResourceManager.getImage(137);
	  
   }

   public final void OnRelease() {
      this.var_67 = null;
      this.stationItems = null;
	  this.tabButton = null;
   }

   public final void sub_50(Ship var1) {
      String var2 = null;
	  ListItem[] var9 = new ListItem[var1.getEquipment().length + 2 + var1.getSlotTypes()];
      byte var3 = 0;
      int var8 = var3 + 1;
      var9[0] = new ListItem(GlobalStatus.gameText.getText(77));
      ++var8;
      var9[1] = new ListItem(var1);

      for(int var4 = 0; var4 < 4; ++var4) {
         if(var1.getSlots(var4) > 0) {
            var9[var8++] = new ListItem(GlobalStatus.gameText.getText(var4 == 0?123:(var4 == 1?124:(var4 == 2?125:127))), var4);
            Item[] var5 = var1.getEquipment(var4);

            for(int var6 = 0; var6 < var5.length; var9[var8 - 1].inTabIndex = var6++) {
               if(var5[var6] != null) {
                  var9[var8++] = new ListItem(var5[var6]);
               } else {
                  var9[var8++] = new ListItem(var4);
               }
            }
         }
      }

      this.perTabEntries[0] = var9;
      if(this.perTabEntries[0] == null) {
         this.listsLengths[0] = 0;
      } else if(this.perTabEntries[0].length == 0) {
         this.perTabEntries[0] = null;
         this.listsLengths[0] = 0;
      } else {
         this.listsLengths[0] = this.perTabEntries[0].length;
      }

      this.displayedEntriesCount = this.perTabEntries[0].length;
      this.listsLengths[0] = this.displayedEntriesCount;
      this.updateScroll();
   }

   public final void initBlueprintTab(BluePrint[] var1) {
      if(var1 != null) {
         int var2 = 1;

         int var3;
         for(var3 = 0; var3 < var1.length; ++var3) {
            if(var1[var3].unlocked) {
               ++var2;
            }
         }

         boolean var4 = true;
         ProducedGood[] var5;
         if((var5 = Status.getWaitingGoods()) != null) {
            for(int var6 = 0; var6 < var5.length; ++var6) {
               if(var5[var6] != null) {
                  ++var2;
               }
            }

            if(var2 > var2) {
               ++var2;
               var4 = false;
            }
         }

         ListItem[] var8;
         (var8 = new ListItem[var2])[0] = new ListItem(GlobalStatus.gameText.getText(131));
         var2 = 1;

         for(var3 = 0; var3 < var1.length; ++var3) {
            if(var1[var3].unlocked) {
               var8[var2++] = new ListItem(var1[var3]);
            }
         }

         if(!var4) {
            var8[var2++] = new ListItem(GlobalStatus.gameText.getText(132));

            for(var3 = 0; var3 < var5.length; ++var3) {
               if(var5[var3] != null) {
                  var8[var2++] = new ListItem(var5[var3]);
               }
            }
         }

         super.setEntries(2, var8);
         this.displayedEntriesCount = this.perTabEntries[2].length;
         this.listsLengths[2] = this.displayedEntriesCount;
         this.updateScroll();
      }
   }

   public final void initShopTab(Item[] var1) {
      this.stationItems = var1;
      if(var1 != null) {
         this.stationItemTypeCounts = new int[5];

         int var2;
         for(var2 = 0; var2 < var1.length; ++var2) {
            ++this.stationItemTypeCounts[var1[var2].getType()];
         }

         var2 = 0;

         int var3;
         for(var3 = 0; var3 < this.stationItemTypeCounts.length; ++var3) {
            if(this.stationItemTypeCounts[var3] > 0) {
               ++var2;
            }
         }

         Ship[] var6;
         if((var6 = Status.getStation().sub_2e3()) != null) {
            var2 += 1 + var6.length;
         }

         ListItem[] var7 = new ListItem[var1.length + var2];
         int var4 = 0;
         int var5;
         if(var6 != null) {
            ++var4;
            var7[0] = new ListItem(GlobalStatus.gameText.getText(68), -1);

            for(var5 = 0; var5 < var6.length; ++var5) {
               var7[var4++] = new ListItem(var6[var5]);
            }
         }

         for(var5 = 0; var5 < 5; ++var5) {
            if(this.stationItemTypeCounts[var5] > 0) {
               var7[var4++] = new ListItem(GlobalStatus.gameText.getText(var5 == 0?123:(var5 == 1?124:(var5 == 2?125:(var5 == 3?127:128)))), var5);

               for(var3 = 0; var3 < var1.length; ++var3) {
                  if(var1[var3].getType() == var5) {
                     var7[var4++] = new ListItem(var1[var3]);
                  }
               }
            }
         }

         super.setEntries(1, var7);
         this.shipLoad = Status.getShip().getCurrentLoad();
         this.displayedEntriesCount = this.perTabEntries[1].length;
         this.listsLengths[1] = this.displayedEntriesCount;
         this.updateScroll();
      }
   }

   private void initMountingWindow(ListItem var1) {
      this.perTabEntries[3] = null;
      this.listsLengths[3] = 0;
      boolean var2 = var1.isShip();
      int var3 = var1.medalTier;
      if(!var2 && !var1.isMedal__()) {
         var3 = var1.item.getType();
      }

      int var4 = 0;
      int var6;
      if(var2) {
         Ship[] var5;
         if((var5 = Status.getStation().sub_2e3()) != null) {
            var4 = var5.length;
         }
      } else {
         Item[] var10;
         if((var10 = Status.getShip().getCargo()) != null) {
            for(var6 = 0; var6 < var10.length; ++var6) {
               if(var10[var6].getType() == var3) {
                  ++var4;
               }
            }
         }
      }

      int var12 = !var2 && !var1.isMedal__()?2:0;
      var6 = 2 + (var4 == 0?2:1) + (var12 == 0?0:1);
      ListItem[] var11;
      (var11 = new ListItem[var4 + var6 + var12])[0] = new ListItem(GlobalStatus.gameText.getText(135));
      var11[1] = new ListItem(var1);
      var6 = 2;
      if(!var2 && !var1.isMedal__()) {
         ++var6;
         var11[2] = new ListItem(GlobalStatus.gameText.getText(136));
         ++var6;
         var11[3] = new ListItem(GlobalStatus.gameText.getText(137), true, 0);
         ++var6;
         var11[4] = new ListItem(GlobalStatus.gameText.getText(138), true, 1);
      }

      var11[var6++] = new ListItem(var2?GlobalStatus.gameText.getText(140):GlobalStatus.gameText.getText(139));
      if(var4 > 0) {
         int var9;
         if(var2) {
            Ship[] var7 = Status.getStation().sub_2e3();

            for(var9 = 0; var9 < var7.length; ++var9) {
               var11[var6++] = new ListItem(var7[var9]);
            }
         } else {
            Item[] var8 = Status.getShip().getCargo();

            for(var9 = 0; var9 < var8.length; ++var9) {
               if(var8[var9].getType() == var3) {
                  var11[var6++] = new ListItem(var8[var9]);
               }
            }
         }
      } else {
         var11[var6] = new ListItem(GlobalStatus.gameText.getText(141), false, -1);
      }

      super.setEntries(3, var11);
      this.displayedEntriesCount = this.perTabEntries[3].length;
      this.listsLengths[3] = this.displayedEntriesCount;
      this.updateScroll();
   }

   private void fillIngredientsList(BluePrint var1) {
      this.bluePrint = var1;
      this.perTabEntries[4] = null;
      this.listsLengths[4] = 0;
      boolean var2 = false;
      Item[] var3 = Status.getShip().getCargo();
      Item[] var4 = Globals.getItems();
      int[] var9;
      ListItem[] var10 = new ListItem[(var9 = var1.getIngredientList()).length + 1];
      int var5 = 0;

      for(int var6 = 0; var6 < var9.length; ++var6) {
         boolean var7 = false;
         if(var3 != null) {
            for(int var8 = 0; var8 < var3.length; ++var8) {
               if(var3[var8].getIndex() == var9[var6]) {
                  var10[var5++] = new ListItem(var3[var8]);
                  var3[var8].setBlueprintAmount(0);
                  var7 = true;
                  break;
               }
            }
         }

         if(!var7) {
            var10[var5++] = new ListItem(var4[var9[var6]]);
            var4[var9[var6]].setBlueprintAmount(0);
         }
      }

      super.setEntries(4, var10);
      this.displayedEntriesCount = this.perTabEntries[4].length;
      this.listsLengths[4] = this.displayedEntriesCount;
      this.updateScroll();
   }

   public final boolean browsingInterrupted_() {
      return this.trading_ || this.popupOpen;
   }

   public final boolean isTrading() {
      return this.trading_;
   }

   public final void cancelTransaction(boolean var1) {
      if(this.trading_) {
         Item var2;
         (var2 = ((ListItem)this.perTabEntries[this.selectedTab][this.selectedEntry]).item).setStationAmount(this.lastStationAmount);
         var2.setAmount(this.lastOnShipAmount);
         var2.setBlueprintAmount(this.lastBluePrintAmount);
         Status.setCredits(this.credits);
         this.shipLoad = this.lastShipLoad;
      }

      this.lastStationAmount = 0;
      this.lastOnShipAmount = 0;
      this.lastBluePrintAmount = 0;
      this.trading_ = false;
   }

   public final void leftAction() {
      if(this.trading_ && !this.popupOpen) {
         if(this.selectedTab == 1) {
            int var1;
            if((var1 = ((ListItem)this.perTabEntries[this.selectedTab][this.selectedEntry]).item.transaction(false)) > 0) {
               --this.shipLoad;
            }

            Status.changeCredits(var1);
         } else if(this.selectedTab == 4) {
            int var2;
            Item var4;
            if((var2 = (var4 = ((ListItem)this.perTabEntries[this.selectedTab][this.selectedEntry]).item).transactionBlueprint(true)) > 0) {
               --this.shipLoad;
            } else if(var2 != 0) {
               --this.amountToPutInBluePrint;
            }

            Item[] var5;
            if((var5 = Status.getShip().getCargo()) != null) {
               for(int var3 = 0; var3 < var5.length; ++var3) {
                  if(var5[var3].getIndex() == var4.getIndex()) {
                     var5[var3].setAmount(var4.getAmount());
                     var5[var3].setBlueprintAmount(var4.getBlueprintAmount());
                  }
               }
            }
         }

         this.updateScroll();
      } else if(!this.popupOpen && this.selectedTab < 3) {
         super.leftAction();
      } else {
         if(this.popupOpen) {
            this.popup.left();
         }

      }
   }

   public final void rightAction() {
      if(this.trading_ && !this.popupOpen) {
         if(this.selectedTab == 1) {
            int var1;
            if((var1 = ((ListItem)this.perTabEntries[this.selectedTab][this.selectedEntry]).item.transaction(true)) < 0) {
               ++this.shipLoad;
            }

            Status.changeCredits(var1);
         } else if(this.selectedTab == 4) {
            Item var4;
            if((var4 = ((ListItem)this.perTabEntries[this.selectedTab][this.selectedEntry]).item).getBlueprintAmount() < this.bluePrint.getRemainingAmount(var4.getIndex())) {
               int var2;
               if((var2 = var4.transactionBlueprint(false)) < 0) {
                  ++this.shipLoad;
               } else if(var2 != 0) {
                  ++this.amountToPutInBluePrint;
               }
            }

            Item[] var5;
            if((var5 = Status.getShip().getCargo()) != null) {
               for(int var3 = 0; var3 < var5.length; ++var3) {
                  if(var5[var3].getIndex() == var4.getIndex()) {
                     var5[var3].setAmount(var4.getAmount());
                     var5[var3].setBlueprintAmount(var4.getBlueprintAmount());
                  }
               }
            }
         }

         this.updateScroll();
      } else {
         if(!this.popupOpen) {
            if(this.selectedTab < 2) {
               super.rightAction();
               return;
            }
         } else {
            this.popup.right();
         }

      }
   }

   public final void skipSell() {
      if(this.trading_) {
         for(int var1 = 0; var1 < 10; ++var1) {
            this.leftAction();
         }
      }

   }

   public final void skipBuy() {
      if(this.trading_) {
         for(int var1 = 0; var1 < 10; ++var1) {
            this.rightAction();
         }
      }

   }

   public final void scrollUp() {
      if(!this.trading_ && !this.popupOpen) {
         boolean var1 = false;
         int var2 = this.selectedEntry;

         for(int var3 = this.selectedEntry; var3 > 0; --var3) {
            if(((ListItem)((ListItem)this.perTabEntries[this.selectedTab][var3 - 1])).isSelectable) {
               var1 = true;
               break;
            }

            --var2;
         }

         if(this.selectedEntry == 0 || this.selectedEntry == 1 && !((ListItem)((ListItem)this.perTabEntries[this.selectedTab][0])).isSelectable) {
            var2 = this.listsLengths[this.selectedTab] - 1;
            this.selectedEntry = this.listsLengths[this.selectedTab] - 1;
            this.scrollPos = this.listsLengths[this.selectedTab] - this.displayedEntriesCount - 1;
         }

         if(var1) {
            super.scrollUp();
         }

         if(var2 > 0 && !((ListItem)((ListItem)this.perTabEntries[this.selectedTab][this.selectedEntry])).isSelectable) {
            this.scrollUp();
         }

         this.updateScroll();
      }

   }

   public final void scrollDown() {
      if(!this.trading_ && !this.popupOpen) {
         boolean var1 = false;
         int var2 = this.selectedEntry;

         for(int var3 = this.selectedEntry; var3 < this.listsLengths[this.selectedTab] - 1; ++var3) {
            if(((ListItem)((ListItem)this.perTabEntries[this.selectedTab][var3 + 1])).isSelectable) {
               var1 = true;
               break;
            }

            ++var2;
         }

         if(this.selectedEntry == this.listsLengths[this.selectedTab] - 1 || this.selectedEntry == this.listsLengths[this.selectedTab] - 3 && !((ListItem)((ListItem)this.perTabEntries[this.selectedTab][this.selectedEntry + 1])).isSelectable && !((ListItem)((ListItem)this.perTabEntries[this.selectedTab][this.selectedEntry + 2])).isSelectable) {
            var2 = 0;
            this.selectedEntry = 0;
            this.scrollPos = 0;
         }

         if(var1) {
            super.scrollDown();
         }

         if(var2 < this.listsLengths[this.selectedTab] - 1 && !((ListItem)((ListItem)this.perTabEntries[this.selectedTab][this.selectedEntry])).isSelectable) {
            this.scrollDown();
         }

         this.updateScroll();
      }

   }

   public final int selectItem() {
      ListItem var1;
      int var3;
      int var6;
      String var19;
      if(this.popupOpen) {
         if(this.var_631) {
            if(this.popup.getChoice()) {
               ModStation.var_80 = true;
               this.var_631 = false;
            }

            this.popupOpen = false;
            return 0;
         }

         this.popupOpen = false;
         if(this.selectedTab == 1 && !this.isShipBuyPopup) {
            return 0;
         }

         if(this.trading_) {
            this.trading_ = this.popup.getChoice();
         }

         if(!this.isIngredientShippingPopup) {
            if(!this.isShipBuyPopup) {
               return 0;
            }

            this.isShipBuyPopup = false;
            this.popupOpen = false;
            if(this.popup.getChoice()) {
               var1 = (ListItem)this.perTabEntries[this.selectedTab][this.selectedEntry];
               Status.changeCredits(Status.getShip().getPrice() - var1.ship.getPrice());
               Ship var14;
               Item[] var16 = (var14 = Status.getShip()).getEquipment();
               Item[] var20 = var14.getCargo();
               Status.setShip(Globals.getShips()[var1.ship.getIndex()].cloneBase());
               Status.getShip().setRace(var1.ship.sub_3e());
               Status.getShip().adjustPrice();
               Status.getShip().setCargo(var20);
               if(var16 != null) {
                  for(int var21 = 0; var21 < var16.length; ++var21) {
                     if(var16[var21] != null) {
                        Status.getShip().addCargo(var16[var21]);
                     }
                  }
               }

               Ship[] var23 = Status.getStation().sub_2e3();

               for(var6 = 0; var6 < var23.length; ++var6) {
                  if(var23[var6].getIndex() == var1.ship.getIndex()) {
                     var23[var6] = Globals.getShips()[var14.getIndex()].cloneBase();
                     var23[var6].setRace(var14.sub_3e());
                     break;
                  }
               }

               this.sub_50(Status.getShip());
               this.initShopTab(Item.combineItems(Status.getShip().getCargo(), Status.getStation().sub_360()));
               this.setCurrentTab(0);
               var19 = Status.replaceTokens(new String(GlobalStatus.gameText.getText(143)), GlobalStatus.gameText.getText(var1.ship.getIndex()), "#N");
               this.popup.setAsWarning(var19);
               this.popupOpen = true;
               ((ModStation)GlobalStatus.scenes[1]).getCutScene().replacePlayerShip(Status.getShip().getIndex(), Status.getShip().sub_3e());
            }

            return 0;
         }

         this.isIngredientShippingPopup = false;
         if(this.popup.getChoice() && !this.trading_) {
            Status.changeCredits(-((ListItem)this.perTabEntries[this.selectedTab][this.selectedEntry]).item.getBlueprintAmount() * 10);
            this.trading_ = true;
            int var13 = this.amountToPutInBluePrint;

            for(var3 = 0; var3 < var13; ++var3) {
               this.leftAction();
            }

            this.trading_ = false;
            return 0;
         }

         this.amountToPutInBluePrint = 0;
         this.trading_ = true;
      }

      if(this.perTabEntries[this.selectedTab] != null && this.perTabEntries[this.selectedTab][this.selectedEntry] != null) {
         var1 = (ListItem)this.perTabEntries[this.selectedTab][this.selectedEntry];
         String var7;
         if(this.selectedTab == 4 && this.trading_ && this.amountToPutInBluePrint > 0 && !this.popupOpen && !this.bluePrint.isStarted() && this.bluePrint.getProductionStationId() != Status.getStation().getId()) {
            var7 = Status.replaceTokens(Status.replaceTokens(new String(GlobalStatus.gameText.getText(142)), this.bluePrint.getProductionStationName(), "#S"), Layout.formatCredits(var1.item.getBlueprintAmount() * 10), "#C");
            this.popup.set(var7, true);
            this.popupOpen = true;
            this.isIngredientShippingPopup = true;
            return 0;
         } else {
            if(this.selectedTab == 0) {
               this.mountingSlot = this.selectedEntry;
               this.preMountingScrollPos = this.scrollPos;
               this.initMountingWindow(var1);
               this.setCurrentTab(3);
               if(!GlobalStatus.itemMounting2HelpShown && var1.isMedal__()) {
                  this.popup.setAsWarning(GlobalStatus.gameText.getText(301));
                  GlobalStatus.itemMounting2HelpShown = true;
                  this.popupOpen = true;
               }
            } else {
               Item var5;
               if(this.selectedTab == 1) {
                  if(var1.isShip()) {
                     if(!GlobalStatus.var_1083 && var1.ship.getSlots(3) > 4) {
                        var7 = Class_1017.sub_2b(36) + "\n\n" + Class_1017.sub_2b(38);
                        this.popup.set(var7, true);
                        this.popupOpen = true;
                        this.var_631 = true;
                        return 0;
                     }

                     if(var1.ship.getPrice() > Status.getCredits() + Status.getShip().getPrice()) {
                        this.popup.setAsWarning(Status.replaceTokens(GlobalStatus.gameText.getText(83), Layout.formatCredits(var1.getPrice() - Status.getCredits() - Status.getShip().getPrice()), "#C"));
						//System.out.println("SHIP PRICE IN SHOP: " + var1.var_234.getShipPrice() + "; CURRENT MONEY: " + Status.getCredits() + "; PLAYER SHIP PRICE: " + Status.getShip().getPrice());
                        this.popupOpen = true;
                        return 0;
                     }

                     if(Status.passengerCount > 0) {
                        this.popup.setAsWarning(GlobalStatus.gameText.getText(161));
                        this.popupOpen = true;
                        return 0;
                     }

                     this.isShipBuyPopup = true;
                     this.popup.set(GlobalStatus.gameText.getText(144), true);
                     this.popupOpen = true;
                     return 0;
                  }

                  if(var1.item.setUnsaleable()) {
                     this.popup.setAsWarning(GlobalStatus.gameText.getText(160));
                     this.popupOpen = true;
                  } else if(var1.item.getAmount() == 0 && Status.getCredits() < var1.item.getSinglePrice()) {
                     this.popup.setAsWarning(Status.replaceTokens(GlobalStatus.gameText.getText(83), Layout.formatCredits(var1.item.getSinglePrice() - Status.getCredits()), "#C"));
                     this.popupOpen = true;
                  } else {
                     if(!GlobalStatus.var_1083 && (var1.item.getTecLevel() >= 4 || var1.item.getSort() == 13 || var1.item.getSort() == 14 || var1.item.getSort() == 7 || var1.item.getSort() == 6 || var1.item.getSort() == 15)) {
                        var7 = Class_1017.sub_2b(35) + "\n\n" + Class_1017.sub_2b(38);
                        this.popup.set(var7, true);
                        this.popupOpen = true;
                        this.var_631 = true;
                        return 0;
                     }

                     this.trading_ = !this.trading_;
                     if(!this.trading_) {
                        if(var1.isItem() && var1.item.getType() != 4 && !GlobalStatus.itemMountingHelpShown) {
                           this.popup.setAsWarning(GlobalStatus.gameText.getText(303));
                           GlobalStatus.itemMountingHelpShown = true;
                           this.popupOpen = true;
                        }

                        if(var1.getIndex() >= 132 && var1.getIndex() < 154) {
                           Status.drinkTypesPossesed[var1.getIndex() - 132] = true;
                        }

                        Item[] var2;
                        if(var1.isItem() && var1.item.getType() == 1 && var1.item.getAmount() > 0 && (var2 = Status.getShip().getEquipment(1)) != null) {
                           for(var3 = 0; var3 < var2.length; ++var3) {
                              Item var4;
                              if((var4 = var2[var3]) != null && var4.getIndex() == var1.item.getIndex()) {
                                 var5 = var1.item.makeItem(var1.item.getAmount() + var4.getAmount());
                                 if(this.stationItems != null) {
                                    for(var6 = 0; var6 < this.stationItems.length; ++var6) {
                                       if(this.stationItems[var6].getIndex() == var5.getIndex()) {
                                          this.stationItems[var6].setAmount(0);
                                       }
                                    }
                                 }

                                 Status.getShip().setEquipment(var5, var3);
                                 Status.getShip().removeCargo(var5.getIndex(), var5.getAmount());
                                 this.sub_50(Status.getShip());
                                 var19 = Status.replaceTokens(new String(GlobalStatus.gameText.getText(87)), GlobalStatus.gameText.getNamedParameterItems(var5.getIndex()), "#N");
                                 this.popup.setAsWarning(var19);
                                 this.popupOpen = true;
                                 break;
                              }
                           }
                        }

                        Status.getShip().setCargo(Item.extractItems(this.stationItems, true));
                        Status.getStation().sub_37b(Item.extractItems(this.stationItems, false));
                        this.initShopTab(Item.combineItems(Status.getShip().getCargo(), Status.getStation().sub_360()));
                        this.updateScroll();
                     } else {
                        if(!GlobalStatus.buyingHelpShown) {
                           this.popup.setAsWarning(GlobalStatus.gameText.getText(302));
                           GlobalStatus.buyingHelpShown = true;
                           this.popupOpen = true;
                        }

                        this.lastStationAmount = var1.item.getStationAmount();
                        this.lastOnShipAmount = var1.item.getAmount();
                        this.credits = Status.getCredits();
                        this.lastShipLoad = this.shipLoad;
                     }
                  }
               } else if(this.selectedTab == 2 && !var1.isPendingProduct()) {
                  this.fillIngredientsList(var1.bluePrint);
                  this.setCurrentTab(4);
               } else {
                  boolean var10;
                  String var12;
                  if(this.selectedTab == 3) {
                     if(this.selectedEntry > 2) {
                        Item var8;
                        if((var8 = ((ListItem)this.perTabEntries[this.selectedTab][1]).item) != null && var8.setUnsaleable()) {
                           this.popup.setAsWarning(GlobalStatus.gameText.getText(160));
                           this.popupOpen = true;
                           return 0;
                        }

                        if(var8 != null && var8.getSort() == 20 && Status.passengerCount > 0) {
                           this.popup.setAsWarning(GlobalStatus.gameText.getText(160));
                           this.popupOpen = true;
                           return 0;
                        }

                        if(var1.isShip()) {
                           if(var1.ship.getPrice() > Status.getCredits() + Status.getShip().getPrice()) {
                              this.popup.setAsWarning(Status.replaceTokens(GlobalStatus.gameText.getText(83), Layout.formatCredits(var1.getPrice() - Status.getCredits() - Status.getShip().getPrice()), "#C"));
                              this.popupOpen = true;
                              return 0;
                           }

                           if(Status.passengerCount > 0) {
                              this.popup.setAsWarning(GlobalStatus.gameText.getText(161));
                              this.popupOpen = true;
                              return 0;
                           }

                           this.isShipBuyPopup = true;
                           this.popup.set(GlobalStatus.gameText.getText(144), true);
                           this.popupOpen = true;
                           return 0;
                        }

                        if(var1.isNonFancyHeader()) {
                           var10 = var8.getType() == 1;
                           if(var1.isSellButton()) {
                              var12 = Status.replaceTokens(new String(GlobalStatus.gameText.getText(88)), GlobalStatus.gameText.getNamedParameterItems(var8.getIndex()), "#N");
                              this.popup.setAsWarning(var12);
                              var5 = var8.makeItem(var10?var8.getAmount():1);
                              Status.getShip().addCargo(var5);
                              Status.getShip().freeSlot(var8);
                              this.sub_50(Status.getShip());
                              this.initShopTab(Item.combineItems(Status.getShip().getCargo(), Status.getStation().sub_360()));
                              this.setCurrentTab(0);
                           } else if(var1.isMoveToCargoButton()) {
                              var12 = Status.replaceTokens(new String(GlobalStatus.gameText.getText(86)), GlobalStatus.gameText.getNamedParameterItems(var8.getIndex()), "#N");
                              this.popup.setAsWarning(var12);
                              var5 = var8.makeItem(var10?var8.getAmount():1);
                              Status.getStation().addItem(var5);
                              Status.changeCredits(var8.getTotalPrice());
                              Status.getShip().freeSlot(var8);
                              this.sub_50(Status.getShip());
                              this.initShopTab(Item.combineItems(Status.getShip().getCargo(), Status.getStation().sub_360()));
                              this.setCurrentTab(0);
                           }

                           this.popupOpen = true;
                        } else {
                           ListItem var11 = (ListItem)this.perTabEntries[this.selectedTab][1];
                           if(Status.getShip().getFirstEquipmentOfSort(var1.item.getSort()) != null && !var1.item.canBeInstalledMultipleTimes() && (!var11.isItem() || var11.item.getSort() != var1.item.getSort())) {
                              var12 = new String(GlobalStatus.gameText.getText(164));
                              this.popup.setAsWarning(var12);
                              this.popupOpen = true;
                              return 0;
                           }

                           boolean var15;
                           String var17;
                           Item var22;
                           if(var11.isMedal__()) {
                              var15 = var1.item.getType() == 1;
                              var17 = Status.replaceTokens(new String(GlobalStatus.gameText.getText(87)), GlobalStatus.gameText.getNamedParameterItems(var1.item.getIndex()), "#N");
                              this.popup.setAsWarning(var17);
                              var22 = var1.item.makeItem(var15?var1.item.getAmount():1);
                              Status.getShip().setEquipment(var22, var11.inTabIndex);
                              Status.getShip().removeCargo(var22.getIndex(), var15?var22.getAmount():1);
                              this.sub_50(Status.getShip());
                              this.initShopTab(Item.combineItems(Status.getShip().getCargo(), Status.getStation().sub_360()));
                              this.setCurrentTab(0);
                           } else {
                              var15 = var11.item.getType() == 1;
                              var17 = Status.replaceTokens(new String(GlobalStatus.gameText.getText(87)), GlobalStatus.gameText.getNamedParameterItems(var1.item.getIndex()), "#N");
                              this.popup.setAsWarning(var17);
                              var22 = var11.item.makeItem(var15?var11.item.getAmount():1);
                              Status.getShip().addCargo(var22);
                              Status.getShip().freeSlot(var11.item, var11.inTabIndex);
                              Status.getShip().setEquipment(var1.item.makeItem(var15?var1.item.getAmount():1), var11.inTabIndex);
                              Status.getShip().removeCargo(var1.item.getIndex(), var15?var1.item.getAmount():1);
                              this.sub_50(Status.getShip());
                              this.initShopTab(Item.combineItems(Status.getShip().getCargo(), Status.getStation().sub_360()));
                              this.setCurrentTab(0);
                           }
                        }

                        this.selectedEntry = this.mountingSlot;
                        this.scrollPos = this.preMountingScrollPos;
                     }
                  } else if(this.selectedTab == 4) {
                     if(this.trading_) {
                        boolean var9 = false;
                        var10 = false;
                        this.bluePrint.startProduction(var1.item, var1.item.getBlueprintAmount(), Status.getStation().getId());
                        if(this.bluePrint.isComplete()) {
                           if(this.bluePrint.getProductionStationId() != Status.getStation().getId()) {
                              var12 = Status.replaceTokens(Status.replaceTokens(new String(GlobalStatus.gameText.getText(89)), GlobalStatus.gameText.getNamedParameterItems(this.bluePrint.getIndex()), "#N"), this.bluePrint.getProductionStationName(), "#S");
                              this.popup.setAsWarning(var12);
                              Status.appendProduced(this.bluePrint);
                              this.setCurrentTab(2);
                           } else {
                              var12 = Status.replaceTokens(new String(GlobalStatus.gameText.getText(90)), GlobalStatus.gameText.getNamedParameterItems(this.bluePrint.getIndex()), "#N");
                              this.popup.setAsWarning(var12);
                              var5 = Globals.getItems()[this.bluePrint.getIndex()].makeItem(this.bluePrint.getTonsPerProduction2());
                              Status.getShip().addCargo(var5);
                              this.setCurrentTab(1);
                           }

                           var10 = true;
                           this.bluePrint.finishProduction();
                           var9 = true;
                        }

                        this.trading_ = false;
                        Status.getShip().setCargo(Item.extractItems(Status.getShip().getCargo(), true));
                        this.initShopTab(Item.combineItems(Status.getShip().getCargo(), Status.getStation().sub_360()));
                        this.initBlueprintTab(Status.getBluePrints());
                        this.updateScroll();
                        if(var9) {
                           for(int var18 = 0; var18 < this.perTabEntries[this.selectedTab].length; ++var18) {
                              this.scrollDown();
                              if(!((ListItem)this.perTabEntries[this.selectedTab][this.selectedEntry]).isBluePrint() && ((ListItem)this.perTabEntries[this.selectedTab][this.selectedEntry]).getIndex() == this.bluePrint.getIndex()) {
                                 break;
                              }
                           }
                        }

                        if(var10) {
                           this.popupOpen = true;
                        }

                        return 0;
                     }

                     this.amountToPutInBluePrint = 0;
                     if(this.bluePrint.isStarted() && ((ListItem)this.perTabEntries[this.selectedTab][this.selectedEntry]).item.getAmount() > 0) {
                        this.popup.set(GlobalStatus.gameText.getText(91), true);
                        this.popupOpen = true;
                     }

                     this.lastBluePrintAmount = var1.item.getBlueprintAmount();
                     this.lastOnShipAmount = var1.item.getAmount();
                     this.credits = Status.getCredits();
                     this.lastShipLoad = this.shipLoad;
                     this.trading_ = !this.trading_;
                  }
               }
            }

            return 0;
         }
      } else {
         return 0;
      }
   }

   public final void updateScroll() {
      if(this.perTabEntries.length >= 3) {
         if(this.selectedTab == 1) {
            this.itemListPosY = this.posY + 14 + this.tabHeight + this.rowHeight + 2;
         } else {
            this.itemListPosY = this.posY + 14 + this.tabHeight;
         }
      }

      super.updateScroll();
      if(this.listsLengths[this.selectedTab] > 0 && this.selectedEntry == 0 && !(this.listsLengths[this.selectedTab] == 0?false:((ListItem)((ListItem)this.perTabEntries[this.selectedTab][this.selectedEntry])).isSelectable)) {
         ++this.selectedEntry;
         super.updateScroll();
      }

   }

   public void draw() {
      this.drawBG();
      Layout.drawMenuWindow(this.titleBarText, this.posX - 1, this.posY - 1, this.width + 1, this.height + 1);
    //  SharedVariables.graphics.drawImage(this.var_10a, SharedVariables.var_e75 - 20, SharedVariables.var_eb6 - 30 - 16, 40);
      if(this.selectedTab > 2) {
         this.tabHeight = 1;
      } else {
         this.tabHeight = Font.getFontSpacingY() + 4;
      }

      this.drawTabs();
      this.drawItems();
      this.drawScroll();
      if(this.popupOpen) {
         this.popup.draw();
      }

   }

   public void drawFrame() {
      /* GlobalStatus.graphics.setColor(Layout.uiOuterDownLeftOutlineInnerLabalBgColor);
      GlobalStatus.graphics.fillRect(this.posX + 2, this.posY + this.height - 14, this.width - 4, 12);
      GlobalStatus.graphics.setColor(0);
      GlobalStatus.graphics.drawLine(this.posX + 3, this.posY + this.height - 15, this.width - 3, this.posY + this.height - 15);
      GlobalStatus.graphics.drawLine(this.posX + 3, this.posY + this.height - 1, this.width - 3, this.posY + this.height - 1);
      GlobalStatus.graphics.drawRect(this.posX + 3, this.posY + this.height - 13, this.width - 6, 10);
      GlobalStatus.graphics.setColor(Layout.uiInnerOutlineColor);
      GlobalStatus.graphics.drawRect(this.posX + 2, this.posY + this.height - 14, this.width - 4, 12); */
      boolean bool;
      if((bool = this.shipLoad > Status.getShip().getCargoPlus()) && Layout.quickClockHigh_() || !bool) {
		  Font.drawString(this.shipLoad + "/" + Status.getShip().getCargoPlus() + "t", (GlobalStatus.var_e75 / 2) + (Layout.AENavigationButton[1].standartButtonWidth / 2), GlobalStatus.var_eb6 - (Layout.AENavigationButton[1].standartButtonHeight / 4), bool?4:0, 34);
      }
	  Font.drawString(Layout.formatCredits(Status.getCredits()), Layout.AENavigationButton[1].standartButtonX - (Layout.AENavigationButton[1].standartButtonWidth + 4), GlobalStatus.var_eb6 - (Layout.AENavigationButton[1].standartButtonHeight / 4), 0, 34);
   }

   protected final void drawTabs() {
      if(this.perTabEntries.length < 3) {
         super.drawTabs();
      } else {
         if(this.showTabs) {
            if(this.selectedTab > 2) {
               return;
            }

            /* GlobalStatus.graphics.setColor(0);
            GlobalStatus.graphics.drawLine(this.posX + this.width / 3, this.posY + 14, this.posX + this.width / 3, this.posY + 14 + 14);
            GlobalStatus.graphics.drawLine(this.posX + this.width - this.width / 3 - 1, this.posY + 14, this.posX + this.width - this.width / 3 - 1, this.posY + 14 + 14);
            GlobalStatus.graphics.setColor(Layout.uiInnerOutlineColor);
            GlobalStatus.graphics.drawLine(this.posX + this.width / 3 - 1, this.posY + 14, this.posX + this.width / 3 - 1, this.posY + 14 + 14);
            GlobalStatus.graphics.drawLine(this.posX + this.width / 3 + 1, this.posY + 14, this.posX + this.width / 3 + 1, this.posY + 14 + 14);
            GlobalStatus.graphics.drawLine(this.posX + this.width - this.width / 3 - 2, this.posY + 14, this.posX + this.width - this.width / 3 - 2, this.posY + 14 + 14);
            GlobalStatus.graphics.drawLine(this.posX + this.width - this.width / 3, this.posY + 14, this.posX + this.width - this.width / 3, this.posY + 14 + 14);
            switch(this.selectedTab) {
            case 0:
               GlobalStatus.graphics.drawLine(this.posX + this.width / 3 - 1, this.posY + 14 + 14, this.posX + this.width - 3, this.posY + 14 + 14);
               break;
            case 1:
               GlobalStatus.graphics.drawLine(this.posX + 3, this.posY + 14 + 14, this.posX + this.width / 3 + 1, this.posY + 14 + 14);
               GlobalStatus.graphics.drawLine(this.posX + this.width - this.width / 3 - 2, this.posY + 14 + 14, this.posX + this.width - 3, this.posY + 14 + 14);
               break;
            case 2:
               GlobalStatus.graphics.drawLine(this.posX + 3, this.posY + 14 + 14, this.posX + this.width - this.width / 3, this.posY + 14 + 14);
            }

            if(this.selectedTab != 0) { //  ship
               GlobalStatus.graphics.setColor(0);
               GlobalStatus.graphics.fillRect(this.posX + 3, this.posY + 14 + 1, this.width / 3 - 4, this.posY + 14 - 2);
               GlobalStatus.graphics.setColor(Layout.uiInactiveInnerLabelColor); // цвет заливки вкладки
               GlobalStatus.graphics.fillRect(this.posX + 4, this.posY + 14 + 2, this.width / 3 - 6, this.posY + 14 - 3);
            }

            if(this.selectedTab != 1) { // shop
               GlobalStatus.graphics.setColor(0);
               GlobalStatus.graphics.fillRect(this.posX + this.width / 3 + 2, this.posY + 14 + 1, this.width / 3 - 2, this.posY + 14 - 2);
               GlobalStatus.graphics.setColor(Layout.uiInactiveInnerLabelColor); // цвет заливки вкладки
               GlobalStatus.graphics.fillRect(this.posX + this.width / 3 + 3, this.posY + 14 + 2, this.width / 3 - 4, this.posY + 14 - 3);
            }

            if(this.selectedTab != 2) { // blueprints
               GlobalStatus.graphics.setColor(0);
               GlobalStatus.graphics.fillRect(this.posX + this.width - this.width / 3 + 1, this.posY + 14 + 1, this.width / 3 - 3, this.posY + 14 - 2);
               GlobalStatus.graphics.setColor(Layout.uiInactiveInnerLabelColor); // цвет заливки вкладки
               GlobalStatus.graphics.fillRect(this.posX + this.width - this.width / 3 + 2, this.posY + 14 + 2, this.width / 3 - 5, this.posY + 14 - 3);
            } */

            Layout.sub_449(this.posX + 2, this.posY + 14, this.selectedTab == 0);
            Layout.sub_449(this.posX + this.width / 3 + 1, this.posY + 14, this.selectedTab == 1);
            Layout.sub_449(this.posX + this.width - this.width / 3, this.posY + 14, this.selectedTab == 2);
			
			this.tabButton[0].drawStandartButton(Globals.beveledButtonNormal, Globals.beveledButtonPressed, this.tabButton[1].standartButtonX - this.tabButton[0].standartButtonWidth, this.tabButton[0].standartButtonHeight / 2);
			this.tabButton[1].drawStandartButton(Globals.beveledButtonNormal, Globals.beveledButtonPressed, this.tabButton[2].standartButtonX - this.tabButton[1].standartButtonWidth, this.tabButton[1].standartButtonHeight / 2);
			this.tabButton[2].drawStandartButton(Globals.beveledButtonNormal, Globals.beveledButtonPressed, this.width - (this.tabButton[2].standartButtonWidth), this.tabButton[2].standartButtonHeight / 2);
			
            Font.drawString(this.tabNames[0], tabButton[0].standartButtonX, tabButton[0].standartButtonY - (tabButton[0].standartButtonHeight / 4), this.selectedTab == 0?2:1, 24);
            Font.drawString(this.tabNames[1], tabButton[1].standartButtonX, tabButton[1].standartButtonY - (tabButton[1].standartButtonHeight / 4), this.selectedTab == 1?2:1, 24);
            Font.drawString(this.tabNames[2], tabButton[2].standartButtonX, tabButton[2].standartButtonY - (tabButton[2].standartButtonHeight / 4), this.selectedTab == 2?2:1, 24);
			
			if(this.tabButton[0].getStandartButtonPressed()) {
				this.selectedTab = 0;
				this.selectedEntry = 0;
				this.scrollPos = 0;
				this.updateScroll();
			}
			
			if(this.tabButton[1].getStandartButtonPressed()) {
				this.selectedTab = 1;
				this.selectedEntry = 0;
				this.scrollPos = 0;
				this.updateScroll();
			}
			
			if(this.tabButton[2].getStandartButtonPressed()) {
				this.selectedTab = 2;
				this.selectedEntry = 0;
				this.scrollPos = 0;
				this.updateScroll();
			}
			
         }

      }
   }

   public final void drawItems() {
      if(this.perTabEntries[this.selectedTab] != null) {
         if(this.perTabEntries.length >= 3 && (this.selectedTab == 1 || this.selectedTab == 4)) {
            String var1 = GlobalStatus.gameText.getText(this.selectedTab == 1?40:78);
            String var2 = this.selectedTab == 1?GlobalStatus.gameText.getText(78):GlobalStatus.gameText.getText(129);
            this.itemListPosY = this.posY + 14 + this.tabHeight + this.rowHeight - 1;
            GlobalStatus.graphics.setColor(Layout.uiInnerOutlineColor);
            GlobalStatus.graphics.drawLine(this.posX + 3, this.itemListPosY - 2, this.posX + this.width - 3, this.itemListPosY - 2);
            Font.drawString(var1, this.innerLeftMargin + 3, this.itemListPosY - this.rowHeight + 3, 0);
            Font.drawString(var2, this.posX + this.width - 4 - 2, this.itemListPosY - this.rowHeight + 3, 0, 18);
         } else {
            this.itemListPosY = this.posY + 14 + this.tabHeight;
         }

         int var4 = -1;
         if(this.scrollPos == -1) {
            this.scrollPos = 0;
         }

         for(int var5 = this.scrollPos; var5 < this.perTabEntries[this.selectedTab].length && this.perTabEntries[this.selectedTab][var5] != null && var5 < this.scrollPos + this.displayedEntriesCount + 1; ++var5) {
            if(((ListItem)((ListItem)this.perTabEntries[this.selectedTab][var5])).isHeader()) {
               if(this.selectedEntry > var5) {
                  var4 = var5;
               }

               GlobalStatus.graphics.setColor(Layout.uiOuterDownLeftOutlineInnerLabalBgColor); // разделитель между типами предметов в ангаре
               GlobalStatus.graphics.fillRect(this.posX + 7, this.itemListPosY + (var5 - this.scrollPos) * this.rowHeight + 3, this.width - 7 - this.listRightPadding - 1, this.rowHeight - 10);
               GlobalStatus.graphics.setColor(0);
               GlobalStatus.graphics.drawRect(this.posX + 7, this.itemListPosY + (var5 - this.scrollPos) * this.rowHeight + 3, this.width - 7 - this.listRightPadding - 7, this.rowHeight - 10);
               GlobalStatus.graphics.setColor(Layout.uiInnerOutlineColor);
               GlobalStatus.graphics.drawRect(this.posX + 6, this.itemListPosY + (var5 - this.scrollPos) * this.rowHeight + 2, this.width - 7 - this.listRightPadding - 5, this.rowHeight - 10);
               Layout.sub_449(this.posX + 6, this.itemListPosY + (var5 - this.scrollPos) * this.rowHeight + 2, false);
            }

            this.drawItem(this.perTabEntries[this.selectedTab][var5], var5);
         }

         if(var4 >= 0) {
            Layout.sub_449(this.posX + 6, this.itemListPosY + (var4 - this.scrollPos) * this.rowHeight + 2, true);
         }

      }
   }

   protected void drawItem(Object var1, int var2) {
      ListItem var9 = (ListItem)((ListItem)var1);
      String var3;
      int var10001;
      String var10000;
      int var10003;
      int var10002;
      if(this.selectedTab != 0 && this.selectedTab != 3) {
         if(this.selectedTab != 1 && this.selectedTab != 4) {
            if(this.selectedTab != 2) {
               return;
            }

            if(var9.isHeader()) {
               var10000 = var9.label;
               var10001 = this.innerLeftMargin + 3;
               var10002 = this.itemListPosY + (var2 - this.scrollPos) * this.rowHeight + 5;
               var10003 = 0;
            } else {
               if(!var9.isBluePrint() && !var9.isPendingProduct()) {
                  return;
               }

               ImageFactory.drawItem(var9.getIndex(), Globals.getItems()[var9.getIndex()].getType(), Globals.itemsImage, this.itemTypesImage, this.innerLeftMargin + 40, this.itemListPosY + (var2 - this.scrollPos) * this.rowHeight + this.getRowHeight() / 2 + 1, 6);
               if(var2 == this.selectedEntry) {
                  GlobalStatus.graphics.drawImage(this.itemTypesSelImage, this.innerLeftMargin + 40, this.itemListPosY + (var2 - this.scrollPos) * this.rowHeight + this.getRowHeight() / 2 + 1, 6);
               }

               if(var9.isBluePrint()) {
                  boolean var11 = false;
                  Item[] var10 = Status.getShip().getCargo();
                  int[] var5 = var9.bluePrint.getIngredientList();
                  int[] var6 = var9.bluePrint.missingComponentsTons;
                  if(var10 != null) {
                     for(int var7 = 0; var7 < var5.length && !var11; ++var7) {
                        if(var6[var7] > 0) {
                           for(int var8 = 0; var8 < var10.length; ++var8) {
                              if(var10[var8].getIndex() == var5[var7]) {
                                 var11 = true;
                                 break;
                              }
                           }
                        }
                     }
                  }

                  Layout.drawStringWidthLimited((var9.bluePrint.getTonsPerProduction2() > 1?var9.bluePrint.getTonsPerProduction() + "x ":"") + Globals.getItemName(var9.getIndex()), this.innerLeftMargin + ImageFactory.itemFrameWidth + 40, this.itemListPosY + (var2 - this.scrollPos) * this.rowHeight + 1, this.width - this.innerLeftMargin - ImageFactory.itemFrameWidth - 20 - 9 - this.listRightPadding + 2, this.selectedEntry == var2?6:0);
                  if(!var11 || var11 && Layout.quickClockHigh_()) {
                     Font.drawString((int)(var9.bluePrint.getCompletionRate() * 100.0F) + "%", this.posX + this.width - 6 - this.listRightPadding - 40, this.itemListPosY + (var2 - this.scrollPos) * this.rowHeight + 1, this.selectedEntry == var2?4:6, 18);
                  }

                  if(!var9.bluePrint.isStarted()) {
                     Font.drawString(GlobalStatus.gameText.getText(133) + " " + var9.bluePrint.getProductionStationName(), this.innerLeftMargin + ImageFactory.itemFrameWidth + 40, this.itemListPosY + (var2 - this.scrollPos) * this.rowHeight + 1 + 12, this.selectedEntry == var2?2:1);
                  }

                  return;
               }

               Font.drawString((var9.producedGood.producedQuantity > 1?var9.producedGood.producedQuantity + "x ":"") + Globals.getItemName(var9.getIndex()), this.innerLeftMargin + ImageFactory.itemFrameWidth + 20, this.itemListPosY + (var2 - this.scrollPos) * this.rowHeight + 1, this.selectedEntry == var2?2:0);
               var10000 = GlobalStatus.gameText.getText(133) + " " + var9.producedGood.stationName;
               var10001 = this.innerLeftMargin + ImageFactory.itemFrameWidth + 6;
               var10002 = this.itemListPosY + (var2 - this.scrollPos) * this.rowHeight + 1 + 9;
               var10003 = this.selectedEntry == var2?2:1;
            }
         } else {
            if(!var9.isHeader()) {
               if(var9.isNonFancyHeader()) {
                  Font.drawString(var9.label, this.innerLeftMargin + 3, this.itemListPosY + (var2 - this.scrollPos) * this.rowHeight + 5, this.selectedEntry == var2?2:1);
                  return;
               }

               if(!var9.isShip()) {
                  var3 = this.selectedTab == 1?var9.item.getStationAmount() + "":var9.item.getAmount() + "";
                  String var4 = this.selectedTab == 1?var9.item.getAmount() + "":this.bluePrint.getCurrentAmount(var9.item.getIndex()) + var9.item.getBlueprintAmount() + "/" + this.bluePrint.getTotalAmount(var9.item.getIndex());
                  ImageFactory.drawItem(var9.item.getIndex(), var9.item.getType(), Globals.itemsImage, this.itemTypesImage, this.innerLeftMargin + 65, this.itemListPosY + (var2 - this.scrollPos) * this.rowHeight + this.getRowHeight() / 2 + 1, 6);
                  if(var2 == this.selectedEntry) {
                     GlobalStatus.graphics.drawImage(this.itemTypesSelImage, this.innerLeftMargin + 65, this.itemListPosY + (var2 - this.scrollPos) * this.rowHeight + this.getRowHeight() / 2 + 1, 6);
                  }

                  Font.drawString(var3, this.innerLeftMargin + 40, this.itemListPosY + (var2 - this.scrollPos) * this.rowHeight + 1, this.selectedEntry == var2?2:1, 18); // количество предметов в ангаре
                  Layout.drawStringWidthLimited(Globals.getItemName(var9.getIndex()), this.innerLeftMargin + 20 + ImageFactory.itemFrameWidth + 60, this.itemListPosY + (var2 - this.scrollPos) * this.rowHeight + 1, this.width - this.innerLeftMargin - ImageFactory.itemFrameWidth - 40 - 9 - this.listRightPadding + 2 - (this.selectedTab == 4?10:0), this.selectedEntry == var2?2:0); // отображает название предмета в магазине или в чертеже
				  int fontColor = 0;
				  if(Status.getCredits() < var9.item.getSinglePrice()) {
					  fontColor = 4;
				  } else {
					  fontColor = 6;
				  }
                  Font.drawString(Layout.formatCredits(var9.item.getSinglePrice()), this.innerLeftMargin + 20 + ImageFactory.itemFrameWidth + 60, this.itemListPosY + (var2 - this.scrollPos) * this.rowHeight + 1 + 14, this.selectedEntry == var2?fontColor:fontColor, 0);
                  Font.drawString(var4, this.posX + this.width - 6 - this.listRightPadding - 40, this.itemListPosY + (var2 - this.scrollPos) * this.rowHeight + 1, this.selectedEntry == var2?2:1, 18); // количество предметов на корабле
				  
                  if(this.trading_ && !this.popupOpen && var2 == this.selectedEntry && Layout.quickClockHigh_()) {
                     Font.drawString("<", this.innerLeftMargin + 20, this.itemListPosY + (var2 - this.scrollPos) * this.rowHeight + 1 + 8, 2, 18);
                     Font.drawString(">", this.posX + this.width - 6 - this.listRightPadding, this.itemListPosY + (var2 - this.scrollPos) * this.rowHeight + 1 + 8, 2, 18);
                  }

                  if(!GlobalStatus.var_1083 && (var9.item.getTecLevel() >= 4 || var9.item.getSort() == 13 || var9.item.getSort() == 14 || var9.item.getSort() == 7 || var9.item.getSort() == 6 || var9.item.getSort() == 15)) {
                     GlobalStatus.graphics.drawImage(Layout.sub_8b(), this.innerLeftMargin + 3 + 20, this.itemListPosY + (var2 - this.scrollPos) * this.rowHeight + this.getRowHeight() - 10, 20);
                  }

                  return;
               }
			   if(ships1[var9.ship.getIndex()] == null) {
				   ships1[var9.ship.getIndex()] = AEResourceManager.getImage(9000 + var9.ship.getIndex());
			   }
			   int fontColorShipsPrice = 0;
			   if(Status.getCredits() < var9.ship.getPrice() - Status.getShip().getPrice()) {
				   fontColorShipsPrice = 4;
			   } else {
				   fontColorShipsPrice = 6;
			   }
			   ImageFactory.drawShip(var9.ship.getIndex(), var9.ship.sub_3e(), this.shipsColorImage, this.innerLeftMargin + 60, this.itemListPosY + (var2 - this.scrollPos) * this.rowHeight + this.getRowHeight() / 2 + 1, 6);
			   GlobalStatus.graphics.drawImage(ships1[var9.ship.getIndex()], this.innerLeftMargin + 60, this.itemListPosY + (var2 - this.scrollPos) * this.rowHeight + this.getRowHeight() / 2 + 1, 6);
               //FACE_GEN.drawItemorShip(var9.var_234.getShipID(), var9.var_234.sub_3e(), ships1, AEAssetsManager.shipsColorImage, this.var_450 + 3 + 20, this.var_4af + (var2 - this.var_3b0) * this.var_4fd + this.sub_e5() / 2 + 1, 6); // отрисовать корабли в ангаре
               Font.drawString(var9.ship.getShipName(var9.ship.getIndex()), this.innerLeftMargin + 20 + ImageFactory.itemFrameWidth + 60, this.itemListPosY + (var2 - this.scrollPos) * this.rowHeight + 1, this.selectedEntry == var2?2:0);
               Font.drawString(Layout.formatCredits(var9.ship.getPrice() - Status.getShip().getPrice()), this.innerLeftMargin + 20 + ImageFactory.itemFrameWidth + 60, this.itemListPosY + (var2 - this.scrollPos) * this.rowHeight + 1 + 14, this.selectedEntry == var2?fontColorShipsPrice:fontColorShipsPrice, 0);
               if(!GlobalStatus.var_1083 && var9.ship.getSlots(3) > 4) {
                  GlobalStatus.graphics.drawImage(Layout.sub_8b(), this.innerLeftMargin + 3 + 20, this.itemListPosY + (var2 - this.scrollPos) * this.rowHeight + this.getRowHeight() - 10, 20);
                  return;
               }

               return;
            }

            var10000 = var9.label + (var9.showCountItemType >= 0?" (" + this.stationItemTypeCounts[var9.showCountItemType] + ")":"");
            var10001 = this.innerLeftMargin + 3;
            var10002 = this.itemListPosY + (var2 - this.scrollPos) * this.rowHeight + 5;
            var10003 = 0;
         }
      } else {
         if(var9.isNonFancyHeader()) {
            Font.drawString(var9.label, this.innerLeftMargin + 3, this.itemListPosY + (var2 - this.scrollPos) * this.rowHeight + 5, this.selectedEntry == var2?2:1);
            return;
         }

         if(!var9.isHeader()) {
            if(var9.isShip()) {
			   if(ships2[var9.ship.getIndex()] == null) {
				   ships2[var9.ship.getIndex()] = AEResourceManager.getImage(9000 + var9.ship.getIndex());
			   }
			   ImageFactory.drawShip(var9.ship.getIndex(), var9.ship.sub_3e(), this.shipsColorImage, this.innerLeftMargin + 60, this.itemListPosY + (var2 - this.scrollPos) * this.rowHeight + this.getRowHeight() / 2 + 1, 6);
			   GlobalStatus.graphics.drawImage(ships2[var9.ship.getIndex()], this.innerLeftMargin + 60, this.itemListPosY + (var2 - this.scrollPos) * this.rowHeight + this.getRowHeight() / 2 + 1, 6);
               //FACE_GEN.drawItemorShip(var9.var_234.getShipID(), var9.var_234.sub_3e(), this.var_67, AEAssetsManager.shipsColorImage, this.var_450 + 1, this.var_4af + (var2 - this.var_3b0) * this.var_4fd + this.sub_e5() / 2 + 1, 6); // отрисовать корабль игрока
               if(!GlobalStatus.var_1083 && var9.ship.getSlots(3) > 4) {
                  GlobalStatus.graphics.drawImage(Layout.sub_8b(), this.innerLeftMargin, this.itemListPosY + (var2 - this.scrollPos) * this.rowHeight + this.getRowHeight() - 10, 20);
               }
			   
			   int shipPirceFontColor = 0;
			   if(Status.getCredits() < var9.ship.getPrice()) {
				   shipPirceFontColor = 4;
			   } else {
				   shipPirceFontColor = 6;
			   }
			   
               Font.drawString(var9.ship.getShipName(var9.ship.getIndex()), this.innerLeftMargin + ImageFactory.itemFrameWidth + 60, this.itemListPosY + (var2 - this.scrollPos) * this.rowHeight + 1, this.selectedEntry == var2?2:0);
               Font.drawString(Layout.formatCredits(var9.ship.getPrice()), this.innerLeftMargin + ImageFactory.itemFrameWidth + 60, this.itemListPosY + (var2 - this.scrollPos) * this.rowHeight + 1 + 14, this.selectedEntry == var2?shipPirceFontColor:shipPirceFontColor, 0);
               return;
            }

            if(var9.isItem()) { // оборудование на корабле игрока
               ImageFactory.drawItem(var9.item.getIndex(), var9.item.getType(), Globals.itemsImage, this.itemTypesImage, this.innerLeftMargin + 60, this.itemListPosY + (var2 - this.scrollPos) * this.rowHeight + this.getRowHeight() / 2 + 1, 6);
               if(var2 == this.selectedEntry) {
                  GlobalStatus.graphics.drawImage(this.itemTypesSelImage, this.innerLeftMargin + 60, this.itemListPosY + (var2 - this.scrollPos) * this.rowHeight + this.getRowHeight() / 2 + 1, 6);
               }

               if(!GlobalStatus.var_1083 && (var9.item.getTecLevel() >= 4 || var9.item.getSort() == 13 || var9.item.getSort() == 14 || var9.item.getSort() == 7 || var9.item.getSort() == 6 || var9.item.getSort() == 15)) {
                  GlobalStatus.graphics.drawImage(Layout.sub_8b(), this.innerLeftMargin + 60, this.itemListPosY + (var2 - this.scrollPos) * this.rowHeight + this.getRowHeight() - 10, 20);
               }

               var3 = Globals.getItemName(var9.item.getIndex());
               if(var9.item.getType() == 1) {
                  var3 = var3 + " (" + var9.item.getAmount() + ")";
               }

               Layout.drawStringWidthLimited(var3, this.innerLeftMargin + ImageFactory.itemFrameWidth + 60, this.itemListPosY + (var2 - this.scrollPos) * this.rowHeight + 1, this.width - this.innerLeftMargin - ImageFactory.itemFrameWidth - 9 - this.listRightPadding + 2, this.selectedEntry == var2?2:0);
               Font.drawString(Layout.formatCredits(var9.item.getSinglePrice()), this.innerLeftMargin + ImageFactory.itemFrameWidth + 60, this.itemListPosY + (var2 - this.scrollPos) * this.rowHeight + 1 + 14, this.selectedEntry == var2?2:1, 0);
               return;
            }

            if(var9.isMedal__()) {
               Font.drawString(GlobalStatus.gameText.getText(69), this.innerLeftMargin + ImageFactory.itemFrameWidth + 5, this.itemListPosY + (var2 - this.scrollPos) * this.rowHeight + 3, this.selectedEntry == var2?2:1);
               return;
            }

            return;
         }

         var10000 = var9.label + (var9.showCountItemType >= 0?" (" + Status.getShip().getUsedSlots(var9.showCountItemType) + "/" + Status.getShip().getSlots(var9.showCountItemType) + ")":"");
         var10001 = this.innerLeftMargin + 5;
         var10002 = this.itemListPosY + (var2 - this.scrollPos) * this.rowHeight + 5;
         var10003 = 0;
      }

      Font.drawString(var10000, var10001, var10002, var10003);
   }

   public final int getRowHeight() {
      return 20;
   }

   public final int getTabHeight() {
      return Font.getFontSpacingY() + 4;
   }
   
   public static void hangarListPointerPressed(int x, int y) {
	   if(tabButton != null) {
		   tabButton[0].standartButtonTouch(x, y);
		   tabButton[1].standartButtonTouch(x, y);
		   tabButton[2].standartButtonTouch(x, y);
		}
   }
   
   public static void hangarListPointerReleased(int x, int y) {
	   if(tabButton != null) {
		   tabButton[0].buttonsTouchReleased(x, y);
		   tabButton[1].buttonsTouchReleased(x, y);
		   tabButton[2].buttonsTouchReleased(x, y);
		}
   }
}
