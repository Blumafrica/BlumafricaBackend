package bluma.africa.blumaafrica.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.ACCEPTED)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleInvalidInput(MethodArgumentNotValidException notValidException) {
        Map <String,String> errorMessage = new HashMap<>();
        notValidException.getBindingResult().getFieldErrors().forEach(error ->{
            errorMessage.put(error.getField(),error.getDefaultMessage());
        });
        return errorMessage;

    }
}
