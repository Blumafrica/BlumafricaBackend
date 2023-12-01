//package bluma.africa.blumaafrica.controllers;
//
//import bluma.africa.blumaafrica.dtos.requests.CreateCommentRequest;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.http.Part;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.mock.web.MockPart;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class CommentControllerTest {
//
//
//    @Autowired
//    private MockMvc mockMvc;
//    private ObjectMapper mapper ;
//
//    private  CreateCommentRequest createCommentRequest;
//    @BeforeEach
//    void setUp(){
//        CreateCommentRequest createCommentRequest = new CreateCommentRequest();
//        createCommentRequest.setCommenterId(12L);
//        createCommentRequest.setCommentText("Ok");
//        mapper = new ObjectMapper();
//
//    }
//
//    @Test
//    public void userCanCreatCommentTest(){
//        Long postId= 1L;
//        Part part= new MockPart(String.valueOf(postId), new byte[]{49});
//        try{
//            mockMvc.perform(post("/ap1/v1/comment").
//                    content(mapper.writeValueAsBytes(createCommentRequest))
//            )
//        }
//
//    }
//}
