package main.data;

/* Class for data that will be transferred between the client and the server. */
public class MessageClackData extends ClackData {
    private String message;

    /*
        Constructor with all arguments provided, that being a username, message, and type.
     */
    public MessageClackData(String userName, String message, int type) {
        super(userName, type);
        this.message = message;
    }

    /*
        Constructor with no arguments, will default to a username "Anon" and message "null", with a default type of 0.
     */
    public MessageClackData() {
        this("Anon", "null", 0);
    }
    /*
        Overridden getData() to return instant message.
     */
    @Override
    public String getData() {
        return message;
    }
    /*
        Overridden hashCode() method.
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31*result+getType();
        result = 31*result+(getUserName()!= null ? getUserName().hashCode():0);
        return result;
      
    }
    /*
        Overridden equals() method.
     */
    @Override
    public boolean equals(Object other) {
       MessageClackData otherMessage = (MessageClackData)other;
       return this.getUserName() == otherMessage.getUserName() && this.message == otherMessage.message && this.getType() == otherMessage.getType();
    }
    /*
        Overridden toString() method.
     */
    @Override
    public String toString() {
       return "The username is: " + this.getUserName() + ".\n The type is: " + this.getType() + ".\n The message is: " + this.message + ".";
    }
  
  
}
