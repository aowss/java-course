package course.ch08.exercises;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TemplateEngineTest {

    @Test
    void renderReplacesPlaceholder() {
        assertEquals("Hello, Alice!",
                TemplateEngine.render("Hello, {{name}}!", Map.of("name", "Alice")));
    }

    @Test
    void renderReplacesMultiplePlaceholders() {
        var template = "{{greeting}}, {{name}}!";
        var values = Map.of("greeting", "Hi", "name", "Bob");
        assertEquals("Hi, Bob!", TemplateEngine.render(template, values));
    }

    @Test
    void renderLeavesUnknownPlaceholdersUnchanged() {
        assertEquals("Hello, {{unknown}}!",
                TemplateEngine.render("Hello, {{unknown}}!", Map.of("name", "Alice")));
    }

    @Test
    void renderHandlesEmptyMap() {
        assertEquals("{{name}}", TemplateEngine.render("{{name}}", Map.of()));
    }

    @Test
    void renderReplacesRepeatedPlaceholders() {
        assertEquals("AB and AB",
                TemplateEngine.render("{{x}}B and {{x}}B", Map.of("x", "A")));
    }

    @Test
    void renderWithNoPlaceholders() {
        assertEquals("plain text",
                TemplateEngine.render("plain text", Map.of("key", "val")));
    }
}
