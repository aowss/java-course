#!/usr/bin/env bash
# Build a single Reveal.js presentation: → between chapters, ↓ between slides.
set -euo pipefail

ROOT="$(cd "$(dirname "$0")/.." && pwd)"
OUT_MD="$ROOT/slides/full-course.md"
OUT_HTML="$ROOT/slides/full-course.html"

python3 - "$ROOT" "$OUT_MD" "$OUT_HTML" <<'PY'
import re
import sys
import os
from pathlib import Path

sys.path.insert(0, str(Path(sys.argv[1]) / "slides"))
from reveal_transforms import SlideContext, link_box_html, transform_slide

root = Path(sys.argv[1])
out_md = Path(sys.argv[2])
out_html = Path(sys.argv[3])
link_prefix = os.environ.get("LINK_PREFIX", "../")


def strip_frontmatter(text: str) -> str:
    lines = text.splitlines()
    if lines and lines[0].strip() == "---":
        for i in range(1, len(lines)):
            if lines[i].strip() == "---":
                return "\n".join(lines[i + 1 :]).strip()
    return text.strip()


def split_slides(body: str) -> list[str]:
    parts = re.split(r"\n---\n", body)
    return [p.strip() for p in parts if p.strip()]


quiz_hash_by_slug = {
    path.stem: str(index + 1)
    for index, path in enumerate(sorted((root / "quizzes").glob("[0-9][0-9]-*.md")))
}

quiz_note = link_box_html(
    "slide-footer further-reading",
    "Quizzes",
    "Quizzes for a given chapter can be accessed from the Examples slide. "
    "To return to the course slide, use your browser ←.",
)

lines = [
    '<!-- .slide: class="intro-slide" -->',
    "# Java Course",
    "## Java 25 — complete deck (30 chapters)",
    "",
    "| Key | Action |",
    "|-----|--------|",
    "| **→** / **←** | Next / previous **chapter** |",
    "| **↓** / **↑** | Next / previous **slide** in this chapter |",
    "| **Esc** | Slide overview |",
    "",
    quiz_note,
    "",
]

for chapter_dir in sorted(root.glob("[0-9][0-9]-*/")):
    slides_file = chapter_dir / "slides.md"
    if not slides_file.exists():
        continue
    slides = split_slides(strip_frontmatter(slides_file.read_text()))
    if not slides:
        continue

    ctx = SlideContext(
        chapter_dir.name,
        chapter_dir,
        link_prefix,
        quiz_hash_by_slug.get(chapter_dir.name),
    )

    lines.append("---")
    lines.append("")
    lines.append('<!-- .slide: data-background-color="#1a5276" -->')
    lines.append(transform_slide(slides[0], ctx))
    for slide in slides[1:]:
        lines.extend(["", "--", "", transform_slide(slide, ctx)])
    lines.append("")

markdown = "\n".join(lines).strip() + "\n"
out_md.write_text(markdown, encoding="utf-8")

# Escape for embedding in <script type="text/template">
embedded = markdown.replace("</script>", "<\\/script>")

html_doc = f"""<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Java Course — Full Presentation</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reveal.js@6.0.1/dist/reveal.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reveal.js@6.0.1/dist/theme/white.css" id="theme">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reveal.js@6.0.1/dist/plugin/highlight/monokai.css">
  <link rel="stylesheet" href="reveal-theme.css">
</head>
<body>
  <div class="nav-hint"><kbd>→</kbd> chapter &nbsp;·&nbsp; <kbd>↓</kbd> slide &nbsp;·&nbsp; <kbd>Esc</kbd> overview</div>
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
  <script src="https://cdn.jsdelivr.net/npm/reveal.js@6.0.1/dist/reveal.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/reveal.js@6.0.1/dist/plugin/markdown.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/reveal.js@6.0.1/dist/plugin/highlight.js"></script>
  <script>
    Reveal.initialize({{
      hash: true,
      slideNumber: 'c/t',
      width: 1280,
      height: 780,
      margin: 0.02,
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
index_html = out_html.with_name("index.html")
index_html.write_text(html_doc, encoding="utf-8")
print(f"Wrote {out_md}")
print(f"Wrote {out_html}")
print(f"Wrote {index_html}")
PY
