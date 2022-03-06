package edu;

import org.junit.jupiter.api.Test;

import static edu.StringUtils.*;
import static org.junit.jupiter.api.Assertions.*;


class StringKeepUtilsTest {

    @Test
    void hello_le() {
        assertEquals("ell", keep("hello", "le"));
    }

    @Test
    void hello_hl() {
        assertEquals("hll", keep("hello", "hl"));
    }

    @Test
    void any() {
        assertEquals("", keep("qweasdzxc", ""));
    }

    @Test
    void emptyNull() {
        assertEquals("", keep("qweasdzxc", null));
    }

    @Test
    void empty() {
        assertEquals("", keep("", "qweasdzxc"));
    }

    @Test
    void nullInNullOut() {
        assertNull(keep(null, "a"));
    }

    @Test
    void nullNPE() {
        assertThrows(NullPointerException.class, () -> keep(null, null));
    }
}