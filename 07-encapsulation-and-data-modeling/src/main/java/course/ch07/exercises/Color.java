package course.ch07.exercises;

/**
 * Exercise 1 (Guided): Color enum with hex values.
 *
 * <p>Implement a {@link Color} enum with RGB hex codes and utility methods.
 */
public enum Color {

    RED("#FF0000"),
    GREEN("#00FF00"),
    BLUE("#0000FF"),
    WHITE("#FFFFFF"),
    BLACK("#000000");

    private final String hex;

    Color(String hex) {
        this.hex = hex;
    }

    /**
     * @return the hex color code (e.g. {@code "#FF0000"})
     */
    public String hex() {
        return hex;
    }

    /**
     * Parses a hex string into a {@link Color} constant.
     *
     * @param hex the hex code (case-insensitive, with or without {@code #})
     * @return the matching color
     * @throws IllegalArgumentException if no color matches
     */
    public static Color fromHex(String hex) {
        // TODO: implement — normalize hex (uppercase, ensure #) and find match
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns whether this color is a "dark" color (any RGB component below 128).
     *
     * @return {@code true} if the color is dark
     */
    public boolean isDark() {
        // TODO: implement — parse hex to RGB and check if all components < 128
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns the complementary color by inverting each RGB component.
     *
     * @return a new hex string for the complementary color
     */
    public String complement() {
        // TODO: implement — invert each byte: 255 - component
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static void main(String[] args) {
        System.out.println(Color.RED.hex());
        System.out.println("RED is dark? " + Color.RED.isDark());
        System.out.println("RED complement: " + Color.RED.complement());
    }
}
