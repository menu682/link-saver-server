package ua.lomakin.linksaverserver.service.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import ua.lomakin.linksaverserver.DTO.MessageResponseDTO;
import ua.lomakin.linksaverserver.DTO.categoryDTO.CategoryRequestDTO;

@Service
@AllArgsConstructor
@Setter
@Getter
public class CategoryService {


    public MessageResponseDTO addCategoryService(CategoryRequestDTO categoryRequestDTO){
        return new MessageResponseDTO("типа добавлена категория " + categoryRequestDTO.getCategoryName());
    }


}
