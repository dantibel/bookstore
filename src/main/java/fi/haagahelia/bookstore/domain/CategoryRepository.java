package fi.haagahelia.bookstore.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CategoryRepository
{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Transactional(readOnly=true)
    public List<Category> findAll() {
        String query = "SELECT * FROM Category";
        return jdbcTemplate.query(query, new CategoryRowMapper());
    }

    @Transactional(readOnly=true)
    public Optional<Category> findByName(String name) {
        String query = "SELECT * FROM Category WHERE name = ?";
        List<Category> result = jdbcTemplate.query(query, new CategoryRowMapper(), name);
        assert result.size() <= 1;
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }

    @Transactional
    public boolean save(Category category) {
        if (category.getId() == null) {
            String query = "INSERT INTO Category(name) VALUES (?)";
            return jdbcTemplate.update(query, category.getName()) == 1;
        }
        return false;
    }
}
