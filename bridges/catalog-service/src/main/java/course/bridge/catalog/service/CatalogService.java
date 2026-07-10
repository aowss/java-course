package course.bridge.catalog.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import course.bridge.catalog.model.Book;
import course.bridge.catalog.repository.BookRepository;

/**
 * Business logic for the book catalog.
 */
public final class CatalogService {

    private final BookRepository repository;

    public CatalogService(BookRepository repository) {
        this.repository = repository;
    }

    public Book addBook(String isbn, String title, String author) {
        if (repository.findByIsbn(isbn).isPresent()) {
            throw new IllegalArgumentException("ISBN already exists: " + isbn);
        }
        Book book = new Book(isbn, title, author);
        repository.save(book);
        return book;
    }

    public Optional<Book> findByIsbn(String isbn) {
        return repository.findByIsbn(isbn);
    }

    public List<Book> searchByTitle(String query) {
        String needle = query.toLowerCase(Locale.ROOT);
        List<Book> matches = new ArrayList<>();
        for (Book book : repository.findAll()) {
            if (book.title().toLowerCase(Locale.ROOT).contains(needle)) {
                matches.add(book);
            }
        }
        matches.sort(Comparator.comparing(Book::title, String.CASE_INSENSITIVE_ORDER));
        return matches;
    }

    public boolean removeBook(String isbn) {
        return repository.deleteByIsbn(isbn);
    }

    public int count() {
        return repository.findAll().size();
    }
}
