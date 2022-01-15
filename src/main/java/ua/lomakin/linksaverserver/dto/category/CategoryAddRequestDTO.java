package ua.lomakin.linksaverserver.dto.category;


public class CategoryAddRequestDTO {

    private String categoryName;

    public CategoryAddRequestDTO() {
    }

    public CategoryAddRequestDTO(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
