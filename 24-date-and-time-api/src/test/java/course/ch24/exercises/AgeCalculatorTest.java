package course.ch24.exercises;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Period;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AgeCalculatorTest {

    @Test
    void calculateAgeReturnsCompleteYears() {
        LocalDate birth = LocalDate.of(1990, 5, 20);
        LocalDate onDate = LocalDate.of(2024, 5, 19);
        assertEquals(33, AgeCalculator.calculateAge(birth, onDate));
    }

    @Test
    void calculateAgeOnBirthday() {
        LocalDate birth = LocalDate.of(1990, 5, 20);
        LocalDate onDate = LocalDate.of(2024, 5, 20);
        assertEquals(34, AgeCalculator.calculateAge(birth, onDate));
    }

    @Test
    void agePeriodReturnsFullPeriod() {
        Period period = AgeCalculator.agePeriod(
                LocalDate.of(1990, 5, 20), LocalDate.of(2024, 7, 1));
        assertEquals(34, period.getYears());
        assertEquals(1, period.getMonths());
        assertEquals(11, period.getDays());
    }

    @Test
    void calculateAgeRejectsBirthDateAfterOnDate() {
        assertThrows(IllegalArgumentException.class,
                () -> AgeCalculator.calculateAge(LocalDate.of(2025, 1, 1), LocalDate.of(2024, 1, 1)));
    }

    @Test
    void calculateAgeRejectsNullDates() {
        assertThrows(IllegalArgumentException.class,
                () -> AgeCalculator.calculateAge(null, LocalDate.now()));
    }
}
