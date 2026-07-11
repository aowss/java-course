package course.bridge.urs.steps;

/**
 * Step 1 — basic classes with public fields and one-way registration (Ch. 4).
 *
 * <p>Run: {@code mvn compile exec:java -pl bridges/university-registration -Dexec.mainClass="course.bridge.urs.steps.Step1BasicDemo"}
 */
public final class Step1BasicDemo {

    private Step1BasicDemo() {
    }

    public static void main(String[] args) {
        Course java = new Course("Java", 1);
        Course cLang = new Course("C", 2);
        Student ali = new Student("Ali", 1);
        Student zaid = new Student("Zaid", 2);

        ali.register(java);
        ali.register(cLang);
        zaid.register(java);

        ali.report();
        zaid.report();
    }

    static final class Course {
        String name;
        int id;

        Course(String name, int id) {
            this.name = name;
            this.id = id;
        }
    }

    static final class Student {
        String name;
        int id;
        final Course[] courses = new Course[6];
        int index;

        Student(String name, int id) {
            this.name = name;
            this.id = id;
        }

        void register(Course course) {
            if (index < courses.length) {
                courses[index++] = course;
            }
        }

        void report() {
            System.out.println("Student " + name + " (id=" + id + ") courses:");
            for (int i = 0; i < index; i++) {
                System.out.println("  - " + courses[i].name + " (id=" + courses[i].id + ")");
            }
        }
    }
}
