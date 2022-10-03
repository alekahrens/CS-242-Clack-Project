package main.data;

import java.util.Date;
/*
    Superclass for Clack Data. Takes a username, type, and a date.
 */

public abstract class ClackData {
    private String userName;
    private int type;
    private Date date;
    /*
        Constructor with both arguments. Date will be when the object is created.
     */
    public ClackData(String userName, int type) {
        this.userName = userName;
        this.type = type;
        this.date = new Date();
    }
    /*
        Constructor with only type argument. Anonymous user is assumed, so username is "Anon".
     */
    public ClackData(int type) {
        this("Anon", type);
    }
    /*
        Constructor with no arguments; calls the constructor with default type of 0.
     */
    public ClackData() {
        this(0);
    }
    /*
        Type getter.
     */
    public int getType() {
        return type;
    }
    /*
        Username getter.
     */
    public String getUserName() {
        return userName;
    }
    /*
        Date getter.
     */
    public Date getDate() {
        return date;
    }
    /*
        Abstract method for retrieving data.
     */
    abstract public Object getData();
  
}
