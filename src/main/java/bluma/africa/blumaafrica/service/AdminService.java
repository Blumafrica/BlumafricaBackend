package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.data.models.Admin;
import bluma.africa.blumaafrica.data.models.Likes;
import bluma.africa.blumaafrica.data.models.Post;
import bluma.africa.blumaafrica.data.models.User;
import bluma.africa.blumaafrica.dtos.requests.DeletePost;
import bluma.africa.blumaafrica.dtos.requests.LoginAsAdminRequest;
import bluma.africa.blumaafrica.dtos.requests.LoginAsAdminResponse;
import bluma.africa.blumaafrica.dtos.requests.PostRequest;
import bluma.africa.blumaafrica.dtos.responses.DeleteResponse;
import bluma.africa.blumaafrica.dtos.responses.FetchAdminPost;
import bluma.africa.blumaafrica.dtos.responses.PostResponse;
import bluma.africa.blumaafrica.exceptions.BlumaException;
import bluma.africa.blumaafrica.exceptions.PostNotFound;
import bluma.africa.blumaafrica.exceptions.UserNotFound;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {

    LoginAsAdminResponse logInAsAdmin(LoginAsAdminRequest request) throws BlumaException;

    PostResponse post(PostRequest postRequest) throws BlumaException;

    Post findPostById(long id) throws PostNotFound;

    DeleteResponse deletePost(DeletePost deletePost) throws  BlumaException;


    FetchAdminPost fetchAllPost();

    Admin findAdminById(String id);
    Admin findAdminByEmail(String email);
    User getUserId(String id) throws UserNotFound;
    Likes getLikesById(Long id);
}
