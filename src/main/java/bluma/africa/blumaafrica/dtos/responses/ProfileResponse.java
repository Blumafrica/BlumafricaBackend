package bluma.africa.blumaafrica.dtos.responses;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ProfileResponse {

    private final String message = "your profile successfully created";

}
