package bluma.africa.blumaafrica.dtos.responses;

import bluma.africa.blumaafrica.data.models.Admin;
import bluma.africa.blumaafrica.data.models.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@RequiredArgsConstructor
@Getter
public class FindUserResponse {

    private Admin foundAdmin;
    private User userFoundUser;

}
