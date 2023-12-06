package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.data.models.Post;
import bluma.africa.blumaafrica.data.models.User;
import bluma.africa.blumaafrica.dtos.requests.FetchUserPostRequest;
import bluma.africa.blumaafrica.dtos.requests.LikeRequest;
import bluma.africa.blumaafrica.dtos.requests.PostRequest;
import bluma.africa.blumaafrica.dtos.requests.UserRequest;
import bluma.africa.blumaafrica.dtos.responses.*;
import bluma.africa.blumaafrica.exceptions.PostNotFound;
import bluma.africa.blumaafrica.exceptions.UserAlreadyExist;
import bluma.africa.blumaafrica.exceptions.UserNotFound;

public interface UserService {
    UserResponse createUser(UserRequest request) throws UserAlreadyExist;

    User getUserBy(String username);
    User getUserById(Long id) throws UserNotFound;
    PostResponse makePost(PostRequest postRequest) throws UserNotFound;
    EditPostResponse editPost(String postId,PostRequest postRequest) throws UserNotFound, PostNotFound;
    void deletePost(Long id) throws PostNotFound;
    Post findPostById (String id) throws PostNotFound;

    FetchUserPostResponse findUserPosts(FetchUserPostRequest request);

    LikeResponse userCanLikePost(LikeRequest likeRequest);
}
