package course.bridge.urs;

import course.bridge.urs.model.Course;
import course.bridge.urs.model.Instructor;
import course.bridge.urs.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RegistrationSystemTest {

    private RegistrationSystem system;

    @BeforeEach
    void setUp() {
        system = new RegistrationSystem();
        system.clearForTests();
    }

    @Test
    void registerLinksStudentAndCourse() {
        Instructor instructor = system.addInstructor("Moussa");
        Course java = system.addCourse("Java", instructor.getId());
        Student ali = system.addStudent("Ali");

        system.register(ali.getId(), java.getId());

        assertEquals(1, ali.getCourseCount());
        assertEquals(1, java.getStudentCount());
        assertEquals("Java", system.coursesForStudent(ali.getId()).getFirst().getName());
        assertEquals("Ali", system.studentsInCourse(java.getId()).getFirst().getName());
    }

    @Test
    void studentCannotExceedCourseLimit() {
        Instructor instructor = system.addInstructor("Moussa");
        Student ali = system.addStudent("Ali");
        for (int i = 0; i < Student.MAX_COURSES; i++) {
            Course course = system.addCourse("Course" + i, instructor.getId());
            system.register(ali.getId(), course.getId());
        }
        Course extra = system.addCourse("Extra", instructor.getId());
        assertThrows(IllegalStateException.class, () -> system.register(ali.getId(), extra.getId()));
    }

    @Test
    void duplicateRegistrationIsRejected() {
        Instructor instructor = system.addInstructor("Moussa");
        Course java = system.addCourse("Java", instructor.getId());
        Student ali = system.addStudent("Ali");
        system.register(ali.getId(), java.getId());
        assertThrows(IllegalStateException.class, () -> system.register(ali.getId(), java.getId()));
    }

    @Test
    void unknownIdsThrow() {
        assertThrows(IllegalArgumentException.class, () -> system.register(99, 1));
    }
}
