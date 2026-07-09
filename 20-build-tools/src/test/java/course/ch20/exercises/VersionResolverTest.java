package course.ch20.exercises;

import course.ch20.exercises.VersionResolver.SemVer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VersionResolverTest {

    @Test
    void parsesValidVersion() {
        SemVer v = SemVer.parse("1.2.3");
        assertEquals(1, v.major());
        assertEquals(2, v.minor());
        assertEquals(3, v.patch());
    }

    @Test
    void parseThrowsOnInvalidFormat() {
        assertThrows(IllegalArgumentException.class, () -> SemVer.parse("1.2"));
    }

    @Test
    void parseThrowsOnNonNumeric() {
        assertThrows(IllegalArgumentException.class, () -> SemVer.parse("1.2.abc"));
    }

    @Test
    void compareToEqualVersions() {
        assertEquals(0, SemVer.parse("1.2.3").compareTo(SemVer.parse("1.2.3")));
    }

    @Test
    void compareToMajorDifference() {
        assertTrue(SemVer.parse("2.0.0").compareTo(SemVer.parse("1.9.9")) > 0);
    }

    @Test
    void compareToMinorDifference() {
        assertTrue(SemVer.parse("1.3.0").compareTo(SemVer.parse("1.2.9")) > 0);
    }

    @Test
    void compareToPatchDifference() {
        assertTrue(SemVer.parse("1.2.4").compareTo(SemVer.parse("1.2.3")) > 0);
    }

    @Test
    void satisfiesSimpleRange() {
        assertTrue(VersionResolver.satisfies(SemVer.parse("1.5.0"), ">=1.2.0,<2.0.0"));
    }

    @Test
    void satisfiesLowerBound() {
        assertTrue(VersionResolver.satisfies(SemVer.parse("1.2.0"), ">=1.2.0,<2.0.0"));
    }

    @Test
    void doesNotSatisfyUpperBound() {
        assertFalse(VersionResolver.satisfies(SemVer.parse("2.0.0"), ">=1.2.0,<2.0.0"));
    }

    @Test
    void doesNotSatisfyBelowRange() {
        assertFalse(VersionResolver.satisfies(SemVer.parse("1.1.9"), ">=1.2.0,<2.0.0"));
    }

    @Test
    void satisfiesExactMatch() {
        assertTrue(VersionResolver.satisfies(SemVer.parse("3.1.4"), "=3.1.4"));
    }

    @Test
    void satisfiesGreaterThan() {
        assertTrue(VersionResolver.satisfies(SemVer.parse("2.0.0"), ">1.0.0"));
    }

    @Test
    void satisfiesLessThanOrEqual() {
        assertTrue(VersionResolver.satisfies(SemVer.parse("1.0.0"), "<=1.0.0"));
    }

    @Test
    void toStringFormatsCorrectly() {
        assertEquals("1.2.3", SemVer.parse("1.2.3").toString());
    }
}
