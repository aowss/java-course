package course.ch02.exercises;

public class ArrayStats {

    public static int min(int[] values) {
        requireNonEmpty(values);
        int result = values[0];
        for (int i = 1; i < values.length; i++) {
            if (values[i] < result) {
                result = values[i];
            }
        }
        return result;
    }

    public static int max(int[] values) {
        requireNonEmpty(values);
        int result = values[0];
        for (int i = 1; i < values.length; i++) {
            if (values[i] > result) {
                result = values[i];
            }
        }
        return result;
    }

    public static long sum(int[] values) {
        requireNonEmpty(values);
        long total = 0;
        for (int v : values) {
            total += v;
        }
        return total;
    }

    public static double average(int[] values) {
        return (double) sum(values) / values.length;
    }

    private static void requireNonEmpty(int[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty");
        }
    }
}
