package course.ch17.examples;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Timeout(10)
class AtomicCounterTest {

    @Test
    void atomicIncrementIsCorrect() {
        var counter = new AtomicCounter();
        assertEquals(1, counter.atomicIncrement());
        assertEquals(2, counter.atomicIncrement());
        assertEquals(2, counter.getAtomicCount());
    }

    @Test
    void nonAtomicIncrementWorksInSingleThread() {
        var counter = new AtomicCounter();
        assertEquals(1, counter.nonAtomicIncrement());
        assertEquals(2, counter.nonAtomicIncrement());
        assertEquals(2, counter.getNonAtomicCount());
    }

    @Test
    void compareAndSetSucceeds() {
        var counter = new AtomicCounter();
        counter.atomicIncrement();
        assertTrue(counter.compareAndSet(1, 10));
        assertEquals(10, counter.getAtomicCount());
    }

    @Test
    void compareAndSetFailsOnWrongExpected() {
        var counter = new AtomicCounter();
        counter.atomicIncrement();
        assertFalse(counter.compareAndSet(0, 10));
        assertEquals(1, counter.getAtomicCount());
    }

    @Test
    void getAndDoubleReturnsOldValue() {
        var counter = new AtomicCounter();
        counter.atomicIncrement();
        counter.atomicIncrement();
        counter.atomicIncrement();
        int old = counter.getAndDouble();
        assertEquals(3, old);
        assertEquals(6, counter.getAtomicCount());
    }

    @Test
    void compareConcurrencyAtomicIsAlwaysCorrect() throws InterruptedException {
        int[] results = AtomicCounter.compareConcurrency(10_000, 4);
        assertEquals(10_000, results[0]);
    }
}
