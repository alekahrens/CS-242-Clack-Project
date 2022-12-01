
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
        this(userName,hostName,DEFAULT_PORT);

    }
    /**
     *  Constructor with only userName provided. Sets default hostName to "localhost".
     *  @param userName     the username.
     */
    public ClackClient(String userName) throws IllegalArgumentException  {
        this(userName, "localhost");

    }
    /**
     *  Constructor with no arguments. Sets default userName to "Anon".
     */
    public ClackClient()  {
        this("Anon");
    }
    /**
     *  The start method will initialize the scanner, the socket, and the input/output streams for the client.
     *  A new ClientSideServerListener will be created, as will a new thread that is started before the while loop.
     *  The while loop will call the methods readClientData() and sendData() until the connection is closed.
     */
    public void start() {
        try {
            this.inFromStd = new Scanner(System.in);
            Socket skt = new Socket(getHostName(), getPort());
            this.outToServer = new ObjectOutputStream(skt.getOutputStream());
            this.inFromServer = new ObjectInputStream(skt.getInputStream());

            (new Thread(new ClientSideServerListener(this))).start();

            while (!this.closeConnection) {
                readClientData();
                sendData();
                if (this.closeConnection) {
                    break;
                }
            }
            this.inFromServer.close();
            this.outToServer.close();
            skt.close();
            this.inFromStd.close();
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
     *  readClientData() takes input from the client user, and has a multitude of functions. If the user enters
     *  "DONE", the connection will be closed and a MessageClackData will be created and assigned to dataToSendToServer
     *  to reflect this. If the user enters "SENDFILE" along with a filename, the file data will be encrypted and
     *  sent to the server. If the user enters "LISTUSERS", a MessageClackData will be sent to the server, giving the
     *  server the instructions to collect the list of users and send them back to the client. Otherwise, a message
     *  will be sent to the server.
     */
    public void readClientData() {
                String input = this.inFromStd.next();
                if (input.equals("DONE")) {
                    System.out.println("Closing connection.");
                    this.closeConnection = true;
                    this.dataToSendToServer = new MessageClackData(this.userName, input, key, ClackData.CONSTANT_LOGOUT);

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

                    }
                }
                else if (input.equals("LISTUSERS")) {
                    String message = input + this.inFromStd.nextLine();
                    this.dataToSendToServer = new MessageClackData(this.userName, message, key, ClackData.CONSTANT_LISTUSERS);
                }

                else {
                    String message = input + this.inFromStd.nextLine();
                    this.dataToSendToServer = new MessageClackData(this.userName, message, key, ClackData.CONSTANT_SENDMESSAGE);
                }
            }

    /**
     *  sendData() uses the output stream to send data to the server.
     */
    public void sendData() {
        try {
            this.outToServer.writeObject(this.dataToSendToServer);
            this.outToServer.flush();
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
     *  receiveData() uses the input stream to receive data from the server.
     */
    public void receiveData() {
        try {
            this.dataToReceiveFromServer = (ClackData) this.inFromServer.readObject();
        }
        catch (ClassNotFoundException cnfe) {
            System.err.println("Class not found.");
        }
        catch (StreamCorruptedException sce) {
            System.err.println("StreamCorruptedException thrown in receiveData(): " + sce.getMessage());

        } catch (OptionalDataException ode) {
            System.err.println("OptionalDataException thrown in receiveData(): " + ode.getMessage());

        }
        catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }

    }
    /**
     *  Prints out the data from the dataToReceiveFromServer ClackData object
     */
    public void printData() {
        if (this.dataToReceiveFromServer != null) {
            System.out.println("From: " + this.dataToReceiveFromServer.getUserName());
            System.out.println("Date: " + this.dataToReceiveFromServer.getDate());
            System.out.println("Data: " + this.dataToReceiveFromServer.getData(key));
            System.out.println();
        }
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
     *  Getter for closeconnection boolean
     * @return closeconnection  returns the connection status
     */
    public boolean getCloseConnection() {
        return closeConnection;
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
        return "This instance of ClackClient has the following properties:\n"
                + "Username: " + this.userName + "\n"
                + "Host name: " + this.hostName + "\n"
                + "Port number: " + this.port + "\n"
                + "Connection status: " + (this.closeConnection ? "Closed" : "Open") + "\n"
                + "Data to send to the server: " + this.dataToSendToServer + "\n"
                + "Data to receive from the server: " + this.dataToReceiveFromServer + "\n";

    }

    public static void main(String[] args) {
        ClackClient clackClient;

        if (args.length == 0) {
            clackClient = new ClackClient();
        } else {
            Scanner scanner = new Scanner(args[0]);
            scanner.useDelimiter("[@:]");
            String userName = scanner.next();

            if (!scanner.hasNext()) {
                clackClient = new ClackClient(userName);
            }
            else {
                String hostName = scanner.next();

                if (!scanner.hasNext()) {
                    clackClient = new ClackClient(userName, hostName);
                }
                else {
                    int port = scanner.nextInt();
                    clackClient = new ClackClient(userName, hostName, port);

                }
            }
            scanner.close();
        }
        clackClient.start();
    }
  
}

