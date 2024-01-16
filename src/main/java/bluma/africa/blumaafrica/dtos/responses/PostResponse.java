package bluma.africa.blumaafrica.dtos.responses;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;



@Setter
@Getter
@RequiredArgsConstructor
public class PostResponse {

    private LocalDateTime timePosted;
    private Long postId;
    private Long postOwnerId;
    private String message;

}
