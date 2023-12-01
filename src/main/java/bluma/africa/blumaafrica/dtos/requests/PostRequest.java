package bluma.africa.blumaafrica.dtos.requests;


import bluma.africa.blumaafrica.data.models.Authority;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequest {

    private String text;
    private String description;
    private String fileUrl;
    private Long id;
    private String authority;
    private Long PosterId;
}
