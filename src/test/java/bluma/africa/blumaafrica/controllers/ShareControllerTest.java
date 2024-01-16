package bluma.africa.blumaafrica.controllers;

import bluma.africa.blumaafrica.dtos.requests.EditShareRequest;
import bluma.africa.blumaafrica.dtos.requests.ShareRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ShareControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();
    @Test
    public void testThatUserCanSharePost(){
        ShareRequest request = new ShareRequest();
        request.setAuthority("user");
        request.setPostId("1");
        request.setContent("content");
        request.setTitle("burhr");
        request.setSharerId("1");

        try {
            byte [] content = mapper.writeValueAsBytes(request);
            mockMvc.perform(post("/api/v1/share")
                            .content(content)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is2xxSuccessful())
                    .andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testThatUserCanEditShare(){
        EditShareRequest request = new EditShareRequest();
        request.setText("ive");
        request.setTitle("baddie");
        request.setPosterId("1");
        request.setPostId("1");
        request.setPosterAuthority("user");

        try {
            byte [] content = mapper.writeValueAsBytes(request);

            mockMvc.perform(put("/api/v1/edit/")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(content))
                    .andExpect(status().is2xxSuccessful())
                    .andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
