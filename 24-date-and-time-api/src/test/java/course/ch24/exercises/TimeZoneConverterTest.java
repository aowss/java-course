package course.ch24.exercises;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TimeZoneConverterTest {

    @Test
    void convertPreservesInstant() {
        ZonedDateTime ny = ZonedDateTime.of(2024, 7, 9, 14, 0, 0, 0, ZoneId.of("America/New_York"));
        ZonedDateTime tokyo = TimeZoneConverter.convert(ny, ZoneId.of("Asia/Tokyo"));
        assertEquals(ny.toInstant(), tokyo.toInstant());
        assertEquals(ZoneId.of("Asia/Tokyo"), tokyo.getZone());
    }

    @Test
    void toLocalTimeReturnsLocalClockInTargetZone() {
        ZonedDateTime utc = ZonedDateTime.of(2024, 7, 9, 12, 0, 0, 0, ZoneId.of("UTC"));
        LocalDateTime london = TimeZoneConverter.toLocalTime(utc, ZoneId.of("Europe/London"));
        assertEquals(13, london.getHour());
    }

    @Test
    void convertRejectsNullArguments() {
        ZonedDateTime ny = ZonedDateTime.of(2024, 7, 9, 14, 0, 0, 0, ZoneId.of("America/New_York"));
        assertThrows(IllegalArgumentException.class,
                () -> TimeZoneConverter.convert(null, ZoneId.of("UTC")));
        assertThrows(IllegalArgumentException.class,
                () -> TimeZoneConverter.convert(ny, null));
    }
}
