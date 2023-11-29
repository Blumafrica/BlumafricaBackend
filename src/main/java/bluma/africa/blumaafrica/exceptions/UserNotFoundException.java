package bluma.africa.blumaafrica.exceptions;

public class UserNotFoundException extends UserException{
    public UserNotFoundException (Throwable throwable){
        super(throwable);
    }
    public UserNotFoundException(String message){
        super(message);
    }
}
