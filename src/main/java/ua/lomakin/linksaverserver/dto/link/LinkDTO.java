package ua.lomakin.linksaverserver.dto.link;

public class LinkDTO {

    private Long linkId;
    private String linkName;
    private String linkUrl;

    public LinkDTO() {
    }

    public LinkDTO(Long linkId, String linkName, String linkUrl) {
        this.linkId = linkId;
        this.linkName = linkName;
        this.linkUrl = linkUrl;
    }

    public Long getLinkId() {
        return linkId;
    }

    public void setLinkId(Long linkId) {
        this.linkId = linkId;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
}
