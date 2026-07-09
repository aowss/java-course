package course.ch11.exercises;

import java.util.function.Function;

/**
 * Exercise 2 (Practice): A builder that chains {@code Function<T, T>} transformations.
 *
 * <p>Usage:
 * <pre>
 *   String result = new Pipeline&lt;String&gt;()
 *       .addStep(String::trim)
 *       .addStep(String::toUpperCase)
 *       .execute("  hello  ");
 *   // result = "HELLO"
 * </pre>
 *
 * @param <T> the type of value being transformed
 */
public class Pipeline<T> {

    /**
     * Adds a transformation step to the pipeline.
     *
     * @param step the transformation function
     * @return this pipeline, for method chaining
     */
    public Pipeline<T> addStep(Function<T, T> step) {
        // TODO: store the step and return this for chaining
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Applies all steps in order to the input value and returns the result.
     *
     * @param input the starting value
     * @return the result after all transformations have been applied
     */
    public T execute(T input) {
        // TODO: apply each step in sequence and return the final value
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
