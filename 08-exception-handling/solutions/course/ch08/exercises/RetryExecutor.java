package course.ch08.exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class RetryExecutor {

    private final int maxRetries;
    private int attemptCount;

    public RetryExecutor(int maxRetries) {
        if (maxRetries < 0) {
            throw new IllegalArgumentException("maxRetries must not be negative");
        }
        this.maxRetries = maxRetries;
    }

    public <T> T execute(Callable<T> task) {
        List<Exception> failures = new ArrayList<>();
        attemptCount = 0;

        for (int i = 0; i <= maxRetries; i++) {
            attemptCount++;
            try {
                return task.call();
            } catch (Exception e) {
                failures.add(e);
            }
        }

        Exception last = failures.removeLast();
        var wrapper = new RuntimeException("All " + attemptCount + " attempts failed", last);
        for (Exception earlier : failures) {
            wrapper.addSuppressed(earlier);
        }
        throw wrapper;
    }

    public int getAttemptCount() {
        return attemptCount;
    }
}
