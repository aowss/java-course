package course.ch21.examples;

/**
 * A basic calculator for arithmetic operations.
 *
 * <p>This class is used as the production code for Exercise 1:
 * students write tests for this class.
 */
public class Calculator {

    /**
     * Adds two numbers.
     *
     * @param a the first operand
     * @param b the second operand
     * @return the sum
     */
    public int add(int a, int b) {
        return a + b;
    }

    /**
     * Subtracts the second number from the first.
     *
     * @param a the first operand
     * @param b the second operand
     * @return the difference
     */
    public int subtract(int a, int b) {
        return a - b;
    }

    /**
     * Multiplies two numbers.
     *
     * @param a the first operand
     * @param b the second operand
     * @return the product
     */
    public int multiply(int a, int b) {
        return a * b;
    }

    /**
     * Divides the first number by the second.
     *
     * @param a the dividend
     * @param b the divisor
     * @return the quotient
     * @throws ArithmeticException if the divisor is zero
     */
    public int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return a / b;
    }
}
