package course.ch18.exercises;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.Callable;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Timeout(10)
class StructuredFanOutTest {

    @Test
    void firstSuccessReturnsFirst() {
        List<Callable<String>> tasks = List.of(() -> "fast", () -> "slow");
        String result = StructuredFanOut.firstSuccess(tasks);
        assertTrue(result.equals("fast") || result.equals("slow"));
    }

    @Test
    void firstSuccessSkipsFailures() {
        List<Callable<String>> tasks = List.of(
                () -> { throw new RuntimeException("fail1"); },
                () -> "success",
                () -> { throw new RuntimeException("fail2"); }
        );
        assertEquals("success", StructuredFanOut.firstSuccess(tasks));
    }

    @Test
    void firstSuccessAllFailThrowsWithSuppressed() {
        List<Callable<String>> tasks = List.of(
                () -> { throw new RuntimeException("a"); },
                () -> { throw new RuntimeException("b"); }
        );
        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> StructuredFanOut.firstSuccess(tasks));
        assertTrue(ex.getSuppressed().length > 0);
    }

    @Test
    void allSuccessesCollectsResults() {
        List<Callable<String>> tasks = List.of(() -> "a", () -> "b", () -> "c");
        List<String> results = StructuredFanOut.allSuccesses(tasks, Duration.ofSeconds(5));
        assertEquals(3, results.size());
        assertTrue(results.contains("a"));
        assertTrue(results.contains("b"));
        assertTrue(results.contains("c"));
    }

    @Test
    void allSuccessesSkipsFailures() {
        List<Callable<String>> tasks = List.of(
                () -> "ok",
                () -> { throw new RuntimeException("fail"); },
                () -> "also ok"
        );
        List<String> results = StructuredFanOut.allSuccesses(tasks, Duration.ofSeconds(5));
        assertEquals(2, results.size());
        assertTrue(results.contains("ok"));
        assertTrue(results.contains("also ok"));
    }

    @Test
    void allSuccessesEmptyTasks() {
        List<String> results = StructuredFanOut.allSuccesses(List.of(), Duration.ofSeconds(1));
        assertTrue(results.isEmpty());
    }
}
