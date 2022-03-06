package edu;

import org.junit.jupiter.api.Test;

import static edu.StringUtils.repeat;
import static org.junit.jupiter.api.Assertions.*;


class StringUtilsSepRepeatTest {

    @Test
    void zeroTimes() {
        assertEquals("", repeat("e", "|", 0));
    }

    @Test
    void simpleOneChar() {
        assertEquals("e|e|e", repeat("e", "|", 3));
    }

    @Test
    void simple() {
        assertEquals("ABC,ABC", repeat("ABC", ",", 2));
    }

    @Test
    void sepNullNPE() {
        assertThrows(NullPointerException.class, () -> repeat("", null, 1));
    }

    @Test
    void nullNPE() {
        assertThrows(NullPointerException.class, () -> repeat(null, "a", 1));
    }

    @Test
    void sepOnly() {
        assertEquals("::", repeat("", ":", 3));
    }

    @Test
    void negativeTimes() {
        assertThrows(IllegalArgumentException.class, () -> repeat("e", ":", -2));
    }

    @Test
    void oneRepeat() {
        assertEquals("DBE", repeat("DBE", ":", 1));
    }

    @Test
    void noSep() {
        assertEquals("DBEDBE", repeat("DBE", "", 2));
    }
}