package course.ch18.exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class VirtualThreadPool {

    public static <T> List<T> processAll(List<Callable<T>> tasks) {
        List<Future<T>> futures = new ArrayList<>();

        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (Callable<T> task : tasks) {
                futures.add(executor.submit(task));
            }
        }

        List<T> results = new ArrayList<>();
        for (Future<T> future : futures) {
            try {
                results.add(future.get());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Interrupted while collecting results", e);
            } catch (ExecutionException e) {
                throw new RuntimeException("Task failed", e.getCause());
            }
        }
        return results;
    }
}
