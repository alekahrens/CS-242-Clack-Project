
package main;

import data.ClackData;
import data.FileClackData;
import data.MessageClackData;

import java.io.*;
import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.Socket;
import java.net.UnknownHostException;
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

    private ObjectInputStream inFromServer;

    private ObjectOutputStream outToServer;
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
        this.inFromServer = null;
        this.outToServer = null;
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
     *  It will also catch an exception if it is thrown.
     */
    public void start() {
        try {
            Socket skt = new Socket(getHostName(), getPort());
            outToServer = new ObjectOutputStream(skt.getOutputStream());
            inFromServer = new ObjectInputStream(skt.getInputStream());
            this.inFromStd = new Scanner(System.in);
            while (!this.closeConnection) {
                this.readClientData();
                this.sendData();
                this.receiveData();
                this.printData();
            }
            skt.close();
            inFromStd.close();
        }
        catch(UnknownHostException uhe) {
            System.err.println("Unknown host.");
        }
        catch (NoRouteToHostException nrhe) {
            System.err.println("Server unreachable.");
        }
        catch (ConnectException ce) {
            System.err.println("Connection refused.");
        }
        catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }

    }
    /**
     *  Reads input from the user, and will do a variety of different things depending on input. Depending on what is
     *  inputted then will tell the program what the next action is. Like closing the server to sending data from the
     *  client to the server.
     */
    public void readClientData() {
                String input = this.inFromStd.next();
                if (input.equals("DONE")) {
                    System.out.println("Closing Connection");
                    this.dataToSendToServer = new MessageClackData(this.userName, input, key, ClackData.CONSTANT_LOGOUT);
                    this.inFromStd.close();
                    this.closeConnection = true;
                }
                else if (input.equals("SENDFILE")) {
                    String fileNameI = this.inFromStd.next();
                    this.dataToSendToServer = new FileClackData(this.userName, fileNameI, ClackData.CONSTANT_SENDFILE);
                    try {
                        ((FileClackData)dataToSendToServer).readFileContents(key);
                    }
                    catch (IOException ioe) {
                        System.err.println("There was an error reading the file.");
                        this.dataToSendToServer = null;

                        System.err.println("There was an error reading the file.");

                    }

                }
                else if (input.equals("LISTUSERS")) {
                    /** Do nothing for now */

                }

                else {
                    String message = input + this.inFromStd.nextLine();
                    this.dataToSendToServer = new MessageClackData(this.userName, message, key, ClackData.CONSTANT_SENDMESSAGE);
                }
            }

    /**
     *  sendData is a function that will write to an object to the server. It will also flush out the stream to make sure
     *  that there is no unwanted data. In this function it will also catch an exception if one is thrown.
     */
    public void sendData() {

        try {
            outToServer.writeObject(this.dataToSendToServer);
            outToServer.flush();
        }
        catch (InvalidClassException ice) {
            System.err.println("Invalid class");
        }
        catch (NotSerializableException nse) {
            System.err.println("Class is not serialized.");
        }
        catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }

    }
    /**
     *  receiveData is a function that will get data from the server by reading the object. It will also catch an
     *  exception if one is thrown.
     */
    public void receiveData() {
        try {
            this.dataToReceiveFromServer = (ClackData) inFromServer.readObject();
        }
        catch (ClassNotFoundException cnfe) {
            System.err.println("Class not found.");
        }
        catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }

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

    public static void main(String[] args) {
        if (args.length > 0) {
            String str = String.join(",",args);
           String[] arguments = str.split("[@:]");
           if (arguments.length == 3) {
               ClackClient thrClient = new ClackClient(arguments[0], arguments[1], Integer.parseInt(arguments[2]));
               thrClient.start();
           }
           else if (arguments.length == 2) {
               ClackClient twClient = new ClackClient(arguments[0], arguments[1]);
               twClient.start();
           }
           else if (arguments.length == 1) {
               ClackClient onClient = new ClackClient(arguments[0]);
               onClient.start();
           }
        }
        else {
            ClackClient aClient = new ClackClient();
            aClient.start();
        }
    }
  
}

