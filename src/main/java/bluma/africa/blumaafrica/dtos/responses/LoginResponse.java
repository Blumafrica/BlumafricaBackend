package bluma.africa.blumaafrica.dtos.responses;

import bluma.africa.blumaafrica.data.models.Authority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class LoginResponse {
    private String email;
    private String accessToke;
    private Long id;
    private Authority authority;

    public LoginResponse(String token) {
    }
}
