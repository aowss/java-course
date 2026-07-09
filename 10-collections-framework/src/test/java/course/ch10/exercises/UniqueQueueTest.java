package course.ch10.exercises;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UniqueQueueTest {

    @Test
    void offerAddsNewElement() {
        var queue = new UniqueQueue<String>();
        assertTrue(queue.offer("a"));
        assertEquals(1, queue.size());
    }

    @Test
    void offerRejectsDuplicate() {
        var queue = new UniqueQueue<String>();
        queue.offer("a");
        assertFalse(queue.offer("a"));
        assertEquals(1, queue.size());
    }

    @Test
    void pollReturnsFifoOrder() {
        var queue = new UniqueQueue<Integer>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        assertEquals(1, queue.poll());
        assertEquals(2, queue.poll());
        assertEquals(3, queue.poll());
    }

    @Test
    void pollReturnsNullWhenEmpty() {
        var queue = new UniqueQueue<String>();
        assertNull(queue.poll());
    }

    @Test
    void pollAllowsReInsert() {
        var queue = new UniqueQueue<String>();
        queue.offer("a");
        queue.poll();
        assertTrue(queue.offer("a"));
        assertEquals(1, queue.size());
    }

    @Test
    void peekReturnsHeadWithoutRemoving() {
        var queue = new UniqueQueue<String>();
        queue.offer("first");
        queue.offer("second");
        assertEquals("first", queue.peek());
        assertEquals(2, queue.size());
    }

    @Test
    void peekReturnsNullWhenEmpty() {
        var queue = new UniqueQueue<String>();
        assertNull(queue.peek());
    }

    @Test
    void containsReturnsTrueForExistingElement() {
        var queue = new UniqueQueue<String>();
        queue.offer("hello");
        assertTrue(queue.contains("hello"));
        assertFalse(queue.contains("world"));
    }
}
