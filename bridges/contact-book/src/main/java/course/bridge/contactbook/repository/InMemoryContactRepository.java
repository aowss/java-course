package course.bridge.contactbook.repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import course.bridge.contactbook.model.Contact;

/**
 * In-memory contact storage backed by a {@link LinkedHashMap} (insertion order).
 */
public final class InMemoryContactRepository implements ContactRepository {

    private final Map<UUID, Contact> contacts = new LinkedHashMap<>();

    @Override
    public void save(Contact contact) {
        contacts.put(contact.id(), contact);
    }

    @Override
    public Optional<Contact> findById(UUID id) {
        return Optional.ofNullable(contacts.get(id));
    }

    @Override
    public List<Contact> findAll() {
        return new ArrayList<>(contacts.values());
    }

    @Override
    public boolean deleteById(UUID id) {
        return contacts.remove(id) != null;
    }
}
