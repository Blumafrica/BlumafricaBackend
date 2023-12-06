package bluma.africa.blumaafrica.dtos.responses;

import bluma.africa.blumaafrica.data.models.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class FetchUserPostResponse {
    private List<Post> userPost;

}
