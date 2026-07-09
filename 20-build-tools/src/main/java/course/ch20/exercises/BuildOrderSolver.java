package course.ch20.exercises;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Exercise 3 (Challenge): Compute build order via topological sort.
 *
 * <p>Given a map where each key is a module name and the value is the set
 * of modules it depends on, compute a valid build order such that every
 * module is built after all of its dependencies.
 *
 * <p>If a circular dependency is detected, throw an {@link IllegalStateException}.
 */
public class BuildOrderSolver {

    /**
     * Computes a valid build order for the given dependency graph.
     *
     * @param dependencies a map from module name to its set of dependencies
     * @return an ordered list of module names (build order)
     * @throws IllegalStateException if circular dependencies are detected
     * @throws IllegalArgumentException if dependencies is null
     */
    public static List<String> solve(Map<String, Set<String>> dependencies) {
        // TODO: implement this method
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
