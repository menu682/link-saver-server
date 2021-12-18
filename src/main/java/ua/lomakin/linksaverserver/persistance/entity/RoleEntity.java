package ua.lomakin.linksaverserver.persistance.entity;

import lombok.Data;
import ua.lomakin.linksaverserver.persistance.ERole;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "role")
@Data
public class RoleEntity extends BaseEntity{

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private ERole name;

}
