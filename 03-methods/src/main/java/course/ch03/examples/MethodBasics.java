package course.ch03.examples;

/**
 * Demonstrates method definition, calling, return types, and pass-by-value semantics.
 */
public class MethodBasics {

    public static double circleArea(double radius) {
        return Math.PI * radius * radius;
    }

    public static double circleCircumference(double radius) {
        return 2 * Math.PI * radius;
    }

    public static boolean isEven(int n) {
        return n % 2 == 0;
    }

    /**
     * Demonstrates that Java is pass-by-value.
     * This method cannot change the caller's variable.
     */
    public static void tryToModify(int value) {
        value = 999;
    }

    public static void main(String[] args) {
        System.out.println("Area of circle (r=5):          " + circleArea(5));
        System.out.println("Circumference of circle (r=5): " + circleCircumference(5));
        System.out.println("Is 42 even? " + isEven(42));
        System.out.println("Is 17 even? " + isEven(17));

        // Pass-by-value demonstration
        int x = 10;
        tryToModify(x);
        System.out.println("\nAfter tryToModify(x), x is still: " + x);
    }
}
