package course.bridge.catalog.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import course.bridge.catalog.model.Book;
import course.bridge.catalog.repository.BookRepository;

@ExtendWith(MockitoExtension.class)
class CatalogServiceMockTest {

    @Mock
    private BookRepository repository;

    @InjectMocks
    private CatalogService service;

    @Test
    void addBookSavesWhenIsbnIsNew() {
        when(repository.findByIsbn("978-1")).thenReturn(Optional.empty());

        Book saved = service.addBook("978-1", "Test Driven Development", "Kent Beck");

        verify(repository).save(saved);
        assertEquals("978-1", saved.isbn());
    }

    @Test
    void addBookDoesNotSaveDuplicateIsbn() {
        when(repository.findByIsbn("978-1")).thenReturn(Optional.of(
                new Book("978-1", "Existing", "Author")));

        assertThrows(IllegalArgumentException.class,
                () -> service.addBook("978-1", "Duplicate", "Author"));
        verify(repository, never()).save(any());
    }
}
