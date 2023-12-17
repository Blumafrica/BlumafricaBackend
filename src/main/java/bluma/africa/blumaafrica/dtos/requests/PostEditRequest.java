package bluma.africa.blumaafrica.dtos.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostEditRequest {
    @NotNull
    private Long postId;
    @NotNull
    private String text;
    @NotNull
    private String description;
    @NotNull
    private String fileUrl;
}
