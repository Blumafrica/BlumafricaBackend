package bluma.africa.blumaafrica.dtos.responses;

import bluma.africa.blumaafrica.data.models.Likes;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class GetAllPostLikesResponse {
    private List<Likes> foundLikes;
}
