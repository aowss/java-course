package course.ch12.exercises;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LRUCacheTest {

    @Test
    void putAndGet() {
        var cache = new LRUCache<String, Integer>(3);
        cache.put("a", 1);
        cache.put("b", 2);
        assertEquals(Optional.of(1), cache.get("a"));
        assertEquals(Optional.of(2), cache.get("b"));
    }

    @Test
    void getReturnsEmptyForMissingKey() {
        var cache = new LRUCache<String, Integer>(3);
        assertEquals(Optional.empty(), cache.get("missing"));
    }

    @Test
    void evictsLeastRecentlyUsed() {
        var cache = new LRUCache<String, Integer>(2);
        cache.put("a", 1);
        cache.put("b", 2);
        cache.get("a");       // "a" is now most recently used
        cache.put("c", 3);    // should evict "b"
        assertFalse(cache.containsKey("b"));
        assertTrue(cache.containsKey("a"));
        assertTrue(cache.containsKey("c"));
    }

    @Test
    void updateExistingKeyDoesNotIncreaseSize() {
        var cache = new LRUCache<String, Integer>(2);
        cache.put("a", 1);
        cache.put("a", 10);
        assertEquals(1, cache.size());
        assertEquals(Optional.of(10), cache.get("a"));
    }

    @Test
    void sizeTracksEntries() {
        var cache = new LRUCache<String, Integer>(5);
        cache.put("a", 1);
        cache.put("b", 2);
        assertEquals(2, cache.size());
    }

    @Test
    void invalidCapacityThrows() {
        assertThrows(IllegalArgumentException.class, () -> new LRUCache<String, Integer>(0));
    }
}
