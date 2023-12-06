package bluma.africa.blumaafrica.service;


import bluma.africa.blumaafrica.data.models.Authority;
import bluma.africa.blumaafrica.dtos.requests.FetchUserPostRequest;
import bluma.africa.blumaafrica.dtos.requests.PostEditRequest;
import bluma.africa.blumaafrica.dtos.requests.PostRequest;
import bluma.africa.blumaafrica.dtos.requests.UserRequest;
import bluma.africa.blumaafrica.dtos.responses.EditPostResponse;
import bluma.africa.blumaafrica.dtos.responses.FetchUserPostResponse;
import bluma.africa.blumaafrica.dtos.responses.PostResponse;
import bluma.africa.blumaafrica.dtos.responses.UserResponse;
import bluma.africa.blumaafrica.exceptions.PostNotFound;
import bluma.africa.blumaafrica.exceptions.UserAlreadyExist;
import bluma.africa.blumaafrica.exceptions.UserNotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static bluma.africa.blumaafrica.data.models.Authority.USER;
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
        postRequest.setAuthority("ADMIN");



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
        Long postId = 152L;

        editPostResponse = userService.editPost(postId, postRequest);
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
        assertThrows(PostNotFound.class, () -> userService.findPostById(postId));
        assertNull(userService.findPostById(postId));

    }
    @Test
    public void testThatUserPostsCanBeFound(){
        FetchUserPostRequest request = new FetchUserPostRequest("103");
        FetchUserPostResponse response = userService.findUserPosts(request);
        assertEquals(10, response.getUserPost().size());
    }
}

