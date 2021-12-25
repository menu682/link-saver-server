package ua.lomakin.linksaverserver.DTO.linkDTO;


public class LinkChangeRequestDTO {

    private String oldLinkName;
    private String oldUrl;
    private Long categoryId;
    private Long linkId;
    private String newLinkName;
    private String newUrl;

    public LinkChangeRequestDTO() {
    }

    public LinkChangeRequestDTO(String oldLinkName,
                                String oldUrl,
                                Long categoryId,
                                Long linkId,
                                String newLinkName,
                                String newUrl) {
        this.oldLinkName = oldLinkName;
        this.oldUrl = oldUrl;
        this.categoryId = categoryId;
        this.linkId = linkId;
        this.newLinkName = newLinkName;
        this.newUrl = newUrl;
    }

    public String getOldLinkName() {
        return oldLinkName;
    }

    public void setOldLinkName(String oldLinkName) {
        this.oldLinkName = oldLinkName;
    }

    public String getOldUrl() {
        return oldUrl;
    }

    public void setOldUrl(String oldUrl) {
        this.oldUrl = oldUrl;
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

    public String getNewLinkName() {
        return newLinkName;
    }

    public void setNewLinkName(String newLinkName) {
        this.newLinkName = newLinkName;
    }

    public String getNewUrl() {
        return newUrl;
    }

    public void setNewUrl(String newUrl) {
        this.newUrl = newUrl;
    }
}
