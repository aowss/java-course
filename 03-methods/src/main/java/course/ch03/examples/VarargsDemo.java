package course.ch03.examples;

/**
 * Demonstrates varargs: variable-length argument lists.
 */
public class VarargsDemo {

    public static int sum(int... numbers) {
        int total = 0;
        for (int n : numbers) {
            total += n;
        }
        return total;
    }

    public static String joinWithSeparator(String separator, String... parts) {
        var sb = new StringBuilder();
        for (int i = 0; i < parts.length; i++) {
            if (i > 0) {
                sb.append(separator);
            }
            sb.append(parts[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("sum()          = " + sum());
        System.out.println("sum(1)         = " + sum(1));
        System.out.println("sum(1, 2, 3)   = " + sum(1, 2, 3));
        System.out.println("sum(array)     = " + sum(new int[]{10, 20, 30}));

        System.out.println("\njoin: " + joinWithSeparator(", ", "Alice", "Bob", "Carol"));
        System.out.println("join: " + joinWithSeparator(" → ", "start", "middle", "end"));
    }
}
