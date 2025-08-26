package GoF2;

/**
@class STATION
**/

public final class Station {

   private String name;
   private int id;
   private int systemId;
   private int planetTextureId;
   private int tecLevel;
   private Item[] var_3af;
   private Ship[] ship;
   private Agent[] var_5b0;


   public Station(String var1, int var2, int var3, int var4, int var5) {
      this.name = var1;
      this.id = var2;
      this.systemId = var3;
      this.tecLevel = var4;
      this.planetTextureId = var5;
      this.var_3af = null;
      this.ship = null;
      this.var_5b0 = null;
	//  System.out.println("CFB READ: " + var1 + ", " + var2 + ", " + var3 + ", " + var4 + ", " + var5);
   }

   public Station() {
      this("X", -1, -1, 1, 1); // void station; Name: X, ID: -1, SYSTEM: -1, LEVEL: 0, Planet: 23
   }

   public final int getSystemIndex() {
      return this.systemId;
   }

   public final String getName() {
      return this.name;
   }

   public final int getId() {
      return this.id;
   }

   public final boolean isAttackedByAliens() {
      return this.id == Status.wormholeStation;
   }

   public final int getPlanetTextureId() {
      return this.planetTextureId;
   }

   public final boolean isDiscovered() {
      return Galaxy.getVisitedStations()[this.id];
   }

   public final void visit() {
      if(!this.isDiscovered()) {
         Status.incStationsVisited();
         Galaxy.visitStation(this.id);
      }

   }

   public final int getTecLevel() {
      return this.tecLevel;
   }

   public final Ship[] sub_2e3() {
      return this.ship;
   }

   public final Item[] sub_360() {
      return this.var_3af;
   }

   public final void sub_37b(Item[] var1) {
      this.var_3af = var1;
   }

   public final void sub_395(Ship[] var1) {
      this.ship = var1;
   }

   public final void sub_3f6(Agent[] var1) {
      this.var_5b0 = var1;
   }

   public final void addItem(Item var1) {
      if(this.var_3af == null) {
         this.var_3af = new Item[]{var1};
      } else {
         for(int var2 = 0; var2 < this.var_3af.length; ++var2) {
            if(this.var_3af[var2].equals(var1)) {
               this.var_3af[var2].changeAmount(var1.getAmount());
               return;
            }
         }

         Item[] var3 = new Item[this.var_3af.length + 1];
         System.arraycopy(this.var_3af, 0, var3, 0, this.var_3af.length);
         var3[var3.length - 1] = var1;
         this.var_3af = var3;
      }
   }

   public final Agent[] sub_41e() {
      return this.var_5b0;
   }

   public final String toString() {
      return this.name;
   }

   public final boolean sub_475(Station var1) {
      return this.name.equals(var1.name);
   }
}
