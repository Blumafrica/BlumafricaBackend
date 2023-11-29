package bluma.africa.blumaafrica.dtos.requests;

import bluma.africa.blumaafrica.data.models.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ProfileRequest {
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String about;
    private String headline;
    private Gender gender;
    private MultipartFile profilePicture;
    private MultipartFile coverPicture;
}
