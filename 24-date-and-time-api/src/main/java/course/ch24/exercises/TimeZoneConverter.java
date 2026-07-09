package course.ch24.exercises;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Exercise 3 (Challenge): Convert date-times between time zones.
 */
public class TimeZoneConverter {

    /**
     * Converts a zoned date-time to the target zone, preserving the instant.
     *
     * @param dateTime   the source date-time
     * @param targetZone the destination zone
     * @return the converted zoned date-time
     * @throws IllegalArgumentException if any argument is {@code null}
     */
    public static ZonedDateTime convert(ZonedDateTime dateTime, ZoneId targetZone) {
        // TODO: implement using withZoneSameInstant
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Converts a zoned date-time to local date-time in the target zone.
     *
     * @param dateTime   the source date-time
     * @param targetZone the destination zone
     * @return local date-time in the target zone
     * @throws IllegalArgumentException if any argument is {@code null}
     */
    public static LocalDateTime toLocalTime(ZonedDateTime dateTime, ZoneId targetZone) {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static void main(String[] args) {
        ZonedDateTime ny = ZonedDateTime.of(2024, 7, 9, 14, 0, 0, 0, ZoneId.of("America/New_York"));
        ZonedDateTime tokyo = convert(ny, ZoneId.of("Asia/Tokyo"));
        System.out.println("Tokyo: " + tokyo);
        System.out.println("Local: " + toLocalTime(ny, ZoneId.of("Europe/London")));
    }
}
