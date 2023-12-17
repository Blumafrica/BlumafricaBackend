package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.data.models.Likes;
import bluma.africa.blumaafrica.dtos.requests.LikeRequest;
import bluma.africa.blumaafrica.dtos.requests.UnlikeRequest;
import bluma.africa.blumaafrica.dtos.responses.LikeResponse;
import bluma.africa.blumaafrica.exceptions.BlumaException;
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
    public void testThatUserCanLike() throws BlumaException {
       LikeRequest likeRequest = new LikeRequest("ADMIN",  "1", "1");
       var response = service.userCanLikePost(likeRequest);
       assertNotNull(response.getPostId());
   }

  @Test
  public void testThatUserCanUnlike() throws PostNotFound {
      UnlikeRequest likeRequest = new UnlikeRequest("1",  "1", "1");
      var response =service.unlikePost(likeRequest);
      assertNotNull(response);
  }
}