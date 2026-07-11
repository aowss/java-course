package course.bridge.urs.steps;

/**
 * Step 3 — add {@code Instructor} and associate each course with a teacher (Ch. 4–6).
 */
public final class Step3InstructorDemo {

    private Step3InstructorDemo() {
    }

    public static void main(String[] args) {
        Instructor moussa = new Instructor("Moussa");
        Course java = new Course("Java", 1, moussa);
        Student ali = new Student("Ali", 1);
        ali.register(java);
        ali.report();
    }

    static final class Instructor {
        private final String name;

        Instructor(String name) {
            this.name = name;
        }

        String getName() {
            return name;
        }
    }

    static final class Course {
        private final String name;
        private final int id;
        private final Instructor instructor;

        Course(String name, int id, Instructor instructor) {
            this.name = name;
            this.id = id;
            this.instructor = instructor;
        }

        String getName() {
            return name;
        }

        Instructor getInstructor() {
            return instructor;
        }
    }

    static final class Student {
        private final String name;
        private final int id;
        private final Course[] courses = new Course[6];
        private int count;

        Student(String name, int id) {
            this.name = name;
            this.id = id;
        }

        void register(Course course) {
            if (count < courses.length) {
                courses[count++] = course;
            }
        }

        void report() {
            System.out.println("Student " + name + ":");
            for (int i = 0; i < count; i++) {
                Course c = courses[i];
                System.out.println("  " + c.getName() + " with " + c.getInstructor().getName());
            }
        }
    }
}
