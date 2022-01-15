package ua.lomakin.linksaverserver.dto.category;

public class CategoryPutRequestDTO {

    private Long categoryId;
    private String newCategoryName;

    public CategoryPutRequestDTO() {
    }

    public CategoryPutRequestDTO(Long categoryId, String newCategoryName) {
        this.categoryId = categoryId;
        this.newCategoryName = newCategoryName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getNewCategoryName() {
        return newCategoryName;
    }

    public void setNewCategoryName(String newCategoryName) {
        this.newCategoryName = newCategoryName;
    }
}
