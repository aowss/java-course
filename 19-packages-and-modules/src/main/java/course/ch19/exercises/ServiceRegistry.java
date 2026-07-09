package course.ch19.exercises;

import java.util.List;

/**
 * Exercise 3 (Challenge): A simple service locator pattern.
 *
 * <p>Implement a registry that mimics {@link java.util.ServiceLoader} behavior:
 * <ul>
 *   <li>Register service implementations for a given service interface</li>
 *   <li>Look up all implementations for a service type</li>
 *   <li>Look up the first implementation for a service type</li>
 * </ul>
 *
 * <p>The registry should be generic and type-safe.
 */
public class ServiceRegistry {

    /**
     * Registers a service implementation for the given service type.
     *
     * @param serviceType      the service interface class
     * @param implementation   an instance implementing the service
     * @param <T>              the service type
     * @throws IllegalArgumentException if either argument is null
     */
    public <T> void register(Class<T> serviceType, T implementation) {
        // TODO: implement this method
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns all registered implementations for the given service type.
     *
     * @param serviceType the service interface class
     * @param <T>         the service type
     * @return an unmodifiable list of implementations (empty if none registered)
     */
    public <T> List<T> lookupAll(Class<T> serviceType) {
        // TODO: implement this method
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns the first registered implementation for the given service type.
     *
     * @param serviceType the service interface class
     * @param <T>         the service type
     * @return the first implementation
     * @throws java.util.NoSuchElementException if no implementation is registered
     */
    public <T> T lookupFirst(Class<T> serviceType) {
        // TODO: implement this method
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
