package michaln.expensetracker.repository;

import michaln.expensetracker.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    public boolean existsCategoryByName(String name);
}
