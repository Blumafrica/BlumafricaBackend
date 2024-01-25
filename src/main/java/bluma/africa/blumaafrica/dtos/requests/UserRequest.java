package bluma.africa.blumaafrica.dtos.requests;

import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRequest {

    @NotNull(message = "input field can not be null")
    @NotBlank(message = "input field can not be blank")
    @NotEmpty(message = "input field can not be empty")
    private String username;
    @NotNull(message = "input field can not be null")
    @NotBlank(message = "input field can not be blank")
    @NotEmpty(message = "input field can not be empty")
    @Email(message = "invalid email address",regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")
    private String email;
    @NotNull(message = "input field can not be null")
    @NotBlank(message = "input field can not be blank")
    @NotEmpty(message = "input field can not be empty")
    private String password;
}
