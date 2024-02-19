package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.data.models.Authority;
import bluma.africa.blumaafrica.data.models.Post;
import bluma.africa.blumaafrica.data.repositories.PostRepository;
import bluma.africa.blumaafrica.dtos.requests.FetchUserPostRequest;
import bluma.africa.blumaafrica.dtos.requests.PostRequest;
import bluma.africa.blumaafrica.dtos.responses.EditPostResponse;
import bluma.africa.blumaafrica.dtos.responses.FetchUserPostResponse;
import bluma.africa.blumaafrica.dtos.responses.PostResponse;
import bluma.africa.blumaafrica.exceptions.BlumaException;
import bluma.africa.blumaafrica.exceptions.PostNotFound;
import bluma.africa.blumaafrica.exceptions.UserNotFound;
import bluma.africa.blumaafrica.mapper.Mapper;
import bluma.africa.blumaafrica.validators.Validate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static bluma.africa.blumaafrica.mapper.Mapper.map;

@Service
@AllArgsConstructor
@Slf4j
public class BlumaPostService implements PostService {
    private final PostRepository postRepository;
    private final UserService userService;
    private final Validate validate;
    private ModelMapper modelMapper;


    @Override
    public PostResponse creatPost(PostRequest postRequest) throws BlumaException {
                Post post;
                if (validate.validateAdminPostDetails(postRequest))
                    post = Mapper.map(postRequest);
                 else {
                    var user = userService.getUserById(postRequest.getPosterId());
                    if (user == null)
                        throw new BlumaException("User with ID " + postRequest.getPosterId() + " not found");
                    Long extractUserId = user.getId();
                    post = map(postRequest);
                    post.setPostOwnerId(extractUserId);
                }
                var savedPost = postRepository.save(post);
                return convertToResponse(savedPost);
        }

    private PostResponse convertToResponse(Post post){
        PostResponse postResponse = new PostResponse();
        postResponse.setDescription(post.getDescription());
        postResponse.setFileUrl(post.getFileUrl());
        postResponse.setContent(post.getContent());
        postResponse.setPostId(post.getId());
        postResponse.setTimePosted(post.getCreatedAt());
        postResponse.setPosterId(post.getPostOwnerId());
        postResponse.setMessage("Posted!!!");
        return postResponse;
    }

    @Override
    public EditPostResponse editPost(Long postId, PostRequest postRequest) throws PostNotFound {
        Post post = getPostById(postId);
        post.setContent(postRequest.getContent());
        post.setDescription(postRequest.getDescription());
        post.setFileUrl(postRequest.getFileUrl());
        post.setCreatedAt(LocalDateTime.now());
        postRepository.save(post);
        return new EditPostResponse();
    }

    @Override
    public Post getPostById(Long id) throws PostNotFound {
        return postRepository.findPostById(id).orElseThrow(()-> new  PostNotFound("post with id " + id + " does not exist"));
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

    public Post getPostById(String id) throws PostNotFound {
            return postRepository.findPostById(Long.valueOf(id)).orElseThrow(() -> new PostNotFound("post not found "));

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

    @Override
    public List<PostResponse> getAllPost(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo -1,pageNo);
        Page<Post>postPage = postRepository.findAll(pageable);
        List<Post> posts = postPage.getContent();
        log.info("posts:: {}",posts);
        return posts.stream()
                .map(post -> modelMapper.map(post,PostResponse.class) ).
                toList();
    }

    private FetchUserPostResponse convertToResponse(List<Post> foundPosts) {
        return new FetchUserPostResponse(foundPosts);
    }




}



