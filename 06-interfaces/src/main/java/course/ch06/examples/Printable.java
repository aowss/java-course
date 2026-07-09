package course.ch06.examples;

/**
 * Demonstrates a basic interface with abstract, default, and static methods.
 *
 * <p>Classes that implement {@link Printable} must provide {@link #format()},
 * but inherit the default {@link #print()} behavior and can use the static
 * {@link #printAll(Printable...)} helper.
 */
public interface Printable {

    /**
     * Returns a formatted string representation of this object.
     *
     * @return the formatted text
     */
    String format();

    /**
     * Prints the formatted representation to standard output.
     */
    default void print() {
        System.out.println(format());
    }

    /**
     * Prints each printable item on its own line.
     *
     * @param items the items to print
     */
    static void printAll(Printable... items) {
        for (Printable item : items) {
            item.print();
        }
    }

    /**
     * A simple document that wraps a title and body.
     *
     * @param title the document title
     * @param body  the document body
     */
    record Document(String title, String body) implements Printable {

        @Override
        public String format() {
            return title + ": " + body;
        }
    }

    /**
     * Formats a label and value pair for display.
     *
     * @param label the field label
     * @param value the field value
     */
    record LabelValue(String label, String value) implements Printable {

        @Override
        public String format() {
            return label + " = " + value;
        }
    }

    /**
     * Returns formatted strings for the given printable items.
     *
     * @param items the items to format
     * @return a list of formatted strings
     */
    static java.util.List<String> formatAll(Printable... items) {
        java.util.List<String> results = new java.util.ArrayList<>();
        for (Printable item : items) {
            results.add(item.format());
        }
        return results;
    }

    public static void main(String[] args) {
        Printable doc = new Document("Report", "All systems nominal");
        Printable field = new LabelValue("Status", "OK");

        doc.print();
        field.print();

        System.out.println("\nFormatted list:");
        formatAll(doc, field).forEach(System.out::println);
    }
}
