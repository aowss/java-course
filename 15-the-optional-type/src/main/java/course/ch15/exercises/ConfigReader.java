package course.ch15.exercises;

import java.util.Map;
import java.util.Optional;

/**
 * Exercise 3 (Challenge): Configuration reader with typed Optional accessors.
 */
public class ConfigReader {

    private final Map<String, String> properties;

    /**
     * Creates a config reader from a property map.
     *
     * @param properties the configuration properties
     */
    public ConfigReader(Map<String, String> properties) {
        this.properties = properties;
    }

    /**
     * Returns the raw string value for a key.
     *
     * @param key the property key
     * @return the value, or empty if absent or blank
     */
    public Optional<String> getString(String key) {
        // TODO: implement — return empty for null, missing, or blank values
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns an integer property value.
     *
     * @param key the property key
     * @return the parsed integer, or empty if absent or invalid
     */
    public Optional<Integer> getInt(String key) {
        // TODO: implement — getString → flatMap parseInt
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns a boolean property value ("true"/"false", case-insensitive).
     *
     * @param key the property key
     * @return the parsed boolean, or empty if absent or invalid
     */
    public Optional<Boolean> getBoolean(String key) {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns a property value or the given default.
     *
     * @param key          the property key
     * @param defaultValue the fallback value
     * @return the value or default
     */
    public String getStringOrDefault(String key, String defaultValue) {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns an integer property or the given default.
     *
     * @param key          the property key
     * @param defaultValue the fallback value
     * @return the parsed integer or default
     */
    public int getIntOrDefault(String key, int defaultValue) {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns a required string property, throwing if absent.
     *
     * @param key the property key
     * @return the value
     * @throws IllegalStateException if the property is missing or blank
     */
    public String requireString(String key) {
        // TODO: implement — orElseThrow with descriptive message
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static void main(String[] args) {
        var config = new ConfigReader(Map.of(
                "host", "localhost",
                "port", "8080",
                "debug", "true"
        ));
        System.out.println("Host: " + config.getString("host"));
        System.out.println("Port: " + config.getInt("port"));
        System.out.println("Timeout: " + config.getIntOrDefault("timeout", 30));
    }
}
