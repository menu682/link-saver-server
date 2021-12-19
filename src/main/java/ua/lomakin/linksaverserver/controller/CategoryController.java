package ua.lomakin.linksaverserver.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.lomakin.linksaverserver.DTO.MessageResponseDTO;
import ua.lomakin.linksaverserver.DTO.categoryDTO.CategoryAddRequestDTO;
import ua.lomakin.linksaverserver.DTO.categoryDTO.CategoryPutRequestDTO;
import ua.lomakin.linksaverserver.service.category.CategoryService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/category/")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/")
    public MessageResponseDTO addCategory(@RequestBody CategoryAddRequestDTO categoryAddRequestDTO){
        return categoryService.addCategoryService(categoryAddRequestDTO);
    }

    @PutMapping("/")
    public MessageResponseDTO updateCategory(@RequestBody CategoryPutRequestDTO categoryPutRequestDTO){
        return categoryService.updateCategoryService(categoryPutRequestDTO);
    }

}
