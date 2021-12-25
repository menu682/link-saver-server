package ua.lomakin.linksaverserver.DTO.linkDTO;

public class LinkDelRequestDTO {

    private Long categoryId;
    private Long linkId;

    public LinkDelRequestDTO() {
    }

    public LinkDelRequestDTO(Long categoryId, Long linkId) {
        this.categoryId = categoryId;
        this.linkId = linkId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getLinkId() {
        return linkId;
    }

    public void setLinkId(Long linkId) {
        this.linkId = linkId;
    }
}
