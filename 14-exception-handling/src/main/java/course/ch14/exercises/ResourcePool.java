package course.ch14.exercises;

import java.util.function.Supplier;

/**
 * Exercise 3 (Challenge): A pool of {@link AutoCloseable} resources.
 *
 * <p>The pool lazily creates resources using a {@link Supplier} factory and reuses them
 * when they are released back to the pool. Closing the pool closes all resources,
 * collecting any exceptions as suppressed exceptions.
 *
 * <pre>{@code
 * try (var pool = new ResourcePool<>(Connection::new, 5)) {
 *     var conn = pool.borrow();
 *     // use conn ...
 *     pool.release(conn);
 * }
 * }</pre>
 *
 * @param <T> the type of resource managed by the pool
 */
public class ResourcePool<T extends AutoCloseable> implements AutoCloseable {

    /**
     * Creates a resource pool.
     *
     * @param factory the supplier used to create new resources
     * @param maxSize the maximum number of resources the pool can manage
     * @throws IllegalArgumentException if {@code maxSize} is not positive
     */
    public ResourcePool(Supplier<T> factory, int maxSize) {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Borrows a resource from the pool.
     *
     * <p>If the pool has available resources, one is returned. Otherwise, a new resource
     * is created using the factory, as long as the total number of managed resources
     * has not reached {@code maxSize}.
     *
     * @return a resource
     * @throws IllegalStateException if the pool is at maximum capacity and all resources are in use
     */
    public T borrow() {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns a resource to the pool for reuse.
     *
     * @param resource the resource to return
     * @throws IllegalArgumentException if the resource is {@code null}
     */
    public void release(T resource) {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Closes all resources in the pool.
     *
     * <p>If any resource throws during close, the first exception is thrown and subsequent
     * exceptions are added as suppressed exceptions.
     *
     * @throws Exception if any resource fails to close
     */
    @Override
    public void close() throws Exception {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
