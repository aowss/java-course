package course.bridge.contactbook.model;

import java.util.Objects;
import java.util.UUID;

/**
 * An immutable contact entry.
 */
public record Contact(UUID id, String name, String email, String phone) {

    public Contact {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(email, "email must not be null");
        Objects.requireNonNull(phone, "phone must not be null");
        if (name.isBlank()) {
            throw new IllegalArgumentException("name must not be blank");
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("email must contain @");
        }
    }

    public static Contact create(String name, String email, String phone) {
        return new Contact(UUID.randomUUID(), name, email, phone == null ? "" : phone);
    }
}
