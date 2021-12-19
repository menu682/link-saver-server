package ua.lomakin.linksaverserver.DTO.categoryDTO;

import lombok.Data;

import java.util.List;

@Data
public class CategoryResponseDTO {

    private List<CategoryDTO> categories;
    private String message;

}
