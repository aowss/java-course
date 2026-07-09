package course.ch25.support;

import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import javax.net.ssl.SSLSession;

/**
 * Test helper that provides a minimal {@link HttpResponse} implementation.
 */
public final class FakeHttpResponse implements HttpResponse<String> {

    private final int statusCode;
    private final String body;
    private final HttpRequest request;

    public FakeHttpResponse(int statusCode, String body) {
        this(statusCode, body, HttpRequest.newBuilder().uri(java.net.URI.create("https://example.com")).build());
    }

    public FakeHttpResponse(int statusCode, String body, HttpRequest request) {
        this.statusCode = statusCode;
        this.body = body;
        this.request = request;
    }

    @Override
    public int statusCode() {
        return statusCode;
    }

    @Override
    public HttpRequest request() {
        return request;
    }

    @Override
    public Optional<HttpResponse<String>> previousResponse() {
        return Optional.empty();
    }

    @Override
    public HttpHeaders headers() {
        return HttpHeaders.of(java.util.Map.of(), (a, b) -> true);
    }

    @Override
    public String body() {
        return body;
    }

    @Override
    public Optional<SSLSession> sslSession() {
        return Optional.empty();
    }

    @Override
    public java.net.URI uri() {
        return request.uri();
    }

    @Override
    public HttpClient.Version version() {
        return HttpClient.Version.HTTP_1_1;
    }
}
