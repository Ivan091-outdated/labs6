package edu;

import org.junit.jupiter.api.Test;

import static edu.StringUtils.*;
import static org.junit.jupiter.api.Assertions.*;


public class StringIndexOfDifferenceUtilsTest {

    @Test
    void nullNullNPE() {
        assertThrows(NullPointerException.class, () -> indexOfDifference(null, null));
    }

    @Test
    void emptyEmpty() {
        assertEquals(-1, indexOfDifference("", ""));
    }

    @Test
    void emptySome() {
        assertEquals(0, indexOfDifference("", "abc"));
    }

    @Test
    void someEmpty() {
        assertEquals(0, indexOfDifference("abc", ""));
    }

    @Test
    void equal() {
        assertEquals(-1, indexOfDifference("abc", "abc"));
    }

    @Test
    void happy1() {
        assertEquals(2, indexOfDifference("ab", "abxyz"));
    }

    @Test
    void happy2() {
        assertEquals(2, indexOfDifference("abcde", "abxyz"));
    }

    @Test
    void happy3() {
        assertEquals(0, indexOfDifference("abcde", "xyz"));
    }
}
