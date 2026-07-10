package course.bridge.catalog.repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import course.bridge.catalog.model.Book;

/**
 * In-memory book catalog.
 */
public final class InMemoryBookRepository implements BookRepository {

    private final Map<String, Book> books = new LinkedHashMap<>();

    @Override
    public void save(Book book) {
        books.put(book.isbn(), book);
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return Optional.ofNullable(books.get(isbn));
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(books.values());
    }

    @Override
    public boolean deleteByIsbn(String isbn) {
        return books.remove(isbn) != null;
    }
}
