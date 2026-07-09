package course.ch24.exercises;

import java.time.LocalDate;
import java.time.Period;

/**
 * Exercise 1 (Guided): Calculate a person's age from their birth date.
 */
public class AgeCalculator {

    /**
     * Calculates whole-year age on the given date.
     *
     * @param birthDate the date of birth
     * @param onDate    the date on which to calculate age
     * @return age in complete years
     * @throws IllegalArgumentException if either date is {@code null} or {@code birthDate} is after {@code onDate}
     */
    public static int calculateAge(LocalDate birthDate, LocalDate onDate) {
        // TODO: implement using Period.between
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns the full age as a {@link Period} (years, months, days).
     *
     * @param birthDate the date of birth
     * @param onDate    the date on which to calculate age
     * @return the age period
     * @throws IllegalArgumentException if either date is {@code null} or {@code birthDate} is after {@code onDate}
     */
    public static Period agePeriod(LocalDate birthDate, LocalDate onDate) {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static void main(String[] args) {
        LocalDate birth = LocalDate.of(1990, 5, 20);
        LocalDate today = LocalDate.of(2024, 5, 19);
        System.out.println("Age: " + calculateAge(birth, today));
        System.out.println("Period: " + agePeriod(birth, today));
    }
}
