package bluma.africa.blumaafrica.dtos.requests;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class LoginAsAdminRequest {
    @NotNull
    @NotEmpty
    private String email;
    @NotNull
    private String password;
}
