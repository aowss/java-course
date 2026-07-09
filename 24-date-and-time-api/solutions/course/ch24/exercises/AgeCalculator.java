package course.ch24.exercises;

import java.time.LocalDate;
import java.time.Period;

public class AgeCalculator {

    public static int calculateAge(LocalDate birthDate, LocalDate onDate) {
        validateDates(birthDate, onDate);
        return Period.between(birthDate, onDate).getYears();
    }

    public static Period agePeriod(LocalDate birthDate, LocalDate onDate) {
        validateDates(birthDate, onDate);
        return Period.between(birthDate, onDate);
    }

    private static void validateDates(LocalDate birthDate, LocalDate onDate) {
        if (birthDate == null || onDate == null) {
            throw new IllegalArgumentException("Dates must not be null");
        }
        if (birthDate.isAfter(onDate)) {
            throw new IllegalArgumentException("Birth date must not be after on date");
        }
    }

    public static void main(String[] args) {
        LocalDate birth = LocalDate.of(1990, 5, 20);
        LocalDate today = LocalDate.of(2024, 5, 19);
        System.out.println("Age: " + calculateAge(birth, today));
        System.out.println("Period: " + agePeriod(birth, today));
    }
}
