package ua.lomakin.linksaverserver.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.lomakin.linksaverserver.DTO.JwtResponseDTO;
import ua.lomakin.linksaverserver.DTO.LoginRequestDTO;
import ua.lomakin.linksaverserver.DTO.MessageResponseDTO;
import ua.lomakin.linksaverserver.DTO.SignupRequestDTO;
import ua.lomakin.linksaverserver.config.security.UserDetailsImpl;
import ua.lomakin.linksaverserver.config.security.jwt.JwtUtils;
import ua.lomakin.linksaverserver.persistance.ERole;
import ua.lomakin.linksaverserver.persistance.entity.RoleEntity;
import ua.lomakin.linksaverserver.persistance.entity.UserEntity;
import ua.lomakin.linksaverserver.persistance.repository.RoleRepository;
import ua.lomakin.linksaverserver.persistance.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder encoder;
    private JwtUtils jwtUtils;

    public AuthService(AuthenticationManager authenticationManager,
                       UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder encoder,
                       JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    public JwtResponseDTO loginUser(LoginRequestDTO loginRequestDTO) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(),
                        loginRequestDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return new JwtResponseDTO(
                jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles
        );
    }


    public MessageResponseDTO registerUser(SignupRequestDTO signupRequestDTO) {

        if (userRepository.existsByUsername(signupRequestDTO.getUsername())) {
            return new MessageResponseDTO("Такое имя уже занято, попробуйте другое.");
        }

        if (userRepository.existsByEmail(signupRequestDTO.getEmail())) {
            return new MessageResponseDTO("Такой email уже занят, попробуйте другой.");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(signupRequestDTO.getUsername());
        userEntity.setEmail(signupRequestDTO.getEmail());
        userEntity.setPassword(encoder.encode(signupRequestDTO.getPassword()));

        Set<String> strRoles = signupRequestDTO.getRole();
        Set<RoleEntity> roles = new HashSet<>();

        if (strRoles == null) {
            RoleEntity userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Такой роли нет"));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {

//  получается что достаточно передать при регистрации admin и ты уже админ ? непорядок ...

//                    case "admin":
//                        RoleEntity adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
//                                .orElseThrow(() -> new RuntimeException("Такой роли нет"));
//                        roles.add(adminRole);
//
//                        break;

                    default:
                        RoleEntity userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Такой роли нет"));
                        roles.add(userRole);
                }
            });
        }

        userEntity.setRoles(roles);
        userRepository.save(userEntity);
        return new MessageResponseDTO("Пользователь успешно зарегистрирован!");

    }

}
