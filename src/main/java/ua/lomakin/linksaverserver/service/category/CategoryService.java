package ua.lomakin.linksaverserver.service.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ua.lomakin.linksaverserver.DTO.MessageResponseDTO;
import ua.lomakin.linksaverserver.DTO.categoryDTO.CategoryRequestDTO;
import ua.lomakin.linksaverserver.config.security.jwt.AuthTokenFilter;
import ua.lomakin.linksaverserver.config.security.jwt.JwtUtils;
import ua.lomakin.linksaverserver.persistance.entity.category.CategoryEntity;
import ua.lomakin.linksaverserver.persistance.entity.security.UserEntity;
import ua.lomakin.linksaverserver.persistance.repository.category.CategoryRepository;
import ua.lomakin.linksaverserver.persistance.repository.security.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;

@Service
@AllArgsConstructor
@Setter
@Getter
public class CategoryService {

    CategoryRepository categoryRepository;
    UserRepository userRepository;
    JwtUtils jwtUtils;
    AuthTokenFilter authTokenFilter;
    HttpServletRequest httpServletRequest;

    public MessageResponseDTO addCategoryService(CategoryRequestDTO categoryRequestDTO){

        if(categoryRepository.existsByCategoryName(categoryRequestDTO.getCategoryName())){
            return new MessageResponseDTO("Такая категория уже есть");
        }

        CategoryEntity categoryEntity = new CategoryEntity();

        String token = authTokenFilter.parseJwt(httpServletRequest);
        Long userId = jwtUtils.getUserIdFromJwtToken(token);
        UserEntity user = userRepository.getById(userId);

        categoryEntity.setCategoryName(categoryRequestDTO.getCategoryName());
        categoryEntity.setUser(user);

        categoryRepository.save(categoryEntity);

        return new MessageResponseDTO("Категория создана");
    }


}
