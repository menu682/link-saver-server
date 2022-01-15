package ua.lomakin.linksaverserver.dto.category;

import ua.lomakin.linksaverserver.dto.link.LinkDTO;
import java.util.List;


public class CategoryAllLinksResponseDTO {

    private Long categoryId;
    private String categoryName;
    private List<LinkDTO> categoryLinks;
    private String message;

    public CategoryAllLinksResponseDTO() {
    }

    public CategoryAllLinksResponseDTO(Long categoryId, String categoryName, List<LinkDTO> categoryLinks, String message) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryLinks = categoryLinks;
        this.message = message;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<LinkDTO> getCategoryLinks() {
        return categoryLinks;
    }

    public void setCategoryLinks(List<LinkDTO> categoryLinks) {
        this.categoryLinks = categoryLinks;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
