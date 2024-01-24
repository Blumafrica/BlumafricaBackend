package bluma.africa.blumaafrica.dtos.responses;

import bluma.africa.blumaafrica.data.models.Authority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class LoginResponse {

    private String accessToken;



}
