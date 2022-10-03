package main.data;

public class MessageClackData extends ClackData {
    private String message;
  
    public MessageClackData(String userName, String message, int type) {
        super(userName, type);
        this.message = message;
    }
  
    public MessageClackData() {
        this("Anon", "null", 0);
    }
  
    public void getData() {
      
    }
  
    public int hashCode() {
        int result = 17;
        result = 31*result+getType();
        result = 31*result+(getUserName()!= null ? getUserName().hashCode():0);
        return result;
      
    }
  
    public boolean equals(Object other) {
       MessageClackData otherMessage = (MessageClackData)other;
       return this.getUserName() == otherMessage.getUserName() && this.message == otherMessage.message && this.getType() == otherMessage.getType();
    }
  
    public String toString() {
       return "The username is: " + this.getUserName() + ".\n The type is: " + this.getType() + ".\n The message is: " + this.message + ".";
    }
  
  
}
