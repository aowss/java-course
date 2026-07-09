package course.ch19.examples;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;
import java.util.spi.ToolProvider;

/**
 * Demonstrates the {@link ServiceLoader} pattern — Java's built-in
 * mechanism for discovering and loading service implementations at runtime.
 *
 * <p>This example uses {@link ToolProvider}, a real service interface
 * from the JDK, to discover available command-line tools (like {@code javac},
 * {@code jar}, etc.) that are registered via the ServiceLoader mechanism.
 */
public class ServiceLoaderDemo {

    /**
     * Discovers all registered {@link ToolProvider} implementations
     * available in the current JVM.
     *
     * @return a list of tool names
     */
    public static List<String> discoverTools() {
        var toolNames = new ArrayList<String>();
        ServiceLoader<ToolProvider> loader = ServiceLoader.load(ToolProvider.class);
        for (ToolProvider provider : loader) {
            toolNames.add(provider.name());
        }
        return toolNames;
    }

    /**
     * Finds a specific tool by name using {@link ToolProvider#findFirst(String)}.
     *
     * @param name the tool name (e.g., "javac", "jar")
     * @return a description of the tool, or a "not found" message
     */
    public static String findTool(String name) {
        return ToolProvider.findFirst(name)
                .map(tp -> "Found tool: " + tp.name())
                .orElse("Tool not found: " + name);
    }

    public static void main(String[] args) {
        System.out.println("=== ServiceLoader Demo ===");
        System.out.println();

        List<String> tools = discoverTools();
        System.out.println("Discovered " + tools.size() + " tool(s):");
        tools.forEach(name -> System.out.println("  - " + name));

        System.out.println();
        System.out.println(findTool("javac"));
        System.out.println(findTool("nonexistent"));
    }
}
