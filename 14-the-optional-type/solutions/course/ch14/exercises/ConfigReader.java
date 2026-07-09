package course.ch14.exercises;

import java.util.Map;
import java.util.Optional;

public class ConfigReader {

    private final Map<String, String> properties;

    public ConfigReader(Map<String, String> properties) {
        this.properties = properties;
    }

    public Optional<String> getString(String key) {
        String value = properties.get(key);
        if (value == null || value.isBlank()) {
            return Optional.empty();
        }
        return Optional.of(value);
    }

    public Optional<Integer> getInt(String key) {
        return getString(key).flatMap(text -> {
            try {
                return Optional.of(Integer.parseInt(text.trim()));
            } catch (NumberFormatException e) {
                return Optional.empty();
            }
        });
    }

    public Optional<Boolean> getBoolean(String key) {
        return getString(key).flatMap(text -> {
            String lower = text.trim().toLowerCase();
            if ("true".equals(lower)) {
                return Optional.of(true);
            }
            if ("false".equals(lower)) {
                return Optional.of(false);
            }
            return Optional.empty();
        });
    }

    public String getStringOrDefault(String key, String defaultValue) {
        return getString(key).orElse(defaultValue);
    }

    public int getIntOrDefault(String key, int defaultValue) {
        return getInt(key).orElse(defaultValue);
    }

    public String requireString(String key) {
        return getString(key).orElseThrow(() ->
                new IllegalStateException("Required property missing or blank: " + key));
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
