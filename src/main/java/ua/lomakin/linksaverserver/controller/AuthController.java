package ua.lomakin.linksaverserver.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.lomakin.linksaverserver.DTO.securityDTO.JwtResponseDTO;
import ua.lomakin.linksaverserver.DTO.securityDTO.LoginRequestDTO;
import ua.lomakin.linksaverserver.DTO.MessageResponseDTO;
import ua.lomakin.linksaverserver.DTO.securityDTO.SignupRequestDTO;
import ua.lomakin.linksaverserver.service.security.AuthService;

import javax.validation.Valid;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController()
@RequestMapping("/api/auth/")
public class AuthController {

    AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login/")
    public JwtResponseDTO login(@Valid @RequestBody LoginRequestDTO loginRequestDTO){
        return authService.loginUser(loginRequestDTO);
    }

    @PostMapping("/register/")
    public MessageResponseDTO register(@Valid @RequestBody SignupRequestDTO signupRequestDTO){
        return authService.registerUser(signupRequestDTO);
    }

}