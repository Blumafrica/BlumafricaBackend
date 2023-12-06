package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.data.models.Authority;
import bluma.africa.blumaafrica.data.models.Post;
import bluma.africa.blumaafrica.data.models.User;
import bluma.africa.blumaafrica.data.repositories.PostRepository;
import bluma.africa.blumaafrica.dtos.requests.FetchUserPostRequest;
import bluma.africa.blumaafrica.dtos.requests.PostRequest;
import bluma.africa.blumaafrica.dtos.responses.EditPostResponse;
import bluma.africa.blumaafrica.dtos.responses.FetchUserPostResponse;
import bluma.africa.blumaafrica.dtos.responses.PostResponse;
import bluma.africa.blumaafrica.exceptions.PostNotFound;
import bluma.africa.blumaafrica.exceptions.UserNotFound;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class BlumaPostService implements PostService {
    private final PostRepository postRepository;
    private final UserService userService;


    @Override
    public PostResponse creatPost(PostRequest postRequest) throws UserNotFound {
        Post post = new Post();
        var user = userService. getUserById(postRequest.getPosterId());
        Long extractUserId = user.getId();

        post.setContent(postRequest.getText());
        post.setDescription(postRequest.getDescription());
        post.setFileUrl(postRequest.getFileUrl());
        post.setCreatedAt(LocalDateTime.now());
        post.setPostOwnerId(extractUserId);
        post.setPostOwnerAuthority(Authority.USER);
        var savedPost = postRepository.save(post);
        PostResponse postResponse = new PostResponse();
        postResponse.setTimePosted(savedPost.getCreatedAt());
        postResponse.setPostId(savedPost.getId());
        postResponse.setPostOwnerId(savedPost.getPostOwnerId());
        postResponse.setMessage("Posted!!!");
        return postResponse;
    }

    @Override
    public EditPostResponse editPost(Long postId, PostRequest postRequest) throws UserNotFound, PostNotFound {
        Post post = getPostById(postId);
        post.setContent(postRequest.getText());
        post.setDescription(postRequest.getDescription());
        post.setFileUrl(postRequest.getFileUrl());
        post.setCreatedAt(LocalDateTime.now());
        postRepository.save(post);
        return new EditPostResponse();
    }

    @Override
    public List<Post> findByPostOwnerAuthority(Authority userAuthority) {
        return postRepository.findByPostOwnerAuthority(userAuthority);
    }

    @Override
    public Long getPostOwnerId(Long postOwnerId) throws PostNotFound {
         return getPostById(postOwnerId).getPostOwnerId();
    }

    @Override

    public Post getPostById(Long id) throws PostNotFound {
            return postRepository.findById(id)
                    .orElseThrow(() -> new PostNotFound("Post not found with id: " + id));
        }

    @Override
    public PostResponse deletePostById(Long postId) throws PostNotFound {
        Post getPost = postRepository.findById(postId).
                 orElseThrow(() -> new PostNotFound("Post not found with id: " + postId));
        var extractId = getPost.getId();
        postRepository.deleteById(extractId);
        PostResponse response = new PostResponse();
        response.setMessage("Post Successfully deleted");
        return response;
    }

    @Override
    public List<Post> getUserPosts(String userId) {
        long convertId = Long.parseLong(userId);
       List<Post> foundPosts = findByPostOwnerAuthority(Authority.USER);
       return foundPosts.stream()
               .filter(x -> x.getPostOwnerId() == convertId)
               .toList();
    }
    @Override
    public FetchUserPostResponse findUserPosts(FetchUserPostRequest request) {
        List<Post> foundPosts = getUserPosts(request.getUserId());
        return convertToResponse(foundPosts);
    }
    private FetchUserPostResponse convertToResponse(List<Post> foundPosts) {
        return new FetchUserPostResponse(foundPosts);
    }

    @Override
    public Post save(Post post) {
        return null;
    }


}



