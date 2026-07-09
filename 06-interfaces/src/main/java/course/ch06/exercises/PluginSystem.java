package course.ch06.exercises;

import java.util.List;

/**
 * Exercise 3 (Challenge): Plugin system with lifecycle hooks.
 *
 * <p>Implement a small plugin framework where plugins can be registered,
 * initialized, and executed in registration order.
 */
public class PluginSystem {

    /**
     * A plugin that can be initialized and executed.
     */
    public interface Plugin {

        /**
         * @return the unique plugin name
         */
        String name();

        /**
         * Called once when the plugin is registered.
         */
        default void onLoad() {
        }

        /**
         * Performs the plugin's main action.
         *
         * @return a result message
         */
        String execute();
    }

    /**
     * Manages plugin registration and execution.
     */
    public static class PluginManager {

        /**
         * Registers a plugin and calls its {@link Plugin#onLoad()} hook.
         *
         * @param plugin the plugin to register
         */
        public void register(Plugin plugin) {
            // TODO: implement — store plugin and call onLoad()
            throw new UnsupportedOperationException("Not yet implemented");
        }

        /**
         * Executes all registered plugins in registration order.
         *
         * @return the list of result messages
         */
        public List<String> executeAll() {
            // TODO: implement — call execute() on each plugin in order
            throw new UnsupportedOperationException("Not yet implemented");
        }

        /**
         * Returns the names of registered plugins in registration order.
         *
         * @return the plugin names
         */
        public List<String> registeredNames() {
            // TODO: implement
            throw new UnsupportedOperationException("Not yet implemented");
        }

        /**
         * Returns the number of registered plugins.
         *
         * @return the plugin count
         */
        public int size() {
            // TODO: implement
            throw new UnsupportedOperationException("Not yet implemented");
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
