package course.ch08.exercises;

public class MarkdownToHtml {

    public static String convert(String markdown) {
        String result = markdown;
        result = result.replaceAll("\\*\\*(.+?)\\*\\*", "<strong>$1</strong>");
        result = result.replaceAll("\\*(.+?)\\*", "<em>$1</em>");
        result = result.replaceAll("`(.+?)`", "<code>$1</code>");
        return result;
    }

    public static void main(String[] args) {
        System.out.println(convert("This is **bold** and *italic* and `code`."));
    }
}
