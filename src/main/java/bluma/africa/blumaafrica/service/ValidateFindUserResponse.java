package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.data.models.Admin;
import bluma.africa.blumaafrica.data.models.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ValidateFindUserResponse {
    private User found;
    private Admin foundAdmin;

}
