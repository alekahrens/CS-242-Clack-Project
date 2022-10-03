package main;

import main.data.ClackData;

public class ClackServer {
    private int port;
    private boolean closeConnection;
    private ClackData dataToReceiveFromClient;
    private ClackData dataToSendToClient;
  
    public ClackServer(int port) {
        this.port = port;
        this.dataToReceiveFromClient = null;
        this.dataToSendToClient = null;
      
    }
    public ClackServer() {
        this(7000);
        final int defaultPort = 7000;
    }
  
    public void start() {
      
    }
  
    public void receiveData() {
      
    }
  
    public void sendData() {
      
    }
    
    public int getPort() {
        return port;
    }
  
    public int hashCode() {
        int result = 17;
        result = 31*result+getPort();
        return result;
    }
  
    public boolean equals(Object other) {
        ClackServer otherServer = (ClackServer)other;
        return this.getPort() == otherServer.getPort();
    }
  
    public String toString() {
        return "The port is: " + this.getPort() + ".";
    }

}
