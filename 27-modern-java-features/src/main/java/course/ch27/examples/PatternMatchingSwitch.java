package course.ch27.examples;

/**
 * Demonstrates pattern matching in {@code switch} expressions and statements.
 *
 * <p>Pattern matching lets a {@code case} label test both the type and shape of a value,
 * binding variables for use in the case body. Combined with {@code when} guards, switches
 * can express complex dispatch logic without chains of {@code instanceof} checks.
 *
 * <pre>{@code
 * String result = PatternMatchingSwitch.classify(42);
 * // "positive integer: 42"
 * }</pre>
 */
public final class PatternMatchingSwitch {

    private PatternMatchingSwitch() {
    }

    /**
     * Classifies an arbitrary object using pattern matching switch.
     *
     * @param obj the value to classify (may be {@code null})
     * @return a human-readable classification string
     */
    public static String classify(Object obj) {
        return switch (obj) {
            case null -> "null";
            case Integer i when i < 0 -> "negative integer: " + i;
            case Integer i -> "positive integer: " + i;
            case String s when s.isBlank() -> "blank string";
            case String s -> "string: " + s;
            case int[] array -> "int array of length " + array.length;
            default -> "unknown: " + obj.getClass().getSimpleName();
        };
    }

    /**
     * Returns the length of a {@link CharSequence} using a type pattern.
     *
     * @param value a character sequence or {@code null}
     * @return the length, or {@code -1} if {@code null}
     */
    public static int sequenceLength(CharSequence value) {
        return switch (value) {
            case null -> -1;
            case String s -> s.length();
            case StringBuilder sb -> sb.length();
            default -> value.length();
        };
    }

    public static void main(String[] args) {
        System.out.println(classify(null));
        System.out.println(classify(-5));
        System.out.println(classify(42));
        System.out.println(classify("   "));
        System.out.println(classify("hello"));
        System.out.println(classify(new int[]{1, 2, 3}));
        System.out.println(classify(3.14));
        System.out.println("sequenceLength(\"abc\") = " + sequenceLength("abc"));
    }
}
