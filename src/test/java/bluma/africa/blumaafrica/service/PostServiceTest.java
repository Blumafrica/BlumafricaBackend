package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.dtos.requests.FetchUserPostRequest;
import bluma.africa.blumaafrica.dtos.requests.PostEditRequest;
import bluma.africa.blumaafrica.dtos.requests.PostRequest;
import bluma.africa.blumaafrica.dtos.responses.EditPostResponse;
import bluma.africa.blumaafrica.dtos.responses.FetchUserPostResponse;
import bluma.africa.blumaafrica.dtos.responses.PostResponse;
import bluma.africa.blumaafrica.exceptions.BlumaException;
import bluma.africa.blumaafrica.exceptions.PostNotFound;
import bluma.africa.blumaafrica.exceptions.UserNotFound;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class PostServiceTest {

    @Autowired
    private PostService postService;
    private PostRequest postRequest;
    private PostEditRequest postEditRequest;
    @BeforeEach
    void setUp(){
        postRequest = new PostRequest();
        postEditRequest = new PostEditRequest();
        postRequest.setAuthority("user");
        postRequest.setContent(" Reaction lead to action.");
        postRequest.setDescription("Action");
        postRequest.setFileUrl("C:\\Users\\mr Adio\\IdeaProjects\\BlumafricaBackend\\src\\main\\resources\\assets\\e field.jpeg");
        postRequest.setPosterId(1L);

        postRequest.setContent("Gin Jin Sin");
        postRequest.setDescription("The sinner");
        postRequest.setFileUrl("my file location");
    }

    @Test
    public void userPostTest() throws BlumaException {
      PostResponse postResponse = postService.creatPost(postRequest);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getMessage());

    }
    @Test
    public void testThatAdminCanPost() throws BlumaException {
        PostRequest postRequest = new PostRequest();
        postRequest.setDescription("about nigeria");
        postRequest.setContent("I love nigerian");
        postRequest.setFileUrl("C:\\Users\\mariam\\capstone-backend\\BlumafricaBackend\\src\\main\\resources\\assets\\e field.jpeg");
        postRequest.setPosterId(1L);
        postRequest.setAuthority("ADMIN");
        PostResponse response = postService.creatPost(postRequest);
        assertNotNull(response);
        assertNotNull(response.getMessage());
        System.out.println(response.getMessage());

    }
    @Test
    public void testThatAdminCanPostThrowsBlumaExceptionWhenWrongCredentialsIsInputed() {
        PostRequest postRequest = new PostRequest();
        postRequest.setDescription("about nigeria");
        postRequest.setContent("I love nigerian");
        postRequest.setFileUrl("C:\\Users\\mariam\\capstone-backend\\BlumafricaBackend\\src\\main\\resources\\assets\\e field.jpeg");
        postRequest.setPosterId(2L);
        postRequest.setAuthority("ADMIN");
        assertThrows(BlumaException.class, ()-> postService.creatPost(postRequest));


    }

    @Test
    public void userEditPostTest() throws UserNotFound, PostNotFound {
        Long postId = 2L;
       EditPostResponse editPostResponse = postService.editPost(postId,postRequest);
        assertNotNull(editPostResponse);
        assertNotNull(editPostResponse.getMessage());
    }


    @Test
    public void testThatUserPostCanBeFetch(){
        var response = postService.getUserPosts("103");
        assertEquals(10, response.size());
    }
    @Test
    public void userDeletePostTest() throws PostNotFound {
        Long postId = 52L;
        PostResponse response = postService.deletePostById(postId);
        assertNotNull(response.getMessage());

    }
    @Test
    public void testThatUserPostsCanBeFound(){
        FetchUserPostRequest request = new FetchUserPostRequest("103");
        FetchUserPostResponse response = postService.findUserPosts(request);
        assertEquals(10, response.getUserPost().size());
    }
     @Test
    public void testIfUserCanPostWithOutRegistering() throws BlumaException {
        PostRequest request = new PostRequest();
        request.setContent("just testing");
        request.setPosterId(1L);
        request.setDescription("let's see");
        request.setAuthority("master");
        var response  = postService.creatPost(request);
     }
     @Test
    public void getAllPostTest(){
        List<PostResponse> posts = postService.getAllPost(1,3);
        log.info("post :: {}",posts);
        assertThat(posts).hasSize(3);
     }

}
