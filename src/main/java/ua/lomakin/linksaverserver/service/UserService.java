package ua.lomakin.linksaverserver.service;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ua.lomakin.linksaverserver.config.security.jwt.AuthTokenFilter;
import ua.lomakin.linksaverserver.config.security.jwt.JwtUtils;
import ua.lomakin.linksaverserver.persistance.entity.security.UserEntity;
import ua.lomakin.linksaverserver.persistance.repository.security.UserRepository;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserService {

    UserRepository userRepository;
    JwtUtils jwtUtils;
    AuthTokenFilter authTokenFilter;
    HttpServletRequest httpServletRequest;

    public UserService(UserRepository userRepository,
                       JwtUtils jwtUtils,
                       AuthTokenFilter authTokenFilter,
                       HttpServletRequest httpServletRequest) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
        this.authTokenFilter = authTokenFilter;
        this.httpServletRequest = httpServletRequest;
    }

    public UserEntity getCurrentUser(){
        String token = authTokenFilter.parseJwt(httpServletRequest);
        Long userId = jwtUtils.getUserIdFromJwtToken(token);
        return userRepository.getById(userId);
    }

}
