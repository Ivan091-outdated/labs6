package edu;

public class Sum {

    public static long accum(int... values) {
        long result = 0;
        for (long value : values) {
            result += value;
        }
        return result;
    }
}
