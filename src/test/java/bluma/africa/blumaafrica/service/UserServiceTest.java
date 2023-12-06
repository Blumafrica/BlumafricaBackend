package bluma.africa.blumaafrica.service;


import bluma.africa.blumaafrica.data.models.Post;
import bluma.africa.blumaafrica.data.models.User;
import bluma.africa.blumaafrica.dtos.requests.*;
import bluma.africa.blumaafrica.dtos.responses.*;
import bluma.africa.blumaafrica.exceptions.PostNotFound;
import bluma.africa.blumaafrica.exceptions.UserAlreadyExist;
import bluma.africa.blumaafrica.exceptions.UserNotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;
    private UserRequest userRequest;
    private UserResponse userResponse;
    private PostRequest postRequest;
    private PostResponse postResponse;
    private PostEditRequest postEditRequest;
    @Autowired
    private PostService postService;

    private EditPostResponse editPostResponse;

    @BeforeEach
    void setUp() {
        userRequest = new UserRequest();
        userResponse = new UserResponse();
        postRequest = new PostRequest();
        postEditRequest = new PostEditRequest();
        editPostResponse = new EditPostResponse();

        userRequest.setUsername("Honorable");
        userRequest.setEmail("honorable@gmail.com");
        userRequest.setPassword("honorable");

        postRequest.setText(" Reaction lead to action.");
        postRequest.setDescription("Action");
        postRequest.setFileUrl("C:\\Users\\mr Adio\\IdeaProjects\\BlumafricaBackend\\src\\main\\resources\\assets\\e field.jpeg");
        postRequest.setPosterId(1L);



    }

    @Test
    public void create_User_Account_Test() throws UserAlreadyExist {
        userResponse = userService.createUser(userRequest);
        assertNotNull(userResponse);
        assertNotNull(userResponse.getMessage());

    }

    @Test
    public void userPostTest() throws UserNotFound {
        postResponse = userService.makePost(postRequest);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getMessage());

    }
    @Test
    public void userEditPostTest() throws UserNotFound, PostNotFound {

        editPostResponse = userService.editPost("152", postRequest);
        assertNotNull(editPostResponse);
        assertNotNull(editPostResponse.getMessage());
    }

    @Test
    public void userDeletePostTest() throws PostNotFound {
        Long postId = 152L;
        assertThrows(PostNotFound.class, () -> userService.deletePost(postId));

    }


    @Test
    public void deletedPostCanNotBeInvokeAgain() throws PostNotFound {
        Long postId = 52L;
        assertThrows(PostNotFound.class, () -> userService.deletePost(postId));
        assertNull( userService.findPostById("52"));
        assertNull(userService.findPostById("52"));

    }
    @Test
    public void testThatUserPostsCanBeFound(){
        FetchUserPostRequest request = new FetchUserPostRequest("103");
        FetchUserPostResponse response = userService.findUserPosts(request);
        assertEquals(10, response.getUserPost().size());
    }

    @Test
    public void testThatUserCanLikePost(){
        LikeRequest likeRequest = new LikeRequest("1",  "201");
        LikeResponse response = userService.userCanLikePost(likeRequest);
        assertNotNull(response.getLikeId());
        Post foundPost = postService.getPostById("201");
        assertNotNull(response.getLikeId());
        System.out.println(response.getLikeId());
        assertEquals(1, foundPost.getListOfLikeIds().size());
    }


}

