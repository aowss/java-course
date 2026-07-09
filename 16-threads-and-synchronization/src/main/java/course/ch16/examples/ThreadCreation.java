package course.ch16.examples;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Demonstrates three ways to create and start threads in Java.
 *
 * <p>Java provides several approaches for creating threads:
 * <ul>
 *   <li>Extending {@link Thread} and overriding {@code run()}</li>
 *   <li>Passing a {@link Runnable} (often a lambda) to the {@link Thread} constructor</li>
 *   <li>Using {@link Thread#ofPlatform()} (preview in earlier releases, standard since Java 21)</li>
 * </ul>
 *
 * <p>Example usage:
 * <pre>{@code
 * List<String> names = ThreadCreation.runWithRunnable(3);
 * // names contains the thread names of the 3 threads that ran
 * }</pre>
 */
public class ThreadCreation {

    /**
     * Creates threads by subclassing {@link Thread}.
     *
     * <p>Each thread records its name into a shared list.
     *
     * @param count number of threads to create
     * @return an unmodifiable list of thread names that executed
     * @throws InterruptedException if the current thread is interrupted while waiting
     */
    public static List<String> runWithSubclass(int count) throws InterruptedException {
        List<String> names = Collections.synchronizedList(new ArrayList<>());
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    names.add(Thread.currentThread().getName());
                }
            };
            t.setName("Subclass-" + i);
            threads.add(t);
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
        return List.copyOf(names);
    }

    /**
     * Creates threads using a {@link Runnable} lambda.
     *
     * @param count number of threads to create
     * @return an unmodifiable list of thread names that executed
     * @throws InterruptedException if the current thread is interrupted while waiting
     */
    public static List<String> runWithRunnable(int count) throws InterruptedException {
        List<String> names = Collections.synchronizedList(new ArrayList<>());
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            int index = i;
            Thread t = new Thread(() -> names.add(Thread.currentThread().getName()));
            t.setName("Runnable-" + index);
            threads.add(t);
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
        return List.copyOf(names);
    }

    /**
     * Creates threads using {@link Thread#ofPlatform()}.
     *
     * @param count number of threads to create
     * @return an unmodifiable list of thread names that executed
     * @throws InterruptedException if the current thread is interrupted while waiting
     */
    public static List<String> runWithPlatformThread(int count) throws InterruptedException {
        List<String> names = Collections.synchronizedList(new ArrayList<>());
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Thread t = Thread.ofPlatform()
                    .name("Platform-" + i)
                    .start(() -> names.add(Thread.currentThread().getName()));
            threads.add(t);
        }

        for (Thread t : threads) {
            t.join();
        }
        return List.copyOf(names);
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Thread subclass ===");
        System.out.println(runWithSubclass(3));

        System.out.println("\n=== Runnable lambda ===");
        System.out.println(runWithRunnable(3));

        System.out.println("\n=== Thread.ofPlatform() ===");
        System.out.println(runWithPlatformThread(3));
    }
}
