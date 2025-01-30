package fi.haagahelia.bookstore.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fi.haagahelia.bookstore.domain.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByTitle(String title);

    List<Book> findByAuthor(String author);

    List<Book> findByIsbn(String Isbn);
}
