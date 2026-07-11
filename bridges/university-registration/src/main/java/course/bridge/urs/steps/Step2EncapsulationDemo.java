package course.bridge.urs.steps;

/**
 * Step 2 — encapsulation with private fields and accessors (Ch. 4).
 */
public final class Step2EncapsulationDemo {

    private Step2EncapsulationDemo() {
    }

    public static void main(String[] args) {
        Course java = new Course("Java", 1);
        Student ali = new Student("Ali", 1);
        ali.register(java);
        System.out.println(ali.getName() + " enrolled in " + ali.registeredCourses()[0].getName());
    }

    static final class Course {
        private final String name;
        private final int id;

        Course(String name, int id) {
            this.name = name;
            this.id = id;
        }

        String getName() {
            return name;
        }

        int getId() {
            return id;
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

        String getName() {
            return name;
        }

        void register(Course course) {
            if (count < courses.length) {
                courses[count++] = course;
            }
        }

        Course[] registeredCourses() {
            Course[] copy = new Course[count];
            System.arraycopy(courses, 0, copy, 0, count);
            return copy;
        }
    }
}
