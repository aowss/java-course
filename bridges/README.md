# Bridge Projects

Four optional mini-projects sit between isolated chapter exercises and the Chapter 30 capstone. Each is a **complete reference implementation** you study, run, test, and extend — not a skeleton with TODOs.

| Bridge | After | Module | Skills |
|--------|-------|--------|--------|
| [University Registration](university-registration/) | Ch. 6 (Part II start) | `bridges/university-registration` | Progressive OOP: Student, Course, Instructor, enrollment |
| [Contact Book](contact-book/) | Part II (Ch. 7) | `bridges/contact-book` | Records, interfaces, collections, CLI, layering |
| [Log Analyzer](log-analyzer/) | Part IV (Ch. 16) | `bridges/log-analyzer` | NIO.2 file I/O, streams, collectors, regex |
| [Catalog Service](catalog-service/) | Part VII (Ch. 22) | `bridges/catalog-service` | Service layer, JUnit, Mockito, test doubles |

## Build and test all bridges

From the repo root:

```bash
mvn test -pl bridges/university-registration,bridges/contact-book,bridges/log-analyzer,bridges/catalog-service
```

## Run the CLIs

**University Registration** (interactive, seeded with demo data):

```bash
mvn compile exec:java -pl bridges/university-registration \
  -Dexec.mainClass="course.bridge.urs.cli.UniversityRegistrationCli"
```

Step-by-step demos (no CLI): see [university-registration/README.md](university-registration/README.md).

**Contact Book** (interactive):

```bash
mvn compile exec:java -pl bridges/contact-book \
  -Dexec.mainClass="course.bridge.contactbook.cli.ContactBookCli"
```

**Log Analyzer** (file path argument):

```bash
mvn compile exec:java -pl bridges/log-analyzer \
  -Dexec.mainClass="course.bridge.loganalyzer.cli.LogAnalyzerApp" \
  -Dexec.args="bridges/log-analyzer/src/test/resources/sample.log"
```

**Catalog Service** has no CLI — run `mvn test -pl bridges/catalog-service` and read the tests as examples.

## How these relate to the capstone

```
Chapter exercises (single-class TODOs)
        ↓
University Registration (progressive OOP, Steps 1–5)
        ↓
Contact Book (records + repository layering)
        ↓
Later bridges + Chapter 30 capstone
```

- **University Registration** teaches multi-class design incrementally (the original URS storyline).
- **Contact Book** reuses the capstone's **CLI → service → repository → model** shape at a smaller scale.

## When to do them

- **University Registration** — after Ch. 6 (interfaces); run one step after each of Ch. 4–6 as you go, or all five before Ch. 7.
- **Contact Book** — after Part II (Ch. 7), before diving into exceptions and APIs.
- **Log Analyzer** — after I/O (Ch. 16); uses streams from Ch. 14.
- **Catalog Service** — after testing chapters (Ch. 21–22); prepares you for the capstone service layer.

See each bridge's `README.md` for extension ideas.
