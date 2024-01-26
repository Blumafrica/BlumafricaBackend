package bluma.africa.blumaafrica.controllers;

import bluma.africa.blumaafrica.dtos.requests.*;
import bluma.africa.blumaafrica.dtos.responses.PostResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static bluma.africa.blumaafrica.data.models.Gender.MALE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private UserRequest userRequest;
    private ObjectMapper objectMapper;
    private PostRequest postRequest;
    private PostResponse postResponse;
    private LoginRequest loginRequest;
    private ProfileRequest profileRequest;

    @BeforeEach
    void setUp(){
        userRequest = new UserRequest();
        postRequest = new PostRequest();
        objectMapper = new ObjectMapper();
        loginRequest = new LoginRequest();
        profileRequest = new ProfileRequest();


        userRequest.setUsername("Jude");
        userRequest.setEmail("jude@gmail.com");
        userRequest.setPassword("judePassword");

        loginRequest.setEmail("adioldmj@gmail.com");
        loginRequest.setPassword("password");

        profileRequest.setFirstname("John");
        profileRequest.setLastname("Mavens");
        profileRequest.setPhoneNumber("+234123454");
        profileRequest.setAbout("Digital native at semicolon");
        profileRequest.setHeadline("Reaping days ahead");
        profileRequest.setGender(MALE);
        profileRequest.setProfilePicture("C:\\Users\\mr Adio\\IdeaProjects\\BlumafricaBackend\\src\\main\\resources\\assets\\e field.jpeg");
        profileRequest.setCoverPicture("C:\\Users\\mr Adio\\IdeaProjects\\BlumafricaBackend\\src\\main\\resources\\assets\\e field.jpeg");
        profileRequest.setUserId(2L);

    }
    @Test
    public void userSignUpTest(){
        try{
            mockMvc.perform(post("/api/v1/user/register").
                    content(objectMapper.writeValueAsBytes(userRequest)).
                    contentType(MediaType.APPLICATION_JSON)).
                    andExpect(status().is2xxSuccessful()).
                    andDo(print());
        }catch (Exception exception){
            exception.printStackTrace();
        }

    }
    @Test
    public void userLoginTest(){
        try{
            mockMvc.perform(post("/api/v1/user/login").
                            content(objectMapper.writeValueAsBytes(loginRequest)).
                            contentType(MediaType.APPLICATION_JSON)).
                    andExpect(status().is3xxRedirection()).
                    andDo(print());
        }catch (Exception exception){
            exception.printStackTrace();
        }

    }


    @Test
    public void testThatUserCanLikePost(){
        LikeRequest request = new LikeRequest();
        request.setAuthority("USER");
        request.setUserId(1L);
        request.setPostId(201L);

        try {
            byte [] content = objectMapper.writeValueAsBytes(request);
            mockMvc.perform(post("/api/v1/likePosts")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(content))
                    .andExpect(status().is2xxSuccessful())
                    .andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testThatAnotherUserCanLogin(){
        LoginRequest request = new LoginRequest();
        request.setEmail("classidios@gmail.com");
        request.setPassword("password");

        try {
            byte [] content = objectMapper.writeValueAsBytes(request);

            mockMvc.perform(post("/api/v1/user/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(content))
                    .andExpect(status().is3xxRedirection())
                    .andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testUserProfile(){
        String token ="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE3MDYyNDQ4MzEsImV4cCI6MTcwNjMzMTIzMSwiaXNzIjoiQmx1bWFmcmljYSAuIiwic3ViIjoiY2xhc3NpZGlvc0BnbWFpbC5jb20iLCJjbGFpbXMiOlsiVVNFUiJdfQ.-htgFGzMD7O_t-PDd4dxn7nwRh5LJF9a0rEHD6NAMz0";
        try{
            mockMvc.perform(post("/api/v1/user/profile").
                    header("Authorization","Bearer "+ token).
                    content(objectMapper.writeValueAsBytes(profileRequest)).
                    contentType(MediaType.APPLICATION_JSON)).
                    andExpect(status().is2xxSuccessful()).
                    andDo(print());
        }catch (Exception exception){
            exception.printStackTrace();
        }

    }


}
