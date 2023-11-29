package bluma.africa.blumaafrica.dtos.requests;


import bluma.africa.blumaafrica.data.models.Person;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequest {

    private String text;
    private String description;
    private String fileUrl;
    private Person postOwner;
}
