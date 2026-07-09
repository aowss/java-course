package course.ch17.examples;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Demonstrates how deadlock occurs and how to prevent it with consistent lock ordering.
 *
 * <p>A <em>deadlock</em> happens when two or more threads each hold a lock the other needs,
 * so neither can proceed. The classic scenario involves two locks acquired in opposite order:
 *
 * <pre>{@code
 * // Thread 1: lock A → lock B
 * // Thread 2: lock B → lock A
 * // Both block forever.
 * }</pre>
 *
 * <p>The fix is to always acquire locks in the <strong>same global order</strong>, or use
 * {@link ReentrantLock#tryLock(long, TimeUnit)} to detect and recover from potential deadlocks.
 */
public class DeadlockDemo {

    private final ReentrantLock lockA = new ReentrantLock();
    private final ReentrantLock lockB = new ReentrantLock();

    /**
     * Result of a transfer attempt.
     *
     * @param thread1Completed whether thread 1 finished its work
     * @param thread2Completed whether thread 2 finished its work
     */
    public record TransferResult(boolean thread1Completed, boolean thread2Completed) {}

    /**
     * Attempts a deadlock-prone operation using {@code tryLock} with a timeout so
     * the demo does not actually hang.
     *
     * <p>Thread 1 acquires {@code lockA} then tries {@code lockB}.
     * Thread 2 acquires {@code lockB} then tries {@code lockA}.
     * With a short timeout, at least one thread will fail to acquire the second lock,
     * illustrating the deadlock scenario without actually hanging.
     *
     * @param timeoutMs timeout in milliseconds for {@code tryLock}
     * @return a {@link TransferResult} indicating which threads completed
     * @throws InterruptedException if the current thread is interrupted while waiting
     */
    public TransferResult runDeadlockProne(long timeoutMs) throws InterruptedException {
        boolean[] completed = new boolean[2];

        Thread t1 = new Thread(() -> {
            try {
                if (lockA.tryLock(timeoutMs, TimeUnit.MILLISECONDS)) {
                    try {
                        Thread.sleep(50);
                        if (lockB.tryLock(timeoutMs, TimeUnit.MILLISECONDS)) {
                            try {
                                completed[0] = true;
                            } finally {
                                lockB.unlock();
                            }
                        }
                    } finally {
                        lockA.unlock();
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "DeadlockProne-1");

        Thread t2 = new Thread(() -> {
            try {
                if (lockB.tryLock(timeoutMs, TimeUnit.MILLISECONDS)) {
                    try {
                        Thread.sleep(50);
                        if (lockA.tryLock(timeoutMs, TimeUnit.MILLISECONDS)) {
                            try {
                                completed[1] = true;
                            } finally {
                                lockA.unlock();
                            }
                        }
                    } finally {
                        lockB.unlock();
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "DeadlockProne-2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        return new TransferResult(completed[0], completed[1]);
    }

    /**
     * Runs the <strong>safe</strong> version with consistent lock ordering.
     *
     * <p>Both threads acquire {@code lockA} first, then {@code lockB}, preventing deadlock.
     *
     * @param timeoutMs timeout in milliseconds for {@code tryLock}
     * @return a {@link TransferResult} — both threads should complete successfully
     * @throws InterruptedException if the current thread is interrupted while waiting
     */
    public TransferResult runSafe(long timeoutMs) throws InterruptedException {
        boolean[] completed = new boolean[2];

        Runnable safeTask = () -> {
            try {
                if (lockA.tryLock(timeoutMs, TimeUnit.MILLISECONDS)) {
                    try {
                        if (lockB.tryLock(timeoutMs, TimeUnit.MILLISECONDS)) {
                            try {
                                int index = Thread.currentThread().getName().endsWith("1") ? 0 : 1;
                                completed[index] = true;
                            } finally {
                                lockB.unlock();
                            }
                        }
                    } finally {
                        lockA.unlock();
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Thread t1 = new Thread(safeTask, "Safe-1");
        Thread t2 = new Thread(safeTask, "Safe-2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        return new TransferResult(completed[0], completed[1]);
    }

    public static void main(String[] args) throws InterruptedException {
        DeadlockDemo demo = new DeadlockDemo();

        System.out.println("=== Deadlock-prone (with tryLock timeout) ===");
        TransferResult prone = demo.runDeadlockProne(200);
        System.out.println("Thread 1 completed: " + prone.thread1Completed());
        System.out.println("Thread 2 completed: " + prone.thread2Completed());

        System.out.println("\n=== Safe (consistent lock ordering) ===");
        DeadlockDemo demo2 = new DeadlockDemo();
        TransferResult safe = demo2.runSafe(2000);
        System.out.println("Thread 1 completed: " + safe.thread1Completed());
        System.out.println("Thread 2 completed: " + safe.thread2Completed());
    }
}
