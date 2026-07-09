package course.ch18.exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ParallelSum {

    public static long parallelSum(int[] array, int numThreads)
            throws InterruptedException, ExecutionException {
        if (array.length == 0) {
            return 0;
        }

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        try {
            int chunkSize = Math.max(1, (array.length + numThreads - 1) / numThreads);
            List<Future<Long>> futures = new ArrayList<>();

            for (int i = 0; i < array.length; i += chunkSize) {
                int start = i;
                int end = Math.min(i + chunkSize, array.length);
                futures.add(executor.submit(() -> {
                    long sum = 0;
                    for (int j = start; j < end; j++) {
                        sum += array[j];
                    }
                    return sum;
                }));
            }

            long total = 0;
            for (Future<Long> future : futures) {
                total += future.get();
            }
            return total;
        } finally {
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        }
    }
}
