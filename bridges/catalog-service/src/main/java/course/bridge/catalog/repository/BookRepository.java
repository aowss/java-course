package course.bridge.catalog.repository;

import java.util.List;
import java.util.Optional;

import course.bridge.catalog.model.Book;

/**
 * Persistence abstraction for books.
 */
public interface BookRepository {

    void save(Book book);

    Optional<Book> findByIsbn(String isbn);

    List<Book> findAll();

    boolean deleteByIsbn(String isbn);
}
