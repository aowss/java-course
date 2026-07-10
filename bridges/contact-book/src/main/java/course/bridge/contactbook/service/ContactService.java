package course.bridge.contactbook.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

import course.bridge.contactbook.model.Contact;
import course.bridge.contactbook.repository.ContactRepository;

/**
 * Business logic for managing contacts.
 */
public final class ContactService {

    private final ContactRepository repository;

    public ContactService(ContactRepository repository) {
        this.repository = repository;
    }

    public Contact addContact(String name, String email, String phone) {
        Contact contact = Contact.create(name, email, phone);
        repository.save(contact);
        return contact;
    }

    public List<Contact> listContacts() {
        List<Contact> contacts = new ArrayList<>(repository.findAll());
        contacts.sort(Comparator.comparing(Contact::name, String.CASE_INSENSITIVE_ORDER));
        return contacts;
    }

    public Optional<Contact> findById(UUID id) {
        return repository.findById(id);
    }

    public List<Contact> searchByName(String query) {
        String needle = query.toLowerCase(Locale.ROOT);
        List<Contact> matches = new ArrayList<>();
        for (Contact contact : repository.findAll()) {
            if (contact.name().toLowerCase(Locale.ROOT).contains(needle)) {
                matches.add(contact);
            }
        }
        matches.sort(Comparator.comparing(Contact::name, String.CASE_INSENSITIVE_ORDER));
        return matches;
    }

    public boolean removeContact(UUID id) {
        return repository.deleteById(id);
    }

    public int count() {
        return repository.findAll().size();
    }
}
