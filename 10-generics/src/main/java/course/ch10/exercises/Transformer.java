package course.ch10.exercises;

import java.util.List;

/**
 * Exercise 2 (Practice): A generic functional transformer interface.
 *
 * <p>Implement a functional interface that transforms a value of type {@code T}
 * into a value of type {@code R}. Then implement:
 * <ul>
 *   <li>{@link #transform(Object)} — the abstract method</li>
 *   <li>{@link #andThen(Transformer)} — compose this transformer with another,
 *       applying this one first</li>
 *   <li>{@link #applyToList(List, Transformer)} — apply a transformer to every
 *       element in a list</li>
 * </ul>
 *
 * @param <T> the input type
 * @param <R> the output type
 */
@FunctionalInterface
public interface Transformer<T, R> {

    /**
     * Transforms the input value.
     *
     * @param input the input value
     * @return the transformed value
     */
    R transform(T input);

    /**
     * Returns a new transformer that applies this transformer first,
     * then applies {@code after} to the result.
     *
     * @param after the transformer to apply after this one
     * @param <V>   the output type of the composed transformer
     * @return the composed transformer
     */
    default <V> Transformer<T, V> andThen(Transformer<R, V> after) {
        // TODO: implement — return a lambda that applies this, then after
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Applies the given transformer to every element in the list.
     *
     * @param list        the input list
     * @param transformer the transformer to apply
     * @param <T>         the input element type
     * @param <R>         the output element type
     * @return a new list containing the transformed elements
     */
    static <T, R> List<R> applyToList(List<T> list, Transformer<T, R> transformer) {
        // TODO: implement — iterate over the list and apply the transformer
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
