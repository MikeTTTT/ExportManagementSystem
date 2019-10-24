package utils;

public class StringUtils {

    public static boolean equalsIgnoreCase(String string1, String string2){
        // 为空情况下都为空才相等
        if (null == string1 || null == string2){
            return string1 == null && string2 == null;
        }else {
            return string1.equalsIgnoreCase(string2);
        }
    }

    public static boolean equals(String string1, String string2){
        if (null == string1 || null == string2){
            return string1 == null && string2 == null;
        }else {
            return string1.equals(string2);
        }
    }
}
