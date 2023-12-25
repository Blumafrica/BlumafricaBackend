package bluma.africa.blumaafrica.service;


import bluma.africa.blumaafrica.dtos.requests.ShareRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlumaShareServiceTest {

    @Autowired
    private ShareService shareService;
    @Test
    public void testThatUserCanSharePost(){
        ShareRequest request = new ShareRequest();
        request.setSharerId("1");
        request.setContent("I love this");
        request.setPostId("1");
        request.setAuthority("USER");
        request.setTitle("The benin culture");
        request.
    }
}