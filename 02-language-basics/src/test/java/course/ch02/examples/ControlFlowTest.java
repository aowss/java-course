package course.ch02.examples;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ControlFlowTest {

    @Test
    void letterGradeA() {
        assertEquals('A', ControlFlow.letterGrade(95));
        assertEquals('A', ControlFlow.letterGrade(90));
    }

    @Test
    void letterGradeB() {
        assertEquals('B', ControlFlow.letterGrade(85));
        assertEquals('B', ControlFlow.letterGrade(80));
    }

    @Test
    void letterGradeF() {
        assertEquals('F', ControlFlow.letterGrade(59));
        assertEquals('F', ControlFlow.letterGrade(0));
    }

    @Test
    void dayTypeWeekday() {
        assertEquals("Weekday", ControlFlow.dayType("Monday"));
        assertEquals("Weekday", ControlFlow.dayType("friday"));
    }

    @Test
    void dayTypeWeekend() {
        assertEquals("Weekend", ControlFlow.dayType("Saturday"));
        assertEquals("Weekend", ControlFlow.dayType("SUNDAY"));
    }

    @Test
    void dayTypeInvalid() {
        assertThrows(IllegalArgumentException.class, () -> ControlFlow.dayType("Funday"));
    }
}
