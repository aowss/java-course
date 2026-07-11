# Bridge Project — University Registration (Part II)

A **progressive** mini-project that rebuilds your original URS exercise arc: `Student`, `Course`, `Instructor`, and enrollment — one step at a time. Do this **after Chapter 6** (before or alongside Chapter 7). Then finish Part II with [Contact Book](../contact-book/) for records, repositories, and modern layering.

## Why this bridge exists

| Contact Book | University Registration |
|--------------|-------------------------|
| One entity type, app layering | **Multiple classes** with relationships |
| Best after Ch. 7 (records) | Best after Ch. 4–6 (classes → interfaces) |
| Capstone-shaped CLI | **Step-by-step OOP** narrative |

## The five steps

| Step | After chapter | Runnable demo | What you learn |
|------|---------------|---------------|----------------|
| 1 | Ch. 4 | `Step1BasicDemo` | Classes, objects, arrays, one-way `register` |
| 2 | Ch. 4 | `Step2EncapsulationDemo` | `private` fields, getters |
| 3 | Ch. 6 | `Step3InstructorDemo` | Third type, course ↔ instructor link |
| 4 | Ch. 6 | `Step4BidirectionalDemo` | Enrollment updates **both** student and course |
| 5 | Ch. 6+ | `Step5SystemDemo` + CLI | `RegistrationSystem` registry, validation, commands |

Each step is a small `main` in `course.bridge.urs.steps` — run it, read the code, compare to the previous step.

## Run the step demos

From the repo root:

```bash
# Step 1 — basic classes
mvn compile exec:java -pl bridges/university-registration \
  -Dexec.mainClass="course.bridge.urs.steps.Step1BasicDemo"

# Step 2 — encapsulation
mvn compile exec:java -pl bridges/university-registration \
  -Dexec.mainClass="course.bridge.urs.steps.Step2EncapsulationDemo"

# Step 3 — instructor
mvn compile exec:java -pl bridges/university-registration \
  -Dexec.mainClass="course.bridge.urs.steps.Step3InstructorDemo"

# Step 4 — bidirectional registration
mvn compile exec:java -pl bridges/university-registration \
  -Dexec.mainClass="course.bridge.urs.steps.Step4BidirectionalDemo"

# Step 5 — full system
mvn compile exec:java -pl bridges/university-registration \
  -Dexec.mainClass="course.bridge.urs.steps.Step5SystemDemo"
```

## Run the interactive CLI (Step 5)

```bash
mvn compile exec:java -pl bridges/university-registration \
  -Dexec.mainClass="course.bridge.urs.cli.UniversityRegistrationCli"
```

The CLI starts with sample instructors, courses, and registrations (like the original URS demo).

```
> students
> courses
> register 2 1
> student-courses 1
> course-roster 1
> help
> exit
```

## Run tests

```bash
mvn test -pl bridges/university-registration
```

## Final architecture (Step 5)

```
CLI (UniversityRegistrationCli)
  └── RegistrationSystem
        ├── Instructor
        ├── Course  ── taught by ──► Instructor
        └── Student ── registers ──► Course (bidirectional roster)
```

Production classes live in `course.bridge.urs.model`:

- **`Student`** — up to 6 courses
- **`Course`** — up to 20 students, one instructor
- **`Instructor`** — identity only (extension point: add `Person` superclass after Ch. 5)

## Suggested study path

1. Run **Step 1** after reading Ch. 4 — notice public fields and fixed-size arrays.
2. Run **Step 2** — same behavior, encapsulated state.
3. After Ch. 6, run **Steps 3–4** — composition and two-way links.
4. Read `model/` and `RegistrationSystem`, run **Step 5** and the CLI.
5. Move on to [Contact Book](../contact-book/) for repository interfaces and records.

## Extension ideas

1. **`Person` hierarchy** — abstract `Person` with `Student` and `Instructor` subclasses (Ch. 5).
2. **Enrollment exceptions** — custom `CourseFullException` / `DuplicateEnrollmentException` (Ch. 8).
3. **`List` instead of arrays** — refactor after Ch. 12.
4. **File persistence** — save registrations after Ch. 16 (like the capstone).

## Connection to Contact Book and capstone

```
Chapter exercises (single-class)
        ↓
University Registration (progressive OOP, Steps 1–5)   ← you are here
        ↓
Contact Book (records + repository layering)
        ↓
Chapter 30 capstone
```
