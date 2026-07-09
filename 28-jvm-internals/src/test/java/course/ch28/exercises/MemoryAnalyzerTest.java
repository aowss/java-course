package course.ch28.exercises;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MemoryAnalyzerTest {

    @Test
    void heapReportContainsMbValues() {
        String report = MemoryAnalyzer.heapReport();
        assertNotNull(report);
        assertTrue(report.contains("used="));
        assertTrue(report.contains("total="));
        assertTrue(report.contains("max="));
        assertTrue(report.contains("% of max"));
    }

    @Test
    void usedPercentageOfMaxIsInValidRange() {
        double pct = MemoryAnalyzer.usedPercentageOfMax();
        assertTrue(pct >= 0.0);
        assertTrue(pct <= 100.0);
    }

    @Test
    void isBelowThresholdWithHighThreshold() {
        assertTrue(MemoryAnalyzer.isBelowThreshold(100.0));
    }

    @Test
    void isBelowThresholdWithZeroThreshold() {
        assertFalse(MemoryAnalyzer.isBelowThreshold(0.0));
    }
}
