package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.dtos.requests.CreateCommentRequest;
import bluma.africa.blumaafrica.dtos.requests.UpdateCommentRequest;
import bluma.africa.blumaafrica.dtos.responses.ResponseApi;
import bluma.africa.blumaafrica.exceptions.*;

public interface CommentService {
    ResponseApi<?> createComment(Long postId, CreateCommentRequest createCommentRequest) throws CommentNotFoundException, PostNotFoundException, UserNotFound, PostNotFound;
    ResponseApi<?> updateComment(Long commentTextId, Long commenterId, UpdateCommentRequest updateCommentRequest) throws BlumaException;
}
