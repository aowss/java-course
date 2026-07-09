package course.ch07.exercises;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ColorTest {

    @Test
    void hexReturnsCode() {
        assertEquals("#FF0000", Color.RED.hex());
        assertEquals("#000000", Color.BLACK.hex());
    }

    @Test
    void fromHexFindsColor() {
        assertEquals(Color.RED, Color.fromHex("#FF0000"));
        assertEquals(Color.RED, Color.fromHex("ff0000"));
        assertEquals(Color.BLUE, Color.fromHex("#0000FF"));
    }

    @Test
    void fromHexThrowsForUnknown() {
        assertThrows(IllegalArgumentException.class, () -> Color.fromHex("#AABBCC"));
    }

    @Test
    void isDarkForBlack() {
        assertTrue(Color.BLACK.isDark());
    }

    @Test
    void isDarkFalseForWhite() {
        assertFalse(Color.WHITE.isDark());
    }

    @Test
    void complementInvertsComponents() {
        assertEquals("#00FFFF", Color.RED.complement());
        assertEquals("#FFFFFF", Color.BLACK.complement());
    }
}
