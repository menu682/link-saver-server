package ua.lomakin.linksaverserver.persistance.repository.category;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.lomakin.linksaverserver.persistance.entity.category.LinkEntity;

public interface LinkRepository extends JpaRepository<LinkEntity, Long> {



}
