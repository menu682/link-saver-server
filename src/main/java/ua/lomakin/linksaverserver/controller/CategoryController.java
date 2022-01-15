package ua.lomakin.linksaverserver.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.lomakin.linksaverserver.dto.MessageResponseDTO;
import ua.lomakin.linksaverserver.dto.category.CategoryAddRequestDTO;
import ua.lomakin.linksaverserver.dto.category.CategoryAllLinksResponseDTO;
import ua.lomakin.linksaverserver.dto.category.CategoryDelRequestDTO;
import ua.lomakin.linksaverserver.dto.category.CategoryPutRequestDTO;
import ua.lomakin.linksaverserver.dto.category.CategoryResponseDTO;
import ua.lomakin.linksaverserver.service.CategoryService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public CategoryResponseDTO getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public CategoryAllLinksResponseDTO getLinksFromCategory(@PathVariable Long id){
        Long categoryId = Long.valueOf(id);
        return categoryService.getLinksFromCategory(categoryId);
    }

    @PostMapping()
    public MessageResponseDTO addCategory(@RequestBody CategoryAddRequestDTO categoryAddRequestDTO){
        return categoryService.addCategory(categoryAddRequestDTO);
    }

    @PutMapping()
    public MessageResponseDTO updateCategory(@RequestBody CategoryPutRequestDTO categoryPutRequestDTO){
        return categoryService.updateCategory(categoryPutRequestDTO);
    }

    @DeleteMapping()
    public MessageResponseDTO deleteCategory(@RequestBody CategoryDelRequestDTO categoryDelRequestDTO){
        return categoryService.deleteCategory(categoryDelRequestDTO);
    }

}
