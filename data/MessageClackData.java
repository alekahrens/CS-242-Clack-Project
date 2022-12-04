package data;

/** Class for data that will be transferred between the client and the server. */
public class MessageClackData extends ClackData {
    private String message;

    /**
     *   Constructor with all arguments provided.
     *   @param userName    the username.
     *   @param message     the message.
     *   @param type        the type.
     */
    public MessageClackData(String userName, String message, int type) {
        super(userName, type);
        this.message = message;
    }

    /**
     *  Constructor with no arguments, will default to a username "Anon" and message "null", with a default type of 0.
     */
    public MessageClackData() {
        this("Anon", "", CONSTANT_SENDMESSAGE);
    }

    /**
     * This method take the incoming message data and encrypt it. Then sent over to the client to decrypt the data.
     * @param userName
     * @param message
     * @param key
     * @param type
     */
    public MessageClackData(String userName, String message, String key, int type) {
        super(userName, type);
        this.message = encrypt(message, key);
    }

    /**
     *  Overridden getData() to return instant message.
     *  @return message     Returns the message
     */
    @Override
    public String getData() {
        return this.message;
    }
    /**
     *  Overridden getData() to return a decrypted message.
     *  @return decryptedMessage    Returns the decrypted message.
     */
    @Override
    public String getData(String key) {
        return decrypt(this.message, key);

    }
    /**
     *  Overridden hashCode() method.
     *  @return result  returns the hashcode.
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31*result+this.getType();
        result = 31*result+(this.getUserName()!= null ? this.getUserName().hashCode():0);
        return result;
      
    }
    /**
     *  Overridden equals() method.
     *  @param other    object to be typecasted and compared.
     *  @return     returns the boolean value for the comparison.
     */
    @Override
    public boolean equals(Object other) {
       MessageClackData otherMessage = (MessageClackData)other;
       return this.getUserName() == otherMessage.getUserName() && this.message == otherMessage.message && this.getType() == otherMessage.getType();
    }
    /**
     *  Overridden toString() method.
     *  @return     returns the object as a string.
     */
    @Override
    public String toString() {
       return "This instance of MessageClackData has the following properties:\n" + "Username: " + this.getUserName() + "\n" + "Type: " + this.getType() + "\n" + "Date: " + this.getDate().toString() + "\n" + "Message: " + this.message + "\n";
    }
  
  
}
