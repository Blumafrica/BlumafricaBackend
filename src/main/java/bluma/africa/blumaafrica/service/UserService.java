package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.data.models.Comment;
import bluma.africa.blumaafrica.data.models.Post;
import bluma.africa.blumaafrica.data.models.User;
import bluma.africa.blumaafrica.dtos.requests.*;
import bluma.africa.blumaafrica.dtos.responses.*;
import bluma.africa.blumaafrica.exceptions.*;

public interface UserService {
    UserResponse createUser(UserRequest request) throws UserAlreadyExist;
    User getUserBy(String username);
    User getUserById(Long id) throws UserNotFound;
    ProfileResponse setProfile(ProfileRequest profileRequest) throws UserNotFound;
    ProfileResponse updateProfile(ProfileRequest profileRequest) throws UserNotFound;

}
