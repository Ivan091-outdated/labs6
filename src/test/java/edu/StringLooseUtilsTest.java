package edu;

import org.junit.jupiter.api.Test;

import static edu.StringUtils.loose;
import static org.junit.jupiter.api.Assertions.*;


class StringLooseUtilsTest {

    @Test
    void nullNullNPE() {
        assertThrows(NullPointerException.class, () -> loose(null, null));
    }

    @Test
    void nullNull() {
        assertNull(loose(null, "qwe"));
    }

    @Test
    void same() {
        assertEquals("qwe", loose("qwe", ""));
    }

    @Test
    void removes2() {
        assertEquals("ho", loose("hello", "le"));
    }

    @Test
    void removes() {
        assertEquals("eo", loose("hello", "hl"));
    }

    @Test
    void sameNull() {
        assertEquals("qwe", loose("qwe", null));
    }

    @Test
    void empty() {
        assertEquals("qwe", loose("qwe", ""));
    }
}