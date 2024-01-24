package bluma.africa.blumaafrica.controllers;

import bluma.africa.blumaafrica.dtos.requests.FindUserRequest;
import bluma.africa.blumaafrica.dtos.requests.LoginAsAdminRequest;
import bluma.africa.blumaafrica.dtos.requests.PostRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Objects;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@Slf4j
//@AllArgsConstructor
class AdminControllersTest {


    @Autowired
    private  MockMvc mockMvc ;

    private final  ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testThatAdminCanLogin(){
        LoginAsAdminRequest request = new LoginAsAdminRequest();
        request.setEmail("mariiam22222@gmail.com");
        request.setPassword("Mariam@21");

        try {
            byte [] content = mapper.writeValueAsBytes(request);
             mockMvc.perform(post("/api/v1/login/")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(content))
                    .andExpect(status().is2xxSuccessful())
                    .andDo(print());
        } catch (Exception e) {
                   e.printStackTrace();
        }

    }

    @Test
    public void testThatUserCanPost(){
        PostRequest request =  new PostRequest();
        request.setAuthority("ADMIN");
        request.setPosterId("1");
        request.setContent("Test admin");
        request.setDescription("description");
        request.setFileUrl("C:\\Users\\mariam\\Desktop\\BlumafricaBackend\\src\\main\\resources\\assets\\the cat.jpeg");


        try {
            byte [] content = mapper.writeValueAsBytes(request);
            mockMvc.perform(post("/api/v1/post")
                    .content(content)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is2xxSuccessful())
                    .andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public  void testThatAdminCanDeletePost(){

        try {
            byte []  content = mapper.writeValueAsBytes(1L);
            mockMvc.perform(post("/api/v1/deletePost{1}"))
                    .andExpect(status().is2xxSuccessful())
                    .andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testThatAdminPostCanBeFetch(){
        try {
            mockMvc.perform(get("/api/v1/getAdminPost")
                    )
                    .andExpect(status().is3xxRedirection())
                    .andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testThatUserCanBeFetch(){
        FindUserRequest request = new FindUserRequest("107", "user");

        try {
            byte [] content = mapper.writeValueAsBytes(request);
            mockMvc.perform(get("/api/getUser/")
                            .content(content)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is2xxSuccessful())
                    .andDo(print());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testThatAdminCanBeFetch(){
        FindUserRequest request = new FindUserRequest("1", "admin");

        try {
            byte [] content = mapper.writeValueAsBytes(request);
            mockMvc.perform(get("/api/getUser/")
                            .content(content)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is2xxSuccessful())
                    .andDo(print());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}