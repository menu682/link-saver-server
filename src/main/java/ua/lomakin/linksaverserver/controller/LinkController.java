package ua.lomakin.linksaverserver.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.lomakin.linksaverserver.config.security.UserDetailsImpl;
import ua.lomakin.linksaverserver.dto.MessageResponseDTO;
import ua.lomakin.linksaverserver.dto.link.LinkAddRequestDTO;
import ua.lomakin.linksaverserver.dto.link.LinkChangeRequestDTO;
import ua.lomakin.linksaverserver.dto.link.LinkDelRequestDTO;
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
    public MessageResponseDTO addLink(@RequestBody LinkAddRequestDTO linkAddRequestDTO,
                                      @AuthenticationPrincipal UserDetailsImpl userDetailsImp){
        return linkService.addLink(linkAddRequestDTO, userDetailsImp.getUser());
    }

    @PutMapping()
    public MessageResponseDTO changeLink(@RequestBody LinkChangeRequestDTO linkChangeRequestDTO,
                                         @AuthenticationPrincipal UserDetailsImpl userDetailsImp){
        return linkService.changeLink(linkChangeRequestDTO, userDetailsImp.getUser());
    }

    @DeleteMapping()
    public MessageResponseDTO deleteLink(@RequestBody LinkDelRequestDTO linkDelRequestDTO,
                                         @AuthenticationPrincipal UserDetailsImpl userDetailsImp){
        return linkService.deleteLink(linkDelRequestDTO, userDetailsImp.getUser());
    }

}
