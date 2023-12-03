package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.data.models.Post;
import bluma.africa.blumaafrica.dtos.requests.PostRequest;
import bluma.africa.blumaafrica.dtos.responses.PostResponse;
import bluma.africa.blumaafrica.exceptions.PostNotFound;
import bluma.africa.blumaafrica.exceptions.UserNotFound;

import java.util.Optional;

public interface PostService {
    PostResponse saveUserPost(Post post) throws UserNotFound;
   Post getPostById(Long id) throws PostNotFound;
   PostResponse deletePostById(Long postId) throws PostNotFound;
}
