package data;

import java.io.Serializable;
import java.util.Date;
/**
 *   Superclass for Clack Data.
 */

public abstract class ClackData implements Serializable {
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
    public abstract String getData();
    
    abstract public String getData(String key);
    
    /**
     * This method will encrypt a string using a given key. The key will be generated in a cyclical manner
     * until it matched the given string in length. Then, the string will be encrypted and returned.
     */
    
    protected String encrypt(String inputStringToEncrypt, String key) {
        if (inputStringToEncrypt == null) {
            return null;
        }

        final int keyLen = key.length();
        int keyIndex = 0;
        StringBuilder stringEncrypted = new StringBuilder();

        for (int i = 0; i < inputStringToEncrypt.length(); i++) {
            char inputCharToEncrypt = inputStringToEncrypt.charAt(i);
            char inputCharEncrypted;

            if (Character.isLowerCase(inputCharToEncrypt)) {
                char keyChar = Character.toLowerCase(key.charAt(keyIndex));
                inputCharEncrypted = (char) (((inputCharToEncrypt - 'a') + (keyChar - 'a')) % 26 + 'a');
                keyIndex = (keyIndex + 1) % keyLen;

            } else if (Character.isUpperCase(inputCharToEncrypt)) {
                char keyChar = Character.toUpperCase(key.charAt(keyIndex));
                inputCharEncrypted = (char) (((inputCharToEncrypt - 'A') + (keyChar - 'A')) % 26 + 'A');
                keyIndex = (keyIndex + 1) % keyLen;

            } else {
                inputCharEncrypted = inputCharToEncrypt;
            }

            stringEncrypted.append(inputCharEncrypted);
        }

        return stringEncrypted.toString();
    }

    /**
     *
     * @param inputStringToDecrypt
     * @param key
     * @return
     * This will decrypt the encoded message
     */
    protected String decrypt(String inputStringToDecrypt, String key) {
        if (inputStringToDecrypt == null) {
            return null;
        }
        final int keyLen = key.length();
        int keyIndex = 0;
        StringBuilder stringDecrypted = new StringBuilder();

        for (int i = 0; i < inputStringToDecrypt.length(); i++) {
            char inputCharToDecrypt = inputStringToDecrypt.charAt(i);
            char inputCharDecrypted;

            if (Character.isLowerCase(inputCharToDecrypt)) {
                char keyChar = Character.toLowerCase(key.charAt(keyIndex));
                inputCharDecrypted = (char) ((inputCharToDecrypt - keyChar + 26) % 26 + 'a');
                keyIndex = (keyIndex + 1) % keyLen;

            } else if (Character.isUpperCase(inputCharToDecrypt)) {
                char keyChar = Character.toUpperCase(key.charAt(keyIndex));
                inputCharDecrypted = (char) ((inputCharToDecrypt - keyChar + 26) % 26 + 'A');
                keyIndex = (keyIndex + 1) % keyLen;

            } else {
                inputCharDecrypted = inputCharToDecrypt;
            }

            stringDecrypted.append(inputCharDecrypted);
        }

        return stringDecrypted.toString();
    }
}
