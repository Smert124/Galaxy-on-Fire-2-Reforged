package GoF2;


public final class Item {

   private static final boolean[] installableMultipleTimes = new boolean[]{true, true, true, true, true, true, true, true, false, false, false, true, true, false, false, false, false, false, false, false, true, false, true, true, true}; // // установка оборудования одного типа
   private int id;
   private int type;
   private int subType;
   private int tecLevel;
   private int lowestPriceSystemId;
   private int highestPriceSystemId;
   private int price;
   private int occurance;
   private int minPrice;
   private int maxPrice;
   private int var_6b1;
   private int[] blueprintComponentsIds;
   private int[] blueprintComponentsQuantities;
   private int[] atributesIndexed;
   private int amount;
   private int stationAmount;
   private int blueprintAmount;
   private boolean unsaleable;


   public Item(int[] var1, int[] var2, int[] var3) {
      this.blueprintComponentsIds = var1;
      this.blueprintComponentsQuantities = var2;
      this.atributesIndexed = var3;
      this.id = this.atributesIndexed[1]; // id предмета
      this.type = this.atributesIndexed[3]; // хз что это
      this.subType = this.atributesIndexed[5]; // тип
      this.tecLevel = this.atributesIndexed[7]; // techlevel
      this.occurance = this.atributesIndexed[13]; // % вероятности наличия в продаже
      this.minPrice = this.atributesIndexed[15]; // минимальная цена
      this.maxPrice = this.atributesIndexed[17]; // максимальная цена
      this.lowestPriceSystemId = this.atributesIndexed[9]; // min system price
      this.highestPriceSystemId = this.atributesIndexed[11]; // max system price
	//  this.var_6b1 = this.var_7b8[23];
      this.price = this.minPrice + (this.maxPrice - this.minPrice) / 2;
      boolean var4 = false;
      this.unsaleable = var4;
	//  System.out.println("Item: " + var_3b + ", HZ: " + var_10d + ", TYPE: " + var_186 + ", Techlevel: " + var_368 + ", Hangar availability: " + var_569 + ", Min price: " + minItemPrice + ", Max price: " + ", Flying Time: " + var_6b1 + "," + maxItemPrice + "," + var_3cb + "," + var_3f7 + "," + currentItemPrice);
   }

   public final boolean canBeInstalledMultipleTimes() { // установка оборудования одного типа
      return installableMultipleTimes[this.subType];
   }

   public final int getIndex() {
      return this.id;
   }

   public final int getType() {
      return this.type;
   }

   public final int getTecLevel() {
      return this.tecLevel;
   }

   public final int getSort() {
      return this.subType;
   }

   public final int getSinglePrice() {
      return this.price;
   }

   public final int getTotalPrice() {
      return this.price * this.amount;
   }

   public final int getMaxPrice() {
      return this.maxPrice;
   }

   public final int getMinPrice() {
      return this.minPrice;
   }

   public final int getHighestPriceSystemId() {
      return this.highestPriceSystemId;
   }

   public final int getLowestPriceSystemId() {
      return this.lowestPriceSystemId;
   }

   public final void setPrice(int var1) {
      this.price = var1;
   }

   public final void setAmount(int var1) {
      this.amount = var1;
   }

   public final int getOccurance() {
      return this.occurance;
   }

   public final int getAmount() {
      return this.amount;
   }

   public final void changeAmount(int var1) {
      this.amount += var1;
   }

   public final void setStationAmount(int var1) {
      this.stationAmount = var1;
   }

   public final int getStationAmount() {
      return this.stationAmount;
   }

   private void changeStationAmount(int var1) {
      this.stationAmount += var1;
   }

   public final void setBlueprintAmount(int var1) {
      this.blueprintAmount = var1;
   }

   public final int getBlueprintAmount() {
      return this.blueprintAmount;
   }

   private void incBlueprintAmount(int var1) {
      this.blueprintAmount += var1;
   }

   public final int[] getBluePrintComponentsIds() {
      return this.blueprintComponentsIds;
   }

   public final int[] getBlueprintComponentsQuantities() {
      return this.blueprintComponentsQuantities;
   }

   public final int getAttribute(int var1) {
      for(int var2 = 0; var2 < this.atributesIndexed.length; var2 += 2) {
         if(this.atributesIndexed[var2] == var1) {
            return this.atributesIndexed[var2 + 1];
         }
      }

      return -979797979;
   }

   public final int transaction(boolean var1) { // покупка предмета
      boolean var2 = false;
      int var3;
      if(var1 && this.stationAmount > 0 && Status.getCredits() >= this.price) { // перенос предмета в грузовой отсек, если достаточно денег
         this.changeStationAmount(-1);
         this.changeAmount(1);
         var3 = -this.price;
      } else {
         if(var1 || this.amount <= 0) { // если предметы на станции кончились
            return 0;
         }

         this.changeAmount(-1);
         this.changeStationAmount(1);
         var3 = this.price;
      }

      return var3;
   }

   public final int transactionBlueprint(boolean var1) {
      if(var1 && this.blueprintAmount > 0) { // забрать из чертежа
         this.incBlueprintAmount(-1);
         this.changeAmount(1);
         return -this.price;
      } else if(!var1 && this.amount > 0) { // добавление в чертеж
         this.changeAmount(-1);
         this.incBlueprintAmount(1);
         return this.price;
      } else {
         return 0;
      }
   }

   public static boolean isInList(int var0, int var1, Item[] var2) {
      if(var2 == null) {
         return false;
      } else {
         for(int var3 = 0; var3 < var2.length; ++var3) {
            if(var2[var3].id == var0 && var2[var3].amount >= var1) {
               return true;
            }
         }

         return false;
      }
   }

   public static Item[] extractItems(Item[] var0, boolean var1) {
      if(var0 == null) {
         return null;
      } else {
         Item[] var2 = new Item[var0.length];
         Item[] var3 = new Item[var0.length];
         System.arraycopy(var0, 0, var3, 0, var0.length);
         int var6 = 0;

         for(int var4 = 0; var4 < var3.length; ++var4) {
            if(var1 && var3[var4].amount > 0) {
               var2[var6] = var3[var4].makeItem(var3[var4].amount);
               ++var6;
            } else if(!var1 && var3[var4].stationAmount > 0) {
               var2[var6] = var3[var4].makeItem(var3[var4].stationAmount);
               ++var6;
            }
         }

         Item[] var7 = new Item[var6];
         System.arraycopy(var2, 0, var7, 0, var6);
         if(var7.length == 0) {
            return null;
         } else {
            return var7;
         }
      }
   }

   public static Item[] combineItems(Item[] var0, Item[] var1) {
      int var2 = var0 == null?0:var0.length;
      int var3 = var1 == null?0:var1.length;
      Item[] var4 = new Item[var2 + var3];
      if(var2 > 0 && var3 == 0) {
         for(var3 = 0; var3 < var0.length; ++var3) {
            var4[var3] = var0[var3].makeItem(var0[var3].amount);
         }

         return var4;
      } else {
         Item var10000;
         if(var3 > 0 && var2 == 0) {
            for(var3 = 0; var3 < var1.length; ++var3) {
               var4[var3] = var1[var3].makeItem(0);
               var10000 = var4[var3];
               var2 = var1[var3].amount;
               var10000.stationAmount = var2;
            }

            return var4;
         } else if(var3 == 0 && var2 == 0) {
            return null;
         } else {
            for(var3 = 0; var3 < var0.length; ++var3) {
               var4[var3] = var0[var3].makeItem(var0[var3].amount);
            }

            var3 = var2;
            int var6 = 0;

            while(var6 < var1.length) {
               var2 = 0;

               while(true) {
                  if(var2 < var4.length) {
                     if(var4[var2] == null) {
                        var4[var2] = var1[var6].makeItem(0);
                        var10000 = var4[var2];
                        var2 = var1[var6].amount;
                        var10000.stationAmount = var2;
                        ++var3;
                     } else {
                        if(!var1[var6].equals(var4[var2])) {
                           ++var2;
                           continue;
                        }

                        var4[var2] = var1[var6].makeItem(var4[var2].amount);
                        var10000 = var4[var2];
                        var2 = var1[var6].amount;
                        var10000.stationAmount = var2;
                     }
                  }

                  ++var6;
                  break;
               }
            }

            var0 = new Item[var3];
            System.arraycopy(var4, 0, var0, 0, var3);

            boolean var8;
            do {
               var8 = true;

               for(int var7 = 1; var7 < var0.length; ++var7) {
                  if(var0[var7 - 1].id > var0[var7].id) {
                     Item var9 = var0[var7 - 1];
                     var0[var7 - 1] = var0[var7];
                     var0[var7] = var9;
                     var8 = false;
                  }
               }
            } while(!var8);

            return var0;
         }
      }
   }

   public static Item[] mixItems(Item[] var0, Item[] var1) {
      if(var0 == null) {
         return var1;
      } else if(var1 == null) {
         return var0;
      } else {
         Item[] var2 = new Item[var1.length];

         for(int var3 = 0; var3 < var1.length; ++var3) {
            var2[var3] = var1[var3];
         }

         Item[] var7 = null;
         int var6 = var2.length;

         int var4;
         int var5;
         for(var4 = 0; var4 < var0.length; ++var4) {
            for(var5 = 0; var5 < var2.length; ++var5) {
               if(var2[var5] != null && var0[var4].id == var2[var5].id) {
                  var0[var4].changeAmount(var2[var5].amount);
                  --var6;
                  var2[var5] = null;
               }
            }
         }

         if(var6 <= 0) {
            return var0;
         } else {
            var7 = new Item[var6];
            var4 = 0;

            for(var5 = 0; var5 < var2.length; ++var5) {
               if(var2[var5] != null) {
                  var7[var4++] = var2[var5];
               }
            }

            Item[] var8 = new Item[var0.length + var7.length];

            for(var6 = 0; var6 < var0.length; ++var6) {
               var8[var6] = var0[var6];
            }

            for(var6 = 0; var6 < var7.length; ++var6) {
               var8[var6 + var0.length] = var7[var6];
            }

            return var8;
         }
      }
   }

   public final boolean isWeapon() {
      return this.type == 0 || this.type == 1 || this.type == 2;
   }

   public final Item makeItem(int var1) {
      return this.getCopyInAmmount(var1, this.price);
   }

   public final Item getCopyInAmmount(int var1, int var2) {
      Item var4;
      Item var10000 = var4 = new Item(this.blueprintComponentsIds, this.blueprintComponentsQuantities, this.atributesIndexed);
      int var6 = this.price;
      var10000.price = var6;
      boolean var7 = this.unsaleable;
      var4.unsaleable = var7;
      var4.price = var2;
      var4.amount = var1;
      return var4;
   }

   public final Item makeItem() {
      return this.getCopyInAmmount(1, this.price);
   }

   public final boolean equals(Item var1) {
      return var1 == null?false:this.id == var1.id;
   }

   public final String toString() {
      return Globals.getItemName(this.id);
   }

   public final boolean setUnsaleable() {
      return this.unsaleable;
   }

   public final void setUnsaleable(boolean var1) {
      this.unsaleable = var1;
   }

}
