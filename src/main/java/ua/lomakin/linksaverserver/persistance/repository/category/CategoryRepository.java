package ua.lomakin.linksaverserver.persistance.repository.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lomakin.linksaverserver.persistance.entity.category.CategoryEntity;

import java.util.Optional;


@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    Boolean existsByCategoryName(String categoryName);

    Optional<CategoryEntity> findByCategoryName(String categoryName);
}
