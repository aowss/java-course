package course.ch16.examples;

import java.util.ArrayList;
import java.util.List;

/**
 * Compares an unsynchronized counter with a synchronized one under concurrent access.
 *
 * <p>When multiple threads increment a shared counter without synchronization, the final
 * value is often less than expected due to <em>lost updates</em>. The {@code synchronized}
 * keyword ensures mutual exclusion so that increments are atomic.
 *
 * <p>Example:
 * <pre>{@code
 * SynchronizedCounter.SafeCounter safe = new SynchronizedCounter.SafeCounter();
 * safe.increment();
 * safe.increment();
 * assert safe.get() == 2;
 * }</pre>
 */
public class SynchronizedCounter {

    /**
     * A counter with <strong>no</strong> synchronization — not thread-safe.
     *
     * <p>Concurrent increments will produce lost updates because {@code count++}
     * is a read-modify-write sequence that is not atomic.
     */
    public static class UnsafeCounter {
        private int count = 0;

        public void increment() {
            count++;
        }

        public int get() {
            return count;
        }
    }

    /**
     * A counter protected by {@code synchronized} — thread-safe.
     *
     * <p>Every access to {@code count} is guarded by the intrinsic lock of this instance,
     * guaranteeing visibility and atomicity.
     */
    public static class SafeCounter {
        private int count = 0;

        public synchronized void increment() {
            count++;
        }

        public synchronized int get() {
            return count;
        }
    }

    /**
     * Runs {@code threadCount} threads, each incrementing the counter {@code incrementsPerThread}
     * times, and returns the final counter value.
     *
     * @param counter             the counter to use (safe or unsafe)
     * @param threadCount         number of threads
     * @param incrementsPerThread number of increments each thread performs
     * @return the final counter value after all threads complete
     * @throws InterruptedException if the current thread is interrupted while waiting
     */
    public static int runTest(Object counter, int threadCount, int incrementsPerThread)
            throws InterruptedException {
        List<Thread> threads = new ArrayList<>();

        Runnable task;
        if (counter instanceof SafeCounter safe) {
            task = () -> {
                for (int i = 0; i < incrementsPerThread; i++) {
                    safe.increment();
                }
            };
        } else if (counter instanceof UnsafeCounter unsafe) {
            task = () -> {
                for (int i = 0; i < incrementsPerThread; i++) {
                    unsafe.increment();
                }
            };
        } else {
            throw new IllegalArgumentException("Unknown counter type");
        }

        for (int i = 0; i < threadCount; i++) {
            Thread t = new Thread(task);
            threads.add(t);
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }

        if (counter instanceof SafeCounter safe) {
            return safe.get();
        } else {
            return ((UnsafeCounter) counter).get();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int threads = 10;
        int increments = 100_000;
        int expected = threads * increments;

        int unsafeResult = runTest(new UnsafeCounter(), threads, increments);
        int safeResult = runTest(new SafeCounter(), threads, increments);

        System.out.println("Expected:      " + expected);
        System.out.println("Unsafe result: " + unsafeResult + (unsafeResult == expected ? " (lucky!)" : " (LOST UPDATES)"));
        System.out.println("Safe result:   " + safeResult);
    }
}
