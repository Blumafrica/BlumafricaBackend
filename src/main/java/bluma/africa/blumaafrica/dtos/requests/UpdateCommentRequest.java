package bluma.africa.blumaafrica.dtos.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCommentRequest {
    @NotNull
    @NotEmpty
    private Long commentId;
    @NotNull
    @NotEmpty
    private Long commenterId;
    @NotNull
    @NotEmpty
    private String newCommentText;
}
