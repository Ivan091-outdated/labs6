package edu;

import java.util.*;


public class StringUtils {

    @SuppressWarnings("StringRepeatCanBeUsed")
    public static String repeat(String pattern, int repeat) {
        if (repeat < 0) {
            throw new IllegalArgumentException("Repeat must be greater than 0 but was " + repeat);
        }
        var stringAcc = new StringBuilder(pattern.length() * repeat);
        for (int i = 0; i < repeat; i++) {
            stringAcc.append(pattern);
        }
        return stringAcc.toString();
    }

    public static String repeat(String str, String separator, int repeat) {
        if (repeat < 0) {
            throw new IllegalArgumentException("Repeat must be greater than 0 but was " + repeat);
        }
        if (repeat == 0){
            return "";
        }
        var stringAcc = new StringBuilder((str.length() + separator.length()) * repeat + str.length());
        for (int i = 0; i < repeat - 1; i++) {
            stringAcc.append(str);
            stringAcc.append(separator);
        }
        stringAcc.append(str);
        return stringAcc.toString();
    }

    public static String keep(String str, String pattern){
        if (str == null && pattern == null){
            throw new NullPointerException();
        }
        if (str == null){
            return null;
        }
        if (pattern == null){
            return "";
        }
        var chars = new HashSet<Character>();
        for (var ch : pattern.toCharArray()){
            chars.add(ch);
        }
        var stringAcc = new StringBuilder(str.length());
        for (var ch : str.toCharArray()){
            if (chars.contains(ch)){
                stringAcc.append(ch);
            }
        }
        return stringAcc.toString();
    }
}
