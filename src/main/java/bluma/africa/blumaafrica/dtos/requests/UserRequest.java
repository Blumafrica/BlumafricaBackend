package bluma.africa.blumaafrica.dtos.requests;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRequest {
    private String username;
    @Email
    private String email;
    private String password;
}
