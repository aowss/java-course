package course.ch05.examples;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShapeTest {

    @Test
    void circleArea() {
        var c = new Circle(5.0);
        assertEquals(Math.PI * 25, c.area(), 0.001);
    }

    @Test
    void circlePerimeter() {
        var c = new Circle(5.0);
        assertEquals(2 * Math.PI * 5, c.perimeter(), 0.001);
    }

    @Test
    void rectangleArea() {
        var r = new Rectangle(4.0, 6.0);
        assertEquals(24.0, r.area(), 0.001);
    }

    @Test
    void rectanglePerimeter() {
        var r = new Rectangle(4.0, 6.0);
        assertEquals(20.0, r.perimeter(), 0.001);
    }

    @Test
    void polymorphicAreaCall() {
        Shape shape = new Circle(3.0);
        assertEquals(Math.PI * 9, shape.area(), 0.001);
    }
}
