package bluma.africa.blumaafrica.controllers;

import bluma.africa.blumaafrica.dtos.requests.FetchUserPostRequest;
import bluma.africa.blumaafrica.dtos.requests.LoginRequest;
import bluma.africa.blumaafrica.dtos.requests.PostRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private PostRequest postRequest;

    @BeforeEach
    void setUp(){
        objectMapper = new ObjectMapper();
        postRequest = new PostRequest();

        postRequest.setContent("Reader are leader");
        postRequest.setDescription("The controller");
        postRequest.setFileUrl("C:\\Users\\mr Adio\\IdeaProjects\\BlumafricaBackend\\src\\main\\resources\\assets\\e field.jpeg");
        postRequest.setAuthority("user");
        postRequest.setPosterId(1L);



    }

    @Test
    public void userPostTest(){
        String token =
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                        "eyJpYXQiOjE3MDYyNDE1MTAsImV4cCI6MTcwNjMyNzkxMCwiaXNzIjoi" +
                        "Qmx1bWFmcmljYSAuIiwic3ViIjoiYWRpb2xkbWpAZ21haWwuY29tIiwiY2xhaW1zIjpbIlVTRVIiXX0." +
                        "lmnHx46f5qdx-JDrbVaWeS675sOj8VIu_bSk7b5Zebk";
        try{
            mockMvc.perform(post("/api/v1/post/post").
                            header("Authorization","Bearer "+ token).
                            content(objectMapper.writeValueAsBytes(postRequest)).
                            contentType(MediaType.APPLICATION_JSON)).
                    andExpect(status().is2xxSuccessful()).
                    andDo(print());

        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
    @Test
    public void userEditPostTest()  {
        Long postId = 102L;
        try{
            mockMvc.perform(post("/api/v1/user/{postId}/editPost/",postId).
                            content(objectMapper.writeValueAsBytes(postRequest)).
                            contentType(MediaType.APPLICATION_JSON)).
                    andExpect(status().is2xxSuccessful()).
                    andDo(print());

        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
    @Test
    public void userDeletePostTest(){
        Long postId =   102L;
        try{
            mockMvc.perform(delete("/api/v1/{postId}/deletePost/",postId)).
                    andExpect(status().is2xxSuccessful()).
                    andDo(print());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testAllUserPostCanBeFound(){
        FetchUserPostRequest request = new FetchUserPostRequest("103");

        try {
            byte [] content = objectMapper.writeValueAsBytes(request);
            mockMvc.perform(get("api/v1/getPosts")
                            .content(content)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is2xxSuccessful())
                    .andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
