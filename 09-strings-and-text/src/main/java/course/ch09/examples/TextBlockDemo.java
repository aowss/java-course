package course.ch09.examples;

/**
 * Demonstrates text blocks (JEP 378) and string formatting methods.
 *
 * <p>Text blocks, introduced in Java 13 and finalized in Java 15, allow
 * multi-line string literals using triple quotes ({@code """}). This class
 * also demonstrates {@link String#format(String, Object...)} and
 * {@link String#formatted(Object...)}.
 */
public class TextBlockDemo {

    /**
     * Returns a JSON string built using a text block.
     *
     * @param name the person's name
     * @param age  the person's age
     * @return a formatted JSON string
     */
    public static String jsonTextBlock(String name, int age) {
        return """
                {
                    "name": "%s",
                    "age": %d
                }""".formatted(name, age);
    }

    /**
     * Returns an HTML snippet built using a text block.
     *
     * @param title the page title
     * @param body  the body content
     * @return an HTML string
     */
    public static String htmlSnippet(String title, String body) {
        return """
                <html>
                    <head><title>%s</title></head>
                    <body>%s</body>
                </html>""".formatted(title, body);
    }

    /**
     * Demonstrates {@link String#format(String, Object...)} for classic formatting.
     *
     * @param item  the item name
     * @param price the item price
     * @return a formatted price string
     */
    public static String formatPrice(String item, double price) {
        return String.format("%-20s $%,.2f", item, price);
    }

    public static void main(String[] args) {
        System.out.println("Text block (JSON):");
        System.out.println(jsonTextBlock("Alice", 30));

        System.out.println("\nText block (HTML):");
        System.out.println(htmlSnippet("Demo", "Hello, text blocks!"));

        System.out.println("\nString.format:");
        System.out.println(formatPrice("Widget", 9.99));
        System.out.println(formatPrice("Gizmo Deluxe", 1234.5));
    }
}
