package course.ch21.examples;

/**
 * Converts between Roman numeral strings and integer values.
 *
 * <p>This class is used as the production code for Exercise 2:
 * students write parameterized tests for this class.
 *
 * <p>Supports values from 1 to 3999.
 */
public class RomanNumeral {

    private static final int[] VALUES =    {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] SYMBOLS = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    /**
     * Converts an integer to a Roman numeral string.
     *
     * @param number the integer value (1–3999)
     * @return the Roman numeral representation
     * @throws IllegalArgumentException if the number is out of range
     */
    public static String toRoman(int number) {
        if (number < 1 || number > 3999) {
            throw new IllegalArgumentException("Number must be between 1 and 3999, got: " + number);
        }
        var sb = new StringBuilder();
        int remaining = number;
        for (int i = 0; i < VALUES.length; i++) {
            while (remaining >= VALUES[i]) {
                sb.append(SYMBOLS[i]);
                remaining -= VALUES[i];
            }
        }
        return sb.toString();
    }

    /**
     * Converts a Roman numeral string to an integer.
     *
     * @param roman the Roman numeral string
     * @return the integer value
     * @throws IllegalArgumentException if the string is null, empty, or contains invalid characters
     */
    public static int fromRoman(String roman) {
        if (roman == null || roman.isEmpty()) {
            throw new IllegalArgumentException("Roman numeral must not be null or empty");
        }
        int result = 0;
        int i = 0;
        String upper = roman.toUpperCase();
        while (i < upper.length()) {
            boolean matched = false;
            for (int j = 0; j < SYMBOLS.length; j++) {
                if (upper.startsWith(SYMBOLS[j], i)) {
                    result += VALUES[j];
                    i += SYMBOLS[j].length();
                    matched = true;
                    break;
                }
            }
            if (!matched) {
                throw new IllegalArgumentException("Invalid Roman numeral character at position " + i);
            }
        }
        return result;
    }
}
