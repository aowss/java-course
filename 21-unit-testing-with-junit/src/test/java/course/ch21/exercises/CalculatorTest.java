package course.ch21.exercises;

import course.ch21.examples.Calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Exercise 1 (Guided): Write tests for the {@link Calculator} class.
 *
 * <p>Test the following:
 * <ul>
 *   <li>{@code add} — verify correct sum for positive, negative, and zero values</li>
 *   <li>{@code subtract} — verify correct difference</li>
 *   <li>{@code multiply} — verify correct product, including multiplication by zero</li>
 *   <li>{@code divide} — verify correct quotient for valid inputs</li>
 *   <li>{@code divide} — verify that division by zero throws {@link ArithmeticException}</li>
 * </ul>
 */
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
        // TODO: verify that calculator.add(2, 3) returns 5
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Test
    @DisplayName("add: positive and negative number")
    void addPositiveAndNegative() {
        // TODO: verify that calculator.add(5, -3) returns 2
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Test
    @DisplayName("subtract: basic subtraction")
    void subtractNumbers() {
        // TODO: verify that calculator.subtract(10, 4) returns 6
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Test
    @DisplayName("multiply: two numbers")
    void multiplyNumbers() {
        // TODO: verify that calculator.multiply(3, 7) returns 21
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Test
    @DisplayName("multiply: by zero returns zero")
    void multiplyByZero() {
        // TODO: verify that calculator.multiply(5, 0) returns 0
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Test
    @DisplayName("divide: even division")
    void divideEvenly() {
        // TODO: verify that calculator.divide(10, 2) returns 5
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Test
    @DisplayName("divide: by zero throws ArithmeticException")
    void divideByZeroThrows() {
        // TODO: use assertThrows to verify ArithmeticException
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
