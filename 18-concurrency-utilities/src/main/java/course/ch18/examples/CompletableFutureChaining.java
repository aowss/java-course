package course.ch18.examples;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

/**
 * Demonstrates {@link CompletableFuture} chaining with {@code thenApply},
 * {@code thenCompose}, {@code thenCombine}, and error handling with {@code exceptionally}.
 *
 * <p>Each method builds and returns a completed or executing future so that callers
 * can inspect the result for testing.
 */
public class CompletableFutureChaining {

    /**
     * Chains transformations using {@code thenApply}: converts input to uppercase,
     * then appends its length.
     *
     * @param input the input string
     * @return a future that completes with the transformed result
     */
    public static CompletableFuture<String> chainWithThenApply(String input) {
        return CompletableFuture.completedFuture(input)
                .thenApply(String::toUpperCase)
                .thenApply(s -> s + ":" + s.length());
    }

    /**
     * Chains asynchronous operations using {@code thenCompose}: simulates fetching
     * a value and then performing an async transformation on it.
     *
     * @param input the input string
     * @return a future that completes with the composed result
     */
    public static CompletableFuture<String> chainWithThenCompose(String input) {
        return CompletableFuture.completedFuture(input)
                .thenCompose(s -> asyncUpperCase(s))
                .thenCompose(s -> asyncAppendExclamation(s));
    }

    /**
     * Combines two independent futures using {@code thenCombine}: computes the
     * length of one string and reverses another, then joins them.
     *
     * @param first  the first input
     * @param second the second input
     * @return a future combining both results
     */
    public static CompletableFuture<String> combineResults(String first, String second) {
        CompletableFuture<Integer> lengthFuture = CompletableFuture.completedFuture(first)
                .thenApply(String::length);
        CompletableFuture<String> reversedFuture = CompletableFuture.completedFuture(second)
                .thenApply(s -> new StringBuilder(s).reverse().toString());

        return lengthFuture.thenCombine(reversedFuture,
                (length, reversed) -> length + "-" + reversed);
    }

    /**
     * Demonstrates error handling with {@code exceptionally}: if the input is null or empty,
     * the chain throws an exception that is caught and replaced with a fallback value.
     *
     * @param input the input string (may be null or empty)
     * @return a future that completes with the result or a fallback on error
     */
    public static CompletableFuture<String> withErrorHandling(String input) {
        return CompletableFuture.completedFuture(input)
                .thenApply(s -> {
                    if (s == null || s.isEmpty()) {
                        throw new CompletionException(
                                new IllegalArgumentException("Input must not be null or empty"));
                    }
                    return s.toUpperCase();
                })
                .exceptionally(ex -> "FALLBACK");
    }

    private static CompletableFuture<String> asyncUpperCase(String input) {
        return CompletableFuture.completedFuture(input.toUpperCase());
    }

    private static CompletableFuture<String> asyncAppendExclamation(String input) {
        return CompletableFuture.completedFuture(input + "!");
    }
}
