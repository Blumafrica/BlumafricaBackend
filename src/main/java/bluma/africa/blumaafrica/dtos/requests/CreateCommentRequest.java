package bluma.africa.blumaafrica.dtos.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCommentRequest {
    @NotNull
    @NotEmpty
    private String commenterId;
    @NotNull
    @NotEmpty
    private String commentText;
    private String postId;
    private String commenterAuthority;
}
