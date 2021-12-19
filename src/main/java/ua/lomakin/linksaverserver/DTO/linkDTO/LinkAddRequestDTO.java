package ua.lomakin.linksaverserver.DTO.linkDTO;

import lombok.Data;

@Data
public class LinkAddRequestDTO {

    private String linkName;
    private String url;
    private Long categoryId;

}
