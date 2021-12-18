package ua.lomakin.linksaverserver.DTO.categoryDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class CategoryResponseDTO {

    private List<CategoryDTO> categories;
    private String message;

}
