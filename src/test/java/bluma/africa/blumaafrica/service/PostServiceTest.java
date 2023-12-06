package bluma.africa.blumaafrica.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PostServiceTest {

    @Autowired
    private PostService service;

    @Test
    public void testThatUserPostCanBeFetch(){
        var response = service.getUserPosts("103");
        assertEquals(10, response.size());
    }
}
