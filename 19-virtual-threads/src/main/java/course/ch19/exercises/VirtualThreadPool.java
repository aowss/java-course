package course.ch19.exercises;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * Exercise 1 (Guided): Process a list of tasks using virtual threads.
 *
 * <p>Use {@code Executors.newVirtualThreadPerTaskExecutor()} to execute all tasks
 * concurrently and collect their results in submission order.
 *
 * <pre>{@code
 * List<Callable<String>> tasks = List.of(() -> "a", () -> "b");
 * List<String> results = VirtualThreadPool.processAll(tasks);
 * // results = ["a", "b"]
 * }</pre>
 */
public class VirtualThreadPool {

    /**
     * Executes all tasks concurrently using virtual threads and returns results in order.
     *
     * <p>If any task throws an exception, it is wrapped in a {@link RuntimeException}.
     *
     * @param tasks the tasks to execute
     * @param <T>   the result type
     * @return a list of results in the same order as the input tasks
     */
    public static <T> List<T> processAll(List<Callable<T>> tasks) {
        // TODO: use Executors.newVirtualThreadPerTaskExecutor() with try-with-resources
        // TODO: submit all tasks, collect futures, get results in order
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
