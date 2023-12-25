package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.dtos.requests.EmailRequest;
import bluma.africa.blumaafrica.dtos.requests.Recipient;
import bluma.africa.blumaafrica.dtos.responses.EmailResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setRecipients(recipients);
        emailRequest.setHtmlContent("<p>welcome to Blumafrica</p>");
        emailRequest.setSubject("Testing Bluma mail service");

        EmailResponse emailResponse = mailService.sendMail(emailRequest);
        assertNotNull(emailResponse);
        assertNotNull(emailResponse.getMessageId());
        assertNotNull(emailResponse.getCode());
        assertEquals(201,emailResponse.getCode());




    }

}
