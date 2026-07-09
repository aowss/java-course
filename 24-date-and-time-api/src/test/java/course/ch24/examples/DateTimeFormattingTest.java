package course.ch24.examples;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DateTimeFormattingTest {

    @Test
    void formatIsoDateUsesIsoPattern() {
        assertEquals("2024-03-15", DateTimeFormatting.formatIsoDate(LocalDate.of(2024, 3, 15)));
    }

    @Test
    void formatCustomUsesConfiguredPattern() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 3, 15, 9, 0);
        assertEquals("15/03/2024 09:00", DateTimeFormatting.formatCustom(dateTime));
    }

    @Test
    void formatLocalizedUsesLocale() {
        String formatted = DateTimeFormatting.formatLocalized(LocalDate.of(2024, 3, 15), Locale.US);
        assertTrue(formatted.contains("2024"));
        assertTrue(formatted.contains("15"));
    }

    @Test
    void parseCustomReadsFormattedString() {
        LocalDateTime parsed = DateTimeFormatting.parseCustom("15/03/2024 09:00");
        assertEquals(LocalDateTime.of(2024, 3, 15, 9, 0), parsed);
    }
}
