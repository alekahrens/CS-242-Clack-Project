package main.data;

import java.util.Date;
/*
 *   Superclass for Clack Data.
 */

public abstract class ClackData {
    private String userName;
    private int type;
    private Date date;
    /*
     *   Constructor with both arguments. Date will be when the object is created.
     *   @param userName    the userName entered by the user.
     *   @param type    the type of data.
     *   @param Date    the date upon data creation.
     */
    public ClackData(String userName, int type) {
        this.userName = userName;
        this.type = type;
        this.date = new Date();
    }
    /*
     *   Constructor with only type argument. Anonymous user is assumed, so username is "Anon".
     *   @param type     the type of data.
     */
    public ClackData(int type) {
        this("Anon", type);
    }
    /*
     *   Constructor with no arguments; calls the constructor with default type of 0.
     */
    public ClackData() {
        this(0);
    }
    /*
     *  Type getter.
     *  @return type    Returns the type.
     */
    public int getType() {
        return type;
    }
    /*
     *  Username getter.
     *  @return userName    Returns the userName.
     */
    public String getUserName() {
        return userName;
    }
    /*
     *  Date getter.
     *  @return date    Returns the date.
     */
    public Date getDate() {
        return date;
    }
    /*
     *  Abstract method for retrieving data.
     */
    abstract public Object getData();
  
}
