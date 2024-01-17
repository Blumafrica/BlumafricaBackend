package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.dtos.requests.CreateCommentRequest;
import bluma.africa.blumaafrica.dtos.requests.UpdateCommentRequest;
import bluma.africa.blumaafrica.dtos.responses.ResponseApi;
import bluma.africa.blumaafrica.exceptions.*;

public interface CommentService {
    ResponseApi<?> createComment( CreateCommentRequest createCommentRequest) throws CommentNotFoundException, PostNotFoundException, UserNotFound, PostNotFound, AuthorityException;
    ResponseApi<?> updateComment( UpdateCommentRequest updateCommentRequest) throws BlumaException;

    String commentOnShare(CreateCommentRequest createCommentRequest) throws ShareException, AuthorityException;
}
