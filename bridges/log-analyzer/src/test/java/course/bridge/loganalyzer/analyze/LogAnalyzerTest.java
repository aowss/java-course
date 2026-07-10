package course.bridge.loganalyzer.analyze;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import course.bridge.loganalyzer.model.LogLevel;

class LogAnalyzerTest {

    private final LogAnalyzer analyzer = new LogAnalyzer();

    private Path sampleLog() throws Exception {
        return Path.of(getClass().getResource("/sample.log").toURI());
    }

    @Test
    void countByLevel() throws Exception {
        Map<LogLevel, Long> counts = analyzer.countByLevel(sampleLog());

        assertEquals(3L, counts.get(LogLevel.INFO));
        assertEquals(3L, counts.get(LogLevel.ERROR));
        assertEquals(1L, counts.get(LogLevel.WARN));
        assertEquals(1L, counts.get(LogLevel.DEBUG));
    }

    @Test
    void errorsOnly() throws Exception {
        List<String> messages = analyzer.errorsOnly(sampleLog()).stream()
                .map(entry -> entry.message())
                .toList();

        assertEquals(3, messages.size());
        assertTrue(messages.stream().allMatch(m -> m.equals("Failed to connect")));
    }

    @Test
    void topMessages() throws Exception {
        List<String> top = analyzer.topMessages(sampleLog(), 2);

        assertEquals(2, top.size());
        assertEquals("Failed to connect", top.getFirst());
        assertEquals("User logged in", top.get(1));
    }
}
