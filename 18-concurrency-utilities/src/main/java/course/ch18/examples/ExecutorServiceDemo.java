package course.ch18.examples;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates creating thread pools with {@link Executors}, submitting tasks,
 * obtaining {@link Future} results, and shutting down the executor properly.
 *
 * <p>All methods are static and return results, making them easy to test without
 * relying on console output or side effects.
 */
public class ExecutorServiceDemo {

    /**
     * Submits a list of {@link Callable} tasks to a fixed thread pool and collects their results.
     *
     * @param tasks      the tasks to execute
     * @param poolSize   the number of threads in the pool
     * @param <T>        the result type of the tasks
     * @return a list of results in submission order
     * @throws InterruptedException if interrupted while waiting for results
     * @throws ExecutionException   if a task throws an exception
     */
    public static <T> List<T> executeAll(List<Callable<T>> tasks, int poolSize)
            throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(poolSize);
        try {
            List<Future<T>> futures = new ArrayList<>();
            for (Callable<T> task : tasks) {
                futures.add(executor.submit(task));
            }
            List<T> results = new ArrayList<>();
            for (Future<T> future : futures) {
                results.add(future.get());
            }
            return results;
        } finally {
            shutdown(executor);
        }
    }

    /**
     * Submits a single {@link Callable} to a single-thread executor and returns the result.
     *
     * @param task the task to execute
     * @param <T>  the result type
     * @return the task's result
     * @throws InterruptedException if interrupted while waiting
     * @throws ExecutionException   if the task throws an exception
     */
    public static <T> T executeSingle(Callable<T> task)
            throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        try {
            Future<T> future = executor.submit(task);
            return future.get();
        } finally {
            shutdown(executor);
        }
    }

    /**
     * Demonstrates submitting {@link Runnable} tasks and waiting for completion.
     * Returns the number of tasks that were successfully submitted.
     *
     * @param tasks    the runnable tasks
     * @param poolSize the thread pool size
     * @return the number of tasks submitted
     * @throws InterruptedException if interrupted while waiting
     */
    public static int executeRunnables(List<Runnable> tasks, int poolSize)
            throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(poolSize);
        try {
            for (Runnable task : tasks) {
                executor.submit(task);
            }
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
            return tasks.size();
        } finally {
            if (!executor.isTerminated()) {
                executor.shutdownNow();
            }
        }
    }

    /**
     * Gracefully shuts down an {@link ExecutorService}, waiting up to 5 seconds
     * before forcing termination.
     *
     * @param executor the executor to shut down
     */
    public static void shutdown(ExecutorService executor) {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
