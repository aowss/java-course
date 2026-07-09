package course.ch17.exercises;

import java.util.concurrent.ExecutionException;

/**
 * Exercise 1 (Guided): Split an array into chunks and sum them in parallel
 * using an {@code ExecutorService}.
 *
 * <p>The parallel sum must produce the same result as a sequential sum regardless
 * of how many threads are used.
 */
public class ParallelSum {

    /**
     * Splits the array into {@code numThreads} chunks, submits each chunk's sum
     * to an {@code ExecutorService}, and combines the partial results.
     *
     * @param array      the array to sum
     * @param numThreads the number of threads (and chunks) to use
     * @return the total sum of all elements
     * @throws InterruptedException if interrupted while waiting for results
     * @throws ExecutionException   if a task throws an exception
     */
    public static long parallelSum(int[] array, int numThreads)
            throws InterruptedException, ExecutionException {
        // TODO: create a fixed thread pool with numThreads threads
        // TODO: split the array into numThreads chunks
        // TODO: submit a Callable for each chunk that returns the chunk's sum
        // TODO: collect all Future results and return the total
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
