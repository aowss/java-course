package course.ch01.exercises;

public class SystemReporter {

    public static String report() {
        return "Java " + System.getProperty("java.version")
                + " on " + System.getProperty("os.name")
                + " (" + System.getProperty("os.arch") + ")";
    }

    public static void main(String[] args) {
        System.out.println(report());
    }
}
