package course.ch18.exercises;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;

public class WebScraper {

    public static Map<String, String> fetchAll(List<String> urls) {
        Map<String, Future<String>> futures = new HashMap<>();
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (String url : urls) {
                futures.put(url, executor.submit(() -> {
                    Thread.sleep(Duration.ofMillis(10));
                    return "Content of " + url;
                }));
            }
        }
        Map<String, String> results = new HashMap<>();
        for (Map.Entry<String, Future<String>> entry : futures.entrySet()) {
            try {
                results.put(entry.getKey(), entry.getValue().get());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e.getCause());
            }
        }
        return results;
    }

    public static Map<String, String> fetchAllWithTimeout(List<String> urls, Duration timeout)
            throws TimeoutException {
        Instant deadline = Instant.now().plus(timeout);
        Map<String, Future<String>> futures = new HashMap<>();
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (String url : urls) {
                futures.put(url, executor.submit(() -> {
                    Thread.sleep(Duration.ofMillis(10));
                    return "Content of " + url;
                }));
            }
        }
        Map<String, String> results = new HashMap<>();
        for (Map.Entry<String, Future<String>> entry : futures.entrySet()) {
            if (Instant.now().isAfter(deadline)) {
                throw new TimeoutException("Operation exceeded timeout of " + timeout);
            }
            try {
                Duration remaining = Duration.between(Instant.now(), deadline);
                if (remaining.isNegative() || remaining.isZero()) {
                    throw new TimeoutException("Operation exceeded timeout of " + timeout);
                }
                results.put(entry.getKey(),
                        entry.getValue().get(remaining.toMillis(), java.util.concurrent.TimeUnit.MILLISECONDS));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e.getCause());
            } catch (java.util.concurrent.TimeoutException e) {
                throw new TimeoutException("Operation exceeded timeout of " + timeout);
            }
        }
        return results;
    }
}
