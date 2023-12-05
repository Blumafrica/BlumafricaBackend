package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.data.models.Authority;
import bluma.africa.blumaafrica.data.models.Post;
import bluma.africa.blumaafrica.data.models.Profile;
import bluma.africa.blumaafrica.data.models.User;
import bluma.africa.blumaafrica.data.repositories.UserRepository;
import bluma.africa.blumaafrica.dtos.requests.PostRequest;
import bluma.africa.blumaafrica.dtos.requests.ProfileRequest;
import bluma.africa.blumaafrica.dtos.requests.UserRequest;
import bluma.africa.blumaafrica.dtos.responses.EditPostResponse;
import bluma.africa.blumaafrica.dtos.responses.PostResponse;
import bluma.africa.blumaafrica.dtos.responses.ProfileResponse;
import bluma.africa.blumaafrica.dtos.responses.UserResponse;
import bluma.africa.blumaafrica.exceptions.PostNotFound;
import bluma.africa.blumaafrica.exceptions.UserAlreadyExist;
import bluma.africa.blumaafrica.exceptions.UserNotFound;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j


public class BlumaUserServiceImpl implements UserService{
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PostService postService;
    private final ModelMapper mapper;

    @Override
    public UserResponse createUser(UserRequest request) throws UserAlreadyExist {
        boolean isUserExist = userRepository.findByUsername(request.getUsername()).isPresent();
        if (isUserExist) throw new UserAlreadyExist("user already exist");
        User user = new User();

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(encodedPassword);
        user.setAuthorities(List.of(Authority.USER));
        var savedUser = userRepository.save(user);
        UserResponse response = new UserResponse();
        response.setId(savedUser.getId());
        response.setMessage("Successfully created");
        return response;
    }

    @Override
    public User getUserBy(String email) {
        return userRepository.findByEmail(email).orElseThrow(()->
                new RuntimeException(
                        String.format("user with %s is not found",email)));
    }

    @Override
    public User getUserById(Long id) throws UserNotFound {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFound("user not found"));
    }

    @Override
    public PostResponse makePost(PostRequest postRequest) throws UserNotFound {
        Post post = new Post();
        var user = userRepository.findById(postRequest.getPosterId()).
                orElseThrow(()-> new UserNotFound("user not found"));
        Long extractUserId = user.getId();

        post.setContent(postRequest.getText());
        post.setDescription(postRequest.getDescription());
        post.setFileUrl(postRequest.getFileUrl());
        post.setCreatedAt(LocalDateTime.now());
        post.setPostOwnerId(extractUserId);
        post.setPostOwnerAuthority(Authority.USER);
        var savedPost = postService.saveUserPost(post);
         PostResponse postResponse = new PostResponse();
         postResponse.setTimePosted(savedPost.getTimePosted());
         postResponse.setPostId(savedPost.getPostId());
         postResponse.setPostOwnerId(savedPost.getPostOwnerId());
         postResponse.setMessage("Posted!!!");
         return postResponse;
    }

    @Override
    public EditPostResponse editPost( Long postId, PostRequest postRequest) throws UserNotFound, PostNotFound {
        Post post = postService.getPostById(postId);
        System.out.println(post);
        post.setContent(postRequest.getText());
        post.setDescription(postRequest.getDescription());
        post.setFileUrl(postRequest.getFileUrl());
        post.setCreatedAt(LocalDateTime.now());
         postService.saveUserPost(post);
        return new EditPostResponse();
    }

    @Override
    public void deletePost(Long postId) throws PostNotFound {
        postService.deletePostById(postId);

    }

    @Override
    public Post findPostById(Long postId) throws PostNotFound {
        return postService.getPostById(postId);
    }

    @Override
    public ProfileResponse setProfile(ProfileRequest profileRequest) throws UserNotFound {
        var getUser = getUserById(profileRequest.getUserId());
        Profile userProfile =mapper.map(profileRequest,Profile.class);
        userProfile.setUserId(getUser.getId());
        getUser.setProfile(userProfile);
         userRepository.save(getUser);
        return new ProfileResponse();
    }

    @Override
    public ProfileResponse updateProfile(ProfileRequest profileRequest) throws UserNotFound {
        var getUser = getUserById(profileRequest.getUserId());
        return null;


        }


    }

