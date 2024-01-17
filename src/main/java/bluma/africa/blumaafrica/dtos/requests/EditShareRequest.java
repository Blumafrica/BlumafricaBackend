package bluma.africa.blumaafrica.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EditShareRequest {
    private String postId;
    private String title;
    private String  text;
    private String posterId;
    private String posterAuthority;
}
