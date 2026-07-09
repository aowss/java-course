package course.ch12.exercises;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class LRUCache<K, V> {

    private final LinkedHashMap<K, V> map;

    public LRUCache(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("capacity must be at least 1");
        }
        map = new LinkedHashMap<>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > capacity;
            }
        };
    }

    public void put(K key, V value) {
        map.put(key, value);
    }

    public Optional<V> get(K key) {
        return Optional.ofNullable(map.get(key));
    }

    public int size() {
        return map.size();
    }

    public boolean containsKey(K key) {
        return map.containsKey(key);
    }
}
