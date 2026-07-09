package course.ch13.exercises;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StringFilters {

    public static Predicate<String> nonEmpty() {
        return s -> s != null && !s.isEmpty();
    }

    public static Predicate<String> startsWith(String prefix) {
        return s -> s != null && s.startsWith(prefix);
    }

    public static Predicate<String> longerThan(int n) {
        return s -> s != null && s.length() > n;
    }

    public static List<String> filter(List<String> items, Predicate<String> predicate) {
        return items.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
}
