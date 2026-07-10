package course.bridge.contactbook.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import course.bridge.contactbook.model.Contact;

/**
 * Persistence abstraction for contacts.
 */
public interface ContactRepository {

    void save(Contact contact);

    Optional<Contact> findById(UUID id);

    List<Contact> findAll();

    boolean deleteById(UUID id);
}
