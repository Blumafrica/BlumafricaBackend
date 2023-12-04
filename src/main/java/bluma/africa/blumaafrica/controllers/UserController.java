package bluma.africa.blumaafrica.controllers;

import bluma.africa.blumaafrica.dtos.requests.FetchUserPostRequest;
import bluma.africa.blumaafrica.dtos.requests.PostRequest;
import bluma.africa.blumaafrica.dtos.requests.UserRequest;
import bluma.africa.blumaafrica.dtos.responses.EditPostResponse;
import bluma.africa.blumaafrica.dtos.responses.FetchUserPostResponse;
import bluma.africa.blumaafrica.dtos.responses.PostResponse;
import bluma.africa.blumaafrica.dtos.responses.UserResponse;
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
    @PostMapping("/post")
    public ResponseEntity<?> userPost(@RequestBody PostRequest postRequest){
        try{
            PostResponse response = userService.makePost(postRequest);
            return new ResponseEntity<>(response.getMessage(),HttpStatus.OK);
        }catch (UserNotFound userNotFound){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userNotFound.getMessage());
        }
    }
    @PostMapping("/{postId}/editPost/")
    public  ResponseEntity<?> editPost(@PathVariable Long postId,@RequestBody PostRequest postRequest){
        try{
            EditPostResponse response = userService.editPost(postId,postRequest);
            return new ResponseEntity<>(response.getMessage(),HttpStatus.OK);
        }catch (PostNotFound | UserNotFound exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }
    @DeleteMapping("/{postId}/deletePost")
    public ResponseEntity<?> deletePost (@PathVariable Long postId) {
        try {
            userService.deletePost(postId);
           return ResponseEntity.ok("successfully deleted ");
        } catch (PostNotFound postNotFound) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(postNotFound.getMessage());
        }
    }
    @GetMapping("api/v1/getPosts")
    public ResponseEntity<?> getUserPost(@RequestBody FetchUserPostRequest request){

        FetchUserPostResponse response = userService.findUserPosts(request);
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

}
