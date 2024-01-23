package bluma.africa.blumaafrica.controllers;

import bluma.africa.blumaafrica.data.models.Authority;
import bluma.africa.blumaafrica.dtos.requests.*;
import bluma.africa.blumaafrica.dtos.responses.PostResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static bluma.africa.blumaafrica.data.models.Authority.USER;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceController {
    @Autowired
    private MockMvc mockMvc;
    private UserRequest userRequest;
    private ObjectMapper objectMapper;
    private PostRequest postRequest;
    private PostResponse postResponse;
    @BeforeEach
    void setUp(){
        userRequest = new UserRequest();
        postRequest = new PostRequest();
        objectMapper = new ObjectMapper();

        userRequest.setUsername("Jude");
        userRequest.setEmail("jude@gmail.com");
        userRequest.setPassword("judePassword");

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
    public void testThatUserCanLikePost(){
        LikeRequest request = new LikeRequest();
        request.setAuthority("USER");
        request.setUserId("1");
        request.setPostId("201");

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


}
