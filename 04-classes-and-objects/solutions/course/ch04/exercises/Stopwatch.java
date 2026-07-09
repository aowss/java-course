package course.ch04.exercises;

public class Stopwatch {

    private boolean running;
    private long startNanos;
    private long accumulatedNanos;

    public void start() {
        if (running) {
            throw new IllegalStateException("Stopwatch is already running");
        }
        running = true;
        startNanos = System.nanoTime();
    }

    public void stop() {
        if (!running) {
            throw new IllegalStateException("Stopwatch is not running");
        }
        accumulatedNanos += System.nanoTime() - startNanos;
        running = false;
    }

    public long elapsedMillis() {
        long total = accumulatedNanos;
        if (running) {
            total += System.nanoTime() - startNanos;
        }
        return total / 1_000_000;
    }

    public void reset() {
        running = false;
        startNanos = 0;
        accumulatedNanos = 0;
    }

    public boolean isRunning() {
        return running;
    }
}
