package main.test;

import main.main.ClackServer;

public class TestClackServer {
    public static void main(){
        ClackServer cServer = new ClackServer(22);
        ClackServer cServer2 = new ClackServer(22);
        ClackServer cServer3 = new ClackServer();
        ClackServer cServer4 = new ClackServer(-22);

        System.out.println(cServer.getPort());
        System.out.println(cServer.hashCode());
        System.out.println(cServer.equals(cServer3));
        System.out.println(cServer.equals(cServer2));
        System.out.println(cServer.toString());

        System.out.println(cServer3.getPort());
        System.out.println(cServer3.hashCode());
        System.out.println(cServer3.equals(cServer2));
        System.out.println(cServer3.toString());

        System.out.println(cServer4.getPort());
        System.out.println(cServer4.hashCode());
        System.out.println(cServer4.equals(cServer2));
        System.out.println(cServer4.toString());

    }
}
