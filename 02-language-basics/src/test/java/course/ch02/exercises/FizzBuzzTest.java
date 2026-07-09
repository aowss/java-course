package course.ch02.exercises;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FizzBuzzTest {

    @ParameterizedTest
    @CsvSource({
            "1,  1",
            "2,  2",
            "4,  4",
            "7,  7",
            "11, 11"
    })
    void plainNumbers(int input, String expected) {
        assertEquals(expected, FizzBuzz.fizzBuzz(input));
    }

    @ParameterizedTest
    @CsvSource({
            "3,  Fizz",
            "6,  Fizz",
            "9,  Fizz",
            "12, Fizz"
    })
    void fizz(int input, String expected) {
        assertEquals(expected, FizzBuzz.fizzBuzz(input));
    }

    @ParameterizedTest
    @CsvSource({
            "5,  Buzz",
            "10, Buzz",
            "20, Buzz"
    })
    void buzz(int input, String expected) {
        assertEquals(expected, FizzBuzz.fizzBuzz(input));
    }

    @ParameterizedTest
    @CsvSource({
            "15, FizzBuzz",
            "30, FizzBuzz",
            "45, FizzBuzz"
    })
    void fizzBuzz(int input, String expected) {
        assertEquals(expected, FizzBuzz.fizzBuzz(input));
    }

    @Test
    void zero() {
        assertEquals("FizzBuzz", FizzBuzz.fizzBuzz(0));
    }
}
