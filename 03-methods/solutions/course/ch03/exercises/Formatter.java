package course.ch03.exercises;

public class Formatter {

    public static String format(String label, int value) {
        return label + ": " + value;
    }

    public static String format(String label, double value) {
        return label + ": " + String.format("%.2f", value);
    }

    public static String format(String label, String... values) {
        var sb = new StringBuilder(label).append(": [");
        for (int i = 0; i < values.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(values[i]);
        }
        sb.append("]");
        return sb.toString();
    }
}
