package course.ch28.examples;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

/**
 * Demonstrates observing garbage collector activity via JMX {@link GarbageCollectorMXBean}.
 *
 * <p>The JVM exposes GC statistics through the platform MBean server. Each
 * {@link GarbageCollectorMXBean} reports collection count and total time in milliseconds.
 *
 * <pre>{@code
 * List<GcStats> stats = GCObserver.collectStats();
 * stats.forEach(s -> System.out.println(s.name() + ": " + s.collectionCount()));
 * }</pre>
 */
public final class GCObserver {

    private GCObserver() {
    }

    /**
     * GC statistics for a single garbage collector.
     *
     * @param name             the collector name (e.g., "G1 Young Generation")
     * @param collectionCount  the number of collections performed
     * @param collectionTimeMs total time spent collecting in milliseconds
     */
    public record GcStats(String name, long collectionCount, long collectionTimeMs) {
    }

    /**
     * Returns GC statistics for all registered garbage collectors.
     *
     * @return a list of GC stats (never empty on a standard HotSpot JVM)
     */
    public static List<GcStats> collectStats() {
        return ManagementFactory.getGarbageCollectorMXBeans().stream()
                .map(bean -> new GcStats(
                        bean.getName(),
                        bean.getCollectionCount(),
                        bean.getCollectionTime()
                ))
                .toList();
    }

    /**
     * Returns the total collection count across all garbage collectors.
     *
     * @return sum of all collection counts
     */
    public static long totalCollectionCount() {
        return collectStats().stream()
                .mapToLong(GcStats::collectionCount)
                .sum();
    }

    /**
     * Allocates temporary objects to encourage GC, then returns whether the total
     * collection count increased.
     *
     * @return {@code true} if at least one additional collection was recorded
     */
    public static boolean observeCollectionAfterAllocation() {
        long before = totalCollectionCount();
        for (int i = 0; i < 100; i++) {
            byte[] temp = new byte[1024 * 512];
            temp = null;
        }
        System.gc();
        long after = totalCollectionCount();
        return after > before;
    }

    public static void main(String[] args) {
        System.out.println("--- GC Collectors ---");
        for (GcStats stats : collectStats()) {
            System.out.printf("  %s: count=%d, time=%dms%n",
                    stats.name(), stats.collectionCount(), stats.collectionTimeMs());
        }
        System.out.println("Total collections: " + totalCollectionCount());
    }
}
