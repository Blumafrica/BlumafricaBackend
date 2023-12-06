package bluma.africa.blumaafrica.dtos.responses;

import lombok.Data;

@Data
public class ResponseApi<T> {
    private String message;
    private T data;
}
