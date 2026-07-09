package course.ch06.exercises;

@FunctionalInterface
public interface Validator<T> {

    boolean validate(T value);

    default String errorMessage(T value) {
        return "Invalid value: " + value;
    }

    static <T> Validator<T> nonNull() {
        return value -> value != null;
    }

    static Validator<String> nonBlank() {
        return value -> value != null && !value.isBlank();
    }

    static Validator<String> lengthBetween(int min, int max) {
        return value -> value != null && value.length() >= min && value.length() <= max;
    }

    default Validator<T> and(Validator<T> other) {
        return value -> this.validate(value) && other.validate(value);
    }

    public static void main(String[] args) {
        Validator<String> username = nonBlank().and(lengthBetween(3, 20));
        System.out.println("Valid 'alice': " + username.validate("alice"));
        System.out.println("Valid '':      " + username.validate(""));
    }
}
