package course.ch25.examples;

import java.net.URI;
import java.net.http.HttpRequest;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Demonstrates building {@link HttpRequest} objects with headers, JSON bodies,
 * and timeouts.
 */
public class RequestBuilderDemo {

    /**
     * Builds a POST request with a JSON body and Content-Type header.
     *
     * @param uri  the target URI
     * @param json the JSON request body
     * @return a built POST request
     */
    public static HttpRequest buildPostJson(URI uri, String json) {
        return HttpRequest.newBuilder()
                .uri(uri)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
    }

    /**
     * Builds a GET request with a connection timeout.
     *
     * @param uri     the target URI
     * @param timeout the maximum time to wait for a connection
     * @return a built GET request with a timeout
     */
    public static HttpRequest buildGetWithTimeout(URI uri, Duration timeout) {
        return HttpRequest.newBuilder()
                .uri(uri)
                .timeout(timeout)
                .GET()
                .build();
    }

    /**
     * Builds a GET request with a Bearer authorization header.
     *
     * @param uri   the target URI
     * @param token the bearer token
     * @return a built GET request with Authorization header
     */
    public static HttpRequest buildAuthorizedGet(URI uri, String token) {
        return HttpRequest.newBuilder()
                .uri(uri)
                .header("Authorization", "Bearer " + token)
                .GET()
                .build();
    }

    /**
     * Extracts request headers into a map (first value per header name).
     *
     * @param request the HTTP request
     * @return a map of header name to value
     */
    public static Map<String, String> extractHeaders(HttpRequest request) {
        Map<String, String> headers = new LinkedHashMap<>();
        request.headers().map().forEach((name, values) -> {
            if (!values.isEmpty()) {
                headers.put(name, values.getFirst());
            }
        });
        return headers;
    }

    public static void main(String[] args) {
        var uri = URI.create("https://api.example.com/posts");
        var post = buildPostJson(uri, "{\"title\":\"Hello\"}");
        System.out.println("POST headers: " + extractHeaders(post));
        System.out.println("Auth headers: " + extractHeaders(buildAuthorizedGet(uri, "secret-token")));
    }
}
