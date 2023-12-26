package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.config.MailConfig;
import bluma.africa.blumaafrica.dtos.requests.EmailRequest;
import bluma.africa.blumaafrica.dtos.responses.EmailResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@Primary
@AllArgsConstructor
public class BlumaMailService implements MailService{
    private final MailConfig mailConfig;
    @Override
    public EmailResponse sendMail(EmailRequest emailRequest) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(APPLICATION_JSON);
        httpHeaders.setAccept(List.of(APPLICATION_JSON));
        httpHeaders.set("api-key",mailConfig.getMailApiKey());
        HttpEntity<EmailRequest> requestHttpEntity = new RequestEntity<>(
                emailRequest,httpHeaders,POST, URI.create(""));
        ResponseEntity<EmailResponse> responseEntity =
                restTemplate.postForEntity(mailConfig.getMailUrl(),requestHttpEntity,EmailResponse.class);
        var emailResponse = responseEntity.getBody();
        emailResponse.setCode(responseEntity.getStatusCode().value());
        return emailResponse;
    }
}
