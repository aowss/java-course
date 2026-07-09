package course.ch15.exercises;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CsvParser {

    public static List<Map<String, String>> parse(Path csvFile) throws IOException {
        return parse(csvFile, ',');
    }

    public static List<Map<String, String>> parse(Path csvFile, char delimiter) throws IOException {
        List<String> lines = Files.readAllLines(csvFile);
        if (lines.isEmpty()) {
            return List.of();
        }

        String[] headers = splitLine(lines.getFirst(), delimiter);
        List<Map<String, String>> result = new ArrayList<>();

        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            if (line.isEmpty()) {
                continue;
            }
            String[] values = splitLine(line, delimiter);
            Map<String, String> row = new LinkedHashMap<>();
            for (int j = 0; j < headers.length; j++) {
                row.put(headers[j], j < values.length ? values[j] : "");
            }
            result.add(row);
        }

        return result;
    }

    private static String[] splitLine(String line, char delimiter) {
        List<String> fields = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == delimiter && !inQuotes) {
                fields.add(current.toString());
                current.setLength(0);
            } else {
                current.append(c);
            }
        }
        fields.add(current.toString());
        return fields.toArray(String[]::new);
    }
}
