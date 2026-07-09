package course.ch25.exercises;

import java.net.URI;
import java.net.http.HttpRequest;

/**
 * Exercise 1 (Guided): Build common HTTP requests.
 */
public class RequestBuilder {

    /**
     * Builds a GET request for the given URL.
     *
     * @param url the target URL
     * @return a GET request
     */
    public static HttpRequest get(String url) {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Builds a POST request with a JSON body and Content-Type header.
     *
     * @param url  the target URL
     * @param json the JSON body
     * @return a POST request
     */
    public static HttpRequest postJson(String url, String json) {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Adds a Bearer authorization header to the given builder.
     *
     * @param builder the request builder
     * @param token   the bearer token
     * @return the same builder for chaining
     */
    public static HttpRequest.Builder withBearerToken(HttpRequest.Builder builder, String token) {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static void main(String[] args) {
        System.out.println("GET:  " + get("https://api.example.com/users"));
        System.out.println("POST: " + postJson("https://api.example.com/users", "{\"name\":\"Ada\"}"));
    }
}
