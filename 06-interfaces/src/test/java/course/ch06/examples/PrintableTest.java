package course.ch06.examples;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PrintableTest {

    @Test
    void documentFormatsTitleAndBody() {
        Printable doc = new Printable.Document("Report", "All clear");
        assertEquals("Report: All clear", doc.format());
    }

    @Test
    void labelValueFormatsPair() {
        Printable field = new Printable.LabelValue("Status", "OK");
        assertEquals("Status = OK", field.format());
    }

    @Test
    void formatAllReturnsAllFormattedStrings() {
        Printable doc = new Printable.Document("A", "1");
        Printable field = new Printable.LabelValue("B", "2");
        List<String> formatted = Printable.formatAll(doc, field);
        assertEquals(List.of("A: 1", "B = 2"), formatted);
    }

    @Test
    void formatAllHandlesEmptyInput() {
        assertTrue(Printable.formatAll().isEmpty());
    }
}
