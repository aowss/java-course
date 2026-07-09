package course.ch28.exercises;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClassInspectorTest {

    @Test
    void describeString() {
        String desc = ClassInspector.describe(String.class);
        assertTrue(desc.startsWith("class java.lang.String"));
        assertTrue(desc.contains("extends java.lang.Object"));
        assertTrue(desc.contains("final"));
    }

    @Test
    void describeInterface() {
        String desc = ClassInspector.describe(Runnable.class);
        assertTrue(desc.startsWith("interface java.lang.Runnable"));
    }

    @Test
    void describeRecord() {
        record Point(int x, int y) { }
        String desc = ClassInspector.describe(Point.class);
        assertTrue(desc.contains("final"));
    }

    @Test
    void declaredFieldCountForString() {
        assertTrue(ClassInspector.declaredFieldCount(String.class) > 0);
    }

    @Test
    void isRecordForRecordClass() {
        record Item(String name) { }
        assertTrue(ClassInspector.isRecord(Item.class));
    }

    @Test
    void isRecordReturnsFalseForRegularClass() {
        assertFalse(ClassInspector.isRecord(String.class));
    }
}
