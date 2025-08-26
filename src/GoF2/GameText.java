/**
**		@class language
**		
**/

package GoF2;

import java.io.DataInputStream;
import java.io.InputStream;

import AE.GlobalStatus;

public final class GameText {
	
	public static final short[] LISTITEMWINDOW_KEY_TEXT_IDS = new short[]{(short)157, (short)157, (short)157, (short)37, (short)36, (short)36, (short)36, (short)36, (short)36, (short)50, (short)42, (short)53, (short)54, (short)57, (short)43, (short)59, (short)52, (short)53, (short)60, (short)44, (short)58, (short)56, (short)45, (short)58, (short)53, (short)55, (short)58, (short)45, (short)46, (short)47, (short)59, (short)48, (short)49, (short)58, (short)53, (short)69, (short)532};
	public static final short[] helpTitles = new short[]{(short)112, (short)296, (short)275, (short)79, (short)130, (short)218, (short)72, (short)146, (short)297, (short)63, (short)298};
	public static final short[] helpFull = new short[]{(short)306, (short)307, (short)308, (short)309, (short)312, (short)314, (short)315, (short)320, (short)321, (short)323, (short)324};
	public static final short[] soundLevels = new short[]{(short)8, (short)9, (short)10, (short)16};
	public static final short[] tips = new short[]{(short)165, (short)166, (short)167, (short)168, (short)169, (short)169, (short)170, (short)171, (short)172, (short)173, (short)174, (short)175, (short)176, (short)177};
	public static final short[] CAMPAIGN_MISSION_DESC = new short[]{(short)389, (short)389, (short)389, (short)389, (short)389, (short)389, (short)389, (short)389, (short)389, (short)389, (short)389, (short)389, (short)389, (short)373, (short)374, (short)389, (short)375, (short)389, (short)376, (short)389, (short)377, (short)377, (short)389, (short)378, (short)379, (short)389, (short)389, (short)389, (short)380, (short)389, (short)389, (short)389, (short)381, (short)382, (short)383, (short)384, (short)385, (short)389, (short)386, (short)389, (short)387, (short)389, (short)389, (short)388, (short)389};
	public static final short[] MISSION_START_MSG = new short[]{(short)201, (short)202, (short)203, (short)204, (short)205};
	public static final short[] MISSION_SUCCESS_MSG = new short[]{(short)195, (short)196, (short)197, (short)198, (short)199};
	public static final short[] MISSION_LOST_MSG = new short[]{(short)206, (short)207, (short)208, (short)209, (short)210};
	private static final int[] catrogoryLengths_ = new int[]{12, 5, 1, 2, 8, 5, 5, 5, 5, 6, 8, 17, 19, 9, 9, 4, 9, 12, 8, 12, 14, 9, 3, 14, 19, 9, 27, 5, 4, 13, 6, 17, 2, 9, 4, 7, 2, 10, 2, 4, 8, 6, 12, 12, 7, 9, 32};
	private static String[] langEntries = new String[1198];
	private static String[] items = new String[GlobalStatus.max_items];
	private static String[] itemDescriptionFile = new String[GlobalStatus.max_items];
	private static String[] agentsLangFile = new String[GlobalStatus.max_agents];
	private static String[][] categories_ = new String[47][];
	private static int language = GlobalStatus.language;
	private static String langPath;
	private static int tempId;
	private static int tempId2;
	
	
	
	public final void setLanguage(int var1) {
		
		langPath = "/Resource/lang/";
		language = var1;
		switch(var1) {
			case 0: langPath = langPath + "de/"; break;
			case 1: langPath = langPath + "en/"; break;
			case 2: langPath = langPath + "es/"; break;
			case 3: langPath = langPath + "fr/"; break;
			case 4: langPath = langPath + "it/"; break;
			case 5: langPath = langPath + "cz/"; break;
			case 6: langPath = langPath + "ru/"; break;
			case 7: langPath = langPath + "pl/"; break;
			case 8: langPath = langPath + "pt/";
		}
		
		if(language == 0) {
			this.readLangFile(langEntries, "de");
			this.loadItemLangFile(items, "de");
			this.loadItemDescriptionLangFile(itemDescriptionFile, "de");
			this.loadAgentsLangFile(agentsLangFile, "de");
		} else if(language == 6) {
			this.readLangFile(langEntries, "ru");
			this.loadItemLangFile(items, "ru");
			this.loadItemDescriptionLangFile(itemDescriptionFile, "ru");
			this.loadAgentsLangFile(agentsLangFile, "ru");
		} else {
			this.readLangFile(langEntries, "en");
			this.loadItemLangFile(items, "en");
			this.loadItemDescriptionLangFile(itemDescriptionFile, "en");
			this.loadAgentsLangFile(agentsLangFile, "en");
		}
		
		init();
		
	}
	
	private void loadAgentsLangFile(String[] langFile, String langCode) {
		InputStream var3 = null;
        int var6;

        try {
            var3 = this.getClass().getResourceAsStream(langPath + langCode + "_agents.lang");
            DataInputStream var5 = new DataInputStream(var3);

            for (var6 = 0; var6 < langFile.length; ++var6) {
                langFile[var6] = var5.readUTF();
            }

            var5.close();

        } catch (Exception var4) {
            for (var6 = 0; var6 < langFile.length; ++var6) {
                langFile[var6] = "<ERROR|AGENTS_LANG>";
            }
        }
    }
	
	public final String getNamedParameterAgents(int agent) {
		
        if(agent < agentsLangFile.length) {
            return agentsLangFile[agent];
        }
		
        return "<ERROR|AGENT_ID>";
    }
	
	private void loadItemDescriptionLangFile(String[] langFile, String langCode) {
		InputStream var3 = null;
        int var6;

        try {
            var3 = this.getClass().getResourceAsStream(langPath + langCode + "_items_description.lang");
            DataInputStream var5 = new DataInputStream(var3);

            for (var6 = 0; var6 < langFile.length; ++var6) {
                langFile[var6] = var5.readUTF();
            }

            var5.close();

        } catch (Exception var4) {
            for (var6 = 0; var6 < langFile.length; ++var6) {
                langFile[var6] = "<ERROR|DESRIPTION_LANG>";
            }
        }
    }
	
	public final String getNamedParameterItemsDescription(int itemId) {
		
        if(itemId < itemDescriptionFile.length) {
            return itemDescriptionFile[itemId];
        }
		
        return "<ERROR|ITEM_DESCRIPTION_FOUND>";
    }
	
	private void loadItemLangFile(String[] langFile, String langCode) {
		InputStream var3 = null;
        int var6;

        try {
            var3 = this.getClass().getResourceAsStream(langPath + langCode + "_items.lang");
            DataInputStream var5 = new DataInputStream(var3);

            for (var6 = 0; var6 < langFile.length; ++var6) {
                langFile[var6] = var5.readUTF();
            }

            var5.close();

        } catch (Exception var4) {
            for (var6 = 0; var6 < langFile.length; ++var6) {
                langFile[var6] = "<ERROR|ITEM_LANG>";
            }
        }
    }
	
	public final String getNamedParameterItems(int itemId) {
		
        if(itemId < items.length) {
            return items[itemId];
        }
		
        return "<ERROR|ITEM_NOT_FOUND>";
    }
	
	private void readLangFile(String[] langFile, String langCode) {
		
		InputStream var3 = null;
		int index;
		
		try {
			
			var3 = this.getClass().getResourceAsStream(langPath + langCode + ".lang");
			DataInputStream var5 = new DataInputStream(var3);
			
			for(index = 0; index < langFile.length; ++index) {
				langFile[index] = var5.readUTF();
			}
			
			var5.close();
		
		} catch (Exception var4) {
			
			for(index = 0; index < langFile.length; ++index) {
				
				langFile[index] = "<ERROR|LANG>";
				
			}
			
		}
		
	}
	
	public final String getText(int index) {
		
		if(index < langEntries.length) {
			return langEntries[index];
		}
		
		if(categories_[tempId][tempId2] == null) {
			this.readLangFile(categories_[tempId], "" + tempId);
		}
		
		return categories_[tempId][tempId2];
		
	}
	
	public static void init() {
		
		for(int var0 = 0; var0 < categories_.length; ++var0) {
			
			categories_[var0] = new String[catrogoryLengths_[var0]];
			
		}
		
	}
	
}
