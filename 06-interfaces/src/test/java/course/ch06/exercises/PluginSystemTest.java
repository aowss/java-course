package course.ch06.exercises;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PluginSystemTest {

    @Test
    void registerStoresPluginAndCallsOnLoad() {
        AtomicBoolean loaded = new AtomicBoolean(false);
        PluginSystem.Plugin plugin = new PluginSystem.Plugin() {
            @Override
            public String name() {
                return "test";
            }

            @Override
            public void onLoad() {
                loaded.set(true);
            }

            @Override
            public String execute() {
                return "done";
            }
        };

        PluginSystem.PluginManager manager = new PluginSystem.PluginManager();
        manager.register(plugin);

        assertTrue(loaded.get());
        assertEquals(1, manager.size());
        assertEquals(List.of("test"), manager.registeredNames());
    }

    @Test
    void executeAllRunsInRegistrationOrder() {
        PluginSystem.PluginManager manager = new PluginSystem.PluginManager();
        List<String> order = new ArrayList<>();

        manager.register(new PluginSystem.Plugin() {
            @Override
            public String name() {
                return "first";
            }

            @Override
            public String execute() {
                order.add("first");
                return "first-result";
            }
        });

        manager.register(new PluginSystem.Plugin() {
            @Override
            public String name() {
                return "second";
            }

            @Override
            public String execute() {
                order.add("second");
                return "second-result";
            }
        });

        assertEquals(List.of("first-result", "second-result"), manager.executeAll());
        assertEquals(List.of("first", "second"), order);
    }

    @Test
    void executeAllReturnsEmptyForNoPlugins() {
        PluginSystem.PluginManager manager = new PluginSystem.PluginManager();
        assertTrue(manager.executeAll().isEmpty());
    }

    @Test
    void registeredNamesPreservesOrder() {
        PluginSystem.PluginManager manager = new PluginSystem.PluginManager();
        manager.register(pluginNamed("alpha"));
        manager.register(pluginNamed("beta"));
        assertEquals(List.of("alpha", "beta"), manager.registeredNames());
    }

    private static PluginSystem.Plugin pluginNamed(String name) {
        return new PluginSystem.Plugin() {
            @Override
            public String name() {
                return name;
            }

            @Override
            public String execute() {
                return name;
            }
        };
    }
}
