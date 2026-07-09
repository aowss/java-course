package course.ch08.exercises;

public class Validator {

    public static class ValidationException extends Exception {

        private final String fieldName;

        public ValidationException(String fieldName, String message) {
            super(message);
            this.fieldName = fieldName;
        }

        public String getFieldName() {
            return fieldName;
        }
    }

    public static String validateName(String name) throws ValidationException {
        if (name == null || name.isBlank()) {
            throw new ValidationException("name", "Name must not be null or blank");
        }
        if (name.length() > 100) {
            throw new ValidationException("name", "Name must not exceed 100 characters");
        }
        return name;
    }

    public static int validateAge(int age) throws ValidationException {
        if (age < 0 || age > 150) {
            throw new ValidationException("age", "Age must be between 0 and 150");
        }
        return age;
    }

    public static String validateEmail(String email) throws ValidationException {
        if (email == null) {
            throw new ValidationException("email", "Email must not be null");
        }
        if (!email.contains("@")) {
            throw new ValidationException("email", "Email must contain @");
        }
        return email;
    }
}
