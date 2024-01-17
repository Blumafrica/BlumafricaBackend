package bluma.africa.blumaafrica.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnlikeRequest {

    private String likeId;
    private String userId;
    private String postId;
    private String shareId;
    private String authority;
}
