package course.ch03.examples;

/**
 * Demonstrates method overloading: same name, different parameter lists.
 */
public class Overloading {

    public static int add(int a, int b) {
        return a + b;
    }

    public static double add(double a, double b) {
        return a + b;
    }

    public static int add(int a, int b, int c) {
        return a + b + c;
    }

    public static String describe(int n) {
        return "int: " + n;
    }

    public static String describe(double n) {
        return "double: " + n;
    }

    public static String describe(String s) {
        return "String: " + s;
    }

    public static void main(String[] args) {
        System.out.println("add(1, 2)       = " + add(1, 2));
        System.out.println("add(1.5, 2.5)   = " + add(1.5, 2.5));
        System.out.println("add(1, 2, 3)    = " + add(1, 2, 3));

        System.out.println("\n" + describe(42));
        System.out.println(describe(3.14));
        System.out.println(describe("hello"));
    }
}
