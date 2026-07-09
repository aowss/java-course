package course.ch25.examples;

import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class HttpClientDemoTest {

    private HttpServer server;
    private String baseUrl;

    @BeforeEach
    void setUp() throws IOException {
        server = HttpServer.create(new InetSocketAddress(0), 0);
        server.createContext("/hello", exchange -> {
            byte[] body = "Hello, HTTP!".getBytes();
            exchange.sendResponseHeaders(200, body.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(body);
            }
        });
        server.start();
        int port = server.getAddress().getPort();
        baseUrl = "http://localhost:" + port;
    }

    @AfterEach
    void tearDown() {
        if (server != null) {
            server.stop(0);
        }
    }

    @Test
    void createClientReturnsNonNullClient() {
        var client = HttpClientDemo.createClient();
        assertNotNull(client);
    }

    @Test
    void buildGetRequestHasCorrectUri() {
        var request = HttpClientDemo.buildGetRequest(URI.create(baseUrl + "/hello"));
        assertEquals(URI.create(baseUrl + "/hello"), request.uri());
        assertEquals("GET", request.method());
    }

    @Test
    void sendAndGetStatusReturns200() throws IOException, InterruptedException {
        var client = HttpClientDemo.createClient();
        var request = HttpClientDemo.buildGetRequest(URI.create(baseUrl + "/hello"));
        assertEquals(200, HttpClientDemo.sendAndGetStatus(client, request));
    }

    @Test
    void sendAndGetBodyReturnsResponseBody() throws IOException, InterruptedException {
        var client = HttpClientDemo.createClient();
        var request = HttpClientDemo.buildGetRequest(URI.create(baseUrl + "/hello"));
        assertEquals("Hello, HTTP!", HttpClientDemo.sendAndGetBody(client, request));
    }
}
