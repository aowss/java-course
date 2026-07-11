package course.bridge.urs.steps;

import course.bridge.urs.RegistrationSystem;
import course.bridge.urs.model.Course;
import course.bridge.urs.model.Instructor;
import course.bridge.urs.model.Student;

/**
 * Step 5 — full {@link RegistrationSystem} and production model classes (Ch. 6+).
 */
public final class Step5SystemDemo {

    private Step5SystemDemo() {
    }

    public static void main(String[] args) {
        RegistrationSystem system = new RegistrationSystem();

        Instructor moussa = system.addInstructor("Moussa");
        Instructor salman = system.addInstructor("Salman");
        Course java = system.addCourse("Java", moussa.getId());
        Course c = system.addCourse("C", salman.getId());
        Student ali = system.addStudent("Ali");
        Student zaid = system.addStudent("Zaid");

        system.register(ali.getId(), java.getId());
        system.register(ali.getId(), c.getId());
        system.register(zaid.getId(), java.getId());

        System.out.println("Ali's courses:");
        system.coursesForStudent(ali.getId()).forEach(course ->
                System.out.println("  " + course.getName()));

        System.out.println("Java roster:");
        system.studentsInCourse(java.getId()).forEach(student ->
                System.out.println("  " + student.getName()));
    }
}
