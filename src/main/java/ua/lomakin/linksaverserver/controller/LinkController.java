package ua.lomakin.linksaverserver.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.lomakin.linksaverserver.service.LinkService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/link/")
public class LinkController {

    LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    //add link

    //put link

    //delete link



}
