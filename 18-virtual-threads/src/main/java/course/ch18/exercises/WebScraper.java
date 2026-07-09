package course.ch18.exercises;

import java.time.Duration;
import java.util.List;
import java.util.Map;

/**
 * Exercise 2 (Practice): Simulate fetching multiple URLs concurrently with virtual threads.
 *
 * <p>Each URL is "fetched" by returning {@code "Content of <url>"} after a small delay
 * (simulating network latency). Virtual threads make it easy to fetch many URLs concurrently.
 *
 * <pre>{@code
 * Map<String, String> results = WebScraper.fetchAll(List.of("http://a.com", "http://b.com"));
 * // results = {"http://a.com" -> "Content of http://a.com", ...}
 * }</pre>
 */
public class WebScraper {

    /**
     * Fetches all URLs concurrently using virtual threads.
     *
     * <p>Each "fetch" simulates a network call by sleeping for 10ms and returning
     * {@code "Content of <url>"}.
     *
     * @param urls the URLs to fetch
     * @return a map of url → content
     */
    public static Map<String, String> fetchAll(List<String> urls) {
        // TODO: use virtual threads to fetch all URLs concurrently
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Fetches all URLs concurrently with a per-URL timeout.
     *
     * <p>If a URL takes longer than {@code timeout} to fetch, it is excluded from the result.
     *
     * @param urls    the URLs to fetch
     * @param timeout the maximum time to wait for each URL
     * @return a map of url → content (only for URLs that completed within the timeout)
     */
    public static Map<String, String> fetchAllWithTimeout(List<String> urls, Duration timeout) {
        // TODO: same as fetchAll but skip URLs that exceed the timeout
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
