package course.ch20.exercises;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class BuildOrderSolver {

    public static List<String> solve(Map<String, Set<String>> dependencies) {
        if (dependencies == null) {
            throw new IllegalArgumentException("Dependencies must not be null");
        }

        Map<String, Integer> inDegree = new HashMap<>();
        Map<String, List<String>> adjacency = new HashMap<>();

        for (String module : dependencies.keySet()) {
            inDegree.putIfAbsent(module, 0);
            adjacency.putIfAbsent(module, new ArrayList<>());
        }

        for (var entry : dependencies.entrySet()) {
            String module = entry.getKey();
            for (String dep : entry.getValue()) {
                inDegree.putIfAbsent(dep, 0);
                adjacency.putIfAbsent(dep, new ArrayList<>());
                adjacency.get(dep).add(module);
                inDegree.merge(module, 1, Integer::sum);
            }
        }

        Queue<String> queue = new ArrayDeque<>();
        for (var entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.add(entry.getKey());
            }
        }

        List<String> order = new ArrayList<>();
        while (!queue.isEmpty()) {
            String current = queue.poll();
            order.add(current);
            for (String dependent : adjacency.get(current)) {
                int newDegree = inDegree.merge(dependent, -1, Integer::sum);
                if (newDegree == 0) {
                    queue.add(dependent);
                }
            }
        }

        if (order.size() != inDegree.size()) {
            throw new IllegalStateException("Circular dependency detected");
        }

        return order;
    }
}
