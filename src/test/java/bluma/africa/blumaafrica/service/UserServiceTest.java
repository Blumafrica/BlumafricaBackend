package bluma.africa.blumaafrica.service;



import bluma.africa.blumaafrica.data.models.Post;
import bluma.africa.blumaafrica.data.models.User;
import bluma.africa.blumaafrica.dtos.requests.*;
import bluma.africa.blumaafrica.dtos.responses.*;
import bluma.africa.blumaafrica.exceptions.PostNotFound;
import bluma.africa.blumaafrica.exceptions.UserAlreadyExist;
import bluma.africa.blumaafrica.exceptions.UserNotFound;

import bluma.africa.blumaafrica.dtos.requests.*;
import bluma.africa.blumaafrica.dtos.responses.*;
import bluma.africa.blumaafrica.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import static bluma.africa.blumaafrica.mapper.Mapper.introductionMessage;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

import static bluma.africa.blumaafrica.data.models.Gender.MALE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class  UserServiceTest {
    @Autowired
    private UserService userService;
    private UserRequest userRequest;
    private UserResponse userResponse;
    private PostRequest postRequest;
    private PostResponse postResponse;
    private PostEditRequest postEditRequest;
    @Autowired
    private PostService postService;

    @Autowired
    private MailService mailService;
    private EditPostResponse editPostResponse;
    private ProfileRequest profileRequest;
    private CreateCommentRequest commentRequest;
    private  EmailRequest emailRequest;


    @BeforeEach
    void setUp() {
        userRequest = new UserRequest();
        userResponse = new UserResponse();
        postRequest = new PostRequest();
        editPostResponse = new EditPostResponse();
        profileRequest = new ProfileRequest();
        commentRequest = new CreateCommentRequest();

        userRequest.setUsername("classics");
        userRequest.setEmail("classidios03@gmail.com");
        userRequest.setPassword("password");
        Recipient recipient = new Recipient();
        recipient.setName(userRequest.getUsername());
        recipient.setEmail(userRequest.getEmail());
        List<Recipient> recipients = List.of(
                recipient);



        emailRequest = new EmailRequest();
        emailRequest.setRecipients(recipients);
        emailRequest.setHtmlContent(introductionMessage());
        emailRequest.setSubject("SignUp");




        profileRequest.setFirstname("John");
        profileRequest.setLastname("Mavens");
        profileRequest.setPhoneNumber("+234123454");
        profileRequest.setAbout("Digital native at semicolon");
        profileRequest.setHeadline("Reaping days ahead");
        profileRequest.setGender(MALE);
        profileRequest.setProfilePicture("C:\\Users\\mr Adio\\IdeaProjects\\BlumafricaBackend\\src\\main\\resources\\assets\\e field.jpeg");
        profileRequest.setCoverPicture("C:\\Users\\mr Adio\\IdeaProjects\\BlumafricaBackend\\src\\main\\resources\\assets\\e field.jpeg");
        profileRequest.setUserId(1L);
        postRequest.setPosterId("1");




    }

    @Test
    public void createUserAccountTest() throws UserAlreadyExist, UserNotFound, EmailException {
        userResponse = userService.createUser(userRequest);
        assertNotNull(userResponse);
        assertNotNull(userResponse.getMessage());


        String token = userResponse.getToken();
        assertNotNull(token);
        log.info("token ::{}",token);


        EmailResponse emailResponse = mailService.sendMail(emailRequest);
        Assertions.assertNotNull(emailResponse);
        Assertions.assertNotNull(emailResponse.getMessageId());
        Assertions.assertNotNull(emailResponse.getCode());
        assertEquals(201,emailResponse.getCode())

    }

    @Test
    public void userProfileTest() throws UserNotFound {
         ProfileResponse response = userService.setProfile(profileRequest);
         assertNotNull(response.getMessage());
    }

    @Test
    public void getUserByIdTest(){
        Long userId = 1L;
    }
//    @Test
//    public void userEditPostTest() throws UserNotFound, PostNotFound {
//
//        editPostResponse = postService.editPost("152", postRequest);
//        assertNotNull(editPostResponse);
//        assertNotNull(editPostResponse.getMessage());
//    }
//
//    @Test
//    public void userDeletePostTest() throws PostNotFound {
//        Long postId = 152L;
//        assertThrows(PostNotFound.class, () -> userService.deletePost(postId));
//
//    }
//
//
//    @Test
//    public void deletedPostCanNotBeInvokeAgain() throws PostNotFound {
//        Long postId = 52L;
//        assertThrows(PostNotFound.class, () -> userService.deletePost(postId));
//        assertNull( postService.getPostById("52"));
//        assertNull(postService.getPostById("52"));
//
//    }
//    @Test
//    public void testThatUserPostsCanBeFound(){
//        FetchUserPostRequest request = new FetchUserPostRequest("103");
//        FetchUserPostResponse response = userService.findUserPosts(request);
//        assertEquals(10, response.getUserPost().size());
//    }
//
//    @Test
//    public void testThatUserCanLikePost(){
//        LikeRequest likeRequest = new LikeRequest("1",  "201");
//        LikeResponse response = userService.userCanLikePost(likeRequest);
//        assertNotNull(response.getLikeId());
//        Post foundPost = postService.getPostById("201");
//        assertNotNull(response.getLikeId());
//        System.out.println(response.getLikeId());
//        assertEquals(1, foundPost.getListOfLikeIds().size());
//    }



    public void userUpdateProfileTest() throws UserNotFound {
        ProfileResponse response = userService.updateProfile(profileRequest);
        assertNotNull(response.getMessage());
    }



}

