package course.bridge.catalog.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import course.bridge.catalog.model.Book;
import course.bridge.catalog.repository.InMemoryBookRepository;

class CatalogServiceTest {

    private CatalogService service;

    @BeforeEach
    void setUp() {
        service = new CatalogService(new InMemoryBookRepository());
    }

    @Test
    void addAndFindBook() {
        Book book = service.addBook("978-0134685991", "Effective Java", "Joshua Bloch");

        assertEquals(book, service.findByIsbn("978-0134685991").orElseThrow());
        assertEquals(1, service.count());
    }

    @Test
    void rejectsDuplicateIsbn() {
        service.addBook("978-0134685991", "Effective Java", "Joshua Bloch");

        assertThrows(IllegalArgumentException.class,
                () -> service.addBook("978-0134685991", "Duplicate", "Author"));
    }

    @Test
    void searchByTitle() {
        service.addBook("978-1", "Java Concurrency in Practice", "Brian Goetz");
        service.addBook("978-2", "Head First Java", "Kathy Sierra");

        assertEquals(1, service.searchByTitle("concurrency").size());
        assertEquals(2, service.searchByTitle("java").size());
    }

    @Test
    void removeBook() {
        service.addBook("978-1", "Test Book", "Author");

        assertTrue(service.removeBook("978-1"));
        assertTrue(service.findByIsbn("978-1").isEmpty());
    }
}
