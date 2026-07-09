package course.ch25.examples;

import org.junit.jupiter.api.Test;

import java.net.URI;
import java.time.Duration;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RequestBuilderDemoTest {

    @Test
    void buildPostJsonSetsContentTypeAndBody() {
        var request = RequestBuilderDemo.buildPostJson(
                URI.create("https://api.example.com/posts"),
                "{\"title\":\"Hello\"}");
        assertEquals("POST", request.method());
        Map<String, String> headers = RequestBuilderDemo.extractHeaders(request);
        assertEquals("application/json", headers.get("Content-Type"));
    }

    @Test
    void buildGetWithTimeoutSetsTimeout() {
        var request = RequestBuilderDemo.buildGetWithTimeout(
                URI.create("https://api.example.com/status"),
                Duration.ofSeconds(5));
        assertEquals(Duration.ofSeconds(5), request.timeout().orElseThrow());
        assertEquals("GET", request.method());
    }

    @Test
    void buildAuthorizedGetSetsBearerHeader() {
        var request = RequestBuilderDemo.buildAuthorizedGet(
                URI.create("https://api.example.com/me"),
                "secret-token");
        Map<String, String> headers = RequestBuilderDemo.extractHeaders(request);
        assertEquals("Bearer secret-token", headers.get("Authorization"));
    }

    @Test
    void extractHeadersReturnsAllHeaders() {
        var request = RequestBuilderDemo.buildPostJson(URI.create("https://x.com"), "{}");
        Map<String, String> headers = RequestBuilderDemo.extractHeaders(request);
        assertTrue(headers.containsKey("Content-Type"));
    }
}
