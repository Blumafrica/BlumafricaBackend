package bluma.africa.blumaafrica.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;


@Getter
@Setter
@Entity(name = "admin")
@Component
public class Admin  {

    @Id
    private Long id;
    private String  email  ;
    private String password;
    @Enumerated(value = EnumType.STRING)
    private List<Authority> authority;


}
