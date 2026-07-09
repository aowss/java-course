package course.ch20.examples;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MavenLifecycleDemoTest {

    @Test
    void defaultLifecycleHasSevenPhases() {
        assertEquals(7, MavenLifecycleDemo.DEFAULT_LIFECYCLE.size());
    }

    @Test
    void phasesUpToCompileIncludesValidateAndCompile() {
        List<MavenLifecycleDemo.Phase> phases = MavenLifecycleDemo.phasesUpTo("compile");
        assertEquals(2, phases.size());
        assertEquals("validate", phases.get(0).name());
        assertEquals("compile", phases.get(1).name());
    }

    @Test
    void phasesUpToTestIncludesThreePhases() {
        List<MavenLifecycleDemo.Phase> phases = MavenLifecycleDemo.phasesUpTo("test");
        assertEquals(3, phases.size());
        assertEquals("test", phases.getLast().name());
    }

    @Test
    void phasesUpToDeployIncludesAllPhases() {
        List<MavenLifecycleDemo.Phase> phases = MavenLifecycleDemo.phasesUpTo("deploy");
        assertEquals(7, phases.size());
    }

    @Test
    void phasesUpToUnknownReturnsEmpty() {
        List<MavenLifecycleDemo.Phase> phases = MavenLifecycleDemo.phasesUpTo("nonexistent");
        assertTrue(phases.isEmpty());
    }

    @Test
    void describePhaseReturnsDescription() {
        String desc = MavenLifecycleDemo.describePhase("compile");
        assertEquals("Compile the source code of the project", desc);
    }

    @Test
    void describePhaseReturnsUnknownForBadPhase() {
        String desc = MavenLifecycleDemo.describePhase("foo");
        assertTrue(desc.startsWith("Unknown phase:"));
    }

    @Test
    void cleanLifecycleHasThreePhases() {
        assertEquals(3, MavenLifecycleDemo.CLEAN_LIFECYCLE.size());
        assertEquals("clean", MavenLifecycleDemo.CLEAN_LIFECYCLE.get(1).name());
    }
}
