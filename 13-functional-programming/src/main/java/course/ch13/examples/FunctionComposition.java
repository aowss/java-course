package course.ch13.examples;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * Demonstrates composing and chaining functions, predicates, and consumers.
 */
public class FunctionComposition {

    /**
     * Composes two functions using {@code andThen}: applies {@code f} first, then {@code g}.
     *
     * @return the composed function {@code g(f(x))}
     */
    public static <T, R, V> Function<T, V> composeWithAndThen(Function<T, R> f, Function<R, V> g) {
        return f.andThen(g);
    }

    /**
     * Composes two functions using {@code compose}: applies {@code g} first, then {@code f}.
     *
     * @return the composed function {@code f(g(x))}
     */
    public static <T, R, V> Function<T, V> composeWithCompose(Function<R, V> f, Function<T, R> g) {
        return f.compose(g);
    }

    /**
     * Combines two predicates with logical AND.
     */
    public static <T> Predicate<T> both(Predicate<T> a, Predicate<T> b) {
        return a.and(b);
    }

    /**
     * Combines two predicates with logical OR.
     */
    public static <T> Predicate<T> either(Predicate<T> a, Predicate<T> b) {
        return a.or(b);
    }

    /**
     * Negates a predicate.
     */
    public static <T> Predicate<T> not(Predicate<T> p) {
        return p.negate();
    }

    /**
     * Filters a list, keeping only elements that match the predicate.
     */
    public static <T> List<T> filter(List<T> items, Predicate<T> predicate) {
        return items.stream().filter(predicate).toList();
    }

    /**
     * Transforms a value through a chain of unary operators, applied left to right.
     */
    @SafeVarargs
    public static <T> T chain(T value, UnaryOperator<T>... steps) {
        T result = value;
        for (UnaryOperator<T> step : steps) {
            result = step.apply(result);
        }
        return result;
    }

    public static void main(String[] args) {
        Function<String, String> trim = String::trim;
        Function<String, String> toUpper = String::toUpperCase;
        Function<String, String> trimThenUpper = composeWithAndThen(trim, toUpper);
        System.out.println(trimThenUpper.apply("  hello  "));

        Predicate<Integer> isPositive = n -> n > 0;
        Predicate<Integer> isEven = n -> n % 2 == 0;
        var positiveEvens = filter(List.of(-3, -2, -1, 0, 1, 2, 3, 4), both(isPositive, isEven));
        System.out.println("Positive evens: " + positiveEvens);

        String result = chain("  hello world  ",
                String::trim,
                String::toUpperCase,
                s -> s.replace(" ", "_"));
        System.out.println("Chained: " + result);
    }
}
