package course.ch21.examples;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Nested Test Demo — Stack behavior")
class NestedTestDemo {

    private Deque<String> stack;

    @BeforeEach
    void createStack() {
        stack = new ArrayDeque<>();
    }

    @Test
    @DisplayName("is initially empty")
    void isEmpty() {
        assertTrue(stack.isEmpty());
    }

    @Test
    @DisplayName("pop on empty stack throws")
    void popOnEmptyThrows() {
        assertThrows(NoSuchElementException.class, stack::pop);
    }

    @Nested
    @DisplayName("after pushing one element")
    class AfterPushing {

        @BeforeEach
        void pushElement() {
            stack.push("first");
        }

        @Test
        @DisplayName("is no longer empty")
        void isNotEmpty() {
            assertFalse(stack.isEmpty());
        }

        @Test
        @DisplayName("peek returns the element without removing")
        void peekReturnsElement() {
            assertEquals("first", stack.peek());
            assertFalse(stack.isEmpty());
        }

        @Test
        @DisplayName("pop returns the element and empties the stack")
        void popReturnsElement() {
            assertEquals("first", stack.pop());
            assertTrue(stack.isEmpty());
        }

        @Nested
        @DisplayName("after pushing a second element")
        class AfterPushingAnother {

            @BeforeEach
            void pushAnother() {
                stack.push("second");
            }

            @Test
            @DisplayName("peek returns last pushed (LIFO)")
            void peekReturnsLast() {
                assertEquals("second", stack.peek());
            }

            @Test
            @DisplayName("size is 2")
            void sizeIsTwo() {
                assertEquals(2, stack.size());
            }
        }
    }
}
