package ua.lomakin.linksaverserver.persistance.entity.category;

import lombok.Data;
import ua.lomakin.linksaverserver.persistance.entity.BaseEntity;
import ua.lomakin.linksaverserver.persistance.entity.security.UserEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "link")
@Data
public class LinkEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    @ManyToOne
    CategoryEntity category;

    @ManyToOne
    UserEntity user;

}
