package utils;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

public class Utils {
    static Random random = new Random();

    public int add(int a, int b) {
        return a + b;
    }

    public LocalDateTime TimeStamp_to_LocalDateTime(Timestamp ts){
        if(ts != null){
            return ts.toLocalDateTime();
        } else {
            return null;
        }
    }

    public boolean CheckPassword(String user, String password) {
        return true;

        // normally this would create a hash of the password,
        // and then check the password table to see if the hash maps

//        Boolean successfulLoginAttempt = (user.equals("admin") && password.equals("admin"));
//        return successfulLoginAttempt;
    }

    public String getRandomColor() {
        // create a big random number - maximum is ffffff (hex) = 16777215 (dez)
        int nextInt = random.nextInt(0xffffff + 1);

        // format it as hexadecimal string (with hashtag and leading zeros)
        String colorCode = String.format("#%06x", nextInt);

        return colorCode;
    }

    public void WriteLoginAttempt(String userName, boolean success) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String datetime = simpleDateFormat.format(date);
        System.out.println(datetime);
        String successString = success ? "SUCCESS" : "FAILURE";
        String lineToLog = datetime + " " + successString + " " + userName;
        try {
            FileWriter fw = new FileWriter("D:/login_activity.txt", true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(lineToLog);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}