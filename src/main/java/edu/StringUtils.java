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
        if (repeat == 0) {
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

    public static String keep(String str, String pattern) {
        if (str == null && pattern == null) {
            throw new NullPointerException();
        }
        if (str == null) {
            return null;
        }
        if (pattern == null) {
            return "";
        }
        var chars = new HashSet<Character>();
        for (var ch : pattern.toCharArray()) {
            chars.add(ch);
        }
        var stringAcc = new StringBuilder(str.length());
        for (var ch : str.toCharArray()) {
            if (chars.contains(ch)) {
                stringAcc.append(ch);
            }
        }
        return stringAcc.toString();
    }

    public static String loose(String str, String remove) {
        if (str == null && remove == null) {
            throw new NullPointerException("Both arguments were null");
        }
        if (str == null) {
            return null;
        }
        if (remove == null) {
            return str;
        }
        var toRemove = new HashSet<Character>();
        for (var ch : remove.toCharArray()) {
            toRemove.add(ch);
        }
        var olds = new ArrayList<Character>(str.length());
        for (var ch : str.toCharArray()) {
            olds.add(ch);
        }
        var news = new StringBuilder(str.length());
        for (var ch : olds) {
            if (!toRemove.contains(ch)) {
                news.append(ch);
            }
        }
        return news.toString();
    }

    public static int indexOfDifference(String str1, String str2) {
        if (str1 == null || str2 == null) {
            throw new NullPointerException("Param was null");
        }
        if (str1.equals(str2)) {
            return -1;
        }
        var s1 = str1.toCharArray();
        var s2 = str2.toCharArray();
        for (var i = 0; i <= s1.length && i <= s2.length; i++) {
            if (i == s1.length || i == s2.length) {
                return i;
            }
            if (s1[i] != s2[i]) {
                return i;
            }
        }
        return -1;
    }

    public static String common(String str1, String str2) {
        if (str1 == null || str2 == null) {
            throw new NullPointerException("Param was null");
        }
        int m = str1.length();
        int n = str2.length();
        int max = 0;
        int[][] dp = new int[m][n];
        int endIndex = -1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    // If first row or column
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        // Add 1 to the diagonal value
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    }
                    if (max < dp[i][j]) {
                        max = dp[i][j];
                        endIndex = i;
                    }
                }
            }
        }
        return str1.substring(endIndex - max + 1, endIndex + 1);
    }

    public static String substringBetween(String str, String open, String close) {
        if (str == null && open == null && close == null) {
            throw new NullPointerException("Params were null");
        }
        if (str == null || open == null || close == null) {
            return null;
        }
        var left = str.indexOf(open);
        var right = str.indexOf(close);
        if (left < 0 || right < 0) {
            return null;
        }
        return str.substring(left + open.length(), right);
    }

    public static int levenshteinDistance(String str1, String str2) {
        if (str1 == null && str2 == null) {
            throw new NullPointerException("Both params were null");
        }
        if (str1 == null || str2 == null) {
            return -1;
        }
        if (str1.isEmpty()) {
            return str2.length();
        }
        if (str2.isEmpty()) {
            return str1.length();
        }
        int replace = levenshteinDistance(str1.substring(1), str2.substring(1)) + numOfReplacement(str1.charAt(0), str2.charAt(0));
        int insert = levenshteinDistance(str1, str2.substring(1)) + 1;
        int delete = levenshteinDistance(str1.substring(1), str2) + 1;
        return minEdits(replace, insert, delete);
    }

    public static int hamingDistance(String str1, String str2){
        if (str1 == null && str2 == null) {
            throw new NullPointerException("Both arguments were null");
        }
        if (str1 == null || str2 == null) {
            return -1;
        }
        if (str1.length() != str2.length()) {
            throw new IllegalArgumentException("Strings must have equal length");
        }
        var s1 = str1.toCharArray();
        var s2 = str2.toCharArray();
        var diffCount = 0;
        for (var i = 0; i < s1.length; i++){
            if (s1[i] != s2[i]){
                diffCount++;
            }
        }
        return diffCount;
    }

    private static int minEdits(int... nums) {
        return Arrays.stream(nums).min().orElse(Integer.MAX_VALUE);
    }

    private static int numOfReplacement(char c1, char c2) {
        return c1 == c2 ? 0 : 1;
    }
}
