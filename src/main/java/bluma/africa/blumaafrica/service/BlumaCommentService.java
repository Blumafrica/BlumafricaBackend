package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.data.models.Comment;
import bluma.africa.blumaafrica.data.models.Post;
import bluma.africa.blumaafrica.data.repositories.CommentRepository;
import bluma.africa.blumaafrica.dtos.requests.CreateCommentRequest;
import bluma.africa.blumaafrica.dtos.requests.UpdateCommentRequest;
import bluma.africa.blumaafrica.dtos.responses.ResponseApi;
import bluma.africa.blumaafrica.exceptions.*;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BlumaCommentService implements CommentService{
    private final UserService userService;
    private final PostService postService;
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    @Override
    public ResponseApi<?> createComment(Long postId, CreateCommentRequest createCommentRequest) throws PostNotFoundException, UserNotFound, PostNotFound {
        Post foundPost = postService.getPostById(postId);
        Comment comment = modelMapper.map(createCommentRequest, Comment.class);
        comment.setPostId(foundPost);
        comment.setCommenterId(userService.getUserById(createCommentRequest.getCommenterId()));
        commentRepository.save(comment);
        ResponseApi<?> response = new ResponseApi<>();
        response.setMessage("comment check");
        return response;
    }

    @Override
    public ResponseApi<?> updateComment(
            Long commentTextId, Long commenterId, UpdateCommentRequest updateCommentRequest) throws BlumaException {

        var comment = commentRepository.findById(commentTextId).
                orElseThrow(()-> new CommentNotFoundException(String.format("no comment found with this id %d", commentTextId)));
        var mainCommenterId= comment.getCommenterId().getId();
        if (!mainCommenterId.equals(commenterId))throw new BlumaException("Only Owner of comment can update comment");
        comment.setCommentId(updateCommentRequest.getCommentId());
        commentRepository.save(comment);
        ResponseApi<?> response = new ResponseApi<>();
        response.setMessage("Comment updated successfully");
        return response;
    }
}
