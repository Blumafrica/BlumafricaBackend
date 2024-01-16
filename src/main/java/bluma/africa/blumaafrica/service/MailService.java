package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.dtos.requests.EmailRequest;
import bluma.africa.blumaafrica.dtos.responses.EmailResponse;

public interface MailService {
    EmailResponse sendMail(EmailRequest emailRequest);
}
