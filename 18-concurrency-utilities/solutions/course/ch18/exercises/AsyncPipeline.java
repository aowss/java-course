package course.ch18.exercises;

import java.util.concurrent.CompletableFuture;

public class AsyncPipeline {

    public static CompletableFuture<String> process(String input) {
        return CompletableFuture.completedFuture(input)
                .thenApply(AsyncPipeline::fetch)
                .thenApply(AsyncPipeline::transform)
                .thenApply(AsyncPipeline::validate)
                .thenApply(AsyncPipeline::format);
    }

    public static CompletableFuture<String> processWithErrorHandling(String input) {
        return CompletableFuture.completedFuture(input)
                .thenApply(AsyncPipeline::fetch)
                .thenApply(AsyncPipeline::transform)
                .thenApply(AsyncPipeline::validate)
                .thenApply(AsyncPipeline::format)
                .exceptionally(ex -> "[ERROR]");
    }

    public static String fetch(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input must not be null");
        }
        return input.toUpperCase();
    }

    public static String transform(String input) {
        return new StringBuilder(input).reverse().toString();
    }

    public static String validate(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Input must not be null or empty");
        }
        return input;
    }

    public static String format(String input) {
        return "[" + input + "]";
    }
}
