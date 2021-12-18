package ua.lomakin.linksaverserver.DTO.securityDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserDTO {

    private String username;
    private String email;
    private String password;

}
