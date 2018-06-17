
package chatapplication;

public class UserRemoveException extends ChatException {
     public UserRemoveException(){
        super("User dose not exists");
    }       
    
    public UserRemoveException(String message){
        super(message);
    }       
    
}
