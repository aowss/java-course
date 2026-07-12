"""Shared markdown transforms for Reveal.js course and quiz decks."""

from __future__ import annotations

import re
from dataclasses import dataclass
from pathlib import Path


@dataclass
class SlideContext:
    chapter_slug: str
    chapter_dir: Path
    link_prefix: str = "../"
    quiz_hash: str | None = None

    def chapter_url(self, *parts: str) -> str:
        segments: list[str] = []
        if self.link_prefix:
            segments.append(self.link_prefix.rstrip("/"))
        segments.append(self.chapter_slug)
        segments.extend(parts)
        return "/".join(segments)

    def quiz_deck_url(self) -> str | None:
        if self.quiz_hash is None:
            return None
        segments: list[str] = []
        if self.link_prefix:
            segments.append(self.link_prefix.rstrip("/"))
        segments.append("quizzes/index.html")
        return f"{'/'.join(segments)}#/{self.quiz_hash}"


def md_inline_to_html(text: str) -> str:
    text = re.sub(r"\[([^\]]+)\]\(([^)]+)\)", r'<a href="\2">\1</a>', text)
    text = re.sub(r"`([^`]+)`", r"<code>\1</code>", text)
    text = re.sub(r"\*\*(.+?)\*\*", r"<strong>\1</strong>", text)
    text = re.sub(r"(?<!\*)\*([^*]+)\*(?!\*)", r"<em>\1</em>", text)
    return text


def autolink_jeps(text: str) -> str:
    def repl(match: re.Match[str]) -> str:
        number = match.group(1)
        return f"[JEP {number}](https://openjdk.org/jeps/{number})"

    return re.sub(r"(?<!\[)JEP (\d+)(?!\]\()", repl, text)


def find_example_java(chapter_dir: Path, class_name: str) -> Path | None:
    matches = list(chapter_dir.glob(f"src/main/java/**/examples/{class_name}.java"))
    return matches[0] if matches else None


def list_exercise_tests(chapter_dir: Path) -> list[Path]:
    return sorted(chapter_dir.glob("src/test/java/**/exercises/*Test.java"))


def list_exercise_solutions(chapter_dir: Path) -> list[Path]:
    return sorted(chapter_dir.glob("solutions/**/exercises/*.java"))


def clean_exercise_name(name: str) -> str:
    return re.sub(r"`([^`]+)`", r"\1", name).strip()


def exercise_name_candidates(name: str) -> list[str]:
    raw = name
    name = clean_exercise_name(name)
    pascal = "".join(word.capitalize() for word in re.split(r"\s+", re.sub(r"[<>\\|]", "", name)))
    candidates = [pascal]
    for match in re.findall(r"`([^`]+)`", raw):
        candidates.append(match.capitalize())
        candidates.append("".join(word.capitalize() for word in match.split()))
    if pascal.endswith("Tests"):
        candidates.append(pascal[:-5])
    words = name.split()
    if len(words) > 1:
        candidates.append(words[0].capitalize())
        candidates.append("".join(word.capitalize() for word in words[:2]))
        candidates.append(words[-1].capitalize())
    return list(dict.fromkeys(candidates))


def match_exercise_file(
    files: list[Path], candidates: list[str], *, is_test: bool
) -> Path | None:
    best_file: Path | None = None
    best_score = 0
    for path in files:
        stem = path.stem.removesuffix("Test") if is_test else path.stem
        for candidate in candidates:
            score = 0
            if stem.lower() == candidate.lower():
                score = 100
            elif stem.lower() == f"{candidate}ter".lower():
                score = 90
            elif candidate.lower().startswith(stem.lower()) or stem.lower().startswith(
                candidate.lower()
            ):
                score = 70 + min(len(stem), len(candidate))
            if score > best_score:
                best_score = score
                best_file = path
    return best_file if best_score >= 70 else None


def find_test_java(chapter_dir: Path, test_name: str) -> Path | None:
    stem = test_name.removesuffix(".java")
    matches = list(chapter_dir.glob(f"src/test/java/**/*{stem}.java"))
    return matches[0] if matches else None


def parse_flow_segment(segment: str) -> tuple[str, str]:
    segment = segment.strip()
    paren_match = re.match(r"\*\*(.+?)\*\*\s*\((.+)\)$", segment)
    if paren_match:
        return paren_match.group(1), paren_match.group(2)
    bold_match = re.match(r"\*\*(.+?)\*\*$", segment)
    if bold_match:
        return bold_match.group(1), ""
    return segment, ""


def flow_box_html(title: str, subtitle: str = "") -> str:
    title_html = md_inline_to_html(title)
    sub_html = (
        f'<span class="flow-sub">{md_inline_to_html(subtitle)}</span>'
        if subtitle
        else ""
    )
    return (
        f'<div class="flow-box"><span class="flow-title">{title_html}</span>'
        f"{sub_html}</div>"
    )


def line_to_flow_html(line: str) -> str | None:
    stripped = line.strip()
    if stripped.startswith(("-", "|", "#", ">", "`", "<")):
        return None
    if " · " in stripped and " → " not in stripped:
        if not re.search(r"\*\*[^*]+\*\*", stripped):
            return None
        parts = [p.strip() for p in stripped.split(" · ")]
        boxes = [flow_box_html(*parse_flow_segment(part)) for part in parts]
        return '<div class="flow flow-cluster">' + "".join(boxes) + "</div>"
    if " → " not in stripped:
        return None
    if not re.match(r"^\*\*", stripped):
        return None
    parts = [p.strip() for p in stripped.split(" → ")]
    if len(parts) < 2:
        return None
    chunks: list[str] = ['<div class="flow">']
    for index, part in enumerate(parts):
        title, subtitle = parse_flow_segment(part)
        chunks.append(flow_box_html(title, subtitle))
        if index < len(parts) - 1:
            chunks.append('<span class="flow-arrow" aria-hidden="true"></span>')
    chunks.append("</div>")
    return "".join(chunks)


def rewrite_repo_links(slide: str, ctx: SlideContext) -> str:
    def to_url(relative: str) -> str:
        segments: list[str] = []
        if ctx.link_prefix:
            segments.append(ctx.link_prefix.rstrip("/"))
        segments.append(relative)
        return "/".join(segments)

    def repl(match: re.Match[str]) -> str:
        return f"]({to_url(f'appendices/{match.group(1)}')})"

    return re.sub(r"\]\((?:\.\./)+appendices/([^)]+)\)", repl, slide)


def transform_flow_lines(slide: str) -> str:
    lines = slide.splitlines()
    result: list[str] = []
    in_fence = False
    for line in lines:
        if line.strip().startswith("```"):
            in_fence = not in_fence
            result.append(line)
            continue
        if in_fence:
            result.append(line)
            continue
        flow_html = line_to_flow_html(line)
        result.append(flow_html if flow_html else line)
    return "\n".join(result)


PRESENTER_PREFIXES = ("Presenter:", "**Presenter:**")


def strip_presenter_prefix(text: str) -> str:
    text = text.strip()
    for prefix in PRESENTER_PREFIXES:
        if text.startswith(prefix):
            return text[len(prefix) :].lstrip()
    return text


def blockquote_class(first_line: str) -> str:
    body = first_line[2:].strip()
    if body.startswith("Presenter:") or body.startswith("**Presenter:**"):
        return "callout-presenter"
    if body.startswith("**Item") or body.startswith("**Effective") or body.startswith("**P**roducer"):
        return "callout-tip"
    cli_prefixes = ("add ", "list", "update ", "count", "exit")
    if any(body.startswith(prefix) for prefix in cli_prefixes):
        return "callout-cli"
    return "callout-note"


def transform_blockquotes(slide: str) -> str:
    lines = slide.splitlines()
    result: list[str] = []
    i = 0
    while i < len(lines):
        line = lines[i]
        if line.startswith("> ") or line.strip() == ">":
            quote_lines: list[str] = []
            while i < len(lines) and (lines[i].startswith("> ") or lines[i].strip() == ">"):
                if lines[i].startswith("> "):
                    quote_lines.append(lines[i][2:].strip())
                i += 1
            css_class = "callout-note"
            for quote_line in quote_lines:
                css_class = blockquote_class(f"> {quote_line}")
                break
            if css_class == "callout-presenter":
                quote_lines = [strip_presenter_prefix(part) for part in quote_lines]
            paragraphs = [md_inline_to_html(part) for part in quote_lines]
            body = "".join(f"<p>{part}</p>" for part in paragraphs)
            result.append(f'<aside class="{css_class}">{body}</aside>')
            continue
        result.append(line)
        i += 1
    return "\n".join(result)


def transform_examples_table(slide: str, ctx: SlideContext) -> str:
    if not re.search(r"^## Examples\b", slide, flags=re.MULTILINE):
        return slide

    lines = slide.splitlines()
    result: list[str] = []
    in_examples = False

    for line in lines:
        if re.match(r"^## Examples\b", line):
            in_examples = True
            result.append(line)
            continue
        if in_examples and line.startswith("## "):
            in_examples = False

        match = re.match(r"^\| `([^`]+)` \|(.*)$", line)
        if in_examples and match:
            class_name = match.group(1)
            rest = match.group(2)
            java_file = find_example_java(ctx.chapter_dir, class_name)
            if java_file:
                rel = java_file.relative_to(ctx.chapter_dir).as_posix()
                url = ctx.chapter_url(rel)
                line = f"| [`{class_name}`]({url}) |{rest}"
        result.append(line)

    return "\n".join(result)


def link_box_html(css_class: str, label: str, body: str) -> str:
    return (
        f'<aside class="link-box {css_class}">'
        f'<span class="footer-label">{label}</span>'
        f'<span class="footer-links">{md_inline_to_html(body)}</span>'
        "</aside>"
    )


def transform_exercise_items(slide: str, ctx: SlideContext) -> str:
    if not re.search(r"^## Exercises\b", slide, flags=re.MULTILINE):
        return slide

    slide = re.sub(r"^Solutions:.*$\n?", "", slide, flags=re.MULTILINE)
    slide = re.sub(
        r"\n*Test: \[`[^`]+`\]\([^)]+\)\n?",
        "\n",
        slide,
    )
    slide = re.sub(
        r"\n*<aside class=\"link-box exercise-links\">.*?</aside>\n?",
        "\n",
        slide,
        flags=re.DOTALL,
    )

    tests = list_exercise_tests(ctx.chapter_dir)
    solutions = list_exercise_solutions(ctx.chapter_dir)
    used_tests: set[Path] = set()
    used_solutions: set[Path] = set()

    lines = slide.splitlines()
    result: list[str] = []
    in_exercises = False

    for line in lines:
        if re.match(r"^## Exercises\b", line):
            in_exercises = True
            result.append(line)
            continue
        if in_exercises and line.startswith("## "):
            in_exercises = False

        match = re.match(r"^(\d+)\.\s+\*\*([^*]+)\*\*(.*)$", line)
        if in_exercises and match:
            number, name, rest = match.groups()
            candidates = exercise_name_candidates(name)
            test_file = match_exercise_file(
                [path for path in tests if path not in used_tests],
                candidates,
                is_test=True,
            )
            solution_file = match_exercise_file(
                [path for path in solutions if path not in used_solutions],
                candidates,
                is_test=False,
            )
            if test_file:
                used_tests.add(test_file)
            if solution_file:
                used_solutions.add(solution_file)

            link_parts: list[str] = []
            if test_file:
                rel = test_file.relative_to(ctx.chapter_dir).as_posix()
                link_parts.append(f"[Test]({ctx.chapter_url(rel)})")
            if solution_file:
                rel = solution_file.relative_to(ctx.chapter_dir).as_posix()
                link_parts.append(f"[Solution]({ctx.chapter_url(rel)})")
            line = f"{number}. **{name}**{rest.rstrip()}"
            if link_parts:
                line += (
                    '<br><span class="exercise-links">'
                    + " · ".join(link_parts)
                    + "</span>"
                )
            result.append(line)
            continue

        result.append(line)

    return "\n".join(result)


def footer_html(label: str, css_class: str, body: str) -> str:
    return link_box_html(f"slide-footer {css_class}", label, body)


def transform_quiz_link(slide: str, ctx: SlideContext) -> str:
    url = ctx.quiz_deck_url()
    if not url or not re.search(r"^## Examples\b", slide, flags=re.MULTILINE):
        return slide

    chapter_num = ctx.chapter_slug[:2].lstrip("0") or "0"
    body = f"[Chapter {chapter_num} quiz]({url})"
    box = link_box_html("quiz-link link-top", "Check your understanding", body)

    lines = slide.splitlines()
    result: list[str] = []
    for line in lines:
        result.append(line)
        if re.match(r"^## Examples\b", line):
            result.append("")
            result.append(box)
    return "\n".join(result)


def transform_full_lesson(slide: str, ctx: SlideContext) -> str:
    def repl(match: re.Match[str]) -> str:
        body = match.group(1).strip()
        body = re.sub(r"\s*·\s*Solutions:\s*`solutions/`", "", body)
        readme_url = ctx.chapter_url("README.md")
        body = re.sub(
            r"\[`README\.md`\]\(README\.md\)",
            f"[`README.md`]({readme_url})",
            body,
        )
        body = re.sub(r"\(README\.md\)", f"({readme_url})", body)
        return footer_html("Full lesson", "lesson-link", body)

    return re.sub(r"^Full lesson:(.*)$", repl, slide, flags=re.MULTILINE)


def transform_further_reading(slide: str) -> str:
    def repl(match: re.Match[str]) -> str:
        body = autolink_jeps(match.group(1).strip())
        return footer_html("Further reading", "further-reading", body)

    return re.sub(r"^Further reading:(.*)$", repl, slide, flags=re.MULTILINE)


def transform_slide(slide: str, ctx: SlideContext | None = None) -> str:
    slide = transform_flow_lines(slide)
    if ctx is not None:
        slide = rewrite_repo_links(slide, ctx)
    slide = transform_blockquotes(slide)
    if ctx is not None:
        slide = transform_examples_table(slide, ctx)
        slide = transform_quiz_link(slide, ctx)
        slide = transform_exercise_items(slide, ctx)
        slide = transform_full_lesson(slide, ctx)
    slide = transform_further_reading(slide)
    if '<aside class="slide-footer' in slide:
        slide = '<!-- .slide: class="slide-has-footer" -->\n' + slide
    return slide
