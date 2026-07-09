# Chapter 25: Networking Basics

## Objectives

- Understand the basics of TCP sockets and HTTP
- Use the modern `java.net.http.HttpClient` API
- Build HTTP requests with headers, bodies, and timeouts
- Make REST calls and handle responses
- Design network code that is testable without hitting the real internet

## Concepts

### Sockets and HTTP

At the transport layer, networking in Java starts with **sockets** — endpoints for sending and receiving data over TCP:

```java
try (ServerSocket server = new ServerSocket(8080)) {
    Socket client = server.accept();
    // read/write streams on client.getInputStream()
}
```

HTTP is an application-layer protocol built on TCP. Modern Java code rarely uses raw sockets for HTTP — instead, use the **`HttpClient`** API (Java 11+).

### HttpClient

`HttpClient` is immutable and thread-safe. Create one instance and reuse it:

```java
HttpClient client = HttpClient.newBuilder()
        .connectTimeout(Duration.ofSeconds(10))
        .followRedirects(HttpClient.Redirect.NORMAL)
        .build();
```

| Method / Setting        | Description                              |
|-------------------------|------------------------------------------|
| `newHttpClient()`       | Default client with HTTP/2 support       |
| `connectTimeout`        | Max time to establish a connection       |
| `followRedirects`       | Whether to follow 3xx redirects          |
| `version(HTTP_2)`       | Prefer HTTP/2                            |
| `authenticator`         | Supply credentials for HTTP auth         |

### HttpRequest

Build requests with the builder pattern:

```java
HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://api.example.com/users"))
        .header("Accept", "application/json")
        .header("Authorization", "Bearer " + token)
        .timeout(Duration.ofSeconds(30))
        .GET()
        .build();
```

| Method   | Body publisher                        | Use case                |
|----------|---------------------------------------|-------------------------|
| `GET()`  | None                                  | Fetch a resource        |
| `POST(body)` | `BodyPublishers.ofString(json)` | Create a resource       |
| `PUT(body)`  | `BodyPublishers.ofString(json)` | Replace a resource      |
| `DELETE()`   | None                              | Remove a resource       |

### HttpResponse

Send a request synchronously:

```java
HttpResponse<String> response = client.send(
        request,
        HttpResponse.BodyHandlers.ofString());

int status = response.statusCode();
String body = response.body();
Map<String, List<String>> headers = response.headers().map();
```

Common status codes:

| Code | Meaning       | Typical action              |
|------|---------------|-----------------------------|
| 200  | OK            | Process the response body   |
| 201  | Created       | Resource created (POST)     |
| 400  | Bad Request   | Fix the request payload     |
| 401  | Unauthorized  | Add or refresh credentials  |
| 404  | Not Found     | Check the URL or resource id|
| 500  | Server Error  | Retry or report upstream    |

### Async Requests

`HttpClient` also supports non-blocking sends:

```java
CompletableFuture<HttpResponse<String>> future = client.sendAsync(
        request,
        HttpResponse.BodyHandlers.ofString());

future.thenApply(HttpResponse::body)
      .thenAccept(System.out::println);
```

### Testing Without the Real Network

Production code should accept injectable dependencies so tests can use a **local stub server** or a mock sender:

```java
// Injectable client + base URL
var client = new JsonPlaceholderClient(HttpClient.newHttpClient(), baseUrl);

// Functional interface for retry logic
public interface HttpSender {
    HttpResponse<String> send(HttpRequest request) throws IOException, InterruptedException;
}
```

In tests, use `com.sun.net.httpserver.HttpServer` on a random port or a stub `HttpSender` that returns canned responses.

## Examples

| File                                                                                    | Demonstrates                                      |
|-----------------------------------------------------------------------------------------|---------------------------------------------------|
| [`HttpClientDemo.java`](src/main/java/course/ch25/examples/HttpClientDemo.java)         | Creating clients, sending GET requests          |
| [`RequestBuilderDemo.java`](src/main/java/course/ch25/examples/RequestBuilderDemo.java) | POST with JSON, timeouts, Authorization headers   |

## Exercises

### Exercise 1: Request Builder (Guided)

**File:** [`RequestBuilder.java`](src/main/java/course/ch25/exercises/RequestBuilder.java)

Build GET and POST requests with appropriate headers.

```bash
mvn test -pl 25-networking-basics -Dtest="course.ch25.exercises.RequestBuilderTest"
```

### Exercise 2: JSON Placeholder Client (Practice)

**File:** [`JsonPlaceholderClient.java`](src/main/java/course/ch25/exercises/JsonPlaceholderClient.java)

Create a small REST client with injectable `HttpClient` and base URL.

```bash
mvn test -pl 25-networking-basics -Dtest="course.ch25.exercises.JsonPlaceholderClientTest"
```

### Exercise 3: Retrying HTTP Client (Challenge)

**File:** [`RetryingHttpClient.java`](src/main/java/course/ch25/exercises/RetryingHttpClient.java)

Implement automatic retries using an injectable `HttpSender` (no real network in tests).

```bash
mvn test -pl 25-networking-basics -Dtest="course.ch25.exercises.RetryingHttpClientTest"
```

## Key Takeaways

- Prefer **`HttpClient`** over legacy `HttpURLConnection` for new code.
- Reuse a single **`HttpClient`** instance — it is thread-safe.
- Set **timeouts** on both the client and individual requests.
- Accept **injectable dependencies** so network code can be tested locally.
- Handle **status codes** explicitly — do not assume every response is 200.

## Further Reading

- [java.net.http.HttpClient — API docs](https://docs.oracle.com/en/java/javase/25/docs/api/java.net.http/java/net/http/HttpClient.html)
- [Sending HTTP Requests — Java Tutorials](https://docs.oracle.com/en/java/javase/25/docs/api/java.net.http/java/net/http/package-summary.html)
- [JEP 321: HTTP Client API](https://openjdk.org/jeps/321)
