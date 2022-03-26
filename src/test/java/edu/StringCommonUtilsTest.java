package edu;

import org.junit.jupiter.api.Test;

import static edu.StringUtils.common;
import static org.junit.jupiter.api.Assertions.*;


class StringCommonUtilsTest {

    @Test
    void nullNullNPE() {
        assertThrows(NullPointerException.class, () -> common(null, null));
    }

    @Test
    void emptyEmpty() {
        assertEquals("", common("", ""));
    }

    @Test
    void emptySome() {
        assertEquals("", common("", "abc"));
    }

    @Test
    void someEmpty() {
        assertEquals("", common("abc", ""));
    }

    @Test
    void equal() {
        assertEquals("abc", common("abc", "abc"));
    }

    @Test
    void happy1() {
        assertEquals("ab", common("ab", "abxyz"));
    }

    @Test
    void happy2() {
        assertEquals("ab", common("abcde", "abxyz"));
    }

    @Test
    void happy3() {
        assertEquals("", common("abcde", "xyz"));
    }

    @Test
    void happy4() {
        assertEquals("deabc", common("deabc", "abcdeabcd"));
    }

    @Test
    void happy5() {
        assertEquals("fabce", common("dfabcegt", "rtoefabceiq"));
    }
}