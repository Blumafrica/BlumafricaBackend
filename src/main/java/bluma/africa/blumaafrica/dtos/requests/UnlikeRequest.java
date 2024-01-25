package bluma.africa.blumaafrica.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnlikeRequest {

    private String likeId;
    private Long userId;
    private Long postId;
    private Long shareId;
    private String authority;
}
