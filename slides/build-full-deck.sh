#!/usr/bin/env bash
# Build a single Reveal.js presentation: → between chapters, ↓ between slides.
set -euo pipefail

ROOT="$(cd "$(dirname "$0")/.." && pwd)"
OUT_MD="$ROOT/slides/full-course.md"
OUT_HTML="$ROOT/slides/full-course.html"

python3 - "$ROOT" "$OUT_MD" "$OUT_HTML" <<'PY'
import html
import re
import sys
from pathlib import Path

root = Path(sys.argv[1])
out_md = Path(sys.argv[2])
out_html = Path(sys.argv[3])


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


def mermaid_to_div(slide: str) -> str:
    """Reveal highlight breaks ```mermaid blocks — emit raw divs instead."""

    def repl(match: re.Match[str]) -> str:
        content = match.group(1).strip()
        return f'<div class="mermaid">\n{content}\n</div>'

    return re.sub(r"```mermaid\s*\n(.*?)```", repl, slide, flags=re.DOTALL)


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
]

for chapter_dir in sorted(root.glob("[0-9][0-9]-*/")):
    slides_file = chapter_dir / "slides.md"
    if not slides_file.exists():
        continue
    slides = split_slides(strip_frontmatter(slides_file.read_text()))
    if not slides:
        continue

    lines.append("---")
    lines.append("")
    lines.append('<!-- .slide: data-background-color="#1a5276" -->')
    lines.append(mermaid_to_div(slides[0]))
    for slide in slides[1:]:
        lines.extend(["", "--", "", mermaid_to_div(slide)])
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
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reveal.js@5.1.0/dist/reveal.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reveal.js@5.1.0/dist/theme/white.css" id="theme">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reveal.js@5.1.0/plugin/highlight/monokai.css">
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
  <script src="https://cdn.jsdelivr.net/npm/reveal.js@5.1.0/dist/reveal.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/reveal.js@5.1.0/plugin/markdown/markdown.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/reveal.js@5.1.0/plugin/highlight/highlight.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/dompurify@3/dist/purify.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/mermaid@10/dist/mermaid.min.js"></script>
  <script>
    mermaid.initialize({{
      startOnLoad: false,
      theme: 'neutral',
      securityLevel: 'loose',
      fontFamily: 'Inter, system-ui, sans-serif'
    }});

    function renderMermaidInSlide(slide) {{
      if (!slide) return;
      var nodes = slide.querySelectorAll('.mermaid:not([data-processed])');
      if (!nodes.length) return;
      mermaid.run({{ nodes: Array.from(nodes) }}).catch(function(err) {{
        console.error('Mermaid render failed:', err);
      }});
    }}

    Reveal.initialize({{
      hash: true,
      slideNumber: 'c/t',
      transition: 'slide',
      transitionSpeed: 'default',
      backgroundTransition: 'fade',
      plugins: [RevealMarkdown, RevealHighlight],
      markdown: {{ smartypants: true }}
    }}).then(function() {{
      renderMermaidInSlide(Reveal.getCurrentSlide());
    }});

    Reveal.on('slidechanged', function(event) {{
      renderMermaidInSlide(event.currentSlide);
    }});
  </script>
</body>
</html>
"""

out_html.write_text(html_doc, encoding="utf-8")
print(f"Wrote {out_md}")
print(f"Wrote {out_html}")
PY
