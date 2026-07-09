package course.ch09.exercises;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ServiceRegistryTest {

    interface Greeter {
        String greet(String name);
    }

    interface Logger {
        void log(String message);
    }

    static class FriendlyGreeter implements Greeter {
        @Override
        public String greet(String name) {
            return "Hello, " + name + "!";
        }
    }

    static class FormalGreeter implements Greeter {
        @Override
        public String greet(String name) {
            return "Good day, " + name + ".";
        }
    }

    @Test
    void registerAndLookupSingleService() {
        var registry = new ServiceRegistry();
        var greeter = new FriendlyGreeter();
        registry.register(Greeter.class, greeter);

        List<Greeter> greeters = registry.lookupAll(Greeter.class);
        assertEquals(1, greeters.size());
        assertEquals("Hello, Alice!", greeters.getFirst().greet("Alice"));
    }

    @Test
    void registerMultipleImplementations() {
        var registry = new ServiceRegistry();
        registry.register(Greeter.class, new FriendlyGreeter());
        registry.register(Greeter.class, new FormalGreeter());

        List<Greeter> greeters = registry.lookupAll(Greeter.class);
        assertEquals(2, greeters.size());
    }

    @Test
    void lookupFirstReturnsFirstRegistered() {
        var registry = new ServiceRegistry();
        var friendly = new FriendlyGreeter();
        registry.register(Greeter.class, friendly);
        registry.register(Greeter.class, new FormalGreeter());

        Greeter first = registry.lookupFirst(Greeter.class);
        assertEquals("Hello, Bob!", first.greet("Bob"));
    }

    @Test
    void lookupFirstThrowsWhenNoneRegistered() {
        var registry = new ServiceRegistry();
        assertThrows(NoSuchElementException.class, () -> registry.lookupFirst(Greeter.class));
    }

    @Test
    void lookupAllReturnsEmptyForUnregisteredType() {
        var registry = new ServiceRegistry();
        List<Logger> loggers = registry.lookupAll(Logger.class);
        assertTrue(loggers.isEmpty());
    }

    @Test
    void differentServiceTypesAreIsolated() {
        var registry = new ServiceRegistry();
        registry.register(Greeter.class, new FriendlyGreeter());

        assertTrue(registry.lookupAll(Logger.class).isEmpty());
        assertEquals(1, registry.lookupAll(Greeter.class).size());
    }

    @Test
    void registerRejectsNullServiceType() {
        var registry = new ServiceRegistry();
        assertThrows(IllegalArgumentException.class,
                () -> registry.register(null, new FriendlyGreeter()));
    }

    @Test
    void registerRejectsNullImplementation() {
        var registry = new ServiceRegistry();
        assertThrows(IllegalArgumentException.class,
                () -> registry.register(Greeter.class, null));
    }

    @Test
    void lookupAllReturnsUnmodifiableList() {
        var registry = new ServiceRegistry();
        registry.register(Greeter.class, new FriendlyGreeter());

        List<Greeter> greeters = registry.lookupAll(Greeter.class);
        assertThrows(UnsupportedOperationException.class,
                () -> greeters.add(new FormalGreeter()));
    }
}
