package course.ch28.examples;

/**
 * Demonstrates JVM memory areas accessible via {@link Runtime}.
 *
 * <p>The JVM divides memory into heap (objects), metaspace (class metadata),
 * and thread stacks (method frames). {@link Runtime} exposes heap sizing and usage.
 *
 * <pre>{@code
 * MemorySnapshot snap = MemoryDemo.captureSnapshot();
 * System.out.println(snap.usedHeapMb() + " MB used");
 * }</pre>
 */
public final class MemoryDemo {

    private MemoryDemo() {
    }

    /**
     * Snapshot of JVM heap memory at a point in time.
     *
     * @param totalHeapBytes total heap size in bytes
     * @param freeHeapBytes  free heap in bytes
     * @param maxHeapBytes   maximum heap size in bytes
     */
    public record MemorySnapshot(long totalHeapBytes, long freeHeapBytes, long maxHeapBytes) {

        /**
         * Returns used heap memory in bytes.
         *
         * @return total minus free
         */
        public long usedHeapBytes() {
            return totalHeapBytes - freeHeapBytes;
        }

        /**
         * Returns used heap memory in megabytes (rounded down).
         *
         * @return used megabytes
         */
        public long usedHeapMb() {
            return usedHeapBytes() / (1024 * 1024);
        }
    }

    /**
     * Captures current heap memory statistics from {@link Runtime}.
     *
     * @return a memory snapshot
     */
    public static MemorySnapshot captureSnapshot() {
        Runtime runtime = Runtime.getRuntime();
        return new MemorySnapshot(
                runtime.totalMemory(),
                runtime.freeMemory(),
                runtime.maxMemory()
        );
    }

    /**
     * Allocates a byte array of the given size on the heap.
     *
     * @param bytes the number of bytes to allocate
     * @return the allocated array (caller should retain a reference to prevent GC)
     */
    public static byte[] allocate(long bytes) {
        if (bytes > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Allocation exceeds int max: " + bytes);
        }
        return new byte[(int) bytes];
    }

    /**
     * Suggests garbage collection and returns heap usage before and after.
     *
     * @return an array of two snapshots: [before, after]
     */
    public static MemorySnapshot[] gcAndCompare() {
        MemorySnapshot before = captureSnapshot();
        System.gc();
        MemorySnapshot after = captureSnapshot();
        return new MemorySnapshot[]{before, after};
    }

    public static void main(String[] args) {
        MemorySnapshot snap = captureSnapshot();
        System.out.println("Total heap: " + snap.totalHeapBytes() / (1024 * 1024) + " MB");
        System.out.println("Used heap:  " + snap.usedHeapMb() + " MB");
        System.out.println("Max heap:   " + snap.maxHeapBytes() / (1024 * 1024) + " MB");

        byte[] buffer = allocate(1024 * 1024);
        System.out.println("Allocated:  " + buffer.length + " bytes");
    }
}
