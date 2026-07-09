package course.ch24.exercises;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public class MeetingScheduler {

    public record Meeting(LocalDateTime start, Duration duration) {

        public LocalDateTime end() {
            return start.plus(duration);
        }
    }

    public static boolean hasOverlap(Meeting first, Meeting second) {
        return first.start().isBefore(second.end()) && second.start().isBefore(first.end());
    }

    public static LocalDateTime findNextSlot(LocalDateTime after, List<Meeting> existing, Duration duration) {
        LocalDateTime candidate = after;
        List<Meeting> sorted = existing.stream()
                .sorted(Comparator.comparing(Meeting::start))
                .toList();

        for (Meeting meeting : sorted) {
            Meeting candidateMeeting = new Meeting(candidate, duration);
            if (hasOverlap(candidateMeeting, meeting)) {
                candidate = meeting.end();
            }
        }
        return candidate;
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
