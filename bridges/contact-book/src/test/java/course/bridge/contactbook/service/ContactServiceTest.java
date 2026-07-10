package course.bridge.contactbook.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import course.bridge.contactbook.model.Contact;
import course.bridge.contactbook.repository.InMemoryContactRepository;

class ContactServiceTest {

    private ContactService service;

    @BeforeEach
    void setUp() {
        service = new ContactService(new InMemoryContactRepository());
    }

    @Test
    void addAndListContacts() {
        service.addContact("Alice", "alice@example.com", "555-0100");
        service.addContact("Bob", "bob@example.com", "");

        assertEquals(2, service.count());
        assertEquals("Alice", service.listContacts().getFirst().name());
        assertEquals("Bob", service.listContacts().get(1).name());
    }

    @Test
    void searchByNameIsCaseInsensitive() {
        Contact alice = service.addContact("Alice", "alice@example.com", "");
        service.addContact("Charlie", "charlie@example.com", "");

        assertEquals(1, service.searchByName("ali").size());
        assertEquals(alice.id(), service.searchByName("ALI").getFirst().id());
    }

    @Test
    void removeContact() {
        Contact contact = service.addContact("Dana", "dana@example.com", "");
        UUID id = contact.id();

        assertTrue(service.removeContact(id));
        assertFalse(service.findById(id).isPresent());
        assertEquals(0, service.count());
    }
}
