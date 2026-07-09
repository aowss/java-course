package course.ch06.exercises;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MeasurableTest {

    @Test
    void circleArea() {
        Measurable.Circle circle = new Measurable.Circle(3);
        assertEquals(Math.PI * 9, circle.area(), 0.001);
    }

    @Test
    void circlePerimeter() {
        Measurable.Circle circle = new Measurable.Circle(3);
        assertEquals(2 * Math.PI * 3, circle.perimeter(), 0.001);
    }

    @Test
    void rectangleArea() {
        Measurable.Rectangle rect = new Measurable.Rectangle(4, 5);
        assertEquals(20, rect.area(), 0.001);
    }

    @Test
    void rectanglePerimeter() {
        Measurable.Rectangle rect = new Measurable.Rectangle(4, 5);
        assertEquals(18, rect.perimeter(), 0.001);
    }

    @Test
    void totalAreaSumsAllShapes() {
        Measurable.MeasurableShape[] shapes = {
                new Measurable.Circle(2),
                new Measurable.Rectangle(3, 4)
        };
        double expected = Math.PI * 4 + 12;
        assertEquals(expected, Measurable.totalArea(shapes), 0.001);
    }

    @Test
    void circleRejectsNonPositiveRadius() {
        assertThrows(IllegalArgumentException.class, () -> new Measurable.Circle(0));
    }

    @Test
    void rectangleRejectsNonPositiveDimensions() {
        assertThrows(IllegalArgumentException.class, () -> new Measurable.Rectangle(-1, 5));
    }

    @Test
    void totalAreaReturnsZeroForNoShapes() {
        assertEquals(0, Measurable.totalArea());
    }
}
