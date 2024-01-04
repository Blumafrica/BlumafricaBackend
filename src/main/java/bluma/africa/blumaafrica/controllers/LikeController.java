package bluma.africa.blumaafrica.controllers;

import bluma.africa.blumaafrica.data.models.Likes;
import bluma.africa.blumaafrica.dtos.requests.GetAllPostLikesRequest;
import bluma.africa.blumaafrica.dtos.requests.LikeRequest;
import bluma.africa.blumaafrica.dtos.requests.UnlikeRequest;
import bluma.africa.blumaafrica.dtos.responses.GetAllPostLikesResponse;
import bluma.africa.blumaafrica.dtos.responses.LikeResponse;
import bluma.africa.blumaafrica.exceptions.BlumaException;
import bluma.africa.blumaafrica.exceptions.LikeException;
import bluma.africa.blumaafrica.exceptions.PostNotFound;
import bluma.africa.blumaafrica.service.LikesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor

public class LikeController {

    private final LikesService likesService;

    @PostMapping("/api/like/")
    private ResponseEntity<?> likePost(@RequestBody LikeRequest request){
        LikeResponse response =  null;
        try {
         response =  likesService.userCanLikePost(request);
        } catch (BlumaException e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/api/getAllPostLikes/")
    private ResponseEntity<?> getAllPostLikes(@RequestBody GetAllPostLikesRequest request){
        GetAllPostLikesResponse response = likesService.getAllPostLikes(request);
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }
    @DeleteMapping("/api/unlike/")
    private ResponseEntity<?> unlike(@RequestBody UnlikeRequest request){
        String response = null;
        try {
            response = likesService.unlikePost(request);
        } catch (BlumaException e) {
            return new ResponseEntity<>(e, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }


}
