package HardEngine;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import AE.GlobalStatus;

public final class TextParser {
	
	public static Class Class_;
	
	public static Class getClassForName(String var0) {
		try {
			return Class.forName(var0);
		} catch (ClassNotFoundException var1) {
			throw new NoClassDefFoundError(var1.getMessage());
		}
	}
	
	public static String getText(String path) {
		InputStream file = (Class_ == null?(Class_ = getClassForName("java.lang.Class")):Class_).getResourceAsStream(path);
		DataInputStream dis = new DataInputStream(file);
        StringBuffer strBuff = new StringBuffer();
        int ch = 0;
		try {
			while((ch = dis.read()) != -1) {
				strBuff.append((char ) ((ch >= 0xc0 && ch <= 0xFF) ? (ch + 0x350) : ch));
            }
            dis.close();
        } catch(Exception e) {
			GlobalStatus.CATCHED_ERROR = "getTextError: " + e;
            System.err.println(GlobalStatus.CATCHED_ERROR);
        }
        return strBuff.toString();
	}
	
	public static String[] split(String original, String separator) {
		Vector nodes = new Vector();
		int index = original.indexOf(separator);
		while(index >= 0) {
			nodes.addElement(original.substring(0, index));
			original = original.substring(index + separator.length());
			index = original.indexOf(separator);
		}
		nodes.addElement(original);
		String[] result = new String[nodes.size()];
		for(int i = 0; i < nodes.size(); i++) {
			result[i] = (String)nodes.elementAt(i);
		}
		return result;
	}
	
	public static int indexOf(StringBuffer sb, String str, int start) {
		int index = -1;
		if((start>=sb.length() || start<-1) || str.length()<=0) return index;
		char[] tofind = str.toCharArray();
		outer: for(;start<sb.length(); start++){
			char c = sb.charAt(start);
			if(c==tofind[0]){
				if(1==tofind.length) return start;
				inner: for(int i = 1; i<tofind.length;i++) {
					char find = tofind[i];
					int currentSourceIndex = start+i;
					if(currentSourceIndex<sb.length()) {
						char source = sb.charAt(start+i);
						if(find==source) {
							if(i==tofind.length-1) {
								return start;
							}
							continue inner;
						} else {
							start++;
							continue outer;
						}
					} else {
						return -1;
					}
				}
			}
		}
		return index;
	}
	
	public static String arrayToString(int[] array) {
		if(array == null) {
			return "null";
		}
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for(int i = 0; i < array.length; i++) {
			sb.append(array[i]);
			if(i < array.length - 1) {
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}
	
	public static String removeComments(String text) {
		if(text == null) {
			return "";
		}
		
		StringBuffer result = new StringBuffer();
		boolean inComment = false;
		
		for(int i = 0; i < text.length(); i++) {
			char currentChar = text.charAt(i);
			if(inComment) {
				if(currentChar == '#' && i > 0 && text.charAt(i-1) != '\\') {
					inComment = false;
				}
			} else {
				if(currentChar == '#' && (i == 0 || text.charAt(i-1) != '\\')) {
					inComment = true;
				} else {
					result.append(currentChar);
				}
			}
		}
		return result.toString();
	}
	
}