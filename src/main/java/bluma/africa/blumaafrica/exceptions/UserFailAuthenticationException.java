package bluma.africa.blumaafrica.exceptions;

public class UserFailAuthenticationException extends RuntimeException{
    public UserFailAuthenticationException(String message){
        super(message);
    }
}
