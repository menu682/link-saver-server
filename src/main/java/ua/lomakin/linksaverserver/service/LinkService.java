package ua.lomakin.linksaverserver.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import ua.lomakin.linksaverserver.DTO.MessageResponseDTO;
import ua.lomakin.linksaverserver.DTO.linkDTO.LinkAddRequestDTO;
import ua.lomakin.linksaverserver.persistance.entity.category.CategoryEntity;
import ua.lomakin.linksaverserver.persistance.entity.category.LinkEntity;
import ua.lomakin.linksaverserver.persistance.entity.security.UserEntity;
import ua.lomakin.linksaverserver.persistance.repository.category.CategoryRepository;
import ua.lomakin.linksaverserver.persistance.repository.category.LinkRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Getter
@Setter
public class LinkService {

    LinkRepository linkRepository;
    CategoryRepository categoryRepository;
    UserService userService;


    public MessageResponseDTO addLink(LinkAddRequestDTO linkAddRequestDTO) {

        UserEntity user = userService.getCurrentUser();
        CategoryEntity category = categoryRepository
                .findById(linkAddRequestDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Нет такой категории"));

        List<LinkEntity> linkEntityList = linkRepository.findAllByUserAndCategory(user, category);
        boolean linkExist = linkEntityList.stream()
                .anyMatch(linkEntity -> linkEntity.getName().equals(linkAddRequestDTO.getLinkName())
                || linkEntity.getUrl().equals(linkAddRequestDTO.getUrl()));

        if (linkExist){
            return new MessageResponseDTO(
                    "Такая ссылка или название ссылки уже есть в этой категории");
        }

        LinkEntity linkEntity = new LinkEntity();
        linkEntity.setName(linkAddRequestDTO.getLinkName());
        linkEntity.setUrl(linkAddRequestDTO.getUrl());
        linkEntity.setCategory(category);
        linkEntity.setUser(user);

        linkRepository.save(linkEntity);

        return new MessageResponseDTO("Ссылка добавлена");
    }
}