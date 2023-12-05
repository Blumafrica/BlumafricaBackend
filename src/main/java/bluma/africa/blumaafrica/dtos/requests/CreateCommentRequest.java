package bluma.africa.blumaafrica.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCommentRequest {
    private Long commenterId;
    //private Long postId;
    private String commentText;
}
