package bluma.africa.blumaafrica.service;


import bluma.africa.blumaafrica.dtos.requests.EditShareRequest;
import bluma.africa.blumaafrica.dtos.requests.LikeRequest;
import bluma.africa.blumaafrica.dtos.requests.LikeShareRequest;
import bluma.africa.blumaafrica.dtos.requests.ShareRequest;
import bluma.africa.blumaafrica.dtos.responses.LikeResponse;
import bluma.africa.blumaafrica.dtos.responses.ShareResponse;
import bluma.africa.blumaafrica.exceptions.AuthorityException;
import bluma.africa.blumaafrica.exceptions.BlumaException;
import bluma.africa.blumaafrica.exceptions.PostNotFound;
import bluma.africa.blumaafrica.exceptions.ShareException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
class BlumaShareServiceTest {

    @Autowired
    private BlumaShareService shareService;
    @Test
    public void testThatUserCanSharePost() throws PostNotFound, AuthorityException {
        ShareRequest request = new ShareRequest();
        request.setSharerId("1");
        request.setContent("I love this");
        request.setPostId("1");
        request.setAuthority("USER");
        request.setTitle("The benin culture");
        ShareResponse response = shareService.share(request);
        assertNotNull(response);
    }
    @Test
    public void testThatExceptionIsThrownWhenUserTryToShareAPostWithWrongIdAnWrongAuthority(){
        ShareRequest request = new ShareRequest();
        request.setSharerId("1123");
        request.setContent("I love this");
        request.setPostId("1334");
        request.setAuthority("USER");
        request.setTitle("The benin culture");
        assertThrows(PostNotFound.class, ()-> shareService.share(request));
        request.setPostId("1");
        request.setAuthority("them");
        assertThrows(AuthorityException.class, ()-> shareService.share(request));
    }
    @Test
    public void testThatUserCanEditShare() throws ShareException, AuthorityException {
        EditShareRequest request = new EditShareRequest();
        request.setText("i must say this");
        request.setPostId("1");
        request.setTitle("maybe ");
        request.setPosterId("1");
        request.setPosterAuthority("User");
        Long id  = shareService.editShare(request);
        System.out.println("share id ==> "+id);
        assertNotNull(id);
    }

    @Test
    public void testThatUserCanLikeSharePost() throws BlumaException {
        LikeShareRequest request = new LikeShareRequest("1","user", "1");
        LikeResponse response = shareService.likeSharedPost(request);
        assertNotNull(response);
    }


  
}