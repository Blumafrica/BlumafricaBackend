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
    public void addCommentTest() throws CommentNotFoundException, UserNotFound, PostNotFoundException, PostNotFound, AuthorityException {
        CreateCommentRequest createCommentRequest = new CreateCommentRequest();
        createCommentRequest.setCommenterId("1");
        createCommentRequest.setPostId("1");
        createCommentRequest.setCommenterAuthority("user");
        createCommentRequest.setCommentText("This is a test");
        var response = commentService.createComment( createCommentRequest);
        assertThat(response).isNotNull();
    }
    @Test
    public void updateCommentTest() throws BlumaException {
       UpdateCommentRequest updateCommentRequest = new UpdateCommentRequest();
        updateCommentRequest.setCommentId(1L);
        updateCommentRequest.setCommenterId(1L);;
        updateCommentRequest.setNewCommentText("This should be updated please");
        var response =commentService.updateComment(updateCommentRequest);
        assertNotNull(response);
        AssertionsForClassTypes.assertThat(response).isInstanceOf(ResponseApi.class);

    }
    @Test
    public void testThatUserCanCommentOnShare() throws ShareException, AuthorityException {
        CreateCommentRequest createCommentRequest = new CreateCommentRequest();
        createCommentRequest.setCommenterId("1");
        createCommentRequest.setCommenterAuthority("admin");
        createCommentRequest.setPostId("1");
        String commentId = commentService.commentOnShare(createCommentRequest);

    }



}
