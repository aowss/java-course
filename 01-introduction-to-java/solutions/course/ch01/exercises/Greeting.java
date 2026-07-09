package course.ch01.exercises;

public class Greeting {

    public static String greet(String name) {
        if (name == null || name.isEmpty()) {
            return "Hello, World!";
        }
        return "Hello, " + name + "!";
    }

    public static void main(String[] args) {
        String name = args.length > 0 ? args[0] : null;
        System.out.println(greet(name));
    }
}
