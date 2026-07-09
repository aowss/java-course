package course.ch08.examples;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ThrowsClauseDemoTest {

    @Test
    void parsePositiveReturnsValue() throws ThrowsClauseDemo.ParseException {
        assertEquals(42, ThrowsClauseDemo.parsePositive("42"));
    }

    @Test
    void parsePositiveThrowsParseExceptionForInvalidText() {
        assertThrows(ThrowsClauseDemo.ParseException.class,
                () -> ThrowsClauseDemo.parsePositive("abc"));
    }

    @Test
    void parsePositiveThrowsIllegalArgumentExceptionForNonPositive() {
        var ex = assertThrows(IllegalArgumentException.class,
                () -> ThrowsClauseDemo.parsePositive("0"));
        assertTrue(ex.getMessage().contains("0"));
    }

    @Test
    void openResourceReturnsMessage() throws ThrowsClauseDemo.ResourceNotFoundException {
        assertEquals("Opened res://data", ThrowsClauseDemo.openResource("res://data"));
    }

    @Test
    void openResourceThrowsCheckedExceptionWhenMissing() {
        assertThrows(ThrowsClauseDemo.ResourceNotFoundException.class,
                () -> ThrowsClauseDemo.openResource("missing"));
    }

    @Test
    void openResourceThrowsUncheckedForBlankName() {
        assertThrows(IllegalArgumentException.class,
                () -> ThrowsClauseDemo.openResource("  "));
    }
}
