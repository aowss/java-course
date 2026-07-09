package course.ch23.examples;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CustomAnnotationDemoTest {

    @Test
    void validateNotNullAcceptsNonNullValue() {
        assertDoesNotThrow(() -> CustomAnnotationDemo.validateNotNull("Java"));
    }

    @Test
    void validateNotNullRejectsNullValue() {
        assertThrows(IllegalArgumentException.class,
                () -> CustomAnnotationDemo.validateNotNull(null));
    }

    @Test
    void validateRangeAcceptsValueInRange() {
        assertDoesNotThrow(() -> CustomAnnotationDemo.validateRange(50, 1, 100));
    }

    @Test
    void validateRangeRejectsValueOutsideRange() {
        assertThrows(IllegalArgumentException.class,
                () -> CustomAnnotationDemo.validateRange(150, 1, 100));
    }

    @Test
    void validateObjectAcceptsValidProfile() {
        var profile = new CustomAnnotationDemo.UserProfile("alice", 30);
        assertDoesNotThrow(() -> CustomAnnotationDemo.validateObject(profile));
    }

    @Test
    void validateObjectRejectsNullUsername() {
        var profile = new CustomAnnotationDemo.UserProfile(null, 30);
        assertThrows(IllegalArgumentException.class,
                () -> CustomAnnotationDemo.validateObject(profile));
    }

    @Test
    void validateObjectRejectsInvalidAge() {
        var profile = new CustomAnnotationDemo.UserProfile("alice", 10);
        assertThrows(IllegalArgumentException.class,
                () -> CustomAnnotationDemo.validateObject(profile));
    }

    @Test
    void findNotNullParametersDetectsAnnotatedParameter() throws NoSuchMethodException {
        Method method = CustomAnnotationDemo.class.getMethod("validateNotNull", Object.class);
        String[] names = CustomAnnotationDemo.findNotNullParameters(method);
        assertEquals(1, names.length);
    }
}
