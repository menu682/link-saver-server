package ua.lomakin.linksaverserver.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lomakin.linksaverserver.persistance.entity.category.CategoryEntity;
import ua.lomakin.linksaverserver.persistance.entity.security.UserEntity;

import java.util.List;
import java.util.Optional;


@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    //Boolean existsByCategoryName(String categoryName);

    boolean existsById(Long id);

    Optional<CategoryEntity> findById(Long id);

    //@Query(value = "from CategoryEntity as category where category.user = ?1")
    List<CategoryEntity> findAllByUser(UserEntity user);

    Integer removeByIdAndUser(Long categoryId, UserEntity user);

}
