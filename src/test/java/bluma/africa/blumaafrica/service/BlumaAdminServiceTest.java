package bluma.africa.blumaafrica.service;


import bluma.africa.blumaafrica.data.models.Admin;
import bluma.africa.blumaafrica.data.models.Post;
import bluma.africa.blumaafrica.data.models.Profile;
import bluma.africa.blumaafrica.data.models.User;
import bluma.africa.blumaafrica.dtos.requests.*;
import bluma.africa.blumaafrica.dtos.responses.DeleteResponse;
import bluma.africa.blumaafrica.dtos.responses.FetchAdminPost;
import bluma.africa.blumaafrica.dtos.responses.FindUserResponse;
import bluma.africa.blumaafrica.dtos.responses.PostResponse;
import bluma.africa.blumaafrica.exceptions.AuthorityException;
import bluma.africa.blumaafrica.exceptions.BlumaException;
import bluma.africa.blumaafrica.exceptions.UserNotFound;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BlumaAdminServiceTest {

    @Autowired
    private AdminService adminService;


    @Test
    public void testThatAdminIsCreated(){
        Admin  admin = adminService.findAdminById("1") ;
        assertNotNull(admin);
    }


    @Test
    public void testThatAdminCanLoginAs() throws BlumaException {
        LoginAsAdminRequest request = new LoginAsAdminRequest();
        request.setEmail("mariiam22222@gmail.com");
        request.setPassword("Mariam@21");
        LoginAsAdminResponse response = adminService.logInAsAdmin(request);
        assertEquals("mariiam22222@gmail.com",response.getEmail());

    }

    @Test
    public void testThatAdminExceptionIsThrownWhenUserTryToLoginWithWrongDetails() {
        LoginAsAdminRequest request = new LoginAsAdminRequest();
        request.setEmail("mariiabm22222@gmail.com");
        request.setPassword("passwhnord");

        assertThrows(BlumaException.class, ()->adminService.logInAsAdmin(request));
    }

    @Test
    public void testThatAdminCanPost() throws BlumaException {
        PostRequest postRequest = new PostRequest();
        postRequest.setDescription("about nigeria");
        postRequest.setContent("I love nigerian");
        postRequest.setFileUrl("C:\\Users\\mariam\\capstone-backend\\BlumafricaBackend\\src\\main\\resources\\assets\\e field.jpeg");
        postRequest.setPosterId("1");
        postRequest.setAuthority("ADMIN");
        PostResponse response = adminService.post(postRequest);
        assertNotNull(response);

    }
    @Test
    public void testThatAdminCanPostThrowsBlumaExceptionWhenWrongCredentialsIsInputed() {
        PostRequest postRequest = new PostRequest();
        postRequest.setDescription("about nigeria");
        postRequest.setContent("I love nigerian");
        postRequest.setFileUrl("C:\\Users\\mariam\\capstone-backend\\BlumafricaBackend\\src\\main\\resources\\assets\\e field.jpeg");
        postRequest.setPosterId("1L");
        postRequest.setAuthority("ADMIN");
        assertThrows(BlumaException.class, ()-> adminService.post(postRequest));


    }

    @Test
    public void testThatAdminCanDeletePost() throws BlumaException {
        DeletePost deletePost = new DeletePost("223", "ADMIN");
        DeleteResponse response = adminService.deletePost(deletePost );
        Post post = adminService.findPostById(1L);
        assertNull(post);
    }

    @Test
    public void testThatAllAdminPostCanBeFetch(){
        FetchAdminPost response = adminService.fetchAllPost();
        assertEquals(0, response.getPosts().size());
    }


    @Test
    public void testThatAdminCanFindUserByIdAndAuthority() throws UserNotFound, AuthorityException {
        FindUserRequest request = new FindUserRequest("1", "admin");
        FindUserResponse response = adminService.findUser(request);
        assertEquals(response.getFoundAdmin().getClass(), Admin.class);
    }
    @Test
    public void testThatUserCanFindUserByIdAndAuthority() throws UserNotFound, AuthorityException {
        FindUserRequest request = new FindUserRequest("107", "user");
        FindUserResponse response = adminService.findUser(request);
        assertEquals(response.getUserFoundUser().getClass(), User.class);
    }

        @Test
        public void testThatUserProfileCanBeFound() throws UserNotFound, AuthorityException {
            GetUserProfile request = new GetUserProfile("107", "user");
            Profile response = adminService.getUserProfile(request);
            assertNotNull(response);
        }







}