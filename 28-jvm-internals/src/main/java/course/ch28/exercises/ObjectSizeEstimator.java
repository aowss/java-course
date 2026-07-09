package course.ch28.exercises;

/**
 * Exercise 3 (Challenge): Estimate object sizes using heuristic calculations.
 *
 * <p>Without instrumentation agents, exact object sizes are unavailable. This exercise
 * uses well-known HotSpot approximations for common types.
 *
 * <pre>{@code
 * long size = ObjectSizeEstimator.estimate(new int[100]);
 * // approximately 400 + header bytes
 * }</pre>
 */
public final class ObjectSizeEstimator {

    /** Approximate object header size in bytes (64-bit JVM with compressed oops). */
    public static final int OBJECT_HEADER_BYTES = 12;

    /** Approximate array header size in bytes. */
    public static final int ARRAY_HEADER_BYTES = 16;

    /** Approximate reference size in bytes. */
    public static final int REFERENCE_BYTES = 4;

    private ObjectSizeEstimator() {
    }

    /**
     * Estimates the shallow size of an object in bytes.
     *
     * <p>Rules:
     * <ul>
     *   <li>{@code null} → 0</li>
     *   <li>Primitive wrapper ({@link Integer}, etc.) → header + 4 bytes for the value</li>
     *   <li>{@code String} → header + array header + 2 bytes per char + reference</li>
     *   <li>Array of primitives → array header + element size × length</li>
     *   <li>Other objects → header + reference size per declared non-static field</li>
     * </ul>
     *
     * @param obj the object to estimate (may be {@code null})
     * @return estimated size in bytes
     */
    public static long estimate(Object obj) {
        // TODO: apply heuristic rules based on object type
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Estimates the size of a {@code String} including its internal {@code byte[]} or {@code char[]}.
     *
     * @param value the string (must not be {@code null})
     * @return estimated size in bytes
     */
    public static long estimateString(String value) {
        // TODO: header + array header + 2 * length + reference
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Estimates the size of a primitive array.
     *
     * @param array     the array (must not be {@code null})
     * @param bytesPerElement element size (e.g., 4 for {@code int}, 1 for {@code byte})
     * @return estimated size in bytes
     */
    public static long estimatePrimitiveArray(Object array, int bytesPerElement) {
        // TODO: array header + length * bytesPerElement
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
