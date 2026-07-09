package course.ch21.examples;

import java.util.List;

/**
 * A simple utility class used to demonstrate various JUnit assertion types.
 *
 * <p>See the corresponding test class for assertion examples:
 * {@code AssertionShowcaseTest}.
 */
public class AssertionShowcase {

    /**
     * Returns the absolute value of the given number.
     *
     * @param n the number
     * @return the absolute value
     */
    public static int absoluteValue(int n) {
        return Math.abs(n);
    }

    /**
     * Checks if a string is a palindrome (case-insensitive).
     *
     * @param s the string to check
     * @return {@code true} if the string is a palindrome
     */
    public static boolean isPalindrome(String s) {
        if (s == null) {
            return false;
        }
        String cleaned = s.toLowerCase().replaceAll("\\s+", "");
        return cleaned.contentEquals(new StringBuilder(cleaned).reverse());
    }

    /**
     * Divides two integers.
     *
     * @param a the dividend
     * @param b the divisor
     * @return the result of integer division
     * @throws ArithmeticException if b is zero
     */
    public static int divide(int a, int b) {
        return a / b;
    }

    /**
     * Filters a list to return only positive numbers.
     *
     * @param numbers the input list
     * @return a new list containing only positive numbers
     */
    public static List<Integer> filterPositive(List<Integer> numbers) {
        return numbers.stream().filter(n -> n > 0).toList();
    }
}
