
package main;
import data.ClackData;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.channels.IllegalBlockingModeException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *  ClackServer represents the server. It contains:
 *  The port number.
 *  A boolean representing whether the connection is open or closed.
 *  Data to receive from the client in the form of a ClackData object.
 *  Data to send to the client in the form of a ClackData object.
 *  An ArrayList of ServerSideClientIO objects.
 */

public class ClackServer {
    private static final int DEFAULT_PORT = 7000;
    
    private int port;
    private boolean closeConnection;

    private ClackData dataToReceiveFromClient;
    private ClackData dataToSendToClient;

    private ArrayList<ServerSideClientIO> serverSideClientIOList;

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
            this.serverSideClientIOList = new ArrayList<>();
        }

    }
    /**
     *  Constructor with no arguments. Sets default port to 7000.
     */
    public ClackServer() {
        this(DEFAULT_PORT);
       
    }
    /**
     *  Start begins by initalizing a new ServerSocket using the port of the ClackServer object.
     *  Then, while the connection is open, a client socket will be created alongside a ServerSideClientIO,
     *  with the latter being added to a list. A new thread will be created using the SSCIO and started. Upon
     *  the connection being closed, the loop will stop and the socket will close.
     */
    public void start() {
        try {
            ServerSocket sskt = new ServerSocket(this.port);

            while (!this.closeConnection) {
                Socket clientSkt = sskt.accept();
                ServerSideClientIO serverSideClientIO = new ServerSideClientIO(this, clientSkt);
                serverSideClientIOList.add(serverSideClientIO);
                Thread thread = new Thread(serverSideClientIO);
                thread.start();
            }
            sskt.close();
        }

      catch (StreamCorruptedException sce) {
            closeConnection = true;
          System.err.println("StreamCorruptedException thrown in start(): " + sce.getMessage());

      } catch (IOException ioe) {
            closeConnection = true;
          System.err.println("IOException thrown in start(): " + ioe.getMessage());

      } catch (SecurityException se) {
          System.err.println("SecurityException thrown in start(): " + se.getMessage());

      } catch (IllegalArgumentException iae) {
          System.err.println("IllegalArgumentException thrown in start(): " + iae.getMessage());
      }

    }
    /**
     * Broadcast is a synchronized method that will iterate through the list of ServerSideClientIO objects
     * and set the data to be sent to the client. Then it will call the sendData() method to send the data to
     * the clients.
     * @param dataToBroadcastToClients      The data that will be sent to the clients.
     */
    public synchronized void broadcast(ClackData dataToBroadcastToClients) {
        for (ServerSideClientIO serverSideClientIO : serverSideClientIOList) {
            serverSideClientIO.setDataToSendToClient(dataToBroadcastToClients);
            serverSideClientIO.sendData();
        }
    }

    /**
     * Remove is a synchronized method that will remove a client from the ServerSideClientIO list, indicated
     * by the argument that is passed.
     * @param serverSideClientIO    The ServerSideClientIO object that represents the client to be removed.
     */
    public synchronized void remove(ServerSideClientIO serverSideClientIO) {
        this.serverSideClientIOList.remove(serverSideClientIO);
    }

    /**
     * GetUsers() is a method that will create a new ArrayList to store all the usernames of the active clients,
     * collected by iterating through the serverSideClientIOList and using the getUserName() method. It will then
     * join the list into a string that will be later passed to a MessageClackData constructor.
     * @return userListString       The string form of the list of usernames.
     */
    public synchronized String GetUsers() {
        ArrayList<String> userList = new ArrayList<>();
        for (int i = 0; i < this.serverSideClientIOList.size(); i++) {
            userList.add(serverSideClientIOList.get(i).getUserName());
        }
        String userListString = String.join(",", userList);
        return userListString;
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
                + "Connection status: " + (this.closeConnection ? "Closed" : "Open") + "\n";

    }

    public static void main(String[] args) {
        ClackServer clackServer;

        if (args.length == 0) {
            clackServer = new ClackServer();
        }
        else {
            int port = Integer.parseInt(args[0]);
            clackServer = new ClackServer(port);

        }
        clackServer.start();
    }
}
