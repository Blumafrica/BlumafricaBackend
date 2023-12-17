package bluma.africa.blumaafrica.dtos.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostEditRequest {
    @NotNull
    @NotEmpty
    private Long postId;
    @NotNull
    @NotEmpty
    private String text;
    @NotNull
    @NotEmpty
    private String description;
    @NotNull
    @NotEmpty
    private String fileUrl;
}
