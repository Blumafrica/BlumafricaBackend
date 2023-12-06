package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.data.models.Post;
import bluma.africa.blumaafrica.dtos.requests.LoginAsAdminRequest;
import bluma.africa.blumaafrica.dtos.requests.LoginAsAdminResponse;
import bluma.africa.blumaafrica.dtos.requests.PostRequest;
import bluma.africa.blumaafrica.dtos.responses.DeleteResponse;
import bluma.africa.blumaafrica.dtos.responses.FetchAdminPost;
import bluma.africa.blumaafrica.dtos.responses.PostResponse;
import bluma.africa.blumaafrica.exceptions.BlumaException;
import bluma.africa.blumaafrica.exceptions.PostNotFound;


public interface AdminService {

    LoginAsAdminResponse logInAsAdmin(LoginAsAdminRequest request) throws BlumaException;

    PostResponse post(PostRequest postRequest) throws BlumaException;

    Post findPostById(long id) throws PostNotFound;

    DeleteResponse deletePost(long id);


    FetchAdminPost fetchAllPost();
}
