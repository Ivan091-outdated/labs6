package edu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class StackFullTestTest {

    private final Stack<Integer> stack = new Stack<>();

    @BeforeEach
    void setUp() {
        stack.push(0);
        stack.push(2);
        stack.push(4);
        stack.push(8);
        stack.push(16);
    }

    @Test
    void counts() {
        assertFalse(stack.isEmpty());
        assertEquals(5, stack.size());
    }

    @Test
    void peeks() {
        assertEquals(16, stack.peek());
        assertFalse(stack.isEmpty());
        assertEquals(5, stack.size());
    }

    @Test
    void pops() {
        assertEquals(16, stack.pop());
        assertFalse(stack.isEmpty());
        assertEquals(4, stack.size());
    }
}