package bluma.africa.blumaafrica.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCommentRequest {
    private Long commentId;
    private String newCommentText;
}
