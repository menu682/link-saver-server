package ua.lomakin.linksaverserver.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.lomakin.linksaverserver.persistance.entity.category.CategoryEntity;
import ua.lomakin.linksaverserver.persistance.entity.category.LinkEntity;
import ua.lomakin.linksaverserver.persistance.entity.security.UserEntity;

import java.util.List;

public interface LinkRepository extends JpaRepository<LinkEntity, Long> {

    //@Query(value = "from LinkEntity as link where link.user = ?1 and link.category = ?2")
    List<LinkEntity> findAllByUserAndCategory(UserEntity user, CategoryEntity category);

    Integer removeByIdAndCategoryAndUser(Long linkId, CategoryEntity category, UserEntity user);

    Integer removeAllByCategoryAndUser(CategoryEntity category, UserEntity user);

}
