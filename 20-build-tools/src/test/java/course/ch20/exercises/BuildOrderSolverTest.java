package course.ch20.exercises;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BuildOrderSolverTest {

    @Test
    void singleModuleNoDependencies() {
        List<String> order = BuildOrderSolver.solve(Map.of("app", Set.of()));
        assertEquals(List.of("app"), order);
    }

    @Test
    void linearDependencyChain() {
        Map<String, Set<String>> deps = Map.of(
                "app", Set.of("service"),
                "service", Set.of("model"),
                "model", Set.of()
        );
        List<String> order = BuildOrderSolver.solve(deps);
        assertTrue(order.indexOf("model") < order.indexOf("service"));
        assertTrue(order.indexOf("service") < order.indexOf("app"));
    }

    @Test
    void diamondDependency() {
        Map<String, Set<String>> deps = Map.of(
                "app", Set.of("web", "data"),
                "web", Set.of("core"),
                "data", Set.of("core"),
                "core", Set.of()
        );
        List<String> order = BuildOrderSolver.solve(deps);
        assertEquals(4, order.size());
        assertTrue(order.indexOf("core") < order.indexOf("web"));
        assertTrue(order.indexOf("core") < order.indexOf("data"));
        assertTrue(order.indexOf("web") < order.indexOf("app"));
        assertTrue(order.indexOf("data") < order.indexOf("app"));
    }

    @Test
    void throwsOnCircularDependency() {
        Map<String, Set<String>> deps = Map.of(
                "a", Set.of("b"),
                "b", Set.of("c"),
                "c", Set.of("a")
        );
        assertThrows(IllegalStateException.class, () -> BuildOrderSolver.solve(deps));
    }

    @Test
    void throwsOnNullInput() {
        assertThrows(IllegalArgumentException.class, () -> BuildOrderSolver.solve(null));
    }

    @Test
    void multipleIndependentModules() {
        Map<String, Set<String>> deps = Map.of(
                "alpha", Set.of(),
                "beta", Set.of(),
                "gamma", Set.of()
        );
        List<String> order = BuildOrderSolver.solve(deps);
        assertEquals(3, order.size());
        assertTrue(order.containsAll(List.of("alpha", "beta", "gamma")));
    }
}
