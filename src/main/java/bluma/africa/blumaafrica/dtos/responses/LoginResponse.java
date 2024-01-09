package bluma.africa.blumaafrica.dtos.responses;

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

    public LoginResponse(String token) {
    }
}
