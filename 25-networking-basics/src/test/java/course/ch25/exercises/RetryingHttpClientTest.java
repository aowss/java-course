package course.ch25.exercises;

import course.ch25.support.FakeHttpResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RetryingHttpClientTest {

    private final HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost/test"))
            .GET()
            .build();

    @Test
    void succeedsOnFirstAttempt() throws IOException, InterruptedException {
        HttpSender sender = req -> new FakeHttpResponse(200, "ok", req);
        var client = new RetryingHttpClient(sender, 3);
        HttpResponse<String> response = client.sendWithRetry(request);
        assertEquals(200, response.statusCode());
        assertEquals("ok", response.body());
    }

    @Test
    void retriesOnIoExceptionAndEventuallySucceeds() throws IOException, InterruptedException {
        AtomicInteger attempts = new AtomicInteger();
        HttpSender sender = req -> {
            if (attempts.getAndIncrement() < 2) {
                throw new IOException("connection reset");
            }
            return new FakeHttpResponse(200, "recovered", req);
        };
        var client = new RetryingHttpClient(sender, 3);
        HttpResponse<String> response = client.sendWithRetry(request);
        assertEquals(200, response.statusCode());
        assertEquals(3, attempts.get());
    }

    @Test
    void retriesOnServerError() throws IOException, InterruptedException {
        AtomicInteger attempts = new AtomicInteger();
        HttpSender sender = req -> {
            if (attempts.getAndIncrement() < 1) {
                return new FakeHttpResponse(503, "unavailable", req);
            }
            return new FakeHttpResponse(200, "ok", req);
        };
        var client = new RetryingHttpClient(sender, 2);
        HttpResponse<String> response = client.sendWithRetry(request);
        assertEquals(200, response.statusCode());
        assertEquals(2, attempts.get());
    }

    @Test
    void throwsAfterExhaustingRetries() {
        HttpSender sender = req -> {
            throw new IOException("always fails");
        };
        var client = new RetryingHttpClient(sender, 2);
        assertThrows(IOException.class, () -> client.sendWithRetry(request));
    }

    @Test
    void getMaxRetriesReturnsConfiguredValue() {
        var client = new RetryingHttpClient(req -> new FakeHttpResponse(200, "", req), 5);
        assertEquals(5, client.getMaxRetries());
    }
}
