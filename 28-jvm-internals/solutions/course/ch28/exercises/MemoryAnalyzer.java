package course.ch28.exercises;

public final class MemoryAnalyzer {

    private MemoryAnalyzer() {
    }

    public static String heapReport() {
        Runtime runtime = Runtime.getRuntime();
        long usedMb = toMb(runtime.totalMemory() - runtime.freeMemory());
        long totalMb = toMb(runtime.totalMemory());
        long maxMb = toMb(runtime.maxMemory());
        long percent = maxMb == 0 ? 0 : usedMb * 100 / maxMb;
        return "used=" + usedMb + "MB total=" + totalMb + "MB max=" + maxMb + "MB (" + percent + "% of max)";
    }

    public static double usedPercentageOfMax() {
        Runtime runtime = Runtime.getRuntime();
        long used = runtime.totalMemory() - runtime.freeMemory();
        long max = runtime.maxMemory();
        if (max == 0) {
            return 0.0;
        }
        return used * 100.0 / max;
    }

    public static boolean isBelowThreshold(double thresholdPercent) {
        return usedPercentageOfMax() < thresholdPercent;
    }

    private static long toMb(long bytes) {
        return bytes / (1024 * 1024);
    }
}
