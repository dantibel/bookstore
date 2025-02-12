package fi.haagahelia.bookstore.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class BookRepository
{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Transactional(readOnly=true)
    public List<Book> findAll() {
        String query = "SELECT * FROM Book JOIN Category ON Book.category = Category.id";
        return jdbcTemplate.query(query, new BookRowMapper());
    }

    @Transactional(readOnly=true)
    public Optional<Book> findById(Long id) {
        String query = "SELECT * FROM Book JOIN Category ON Book.category = Category.id WHERE Book.id = ?";
        List<Book> result = jdbcTemplate.query(query, new BookRowMapper(), id);
        assert result.size() <= 1;
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }

    @Transactional
    public boolean deleteById(Long id) {
        String query = "DELETE FROM Book WHERE Book.id = ?";
        return jdbcTemplate.update(query, id) == 1;
    }

    @Transactional
    public boolean save(Book book) {
        if (book.getId() == null) {
            String query = "INSERT INTO Book(id, title, author, publicationYear, category, isbn, price) VALUES (?, ?, ?, ?, ?, ?, ?)";
            return jdbcTemplate.update(query, book.getTitle(), book.getAuthor(), book.getYear(), book.getCategory().getId(), book.getIsbn(), book.getPrice()) == 1;
        } else {
            String query = "UPDATE Book SET title = ?, author = ?, publicationYear = ?, category = ?, isbn = ?, price = ? WHERE id = ?";
            return jdbcTemplate.update(query, book.getTitle(), book.getAuthor(), book.getYear(), book.getCategory().getId(), book.getIsbn(), book.getPrice(), book.getId()) == 1;
        }
        
    }
}
