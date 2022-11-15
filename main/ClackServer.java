
package main;
import data.ClackData;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.channels.IllegalBlockingModeException;

/**
 *  ClackServer represents the server. It contains:
 *  The port number.
 *  A boolean representing whether the connection is open or closed.
 *  Data to receive from the client in the form of a ClackData object.
 *  Data to send to the client in the form of a ClackData object.
 */

public class ClackServer {
    private static final int DEFAULT_PORT = 7000;
    
    private int port;
    private boolean closeConnection;
    private ClackData dataToReceiveFromClient;
    private ClackData dataToSendToClient;

    private ObjectInputStream inFromClient;

    private ObjectOutputStream outToClient;
    /**
     *  Constructor with port declared. Sets data to null.
     *  @param port     the port number defined by the user.
     */
    public ClackServer(int port) {
        if (port < 1024) {
            throw new IllegalArgumentException("Port number must be 1024 or greater.");
        }
        else {
            this.port = port;
            this.closeConnection = false;
            this.dataToReceiveFromClient = null;
            this.dataToSendToClient = null;
            this.inFromClient = null;
            this.outToClient = null;
        }

    }
    /**
     *  Constructor with no arguments. Sets default port to 7000.
     */
    public ClackServer() {
        this(DEFAULT_PORT);
       
    }
    /**
     *  Currently no implementation.
     */
    public void start() {
      try {
          ServerSocket sskt = new ServerSocket(getPort());
          Socket clientSkt = sskt.accept();
          inFromClient = (ObjectInputStream) clientSkt.getInputStream();
          outToClient = (ObjectOutputStream) clientSkt.getOutputStream();
          receiveData();
          this.dataToSendToClient = this.dataToReceiveFromClient;
          sendData();
          clientSkt.close();
          sskt.close();
      }
      catch (SecurityException se) {
          System.err.println(se.getMessage());
      }
      catch (SocketTimeoutException ste) {
          System.err.println(ste.getMessage());
      }
      catch (IllegalBlockingModeException ibme) {
          System.err.println(ibme.getMessage());
      }
      catch (IllegalArgumentException iae) {
          System.err.println("Invalid port.");
      }
      catch (IOException ioe) {
          System.err.println(ioe.getMessage());
      }

    }
    /**
     *  Currently no implementation.
     */
    public void receiveData() {
        try {
            this.dataToReceiveFromClient = (ClackData) inFromClient.readObject();
            System.out.println(this.dataToReceiveFromClient.toString());
            if (this.dataToReceiveFromClient.getType() == 1) {
                this.closeConnection = true;
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
     *  Currently no implementation.
     */
    public void sendData() {
        try {
            outToClient.writeObject(this.dataToSendToClient);
            outToClient.flush();
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
     *  Getter for port.
     *  @return port    returns the port.
     */
    public int getPort() {
        return port;
    }
    /**
     *  Overridden hashCode() method.
     *  @return result  returns the hashcode.
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31*result+this.getPort();
        return result;
    }
    /**
     *  Overridden equals() method.
     *  @param other    object to compare to.
     *  @return     returns the boolean value for the comparison.
     */
    @Override
    public boolean equals(Object other) {
        ClackServer otherServer = (ClackServer)other;
        return this.getPort() == otherServer.getPort();
    }
    /**
     *  Overridden toString() method.
     *  @return     returns the object as a string.
     */
    @Override
    public String toString() {
        return "This instance of ClackServer has the following properties:\n"
                + "Port number: " + this.port + "\n"
                + "Connection status: " + (this.closeConnection ? "Closed" : "Open") + "\n"
                + "Data to receive from the client: " + this.dataToReceiveFromClient + "\n"
                + "Data to send to the client: " + this.dataToSendToClient + "\n";
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            int argport = Integer.parseInt(args[0]);
            ClackServer pServer = new ClackServer(argport);
        }
        else {
            ClackServer sServer = new ClackServer(DEFAULT_PORT);
        }
    }
}
