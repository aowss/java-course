# Chapter 30: Capstone Project — Task Manager CLI

## Overview

This capstone brings together concepts from across the course into a complete, working command-line application. You will build (or study) a **Task Manager** that persists tasks to a file, supports full CRUD operations, and follows layered architecture principles.

Unlike prior chapters, there are no exercise stubs — this is a finished application intended as a reference implementation and a starting point for your own extensions.

## Requirements

| Requirement         | Description                                                    |
|---------------------|----------------------------------------------------------------|
| Create tasks        | Add a task with a title and optional description               |
| List tasks          | Display all tasks, optionally filtered by status               |
| View task details   | Show full details for a single task by ID                        |
| Update status       | Change a task's lifecycle status                               |
| Delete tasks        | Remove a task by ID                                            |
| Persistence         | Tasks survive application restarts via file storage            |
| Input validation    | Reject blank titles and invalid UUIDs/statuses gracefully      |

## Design Walkthrough

### Layered Architecture

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

Each layer depends only on the layer below it. The CLI never touches the file directly; the service never parses command-line input. This separation makes each layer independently testable.

### Model

- **`Task`** — an immutable record with `id`, `title`, `description`, `status`, and `createdAt`. Factory method `Task.create()` generates a new pending task.
- **`TaskStatus`** — enum with values `PENDING`, `IN_PROGRESS`, `COMPLETED`, `CANCELLED`.

### Repository

- **`TaskRepository`** — interface defining `save`, `findById`, `findAll`, `findByStatus`, `deleteById`.
- **`FileTaskRepository`** — pipe-delimited text file implementation. Handles escaping for titles/descriptions containing `|`.

### Service

- **`TaskService`** — orchestrates repository calls. Creates tasks, updates statuses, and enforces "task not found" errors.

### CLI

- **`TaskManagerCli`** — interactive command loop with commands: `add`, `list`, `show`, `update`, `delete`, `count`, `help`, `exit`.

### SOLID Principles in Practice

| Principle                 | How it appears in this project                              |
|---------------------------|-------------------------------------------------------------|
| Single Responsibility     | Each class has one job (CLI parses, service validates, repo persists) |
| Open/Closed               | New repositories (e.g., database) implement the interface without changing service code |
| Liskov Substitution       | `FileTaskRepository` can replace any `TaskRepository` implementation |
| Interface Segregation     | `TaskRepository` exposes only persistence methods           |
| Dependency Inversion      | `TaskService` depends on `TaskRepository` interface, not the file implementation |

## Project Structure

```
30-capstone-project/
  README.md
  pom.xml
  src/
    main/java/course/ch30/
      model/
        Task.java
        TaskStatus.java
      repository/
        TaskRepository.java
        FileTaskRepository.java
      service/
        TaskService.java
      cli/
        TaskManagerCli.java
    test/java/course/ch30/
      repository/
        FileTaskRepositoryTest.java
      service/
        TaskServiceTest.java
```

## Build and Run

### Run tests

```bash
cd 30-capstone-project
mvn test
```

### Run the application

```bash
cd 30-capstone-project
mvn compile exec:java -Dexec.mainClass="course.ch30.cli.TaskManagerCli"
```

Or with an explicit data file:

```bash
mvn compile exec:java -Dexec.mainClass="course.ch30.cli.TaskManagerCli" -Dexec.args="/tmp/my-tasks.txt"
```

### Build a runnable JAR

```bash
mvn package
java -jar target/ch30-capstone-project-1.0.0.jar
```

### Example session

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

## Extensions

Consider these enhancements to practice further:

| Extension              | Concepts practiced                                    |
|------------------------|-------------------------------------------------------|
| Due dates and priorities | `LocalDate`, `Comparable`, sorting                  |
| JSON persistence       | I/O (Ch. 15), text processing (Ch. 8)                |
| Search by keyword      | Streams API (Ch. 12), filtering                      |
| Undo/redo              | Command pattern (Ch. 29), stack data structure       |
| Configuration file     | Properties, packages (Ch. 19)                        |
| Concurrent access      | File locking, synchronized methods (Ch. 16)          |
| Web API version        | HTTP server (Ch. 25), service layer reuse            |
| Unit test coverage     | JUnit parameterized tests (Ch. 21)                 |

## Key Takeaways

- **Layered architecture** separates concerns and makes each component testable in isolation.
- **Interfaces** (`TaskRepository`) enable swapping implementations without changing business logic.
- **Immutable records** (`Task`) simplify reasoning about state — updates produce new instances.
- **A complete application** combines language features, design patterns, I/O, testing, and build tooling from across the course.
- This project is a foundation — extend it to make it your own.
