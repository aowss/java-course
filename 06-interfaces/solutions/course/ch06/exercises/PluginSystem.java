package course.ch06.exercises;

import java.util.ArrayList;
import java.util.List;

public class PluginSystem {

    public interface Plugin {
        String name();

        default void onLoad() {
        }

        String execute();
    }

    public static class PluginManager {

        private final List<Plugin> plugins = new ArrayList<>();

        public void register(Plugin plugin) {
            plugins.add(plugin);
            plugin.onLoad();
        }

        public List<String> executeAll() {
            List<String> results = new ArrayList<>();
            for (Plugin plugin : plugins) {
                results.add(plugin.execute());
            }
            return results;
        }

        public List<String> registeredNames() {
            return plugins.stream().map(Plugin::name).toList();
        }

        public int size() {
            return plugins.size();
        }
    }

    public static void main(String[] args) {
        PluginManager manager = new PluginManager();
        manager.register(new Plugin() {
            @Override
            public String name() {
                return "greeter";
            }

            @Override
            public String execute() {
                return "Hello from greeter";
            }
        });
        System.out.println(manager.executeAll());
    }
}
