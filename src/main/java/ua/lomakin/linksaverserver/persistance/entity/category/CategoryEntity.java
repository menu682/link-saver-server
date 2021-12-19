package ua.lomakin.linksaverserver.persistance.entity.category;


import lombok.Data;
import ua.lomakin.linksaverserver.persistance.entity.BaseEntity;
import ua.lomakin.linksaverserver.persistance.entity.security.UserEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "category")
@Data
public class CategoryEntity extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String categoryName;

    @ManyToOne
    private UserEntity user;

}
