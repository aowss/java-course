package course.bridge.urs.model;

import java.util.Objects;

/**
 * A student who can register for up to {@link #MAX_COURSES} courses.
 */
public final class Student {

    public static final int MAX_COURSES = 6;

    private static int nextId = 1;

    private final int id;
    private final String name;
    private final Course[] courses = new Course[MAX_COURSES];
    private int courseCount;

    public Student(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name must not be blank");
        }
        this.id = nextId++;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCourseCount() {
        return courseCount;
    }

    /**
     * Enrolls this student in a course and updates the course roster (bidirectional link).
     */
    public void register(Course course) {
        Objects.requireNonNull(course, "course must not be null");
        if (courseCount >= MAX_COURSES) {
            throw new IllegalStateException("student course limit reached: " + name);
        }
        for (int i = 0; i < courseCount; i++) {
            if (courses[i].equals(course)) {
                throw new IllegalStateException("already registered for " + course.getName());
            }
        }
        courses[courseCount++] = course;
        course.addStudent(this);
    }

    public Course[] registeredCourses() {
        Course[] copy = new Course[courseCount];
        System.arraycopy(courses, 0, copy, 0, courseCount);
        return copy;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Student other && id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "'}";
    }

    public static void resetIdSequenceForTests() {
        nextId = 1;
    }
}
