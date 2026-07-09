package course.ch24.examples;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

/**
 * Demonstrates creating date and time values with the java.time API.
 */
public class DateTimeCreation {

    /**
     * Creates a {@link LocalDate} from year, month, and day components.
     *
     * @param year  the year
     * @param month the month (1–12)
     * @param day   the day of month
     * @return the resulting date
     */
    public static LocalDate createDate(int year, int month, int day) {
        return LocalDate.of(year, month, day);
    }

    /**
     * Creates a {@link LocalTime} from hour, minute, and second components.
     *
     * @param hour   the hour (0–23)
     * @param minute the minute (0–59)
     * @param second the second (0–59)
     * @return the resulting time
     */
    public static LocalTime createTime(int hour, int minute, int second) {
        return LocalTime.of(hour, minute, second);
    }

    /**
     * Combines a date and time into a {@link LocalDateTime}.
     *
     * @param date the date component
     * @param time the time component
     * @return the combined date-time
     */
    public static LocalDateTime combine(LocalDate date, LocalTime time) {
        return LocalDateTime.of(date, time);
    }

    /**
     * Parses an ISO-8601 date string such as {@code 2024-03-15}.
     *
     * @param isoDate the date string
     * @return the parsed date
     */
    public static LocalDate parseDate(String isoDate) {
        return LocalDate.parse(isoDate);
    }

    /**
     * Returns the number of days in the given month, accounting for leap years.
     *
     * @param year  the year
     * @param month the month
     * @return days in the month
     */
    public static int daysInMonth(int year, Month month) {
        return month.length(LocalDate.of(year, 1, 1).isLeapYear());
    }

    public static void main(String[] args) {
        LocalDate date = createDate(2024, Month.MARCH.getValue(), 15);
        LocalTime time = createTime(14, 30, 0);
        LocalDateTime dateTime = combine(date, time);
        System.out.println("Date:     " + date);
        System.out.println("Time:     " + time);
        System.out.println("DateTime: " + dateTime);
        System.out.println("Days in Feb 2024: " + daysInMonth(2024, Month.FEBRUARY));
    }
}
