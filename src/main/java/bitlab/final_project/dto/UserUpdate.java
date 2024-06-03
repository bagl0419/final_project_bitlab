package bitlab.final_project.dto;

import bitlab.final_project.entity.Country;
import bitlab.final_project.entity.Role;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdate {
    private Long id;
    private String username;
    private String password;
    private String newPassword;
    private String reNewPassword;
    private Country country;
    private List<Role> roles;
}
