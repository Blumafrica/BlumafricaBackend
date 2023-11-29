package bluma.africa.blumaafrica.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;



@Setter
@Getter
public class PostResponse {

    private LocalDateTime timePosted;
    private Long postId;
    private Long postOwnerId;
}
