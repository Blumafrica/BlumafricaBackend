package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.dtos.requests.CreateCommentRequest;
import bluma.africa.blumaafrica.dtos.requests.UpdateCommentRequest;
import bluma.africa.blumaafrica.dtos.responses.ResponseApi;
import bluma.africa.blumaafrica.exceptions.*;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class BlumaCommentServiceTest {
    @Autowired

    private CommentService commentService;

    @Test
    public void addCommentTest() throws CommentNotFoundException, UserNotFound, PostNotFoundException, PostNotFound {
        CreateCommentRequest createCommentRequest = new CreateCommentRequest();
        createCommentRequest.setCommenterId(2L);
        createCommentRequest.setCommentText("This is a test");
        var response = commentService.createComment(1L, createCommentRequest);
        assertThat(response).isNotNull();
    }
    @Test
    public void updateCommentTest() throws BlumaException {
       UpdateCommentRequest updateCommentRequest = new UpdateCommentRequest();
//        CreateCommentRequest createCommentRequest = new CreateCommentRequest();
//        createCommentRequest.setCommenterId(2L);
//        createCommentRequest.setCommentText("This is an updated test.");

        updateCommentRequest.setCommentId(1L);
        updateCommentRequest.setNewCommentText("This should be updated please");
        var response =commentService.updateComment(1L,1L,updateCommentRequest);
        assertNotNull(response);
        AssertionsForClassTypes.assertThat(response).isInstanceOf(ResponseApi.class);

    }

}
