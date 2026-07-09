package course.ch23.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a zero-argument method as a test method for {@link course.ch23.exercises.MiniTestRunner}.
 *
 * <p>This is a course-specific annotation and is not related to JUnit.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {
}
