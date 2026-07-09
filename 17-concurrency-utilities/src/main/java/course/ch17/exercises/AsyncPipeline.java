package course.ch17.exercises;

import java.util.concurrent.CompletableFuture;

/**
 * Exercise 2 (Practice): Chain {@link CompletableFuture} stages to build an
 * asynchronous processing pipeline.
 *
 * <p>Pipeline stages: fetch (uppercase) → transform (reverse) → validate (non-empty)
 * → format (add brackets). Each stage is a separate static method.
 */
public class AsyncPipeline {

    /**
     * Processes the input through a chain of asynchronous stages:
     * fetch → transform → validate → format.
     *
     * @param input the input string
     * @return a future completing with the formatted result
     */
    public static CompletableFuture<String> process(String input) {
        // TODO: chain fetch, transform, validate, and format using thenApply/thenCompose
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Same as {@link #process(String)} but with error handling: if the input is null
     * or empty, the pipeline completes with {@code "[ERROR]"} instead of failing.
     *
     * @param input the input string (may be null or empty)
     * @return a future completing with the result or "[ERROR]" on failure
     */
    public static CompletableFuture<String> processWithErrorHandling(String input) {
        // TODO: same as process but use exceptionally to return "[ERROR]" on failure
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Fetch stage: converts the input to uppercase.
     *
     * @param input the raw input
     * @return the uppercase string
     */
    public static String fetch(String input) {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Transform stage: reverses the string.
     *
     * @param input the string to reverse
     * @return the reversed string
     */
    public static String transform(String input) {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Validate stage: checks that the string is non-empty.
     *
     * @param input the string to validate
     * @return the input if valid
     * @throws IllegalArgumentException if the input is null or empty
     */
    public static String validate(String input) {
        // TODO: implement — throw IllegalArgumentException if null or empty
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Format stage: wraps the string in brackets.
     *
     * @param input the string to format
     * @return the string wrapped as {@code "[input]"}
     */
    public static String format(String input) {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
