package course.ch11.exercises;

import java.util.function.Consumer;

/**
 * Exercise 3 (Challenge): An event bus that dispatches events to registered listeners.
 *
 * <p>Listeners are registered for a specific event type using {@link #register(Class, Consumer)}.
 * When an event is published with {@link #publish(Object)}, all listeners registered for that
 * event's exact type are notified.
 *
 * <p>Usage:
 * <pre>
 *   var bus = new EventBus();
 *   bus.register(String.class, s -&gt; System.out.println("Got: " + s));
 *   bus.publish("hello");  // prints "Got: hello"
 * </pre>
 */
public class EventBus {

    /**
     * Registers a listener for events of the given type.
     *
     * @param eventType the class of events to listen for
     * @param listener  the consumer to invoke when a matching event is published
     * @param <T>       the event type
     */
    public <T> void register(Class<T> eventType, Consumer<T> listener) {
        // TODO: store the listener for the given event type
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Publishes an event to all listeners registered for its runtime type.
     *
     * @param event the event to publish (must not be {@code null})
     * @param <T>   the event type
     */
    public <T> void publish(T event) {
        // TODO: look up listeners for event.getClass() and invoke each one
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
