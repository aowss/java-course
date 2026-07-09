package course.ch15.exercises;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConfigReaderTest {

    private final ConfigReader config = new ConfigReader(Map.of(
            "host", "localhost",
            "port", "8080",
            "debug", "true",
            "empty", "",
            "badport", "abc"
    ));

    @Test
    void getStringPresent() {
        assertEquals("localhost", config.getString("host").orElse(""));
    }

    @Test
    void getStringMissingOrBlank() {
        assertTrue(config.getString("missing").isEmpty());
        assertTrue(config.getString("empty").isEmpty());
    }

    @Test
    void getIntValid() {
        assertEquals(8080, config.getInt("port").orElse(0));
    }

    @Test
    void getIntInvalid() {
        assertTrue(config.getInt("badport").isEmpty());
        assertTrue(config.getInt("missing").isEmpty());
    }

    @Test
    void getBooleanValid() {
        assertTrue(config.getBoolean("debug").orElse(false));
    }

    @Test
    void getStringOrDefault() {
        assertEquals("localhost", config.getStringOrDefault("host", "default"));
        assertEquals("default", config.getStringOrDefault("missing", "default"));
    }

    @Test
    void getIntOrDefault() {
        assertEquals(8080, config.getIntOrDefault("port", 0));
        assertEquals(30, config.getIntOrDefault("missing", 30));
    }

    @Test
    void requireStringPresent() {
        assertEquals("localhost", config.requireString("host"));
    }

    @Test
    void requireStringThrows() {
        assertThrows(IllegalStateException.class, () -> config.requireString("missing"));
    }
}
