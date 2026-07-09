package course.ch09.exercises;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateEngine {

    public static String render(String template, Map<String, String> values) {
        Pattern pattern = Pattern.compile("\\{\\{(\\w+)}}");
        Matcher matcher = pattern.matcher(template);
        StringBuilder result = new StringBuilder();
        while (matcher.find()) {
            String key = matcher.group(1);
            String replacement = values.getOrDefault(key, matcher.group());
            matcher.appendReplacement(result, Matcher.quoteReplacement(replacement));
        }
        matcher.appendTail(result);
        return result.toString();
    }

    public static void main(String[] args) {
        var template = "Dear {{name}}, your order #{{orderId}} is {{status}}.";
        var values = Map.of("name", "Alice", "orderId", "12345", "status", "shipped");
        System.out.println(render(template, values));
    }
}
