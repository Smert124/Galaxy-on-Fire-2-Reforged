package GoF2;

import AE.GlobalStatus;

// диалоговые окна в миссиях

public final class Dialogue {

   private static short[][] CAMPAIGN_BRIEFING = new short[][]{{(short)16, (short)853}, {(short)0, (short)0}, {(short)0, (short)883, (short)2, (short)884, (short)0, (short)885, (short)2, (short)886, (short)16, (short)887}, {(short)0, (short)0}, {(short)2, (short)896}, {(short)0, (short)0}, {(short)0, (short)0}, {(short)2, (short)906, (short)16, (short)907, (short)16, (short)908}, {(short)0, (short)0}, {(short)0, (short)0}, {(short)0, (short)0}, {(short)0, (short)947}, {(short)0, (short)0}, {(short)0, (short)0}, {(short)0, (short)967}, {(short)0, (short)0}, {(short)1, (short)981, (short)0, (short)982}, {(short)0, (short)0}, {(short)0, (short)0}, {(short)0, (short)0}, {(short)0, (short)0}, {(short)1, (short)1016, (short)16, (short)1017}, {(short)0, (short)0}, {(short)5, (short)1026}, {(short)6, (short)1041, (short)0, (short)1042, (short)19, (short)1043, (short)6, (short)1044, (short)0, (short)1045, (short)6, (short)1046}, {(short)0, (short)0}, {(short)6, (short)1055, (short)0, (short)1056, (short)6, (short)1057}, {(short)0, (short)0}, {(short)0, (short)1068, (short)19, (short)1069, (short)0, (short)1070}, {(short)0, (short)1071, (short)0, (short)1072}, {(short)0, (short)0}, {(short)0, (short)0}, {(short)0, (short)1095}, {(short)0, (short)0}, {(short)0, (short)0}, {(short)0, (short)0}, {(short)7, (short)1133, (short)0, (short)1134}, {(short)0, (short)0}, {(short)0, (short)1145}, {(short)0, (short)0}, {(short)0, (short)1158, (short)7, (short)1159, (short)0, (short)1160, (short)8, (short)1161, (short)0, (short)1162}, {(short)0, (short)1165, (short)7, (short)1166}, {(short)0, (short)0}, {(short)0, (short)0}, {(short)0, (short)0}, {(short)0, (short)0}};
   private static short[][] CAMPAIGN_SUCCESS = new short[][]{{(short)0, (short)0}, {(short)0, (short)870, (short)2, (short)871, (short)0, (short)872, (short)2, (short)873, (short)0, (short)874, (short)2, (short)875, (short)0, (short)876, (short)2, (short)877, (short)0, (short)878, (short)2, (short)879, (short)0, (short)880, (short)2, (short)881, (short)0, (short)882}, {(short)2, (short)888, (short)0, (short)889, (short)16, (short)890}, {(short)2, (short)891, (short)0, (short)892, (short)2, (short)893, (short)0, (short)894, (short)2, (short)895}, {(short)0, (short)897, (short)2, (short)898}, {(short)2, (short)899, (short)0, (short)900, (short)2, (short)901, (short)0, (short)902, (short)2, (short)903, (short)16, (short)904}, {(short)2, (short)905}, {(short)2, (short)911, (short)0, (short)912, (short)2, (short)913, (short)0, (short)914, (short)2, (short)915}, {(short)2, (short)916, (short)0, (short)917, (short)2, (short)918, (short)0, (short)919, (short)2, (short)920, (short)0, (short)921, (short)2, (short)922, (short)0, (short)923, (short)2, (short)924}, {(short)17, (short)925, (short)17, (short)926, (short)2, (short)927, (short)0, (short)928, (short)2, (short)929, (short)0, (short)930, (short)2, (short)931, (short)0, (short)932, (short)2, (short)933, (short)16, (short)934}, {(short)3, (short)935, (short)0, (short)936, (short)3, (short)937, (short)0, (short)938, (short)3, (short)939, (short)0, (short)940, (short)3, (short)941, (short)0, (short)942, (short)3, (short)943, (short)0, (short)944, (short)3, (short)945, (short)0, (short)946}, {(short)0, (short)948, (short)4, (short)949, (short)0, (short)950, (short)4, (short)951, (short)0, (short)952, (short)4, (short)953, (short)0, (short)954, (short)4, (short)955, (short)0, (short)956}, {(short)3, (short)957, (short)0, (short)958, (short)3, (short)959, (short)0, (short)960, (short)3, (short)961, (short)0, (short)962, (short)3, (short)963, (short)16, (short)964}, {(short)3, (short)965, (short)0, (short)966}, {(short)17, (short)972}, {(short)1, (short)973, (short)0, (short)974, (short)1, (short)975, (short)0, (short)976, (short)1, (short)977, (short)12, (short)978, (short)0, (short)979, (short)1, (short)980}, {(short)1, (short)985, (short)0, (short)986, (short)1, (short)987}, {(short)1, (short)988, (short)0, (short)989, (short)1, (short)990, (short)0, (short)991, (short)1, (short)992, (short)0, (short)993, (short)1, (short)994, (short)0, (short)995, (short)1, (short)996, (short)0, (short)997, (short)1, (short)998, (short)0, (short)999, (short)1, (short)1000, (short)0, (short)1001, (short)1, (short)1002}, {(short)0, (short)1003, (short)13, (short)1004, (short)0, (short)1005, (short)13, (short)1006, (short)0, (short)1007, (short)1, (short)1008, (short)0, (short)1009}, {(short)1, (short)1010, (short)0, (short)1011, (short)1, (short)1012, (short)0, (short)1013, (short)1, (short)1014}, {(short)0, (short)1015}, {(short)1, (short)1021}, {(short)5, (short)1022, (short)0, (short)1023, (short)5, (short)1024, (short)16, (short)1025}, {(short)6, (short)1027, (short)0, (short)1028, (short)6, (short)1029, (short)0, (short)1030, (short)6, (short)1031, (short)0, (short)1032, (short)6, (short)1033, (short)0, (short)1034, (short)6, (short)1035, (short)0, (short)1036, (short)6, (short)1037, (short)0, (short)1038, (short)6, (short)1039, (short)0, (short)1040}, {(short)0, (short)0}, {(short)6, (short)1049, (short)0, (short)1050, (short)6, (short)1051, (short)0, (short)1052, (short)6, (short)1053, (short)0, (short)1054}, {(short)6, (short)1058, (short)0, (short)1059}, {(short)16, (short)1060, (short)0, (short)1061, (short)6, (short)1062, (short)0, (short)1063, (short)6, (short)1064, (short)0, (short)1065, (short)6, (short)1066, (short)0, (short)1067}, {(short)0, (short)0}, {(short)0, (short)1078}, {(short)1, (short)1079, (short)0, (short)1080, (short)1, (short)1081}, {(short)1, (short)1083, (short)0, (short)1084, (short)1, (short)1085, (short)0, (short)1086, (short)1, (short)1087, (short)0, (short)1088, (short)1, (short)1089, (short)0, (short)1090, (short)1, (short)1091, (short)0, (short)1092, (short)1, (short)1093, (short)0, (short)1094}, {(short)20, (short)1096, (short)0, (short)1097, (short)20, (short)1098, (short)0, (short)1099, (short)20, (short)1100, (short)0, (short)1101, (short)20, (short)1102, (short)0, (short)1103}, {(short)20, (short)1104, (short)0, (short)1105, (short)6, (short)1106, (short)0, (short)1107, (short)6, (short)1108, (short)0, (short)1109, (short)6, (short)1110, (short)0, (short)1111}, {(short)1, (short)1112, (short)0, (short)1113, (short)1, (short)1114, (short)0, (short)1115, (short)1, (short)1116, (short)0, (short)1117, (short)1, (short)1118, (short)0, (short)1119, (short)1, (short)1120}, {(short)7, (short)1121, (short)0, (short)1122, (short)7, (short)1123, (short)0, (short)1124, (short)7, (short)1125, (short)0, (short)1126, (short)7, (short)1127, (short)0, (short)1128, (short)7, (short)1129, (short)0, (short)1130, (short)7, (short)1131, (short)0, (short)1132}, {(short)7, (short)1135, (short)0, (short)1136}, {(short)1, (short)1137, (short)0, (short)1138, (short)1, (short)1139, (short)0, (short)1140, (short)1, (short)1141, (short)0, (short)1142, (short)1, (short)1143, (short)0, (short)1144}, {(short)0, (short)1147, (short)1, (short)1148}, {(short)1, (short)1149, (short)0, (short)1150, (short)1, (short)1151, (short)0, (short)1152, (short)1, (short)1153, (short)0, (short)1154, (short)1, (short)1155, (short)0, (short)1156, (short)1, (short)1157}, {(short)0, (short)0}, {(short)0, (short)1167, (short)7, (short)1168, (short)0, (short)1169, (short)7, (short)1170, (short)0, (short)1171}, {(short)1, (short)1172, (short)0, (short)1173, (short)1, (short)1174, (short)0, (short)1175, (short)1, (short)1176, (short)0, (short)1177, (short)1, (short)1178, (short)0, (short)1179, (short)1, (short)1180}, {(short)0, (short)1181, (short)1, (short)1182, (short)0, (short)1183, (short)1, (short)1184, (short)0, (short)1185, (short)6, (short)1186, (short)0, (short)1187, (short)6, (short)1188, (short)1, (short)1189, (short)6, (short)1190, (short)1, (short)1191, (short)0, (short)1192, (short)6, (short)1193}, {(short)0, (short)1194, (short)0, (short)1195, (short)0, (short)1196, (short)0, (short)1197}, {(short)0, (short)0}};
   private byte[] face;
   private String message;
   private String name;
   private TextBox textBox;
   private int type;
   private int page;
   private Mission mission;
   private Popup popup;
   private boolean popupOpen;
   private Level level;


   public Dialogue(Mission var1, Level var2, int var3) {
      this();
      this.level = var2;
      this.set(var1, var3);
   }

   public Dialogue() {
      this.popup = new Popup();
      this.popup.set(GlobalStatus.gameText.getText(216), true);
      this.popupOpen = false;
   }

   public Dialogue(String var1, String var2, byte[] var3) {
      this();
      if(this.textBox == null) {
         this.textBox = new TextBox(15, 25, GlobalStatus.var_e75 - 10 - 20, GlobalStatus.var_eb6 - 22 - 8 - 40 + 13, (String)null);
      }

      this.textBox.set(var3, var2, true);
      this.textBox.setText(var1);
      this.name = var2;
      TextBox var4 = this.textBox;
      this.textBox.topPadding = 0;
   }

   public Dialogue(String var1) {
      this(var1, GlobalStatus.gameText.getText(835), Globals.CHAR_COMPUTER);
   }

   public static boolean hasBriefingDialogue(int var0) {
      return var0 < CAMPAIGN_BRIEFING.length?CAMPAIGN_BRIEFING[var0][1] != 0:false;
   }

   public static boolean hasSuccessDialogue(int var0) {
      return var0 < CAMPAIGN_BRIEFING.length?CAMPAIGN_SUCCESS[var0][1] != 0:false;
   }

   public final void set(Mission var1, int var2) {
      this.mission = var1;
      this.type = var2;
      Agent var4;
      if(var2 == 2) {
         if((var4 = var1.getAgent()) != null && !var4.isGenericAgent_()) {
            var4.sub_789(false);
         }

         var1.setFailed(true);
      } else if(var2 == 1) {
         var4 = null;
         var1.setWon(true);
      }

      this.page = 0;
      if(this.textBox == null) {
         this.textBox = new TextBox(15, 25, GlobalStatus.var_e75 - 10 - 20, GlobalStatus.var_eb6 - 22 - 8 - 40 + 13, (String)null);
      }

      try {
         this.loadContent_();
      } catch (Exception var3) {
         var3.printStackTrace();
      }

      GlobalStatus.soundManager.playSfx(3);
   }

   public final void OnRelease() {
      this.face = null;
      this.textBox = null;
   }

   public final boolean OnKeyPress_(int var1) { // реакция на нажатия кнопок в окне диалога
      if(!this.popupOpen) {
         boolean var10000;
         if((var1 == 16384) || Layout.dialogueButtonNext.getStandartButtonPressed()) {
			Layout.dialogueButtonNext.standartButtonActive = false;
            if(this.page < this.length() - 1) {
               ++this.page;
               this.loadContent_();
               var10000 = true;
            } else {
               var10000 = false;
            }

            if(!var10000) {
               return false;
            }
         }

         if((var1 == 8192) || Layout.dialogueButtonBack.getStandartButtonPressed()) {
			Layout.dialogueButtonBack.standartButtonActive = false;
            if(this.page > 0) {
               --this.page;
               this.loadContent_();
               var10000 = true;
            } else {
               var10000 = false;
            }
         }
      }

      if((var1 == 256 && (!this.lastPageOn() || !this.firstPageOn())) || Layout.dialogueButtonSkip.getStandartButtonPressed()) {
		 Layout.dialogueButtonSkip.standartButtonActive = false;
         this.popupOpen = !this.popupOpen;
         if(!this.popupOpen && this.popup.getChoice()) {
            return false;
         }
      }
	  
	  if(this.popup.AEButton[0].getStandartButtonPressed() && this.popupOpen) {
		  this.popup.left();
		  OnKeyPress_(256);
		  this.popup.AEButton[0].standartButtonActive = false;
		  if(!this.popupOpen && this.popup.getChoice()) {
            return false;
         }
	  }
	  
	  if(this.popup.AEButton[1].getStandartButtonPressed() && this.popupOpen) {
		  this.popup.right();
		  OnKeyPress_(256);
		  this.popup.AEButton[1].standartButtonActive = false;
		  if(!this.popupOpen && this.popup.getChoice()) {
            return false;
         }
	  }
	  
      if(var1 == 4 && this.popupOpen) {
         this.popup.left();
      }

      if(var1 == 32 && this.popupOpen) {
         this.popup.right();
      }

      return true;
   }

   public final boolean handleScrollPress_(int var1, int var2) { // textbox scroll
      if((var1 & 64) != 0) {
         this.textBox.scrollUp(var2);
      }

      if((var1 & 2) != 0) {
         this.textBox.scrollDown(var2);
      }

      return true;
   }

   private int length() {
      return this.mission.isCampaignMission()?(this.type == 0?CAMPAIGN_BRIEFING[Status.getCurrentCampaignMission()].length / 2:(this.type == 1?CAMPAIGN_SUCCESS[Status.getCurrentCampaignMission()].length / 2:1)):1;
   }

   private boolean firstPageOn() {
      return this.page == 0;
   }

   private boolean lastPageOn() {
      return this.page == this.length() - 1;
   }

   private void loadContent_() {
      short var1 = -1;
      if(!this.mission.isCampaignMission() && (this.type != 2 || Status.getCurrentCampaignMission() != 42)) {
         if(this.page % 2 == 0) {
            this.face = this.mission.getClientImage();
            this.name = this.mission.getClientName();
         } else {
            this.face = Globals.CHAR_IMAGES[0];
            this.name = GlobalStatus.gameText.getText(819);
         }

         if(this.type == 0) {
            if(this.mission.getType() == 12) {
               this.message = GlobalStatus.gameText.getText(194);
            } else if(this.mission.getType() == 7) {
               this.message = GlobalStatus.gameText.getText(200);
            } else {
               this.message = GlobalStatus.gameText.getText(GameText.MISSION_START_MSG[GlobalStatus.random.nextInt(GameText.MISSION_START_MSG.length)]);
            }
         } else if(this.type == 1) {
            boolean var5 = this.mission.getAgent() != null?this.mission.getAgent().isSpecialAgent():false;
            if(this.mission.getType() == 12) {
               this.message = GlobalStatus.gameText.getText(192) + (var5?"\n\n" + Status.replaceTokens(GlobalStatus.gameText.getText(211), this.mission.getAgent().getStationName(), "#S"):"");
               this.message = Status.replaceTokens(Status.replaceTokens(this.message, this.level.var_633 + "", "#Q1"), this.level.var_614 + "", "#Q2");
            } else {
               this.message = GlobalStatus.gameText.getText(GameText.MISSION_SUCCESS_MSG[GlobalStatus.random.nextInt(GameText.MISSION_SUCCESS_MSG.length)]) + "\n\n" + (var5?Status.replaceTokens(GlobalStatus.gameText.getText(211), this.mission.getAgent().getStationName(), "#S"):GlobalStatus.gameText.getText(97));
            }

            Status.getStanding().increase(this.mission.getClientRace());
         } else if(this.mission.getType() == 12) {
            this.message = Status.replaceTokens(Status.replaceTokens(GlobalStatus.gameText.getText(193), this.level.var_633 + "", "#Q1"), this.level.var_614 + "", "#Q2");
         } else {
            this.message = GlobalStatus.gameText.getText(GameText.MISSION_LOST_MSG[GlobalStatus.random.nextInt(GameText.MISSION_LOST_MSG.length)]) + "\n\n" + GlobalStatus.gameText.getText(213);
         }
      } else {
         var1 = this.type == 0?CAMPAIGN_BRIEFING[Status.getCurrentCampaignMission()][2 * this.page]:(this.type == 1?CAMPAIGN_SUCCESS[Status.getCurrentCampaignMission()][2 * this.page]:16);
         this.face = Globals.CHAR_IMAGES[var1];
         this.name = GlobalStatus.gameText.getText(var1 + 819);
         if(this.type == 0) {
            this.message = GlobalStatus.gameText.getText(CAMPAIGN_BRIEFING[Status.getCurrentCampaignMission()][2 * this.page + 1]);
         } else if(this.type == 1) {
            this.message = GlobalStatus.gameText.getText(CAMPAIGN_SUCCESS[Status.getCurrentCampaignMission()][2 * this.page + 1]);
         } else {
            int var2;
            String var4 = (var2 = Status.getCurrentCampaignMission()) != 38 && var2 != 40 && var2 != 41?"":GlobalStatus.gameText.getText(256);
            this.message = GlobalStatus.gameText.getText(213) + "\n" + var4 + "\n\n" + GlobalStatus.gameText.getText(156);
         }
      }

      this.textBox.set(this.face, this.name, var1 == 16?true:this.page % 2 == 1);
      this.textBox.setText(this.message);
      TextBox var6 = this.textBox;
      this.textBox.topPadding = 0;
      byte var3;
      if(var1 == 19) {
         var3 = 3;
         var6 = this.textBox;
         this.textBox.font = var3;
      } else {
         var3 = 1;
         var6 = this.textBox;
         this.textBox.font = var3;
      }
   }

   public final void draw() { // окно с диалогом
      Layout.drawTextBox(this.name, this.textBox.startX, this.textBox.startY, this.textBox.rectWidth, this.textBox.getHeight_(), true);
      this.textBox.draw();
      Layout.drawButtonsPanel(this.lastPageOn()?(this.mission.isCampaignMission() && this.type == 2?GlobalStatus.gameText.getText(67):(this.type != 2 && this.type != 1?GlobalStatus.gameText.getText(76):GlobalStatus.gameText.getText(253))):GlobalStatus.gameText.getText(75), !this.firstPageOn()?GlobalStatus.gameText.getText(74):"", !this.lastPageOn() || !this.firstPageOn());
      if(this.popupOpen) {
         this.popup.draw();
      }

   }

    public final void drawInterupring_() { // рисует окно для всплывающих подсказок
		Layout.sub_1e6();
		Layout.drawTextBox(this.name, this.textBox.startX, this.textBox.startY, this.textBox.rectWidth, this.textBox.getHeight_() + 20, true);
		this.textBox.draw();
		Layout.drawHintsWindow(GlobalStatus.gameText.getText(35), this.textBox.startX, this.textBox.startY + this.textBox.getHeight_() + 20, this.textBox.rectWidth, true, true);
	}

}
