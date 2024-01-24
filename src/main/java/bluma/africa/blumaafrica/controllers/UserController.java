package bluma.africa.blumaafrica.controllers;

import bluma.africa.blumaafrica.config.security.filter.BlumaAuthenticationFilter;
import bluma.africa.blumaafrica.dtos.requests.LoginRequest;
import bluma.africa.blumaafrica.dtos.requests.ProfileRequest;
import bluma.africa.blumaafrica.dtos.requests.UserRequest;
import bluma.africa.blumaafrica.dtos.responses.LoginResponse;
import bluma.africa.blumaafrica.dtos.responses.ProfileResponse;
import bluma.africa.blumaafrica.dtos.responses.UserResponse;
import bluma.africa.blumaafrica.exceptions.EmailException;
import bluma.africa.blumaafrica.exceptions.UserAlreadyExist;
import bluma.africa.blumaafrica.exceptions.UserNotFound;
import bluma.africa.blumaafrica.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class UserController {
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRequest userRequest) throws UserAlreadyExist {
        try {
            UserResponse response = userService.createUser(userRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (UserAlreadyExist | UserNotFound | EmailException exception) {
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


}
