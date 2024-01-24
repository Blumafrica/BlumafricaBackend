package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.data.models.Admin;
import bluma.africa.blumaafrica.data.models.Authority;
import bluma.africa.blumaafrica.data.models.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ValidateFindUserResponse {
    private Authority user;
    private Authority admin;

}
