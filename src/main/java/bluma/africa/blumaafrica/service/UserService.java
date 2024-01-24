package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.data.models.Comment;
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
import bluma.africa.blumaafrica.dtos.requests.*;
import bluma.africa.blumaafrica.dtos.responses.*;
import bluma.africa.blumaafrica.exceptions.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
public interface UserService {
    UserResponse createUser(UserRequest request) throws UserAlreadyExist, UserNotFound, EmailException;
    User getUserBy(String username);
    User getUserById(Long id) throws UserNotFound;

//    LikeResponse userCanLikePost(LikeRequest likeRequest);
    ProfileResponse setProfile(ProfileRequest profileRequest) throws UserNotFound;
    ProfileResponse updateProfile(ProfileRequest profileRequest) throws UserNotFound;

}
