package bluma.africa.blumaafrica.dtos.requests;

import bluma.africa.blumaafrica.data.models.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ProfileRequest {
    @NotNull(message = "input field can not be null")
    @NotBlank(message = "input field can not be blank")
    @NotEmpty(message = "input field can not be empty")
    private String firstname;
    @NotNull(message = "input field can not be null")
    @NotBlank(message = "input field can not be blank")
    @NotEmpty(message = "input field can not be empty")
    private String lastname;
    @NotNull(message = "input field can not be null")
    @NotBlank(message = "input field can not be blank")
    @NotEmpty(message = "input field can not be empty")
    private String phoneNumber;
    @NotNull(message = "input field can not be null")
    @NotBlank(message = "input field can not be blank")
    @NotEmpty(message = "input field can not be empty")
    private String about;
    @NotNull(message = "input field can not be null")
    @NotBlank(message = "input field can not be blank")
    @NotEmpty(message = "input field can not be empty")
    private String headline;
    @NotNull(message = "input field can not be null")
    @NotBlank(message = "input field can not be blank")
    @NotEmpty(message = "input field can not be empty")
    private Gender gender;
    @NotNull(message = "input field can not be null")
    @NotBlank(message = "input field can not be blank")
    @NotEmpty(message = "input field can not be empty")
    private String profilePicture;
    @NotNull(message = "input field can not be null")
    @NotBlank(message = "input field can not be blank")
    @NotEmpty(message = "input field can not be empty")
    private String coverPicture;

    @NotNull
    @NotEmpty
    @NotNull(message = "input field can not be null")
    @NotBlank(message = "input field can not be blank")
    @NotEmpty(message = "input field can not be empty")
    private Long userId;
}
