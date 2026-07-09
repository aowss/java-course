package course.ch25.examples;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Demonstrates the {@link HttpClient} API introduced in Java 11.
 *
 * <p>{@link HttpClient} is immutable and thread-safe. Create one instance
 * and reuse it across requests.
 */
public class HttpClientDemo {

    /**
     * Creates a default {@link HttpClient} instance.
     *
     * @return a new HTTP client with default settings
     */
    public static HttpClient createClient() {
        return HttpClient.newHttpClient();
    }

    /**
     * Builds a GET request for the given URI.
     *
     * @param uri the target URI
     * @return a built GET request with an Accept header
     */
    public static HttpRequest buildGetRequest(URI uri) {
        return HttpRequest.newBuilder()
                .uri(uri)
                .header("Accept", "text/plain")
                .GET()
                .build();
    }

    /**
     * Sends a request and returns the response status code.
     *
     * @param client  the HTTP client to use
     * @param request the request to send
     * @return the HTTP status code
     * @throws IOException          if an I/O error occurs
     * @throws InterruptedException if the operation is interrupted
     */
    public static int sendAndGetStatus(HttpClient client, HttpRequest request)
            throws IOException, InterruptedException {
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.statusCode();
    }

    /**
     * Sends a request and returns the response body as a string.
     *
     * @param client  the HTTP client to use
     * @param request the request to send
     * @return the response body
     * @throws IOException          if an I/O error occurs
     * @throws InterruptedException if the operation is interrupted
     */
    public static String sendAndGetBody(HttpClient client, HttpRequest request)
            throws IOException, InterruptedException {
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient client = createClient();
        HttpRequest request = buildGetRequest(URI.create("https://httpbin.org/get"));
        System.out.println("Status: " + sendAndGetStatus(client, request));
    }
}
