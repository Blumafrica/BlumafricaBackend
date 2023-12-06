package bluma.africa.blumaafrica.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LikeRequest {

    private String userId;
    private String postId;
}
