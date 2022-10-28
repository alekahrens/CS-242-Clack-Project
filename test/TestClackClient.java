package test;

import main.ClackClient;

public class TestClackClient {
    public static void main(String[] args) {
        ClackClient cClient = new ClackClient("ahrensaj", "clarkson", 1024);
        cClient.start();
        ClackClient cClient2 = new ClackClient("peetsca", "potsdam", 1025);
        ClackClient cClient3 = new ClackClient("millerm");
        ClackClient cClient4 = new ClackClient("ahrensaj", "clarkson", 1204);
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
