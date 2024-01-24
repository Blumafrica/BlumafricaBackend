package bluma.africa.blumaafrica.dtos.responses;

import bluma.africa.blumaafrica.data.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ValidateUserLoginRequest {
    private User foundUser;
}
