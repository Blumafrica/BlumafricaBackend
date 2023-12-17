package bluma.africa.blumaafrica.service;


import bluma.africa.blumaafrica.data.models.Admin;
import bluma.africa.blumaafrica.data.models.Authority;
import bluma.africa.blumaafrica.data.models.Post;
import bluma.africa.blumaafrica.data.models.User;
import bluma.africa.blumaafrica.data.repositories.AdminRepository;
import bluma.africa.blumaafrica.dtos.requests.DeletePost;
import bluma.africa.blumaafrica.dtos.requests.LoginAsAdminRequest;
import bluma.africa.blumaafrica.dtos.requests.LoginAsAdminResponse;
import bluma.africa.blumaafrica.dtos.requests.PostRequest;
import bluma.africa.blumaafrica.dtos.responses.DeleteResponse;
import bluma.africa.blumaafrica.dtos.responses.FetchAdminPost;
import bluma.africa.blumaafrica.dtos.responses.PostResponse;
import bluma.africa.blumaafrica.exceptions.BlumaException;
;
import bluma.africa.blumaafrica.exceptions.PostNotFound;
import bluma.africa.blumaafrica.exceptions.UserNotFound;
import bluma.africa.blumaafrica.mapper.Mapper;
import bluma.africa.blumaafrica.validators.Validate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@AllArgsConstructor
public class BlumaAdminService implements AdminService {

    private final Validate validate;
    private final PostService postService;
    private final AdminRepository repository;
    private final UserService userService;


    @PostConstruct
    @Override
    public void createAdmin() {
        Admin admin = new Admin();
        admin.setAuthority(List.of(Authority.ADMIN));
        admin.setId(1L);
        admin.setEmail("mariiam22222@gmail.com");
        admin.setPassword("Mariam@21");
        repository.save(admin);
    }

    @Override
    public LoginAsAdminResponse logInAsAdmin(LoginAsAdminRequest request) throws BlumaException {
        boolean response = validate.validateAdminDetails(request);
        if (response) return new LoginAsAdminResponse(request.getEmail());
        throw  new BlumaException("incorrect details");
    }



    @Override
    public PostResponse post(PostRequest postRequest) throws BlumaException {
        validate.validatePostDetails(postRequest);
        Post post = Mapper.map(postRequest);
        Post savedPost  = postService.save(post);
        System.out.println("created post " + post);
        System.out.println("post id" + post.getId());
        return convertToResponse(savedPost);
    }

    @Override
    public Post findPostById(long id) throws PostNotFound {
        return postService.getPostById(id);
    }

    @Override
    public DeleteResponse deletePost(DeletePost deletePost) throws BlumaException {
        postService.getPostById(deletePost.getPostId());
        if (deletePost.getUserAuthority().equals(Authority.ADMIN.toString())) {
            postService.deletePostById(Long.parseLong(deletePost.getPostId()));
            return new DeleteResponse("POST was deleted successfully");
        }
        throw  new BlumaException("No authority to delete");
    }



    @Override
    public FetchAdminPost fetchAllPost() {
        List<Post> foundPost = postService.findByPostOwnerAuthority(Authority.ADMIN);
        return convertToResponse(foundPost);
    }

    @Override
    public Admin findAdminById(String id) {
        return repository.findAdminById(Long.valueOf(id));
    }

    @Override
    public User getUserId(String id) throws UserNotFound {
        return userService.getUserById(Long.valueOf(id));
    }

    private FetchAdminPost convertToResponse(List<Post> posts) {
        return new FetchAdminPost(posts);
    }


    private PostResponse convertToResponse(Post post){
        PostResponse response = new PostResponse();
        response.setPostId(post.getId());
        response.setTimePosted(post.getCreatedAt());
        response.setPostOwnerId(post.getPostOwnerId());
        System.out.println("response created ==> "+ response);
        System.out.println("time posted "+response.getTimePosted());
        return response;
    }



}
