package edu;

import org.junit.jupiter.api.*;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;


class StackEmptyTest {

    private final Stack<Integer> stack = new Stack<>();

    @Test
    void zeroSizeIsCorrect() {
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    void emptyPopThrows() {
        assertThrows(NoSuchElementException.class, stack::pop);
    }

    @Test
    void emptyPeekNull() {
        assertThrows(NoSuchElementException.class, stack::peek);
    }
}