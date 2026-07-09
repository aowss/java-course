package course.ch29.exercises;

import java.util.ArrayList;
import java.util.List;

/**
 * Exercise 2 (Practice): Build an event system using the Observer pattern.
 *
 * <p>Implement a generic {@link EventBus} that allows listeners to subscribe to
 * events and receive notifications when events are published.
 *
 * <pre>{@code
 * EventBus<String> bus = new EventBus<>();
 * bus.subscribe(msg -> System.out.println(msg));
 * bus.publish("user.login");
 * }</pre>
 */
public final class EventSystem {

    private EventSystem() {
    }

    /**
     * Functional listener for events of type {@code T}.
     *
     * @param <T> the event type
     */
    @FunctionalInterface
    public interface EventListener<T> {
        /**
         * Handles an event.
         *
         * @param event the event payload
         */
        void onEvent(T event);
    }

    /**
     * A simple event bus supporting subscribe/publish semantics.
     *
     * @param <T> the event type
     */
    public static final class EventBus<T> {

        private final List<EventListener<T>> listeners = new ArrayList<>();

        /**
         * Registers a listener.
         *
         * @param listener the listener to add
         */
        public void subscribe(EventListener<T> listener) {
            // TODO: add listener to the list
            throw new UnsupportedOperationException("Not yet implemented");
        }

        /**
         * Publishes an event to all registered listeners.
         *
         * @param event the event to publish
         */
        public void publish(T event) {
            // TODO: notify all listeners
            throw new UnsupportedOperationException("Not yet implemented");
        }

        /**
         * Returns the number of registered listeners.
         *
         * @return listener count
         */
        public int listenerCount() {
            return listeners.size();
        }
    }
}
