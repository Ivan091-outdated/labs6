package edu;

import java.util.NoSuchElementException;


/**
 * The <tt>Stack</tt> class represents a last-in-first-out (LIFO) stack of
 * generic items. It supports the usual <em>push</em> and <em>pop</em>
 * operations, along with methods for peeking at the top item, testing if the
 * stack is empty, and iterating through the items in LIFO order.
 * <p>
 */
public class Stack<Item> {

    private int n; // size of the stack

    private Node head; // top of stack

    public int size() {
        return n;
    }

    public void push(Item item) {
        Node oldFirst = head;
        head = new Node();
        head.item = item;
        head.next = oldFirst;
        n++;
        assert check();
    }

    /**
     * Delete and return the item most recently added to the stack.
     *
     * @throws java.util.NoSuchElementException if stack is empty.
     */
    public Item pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("The stack is empty");
        }
        Item item = head.item;
        head = head.next;
        n--;
        assert check();
        return item;
    }

    /**
     * Return the item most recently added to the stack without deletion.
     *
     * @throws java.util.NoSuchElementException if stack is empty.
     */
    public Item peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("The stack is empty");
        }
        assert check();
        return head.item;
    }

    /**
     * Return string representation.
     */
    public String toString() {
        StringBuilder acc = new StringBuilder();
        acc.append(this.getClass().getSimpleName())
                .append('(');
        if (head == null) {
            return acc.append(')').toString();
        }
        var current = head;
        acc.append(current.item);
        do {
            acc.append(',').append(current.item);
            current = current.next;
        } while (current.next != null);

        return acc.append(')').toString();
    }

    /**
     * Invariant
     *
     * @return if the state is correct or not
     */
    private boolean check() {
        switch (n) {
            case 0 -> {
                if (head != null) {
                    return false;
                }
            }
            case 1 -> {
                if (head == null) {
                    return false;
                }
                if (head.next != null) {
                    return false;
                }
            }
            default -> {
                if (head.next == null) {
                    return false;
                }
            }
        }
        int numberOfNodes = 0;
        for (Node x = head; x != null; x = x.next) {
            numberOfNodes++;
        }
        return numberOfNodes == n;
    }

    public boolean isEmpty() {
        return head == null;
    }

    private class Node {

        private Item item;

        private Node next;
    }
}