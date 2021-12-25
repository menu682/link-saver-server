package ua.lomakin.linksaverserver.service;

import org.springframework.stereotype.Service;
import ua.lomakin.linksaverserver.DTO.MessageResponseDTO;
import ua.lomakin.linksaverserver.DTO.categoryDTO.CategoryAddRequestDTO;
import ua.lomakin.linksaverserver.DTO.categoryDTO.CategoryAllLinksResponseDTO;
import ua.lomakin.linksaverserver.DTO.categoryDTO.CategoryDTO;
import ua.lomakin.linksaverserver.DTO.categoryDTO.CategoryDelRequestDTO;
import ua.lomakin.linksaverserver.DTO.categoryDTO.CategoryPutRequestDTO;
import ua.lomakin.linksaverserver.DTO.categoryDTO.CategoryResponseDTO;
import ua.lomakin.linksaverserver.DTO.linkDTO.LinkDTO;
import ua.lomakin.linksaverserver.persistance.entity.category.CategoryEntity;
import ua.lomakin.linksaverserver.persistance.entity.security.UserEntity;
import ua.lomakin.linksaverserver.persistance.repository.CategoryRepository;
import ua.lomakin.linksaverserver.persistance.repository.LinkRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryService {

    CategoryRepository categoryRepository;
    LinkRepository linkRepository;
    UserService userService;

    public CategoryService(CategoryRepository categoryRepository,
                           LinkRepository linkRepository,
                           UserService userService) {
        this.categoryRepository = categoryRepository;
        this.linkRepository = linkRepository;
        this.userService = userService;
    }

    public CategoryResponseDTO getAllCategories() {

        UserEntity user = userService.getCurrentUser();

        List<CategoryEntity> categoryEntityList = categoryRepository.findAllByUser(user);

        List<CategoryDTO> categoryDTOList = categoryEntityList.stream()
                .map(categoryEntity -> {
                    CategoryDTO categoryDTO = new CategoryDTO();
                    categoryDTO.setId(categoryEntity.getId());
                    categoryDTO.setCategoryName(categoryEntity.getCategoryName());
                    return categoryDTO;
                }).collect(Collectors.toList());

        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setCategories(categoryDTOList);
        categoryResponseDTO.setMessage("Все категории загружены");

        return categoryResponseDTO;
    }

    public CategoryAllLinksResponseDTO getLinksFromCategory(Long categoryId){

        CategoryEntity category =
                categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new RuntimeException("Такой категории нет"));

        UserEntity user = userService.getCurrentUser();

        List<LinkDTO> linkDTOList = linkRepository.findAllByUserAndCategory(user, category)
                .stream().map(linkEntity -> {
                    LinkDTO linkDTO = new LinkDTO();
                    linkDTO.setLinkId(linkEntity.getId());
                    linkDTO.setLinkName(linkEntity.getName());
                    linkDTO.setLinkUrl(linkEntity.getUrl());
                    return linkDTO;
                }).collect(Collectors.toList());

        CategoryAllLinksResponseDTO categoryAllLinksResponseDTO = new CategoryAllLinksResponseDTO();
        categoryAllLinksResponseDTO.setCategoryId(categoryId);
        categoryAllLinksResponseDTO.setCategoryName(category.getCategoryName());
        categoryAllLinksResponseDTO.setCategoryLinks(linkDTOList);
        categoryAllLinksResponseDTO.setMessage("Все ссылки загружены.");

        return categoryAllLinksResponseDTO;

    }


    public MessageResponseDTO addCategoryService(CategoryAddRequestDTO categoryAddRequestDTO){

        UserEntity user = userService.getCurrentUser();

        List<CategoryEntity> categoryEntityList = categoryRepository.findAllByUser(user);
        boolean categoryExist = categoryEntityList.stream()
                .anyMatch(categoryEntity -> categoryEntity.getCategoryName()
                        .equals(categoryAddRequestDTO.getCategoryName()));

        if(categoryExist){
            throw new RuntimeException("Нельзя создать, такая категория уже есть");
        }

        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setCategoryName(categoryAddRequestDTO.getCategoryName());
        categoryEntity.setUser(user);

        categoryRepository.save(categoryEntity);

        return new MessageResponseDTO("Категория создана");
    }


    public MessageResponseDTO updateCategoryService(CategoryPutRequestDTO categoryPutRequestDTO) {

        if(!categoryRepository.existsById(categoryPutRequestDTO.getCategoryId())){
            throw new RuntimeException("Такой категории нет");
        }

        UserEntity user = userService.getCurrentUser();

        List<CategoryEntity> categoryEntityList = categoryRepository.findAllByUser(user);
        boolean categoryExist = categoryEntityList.stream()
                .anyMatch(categoryEntity -> categoryEntity.getCategoryName()
                        .equals(categoryPutRequestDTO.getNewCategoryName()));

        if(categoryExist){
            throw new RuntimeException("Нельзя переименовать, такая категория уже есть");
        }

        CategoryEntity categoryEntity = categoryEntityList.stream()
                .filter(category -> category.getId().equals(categoryPutRequestDTO.getCategoryId()))
                .findFirst().orElseThrow(() -> new RuntimeException("Такой категории нет"));

        categoryEntity.setCategoryName(categoryPutRequestDTO.getNewCategoryName());
        categoryRepository.save(categoryEntity);
        return new MessageResponseDTO("Имя категории изменено");
    }

    public MessageResponseDTO deleteCategory(CategoryDelRequestDTO categoryDelRequestDTO){

        CategoryEntity category =
        categoryRepository.findById(categoryDelRequestDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Такой категории нет"));

        UserEntity user = userService.getCurrentUser();

        Integer delLinks = linkRepository.removeAllByCategoryAndUser(category, user);
        Integer delCategory = categoryRepository.removeByIdAndUser(categoryDelRequestDTO.getCategoryId(), user);

        if (delCategory > 0){

            return new MessageResponseDTO("Категория удалена. В категории удалено " + delLinks + " ссылок!");
        }

        throw new RuntimeException("Такая категори не существует.");
    }


}
