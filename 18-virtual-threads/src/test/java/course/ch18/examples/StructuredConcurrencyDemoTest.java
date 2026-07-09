package course.ch18.examples;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Timeout(10)
class StructuredConcurrencyDemoTest {

    @Test
    void executeAllReturnsResults() throws ExecutionException, InterruptedException {
        List<Callable<String>> tasks = List.of(() -> "a", () -> "b", () -> "c");
        List<String> results = StructuredConcurrencyDemo.executeAll(tasks);
        assertEquals(List.of("a", "b", "c"), results);
    }

    @Test
    void executeAllPreservesOrder() throws ExecutionException, InterruptedException {
        List<Callable<Integer>> tasks = List.of(
                () -> { Thread.sleep(30); return 1; },
                () -> { Thread.sleep(10); return 2; },
                () -> 3
        );
        List<Integer> results = StructuredConcurrencyDemo.executeAll(tasks);
        assertEquals(List.of(1, 2, 3), results);
    }

    @Test
    void executeAllPropagatesException() {
        List<Callable<String>> tasks = List.of(
                () -> "ok",
                () -> { throw new RuntimeException("boom"); }
        );
        assertThrows(ExecutionException.class,
                () -> StructuredConcurrencyDemo.executeAll(tasks));
    }

    @Test
    void fetchFromTwoServicesCombinesResults()
            throws ExecutionException, InterruptedException {
        String result = StructuredConcurrencyDemo.fetchFromTwoServices("userA", "order123");
        assertEquals("userA | order123", result);
    }

    @Test
    void executeAllEmptyList() throws ExecutionException, InterruptedException {
        List<Callable<String>> tasks = List.of();
        List<String> results = StructuredConcurrencyDemo.executeAll(tasks);
        assertTrue(results.isEmpty());
    }
}
