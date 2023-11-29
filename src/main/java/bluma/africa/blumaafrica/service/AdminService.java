package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.dtos.requests.LoginAsAdminRequest;
import bluma.africa.blumaafrica.dtos.requests.LoginAsAdminResponse;
import bluma.africa.blumaafrica.dtos.requests.PostRequest;
import bluma.africa.blumaafrica.dtos.responses.PostResponse;
import bluma.africa.blumaafrica.exceptions.BlumaException;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {

    LoginAsAdminResponse logInAsAdmin(LoginAsAdminRequest request) throws BlumaException;

    PostResponse post(PostRequest postRequest);

}
