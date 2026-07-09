package course.ch19.exercises;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ModuleInfoParserTest {

    private static final String SAMPLE = """
            module com.example.app {
                requires java.sql;
                requires java.logging;
                exports com.example.app.api;
                exports com.example.app.model;
            }
            """;

    @Test
    void parsesModuleName() {
        var info = ModuleInfoParser.parse(SAMPLE);
        assertEquals("com.example.app", info.moduleName());
    }

    @Test
    void parsesRequires() {
        var info = ModuleInfoParser.parse(SAMPLE);
        assertEquals(List.of("java.sql", "java.logging"), info.requires());
    }

    @Test
    void parsesExports() {
        var info = ModuleInfoParser.parse(SAMPLE);
        assertEquals(List.of("com.example.app.api", "com.example.app.model"), info.exports());
    }

    @Test
    void parsesModuleWithNoDirectives() {
        String source = "module my.empty.module { }";
        var info = ModuleInfoParser.parse(source);
        assertEquals("my.empty.module", info.moduleName());
        assertTrue(info.requires().isEmpty());
        assertTrue(info.exports().isEmpty());
    }

    @Test
    void parsesRequiresTransitive() {
        String source = """
                module com.example.lib {
                    requires transitive java.desktop;
                    exports com.example.lib.api;
                }
                """;
        var info = ModuleInfoParser.parse(source);
        assertEquals("com.example.lib", info.moduleName());
        assertEquals(List.of("java.desktop"), info.requires());
        assertEquals(List.of("com.example.lib.api"), info.exports());
    }

    @Test
    void throwsOnNullInput() {
        assertThrows(IllegalArgumentException.class, () -> ModuleInfoParser.parse(null));
    }

    @Test
    void throwsOnInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> ModuleInfoParser.parse("not a module"));
    }
}
