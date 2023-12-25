package bluma.africa.blumaafrica.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Sender {
    private final String email;
    private final String name;


    public Sender(String email, String name) {
        this.email = email;
        this.name = name;
    }
}
