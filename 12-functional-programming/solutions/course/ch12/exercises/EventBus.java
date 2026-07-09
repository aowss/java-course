package course.ch12.exercises;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class EventBus {

    private final Map<Class<?>, List<Consumer<?>>> listeners = new HashMap<>();

    public <T> void register(Class<T> eventType, Consumer<T> listener) {
        listeners.computeIfAbsent(eventType, k -> new ArrayList<>()).add(listener);
    }

    @SuppressWarnings("unchecked")
    public <T> void publish(T event) {
        List<Consumer<?>> typeListeners = listeners.get(event.getClass());
        if (typeListeners == null) {
            return;
        }
        for (Consumer<?> listener : typeListeners) {
            ((Consumer<T>) listener).accept(event);
        }
    }
}
