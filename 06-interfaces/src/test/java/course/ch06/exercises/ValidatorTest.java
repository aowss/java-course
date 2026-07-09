package course.ch06.exercises;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidatorTest {

    @Test
    void nonNullRejectsNull() {
        Validator<String> validator = Validator.nonNull();
        assertFalse(validator.validate(null));
        assertTrue(validator.validate("hello"));
    }

    @Test
    void nonBlankRejectsNullAndBlank() {
        Validator<String> validator = Validator.nonBlank();
        assertFalse(validator.validate(null));
        assertFalse(validator.validate(""));
        assertFalse(validator.validate("   "));
        assertTrue(validator.validate("hello"));
    }

    @Test
    void lengthBetweenChecksBounds() {
        Validator<String> validator = Validator.lengthBetween(3, 10);
        assertFalse(validator.validate("ab"));
        assertTrue(validator.validate("abc"));
        assertTrue(validator.validate("abcdefghij"));
        assertFalse(validator.validate("abcdefghijk"));
    }

    @Test
    void andRequiresBothValidators() {
        Validator<String> validator = Validator.nonBlank().and(Validator.lengthBetween(3, 5));
        assertFalse(validator.validate(""));
        assertFalse(validator.validate("ab"));
        assertTrue(validator.validate("abc"));
        assertFalse(validator.validate("abcdef"));
    }

    @Test
    void errorMessageIncludesValue() {
        Validator<String> validator = Validator.nonNull();
        assertTrue(validator.errorMessage("bad").contains("bad"));
    }
}
