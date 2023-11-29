package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.dtos.requests.UserRequest;
import bluma.africa.blumaafrica.dtos.responses.UserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;
    private UserRequest userRequest;
    private UserResponse userResponse;
    @BeforeEach
    void setUp(){
        userRequest = new UserRequest();
        userResponse = new UserResponse();

        userRequest.setUsername("Honorable");
        userRequest.setEmail("honorable@gmail.com");
        userRequest.setPassword("honorable");
    }
    @Test
    public void creat_User_Account_Test(){
        userResponse = userService.createUser(userRequest);
        assertNotNull(userResponse);
        assertNotNull(userResponse.getMessage());

    }
}
