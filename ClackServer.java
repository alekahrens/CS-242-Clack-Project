package main;

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
        constant int defaultPort = 7000;
        ClackServer(defaultPort);
    }
  
    public start() {
      
    }
  
    public receiveData() {
      
    }
  
    public sendData() {
      
    }
    
    public getPort() {
        return port;
    }
  
    public int hashCode() {
      
    }
  
    public boolean equals(ClackServer otherClackServer) {
      
    }
  
    public String toString() {
      
    }

}
