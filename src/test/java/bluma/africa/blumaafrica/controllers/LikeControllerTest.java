package bluma.africa.blumaafrica.controllers;

import bluma.africa.blumaafrica.dtos.requests.GetAllPostLikesRequest;
import bluma.africa.blumaafrica.dtos.requests.LikeRequest;
import bluma.africa.blumaafrica.dtos.requests.LikeShareRequest;
import bluma.africa.blumaafrica.dtos.requests.UnlikeRequest;
import bluma.africa.blumaafrica.service.LikesService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RestController;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
public class LikeControllerTest {


    private ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc ;

    @Test
    public void testThatUserCanLikePost(){
        LikeRequest request = new LikeRequest();
          request.setUserId(1L);
          request.setPostId(1L);
          request.setAuthority("ADMIN");
        try {
            byte [] content = mapper.writeValueAsBytes(request);
            mockMvc.perform(post("/api/like/")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(content))
                    .andExpect(status().is2xxSuccessful())
                    .andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testThatAllPostLikes(){
        GetAllPostLikesRequest request = new GetAllPostLikesRequest(1L);

        try {
            byte [] content = mapper.writeValueAsBytes(request);
            mockMvc.perform(post("/api/getAllPostLikes/")
                    .content(content)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is2xxSuccessful())
                    .andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void unlikePost(){
        UnlikeRequest request = new UnlikeRequest();
           request.setLikeId("1");
           request.setPostId(1L);
           request.setUserId(1L);
           request.setAuthority("user");
        try {
            byte [] content = mapper.writeValueAsBytes(request);

            mockMvc.perform(delete("/api/unlike/")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(content))
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testThatUserCanLikeShare(){
        LikeShareRequest likeShareRequest = new LikeShareRequest("1", "user", "234");

        try {
            byte [] content = mapper.writeValueAsBytes(likeShareRequest);

            mockMvc.perform(post("/api/v1/likeShare/")
                    .content(content)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is2xxSuccessful())
                    .andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testThatUserCanUnLike(){
        UnlikeRequest request = new UnlikeRequest();
        request.setLikeId("234");
        request.setShareId(1L);
        request.setUserId(1L);
        request.setAuthority("user");
        try {
            byte [] content = mapper.writeValueAsBytes(request);

            mockMvc.perform(delete("/api/v1/unlikeShare/")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(content))
                    .andExpect(status().is2xxSuccessful())
                    .andDo(print());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
