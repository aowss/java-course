package course.ch08.exercises;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MarkdownToHtmlTest {

    @Test
    void convertBold() {
        assertEquals("This is <strong>bold</strong> text",
                MarkdownToHtml.convert("This is **bold** text"));
    }

    @Test
    void convertItalic() {
        assertEquals("This is <em>italic</em> text",
                MarkdownToHtml.convert("This is *italic* text"));
    }

    @Test
    void convertCode() {
        assertEquals("Use <code>println</code> here",
                MarkdownToHtml.convert("Use `println` here"));
    }

    @Test
    void convertMixed() {
        assertEquals("<strong>bold</strong> and <em>italic</em> and <code>code</code>",
                MarkdownToHtml.convert("**bold** and *italic* and `code`"));
    }

    @Test
    void convertNoMarkdown() {
        assertEquals("plain text", MarkdownToHtml.convert("plain text"));
    }

    @Test
    void convertMultipleBold() {
        assertEquals("<strong>a</strong> and <strong>b</strong>",
                MarkdownToHtml.convert("**a** and **b**"));
    }
}
