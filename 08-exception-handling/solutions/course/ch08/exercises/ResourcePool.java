package course.ch08.exercises;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.function.Supplier;

public class ResourcePool<T extends AutoCloseable> implements AutoCloseable {

    private final Supplier<T> factory;
    private final int maxSize;
    private final Deque<T> available = new ArrayDeque<>();
    private final List<T> all = new ArrayList<>();

    public ResourcePool(Supplier<T> factory, int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize must be positive");
        }
        this.factory = factory;
        this.maxSize = maxSize;
    }

    public T borrow() {
        if (!available.isEmpty()) {
            return available.poll();
        }
        if (all.size() >= maxSize) {
            throw new IllegalStateException("Pool exhausted: all " + maxSize + " resources are in use");
        }
        T resource = factory.get();
        all.add(resource);
        return resource;
    }

    public void release(T resource) {
        if (resource == null) {
            throw new IllegalArgumentException("Resource must not be null");
        }
        available.offer(resource);
    }

    @Override
    public void close() throws Exception {
        Exception first = null;
        for (T resource : all) {
            try {
                resource.close();
            } catch (Exception e) {
                if (first == null) {
                    first = e;
                } else {
                    first.addSuppressed(e);
                }
            }
        }
        all.clear();
        available.clear();
        if (first != null) {
            throw first;
        }
    }
}
