package course.ch29.exercises;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventSystemTest {

    @Test
    void publishNotifiesSubscribers() {
        EventSystem.EventBus<String> bus = new EventSystem.EventBus<>();
        List<String> received = new ArrayList<>();
        bus.subscribe(received::add);
        bus.publish("event.one");
        bus.publish("event.two");
        assertEquals(List.of("event.one", "event.two"), received);
    }

    @Test
    void multipleSubscribersAllReceiveEvents() {
        EventSystem.EventBus<Integer> bus = new EventSystem.EventBus<>();
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        bus.subscribe(a::add);
        bus.subscribe(b::add);
        bus.publish(42);
        assertEquals(List.of(42), a);
        assertEquals(List.of(42), b);
    }

    @Test
    void listenerCountTracksSubscriptions() {
        EventSystem.EventBus<String> bus = new EventSystem.EventBus<>();
        assertEquals(0, bus.listenerCount());
        bus.subscribe(msg -> {});
        bus.subscribe(msg -> {});
        assertEquals(2, bus.listenerCount());
    }
}
