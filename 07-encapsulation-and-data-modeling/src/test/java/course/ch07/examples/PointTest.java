package course.ch07.examples;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PointTest {

    @Test
    void distanceFromOrigin() {
        Point p = new Point(3, 4);
        assertEquals(5.0, p.distanceFromOrigin(), 0.001);
    }

    @Test
    void distanceToAnotherPoint() {
        Point a = new Point(0, 0);
        Point b = new Point(3, 4);
        assertEquals(5.0, a.distanceTo(b), 0.001);
    }

    @Test
    void translateReturnsNewPoint() {
        Point p = new Point(1, 2);
        Point moved = p.translate(3, 4);
        assertEquals(new Point(4, 6), moved);
        assertEquals(new Point(1, 2), p);
    }

    @Test
    void midpoint() {
        Point mid = Point.midpoint(new Point(0, 0), new Point(4, 6));
        assertEquals(new Point(2, 3), mid);
    }

    @Test
    void recordEquality() {
        assertEquals(new Point(1, 2), new Point(1, 2));
    }
}
