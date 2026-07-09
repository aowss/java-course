package course.ch25.exercises;

import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RequestBuilderTest {

    @Test
    void getBuildsGetRequest() {
        var request = RequestBuilder.get("https://api.example.com/users");
        assertEquals("GET", request.method());
        assertEquals(URI.create("https://api.example.com/users"), request.uri());
    }

    @Test
    void postJsonBuildsPostWithContentType() {
        var request = RequestBuilder.postJson("https://api.example.com/users", "{\"name\":\"Ada\"}");
        assertEquals("POST", request.method());
        assertEquals("application/json", request.headers().firstValue("Content-Type").orElseThrow());
    }

    @Test
    void withBearerTokenAddsAuthorizationHeader() {
        var builder = HttpRequest.newBuilder().uri(URI.create("https://api.example.com/me"));
        var request = RequestBuilder.withBearerToken(builder, "my-token").GET().build();
        assertEquals("Bearer my-token", request.headers().firstValue("Authorization").orElseThrow());
    }
}
