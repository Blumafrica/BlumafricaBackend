package bluma.africa.blumaafrica.dtos.requests;


import bluma.africa.blumaafrica.data.models.Authority;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequest {
    @NotNull
    private String text;
    @NotNull
    private String description;
    @NotNull
    private String fileUrl;
    @NotNull
    private Long postId;
    @NotNull
    private String authority;
    @NotNull
    private Long PosterId;
}