package bluma.africa.blumaafrica.dtos.requests;


import bluma.africa.blumaafrica.data.models.Authority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

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