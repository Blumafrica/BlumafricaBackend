package bluma.africa.blumaafrica.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FindUserRequest {

    private String userId;
    private String userAuthority;
}
