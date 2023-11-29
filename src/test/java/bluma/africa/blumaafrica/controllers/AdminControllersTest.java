package bluma.africa.blumaafrica.controllers;

import bluma.africa.blumaafrica.dtos.requests.LoginAsAdminRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
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
class AdminControllersTest {



    @Autowired
    private  MockMvc mockMvc ;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testThatAdminCanLogin(){
        LoginAsAdminRequest request = new LoginAsAdminRequest();
        request.setEmail("mariiam22222@gmail.com");
        request.setPassword("password");

        try {
            byte [] content = mapper.writeValueAsBytes(request);
            mockMvc.perform(post("/api/v1/loginAsAdmin")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(content))
                    .andExpect(status().is2xxSuccessful())
                    .andDo(print());
        } catch (Exception e) {
                   e.printStackTrace();
        }
    }
}