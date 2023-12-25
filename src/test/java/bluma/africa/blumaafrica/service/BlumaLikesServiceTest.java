package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.data.models.Likes;
import bluma.africa.blumaafrica.dtos.requests.LikeRequest;
import bluma.africa.blumaafrica.dtos.responses.LikeResponse;
import bluma.africa.blumaafrica.exceptions.PostNotFound;
import bluma.africa.blumaafrica.exceptions.UserNotFound;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
class BlumaLikesServiceTest {


    @Autowired
    private BlumaLikesService service;


   @Test
    public void testThatUserCanLike() throws UserNotFound, PostNotFound {
       LikeRequest likeRequest = new LikeRequest("1",  "201");
       var response = service.userCanLikePost(likeRequest);
       assertNotNull(response.getPostId());
   }

    @Test
    public void testThatUserCanUnlike(){
    UnlikeR
   }
}