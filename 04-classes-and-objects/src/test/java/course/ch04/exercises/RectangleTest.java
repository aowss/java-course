package course.ch04.exercises;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RectangleTest {

    @Test
    void areaComputation() {
        var rect = new Rectangle(5.0, 3.0);
        assertEquals(15.0, rect.area(), 0.001);
    }

    @Test
    void perimeterComputation() {
        var rect = new Rectangle(5.0, 3.0);
        assertEquals(16.0, rect.perimeter(), 0.001);
    }

    @Test
    void squareAreaAndPerimeter() {
        var square = new Rectangle(4.0, 4.0);
        assertEquals(16.0, square.area(), 0.001);
        assertEquals(16.0, square.perimeter(), 0.001);
    }

    @Test
    void toStringFormat() {
        var rect = new Rectangle(5.0, 3.0);
        assertEquals("Rectangle{width=5.0, height=3.0}", rect.toString());
    }

    @Test
    void rejectsNonPositiveWidth() {
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(0, 3.0));
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(-1, 3.0));
    }

    @Test
    void rejectsNonPositiveHeight() {
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(5.0, 0));
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(5.0, -1));
    }
}
