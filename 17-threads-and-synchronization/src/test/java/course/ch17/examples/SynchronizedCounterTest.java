package course.ch17.examples;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Timeout(10)
class SynchronizedCounterTest {

    @Test
    void safeCounterSingleThread() {
        var counter = new SynchronizedCounter.SafeCounter();
        counter.increment();
        counter.increment();
        counter.increment();
        assertEquals(3, counter.get());
    }

    @Test
    void unsafeCounterSingleThread() {
        var counter = new SynchronizedCounter.UnsafeCounter();
        counter.increment();
        counter.increment();
        assertEquals(2, counter.get());
    }

    @Test
    void safeCounterIsConcurrentlyCorrect() throws InterruptedException {
        int threads = 10;
        int increments = 10_000;
        int expected = threads * increments;
        int result = SynchronizedCounter.runTest(
                new SynchronizedCounter.SafeCounter(), threads, increments);
        assertEquals(expected, result);
    }

    @Test
    void runTestWithSafeCounterReturnsExpected() throws InterruptedException {
        int result = SynchronizedCounter.runTest(
                new SynchronizedCounter.SafeCounter(), 5, 1000);
        assertEquals(5000, result);
    }
}
