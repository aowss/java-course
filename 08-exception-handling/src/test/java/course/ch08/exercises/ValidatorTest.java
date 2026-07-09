package course.ch08.exercises;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidatorTest {

    @Test
    void validateNameValid() throws Validator.ValidationException {
        assertEquals("Alice", Validator.validateName("Alice"));
    }

    @Test
    void validateNameNullThrows() {
        var ex = assertThrows(Validator.ValidationException.class,
                () -> Validator.validateName(null));
        assertEquals("name", ex.getFieldName());
    }

    @Test
    void validateNameBlankThrows() {
        var ex = assertThrows(Validator.ValidationException.class,
                () -> Validator.validateName("   "));
        assertEquals("name", ex.getFieldName());
    }

    @Test
    void validateNameEmptyThrows() {
        assertThrows(Validator.ValidationException.class,
                () -> Validator.validateName(""));
    }

    @Test
    void validateNameTooLongThrows() {
        String longName = "A".repeat(101);
        var ex = assertThrows(Validator.ValidationException.class,
                () -> Validator.validateName(longName));
        assertEquals("name", ex.getFieldName());
    }

    @Test
    void validateNameExactly100Chars() throws Validator.ValidationException {
        String name = "A".repeat(100);
        assertEquals(name, Validator.validateName(name));
    }

    @Test
    void validateAgeValid() throws Validator.ValidationException {
        assertEquals(25, Validator.validateAge(25));
    }

    @Test
    void validateAgeBoundaries() throws Validator.ValidationException {
        assertEquals(0, Validator.validateAge(0));
        assertEquals(150, Validator.validateAge(150));
    }

    @Test
    void validateAgeNegativeThrows() {
        var ex = assertThrows(Validator.ValidationException.class,
                () -> Validator.validateAge(-1));
        assertEquals("age", ex.getFieldName());
    }

    @Test
    void validateAgeTooHighThrows() {
        var ex = assertThrows(Validator.ValidationException.class,
                () -> Validator.validateAge(151));
        assertEquals("age", ex.getFieldName());
    }

    @Test
    void validateEmailValid() throws Validator.ValidationException {
        assertEquals("alice@example.com", Validator.validateEmail("alice@example.com"));
    }

    @Test
    void validateEmailNullThrows() {
        var ex = assertThrows(Validator.ValidationException.class,
                () -> Validator.validateEmail(null));
        assertEquals("email", ex.getFieldName());
    }

    @Test
    void validateEmailMissingAtThrows() {
        var ex = assertThrows(Validator.ValidationException.class,
                () -> Validator.validateEmail("alice-example.com"));
        assertEquals("email", ex.getFieldName());
    }
}
