package course.ch07.exercises;

public sealed interface Result<T> permits Result.Success, Result.Failure {

    record Success<T>(T value) implements Result<T> {
    }

    record Failure<T>(String message) implements Result<T> {
    }

    static <T> Result<T> ok(T value) {
        return new Success<>(value);
    }

    static <T> Result<T> fail(String message) {
        return new Failure<>(message);
    }

    default T getOrElse(T defaultValue) {
        return switch (this) {
            case Success<T>(var value) -> value;
            case Failure<T> ignored -> defaultValue;
        };
    }

    default <R> Result<R> map(java.util.function.Function<T, R> mapper) {
        return switch (this) {
            case Success<T>(var value) -> Result.ok(mapper.apply(value));
            case Failure<T>(var message) -> Result.fail(message);
        };
    }

    default String describe() {
        return switch (this) {
            case Success<T>(var value) -> "Success: " + value;
            case Failure<T>(var message) -> "Failure: " + message;
        };
    }

    public static void main(String[] args) {
        Result<Integer> result = Result.ok(42);
        System.out.println(result.describe());
        System.out.println("Value: " + result.getOrElse(0));
    }
}
