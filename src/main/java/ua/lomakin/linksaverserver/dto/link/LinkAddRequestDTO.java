package ua.lomakin.linksaverserver.dto.link;

public class LinkAddRequestDTO {

    private String linkName;
    private String url;
    private Long categoryId;

    public LinkAddRequestDTO() {
    }

    public LinkAddRequestDTO(String linkName, String url, Long categoryId) {
        this.linkName = linkName;
        this.url = url;
        this.categoryId = categoryId;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
