package course.ch24.examples;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateTimeCreationTest {

    @Test
    void createDateBuildsExpectedDate() {
        assertEquals(LocalDate.of(2024, 3, 15), DateTimeCreation.createDate(2024, 3, 15));
    }

    @Test
    void createTimeBuildsExpectedTime() {
        assertEquals(LocalTime.of(14, 30, 0), DateTimeCreation.createTime(14, 30, 0));
    }

    @Test
    void combineJoinsDateAndTime() {
        LocalDate date = LocalDate.of(2024, 1, 1);
        LocalTime time = LocalTime.of(8, 15, 30);
        assertEquals(LocalDateTime.of(date, time), DateTimeCreation.combine(date, time));
    }

    @Test
    void parseDateReadsIsoString() {
        assertEquals(LocalDate.of(2024, 12, 25), DateTimeCreation.parseDate("2024-12-25"));
    }

    @Test
    void daysInMonthHandlesLeapYear() {
        assertEquals(29, DateTimeCreation.daysInMonth(2024, Month.FEBRUARY));
        assertEquals(28, DateTimeCreation.daysInMonth(2023, Month.FEBRUARY));
    }
}
