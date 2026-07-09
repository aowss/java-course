package course.ch23.exercises;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SimpleSerializerTest {

    @Test
    void serializeIncludesOnlyAnnotatedFields() {
        var book = new SimpleSerializer.Book("Effective Java", "Joshua Bloch", 416);
        String serialized = SimpleSerializer.serialize(book);
        assertTrue(serialized.contains("author=Joshua Bloch"));
        assertTrue(serialized.contains("title=Effective Java"));
        assertFalse(serialized.contains("pages="));
    }

    @Test
    void serializeSortsFieldsAlphabetically() {
        var book = new SimpleSerializer.Book("Clean Code", "Robert Martin", 464);
        assertEquals("author=Robert Martin,title=Clean Code", SimpleSerializer.serialize(book));
    }

    @Test
    void serializeRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> SimpleSerializer.serialize(null));
    }
}
