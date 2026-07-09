package course.ch24.examples;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Demonstrates time zones and {@link ZonedDateTime}.
 */
public class ZonedDateTimeDemo {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm z");

    /**
     * Creates a {@link ZonedDateTime} in the given zone.
     *
     * @param dateTime the local date-time
     * @param zoneId   the time zone
     * @return the zoned date-time
     */
    public static ZonedDateTime inZone(LocalDateTime dateTime, ZoneId zoneId) {
        return dateTime.atZone(zoneId);
    }

    /**
     * Converts a zoned date-time to another time zone, preserving the instant.
     *
     * @param dateTime  the source zoned date-time
     * @param targetZone the target zone
     * @return the converted zoned date-time
     */
    public static ZonedDateTime convertZone(ZonedDateTime dateTime, ZoneId targetZone) {
        return dateTime.withZoneSameInstant(targetZone);
    }

    /**
     * Formats a zoned date-time for display.
     *
     * @param dateTime the zoned date-time
     * @return a formatted string including the zone abbreviation
     */
    public static String format(ZonedDateTime dateTime) {
        return dateTime.format(FORMATTER);
    }

    /**
     * Returns the UTC offset for the given zone at the specified date-time.
     *
     * @param dateTime the reference date-time
     * @param zoneId   the zone to inspect
     * @return offset string such as {@code +02:00}
     */
    public static String offsetAt(LocalDateTime dateTime, ZoneId zoneId) {
        return dateTime.atZone(zoneId).getOffset().getId();
    }

    public static void main(String[] args) {
        LocalDateTime meeting = LocalDateTime.of(2024, 7, 9, 14, 0);
        ZonedDateTime ny = inZone(meeting, ZoneId.of("America/New_York"));
        ZonedDateTime tokyo = convertZone(ny, ZoneId.of("Asia/Tokyo"));
        System.out.println("New York: " + format(ny));
        System.out.println("Tokyo:    " + format(tokyo));
        System.out.println("Offset:   " + offsetAt(meeting, ZoneId.of("America/New_York")));
    }
}
