package HardEngine;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import AE.GlobalStatus;

public final class TextParser {
    
    private static final java.util.Hashtable textCache = new java.util.Hashtable();
    private static final byte[] readBuffer = new byte[1024];
    public static Class Class_;
    
    public static Class getClassForName(String var0) {
        try {
            return Class.forName(var0);
        } catch (ClassNotFoundException var1) {
            throw new NoClassDefFoundError(var1.getMessage());
        }
    }
    
    public static String getText(String path) {
        String cached = (String)textCache.get(path);
        if(cached != null) {
            return cached;
        }
        
        InputStream file = null;
        DataInputStream dis = null;
        StringBuffer strBuff = null;
        
        try {
            if(Class_ == null) {
                Class_ = getClassForName("java.lang.Class");
            }
            file = Class_.getResourceAsStream(path);
            if(file == null) {
                return "";
            }
            
            dis = new DataInputStream(file);
            strBuff = new StringBuffer();
            
            int bytesRead;
            while((bytesRead = dis.read(readBuffer)) != -1) {
                for(int i = 0; i < bytesRead; i++) {
                    int ch = readBuffer[i] & 0xFF;
                    if(ch >= 0xC0 && ch <= 0xFF) {
                        strBuff.append((char)(ch + 0x350));
                    } else {
                        strBuff.append((char)ch);
                    }
                }
            }
            
            String result = strBuff.toString();
            textCache.put(path, result);
            
            return result;
            
        } catch(Exception e) {
            GlobalStatus.CATCHED_ERROR = "getTextError: " + e;
            System.err.println(GlobalStatus.CATCHED_ERROR);
            return "";
        } finally {
            try {
                if(dis != null) dis.close();
            } catch (IOException e) {
				
            }
            try {
                if(file != null) file.close();
            } catch (IOException e) {
				
            }
        }
    }
    
    public static String[] split(String original, String separator) {
        if(original == null || separator == null) {
            return new String[0];
        }
        
        if(original.length() == 0) {
            return new String[]{""};
        }
        
        int count = 0;
        int index = 0;
        int sepLen = separator.length();
        
        while((index = original.indexOf(separator, index)) != -1) {
            count++;
            index += sepLen;
        }
		
        String[] result = new String[count + 1];
        
        index = 0;
        int start = 0;
        int resultIndex = 0;
        
        while((index = original.indexOf(separator, start)) != -1) {
            result[resultIndex++] = original.substring(start, index);
            start = index + sepLen;
        }
        
        result[resultIndex] = original.substring(start);
        
        return result;
    }
	
    public static int indexOf(StringBuffer sb, String str, int start) {
        if (sb == null || str == null || start < 0 || start >= sb.length() || str.length() == 0) {
            return -1;
        }
        
        int sbLen = sb.length();
        int strLen = str.length();
        
        if(strLen == 1) {
            char ch = str.charAt(0);
            for (int i = start; i < sbLen; i++) {
                if (sb.charAt(i) == ch) {
                    return i;
                }
            }
            return -1;
        }
        
        if(start + strLen > sbLen) {
            return -1;
        }
        
        char firstChar = str.charAt(0);
        int maxStart = sbLen - strLen;
        
        for(int i = start; i <= maxStart; i++) {
            if(sb.charAt(i) != firstChar) {
                continue;
            }
            
            boolean found = true;
            for(int j = 1; j < strLen; j++) {
                if(sb.charAt(i + j) != str.charAt(j)) {
                    found = false;
                    break;
                }
            }
            
            if(found) {
                return i;
            }
        }
        
        return -1;
    }
    
    public static String arrayToString(int[] array) {
        if(array == null) {
            return "null";
        }
        
        int length = array.length;
        if(length == 0) {
            return "[]";
        }
		
        StringBuffer sb = new StringBuffer(length * 10);
        sb.append('[');
        sb.append(array[0]);
        
        for(int i = 1; i < length; i++) {
            sb.append(", ");
            sb.append(array[i]);
        }
        
        sb.append(']');
        return sb.toString();
    }
    
    public static String removeComments(String text) {
        if (text == null || text.length() == 0) {
            return "";
        }
        
        int length = text.length();
        StringBuffer result = new StringBuffer(length);
        boolean inComment = false;
        
        for(int i = 0; i < length; i++) {
            char currentChar = text.charAt(i);
            
            if(inComment) {
                if(currentChar == '#' && (i == 0 || text.charAt(i - 1) != '\\')) {
                    inComment = false;
                }
            } else {
                if(currentChar == '#' && (i == 0 || text.charAt(i - 1) != '\\')) {
                    inComment = true;
                } else {
                    result.append(currentChar);
                }
            }
        }
        
        return result.toString();
    }
    
    public static void clearCache() {
        textCache.clear();
    }
    
    public static void clearCachedText(String path) {
        textCache.remove(path);
    }
}