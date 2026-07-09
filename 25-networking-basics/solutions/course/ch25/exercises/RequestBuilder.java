package course.ch25.exercises;

import java.net.URI;
import java.net.http.HttpRequest;

public class RequestBuilder {

    public static HttpRequest get(String url) {
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
    }

    public static HttpRequest postJson(String url, String json) {
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
    }

    public static HttpRequest.Builder withBearerToken(HttpRequest.Builder builder, String token) {
        return builder.header("Authorization", "Bearer " + token);
    }

    public static void main(String[] args) {
        System.out.println("GET:  " + get("https://api.example.com/users"));
        System.out.println("POST: " + postJson("https://api.example.com/users", "{\"name\":\"Ada\"}"));
    }
}
