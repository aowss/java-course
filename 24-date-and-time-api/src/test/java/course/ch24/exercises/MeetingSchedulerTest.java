package course.ch24.exercises;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MeetingSchedulerTest {

    @Test
    void hasOverlapDetectsOverlappingMeetings() {
        var first = new MeetingScheduler.Meeting(
                LocalDateTime.of(2024, 3, 15, 9, 0), Duration.ofHours(1));
        var second = new MeetingScheduler.Meeting(
                LocalDateTime.of(2024, 3, 15, 9, 30), Duration.ofMinutes(30));
        assertTrue(MeetingScheduler.hasOverlap(first, second));
    }

    @Test
    void hasOverlapReturnsFalseForAdjacentMeetings() {
        var first = new MeetingScheduler.Meeting(
                LocalDateTime.of(2024, 3, 15, 9, 0), Duration.ofHours(1));
        var second = new MeetingScheduler.Meeting(
                LocalDateTime.of(2024, 3, 15, 10, 0), Duration.ofMinutes(30));
        assertFalse(MeetingScheduler.hasOverlap(first, second));
    }

    @Test
    void findNextSlotReturnsFirstAvailableTime() {
        var existing = List.of(
                new MeetingScheduler.Meeting(
                        LocalDateTime.of(2024, 3, 15, 9, 0), Duration.ofHours(1)),
                new MeetingScheduler.Meeting(
                        LocalDateTime.of(2024, 3, 15, 11, 0), Duration.ofMinutes(30)));
        LocalDateTime slot = MeetingScheduler.findNextSlot(
                LocalDateTime.of(2024, 3, 15, 9, 0), existing, Duration.ofMinutes(45));
        assertEquals(LocalDateTime.of(2024, 3, 15, 10, 0), slot);
    }

    @Test
    void findNextSlotSkipsBusyPeriods() {
        var existing = List.of(
                new MeetingScheduler.Meeting(
                        LocalDateTime.of(2024, 3, 15, 9, 0), Duration.ofHours(2)));
        LocalDateTime slot = MeetingScheduler.findNextSlot(
                LocalDateTime.of(2024, 3, 15, 9, 0), existing, Duration.ofHours(1));
        assertEquals(LocalDateTime.of(2024, 3, 15, 11, 0), slot);
    }
}
