package edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class SumTest {

    @Test
    void eq6() {
        Assertions.assertEquals(6, Sum.accum(1, 2, 3));
    }

    @Test
    void emptyEq0() {
        Assertions.assertEquals(0, Sum.accum());
    }

    @Test
    void noOverflow() {
        Assertions.assertTrue(Sum.accum(Integer.MAX_VALUE, 1) > 0);
    }
}