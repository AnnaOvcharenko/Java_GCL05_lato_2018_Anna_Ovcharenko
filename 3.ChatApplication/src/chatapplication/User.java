
package chatapplication;

public class User {
    long id;
    String name;
    long numberOfStars;
    long  numberOfSentMessages;
    
    public long getId(){
        return this.id;
    }
    public String getName(){
        return name;
    }
    public long getNumberOfStars(){
        return numberOfStars;
    }
    public long getNumberOfSentMessages(){
        return numberOfSentMessages;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumberOfStars(long numberOfStars) {
        this.numberOfStars = numberOfStars;
    }

    public void setNumberOfSentMessages(long numberOfSentMessages) {
        this.numberOfSentMessages = numberOfSentMessages;
    }
    public void addStar(){
        this.numberOfStars+=1;
    }
    public void removeStar(){
        this.numberOfStars-=1;
    }
    public void incNumberOfSentMessages(){
        this.numberOfSentMessages+=1;
    }
       
            
}
