package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.data.models.Authority;
import bluma.africa.blumaafrica.data.models.Post;
import bluma.africa.blumaafrica.data.models.User;
import bluma.africa.blumaafrica.dtos.requests.FetchUserPostRequest;
import bluma.africa.blumaafrica.dtos.requests.PostRequest;
import bluma.africa.blumaafrica.dtos.responses.EditPostResponse;
import bluma.africa.blumaafrica.dtos.responses.FetchUserPostResponse;
import bluma.africa.blumaafrica.dtos.responses.PostResponse;


import bluma.africa.blumaafrica.exceptions.PostNotFoundException;

import bluma.africa.blumaafrica.exceptions.PostNotFound;
import bluma.africa.blumaafrica.exceptions.UserNotFound;


import java.util.List;
public interface PostService {
<<<<<<< HEAD

    PostResponse saveUserPost(Post post) throws UserNotFound;




=======
    PostResponse saveUserPost(Post post) throws UserNotFound;
   Post getPostById(String id) throws PostNotFound;
>>>>>>> d18f1b7b128efb1a83aa057e8f7b5e49a6a02e3b
    PostResponse creatPost(PostRequest postRequest) throws UserNotFound;
    EditPostResponse editPost(Long postId, PostRequest postRequest) throws UserNotFound, PostNotFound;
   Post getPostById(Long id) throws PostNotFound;
   PostResponse deletePostById(Long postId) throws PostNotFound;

    List<Post> getUserPosts(String userId);

    Post save(Post post);
    List<Post> findByPostOwnerAuthority(Authority userAuthority);
    Long getPostOwnerId(Long postOwnerId) throws PostNotFound;
    FetchUserPostResponse findUserPosts(FetchUserPostRequest request);
}
