package ua.lomakin.linksaverserver.DTO.categoryDTO;

import lombok.Data;

@Data
public class CategoryPutRequestDTO {

    private String categoryName;
    private String newCategoryName;

}
