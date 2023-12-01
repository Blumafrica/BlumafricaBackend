package bluma.africa.blumaafrica.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponseApi<T> {
    private String commentMessage;
    private T data;

    public CommentResponseApi(T message, T o) {
    }
}
