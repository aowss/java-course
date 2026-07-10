# Bridge Project 3 — Catalog Service (after Part VII)

A small **service + repository** module with **unit tests** and **Mockito** tests. Complete this after Chapter 22 (Mocking and Integration Testing).

## What you will practice

| Concept from the course | Where it appears |
|-------------------------|------------------|
| Layered design          | `CatalogService` → `BookRepository` |
| `Optional`              | `findByIsbn` |
| JUnit 5 tests           | `CatalogServiceTest` |
| Mockito mocks           | `CatalogServiceMockTest` |
| Dependency inversion    | service depends on interface, not `InMemoryBookRepository` |

## Architecture

```
CatalogService
  └── BookRepository (interface)
        └── InMemoryBookRepository
Model: Book
```

There is no CLI — this bridge focuses on **testable business logic**, the same pattern you will use in the capstone service layer.

## Run tests

```bash
mvn test -pl bridges/catalog-service
```

`CatalogServiceTest` uses a real in-memory repository. `CatalogServiceMockTest` isolates the service with a Mockito mock.

## Suggested extensions

1. **Update title** — add `updateTitle(isbn, newTitle)` with tests for missing ISBN.
2. **Search by author** — mirror `searchByTitle` with its own tests.
3. **Spy repository** — add a test using `@Spy` on `InMemoryBookRepository`.
4. **JDBC repository** — after Chapter 26, implement `JdbcBookRepository` and an integration test.

## Connection to the capstone

Chapter 30's `TaskService` follows this exact shape: validate input, call the repository interface, throw on invalid state. Master this bridge and the capstone service layer will feel familiar.
