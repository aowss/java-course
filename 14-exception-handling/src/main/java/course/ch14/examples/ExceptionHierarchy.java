package course.ch14.examples;

/**
 * Demonstrates the Java exception class hierarchy and how to catch different exception types.
 *
 * <p>The hierarchy is:
 * <pre>{@code
 * Throwable
 * ├── Error               (serious JVM problems — don't catch)
 * └── Exception           (recoverable conditions)
 *     ├── IOException     (checked)
 *     ├── SQLException    (checked)
 *     └── RuntimeException (unchecked)
 *         ├── NullPointerException
 *         ├── IllegalArgumentException
 *         └── ArithmeticException
 * }</pre>
 *
 * <p><strong>Key rule:</strong> catch the most specific exception first, then broaden.
 */
public class ExceptionHierarchy {

    /**
     * Divides two integers, demonstrating {@link ArithmeticException} on division by zero.
     *
     * @param a the dividend
     * @param b the divisor
     * @return the quotient
     * @throws ArithmeticException if {@code b} is zero
     */
    public static int divide(int a, int b) {
        return a / b;
    }

    /**
     * Accesses an array element, demonstrating {@link ArrayIndexOutOfBoundsException}.
     *
     * @param array the array to access
     * @param index the index to retrieve
     * @return the element at {@code index}
     * @throws ArrayIndexOutOfBoundsException if {@code index} is out of bounds
     * @throws NullPointerException           if {@code array} is {@code null}
     */
    public static int accessArray(int[] array, int index) {
        return array[index];
    }

    /**
     * Parses a string to an integer, demonstrating {@link NumberFormatException}.
     *
     * @param text the string to parse
     * @return the parsed integer
     * @throws NumberFormatException if {@code text} is not a valid integer
     */
    public static int parseNumber(String text) {
        return Integer.parseInt(text);
    }

    /**
     * Catches a specific exception and returns a description of what was caught.
     *
     * @param type the type of exception to provoke:
     *             {@code "arithmetic"}, {@code "null"}, {@code "format"}, or {@code "argument"}
     * @return a string describing the caught exception
     */
    public static String catchSpecific(String type) {
        try {
            switch (type) {
                case "arithmetic" -> divide(1, 0);
                case "null" -> accessArray(null, 0);
                case "format" -> parseNumber("not-a-number");
                case "argument" -> {
                    throw new IllegalArgumentException("bad argument");
                }
                default -> throw new IllegalStateException("unknown type: " + type);
            }
            return "no exception";
        } catch (ArithmeticException e) {
            return "ArithmeticException: " + e.getMessage();
        } catch (NullPointerException e) {
            return "NullPointerException";
        } catch (NumberFormatException e) {
            return "NumberFormatException: " + e.getMessage();
        } catch (IllegalArgumentException e) {
            return "IllegalArgumentException: " + e.getMessage();
        }
    }

    /**
     * Demonstrates multi-catch syntax: catching multiple exception types in one block.
     *
     * @param input the input to process
     * @return a description of the result or the caught exception
     */
    public static String multiCatch(String input) {
        try {
            int value = Integer.parseInt(input);
            return "parsed: " + divide(100, value);
        } catch (NumberFormatException | ArithmeticException e) {
            return "caught: " + e.getClass().getSimpleName();
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Catching specific exceptions ---");
        System.out.println(catchSpecific("arithmetic"));
        System.out.println(catchSpecific("null"));
        System.out.println(catchSpecific("format"));
        System.out.println(catchSpecific("argument"));

        System.out.println("\n--- Multi-catch ---");
        System.out.println(multiCatch("abc"));
        System.out.println(multiCatch("0"));
        System.out.println(multiCatch("5"));
    }
}
