package main;

import main.data.ClackData;

/**
 *  ClackClient represents the client user, and contains:
 *  The userName for the client.
 *  The hostName for the server.
 *  The port number.
 *  A boolean representing whether a connection is closed or open.
 *  Data to send to the server as a ClackData object.
 *  Data to receive from the server as a ClackData object.
 */

public class  ClackClient{
    private String userName;
    private String hostName;
    private int port;
    private boolean closeConnection;
    private ClackData dataToSendToServer;
    private ClackData dataToReceiveFromServer;
    /**
     *  Constructor with all arguments provided. Opens connection and sets both datas to null.
     *  @param userName     the username.
     *  @param hostName     the host name.
     *  @param port         the port number.
     */
    public ClackClient(String userName, String hostName, int port) {
        this.userName = userName;
        this.hostName = hostName;
        this.port = port;
        this.closeConnection = false;
        this.dataToSendToServer = null;
        this.dataToReceiveFromServer = null;
    }
    /**
     *  Constructor without port provided. Sets default port to a constant 7000.
     *  @param userName     the username.
     *  @param hostName     the host name.
     */
    public ClackClient(String userName, String hostName) {
        this(userName, hostName, 7000);
        final int defaultPort = 7000;
    }
    /**
     *  Constructor with only userName provided. Sets default hostName to "localhost".
     *  @param userName     the username.
     */
    public ClackClient(String userName) {
        this(userName, "localhost");
    }
    /*
     *  Constructor with no arguments. Sets default userName to "Anon".
     */
    public ClackClient() {
        this("Anon");
    }
    /*
     *  Currently no implementation.
     */
    public void start() {
      
    }
    /*
     *  Currently no implementation.
     */
    public void readClientData() {
      
    }
    /*
     *  Currently no implementation.
     */
    public void sendData() {
      
    }
    /*
     *  Currently no implementation.
     */
    public void receiveData() {
      
    }
    /*
     *  Currently no implementation.
     */
    public void printData() {
      
    }
    /**
     *  Getter for userName.
     *  @return userName    returns the username.
     */
    public String getUserName() {
        return userName; 
    }
    /**
     *  Getter for hostName.
     *  @return hostName    returns the host name.
     */
    public String getHostName() {
        return hostName; 
    }
    /**
     *  Getter for port.
     *  @return port    returns the port.
     */
    public int getPort() {
        return port; 
    }
    /**
     *  Overridden hashCode() method.
     *  @return result      returns the hashcode.
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31*result+getPort();
        result = 31*result+(getUserName()!= null ? getUserName().hashCode():0);
        return result;
    }
    /**
     *  Overridden equals() method.
     *  @param other    object to compare to.
     *  @return     returns the boolean value for the comparison.
     */
    @Override
    public boolean equals(Object other) {
        ClackClient otherClient = (ClackClient)other;
        return this.userName == otherClient.userName && this.hostName == otherClient.hostName && this.port == otherClient.port;
    }
    /**
     *  Overridden toString() method.
     *  @return     returns the object as a string.
     */
    @Override
    public String toString() {
        return "The username is: " + getUserName() + ".\n The hostname is: " + getHostName() + ".\n The port is: " + getPort() + ".";
    }
  
}
