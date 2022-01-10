package ua.lomakin.linksaverserver.service;

import org.springframework.stereotype.Service;
import ua.lomakin.linksaverserver.DTO.MessageResponseDTO;
import ua.lomakin.linksaverserver.DTO.linkDTO.LinkAddRequestDTO;
import ua.lomakin.linksaverserver.DTO.linkDTO.LinkChangeRequestDTO;
import ua.lomakin.linksaverserver.DTO.linkDTO.LinkDelRequestDTO;
import ua.lomakin.linksaverserver.persistance.entity.category.CategoryEntity;
import ua.lomakin.linksaverserver.persistance.entity.category.LinkEntity;
import ua.lomakin.linksaverserver.persistance.entity.security.UserEntity;
import ua.lomakin.linksaverserver.persistance.repository.CategoryRepository;
import ua.lomakin.linksaverserver.persistance.repository.LinkRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LinkService {

    LinkRepository linkRepository;
    CategoryRepository categoryRepository;
    UserService userService;

    public LinkService(LinkRepository linkRepository,
                       CategoryRepository categoryRepository,
                       UserService userService) {
        this.linkRepository = linkRepository;
        this.categoryRepository = categoryRepository;
        this.userService = userService;
    }

    public MessageResponseDTO addLink(LinkAddRequestDTO linkAddRequestDTO) {

        if(linkAddRequestDTO.getLinkName().isBlank()
                || linkAddRequestDTO.getUrl().isBlank()){
            throw new RuntimeException("Не все поля заполнены!");
        }

        UserEntity user = userService.getCurrentUser();
        CategoryEntity category = categoryRepository
                .findById(linkAddRequestDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Нет такой категории"));

        List<LinkEntity> linkEntityList = linkRepository.findAllByUserAndCategory(user, category);
        boolean linkExist = linkEntityList.stream()
                .anyMatch(linkEntity -> linkEntity.getName().equals(linkAddRequestDTO.getLinkName())
                || linkEntity.getUrl().equals(linkAddRequestDTO.getUrl()));

        if (linkExist){
            throw new RuntimeException(
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

    public MessageResponseDTO changeLink(LinkChangeRequestDTO linkChangeRequestDTO) {

        if(linkChangeRequestDTO.getNewLinkName().isBlank()
                || linkChangeRequestDTO.getNewUrl().isBlank()){
            throw new RuntimeException("Не все поля заполнены!");
        }

        UserEntity user = userService.getCurrentUser();
        CategoryEntity category = categoryRepository
                .findById(linkChangeRequestDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Нет такой категории"));

        List<LinkEntity> linkEntityList = linkRepository.findAllByUserAndCategory(user, category);
        boolean linkExist = linkEntityList.stream()
                .anyMatch(linkEntity ->
                        linkEntity.getName().equals(linkChangeRequestDTO.getNewLinkName())
                        || linkEntity.getUrl().equals(linkChangeRequestDTO.getNewUrl()));

        if (linkExist){
            throw new RuntimeException(
                    "Такая ссылка или название ссылки уже есть в этой категории");
        }

        LinkEntity linkEntity = linkEntityList.stream()
                .filter(link -> link.getUrl().equals(linkChangeRequestDTO.getOldUrl())
                && link.getId().equals(linkChangeRequestDTO.getLinkId())
                && link.getName().equals(linkChangeRequestDTO.getOldLinkName()))
                .findFirst().orElseThrow(() -> new RuntimeException("Такой ссылки нет"));

        linkEntity.setName(linkChangeRequestDTO.getNewLinkName());
        linkEntity.setUrl(linkChangeRequestDTO.getNewUrl());
        linkEntity.setCategory(category);
        linkEntity.setUser(user);

        linkRepository.save(linkEntity);

        return new MessageResponseDTO("Ссылка изменена");
    }

    public MessageResponseDTO deleteLink(LinkDelRequestDTO linkDelRequestDTO) {

        UserEntity user = userService.getCurrentUser();
        CategoryEntity category = categoryRepository
                .findById(linkDelRequestDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Нет такой категории"));

        Integer isDeleted = linkRepository.
                removeByIdAndCategoryAndUser(linkDelRequestDTO.getLinkId(), category, user);

        if (isDeleted > 0){
            return new MessageResponseDTO("Ссылка удалена");
        }

        throw new RuntimeException("Такой ссылки не существует");

    }
}
