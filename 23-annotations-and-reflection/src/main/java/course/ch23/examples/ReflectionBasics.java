package course.ch23.examples;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Introduces core reflection APIs for inspecting and invoking classes at runtime.
 */
public class ReflectionBasics {

    /**
     * Returns the simple name of the object's runtime class.
     *
     * @param target the object to inspect
     * @return the simple class name
     */
    public static String getClassName(Object target) {
        return target.getClass().getSimpleName();
    }

    /**
     * Returns the declared field names of the given class, excluding synthetic fields.
     *
     * @param type the class to inspect
     * @return an alphabetically sorted list of field names
     */
    public static List<String> getDeclaredFieldNames(Class<?> type) {
        return Arrays.stream(type.getDeclaredFields())
                .filter(field -> !field.isSynthetic())
                .map(Field::getName)
                .sorted()
                .toList();
    }

    /**
     * Returns the declared method names of the given class, excluding synthetic methods.
     *
     * @param type the class to inspect
     * @return an alphabetically sorted list of method names
     */
    public static List<String> getDeclaredMethodNames(Class<?> type) {
        return Arrays.stream(type.getDeclaredMethods())
                .filter(method -> !method.isSynthetic())
                .map(Method::getName)
                .sorted()
                .distinct()
                .toList();
    }

    /**
     * Invokes a zero-argument method on the target object by name.
     *
     * @param target     the object instance
     * @param methodName the method to invoke
     * @return the method return value
     * @throws ReflectiveOperationException if the method cannot be found or invoked
     */
    public static Object invokeMethod(Object target, String methodName)
            throws ReflectiveOperationException {
        Method method = target.getClass().getDeclaredMethod(methodName);
        method.setAccessible(true);
        return method.invoke(target);
    }

    /**
     * Creates a new instance of the given class using its String constructor.
     *
     * @param type  the class to instantiate
     * @param value the constructor argument
     * @param <T>   the instance type
     * @return a new instance
     * @throws ReflectiveOperationException if instantiation fails
     */
    public static <T> T createStringInstance(Class<T> type, String value)
            throws ReflectiveOperationException {
        Constructor<T> constructor = type.getDeclaredConstructor(String.class);
        constructor.setAccessible(true);
        return constructor.newInstance(value);
    }

    /**
     * Reads a declared field value from the target object.
     *
     * @param target    the object instance
     * @param fieldName the field name
     * @return the field value
     * @throws ReflectiveOperationException if the field cannot be accessed
     */
    public static Object readField(Object target, String fieldName)
            throws ReflectiveOperationException {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(target);
    }

    /**
     * Returns all superclasses and interfaces implemented by the given type.
     *
     * @param type the class to inspect
     * @return type names from the inheritance hierarchy
     */
    public static List<String> getTypeHierarchy(Class<?> type) {
        List<String> names = new ArrayList<>();
        Class<?> current = type.getSuperclass();
        while (current != null) {
            names.add(current.getSimpleName());
            current = current.getSuperclass();
        }
        Arrays.stream(type.getInterfaces())
                .map(Class::getSimpleName)
                .forEach(names::add);
        return names;
    }

    public static class Person {
        private final String name;

        Person(String name) {
            this.name = name;
        }

        String greet() {
            return "Hello, " + name;
        }
    }

    public static void main(String[] args) throws ReflectiveOperationException {
        Person person = createStringInstance(Person.class, "Alice");
        System.out.println("Class: " + getClassName(person));
        System.out.println("Fields: " + getDeclaredFieldNames(Person.class));
        System.out.println("Methods: " + getDeclaredMethodNames(Person.class));
        System.out.println("Invoke greet(): " + invokeMethod(person, "greet"));
        System.out.println("Field name: " + readField(person, "name"));
    }
}
