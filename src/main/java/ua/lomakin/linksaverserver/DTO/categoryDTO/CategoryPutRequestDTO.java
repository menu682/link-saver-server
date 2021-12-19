package ua.lomakin.linksaverserver.DTO.categoryDTO;

import lombok.Data;

@Data
public class CategoryPutRequestDTO {

    private Long categoryId;
    private String newCategoryName;

}
