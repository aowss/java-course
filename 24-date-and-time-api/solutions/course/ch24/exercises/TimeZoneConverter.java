package course.ch24.exercises;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeZoneConverter {

    public static ZonedDateTime convert(ZonedDateTime dateTime, ZoneId targetZone) {
        if (dateTime == null || targetZone == null) {
            throw new IllegalArgumentException("Arguments must not be null");
        }
        return dateTime.withZoneSameInstant(targetZone);
    }

    public static LocalDateTime toLocalTime(ZonedDateTime dateTime, ZoneId targetZone) {
        return convert(dateTime, targetZone).toLocalDateTime();
    }

    public static void main(String[] args) {
        ZonedDateTime ny = ZonedDateTime.of(2024, 7, 9, 14, 0, 0, 0, ZoneId.of("America/New_York"));
        ZonedDateTime tokyo = convert(ny, ZoneId.of("Asia/Tokyo"));
        System.out.println("Tokyo: " + tokyo);
        System.out.println("Local: " + toLocalTime(ny, ZoneId.of("Europe/London")));
    }
}
