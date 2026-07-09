package course.ch23.exercises;

/**
 * Exercise 2 (Practice): Serialize annotated fields to a simple string format.
 *
 * <p>Only fields marked with {@link course.ch23.annotations.Serializable} are
 * included. Output format: {@code name=value,name=value} with fields sorted
 * alphabetically by name.
 */
public class SimpleSerializer {

    /**
     * Serializes the annotated fields of the given object.
     *
     * @param target the object to serialize
     * @return a comma-separated {@code key=value} string
     * @throws IllegalArgumentException if {@code target} is {@code null}
     * @throws IllegalStateException if a field cannot be read
     */
    public static String serialize(Object target) {
        // TODO: implement using reflection and @Serializable
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static void main(String[] args) {
        var book = new Book("Effective Java", "Joshua Bloch", 416);
        System.out.println(serialize(book));
    }

    /**
     * Sample type used in serialization demos.
     */
    public static class Book {

        @course.ch23.annotations.Serializable
        private final String title;

        @course.ch23.annotations.Serializable
        private final String author;

        private final int pages;

        public Book(String title, String author, int pages) {
            this.title = title;
            this.author = author;
            this.pages = pages;
        }
    }
}
