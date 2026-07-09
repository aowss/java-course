package course.ch24.examples;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

/**
 * Demonstrates {@link Duration} for time-based amounts and {@link Period}
 * for date-based amounts.
 */
public class DurationAndPeriod {

    /**
     * Calculates the {@link Duration} between two date-times.
     *
     * @param start the start date-time
     * @param end   the end date-time
     * @return the elapsed duration (never negative)
     */
    public static Duration elapsed(LocalDateTime start, LocalDateTime end) {
        return Duration.between(start, end).abs();
    }

    /**
     * Adds a duration to a date-time.
     *
     * @param dateTime the starting date-time
     * @param duration the duration to add
     * @return the resulting date-time
     */
    public static LocalDateTime addDuration(LocalDateTime dateTime, Duration duration) {
        return dateTime.plus(duration);
    }

    /**
     * Calculates the {@link Period} between two dates.
     *
     * @param start the start date
     * @param end   the end date
     * @return the period between dates
     */
    public static Period ageBetween(LocalDate start, LocalDate end) {
        return Period.between(start, end);
    }

    /**
     * Adds a period to a date.
     *
     * @param date   the starting date
     * @param period the period to add
     * @return the resulting date
     */
    public static LocalDate addPeriod(LocalDate date, Period period) {
        return date.plus(period);
    }

    /**
     * Formats a duration as {@code HH:MM:SS}.
     *
     * @param duration the duration
     * @return a zero-padded hours:minutes:seconds string
     */
    public static String formatDuration(Duration duration) {
        long seconds = duration.getSeconds();
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long secs = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, secs);
    }

    public static void main(String[] args) {
        LocalDateTime start = LocalDateTime.of(2024, 3, 15, 9, 0);
        LocalDateTime end = LocalDateTime.of(2024, 3, 15, 11, 30);
        Duration duration = elapsed(start, end);
        System.out.println("Elapsed: " + formatDuration(duration));

        LocalDate birth = LocalDate.of(1990, 5, 20);
        Period age = ageBetween(birth, LocalDate.of(2024, 5, 20));
        System.out.println("Age: " + age.getYears() + " years");
    }
}
