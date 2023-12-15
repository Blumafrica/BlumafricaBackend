package bluma.africa.blumaafrica.controllers;

import bluma.africa.blumaafrica.dtos.requests.FetchUserPostRequest;
import bluma.africa.blumaafrica.dtos.requests.PostRequest;
import bluma.africa.blumaafrica.dtos.responses.EditPostResponse;
import bluma.africa.blumaafrica.dtos.responses.FetchUserPostResponse;
import bluma.africa.blumaafrica.dtos.responses.PostResponse;
import bluma.africa.blumaafrica.exceptions.BlumaException;
import bluma.africa.blumaafrica.exceptions.PostNotFound;
import bluma.africa.blumaafrica.exceptions.UserNotFound;
import bluma.africa.blumaafrica.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/post/")
@AllArgsConstructor
public class PostController {
    private PostService postService;
    @PostMapping("/post")
    public ResponseEntity<?> userPost(@RequestBody PostRequest postRequest){
        try{
            PostResponse response = postService.creatPost(postRequest);
            return new ResponseEntity<>(response.getMessage(), HttpStatus.OK);
        }catch (BlumaException userNotFound){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userNotFound.getMessage());
        }
    }
    @PostMapping("/{postId}/editPost/")
    public  ResponseEntity<?> editPost(@PathVariable Long postId, @RequestBody PostRequest postRequest){
        try{
            EditPostResponse response = postService.editPost(postId,postRequest);
            return new ResponseEntity<>(response.getMessage(),HttpStatus.OK);
        }catch (PostNotFound | UserNotFound exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }
    @DeleteMapping("/{postId}/deletePost")
    public ResponseEntity<?> deletePost (@PathVariable Long postId) {
        try {
            postService.deletePostById(postId);
            return ResponseEntity.ok("successfully deleted ");
        } catch (PostNotFound postNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(postNotFound.getMessage());
        }
    }
    @GetMapping("api/v1/getPosts")
    public ResponseEntity<?> getUserPost(@RequestBody FetchUserPostRequest request){

        FetchUserPostResponse response = postService.findUserPosts(request);
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }




}
