package ua.lomakin.linksaverserver.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.lomakin.linksaverserver.DTO.MessageResponseDTO;
import ua.lomakin.linksaverserver.DTO.linkDTO.LinkAddRequestDTO;
import ua.lomakin.linksaverserver.DTO.linkDTO.LinkChangeRequestDTO;
import ua.lomakin.linksaverserver.DTO.linkDTO.LinkDelRequestDTO;
import ua.lomakin.linksaverserver.service.LinkService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/link")
public class LinkController {

    LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping()
    public MessageResponseDTO addLink(@RequestBody LinkAddRequestDTO linkAddRequestDTO){
        return linkService.addLink(linkAddRequestDTO);
    }

    @PutMapping()
    public MessageResponseDTO changeLink(@RequestBody LinkChangeRequestDTO linkChangeRequestDTO){
        return linkService.changeLink(linkChangeRequestDTO);
    }

    @DeleteMapping()
    public MessageResponseDTO deleteLink(@RequestBody LinkDelRequestDTO linkDelRequestDTO){
        return linkService.deleteLink(linkDelRequestDTO);
    }

}
