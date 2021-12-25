package ua.lomakin.linksaverserver.DTO.categoryDTO;

public class CategoryDelRequestDTO {

    Long categoryId;

    public CategoryDelRequestDTO() {
    }

    public CategoryDelRequestDTO(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
