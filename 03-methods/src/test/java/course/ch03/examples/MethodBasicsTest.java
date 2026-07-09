package course.ch03.examples;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MethodBasicsTest {

    @Test
    void circleArea() {
        assertEquals(Math.PI * 25, MethodBasics.circleArea(5), 0.001);
    }

    @Test
    void circleAreaZeroRadius() {
        assertEquals(0.0, MethodBasics.circleArea(0), 0.001);
    }

    @Test
    void isEvenTrue() {
        assertTrue(MethodBasics.isEven(42));
        assertTrue(MethodBasics.isEven(0));
    }

    @Test
    void isEvenFalse() {
        assertFalse(MethodBasics.isEven(17));
        assertFalse(MethodBasics.isEven(-3));
    }
}
