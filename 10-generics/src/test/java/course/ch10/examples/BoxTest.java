package course.ch10.examples;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BoxTest {

    @Test
    void emptyBoxIsEmpty() {
        var box = new Box<String>();
        assertTrue(box.isEmpty());
        assertNull(box.getValue());
    }

    @Test
    void boxWithValueIsNotEmpty() {
        var box = new Box<>("Hello");
        assertFalse(box.isEmpty());
        assertEquals("Hello", box.getValue());
    }

    @Test
    void setValueUpdatesBox() {
        var box = new Box<Integer>();
        box.setValue(42);
        assertEquals(42, box.getValue());
        assertFalse(box.isEmpty());
    }

    @Test
    void toStringShowsValue() {
        var box = new Box<>("test");
        assertEquals("Box[test]", box.toString());
    }

    @Test
    void toStringShowsNullForEmptyBox() {
        var box = new Box<>();
        assertEquals("Box[null]", box.toString());
    }
}
