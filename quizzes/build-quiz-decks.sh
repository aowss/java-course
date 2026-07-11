#!/usr/bin/env bash
# Build Reveal.js quiz decks from quizzes/*.md (Parts I–II).
# Generates full-quizzes.html and per-chapter Marp slides in quizzes/slides/.
set -euo pipefail

ROOT="$(cd "$(dirname "$0")/.." && pwd)"
QUIZ_DIR="$ROOT/quizzes"
SLIDES_DIR="$QUIZ_DIR/slides"
OUT_MD="$QUIZ_DIR/full-quizzes.md"
OUT_HTML="$QUIZ_DIR/full-quizzes.html"

mkdir -p "$SLIDES_DIR"

python3 - "$QUIZ_DIR" "$SLIDES_DIR" "$OUT_MD" "$OUT_HTML" <<'PY'
import html
import re
import sys
from pathlib import Path

quiz_dir = Path(sys.argv[1])
slides_dir = Path(sys.argv[2])
out_md = Path(sys.argv[3])
out_html = Path(sys.argv[4])

QUIZ_FILES = sorted(quiz_dir.glob("[0-9][0-9]-*.md"))


def parse_quiz(path: Path) -> dict:
    text = path.read_text(encoding="utf-8")
    blocks = [b.strip() for b in re.split(r"\n---\n", text) if b.strip()]
    if not blocks:
        return {"title": path.stem, "subtitle": "", "questions": []}

    header = blocks[0]
    title_match = re.match(r"# Quiz — (.+)", header)
    title = title_match.group(1) if title_match else path.stem
    subtitle = ""
    for line in header.splitlines()[1:]:
        line = line.strip()
        if line and not line.startswith("#"):
            subtitle = re.sub(r"\*+", "", line).strip()
            break

    questions = []
    for block in blocks[1:]:
        m = re.match(r"### (\d+)\.\s*(.+?)\n\n(.*)", block, re.DOTALL)
        if not m:
            continue
        num, qtype, rest = m.groups()
        if "<details>" not in rest:
            continue
        question_text, _, details = rest.partition("<details>")
        answer_match = re.search(
            r"</summary>\s*\n+(.*?)\s*</details>", details, re.DOTALL
        )
        if not answer_match:
            continue
        questions.append(
            {
                "num": num,
                "type": qtype.strip(),
                "question": question_text.strip(),
                "answer": answer_match.group(1).strip(),
            }
        )

    return {"title": title, "subtitle": subtitle, "questions": questions}


def marp_frontmatter(title: str, chapter_num: str) -> str:
    return f"""---
marp: true
theme: default
paginate: true
header: 'Java Course — Quiz'
footer: '{title}'
style: |
  section {{ font-size: 28px; }}
  h2 {{ color: #1a5276; }}
  section.answer {{ background: #e8f6ef; }}
  section.answer h2 {{ color: #1e5631; }}
  code {{ font-size: 0.85em; }}
---

"""


def write_marp_slides(quiz_path: Path, data: dict) -> Path:
    out = slides_dir / quiz_path.name
    lines = [marp_frontmatter(data["title"], quiz_path.name[:2])]
    lines.append(f"# Quiz\n## {data['title']}\n")
    if data["subtitle"]:
        lines.append(data["subtitle"])
    lines.append("\n---\n")

    for q in data["questions"]:
        lines.append(f"## Q{q['num']} · {q['type']}\n")
        lines.append(q["question"])
        lines.append("\n---\n")
        lines.append('<!-- _class: answer -->\n')
        lines.append(f"## Answer — Q{q['num']}\n")
        lines.append(q["answer"])
        lines.append("\n---\n")

    out.write_text("\n".join(lines).strip() + "\n", encoding="utf-8")
    return out


def build_reveal_markdown(all_quizzes: list[tuple[Path, dict]]) -> str:
    lines = [
        '<!-- .slide: class="intro-slide" -->',
        "# In-Class Quizzes",
        "## Parts I–II (Chapters 1–7)",
        "",
        "| Key | Action |",
        "|-----|--------|",
        "| **→** / **←** | Next / previous **quiz** (chapter) |",
        "| **↓** / **↑** | Question → answer → next question |",
        "| **Esc** | Slide overview |",
        "",
        "Pause on each **question** slide. Press **↓** to reveal the answer.",
        "",
    ]

    for quiz_path, data in all_quizzes:
        lines.append("---")
        lines.append("")
        lines.append('<!-- .slide: data-background-color="#1a5276" -->')
        lines.append(f"# Quiz")
        lines.append(f"## {data['title']}")
        if data["subtitle"]:
            lines.append("")
            lines.append(data["subtitle"])

        for q in data["questions"]:
            lines.extend(["", "--", ""])
            lines.append(f"## Q{q['num']} · {q['type']}")
            lines.append("")
            lines.append(q["question"])
            lines.extend(["", "--", ""])
            lines.append('<!-- .slide: class="answer-slide" -->')
            lines.append(f"## Answer — Q{q['num']}")
            lines.append("")
            lines.append(q["answer"])

        lines.append("")

    return "\n".join(lines).strip() + "\n"


all_quizzes: list[tuple[Path, dict]] = []
for quiz_path in QUIZ_FILES:
    data = parse_quiz(quiz_path)
    if not data["questions"]:
        continue
    write_marp_slides(quiz_path, data)
    all_quizzes.append((quiz_path, data))

if not all_quizzes:
    raise SystemExit("No quiz files found in quizzes/")

markdown = build_reveal_markdown(all_quizzes)
out_md.write_text(markdown, encoding="utf-8")

embedded = markdown.replace("</script>", "<\\/script>")

html_doc = f"""<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Java Course — In-Class Quizzes</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reveal.js@5.1.0/dist/reveal.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reveal.js@5.1.0/dist/theme/white.css" id="theme">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reveal.js@5.1.0/plugin/highlight/monokai.css">
  <link rel="stylesheet" href="../slides/reveal-theme.css">
  <link rel="stylesheet" href="quiz-reveal-theme.css">
</head>
<body>
  <div class="nav-hint"><kbd>→</kbd> quiz &nbsp;·&nbsp; <kbd>↓</kbd> question / answer &nbsp;·&nbsp; <kbd>Esc</kbd> overview</div>
  <div class="reveal">
    <div class="slides">
      <section
        data-markdown
        data-separator="^---$"
        data-separator-vertical="^--$"
        data-charset="utf-8">
        <script type="text/template">
{embedded}</script>
      </section>
    </div>
  </div>
  <script src="https://cdn.jsdelivr.net/npm/reveal.js@5.1.0/dist/reveal.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/reveal.js@5.1.0/plugin/markdown/markdown.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/reveal.js@5.1.0/plugin/highlight/highlight.js"></script>
  <script>
    Reveal.initialize({{
      hash: true,
      slideNumber: 'c/t',
      transition: 'slide',
      transitionSpeed: 'default',
      backgroundTransition: 'fade',
      plugins: [RevealMarkdown, RevealHighlight],
      markdown: {{ smartypants: true }}
    }});
  </script>
</body>
</html>
"""

out_html.write_text(html_doc, encoding="utf-8")
print(f"Wrote {len(all_quizzes)} Marp decks under {slides_dir}")
print(f"Wrote {out_md}")
print(f"Wrote {out_html}")
PY

chmod +x "$QUIZ_DIR/build-quiz-decks.sh"
