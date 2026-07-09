package course.ch16.exercises;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

/**
 * Exercise 3 (Challenge): Read a CSV file into a list of row maps.
 */
public class CsvParser {

    /**
     * Parses a CSV file using a comma ({@code ','}) as the delimiter.
     *
     * <p>The first line is treated as the header row whose values become the map keys.
     * Each subsequent line produces a {@code Map<String,String>} with one entry per column.
     *
     * <p>Quoted fields (enclosed in double quotes) may contain the delimiter character;
     * the quotes are stripped from the resulting value.
     *
     * @param csvFile the path to the CSV file
     * @return a list of row maps, one per data line
     * @throws IOException if the file cannot be read
     */
    public static List<Map<String, String>> parse(Path csvFile) throws IOException {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Parses a CSV file using the specified {@code delimiter}.
     *
     * <p>Behaves identically to {@link #parse(Path)} but splits fields on
     * {@code delimiter} instead of a comma.
     *
     * @param csvFile   the path to the CSV file
     * @param delimiter the field delimiter character
     * @return a list of row maps, one per data line
     * @throws IOException if the file cannot be read
     */
    public static List<Map<String, String>> parse(Path csvFile, char delimiter) throws IOException {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
