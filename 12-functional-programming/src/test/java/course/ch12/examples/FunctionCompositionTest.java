package course.ch12.examples;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FunctionCompositionTest {

    @Test
    void composeWithAndThen() {
        Function<String, String> trim = String::trim;
        Function<String, String> toUpper = String::toUpperCase;
        var composed = FunctionComposition.composeWithAndThen(trim, toUpper);
        assertEquals("HELLO", composed.apply("  hello  "));
    }

    @Test
    void composeWithCompose() {
        Function<String, String> toUpper = String::toUpperCase;
        Function<String, String> trim = String::trim;
        var composed = FunctionComposition.composeWithCompose(toUpper, trim);
        assertEquals("HELLO", composed.apply("  hello  "));
    }

    @Test
    void bothPredicates() {
        Predicate<Integer> isPositive = n -> n > 0;
        Predicate<Integer> isEven = n -> n % 2 == 0;
        var positiveAndEven = FunctionComposition.both(isPositive, isEven);
        assertTrue(positiveAndEven.test(4));
        assertFalse(positiveAndEven.test(3));
        assertFalse(positiveAndEven.test(-2));
    }

    @Test
    void eitherPredicate() {
        Predicate<Integer> isNegative = n -> n < 0;
        Predicate<Integer> isEven = n -> n % 2 == 0;
        var negativeOrEven = FunctionComposition.either(isNegative, isEven);
        assertTrue(negativeOrEven.test(-3));
        assertTrue(negativeOrEven.test(4));
        assertFalse(negativeOrEven.test(3));
    }

    @Test
    void notPredicate() {
        Predicate<String> isEmpty = String::isEmpty;
        var notEmpty = FunctionComposition.not(isEmpty);
        assertTrue(notEmpty.test("hello"));
        assertFalse(notEmpty.test(""));
    }

    @Test
    void filterWithPredicate() {
        var result = FunctionComposition.filter(
                List.of(1, 2, 3, 4, 5, 6),
                n -> n % 2 == 0);
        assertEquals(List.of(2, 4, 6), result);
    }

    @Test
    void chainOperators() {
        String result = FunctionComposition.chain("  hello world  ",
                String::trim,
                String::toUpperCase);
        assertEquals("HELLO WORLD", result);
    }
}
