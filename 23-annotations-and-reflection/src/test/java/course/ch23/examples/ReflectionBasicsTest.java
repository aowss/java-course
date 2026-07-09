package course.ch23.examples;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReflectionBasicsTest {

    @Test
    void getClassNameReturnsSimpleName() {
        var person = new ReflectionBasics.Person("Alice");
        assertEquals("Person", ReflectionBasics.getClassName(person));
    }

    @Test
    void getDeclaredFieldNamesReturnsFieldNames() {
        List<String> fields = ReflectionBasics.getDeclaredFieldNames(ReflectionBasics.Person.class);
        assertTrue(fields.contains("name"));
    }

    @Test
    void getDeclaredMethodNamesReturnsMethodNames() {
        List<String> methods = ReflectionBasics.getDeclaredMethodNames(ReflectionBasics.Person.class);
        assertTrue(methods.contains("greet"));
    }

    @Test
    void invokeMethodCallsTargetMethod() throws ReflectiveOperationException {
        var person = new ReflectionBasics.Person("Bob");
        Object result = ReflectionBasics.invokeMethod(person, "greet");
        assertEquals("Hello, Bob", result);
    }

    @Test
    void createStringInstanceBuildsObject() throws ReflectiveOperationException {
        ReflectionBasics.Person person =
                ReflectionBasics.createStringInstance(ReflectionBasics.Person.class, "Carol");
        assertEquals("Hello, Carol", ReflectionBasics.invokeMethod(person, "greet"));
    }

    @Test
    void readFieldReturnsFieldValue() throws ReflectiveOperationException {
        var person = new ReflectionBasics.Person("Dan");
        assertEquals("Dan", ReflectionBasics.readField(person, "name"));
    }

    @Test
    void getTypeHierarchyIncludesSuperclass() {
        List<String> hierarchy = ReflectionBasics.getTypeHierarchy(ReflectionBasics.Person.class);
        assertTrue(hierarchy.contains("Object"));
    }
}
