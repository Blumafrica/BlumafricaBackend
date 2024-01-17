package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.data.models.*;
import bluma.africa.blumaafrica.data.repositories.AdminRepository;
import bluma.africa.blumaafrica.data.repositories.CommentRepository;
import bluma.africa.blumaafrica.dtos.requests.CreateCommentRequest;
import bluma.africa.blumaafrica.dtos.requests.UpdateCommentRequest;
import bluma.africa.blumaafrica.dtos.responses.ResponseApi;
import bluma.africa.blumaafrica.dtos.responses.ValidateCommentResponse;
import bluma.africa.blumaafrica.exceptions.*;
import bluma.africa.blumaafrica.mapper.Mapper;
import bluma.africa.blumaafrica.validators.Validate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class BlumaCommentService implements CommentService{
    private final CommentRepository commentRepository;
    private UserService userService;
    private PostService postService;
    private AdminRepository adminRepository;
    private Validate validator;


    public ResponseApi<?> createComment( CreateCommentRequest createCommentRequest) throws UserNotFound, PostNotFound, AuthorityException {
      Post foundPost = postService.getPostById(createCommentRequest.getPostId());
      log.info("user :: {}",foundPost);
       ValidateCommentResponse validateResponse = validator.validateCommentOnPostRequest(createCommentRequest);
       Comment comment =  Mapper.map(validateResponse);
       commentRepository.save(comment);

        ResponseApi<?> response = new ResponseApi<>();
        response.setMessage("comment check");
        return response;
    }


    public ResponseApi<?> updateComment(UpdateCommentRequest updateCommentRequest) throws BlumaException {
        Comment comment = commentRepository.findById(updateCommentRequest.getCommentId()).
                orElseThrow(()-> new CommentNotFoundException(String.format("no comment found with this id %d", updateCommentRequest.getCommentId())));
        return updates(updateCommentRequest, comment.getUserAuthority(), comment);


    }

    private ResponseApi<?> updates(UpdateCommentRequest updateCommentRequest, Authority userAuthority, Comment comment) throws BlumaException {
        if (comment.getUserAuthority() == userAuthority){
            return updateUserComment(comment, updateCommentRequest, userAuthority);
        }else if (comment.getUserAuthority() == Authority.ADMIN){
            return UpdateAdminComment(comment, updateCommentRequest, userAuthority);
        }throw new AuthorityException("unknown authority");
    }

    private ResponseApi<?> UpdateAdminComment(Comment comment, UpdateCommentRequest updateCommentRequest, Authority userAuthority) throws BlumaException {
        Admin admin = adminRepository.findAdminById(updateCommentRequest.getCommenterId());
        if (comment.getCommenterId().equals(admin.getId())){
            comment.setCommentId(updateCommentRequest.getCommentId());
            commentRepository.save(comment);
            ResponseApi<?> response = new ResponseApi<>();
            response.setMessage("Comment updated successfully");
            return response;
        }throw new BlumaException("Only commenter can update comment");
    }

    private ResponseApi<?> updateUserComment(Comment comment, UpdateCommentRequest updateCommentRequest, Authority userAuthority) throws BlumaException {
            User user = userService.getUserById(updateCommentRequest.getCommenterId());
            if (comment.getCommenterId().equals(user.getId())) {
                comment.setCommentId(updateCommentRequest.getCommentId());
                commentRepository.save(comment);
                ResponseApi<?> response = new ResponseApi<>();
                response.setMessage("Comment updated successfully");
                return response;
            }else {throw new BlumaException("Only commenter can update comment");}

    }

    @Override
    public String commentOnShare(CreateCommentRequest createCommentRequest) throws ShareException, AuthorityException {
        validator.validateCommentOnShare(createCommentRequest);
        return null;

    }
}
