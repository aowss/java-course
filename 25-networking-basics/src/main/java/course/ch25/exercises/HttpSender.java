package course.ch25.exercises;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Functional interface for sending HTTP requests (enables testing without network).
 */
@FunctionalInterface
public interface HttpSender {

    /**
     * Sends an HTTP request and returns the response.
     *
     * @param request the request to send
     * @return the HTTP response
     * @throws IOException          if an I/O error occurs
     * @throws InterruptedException if the operation is interrupted
     */
    HttpResponse<String> send(HttpRequest request) throws IOException, InterruptedException;
}
