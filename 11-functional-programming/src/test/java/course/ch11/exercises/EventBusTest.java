package course.ch11.exercises;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EventBusTest {

    @Test
    void publishNotifiesRegisteredListener() {
        var bus = new EventBus();
        var received = new ArrayList<String>();
        bus.register(String.class, received::add);
        bus.publish("hello");
        assertEquals(List.of("hello"), received);
    }

    @Test
    void publishNotifiesMultipleListeners() {
        var bus = new EventBus();
        var first = new ArrayList<String>();
        var second = new ArrayList<String>();
        bus.register(String.class, first::add);
        bus.register(String.class, second::add);
        bus.publish("event");
        assertEquals(List.of("event"), first);
        assertEquals(List.of("event"), second);
    }

    @Test
    void publishDoesNotNotifyUnregisteredType() {
        var bus = new EventBus();
        var received = new ArrayList<Integer>();
        bus.register(Integer.class, received::add);
        bus.publish("not an integer");
        assertTrue(received.isEmpty());
    }

    @Test
    void publishWithDifferentEventTypes() {
        var bus = new EventBus();
        var strings = new ArrayList<String>();
        var integers = new ArrayList<Integer>();
        bus.register(String.class, strings::add);
        bus.register(Integer.class, integers::add);
        bus.publish("text");
        bus.publish(42);
        assertEquals(List.of("text"), strings);
        assertEquals(List.of(42), integers);
    }
}
