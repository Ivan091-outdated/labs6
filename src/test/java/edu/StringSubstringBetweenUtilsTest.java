package edu;

import org.junit.jupiter.api.Test;

import static edu.StringUtils.substringBetween;
import static org.junit.jupiter.api.Assertions.*;


class StringSubstringBetweenUtilsTest {

    @Test
    void nullNPE() {
        assertThrows(NullPointerException.class, () -> substringBetween(null,null,null));
    }

    @Test
    void nullAny() {
        assertNull(substringBetween(null, "", ""));
        assertNull(substringBetween("", null, ""));
        assertNull(substringBetween("", "", null));
    }

    @Test
    void allEmpty() {
        assertEquals("", substringBetween("", "", ""));
    }

    @Test
    void incorrect() {
        assertNull(substringBetween("", "", "]"));
        assertNull(substringBetween("", "[", "]"));
    }

    @Test
    void happy1() {
        assertEquals("", substringBetween("yabcz", "", ""));
    }

    @Test
    void happy2() {
        assertEquals("abc", substringBetween("yabcz", "y", "z"));
    }

    @Test
    void happy3() {
        assertEquals("abc", substringBetween("yabczyabcz", "y", "z"));
    }

    @Test
    void happy4() {
        assertEquals("b", substringBetween("wx[b]yz", "[", "]"));
    }
}