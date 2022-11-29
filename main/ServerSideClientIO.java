package main;

import data.ClackData;
import data.MessageClackData;

import java.io.*;
import java.net.Socket;

public class ServerSideClientIO implements Runnable {
    private boolean closeConnection;

    private ClackData dataToReceiveFromClient;

    private ClackData dataToSendToClient;

    private ObjectInputStream inFromClient;

    private ObjectOutputStream outToClient;

    private ClackServer server;

    private Socket clientSocket;

    public ServerSideClientIO (ClackServer server, Socket clientSocket) {
            this.server = server;
            this.clientSocket = clientSocket;
            this.closeConnection = false;
            this.dataToReceiveFromClient = null;
            this.dataToSendToClient = null;
            this.inFromClient = null;
            this.outToClient = null;
    }


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

    public void receiveData() {
        try {
            this.dataToReceiveFromClient = (ClackData) this.inFromClient.readObject();
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

    public String getUserName() {
        return this.dataToReceiveFromClient.getUserName();
    }

    public void setDataToSendToClient(ClackData dataToSendToClient) {
        this.dataToSendToClient = dataToSendToClient;
    }
}
