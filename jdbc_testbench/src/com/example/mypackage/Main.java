package com.example.mypackage;

import utils.DBConnection;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("Hey");
        DBConnection.startConnection();
        pause(2);
        DBConnection.endConnection();
    }

    private static void pause(int seconds) {

        System.out.println("Pausing for 2 seconds");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
