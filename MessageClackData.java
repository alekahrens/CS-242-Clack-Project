package data;

public class MessageClackData extends ClackData {
    private String message;
  
    public MessageClackData(userName, message, type) {
        super(userName, type);
        this.message = message;
    }
  
    public MessageClackData() {
        MessageClackData("Anon", "null", 0); 
    }
  
    public getData() {
      
    }
  
    public int hashCode() {
        return ;
      
    }
  
    public boolean equals(Object other) {
       MessageClackData otherMessage = (MessageClackData)other;
       return this.message == otherMessage.message;
    }
  
    public String toString() {
       return "The username is: " + this.username + ".\n The type is: " + this.type + ".\n The message is: " + this.message + ".";
    }
  
  
}
