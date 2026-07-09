package course.ch19.examples;

import java.util.concurrent.ThreadFactory;

/**
 * Demonstrates the basics of creating virtual threads in Java.
 *
 * <p>Virtual threads are lightweight threads managed by the JVM rather than the OS.
 * They enable a thread-per-request programming model without the resource overhead
 * of platform (OS) threads. Key creation methods include:
 *
 * <ul>
 *   <li>{@code Thread.ofVirtual().start(Runnable)} — creates and starts a virtual thread</li>
 *   <li>{@code Thread.startVirtualThread(Runnable)} — convenience method to start a virtual thread</li>
 *   <li>{@code Thread.ofVirtual().name("prefix-", start).factory()} — creates a {@link ThreadFactory}
 *       that produces named virtual threads</li>
 * </ul>
 *
 * <p>Virtual threads are always daemon threads and cannot be changed to non-daemon.
 *
 * <pre>{@code
 * Thread vt = Thread.ofVirtual().start(() -> System.out.println("Hello from virtual thread!"));
 * vt.join();
 * }</pre>
 */
public class VirtualThreadBasics {

    /**
     * Creates and starts a virtual thread that executes the given task, then waits for it to finish.
     *
     * @param task the task to run on a virtual thread
     * @return the thread that was created (will be in TERMINATED state after this method returns)
     * @throws InterruptedException if the current thread is interrupted while waiting
     */
    public static Thread startAndJoin(Runnable task) throws InterruptedException {
        Thread vt = Thread.ofVirtual().start(task);
        vt.join();
        return vt;
    }

    /**
     * Creates and starts a virtual thread using {@link Thread#startVirtualThread(Runnable)}.
     *
     * @param task the task to run
     * @return the started virtual thread (will be in TERMINATED state after this method returns)
     * @throws InterruptedException if the current thread is interrupted while waiting
     */
    public static Thread startVirtualThread(Runnable task) throws InterruptedException {
        Thread vt = Thread.startVirtualThread(task);
        vt.join();
        return vt;
    }

    /**
     * Creates a {@link ThreadFactory} that produces named virtual threads with the given prefix
     * and a sequential counter starting at 0.
     *
     * @param prefix the name prefix for created threads
     * @return a thread factory producing named virtual threads
     */
    public static ThreadFactory createNamedFactory(String prefix) {
        return Thread.ofVirtual().name(prefix, 0).factory();
    }

    /**
     * Demonstrates that virtual threads are daemon threads.
     *
     * @return {@code true} because virtual threads are always daemon threads
     * @throws InterruptedException if the current thread is interrupted while waiting
     */
    public static boolean virtualThreadsAreDaemon() throws InterruptedException {
        Thread vt = Thread.ofVirtual().start(() -> {});
        boolean isDaemon = vt.isDaemon();
        vt.join();
        return isDaemon;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("--- startAndJoin ---");
        Thread t1 = startAndJoin(() -> System.out.println("  Running on: " + Thread.currentThread()));
        System.out.println("  isVirtual: " + t1.isVirtual());
        System.out.println("  isDaemon: " + t1.isDaemon());

        System.out.println("\n--- startVirtualThread ---");
        Thread t2 = startVirtualThread(() -> System.out.println("  Running on: " + Thread.currentThread()));
        System.out.println("  isVirtual: " + t2.isVirtual());

        System.out.println("\n--- Named ThreadFactory ---");
        ThreadFactory factory = createNamedFactory("worker-");
        Thread t3 = factory.newThread(() -> System.out.println("  Running on: " + Thread.currentThread()));
        t3.start();
        t3.join();
        System.out.println("  Name: " + t3.getName());
        System.out.println("  isVirtual: " + t3.isVirtual());

        System.out.println("\n--- Daemon check ---");
        System.out.println("  Virtual threads are daemon: " + virtualThreadsAreDaemon());
    }
}
