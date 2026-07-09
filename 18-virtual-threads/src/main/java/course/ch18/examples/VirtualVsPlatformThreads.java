package course.ch18.examples;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Compares virtual threads and platform threads in terms of creation speed and scalability.
 *
 * <p>Platform threads are backed by OS threads and are expensive to create (typically limited
 * to a few thousand). Virtual threads are managed by the JVM and can scale to millions,
 * making them ideal for I/O-bound workloads that spend most of their time waiting.
 *
 * <p>This example demonstrates:
 * <ul>
 *   <li>Creating many virtual threads vs. platform threads</li>
 *   <li>Measuring creation and completion time</li>
 *   <li>Using {@link Executors#newVirtualThreadPerTaskExecutor()} for task submission</li>
 * </ul>
 *
 * <pre>{@code
 * try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
 *     for (int i = 0; i < 10_000; i++) {
 *         executor.submit(() -> Thread.sleep(Duration.ofMillis(100)));
 *     }
 * } // waits for all tasks to complete
 * }</pre>
 */
public class VirtualVsPlatformThreads {

    /**
     * Creates the specified number of virtual threads, each sleeping for the given duration,
     * and waits for all to complete. Returns the elapsed time in milliseconds.
     *
     * @param count    the number of virtual threads to create
     * @param sleepMs  how long each thread sleeps in milliseconds
     * @return elapsed wall-clock time in milliseconds
     */
    public static long runVirtualThreads(int count, long sleepMs) {
        Instant start = Instant.now();
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < count; i++) {
                executor.submit(() -> {
                    Thread.sleep(Duration.ofMillis(sleepMs));
                    return null;
                });
            }
        }
        return Duration.between(start, Instant.now()).toMillis();
    }

    /**
     * Creates the specified number of platform threads, each sleeping for the given duration,
     * and waits for all to complete. Returns the elapsed time in milliseconds.
     *
     * @param count    the number of platform threads to create
     * @param sleepMs  how long each thread sleeps in milliseconds
     * @return elapsed wall-clock time in milliseconds
     */
    public static long runPlatformThreads(int count, long sleepMs) {
        Instant start = Instant.now();
        Thread[] threads = new Thread[count];
        for (int i = 0; i < count; i++) {
            threads[i] = Thread.ofPlatform().start(() -> {
                try {
                    Thread.sleep(Duration.ofMillis(sleepMs));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return Duration.between(start, Instant.now()).toMillis();
    }

    /**
     * Uses {@link Executors#newVirtualThreadPerTaskExecutor()} to run tasks and counts
     * how many complete successfully.
     *
     * @param taskCount the number of tasks to submit
     * @return the number of tasks that completed
     */
    public static int runWithExecutor(int taskCount) {
        AtomicInteger completed = new AtomicInteger();
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < taskCount; i++) {
                executor.submit(() -> {
                    completed.incrementAndGet();
                    return null;
                });
            }
        }
        return completed.get();
    }

    public static void main(String[] args) {
        int largeCount = 100_000;
        long sleepMs = 10;

        System.out.println("--- Virtual Threads: " + largeCount + " threads, sleeping " + sleepMs + "ms each ---");
        long virtualTime = runVirtualThreads(largeCount, sleepMs);
        System.out.println("  Elapsed: " + virtualTime + " ms");

        int platformCount = 1_000;
        System.out.println("\n--- Platform Threads: " + platformCount + " threads, sleeping " + sleepMs + "ms each ---");
        long platformTime = runPlatformThreads(platformCount, sleepMs);
        System.out.println("  Elapsed: " + platformTime + " ms");

        System.out.println("\n--- Virtual Thread Executor ---");
        int completed = runWithExecutor(10_000);
        System.out.println("  Tasks completed: " + completed);
    }
}
