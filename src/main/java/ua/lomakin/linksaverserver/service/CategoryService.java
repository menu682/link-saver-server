package ua.lomakin.linksaverserver.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import ua.lomakin.linksaverserver.DTO.MessageResponseDTO;
import ua.lomakin.linksaverserver.DTO.categoryDTO.CategoryAddRequestDTO;
import ua.lomakin.linksaverserver.DTO.categoryDTO.CategoryPutRequestDTO;
import ua.lomakin.linksaverserver.persistance.entity.category.CategoryEntity;
import ua.lomakin.linksaverserver.persistance.entity.security.UserEntity;
import ua.lomakin.linksaverserver.persistance.repository.category.CategoryRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Setter
@Getter
public class CategoryService {

    CategoryRepository categoryRepository;
    UserService userService;

    public MessageResponseDTO addCategoryService(CategoryAddRequestDTO categoryAddRequestDTO){

        UserEntity user = userService.getCurrentUser();

        List<CategoryEntity> categoryEntityList = categoryRepository.findAllByUser(user);
        boolean categoryExist = categoryEntityList.stream()
                .anyMatch(categoryEntity -> categoryEntity.getCategoryName()
                        .equals(categoryAddRequestDTO.getCategoryName()));

        if(categoryExist){
            return new MessageResponseDTO("Нельзя создать, такая категория уже есть");
        }

        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setCategoryName(categoryAddRequestDTO.getCategoryName());
        categoryEntity.setUser(user);

        categoryRepository.save(categoryEntity);

        return new MessageResponseDTO("Категория создана");
    }


    public MessageResponseDTO updateCategoryService(CategoryPutRequestDTO categoryPutRequestDTO) {

        if(!categoryRepository.existsById(categoryPutRequestDTO.getCategoryId())){
            return new MessageResponseDTO("Такой категории нет");
        }

        UserEntity user = userService.getCurrentUser();

        List<CategoryEntity> categoryEntityList = categoryRepository.findAllByUser(user);
        boolean categoryExist = categoryEntityList.stream()
                .anyMatch(categoryEntity -> categoryEntity.getCategoryName()
                        .equals(categoryPutRequestDTO.getNewCategoryName()));

        if(categoryExist){
            return new MessageResponseDTO("Нельзя переименовать, такая категория уже есть");
        }

        //TODO доставать не из базы а из листа
        CategoryEntity categoryEntity =
                categoryRepository.findById(categoryPutRequestDTO.getCategoryId())
                        .orElseThrow(() -> new RuntimeException("Такой категории нет"));


        categoryEntity.setCategoryName(categoryPutRequestDTO.getNewCategoryName());
        categoryRepository.save(categoryEntity);
        return new MessageResponseDTO("Имя категории изменено");
    }
}
