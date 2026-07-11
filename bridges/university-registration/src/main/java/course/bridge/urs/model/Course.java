package course.bridge.urs.model;

import java.util.Objects;

/**
 * A university course taught by one instructor.
 */
public final class Course {

    public static final int MAX_STUDENTS = 20;

    private static int nextId = 1;

    private final int id;
    private final String name;
    private final Instructor instructor;
    private final Student[] students = new Student[MAX_STUDENTS];
    private int studentCount;

    public Course(String name, Instructor instructor) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name must not be blank");
        }
        Objects.requireNonNull(instructor, "instructor must not be null");
        this.id = nextId++;
        this.name = name;
        this.instructor = instructor;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public int getStudentCount() {
        return studentCount;
    }

    /**
     * Adds a student to this course roster. Called from {@link Student#register(Course)}.
     */
    void addStudent(Student student) {
        Objects.requireNonNull(student, "student must not be null");
        if (studentCount >= MAX_STUDENTS) {
            throw new IllegalStateException("course is full: " + name);
        }
        for (int i = 0; i < studentCount; i++) {
            if (students[i].equals(student)) {
                throw new IllegalStateException("student already enrolled in " + name);
            }
        }
        students[studentCount++] = student;
    }

    public Student[] enrolledStudents() {
        Student[] copy = new Student[studentCount];
        System.arraycopy(students, 0, copy, 0, studentCount);
        return copy;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Course other && id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Course{id=" + id + ", name='" + name + "', instructor=" + instructor.getName() + "}";
    }

    public static void resetIdSequenceForTests() {
        nextId = 1;
    }
}
