package ua.lomakin.linksaverserver.DTO.linkDTO;

import lombok.Data;

@Data
public class LinkAddRequestDTO {

    private String name;
    private String url;
    private Long categoryId;
    private Long userId;

}
