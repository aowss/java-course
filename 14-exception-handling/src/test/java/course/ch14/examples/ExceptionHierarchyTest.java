package course.ch14.examples;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExceptionHierarchyTest {

    // --- divide ---

    @Test
    void divideNormal() {
        assertEquals(5, ExceptionHierarchy.divide(10, 2));
    }

    @Test
    void divideByZeroThrows() {
        assertThrows(ArithmeticException.class, () -> ExceptionHierarchy.divide(1, 0));
    }

    // --- accessArray ---

    @Test
    void accessArrayValid() {
        assertEquals(42, ExceptionHierarchy.accessArray(new int[]{42, 7}, 0));
    }

    @Test
    void accessArrayOutOfBounds() {
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> ExceptionHierarchy.accessArray(new int[]{1}, 5));
    }

    @Test
    void accessArrayNullThrows() {
        assertThrows(NullPointerException.class,
                () -> ExceptionHierarchy.accessArray(null, 0));
    }

    // --- parseNumber ---

    @Test
    void parseNumberValid() {
        assertEquals(123, ExceptionHierarchy.parseNumber("123"));
    }

    @Test
    void parseNumberInvalidThrows() {
        assertThrows(NumberFormatException.class,
                () -> ExceptionHierarchy.parseNumber("abc"));
    }

    // --- catchSpecific ---

    @Test
    void catchSpecificArithmetic() {
        String result = ExceptionHierarchy.catchSpecific("arithmetic");
        assertTrue(result.startsWith("ArithmeticException"));
    }

    @Test
    void catchSpecificNull() {
        assertEquals("NullPointerException", ExceptionHierarchy.catchSpecific("null"));
    }

    @Test
    void catchSpecificFormat() {
        String result = ExceptionHierarchy.catchSpecific("format");
        assertTrue(result.startsWith("NumberFormatException"));
    }

    @Test
    void catchSpecificArgument() {
        String result = ExceptionHierarchy.catchSpecific("argument");
        assertTrue(result.startsWith("IllegalArgumentException"));
    }

    // --- multiCatch ---

    @Test
    void multiCatchFormatException() {
        assertEquals("caught: NumberFormatException", ExceptionHierarchy.multiCatch("abc"));
    }

    @Test
    void multiCatchArithmeticException() {
        assertEquals("caught: ArithmeticException", ExceptionHierarchy.multiCatch("0"));
    }

    @Test
    void multiCatchSuccess() {
        assertEquals("parsed: 20", ExceptionHierarchy.multiCatch("5"));
    }
}
