package edu;

import org.junit.jupiter.api.Test;

import static edu.StringUtils.levenshteinDistance;
import static org.junit.jupiter.api.Assertions.*;


class StringLevensteinDistanceUtilsTest {

    @Test
    void nullNullNPE() {
        assertThrows(NullPointerException.class, () -> levenshteinDistance(null, null));
    }

    @Test
    void oneNull() {
        assertEquals(-1, levenshteinDistance("", null));
        assertEquals(-1, levenshteinDistance(null, ""));
    }

    @Test
    void bothEmpty() {
        assertEquals(0, levenshteinDistance("", ""));
    }

    @Test
    void emptySome() {
        assertEquals(1, levenshteinDistance("", "a"));
    }

    @Test
    void someNone() {
        assertEquals(1, levenshteinDistance("", "a"));
    }

    @Test
    void happy1() {
        assertEquals(1, levenshteinDistance("frog", "fog"));
    }

    @Test
    void happy2() {
        assertEquals(3,levenshteinDistance("fly", "ant"));
    }

    @Test
    void happy3() {
        assertEquals(7,levenshteinDistance("elephant", "hippo"));
    }

    @Test
    void happy3Reversed() {
        assertEquals(7, levenshteinDistance("hippo", "elephant"));
    }

    @Test
    void happy4() {
        assertEquals(8, levenshteinDistance("hippo","zzzzzzzz"));
    }

    @Test
    void happy5() {
        assertEquals(1, levenshteinDistance("hello", "hallo"));
    }
}