package bluma.africa.blumaafrica.dtos.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter

public class LikeRequest {
    @NotNull
    @NotEmpty
    private String authority;
    @NotNull
    @NotEmpty
    private Long userId;

    private Long postId;
    private Long shareId;

}
