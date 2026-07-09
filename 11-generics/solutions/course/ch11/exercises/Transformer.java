package course.ch11.exercises;

import java.util.ArrayList;
import java.util.List;

@FunctionalInterface
public interface Transformer<T, R> {

    R transform(T input);

    default <V> Transformer<T, V> andThen(Transformer<R, V> after) {
        return input -> after.transform(this.transform(input));
    }

    static <T, R> List<R> applyToList(List<T> list, Transformer<T, R> transformer) {
        List<R> result = new ArrayList<>();
        for (T item : list) {
            result.add(transformer.transform(item));
        }
        return result;
    }
}
