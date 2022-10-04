package main;

import main.data.ClackData;

/*
 *  ClackServer represents the server. It contains:
 *  The port number.
 *  A boolean representing whether the connection is open or closed.
 *  Data to receive from the client in the form of a ClackData object.
 *  Data to send to the client in the form of a ClackData object.
 */

public class ClackServer {
    private int port;
    private boolean closeConnection;
    private ClackData dataToReceiveFromClient;
    private ClackData dataToSendToClient;
    /*
     *  Constructor with port declared. Sets data to null.
     *  @param port     the port number defined by the user.
     */
    public ClackServer(int port) {
        this.port = port;
        this.dataToReceiveFromClient = null;
        this.dataToSendToClient = null;
    }
    /*
     *  Constructor with no arguments. Sets default port to 7000.
     */
    public ClackServer() {
        this(7000);
        final int defaultPort = 7000;
    }
    /*
     *  Currently no implementation.
     */
    public void start() {
      
    }
    /*
     *  Currently no implementation.
     */
    public void receiveData() {
      
    }
    /*
     *  Currently no implementation.
     */
    public void sendData() {
      
    }
    /*
     *  Getter for port.
     *  @return port    returns the port.
     */
    public int getPort() {
        return port;
    }
    /*
     *  Overridden hashCode() method.
     *  @return result  returns the hashcode.
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31*result+getPort();
        return result;
    }
    /*
     *  Overridden equals() method.
     *  @param other    object to compare to.
     *  @return     returns the boolean value for the comparison.
     */
    @Override
    public boolean equals(Object other) {
        ClackServer otherServer = (ClackServer)other;
        return this.getPort() == otherServer.getPort();
    }
    /*
     *  Overridden toString() method.
     *  @return     returns the object as a string.
     */
    @Override
    public String toString() {
        return "The port is: " + this.getPort() + ".";
    }

}
