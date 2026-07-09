package course.ch18.examples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Compares {@link AtomicInteger} operations with a non-atomic counter under concurrent access.
 *
 * <p>Demonstrates {@code incrementAndGet()}, {@code compareAndSet()}, and {@code getAndUpdate()}
 * and shows how non-atomic operations can produce incorrect results when accessed by multiple threads.
 */
public class AtomicCounter {

    private final AtomicInteger atomicCount = new AtomicInteger(0);
    private int nonAtomicCount = 0;

    /**
     * Increments the atomic counter using {@code incrementAndGet()}.
     *
     * @return the updated value
     */
    public int atomicIncrement() {
        return atomicCount.incrementAndGet();
    }

    /**
     * Increments the non-atomic counter. This method is intentionally <em>not</em>
     * thread-safe to demonstrate data races.
     *
     * @return the updated value (may be stale under concurrent access)
     */
    public int nonAtomicIncrement() {
        return ++nonAtomicCount;
    }

    /**
     * Returns the current atomic count.
     *
     * @return the atomic counter value
     */
    public int getAtomicCount() {
        return atomicCount.get();
    }

    /**
     * Returns the current non-atomic count.
     *
     * @return the non-atomic counter value
     */
    public int getNonAtomicCount() {
        return nonAtomicCount;
    }

    /**
     * Atomically sets the value to {@code newValue} if the current value equals {@code expected}.
     *
     * @param expected the expected current value
     * @param newValue the new value to set
     * @return {@code true} if successfully updated, {@code false} otherwise
     */
    public boolean compareAndSet(int expected, int newValue) {
        return atomicCount.compareAndSet(expected, newValue);
    }

    /**
     * Atomically updates the counter by applying a function (doubling the current value).
     *
     * @return the value before the update
     */
    public int getAndDouble() {
        return atomicCount.getAndUpdate(current -> current * 2);
    }

    /**
     * Runs the specified number of increments on both the atomic and non-atomic counters
     * using the given number of threads. Returns the final atomic count (which is always
     * correct) and the non-atomic count (which may be less than expected due to races).
     *
     * @param increments     total number of increments to perform
     * @param numThreads     number of threads to use
     * @return an array of two elements: [atomicResult, nonAtomicResult]
     * @throws InterruptedException if interrupted while waiting
     */
    public static int[] compareConcurrency(int increments, int numThreads)
            throws InterruptedException {
        AtomicCounter counter = new AtomicCounter();
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        for (int i = 0; i < increments; i++) {
            executor.submit(() -> {
                counter.atomicIncrement();
                counter.nonAtomicIncrement();
            });
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);

        return new int[]{counter.getAtomicCount(), counter.getNonAtomicCount()};
    }
}
