package course.ch17.examples;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Timeout(10)
class ExecutorServiceDemoTest {

    @Test
    void executeAllReturnsList() throws InterruptedException, ExecutionException {
        List<Callable<Integer>> tasks = List.of(() -> 1, () -> 2, () -> 3);
        List<Integer> results = ExecutorServiceDemo.executeAll(tasks, 2);
        assertEquals(List.of(1, 2, 3), results);
    }

    @Test
    void executeAllPreservesOrder() throws InterruptedException, ExecutionException {
        List<Callable<String>> tasks = List.of(() -> "a", () -> "b", () -> "c", () -> "d");
        List<String> results = ExecutorServiceDemo.executeAll(tasks, 2);
        assertEquals(List.of("a", "b", "c", "d"), results);
    }

    @Test
    void executeSingleReturnsResult() throws InterruptedException, ExecutionException {
        String result = ExecutorServiceDemo.executeSingle(() -> "hello");
        assertEquals("hello", result);
    }

    @Test
    void executeSinglePropagatesException() {
        assertThrows(ExecutionException.class,
                () -> ExecutorServiceDemo.executeSingle(() -> {
                    throw new RuntimeException("boom");
                }));
    }

    @Test
    void executeRunnablesCompletesAll() throws InterruptedException {
        AtomicInteger counter = new AtomicInteger();
        List<Runnable> tasks = List.of(counter::incrementAndGet, counter::incrementAndGet,
                counter::incrementAndGet);
        int submitted = ExecutorServiceDemo.executeRunnables(tasks, 2);
        assertEquals(3, submitted);
        assertEquals(3, counter.get());
    }

    @Test
    void executeAllEmptyListReturnsEmpty() throws InterruptedException, ExecutionException {
        List<Callable<String>> empty = List.of();
        List<String> results = ExecutorServiceDemo.executeAll(empty, 1);
        assertEquals(List.of(), results);
    }
}
