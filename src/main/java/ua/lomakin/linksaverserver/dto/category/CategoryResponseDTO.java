package ua.lomakin.linksaverserver.dto.category;

import java.util.List;

public class CategoryResponseDTO {

    private List<CategoryDTO> categories;
    private String message;

    public CategoryResponseDTO() {
    }

    public CategoryResponseDTO(List<CategoryDTO> categories, String message) {
        this.categories = categories;
        this.message = message;
    }

    public List<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDTO> categories) {
        this.categories = categories;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
