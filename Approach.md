# Design Rationale

## Sources

This table of contents is synthesized from patterns across the Java education ecosystem, including:

- **Oracle's official Java Tutorials and JLS** — the canonical progression Oracle uses (basics → OOP → APIs → concurrency) heavily influences the overall arc.
- **Widely-used Java textbooks** — *Core Java* (Horstmann), *Effective Java* (Bloch), *Head First Java*, *Java Concurrency in Practice* (Goetz), *Modern Java in Action* — these books collectively establish a "standard" teaching order.
- **OCA/OCP certification objectives** — the Java certification exam topics provide a well-vetted checklist of what a Java developer should know, and in what depth.
- **University CS curricula** — typical CS2/CS3 Java courses at universities follow a similar foundations → OOP → data structures → advanced arc.
- **Online courses** (Coursera, Udemy, Pluralsight) — popular Java courses tend to converge on a similar structure.
- **JEP history and release notes** — knowing which features landed in which version (records in 16, sealed in 17, virtual threads in 21) informed where modern features got placed.

## Tooling Decisions

| Area              | Choice     | Version |
|-------------------|------------|---------|
| Java              | Latest LTS | 25      |
| Build system      | Maven      |         |
| Testing framework | JUnit      | 6       |

## Structural Decisions

### Flat chapter folders

Each chapter is a top-level directory numbered `01-` through `30-`. No nesting by part — the part groupings exist in the TOC for reading order, but nesting in the filesystem adds depth without benefit. The chapter numbers encode the order.

### Multi-module Maven project

A root `pom.xml` defines shared configuration (Java version, JUnit dependency, plugin versions). Each chapter is a Maven module with a minimal `pom.xml` that inherits from the parent. This lets a student build the whole course or work on a single chapter independently.

### Consistent chapter structure

Every chapter follows the same layout:

```
NN-chapter-name/
  README.md                           # The lesson: objectives, concepts, examples, exercises
  slides.md                           # Marp source per chapter (see slides/README.md)
  pom.xml                             # Module POM inheriting from root
  src/
    main/java/course/chNN/
      examples/                       # Runnable, annotated example code
      exercises/                      # Skeleton code with TODOs for the student
    test/java/course/chNN/
      examples/                       # Tests that demonstrate the examples
      exercises/                      # Tests that validate the exercises
  solutions/course/chNN/exercises/    # Complete exercise solutions

slides/
  full-course.html                    # Reveal.js deck for all chapters (generated)
  build-full-deck.sh                  # Build script
  reveal-theme.css                    # Full-course styling
```

### Test-driven exercises

Every exercise comes with a pre-written test suite. The student gets skeleton code with `// TODO` markers and runs the tests to check their work. The expected behavior is defined by the tests, not by vague instructions.

### Three exercise tiers per chapter

1. **Guided** — heavily scaffolded, one method to fill in.
2. **Practice** — less scaffolding, student designs the solution within constraints.
3. **Challenge** — minimal guidance, may combine concepts from prior chapters.

### Solutions alongside each chapter

Solutions live in a `solutions/` subfolder inside each chapter, keeping them close to the exercises while clearly separated.

### Best practices enforced by example

The example code *is* the style guide: proper naming, access modifiers, Javadoc on public APIs, no wildcard imports, `final` where appropriate. No separate style guide document — the code demonstrates the standard.

## Key Design Decisions

### 1. Nine parts instead of fewer, larger ones

The split into 9 parts creates natural "milestone" boundaries. A student finishing Part II knows OOP. Finishing Part V knows concurrency. This makes the course modular — you could teach Parts I–IV as a "fundamentals" course and V–IX as "advanced."

### 2. OOP gets its own dedicated Part (II) rather than being folded into "basics"

OOP is *the* core paradigm of Java. Lumping it with variables and loops would underweight it. Giving it a full part signals: "this is where you spend real time."

### 3. Functional Programming, Streams, and Optional are three separate chapters (not one)

Students frequently conflate lambdas, streams, and Optional because they arrived together in Java 8. Separating them makes each concept digestible on its own. You learn lambdas → then you see streams as a *consumer* of lambdas → then Optional as a related but distinct tool.

### 4. Records, sealed classes, and pattern matching live in Part II (OOP), not in a "Modern Java" appendix

Teach them *where they belong conceptually* rather than as novelties. Records are a data modeling tool — they belong next to classes. Sealed classes constrain inheritance — they belong next to inheritance. This normalizes modern features instead of treating them as add-ons.

### 5. Testing comes late (Part VII) rather than early

This is debatable. A TDD-first approach would introduce JUnit in Part I. The reasoning here was: you need enough language surface (OOP, exceptions, collections) before testing is meaningful. But this is one of the weakest decisions in the TOC — there's a strong argument for introducing testing incrementally alongside each part.

### 6. Virtual Threads get their own chapter, not a subsection

Virtual threads are a paradigm shift in how Java handles concurrency, not just another API in `java.util.concurrent`. Treating them as a subsection would understate their importance and the mental model change they require.

### 7. Concurrency comes after I/O (Part V after Part IV)

Because concurrency's motivating examples often involve I/O-bound work (reading files, making network calls). Having I/O first gives you concrete scenarios for "why would I want another thread?"

### 8. What's deliberately excluded: Spring, web frameworks, microservices, ORMs in depth

The scope is *the Java language and its standard library*. Spring Boot gets a mention under Networking, JPA/Hibernate are absent. The reasoning: those are ecosystem courses, not language courses. Mixing them in dilutes the core material and dates the course faster.

### 9. The "Modern Java Features Roundup" chapter (Part VIII) exists despite decision #4

This seems contradictory — if modern features are already woven in, why a roundup? Because some features (string templates, FFM API, unnamed variables) don't fit neatly into an earlier chapter's topic. The roundup catches those stragglers and serves as a "what's new" reference for experienced developers who skip to it.

### 10. Capstone Project at the end

Synthesis matters. Without it, the course is a reference manual. With it, students prove to themselves they can build something real by combining what they've learned.

Three **bridge projects** in `bridges/` (contact book, log analyzer, catalog service) provide stepping stones between chapter exercises and the capstone.

### 11. Stack and heap introduced early (Chapter 3), JVM internals late (Chapter 28)

The stack/heap memory model is introduced in Chapter 3 (Methods) with a visual diagram — just enough for students to understand pass-by-value, why primitives differ from objects, and what a stack frame is. Initialization blocks and modifier combinations land in Chapter 4 (Classes and Objects), where students first need them; Chapter 5 extends the order for inheritance; Chapter 28 connects the same sequence to class loading, `<clinit>`, and JVM memory areas.

### 12. Appendices for reference depth

Chapter lessons stay practical. Material that is useful but easy to over-teach early — operator precedence tables, promotion rules, evaluation-order puzzles — lives in `appendices/` and is linked from the relevant chapter. This mirrors how the old PPTX course had a dedicated Operators section without forcing every student through exam-level detail on day one.
