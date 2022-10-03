package main;

import main.data.ClackData;

public class ClackClient{
    private String userName;
    private String hostName;
    private int port;
    private boolean closeConnection;
    private ClackData dataToSendToServer;
    private ClackData dataToReceiveFromServer;
  
    public ClackClient(String userName, String hostName, int port) {
        this.userName = userName;
        this.hostName = hostName;
        this.port = port;
        this.closeConnection = false;
        this.dataToSendToServer = null;
        this.dataToReceiveFromServer = null;
    }
  
    public ClackClient(String userName, String hostName) {
        this(userName, hostName, 7000);
        final int defaultPort = 7000;
    }
  
    public ClackClient(String userName) {
        this(userName, "localhost");
    }
  
    public ClackClient() {
        this("Anon");
    }
  
    public void start() {
      
    }
  
    public void readClientData() {
      
    }
  
    public void sendData() {
      
    }
  
    public void receiveData() {
      
    }
  
    public void printData() {
      
    }
  
    public String getUserName() {
        return userName; 
    }
  
    public String getHostName() {
        return hostName; 
    }
  
    public int getPort() {
        return port; 
    }
  
    public int hashCode() {
        int result = 17;
        result = 31*result+getPort();
        result = 31*result+(getUserName()!= null ? getUserName().hashCode():0);
        return result;
    }
  
    public boolean equals(Object other) {
        ClackClient otherClient = (ClackClient)other;
        return this.userName == otherClient.userName && this.hostName == otherClient.hostName && this.port == otherClient.port;
    }
  
    public String toString() {
        return "The username is: " + getUserName() + ".\n The hostname is: " + getHostName() + ".\n The port is: " + getPort() + ".";
    }
  
}
