package course.ch18.exercises;

import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Timeout(10)
class ParallelSumTest {

    @Test
    void sumSmallArray() throws InterruptedException, ExecutionException {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertEquals(55, ParallelSum.parallelSum(array, 2));
    }

    @Test
    void sumWithSingleThread() throws InterruptedException, ExecutionException {
        int[] array = {10, 20, 30};
        assertEquals(60, ParallelSum.parallelSum(array, 1));
    }

    @Test
    void sumWithMoreThreadsThanElements() throws InterruptedException, ExecutionException {
        int[] array = {5, 10};
        assertEquals(15, ParallelSum.parallelSum(array, 4));
    }

    @Test
    void sumEmptyArray() throws InterruptedException, ExecutionException {
        int[] array = {};
        assertEquals(0, ParallelSum.parallelSum(array, 2));
    }

    @Test
    void sumLargeArray() throws InterruptedException, ExecutionException {
        int size = 100_000;
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = 1;
        }
        assertEquals(size, ParallelSum.parallelSum(array, 4));
    }

    @Test
    void sumMatchesSequential() throws InterruptedException, ExecutionException {
        int[] array = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5};
        long sequential = 0;
        for (int v : array) {
            sequential += v;
        }
        assertEquals(sequential, ParallelSum.parallelSum(array, 3));
    }
}
