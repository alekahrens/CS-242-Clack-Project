package main.data;

import java.util.Date;
/**
 *   Superclass for Clack Data.
 */

public abstract class ClackData {
    private String userName;
    private int type;
    private Date date;
    public static final int CONSTANT_LISTUSERS = 0;
    public static final int CONSTANT_LOGOUT = 1;
    public static final int CONSTANT_SENDMESSAGE = 2;
    public static final int CONSTANT_SENDFILE = 3;
    /**
     *   Constructor with both arguments. Date will be when the object is created.
     *   @param userName    the userName entered by the user.
     *   @param type    the type of data.
     */
    public ClackData(String userName, int type) {
        this.userName = userName;
        this.type = type;
        this.date = new Date();
    }
    /**
     *   Constructor with only type argument. Anonymous user is assumed, so username is "Anon".
     *   @param type     the type of data.
     */
    public ClackData(int type) {
        this("Anon", type);
    }
    /**
     *   Constructor with no arguments; calls the constructor with default type of 0.
     */
    public ClackData() {
        this(0);
    }
    /**
     *  Type getter.
     *  @return type    Returns the type.
     */
    public int getType() {
        return type;
    }
    /**
     *  Username getter.
     *  @return userName    Returns the userName.
     */
    public String getUserName() {
        return userName;
    }
    /**
     *  Date getter.
     *  @return date    Returns the date.
     */
    public Date getDate() {
        return date;
    }
    /**
     *  Abstract method for retrieving data.
     */
    abstract public Object getData();
    
    abstract public String getData(String key);
    
    /**
     * This method will encrypt a string using a given key. The key will be generated in a cyclical manner
     * until it matched the given string in length. Then, the string will be encrypted and returned.
     */
    
    protected String encrypt(String inputStringToEncrypt, String key) {
        int length = inputStringToEncrypt.length();

        for (int i = 0; ;i++) {
            if (length == i) {
                i = 0;
            }
            if (key.length() == inputStringToEncrypt.length()) {
                break;   
            }
            key+=(key.charAt(i));
        }
        
        String encryptedString = "";
        
        for (int j = 0; j < inputStringToEncrypt.length(); j++) {
            int y = (inputStringToEncrypt.charAt(j) + key.charAt(j)) % 26;
            
            y += 'A';
           
            encryptedString+=(char)(y);
        }
        return encryptedString;
    }
    
    protected String decrypt(String inputStringToDecrypt, String key) {
        String decryptedText="";
 
         for (int k = 0 ; k < inputStringToDecrypt.length() && k < key.length(); k++) {
       
            int z = (inputStringToDecrypt.charAt(k) - key.charAt(k) + 26) % 26;
            z += 'A';
            decryptedText+=(char)(z);
         }
         return decryptedText;
    }
  
}
