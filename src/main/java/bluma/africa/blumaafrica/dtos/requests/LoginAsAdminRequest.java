package bluma.africa.blumaafrica.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class LoginAsAdminRequest {
    private String email;
    private String password;
}
