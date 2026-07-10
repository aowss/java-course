package course.bridge.catalog.model;

import java.util.Objects;

/**
 * A book in the catalog.
 */
public record Book(String isbn, String title, String author) {

    public Book {
        Objects.requireNonNull(isbn, "isbn must not be null");
        Objects.requireNonNull(title, "title must not be null");
        Objects.requireNonNull(author, "author must not be null");
        if (isbn.isBlank() || title.isBlank() || author.isBlank()) {
            throw new IllegalArgumentException("isbn, title, and author must not be blank");
        }
    }
}
