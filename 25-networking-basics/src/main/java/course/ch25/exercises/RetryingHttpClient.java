package course.ch25.exercises;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Exercise 3 (Challenge): HTTP client wrapper that retries failed requests.
 */
public class RetryingHttpClient {

    private final HttpSender sender;
    private final int maxRetries;

    /**
     * Creates a retrying client.
     *
     * @param sender     the HTTP sender to delegate to
     * @param maxRetries the maximum number of retry attempts after the first failure
     */
    public RetryingHttpClient(HttpSender sender, int maxRetries) {
        this.sender = sender;
        this.maxRetries = maxRetries;
    }

    /**
     * Sends a request, retrying on I/O failure up to {@code maxRetries} times.
     *
     * <p>Returns immediately on the first successful response (status code &lt; 500).
     * Retries when an {@link IOException} is thrown or the status code is 500+.
     *
     * @param request the request to send
     * @return the successful HTTP response
     * @throws IOException          if all attempts fail with I/O errors
     * @throws InterruptedException if the operation is interrupted
     */
    public HttpResponse<String> sendWithRetry(HttpRequest request)
            throws IOException, InterruptedException {
        // TODO: implement — loop up to maxRetries + 1 attempts
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns the configured maximum number of retries.
     *
     * @return the max retry count
     */
    public int getMaxRetries() {
        return maxRetries;
    }
}
