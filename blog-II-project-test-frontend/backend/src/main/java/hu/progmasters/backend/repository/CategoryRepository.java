package hu.progmasters.backend.repository;

import hu.progmasters.backend.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("select c.name from Category c where c.name = :name")
    String findCategoryName(String name);
}
