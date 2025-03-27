package fi.haagahelia.bookstore.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    List<Category> findAll();

    Optional<Category> findByName(String name);

    void deleteById(Long id);

}
