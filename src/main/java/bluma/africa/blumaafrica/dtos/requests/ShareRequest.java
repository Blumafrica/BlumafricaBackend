package bluma.africa.blumaafrica.dtos.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShareRequest {

    @NotNull
    private String sharerId;
    @NotNull
    private String postId;
    @NotNull
    private String title;
    @NotNull
    private String content;
    @NotNull
    private String authority;

}
