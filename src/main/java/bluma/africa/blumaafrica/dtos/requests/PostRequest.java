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
    @NotNull(message = "input field can not be null")
    @NotBlank(message = "input field can not be blank")
    @NotEmpty(message = "input field can not be empty")
    private String text;
    @NotNull(message = "input field can not be null")
    @NotBlank(message = "input field can not be blank")
    @NotEmpty(message = "input field can not be empty")
    private String description;
    @NotNull(message = "input field can not be null")
    @NotBlank(message = "input field can not be blank")
    @NotEmpty(message = "input field can not be empty")
    private String fileUrl;
    @NotNull(message = "input field can not be null")
    @NotBlank(message = "input field can not be blank")
    @NotEmpty(message = "input field can not be empty")
    private Long id;
    @NotNull(message = "input field can not be null")
    @NotBlank(message = "input field can not be blank")
    @NotEmpty(message = "input field can not be empty")
    private String authority;
    @NotNull(message = "input field can not be null")
    @NotBlank(message = "input field can not be blank")
    @NotEmpty(message = "input field can not be empty")
    private Long PosterId;
}
