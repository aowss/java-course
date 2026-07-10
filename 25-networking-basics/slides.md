---
marp: true
theme: default
paginate: true
header: 'Java Course — Chapter 25'
footer: 'Networking Basics'
style: |
  section { font-size: 28px; }
  h2 { color: #1a5276; }
  code { font-size: 0.85em; }
---

# Chapter 25
## Networking Basics

---

## Objectives

- Understand the basics of TCP sockets and HTTP
- Use the modern `java.net.http.HttpClient` API
- Build HTTP requests with headers, bodies, and timeouts
- Make REST calls and handle responses
- Design network code that is testable without hitting the real internet

---

## Sockets and HTTP

At the transport layer, **sockets** are endpoints for sending and receiving data over TCP:

```java
try (ServerSocket server = new ServerSocket(8080)) {
    Socket client = server.accept();
    // read/write streams on client.getInputStream()
}
```

HTTP is an application-layer protocol built on TCP. Modern Java code uses **`HttpClient`** (Java 11+) instead of raw sockets.

---

## HttpClient

`HttpClient` is **immutable and thread-safe** — create one instance and reuse it:

```java
HttpClient client = HttpClient.newBuilder()
        .connectTimeout(Duration.ofSeconds(10))
        .followRedirects(HttpClient.Redirect.NORMAL)
        .build();
```

| Setting | Description |
|---------|-------------|
| `connectTimeout` | Max time to establish a connection |
| `followRedirects` | Whether to follow 3xx redirects |
| `version(HTTP_2)` | Prefer HTTP/2 |

---

## HttpRequest

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

Set **timeouts** on both the client and individual requests.

---

## HTTP Methods

| Method | Body publisher | Use case |
|--------|----------------|----------|
| `GET()` | None | Fetch a resource |
| `POST(body)` | `BodyPublishers.ofString(json)` | Create a resource |
| `PUT(body)` | `BodyPublishers.ofString(json)` | Replace a resource |
| `DELETE()` | None | Remove a resource |

---

## HttpResponse

Send a request synchronously:

```java
HttpResponse<String> response = client.send(
        request,
        HttpResponse.BodyHandlers.ofString());

int status = response.statusCode();
String body = response.body();
```

Handle **status codes** explicitly — do not assume every response is 200.

---

## Common Status Codes

| Code | Meaning | Typical action |
|------|---------|----------------|
| 200 | OK | Process the response body |
| 201 | Created | Resource created (POST) |
| 400 | Bad Request | Fix the request payload |
| 401 | Unauthorized | Add or refresh credentials |
| 404 | Not Found | Check the URL or resource id |
| 500 | Server Error | Retry or report upstream |

---

## Async Requests

`HttpClient` supports non-blocking sends:

```java
CompletableFuture<HttpResponse<String>> future = client.sendAsync(
        request,
        HttpResponse.BodyHandlers.ofString());

future.thenApply(HttpResponse::body)
      .thenAccept(System.out::println);
```

Use async when you need concurrency without blocking threads.

---

## Testing Without the Real Network

Production code should accept **injectable dependencies**:

```java
var client = new JsonPlaceholderClient(HttpClient.newHttpClient(), baseUrl);

public interface HttpSender {
    HttpResponse<String> send(HttpRequest request)
            throws IOException, InterruptedException;
}
```

In tests: use `com.sun.net.httpserver.HttpServer` on a random port or a stub `HttpSender` with canned responses.

---

## Examples in This Chapter

| File | Topic |
|------|-------|
| `HttpClientDemo` | Creating clients, sending GET requests |
| `RequestBuilderDemo` | POST with JSON, timeouts, Authorization headers |

```bash
mvn test -pl 25-networking-basics
```

---

## Exercises — Your Turn

1. **Request Builder** (Guided) — build GET and POST requests with appropriate headers
2. **JSON Placeholder Client** (Practice) — REST client with injectable `HttpClient` and base URL
3. **Retrying HTTP Client** (Challenge) — automatic retries via injectable `HttpSender`

```bash
mvn test -pl 25-networking-basics -Dtest="RequestBuilderTest"
```

Full lesson: [`README.md`](README.md) · Solutions: `solutions/`

---

## Key Takeaways

- Prefer **`HttpClient`** over legacy `HttpURLConnection` for new code
- Reuse a single **`HttpClient`** instance — it is thread-safe
- Set **timeouts** on both the client and individual requests
- Accept **injectable dependencies** so network code can be tested locally
- Handle **status codes** explicitly — do not assume every response is 200

Further reading: [HttpClient API](https://docs.oracle.com/en/java/javase/25/docs/api/java.net.http/java/net/http/HttpClient.html) · [JEP 321](https://openjdk.org/jeps/321)
