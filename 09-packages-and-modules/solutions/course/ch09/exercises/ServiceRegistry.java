package course.ch09.exercises;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class ServiceRegistry {

    private final Map<Class<?>, List<Object>> registry = new HashMap<>();

    public <T> void register(Class<T> serviceType, T implementation) {
        if (serviceType == null) {
            throw new IllegalArgumentException("Service type must not be null");
        }
        if (implementation == null) {
            throw new IllegalArgumentException("Implementation must not be null");
        }
        registry.computeIfAbsent(serviceType, k -> new ArrayList<>()).add(implementation);
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> lookupAll(Class<T> serviceType) {
        List<Object> impls = registry.getOrDefault(serviceType, List.of());
        List<T> typed = new ArrayList<>();
        for (Object impl : impls) {
            typed.add((T) impl);
        }
        return Collections.unmodifiableList(typed);
    }

    public <T> T lookupFirst(Class<T> serviceType) {
        List<T> all = lookupAll(serviceType);
        if (all.isEmpty()) {
            throw new NoSuchElementException("No implementation registered for " + serviceType.getName());
        }
        return all.getFirst();
    }
}
