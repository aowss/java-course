# Bridge Project 1 — Contact Book (after Part II)

A small CLI application that practices **records**, **interfaces**, **collections**, and **layered structure** without file I/O or streams. Complete this after Chapter 7 (Encapsulation and Data Modeling).

## What you will practice

| Concept from the course | Where it appears |
|-------------------------|------------------|
| Records and validation  | `Contact`          |
| Interfaces              | `ContactRepository` |
| `HashMap` / `List`      | `InMemoryContactRepository` |
| Service layer           | `ContactService`   |
| Simple CLI              | `ContactBookCli`   |

## Architecture

```
CLI (ContactBookCli)
  └── Service (ContactService)
        └── Repository (ContactRepository)
              └── InMemoryContactRepository
Model: Contact
```

## Run it

```bash
cd bridges/contact-book
mvn compile exec:java -Dexec.mainClass="course.bridge.contactbook.cli.ContactBookCli"
```

Example session:

```
> add Alice alice@example.com 555-0100
> add Bob bob@example.com
> list
> find ali
> count
> remove <uuid-from-list>
> exit
```

## Run tests

```bash
mvn test -pl bridges/contact-book
```

## Suggested extensions

1. **Multi-word names** — improve the CLI parser to accept quoted names (`add "Alice Smith" ...`).
2. **Duplicate detection** — reject two contacts with the same email in `ContactService`.
3. **File persistence** — after Chapter 16, add `FileContactRepository` (pipe-delimited, like the capstone).
4. **Enums** — add `ContactLabel` (`WORK`, `PERSONAL`) to the model.

## Connection to the capstone

This project uses the same layering as [Chapter 30](../../30-capstone-project/README.md) (CLI → service → repository → model), but stays small enough to finish in an afternoon.
