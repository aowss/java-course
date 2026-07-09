package course.ch02.examples;

/**
 * Demonstrates if/else, enhanced switch, and all loop forms.
 */
public class ControlFlow {

    public static char letterGrade(int score) {
        if (score >= 90) {
            return 'A';
        } else if (score >= 80) {
            return 'B';
        } else if (score >= 70) {
            return 'C';
        } else if (score >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }

    public static String dayType(String day) {
        return switch (day.toUpperCase()) {
            case "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY" -> "Weekday";
            case "SATURDAY", "SUNDAY" -> "Weekend";
            default -> throw new IllegalArgumentException("Unknown day: " + day);
        };
    }

    public static void main(String[] args) {
        // if/else
        System.out.println("Score 85 → grade: " + letterGrade(85));
        System.out.println("Score 42 → grade: " + letterGrade(42));

        // switch expression
        System.out.println("\nMonday is a: " + dayType("Monday"));
        System.out.println("Sunday is a: " + dayType("Sunday"));

        // for loop
        System.out.println("\nCounting 1 to 5 (for):");
        for (int i = 1; i <= 5; i++) {
            System.out.println("  " + i);
        }

        // while loop
        System.out.println("\nPowers of 2 under 100 (while):");
        int power = 1;
        while (power < 100) {
            System.out.println("  " + power);
            power *= 2;
        }

        // do-while loop
        System.out.println("\ndo-while runs at least once:");
        int x = 10;
        do {
            System.out.println("  x = " + x);
            x++;
        } while (x < 10);
    }
}
