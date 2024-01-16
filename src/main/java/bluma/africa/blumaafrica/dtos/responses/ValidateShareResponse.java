package bluma.africa.blumaafrica.dtos.responses;

import bluma.africa.blumaafrica.data.models.Authority;
import bluma.africa.blumaafrica.data.models.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public class ValidateShareResponse {
    private Post post;
    private Authority authority;
}
