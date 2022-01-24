package ua.lomakin.linksaverserver.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.lomakin.linksaverserver.config.security.UserDetailsImpl;
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
    public CategoryResponseDTO getAllCategories(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl){
        return categoryService.getAllCategories(userDetailsImpl.getUser());
    }

    @GetMapping("/{id}")
    public CategoryAllLinksResponseDTO getLinksFromCategory(@PathVariable Long id,
                                        @AuthenticationPrincipal UserDetailsImpl userDetailsImpl){
        Long categoryId = Long.valueOf(id);
        return categoryService.getLinksFromCategory(categoryId, userDetailsImpl.getUser());
    }

    @PostMapping()
    public MessageResponseDTO addCategory(@RequestBody CategoryAddRequestDTO categoryAddRequestDTO,
                                        @AuthenticationPrincipal UserDetailsImpl userDetailsImpl){
        return categoryService.addCategory(categoryAddRequestDTO, userDetailsImpl.getUser());
    }

    @PutMapping()
    public MessageResponseDTO updateCategory(@RequestBody CategoryPutRequestDTO categoryPutRequestDTO,
                                             @AuthenticationPrincipal UserDetailsImpl userDetailsImp){
        return categoryService.updateCategory(categoryPutRequestDTO, userDetailsImp.getUser());
    }

    @DeleteMapping()
    public MessageResponseDTO deleteCategory(@RequestBody CategoryDelRequestDTO categoryDelRequestDTO,
                                             @AuthenticationPrincipal UserDetailsImpl userDetailsImp){
        return categoryService.deleteCategory(categoryDelRequestDTO, userDetailsImp.getUser());
    }

}
