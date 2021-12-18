package ua.lomakin.linksaverserver.DTO;

import lombok.Data;

import java.util.Set;


@Data
public class SignupRequestDTO {

    private String username;
    private String email;
    private String password;
    private Set<String> role;

    public SignupRequestDTO(String username, String email, String password, Set<String> role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
