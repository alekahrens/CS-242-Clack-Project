package main;

public class ClackClient{
    private String userName;
    private String hostName;
    private int port;
    private boolean closeConnection;
    private ClackData dataToSendToServer;
    private ClackData dataToReceiveFromServer;
  
    public ClackClient(string userName, string hostName, int port) {
        this.userName = userName;
        this.hostName = hostName;
        this.port = port;
        this.closeConnection = 0;
        this.dataToSendToServer = null;
        this.dataToReceiveFromServer = null;
    }
  
    public ClackClient(string userName, string hostName) {
        constant int defaultport = 7000;
        ClackClient(userName, hostName, defaultport);
    }
  
    public ClackClient(string userName) {
        this.hostName = "localhost";
        ClackClient(userName, hostName);
    }
  
    public ClackClient() {
        this.userName = "Anon";
        ClackClient(userName);
    }
  
    public start() {
      
    }
  
    public readClientData() {
      
    }
  
    public sendData() {
      
    }
  
    public receiveData() {
      
    }
  
    public printData() {
      
    }
  
    public getUserName() {
        return userName; 
    }
  
    public getHostName() {
        return hostName; 
    }
  
    public getPort() {
        return port; 
    }
  
    public int hashCode() {
      
    }
  
    public boolean equals(ClackClient otherClient) {
      
    }
  
    public String toString() {

    }
  
}
