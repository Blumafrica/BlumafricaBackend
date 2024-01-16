package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.data.models.Admin;
import bluma.africa.blumaafrica.data.models.Likes;
import bluma.africa.blumaafrica.dtos.requests.GetAllPostLikesRequest;
import bluma.africa.blumaafrica.dtos.requests.LikeRequest;
import bluma.africa.blumaafrica.dtos.requests.UnlikeRequest;
import bluma.africa.blumaafrica.dtos.responses.GetAllPostLikesResponse;
import bluma.africa.blumaafrica.dtos.responses.LikeResponse;
import bluma.africa.blumaafrica.exceptions.BlumaException;
import bluma.africa.blumaafrica.exceptions.LikeException;
import bluma.africa.blumaafrica.exceptions.PostNotFound;
import bluma.africa.blumaafrica.exceptions.UserNotFound;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNull;

@SpringBootTest
class BlumaLikesServiceTest {


    @Autowired
    private BlumaLikesService service;

   @Test
    public void testThatUserCanLike() throws BlumaException {
       LikeRequest likeRequest = new LikeRequest();
       likeRequest.setUserId("1");
       likeRequest.setAuthority("ADMIN");
       likeRequest.setPostId("1");
       LikeResponse response = service.userCanLikePost(likeRequest);
       assertNotNull(response);
       assertNotNull(response.getLikeId());
   }
   @Test
   public void testThatUserCanGetLikesOnAPost(){
       GetAllPostLikesRequest request = new GetAllPostLikesRequest("1");
       GetAllPostLikesResponse response = service.getAllPostLikes(request);
       assertNotNull(response.getFoundLikes());
       assertEquals("working",1, response.getFoundLikes().size());
   }
    @Test
    public void testThatUserCanNotLikeTwice() {
        LikeRequest likeRequest = new LikeRequest();
        likeRequest.setPostId("1");
        likeRequest.setUserId("1");
        likeRequest.setAuthority("Admin");
        assertThrows(BlumaException.class, () -> service.userCanLikePost(likeRequest));
    }

    @Test
    public void testThatUserCanLike1() throws BlumaException {
        LikeRequest likeRequest = new LikeRequest();
        likeRequest.setPostId("1");
        likeRequest.setUserId("1");
        likeRequest.setAuthority("Admin");
        LikeResponse response = service.userCanLikePost(likeRequest);
        assertNotNull(response);
        assertNotNull(response.getLikeId());
    }

    @Test
    public void testThatUserCanUnlike() throws PostNotFound, LikeException {
       UnlikeRequest request = new UnlikeRequest();
       request.setLikeId("1");
       request.setUserId("1");
       request.setPostId("1");
       request.setAuthority("user");
       var response = service.unlikePost(request);
       Likes likes = service.getLikes("1");

       assertEquals("", null, likes);
   }
   @Test
    public void testThatUserCanLikeSharedPost() throws BlumaException {
       LikeRequest request = new LikeRequest();
       request.setShareId("1");
       request.setAuthority("user");
       request.setUserId("1");
       LikeResponse response = service.likeSharedPost(request);
       assertNotNull(response);
       assertNotNull(response.getLikeId());
   }

   @Test
    public void testThatUserCanNotLikeShareTwice(){
       LikeRequest request = new LikeRequest();
       request.setShareId("1");
       request.setAuthority("user");
       request.setUserId("1");
       assertThrows(LikeException.class, ()-> service.likeSharedPost(request));
   }

   @Test
    public void testThatUserCanUnlikeShare() throws PostNotFound, LikeException {
       UnlikeRequest unlikeRequest = new UnlikeRequest();
       unlikeRequest.setLikeId("352");
       unlikeRequest.setShareId("1");
       unlikeRequest.setUserId("1");
       unlikeRequest.setAuthority("user");
        service.unLikeShare(unlikeRequest);
       Likes likes = service.getLikes("352");
       assertEquals("",null, likes);

   }



}