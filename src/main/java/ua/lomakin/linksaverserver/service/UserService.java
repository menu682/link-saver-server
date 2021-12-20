package ua.lomakin.linksaverserver.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import ua.lomakin.linksaverserver.config.security.UserDetailsImpl;
import ua.lomakin.linksaverserver.config.security.jwt.AuthTokenFilter;
import ua.lomakin.linksaverserver.config.security.jwt.JwtUtils;
import ua.lomakin.linksaverserver.persistance.ERole;
import ua.lomakin.linksaverserver.persistance.entity.security.RoleEntity;
import ua.lomakin.linksaverserver.persistance.entity.security.UserEntity;
import ua.lomakin.linksaverserver.persistance.repository.security.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Getter
@Setter
public class UserService {

    UserRepository userRepository;
    JwtUtils jwtUtils;
    AuthTokenFilter authTokenFilter;
    HttpServletRequest httpServletRequest;



    public UserEntity getCurrentUser(){

        String token = authTokenFilter.parseJwt(httpServletRequest);
        Long userId = jwtUtils.getUserIdFromJwtToken(token);
        return userRepository.getById(userId);
    }
}
