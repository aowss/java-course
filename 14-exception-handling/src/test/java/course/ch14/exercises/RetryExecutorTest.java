package course.ch14.exercises;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RetryExecutorTest {

    @Test
    void executeSucceedsOnFirstAttempt() {
        var executor = new RetryExecutor(3);
        String result = executor.execute(() -> "ok");
        assertEquals("ok", result);
        assertEquals(1, executor.getAttemptCount());
    }

    @Test
    void executeRetriesOnFailureThenSucceeds() {
        var counter = new AtomicInteger(0);
        var executor = new RetryExecutor(3);
        String result = executor.execute(() -> {
            if (counter.incrementAndGet() < 3) {
                throw new RuntimeException("fail #" + counter.get());
            }
            return "recovered";
        });
        assertEquals("recovered", result);
        assertEquals(3, executor.getAttemptCount());
    }

    @Test
    void executeThrowsAfterAllRetriesExhausted() {
        var executor = new RetryExecutor(2);
        var ex = assertThrows(RuntimeException.class,
                () -> executor.execute(() -> { throw new RuntimeException("always fails"); }));
        assertTrue(ex.getMessage().contains("3 attempts failed"));
        assertTrue(ex.getCause() instanceof RuntimeException);
        assertEquals(2, ex.getSuppressed().length);
        assertEquals(3, executor.getAttemptCount());
    }

    @Test
    void executeWithZeroRetriesTriesOnce() {
        var executor = new RetryExecutor(0);
        var ex = assertThrows(RuntimeException.class,
                () -> executor.execute(() -> { throw new RuntimeException("fail"); }));
        assertTrue(ex.getCause() instanceof RuntimeException);
        assertEquals(0, ex.getSuppressed().length);
        assertEquals(1, executor.getAttemptCount());
    }

    @Test
    void constructorRejectsNegativeRetries() {
        assertThrows(IllegalArgumentException.class, () -> new RetryExecutor(-1));
    }

    @Test
    void attemptCountResetsPerExecution() {
        var executor = new RetryExecutor(3);
        executor.execute(() -> "first");
        assertEquals(1, executor.getAttemptCount());
        executor.execute(() -> "second");
        assertEquals(1, executor.getAttemptCount());
    }
}
