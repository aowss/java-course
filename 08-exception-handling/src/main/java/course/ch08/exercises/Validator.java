package course.ch08.exercises;

/**
 * Exercise 1 (Guided): Validate input and throw descriptive exceptions.
 *
 * <p>Implement validation methods that throw {@link ValidationException} when the input
 * is invalid. Each exception carries the field name and a message describing the violation.
 *
 * <pre>{@code
 * Validator.validateName("Alice");  // returns "Alice"
 * Validator.validateName("");       // throws ValidationException
 * Validator.validateAge(25);        // returns 25
 * Validator.validateAge(-1);        // throws ValidationException
 * }</pre>
 */
public class Validator {

    /**
     * A checked exception thrown when a validation rule is violated.
     */
    public static class ValidationException extends Exception {

        private final String fieldName;

        /**
         * Creates a new validation exception.
         *
         * @param fieldName the name of the field that failed validation
         * @param message   a description of the validation failure
         */
        public ValidationException(String fieldName, String message) {
            // TODO: call super with the message and store fieldName
            super(message);
            throw new UnsupportedOperationException("Not yet implemented");
        }

        /**
         * Returns the name of the field that failed validation.
         *
         * @return the field name
         */
        public String getFieldName() {
            // TODO: implement
            throw new UnsupportedOperationException("Not yet implemented");
        }
    }

    /**
     * Validates a name.
     *
     * <p>A valid name is non-null, non-blank, and at most 100 characters long.
     *
     * @param name the name to validate
     * @return the name if valid
     * @throws ValidationException if the name is null, blank, or too long
     */
    public static String validateName(String name) throws ValidationException {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Validates an age.
     *
     * <p>A valid age is between 0 and 150 (inclusive).
     *
     * @param age the age to validate
     * @return the age if valid
     * @throws ValidationException if the age is out of range
     */
    public static int validateAge(int age) throws ValidationException {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Validates an email address.
     *
     * <p>A valid email is non-null and contains an {@code @} character.
     *
     * @param email the email to validate
     * @return the email if valid
     * @throws ValidationException if the email is null or does not contain {@code @}
     */
    public static String validateEmail(String email) throws ValidationException {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
