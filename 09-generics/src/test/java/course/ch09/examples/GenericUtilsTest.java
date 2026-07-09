package course.ch09.examples;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GenericUtilsTest {

    @Test
    void maxReturnsGreaterInteger() {
        assertEquals(7, GenericUtils.max(3, 7));
    }

    @Test
    void maxReturnsGreaterString() {
        assertEquals("banana", GenericUtils.max("apple", "banana"));
    }

    @Test
    void maxReturnsFirstWhenEqual() {
        assertEquals(5, GenericUtils.max(5, 5));
    }

    @Test
    void swapExchangesElements() {
        String[] array = {"a", "b", "c"};
        GenericUtils.swap(array, 0, 2);
        assertArrayEquals(new String[]{"c", "b", "a"}, array);
    }

    @Test
    void sumOfNumbersSumsCorrectly() {
        assertEquals(6.5, GenericUtils.sumOfNumbers(List.of(1, 2.5, 3)));
    }

    @Test
    void sumOfNumbersHandlesEmptyList() {
        assertEquals(0.0, GenericUtils.sumOfNumbers(List.of()));
    }

    @Test
    void addIntegersAddsRange() {
        List<Number> list = new ArrayList<>();
        GenericUtils.addIntegers(list, 1, 4);
        assertEquals(List.of(1, 2, 3), list);
    }
}
