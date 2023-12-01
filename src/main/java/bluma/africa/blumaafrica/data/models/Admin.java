package bluma.africa.blumaafrica.data.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;


@Getter
@Component
public class Admin  {

    @Id
    private final Long id = 1L;
    private final String  email = "mariiam22222@gmail.com" ;
    private final String password = "@Ahhaj(JJI";
    @Enumerated(EnumType.STRING)
    private List<Authority> authority = List.of(Authority.ADMIN);

}
