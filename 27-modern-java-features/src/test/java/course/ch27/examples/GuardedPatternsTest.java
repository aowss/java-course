package course.ch27.examples;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GuardedPatternsTest {

    @Test
    void describeCircle() {
        assertEquals("circle radius=3.0", GuardedPatterns.describe(new GuardedPatterns.Circle(3)));
    }

    @Test
    void describeInvalidCircle() {
        assertEquals("invalid circle", GuardedPatterns.describe(new GuardedPatterns.Circle(0)));
    }

    @Test
    void describeSquare() {
        assertEquals("square side=4.0", GuardedPatterns.describe(new GuardedPatterns.Rectangle(4, 4)));
    }

    @Test
    void describeRectangle() {
        assertEquals("rectangle 3.0x5.0", GuardedPatterns.describe(new GuardedPatterns.Rectangle(3, 5)));
    }

    @Test
    void describeTriangle() {
        assertEquals("triangle base=6.0 height=4.0",
                GuardedPatterns.describe(new GuardedPatterns.Triangle(6, 4)));
    }

    @Test
    void areaCircle() {
        assertEquals(Math.PI * 9, GuardedPatterns.area(new GuardedPatterns.Circle(3)), 0.001);
    }

    @Test
    void areaRectangle() {
        assertEquals(12, GuardedPatterns.area(new GuardedPatterns.Rectangle(3, 4)));
    }

    @Test
    void areaTriangle() {
        assertEquals(12, GuardedPatterns.area(new GuardedPatterns.Triangle(6, 4)));
    }
}
