package course.ch18.examples;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Demonstrates the concept of structured concurrency using virtual threads.
 *
 * <p><strong>Structured concurrency</strong> ties the lifetime of concurrent subtasks
 * to a well-defined scope so that:
 * <ul>
 *   <li>If any subtask fails, the others are cancelled.</li>
 *   <li>The parent cannot complete until all subtasks have finished.</li>
 *   <li>Errors are propagated cleanly to the parent.</li>
 * </ul>
 *
 * <p>Java provides {@code StructuredTaskScope} for this purpose, but it is a
 * <strong>preview feature</strong> in Java 25 and requires {@code --enable-preview}
 * to use. The code below would be written as:
 *
 * <pre>{@code
 * // Requires --enable-preview in Java 25
 * try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
 *     Subtask<String> user  = scope.fork(() -> fetchUser(userId));
 *     Subtask<String> order = scope.fork(() -> fetchOrder(orderId));
 *     scope.join().throwIfFailed();
 *     return user.get() + " | " + order.get();
 * }
 * }</pre>
 *
 * <p>This class uses the standard {@link Executors#newVirtualThreadPerTaskExecutor()}
 * with try-with-resources to approximate structured concurrency without preview features.
 * The executor's {@code close()} method waits for all submitted tasks to complete,
 * providing the "join at end of scope" behavior.
 */
public class StructuredConcurrencyDemo {

    /**
     * Executes multiple tasks concurrently using virtual threads and returns their results.
     *
     * <p>Uses try-with-resources to ensure all tasks complete before this method returns,
     * simulating the scoping behavior of {@code StructuredTaskScope}.
     *
     * @param tasks the tasks to execute
     * @param <T>   the result type
     * @return a list of results in submission order
     * @throws ExecutionException   if any task fails
     * @throws InterruptedException if the current thread is interrupted while waiting
     */
    public static <T> List<T> executeAll(List<Callable<T>> tasks)
            throws ExecutionException, InterruptedException {
        List<Future<T>> futures = new ArrayList<>();

        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (Callable<T> task : tasks) {
                futures.add(executor.submit(task));
            }
        }

        List<T> results = new ArrayList<>();
        for (Future<T> future : futures) {
            results.add(future.get());
        }
        return results;
    }

    /**
     * Simulates fetching data from two independent services and combining the results.
     *
     * @param service1Result the simulated result from service 1
     * @param service2Result the simulated result from service 2
     * @return the combined result as {@code "service1 | service2"}
     * @throws ExecutionException   if a task fails
     * @throws InterruptedException if interrupted
     */
    public static String fetchFromTwoServices(String service1Result, String service2Result)
            throws ExecutionException, InterruptedException {
        List<Callable<String>> tasks = List.of(
                () -> {
                    Thread.sleep(10);
                    return service1Result;
                },
                () -> {
                    Thread.sleep(10);
                    return service2Result;
                }
        );

        List<String> results = executeAll(tasks);
        return results.get(0) + " | " + results.get(1);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("--- Structured-concurrency-style execution ---");

        List<Callable<String>> tasks = List.of(
                () -> { Thread.sleep(50); return "Task A done"; },
                () -> { Thread.sleep(30); return "Task B done"; },
                () -> { Thread.sleep(10); return "Task C done"; }
        );

        List<String> results = executeAll(tasks);
        results.forEach(System.out::println);

        System.out.println("\n--- Fetch from two services ---");
        String combined = fetchFromTwoServices("user:Alice", "order:12345");
        System.out.println(combined);
    }
}
