package test;

import data.FileClackData;
import data.MessageClackData;

public class TestClackData {
    public static void main(String[] args){
        MessageClackData mcTest = new MessageClackData("ahrensaj", "Hello_World", 3);
        MessageClackData mcTest2 = new MessageClackData("ahrensa", "Hello_World", 3);
        MessageClackData mcTest3 = new MessageClackData();
        FileClackData fcTest = new FileClackData("peetsca", "CS_242", 2);
        FileClackData fcTest2 = new FileClackData("peetsca", "CS_242", 2);
        FileClackData fcTest3 = new FileClackData();

        //Test cases for MessageClackData
        System.out.println(mcTest.getData());
        System.out.println(mcTest.equals(mcTest2));
        System.out.println(mcTest.equals(mcTest3));
        System.out.println(mcTest.hashCode());
        System.out.println(mcTest.toString());

        System.out.println(mcTest3.getData());
        System.out.println(mcTest3.equals(mcTest2));
        System.out.println(mcTest3.hashCode());
        System.out.println(mcTest3.toString());

        //Test cases for FileClackData
        System.out.println(fcTest.getFileName());
        System.out.println(fcTest.getData());
        System.out.println(fcTest.hashCode());
        System.out.println(fcTest.equals(fcTest3));
        System.out.println(fcTest.equals(fcTest2));
        System.out.println(fcTest.toString());

        fcTest.setFileName("Changing File Name");
        System.out.println(fcTest.getFileName());

        System.out.println(fcTest3.getFileName());
        System.out.println(fcTest3.getData());
        System.out.println(fcTest3.hashCode());
        System.out.println(fcTest3.equals(fcTest2));
        System.out.println(fcTest3.toString());

    }

}