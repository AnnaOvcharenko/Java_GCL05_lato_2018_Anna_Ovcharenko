/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapplication;

/**
 *
 * @author Anna
 */
public class UserLoginException extends ChatException {
     public UserLoginException(){
        super("User already exists");
    }       
    
    public UserLoginException(String message){
        super(message);
    }       
}
