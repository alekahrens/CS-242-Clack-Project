package test;

import main.ClackClient;

public class TestClackClient {
    public static void main(String[] args) {
        /**Test cases for CLackClient receiving data and encrypting it and decrypting it*/
        ClackClient cClient = new ClackClient("ahrensaj", "clarkson", 1024);
        cClient.start();
        ClackClient cClient2 = new ClackClient("peetsca", "potsdam", 1025);
        cClient2.start();
        ClackClient cClient3 = new ClackClient("millerm");
        cClient3.start();
        ClackClient cClient4 = new ClackClient("okeefed", "clarkson");
        cClient4.start();
        ClackClient cClient5 = new ClackClient();
        cClient5.start();
        ClackClient cClient6 = new ClackClient("linr", "clarkson", 1023);
        cClient6.start();
        ClackClient cClient7 = new ClackClient(null, "clarkson", 1024);
        cClient7.start();
        ClackClient cClient8 = new ClackClient("linr", null, 1024);
        cClient8.start();
        /** Test Cases for ClackClient */
        System.out.println(cClient.getUserName());
        System.out.println(cClient.getHostName());
        System.out.println(cClient.getPort());
        System.out.println(cClient.equals(cClient2));
        System.out.println(cClient.equals(cClient4));
        System.out.println(cClient.toString());
        System.out.println(cClient3.getUserName());
        System.out.println(cClient3.getHostName());
        System.out.println(cClient3.getPort());
        System.out.println(cClient3.equals(cClient2));
        System.out.println(cClient3.toString());
        System.exit(0);
    }
}