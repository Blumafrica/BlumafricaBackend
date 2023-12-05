package bluma.africa.blumaafrica.controllers;

import bluma.africa.blumaafrica.dtos.requests.CreateCommentRequest;
import bluma.africa.blumaafrica.dtos.requests.UpdateCommentRequest;
import bluma.africa.blumaafrica.dtos.responses.ResponseApi;
import bluma.africa.blumaafrica.exceptions.BlumaException;
import bluma.africa.blumaafrica.exceptions.CommentNotFoundException;
import bluma.africa.blumaafrica.exceptions.PostNotFoundException;
import bluma.africa.blumaafrica.exceptions.UserNotFound;
import bluma.africa.blumaafrica.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {




    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("createComment")
    public ResponseEntity<?>creatComment(@RequestParam Long commenterId, @RequestBody CreateCommentRequest createCommentRequest){
        try{
            ResponseApi<?> response = commentService.createComment(commenterId ,createCommentRequest);
            return ResponseEntity.ok(response);
        }
        catch (CommentNotFoundException | PostNotFoundException | UserNotFound message) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message.getMessage());

        }
    }

    @PostMapping("/updateComment")
    public ResponseEntity<ResponseApi<?>> updateComment(@RequestParam Long commentTextId, @RequestParam Long commenterId, @RequestBody UpdateCommentRequest updateCommentRequest) throws BlumaException {
        ResponseApi<?> response = commentService.updateComment(commentTextId, commenterId, updateCommentRequest);
        return ResponseEntity.ok(response);
    }
}
