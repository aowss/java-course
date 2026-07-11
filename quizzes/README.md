# In-Class Quizzes

Short formative quizzes for live sessions. Use them **before** students tackle the chapter exercises — they surface misconceptions cheaply.

Each chapter quiz has 6–8 questions: predict-the-output, spot-the-bug, and concept checks.

| Format | File | Use for |
|--------|------|---------|
| **Written quiz** | `01-….md` | Self-study, printing; answers in collapsible `<details>` sections |
| **Slide deck (one chapter)** | `slides/01-….md` | Marp preview/export for a single quiz |
| **All quizzes (present)** | `full-quizzes.html` | Reveal.js — question on **↓**, answer on light green card |

## Part I: Foundations

| Chapter | Written | Slides |
|---------|---------|--------|
| 1. Introduction to Java | [Quiz](01-introduction-to-java.md) | [Slides](slides/01-introduction-to-java.md) |
| 2. Language Basics | [Quiz](02-language-basics.md) | [Slides](slides/02-language-basics.md) |
| 3. Methods | [Quiz](03-methods.md) | [Slides](slides/03-methods.md) |

## Part II: Object-Oriented Programming

| Chapter | Written | Slides |
|---------|---------|--------|
| 4. Classes and Objects | [Quiz](04-classes-and-objects.md) | [Slides](slides/04-classes-and-objects.md) |
| 5. Inheritance and Polymorphism | [Quiz](05-inheritance-and-polymorphism.md) | [Slides](slides/05-inheritance-and-polymorphism.md) |
| 6. Interfaces | [Quiz](06-interfaces.md) | [Slides](slides/06-interfaces.md) |
| 7. Encapsulation and Data Modeling | [Quiz](07-encapsulation-and-data-modeling.md) | [Slides](slides/07-encapsulation-and-data-modeling.md) |

---

## Present all quizzes (recommended)

### 1. Build

From the repo root:

```bash
./quizzes/build-quiz-decks.sh
```

Creates:

| Output | Purpose |
|--------|---------|
| `quizzes/full-quizzes.html` | Open in a browser — **present from here** |
| `quizzes/full-quizzes.md` | Combined Reveal.js source (generated) |
| `quizzes/slides/*.md` | Per-chapter Marp decks (generated) |

### 2. Navigate

Open `quizzes/full-quizzes.html` in a browser.

| Key | Action |
|-----|--------|
| **→** / **←** | Next / previous **quiz** (chapter) |
| **↓** / **↑** | Question → **answer** (light green card) → next question |
| **Esc** | Overview |

Pause on each white question slide. Press **↓** to reveal the answer on a light green card.

### 3. After editing quiz content

Edit the written quiz files (`01-….md`, etc.), then rebuild:

```bash
./quizzes/build-quiz-decks.sh
```

Slide decks are **generated** from the written quizzes — do not edit `slides/*.md` or `full-quizzes.*` by hand.

---

## Single-quiz export (Marp)

For one chapter only — PDF, HTML, or PowerPoint:

```bash
# Preview in VS Code/Cursor: open quizzes/slides/02-language-basics.md → Marp: Open Preview

# Export HTML
marp --no-stdin quizzes/slides/02-language-basics.md -o quizzes/slides/02-language-basics.html

# Export PDF (requires Chrome/Chromium)
marp --no-stdin quizzes/slides/02-language-basics.md --pdf -o quizzes/slides/02-language-basics.pdf
```

---

## How to run these in class

1. Open `full-quizzes.html` (or a single-chapter Marp export).
2. Show the **question** slide; give students 30–60 seconds.
3. Press **↓** to reveal the **answer** card (light green background).
4. Discuss why wrong answers are tempting, then continue.
5. Move on to the chapter exercises.
