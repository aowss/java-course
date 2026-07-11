package course.bridge.urs.steps;

/**
 * Step 4 — bidirectional registration: enrolling updates both student and course (Ch. 6).
 */
public final class Step4BidirectionalDemo {

    private Step4BidirectionalDemo() {
    }

    public static void main(String[] args) {
        Instructor moussa = new Instructor("Moussa");
        Course java = new Course("Java", moussa);
        Student ali = new Student("Ali");
        Student zaid = new Student("Zaid");

        ali.register(java);
        zaid.register(java);

        ali.report();
        java.report();
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
        private static int nextId = 1;

        private final int id;
        private final String name;
        private final Instructor instructor;
        private final Student[] students = new Student[20];
        private int count;

        Course(String name, Instructor instructor) {
            this.id = nextId++;
            this.name = name;
            this.instructor = instructor;
        }

        String getName() {
            return name;
        }

        void addStudent(Student student) {
            students[count++] = student;
        }

        void report() {
            System.out.println("Course " + name + " (id=" + id + ") roster:");
            for (int i = 0; i < count; i++) {
                System.out.println("  " + students[i].getName());
            }
        }
    }

    static final class Student {
        private static int nextId = 1;

        private final int id;
        private final String name;
        private final Course[] courses = new Course[6];
        private int count;

        Student(String name) {
            this.id = nextId++;
            this.name = name;
        }

        String getName() {
            return name;
        }

        void register(Course course) {
            courses[count++] = course;
            course.addStudent(this);
        }

        void report() {
            System.out.println("Student " + name + " (id=" + id + ") courses:");
            for (int i = 0; i < count; i++) {
                System.out.println("  " + courses[i].getName());
            }
        }
    }
}
