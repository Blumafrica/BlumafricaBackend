package bluma.africa.blumaafrica.service;


import bluma.africa.blumaafrica.dtos.requests.*;
import bluma.africa.blumaafrica.dtos.responses.*;
import bluma.africa.blumaafrica.exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static bluma.africa.blumaafrica.data.models.Gender.MALE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;
    private UserRequest userRequest;
    private UserResponse userResponse;
    private PostRequest postRequest;
    private PostResponse postResponse;

    private EditPostResponse editPostResponse;
    private ProfileRequest profileRequest;
    private CreateCommentRequest commentRequest;


    @BeforeEach
    void setUp() {
        userRequest = new UserRequest();
        userResponse = new UserResponse();
        postRequest = new PostRequest();
        editPostResponse = new EditPostResponse();
        profileRequest = new ProfileRequest();
        commentRequest = new CreateCommentRequest();

        userRequest.setUsername("Honorable");
        userRequest.setEmail("honorable@gmail.com");
        userRequest.setPassword("honorable");


//
        profileRequest.setFirstname("John");
        profileRequest.setLastname("Mavens");
        profileRequest.setPhoneNumber("+234123454");
        profileRequest.setAbout("Digital native at semicolon");
        profileRequest.setHeadline("Reaping days ahead");
        profileRequest.setGender(MALE);
        profileRequest.setProfilePicture("C:\\Users\\mr Adio\\IdeaProjects\\BlumafricaBackend\\src\\main\\resources\\assets\\e field.jpeg");
        profileRequest.setCoverPicture("C:\\Users\\mr Adio\\IdeaProjects\\BlumafricaBackend\\src\\main\\resources\\assets\\e field.jpeg");
        profileRequest.setUserId(1L);
        postRequest.setPosterId(1L);
    }

    @Test
    public void create_User_Account_Test() throws UserAlreadyExist {
        userResponse = userService.createUser(userRequest);
        assertNotNull(userResponse);
        assertNotNull(userResponse.getMessage());

    }

    @Test
    public void userProfileTest() throws UserNotFound {
         ProfileResponse response = userService.setProfile(profileRequest);
         assertNotNull(response.getMessage());
    }
    @Test
    public void userUpdateProfileTest() throws UserNotFound {
        ProfileResponse response = userService.updateProfile(profileRequest);
        assertNotNull(response.getMessage());
    }


}

