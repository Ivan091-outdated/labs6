package edu;

import org.junit.jupiter.api.Test;

import static edu.StringUtils.hamingDistance;
import static org.junit.jupiter.api.Assertions.*;


class StringHamingDistanceUtilsTest {

    @Test
    void nullNPE() {
        assertThrows(NullPointerException.class, () -> hamingDistance(null, null));
    }

    @Test
    void nullAny() {
        assertEquals(-1, hamingDistance(null, ""));
        assertEquals(-1, hamingDistance("", null));
    }

    @Test
    void differentLength() {
        assertThrows(IllegalArgumentException.class, () -> hamingDistance("abc", "abcd"));
    }

    @Test
    void emptyEmpty() {
        assertEquals(0, hamingDistance("", ""));
    }

    @Test
    void equal() {
        assertEquals(0, hamingDistance("father", "father"));
    }

    @Test
    void happy1() {
        assertEquals(1, hamingDistance("pip", "pop"));
    }

    @Test
    void happy2() {
        assertEquals(2, hamingDistance("abcd", "abab"));
    }

    @Test
    void happy3(){
        assertEquals(1, hamingDistance("hallo", "hello"));
    }

    @Test
    void happy4() {
        assertEquals(4, hamingDistance("abcd", "efgi"));
    }
}