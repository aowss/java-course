package course.ch23.examples;

import course.ch23.annotations.NotNull;
import course.ch23.annotations.Range;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * Demonstrates custom annotations and runtime validation via reflection.
 */
public class CustomAnnotationDemo {

    /**
     * Validates that a non-null argument is present.
     *
     * @param value the value to check
     * @throws IllegalArgumentException if {@code value} is {@code null}
     */
    public static void validateNotNull(@NotNull Object value) {
        if (value == null) {
            throw new IllegalArgumentException("Value must not be null");
        }
    }

    /**
     * Validates that an integer falls within the given inclusive range.
     *
     * @param value the value to check
     * @param min   the minimum allowed value
     * @param max   the maximum allowed value
     * @throws IllegalArgumentException if {@code value} is outside the range
     */
    public static void validateRange(@Range(min = 1, max = 100) int value, int min, int max) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(
                    "Value " + value + " is outside range [" + min + ", " + max + "]");
        }
    }

    /**
     * Validates all {@link NotNull} and {@link Range} constraints on the fields
     * of the given object.
     *
     * @param target the object to validate
     * @throws IllegalArgumentException if a constraint is violated
     */
    public static void validateObject(Object target) {
        Class<?> type = target.getClass();
        for (Field field : type.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(NotNull.class)) {
                try {
                    Object value = field.get(target);
                    if (value == null) {
                        throw new IllegalArgumentException(field.getName() + " must not be null");
                    }
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException("Cannot access field: " + field.getName(), e);
                }
            }
            if (field.isAnnotationPresent(Range.class)) {
                Range range = field.getAnnotation(Range.class);
                try {
                    int value = field.getInt(target);
                    validateRange(value, range.min(), range.max());
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException("Cannot access field: " + field.getName(), e);
                }
            }
        }
    }

    /**
     * Returns the names of parameters annotated with {@link NotNull} on the given method.
     *
     * @param method the method to inspect
     * @return parameter names marked {@code @NotNull}
     */
    public static String[] findNotNullParameters(Method method) {
        Parameter[] parameters = method.getParameters();
        return java.util.Arrays.stream(parameters)
                .filter(parameter -> parameter.isAnnotationPresent(NotNull.class))
                .map(Parameter::getName)
                .toArray(String[]::new);
    }

    /**
     * Sample profile used in validation demos.
     */
    public static class UserProfile {

        @NotNull
        private String username;

        @Range(min = 18, max = 120)
        private int age;

        public UserProfile(String username, int age) {
            this.username = username;
            this.age = age;
        }
    }

    public static void main(String[] args) throws NoSuchMethodException {
        validateNotNull("Java");
        validateRange(42, 1, 100);

        UserProfile valid = new UserProfile("alice", 30);
        validateObject(valid);
        System.out.println("Valid profile accepted: " + valid.username);

        Method method = CustomAnnotationDemo.class.getMethod("validateNotNull", Object.class);
        System.out.println("NotNull parameters: " + String.join(", ", findNotNullParameters(method)));
    }
}
