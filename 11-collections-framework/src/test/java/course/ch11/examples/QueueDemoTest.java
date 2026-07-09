package course.ch11.examples;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QueueDemoTest {

    @Test
    void processQueueJoinsElements() {
        Queue<String> queue = new LinkedList<>();
        queue.offer("a");
        queue.offer("b");
        queue.offer("c");
        var result = QueueDemo.processQueue(queue);
        assertTrue(result.contains("a"));
        assertTrue(result.contains("b"));
        assertTrue(result.contains("c"));
    }

    @Test
    void processQueueEmptiesTheQueue() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        queue.offer(2);
        QueueDemo.processQueue(queue);
        assertTrue(queue.isEmpty());
    }

    @Test
    void reverseWithDequeReversesOrder() {
        assertEquals(List.of("C", "B", "A"),
                QueueDemo.reverseWithDeque(List.of("A", "B", "C")));
    }

    @Test
    void reverseWithDequeEmptyList() {
        assertEquals(List.of(), QueueDemo.reverseWithDeque(List.of()));
    }
}
