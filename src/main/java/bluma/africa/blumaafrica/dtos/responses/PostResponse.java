package bluma.africa.blumaafrica.dtos.responses;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;



@Setter
@Getter
@ToString
@RequiredArgsConstructor
public class  PostResponse {
    private String content;
    private String description;
    private String fileUrl;
    private Long posterId;
    private LocalDateTime timePosted;
    private Long postId;
    private String message;

}
