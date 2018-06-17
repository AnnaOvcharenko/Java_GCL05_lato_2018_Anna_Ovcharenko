
package chatapplication;

import java.util.HashMap;
import java.util.Map;

public class ChatEngine {
    private Map<Long,User> users;
    private Map<String, Long> usernames;
    private long currentId;
    

    public ChatEngine() {
        this.users = new HashMap<>();
        currentId=0;  
    }
    
    public void loginUser(User user) throws UserLoginException
    {
        if(this.users.containsValue(user))
            throw new UserLoginException();
        this.currentId+=1;
        user.setId(currentId);
        this.users.put(currentId, user);
        this.usernames.put(user.getName(), currentId);
        
 
    }
    
    public void logoutUser(long userId) throws UserRemoveException {
        if(!this.users.containsKey(userId))
            throw new UserRemoveException();
        this.users.remove(userId);
        
    }
    public int getNumberOfUsers(){
        return this.users.size();
        
    }
    public void addUserStar(String userName){
        Long id;
        if(this.usernames.containsKey(userName)){
            id=this.usernames.get(userName);
            this.users.get(id).addStar();  
        }
        
            
       // this.users.get(this.usernames.get(userName)).setNumberOfStars(this.users.get(this.usernames.get(userName)).getNumberOfStars()+1);
    }
    public  void removeUserStar(String userName){
        Long id;
        if(this.usernames.containsKey(userName)){
            id=this.usernames.get(userName);
            this.users.get(id).removeStar();  
        }
        
    }
    
    public boolean hasUser(long userId){
        return (this.users.containsKey(userId));  
    }
    
    private boolean hasUser(String userName){
        return(this.usernames.containsKey(userName));
    }
    private void showAllUsers(){
        for(Map.Entry<Long, User> user : this.users.entrySet()){
            Long key=user.getKey();
            String value = user.getValue().getName();
            Long pts=user.getValue().getNumberOfStars();
            Long message=user.getValue().getNumberOfSentMessages();
            
            System.out.println(key+" "+value+" stars" + pts + "message "+message);
            
        }
        
    }
    
    private boolean broadcastMessage(long userId, String message){
        if(!this.users.containsKey(userId))
            return false;
        this.users.get(userId).incNumberOfSentMessages();
        
        System.out.println("User "+userId+"sent message"+message+"to:");
        this.showAllUsers();
        return true;          
    }
    
    
    

    
    
    
    
}
