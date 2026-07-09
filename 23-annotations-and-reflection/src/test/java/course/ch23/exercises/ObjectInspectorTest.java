package course.ch23.exercises;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ObjectInspectorTest {

    static class Widget {
        private String name;
        private int quantity;

        public String label() {
            return name;
        }
    }

    @Test
    void inspectFormatsSummary() {
        String summary = ObjectInspector.inspect(new Widget());
        assertTrue(summary.contains("Class: Widget"));
        assertTrue(summary.contains("Fields:"));
        assertTrue(summary.contains("Methods:"));
    }

    @Test
    void getFieldNamesReturnsSortedFields() {
        List<String> fields = ObjectInspector.getFieldNames(new Widget());
        assertEquals(List.of("name", "quantity"), fields);
    }

    @Test
    void getMethodNamesIncludesDeclaredMethods() {
        List<String> methods = ObjectInspector.getMethodNames(new Widget());
        assertTrue(methods.contains("label"));
    }

    @Test
    void inspectRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> ObjectInspector.inspect(null));
    }

    @Test
    void getFieldNamesRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> ObjectInspector.getFieldNames(null));
    }
}
