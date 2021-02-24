package main;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.util.Random;

public class Utils {
    static Random random = new Random();

    public int add(int a, int b){
        return a + b;
    }

    public boolean CheckPassword(String user, String password) {
        return true;

        // normally this would create a hash of the password,
        // and then check the password table to see if the hash maps

//        Boolean successfulLoginAttempt = (user.equals("admin") && password.equals("admin"));
//        return successfulLoginAttempt;
    }

    public String getRandomColor(){
        // create a big random number - maximum is ffffff (hex) = 16777215 (dez)
        int nextInt = random.nextInt(0xffffff + 1);

        // format it as hexadecimal string (with hashtag and leading zeros)
        String colorCode = String.format("#%06x", nextInt);

        return colorCode;
    }
}
