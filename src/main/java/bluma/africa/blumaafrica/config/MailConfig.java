package bluma.africa.blumaafrica.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@Getter
public class MailConfig {
    private Environment environment;
    @Value("${mail.api.key}")
    private String mailApiKey;
    @Value("${brevo.mail.url}")
    private String mailUrl;

    public  void createEnvironmentVariable(){
        String mailApiKey = environment.getProperty("mail.api.key");
        String mailUrl = environment.getProperty("brevo_url");

    }
}
