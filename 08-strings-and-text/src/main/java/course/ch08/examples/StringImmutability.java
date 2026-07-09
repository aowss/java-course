package course.ch08.examples;

/**
 * Demonstrates that Java strings are immutable objects.
 *
 * <p>Every method that appears to modify a string actually returns a new
 * {@link String} instance — the original is never changed. This demo also
 * shows how the JVM interns string literals and the difference between
 * {@code ==} and {@link String#equals(Object)}.
 */
public class StringImmutability {

    /**
     * Returns the original string and the result of {@link String#toUpperCase()}
     * to illustrate that the original is unmodified.
     *
     * @param text the input string
     * @return a two-element array: {@code [original, upperCase]}
     */
    public static String[] demonstrateImmutability(String text) {
        String upper = text.toUpperCase();
        return new String[]{text, upper};
    }

    /**
     * Checks whether two string references point to the same interned object.
     *
     * @param a the first string
     * @param b the second string
     * @return {@code true} if {@code a} and {@code b} are the same reference
     */
    public static boolean areSameReference(String a, String b) {
        return a == b;
    }

    /**
     * Returns the length of the given string.
     *
     * @param text the input string
     * @return the number of characters
     */
    public static int length(String text) {
        return text.length();
    }

    /**
     * Returns the character at the given index.
     *
     * @param text  the input string
     * @param index the zero-based index
     * @return the character at {@code index}
     */
    public static char charAt(String text, int index) {
        return text.charAt(index);
    }

    /**
     * Checks if the string contains the given sequence.
     *
     * @param text   the input string
     * @param target the sequence to search for
     * @return {@code true} if {@code text} contains {@code target}
     */
    public static boolean containsSubstring(String text, String target) {
        return text.contains(target);
    }

    public static void main(String[] args) {
        String original = "Hello";
        String[] result = demonstrateImmutability(original);
        System.out.println("Original:  " + result[0]);
        System.out.println("Uppercase: " + result[1]);
        System.out.println("Same object? " + (result[0] == result[1]));

        String a = "Java";
        String b = "Java";
        String c = new String("Java");
        System.out.println("\nInterning:");
        System.out.println("a == b (both literals): " + areSameReference(a, b));
        System.out.println("a == c (new String):    " + areSameReference(a, c));
        System.out.println("a.equals(c):            " + a.equals(c));

        System.out.println("\nCommon methods:");
        System.out.println("Length of 'Hello': " + length("Hello"));
        System.out.println("Char at index 1:   " + charAt("Hello", 1));
        System.out.println("Contains 'ell':    " + containsSubstring("Hello", "ell"));
    }
}
