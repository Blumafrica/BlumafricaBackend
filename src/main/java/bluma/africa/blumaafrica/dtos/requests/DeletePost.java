package bluma.africa.blumaafrica.dtos.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DeletePost {
    @NotNull
    @NotEmpty
    private String postId;
    @NotNull
    @NotEmpty
    private String userAuthority;
}
