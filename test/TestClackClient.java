package main.test;

import main.main.ClackClient;

public class TestClackClient {

    public static void main(){
        ClackClient cClient = new ClackClient("ahrensaj", "clarkson", 22);
        ClackClient cClient2 = new ClackClient("peetsca", "potsdam", 22);
        ClackClient cClient3 = new ClackClient("millerm");
        ClackClient cClient4 = new ClackClient("ahrensaj", "clarkson", 22);
        //Test Cases for ClackClient
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

    }
}
