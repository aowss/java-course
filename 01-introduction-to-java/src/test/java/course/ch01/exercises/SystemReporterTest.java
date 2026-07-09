package course.ch01.exercises;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SystemReporterTest {

    @Test
    void reportContainsExpectedFormat() {
        String expected = "Java " + System.getProperty("java.version")
                + " on " + System.getProperty("os.name")
                + " (" + System.getProperty("os.arch") + ")";
        assertEquals(expected, SystemReporter.report());
    }

    @Test
    void reportIsNotNull() {
        var report = SystemReporter.report();
        assertEquals(false, report == null, "report() must not return null");
    }

    @Test
    void reportStartsWithJava() {
        var report = SystemReporter.report();
        assertEquals(true, report.startsWith("Java "),
                "report() must start with 'Java '");
    }
}
