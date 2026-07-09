package course.ch09.exercises;

import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StackTest {

    @Test
    void newStackIsEmpty() {
        var stack = new Stack<Integer>();
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    void pushIncreasesSize() {
        var stack = new Stack<String>();
        stack.push("a");
        assertEquals(1, stack.size());
        assertFalse(stack.isEmpty());
    }

    @Test
    void popReturnsLastPushed() {
        var stack = new Stack<Integer>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }

    @Test
    void popDecreasesSize() {
        var stack = new Stack<String>();
        stack.push("a");
        stack.push("b");
        stack.pop();
        assertEquals(1, stack.size());
    }

    @Test
    void peekReturnsTopWithoutRemoving() {
        var stack = new Stack<String>();
        stack.push("a");
        stack.push("b");
        assertEquals("b", stack.peek());
        assertEquals(2, stack.size());
    }

    @Test
    void popOnEmptyStackThrows() {
        var stack = new Stack<Integer>();
        assertThrows(EmptyStackException.class, stack::pop);
    }

    @Test
    void peekOnEmptyStackThrows() {
        var stack = new Stack<Integer>();
        assertThrows(EmptyStackException.class, stack::peek);
    }

    @Test
    void stackResizesBeyondInitialCapacity() {
        var stack = new Stack<Integer>();
        for (int i = 0; i < 25; i++) {
            stack.push(i);
        }
        assertEquals(25, stack.size());
        assertEquals(24, stack.peek());
    }
}
