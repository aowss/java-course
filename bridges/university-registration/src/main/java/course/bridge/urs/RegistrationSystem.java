package course.bridge.urs;

import course.bridge.urs.model.Course;
import course.bridge.urs.model.Instructor;
import course.bridge.urs.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Registry for instructors, courses, and students with enrollment operations.
 */
public final class RegistrationSystem {

    private final List<Instructor> instructors = new ArrayList<>();
    private final List<Course> courses = new ArrayList<>();
    private final List<Student> students = new ArrayList<>();

    public Instructor addInstructor(String name) {
        Instructor instructor = new Instructor(name);
        instructors.add(instructor);
        return instructor;
    }

    public Course addCourse(String name, int instructorId) {
        Instructor instructor = findInstructor(instructorId)
                .orElseThrow(() -> new IllegalArgumentException("unknown instructor id: " + instructorId));
        Course course = new Course(name, instructor);
        courses.add(course);
        return course;
    }

    public Student addStudent(String name) {
        Student student = new Student(name);
        students.add(student);
        return student;
    }

    public void register(int studentId, int courseId) {
        Student student = findStudent(studentId)
                .orElseThrow(() -> new IllegalArgumentException("unknown student id: " + studentId));
        Course course = findCourse(courseId)
                .orElseThrow(() -> new IllegalArgumentException("unknown course id: " + courseId));
        student.register(course);
    }

    public List<Instructor> listInstructors() {
        return List.copyOf(instructors);
    }

    public List<Course> listCourses() {
        return List.copyOf(courses);
    }

    public List<Student> listStudents() {
        return List.copyOf(students);
    }

    public Optional<Student> findStudent(int id) {
        return students.stream().filter(s -> s.getId() == id).findFirst();
    }

    public Optional<Course> findCourse(int id) {
        return courses.stream().filter(c -> c.getId() == id).findFirst();
    }

    public Optional<Instructor> findInstructor(int id) {
        return instructors.stream().filter(i -> i.getId() == id).findFirst();
    }

    public List<Course> coursesForStudent(int studentId) {
        Student student = findStudent(studentId)
                .orElseThrow(() -> new IllegalArgumentException("unknown student id: " + studentId));
        return List.of(student.registeredCourses());
    }

    public List<Student> studentsInCourse(int courseId) {
        Course course = findCourse(courseId)
                .orElseThrow(() -> new IllegalArgumentException("unknown course id: " + courseId));
        return List.of(course.enrolledStudents());
    }

    void clearForTests() {
        instructors.clear();
        courses.clear();
        students.clear();
        Instructor.resetIdSequenceForTests();
        Course.resetIdSequenceForTests();
        Student.resetIdSequenceForTests();
    }
}
