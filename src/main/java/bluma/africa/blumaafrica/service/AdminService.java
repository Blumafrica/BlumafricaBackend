package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.data.models.*;
import bluma.africa.blumaafrica.dtos.requests.*;
import bluma.africa.blumaafrica.dtos.responses.DeleteResponse;
import bluma.africa.blumaafrica.dtos.responses.FetchAdminPost;
import bluma.africa.blumaafrica.dtos.responses.FindUserResponse;
import bluma.africa.blumaafrica.dtos.responses.PostResponse;
import bluma.africa.blumaafrica.exceptions.AuthorityException;
import bluma.africa.blumaafrica.exceptions.BlumaException;
import bluma.africa.blumaafrica.exceptions.PostNotFound;
import bluma.africa.blumaafrica.exceptions.UserNotFound;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {

    LoginAsAdminResponse logInAsAdmin(LoginAsAdminRequest request) throws BlumaException;

    Post findPostById(long id) throws PostNotFound;

    DeleteResponse deletePost(DeletePost deletePost) throws  BlumaException;


    FetchAdminPost fetchAllPost();

    Admin findAdminById(String id);
    Admin findAdminByEmail(String email);
    User getUserId(String id) throws UserNotFound;
    Likes getLikesById(Long id);

    FindUserResponse findUser(FindUserRequest findUser) throws UserNotFound, AuthorityException;

    Profile getUserProfile(GetUserProfile request) throws AuthorityException, UserNotFound;
}
