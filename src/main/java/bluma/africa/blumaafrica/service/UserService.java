package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.data.models.User;
import bluma.africa.blumaafrica.dtos.requests.PostRequest;
import bluma.africa.blumaafrica.dtos.requests.UserRequest;
import bluma.africa.blumaafrica.dtos.responses.PostResponse;
import bluma.africa.blumaafrica.dtos.responses.UserResponse;
import bluma.africa.blumaafrica.exceptions.UserNotFound;

public interface UserService {
    UserResponse createUser(UserRequest request);

    User getUserBy(String username);
    User getUserById(Long id) throws UserNotFound;
    PostResponse makePost(PostRequest postRequest) throws UserNotFound;

}
