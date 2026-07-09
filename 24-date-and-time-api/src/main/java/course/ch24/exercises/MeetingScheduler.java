package course.ch24.exercises;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Exercise 2 (Practice): Schedule meetings and detect overlaps.
 */
public class MeetingScheduler {

    /**
     * A scheduled meeting with a start time and duration.
     *
     * @param start    meeting start
     * @param duration meeting length
     */
    public record Meeting(LocalDateTime start, Duration duration) {

        /**
         * @return the exclusive end time of the meeting
         */
        public LocalDateTime end() {
            return start.plus(duration);
        }
    }

    /**
     * Returns {@code true} if two meetings overlap in time.
     *
     * <p>Meetings that touch at an exact boundary (one ends when the other starts)
     * do not overlap.
     *
     * @param first  the first meeting
     * @param second the second meeting
     * @return {@code true} if the meetings overlap
     */
    public static boolean hasOverlap(Meeting first, Meeting second) {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Finds the earliest start time at or after {@code after} where a new meeting
     * of the given duration would not overlap any existing meeting.
     *
     * @param after    earliest acceptable start
     * @param existing scheduled meetings (may overlap each other)
     * @param duration duration of the new meeting
     * @return the next available start time
     */
    public static LocalDateTime findNextSlot(LocalDateTime after, List<Meeting> existing, Duration duration) {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static void main(String[] args) {
        var existing = List.of(
                new Meeting(LocalDateTime.of(2024, 3, 15, 9, 0), Duration.ofHours(1)),
                new Meeting(LocalDateTime.of(2024, 3, 15, 11, 0), Duration.ofMinutes(30)));
        LocalDateTime slot = findNextSlot(
                LocalDateTime.of(2024, 3, 15, 9, 0), existing, Duration.ofMinutes(45));
        System.out.println("Next slot: " + slot);
    }
}
