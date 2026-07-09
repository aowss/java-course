package course.ch11.exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Pipeline<T> {

    private final List<Function<T, T>> steps = new ArrayList<>();

    public Pipeline<T> addStep(Function<T, T> step) {
        steps.add(step);
        return this;
    }

    public T execute(T input) {
        T result = input;
        for (Function<T, T> step : steps) {
            result = step.apply(result);
        }
        return result;
    }
}
