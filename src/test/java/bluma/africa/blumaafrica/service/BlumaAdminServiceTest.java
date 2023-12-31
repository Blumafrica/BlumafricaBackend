package bluma.africa.blumaafrica.service;


import bluma.africa.blumaafrica.data.models.Admin;
import bluma.africa.blumaafrica.dtos.requests.LoginAsAdminRequest;
import bluma.africa.blumaafrica.dtos.requests.LoginAsAdminResponse;
import bluma.africa.blumaafrica.dtos.requests.PostRequest;
import bluma.africa.blumaafrica.dtos.responses.PostResponse;
import bluma.africa.blumaafrica.exceptions.BlumaException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BlumaAdminServiceTest {

    @Autowired
    private AdminService adminService;
    @Autowired
    Admin admin ;
    @Test
    public void testThatAdminCanRegister() throws BlumaException {
        LoginAsAdminRequest request = new LoginAsAdminRequest();
        request.setEmail("mariiam22222@gmail.com");
        request.setPassword("password");
        System.out.println("admin"+ admin);

        LoginAsAdminResponse response = adminService.logInAsAdmin(request);
        assertEquals("mariiam22222@gmail.com",response.getEmail());


    }

    @Test
    public void testThatAdminExceptionIsThrownWhenUserTryToLoginWithWrongDetails() {
        LoginAsAdminRequest request = new LoginAsAdminRequest();
        request.setEmail("mariiabm22222@gmail.com");
        request.setPassword("passwhnord");
        System.out.println("admin"+ admin);
        assertThrows(BlumaException.class, ()->adminService.logInAsAdmin(request));
    }

    @Test
    public void testThatAdminCanPost(){
        PostRequest postRequest = new PostRequest();
        postRequest.setDescription("about nigeria");
        postRequest.setText("I love nigerian");
        postRequest.setFileUrl("C:\\Users\\mariam\\capstone-backend\\BlumafricaBackend\\src\\main\\resources\\assets\\e field.jpeg");
        postRequest.setPostOwner(admin);
        PostResponse response = adminService.post(postRequest);
        assertNotNull(response);
        assertEquals(1L, response.getPostId());
    }





}