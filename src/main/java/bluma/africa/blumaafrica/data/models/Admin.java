package bluma.africa.blumaafrica.data.models;

import jakarta.persistence.Id;
import lombok.Getter;
import org.springframework.stereotype.Component;


@Getter
@Component
public class Admin extends Person {

    @Id
    private Long id = 1L;
    private final String  email = "mariiam22222@gmail.com";
    private final String password = "password";

}
