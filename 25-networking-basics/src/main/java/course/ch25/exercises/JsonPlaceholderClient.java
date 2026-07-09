package course.ch25.exercises;

import java.io.IOException;
import java.net.http.HttpClient;

/**
 * Exercise 2 (Practice): A small REST client with injectable {@link HttpClient}.
 *
 * <p>Tests use a local HTTP server — no internet connection required.
 */
public class JsonPlaceholderClient {

    private final HttpClient client;
    private final String baseUrl;

    /**
     * Creates a client with the given HTTP client and base URL.
     *
     * @param client  the HTTP client
     * @param baseUrl the API base URL
     */
    public JsonPlaceholderClient(HttpClient client, String baseUrl) {
        this.client = client;
        this.baseUrl = baseUrl;
    }

    /**
     * Fetches a post by id and returns the response body.
     *
     * @param id the post id
     * @return the JSON response body
     * @throws IOException          if the request fails
     * @throws InterruptedException if the thread is interrupted
     */
    public String fetchPost(int id) throws IOException, InterruptedException {
        // TODO: implement — GET {baseUrl}/posts/{id} with Accept: application/json
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Creates a post and returns the HTTP status code.
     *
     * @param json the JSON request body
     * @return the response status code
     * @throws IOException          if the request fails
     * @throws InterruptedException if the thread is interrupted
     */
    public int createPost(String json) throws IOException, InterruptedException {
        // TODO: implement — POST {baseUrl}/posts with Content-Type: application/json
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        var client = new JsonPlaceholderClient(HttpClient.newHttpClient(), "https://jsonplaceholder.typicode.com");
        System.out.println(client.fetchPost(1));
    }
}
