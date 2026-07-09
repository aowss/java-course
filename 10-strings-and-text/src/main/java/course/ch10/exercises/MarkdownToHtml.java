package course.ch10.exercises;

/**
 * Exercise 3 (Challenge): Convert simple Markdown to HTML.
 *
 * <p>Implement the {@link #convert(String)} method to convert the following
 * Markdown syntax to HTML:
 * <ul>
 *   <li>{@code **bold**} → {@code <strong>bold</strong>}</li>
 *   <li>{@code *italic*} → {@code <em>italic</em>}</li>
 *   <li>{@code `code`} → {@code <code>code</code>}</li>
 * </ul>
 *
 * <p>Process in order: bold first, then italic, then code. This avoids
 * conflicts between {@code **} and {@code *}.
 */
public class MarkdownToHtml {

    /**
     * Converts simple Markdown formatting to HTML tags.
     *
     * @param markdown the input Markdown string
     * @return the HTML string with formatting tags applied
     */
    public static String convert(String markdown) {
        // TODO: implement — hint: use String.replaceAll() with regex patterns
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static void main(String[] args) {
        System.out.println(convert("This is **bold** and *italic* and `code`."));
    }
}
