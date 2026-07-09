package course.ch13.examples;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Demonstrates the four kinds of method references:
 * <ol>
 *   <li>Static — {@code ClassName::staticMethod}</li>
 *   <li>Bound instance — {@code instance::method}</li>
 *   <li>Unbound instance — {@code ClassName::instanceMethod}</li>
 *   <li>Constructor — {@code ClassName::new}</li>
 * </ol>
 */
public class MethodReferenceDemo {

    /**
     * Static method reference: {@code Integer::parseInt}.
     *
     * @param s the string to parse
     * @return the parsed integer
     */
    public static int parseWithReference(String s) {
        Function<String, Integer> parser = Integer::parseInt;
        return parser.apply(s);
    }

    /**
     * Bound instance method reference: {@code text::startsWith}.
     * The receiver ({@code text}) is captured at reference-creation time.
     *
     * @param text   the string to test
     * @param prefix the prefix to look for
     * @return {@code true} if {@code text} starts with {@code prefix}
     */
    public static boolean startsWithPrefix(String text, String prefix) {
        Predicate<String> checker = text::startsWith;
        return checker.test(prefix);
    }

    /**
     * Unbound instance method reference: {@code String::toUpperCase}.
     * The receiver is supplied by the stream at call time.
     *
     * @param words the list to transform
     * @return a new list with each word in upper case
     */
    public static List<String> toUpperAll(List<String> words) {
        return words.stream().map(String::toUpperCase).toList();
    }

    /**
     * Constructor reference: {@code StringBuilder::new}.
     *
     * @param initial the initial content
     * @return a new {@link StringBuilder} initialized with {@code initial}
     */
    public static StringBuilder createBuilder(String initial) {
        Function<String, StringBuilder> factory = StringBuilder::new;
        return factory.apply(initial);
    }

    public static void main(String[] args) {
        System.out.println("parseInt via reference: " + parseWithReference("42"));
        System.out.println("Starts with 'He': " + startsWithPrefix("Hello", "He"));
        System.out.println("Upper all: " + toUpperAll(List.of("hello", "world")));
        System.out.println("Builder: " + createBuilder("initial"));
    }
}
