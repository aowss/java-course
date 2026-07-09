package course.ch23.exercises;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ObjectInspector {

    public static String inspect(Object target) {
        if (target == null) {
            throw new IllegalArgumentException("Target must not be null");
        }
        List<String> fields = getFieldNames(target);
        List<String> methods = getMethodNames(target);
        return "Class: " + target.getClass().getSimpleName() + "\n"
                + "Fields: " + String.join(", ", fields) + "\n"
                + "Methods: " + String.join(", ", methods);
    }

    public static List<String> getFieldNames(Object target) {
        if (target == null) {
            throw new IllegalArgumentException("Target must not be null");
        }
        return Arrays.stream(target.getClass().getDeclaredFields())
                .filter(field -> !field.isSynthetic())
                .map(Field::getName)
                .sorted()
                .toList();
    }

    public static List<String> getMethodNames(Object target) {
        if (target == null) {
            throw new IllegalArgumentException("Target must not be null");
        }
        return Arrays.stream(target.getClass().getDeclaredMethods())
                .filter(method -> !method.isSynthetic())
                .map(Method::getName)
                .sorted()
                .distinct()
                .toList();
    }

    public static void main(String[] args) {
        System.out.println(inspect("Hello, reflection!"));
    }
}
