package GoF2;


public final class Agent {

   public String var_4f;
   public String wingman1Name;
   public String wingman2Name;
   private int var_30d;
   private int var_35c;
   private int var_3d9;
   private int var_430;
   private int stationId;
   private int var_4b1;
   private int var_50a;
   private boolean male;
   private int event;
   private int origin_;
   private int var_6c2;
   public int wingmanFriendsCount;
   private int var_71e;
   private int var_7a8;
   private int var_80a;
   private String var_839 = "";
   private String var_84d;
   public String var_875;
   private boolean var_88c;
   private boolean unused_0_;
   private byte[] var_91b;
   private Mission var_931;
   public boolean wasAskedForDifficulty;
   public boolean wasAskedForLocation;


   public Agent(int var1, String var2, int var3, int var4, int var5, boolean var6, int var7, int var8, int var9) {
      this.var_430 = var1;
      this.var_4f = var2;
      this.stationId = var3;
      this.var_4b1 = var4;
      this.var_50a = var5;
      this.male = var6;
      this.event = 0;
      this.var_7a8 = var7;
      if(var7 >= 0) {
         this.var_6c2 = 4;
      }

      this.var_80a = var8;
      if(var8 >= 0) {
         this.var_6c2 = 3;
      }

      this.var_3d9 = var9;
      this.origin_ = var1 >= 0?0:1;
      this.wingmanFriendsCount = 0;
      this.var_88c = false;
      this.unused_0_ = false;
      this.wasAskedForDifficulty = false;
      this.wasAskedForLocation = false;
   }

   public final int getMessageId() {
      return this.var_430;
   }

   public final String sub_81() {
      return this.var_4f;
   }

   public final int sub_8f() {
      return this.var_6c2;
   }

   public final int sub_166() {
      return this.var_50a;
   }

   public final int getSystemId() {
      return this.var_4b1;
   }

   public final int getStationId() {
      return this.stationId;
   }

   public final boolean isMale() {
      return this.male;
   }

   public final int getEvent() {
      return this.event;
   }

   public final void setEvent(int var1) {
      this.event = var1;
   }

   public final boolean isSpecialAgent() {
      return this.origin_ == 0;
   }

   public final boolean isGenericAgent_() {
      return this.origin_ == 1;
   }

   public final void setImageParts(byte[] var1) {
      this.var_91b = var1;
   }

   public final byte[] getFace() {
      return this.var_91b;
   }

   public final void setType(int var1) {
      this.var_6c2 = var1;
   }

   public final void sub_3c9(Mission var1) {
      this.var_931 = var1;
   }

   public final Mission sub_3d9() {
      return this.var_931;
   }

   public final boolean isKnown() {
      return this.event > 0;
   }

   public final int getSellSystemIndex() {
      return this.var_7a8;
   }

   public final int getSellBlueprintIndex() {
      return this.var_80a;
   }

   public final void setCosts(int var1) {
      this.var_71e = var1;
   }

   public final int getCosts() {
      return this.var_71e;
   }

   public final int getWingmanFriendsCount_() {
      return this.wingmanFriendsCount;
   }

   public final String getWingmanName(int var1) {
      return this.wingman1Name;
   }

   public final String[] getWingmenNames() {
      String[] var1;
      (var1 = new String[1 + this.wingmanFriendsCount])[0] = this.var_4f;
      if(this.wingmanFriendsCount > 0) {
         var1[1] = this.wingman1Name;
      }

      if(this.wingmanFriendsCount > 1) {
         var1[2] = this.wingman2Name;
      }

      return var1;
   }

   public final String getMessage() {
      return this.var_839;
   }

   public final void setMessage(String var1) {
      this.var_839 = var1;
   }

   public final int getSellItemIndex() {
      return this.var_30d;
   }

   public final int getSellItemQuantity() {
      return this.var_35c;
   }

   public final int getSellItemPrice() {
      return this.var_3d9;
   }

   public final void setSellItem(int var1, int var2, int var3) {
      this.var_30d = var1;
      this.var_35c = var2;
      this.var_3d9 = var3;
   }

   public final boolean isAccepted() {
      return this.var_88c;
   }

   public final void sub_789(boolean var1) {
      this.var_88c = var1;
   }

   public final boolean getUnused0_() {
      return this.unused_0_;
   }

   public final void setUnused0_(boolean var1) {
      this.unused_0_ = var1;
   }

   public final String getStationName() {
      return this.var_84d;
   }

   public final void setAgentsStationName(String var1) {
      this.var_84d = var1.equals("")?null:var1;
   }

   public final void setAgentsSystemName(String var1) {
      this.var_875 = var1.equals("")?null:var1;
   }
}
