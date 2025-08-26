package AE;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class ImageFont {

   private Image fontTexture;
   private short[] posX;
   private short[] posY;
   private byte[] widths;
   private byte spacingY;
   private byte tileHeight;
   private byte tileWidth;
   private static Graphics graphics;
   private byte spacingX;
   private byte offsetY = 0;


   public ImageFont(String var1, Graphics var2, int var3, int var4, int var5) {
      graphics = var2;

      try {
         this.fontTexture = Image.createImage(var1);
         this.posX = new short[var4 * var5];
         this.posY = new short[this.posX.length];
         this.widths = new byte[this.posX.length];
         this.tileHeight = this.spacingY = (byte)(this.fontTexture.getHeight() / var4);
         this.tileWidth = (byte)(this.fontTexture.getWidth() / var5);
         int[] var7 = new int[this.fontTexture.getWidth() * this.fontTexture.getHeight()];
         this.fontTexture.getRGB(var7, 0, this.fontTexture.getWidth(), 0, 0, this.fontTexture.getWidth(), this.fontTexture.getHeight());

         for(int var8 = 0; var8 < var5; ++var8) {
            for(var3 = 0; var3 < var4; ++var3) {
               this.posX[var8 + var5 * var3] = (short)(var8 * this.tileWidth);
               this.posY[var8 + var5 * var3] = (short)(var3 * this.spacingY);
               this.widths[var8 + var5 * var3] = this.getSymbolWidth(var8 * this.tileWidth, var3 * this.spacingY, var7);
            }
         }

         this.widths[charToSymbolIdx('1')] = this.widths[charToSymbolIdx('0')];
         this.spacingX = 1;
         this.widths[0] = 4;
      } catch (Exception var6) {
         this.fontTexture = null;
      }

      System.gc();
   }

   public static void setGraphics(Graphics var0) {
      graphics = var0;
   }

   public final void setSpacingX(int var1) {
      this.spacingX = (byte)var1;
   }

   private byte getSymbolWidth(int var1, int var2, int[] var3) {
      byte var4 = (byte)(this.tileWidth - 1);
      byte var5 = 0;

      while(var5 < this.tileWidth) {
         byte var6 = 0;

         while(true) {
            if(var6 < this.spacingY) {
               if(var3[var5 + var1 + (var6 + var2) * this.fontTexture.getWidth()] >>> 24 <= 0 || var3[var5 + var1 + (var6 + var2) * this.fontTexture.getWidth()] == -1) {
                  ++var6;
                  continue;
               }

               var4 = var5;
            }

            ++var5;
            break;
         }
      }

      return (byte)(var4 + 1);
   }

   public final int stringWidth(String var1) {
      int var2 = 0;
      boolean var3 = false;

      for(int var4 = 0; var4 < var1.length(); ++var4) {
         int var6;
         if((var6 = charToSymbolIdx(var1.charAt(var4))) == -1) {
            var6 = charToSymbolIdx('.');

            for(int var5 = 0; var5 < 3; ++var5) {
               var2 += this.widths[var6] + this.spacingX;
            }
         } else {
            var2 += this.widths[var6] + this.spacingX;
         }
      }

      return var2;
   }

   public final int subStringWidth(String var1, int var2, int var3) {
      int var4 = 0;
      boolean var5 = false;
      var2 = var2 >= 0?var2:0;
      var3 = var3 <= var1.length()?var3:var1.length();

      for(var2 = var2; var2 < var3; ++var2) {
         int var7;
         if((var7 = charToSymbolIdx(var1.charAt(var2))) == -1) {
            var7 = charToSymbolIdx('.');

            for(int var6 = 0; var6 < 3; ++var6) {
               var4 += this.widths[var7] + this.spacingX;
            }
         } else {
            var4 += this.widths[var7] + this.spacingX;
         }
      }

      return var4;
   }

   private static int charToSymbolIdx(char var0) {
      switch(var0) {
      case 32:
         return 0;
      case 33:
         return 1;
      case 34:
         return 2;
      case 35:
         return 3;
      case 36:
         return 4;
      case 37:
         return 5;
      case 38:
         return 6;
      case 39:
      case 96:
      case 8217:
         return 7;
      case 40:
         return 8;
      case 41:
         return 9;
      case 42:
         return 10;
      case 43:
         return 11;
      case 44:
         return 12;
      case 45:
         return 13;
      case 46:
         return 14;
      case 47:
         return 15;
      case 48:
         return 16;
      case 49:
         return 17;
      case 50:
         return 18;
      case 51:
         return 19;
      case 52:
         return 20;
      case 53:
         return 21;
      case 54:
         return 22;
      case 55:
         return 23;
      case 56:
         return 24;
      case 57:
         return 25;
      case 58:
         return 26;
      case 59:
         return 27;
      case 60:
         return 28;
      case 61:
         return 29;
      case 62:
         return 30;
      case 63:
         return 31;
      case 64:
         return 32;
      case 65:
      case 1040:
         return 33;
      case 66:
      case 1042:
         return 34;
      case 67:
      case 1057:
         return 35;
      case 68:
         return 36;
      case 69:
      case 1045:
         return 37;
      case 70:
         return 38;
      case 71:
         return 39;
      case 72:
      case 1053:
         return 40;
      case 73:
         return 41;
      case 74:
         return 42;
      case 75:
      case 1050:
         return 43;
      case 76:
         return 44;
      case 77:
      case 1052:
         return 45;
      case 78:
         return 46;
      case 79:
      case 1054:
         return 47;
      case 80:
      case 1056:
         return 48;
      case 81:
         return 49;
      case 82:
         return 50;
      case 83:
         return 51;
      case 84:
      case 1058:
         return 52;
      case 85:
         return 53;
      case 86:
         return 54;
      case 87:
         return 55;
      case 88:
      case 1061:
         return 56;
      case 89:
         return 57;
      case 90:
         return 58;
      case 91:
         return 59;
      case 92:
         return 60;
      case 93:
         return 61;
      case 94:
         return 62;
      case 95:
         return 63;
      case 97:
      case 170:
      case 1072:
         return 64;
      case 98:
         return 65;
      case 99:
      case 1089:
         return 66;
      case 100:
         return 67;
      case 101:
      case 1077:
         return 68;
      case 102:
         return 69;
      case 103:
         return 70;
      case 104:
         return 71;
      case 105:
         return 72;
      case 106:
         return 73;
      case 107:
         return 74;
      case 108:
         return 75;
      case 109:
         return 76;
      case 110:
         return 77;
      case 111:
      case 186:
      case 1086:
         return 78;
      case 112:
      case 1088:
         return 79;
      case 113:
         return 80;
      case 114:
         return 81;
      case 115:
         return 82;
      case 116:
         return 83;
      case 117:
         return 84;
      case 118:
         return 85;
      case 119:
         return 86;
      case 120:
      case 1093:
         return 87;
      case 121:
         return 88;
      case 122:
         return 89;
      case 123:
         return 90;
      case 124:
         return 91;
      case 125:
         return 92;
      case 126:
         return 93;
      case 153:
         return 96;
      case 161:
         return 97;
      case 169:
         return 95;
      case 171:
         return 98;
      case 174:
         return 94;
      case 187:
         return 99;
      case 191:
         return 100;
      case 192:
         return 101;
      case 193:
         return 102;
      case 194:
         return 103;
      case 195:
         return 104;
      case 196:
         return 105;
      case 198:
         return 106;
      case 199:
         return 107;
      case 200:
         return 108;
      case 201:
         return 109;
      case 202:
         return 110;
      case 203:
         return 111;
      case 204:
         return 112;
      case 205:
         return 113;
      case 206:
         return 114;
      case 207:
         return 115;
      case 209:
         return 116;
      case 210:
         return 117;
      case 211:
         return 118;
      case 212:
         return 119;
      case 213:
         return 120;
      case 214:
         return 121;
      case 217:
         return 122;
      case 218:
         return 123;
      case 219:
         return 124;
      case 220:
         return 125;
      case 221:
         return 203;
      case 223:
         return 126;
      case 224:
         return 128;
      case 225:
         return 129;
      case 226:
         return 130;
      case 227:
         return 131;
      case 228:
         return 132;
      case 230:
         return 133;
      case 231:
         return 134;
      case 232:
         return 135;
      case 233:
         return 136;
      case 234:
         return 137;
      case 235:
         return 138;
      case 236:
         return 139;
      case 237:
         return 140;
      case 238:
         return 141;
      case 239:
         return 142;
      case 241:
         return 143;
      case 242:
         return 144;
      case 243:
         return 145;
      case 244:
         return 146;
      case 245:
         return 147;
      case 246:
         return 148;
      case 249:
         return 149;
      case 250:
         return 150;
      case 251:
         return 151;
      case 252:
         return 152;
      case 253:
         return 213;
      case 255:
         return 153;
      case 260:
         return 223;
      case 261:
         return 231;
      case 262:
         return 224;
      case 263:
         return 232;
      case 268:
         return 204;
      case 269:
         return 214;
      case 270:
         return 205;
      case 271:
         return 215;
      case 280:
         return 225;
      case 281:
         return 233;
      case 282:
         return 206;
      case 283:
         return 216;
      case 321:
         return 226;
      case 322:
         return 234;
      case 323:
         return 227;
      case 324:
         return 235;
      case 327:
         return 207;
      case 328:
         return 217;
      case 338:
         return 154;
      case 339:
         return 155;
      case 344:
         return 208;
      case 345:
         return 218;
      case 346:
         return 228;
      case 347:
         return 236;
      case 352:
         return 209;
      case 353:
         return 219;
      case 356:
         return 210;
      case 357:
         return 220;
      case 366:
         return 211;
      case 367:
         return 221;
      case 376:
         return 127;
      case 377:
         return 229;
      case 378:
         return 237;
      case 379:
         return 230;
      case 380:
         return 238;
      case 381:
         return 212;
      case 382:
         return 222;
      case 1041:
         return 156;
      case 1043:
         return 157;
      case 1044:
         return 158;
      case 1046:
         return 159;
      case 1047:
         return 160;
      case 1048:
         return 161;
      case 1049:
         return 162;
      case 1051:
         return 163;
      case 1055:
         return 164;
      case 1059:
         return 165;
      case 1060:
         return 166;
      case 1062:
         return 167;
      case 1063:
         return 168;
      case 1064:
         return 169;
      case 1065:
         return 170;
      case 1066:
         return 171;
      case 1067:
         return 172;
      case 1068:
         return 173;
      case 1069:
         return 174;
      case 1070:
         return 175;
      case 1071:
         return 176;
      case 1073:
         return 177;
      case 1074:
         return 178;
      case 1075:
         return 179;
      case 1076:
         return 180;
      case 1078:
         return 181;
      case 1079:
         return 182;
      case 1080:
         return 183;
      case 1081:
         return 184;
      case 1082:
         return 185;
      case 1083:
         return 186;
      case 1084:
         return 187;
      case 1085:
         return 188;
      case 1087:
         return 189;
      case 1090:
         return 190;
      case 1091:
         return 191;
      case 1092:
         return 192;
      case 1094:
         return 193;
      case 1095:
         return 194;
      case 1096:
         return 195;
      case 1097:
         return 196;
      case 1098:
         return 197;
      case 1099:
         return 198;
      case 1100:
         return 199;
      case 1101:
         return 200;
      case 1102:
         return 201;
      case 1103:
         return 202;
      default:
         return 0;
      }
   }

   public final void setOffsetY(int var1) {
      this.offsetY = (byte)var1;
   }

   public final void drawString(String var1, int var2, int var3) {
      int var4 = 0;
      boolean var5 = false;

      for(int var6 = 0; var6 < var1.length(); ++var6) {
         int var8;
         if((var8 = charToSymbolIdx(var1.charAt(var6))) == -1) {
            var8 = charToSymbolIdx('.');

            for(int var7 = 0; var7 < 3; ++var7) {
               graphics.drawRegion(this.fontTexture, this.posX[var8], this.posY[var8], this.widths[var8], this.tileHeight, 0, var2 + var4, var3 + this.offsetY, 0);
               var4 += this.widths[var8] + this.spacingX;
            }
         } else {
            graphics.drawRegion(this.fontTexture, this.posX[var8], this.posY[var8], this.widths[var8], this.tileHeight, 0, var2 + var4, var3 + this.offsetY, 0);
            var4 += this.widths[var8] + this.spacingX;
         }
      }

   }

   public final void drawStringRightAlligned(String var1, int var2, int var3) {
      int var4 = 0;
      boolean var5 = false;

      for(int var6 = var1.length() - 1; var6 >= 0; --var6) {
         int var8;
         if((var8 = charToSymbolIdx(var1.charAt(var6))) == -1) {
            var8 = charToSymbolIdx('.');

            for(int var7 = 0; var7 < 3; ++var7) {
               var4 += this.widths[var8];
               graphics.drawRegion(this.fontTexture, this.posX[var8], this.posY[var8], this.widths[var8], this.tileHeight, 0, var2 - var4, var3 + this.offsetY, 0);
               var4 += this.spacingX;
            }
         } else {
            var4 += this.widths[var8];
            graphics.drawRegion(this.fontTexture, this.posX[var8], this.posY[var8], this.widths[var8], this.tileHeight, 0, var2 - var4, var3 + this.offsetY, 0);
            var4 += this.spacingX;
         }
      }

   }

   public final int getSpacingY() {
      return this.spacingY;
   }

   public final int getTileHeight() {
      return this.tileHeight;
   }

   public final void setSpacingY(int var1) {
      this.spacingY = (byte)var1;
   }
}
