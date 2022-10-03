package data;
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
        this.userName = "Anon";
        ClackData(userName, type);
    }
  
    public ClackData() {
        ClackData(0); 
    }
  
    public getType() {
        return type;
    }
  
    public getUserName() {
        return userName;
    }
  
    public getDate() {
        return date;
    }
  
    public getData() {
      
    }
  
}
