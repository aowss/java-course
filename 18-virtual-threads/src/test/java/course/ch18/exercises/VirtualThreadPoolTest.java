package course.ch18.exercises;

import java.util.List;
import java.util.concurrent.Callable;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Timeout(10)
class VirtualThreadPoolTest {

    @Test
    void processAllReturnsResults() {
        List<Callable<String>> tasks = List.of(() -> "a", () -> "b", () -> "c");
        List<String> results = VirtualThreadPool.processAll(tasks);
        assertEquals(List.of("a", "b", "c"), results);
    }

    @Test
    void processAllPreservesOrder() {
        List<Callable<Integer>> tasks = List.of(
                () -> { Thread.sleep(30); return 1; },
                () -> { Thread.sleep(10); return 2; },
                () -> 3
        );
        List<Integer> results = VirtualThreadPool.processAll(tasks);
        assertEquals(List.of(1, 2, 3), results);
    }

    @Test
    void processAllEmptyList() {
        List<Callable<String>> tasks = List.of();
        List<String> results = VirtualThreadPool.processAll(tasks);
        assertEquals(List.of(), results);
    }

    @Test
    void processAllWrapsExceptionInRuntimeException() {
        List<Callable<String>> tasks = List.of(
                () -> { throw new Exception("task failed"); }
        );
        assertThrows(RuntimeException.class, () -> VirtualThreadPool.processAll(tasks));
    }

    @Test
    void processAllHandlesManyTasks() {
        List<Callable<Integer>> tasks = new java.util.ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            int val = i;
            tasks.add(() -> val);
        }
        List<Integer> results = VirtualThreadPool.processAll(tasks);
        assertEquals(1000, results.size());
        assertEquals(0, results.getFirst());
        assertEquals(999, results.getLast());
    }
}
