package course.ch23.examples;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BuiltInAnnotationsTest {

    @Test
    void describeBuiltInAnnotationsIncludesKeyAnnotations() {
        String description = BuiltInAnnotations.describeBuiltInAnnotations();
        assertTrue(description.contains("@Override"));
        assertTrue(description.contains("@Deprecated"));
        assertTrue(description.contains("@SuppressWarnings"));
        assertTrue(description.contains("@FunctionalInterface"));
    }

    @Test
    void isDeprecatedDetectsDeprecatedClass() {
        assertTrue(BuiltInAnnotations.isDeprecated(BuiltInAnnotations.LegacyApi.class));
    }

    @Test
    void isDeprecatedReturnsFalseForActiveClass() {
        assertFalse(BuiltInAnnotations.isDeprecated(BuiltInAnnotations.class));
    }

    @Test
    void hasFunctionalInterfaceAnnotationDetectsGreeter() {
        assertTrue(BuiltInAnnotations.hasFunctionalInterfaceAnnotation(BuiltInAnnotations.Greeter.class));
    }

    @Test
    void greeterReturnsConfiguredMessage() {
        BuiltInAnnotations.Greeter greeter = BuiltInAnnotations.createGreeter("Hi there");
        assertTrue(greeter.get().contains("Hi there"));
    }

    @Test
    void greetReturnsModernGreeting() {
        assertTrue(BuiltInAnnotations.greet("Alice").contains("Alice"));
    }
}
