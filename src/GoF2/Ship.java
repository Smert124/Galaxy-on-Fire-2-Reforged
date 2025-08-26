package GoF2;

import AE.GlobalStatus;

/**
**
** ����� ���������� �� �������.
**
**/

public final class Ship {
	
   // ��� ������� short ��������� �� ����������� � �������� ������� ��� ��������� �������������
   public static final short[] SHIP_PREVIEW_SCALING = FileRead.readShipViewportOffset(1); // �������� ������-�����
   public static final short[] SHIP_HANGAR_OFFSETS = FileRead.readShipViewportOffset(2); // �����������
   private int id;
   private int var_1b3;
   private int var_240;
   private int var_2c9;
   private int var_2db;
   private int var_2eb;
   private int[] var_342;
   private float var_385;
   private int var_3df;
   private int var_441;
   private int var_453;
   private int var_491;
   private int var_4da;
   private int var_519;
   private int var_55e;
   private int var_5a8;
   private int passengersCapacity;
   private int var_64d;
   private boolean var_66f;
   private boolean var_6b2;
   private boolean var_6c6;
   private int var_6f8;
   private Item[] equipped;
   private Item[] var_720;
   private String[] ship_name = new String[GlobalStatus.max_ships];
   private boolean deepscience_ship;


   public Ship(String[] name, int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, float var9) {
	  this.ship_name = name;
      this.id = var1;
      this.var_1b3 = var2;
      this.var_2c9 = var3;
      this.var_2db = 0;
      this.var_2eb = var4;
      this.var_240 = var4;
      this.var_342 = new int[4];
      this.var_342[0] = var5;
      this.var_342[1] = var6;
      this.var_342[2] = var7;
      this.var_342[3] = var8;
      this.var_385 = var9 / 100.0F;
      this.equipped = new Item[var5 + var6 + var7 + var8];
      this.var_720 = null;
      this.var_6f8 = 0;
      this.sub_8aa();
	//  System.out.println("" + var1 + "," + var2 + "," + var3 + "," + var4 + "," + var5 + "," + var6 + "," + var7 + "," + var8 + "," + var9);
   }
   
   public final boolean getKhadorIntegrated() {
	   if(this.getIndex() == 37 || this.getIndex() == 38 || this.getIndex() == 40) {
		   deepscience_ship = true;
	   } else {
		   deepscience_ship = false;
	   }
	   return this.deepscience_ship;
   }
   
   public final String getShipName(int id) {
	   return this.ship_name[id];
   }

   public final void setRace(int var1) {
      this.var_6f8 = var1;
   }

   public final int sub_3e() {
      return this.var_6f8;
   }

   public final int getIndex() {
      return this.id;
   }

   public final int getPrice() {
      return this.id == 10 && Achievements.gotAllMedals()?'\uc350':this.var_2eb;
   }

   public final float getHandling() {
      return this.var_385;
   }

   public final int getBaseHP() {
      return this.var_1b3;
   }

   public final int getCombinedHP() {
      return this.var_1b3 + this.var_3df + this.var_441;
   }

   public final int getAdditionalArmour() {
      return this.var_441;
   }

   public final int getBaseArmour() {
      return this.var_1b3;
   }

   public final int getShield() {
      return this.var_3df;
   }

   public final int sub_2e2() {
      return this.var_453;
   }

   public final int sub_2ec() {
      return this.var_4da;
   }

   public final int getBoostDuration() {
      return this.var_55e;
   }

   public final int getBoostDelay() {
      return this.var_519;
   }

   public final int getAddHandlingPercent() {
      return this.var_5a8;
   }

   public final boolean sub_3f2() {
      return this.var_4da > 0;
   }

   public final boolean sub_405() {
      return this.var_6b2;
   }

   public final boolean sub_413() {
      return this.var_6c6;
   }

   public final boolean hasRepairBot() {
      return this.var_66f;
   }

   public final int sub_4ab() {
      return this.var_64d;
   }

   public final int getBaseLoad() {
      return this.var_2c9;
   }

   public final int getCargoPlus() {
      return this.var_2c9 + this.var_491;
   }

   public final int getCurrentLoad() {
      return this.var_2db;
   }

   public final Item[] getEquipment() {
      return this.equipped;
   }

   public final Item[] getCargo() {
      return this.var_720;
   }

   public final void refreshCargoSpaceUnsafe(Item[] var1) {
      this.var_720 = var1;
      this.var_2db = 0;

      for(int var2 = 0; var2 < this.var_720.length; ++var2) {
         this.var_2db += this.var_720[var2].getAmount();
      }

      this.sub_8aa();
      if(Status.maxFreeCargo < this.getFreeCargo()) {
         Status.maxFreeCargo = this.getFreeCargo();
      }

   }

   public final Item getFirstEquipmentOfSort(int var1) {
      for(int var2 = 0; var2 < this.equipped.length; ++var2) {
         if(this.equipped[var2] != null && this.equipped[var2].getSort() == var1) {
            return this.equipped[var2];
         }
      }

      return null;
   }

   public final boolean hasEquipment(int var1) {
      if(this.equipped == null) {
         return false;
      } else {
         for(int var2 = 0; var2 < this.equipped.length; ++var2) {
            if(this.equipped[var2] != null && this.equipped[var2].getIndex() == var1) {
               return true;
            }
         }

         return false;
      }
   }

   public final boolean hasCargoType_FIX_ME_() {
      if(this.var_720 == null) {
         return false;
      } else {
         for(int var1 = 0; var1 < this.var_720.length; ++var1) {
            if(this.var_720[var1] != null && this.var_720[var1].getType() == this.id) {
               return true;
            }
         }

         return false;
      }
   }

   public final void removeCargo(Item var1) {
      this.removeCargo(var1.getIndex(), var1.getAmount());
   }

   public final void removeAllCargo() {
      this.var_720 = null;
   }

   public final boolean removeCargo(int var1) {
      return this.removeCargo(131, 9999999); // ������� ����������. ��������, ������ ������� ������� ������.
   }

   public final boolean removeCargo(int var1, int var2) { // ������ �� ���������� �������
      if(this.var_720 == null) {
         return false;
      } else {
         boolean var3 = false;
         for(int var4 = 0; var4 < this.var_720.length; ++var4) {
		//	System.out.println("Debug: " + var1 + ", " + var2)
            if(this.var_720[var4].getIndex() == var1) {
               this.var_720[var4].changeAmount(-var2);
               if(this.var_720[var4].getAmount() <= 0) {
                  var3 = true;
               }
               break;
            }
         }

         if(var3) {
            this.setCargo(Item.extractItems(this.var_720, true));
         } else {
            this.setCargo(this.var_720);
         }

         return var3;
      }
   }

   public final void setCargo(Item[] var1) {
      this.var_720 = var1;
      this.var_2db = 0;
      if(var1 != null) {
         for(int var2 = 0; var2 < this.var_720.length; ++var2) {
            this.var_2db += this.var_720[var2].getAmount();
         }
      }

      this.sub_8aa();
      if(Status.maxFreeCargo < this.getFreeCargo()) {
         Status.maxFreeCargo = this.getFreeCargo();
      }

   }

   public final boolean spaceAvailable(int var1) {
      return this.var_2db + var1 <= this.var_2c9 + this.var_491;
   }

   public final int getFreeCargo() {
      return this.getCargoPlus() - this.var_2db;
   }

   public final void addCargo(Item var1) {
      Item[] var2 = new Item[]{var1};
      this.setCargo(Item.mixItems(this.var_720, var2));
   }

   public final void replaceEquipment(Item[] var1) {
      this.equipped = var1;
      this.sub_8aa();
   }

   public final void removeEquipment(Item var1) {
      if(this.equipped != null) {
         for(int var2 = 0; var2 < this.equipped.length; ++var2) {
            if(this.equipped[var2] != null && this.equipped[var2].equals(var1)) {
               this.equipped[var2] = null;
               return;
            }
         }

      }
   }

   public final Item[] getEquipment(int var1) { // ������� ������������
      if(var1 < this.var_342.length && this.var_342[var1] != 0) {
         Item[] var2 = new Item[this.var_342[var1]];
         int var3 = 0;

         int var4;
         for(var4 = 0; var4 < var1; ++var4) {
            var3 += this.var_342[var4];
         }

         var4 = 0;

         for(int var5 = var3; var5 < var3 + this.var_342[var1]; ++var5) {
            if(var4 < var2.length) {
               var2[var4++] = this.equipped[var5];
            } else {
               System.err.println("Ship.getEquipment() failed");
            }
         }

         return var2;
      } else {
         return null;
      }
   }

   public final void setEquipment(Item var1, int var2) { // ��������� ������������
      var2 = var2;
	//  System.out.println("Debug: " + var1 + ", " + var2);
      for(int var3 = 0; var3 < var1.getType(); ++var3) {
         var2 += this.var_342[var3];
      }

      if(var2 < this.equipped.length) {
         this.equipped[var2] = var1;
         this.sub_8aa();
      } else {
         System.out.println("Ship.setEquipment() Array Index out of bounds");
      }
   }

   private void sub_8aa() {
      this.var_66f = false;
      this.var_6b2 = false;
      this.var_6c6 = false;
      this.var_3df = 0;
      this.var_453 = 0;
      this.var_491 = 0;
      this.var_519 = 0;
      this.var_4da = 0;
      this.var_55e = 0;
      this.var_5a8 = 0;
      this.var_441 = 0;
      this.var_64d = 0;
      this.passengersCapacity = 0;
      this.var_240 = this.var_2eb;

      for(int var1 = 0; var1 < this.equipped.length; ++var1) {
         if(this.equipped[var1] != null) {
            switch(this.equipped[var1].getSort()) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 8:
               this.var_64d += this.equipped[var1].getAttribute(9);
            case 4:
            case 5:
            case 6:
            case 7:
            case 11:
            case 13:
            case 17:
            case 19:
            default:
               break;
            case 9:
               this.var_3df = this.equipped[var1].getAttribute(16);
               this.var_453 = this.equipped[var1].getAttribute(17);
               break;
            case 10:
               this.var_441 = this.equipped[var1].getAttribute(18);
               break;
            case 12:
               this.var_491 += this.equipped[var1].getAttribute(20);
               break;
            case 14:
               this.var_4da = this.equipped[var1].getAttribute(23);
               this.var_55e = this.equipped[var1].getAttribute(25);
               this.var_519 = this.equipped[var1].getAttribute(24);
               break;
            case 15:
               this.var_66f = true;
               break;
            case 16:
               this.var_5a8 = this.equipped[var1].getAttribute(26);
               break;
            case 18:
               this.var_6b2 = true;
               break;
            case 20:
               this.passengersCapacity += this.equipped[var1].getAttribute(32);
               break;
            case 21:
               this.var_6c6 = true;
            }

            this.var_240 += this.equipped[var1].getTotalPrice();
         }
      }

      this.var_491 = (int)((float)this.var_2c9 * (float)this.var_491 / 100.0F);
      Ship var4 = this;
      int var2 = 0;
      if(this.var_720 != null) {
         for(int var3 = 0; var3 < var4.var_720.length; ++var3) {
            if(var4.var_720[var3] != null) {
               var2 += var4.var_720[var3].getTotalPrice();
            }
         }
      }

      this.var_240 += var2;
   }

   public final int getMaxPassengers() {
      return this.passengersCapacity;
   }

   public final void freeSlot(Item var1, int var2) {
      for(int var3 = 0; var3 < this.equipped.length; ++var3) {
         if(this.equipped[var3] != null && this.equipped[var3].equals(var1) && var3 == var2) {
            this.equipped[var3] = null;
            break;
         }
      }

      this.sub_8aa();
   }

   public final void freeSlot(Item var1) { // ��������������
      for(int var2 = 0; var2 < this.equipped.length; ++var2) {
         if(this.equipped[var2] != null && this.equipped[var2].equals(var1)) {
            this.equipped[var2] = null;
            break;
         }
      }

      this.sub_8aa();
   }

   public final int getSlots(int var1) {
      return this.var_342[var1];
   }

   public final int getSlotTypes() {
      int var1 = 0;

      for(int var2 = 0; var2 < this.var_342.length; ++var2) {
         if(this.var_342[var2] > 0) {
            ++var1;
         }
      }

      return var1;
   }

   public final int getUsedSlots(int var1) {
      int var2 = 0;

      for(int var3 = 0; var3 < this.equipped.length; ++var3) {
         if(this.equipped[var3] != null && this.equipped[var3].getType() == var1) {
            ++var2;
         }
      }

      return var2;
   }

   public final Ship cloneBase() {
      return new Ship(this.ship_name, this.id, this.var_1b3, this.var_2c9, this.var_2eb, this.var_342[0], this.var_342[1], this.var_342[2], this.var_342[3], this.var_385 * 100.0F);
   }

   public final void setSellingPrice() {
      this.var_2eb = (int)((float)Globals.getShips()[this.id].getPrice() / 1.25F);
   }

   public final void adjustPrice() {
      if(Status.getStation() != null) {
         this.var_2eb = Globals.getShips()[this.id].getPrice() - (int)((float)Status.getStation().getTecLevel() / 100.0F * (float)Globals.getShips()[this.id].getPrice());
      }

   }

}
