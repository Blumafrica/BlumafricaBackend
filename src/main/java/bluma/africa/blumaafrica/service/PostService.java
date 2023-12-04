package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.data.models.Authority;
import bluma.africa.blumaafrica.data.models.Post;
import bluma.africa.blumaafrica.dtos.responses.PostResponse;
import bluma.africa.blumaafrica.exceptions.PostNotFound;
import bluma.africa.blumaafrica.exceptions.UserNotFound;

import java.util.List;

public interface PostService {
    PostResponse saveUserPost(Post post) throws UserNotFound;
   Post getPostById(Long id) throws PostNotFound;
   PostResponse deletePostById(Long postId) throws PostNotFound;

    List<Post> getUserPosts(String userId);

    Post save(Post post);
    List<Post> findByPostOwnerAuthority(Authority userAuthority);

}
