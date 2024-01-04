package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.dtos.requests.CreateCarnivalFestivalRequest;
import bluma.africa.blumaafrica.dtos.responses.CarnivalResponse;
import bluma.africa.blumaafrica.exceptions.AuthorityException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Carnival_FestivalServiceTest {


    @Autowired
    private Carnival_FestivalService service;

    @Test
    public void testThatCarnival_FestivalCanBeCreated() throws AuthorityException {
        CreateCarnivalFestivalRequest request = new CreateCarnivalFestivalRequest();
        request.setId("1");
        request.setCity("lagos");
        request.setAbout("enugun ");
        request.setDay("monday");
        request.setAuthority("admin");
        request.setMonth("october");
        request.setState("location");
        request.setTime("7:00");
        request.setFilesUrl(List.of("C:\\Users\\mariam\\"));
        request.setYear("2024");
        request.setStreet("abule");
        request.setName("eyo");
        String response = service.createCarnivalAndFestival(request);
        assertNotNull(response);
    }
}