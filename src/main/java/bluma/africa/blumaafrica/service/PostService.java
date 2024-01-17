package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.data.models.Authority;
import bluma.africa.blumaafrica.data.models.Post;
import bluma.africa.blumaafrica.dtos.requests.FetchUserPostRequest;
import bluma.africa.blumaafrica.dtos.requests.PostRequest;
import bluma.africa.blumaafrica.dtos.responses.EditPostResponse;
import bluma.africa.blumaafrica.dtos.responses.FetchUserPostResponse;
import bluma.africa.blumaafrica.dtos.responses.PostResponse;
import bluma.africa.blumaafrica.exceptions.BlumaException;
import bluma.africa.blumaafrica.exceptions.PostNotFound;
import bluma.africa.blumaafrica.exceptions.UserNotFound;

import java.util.List;
public interface PostService {




   Post getPostById(String id) throws PostNotFound;

    PostResponse creatPost(PostRequest postRequest) throws BlumaException;

    EditPostResponse editPost(Long postId, PostRequest postRequest) throws UserNotFound, PostNotFound;
   Post getPostById(Long id) throws PostNotFound;
   PostResponse deletePostById(Long postId) throws PostNotFound;

    List<Post> getUserPosts(String userId);

    List<Post> findByPostOwnerAuthority(Authority userAuthority);

    FetchUserPostResponse findUserPosts(FetchUserPostRequest request);
}
