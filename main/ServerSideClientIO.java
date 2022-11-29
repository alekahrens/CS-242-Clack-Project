package main;

import data.ClackData;
import data.MessageClackData;

import java.io.*;
import java.net.Socket;

/**
 * The ServerSideClientIO class is a class that implements the Runnable class for multi-threading. It contains:
 * The closeConnection boolean to be used to determine if the connection should be closed.
 * The dataToReceiveFromClient ClackData object contains the data that will be received from the client.
 * The dataToSendToClient ClackData object contains the data to be sent to the client.
 * The inFromClient ObjectInputStream gets the data from the client.
 * The outToClient ObjectOutputStream is used to send the data to the client.
 * The server ClackServer object represents the server.
 * The clientSocket Socket is used for the client.
 */

public class ServerSideClientIO implements Runnable {
    private boolean closeConnection;
    private ClackData dataToReceiveFromClient;
    private ClackData dataToSendToClient;
    private ObjectInputStream inFromClient;
    private ObjectOutputStream outToClient;
    private ClackServer server;
    private Socket clientSocket;

    /**
     * Constructor for ServerSideClientIO using all arguments. Sets the instance variables of server and clientSocket
     * to the parameters passed to the method call, sets closeConnection to false, and every other instance variable
     * to null.
     * @param server    The ClackServer object for the server.
     * @param clientSocket      The Socket object for the client.
     */

    public ServerSideClientIO (ClackServer server, Socket clientSocket) {
            this.server = server;
            this.clientSocket = clientSocket;
            this.closeConnection = false;
            this.dataToReceiveFromClient = null;
            this.dataToSendToClient = null;
            this.inFromClient = null;
            this.outToClient = null;
    }

    /**
     * Overridden runnable run method that is used to run the ServerSideClientIO. The inFromClient and outToClient
     * streams will be initialized, and while the connection is open, run() will call the receiveData() method
     * to receive the client data, and broadcast said data.
     */
    @Override
    public void run() {
        try {
            this.inFromClient = new ObjectInputStream(this.clientSocket.getInputStream());
            this.outToClient = new ObjectOutputStream(this.clientSocket.getOutputStream());
            while(!this.closeConnection) {
                receiveData();
                this.server.broadcast(this.dataToSendToClient);
                if (this.closeConnection) {
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * SendData() is used to send data to the client by writing dataToSendToClient to the output stream.
     */
    public void sendData() {
        try {
            this.outToClient.writeObject(this.dataToSendToClient);
            this.outToClient.flush();
        }
        catch (InvalidClassException ice) {
            System.err.println("Invalid class");
        }
        catch (NotSerializableException nse) {
            System.err.println("Class is not serialized.");
        }
        catch (IOException ioe) {
            System.err.println("There was an error sending the data.");
        }
    }

    /**
     * receiveData() is used to receive data from the client, first by reading it from the input stream.
     * Then, it will check what type of action has been requested by the client. If the client wishes to logout,
     * the connection will be closed and removed from the server list. If the client wants to LISTUSERS, the
     * GetUsers() method will be called and the dataToSendToClient will be sent as a new MessageClackData object
     * containing the list.
     */
    public void receiveData() {
        try {
            this.dataToReceiveFromClient = (ClackData) this.inFromClient.readObject();
            /** For debugging purposes */
            System.out.println("Received data from the client:");
            System.out.println("From: " + this.dataToReceiveFromClient.getUserName());
            System.out.println("Date: " + this.dataToReceiveFromClient.getDate());
            System.out.println("Data: " + this.dataToReceiveFromClient.getData());
            System.out.println();

            if (this.dataToReceiveFromClient.getType() == ClackData.CONSTANT_LOGOUT) {
                this.closeConnection = true;
                this.server.remove(this);
            }
            if (this.dataToReceiveFromClient.getType() == ClackData.CONSTANT_LISTUSERS) {
                String userName = this.dataToReceiveFromClient.getUserName();
                String userList = this.server.GetUsers();
                this.dataToSendToClient = new MessageClackData(userName, userList, ClackData.CONSTANT_LISTUSERS);
            }

        }
        catch (ClassNotFoundException cnfe) {
            System.err.println("Could not find class.");

        }
        catch (InvalidClassException ice) {
            System.err.println("Class is not serialized.");
        }
        catch (StreamCorruptedException sce) {
            System.err.println("Stream has been corrupted.");
        }
        catch (OptionalDataException ode) {
            System.err.println("Primitive data type found in stream.");
        }
        catch (IOException ioe) {
            System.err.println("There was an error reading the data.");
        }
    }

    /**
     * Getter for UserName
     * @return  this.dataToReceiveFromClient.getUserName()  Returns the username from the client's data.
     */
    public String getUserName() {
        return this.dataToReceiveFromClient.getUserName();
    }

    /**
     * Setter for dataToSendToClient
     * @param dataToSendToClient    The data that is to be sent to the client.
     */
    public void setDataToSendToClient(ClackData dataToSendToClient) {
        this.dataToSendToClient = dataToSendToClient;
    }
}
