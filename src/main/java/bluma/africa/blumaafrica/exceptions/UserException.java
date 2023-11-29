package bluma.africa.blumaafrica.exceptions;

public class UserException extends Throwable{
    public UserException(Throwable throwable){
        super(throwable);
    }
    public UserException(String message){
        super(message);
    }
}
