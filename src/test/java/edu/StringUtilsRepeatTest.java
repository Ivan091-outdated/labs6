package edu;

import org.junit.jupiter.api.Test;

import static edu.StringUtils.repeat;
import static org.junit.jupiter.api.Assertions.*;


class StringUtilsRepeatTest {

    @Test
    void zeroTimes() {
        assertEquals("", repeat("e", 0));
    }

    @Test
    void oneChar() {
        assertEquals("eee", repeat("e", 3));
    }

    @Test
    void simpleString() {
        assertEquals("ABCABC", repeat("ABC", 2));
    }

    @Test
    void negativeThrows() {
        assertThrows(IllegalArgumentException.class, () -> repeat("e", -2));
    }

    @Test
    void nullNPE() {
        assertThrows(NullPointerException.class, () -> repeat(null, 1));
    }
}