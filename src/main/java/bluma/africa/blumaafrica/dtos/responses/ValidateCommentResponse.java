package bluma.africa.blumaafrica.dtos.responses;


import bluma.africa.blumaafrica.data.models.Authority;
import bluma.africa.blumaafrica.data.models.Post;
import bluma.africa.blumaafrica.data.models.Share;
import bluma.africa.blumaafrica.data.models.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ValidateCommentResponse {

    private User user;
    private Post post;
    private Authority authority;
}
