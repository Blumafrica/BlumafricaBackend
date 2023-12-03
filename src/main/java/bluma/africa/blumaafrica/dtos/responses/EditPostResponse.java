package bluma.africa.blumaafrica.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter

public class EditPostResponse {
    private String message;

    public String getMessage(){
        return "Post Updated";
    }
}
