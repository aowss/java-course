package course.ch08.exercises;

import java.util.Map;

/**
 * Exercise 2 (Practice): A simple template engine.
 *
 * <p>Implement the {@link #render(String, Map)} method to replace
 * {@code {{placeholder}}} patterns in a template string with values
 * from a map.
 *
 * <p>Example:
 * <pre>
 *   render("Hello, {{name}}!", Map.of("name", "Alice"))
 *   → "Hello, Alice!"
 * </pre>
 */
public class TemplateEngine {

    /**
     * Replaces all {@code {{key}}} placeholders in the template with values from the map.
     *
     * <p>If a placeholder key is not found in the map, it is left unchanged.
     *
     * @param template the template string containing {@code {{key}}} placeholders
     * @param values   the map of placeholder names to replacement values
     * @return the rendered string
     */
    public static String render(String template, Map<String, String> values) {
        // TODO: implement — hint: iterate over map entries or use regex with Pattern
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static void main(String[] args) {
        var template = "Dear {{name}}, your order #{{orderId}} is {{status}}.";
        var values = Map.of("name", "Alice", "orderId", "12345", "status", "shipped");
        System.out.println(render(template, values));
    }
}
