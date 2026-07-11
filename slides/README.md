# Slide Decks — Instructions

This course has three related formats:

| Format | File | Use for |
|--------|------|---------|
| **Written lesson** | `NN-chapter-name/README.md` | Self-study, reference, exercises |
| **Chapter slides** | `NN-chapter-name/slides.md` | Editing, single-chapter export (Marp) |
| **Full-course presentation** | `slides/full-course.html` | Presenting all 30 chapters (Reveal.js) |

Chapter `slides.md` files are [Marp](https://marp.app/) Markdown (~12–18 slides each). The **full-course** deck is built from those files into a single [Reveal.js](https://revealjs.com/) presentation with chapter-aware navigation.

---

## Recommended — present the full course

Use this when teaching or recording the complete course.

### 1. Build

From the repo root (requires Python 3, included on macOS/Linux):

```bash
./slides/build-full-deck.sh
```

This creates:

| Output | Purpose |
|--------|---------|
| `slides/full-course.html` | Open in any browser — **this is the presentation** |
| `slides/full-course.md` | Combined source (`---` = chapter, `--` = slide within chapter) |

No Node.js or Marp install needed for the full-course deck.

### 2. Open and navigate

Open `slides/full-course.html` in a browser (double-click or drag into a window).

| Key | Action |
|-----|--------|
| **→** / **←** | Next / previous **chapter** |
| **↓** / **↑** | Next / previous **slide** within the current chapter |
| **Esc** | Overview of all slides |
| **F** | Fullscreen (browser) |

A hint bar at the bottom left repeats these controls.

### 3. Export as PDF

1. Open `slides/full-course.html?print-pdf` in Chrome (append `?print-pdf` to the URL).
2. Print → **Save as PDF**.

See [Reveal.js PDF export](https://revealjs.com/pdf-export/) for page-size tips.

### 4. After editing chapter slides

Chapter content lives in each `NN-chapter-name/slides.md`. After any edits, rebuild:

```bash
./slides/build-full-deck.sh
```

Then reopen `slides/full-course.html`.

### Customize appearance

Edit [`slides/reveal-theme.css`](reveal-theme.css) to change colors, fonts, tables, and code blocks. CSS variables at the top (`--jc-primary`, `--jc-accent`, etc.) control the palette.

- **CSS only** — save the file and hard-refresh the browser (Cmd+Shift+R); no rebuild needed.
- **Chapter `slides.md` or `build-full-deck.sh`** — run `./slides/build-full-deck.sh`, then reopen `slides/full-course.html`.

Chapter title slides use a blue background; the intro slide uses a light card with a navigation table. Content slides use a light card layout with subtle shadow.

---

## Single-chapter slides (Marp)

Use Marp when you need **one chapter only** — preview while editing, or export a standalone PDF/HTML for a single lesson.

### Prerequisites

**VS Code / Cursor:** install [Marp for VS Code](https://marketplace.visualstudio.com/items?itemName=marp-team.marp-vscode).

**Command line (optional):** Node.js + `npm install -g @marp-team/marp-cli` (PDF export also needs Chrome/Chromium).

### Preview one chapter

1. Open e.g. `08-exception-handling/slides.md`.
2. Run **Marp: Open Preview** from the Command Palette or Marp toolbar.

### Export one chapter

```bash
# HTML
marp --no-stdin 08-exception-handling/slides.md -o 08-exception-handling/slides.html

# PDF
marp --no-stdin 08-exception-handling/slides.md --pdf -o 08-exception-handling/slides.pdf

# PowerPoint
marp --no-stdin 08-exception-handling/slides.md --pptx -o 08-exception-handling/slides.pptx
```

> Always pass `--no-stdin` in scripts and terminals — otherwise Marp may hang waiting for input.

### Export all chapters separately

```bash
find . -path './slides' -prune -o -name slides.md -print | while read -r f; do
  marp --no-stdin "$f" -o "${f%.md}.html"
done
```

Each chapter folder gets its own linear `slides.html`. These do **not** support →/↓ chapter navigation; use `full-course.html` for that.

---

## Slide content structure

Each `slides.md` follows the same layout:

1. **Title** — chapter number and name
2. **Objectives**
3. **Concepts** — short bullets, code snippets (one idea per slide)
4. **Examples** — table + `mvn test -pl …`
5. **Exercises** — Guided / Practice / Challenge
6. **Key takeaways**

Chapter 30 (capstone) uses a project walkthrough instead of the three-tier exercise pattern.

When editing, separate slides with a line containing only `---`. The chapter `README.md` remains the source of truth for depth.

**Mermaid diagrams** — chapters use ` ```mermaid ` blocks in `slides.md` and `README.md`. The build script converts them to `<div class="mermaid">` in `full-course.html` (Reveal's syntax highlighter breaks fenced mermaid blocks). Diagrams render via Mermaid.js when you navigate to the slide.

---

## Files in `slides/`

| File | Purpose |
|------|---------|
| `README.md` | This guide |
| `reveal-theme.css` | Custom styles — edit and hard-refresh the browser (rebuild only if you change the build script) |
| `build-full-deck.sh` | Builds `full-course.md` + `full-course.html` (Reveal.js) |
| `full-course.md` | Generated — do not edit by hand |
| `full-course.html` | Generated — open this to present |

Keep `slides.md` in each chapter under version control. Regenerate `full-course.*` and `index.html` after slide edits.

---

## Publish to GitHub Pages

The live site is deployed from this repo:

| URL | Content |
|-----|---------|
| [aowss.github.io/java-course](https://aowss.github.io/java-course/) | Full course slides |
| [aowss.github.io/java-course/quizzes](https://aowss.github.io/java-course/quizzes/) | In-class quizzes (Parts I–II) |

### One-time setup (repo owner)

1. Open the repo on GitHub → **Settings** → **Pages**.
2. Under **Build and deployment**, set **Source** to **GitHub Actions** (not “Deploy from a branch”).
3. Push `.github/workflows/pages.yml` to `main` (included in this repo).
4. After the **Deploy site to GitHub Pages** workflow succeeds, the site is live.

### How it works

- Workflow: [`.github/workflows/pages.yml`](../.github/workflows/pages.yml)
- On each push to `main` that touches `slides.md`, `slides/`, or `quizzes/`, CI runs both build scripts and publishes:
  - `slides/index.html` → site homepage (full course)
  - `quizzes/index.html` → `/quizzes/` (in-class quizzes)
  - Shared `reveal-theme.css` + `quiz-reveal-theme.css`
- Reveal.js, Mermaid, and fonts load from CDNs — no extra assets to ship.

### Manual deploy

```bash
./slides/build-full-deck.sh      # slides/index.html + full-course.html
./quizzes/build-quiz-decks.sh    # quizzes/index.html + full-quizzes.html
```

Then push to `main`; the Pages workflow handles the rest.

---

## Troubleshooting

| Problem | Fix |
|---------|-----|
| Styling looks stale after editing CSS | Hard-refresh the browser (Cmd+Shift+R) — `reveal-theme.css` is loaded directly |
| Full-course arrows don't change chapters | Rebuild with `./slides/build-full-deck.sh` and hard-refresh the browser |
| Build script permission denied | `chmod +x slides/build-full-deck.sh` |
| Marp hangs in terminal | Add `--no-stdin` |
| Marp PDF export fails | Install Chrome/Chromium |
| `marp: command not found` | `npm install -g @marp-team/marp-cli` or use `npx @marp-team/marp-cli` |
| Marp preview shows raw Markdown | Install the Marp VS Code extension |

---

## All chapter decks

| Ch | Topic | Source |
|----|-------|--------|
| 01 | Introduction to Java | [`01-introduction-to-java/slides.md`](../01-introduction-to-java/slides.md) |
| 02 | Language Basics | [`02-language-basics/slides.md`](../02-language-basics/slides.md) |
| 03 | Methods | [`03-methods/slides.md`](../03-methods/slides.md) |
| 04 | Classes and Objects | [`04-classes-and-objects/slides.md`](../04-classes-and-objects/slides.md) |
| 05 | Inheritance and Polymorphism | [`05-inheritance-and-polymorphism/slides.md`](../05-inheritance-and-polymorphism/slides.md) |
| 06 | Interfaces | [`06-interfaces/slides.md`](../06-interfaces/slides.md) |
| 07 | Encapsulation and Data Modeling | [`07-encapsulation-and-data-modeling/slides.md`](../07-encapsulation-and-data-modeling/slides.md) |
| 08 | Exception Handling | [`08-exception-handling/slides.md`](../08-exception-handling/slides.md) |
| 09 | Packages and Modules | [`09-packages-and-modules/slides.md`](../09-packages-and-modules/slides.md) |
| 10 | Strings and Text | [`10-strings-and-text/slides.md`](../10-strings-and-text/slides.md) |
| 11 | Generics | [`11-generics/slides.md`](../11-generics/slides.md) |
| 12 | Collections Framework | [`12-collections-framework/slides.md`](../12-collections-framework/slides.md) |
| 13 | Functional Programming | [`13-functional-programming/slides.md`](../13-functional-programming/slides.md) |
| 14 | Streams API | [`14-streams-api/slides.md`](../14-streams-api/slides.md) |
| 15 | The Optional Type | [`15-the-optional-type/slides.md`](../15-the-optional-type/slides.md) |
| 16 | I/O and NIO | [`16-io-and-nio/slides.md`](../16-io-and-nio/slides.md) |
| 17 | Threads and Synchronization | [`17-threads-and-synchronization/slides.md`](../17-threads-and-synchronization/slides.md) |
| 18 | Concurrency Utilities | [`18-concurrency-utilities/slides.md`](../18-concurrency-utilities/slides.md) |
| 19 | Virtual Threads | [`19-virtual-threads/slides.md`](../19-virtual-threads/slides.md) |
| 20 | Build Tools | [`20-build-tools/slides.md`](../20-build-tools/slides.md) |
| 21 | Unit Testing with JUnit | [`21-unit-testing-with-junit/slides.md`](../21-unit-testing-with-junit/slides.md) |
| 22 | Mocking and Integration Testing | [`22-mocking-and-integration-testing/slides.md`](../22-mocking-and-integration-testing/slides.md) |
| 23 | Annotations and Reflection | [`23-annotations-and-reflection/slides.md`](../23-annotations-and-reflection/slides.md) |
| 24 | Date and Time API | [`24-date-and-time-api/slides.md`](../24-date-and-time-api/slides.md) |
| 25 | Networking Basics | [`25-networking-basics/slides.md`](../25-networking-basics/slides.md) |
| 26 | Database Access with JDBC | [`26-database-access-with-jdbc/slides.md`](../26-database-access-with-jdbc/slides.md) |
| 27 | Modern Java Features | [`27-modern-java-features/slides.md`](../27-modern-java-features/slides.md) |
| 28 | JVM Internals | [`28-jvm-internals/slides.md`](../28-jvm-internals/slides.md) |
| 29 | Design Principles and Patterns | [`29-design-principles-and-patterns/slides.md`](../29-design-principles-and-patterns/slides.md) |
| 30 | Capstone Project | [`30-capstone-project/slides.md`](../30-capstone-project/slides.md) |

---

## Quick reference

| Goal | What to do |
|------|------------|
| **Present full course** | `./slides/build-full-deck.sh` → open `slides/full-course.html` |
| **Present online** | [aowss.github.io/java-course](https://aowss.github.io/java-course/) (GitHub Pages) |
| Navigate chapters | **→** / **←** |
| Navigate slides in a chapter | **↓** / **↑** |
| Full-course PDF | URL + `?print-pdf` → Print → Save as PDF |
| Edit slide content | Edit `NN-chapter/slides.md` → rebuild full course |
| Preview one chapter | Marp: Open Preview on `slides.md` |
| Export one chapter | `marp --no-stdin CHAPTER/slides.md -o CHAPTER/slides.html` |
| **Present all quizzes** | `./quizzes/build-quiz-decks.sh` → open `quizzes/full-quizzes.html` |
| **Present online** | [Course](https://aowss.github.io/java-course/) · [Quizzes](https://aowss.github.io/java-course/quizzes/) |

---

## In-class quiz decks

Generated from `quizzes/*.md` — see [`quizzes/README.md`](../quizzes/README.md).

```bash
./quizzes/build-quiz-decks.sh
```

| Key | Action |
|-----|--------|
| **→** / **←** | Next / previous quiz (chapter) |
| **↓** / **↑** | Question → answer (light green card) → next question |

Per-chapter Marp sources: `quizzes/slides/*.md`
