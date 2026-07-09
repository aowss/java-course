package course.ch27.exercises;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShapeFormatterTest {

    @Test
    void formatCircle() {
        assertEquals("circle 3.0", ShapeFormatter.format(new ShapeFormatter.Circle(3)));
    }

    @Test
    void formatInvalidCircle() {
        assertEquals("invalid circle", ShapeFormatter.format(new ShapeFormatter.Circle(-1)));
    }

    @Test
    void formatSquare() {
        assertEquals("square 4.0", ShapeFormatter.format(new ShapeFormatter.Rectangle(4, 4)));
    }

    @Test
    void formatRectangle() {
        assertEquals("rectangle 3.0x5.0", ShapeFormatter.format(new ShapeFormatter.Rectangle(3, 5)));
    }

    @Test
    void formatInvalidRectangle() {
        assertEquals("invalid rectangle", ShapeFormatter.format(new ShapeFormatter.Rectangle(0, 5)));
    }

    @Test
    void formatTriangle() {
        assertEquals("triangle 6.0x4.0", ShapeFormatter.format(new ShapeFormatter.Triangle(6, 4)));
    }

    @Test
    void formatInvalidTriangle() {
        assertEquals("invalid triangle", ShapeFormatter.format(new ShapeFormatter.Triangle(0, 4)));
    }
}
