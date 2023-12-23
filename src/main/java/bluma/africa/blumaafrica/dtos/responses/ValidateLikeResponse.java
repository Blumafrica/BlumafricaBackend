package bluma.africa.blumaafrica.dtos.responses;

import bluma.africa.blumaafrica.data.models.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class ValidateLikeResponse {


    private boolean isValidate;
    private Post foundPost;
    private Long userId;
}
