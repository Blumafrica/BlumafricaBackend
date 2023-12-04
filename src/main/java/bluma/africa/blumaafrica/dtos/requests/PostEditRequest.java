package bluma.africa.blumaafrica.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostEditRequest {
    private Long postId;
    private String text;
    private String description;
    private String fileUrl;
}
