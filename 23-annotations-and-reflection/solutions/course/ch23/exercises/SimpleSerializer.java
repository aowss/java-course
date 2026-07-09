package course.ch23.exercises;

import course.ch23.annotations.Serializable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SimpleSerializer {

    public static String serialize(Object target) {
        if (target == null) {
            throw new IllegalArgumentException("Target must not be null");
        }
        List<String> parts = new ArrayList<>();
        Field[] fields = target.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(Serializable.class)) {
                continue;
            }
            field.setAccessible(true);
            try {
                Object value = field.get(target);
                parts.add(field.getName() + "=" + value);
            } catch (IllegalAccessException e) {
                throw new IllegalStateException("Cannot read field: " + field.getName(), e);
            }
        }
        parts.sort(Comparator.naturalOrder());
        return String.join(",", parts);
    }

    public static void main(String[] args) {
        var book = new Book("Effective Java", "Joshua Bloch", 416);
        System.out.println(serialize(book));
    }

    public static class Book {

        @Serializable
        private final String title;

        @Serializable
        private final String author;

        private final int pages;

        public Book(String title, String author, int pages) {
            this.title = title;
            this.author = author;
            this.pages = pages;
        }
    }
}
