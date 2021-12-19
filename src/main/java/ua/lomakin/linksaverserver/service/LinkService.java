package ua.lomakin.linksaverserver.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import ua.lomakin.linksaverserver.persistance.repository.category.CategoryRepository;
import ua.lomakin.linksaverserver.persistance.repository.category.LinkRepository;

@Service
@AllArgsConstructor
@Getter
@Setter
public class LinkService {

    LinkRepository linkRepository;
    CategoryRepository categoryRepository;
    UserService userService;


}
