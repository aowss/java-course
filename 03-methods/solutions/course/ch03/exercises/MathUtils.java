package course.ch03.exercises;

public class MathUtils {

    public static int clamp(int value, int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("min (" + min + ") > max (" + max + ")");
        }
        if (value < min) {
            return min;
        }
        if (value > max) {
            return max;
        }
        return value;
    }

    public static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be non-negative, was: " + n);
        }
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
