package ua.lomakin.linksaverserver.DTO.linkDTO;


import lombok.Data;

@Data
public class LinkChangeRequestDTO {

    private String oldLinkName;
    private String oldUrl;
    private Long categoryId;
    private Long linkId;
    private String newLinkName;
    private String newUrl;

}
