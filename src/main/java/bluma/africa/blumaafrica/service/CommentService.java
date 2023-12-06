package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.dtos.requests.CreateCommentRequest;
import bluma.africa.blumaafrica.dtos.requests.UpdateCommentRequest;
import bluma.africa.blumaafrica.dtos.responses.ResponseApi;
import bluma.africa.blumaafrica.exceptions.BlumaException;
import bluma.africa.blumaafrica.exceptions.CommentNotFoundException;
import bluma.africa.blumaafrica.exceptions.PostNotFoundException;
import bluma.africa.blumaafrica.exceptions.UserNotFound;

public interface CommentService {
    ResponseApi<?> createComment(Long postId, CreateCommentRequest createCommentRequest) throws CommentNotFoundException, PostNotFoundException, UserNotFound;
    ResponseApi<?> updateComment(Long commentTextId, Long commenterId, UpdateCommentRequest updateCommentRequest) throws BlumaException;
}
