package course.ch24.examples;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ZonedDateTimeDemoTest {

    @Test
    void inZoneAttachesZone() {
        LocalDateTime local = LocalDateTime.of(2024, 7, 9, 14, 0);
        ZonedDateTime zoned = ZonedDateTimeDemo.inZone(local, ZoneId.of("America/New_York"));
        assertEquals(ZoneId.of("America/New_York"), zoned.getZone());
        assertEquals(local, zoned.toLocalDateTime());
    }

    @Test
    void convertZonePreservesInstant() {
        ZonedDateTime ny = ZonedDateTime.of(2024, 7, 9, 14, 0, 0, 0, ZoneId.of("America/New_York"));
        ZonedDateTime tokyo = ZonedDateTimeDemo.convertZone(ny, ZoneId.of("Asia/Tokyo"));
        assertEquals(ny.toInstant(), tokyo.toInstant());
    }

    @Test
    void formatIncludesZone() {
        ZonedDateTime zoned = ZonedDateTime.of(2024, 7, 9, 14, 0, 0, 0, ZoneId.of("UTC"));
        assertTrue(ZonedDateTimeDemo.format(zoned).contains("UTC"));
    }

    @Test
    void offsetAtReturnsOffsetString() {
        LocalDateTime local = LocalDateTime.of(2024, 7, 9, 14, 0);
        String offset = ZonedDateTimeDemo.offsetAt(local, ZoneId.of("America/New_York"));
        assertTrue(offset.equals("-04:00") || offset.equals("-05:00"));
    }
}
