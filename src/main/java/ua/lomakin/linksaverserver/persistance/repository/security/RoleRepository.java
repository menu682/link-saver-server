package ua.lomakin.linksaverserver.persistance.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lomakin.linksaverserver.persistance.ERole;
import ua.lomakin.linksaverserver.persistance.entity.security.RoleEntity;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByName(ERole name);

}
