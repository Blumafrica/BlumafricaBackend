package bluma.africa.blumaafrica.dtos.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LikeRequest {
    @NotNull
    @NotEmpty
    private String authority;
    @NotNull
    @NotEmpty
    private String userId;
    @NotNull
    @NotEmpty
    private String postId;
}
