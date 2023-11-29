package bluma.africa.blumaafrica.controllers;

import bluma.africa.blumaafrica.dtos.requests.UserRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceController {
    @Autowired
    private MockMvc mockMvc;
    private UserRequest userRequest;
    private ObjectMapper objectMapper;
    @BeforeEach
    void setUp(){
        userRequest = new UserRequest();
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

}
