package course.ch11.examples;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

/**
 * Demonstrates lambda expression syntax, Comparator lambdas, Runnable lambdas,
 * and variable capture.
 */
public class LambdaBasics {

    /**
     * Sorts strings by length using a lambda expression.
     *
     * @param words the list to sort
     * @return a new list sorted by string length (ascending)
     */
    public static List<String> sortByLength(List<String> words) {
        var sorted = new ArrayList<>(words);
        sorted.sort((a, b) -> Integer.compare(a.length(), b.length()));
        return sorted;
    }

    /**
     * Sorts strings by length first, then alphabetically for ties.
     *
     * @param words the list to sort
     * @return a new list sorted by length, then alphabetically
     */
    public static List<String> sortByLengthThenAlpha(List<String> words) {
        var sorted = new ArrayList<>(words);
        Comparator<String> byLength = (a, b) -> Integer.compare(a.length(), b.length());
        sorted.sort(byLength.thenComparing(Comparator.naturalOrder()));
        return sorted;
    }

    /**
     * Demonstrates variable capture — the lambda captures {@code prefix}
     * from the enclosing scope.
     *
     * @param prefix the greeting prefix
     * @param name   the name to greet
     * @return the formatted greeting
     */
    public static String greetWithPrefix(String prefix, String name) {
        Function<String, String> greeter = n -> prefix + " " + n + "!";
        return greeter.apply(name);
    }

    public static void main(String[] args) {
        var words = List.of("banana", "apple", "cherry", "date", "fig");
        System.out.println("Original:             " + words);
        System.out.println("By length:            " + sortByLength(words));
        System.out.println("By length then alpha: " + sortByLengthThenAlpha(words));

        Runnable greeting = () -> System.out.println("Hello from a lambda!");
        greeting.run();

        System.out.println(greetWithPrefix("Hi", "Alice"));
    }
}
