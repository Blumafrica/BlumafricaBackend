package bluma.africa.blumaafrica.dtos.responses;

import bluma.africa.blumaafrica.data.models.Post;
import bluma.africa.blumaafrica.data.models.Share;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@RequiredArgsConstructor
@Setter
public class ValidateLikeResponse {


    private boolean isValidate;
    private Post foundPost;
    private Long userId;
    private Share foundShare;
}
