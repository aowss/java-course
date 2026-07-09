package course.ch21.exercises;

import course.ch21.examples.Calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Calculator Tests")
class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    @DisplayName("add: two positive numbers")
    void addPositiveNumbers() {
        assertEquals(5, calculator.add(2, 3));
    }

    @Test
    @DisplayName("add: positive and negative number")
    void addPositiveAndNegative() {
        assertEquals(2, calculator.add(5, -3));
    }

    @Test
    @DisplayName("subtract: basic subtraction")
    void subtractNumbers() {
        assertEquals(6, calculator.subtract(10, 4));
    }

    @Test
    @DisplayName("multiply: two numbers")
    void multiplyNumbers() {
        assertEquals(21, calculator.multiply(3, 7));
    }

    @Test
    @DisplayName("multiply: by zero returns zero")
    void multiplyByZero() {
        assertEquals(0, calculator.multiply(5, 0));
    }

    @Test
    @DisplayName("divide: even division")
    void divideEvenly() {
        assertEquals(5, calculator.divide(10, 2));
    }

    @Test
    @DisplayName("divide: by zero throws ArithmeticException")
    void divideByZeroThrows() {
        assertThrows(ArithmeticException.class, () -> calculator.divide(10, 0));
    }
}
