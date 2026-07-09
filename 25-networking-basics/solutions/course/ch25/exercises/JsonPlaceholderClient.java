package course.ch25.exercises;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class JsonPlaceholderClient {

    private final HttpClient client;
    private final String baseUrl;

    public JsonPlaceholderClient(HttpClient client, String baseUrl) {
        this.client = client;
        this.baseUrl = baseUrl;
    }

    public String fetchPost(int id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/posts/" + id))
                .header("Accept", "application/json")
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public int createPost(String json) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/posts"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.statusCode();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        var client = new JsonPlaceholderClient(HttpClient.newHttpClient(), "https://jsonplaceholder.typicode.com");
        System.out.println(client.fetchPost(1));
    }
}
