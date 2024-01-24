package bluma.africa.blumaafrica.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {AuthorityException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN )
    public ResponseEntity<?> handleAuthorityException(){
        return new ResponseEntity<>("user is not authorize", HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = PostNotFound.class)
    @ResponseStatus(NOT_FOUND)
    public ResponseEntity<?> handlePostNotFound(){
        return new ResponseEntity<>("post not found", NOT_FOUND);
    }

    @ExceptionHandler(value = UserNotFound.class)
    @ResponseStatus(NOT_FOUND)
    public ResponseEntity<?> handleUserNotFound(){
        return new ResponseEntity<>("user not found", NOT_FOUND);
    }


}
