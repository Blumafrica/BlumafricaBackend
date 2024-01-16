package bluma.africa.blumaafrica.controllers;

import bluma.africa.blumaafrica.dtos.requests.CreateCommentRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Part;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockPart;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CommentControllerTest {


    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper mapper  = new ObjectMapper();

    private  CreateCommentRequest createCommentRequest;
    @BeforeEach
    void setUp(){
        CreateCommentRequest createCommentRequest = new CreateCommentRequest();
        createCommentRequest.setCommenterId("1");
        createCommentRequest.setCommentText("Ok");

    }

    @Test
    public void userCanCreatCommentTest() {

        CreateCommentRequest createCommentRequest = new CreateCommentRequest();
        createCommentRequest.setCommenterId("1");
        createCommentRequest.setCommentText("Ok");
        createCommentRequest.setCommenterAuthority("ADMIN");
        createCommentRequest.setPostId("1");


        try {

            byte [] content = mapper.writeValueAsBytes(createCommentRequest);

            mockMvc.perform(post("/ap1/v1/comment")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(content))
                    .andExpect(status().is2xxSuccessful())
                    .andDo(print());

        } catch (Exception e) {
            e.printStackTrace();

        }


    }
}
