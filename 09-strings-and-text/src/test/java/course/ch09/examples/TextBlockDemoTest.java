package course.ch09.examples;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TextBlockDemoTest {

    @Test
    void jsonTextBlockContainsNameAndAge() {
        var json = TextBlockDemo.jsonTextBlock("Alice", 30);
        assertTrue(json.contains("\"name\": \"Alice\""));
        assertTrue(json.contains("\"age\": 30"));
    }

    @Test
    void htmlSnippetContainsTitleAndBody() {
        var html = TextBlockDemo.htmlSnippet("Test", "Content");
        assertTrue(html.contains("<title>Test</title>"));
        assertTrue(html.contains("<body>Content</body>"));
    }

    @Test
    void formatPriceIncludesDollarSign() {
        var result = TextBlockDemo.formatPrice("Widget", 9.99);
        assertTrue(result.contains("Widget"));
        assertTrue(result.contains("$9.99"));
    }

    @Test
    void formatPriceUsesThousandsSeparator() {
        var result = TextBlockDemo.formatPrice("Expensive", 1234.50);
        assertTrue(result.contains("$1,234.50"));
    }
}
