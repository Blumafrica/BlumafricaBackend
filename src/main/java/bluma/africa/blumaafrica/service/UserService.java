package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.dtos.requests.UserRequest;
import bluma.africa.blumaafrica.dtos.responses.UserResponse;

public interface UserService {
    UserResponse createUser(UserRequest request);
}
