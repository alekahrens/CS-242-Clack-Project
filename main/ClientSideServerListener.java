package main;

/**
 * ClientSideServerListener is a class that implements Runnable to be used for the data transfer. It contains:
 * The client to be used in the form of a ClackClient object.
 */

public class ClientSideServerListener implements Runnable {

    private ClackClient client;

    /**
     * Constructor with all arguments. Sets the client instance variable to the ClackClient object passed as a
     * parameter.
     * @param client    The client that will be used.
     */
    public ClientSideServerListener (ClackClient client) {
        this.client = client;
    }

    /**
     * Overridden run method. While the connection is open, the while loop will call the methods receiveData()
     * and printData() from the client.
     */
    @Override
    public void run() {
       while(!client.getCloseConnection()) {
            client.receiveData();
            client.printData();
            if(client.getCloseConnection()) {
                break;
            }
       }
    }
}
