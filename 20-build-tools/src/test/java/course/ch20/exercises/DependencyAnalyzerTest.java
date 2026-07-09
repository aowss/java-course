package course.ch20.exercises;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DependencyAnalyzerTest {

    @Test
    void parsesSingleDependency() {
        String xml = """
                <dependencies>
                    <dependency>
                        <groupId>org.junit.jupiter</groupId>
                        <artifactId>junit-jupiter</artifactId>
                        <version>5.11.0</version>
                        <scope>test</scope>
                    </dependency>
                </dependencies>
                """;
        List<DependencyAnalyzer.Dependency> deps = DependencyAnalyzer.parse(xml);
        assertEquals(1, deps.size());
        var dep = deps.getFirst();
        assertEquals("org.junit.jupiter", dep.groupId());
        assertEquals("junit-jupiter", dep.artifactId());
        assertEquals("5.11.0", dep.version());
        assertEquals("test", dep.scope());
    }

    @Test
    void parsesMultipleDependencies() {
        String xml = """
                <dependencies>
                    <dependency>
                        <groupId>com.google.guava</groupId>
                        <artifactId>guava</artifactId>
                        <version>33.0.0-jre</version>
                    </dependency>
                    <dependency>
                        <groupId>org.slf4j</groupId>
                        <artifactId>slf4j-api</artifactId>
                        <version>2.0.9</version>
                        <scope>compile</scope>
                    </dependency>
                </dependencies>
                """;
        List<DependencyAnalyzer.Dependency> deps = DependencyAnalyzer.parse(xml);
        assertEquals(2, deps.size());
    }

    @Test
    void defaultsScopeToCompile() {
        String xml = """
                <dependencies>
                    <dependency>
                        <groupId>com.example</groupId>
                        <artifactId>my-lib</artifactId>
                        <version>1.0.0</version>
                    </dependency>
                </dependencies>
                """;
        List<DependencyAnalyzer.Dependency> deps = DependencyAnalyzer.parse(xml);
        assertEquals("compile", deps.getFirst().scope());
    }

    @Test
    void emptyDependenciesReturnsEmptyList() {
        String xml = "<dependencies></dependencies>";
        List<DependencyAnalyzer.Dependency> deps = DependencyAnalyzer.parse(xml);
        assertTrue(deps.isEmpty());
    }

    @Test
    void throwsOnNull() {
        assertThrows(IllegalArgumentException.class, () -> DependencyAnalyzer.parse(null));
    }
}
