package course.ch18.exercises;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Timeout(10)
class WebScraperTest {

    @Test
    void fetchAllReturnsSingleUrl() {
        Map<String, String> results = WebScraper.fetchAll(List.of("http://example.com"));
        assertEquals(1, results.size());
        assertEquals("Content of http://example.com", results.get("http://example.com"));
    }

    @Test
    void fetchAllReturnsMultipleUrls() {
        List<String> urls = List.of("http://a.com", "http://b.com", "http://c.com");
        Map<String, String> results = WebScraper.fetchAll(urls);
        assertEquals(3, results.size());
        for (String url : urls) {
            assertEquals("Content of " + url, results.get(url));
        }
    }

    @Test
    void fetchAllEmptyList() {
        Map<String, String> results = WebScraper.fetchAll(List.of());
        assertTrue(results.isEmpty());
    }

    @Test
    void fetchAllWithTimeoutReturnsResults() {
        List<String> urls = List.of("http://fast.com");
        Map<String, String> results = WebScraper.fetchAllWithTimeout(urls, Duration.ofSeconds(5));
        assertEquals(1, results.size());
        assertEquals("Content of http://fast.com", results.get("http://fast.com"));
    }

    @Test
    void fetchAllIsConcurrent() {
        List<String> urls = new java.util.ArrayList<>();
        for (int i = 0; i < 20; i++) {
            urls.add("http://site" + i + ".com");
        }
        long start = System.currentTimeMillis();
        Map<String, String> results = WebScraper.fetchAll(urls);
        long elapsed = System.currentTimeMillis() - start;
        assertEquals(20, results.size());
        assertTrue(elapsed < 5000, "Should complete quickly with concurrent virtual threads");
    }
}
