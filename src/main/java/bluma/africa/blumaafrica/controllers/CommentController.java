package bluma.africa.blumaafrica.controllers;

import bluma.africa.blumaafrica.dtos.requests.CreateCommentRequest;
import bluma.africa.blumaafrica.dtos.requests.UpdateCommentRequest;
import bluma.africa.blumaafrica.dtos.responses.ResponseApi;
import bluma.africa.blumaafrica.exceptions.*;
import bluma.africa.blumaafrica.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comment")
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;


    @PostMapping("createComment")
    public ResponseEntity<?>creatComment( @RequestBody CreateCommentRequest createCommentRequest){

        ResponseApi<?> response = null;
        try {
            response = commentService.createComment(createCommentRequest);
            return ResponseEntity.ok(response);

        } catch (CommentNotFoundException | UserNotFound | PostNotFound | AuthorityException | PostNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }





    }

    @PostMapping("/updateComment")
    public ResponseEntity<ResponseApi<?>> updateComment(@RequestParam Long commentTextId, @RequestParam Long commenterId, @RequestBody UpdateCommentRequest updateCommentRequest) throws BlumaException {
        ResponseApi<?> response = commentService.updateComment(updateCommentRequest);
        return ResponseEntity.ok(response);
    }
}
