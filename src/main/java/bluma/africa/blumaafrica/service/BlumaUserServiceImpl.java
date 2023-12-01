package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.data.models.Authority;
import bluma.africa.blumaafrica.data.models.Post;
import bluma.africa.blumaafrica.data.models.User;
import bluma.africa.blumaafrica.data.repositories.UserRepository;
import bluma.africa.blumaafrica.dtos.requests.PostRequest;
import bluma.africa.blumaafrica.dtos.requests.UserRequest;
import bluma.africa.blumaafrica.dtos.responses.PostResponse;
import bluma.africa.blumaafrica.dtos.responses.UserResponse;
import bluma.africa.blumaafrica.exceptions.UserNotFound;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BlumaUserServiceImpl implements UserService{
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PostService postService;
    @Override
    public UserResponse createUser(UserRequest request) {
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
    public User getUserBy(String username) {
        return userRepository.findByUsername(username).orElseThrow(()-> new RuntimeException("user with this username is not found"));
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
         postResponse.getMessage();
         return postResponse;
    }
}
