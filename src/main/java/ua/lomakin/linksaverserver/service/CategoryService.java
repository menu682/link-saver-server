package ua.lomakin.linksaverserver.service;

import org.springframework.stereotype.Service;
import ua.lomakin.linksaverserver.config.security.UserDetailsImpl;
import ua.lomakin.linksaverserver.dto.MessageResponseDTO;
import ua.lomakin.linksaverserver.dto.category.CategoryAddRequestDTO;
import ua.lomakin.linksaverserver.dto.category.CategoryAllLinksResponseDTO;
import ua.lomakin.linksaverserver.dto.category.CategoryDTO;
import ua.lomakin.linksaverserver.dto.category.CategoryDelRequestDTO;
import ua.lomakin.linksaverserver.dto.category.CategoryPutRequestDTO;
import ua.lomakin.linksaverserver.dto.category.CategoryResponseDTO;
import ua.lomakin.linksaverserver.dto.link.LinkDTO;
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

    public CategoryService(CategoryRepository categoryRepository,
                           LinkRepository linkRepository) {
        this.categoryRepository = categoryRepository;
        this.linkRepository = linkRepository;
    }

    public CategoryResponseDTO getAllCategories(UserEntity user) {

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
        categoryResponseDTO.setMessage("?????? ?????????????????? ??????????????????");

        return categoryResponseDTO;
    }

    public CategoryAllLinksResponseDTO getLinksFromCategory(Long categoryId, UserEntity user){

        CategoryEntity category =
                categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new RuntimeException("?????????? ?????????????????? ??????"));

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
        categoryAllLinksResponseDTO.setMessage("?????? ???????????? ??????????????????.");

        return categoryAllLinksResponseDTO;

    }


    public MessageResponseDTO addCategory(CategoryAddRequestDTO categoryAddRequestDTO,
                                          UserEntity user){

        if(categoryAddRequestDTO.getCategoryName().isBlank()){
            throw new RuntimeException("?????? ???? ???????????? ???????? ????????????!");
        }

        List<CategoryEntity> categoryEntityList = categoryRepository.findAllByUser(user);
        boolean categoryExist = categoryEntityList.stream()
                .anyMatch(categoryEntity -> categoryEntity.getCategoryName()
                        .equals(categoryAddRequestDTO.getCategoryName()));

        if(categoryExist){
            throw new RuntimeException("???????????? ??????????????, ?????????? ?????????????????? ?????? ????????");
        }

        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setCategoryName(categoryAddRequestDTO.getCategoryName());
        categoryEntity.setUser(user);

        categoryRepository.save(categoryEntity);

        return new MessageResponseDTO("?????????????????? ??????????????");
    }


    public MessageResponseDTO updateCategory(CategoryPutRequestDTO categoryPutRequestDTO,
                                             UserEntity user) {

        if(categoryPutRequestDTO.getNewCategoryName().isBlank()){
            throw new RuntimeException("?????? ???? ???????????? ???????? ????????????!");
        }

        if(!categoryRepository.existsById(categoryPutRequestDTO.getCategoryId())){
            throw new RuntimeException("?????????? ?????????????????? ??????");
        }

        List<CategoryEntity> categoryEntityList = categoryRepository.findAllByUser(user);
        boolean categoryExist = categoryEntityList.stream()
                .anyMatch(categoryEntity -> categoryEntity.getCategoryName()
                        .equals(categoryPutRequestDTO.getNewCategoryName()));

        if(categoryExist){
            throw new RuntimeException("???????????? ??????????????????????????, ?????????? ?????????????????? ?????? ????????");
        }

        CategoryEntity categoryEntity = categoryEntityList.stream()
                .filter(category -> category.getId().equals(categoryPutRequestDTO.getCategoryId()))
                .findFirst().orElseThrow(() -> new RuntimeException("?????????? ?????????????????? ??????"));

        categoryEntity.setCategoryName(categoryPutRequestDTO.getNewCategoryName());
        categoryRepository.save(categoryEntity);
        return new MessageResponseDTO("?????? ?????????????????? ????????????????");
    }

    public MessageResponseDTO deleteCategory(CategoryDelRequestDTO categoryDelRequestDTO,
                                             UserEntity user){

        CategoryEntity category =
        categoryRepository.findById(categoryDelRequestDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("?????????? ?????????????????? ??????"));

        Integer delLinks = linkRepository.removeAllByCategoryAndUser(category, user);
        Integer delCategory = categoryRepository.removeByIdAndUser(categoryDelRequestDTO.getCategoryId(), user);

        if (delCategory > 0){

            return new MessageResponseDTO("?????????????????? ??????????????. ?? ?????????????????? ?????????????? " + delLinks + " ????????????!");
        }

        throw new RuntimeException("?????????? ???????????????? ???? ????????????????????.");
    }

}
