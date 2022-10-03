package main.data;

import java.util.Date;

public class ClackData {
    private String userName;
    private int type;
    private Date date;
    
    public ClackData(String userName, int type) {
        this.userName = userName;
        this.type = type;
        this.date = new Date();
    }
  
    public ClackData(int type) {
        this("Anon", type);
    }
  
    public ClackData() {
        this(0);
    }
  
    public int getType() {
        return type;
    }
  
    public String getUserName() {
        return userName;
    }
  
    public Date getDate() {
        return date;
    }
  
    public void getData() {
      
    }
  
}
