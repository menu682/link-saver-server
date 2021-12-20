package ua.lomakin.linksaverserver.DTO.categoryDTO;


import lombok.Data;
import ua.lomakin.linksaverserver.DTO.linkDTO.LinkDTO;

import java.util.List;

@Data
public class CategoryAllLinksResponseDTO {

    private Long categoryId;
    private String categoryName;
    private List<LinkDTO> categoryLinks;
    private String message;

}
