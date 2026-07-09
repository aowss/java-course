package course.ch24.examples;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DurationAndPeriodTest {

    @Test
    void elapsedCalculatesDurationBetweenDateTimes() {
        LocalDateTime start = LocalDateTime.of(2024, 3, 15, 9, 0);
        LocalDateTime end = LocalDateTime.of(2024, 3, 15, 11, 30);
        assertEquals(Duration.ofHours(2).plusMinutes(30), DurationAndPeriod.elapsed(start, end));
    }

    @Test
    void addDurationShiftsDateTime() {
        LocalDateTime start = LocalDateTime.of(2024, 3, 15, 9, 0);
        assertEquals(LocalDateTime.of(2024, 3, 15, 10, 30),
                DurationAndPeriod.addDuration(start, Duration.ofMinutes(90)));
    }

    @Test
    void ageBetweenCalculatesPeriod() {
        Period period = DurationAndPeriod.ageBetween(
                LocalDate.of(1990, 5, 20), LocalDate.of(2024, 5, 20));
        assertEquals(34, period.getYears());
    }

    @Test
    void addPeriodShiftsDate() {
        LocalDate date = LocalDate.of(2024, 1, 15);
        Period period = Period.ofMonths(2);
        assertEquals(LocalDate.of(2024, 3, 15), DurationAndPeriod.addPeriod(date, period));
    }

    @Test
    void formatDurationRendersHms() {
        assertEquals("02:30:45", DurationAndPeriod.formatDuration(Duration.ofHours(2).plusMinutes(30).plusSeconds(45)));
    }
}
