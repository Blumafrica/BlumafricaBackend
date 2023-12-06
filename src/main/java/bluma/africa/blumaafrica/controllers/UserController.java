package bluma.africa.blumaafrica.controllers;

import bluma.africa.blumaafrica.dtos.requests.FetchUserPostRequest;
import bluma.africa.blumaafrica.dtos.requests.LikeRequest;
import bluma.africa.blumaafrica.dtos.requests.PostRequest;
import bluma.africa.blumaafrica.dtos.requests.UserRequest;
import bluma.africa.blumaafrica.dtos.responses.*;
import bluma.africa.blumaafrica.exceptions.PostNotFound;
import bluma.africa.blumaafrica.exceptions.UserAlreadyExist;
import bluma.africa.blumaafrica.exceptions.UserNotFound;
import bluma.africa.blumaafrica.service.PostService;
import bluma.africa.blumaafrica.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRequest userRequest) throws UserAlreadyExist {
        try {
            UserResponse response = userService.createUser(userRequest);
            return new ResponseEntity<>(response.getMessage(), HttpStatus.OK);
        } catch (UserAlreadyExist exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }




    @PostMapping("/api/v1/likePosts")
    public ResponseEntity<?> likePost(@RequestBody LikeRequest request){
//        LikeResponse response = userService
        return null;
    }

}
