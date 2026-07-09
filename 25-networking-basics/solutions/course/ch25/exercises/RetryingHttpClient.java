package course.ch25.exercises;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RetryingHttpClient {

    private final HttpSender sender;
    private final int maxRetries;

    public RetryingHttpClient(HttpSender sender, int maxRetries) {
        this.sender = sender;
        this.maxRetries = maxRetries;
    }

    public HttpResponse<String> sendWithRetry(HttpRequest request)
            throws IOException, InterruptedException {
        IOException lastIo = null;
        for (int attempt = 0; attempt <= maxRetries; attempt++) {
            try {
                HttpResponse<String> response = sender.send(request);
                if (response.statusCode() < 500) {
                    return response;
                }
            } catch (IOException e) {
                lastIo = e;
            }
        }
        if (lastIo != null) {
            throw lastIo;
        }
        throw new IOException("All retry attempts exhausted");
    }

    public int getMaxRetries() {
        return maxRetries;
    }
}
