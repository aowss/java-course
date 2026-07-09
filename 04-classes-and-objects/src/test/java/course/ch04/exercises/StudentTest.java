package course.ch04.exercises;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StudentTest {

    @Test
    void constructorSetsName() {
        var student = new Student("Alice");
        assertEquals("Alice", student.getName());
    }

    @Test
    void constructorRejectsNullName() {
        assertThrows(IllegalArgumentException.class, () -> new Student(null));
    }

    @Test
    void constructorRejectsEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> new Student(""));
    }

    @Test
    void addGradeAndGetGrades() {
        var student = new Student("Alice");
        student.addGrade(85.0);
        student.addGrade(92.0);
        assertEquals(2, student.getGrades().size());
    }

    @Test
    void addGradeRejectsBelowZero() {
        var student = new Student("Alice");
        assertThrows(IllegalArgumentException.class, () -> student.addGrade(-1));
    }

    @Test
    void addGradeRejectsAbove100() {
        var student = new Student("Alice");
        assertThrows(IllegalArgumentException.class, () -> student.addGrade(101));
    }

    @Test
    void getAverageComputation() {
        var student = new Student("Alice");
        student.addGrade(80.0);
        student.addGrade(90.0);
        student.addGrade(100.0);
        assertEquals(90.0, student.getAverage(), 0.001);
    }

    @Test
    void getAverageThrowsWhenEmpty() {
        var student = new Student("Alice");
        assertThrows(IllegalStateException.class, student::getAverage);
    }

    @Test
    void getHighestReturnsMaxGrade() {
        var student = new Student("Alice");
        student.addGrade(70.0);
        student.addGrade(95.0);
        student.addGrade(85.0);
        assertEquals(95.0, student.getHighest(), 0.001);
    }

    @Test
    void getHighestThrowsWhenEmpty() {
        var student = new Student("Alice");
        assertThrows(IllegalStateException.class, student::getHighest);
    }

    @Test
    void gradesListIsUnmodifiable() {
        var student = new Student("Alice");
        student.addGrade(90.0);
        assertThrows(UnsupportedOperationException.class,
                () -> student.getGrades().add(50.0));
    }
}
