package course.ch19.examples;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ServiceLoaderDemoTest {

    @Test
    void discoverToolsReturnsNonEmptyList() {
        List<String> tools = ServiceLoaderDemo.discoverTools();
        assertFalse(tools.isEmpty(), "JDK should provide at least one ToolProvider");
    }

    @Test
    void findToolReturnsFoundForJavac() {
        String result = ServiceLoaderDemo.findTool("javac");
        assertTrue(result.startsWith("Found tool:"), "javac should be discoverable");
    }

    @Test
    void findToolReturnsNotFoundForUnknown() {
        String result = ServiceLoaderDemo.findTool("nonexistent");
        assertTrue(result.startsWith("Tool not found:"));
    }
}
