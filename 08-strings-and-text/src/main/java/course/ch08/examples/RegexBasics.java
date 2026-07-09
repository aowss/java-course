package course.ch08.examples;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Demonstrates Java regular expression basics using {@link Pattern} and {@link Matcher}.
 *
 * <p>Covers pattern compilation, matching, finding, and group extraction.
 */
public class RegexBasics {

    /**
     * Checks if the entire string matches the given regex pattern.
     *
     * @param text    the string to test
     * @param pattern the regular expression
     * @return {@code true} if the full string matches
     */
    public static boolean fullMatch(String text, String pattern) {
        return Pattern.matches(pattern, text);
    }

    /**
     * Finds all substrings matching the given pattern.
     *
     * @param text    the string to search
     * @param pattern the regular expression
     * @return a list of all matching substrings
     */
    public static List<String> findAll(String text, String pattern) {
        List<String> matches = new ArrayList<>();
        Matcher matcher = Pattern.compile(pattern).matcher(text);
        while (matcher.find()) {
            matches.add(matcher.group());
        }
        return matches;
    }

    /**
     * Replaces all occurrences of the pattern with the replacement string.
     *
     * @param text        the input string
     * @param pattern     the regular expression to match
     * @param replacement the replacement string
     * @return the string with all matches replaced
     */
    public static String replaceAll(String text, String pattern, String replacement) {
        return Pattern.compile(pattern).matcher(text).replaceAll(replacement);
    }

    /**
     * Extracts the first capture group from the first match of the pattern.
     *
     * @param text    the input string
     * @param pattern a regular expression with at least one capture group
     * @return the content of group 1, or {@code null} if no match
     */
    public static String extractFirstGroup(String text, String pattern) {
        Matcher matcher = Pattern.compile(pattern).matcher(text);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println("Full match (email pattern):");
        System.out.println("  test@example.com → " +
                fullMatch("test@example.com", "[\\w.]+@[\\w.]+\\.[a-z]{2,}"));
        System.out.println("  not-an-email     → " +
                fullMatch("not-an-email", "[\\w.]+@[\\w.]+\\.[a-z]{2,}"));

        System.out.println("\nFind all words:");
        System.out.println("  " + findAll("Hello, World! Java 25 is here.", "\\w+"));

        System.out.println("\nReplace digits with #:");
        System.out.println("  " + replaceAll("Call 555-1234 or 555-5678", "\\d", "#"));

        System.out.println("\nExtract area code from (555) 123-4567:");
        System.out.println("  " + extractFirstGroup("(555) 123-4567", "\\((\\d{3})\\)"));
    }
}
