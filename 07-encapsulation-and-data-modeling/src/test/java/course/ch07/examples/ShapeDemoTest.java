package course.ch07.examples;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShapeDemoTest {

    @Test
    void circleArea() {
        ShapeDemo.Circle circle = new ShapeDemo.Circle(3);
        assertEquals(Math.PI * 9, circle.area(), 0.001);
    }

    @Test
    void rectangleArea() {
        ShapeDemo.Rectangle rect = new ShapeDemo.Rectangle(4, 5);
        assertEquals(20, rect.area(), 0.001);
    }

    @Test
    void triangleArea() {
        ShapeDemo.Triangle tri = new ShapeDemo.Triangle(6, 4);
        assertEquals(12, tri.area(), 0.001);
    }

    @Test
    void describeUsesPatternMatching() {
        assertTrue(ShapeDemo.describe(new ShapeDemo.Circle(5)).contains("radius 5"));
        assertEquals("Rectangle 4×3", ShapeDemo.describe(new ShapeDemo.Rectangle(4, 3)));
    }

    @Test
    void areaOfWithInstanceof() {
        assertEquals(Math.PI * 4, ShapeDemo.areaOf(new ShapeDemo.Circle(2)), 0.001);
        assertEquals(-1, ShapeDemo.areaOf("not a shape"));
    }

    @Test
    void totalAreaSumsAll() {
        ShapeDemo.Shape[] shapes = {
                new ShapeDemo.Circle(2),
                new ShapeDemo.Rectangle(3, 4)
        };
        double expected = Math.PI * 4 + 12;
        assertEquals(expected, ShapeDemo.totalArea(shapes), 0.001);
    }
}
