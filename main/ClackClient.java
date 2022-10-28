
package main.main;

import main.data.ClackData;
import main.data.FileClackData;
import main.data.MessageClackData;

import java.util.Scanner;

/**
 *  ClackClient represents the client user, and contains:
 *  The userName for the client.
 *  The hostName for the server.
 *  The port number.
 *  A boolean representing whether a connection is closed or open.
 *  Data to send to the server as a ClackData object.
 *  Data to receive from the server as a ClackData object.
 */

public class ClackClient{
    private static final int DEFAULT_PORT = 7000;
    private static final String key = "ADAIN";
    
    private String userName;
    private String hostName;
    private int port;
    private boolean closeConnection;
    private ClackData dataToSendToServer;
    private ClackData dataToReceiveFromServer;
    private java.util.Scanner inFromStd;
    /**
     *  Constructor with all arguments provided. Opens connection and sets both datas to null.
     *  @param userName     the username.
     *  @param hostName     the host name.
     *  @param port         the port number.
     */
    public ClackClient(String userName, String hostName, int port) throws IllegalArgumentException {
        if (userName == null) {
            throw new IllegalArgumentException("User name cannot be null.");   
        }
        if (hostName == null) {
            throw new IllegalArgumentException("Host name cannot be null.");   
        }
        if (port < 1024) {
            throw new IllegalArgumentException("Port number must be 1024 or greater.");   
        }
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
    public ClackClient(String userName, String hostName) throws IllegalArgumentException {
        if (userName == null) {
            throw new IllegalArgumentException("User name cannot be null.");
        }
        if (hostName == null) {
            throw new IllegalArgumentException("Host name cannot be null.");
        }
        new ClackClient(userName,hostName,DEFAULT_PORT);
    }
    /**
     *  Constructor with only userName provided. Sets default hostName to "localhost".
     *  @param userName     the username.
     */
    public ClackClient(String userName) throws IllegalArgumentException  {
        if (userName == null) {
            throw new IllegalArgumentException("User name cannot be null.");   
        }
        new ClackClient(userName, "localhost");

    }
    /**
     *  Constructor with no arguments. Sets default userName to "Anon".
     */
    public ClackClient()  {
        this("Anon");
    }
    /**
     *  Initializing scanner and reading/printing data.
     */
    public void start() {
        this.inFromStd = new Scanner(System.in);
        this.readClientData();
        this.dataToReceiveFromServer = this.dataToSendToServer;
        this.printData();
    }
    /**
     *  Reads input from the user, and will do a variety of different things depending on input
     */
    public void readClientData() {
        try {
            while (this.inFromStd.hasNext()) {
                String input = this.inFromStd.next();
                
                if (input == "DONE") {
                    this.closeConnection = true;
                }
                if (input == "SENDFILE") {
                    String fileNameI = this.inFromStd.next();
                    this.dataToSendToServer = new FileClackData(this.userName, fileNameI, 3);
                    try {
                        ((FileClackData) dataToSendToServer).readFileContents();
                    }
                    catch (Exception e) {
                        this.dataToSendToServer = null;
                        System.err.println("There was an error reading the file.");   
                    }
                }
                if (input == "LISTUSERS") {
                    /** Do nothing for now */   
                }
                else {
                    this.dataToSendToServer = new MessageClackData(this.userName, "", 2);
                }
            }
            this.inFromStd.close();
        }
        catch (Exception e) {
            System.err.println("There was an error.");
        }
    }
    /**
     *  Currently no implementation.
     */
    public void sendData() {
      
    }
    /**
     *  Currently no implementation.
     */
    public void receiveData() {
      
    }
    /**
     *  Prints out the data from the dataToReceiveFromServer ClackData object
     */
    public void printData() {
        System.out.println(this.dataToReceiveFromServer.getData());
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
        result = 31*result+this.getPort();
        result = 31*result+(this.getUserName()!= null ? this.getUserName().hashCode():0);
        return result;
    }
    /*
     *  Overridden equals() method.
     *  @param other    object to compare to.
     *  @return     returns the boolean value for the comparison.
     */
    @Override
    public boolean equals(Object other) {
        ClackClient otherClient = (ClackClient)other;
        return this.userName == otherClient.userName && this.hostName == otherClient.hostName && this.port == otherClient.port;
    }
    /*
     *  Overridden toString() method.
     *  @return     returns the object as a string.
     */
    @Override
    public String toString() {
        return "This instance of ClackClient has the following properties:\n" 
               + "Username: " + this.userName + "\n"
               + "Host name: " + this.hostName + "\n"
               + "Port number: " + this.port + "\n"
               + "Connection status: " + (this.closeConnection ? "Closed" : "Open") + "\n"
               + "Data to send to the server: " + this.dataToSendToServer + "\n"
               + "Data to receive from the server: " + this.dataToReceiveFromServer + "\n";
                
    }
  
}

