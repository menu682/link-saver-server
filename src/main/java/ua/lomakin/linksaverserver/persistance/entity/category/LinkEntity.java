package ua.lomakin.linksaverserver.persistance.entity.category;

import ua.lomakin.linksaverserver.persistance.entity.BaseEntity;
import ua.lomakin.linksaverserver.persistance.entity.security.UserEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "link")
public class LinkEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    @ManyToOne
    CategoryEntity category;

    @ManyToOne
    UserEntity user;

    public LinkEntity() {
    }

    public LinkEntity(Long id,
                      LocalDateTime created,
                      LocalDateTime updated,
                      String name,
                      String url,
                      CategoryEntity category,
                      UserEntity user) {
        super(id, created, updated);
        this.name = name;
        this.url = url;
        this.category = category;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
