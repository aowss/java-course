---
marp: true
theme: default
paginate: true
header: 'Java Course — Chapter 30'
footer: 'Capstone Project — Task Manager CLI'
style: |
  section { font-size: 28px; }
  h2 { color: #1a5276; }
  code { font-size: 0.85em; }
---

# Chapter 30
## Capstone Project — Task Manager CLI

---

## Overview

This capstone brings together concepts from across the course into a complete command-line application.

- **Task Manager** — persists tasks to a file, supports full CRUD
- Follows **layered architecture** principles
- A finished reference implementation — no exercise stubs
- Starting point for your own extensions

---

## Requirements

| Requirement | Description |
|-------------|-------------|
| Create tasks | Add a task with title and optional description |
| List tasks | Display all tasks, optionally filtered by status |
| View details | Show full details for a single task by ID |
| Update status | Change a task's lifecycle status |
| Delete tasks | Remove a task by ID |
| Persistence | Tasks survive restarts via file storage |
| Validation | Reject blank titles and invalid UUIDs/statuses |

---

## Layered Architecture

```
┌─────────────────────────────────────────────┐
│  CLI Layer (TaskManagerCli)                 │
│  Parses commands, formats output            │
├─────────────────────────────────────────────┤
│  Service Layer (TaskService)                │
│  Business logic, validation                 │
├─────────────────────────────────────────────┤
│  Repository Layer (TaskRepository)          │
│  Persistence abstraction                    │
├─────────────────────────────────────────────┤
│  Model Layer (Task, TaskStatus)             │
│  Domain data types                          │
└─────────────────────────────────────────────┘
```

Each layer depends only on the layer **below** it.

---

## Layer Responsibilities

| Layer | Responsibility | Does **not** do |
|-------|----------------|-----------------|
| CLI | Parse commands, format output | Touch files directly |
| Service | Business logic, validation | Parse command-line input |
| Repository | Persist and retrieve tasks | Enforce business rules |
| Model | Domain data types | I/O or user interaction |

This separation makes each layer **independently testable**.

---

## Model Layer

- **`Task`** — immutable record with `id`, `title`, `description`, `status`, `createdAt`
  - Factory method `Task.create()` generates a new pending task
- **`TaskStatus`** — enum: `PENDING`, `IN_PROGRESS`, `COMPLETED`, `CANCELLED`

Immutable records simplify reasoning — updates produce **new instances**.

---

## Repository Layer

- **`TaskRepository`** — interface: `save`, `findById`, `findAll`, `findByStatus`, `deleteById`
- **`FileTaskRepository`** — pipe-delimited text file implementation
  - Escapes titles/descriptions containing `|`

Swapping to a database repository requires **no service changes** — only a new implementation.

---

## Service Layer

- **`TaskService`** — orchestrates repository calls
  - Creates tasks, updates statuses
  - Enforces "task not found" errors
  - Validates input before persistence

Depends on `TaskRepository` **interface**, not `FileTaskRepository`.

---

## CLI Layer

- **`TaskManagerCli`** — interactive command loop

| Command | Action |
|---------|--------|
| `add` | Create a new task |
| `list` | List tasks (optional `--status` filter) |
| `show` | View task details by ID |
| `update` | Change task status |
| `delete` | Remove a task |
| `count` / `help` / `exit` | Utility commands |

---

## SOLID in Practice

| Principle | How it appears |
|-----------|----------------|
| Single Responsibility | CLI parses, service validates, repo persists |
| Open/Closed | New repositories implement the interface without changing service |
| Liskov Substitution | `FileTaskRepository` replaces any `TaskRepository` |
| Interface Segregation | `TaskRepository` exposes only persistence methods |
| Dependency Inversion | `TaskService` depends on interface, not file implementation |

---

## Project Structure

```
30-capstone-project/
  src/main/java/course/ch30/
    model/       Task.java, TaskStatus.java
    repository/  TaskRepository.java, FileTaskRepository.java
    service/     TaskService.java
    cli/         TaskManagerCli.java
  src/test/java/course/ch30/
    repository/  FileTaskRepositoryTest.java
    service/     TaskServiceTest.java
```

---

## Build and Test

```bash
cd 30-capstone-project
mvn test
```

Tests cover the repository and service layers in isolation — no CLI interaction required.

---

## Run the Application

```bash
cd 30-capstone-project
mvn compile exec:java -Dexec.mainClass="course.ch30.cli.TaskManagerCli"
```

With an explicit data file:

```bash
mvn compile exec:java -Dexec.mainClass="course.ch30.cli.TaskManagerCli" \
  -Dexec.args="/tmp/my-tasks.txt"
```

Build a runnable JAR:

```bash
mvn package
java -jar target/ch30-capstone-project-1.0.0.jar
```

---

## Example Session

```
Task Manager — type 'help' for commands
> add Buy groceries --desc Milk and eggs
Created: [a1b2c3d4-...] Buy groceries (PENDING)
> list
[a1b2c3d4-...] Buy groceries (PENDING)
> update a1b2c3d4-... IN_PROGRESS
Updated: [a1b2c3d4-...] Buy groceries (IN_PROGRESS)
> list --status IN_PROGRESS
[a1b2c3d4-...] Buy groceries (IN_PROGRESS)
> count
Total tasks: 1
> exit
Goodbye.
```

---

## Extensions — Make It Your Own

| Extension | Concepts practiced |
|-----------|-------------------|
| Due dates and priorities | `LocalDate`, `Comparable`, sorting |
| JSON persistence | I/O (Ch. 15), text processing (Ch. 8) |
| Search by keyword | Streams API (Ch. 12), filtering |
| Undo/redo | Command pattern (Ch. 29), stack data structure |
| Configuration file | Properties, packages (Ch. 19) |
| Concurrent access | File locking, synchronized methods (Ch. 16) |
| Web API version | HTTP server (Ch. 25), service layer reuse |
| Unit test coverage | JUnit parameterized tests (Ch. 21) |

---

## Key Takeaways

- **Layered architecture** separates concerns and makes each component testable in isolation
- **Interfaces** (`TaskRepository`) enable swapping implementations without changing business logic
- **Immutable records** (`Task`) simplify reasoning about state
- A complete application combines language features, design patterns, I/O, testing, and build tooling
- This project is a **foundation** — extend it to make it your own

Full lesson: [`README.md`](README.md)
