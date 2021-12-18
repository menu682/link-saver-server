package ua.lomakin.linksaverserver.DTO.securityDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@AllArgsConstructor
@Getter
@Setter
public class SignupRequestDTO {

    private String username;
    private String email;
    private String password;
    private Set<String> role;

}
