package course.ch28.exercises;

/**
 * Exercise 1 (Guided): Analyze JVM heap memory using {@link Runtime}.
 *
 * <p>Implement methods that report heap usage as human-readable strings and percentages.
 *
 * <pre>{@code
 * String report = MemoryAnalyzer.heapReport();
 * // "used=45MB total=128MB max=4096MB (35% of max)"
 * }</pre>
 */
public final class MemoryAnalyzer {

    private MemoryAnalyzer() {
    }

    /**
     * Returns a formatted heap usage report.
     *
     * <p>Format: {@code "used=XMB total=YMB max=ZMB (P% of max)"} where all values
     * are whole megabytes (rounded down) and P is the percentage of max heap used.
     *
     * @return the heap report string
     */
    public static String heapReport() {
        // TODO: use Runtime.getRuntime() to compute used, total, max in MB
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns the percentage of max heap currently in use.
     *
     * @return a value between 0.0 and 100.0
     */
    public static double usedPercentageOfMax() {
        // TODO: compute (totalMemory - freeMemory) / maxMemory * 100
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns {@code true} if less than the given percentage of max heap is in use.
     *
     * @param thresholdPercent the threshold percentage (0–100)
     * @return {@code true} if current usage is below the threshold
     */
    public static boolean isBelowThreshold(double thresholdPercent) {
        // TODO: compare usedPercentageOfMax() against thresholdPercent
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
