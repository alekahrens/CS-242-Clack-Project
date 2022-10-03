package data;
public class ClackData {
    private String username;
    private int type;
    private Date date;
    
    public ClackData(String userName, int type) {
        this.username = userName;
        this.type = type;
        this.date = new Date();
    }
  
    public ClackData(int type) {
        ClackData("Anon", type);
    }
  
    public ClackData() {
        ClackData(0); 
    }
  
    public getType() {
        return type;
    }
  
    public getUserName() {
        return username;
    }
  
    public getDate() {
        return date;
    }
  
    public getData() {
      
    }
  
}
