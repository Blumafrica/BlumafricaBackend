package bluma.africa.blumaafrica.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DeletePost {

    private String postId;
    private String userAuthority;
}
