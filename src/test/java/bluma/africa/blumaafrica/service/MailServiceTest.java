package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.dtos.requests.Recipient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest

public class MailServiceTest {
    @Autowired
    private MailService mailService;

    @Test
    public void sentMailTest(){
        Recipient recipient = new Recipient();
        recipient.setEmail("adioldmj@gmail.com");
        recipient.setName("Adio");
        List<Recipient> recipients = List.of(
                recipient
        );
        


    }

}
