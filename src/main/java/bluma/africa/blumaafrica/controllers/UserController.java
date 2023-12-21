package bluma.africa.blumaafrica.controllers;

import bluma.africa.blumaafrica.dtos.requests.LoginRequest;
import bluma.africa.blumaafrica.dtos.requests.ProfileRequest;
import bluma.africa.blumaafrica.dtos.requests.UserRequest;
import bluma.africa.blumaafrica.dtos.responses.LoginResponse;
import bluma.africa.blumaafrica.dtos.responses.ProfileResponse;
import bluma.africa.blumaafrica.dtos.responses.UserResponse;
import bluma.africa.blumaafrica.exceptions.UserAlreadyExist;
import bluma.africa.blumaafrica.exceptions.UserNotFound;
import bluma.africa.blumaafrica.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRequest userRequest) throws UserAlreadyExist {
        try {
            UserResponse response = userService.createUser(userRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (UserAlreadyExist exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }

    }

    @PostMapping("/profile")
    public ResponseEntity<?> userProfile(@RequestBody ProfileRequest profileRequest) {
        try {
            ProfileResponse response = userService.setProfile(profileRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (UserNotFound userNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userNotFound.getMessage());
        }

    }

    @PostMapping("/login")
    public void userLogin(@RequestBody LoginRequest loginRequest) throws UserNotFound {
        throw new UserNotFound("user not found");




    }
}
