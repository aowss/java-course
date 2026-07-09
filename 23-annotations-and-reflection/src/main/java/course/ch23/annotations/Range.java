package course.ch23.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a numeric field or parameter that must fall within an inclusive range.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.FIELD})
public @interface Range {

    /**
     * @return the minimum allowed value (inclusive)
     */
    int min();

    /**
     * @return the maximum allowed value (inclusive)
     */
    int max();
}
