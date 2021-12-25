package ua.lomakin.linksaverserver.persistance.entity.category;


import ua.lomakin.linksaverserver.persistance.entity.BaseEntity;
import ua.lomakin.linksaverserver.persistance.entity.security.UserEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;


@Entity
@Table(name = "category")
public class CategoryEntity extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String categoryName;

    @ManyToOne
    private UserEntity user;

    public CategoryEntity() {
    }

    public CategoryEntity(Long id,
                          LocalDateTime created,
                          LocalDateTime updated,
                          String categoryName,
                          UserEntity user) {
        super(id, created, updated);
        this.categoryName = categoryName;
        this.user = user;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

}
