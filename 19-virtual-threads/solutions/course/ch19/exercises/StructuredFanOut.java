package course.ch19.exercises;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;

public class StructuredFanOut {

    public static <T> T firstSuccess(List<Callable<T>> tasks) {
        if (tasks.isEmpty()) {
            throw new RuntimeException("No tasks provided");
        }
        AtomicReference<T> result = new AtomicReference<>();
        CountDownLatch done = new CountDownLatch(1);
        List<Throwable> failures = new ArrayList<>();
        CountDownLatch allFinished = new CountDownLatch(tasks.size());

        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            List<Future<?>> futures = new ArrayList<>();
            for (Callable<T> task : tasks) {
                futures.add(executor.submit(() -> {
                    try {
                        T value = task.call();
                        if (result.compareAndSet(null, value)) {
                            done.countDown();
                        }
                    } catch (Exception e) {
                        synchronized (failures) {
                            failures.add(e);
                        }
                    } finally {
                        allFinished.countDown();
                    }
                    return null;
                }));
            }

            try {
                allFinished.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }

            for (Future<?> future : futures) {
                future.cancel(true);
            }
        }

        T value = result.get();
        if (value != null) {
            return value;
        }

        RuntimeException exception = new RuntimeException("All tasks failed");
        synchronized (failures) {
            for (Throwable t : failures) {
                exception.addSuppressed(t);
            }
        }
        throw exception;
    }

    public static <T> List<T> allSuccesses(List<Callable<T>> tasks, Duration timeout) {
        if (tasks.isEmpty()) {
            return List.of();
        }
        List<Future<T>> futures = new ArrayList<>();
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (Callable<T> task : tasks) {
                futures.add(executor.submit(task));
            }

            try {
                Thread.sleep(timeout);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            for (Future<T> future : futures) {
                future.cancel(true);
            }
        }

        List<T> results = new ArrayList<>();
        for (Future<T> future : futures) {
            if (future.isDone() && !future.isCancelled()) {
                try {
                    results.add(future.get());
                } catch (Exception e) {
                    // skip failures
                }
            }
        }
        return results;
    }
}
