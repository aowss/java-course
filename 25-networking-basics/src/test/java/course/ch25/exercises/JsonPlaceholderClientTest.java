package course.ch25.exercises;

import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.http.HttpClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JsonPlaceholderClientTest {

    private HttpServer server;
    private JsonPlaceholderClient client;

    @BeforeEach
    void setUp() throws IOException {
        server = HttpServer.create(new InetSocketAddress(0), 0);
        server.createContext("/posts/1", exchange -> {
            byte[] body = "{\"id\":1,\"title\":\"Test Post\"}".getBytes();
            exchange.getResponseHeaders().add("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, body.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(body);
            }
        });
        server.createContext("/posts", exchange -> {
            if ("POST".equals(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(201, 0);
                exchange.close();
            } else {
                exchange.sendResponseHeaders(405, 0);
                exchange.close();
            }
        });
        server.start();
        int port = server.getAddress().getPort();
        client = new JsonPlaceholderClient(HttpClient.newHttpClient(), "http://localhost:" + port);
    }

    @AfterEach
    void tearDown() {
        if (server != null) {
            server.stop(0);
        }
    }

    @Test
    void fetchPostReturnsJsonBody() throws IOException, InterruptedException {
        String body = client.fetchPost(1);
        assertTrue(body.contains("\"id\":1"));
        assertTrue(body.contains("Test Post"));
    }

    @Test
    void createPostReturns201() throws IOException, InterruptedException {
        assertEquals(201, client.createPost("{\"title\":\"New\"}"));
    }
}
