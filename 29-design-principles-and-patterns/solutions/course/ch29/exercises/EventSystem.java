package course.ch29.exercises;

import java.util.ArrayList;
import java.util.List;

public final class EventSystem {

    private EventSystem() {
    }

    @FunctionalInterface
    public interface EventListener<T> {
        void onEvent(T event);
    }

    public static final class EventBus<T> {

        private final List<EventListener<T>> listeners = new ArrayList<>();

        public void subscribe(EventListener<T> listener) {
            listeners.add(listener);
        }

        public void publish(T event) {
            for (EventListener<T> listener : listeners) {
                listener.onEvent(event);
            }
        }

        public int listenerCount() {
            return listeners.size();
        }
    }
}
