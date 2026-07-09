package course.ch12.exercises;

import java.util.Optional;

/**
 * Exercise 3 (Challenge): Least Recently Used (LRU) cache.
 *
 * <p>Implement a fixed-capacity cache that evicts the least recently used
 * entry when the capacity is exceeded. Use {@link java.util.LinkedHashMap}
 * with access-order to track recency.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public class LRUCache<K, V> {

    /**
     * Creates an LRU cache with the given maximum capacity.
     *
     * @param capacity the maximum number of entries
     * @throws IllegalArgumentException if capacity is less than 1
     */
    public LRUCache(int capacity) {
        // TODO: implement — initialize a LinkedHashMap with accessOrder=true
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Adds or updates an entry in the cache. If the cache exceeds capacity,
     * the least recently used entry is evicted.
     *
     * @param key   the key
     * @param value the value
     */
    public void put(K key, V value) {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns the value associated with the key, marking it as recently used.
     *
     * @param key the key to look up
     * @return an {@link Optional} containing the value, or empty if not present
     */
    public Optional<V> get(K key) {
        // TODO: implement — hint: LinkedHashMap.get() updates access order automatically
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns the number of entries in the cache.
     *
     * @return the current size
     */
    public int size() {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns {@code true} if the cache contains the given key.
     *
     * @param key the key to check
     * @return {@code true} if present
     */
    public boolean containsKey(K key) {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
